import java.util.Random;

public class Heapsort {

	private int num_mem_accesses = 0;
	
	public void swapper(int[] data_list, int pos1, int pos2) {
		int temp = data_list[pos1];
		data_list[pos1] = data_list[pos2];
		data_list[pos2] = temp;
		num_mem_accesses += 3;
	}

	// Function builds the heap as new nodes get added in.
	public void heapify(int[] data_list, int nodePos, int unsorted_array_size) {
		int largestElementPos = nodePos;
		int leftChildPos = 2 * nodePos + 1;
		int rightChildPos = 2 * nodePos + 2;

		// If the left child is larger than the root
		if ((leftChildPos < unsorted_array_size) &&
			 data_list[largestElementPos] < data_list[leftChildPos]) {
				num_mem_accesses += 2;
				largestElementPos = leftChildPos;
			 }
		if ((rightChildPos < unsorted_array_size) &&
			 data_list[largestElementPos] < data_list[rightChildPos]) {
				 largestElementPos = rightChildPos;
			 }

	 	if (largestElementPos != nodePos) {
			swapper(data_list, nodePos, largestElementPos);
			heapify(data_list, largestElementPos, unsorted_array_size);
		}
	}

	public int heapSort(int[] data, double probFail) {
		int size = data.length;

		// Build the heap up
		for (int node = (size/2) - 1; node >= 0; node--) {
			heapify(data, node, size);
		}

		// Fix the heap after removing max element from root and adding it to the
		// sorted section of the array
		for (int node = size - 1; node >= 0; node--) {
			swapper(data, 0, node);
			heapify(data, 0, node);
		}
		
		double hazard = probFail * num_mem_accesses;
		Random rFail = new Random();
		double randomValue = rFail.nextDouble();

		if ((randomValue >= 0.5) && (randomValue <= (0.5 + hazard))) {
			return 0;
		}
		
		return 1;
	}
}
