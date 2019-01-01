// Nathan Hamilton
// CSCD300-02
// Dr. Bojian Xu

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test_BestTrading
{
	public static void main(String[] args) throws Exception
	{
		String fileName = args[0]; // Filename - one int per line
		int[] array = readFile(fileName); // Create array of trading prices from file
		
		Profit profit = bestTrade(array, 0, array.length - 1); // Find the best trade
		System.out.println(profit); 
	}
    
	public static Profit bestTrade(final int[] array, final int leftBound, final int rightBound)
	{
		if (leftBound == rightBound) // Base case
			return new Profit(leftBound, rightBound, 0);
		
		Profit profit1;
		Profit profit2;
		Profit profit3;
		int mid = (int) Math.floor((leftBound + rightBound) / 2);
		
		profit1 = bestTrade(array, leftBound, mid); // Find best trade on left half of array
		profit2 = bestTrade(array, mid + 1, rightBound); // Find best trade on right half of array
		
		// Find best trade across entire array
		int min = array[0];
		int minIndex = 0;
		for (int x = 0; x < mid; x++) // Find min value
		{
			if (array[x] < min)
			{
				min = array[x];
				minIndex = x;
			}
		}
		
		int max = array[mid];
		int maxIndex = mid;
		for (int x = mid + 1; x < array.length; x++) // Find max value
		{
			if (array[x] > max)
			{
				max = array[x];
				maxIndex = x;
			}
		}
		profit3 = new Profit(minIndex, maxIndex, max - min);
		
		// Compare
		if (profit1.getProfit() > profit2.getProfit() && profit1.getProfit() > profit3.getProfit())
			return profit1;
		else if (profit2.getProfit() > profit1.getProfit() && profit2.getProfit() > profit3.getProfit())
			return profit2;
		else
			return profit3;
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