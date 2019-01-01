package cscd211Lab14;

import java.util.Scanner;

public class Lab14Methods
{
	public static void doBoth(final Scanner fin)
	{
		if (fin == null)
			throw new IllegalArgumentException("Invalid file Scanner.");
		
		String line = fin.nextLine();
		if (fin.hasNext())
			doBoth(fin);
		System.out.println(reverseString(line));
	}
	
	public static int menu(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		int choice;
		do
		{
			System.out.println("REVERSE MENU");
			System.out.println("-------------------------");
			System.out.println("1.) REVERSE STRING");
			System.out.println("2.) REVERSE FILE CONTENTS");
			System.out.println("3.) DO BOTH");
			System.out.println("4.) QUIT");
			System.out.println("-------------------------");
			System.out.println("CHOICE:");
			choice = Integer.parseInt(kin.nextLine());
			
			if (choice < 1 || choice > 4)
				System.out.println("Invalid choice.");
		} while (choice < 1 || choice > 4);
		
		return choice;
	}
	
	public static String readString(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		String str;
		do
		{
			System.out.println("STRING:");
			str = kin.nextLine();
			
			if (str == null || str.equals(""))
				System.out.println("Invalid String.");
		} while (str == null || str.equals(""));
		
		return str;
	}
	
	public static void reverseFile(final Scanner fin)
	{
		if (fin == null)
			throw new IllegalArgumentException("Invalid file Scanner.");
		
		String line = fin.nextLine();
		if (fin.hasNext())
			reverseFile(fin);
		System.out.println(line);
	}
	
	public static String reverseString(final String str)
	{
		if (str == null)
			throw new IllegalArgumentException("Invalid String.");
		
		if (str.length() <= 1)
			return str;
		return reverseString(str.substring(1)) + str.charAt(0);
	}
}
