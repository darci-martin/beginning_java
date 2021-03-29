//Homework Problem 5
//Darci Martin
//March 7th 2018
//Mac Computer with Eclipse Compiler
//A program that takes in an array of Female and Male patient data,
//and returns their Ideal Weight and BMR in calories.

public class Prog5 {

	public static void main(String[] args) {
		Patient[] pArray; //array of Patient objects
		pArray = fillArray(); //call method to fill array
		sortArray(pArray); //call method to sort array by patient name
		display(pArray); //call method to display sorted array
		
		int underweight = underweight(pArray); //instance variable for underweight count
		if (underweight < 0) { //if method returned -1
			System.out.println("\nThere are no adult female patients on the list");
		}
		else System.out.println("\nThe number of underweight female patients: " + underweight);
	} //end main
	
	//------- fillArray ---------
	//This method takes a hard-coded array and fills an array of Patient objects
	
	public static Patient[] fillArray() {
		/*Patient [] array = {
				new AdultFemale("Lopez, Jennifer", 1969, 7, 167.64, 65.77),
				new AdultFemale("Chenoweth, Kristin", 1968, 7, 149.84, 39.9),
				new AdultMale("Reynolds, Ryan", 1976, 10, 187.96, 85.28),
				new AdultMale("DICAPRIO, LEONARDO", 1974, 11, 181.6, 75),
				new AdultFemale("Flockhart, Calista", 1964, 11, 167.64, 44),
				new AdultMale("Damon, Matt", 1970, 10, 177.8, 70.0)
			};*/
			// use in a separate run
		/*Patient [] array = {
				new AdultMale("STILLER, BEN", 1965, 11, 168.9, 70.3),
				new AdultMale("Depp, Johnny", 1963, 6, 177.8, 71),
				new AdultMale("Stallone, Sylvester", 1946, 7, 175.26, 103.4),
				new AdultMale("Short, Guy", 1973, 8, 144.78, 45.34)
			};*/
			//array for turning in
		Patient [] array = {
				new AdultMale("DIESEL, VIN", 1967, 7, 182.88, 100.),
				new AdultFemale("HUNT, LINDA", 1945, 4, 144.78, 55.8),
				new AdultFemale("Hayek, Salma", 1966, 9, 157., 54),
				new AdultMale("Low, Man", 1934, 2, 147.32, 44.68),
				new AdultFemale("Lady Gaga", 1986, 3, 154.94, 49.9 )
			};
		return array;
	} //end fillArray
	
	//------- sortArray ---------	
	//Used the bubble sort algorithm to check pairs of names,
	//	and move the names into the correct order on each pass. 
	//	Passes continue until all pairs are in order.
	
	public static void sortArray(Patient[] array) {
		boolean needNextPass = true; //to indicate if another pass is needed
		Patient temp; //temp object to hold for swaps
		for (int i = 1; i < array.length  && needNextPass; i++) {
			needNextPass = false; 
			
			for (int j = 0; j < array.length - i; j++) {
				//using compareToIgnoreCase to compare names
				if (array[j].getName().compareToIgnoreCase(array[j + 1].getName()) > 0) {
					temp = array[j]; //use temp to swap
					array[j] = array[j + 1];
					array[j + 1] = temp;
					
					needNextPass = true; //requires another pass
				} //end if
			} //end inner loop
		} //end outer loop
	} //end sortArray
	
	//------- display --------------	
	//This method uses an enhanced for loop to display the array.
	//Output is formatted for printing.
	
	public static void display(Patient[] array) {
		for (Patient i : array) {
			System.out.printf("\n%s" + "\nAge= %d" + ", Ideal Weight = " + "%5.1f kg." + 
					", BMR = " + "%5.1f calories\n" , i.toString(), i.currentAge(), i.calcIdealWeight(), i.calcBMR());
		} //end enhanced for loop
	} //end display
	
	//------- underweight --------------	
	//This method returns how many Adult Female patients are underweight.
	//Underweight as categorized as a weight below the ideal calculated weight.
	//If there are no Adult Female objects, the method returns -1.
	
	public static int underweight(Patient[] array) {
		int count = 0; //variable for count of underweight
		int female = 0; //variable for count of females
		for (int i = 0; i < array.length; i++) {
			if (array[i] instanceof AdultFemale) { //if object is an Adult Female object
				female++;
				if (array[i].getWeight() < array[i].calcIdealWeight()) { //check weight
					count++;
				} //end if
			} //end if
		} //end for loop
		if (female == 0) { //adjust if no females
			return -1;
		} //end if
		return count;
	} //end underweight

} //end main

/*Output:
RUN 1:
AdultFemale Patient: Name=Chenoweth, Kristin,
Birth month/year=7/1968, Height=149.84 cm., Weight=39.9 kg.
Age= 49, Ideal Weight =  44.4 kg., BMR = 1077.5 calories

AdultMale Patient: Name=Damon, Matt,
Birth month/year=10/1970, Height=177.8 cm., Weight=70.0 kg.
Age= 47, Ideal Weight =  76.4 kg., BMR = 1594.4 calories

AdultMale Patient: Name=DICAPRIO, LEONARDO,
Birth month/year=11/1974, Height=181.6 cm., Weight=75.0 kg.
Age= 43, Ideal Weight =  80.6 kg., BMR = 1709.1 calories

AdultFemale Patient: Name=Flockhart, Calista,
Birth month/year=11/1964, Height=167.64 cm., Weight=44.0 kg.
Age= 53, Ideal Weight =  59.1 kg., BMR = 1130.1 calories

AdultFemale Patient: Name=Lopez, Jennifer,
Birth month/year=7/1969, Height=167.64 cm., Weight=65.77 kg.
Age= 48, Ideal Weight =  59.1 kg., BMR = 1362.5 calories

AdultMale Patient: Name=Reynolds, Ryan,
Birth month/year=10/1976, Height=187.96 cm., Weight=85.28 kg.
Age= 41, Ideal Weight =  87.6 kg., BMR = 1895.3 calories

The number of underweight female patients: 2

RUN 2:

AdultMale Patient: Name=Depp, Johnny,
Birth month/year=6/1963, Height=177.8 cm., Weight=71.0 kg.
Age= 54, Ideal Weight =  76.4 kg., BMR = 1560.5 calories

AdultMale Patient: Name=Short, Guy,
Birth month/year=8/1973, Height=144.78 cm., Weight=45.34 kg.
Age= 44, Ideal Weight =  45.6 kg., BMR = 1111.9 calories

AdultMale Patient: Name=Stallone, Sylvester,
Birth month/year=7/1946, Height=175.26 cm., Weight=103.4 kg.
Age= 71, Ideal Weight =  73.6 kg., BMR = 1876.1 calories

AdultMale Patient: Name=STILLER, BEN,
Birth month/year=11/1965, Height=168.9 cm., Weight=70.3 kg.
Age= 52, Ideal Weight =  66.6 kg., BMR = 1520.0 calories

There are no adult female patients on the list

RUN 3:

AdultMale Patient: Name=DIESEL, VIN,
Birth month/year=7/1967, Height=182.88 cm., Weight=100.0 kg.
Age= 50, Ideal Weight =  82.0 kg., BMR = 2010.4 calories

AdultFemale Patient: Name=Hayek, Salma,
Birth month/year=9/1966, Height=157.0 cm., Weight=54.0 kg.
Age= 51, Ideal Weight =  49.5 kg., BMR = 1216.3 calories

AdultFemale Patient: Name=HUNT, LINDA,
Birth month/year=4/1945, Height=144.78 cm., Weight=55.8 kg.
Age= 72, Ideal Weight =  42.8 kg., BMR = 1112.9 calories

AdultFemale Patient: Name=Lady Gaga,
Birth month/year=3/1986, Height=154.94 cm., Weight=49.9 kg.
Age= 32, Ideal Weight =  47.6 kg., BMR = 1262.5 calories

AdultMale Patient: Name=Low, Man,
Birth month/year=2/1934, Height=147.32 cm., Weight=44.68 kg.
Age= 84, Ideal Weight =  46.5 kg., BMR = 843.5 calories

The number of underweight female patients: 0


*/
