 package serverAndClientHendler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import MyObject.Solution;
import SolverAndCacheManager.*;

public class MyClientHandler implements ClientHandler {
	
	Solver<String> solv;
	CacheManager cm;
	
	
	public MyClientHandler(Solver<String> solve,CacheManager cache) {
		solv=solve;
		cm=cache;
	}
	
	@Override
	public void handleClient(InputStream inFromServer, OutputStream outToServer) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(inFromServer));
		ArrayList<String> problem =new ArrayList<String>();
		String line=new String();
		PrintWriter bw = new PrintWriter(new OutputStreamWriter(outToServer));
		while (!(line.equals("done"))) {// Read from client
			line = br.readLine();
			problem.add(line);
		}
		problem.remove(problem.size()-1);
			Solution<String> solution=null;
			try {
				solution=cm.getSolution(problem);
				if (solution == null) {
					solution=solv.Solve(problem);
					cm.insert(problem, solution.getSolution());// Save in the cach
					} 
			}catch (IOException e) {e.printStackTrace();}
			for (String s : solution.getSolution()) {//Write the client
				bw.println(s);
				bw.flush();
			}
			bw.println("done");
			bw.flush();
	}
}
