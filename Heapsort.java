import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Heapsort {

	// Re-heapify the heap after the largest element in the heap was removed
	void heapFilterDown(ArrayList<Integer> data_list, int root_pointer, int nodePos) {
		int leftChildPos;
		int rightChildPos;
		int temp;
		int rootPointerVal;
		int leftChildVal;
		int rightChildVal;

		while(2*root_pointer <= nodePos) {

			leftChildPos = 2*root_pointer + 1;
			rightChildPos = 2*root_pointer + 2;

			// Accessing element from sorted list, break
			if (leftChildPos > nodePos) {
				break;
			}

			rootPointerVal = data_list.get(root_pointer);
			leftChildVal = data_list.get(leftChildPos);
			rightChildVal = data_list.get(rightChildPos);

			// If the node is larger than its children, then break, as it is in the
			// correct position
			if (((leftChildPos >= nodePos)||(rightChildPos >= nodePos)) && (nodePos > 3)) {
					break;
			}

			// Find maximum of node
			if (rightChildPos <= nodePos) {

				// Node of root_pointer is smaller than left and right child
				// Then node is in correct position
				if ((rootPointerVal > leftChildVal) &&
					(rootPointerVal > rightChildVal)) {
					break;
				}
				// The node is larger than both children
				else {
					// If the right child is larger than the left
					if (leftChildVal > rightChildVal) {
						swapper(data_list, root_pointer, leftChildPos);
						root_pointer = leftChildPos;
					}

					//If left child is larger, swap the node and right child
					else {
						swapper(data_list, root_pointer, rightChildPos);
						root_pointer = rightChildPos;
					}
				}
			}
			// There is no right child
			else {
				// If the node is greater than the left child, then its position is correct
				if (rootPointerVal > leftChildVal) {
					break;
				}
				// Left child is greater than the node, so swap them
				else {
					swapper(data_list, root_pointer, leftChildPos);
					root_pointer = leftChildPos;
				}
			}
		}
	}

	public void swapper(ArrayList<Integer> data_list, int pos1, int pos2) {
		int temp = data_list.get(pos1);
		data_list.set(pos1, data_list.get(pos2));
		data_list.set(pos2, temp);
	}

	// Function builds the heap as new nodes get added in.
	public void heapFilterUp(ArrayList<Integer> data_list, int nodePos) {
		int parentPos;
		int temp;
		int nodeVal;
		int parentVal;

		while (nodePos != 0) {
			// division will drop fractional portion
			// example: 3.5 -> 3
			parentPos = (nodePos-1)/2;

			nodeVal = data_list.get(nodePos);
			parentVal = data_list.get(parentPos);

			// Swap the node with its parent if the added node is larger then the parent
			if (nodeVal > parentVal) {
				swapper(data_list, parentPos, nodePos);
				nodePos = parentPos;
			}

			else {
				break;
			}
		}

	}

	public void heapSort(ArrayList<Integer> data) {
		int i;
		int size = data.size();
		int node;
		int temp;

		// Build the heap up
		for (node = 0; node < size; node++) {
			heapFilterUp(data, node);
		}

		node--;
		// Fix the heap after removing max element from root and adding it to the
		// sorted section of the array
		for (i = 0; i < size; i++) {
			swapper(data, 0, node);
			node--;
			heapFilterDown(data, 0, node);
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
