public class Node
{
	public Node next;
	public ComputerProcess data;
	
	public Node(final ComputerProcess data)
	{
		this.data = data;
		this.next = null;
	}
	
	public Node(final ComputerProcess data, final Node next)
	{
		this.data = data;
		this.next = next;
	}
	
	@Override
	public String toString() { return data.toString(); }
}