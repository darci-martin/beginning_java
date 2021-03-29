
public class AdultFemale extends Patient {

	//constructor for the class
	public AdultFemale(String nm, int bY, int bM) {
		super(nm, bY, bM); //call Patient constructor
	} //end constructor

	//constructor for the class
	public AdultFemale(String nm, int bY, int bM, double h, double w) {
		super(nm, bY, bM, h, w); //call Patient constructor
	} //end constructor
	
	//------- calcIdealWeight ---------
	//Override the abstract method using provided formula
	//	to calculate the ideal weight of a patient

	@Override
	public double calcIdealWeight() {
		if (height >= 152) {
			return 45. + (0.9 * (height - 152));
		}
		return 45. - (0.3 * (152 - height));
	} //end calcIdealWeight
	
	//------- calcBMR ---------
	//Override the abstract method using provided formula
	//	to calculate Basal Metabolic Rate of the patient

	@Override
	public double calcBMR() {
		return 655 + (9.6 * weight) + (1.8 * height) - (4.7 * this.currentAge());
	} //end calcBMR
	
	//Overrides toString from superclass
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(); //use StringBuilder
		sb.append("AdultFemale ");
		sb.append(super.toString());
		return sb.toString();
	} //end toString

} //end class
