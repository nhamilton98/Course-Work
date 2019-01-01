package cscd211Methods;

import java.util.Scanner;

import cscd211Classes.Sandwich;

public class Lab1Methods
{
	public static Sandwich[] fillArray(final Scanner fin)
	{
		if (fin == null)
			throw new IllegalArgumentException("Invalid file Scanner.");
		
		int count = Integer.parseInt(fin.nextLine());
		Sandwich[] sandwiches = new Sandwich[count];
		int index = 0;
		
		String name;
		int calories, topCount;
		String[] toppings;
		Sandwich temp;
		while (fin.hasNext())
		{
			name = fin.nextLine();
			calories = Integer.parseInt(fin.nextLine());
			topCount = Integer.parseInt(fin.nextLine());
			toppings = new String[topCount];
			for (int i = 0; i < topCount; i++)
				toppings[i] = fin.nextLine();
			
			temp = new Sandwich(name, calories, toppings);
			sandwiches[index] = temp;
			index++;
		}
		
		return sandwiches;
	}
	
	public static int menu(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		int choice;
		do
		{
			System.out.println("SANDWICH MENU");
			System.out.println("-------------------------");
			System.out.println("1.) PRINT ARRAY TO SCREEN");
			System.out.println("2.) SORT BY METHOD");
			System.out.println("3.) SORT BY COMPARATOR");
			System.out.println("4.) PRINT ARRAY TO FILE");
			System.out.println("\n5.) QUIT");
			System.out.println("-------------------------");
			System.out.println("\nCHOICE:");
			choice = Integer.parseInt(kin.nextLine());
			
			if (choice < 1 || choice > 5)
				System.out.println("Invalid choice.");
		} while (choice < 1 || choice > 5);
		
		return choice;
	}
}
