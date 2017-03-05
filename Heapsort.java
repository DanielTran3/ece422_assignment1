import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Heapsort {

	public void swapper(int[] data_list, int pos1, int pos2) {
		int temp = data_list[pos1];
		data_list[pos1] = data_list[pos2];
		data_list[pos2] = temp;
	}

	// Function builds the heap as new nodes get added in.
	public void heapify(int[] data_list, int nodePos, int unsorted_array_size) {
		int largestElementPos = nodePos;
		int leftChildPos = 2 * nodePos + 1;
		int rightChildPos = 2 * nodePos + 2;

		// If the left child is larger than the root
		if ((leftChildPos < unsorted_array_size) &&
			 data_list[largestElementPos] < data_list[leftChildPos]) {
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

	public void heapSort(int[] data) {
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
	}

	

//	public static void main(String[] args) {
//		Heapsort h = new Heapsort();
//		ArrayList<Integer> data = new ArrayList<Integer>();
//		data = h.read_data(args[0]);
//
//		for (Integer i : data) {
//			System.out.println(i.toString());
//		}
//		h.heapSort(data);
//		System.out.println("-----------------------------------------------");
//
//		for (Integer i : data) {
//			System.out.println(i.toString());
//		}
//	}
}
