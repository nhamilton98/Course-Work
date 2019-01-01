package cscd211Utils;

import java.io.PrintStream;

public class ArrayUtils
{
	public static void printArray(final Comparable[] array)
	{
		for (Comparable comp: array)
			System.out.println(comp);
	}
	
	public static void printArray(final Comparable[] array, final PrintStream fout)
	{
		for (Comparable comp: array)
			fout.println(comp);
	}
	
	public static void selectionSort(final Comparable[] array)
	{
		int min;
		Comparable temp;
		for (int i = 0; i < array.length - 1; i++)
		{
			min = i;
			for (int j = i + 1; j < array.length; j++)
			{
				if (array[j].compareTo(array[min]) < 0)
					min = j;
			}
			
			if (min != i)
			{
				temp = array[min];
				array[min] = array[i];
				array[i] = temp;
			}
		}
	}
}
