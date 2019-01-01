package cscd211Inheritance;

public class Undergrad extends Student
{
	private int gradYear;
	
	public Undergrad(final String name, final int id, final int year)
	{
		super(name, id);
		
		if (year < 2000)
			throw new IllegalArgumentException("Invalid grad year.");
		
		this.gradYear = year;
	}
	
	public int getGrad() { return this.gradYear; }
	
	@Override
	public String toString() { return "UNDERGRAD STUDENT -- " + super.toString() + " GRAD YEAR: " + this.gradYear; }
}
