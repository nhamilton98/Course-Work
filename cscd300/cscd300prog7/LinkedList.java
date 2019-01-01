public class LinkedList 
{	
	private Node head;
	private int size;
	
	public LinkedList()
	{
		this.head = null;
		this.size = 0;
	}
	
	public int size() { return this.size; }
	
	public Node head() { return this.head; }
	
	public void add(Node n)
	{
		if (this.size == 0)
			this.head = n;
		else
		{
			n.next = this.head;
			this.head = n;
		}
		
		this.size++;
	}
	
	public Node remove(int key)
	{
		if (this.size == 1)
		{
			Node ret = this.head;
			
			this.head = null;
			this.size--;
			
			return ret;
		}	
		else
		{
			Node cur = this.head;
			Node prev = this.head.next;
			
			while (cur.data.getID() != key)
			{
				prev = cur;
				cur = cur.next;
			}
			
			prev.next = cur.next;
			this.size--;
			
			return cur;
		}
	}
	
	public Node search(int key)
	{
		Node ret = this.head;
		
		while (ret != null && ret.data.getID() != key)
			ret = ret.next;
		
		return ret;
	}
}