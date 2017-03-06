import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Data_Sorter {
	
	public Data_Sorter() {}
	
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
	
	public static void main(String[] args) {
//		if (args.length != 4) {
//			System.out.println("Please Enter Only Four Inputs: Input Filename, Output Filename, Failure Probability, and Time Limit");
//			System.exit(0);
//		}
		
//		int[] insertionSortedElements;
		String inputFilename;
		String outputFilename;
		double failureProbability;
		int timeLimit_seconds;
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Please Enter the Input Filename (ex. <\"filename\".txt>)");
			inputFilename = console.readLine();

			System.out.println("Please Enter the Output Filename (ex. <\"filename\".txt>)");
			outputFilename = console.readLine();

			System.out.println("Please Enter the Failure Probability(ex. 0.44)");
			failureProbability = Double.parseDouble(console.readLine());
			
			System.out.println("Please Enter the Time Limit in Seconds (ex. 5)");
			timeLimit_seconds = console.read();
			
			// Create executive, read the input data, create the adjudicator
			Data_Sorter exec = new Data_Sorter();
			int[] nums = exec.read_data(inputFilename);
			Adjudicator adj = new Adjudicator(nums);
			
			// Create Primary and run with watchdog timer
			HeapsortThread primaryThread = new HeapsortThread(nums);
			Timer sortingTimer = new Timer();
			Watchdog watchdogTimer = new Watchdog(primaryThread);
			sortingTimer.schedule(watchdogTimer, timeLimit_seconds * 1000);
			primaryThread.start();

			try {
				primaryThread.join();
				sortingTimer.cancel();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Error in Timing");
			}
			System.out.println("---------------------");
			if (watchdogTimer.hasStopped() /**|| !adj.acceptanceTest(nums)*/ /** || failure check here*/) {
				System.out.println("----Stopped----");
//				nums = exec.read_data(inputFilename);
//				Insertionsort backupThread = new Insertionsort();
//				watchdogTimer = new Watchdog(backupThread);
//				System.loadLibrary("insertionsort");
//				insertionSortedElements = backupThread.insertSort(nums);
//				sortingTimer.schedule(watchdogTimer, timeLimit_seconds * 1000);
//				backupThread.start();
			}
			else {
				exec.write_data(outputFilename, nums);
			}
			if (watchdogTimer.hasStopped() || !adj.acceptanceTest(nums) /** || failure check here*/) {
//				throw 
			}
			
		} catch (IOException inputError) {
			inputError.printStackTrace();
			System.out.println("Couldn't read line");
		}
	}
}

//Thread sortingThread = new Thread(new Data_Sorter(), "sortingThread");

// Another way to make a thread (inside main)
//Thread sortingThread = new Thread(new Runnable() {
//public void run() {
//	while(true) {
//		System.out.println("*** Hello from Sorting Thread ***");
//	}
//}
//});
//sortingThread.start();

// Another way, if the Data_Sorter class extends Thread
//public Data_Sorter(String threadname) {
//	super(threadname);
//	System.out.println(this);
//	start();
//}
//
//public void run() {
//	for (int i = 0; i < 20; i++) {
//		System.out.println(Thread.currentThread().getName());
//	}
//}
