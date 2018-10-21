package MyObject;

import java.util.ArrayList;

public class Solution<T> {
	private ArrayList<T> theSolution;
	
	public Solution() {
		theSolution=new ArrayList<T>();
	}
	
	public ArrayList<T> getSolution() {
		return theSolution;
	}
	
}
