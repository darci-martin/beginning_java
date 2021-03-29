import java.util.*;

public class MyComp implements Comparator<String>{ 
	
	//override the compare method to use compareToIgnoreCase
	@Override
	public int compare(String leftString, String rightString) {
		return leftString.compareToIgnoreCase(rightString);
	}

}
