import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Data_Sorter {

	public Data_Sorter() {}

	// Read data into an int[] from a file
	public int[] read_data(String filename) {
		List<Integer> data_List = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();

			while (line != null) {
				data_List.add(Integer.valueOf(line));
				line = br.readLine();
			}
			br.close();
		}

		catch (IOException e) {
			System.out.println("Could not read data!");
			e.printStackTrace();
		}

		// Convert list into int[] (Java 8)
		int[] data_intArray = data_List.stream().mapToInt(i->i).toArray();

		return data_intArray;
	}

	// Write an int[] to a file
	public void write_data(String filename, int[] data) {
		try {
			PrintWriter writer = new PrintWriter(filename);

			for (int value : data) {
				writer.println(Integer.toString(value));
			}
			writer.close();
		}
		catch(IOException e) {
			System.out.println("Couldn't write to File!");
			e.printStackTrace();
		}
	}

	// Check for an invalid filename
	public boolean invalidFilename(String filename) {
		String pattern = "\\w\\.txt";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(filename);

		if (m.find()) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("Please Enter Only Four Inputs: Input Filename, Output Filename, Failure Probability, and Time Limit");
			System.exit(0);
		}

		// Check for a valid input filename
		if (!dg.invalidFilename(inputFilename)) {
			System.out.println("Please enter a valid file name: <\"filename\".txt>");
			System.exit(0);
		}

		// Check for a valid output filename
		if (!dg.invalidFilename(outputFilename)) {
			System.out.println("Please enter a valid file name: <\"filename\".txt>");
			System.exit(0);
		}

		// Check if the probability input contains numbers only
		if (!args[2].matches("[0-9.]+")) {
			 System.out.println("Please enter a valid number of elements");
			 System.exit(0);
		}

		// Check if the time is in numbers
		if (!args[3].matches("[0-9]+")) {
			 System.out.println("Please enter a valid number of elements");
			 System.exit(0);
		}
		// Read input parameters
		String inputFilename = args[0];
		String outputFilename = args[1];
		float failureProbability = Float.parseFloat(args[2]);
		int timeLimit_milli = Integer.parseInt(args[3]);

//			// Create executive, read the input data, create the adjudicator
		Data_Sorter exec = new Data_Sorter();
		int[] nums = exec.read_data(inputFilename);
		Adjudicator adj = new Adjudicator(nums);

		// Create Primary and run with watchdog timer
		HeapsortThread primaryThread = new HeapsortThread(nums, failureProbability);
		Timer sortingTimer = new Timer();
		Watchdog watchdogTimer = new Watchdog(primaryThread);
		sortingTimer.schedule(watchdogTimer, timeLimit_milli);
		primaryThread.start();

		// Join primary thread and stop timer
		try {
			primaryThread.join();
			sortingTimer.cancel();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Primary Thread Failed to Join");
		}

		// Check if either the primary thread failed, timed out, or failed the acceptance test
		if (primaryThread.threadFail() || watchdogTimer.hasStopped() || !adj.acceptanceTest(nums)) {
			if (primaryThread.threadFail()) {
				System.out.println("Primary Variant Failed");
			}
			else if (watchdogTimer.hasStopped()) {
				System.out.println("Primary Variant Failed to Complete On Time");
			}
			else if (!adj.acceptanceTest(nums)) {
				System.out.println("Primary Variant Failed Acceptance Test");
			}

			// Restore Checkpoint
			nums = exec.read_data(inputFilename);

			// Start backup thread (insertion sort)
			InsertionsortThread backupThread = new InsertionsortThread(nums, failureProbability);

			// Restart watchdog timer and schedule timer for the backup variant
			watchdogTimer = new Watchdog(backupThread);
			sortingTimer = new Timer();
			sortingTimer.schedule(watchdogTimer, timeLimit_milli);

			// Load the insertion sort native variable
			System.loadLibrary("insertionsort");

			// Start backup variant
			backupThread.start();

			// Join the backup thread and stop the timer
			try {
				backupThread.join();
				sortingTimer.cancel();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Backup Thread Failed to Join");
			}

			// Check if either the primary thread failed, timed out, or failed the acceptance test
			if (backupThread.threadFail() || watchdogTimer.hasStopped() || !adj.acceptanceTest(nums)) {
				if (backupThread.threadFail()) {
					System.out.println("Backup Variant Failed");
				}
				else if (watchdogTimer.hasStopped()) {
					System.out.println("Backup Variant Failed to Complete On Time");
				}
				else if (!adj.acceptanceTest(nums)) {
					System.out.println("Backup Variant Failed Acceptance Test");
				}

				// Terminate the program
				System.exit(0);
			}

			else {
				// Write the int[] from backup thread to a file
				exec.write_data(outputFilename, nums);
			}
		}
		else {
			// Write the int[] from primary thread to a file
			exec.write_data(outputFilename, nums);
		}
	}
}
