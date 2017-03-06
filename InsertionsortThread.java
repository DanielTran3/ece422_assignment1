public class InsertionsortThread extends Thread{
	
	private int[] listToSort;
	private Insertionsort sorter;
	
	public InsertionsortThread(int[] list_to_sort) {
		this.listToSort = list_to_sort;
		this.sorter = new Insertionsort();
	}
	
	public void run() {
		
		try {
			sorter.insertSort(listToSort);
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
