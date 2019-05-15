import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	private static boolean isRunning = true;
	
    public static void main(String[] args) throws Exception
    {
        System.out.println("Server Running...");
        
        ThreadPool threadPool = new ThreadPool();
        threadPool.startThreadPool();
        ThreadPoolManager manager = new ThreadPoolManager(threadPool);
        manager.start();
        
        int clientNum = 0;
        
        ServerSocket listener = new ServerSocket(9898);
        try
        {
            while (Server.isRunning)
            {
            	Socket client = listener.accept();
            	if (threadPool.isQueueFull())
            	{
            		PrintWriter clientOutput = new PrintWriter(client.getOutputStream(), true);
            		clientOutput.println("The server is currently busy. Please try again later.");
            	}
            	else
            		threadPool.enqueueClient(new ClientData(client, clientNum++));
            }
        }
        finally { listener.close(); }
    }
    
    private static class ThreadPoolManager extends Thread
    {
    	private boolean isRunning;
    	private ThreadPool threadPool;
    	
    	public ThreadPoolManager(ThreadPool threadPool)
    	{
    		this.isRunning = true;
    		this.threadPool = threadPool;
    	}
    	
    	@Override
    	public void run()
    	{
    		while (this.isRunning)
    		{
    			if (!this.threadPool.isRunning())
    			{
    				this.isRunning = false;
    				Server.isRunning = false;
    			}
    			else
    			{
	    			int threadCount = this.threadPool.getActiveThreadCount();
	    			int queueSize = this.threadPool.getQueueSize();
	
		    		if ((threadCount == ThreadPool.BASE_THREAD_COUNT 
		    		  && queueSize > ThreadPool.QUEUE_SIZE_THRESHOLD_1 
		    		  && queueSize <= ThreadPool.QUEUE_SIZE_THRESHOLD_2) || 
		    			(threadCount == 2 * ThreadPool.BASE_THREAD_COUNT 
		    		  && queueSize > ThreadPool.QUEUE_SIZE_THRESHOLD_2 
		    		  && queueSize <= ThreadPool.QUEUE_SIZE_MAX))
		    		{
		    			System.out.println("Number of Client requests exceeds next threshold. Attempting to increase ThreadPool size...");
		    			this.threadPool.increaseThreadCount();
		    		}
		    		else if ((threadCount == 4 * ThreadPool.BASE_THREAD_COUNT
		    			   && queueSize < ThreadPool.QUEUE_SIZE_THRESHOLD_2
		    			   && queueSize > ThreadPool.QUEUE_SIZE_THRESHOLD_1) ||
		    				 (threadCount == 2 * ThreadPool.BASE_THREAD_COUNT
		    			   && queueSize < ThreadPool.QUEUE_SIZE_THRESHOLD_1
		    			   && queueSize > ThreadPool.BASE_THREAD_COUNT))
		    		{
		    			System.out.println("Number of active threads is below next threshold. Attempting to decrease ThreadPool size...");
		    			this.threadPool.decreaseThreadCount();
		    		}
	    			try { Thread.sleep(5000); }
	    			catch (InterruptedException e) { System.out.println("ThreadPoolManager was interrupted."); }
    			}
    		}
    	}
    }
}