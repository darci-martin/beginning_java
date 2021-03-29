//Homework Problem 5
//Darci Martin
//March 7th 2018
//Mac Computer with Eclipse Compiler
//A program that takes in an array of Female and Male patient data,
//and returns their birth month/year, height and weight in sorted order.

import java.util.*;

public class Prog7 {

	public static void main(String[] args) {
		TreeMap<String, Patient> patients = new TreeMap<>(); //TreeMap of <String,Patient> objects
		patients = fillTreeMap(); //call method to fill TreeMap
		display(patients); //call method to display TreeMap
		
		int underweight = underweight(patients); //instance variable for underweight count
		if (underweight < 0) { //if method returned -1
			System.out.println("\nThere are no adult female patients on the list");
		}
		else System.out.println("\nThe number of underweight female patients: " + underweight);
	} //end main
	
	//------- fillTreeMap ---------
	//This method takes a hard-coded array and fills a TreeMap.
	//The TreeMap uses a custom comparator to ensure the correct order.
	
	public static TreeMap<String,Patient> fillTreeMap() {
		Patient [] array = {
				new AdultFemale("Lopez, Jennifer", 1969, 7, 167.64, 65.77),
				new AdultFemale("Chenoweth, Kristin", 1968, 7, 149.84, 39.9),
				new AdultMale("Reynolds, Ryan", 1976, 10, 187.96, 85.28),
				new AdultMale("DICAPRIO, LEONARDO", 1974, 11, 181.6, 75),
				new AdultFemale("Flockhart, Calista", 1964, 11, 167.64, 44),
				new AdultMale("Damon, Matt", 1970, 10, 177.8, 70.0)
			};
			// use in a separate run
		/*Patient [] array = {
				new AdultMale("STILLER, BEN", 1965, 11, 168.9, 70.3),
				new AdultMale("Depp, Johnny", 1963, 6, 177.8, 71),
				new AdultMale("Stallone, Sylvester", 1946, 7, 175.26, 103.4),
				new AdultMale("Short, Guy", 1973, 8, 144.78, 45.34)
			};*/
		
		TreeMap<String,Patient> tm = new TreeMap<>(new MyComp());
		//add key-value pair to TreeMap with enhanced for loop
		for (Patient element: array ) {
			tm.put(element.getCompareString(), element);
		}
			
		return tm;
	} //end fillTreeMap
	
	
	//------- display --------------	
	//This method uses an entrySet() iterator to display the TreeMap values.
	//Output uses the toString() method of the Patient class.
	
	public static void display(TreeMap<String,Patient> treemap) {
		//Get a set of the entries
		Set<Map.Entry<String,Patient>> set = treemap.entrySet();
		//Get an iterator
		Iterator<Map.Entry<String,Patient>> iterator = set.iterator();
		//Display elements
		while (iterator.hasNext()) {
			Map.Entry<String,Patient> values = iterator.next();
			if (values != null) { //check if value is null
				System.out.println("\n" + values.getValue().toString());
			} //end if
		} //end while
	} //end display
	
	//------- underweight --------------	
	//This method returns how many Adult Female patients are underweight.
	//Underweight is categorized as a weight below the ideal calculated weight.
	//If there are no Adult Female objects, the method returns -1.
	
	public static int underweight(TreeMap<String,Patient> treemap) {
		int count = 0; //variable for count of underweight
		int female = 0; //variable for count of females
		
		//Get a set of the entries
		Set<Map.Entry<String,Patient>> set = treemap.entrySet();
		//Get value from each entry
		for (Map.Entry<String, Patient> entry: set)
			if (entry.getValue() instanceof AdultFemale) { //if object is an Adult Female
				female++;
				if (entry.getValue().getWeight() < entry.getValue().calcIdealWeight()) { //check weight
					count++;
				} //end if
			} //end if
		if (female == 0) { //adjust if no females
			return -1;
		} //end if
		return count;
	} //end underweight

} //end main

/*Output
 RUN 1:
 
AdultFemale Patient: Name=Chenoweth, Kristin,
Birth month/year=7/1968, Height=149.84 cm., Weight=39.9 kg.

AdultMale Patient: Name=Damon, Matt,
Birth month/year=10/1970, Height=177.8 cm., Weight=70.0 kg.

AdultMale Patient: Name=DICAPRIO, LEONARDO,
Birth month/year=11/1974, Height=181.6 cm., Weight=75.0 kg.

AdultFemale Patient: Name=Flockhart, Calista,
Birth month/year=11/1964, Height=167.64 cm., Weight=44.0 kg.

AdultFemale Patient: Name=Lopez, Jennifer,
Birth month/year=7/1969, Height=167.64 cm., Weight=65.77 kg.

AdultMale Patient: Name=Reynolds, Ryan,
Birth month/year=10/1976, Height=187.96 cm., Weight=85.28 kg.

The number of underweight female patients: 2

RUN 2:
 
AdultMale Patient: Name=Depp, Johnny,
Birth month/year=6/1963, Height=177.8 cm., Weight=71.0 kg.

AdultMale Patient: Name=Short, Guy,
Birth month/year=8/1973, Height=144.78 cm., Weight=45.34 kg.

AdultMale Patient: Name=Stallone, Sylvester,
Birth month/year=7/1946, Height=175.26 cm., Weight=103.4 kg.

AdultMale Patient: Name=STILLER, BEN,
Birth month/year=11/1965, Height=168.9 cm., Weight=70.3 kg.

There are no adult female patients on the list
 */
