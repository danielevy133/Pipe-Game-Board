package searcherAndsearchable;

import MyObject.MatrixChar;
import MyObject.State;


public class MyStateGrader implements StateGrader<MatrixChar> {

	@Override
	public int grade(State<MatrixChar> s) {
		
		return (int) s.getCost();
	}

}
