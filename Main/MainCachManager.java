package Main;

import java.io.IOException;
import java.util.ArrayList;

import MyObject.Solution;
import SolverAndCacheManager.MyCacheManager;

public class MainCachManager {

	public static void main(String[] args) throws IOException {
		MyCacheManager test=new MyCacheManager();
		ArrayList<String> problem=new ArrayList<String>();
		Solution<String> solution=new Solution<String>();
		problem.add("sdss");
		problem.add("rrtr");
		//solution.getSolution().add("3,4,5");
		//solution.getSolution().add("1,2,3");
		//test.insert(problem,solution.getSolution());
		solution=test.getSolution(problem);
	}

}
