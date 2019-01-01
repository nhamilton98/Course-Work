// Nathan Hamilton
// CSCD300-02
// Dr. Bojian Xu
// Quick Sort

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test_ListQuickSort 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		LinkedList list = new LinkedList();
		
		list = readFile(args[0]); // Fill list with numbers from given file
		
		quickSort(list, list.head(), list.tail()); // QuickSort the list
		
		list.print(); // Print the list
	}
	
	private static void quickSort(final LinkedList list, final Node left, final Node right)
	{
		if (left.next == right || left == right || left == null || right == null) // Base cases
			return;
		
		Node node = partition(list, left.next, right.previous); // Set pivot
		
		quickSort(list, left, node); // Sort left side
		quickSort(list, node, right); // Sort right side
	}
	
	public static Node partition(final LinkedList list, Node left, Node right)
	{
		Node pivot = right;
		Node node = left;
		
		for (Node x = left; x != right; x = x.next)
		{
			if (x.data <= pivot.data) // Ensure Nodes are sorted
			{
				node = node.previous;
				x = x.next;
				
				list.swap(node.next, x.previous);
				
				node = node.next.next;
				x = x.previous;
			}
		}
		
		// Move Node pointers for swap
		node = node.previous;
		right = right.next;
		
		list.swap(node.next, right.previous); // Swap index and pivot Nodes
		
		// Move Node pointers back to correct Nodes
		node = node.next;
		right = right.next;
		
		return node; // Return index Node
	}
	
	public static LinkedList readFile(String filename) throws FileNotFoundException
	{
		File file = new File(filename);
		Scanner fin = new Scanner(file);
		
		LinkedList list = new LinkedList();
		
		// Fill list
		while(fin.hasNext())
			list.add(Integer.parseInt(fin.nextLine()));
		
		fin.close();
		
		return list;
	}
}