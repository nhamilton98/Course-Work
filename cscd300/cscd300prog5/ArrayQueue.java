public class ArrayQueue implements Queue
{
	private static final int CAPACITY = 10;
	
	private int capacity;
	private int[] queue;
	private int head;
	private int tail;
	private int size;
	
	public ArrayQueue()
	{
		this.capacity = CAPACITY;
		this.queue = new int[this.capacity];
		this.head = -1;
		this.tail = -1;
		this.size = 0;
	}
	
	public ArrayQueue(final int size)
	{
		this.capacity = size;
		this.queue = new int[this.capacity];
		this.head = -1;
		this.tail = -1;
		this.size = 0;
	}
	
	public int size() { return this.size; }
	
	public int front()
	{
		if (this.size == 0)
		{
			System.out.println("The queue is empty.");
			return -1;
		}
		
		return this.queue[this.head];
	}
	
	public void enqueue(final int num)
	{
		if (this.size == this.capacity)
			System.out.println("The queue is full.");
		else if(this.size == 0)
		{
			this.queue[0] = num;
			this.head = 0;
			this.tail = 0;
			
			this.size++;
		}
		else
		{
			this.tail = (this.tail + 1) % this.capacity;
			this.queue[this.tail] = num;
			
			this.size++;
		}
	}
	
	public int dequeue()
	{
		if (this.size == 0)
		{
			System.out.println("The queue is empty.");
			return -1;
		}
		
		int ret = this.queue[this.head];
		this.queue[this.head] = 0;
		
		if (this.size == 1)
		{
			this.head = -1;
			this.tail = -1;
		}
		else
			this.head = (this.head + 1) % this.capacity;
		
		this.size--;
		return ret;
	}
}