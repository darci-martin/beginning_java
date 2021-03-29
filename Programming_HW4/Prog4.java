//Homework Problem 4
//Darci Martin
//February 26th 2018
//Mac Computer with Eclipse Compiler
//A program to take a numeric input from the user, and add its own reverse
//value until a palindrome number is created.

import java.util.Scanner;

public class Prog4 {
	static Scanner input = new Scanner(System.in); //for user input
	
	public static void main(String[] args) {
		NumberString[] palindromeArray; //array of NumberString
		palindromeArray = createArray(); //call method to create Array
		display(palindromeArray); //print out the NumberString array
	} //end main
	
	// ----------------createArray-------------------
	//This method asks user for the number of palindromes to create.
	//The user is then prompted to provide the values.
	//The values are passed to the NumberString constructor for each element in the array.
	//The created NumberString array is returned.

	public static NumberString[] createArray() {
		String userValue; //for use when filling array
		
		System.out.print("How many numbers do you want to create palindromes? ");
		int size = input.nextInt();
		size = (size <= 0)? 1 : size ; //if user enters 0 or less, it is 1
		
		NumberString[] pArray = new NumberString[size]; //create array with specified size
		
		for (int i = 0; i < pArray.length; i++) {
			System.out.print("Enter number #" + (i + 1) + ": "); //Prompt user to input numbers
			userValue = input.next();
			//assign a new instance of a NumberString, passing string, to next element in array
			pArray[i] = new NumberString(userValue); 
		} //end for loop
		
		return pArray; //return the array
	} //end createArray
	
	// ----------------display-------------------
	//This method displays each element in the NumberString array
	
	public static void display(NumberString[]  param) {
		System.out.printf("%-20s%-20s\n","Number","Generated Palindrome"); //format output
		for (int i = 0; i < param.length; i++) {
			//call accessors to output the NumberString of the array
			System.out.printf("%-20s%-20s\n",param[i].getNumStr(),param[i].getNumStrPalin());
		} //end for loop
	} //end display
	
}

/*Sample Output:
RUN 1
How many numbers do you want to create palindromes? 5
Enter number #1: 13579
Enter number #2: 468
Enter number #3: 98
Enter number #4: 97568
Enter number #5: 545
Number              Generated Palindrome
13579               122221              
468                 3663                
98                  8813200023188       
97568               893974888888479398  
545                 545   

RUN 2
How many numbers do you want to create palindromes? 0
Enter number #1: 123123123
Number              Generated Palindrome
123123123           444444444   

RUN 3
How many numbers do you want to create palindromes? 8
Enter number #1: 99888
Enter number #2: 7
Enter number #3: -22
Enter number #4: one?
Enter number #5: 76.543
Enter number #6: 9898989898
Enter number #7: 98787
Enter number #8: 88
Number              Generated Palindrome
99888               12695991019959621   
7                   7                   
0                   Invalid Parameter   
0                   Invalid Parameter   
0                   Invalid Parameter   
9898989898          No Solution < 200 digits
98787               4456268448626544    
88                  88  
*/
