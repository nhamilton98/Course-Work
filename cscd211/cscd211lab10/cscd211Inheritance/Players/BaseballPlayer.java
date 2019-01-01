package cscd211Inheritance.Players;

public class BaseballPlayer extends Player implements Cloneable
{
	protected double batAvg;
	
	public BaseballPlayer(final String firstName, final String lastName, final String ssn, final int salary, final String position, final double batAvg)
	{
		super(firstName, lastName, ssn, salary, position);
		
		if (batAvg < 0 || batAvg > 1)
			throw new IllegalArgumentException("Invalid batting average.");
		
		this.batAvg = batAvg;
	}
	
	@Override
	public BaseballPlayer clone() throws CloneNotSupportedException { return new BaseballPlayer(this.getFirstName(), this.getLastName(), this.getSSN(), this.getSalary(), this.getPosition(), this.batAvg); }
	
	@Override
	public String toString() { return super.toString() + "	Batting Avg: " + this.batAvg + "\n"; }
}
