package searcherAndsearchable;

import java.util.List;
import java.util.Random;

import MyObject.Solution;
import MyObject.State;

public class HillClimbing<T> implements Sercher<T> {
	private long timeToRun;
	private StateGrader<T> grade;
	
	public HillClimbing(StateGrader<T> grade,long timeToRun) {
		this.timeToRun=timeToRun;
		this.grade=grade;
	}
	@Override
	public Solution<T> search(Serchable<T> S) {
		State<T> next = S.getInitialState();
		Solution<T> result=new Solution<T>();
		
		long time0=System.currentTimeMillis();
		while(System.currentTimeMillis()-time0<timeToRun) {
			result.getSolution().add(next.getState());
			if (S.isGoal(next))
				break;
			List<State<T>> successor = S.getAllPosibleState(next);
			if (Math.random()<0.7) {
				int grade=0;
				for (State<T> state : successor) {
					int g=this.grade.grade(state);
					if(g<grade) {
						grade=g;
						next=state;
					}
				}
			}else {
				next=successor.get(new Random().nextInt(successor.size()));
			}
		}
		return result;
	}

}
