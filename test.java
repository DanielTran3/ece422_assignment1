import java.util.Timer;

public class test {

	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("Please Enter Only Four Inputs: Input Filename, Output Filename, Failure Probability, and Time Limit");
			System.exit(0);
		}
		String inputFilename;
		String outputFilename;
		float failureProbability;
		int timeLimit_seconds;
		
		inputFilename = args[0];
		outputFilename = args[1];
		failureProbability = Float.parseFloat(args[2]);
		timeLimit_seconds = Integer.parseInt(args[3]);

		Data_Sorter exec = new Data_Sorter();
		int[] nums = exec.read_data(inputFilename);
		Adjudicator adj = new Adjudicator(nums);
		Timer sortingTimer = new Timer();
		nums = exec.read_data(inputFilename);
		InsertionsortThread backupThread = new InsertionsortThread(nums, failureProbability);
		Watchdog watchdogTimer = new Watchdog(backupThread);
		System.loadLibrary("insertionsort");
		sortingTimer.schedule(watchdogTimer, timeLimit_seconds * 1000);
		backupThread.start();
	
		try {
			backupThread.join();
			sortingTimer.cancel();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Error in Timing");
		}

		if (backupThread.threadFail() || watchdogTimer.hasStopped() || !adj.acceptanceTest(nums)) {
			if (backupThread.threadFail()) {
				System.out.println("Primary Sort Failed");
			}
			else if (watchdogTimer.hasStopped()) {
				System.out.println("Primary Sort Failed to Complete On Time");
			}
			else if (!adj.acceptanceTest(nums)) {
				System.out.println("Primary Sort Failed Acceptance Test");
			}
			System.out.println("FAIL");			
		}
		System.out.println("Success");	
		exec.write_data(outputFilename, nums);
	}
		
}
