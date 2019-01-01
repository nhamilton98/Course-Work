package cscd211Inheritance.Players;

public class FootballPlayer extends Player implements Cloneable
{
	protected int td;
	
	public FootballPlayer(final String firstName, final String lastName, final String ssn, final int salary, final String position, final int td)
	{
		super(firstName, lastName, ssn, salary, position);
		
		if (td < 0)
			throw new IllegalArgumentException("Invalid number of touchdowns.");
		
		this.td = td;
	}
	
	@Override
	public FootballPlayer clone() throws CloneNotSupportedException { return new FootballPlayer(this.getFirstName(), this.getLastName(), this.getSSN(), this.getSalary(), this.getPosition(), this.td); }
	
	@Override
	public String toString() { return super.toString() + "	Touchdowns: " + this.td + "\n"; }
}
