package SolverAndCacheManager;

import java.util.ArrayList;
import MyObject.Solution;

public interface Solver<T> {

	public Solution<T> Solve(ArrayList<T> problem);



}
