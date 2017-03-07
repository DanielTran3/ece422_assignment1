Name: Daniel Tran
ID: 1402478

To generate the data file, first run Data_Generator with two inputs: Filename of the file that will be produced (with extension .txt) and the number of elements to be generated
	(ex. java Data_Generator data.txt 100)
	 - Creates a file called data.txt that stores the 100 generated random numbers

To run the fault-tolerant data sorting driver, run Data_Sorter with four inputs: Input filename (file with the data), output filename (filename of the file that the sorted elements will be written to), the failure probability (as a decimal), and the time limit (measured in milliseconds)
	(ex. java Data_Sorter data.txt sorted_data.txt 0.4 1000)
	 - Reads from data.txt, writes to sorted_data.txt, probability of failing is 0.4, with a timeout of 1 second (1000 milliseconds)
