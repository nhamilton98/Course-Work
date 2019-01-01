package cscd211Methods;

import java.util.Scanner;

import cscd211Classes.BoxCar;

public class LinkedListMethods
{
	public static BoxCar createBoxCar(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		System.out.println("NODE DATA:");
		return new BoxCar(kin.nextLine());
	}
	
	public static BoxCar[] fillArray(final Scanner kin, int total)
	{
		if (kin == null)
			throw new NullPointerException("Invalid keyboard Scanner.");
		if (total < 1)
			throw new IllegalArgumentException("Invalid total.");
		
		BoxCar[] ret = new BoxCar[total];
		for (int i = 0; i < ret.length; i++)
		{
			System.out.println("DATA:");
			ret[i] = new BoxCar(kin.nextLine());
		}
		
		return ret;
	}
	
	public static int menu(final Scanner kin)
	{
		if (kin == null)
			throw new NullPointerException("Invalid keyboard Scanner.");
		
		int choice;
		do
		{
			System.out.println("LINKEDLIST MENU");
			System.out.println("---------------------------");
			System.out.println("1.) PRINT LIST");
			System.out.println("2.) ADD LAST");
			System.out.println("3.) ADD ALL");
			System.out.println("4.) GET AT INDEX");
			System.out.println("5.) GET LAST");
			System.out.println("6.) REMOVE");
			System.out.println("7.) REMOVE AT INDEX");
			System.out.println("8.) REMOVE LAST");
			System.out.println("9.) TO ARRAY");
			System.out.println("10.) CLEAR LIST");
			System.out.println("11.) PRINT LIST SIZE");
			System.out.println("\n12.) QUIT");
			System.out.println("---------------------------");
			System.out.println("CHOICE:");
			choice = Integer.parseInt(kin.nextLine());
			
			if (choice < 0 || choice > 12)
				System.out.println("Invalid choice.");
		} while (choice < 0 || choice > 12);
		
		return choice;
	}
	
	public static int readIndex(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		int index;
		do
		{
			System.out.println("INDEX:");
			index = Integer.parseInt(kin.nextLine());
			
			if (index < 0)
				System.out.println("Invalid index.");
		} while (index < 0);
		
		return index;
	}
}
