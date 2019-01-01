public class Node 
{
	public Node next;
	public Node previous;
	public int data;
	
	public Node()
	{
		this.next = null;
		this.previous = null;
		this.data = 0;
	}
	
	public Node(final int num)
	{
		this.next = null;
		this.previous = null;
		this.data = num;
	}
}