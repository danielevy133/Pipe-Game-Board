
package SolverAndCacheManager;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import MyObject.Solution;

public class MyCacheManager implements CacheManager {

//	private static SimpelCachManager instance; //Singleton
	private static HashMap<Integer, File> solution;
	
	// Upload the cache from computer memory or open new path
	public MyCacheManager()  {
		solution = new HashMap<Integer, File>();
		Path path = Paths.get("Solutions");
		if (Files.exists(path,LinkOption.NOFOLLOW_LINKS)) {
			File files[]=new File("Solutions").listFiles();
			for (File file : files) {
				solution.put(Integer.parseInt(file.getName()), file);
			}
		}else {try {
			Files.createDirectory(path);
		} catch (IOException e) {
			e.printStackTrace();
		}}
			
	}
	
	@Override
	public void insert(ArrayList<String> key, ArrayList<String> val) throws IOException {
		Integer boardKey=codeString(key);
		File newSol=new File("Solutions/"+boardKey.toString());
		PrintWriter file;
			file = new PrintWriter(new FileWriter(newSol.getAbsoluteFile()));
			for(String enia : val) {
				file.println(enia);
				file.flush();
			}
			file.println("done");
			file.flush();
			file.close();
			solution.put(boardKey, newSol);

	}

	@Override
	public Solution<String> getSolution(ArrayList<String> key) throws IOException {
		Integer boardKey=codeString(key);
		if (solution.containsKey(boardKey)) {
			BufferedReader file;
			Solution<String> eniot;
			String end=new String();
			try {
				file=new BufferedReader(new FileReader(solution.get(boardKey).getAbsoluteFile()));
				eniot=new Solution<String>();
				while (!end.equals("done")) {
					end=new String(file.readLine());
					eniot.getSolution().add(end);
					
				}
				file.close();
				return eniot;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	
		}
		return null;
	}
	// Board key
	private Integer codeString(ArrayList<String> problem) {
		String code = problem.get(0);
		for(int i=1;i<problem.size();i++)
			code=new String(code+problem.get(i));
		return code.hashCode();
		
	}
}