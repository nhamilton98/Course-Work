public class Stack
{
	private class Node // Embedded Node class
	{
		public int data;
		public Node next;
		
		public Node(int num)
		{
			this.data = num;
			this.next = null;
		}
		
		public Node(int num, Node next)
		{
			this.data = num;
			this.next = next;
		}
	}
	
	protected int size;
	protected Node top;
	
	public Stack()
	{
		this.size = 0;
		this.top = null;
	}
	
	public int size() { return this.size; }
	
	public boolean isEmpty()
	{
		if (this.size == 0)
			return true;
		return false;
	}
	
	public int top() // Returns top value
	{
		if (size == 0)
			return -1;
		return this.top.data;
	}
	
	public void push(int num) // Add value to top
	{
		if (this.size == 0)
			this.top = new Node(num);
		else
			this.top = new Node(num, this.top);
		this.size++;
	}
	
	public int pop() // Delete and return top value
	{
		if (this.size == 0)
			throw new NullPointerException("The stack is empty");
		
		int ret = this.top.data;
		
		if (this.size == 1)
			this.top = null;
		else
			this.top = this.top.next;
		this.size--;
		
		return ret;
	}
}