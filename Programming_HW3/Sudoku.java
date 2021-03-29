
public class Sudoku {
	//declare class scope variable for size of puzzle
	public static final int SIZE = 9;
	//declare class scope constant array of the original puzzle
	public static final int[][] temp = {{1,2,3,4,5,6,7,8,9}, {4,5,6,7,8,9,1,2,3}, {7,8,9,1,2,3,4,5,6}, {2,3,4,5,6,7,8,9,1}, {5,6,7,8,9,1,2,3,4}, {8,9,1,2,3,4,5,6,7}, {3,4,5,6,7,8,9,1,2}, {6,7,8,9,1,2,3,4,5}, {9,1,2,3,4,5,6,7,8}};
	//allocate memory for the user puzzle and the puzzle answer as private instance variables
	private int[][] userPuzzle;
	private int[][] puzzleAnswer;
	
	//Constructor for the class
	public Sudoku(boolean display) {
		userPuzzle =  new int [SIZE][SIZE]; //allocate memory
		puzzleAnswer = new int [SIZE][SIZE]; //allocate memory
		fillPuzzle();
		copyPuzzle(puzzleAnswer, userPuzzle);
		setPuzzleHoles();
		if (display) {
			System.out.println("For debugging, the answer puzzle: ");
			display2DimArray(puzzleAnswer);
		}
	}
	
	// ----------------fillPuzzle-------------------
	//This method first copies the class scope array to the puzzle for this round
	//The first loop swaps a pseudo-random number 1 through 9 with the number
	//	of the current loop within the row for all rows.
	//The second loop swaps the first row of the section with another row in its 3 x 3 section
	//The third loop swaps the first column with another column in its 3 x 3 section
	
	private void fillPuzzle () {
		
		copyPuzzle(temp, puzzleAnswer); //using copyPuzzle method
		
		//swap numbers within a row
		for (int num = 1; num < 10; num++) {
			int pseudo = (int)(Math.random() * (9-1+1)) + 1;
			while (pseudo == num) {
				pseudo = (int)(Math.random() * (9-1+1)) + 1;
			}
			int numcolumn = -1; //to hold columns for swapping
			int pseudocolumn = -1; //to hold columns for swapping
			for (int row = 0; row < puzzleAnswer.length; row++) {
				for (int column = 0; column < puzzleAnswer[row].length; column++) {
					if (puzzleAnswer[row][column] == num) { numcolumn = column; }
					if (puzzleAnswer[row][column] == pseudo) { pseudocolumn = column;}
				}
				puzzleAnswer[row][numcolumn] = pseudo; //swap the num with the random number
				puzzleAnswer[row][pseudocolumn] = num; //swap the num with the random number
			}
		} //end numbers in row swap
		
		//swap rows within a section
		int[] tempRow = new int[puzzleAnswer.length]; //temporary array to hold row for swap
		for (int row = 0; row < 7 ; row += 3) {
			int pseudo = (int)(Math.random() * (2-1+1)) + 1;
			for (int i = 0; i < puzzleAnswer.length; i++) {
				tempRow[i] = puzzleAnswer[row][i]; //copy row to temp
			}
			for (int i = 0; i < puzzleAnswer.length; i++) {
				puzzleAnswer[row][i] = puzzleAnswer[row + pseudo][i]; //copy second row to first row
			}
			for (int i = 0; i < puzzleAnswer.length; i++) {
				puzzleAnswer[row + pseudo][i] = tempRow[i]; //copy temp to second row
			}
		} //end row swap
		
		//swap columns within a section
		int[] tempColumn = new int[puzzleAnswer[0].length]; //temporary array to hold column for swap
		for (int column = 0; column < 7 ; column += 3) {
			int pseudo = (int)(Math.random() * (2-1+1)) + 1;
			for (int i = 0; i < puzzleAnswer.length; i++) {
				tempColumn[i] = puzzleAnswer[i][column]; //copy column to temp
			}
			for (int i = 0; i < puzzleAnswer.length; i++) {
				puzzleAnswer[i][column] = puzzleAnswer[i][column + pseudo]; //copy second column to first column
			}
			for (int i = 0; i < puzzleAnswer.length; i++) {
				puzzleAnswer[i][column + pseudo] = tempColumn[i]; //copy temp to second column
			}
		} //end column swap
	} //end fillPuzzle
	
	// ----------------copyPuzzle-------------------
		//This method copies each position value from the first array parameter to the second
		
	private void copyPuzzle(int [][] copyfrom, int [][] copyto) {
			//for loop to copy each element. checks length for both arrays
			for (int row = 0; row < copyto.length && row < copyfrom.length; row++) {
				for (int column = 0; column < copyto[row].length && column < copyfrom.length; column++) {
					copyto[row][column] = copyfrom[row][column];
			}
		}
	} //end copyPuzzle
	
	// ----------------setPuzzleHoles-------------------
		//This method randomly sets values in the puzzle to zero (blanks)
		//A random number between 25 and 36 is calculated
		//A for loop is executed, randomly choosing a position on the 9x9 board and replacing with a zero
		//If the random location has already been set to zero it won't be set again
		//The loop counter only increments if it has successfully set a location to zero
		//The method is complete once it has reached the random number between 25 and 36
		
	private void setPuzzleHoles() {
			int numHoles = (int)(Math.random() * (36-25+1)) + 25;
			for (int i = 0; i < numHoles;) {
				int randomRow = (int)(Math.random() * (8-0+1)) + 0;
				int randomCol = (int)(Math.random() * (8-0+1)) + 0;
				if (userPuzzle[randomRow][randomCol] != 0) {
					userPuzzle[randomRow][randomCol] = 0;
					i++;
				}
			}
		} //end setPuzzleHoles
	
	// ----------------display2Dim Array-------------------
		//This method displays the puzzles
		//Column numbers with dividers every 3 elements are first displayed
		//Each row displays the row number, then a divider, then the row values with dividers every 3
		//A dotted line is printed every 3 rows
		//If the value is a zero, then a blank spot is instead displayed
		
	private void display2DimArray(int [][] puzzle) {
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
		System.out.println();
	} //end display2DimArray
	
	// ----------------comparePuzzles-------------------
	// This method compares the corresponding elements in the user puzzle
	//	and the answer puzzle and returns false if it finds anything
	//	different, and true if ALL are the same
	
	public boolean comparePuzzles() {
		for (int row = 0; row < this.userPuzzle.length && row < this.puzzleAnswer.length; row++) {
			for (int column = 0; column < this.userPuzzle[row].length && column < this.puzzleAnswer[row].length; column++) {
				if (this.userPuzzle[row][column] != this.puzzleAnswer[row][column])
					return false; //immediately return false if anything is different
			} 
		} //end full comparison
		return true; //none are different so return true
	} // end comparePuzzles
	
	// ----------------displayPuzzle-------------------
	// This method calls the display2DimArray method, passing to it this objects userPuzzle
	
	public void displayPuzzle() {
		display2DimArray(this.userPuzzle);
	} //end displayPuzzle
	
	// ----------------resetPuzzle-------------------
	// This method sets up a new puzzle to play	
	// It follows the same set as the constructor of filling a puzzle
	//		copying a puzzle, and setting holes for the puzzle.

	public void resetPuzzle(boolean debugging) {
		fillPuzzle();
		copyPuzzle(puzzleAnswer, userPuzzle);
		setPuzzleHoles();
		if (debugging) {
			System.out.println("For debugging, the answer puzzle: ");
			display2DimArray(puzzleAnswer);
		}
	} //end resetPuzzle
	
	// ----------------isValidPlacement-------------------
	// This method will return false if 
	// 		the spot isn't 0, (must be blank)
	//		the value is already in the same row or column
	//		the value is already in the same sub-square
	// Otherwise, the method returns true
	
	private boolean isValidPlacement(int row, int col, int value) {
		if( userPuzzle[row][col] != 0 ) // not empty?
			return false;
		// check if in the same row and column
		for( int i=0; i < userPuzzle.length; ++i ){
			if( userPuzzle[i][col] == value || userPuzzle[row][i] == value)
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
				if( userPuzzle[subrow][subcol] == value )
					return false;
		} // end checking block
		return true;
	} // end isValidPlacement
	
	// ----------------setOneSpot-------------------
	// This method tries to place a number in a spot, but returns
	//     an error message if the input is out of range or the value
	//     is already in the row, column or sub-square or not 0
	// If the input is valid, the value is assigned in the puzzle
	//      & "Number placed!" is returned
	
	public String setOneSpot(int row, int col, int value)
	{
		int minVal = 1, maxVal = userPuzzle.length;
		
		if( row < 0 || row >= maxVal || col < 0 || col >= maxVal || value < minVal || value > maxVal){
			return "Invalid input, must be "+minVal + " to " + maxVal + ", inclusive!";
		}
		else if( !isValidPlacement(row, col, value)){
			return "You can't place that value there!" ;
		}

		// place the value
		userPuzzle[row][col] = value;
		return "Number placed!";
	} // end setOneSpot
	
} //end Sudoku class
