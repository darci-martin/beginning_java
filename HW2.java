import java.util.Scanner;

//Homework Problem 2
//Darci Martin
//February 5th 2018
//Mac Computer with Eclipse Compiler
//A program to generate a Sudoku puzzle and allow the user to play the puzzle.
//Each puzzle is a 9x9 2-Dim array of integers

public class HW2 {
	static Scanner input = new Scanner(System.in); //for user input
	
	//declare class scope constant array of the original puzzle
	public static final int[][] temp = {{1,2,3,4,5,6,7,8,9}, {4,5,6,7,8,9,1,2,3}, {7,8,9,1,2,3,4,5,6}, {2,3,4,5,6,7,8,9,1}, {5,6,7,8,9,1,2,3,4}, {8,9,1,2,3,4,5,6,7}, {3,4,5,6,7,8,9,1,2}, {6,7,8,9,1,2,3,4,5}, {9,1,2,3,4,5,6,7,8}};

	public static void main(String[] args) {
		//allocate memory for the user puzzle and the puzzle answer
		int[][] userPuzzle = new int [9][9];
		int[][] puzzleAnswer = new int [9][9];
		//boolean for the loop if a user wants to play a new puzzle
		boolean playAgain; 
		//loop for one entire run of the game
		do {
			fillPuzzle(puzzleAnswer); //fill in the puzzle
			copyPuzzle(puzzleAnswer, userPuzzle); //copy the answer to the user puzzle
			//displayPuzzle(puzzleAnswer); System.out.println(); //used for testing puzzle
			setPuzzleHoles(userPuzzle); //put holes in the puzzle for the user
			playSudoku(userPuzzle, puzzleAnswer); //play the puzzle
			playAgain = wantsToContinue("\nPlay another puzzle? "); //ask user if they wish to play again
		}
		while (playAgain);
	} //end main
	
	// ----------------fillPuzzle-------------------
	//This method first copies the class scope array to the puzzle for this round
	//The first loop swaps a pseudo-random number 1 through 9 with the number
	//	of the current loop within the row for all rows.
	//The second loop swaps the first row of the section with another row in its 3 x 3 section
	//The third loop swaps the first column with another column in its 3 x 3 section
	
	public static void fillPuzzle (int[][] puzzle) {
		
		copyPuzzle(temp, puzzle); //using copyPuzzle method
		
		//swap numbers within a row
		for (int num = 1; num < 10; num++) {
			int pseudo = (int)(Math.random() * (9-1+1)) + 1;
			while (pseudo == num) {
				pseudo = (int)(Math.random() * (9-1+1)) + 1;
			}
			int numcolumn = -1; //to hold columns for swapping
			int pseudocolumn = -1; //to hold columns for swapping
			for (int row = 0; row < puzzle.length; row++) {
				for (int column = 0; column < puzzle[row].length; column++) {
					if (puzzle[row][column] == num) { numcolumn = column; }
					if (puzzle[row][column] == pseudo) { pseudocolumn = column;}
				}
				puzzle[row][numcolumn] = pseudo; //swap the num with the random number
				puzzle[row][pseudocolumn] = num; //swap the num with the random number
			}
		} //end numbers in row swap
		
		//swap rows within a section
		for (int row = 0; row < 7 ; row += 3) {
			int pseudo = (int)(Math.random() * (2-1+1)) + 1;
			int[] tempRow = new int[puzzle.length]; //temporary array to hold row for swap
			for (int i = 0; i < puzzle.length; i++) {
				tempRow[i] = puzzle[row][i]; //copy row to temp
			}
			for (int i = 0; i < puzzle.length; i++) {
				puzzle[row][i] = puzzle[row + pseudo][i]; //copy second row to first row
			}
			for (int i = 0; i < puzzle.length; i++) {
				puzzle[row + pseudo][i] = tempRow[i]; //copy temp to second row
			}
		} //end row swap
		
		//swap columns within a section
		for (int column = 0; column < 7 ; column += 3) {
			int pseudo = (int)(Math.random() * (2-1+1)) + 1;
			int[] tempColumn = new int[puzzle[0].length]; //temporary array to hold column for swap
			for (int i = 0; i < puzzle.length; i++) {
				tempColumn[i] = puzzle[i][column]; //copy column to temp
			}
			for (int i = 0; i < puzzle.length; i++) {
				puzzle[i][column] = puzzle[i][column + pseudo]; //copy second column to first column
			}
			for (int i = 0; i < puzzle.length; i++) {
				puzzle[i][column + pseudo] = tempColumn[i]; //copy temp to second column
			}
		} //end column swap
	} //end fillPuzzle
	
	// ----------------copyPuzzle-------------------
	//This method copies each position value from the first array parameter to the second
	
	public static void copyPuzzle(int [][] copyfrom, int [][] copyto) {
		//for loop to copy each element. checks length for both arrays
		for (int row = 0; row < copyto.length && row < copyfrom.length; row++) {
			for (int column = 0; column < copyto[row].length && column < copyfrom.length; column++) {
				copyto[row][column] = copyfrom[row][column];
			}
		}
	} //end copyPuzzle
	
	// ----------------displayPuzzle-------------------
	//This method displays the puzzles
	//Column numbers with dividers every 3 elements are first displayed
	//Each row displays the row number, then a divider, then the row values with dividers every 3
	//A dotted line is printed every 3 rows
	//If the value is a zero, then a blank spot is instead displayed
	
	public static void displayPuzzle(int [][] puzzle) {
		int value; //used when checking for zeroes
		System.out.println("  | 1  2  3  | 4  5  6  | 7  8  9");
		for (int row = 0; row < puzzle.length; row++) {
			if (row % 3 == 0) //checking to print the dotted line
				System.out.println("----------------------------------");
			System.out.print(row + 1 + " "); //for row numbers
			for (int column = 0; column < puzzle[row].length; column++) {
				if (column % 3 == 0) //checking to print the section dividers
					System.out.print("| ");
				value = puzzle[row][column]; //set value to check for zeroes
				if(value == 0)
					System.out.print(" " + "  "); //print out blanks instead of zeroes
				else
					System.out.print(puzzle[row][column] + "  ");
			}
			System.out.println();
		}
	} //end displayPuzzle
	
	// ----------------setPuzzleHoles-------------------
	//This method randomly sets values in the puzzle to zero (blanks)
	//A random number between 25 and 36 is calculated
	//A for loop is executed, randomly choosing a position on the 9x9 board and replacing with a zero
	//If the random location has already been set to zero it won't be set again
	//The loop counter only increments if it has successfully set a location to zero
	//The method is complete once it has reached the random number between 25 and 36
	
	public static void setPuzzleHoles(int [][] userpuzzle) {
		int numHoles = (int)(Math.random() * (36-25+1)) + 25;
		for (int i = 0; i < numHoles;) {
			int randomRow = (int)(Math.random() * (8-0+1)) + 0;
			int randomCol = (int)(Math.random() * (8-0+1)) + 0;
			if (userpuzzle[randomRow][randomCol] != 0) {
				userpuzzle[randomRow][randomCol] = 0;
				i++;
			}
		}
	} //end setPuzzleHoles
	
	// ----------------playSudoku-------------------
	//This method allows the user to play a Sudoku puzzle
	//The puzzle continues until the player finishes or selects to stop playing
	//In each loop the puzzle is displayed, the user enters a value, it is compared
	//	and if the input is invalid it displays an invalid message, if the input
	//	is valid it displays a valid message, and for either valid or invalid input the 
	//	user is asked if they wish to keep playing.
	//Once the puzzle is complete a "finish" message appears and the loop breaks exiting back
	//	to main
	
	public static void playSudoku(int [][] user, int [][] answer) {
		boolean keepPlaying; //to hold the user input to keep playing
		String status; //status to use if valid input
		boolean finished; //used if the puzzle is finished
		do {
			displayPuzzle(user);
			status = setPuzzleSpot(user); //set valid or invalid status
			System.out.println(status);
			if (status == "Number placed!") {
				finished = comparePuzzles(user, answer); //compare if puzzle is finished
				if (finished) {
					System.out.println("You finished the puzzle!!!");
					break; //exit to main
				}
			}
			keepPlaying = wantsToContinue("Keep playing?");
		} while (keepPlaying); //repeat or exit playing loop
	} //end playSudoku
	
	// ----------------setPuzzleSpot-------------------
	// This method tries to place a number in a spot, but returns
	//     an error message if the input is out of range or the value
	//     is already in the row, column or sub-square or not 0
	// If the input is valid, the value is assigned in the puzzle
	//      & "Number placed!" is returned
	
	public static String setPuzzleSpot(int [][] puzzle)
	{
		int row, col, value;
		int minVal = 1, maxVal = puzzle.length;
		
		System.out.print("Enter row, column, and value to set (sep. by a space): ");
		row = input.nextInt() - 1; // adjust for 0 through 8
		col = input.nextInt() - 1; // adjust for 0 through 8
		value = input.nextInt();
		if( row < 0 || row >= maxVal || col < 0 || col >= maxVal || value < minVal || value > maxVal){
			return "Invalid input, must be "+minVal + " to " + maxVal + ", inclusive!";
		}
		else if( !isValidPlacement(puzzle, row, col, value)){
			return "You can't place that value there!" ;
		}

		// place the value
		puzzle[row][col] = value;
		return "Number placed!";
	} // end setPuzzleSpot

	// ----------------isValidPlacement-------------------
	// This method will return false if 
	// 		the spot isn't 0, (must be blank)
	//		the value is already in the same row or column
	//		the value is already in the same sub-square
	// Otherwise, the method returns true
	
	public static boolean isValidPlacement(int [][] puzzle, int row, int col, int value) {
		if( puzzle[row][col] != 0 ) // not empty?
			return false;
		// check if in the same row and column
		for( int i=0; i < puzzle.length; ++i ){
			if( puzzle[i][col] == value || puzzle[row][i] == value)
				return false;
		} // end checking same row and column

		// check if in its sub-square
		int begRow, begCol, endRow, endCol;
		begRow = 3* (row/3);
		endRow = begRow + 3;
		begCol = 3* (col/3);
		endCol = begCol + 3;
		for( int subrow = begRow; subrow < endRow ; ++subrow ){
			for( int subcol = begCol; subcol < endCol; ++subcol )
				if( puzzle[subrow][subcol] == value )
					return false;
		} // end checking block
		return true;
	} // end isValidPlacement
	
	// ----------------comparePuzzles-------------------
	// This method compares the corresponding elements in the user puzzle
	//	and the answer puzzle and returns false if it finds anything
	//	different, and true if ALL are the same
	
	public static boolean comparePuzzles(int [][] user, int [][] answer) {
		for (int row = 0; row < user.length && row < answer.length; row++) {
			for (int column = 0; column < user[row].length && column < answer[row].length; column++) {
				if (user[row][column] != answer[row][column])
					return false; //immediately return false if anything is different
			} 
		} //end full comparison
		return true; //none are different so return true
	} // end comparePuzzles
	
	// ----------------wantsToContinue-------------------
	// Method to output whether a user wants to continue
	
	public static boolean wantsToContinue(String prompt){
		String answer;
		System.out.print(prompt + " (y for yes): ");
		answer = input.next();
		return answer.charAt(0)=='y' || answer.charAt(0)=='Y';
	} // end wantsToContinue	
}

//Sample Output
//Invalid Input:
/*
  | 1  2  3  | 4  5  6  | 7  8  9
----------------------------------
1 |    9  4  | 8  6  7  | 5  1  2  
2 | 2  5  1  | 9  4  3  | 8  6  7  
3 | 7  8  6  | 5  1  2  | 9  4  3  
----------------------------------
4 | 4  2  5  | 3  9  6  | 7  8  1  
5 | 1  7  8  | 2     4  | 3  9  6  
6 | 6  3  9  | 7  8  1  | 2  5  4  
----------------------------------
7 | 8     3  | 1  7  5  | 4  2  9  
8 | 9  4  2  | 6  3  8  | 1  7  5  
9 | 5  1  7  | 4  2  9  | 6  3  8  
Enter row, column, and value to set (sep. by a space): 1 1 4
You can't place that value there!
Keep playing? (y for yes): 
 */

//Valid Input:
/*
  | 1  2  3  | 4  5  6  | 7  8  9
----------------------------------
1 |    9  4  | 8  6  7  | 5  1  2  
2 | 2  5  1  | 9  4  3  | 8  6  7  
3 | 7  8  6  | 5  1  2  | 9  4  3  
----------------------------------
4 | 4  2  5  | 3  9  6  | 7  8  1  
5 | 1  7  8  | 2     4  | 3  9  6  
6 | 6  3  9  | 7  8  1  | 2  5  4  
----------------------------------
7 | 8     3  | 1  7  5  | 4  2  9  
8 | 9  4  2  | 6  3  8  | 1  7  5  
9 | 5  1  7  | 4  2  9  | 6  3  8  
Enter row, column, and value to set (sep. by a space): 5 5 5
Number placed!
Keep playing? (y for yes): 
*/

//Solving the Puzzle:
/*
  | 1  2  3  | 4  5  6  | 7  8  9
----------------------------------
1 | 3  9  4  | 8  6  7  | 5  1  2  
2 | 2  5  1  | 9  4  3  | 8  6  7  
3 | 7  8  6  | 5  1  2  | 9  4  3  
----------------------------------
4 | 4  2  5  | 3  9  6  | 7  8  1  
5 | 1  7  8  | 2  5  4  | 3  9  6  
6 | 6  3  9  | 7  8  1  | 2  5  4  
----------------------------------
7 | 8     3  | 1  7  5  | 4  2  9  
8 | 9  4  2  | 6  3  8  | 1  7  5  
9 | 5  1  7  | 4  2  9  | 6  3  8  
Enter row, column, and value to set (sep. by a space): 7 2 6
Number placed!
You finished the puzzle!!!

Play another puzzle?  (y for yes):  
*/
