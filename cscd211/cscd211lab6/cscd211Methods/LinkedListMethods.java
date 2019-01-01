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
	
	public static int menu(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		int choice;
		do
		{
			System.out.println("LINKEDLIST MENU");
			System.out.println("---------------------------");
			System.out.println("1.) PRINT LIST");
			System.out.println("2.) ADD FIRST");
			System.out.println("3.) ADD LAST");
			System.out.println("4.) ADD AT INDEX");
			System.out.println("5.) CONTAINS DATA");
			System.out.println("6.) INDEX OF DATA");
			System.out.println("7.) REMOVE FIRST");
			System.out.println("8.) REMOVE LAST");
			System.out.println("9.) REMOVE AT INDEX");
			System.out.println("10.) REMOVE FIRST OCCURENCE");
			System.out.println("11.) REMOVE LAST OCCURENCE");
			System.out.println("12.) CLEAR LIST");
			System.out.println("13.) PRINT LIST SIZE");
			System.out.println("\n14.) QUIT");
			System.out.println("---------------------------");
			System.out.println("CHOICE:");
			choice = Integer.parseInt(kin.nextLine());
			
			if (choice < 0 || choice > 14)
				System.out.println("Invalid choice.");
		} while (choice < 0 || choice > 14);
		
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
