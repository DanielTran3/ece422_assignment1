import java.util.Random;

public class Heapsort {

	private int num_mem_accesses = 0;
	
	// Re-heapify the heap after the largest element in the heap was removed
	void heapFilterDown(int[] data_list, int root_pointer, int nodePos) {
		int leftChildPos;
		int rightChildPos;
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

			rootPointerVal = data_list[root_pointer];
			leftChildVal = data_list[leftChildPos];
			rightChildVal = data_list[rightChildPos];
			num_mem_accesses += 3;

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

	public void swapper(int[] data_list, int pos1, int pos2) {
		int temp = data_list[pos1];
		data_list[pos1] = data_list[pos2];
		data_list[pos2] = temp;
		num_mem_accesses += 3;
	}
	
	// Function builds the heap as new nodes get added in.
	public void heapFilterUp(int[] data_list, int nodePos) {
		int parentPos;
		int nodeVal;
		int parentVal;

		while (nodePos != 0) {
			// division will drop fractional portion
			// example: 3.5 -> 3
			parentPos = (nodePos-1)/2;

			nodeVal = data_list[nodePos];
			parentVal = data_list[parentPos];

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
	
	public void heapSort(int[] data, double probFail) {
		int i;
		int size = data.length;
		int node;

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
		
		double hazard = probFail * num_mem_accesses;
		Random rFail = new Random();
		double randomValue = rFail.nextDouble();
		System.out.println(hazard);
		System.out.println(randomValue);
		if ((randomValue >= 0.5) && (randomValue <= (0.5 + hazard))) {
			data[0] = -1;
			System.out.println(data.length);
		}
	}
}
