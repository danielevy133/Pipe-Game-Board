package searcherAndsearchable;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import MyObject.Solution;
import MyObject.State;

public class BestFS<T> extends CommonSearcher<T> {
	

	public BestFS() {
		super(new PriorityQueue<State<T>>(new State<T>()));
	}

	@Override
	public Solution<T> search (Serchable<T> S) {
		addToOpenList(S.getInitialState());
		HashSet<State<T>> close = new HashSet<State<T>>();
		while (getSize() >0 ) {
			State<T> n = popOpenList();
			close.add(n);
			if (S.isGoal(n))
				return backTrace(n);
			List<State<T>> successor = S.getAllPosibleState(n);
			for (State<T> states : successor) {
				states.setCost(states.getCost() + n.getCost());
				states.setCameFrom(n);
				if (! close.contains(states) && ! openListContains(states))
					addToOpenList(states);
				else 
					if (openListContains(states))
						if (getState(states).getCost()>states.getCost()) {
							removeFromOpenList(states);
							addToOpenList(states);
						}
			}
		}
			return null;
	}


}
