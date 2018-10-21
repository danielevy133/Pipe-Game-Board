package serverAndClientHendler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyServer implements Server {

	private boolean exit;
	private int port;
	public static PriorityThreadPool pool;
	private final Object lock;
	private static MyServer instance; //Singleton

	public MyServer(int port, int maxThread) {
		pool = new PriorityThreadPool(1, maxThread, 1, TimeUnit.DAYS, new PriorityBlockingQueue<>());
		this.port = port;
		exit = false;
		lock = new Object();
	}
	/*
	 public static MyServer getInstance (int port,int maxThread) { Singleton
	 if (instance == null)
		 synchronized (MyServer.class) {
			 if (instance == null)
				 instance =new MyServer(port,maxThread);
		}
	 return instance;
	 }
*/
	@Override
	public void start(ClientHandler CH) { // Thread for server
		new Thread(() -> {
			runServer(CH);
		},"start").start();
	}

	private void runServer(ClientHandler CH){
		
		try {
			ServerSocket serverSocket = new ServerSocket(port);
	   		serverSocket.setSoTimeout(5000);
			while (!exit) {
				System.out.println("with to client....");
				Socket client =serverSocket.accept(); // With to client
				InputStream inFromClient = new BufferedInputStream(client.getInputStream());
				inFromClient.mark(0); // Save the beginning inputstream
				if (!new BufferedReader(new InputStreamReader(inFromClient)).readLine().equals("stop the server")) {
					inFromClient.reset();
					pool.execute(()->{// Threads for clients
						OutputStream outToClient = null;
						try {
							System.out.println("client is here");
							long timer=System.currentTimeMillis();//Solution time
							outToClient = client.getOutputStream();
							Thread.sleep(7000); //For connect few client parallel
							CH.handleClient(inFromClient, outToClient);
							timer=System.currentTimeMillis()-timer;
							System.out.println("solution is here" + timer);
						}catch (IOException e) { e.printStackTrace();}
						catch (InterruptedException e) {e.printStackTrace();} // Sleep catch
						finally {
							try {
								inFromClient.close();
								outToClient.close();
								client.close();
							} catch (IOException e) {e.printStackTrace();}
						}
						if (pool.getActiveCount()==1)// Finish handling the remaining client
							synchronized (lock) {
								lock.notifyAll();
							}
					}, lock,priorityProblem(inFromClient));
				}
			}
			if (pool.getActiveCount() != 0)// With to finish handling
				synchronized (lock) {
					lock.wait();
				}
			pool.shutdown();
			serverSocket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		exit = true;
		try {
			Socket soc = new Socket(InetAddress.getByName("127.0.0.1"), port);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()), true);
			out.println("stop the server");// Sent to himself stop massage
			out.close();
			soc.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	
	private int priorityProblem (InputStream problem) {
		int priority =0;
		BufferedReader br = new BufferedReader(new InputStreamReader(problem));
		ArrayList<String> tempPriority=new ArrayList<String>();
		try {
			String line = br.readLine();
			while (!line.equals("done")) {
				tempPriority.add(line);
				line=br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String line : tempPriority) {
			for (int j=0;j<line.length();j++) {
				char sign=line.charAt(j);
				if (sign !=' '&& sign!='s' && sign!='g')
					if (sign=='-' || sign=='|')priority++;
					else priority+=2;
			}
		}
		try {problem.reset();} catch (IOException e) {e.printStackTrace();}// to return the problem to the beginning
		return priority;
	}
}
