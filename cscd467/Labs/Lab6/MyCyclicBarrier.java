import java.util.concurrent.BrokenBarrierException;

public class MyCyclicBarrier
{
	private int threadCount;
	private Runnable command;
	private int notReachedCount;
	private boolean inCycle;
	private boolean isBroken;
	
	public MyCyclicBarrier(int parties, Runnable barrierAction)
	{
		this.command = barrierAction;
		this.threadCount = parties;
		this.notReachedCount = this.threadCount;
		this.inCycle = false;
		this.isBroken = false;
	}
	
	public synchronized int await() throws InterruptedException, BrokenBarrierException
	{
		int threadIndex = this.threadCount - this.notReachedCount;
		
		while (!this.inCycle && this.notReachedCount != this.threadCount)
			this.wait();
		
		if (!this.inCycle)
			inCycle = true;
		
		if (this.notReachedCount > 1)
		{
			this.notReachedCount--;
			while (this.inCycle)
			{
				try { this.wait(); }
				catch (InterruptedException e)
				{
					this.isBroken = true;
					throw new InterruptedException();
				}
				
				if (this.isBroken)
				{
					this.notifyAll();
					throw new BrokenBarrierException();
				}
				if (!this.inCycle)
					this.notReachedCount++;
				if (this.notReachedCount == this.threadCount)
					this.notifyAll();
			}
		}
		else if (this.notReachedCount == 1)
		{
			this.inCycle = false;
			this.command.run();
			this.notifyAll();			
		}
		return threadIndex;
	}
}