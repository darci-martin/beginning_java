
public class AdultMale extends Patient {

	//constructor for the class
	public AdultMale(String nm, int bY, int bM) {
		super(nm, bY, bM); //call Patient constructor
	} //end constructor
	
	//constructor for the class
	public AdultMale(String nm, int bY, int bM, double h, double w) {
		super(nm, bY, bM, h, w); //call Patient constructor
	} //end constructor

	//------- calcIdealWeight ---------
	//Override the abstract method using provided formula
	//	to calculate the ideal weight of a patient
	
	@Override
	public double calcIdealWeight() {
		if (height >= 152) {
			return 48. + (1.1 * (height - 152));
		} 
		else
		return 48 - (0.327 * (152 - height));
	} //end calcIdealWeight

	//------- calcBMR ---------
	//Override the abstract method using provided formula
	//	to calculate Basal Metabolic Rate of the patient
	
	@Override
	public double calcBMR() {
		return 66 + (13.7 * weight) + (5 * height) - (6.8 * this.currentAge());
	} //end calcBMR
	
	//Overrides toString from superclass
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(); //use StringBuilder
		sb.append("AdultMale ");
		sb.append(super.toString());
		return sb.toString();
	} //end toString

} //end class
