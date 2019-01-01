public class Node 
{
	public Node next;
	public Student data;
	
	public Node(final int ID, final String name)
	{
		this.next = null;
		this.data = new Student(ID, name);
	}
	
	public Node(final Student student)
	{
		this.next = null;
		this.data = student;
	}
}