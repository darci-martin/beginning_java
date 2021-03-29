//Homework Problem 6
//Darci Martin
//March 18th 2018
//Mac Computer with Eclipse Compiler
//A program to take a numeric input from the user, and add its own reverse
//value until a palindrome number is created. Input comes from an input file,
//invalid output is printed to screen, and successfully created palindromes 
//are printed to an output file.

import java.io.*;
import java.util.*;

public class Prog6 {
	static Scanner input = new Scanner(System.in); //for user input
	
	public static void main(String[] args) {
		//Declare an ArrayList for NumberString
		ArrayList<NumberString> array = new ArrayList<>(); //create new ArrayList
		array = createArray(); //call method to input values from text file into array
		
		if (array.isEmpty()) { //array list is empty
			System.out.println("Nothing read, ending program"); //display message
			System.exit(0); //exit program
		} //end if
		
		Collections.sort(array); //call Collections sort method
		outputArray(array); //call method to write to output file

		if (array.size() > 0) { //if elements in array
			int index = array.size()/2; //find middle index
			System.out.println("The middle NumberString = " + array.get(index).getNumStr() + 
					": " + array.get(index).getNumStrPalin()); //output from middle index using .get
		} //end if
		
	} //end main
	
	// ----------------createArray-------------------
	//This method asks user for the file containing input for palindromes.
	//The values are passed to the NumberString constructor for each element read from file.
	//If constructor throws exception, out put the invalid input.
	//If no exception to the constructor, then add the NumberString to the ArrayList.
	
	public static ArrayList<NumberString> createArray() {
		ArrayList<NumberString> local= new ArrayList<>(); //create local ArrayList
		
		System.out.print("Enter the input filename: "); //user enters input file name
		String filename = input.nextLine(); //read whole line just in case
		
		java.io.File file = new java.io.File(filename);
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(file); //try to open the file
			while (scanner.hasNext()) { //check if end of file
				String temp = scanner.next(); //temp for next input
				try {
					NumberString tempObj = new NumberString(temp); //call constructor
					local.add(tempObj); //add if constructor was successful
				}
				catch (IllegalArgumentException ex) { //catch if constructor throws exception
					System.out.print("Invalid input in the file: " + temp + "\n"); //output invalid input to screen
				}
			} //end while
			scanner.close(); //close the input file
		}
		catch (FileNotFoundException ex) { //file cannot be found
			System.out.println("Can't open input file\n");
			return local; //return empty ArrayList
		}
		return local; //return filled ArrayList
	} //end createArray
	
	// ----------------outputArray-------------------
	//This method asks user for a file for output.
	//The ArrayList parameter is traversed with an Iterator, and each NumberString
	//	is output to the output file.

	public static void outputArray(ArrayList<NumberString> list) {
	
		System.out.print("Enter the output filename: "); //prompt user for output file name
		String filename = input.nextLine(); //read whole line just in case
	
		FileOutputStream outFile = null;
	
		try {
			outFile = new FileOutputStream(filename); //try to open file
			PrintWriter prtWriter = new PrintWriter(outFile,true); //for printing to file
			Iterator<NumberString> iterator = list.iterator(); //local iterator for ArrayList
			while (iterator.hasNext()) { //while next element in ArrayList
				NumberString nextOne = iterator.next(); //NumberString from iterator
				prtWriter.println(nextOne.getNumStr() + ": " + nextOne.getNumStrPalin()); //print to file
				} //end while
			prtWriter.close(); //close PrintWriter
			}
		catch (FileNotFoundException ex) { //file cannot be opened
			System.out.println("Can't open output file\n"); //output error message
		}
	} //end outputArray

}

/*Sample Output:
RUN 1
Enter the input filename: HW6TestInput1.txt
Enter the output filename: HW6TestOutput1.txt
The middle NumberString = 545: 545

RUN 2
Enter the input filename: HW6TestInput2.txt
Invalid input in the file: -22
Invalid input in the file: one?
Invalid input in the file: 76.543
Invalid input in the file: 35A
Enter the output filename: HW6TestOutput2.txt
The middle NumberString = 5678: 48884 

RUN 3
Enter the input filename: InvalidFile
Can't open input file

Nothing read, ending program
*/

