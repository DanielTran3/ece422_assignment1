public class InsertionsortThread extends Thread{

	private int[] listToSort;
	private Insertionsort sorter;
	private float failureProbability;
	private int failFlag;

	public InsertionsortThread(int[] list_to_sort, float failChance) {
		this.listToSort = list_to_sort;
		this.sorter = new Insertionsort();
		this.failureProbability = failChance;
	}

	public void run() {

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

	public boolean threadFail() {
		if (this.failFlag == 0) {
			return true;

		}

		return false;
	}	
}
