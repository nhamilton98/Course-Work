public class ListQueue implements Queue
{
	private class Node
	{
		public int data;
		public Node next;
		
		public Node(final int data)
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
	
	public int front()
	{
		if (this.size == 0)
		{
			System.out.println("The queue is empty.");
			return -1;
		}
		
		return this.head.data;
	}
	
	public void enqueue(final int num)
	{
		Node node = new Node(num);
		
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
	
	public int dequeue()
	{
		if (this.size == 0)
		{
			System.out.println("The queue is empty.");
			return -1;
		}
		
		int ret = this.head.data;
		
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