
public class Executive extends Thread {
	
	public Executive() {
		
	}
	Thread sortingThreads = new Thread(new Runnable() {
		public void run() {
			
		}
	});

	Thread timerThread = new Thread(new Runnable() {
		public void run() {
			
		}
	});
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Please Enter Only Four Inputs: Input Filename, Output Filename, Failure Probability, and Time Limit");
			System.exit(0);
		}
	}
}
