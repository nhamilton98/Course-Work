public class ComputerProcess 
{
	private int PID;
	private int time;
	
	public ComputerProcess(final int PID, final int time)
	{
		this.PID = PID;
		this.time = time;
	}
	
	public void setTime(final int time) { this.time = time; }
	
	public int getPID() { return this.PID; }
	
	public int getTime() { return this.time; }
	
	@Override
	public String toString() { return "" + this.PID; }
}