import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Heapsort {

	// Re-heapify the heap after the largest element in the heap was removed
	void heapFilterDown(ArrayList<Integer> data_list, int root_pointer, int nodePos) {
		int leftChild;
		int rightChild;
		int temp;
		int tempPointer;
		int tempLeftChild;
		int tempRightChild;

		while(2*root_pointer <= nodePos) {

			leftChild = 2*root_pointer + 1;
			rightChild = 2*root_pointer + 2;

			// Break the loop because this means that an element from the Sorted
			// array (portion of the list that is excluded from the heap) is being
			// accessed
			if (leftChild > nodePos) {
				break;
			}

			tempPointer = data_list.get(root_pointer);
			tempLeftChild = data_list.get(leftChild);

			// If the node is larger than its children, then break, as it is in the
			// correct position
			if (((leftChild >= nodePos)||(rightChild >= nodePos)) && (nodePos > 3)) {
					break;
			}

			// Find maximum of node
			if (rightChild <= nodePos) {

				tempRightChild = data_list.get(rightChild);

				// Node of root_pointer is smaller than left and right child
				// Then node is in correct position
				if ((tempPointer > tempLeftChild) &&
					 (tempPointer > tempRightChild)) {
					break;
				}
				// The node is larger than both children
				else {
					// If the right child is larger than the left
					if (tempLeftChild > tempRightChild) {
						temp = tempPointer;
						data_list.set(root_pointer, tempLeftChild);
						data_list.set(leftChild, temp);
						root_pointer = leftChild;
					}

					//If left child is larger, swap the node and right child
					else {
						temp = tempPointer;
						data_list.set(root_pointer, tempRightChild);
						data_list.set(rightChild, temp);
						root_pointer = rightChild;
					}
				}
			}
			// There is no right child
			else {
				// If the node is greater than the left child, then its position is correct
				if (tempPointer > tempLeftChild) {
					break;
				}
				// Left child is greater than the node, so swap them
				else {
					temp = tempPointer;
					data_list.set(root_pointer, tempLeftChild);
					data_list.set(leftChild, temp);
					root_pointer = leftChild;
				}
			}
		}
	}


	// Function builds the heap as new nodes get added in.
	public void heapFilterUp(ArrayList<Integer> data_list, int nodePos) {
		int parentPos;
		int temp;
		int tempNodePos;
		int tempParent;

		while (nodePos != 0) {
			// division will drop fractional portion
			// example: 3.5 -> 3
			parentPos = (nodePos-1)/2;

			tempNodePos = data_list.get(nodePos);
			tempParent = data_list.get(parentPos);

			// Swap the node with its parent if the added node is larger then the parent
			if (tempNodePos > tempParent) {
				temp = tempParent;
				data_list.set(parentPos, tempNodePos);
				data_list.set(nodePos, temp);

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
			temp = data.get(0);
			data.set(0, data.get(node));
			data.set(node, temp);

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
