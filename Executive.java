import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

public class Executive {
	
	public Executive() {
		
	}
	
	public ArrayList<Integer> read_data(String filename) {
		ArrayList<Integer> data_list = new ArrayList<Integer>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			while (line != null) {
				data_list.add(Integer.valueOf(line));
				line = br.readLine();
			}
		}

		catch (IOException e) {
			System.out.println("Could not read data!");
			e.printStackTrace();
		}
		return data_list;
	}
	
	public static void main(String[] args) {
//		if (args.length != 4) {
//			System.out.println("Please Enter Only Four Inputs: Input Filename, Output Filename, Failure Probability, and Time Limit");
//			System.exit(0);
//		}
		
//		Thread sortingThread = new Thread(new Executive(), "sortingThread");
		Executive exec = new Executive();
		ArrayList<Integer> nums = exec.read_data(args[0]);
		
		HeapsortThread sortingThread = new HeapsortThread(nums);
		Timer sortingTimer = new Timer();
		Watchdog watchdogTimer = new Watchdog(sortingThread);
		sortingTimer.schedule(watchdogTimer, 1000);
		sortingThread.start();
		try {
			sortingThread.join();
			sortingTimer.cancel();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Error in Timing");
		}

		
		
//		while(true) {
//			System.out.println("From Main Thread");
//		}
	}
}

// Another way to make a thread (inside main)
//Thread sortingThread = new Thread(new Runnable() {
//public void run() {
//	while(true) {
//		System.out.println("*** Hello from Sorting Thread ***");
//	}
//}
//});
//sortingThread.start();

// Another way, if the Executive class extends Thread
//public Executive(String threadname) {
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