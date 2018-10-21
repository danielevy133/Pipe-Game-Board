package searcherAndsearchable;

import MyObject.State;

public interface StateGrader<T> {
	
	public int grade(State<T> s);
}
