package cscd211Methods;

import java.util.Scanner;

import cscd211Classes.Inventory;
import cscd211Classes.InventoryItem;

public class Lab5Methods
{
	public static int menu(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		int choice;
		do
		{
			System.out.println("STORE MENU");
			System.out.println("-------------------------");
			System.out.println("1.) PRINT STORE INVENTORY");
			System.out.println("2.) SORT BY NATURAL ORDER");
			System.out.println("3.) SORT BY SKU");
			System.out.println("4.) SORT BY PRICE");
			System.out.println("\n5.) QUIT");
			System.out.println("-------------------------");
			System.out.println("CHOICE:");
			choice = Integer.parseInt(kin.nextLine());
			
			if (choice < 1 || choice > 5)
				System.out.println("Invalid choice.");
		} while (choice < 1 || choice > 5);
		
		return choice;
	}
	
	public static Inventory populateInventory(final Scanner fin)
	{
		if (fin == null)
			throw new IllegalArgumentException("Invalid file Scanner.");
		
		Inventory inventory = new Inventory(Integer.parseInt(fin.nextLine()));
		String name;
		int sku, quantity;
		double price;
		while (fin.hasNext())
		{
			name = fin.nextLine();
			sku = Integer.parseInt(fin.nextLine());
			quantity = Integer.parseInt(fin.nextLine());
			price = Double.parseDouble(fin.nextLine());
			inventory.addInventoryItem(new InventoryItem(name, sku, quantity, price));
		}
		
		return inventory;
	}
}
