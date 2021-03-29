//Homework Problem 3
//Darci Martin
//February 15th 2018
//Mac Computer with Eclipse Compiler
//A program to generate a Sudoku puzzle and allow the user to play the puzzle.
//Each puzzle is a 9x9 2-Dim array of integers
//This is an updated version with a separate Sudoku class

import java.util.Scanner;

public class Prog3 {
	static final Scanner input = new Scanner(System.in); //for user input

	public static void main(String[] args) {

		Sudoku puzzle1 = new Sudoku(true); //Sudoku object variable
		boolean playAgain; //boolean for the loop if a user wants to play a new puzzle
		
		//loop for one entire run of the game
		do {
			playSudoku(puzzle1); //play the puzzle
			playAgain = wantsToContinue("\nPlay another puzzle? "); //ask user if they wish to play again
			if (playAgain) {
				puzzle1.resetPuzzle(true); //only reset puzzle if desired by the user
			}
		}
		while (playAgain);

	}
	
	// ----------------playSudoku-------------------
		//This method allows the user to play a Sudoku puzzle
		//The puzzle continues until the player finishes or selects to stop playing
		//In each loop the puzzle is displayed, the user enters a value, it is compared
		//	and if the input is invalid it displays an invalid message, if the input
		//	is valid it displays a valid message, and for either valid or invalid input the 
		//	user is asked if they wish to keep playing.
		//Once the puzzle is complete a "finish" message appears and the loop breaks exiting back
		//	to main
		
		public static void playSudoku(Sudoku puzzle) {
			boolean keepPlaying; //to hold the user input to keep playing
			String status; //status to use if valid input
			boolean finished; //used if the puzzle is finished
			do {
				puzzle.displayPuzzle();
				status = setPuzzleSpot(puzzle); //set valid or invalid status
				System.out.println(status);
				if (status == "Number placed!") {
					finished = puzzle.comparePuzzles(); //compare if puzzle is finished
					if (finished) {
						System.out.println("You finished the puzzle!!!");
						break; //exit to main
					}
				}
				keepPlaying = wantsToContinue("Keep playing?");
			} while (keepPlaying); //repeat or exit playing loop
		} //end playSudoku
	
	// ----------------wantsToContinue-------------------
	// Method to output whether a user wants to continue
	
	public static boolean wantsToContinue(String prompt){
		String answer;
		System.out.print(prompt + " (y for yes): ");
		answer = input.next();
		return answer.charAt(0)=='y' || answer.charAt(0)=='Y';
	} // end wantsToContinue	
	
	// ----------------setPuzzleSpot-------------------
	// This method reads in from the user and passes the 
	//		row, column, and value to the setOneSpot method
	//		for verification. It returns the status as a string.
		
		public static String setPuzzleSpot(Sudoku puzzle)
		{
			int row, col, value;
			String status;
			
			System.out.print("Enter row, column, and value to set (sep. by a space): ");
			row = input.nextInt() - 1; // adjust for 0 through 8
			col = input.nextInt() - 1; // adjust for 0 through 8
			value = input.nextInt();
			
			status = puzzle.setOneSpot(row, col, value);
			return status;

		} // end setPuzzleSpot

}

//Sample Output
//Invalid Input:
/*
  | 1  2  3  | 4  5  6  | 7  8  9
----------------------------------
1 | 9     5  |          |    6  7  
2 |    6  7  | 5     8  | 2  1     
3 |    1  3  |    4     | 9     5  
----------------------------------
4 | 3  2  6  |       4  |    9  1  
5 | 5  9  1  | 6  3  2  | 7  4  8  
6 | 7  4  8  | 1  5  9  |          
----------------------------------
7 | 6  3     | 9  8  7  | 1  5     
8 |       2  | 4        | 8  7  9  
9 |    7     | 2        | 6     4  

Enter row, column, and value to set (sep. by a space): 1 1 9
You can't place that value there!
Keep playing? (y for yes): y
  | 1  2  3  | 4  5  6  | 7  8  9
----------------------------------
1 | 9     5  |          |    6  7  
2 |    6  7  | 5     8  | 2  1     
3 |    1  3  |    4     | 9     5  
----------------------------------
4 | 3  2  6  |       4  |    9  1  
5 | 5  9  1  | 6  3  2  | 7  4  8  
6 | 7  4  8  | 1  5  9  |          
----------------------------------
7 | 6  3     | 9  8  7  | 1  5     
8 |       2  | 4        | 8  7  9  
9 |    7     | 2        | 6     4  

Enter row, column, and value to set (sep. by a space): 1 2 1
You can't place that value there!
Keep playing? (y for yes): y
  | 1  2  3  | 4  5  6  | 7  8  9
----------------------------------
1 | 9     5  |          |    6  7  
2 |    6  7  | 5     8  | 2  1     
3 |    1  3  |    4     | 9     5  
----------------------------------
4 | 3  2  6  |       4  |    9  1  
5 | 5  9  1  | 6  3  2  | 7  4  8  
6 | 7  4  8  | 1  5  9  |          
----------------------------------
7 | 6  3     | 9  8  7  | 1  5     
8 |       2  | 4        | 8  7  9  
9 |    7     | 2        | 6     4  

Enter row, column, and value to set (sep. by a space): 1 4 1
You can't place that value there!
*/

//Valid Input:
/*
  | 1  2  3  | 4  5  6  | 7  8  9
----------------------------------
1 | 9     5  |          |    6  7  
2 |    6  7  | 5     8  | 2  1     
3 |    1  3  |    4     | 9     5  
----------------------------------
4 | 3  2     |       4  |    9  1  
5 | 5  9  1  | 6  3  2  | 7  4     
6 | 7  4  8  |    5  9  |          
----------------------------------
7 | 6  3     | 9  8  7  | 1  5     
8 |       2  | 4        | 8  7  9  
9 |    7     | 2        | 6     4  

Enter row, column, and value to set (sep. by a space): 4 3 6
Number placed!
Keep playing? (y for yes): y
  | 1  2  3  | 4  5  6  | 7  8  9
----------------------------------
1 | 9     5  |          |    6  7  
2 |    6  7  | 5     8  | 2  1     
3 |    1  3  |    4     | 9     5  
----------------------------------
4 | 3  2  6  |       4  |    9  1  
5 | 5  9  1  | 6  3  2  | 7  4     
6 | 7  4  8  |    5  9  |          
----------------------------------
7 | 6  3     | 9  8  7  | 1  5     
8 |       2  | 4        | 8  7  9  
9 |    7     | 2        | 6     4  

Enter row, column, and value to set (sep. by a space): 5 9 8
Number placed!
Keep playing? (y for yes): y
  | 1  2  3  | 4  5  6  | 7  8  9
----------------------------------
1 | 9     5  |          |    6  7  
2 |    6  7  | 5     8  | 2  1     
3 |    1  3  |    4     | 9     5  
----------------------------------
4 | 3  2  6  |       4  |    9  1  
5 | 5  9  1  | 6  3  2  | 7  4  8  
6 | 7  4  8  |    5  9  |          
----------------------------------
7 | 6  3     | 9  8  7  | 1  5     
8 |       2  | 4        | 8  7  9  
9 |    7     | 2        | 6     4  

Enter row, column, and value to set (sep. by a space): 6 4 1
Number placed!
Keep playing? (y for yes): 
*/

//Quitting Before Solving:
/*
  | 1  2  3  | 4  5  6  | 7  8  9
----------------------------------
1 | 6     5  | 9  4     | 7  3  1  
2 | 7  3  1  | 6  2  5  | 9        
3 | 9  4     |          |    2     
----------------------------------
4 | 1        |          | 8  9  3  
5 | 8        | 1  7  2  | 5        
6 | 5     4  | 8  9  3  | 1  7     
----------------------------------
7 |       6  | 4     9  |       7  
8 | 3  8  7  | 2  1     | 4     9  
9 | 4  5  9  | 3     7  |    1  6  

Enter row, column, and value to set (sep. by a space): 1 2 2
Number placed!
Keep playing? (y for yes): n

Play another puzzle?  (y for yes): n
*/

//Solving the Puzzle:
/*
  | 1  2  3  | 4  5  6  | 7  8  9
----------------------------------
1 | 9  8  5  | 3  2  1  | 4  6  7  
2 | 4  6  7  | 5  9  8  | 2  1  3  
3 | 2  1  3  | 7  4  6  | 9  8  5  
----------------------------------
4 | 3  2  6  | 8  7  4  | 5  9  1  
5 | 5  9  1  | 6  3  2  | 7  4  8  
6 | 7  4  8  | 1  5  9  | 3  2  6  
----------------------------------
7 | 6  3  4  | 9  8  7  | 1  5  2  
8 | 1  5  2  | 4     3  | 8  7  9  
9 | 8  7  9  | 2  1  5  | 6  3  4  

Enter row, column, and value to set (sep. by a space): 8 5 6
Number placed!
You finished the puzzle!!!

Play another puzzle?  (y for yes): 
*/
