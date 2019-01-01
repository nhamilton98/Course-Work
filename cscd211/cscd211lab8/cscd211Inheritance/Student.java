package cscd211Inheritance;

public class Student implements Comparable<Student>
{
	protected int id;
	private String name;
	
	protected Student(final String name, final int id)
	{
		if (name == null || name.equals(""))
			throw new IllegalArgumentException("Invalid name.");
		if (id < 0)
			throw new IllegalArgumentException("Invalid ID.");
		
		this.name = name;
		this.id = id;
	}
	
	public int getID() { return this.id; }
	
	public String getName() { return this.name; }
	
	public String getType() { return this.getClass().toString(); }
	
	@Override
	public int compareTo(Student anotherStudent)
	{
		if (this.name.compareTo(anotherStudent.name) > 0)
			return 1;
		else if (this.name.compareTo(anotherStudent.name) < 0)
			return -1;
		else
			return 0;
	}
	
	@Override
	public String toString() { return "NAME: " + this.name + " ID: " + this.id; }
}
