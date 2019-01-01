package cscd211Inheritance;

public class Accountant extends Employee
{
	private double parkingStipend;
	
	public Accountant(final String name, final double basePay, final double addPay, final double parkingStipend)
	{
		super(name, basePay, addPay);
		
		if (parkingStipend < 1)
			throw new IllegalArgumentException("Invalid parking stipend amount.");
		
		this.parkingStipend = parkingStipend;
	}
	
	public double getParkingStipend() { return this.parkingStipend; }
	
	public void setParkingStipend(final double parkingStipend)
	{
		if (parkingStipend < 1)
			throw new IllegalArgumentException("Invalid parking stipend amount.");
		
		this.parkingStipend = parkingStipend;
	}
	
	@Override
	public void report() { System.out.println("I am an accountant. I make $" + String.format("%.2f", super.getSalary()) + " plus a parking stipend of $" + String.format("%.2f", this.parkingStipend) + "."); }
	
	@Override
	public String toString() { return "Programmer: " + super.toString(); }
}
