package cscd211Inheritance.Players;

public class Player implements Cloneable, Comparable<Player>
{
	protected String firstName;
	protected String lastName;
	protected String position;
	protected int salary;
	protected String ssn;
	
	protected Player(final String firstName, final String lastName, final String ssn, final int salary, final String position)
	{
		if (firstName == null || firstName.equals(""))
			throw new IllegalArgumentException("Invalid first name.");
		if (lastName == null || lastName.equals(""))
			throw new IllegalArgumentException("Invalid last name.");
		if (ssn == null || ssn.equals(""))
			throw new IllegalArgumentException("Invalid SSN.");
		if (salary < 0)
			throw new IllegalArgumentException("Invalid salary.");
		if (position == null || position.equals(""))
			throw new IllegalArgumentException("Invalid position.");
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.salary = salary;
		this.position = position;
	}
	
	public int getSalary() { return this.salary; }
	
	public String getPosition() { return this.position; }
	
	public String getName() { return this.firstName + " " + this.lastName; }
	
	public String getSSN() { return this.ssn; }
	
	public String getFirstName() { return this.firstName; }
	
	public String getLastName() { return this.lastName; }
	
	@Override
	public Player clone() throws CloneNotSupportedException { return new Player(this.firstName, this.lastName, this.ssn, this.salary, this.position); }
	
	@Override
	public int compareTo(Player anotherPlayer)
	{
		if (anotherPlayer == null)
			throw new IllegalArgumentException("Cannot compare to null.");
		
		if (this.lastName.compareTo(anotherPlayer.lastName) > 0)
			return 1;
		else if (this.lastName.compareTo(anotherPlayer.lastName) < 0)
			return -1;
		else
		{
			if (this.firstName.compareTo(anotherPlayer.firstName) > 0)
				return 1;
			else if (this.firstName.compareTo(anotherPlayer.firstName) < 0)
				return -1;
			else
				return 0;
		}
	}
	
	@Override
	public String toString() { return this.getName() + " " + this.ssn + " $" + this.salary + " " + this.position; }
}
