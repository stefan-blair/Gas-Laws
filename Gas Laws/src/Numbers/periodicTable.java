package Numbers;

import java.util.Arrays;

public class periodicTable {

	double O, H, Cl;
	String[] elements;
	double[] masses;
	
	public periodicTable(){
		
		O = 16;
		H = 1.0027;
		Cl = 35.453;
		elements = new String[110];
		masses = new double[110];

		elements[0] = "O";
		elements[1] = "H";
		elements[2] = "Cl";
		elements[3] = "He";
		
		masses[0] = 16;
		masses[1] = 1.0027;
		masses[2] = 35.453;
		masses[3] = 4.0026;
		
	}
	
	@Override
	public String toString() {
		return "periodicTable [O=" + O + ", H=" + H + ", Cl=" + Cl
				+ ", elements=" + Arrays.toString(elements) + ", masses="
				+ Arrays.toString(masses) + "]";
	}

	public double getElement(String element){
		
		for(int i = 0; i<elements.length; i++){
			if(element.equals(elements[i]))return masses[i];
		}
		
		return 0.00;
	}
	
}
