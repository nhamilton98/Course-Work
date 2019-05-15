import java.util.LinkedList;

public class SharedQueue
{
	public static final int CAPACITY = 100;
	
	private LinkedList<String> queueList;
	private int size;
	private boolean endOfFile;
	
	public SharedQueue()
	{
		this.queueList = new LinkedList<String>();
		this.size = 0;
		this.endOfFile = false;
	}
	
	public synchronized boolean isEmpty() { return this.queueList.isEmpty(); }
	
	public synchronized int size() { return this.size; }
	
	public synchronized void markEndOfFile() { this.endOfFile = true; }
	
	public synchronized boolean atEndOfFile() { return this.endOfFile; }
	
	public synchronized void enqueue(String s)
	{
		while (this.size >= SharedQueue.CAPACITY)
		{
			try { this.wait(); }
			catch (InterruptedException e) { e.printStackTrace(); }
		}

		this.queueList.addLast(s);
		this.size++;
		
		this.notifyAll();
	}
	
	public synchronized String dequeue()
	{
		while (this.isEmpty())
		{
			try { this.wait(); }
			catch (InterruptedException e) { e.printStackTrace(); }
		}

		String ret = this.queueList.getFirst();
		this.queueList.removeFirst();
		this.size--;
		
		this.notify();
		return ret;
	}
}
