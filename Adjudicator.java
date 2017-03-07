public class Adjudicator {

	private int checksum;

	public Adjudicator(int[] data) {
		this.checksum = arraySum(data);
	}

	// Calculate the sum for the array as a checksum
	public int arraySum (int[] data) {
		int total = 0;
		for (int i = 0; i < data.length; i++) {
			total += data[i];
		}
		return total;
	}

	// Check if the array is ordered
	public boolean isOrdered (int[] data) {
		if ((data == null) || (data.length == 1)) {
			return true;
		}

		int currentLargestValue = data[0];

		for (int i = 1; i < data.length; i++) {
			if (currentLargestValue > data[i]) {
				return false;
			}
			currentLargestValue = data[i];
		}

		return true;
	}

	// Return weather or not the checksum is valid
	public boolean validChecksum (int[] data) {
		if (arraySum(data) == checksum) {
			return true;
		}
		return false;
	}

	// Return if the acceptance test passed
	public boolean acceptanceTest(int[] data) {
		if (isOrdered(data) && validChecksum(data)) {
			return true;
		}
		return false;
	}
}
