package cscd211Inheritance;

public class Lawyer extends Employee
{
	private int stockOptions;
	
	public Lawyer(final String name, final double basePay, final double addPay, final int stockOptions)
	{
		super(name, basePay, addPay);
		
		if (stockOptions < 1)
			throw new IllegalArgumentException("Invalid number of stock options.");
		
		this.stockOptions = stockOptions;
	}
	
	public int getStockOptions() { return this.stockOptions; }
	
	public void setStockOptions(final int stockOptions)
	{
		if (stockOptions < 1)
			throw new IllegalArgumentException("Invalid number of stock options.");
		
		this.stockOptions = stockOptions;
	}
	
	@Override
	public void report() { System.out.println("I am a lawyer. I get $" + String.format("%.2f", super.getSalary()) + " and I have " + this.stockOptions + " shares of stock."); }

	@Override
	public String toString() { return "Lawyer: " + super.toString(); }
}
