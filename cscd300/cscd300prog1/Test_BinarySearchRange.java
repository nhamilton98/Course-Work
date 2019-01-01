// Nathan Hamilton
// CSCD300-02
// Dr. Bojian Xu
// Binary Search

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Test_BinarySearchRange
{
	public static void main(String[] args) throws IOException // Time Complexity: T(c + 2 * log n) = O(log n)
	{
		String filename = args[0]; // Filename - one int per line, ascending order
		int min = Integer.parseInt(args[1]); // Min value
		int max = Integer.parseInt(args[2]); // Max value
		
		int[] array = readFile(filename); // Create array from file
		if (array.length == 0 || min > max)
			System.out.println("Null");
		else if (max < array[0])
			System.out.println("Null");
		else if (min > array[array.length - 1])
			System.out.println("Null");
		else
		{
			int minIndex = binaryRangeMin(array, min); // Find min index
			int maxIndex = binaryRangeMax(array, max); // Find max index
			
			if (minIndex == -1 || maxIndex == -1 || minIndex > maxIndex) // Values not found
				System.out.println("Null");
			else
				System.out.println("A[" + minIndex + ".." + maxIndex + "]");
		}
	}
	
	public static int binaryRangeMin(final int[] array, final int min) // Time cost: T(log(n))
	{
		if (array[0] >= min)
			return 0;
		
		int low = 0;
		int high = array.length - 1;
		int mid;
		
		while (low <= high)
		{
			mid = (int) Math.floor((low + high) / 2);
			if (array[mid] == min)
			{
				if (array[mid - 1] < min)
					return mid; // Value found
				else if (array[mid - 1] == min)
					high = mid - 1; // Search left side
			}
			else if (array[mid] > min)
			{
				if (array[mid - 1] < min)
					return mid; // Value found
				else
					high = mid - 1; // Search left side
			}
			else
				low = mid + 1; // Search right side
		}
		return -1; // Value not found
	}
   
	public static int binaryRangeMax(final int[] array, final int max) // Time cost: T(log(n))
	{
		if (array[array.length - 1] <= max)
			return array.length - 1;
		
		int low = 0;
		int high = array.length - 1;
		int mid;
		
		while (low <= high)
		{
			mid = (int) Math.floor((low + high) / 2);
			
			if (array[mid] == max)
			{
				if (array[mid + 1] > max)
					return mid; // Value found
				else if (array[mid + 1] == max)
					low = mid + 1; // Search right side
			}
			else if (array[mid] < max)
			{
				if (array[mid + 1] > max)
					return mid; // Value found
				else
					low = mid + 1; // Search right side
			}
			else
				high = mid - 1; // Search left side
		}
		return -1; // Value was not found
	}
	
	public static int[] readFile(String filename) throws IOException
	{
		File file = new File(filename);
		Scanner fin = new Scanner(file);
		
		int count = 0;
		while (fin.hasNext()) // File line count
		{
			count++;
			fin.nextLine();
		}
		int[] array = new int[count];
		
		fin.close();
		fin = new Scanner(file);
		
		for (int i = 0; i < array.length; i++) // Transfer file contents into the array
			array[i] = Integer.parseInt(fin.nextLine());
		
		fin.close();
		
		return array;
	}
}