import java.io.IOException;
import java.util.LinkedList;

public class ThreadPool
{
	public static final int BASE_THREAD_COUNT = 5;
	public static final int MAX_THREAD_COUNT = 40;
	public static final int QUEUE_SIZE_THRESHOLD_1 = 10;
	public static final int QUEUE_SIZE_THRESHOLD_2 = 20;
	public static final int QUEUE_SIZE_MAX = 50;
	
	private static int threadID = 0;
	
	private int threadCount;
	private ProcessorThread[] threadReferences;
	
	private boolean isRunning;
	
	private JobQueue queue;
	
	public ThreadPool()
	{
		this.isRunning = true;
		this.threadCount = 5;
		this.queue = new JobQueue(ThreadPool.QUEUE_SIZE_MAX);
		this.threadReferences = new ProcessorThread[40];
		for (int i = 0; i < 5; i++)
			this.threadReferences[i] = new ProcessorThread(ThreadPool.threadID++);
	}
	
	public void startThreadPool()
	{
		for (int i = 0; i < 5; i++)
			this.threadReferences[i].start();
	}
	
	public void stopThreadPool()
	{
		this.isRunning = false;
		for (int i = 0; i < this.threadReferences.length; i++)
		{
			ProcessorThread t = this.threadReferences[i];
			if (t != null)
			{
				t.stopThread();
				this.threadReferences[i] = null;
				this.threadCount--;
			}
		}
	}
	
	public void increaseThreadCount()
	{
		if (this.threadCount < ThreadPool.MAX_THREAD_COUNT)
		{
			int end = this.threadCount * 2;
			for (int i = this.threadCount; i < end; i++)
			{
				this.threadReferences[i] = new ProcessorThread(ThreadPool.threadID++);
				this.threadReferences[i].start();
				this.threadCount++;
			}
			System.out.println("Thread count in ThreadPool increased to " + this.threadCount + ".");
		}
		else
			System.out.println("Thread count in ThreadPool already at maximum.");
	}
	
	public void decreaseThreadCount()
	{
		if (this.threadCount > 5)
		{
			int end = this.threadCount / 2;
			for (int i = this.threadCount - 1; i >= end; i--)
			{
				this.threadReferences[i].stopThread();
				this.threadReferences[i] = null;
				this.threadCount--;
			}
			System.out.println("Thread count in ThreadPool decreased to " + this.threadCount + ".");
		}
		else
			System.out.println("Thread count in ThreadPool already at minimum.");
	}
	
	public boolean enqueueClient(ClientData client) { return this.queue.enqueue(client); }
	
	public int getActiveThreadCount() { return this.threadCount; }
	
	public int getQueueSize() { return this.queue.size; }
	
	public boolean isQueueFull() { return this.queue.size >= this.queue.capacity; }
	
	public boolean isRunning() { return this.isRunning; }
	
	private class JobQueue
	{
		private LinkedList<ClientData> queue;
		private int size;
		private int capacity;
		
		public JobQueue(int maxSize)
		{
			this.queue = new LinkedList<ClientData>();
			this.size = 0;
			this.capacity = maxSize;
		}
		
		public synchronized boolean isEmpty() { return this.queue.isEmpty(); }
		
		public synchronized boolean enqueue(ClientData client)
		{
			if (this.size >= this.capacity)
				return false;
			else
			{
				this.queue.addLast(client);
				this.size++;
				
				this.notifyAll();
				return true;
			}
		}
		
		public synchronized ClientData dequeue()
		{
			while (this.isEmpty())
			{
				try { this.wait(); }
				catch (InterruptedException e) { e.printStackTrace(); }
			}
			
			ClientData ret = this.queue.getFirst();
			this.queue.removeFirst();
			this.size--;
			
			return ret;
		}
	}
	
	private class ProcessorThread extends Thread
	{
		private int id;
		private boolean isRunning;
		private boolean isIdle;
		
		public ProcessorThread(int id)
		{
			this.id = id;
			this.isRunning = true;
			this.isIdle = true;
		}
		
		public void stopThread() { this.isRunning = false; }
		
		@Override
		public void run()
		{
			while(this.isRunning)
			{
				ClientData client = queue.dequeue();
				this.isIdle = false;
				while (!this.isIdle)
				{
					try
					{
						while(true)
						{
							String input = client.listen();
							if (input == null || input.equals("."))
								break;
							try
							{
								String[] parsed = input.split(",");
								if (parsed.length > 3)
									client.respond("Invalid command: " + input);
								else if (parsed[0].equalsIgnoreCase("add"))
									client.respond(Integer.parseInt(parsed[1]) + Integer.parseInt(parsed[2]) + "");
								else if (parsed[0].equalsIgnoreCase("sub"))
									client.respond(Integer.parseInt(parsed[1]) - Integer.parseInt(parsed[2]) + "");
								else if (parsed[0].equalsIgnoreCase("mul"))
									client.respond(Integer.parseInt(parsed[1]) * Integer.parseInt(parsed[2]) + "");
								else if (parsed[0].equalsIgnoreCase("div"))
									client.respond(Double.parseDouble(parsed[1]) / Double.parseDouble(parsed[2]) + "");
								else if (parsed[0].equalsIgnoreCase("kill"))
									stopThreadPool();
								else
									client.respond(input.toUpperCase());
							}
							catch (Exception e) { client.respond("Invalid command: " + input); }
						}
					}
					catch (IOException e) { System.out.println("I/O Exception encountered when processing Client #" + client.getID() + ": " + e.getMessage()); }
					catch (RuntimeException e) { System.out.println("Runtime Exception encountered when processing Client #" + client.getID() + ": " + e.getMessage()); }
					finally
					{
						try
						{
							this.isIdle = true;
							System.out.println("ProcessorThread " + this.id + " has finished servicing Client #" + client.getID() + ".");
							client.closeSocket();
						}
						catch (IOException e) { System.out.println("I/O Exception encountered when closing Client #" + client.getID() + "."); }
						catch (RuntimeException e) { System.out.println("Runtime Exception encountered closing Client #" + client.getID() + "."); }
						System.out.println("Connection with Client #" + client.getID() + " closed.");
					}
				}
			}
		}
	}
}
