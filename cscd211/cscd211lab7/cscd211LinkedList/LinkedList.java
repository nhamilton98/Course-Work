package cscd211LinkedList;

import java.util.NoSuchElementException;

import cscd211Classes.BoxCar;

public class LinkedList
{
	private class Node
	{
		public Comparable data;
		public Node next;
		
		public Node(final Comparable data)
		{
			this.data = data;
			this.next = null;
		}
		
		public Node(final Comparable data, final Node next)
		{
			this.data = data;
			this.next = next;
		}
	}
	
	private Node head;
	private int size;
	
	// Dummy Head Node
	public LinkedList(final Comparable[] array)
	{
		this.head = new Node(null);
		this.size = 0;
		
		this.addAll(0, array);
	}
	
	public boolean add(final Comparable item)
	{
		if (item == null)
			throw new IllegalArgumentException("Invalid item.");
		
		if (this.size == 0)
			this.head.next = new Node(item);
		else
		{
			Node curr = this.head;
			while (curr.next != null)
				curr = curr.next;
			
			curr.next = new Node(item);
		}
		this.size++;
		
		return true;
	}
	
	public boolean addAll(final int startIndex, final Comparable[] array)
	{
		if (startIndex < 0 || startIndex > this.size)
			throw new IndexOutOfBoundsException("Invalid start index.");
		if (array == null)
			throw new IllegalArgumentException("Invalid array.");
		
		if (startIndex == 0)
		{
			for (int i = array.length - 1; i >= 0; i--)
				this.addFirst(array[i]);
		}
		else if (startIndex == this.size - 1)
		{
			for (int i = 0; i < array.length; i++)
				this.add(array[i]);
		}
		else
		{
			Node curr = this.head;
			for (int i = 0; i < startIndex; i++)
				curr = curr.next;
			
			Node next;
			for (int i = 0; i < array.length; i++)
			{
				next = new Node(array[i], curr.next);
				curr.next = next;
				this.size++;
				
				curr = curr.next;
			}
		}
		
		return true;
	}
	
	public void addFirst(final Comparable item)
	{
		if (item == null)
			throw new IllegalArgumentException("Invalid item.");
		
		this.head.next = new Node(item, this.head.next);
		this.size++;
	}
	
	public void clear()
	{
		this.head.next = null;
		this.size = 0;
	}
	
	public Comparable get(final int index)
	{
		if (index < 0 || index > this.size - 1)
			throw new IndexOutOfBoundsException("Invalid index.");
		
		Node curr = this.head.next;
		for (int i = 0; i < index; i++)
			curr = curr.next;
		
		return curr.data;
	}
	
	public Comparable getLast()
	{
		if (this.size == 0)
			throw new NoSuchElementException("Empty list.");
		
		Node curr = this.head;
		while (curr.next != null)
			curr = curr.next;
		
		return curr.data;
	}
	
	public Comparable remove()
	{
		if (this.size == 0)
			throw new NoSuchElementException("Empty list.");
		
		Node curr = this.head.next;
		Comparable ret = curr.data;
		
		this.head.next = curr.next;
		this.size--;
		
		return ret;
	}
	
	public Comparable remove(final int index)
	{
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException("Invalid index.");
		
		if (index == 0)
			return this.remove();
		else if (index == this.size - 1)
			return this.removeLast();
		else
		{
			Node curr = this.head.next;
			Node prev = this.head;
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
	
	public Comparable removeLast()
	{
		if (this.size == 0)
			throw new NoSuchElementException("Empty list.");
		
		Node curr = this.head.next;
		Node prev = this.head;
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
	
	public int size() { return this.size; }
	
	public Comparable[] toArray()
	{
		Comparable[] temp = new Comparable[this.size];
		
		Node curr = this.head.next;
		for (int i = 0; i < temp.length; i++)
		{
			temp[i] = curr.data;
			curr = curr.next;
		}
		
		return temp;
	}
	
	@Override
	public String toString()
	{
		if (this.size == 0)
			return "Empty List";
		
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		
		Node curr = this.head.next;
		while (curr != null)
		{
			builder.append(curr.data.toString() + ", ");
			curr = curr.next;
		}
		
		return builder.substring(0, builder.length() - 2) + "]";
	}
}
