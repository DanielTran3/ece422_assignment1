import java.util.ArrayList;

public class HeapsortThread extends Thread {
	
	private int[] listToSort;
	private Heapsort sorter;
	private double failureProbability;
	private int failFlag;
	
	public HeapsortThread(int[] list_to_sort, double failChance) {
		this.listToSort = list_to_sort;
		this.sorter = new Heapsort();
		this.failureProbability = failChance;
	}
	
	public void run() {
		
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
	
	public boolean threadFail() {
		if (this.failFlag == 0) {
			return true;
		}
		
		return false;
	}
}
