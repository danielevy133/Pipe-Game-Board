package MyObject;

import java.util.Comparator;

public class State<T> implements Comparator<State<T>>{

	private T state;
	private double cost;
	private State<T> cameFrom;
	
	public State() {}
	
	public State( T state ) {
		this.state = state;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public T getState() {
		return state;
	}

	public void setState(T state) {
		this.state = state;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}

	public boolean equals(State<T> s) {
		return state.equals(s.state);
	}

	@Override
	public int hashCode() {
		return state.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return (this.hashCode()==obj.hashCode());
	}
	
 	@Override
	public int compare(State<T> o1, State<T> o2) {
		if (o1.cost<o2.cost)return 1; 
		if (o1.cost>o2.cost)return -1;
		return 0;
	}
	
	
	
}