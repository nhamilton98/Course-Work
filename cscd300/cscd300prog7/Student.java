public class Student 
{
	private int ID;
	private String name;
	
	public Student(final int ID, final String name)
	{
		this.ID = ID;
		this.name = name;
	}
	
	public int getID() { return this.ID; }
	
	public void setName(final String name) { this.name = name; }
	
	@Override
	public String toString() { return "Student ID: " + this.ID + "\nStudent Name:" + this.name; }
}