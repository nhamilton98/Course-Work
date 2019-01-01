package cscd211Inheritance;

public class Programmer extends Employee
{
	private boolean busPass;
	
	public Programmer(final String name, final double basePay, final double addPay, final boolean busPass)
	{
		super(name, basePay, addPay);
		
		this.busPass = busPass;
	}
	
	public boolean getBusPass() { return this.busPass; }
	
	public void setBusPass(final boolean busPass) { this.busPass = busPass; }
	
	@Override
	public void report()
	{
		StringBuilder build = new StringBuilder();
		
		build.append("I am a programmer. I get $" + String.format("%.2f", super.getSalary()) + " and I ");
		
		if (!busPass)
			build.append("do not ");
		
		build.append("get a bus pass.");
		
		System.out.println(build);
	}
	
	@Override
	public String toString() { return "Programmer: " + super.toString(); }
}
