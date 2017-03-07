public class InsertionsortThread extends Thread{

	private int[] listToSort;
	private Insertionsort sorter;
	private float failureProbability;
	// Initialize flag to a value that is not 1 or 0 to determine if 
	// a timeout occurred
	private int failFlag = 2;

	public InsertionsortThread(int[] list_to_sort, float failChance) {
		this.listToSort = list_to_sort;
		this.sorter = new Insertionsort();
		this.failureProbability = failChance;
	}

	public void run() {

		// Run insertionsort and set the flag to determine a fail or not
		try {
			failFlag = sorter.insertSort(listToSort, failureProbability);
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
