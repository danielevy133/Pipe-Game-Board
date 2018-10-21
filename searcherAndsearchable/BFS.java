package searcherAndsearchable;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;

import MyObject.Solution;
import MyObject.State;

public class BFS<T> extends CommonSearcher<T> {
	
	public BFS() {
		super(new ArrayDeque<State<T>>());
	}

	@Override
	public Solution<T> search(Serchable<T> S) {

			addToOpenList(S.getInitialState());
			HashSet<State<T>> close = new HashSet<State<T>>();
			while (getSize() >0 ) {
				State<T> n = popOpenList();
				close.add(n);
				if (S.isGoal(n))
					return backTrace(n);
				List<State<T>> successor = S.getAllPosibleState(n);
				for (State<T> states : successor) {
					states.setCameFrom(n);
					if (! close.contains(states) && ! openListContains(states))
						addToOpenList(states);
				}
			}
				return null;
		}
}
