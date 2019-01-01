package cscd211Inheritance;

public class Grad extends Student
{
	public Grad(final String name, final int id) { super(name, id); }
	
	@Override
	public String toString() { return "GRAD STUDENT -- " + super.toString(); }
}
