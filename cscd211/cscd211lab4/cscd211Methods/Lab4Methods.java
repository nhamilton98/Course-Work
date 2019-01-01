package cscd211Methods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Lab4Methods
{
	public static void addNum(final ArrayList<Double> list, final Scanner kin)
	{
		if (list == null)
			throw new IllegalArgumentException("Invalid ArrayList.");
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		double num;
		do
		{
			System.out.println("ENTER NUMBER [0.0, 100.0]:");
			num = Double.valueOf(kin.nextLine());
			
			if (num < 0 || num > 100)
				System.out.println("Invalid number.");
		} while (num < 0 || num > 100);
		
		list.add(num);
		list.trimToSize();
	}
	
	public static double computeMean(final ArrayList<Double> list)
	{
		if (list == null || list.size() == 0)
			throw new IllegalArgumentException("Invalid ArrayList.");
		
		double sum = 0;
		for (Double num: list)
			sum += num;
		
		return sum / (double)list.size();
	}
	
	public static double computeMedian(final ArrayList<Double> list)
	{
		if (list == null || list.size() == 0)
			throw new IllegalArgumentException("Invalid ArrayList");
		
		Collections.sort(list);
		if (list.size() % 2 == 1)
			return list.get(list.size() / 2);
		else
			return (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2.0;
	}
	
	public static double computeMidpoint(final ArrayList<Double> list)
	{
		if (list == null || list.size() == 0)
			throw new IllegalArgumentException("Invalid ArrayList.");
		
		if (list.size() == 1)
			return list.get(0);
		else
		{
			Collections.sort(list);
			return (list.get(0) + list.get(list.size() - 1)) / 2.0;
		}
	}
	
	public static double computeStandardDeviation(final ArrayList<Double> list)
	{
		if (list == null || list.size() == 0)
			throw new IllegalArgumentException("Invalid ArrayList.");
		
		double mean = Lab4Methods.computeMean(list);
		
		ArrayList<Double> devs = new ArrayList<>();
		for(Double num: list)
			devs.add(num - mean);
		
		double devSqSum = 0;
		for(Double num: devs)
			devSqSum += Math.pow(num,  2);
		
		return Math.sqrt(devSqSum / (double)list.size() - 1);
	}
	
	public static void deleteValue(final ArrayList<Double> list, final Scanner kin)
	{
		if (list == null)
			throw new IllegalArgumentException("Invalid ArrayList.");
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner");
		
		if (list.size() > 0)
		{
			double num;
			do
			{
				System.out.println("NUMBER TO DELETE [0.0, 100.0]:");
				num = Double.parseDouble(kin.nextLine());
				
				if (num < 0 || num > 100)
					System.out.println("Invalid number.");
			} while (num < 0 || num > 100);
			
			if (list.contains(num))
				System.out.println(num + " removed from the ArrayList.");
			else
				System.out.println(num + " does not exist in the ArrayList.");
		}
		else
			System.out.println("The ArrayList is empty.");
	}
	
	public static void deleteValueByIndex(final ArrayList<Double> list, final Scanner kin)
	{
		if (list == null)
			throw new IllegalArgumentException("Invalid ArrayList.");
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		if (list.size() > 0)
		{
			int index;
			do
			{
				System.out.println("INDEX TO DELETE:");
				index = Integer.parseInt(kin.nextLine());
				
				if (index < 0)
					System.out.println("Invalid index.");
				if (index > list.size() - 1)
					System.out.println("Index is too large.");
			} while (index < 0 || index > list.size() - 1);
			
			System.out.println(list.remove(index) + " removed from the ArrayList.");
		}
		else
			System.out.println("The ArrayList is empty.");
	}
	
	public static void fillArrayList(final int size, final ArrayList<Double> list)
	{
		if (size < 1)
			throw new IllegalArgumentException("Invalid size.");
		if (list == null)
			throw new IllegalArgumentException("Invalid ArrayList.");
		
		Random rand = new Random();
		for (int i = 0; i < size; i++)
			list.add(rand.nextDouble() * 100);
	}
	
	public static int menu(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		int choice;
		do
		{
			System.out.println("ARRAYLIST MENU");
			System.out.println("------------------------------");
			System.out.println("1.) ADD VALUE");
			System.out.println("2.) DELETE BY VALUE");
			System.out.println("3.) DELETE BY INDEX");
			System.out.println("4.) DISPLAY ARRAYLIST");
			System.out.println("5.) COMPUTE MEAN");
			System.out.println("6.) COMPUTE MEDIAN");
			System.out.println("7.) COMPUTE MIDPOINT");
			System.out.println("8.) COMPUTE STANDARD DEVIAITON");
			System.out.println("\n9.) QUIT");
			System.out.println("------------------------------");
			System.out.println("CHOICE:");
			choice = Integer.parseInt(kin.nextLine());
			
			if (choice < 1 || choice > 9)
				System.out.println("Invalid choice.");
		} while (choice < 1 || choice > 9);
		
		return choice;
	}
	
	public static void printArrayList(final ArrayList<Double> list)
	{
		if (list == null)
			throw new IllegalArgumentException("Invalid ArrayList.");
		
		if (list.size() == 0)
			System.out.println("Empty ArrayList.");
		else
			System.out.println(list);
	}
	
	public static void printResults(final String type, final double value)
	{
		if (type == null || type.equals(""))
			throw new IllegalArgumentException("Invalid result type.");
		
		System.out.println(type + ": " + value);
	}
	
	public static int readNum(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		int num;
		do
		{
			System.out.println("ENTER SIZE:");
			num = Integer.parseInt(kin.nextLine());
			
			if (num < 1)
				System.out.println("Invalid number.");
		} while (num < 1);
		
		return num;
	}
}
