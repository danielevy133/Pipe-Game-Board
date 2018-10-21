package searcherAndsearchable;

import MyObject.Solution;

public interface Sercher<T> {
	
	public Solution<T> search (Serchable<T> S);
	
}
