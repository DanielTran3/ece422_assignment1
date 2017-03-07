import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data_Generator {

	// Empty constructor
	public Data_Generator() {}

	// Function to open a file and write random numbers into it
	public void gen_numbers(String filename, int num_elements) {
		int random_number;
		int counter = 0;
		try {
			PrintWriter writer = new PrintWriter(filename);

			while (counter < num_elements) {
				random_number = (int) (Math.random() * 10000 + 1);
				writer.println(Integer.toString(random_number));
				counter++;
			}
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to open filename");
		}
	}

	// Check for an invalid filename
	public boolean invalidFilename(String filename) {
		String pattern = "\\w\\.txt";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(filename);

		if (m.find()) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {

		// Check for two inputs
		if (args.length != 2) {
			System.out.println("Please Enter Only Two Inputs: Filename to write (ex. <\"filename\".txt>) to and number of elements to generate");
			System.exit(0);
		}

		// Receive the inputs
		String filename = args[0];
		String num_ints = args[1];

		System.out.println("Your Filename: " + filename);
		System.out.println("Your Number of Integers: " + num_ints);

		Data_Generator dg = new Data_Generator();

		// Check for a valid filename
		if (!dg.invalidFilename(filename)) {
			System.out.println("Please enter a valid file name: <\"filename\".txt>");
			System.exit(0);
		}

		// Check if the input for the number of random numbers to generate
		// contains only digits
		if (!num_ints.matches("[0-9]+")) {
			 System.out.println("Please enter a valid number of elements");
			 System.exit(0);
		}

		dg.gen_numbers(filename, Integer.parseInt(num_ints));
	}
}
