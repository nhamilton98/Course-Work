package cscd211Lab5;

import java.io.File;
import java.util.Scanner;
import cscd211Classes.Store;
import cscd211Utils.FileUtils;
import cscd211Classes.Inventory;
import cscd211Methods.Lab5Methods;
/**
 * The purpose of this lab is to combine ArrayList with composition. <br><br>
 * The over view is the following <br>
 * A store contains a name and an inventory<br>
 * An inventory has a default items of 5 and an ArrayList of InventoryItem(s)<br>
 * InventoryItem(s) is an ArrayList of Item(s), a price and a quantity.<br>
 * Item(s) contains a name and a SKU<br>
 */
public class CSCD211Lab5
{
	/**
     * This constructor is NOT REQUIRED. It is only here because it appears
     * automatically when the JavaDoc is generated. If it were not here it would
     * still appear in the JavaDoc.
     */
    public CSCD211Lab5()
    {
        /* empty constructor */
    }// end DVC
    
    /**
     * The flow of the program is the following<br>
     * The program prompts for the name of the input file that contains the inventory for the store<br>
     * That Inventory is populated and returned as an Inventory object<br>
     * That returned Inventory object is passed to the Store Constructor and the store is opened<br>
     * <br>
     * The menu choices are:<br>
     * 1) Print the Store Inventory<br>
     * 2) Sort the inventory by using the Natural Order<br>
     * 3) Sort the inventory by the SKU using a comparator <br>
     * 4) Sort the inventory by the Price using a comparator<br>
     * 5) Quit<br>
     * @param args Representing the command line arguments -- we simply ignore this for now
     * @throws Exception If the Scanner object can't wrap the File object.
     */
	public static void main(String [] args) throws Exception
	{
		int choice;
		Scanner kb = new Scanner(System.in);
		File inf = FileUtils.openFile("input", kb);
		
		Scanner fin = new Scanner(inf);
		Inventory storeInventory = Lab5Methods.populateInventory(fin);
		fin.close();
		
		Store stusHappyPlace = new Store("Stus Class of Horrors", storeInventory);
		
		do
		{
			choice = Lab5Methods.menu(kb);
			
			switch(choice)
			{
				case 1: System.out.println(stusHappyPlace);
						break;
						
				case 2: stusHappyPlace.sortInventory();
						break;
						
				case 3:	stusHappyPlace.sortInventoryBySKU();
						break;
				
				case 4: stusHappyPlace.sortInventoryByPrice();
						break;
			
			}// end switch
			
		}while(choice != 5);
		
	}// end main

}// end class
