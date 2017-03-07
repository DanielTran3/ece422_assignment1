import java.util.TimerTask;

public class Watchdog extends TimerTask{
	Thread watched;
	Boolean stopped = false;

	public Watchdog(Thread target) {
		watched = target;
	}

	public void run() {
		watched.stop();
		stopped = true;
	}

	// Check if the watchdog timer had stopped the thread
	public Boolean hasStopped() {
		return stopped;
	}
}
