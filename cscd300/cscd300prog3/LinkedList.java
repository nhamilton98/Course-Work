public class LinkedList
{
	public Node cursor;
	private int size;
	
	public LinkedList()
	{
		this.cursor = null;
		this.size = 0;
	}
	
	public LinkedList(final Node cursor)
	{
		this.cursor = cursor;
		this.size = 1;
	}
	
	public int size() { return this.size; }
	
	public boolean isEmpty()
	{
		if (this.size == 0)
			return true;
		return false;
	}
	
	// Insert Nodes in ascending order by PID
	public void addOrdered(final Node n)
	{
		if (n == null)
			throw new IllegalArgumentException("Invalid node");
		
		Node temp = this.cursor;
		if (this.size == 0) // Empty list, add first
		{
			this.cursor = n;
			this.cursor.next = this.cursor;
		}
		else if (this.cursor.data.getPID() > n.data.getPID()) // Non-empty list, add first
		{
			n.next = this.cursor;
			this.cursor = n;
		}
		else
		{
			Node prev = this.cursor;
			while (temp.next != this.cursor && temp.data.getPID() <= n.data.getPID()) // Find correct location
			{
				prev = temp;
				temp = temp.next;
			}
			prev.next = n;
			n.next = temp;
		}
		this.size++; // Increment size
	}
	
	// Removes specified Node
	public void remove(final Node n)
	{
		if (this.isEmpty()) // Exception if the LinkedList is empty
			throw new NullPointerException("List is empty");
		
		else if (this.size == 1) // Remove first, only Node
			this.cursor = null;
		else if (this.cursor.data.getPID() == n.data.getPID()) // Remove first
		{
			Node prev = this.cursor;
			while (prev.next != this.cursor)
				prev = prev.next;
			Node cur = this.cursor;
			this.cursor = this.cursor.next;
			cur.next = null;
			prev.next = this.cursor;
		}
		else
		{
			Node cur = this.cursor;
			while (cur.next != n && cur.next != this.cursor)
				cur = cur.next;
			
			if (cur.next != this.cursor)
			{
				Node trash = cur.next;
				cur.next = cur.next.next;
				trash.next = null;
			}
		}
		this.size--; // Decrement size
	}
}