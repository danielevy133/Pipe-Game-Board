package Main;

import java.util.Scanner;

import MyObject.MatrixChar;
import SolverAndCacheManager.MySolver;
import SolverAndCacheManager.MyCacheManager;
import searcherAndsearchable.BestFS;
import serverAndClientHendler.*;

public class MainServer {
	// start the server and with to input for close it
	public static void main(String[] args) {
		MyServer server=new MyServer(6400, 4);
		server.start(new MyClientHandler(new MySolver(new BestFS<MatrixChar>()), new MyCacheManager()));
		new Scanner(System.in).nextLine();
		server.stop();
	}
}
