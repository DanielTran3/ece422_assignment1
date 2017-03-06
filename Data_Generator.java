import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data_Generator {

	public Data_Generator() {}
	
	public void gen_numbers(String filename, int num_elements) {
		int random_number;
		int counter = 0;
		try {
			PrintWriter writer = new PrintWriter(filename);

			while (counter < num_elements) {
				random_number = (int) (Math.random() * 1000 + 1);
				writer.println(Integer.toString(random_number));
				counter++;
			}
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

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
		
//		if (args.length != 2) {
//			System.out.println("Please Enter Only Two Inputs: Filename to write (ex. <\"filename\".txt>) to and number of elements to generate");
//			System.exit(0);
//		}

		String filename;
		String num_ints;
		filename = args[0];
		num_ints = args[1];
		
		System.out.println("Your Filename: " + filename);
		System.out.println("Your Number of Integers: " + num_ints);

		Data_Generator dg = new Data_Generator();

		if (!dg.invalidFilename(filename)) {
			System.out.println("Please enter a valid file name: <\"filename\".txt>");
			System.exit(0);
		}

		if (!num_ints.matches("[0-9]+")) {
			 System.out.println("Please enter a valid number of elements");
			 System.exit(0);
		}

		dg.gen_numbers(filename, Integer.parseInt(num_ints));		
	}
}
