package SolverAndCacheManager;

import java.io.IOException;
import java.util.ArrayList;
import MyObject.Solution;


public interface CacheManager {
	
	void insert(ArrayList<String> key, ArrayList<String> val) throws IOException;
	Solution<String> getSolution(ArrayList<String> key) throws IOException;
	
}
