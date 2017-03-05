
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.lang.*;

public class Data_Generator {

	public static void gen_numbers(String filename, int num_elements) {
		int random_number;
		int counter = 0;
		try {
			PrintWriter writer = new PrintWriter(filename + ".txt");

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

	public static boolean invalidFilename(String filename) {
		String pattern = "\\W";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(filename);

		if (m.find()) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {

		//System.out.println("Please Enter Two Inputs: Filename to write to and number of elements to generate");

		if (args.length != 2) {
			System.out.println("Please Enter Only Two Inputs: Filename to write to and number of elements to generate");
			System.exit(0);
		}

		String filename = args[0];
		String num_ints = args[1];

		System.out.println("Your Filename: " + filename);
		System.out.println("Your Number of Integers: " + num_ints);

		if (invalidFilename(filename)) {
			System.out.println("Please enter a valid file name");
			System.exit(0);
		}

		if (!num_ints.matches("[0-9]+")) {
			 System.out.println("Please enter a valid number of elements");
			 System.exit(0);
		}

		gen_numbers(filename, Integer.parseInt(num_ints));
	}
}
