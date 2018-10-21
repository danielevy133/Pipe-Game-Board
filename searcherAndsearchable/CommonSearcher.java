package searcherAndsearchable;

import java.util.ArrayDeque;
import java.util.Queue;

import MyObject.Solution;
import MyObject.State;


public abstract class CommonSearcher<T> implements Sercher<T> {
	private Queue<State<T>> openList;
	private int evaluatedNodes;
	
	public CommonSearcher(Queue<State<T>> dfult) {
		evaluatedNodes=0;
		openList=dfult;
	}

	public void addToOpenList(State<T> s) {
		openList.offer(s);
	}

	public int getEvaluatedNodes() {
		return evaluatedNodes;
	}

	protected State<T> peekEndOfOpenList(){
		return ((ArrayDeque<State<T>>)openList).peekLast();
	}
	
	protected State<T> popEndOfOpenList(){
		evaluatedNodes++;
		return ((ArrayDeque<State<T>>)openList).pollLast();
	}
	
	public void setEvaluatedNodes(int evaluatedNodes) {
		this.evaluatedNodes = evaluatedNodes;
	}
	
	public State<T> popOpenList() {
		evaluatedNodes++;
		return openList.poll();
	}

	public int getSize() {
		return openList.size();
	}
	
	public boolean openListContains (State<T> s) {
		return openList.contains(s);
	}
	
	public State<T> getState (State<T> s) {
		for (State <T> states : openList) 
			if (states.equals(s)) {
				return states;
			}
		return null;
	}
	
	public boolean removeFromOpenList(State<T> s) {
		return openList.remove(s);
	}
	
	protected Solution<T> backTrace(State<T> goalState) {
		evaluatedNodes=0;
		Solution<T> solution=new Solution<T>();
		State<T> tempGoal=new State<T>(goalState.getState());
		tempGoal.setCameFrom(goalState.getCameFrom());
		while (tempGoal!=null) {
			solution.getSolution().add(tempGoal.getState());
			tempGoal=tempGoal.getCameFrom();
		}
		
		return solution;
}
	@Override
	public abstract Solution<T> search (Serchable<T> S);
}
