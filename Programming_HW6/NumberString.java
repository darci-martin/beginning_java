
public class NumberString implements Comparable<NumberString> {
	//declare class scope variable for the maximum size of palindrome
	public static final int MAX_SIZE = 200;
	
	//private class members
	private String numStr = "0"; //0 default
	private StringBuilder numStrPalin = new StringBuilder("0"); //0 default
	private StringBuilder reverseStr = new StringBuilder("0"); //0 default

	//Constructor for the class
	NumberString(String param1) throws IllegalArgumentException { //throw exception
		setNumStr(param1);
	} //end constructor

	//default constructor
	NumberString() {
	} //end default constructor

	//accessor for numStr
	public String getNumStr() {
		return numStr;
	}

	//accessor for numStrPalin
	public String getNumStrPalin() {
		return numStrPalin.toString();
	}
	
	@Override //override compareTo method from Comparable
	public int compareTo(NumberString obj) {
		Long thisLong = Long.parseLong(this.numStr); //convert this numStr to Long
		Long paramLong = Long.parseLong(obj.getNumStr()); //convert parameter to Long
		return thisLong.compareTo(paramLong); //compare Longs
	} //end compareTo

	// ----------------makePalindrome-------------------
	//This method checks whether numStrPalin is a palindrome
	//If it is a palindrome and it's length is < 200 digits it is returned
	//If it is not a palindrome, the addNumStr method is called until
	//	a palindrome is returned
	
	private void makePalindrome() {
		numStrPalin.setLength(0);
		numStrPalin.append(numStr); //store the characters in numStr in numStrPalin
		reverseStr.setLength(0); //clear reverseStr
		reverseStr.append(numStrPalin); //put numStrPalin in reverseStr
		reverseStr.reverse(); //reverse numStrPalin in reverseStr -- corrected from HW 4

		if (!numStrPalin.toString().equals(reverseStr.toString())) { //check if equal
			do {
			addNumStr(); //call method
			reverseStr.setLength(0);
			reverseStr.append(numStrPalin);//store numStrPalin in reverseStr
			reverseStr.reverse(); //reverse numStrPalin in reverseStr -- corrected from HW 4
			} while (numStrPalin.length() <= MAX_SIZE && !numStrPalin.toString().equals(reverseStr.toString()));
		} //end if

		if (numStrPalin.length() > MAX_SIZE) { //check if larger than max size
			numStrPalin.setLength(0);
			numStrPalin.append("No Solution < 200 digits");
		} //end if

	} //end makePalindrome
	
	// ----------------addNumStr-------------------
	//This method adds together numStrPalin with its reverse (reverseStr) and stores
	//  the result as StringBuilder back in numStrPalin.
	//The numbers are replaced starting at the last position, working to zero position.

	private void addNumStr() {
		int base = 0, temp = 0; //local variables to hold numbers
		boolean carry = false; //local variable to indicate if there is a carry

		for (int i = reverseStr.length() - 1; i >= 0; i--) {
			// turn Char to Numeric, add together and store in temp
			temp = Character.getNumericValue(reverseStr.charAt(i))+ Character.getNumericValue(numStrPalin.charAt(i));
			if (carry) { //if carry is true add one to temp
				temp++; 
				carry = false; //set carry back to false
			} //end if
			if (temp > 9) { //if temp is greater than 9, create carry
				base = temp - 10;
				carry = true;
			} //end if
			if (temp < 10) { //if temp less than 10, just use it
				base = temp;
			} //end if
			//cast integer back to char and put in correct position
			numStrPalin.setCharAt(i,(char)('0' + base));
			base = 0; //reset
			temp = 0; //reset
		} //end for loop

		if (carry) { //if remaining carry, add to zero position
			numStrPalin.insert(0, (int)1);
		} //end if
	} //end addNumStr
	
	// ----------------setNumStr-------------------
	//This method checks that the input is valid. It must be not null,
	//	not empty, must be all digits, and cannot have length greater than 19. 
	//If it passes, it is assigned to numStr.

	private boolean setNumStr(String input) throws IllegalArgumentException{
		if (input == null || input.equals("")) { //checking if null or empty string
			throw new IllegalArgumentException("Cannot be null or empty string");
		}
		if (input.length() > 19) { //checking length
			throw new IllegalArgumentException("Cannot have length greater than 19");
		}
		if (!input.matches("^[0-9]+$")) { //checking that it is all digits
			throw new IllegalArgumentException("Must be all digits");
		}
		numStr = input; 
		makePalindrome(); //call method
		return true;
	} //end setNumStr

} //end class
