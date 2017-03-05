import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Executive {
	
	public Executive() {
		
	}
	
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
	
	public static void main(String[] args) {
//		if (args.length != 4) {
//			System.out.println("Please Enter Only Four Inputs: Input Filename, Output Filename, Failure Probability, and Time Limit");
//			System.exit(0);
//		}
		

		Executive exec = new Executive();
		int[] nums = exec.read_data(args[0]);
//		ArrayList<Integer> insertionSortedElements = new ArrayList<Integer>();
		
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
		
//		Insertionsort is = new Insertionsort();
//		System.loadLibrary("sort");
//		insertionSortedElements = is.sort(nums);
	}
}

//Thread sortingThread = new Thread(new Executive(), "sortingThread");

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