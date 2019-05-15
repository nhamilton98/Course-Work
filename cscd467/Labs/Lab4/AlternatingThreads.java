public class AlternatingThreads
{	
	private Condition condition;
	
	public AlternatingThreads(int threadCount)
	{
		this.condition = new Condition();
		
		for (int i = 0; i < threadCount; i++)
		{
			Thread t = new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					for (int i = 1; i <= 10; i++)
						condition.conditionalWait(threadCount, i);
				}
			});
			t.setName(i + "");
			t.start();
		}	
	}

	
	private static class Condition
	{
		private int currentThread;
		
		public Condition()
		{
			this.currentThread = 0;
		}
		
		public synchronized void conditionalWait(int threadCount, int messageNumber)
		{
			Thread t = Thread.currentThread();
			while (this.currentThread != Integer.parseInt(t.getName()))
			{
				try { this.wait(); }
				catch(InterruptedException e) { e.printStackTrace(); }
			}
			System.out.println("Message " + messageNumber + " from thread " + Integer.parseInt(t.getName()));
			this.currentThread++;
			if (this.currentThread > threadCount - 1)
				this.currentThread = 0;
			this.notifyAll();
		}
	}
	
	public static void main(String[] args) { new AlternatingThreads(Integer.parseInt(args[0])); }
}