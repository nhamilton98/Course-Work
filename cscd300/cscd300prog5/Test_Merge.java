// Nathan Hamilton
// CSCD300-02
// Dr. Bojian Xu
// Queue

import java.io.FileNotFoundException;

public class Test_Merge 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Holder[] inputs = new Holder[args.length]; 
		
		for (int x = 0; x < args.length; x++)
			inputs[x] = new Holder(args[x]); // Two or more filenames - one int per line, ascending order
		
		displaySolution(merge(inputs));
	}
	
	public static ListQueue merge(final Holder[] inputs) throws FileNotFoundException
	{
		ListQueue solution = new ListQueue();
		
		int totalLineCount = 0;
		for (int  x = 0; x < inputs.length; x++)
			totalLineCount += inputs[x].lineCount;
		
		for (int x = 0; x < inputs.length; x++)
			inputs[x].initialFill(); // Fill ArrayQueues with first 10 ints
		
		int min;
		int index;
		while(solution.size() != totalLineCount)
		{
			min = inputs[0].queue.front();
			index = 0;
			
			for (int x = 0; x < inputs.length; x++)
			{
				if (inputs[x].queue.front() < min) // Find min int from ArrayQueues
				{
					min = inputs[x].queue.front();
					index = x;
				}
			}
			
			solution.enqueue(inputs[index].queue.dequeue()); // Enqueue min value to solution Queue
			inputs[index].fill(); // Enqueue next value if exists
		}
		
		return solution;
	}
	
	public static void displaySolution(final ListQueue solution)
	{
		while (!solution.isEmpty())
			System.out.print(solution.dequeue() + " ");
		System.out.println();
	}
}