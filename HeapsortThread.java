import java.util.ArrayList;

public class HeapsortThread extends Thread {
	
	private int[] listToSort;
	private Heapsort sorter;
	private double failureProbability;
	
	public HeapsortThread(int[] list_to_sort, double failChance) {
		this.listToSort = list_to_sort;
		this.sorter = new Heapsort();
		this.failureProbability = failChance;
	}
	
	public void run() {
		
		try {
			sorter.heapSort(listToSort, failureProbability);
			if (listToSort[0] != -1) {
				for (Integer i : listToSort) {
					System.out.println(i.toString());
				}	
			}
			
		}
		
		catch (ThreadDeath td) {
			System.out.println("Thread died");
			throw new ThreadDeath();
		}
	}
}
