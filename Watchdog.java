import java.util.TimerTask;

public class Watchdog extends TimerTask{
	Thread watched;
	Boolean stopped;
	
	public Watchdog(Thread target) {
		watched = target;
	}
	
	public void run() {
		watched.stop();
		stopped = true;
		System.out.println("Time out!");
	}
	
	public Boolean hasStopped() {
		return stopped;
	}
}
