package Main;

import java.util.ArrayList;

import MyObject.MatrixChar;
import MyObject.Solution;
import SolverAndCacheManager.MySolver;
import SolverAndCacheManager.Solver;
import searcherAndsearchable.BestFS;

public class MainMySolver {

	public static void main(String[] args) {
		Solver<String> solv=new MySolver(new BestFS<MatrixChar>());
		ArrayList<String> problem=new ArrayList<String>();
		char matrix[][]=MainSearcherAndSearchable.goal(MainSearcherAndSearchable.randomMatrix(5, 4));
		for (int i=0;i<matrix.length;i++)
			problem.add(new String(matrix[i]));
		for (int j=0;j<problem.size();j++)
			System.out.println(problem.get(j));
		Solution<String> sol=solv.Solve(problem);
		for (String s:sol.getSolution())
			System.out.println(s);
	}

}
