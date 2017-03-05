import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Heapsort {

	public void swapper(ArrayList<Integer> data_list, int pos1, int pos2) {
		int temp = data_list.get(pos1);
		data_list.set(pos1, data_list.get(pos2));
		data_list.set(pos2, temp);
	}

	// Function builds the heap as new nodes get added in.
	public void heapify(ArrayList<Integer> data_list, int nodePos, int unsorted_array_size) {
		int largestElementPos = nodePos;
		int leftChildPos = 2 * nodePos + 1;
		int rightChildPos = 2 * nodePos + 2;

		// If the left child is larger than the root
		if ((leftChildPos < unsorted_array_size) &&
			 data_list.get(largestElementPos) < data_list.get(leftChildPos)) {
				 largestElementPos = leftChildPos;
			 }
		if ((rightChildPos < unsorted_array_size) &&
			 data_list.get(largestElementPos) < data_list.get(rightChildPos)) {
				 largestElementPos = rightChildPos;
			 }

	 	if (largestElementPos != nodePos) {
			swapper(data_list, nodePos, largestElementPos);
			heapify(data_list, largestElementPos, unsorted_array_size);
		}
	}

	public void heapSort(ArrayList<Integer> data) {
		int size = data.size();

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

	public ArrayList<Integer> read_data(String filename) {
		ArrayList<Integer> data_list = new ArrayList<Integer>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			while (line != null) {
				data_list.add(Integer.valueOf(line));
				line = br.readLine();
			}
		}

		catch (IOException e) {
			System.out.println("Could not read data!");
			e.printStackTrace();
		}
		return data_list;
	}

	public static void main(String[] args) {
		Heapsort h = new Heapsort();
		ArrayList<Integer> data = new ArrayList<Integer>();
		data = h.read_data(args[0]);

		for (Integer i : data) {
			System.out.println(i.toString());
		}
		h.heapSort(data);
		System.out.println("-----------------------------------------------");

		for (Integer i : data) {
			System.out.println(i.toString());
		}
	}
}
