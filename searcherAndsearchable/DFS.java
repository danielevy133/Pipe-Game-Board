package searcherAndsearchable;



import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Stack;

import MyObject.Solution;
import MyObject.State;

public class DFS<T> extends CommonSearcher<T> {

	public DFS() {
		super(new ArrayDeque<State<T>>());
	}

	@Override
	public Solution<T> search(Serchable<T> S) {
		HashSet<State<T>> closeList=new HashSet<State<T>>();
		Stack<State<T>> succsessor=new Stack<State<T>>();
		int stackSize=-1;
		addToOpenList(S.getInitialState());
		closeList.add(S.getInitialState());
		while(getSize()>0) {
			State<T> n=peekEndOfOpenList();
			if(S.isGoal(n))
				return backTrace(n);
			stackSize=succsessor.size();
			for (State<T> state : S.getAllPosibleState(n))
				if (!closeList.contains(state))
					succsessor.push(state);
			if (stackSize==succsessor.size())
				popEndOfOpenList();
			closeList.add(succsessor.peek());
			addToOpenList(succsessor.pop());
		}
		return null;
	}

	
}
