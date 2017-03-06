
public class Adjudicator {
	
	private int checksum;
	
	public Adjudicator(int[] data) {
		this.checksum = arraySum(data);
	}
	
	public int arraySum (int[] data) {
		int total = 0;
		for (int i = 0; i < data.length; i++) {
			total += data[i];
		}
		return total;
	}
	
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
	
	public boolean validChecksum (int[] data) {
		if (arraySum(data) == checksum) {
			return true;
		}
		return false;
	}
	
	public boolean acceptanceTest(int[] data) {
		if (isOrdered(data) && validChecksum(data)) {
			return true;
		}
		return false;
	}
}
