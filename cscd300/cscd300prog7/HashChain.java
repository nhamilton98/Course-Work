public class HashChain implements Map
{
	private int size;
	public LinkedList[] table;
	
	public HashChain(final int size)
	{
		this.size = 0;
		this.table = new LinkedList[size];
		
		for (int x = 0; x < this.table.length; x++)
			this.table[x] = new LinkedList();
	}
	
	public int hash(int key) { return (7 * key + 29) % 5; }
	
	public int size() { return this.size; }
	
	public Student get(int key)
	{
		Node temp = this.table[hash(key)].search(key);
		
		if (temp == null)
			return null;
		
		return temp.data;
	}
	
	public Student put(Student student)
	{
		Node n = new Node(student);
		Node temp = this.table[hash(student.getID())].search(student.getID());
		
		if (temp == null)
		{
			this.table[hash(student.getID())].add(n);
			this.size++;
			
			System.out.println("RECORD ADDED");
			
			return null;
		}
		
		else
		{
			Student ret = temp.data;
			
			n.next = temp.next;
			temp = n;
			
			System.out.println("RECORD UPDATED");
			
			return ret;
		}
	}
	
	public Student remove(int key)
	{
		Node temp = this.table[hash(key)].search(key);
		
		if (temp != null)
		{
			Node prev = this.table[hash(key)].head();
			
			while (prev.next != temp)
				prev = prev.next;
		
			prev.next = temp.next;
			this.size--;
			
			System.out.println("RECORD DELETED");
			return temp.data;
		}
		
		System.out.println("RECORD DOES NOT EXISTS");
		return null;
	}
}