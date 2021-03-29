import java.util.*;

public abstract class Patient {
	//protected instance variables
	protected String name ="Darci";
	protected int birthYear = 1982;
	protected int birthMonth = 6;
	protected double height = 162.56;
	protected double weight = 53.52;
	
	//constructor for the class
	public Patient(String nm, int bY, int bM) {
		setName(nm); //call mutator
		this.birthYear = bY;
		this.birthMonth = bM;
	} //end constructor
	
	//second constructor
	public Patient(String nm, int bY, int bM, double h, double w) {
		this(nm, bY, bM); //call constructor
		setHeight(h);
		setWeight(w);
	} //end second constructor
	
	//accessor for name
	public String getName() {
		return name;
	}
	
	//accessor for birth year
	public int getBirthYr() {
		return birthYear;
	}
	
	//accessor for birth month
	public int getBirthMth() {
		return birthMonth;
	}
	
	//accessor for height
	public double getHeight() {
		return height;
	}
	
	//accessor for weight
	public double getWeight() {
		return weight;
	}
	
	//mutator for name
	public boolean setName(String nm) {
		if (nm != null && nm.length() > 0) { //must not be null or length <= 0
			this.name = nm;
			return true;
		}
		return false;
	} //end mutator
	
	//mutator for height
	public boolean setHeight(double h) { 
		if (h > 0) { //must be > 0
			this.height = h;
			return true;
		}
		return false;
	} //end mutator
	
	//mutator for weight
	public boolean setWeight(double w) {
		if (w > 0) { //must be > 0
			this.weight = w;
			return true;
		}
		return false;
	} //end mutator
	
	//------- currentAge ---------
	//This method returns the age of the Patient.
	//A Gregorian calendar object is used to get the current Year and Month.
	//The age of the patient is calculated by subtracting current year minus
	//	their birth year, then decremented if the current month is less
	//	than the birth month.
	
	public int currentAge() {
		int age;
		int year;
		int month;
		Calendar calendar = new GregorianCalendar(); //Gregorian calendar object
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		age = year - this.birthYear;
		if (month < this.birthMonth) {
			age--;
		} //end if
		return age;
	} //end currentAge
	
	//abstract method
	public abstract double calcIdealWeight();
	
	//abstract method
	public abstract double calcBMR();
	
	//overides Object's toString
	public String toString() { 
		StringBuilder sb = new StringBuilder(); //use StringBuilder instead of concat (+)
		sb.append("Patient: Name=");
		sb.append(this.name);
		sb.append(",\nBirth month/year=");
		sb.append(this.birthMonth);
		sb.append("/");
		sb.append(this.birthYear);
		sb.append(", Height=");
		sb.append(this.height);
		sb.append(" cm., Weight=");
		sb.append(this.weight);
		sb.append(" kg.");
		return sb.toString();
	}  //end toString
	
} //end class
