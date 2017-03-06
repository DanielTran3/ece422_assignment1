public class InsertionsortThread extends Thread{

	private int[] listToSort;
	private Insertionsort sorter;
	private double failureProbability;

	public InsertionsortThread(int[] list_to_sort, double failChance) {
		this.listToSort = list_to_sort;
		this.sorter = new Insertionsort();
		this.failureProbability = failChance;
	}

	public void run() {

		try {
			sorter.insertSort(listToSort, failureProbability);
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
