import java.util.ArrayList;

public class HeapsortThread extends Thread{
	
	private ArrayList<Integer> listToSort;
	private Heapsort sorter;
	
	public HeapsortThread(ArrayList<Integer> list_to_sort) {
		this.listToSort = list_to_sort;
		this.sorter = new Heapsort();
	}
	
	public void run() {
		
		try {
			sorter.heapSort(listToSort);
			for (Integer i : listToSort) {
				System.out.println(i.toString());
			}
		}
		
		catch (ThreadDeath td) {
			System.out.println("Thread died");
			throw new ThreadDeath();
		}
	}
}
