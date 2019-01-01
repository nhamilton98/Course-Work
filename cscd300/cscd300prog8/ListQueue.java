public class ListQueue
{
	private class Node
	{
		public BSTNode data;
		public Node next;
		
		public Node(final BSTNode data)
		{
			this.data = data;
			this.next = null;
		}
	}
	
	private Node head;
	private Node tail;
	private int size;
	
	public ListQueue()
	{
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	public int size() { return this.size; }
	
	public boolean isEmpty()
	{
		if (this.size == 0)
			return true;
		
		return false;
	}
	
	public BSTNode front()
	{
		if (this.size == 0)
		{
			System.out.println("The queue is empty.");
			return null;
		}
		
		return this.head.data;
	}
	
	public void enqueue(final BSTNode n)
	{
		Node node = new Node(n);
		
		if (this.size == 0)
		{
			this.head = node;
			this.tail = node;
		}
		else
		{
			this.tail.next = node;
			this.tail = this.tail.next;
		}
		
		this.size++;
	}
	
	public BSTNode dequeue()
	{
		if (this.size == 0)
		{
			System.out.println("The queue is empty.");
			return null;
		}
		
		BSTNode ret = this.head.data;
		
		if (this.size == 1)
		{
			this.head = null;
			this.tail = null;
		}
		else
			this.head = this.head.next;
		
		this.size--;
		return ret;
	}
}