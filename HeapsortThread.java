import java.util.ArrayList;

public class HeapsortThread extends Thread {

	private int[] listToSort;
	private Heapsort sorter;
	private float failureProbability;
	private int failFlag;

	public HeapsortThread(int[] list_to_sort, float failChance) {
		this.listToSort = list_to_sort;
		this.sorter = new Heapsort();
		this.failureProbability = failChance;
	}

	public void run() {

		// Run heapsort and set the flag to determine a fail or not
		try {
			failFlag = sorter.heapSort(listToSort, failureProbability);
			if (failFlag == 0) {
				Thread.currentThread().stop();
			}
		}

		catch (ThreadDeath td) {
			throw new ThreadDeath();
		}
	}

	// Return if the thread failed
	public boolean threadFail() {
		if (this.failFlag == 0) {
			return true;
		}

		return false;
	}
}
