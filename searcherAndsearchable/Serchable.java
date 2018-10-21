package searcherAndsearchable;

import java.util.List;

import MyObject.State;

public interface Serchable<T> {
	public State<T> getInitialState();
	public boolean isGoal(State<T> theState);
	public List<State<T>> getAllPosibleState(State<T> n);
	

}
