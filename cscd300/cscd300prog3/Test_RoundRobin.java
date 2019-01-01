// Nathan Hamilton
// CSCD300-02
// Dr. Bojian Xu
// Circular LinkedList

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test_RoundRobin
{
	public static void main(String[] args) throws Exception
	{
		String filename = args[0]; // Filename - "int,int" per line
		File file = new File(filename);
		final int TIME = Integer.parseInt(args[1]);
		
		LinkedList processes = readFile(file); // Create circular LinkedList from file
		CPU(processes, TIME); // Run the list of processes on the CPU
	}
	
	public static LinkedList readFile(File file) throws IOException
	{
		Scanner fin = new Scanner(file);
		
		String[] values = new String[2];
		LinkedList list = new LinkedList();
		while (fin.hasNextLine())
		{
			values = fin.nextLine().split(",");
			list.addOrdered(new Node(new ComputerProcess(Integer.parseInt(values[0]), Integer.parseInt(values[1]))));
		}
		
		fin.close();
		
		return list;
	}
	
	public static void CPU(final LinkedList list, final int time)
	{
		Node prev = list.cursor;
		while (prev.next != list.cursor)
			prev = prev.next; // Increment prev to one before cursor
		Node cur = list.cursor;
		while (!list.isEmpty())
		{
			cur.data.setTime(cur.data.getTime() - time); // Service current process
			
			if (cur.data.getTime() <= 0)
			{
				// Delete and print current PID
				System.out.println("Process Terminated: " + cur.data.getPID());
				list.remove(cur);
				cur = prev.next;
			}
			else
			{
				prev = cur;
				cur = cur.next;
			}
		}
	}
}