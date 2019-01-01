package cscd211LinkedList;

import java.util.NoSuchElementException;

public class LinkedList
{
	private class Node
	{
		public Comparable data;
		public Node next;
		
		public Node(final Comparable data)
		{
			if (data == null)
				throw new IllegalArgumentException("Invalid Node data.");
			
			this.data = data;
			this.next = null;
		}
		
		public Node(final Comparable data, final Node next)
		{
			if (data == null)
				throw new IllegalArgumentException("Invalid Node data.");
			
			this.data = data;
			this.next = next;
		}
	}
	
	private Node head;
	private int size;
	
	// No Dummy Head Node
	public LinkedList()
	{
		this.head = null;
		this.size = 0;
	}
	
	public void add(final int index, final Comparable item)
	{
		if (index < 0 || (index > this.size - 1 && this.size != 0))
			throw new IndexOutOfBoundsException("Invalid index.");
		if (item == null)
			throw new IllegalArgumentException("Invalid item.");
		
		if (index == 0)
			this.addFirst(item);
		else if (index == this.size - 1)
			this.addLast(item);
		else
		{
			Node node = new Node(item);
			
			Node curr = this.head;
			for (int i = 0; i < index - 1; i++)
				curr = curr.next;
			
			node.next = curr.next;
			curr.next = node;
			this.size++;
		}
	}
	
	public void addFirst(final Comparable item)
	{
		if (item == null)
			throw new IllegalArgumentException("Invalid item.");
		
		Node node = new Node(item);
		
		if (this.size != 0)
			node.next = this.head;
		
		this.head = node;
		this.size++;
	}
	
	public void addLast(final Comparable item)
	{
		if (item == null)
			throw new IllegalArgumentException("Invalid item");
		
		if (this.size == 0)
			this.addFirst(item);
		else
		{
			Node node = new Node(item);
			
			Node curr = this.head;
			while (curr.next != null)
				curr = curr.next;
			curr.next = node;
			this.size++;
		}
	}
	
	public void clear()
	{
		this.head = null;
		this.size = 0;
	}
	
	public boolean contains(final Comparable item)
	{
		if (item == null)
			throw new IllegalArgumentException("Invalid item.");
		
		if (this.size == 0)
			return false;
		
		Node curr = this.head;
		while (curr != null)
		{
			if (curr.data.compareTo(item) == 0)
				return true;
			
			curr = curr.next;
		}
		
		return false;
	}
	
	public int indexOf(final Comparable item)
	{
		if (item == null)
			throw new IllegalArgumentException("Invalid item.");
		
		if (this.size == 0)
			return -1;
		
		Node curr = this.head;
		int index = 0;
		while (curr != null)
		{
			if (curr.data.compareTo(item) == 0)
				return index;
			
			curr = curr.next;
			index++;
		}
		
		return -1;
	}
	
	public Comparable remove(final int index)
	{
		if (index < 0 || index > this.size - 1)
			throw new IndexOutOfBoundsException("Invalid index.");
		
		if (index == 0)
			return this.removeFirst();
		else if (index == this.size - 1)
			return this.removeLast();
		else
		{
			Node curr = this.head;
			Node prev = null;
			for (int i = 0; i < index; i++)
			{
				prev = curr;
				curr = curr.next;
			}
			Comparable ret = curr.data;
			
			prev.next = curr.next;
			this.size--;
			
			return ret;
		}
	}
	
	public Comparable removeFirst()
	{
		if (this.size == 0)
			throw new NoSuchElementException("Empty list.");
		
		Comparable ret = this.head.data;
		this.head = this.head.next;
		this.size--;
		
		return ret;
	}
	
	public boolean removeFirstOccurrence(final Comparable item)
	{
		if (item == null)
			throw new IllegalArgumentException("Invalid item.");
		
		if (this.contains(item))
		{
			this.remove(this.indexOf(item));
			return true;
		}
		else
			return false;
	}
	
	public Comparable removeLast()
	{
		if (this.size == 0)
			throw new NoSuchElementException("Empty list.");
		
		if (this.size == 1)
			return this.removeFirst();
		else
		{
			Node curr = this.head;
			Node prev = null;
			while (curr.next != null)
			{
				prev = curr;
				curr = curr.next;
			}
			Comparable ret = curr.data;
			
			prev.next = null;
			this.size--;
			
			return ret;
		}
	}
	
	public boolean removeLastOccurrence(final Comparable item)
	{
		if (item == null)
			throw new IllegalArgumentException("Invalid item.");
		
		if (this.contains(item))
		{
			int index = -1;
			Node curr = this.head;
			for (int i = 0; curr != null; i++)
			{
				if (curr.data.compareTo(item) == 0)
					index = i;
				curr = curr.next;
			}
			this.remove(index);
			return true;
		}
		else
			return false;
	}
	
	public int size() { return this.size; }
	
	@Override
	public String toString()
	{
		if (this.size == 0)
			return "Empty List";
		
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		
		Node curr = this.head;
		while (curr != null)
		{
			builder.append(curr.data.toString() + ", ");
			curr = curr.next;
		}
		
		return builder.substring(0, builder.length() - 2) + "]";
	}
}
