package serverAndClientHendler;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PriorityThreadPool extends ThreadPoolExecutor{

	public PriorityThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			PriorityBlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	private class Helper<V> extends FutureTask<V> implements Comparable<Helper<V>>{ 
		
		private int priority;
		
		public Helper(Runnable runnable, V result, int priority) {
			super(runnable, result);
			this.priority=priority;
		}

		public Helper(Callable<V> arg0,int priority) {
			super(arg0);
			this.priority=priority;
		}

		@Override
		public int compareTo(Helper<V> o) {
			return this.priority-o.priority;
		}

	
	}
	

	
	public <V> void execute(Runnable r,V result,int priority){
		Helper<V> temp=new Helper<V>(r,result, priority);
		super.execute(temp);
	}
	
	public <V> Future<V> submit(Callable<V> c, int priority){
		Helper<V> temp= new Helper<>(c, priority);
		super.execute(temp);
		return temp;
	}

}
																																					