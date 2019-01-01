public class LinkedList 
{	
	private Node head;
	private Node tail;
	private int size;
	
	public LinkedList()
	{
		this.head = new Node();
		this.tail = new Node();
		this.size = 0;
		
		this.head.next = this.tail;
		this.tail.previous = this.head;
	}
	
	public int size() { return this.size; }
	
	public boolean isEmpty()
	{
		if (this.size == 0)
			return true;
		return false;
	}
	
	public Node head() { return this.head; }
	
	public Node tail() { return this.tail; }
	
	public void add(int num)
	{
		Node node = new Node(num);
		
		node.next = this.tail;
		node.previous = this.tail.previous;
		this.tail.previous.next = node;
		this.tail.previous = node;
		
		this.size++;
	}
	
	public void swap(final Node x, final Node y)
	{	
		if (x == y)
			return;
		else if (x.next == y)
		{
			x.next = y.next;
			y.previous = x.previous;
			y.next = x;
			x.previous = y;
		}
		else
		{
			Node temp1 = y.previous;
			Node temp2 = y.next;
			
			y.previous = x.previous;
			y.next = x.next;
			x.previous = temp1;
			x.next = temp2;
			
			y.next.previous = y;
			y.previous.next = y;
			x.next.previous = x;
			x.previous.next = x;
		}
	}
	
	public void print()
	{
		Node cur = this.head.next;
		
		while (cur != this.tail)
		{
			System.out.println(cur.data);
			cur = cur.next;
		}
	}
}