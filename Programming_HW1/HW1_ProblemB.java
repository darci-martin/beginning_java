//Homework Problem B
//Darci Martin
//January 21st 2018
//Mac Computer with Eclipse Compiler
//A program to calculate and display a table of odds of winning a lottery.
//Variables: 
//odds_five = for each loop holds the value of the odds of winning having chosen 5 numbers
//odds_six = for each loop holds the value of the odds of winning having chosen 6 numbers
//numbers = for each loop holds the value of the numbers available to choose

public class HW1_ProblemB {
	//class scope constants for number of numbers available in each lottery
	public static final int MIN_CHOICE = 37;
	public static final int MAX_CHOICE = 69;
	//class scope constant for incrementing the number of numbers available
	public static final int INCREMENT = 2;
	//class scope constants for how many numbers are chosen each round of the lottery
	public static final int MIN_CHOSEN = 5;
	public static final int MAX_CHOSEN = 6;
	
	public static void main(String[] args) {
		//output header
		System.out.println("n" +"\t\tr = 5" + "\t\t\tr = 6");
		
		//loop to calculate the odds for each number of numbers available and how many chosen per round
		for (int i = 0; (MIN_CHOICE + INCREMENT*i) < (MAX_CHOICE + 1); i++) {
			//variables to hold the value of the odds
			double odds_five, odds_six;
			//variable for number of numbers available to choose from each round
			int numbers;
			numbers = MIN_CHOICE + INCREMENT*i;
			//use given formula n!/r!(n-r)! to calculate odds of winning
			odds_five = factorial(numbers)/(factorial(MIN_CHOSEN)*factorial(numbers - MIN_CHOSEN));
			odds_six = factorial(numbers)/(factorial(MAX_CHOSEN)*factorial(numbers - MAX_CHOSEN));
			//output value of the round
			System.out.printf("%d\t1 in%12.1f\t\t1 in%12.1f\n", numbers, odds_five, odds_six);
		}
	}
	
	//public method to compute the factorial
	public static double factorial(int input) {
		double factor = input;
		input--;
		for (; input > 0;)
			factor *= (input--);
		return factor;
	}
}

/*
 Output:
 n		r = 5			r = 6
37	1 in    435897.0		1 in   2324784.0
39	1 in    575757.0		1 in   3262623.0
41	1 in    749398.0		1 in   4496388.0
43	1 in    962598.0		1 in   6096454.0
45	1 in   1221759.0		1 in   8145060.0
47	1 in   1533939.0		1 in  10737573.0
49	1 in   1906884.0		1 in  13983816.0
51	1 in   2349060.0		1 in  18009460.0
53	1 in   2869685.0		1 in  22957480.0
55	1 in   3478761.0		1 in  28989675.0
57	1 in   4187106.0		1 in  36288252.0
59	1 in   5006386.0		1 in  45057474.0
61	1 in   5949147.0		1 in  55525372.0
63	1 in   7028847.0		1 in  67945521.0
65	1 in   8259888.0		1 in  82598880.0
67	1 in   9657648.0		1 in  99795696.0
69	1 in  11238513.0		1 in 119877472.0 
 */
