// Nathan Hamilton
// CSCD300-02
// Dr. Bojian Xu
// BinarySearchTree

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test_BST
{
	public static void main(String[] args) throws FileNotFoundException
	{
		BinarySearchTree tree = readFile(args[0]); // Filename - one int per line
		
		Scanner kin = new Scanner(System.in);
		int choice;
		int num;
		BSTNode temp;
		
		do
		{
			choice = menu(kin);
			
			if (choice == 1)
			{
				System.out.print("ENTER KEY:");
				num = Integer.parseInt(kin.nextLine());
				System.out.println("\n");
				
				temp = tree.search(num);
				
				if (temp == null)
					System.out.println("Key does not exist.");
				else
					System.out.println("Key exists.");
			}
			else if (choice == 2)
			{
				System.out.print("Enter key: ");
				num = Integer.parseInt(kin.nextLine());
				System.out.println("\n");
				
				temp = tree.insert(num);
				
				if (temp == null)
					System.out.println("Key already exists.");
				else
					System.out.println("Key inserted.");
			}
			else if (choice == 3)
			{
				System.out.print("ENTER KEY:");
				num = Integer.parseInt(kin.nextLine());
				System.out.println("\n");
				
				temp = tree.delete(num);
				
				if (temp == null)
					System.out.println("Key does not exist.");
				else
					System.out.println("Key deleted.");
			}
			else if (choice == 4)
				tree.inOrderTraversal(tree.root);
			else if (choice == 5)
				tree.preOrderTraversal(tree.root);
			else if (choice == 6)
				tree.postOrderTraversal(tree.root);
			else if (choice == 7)
				tree.levelOrderTraversal(tree.root);
			else if (choice == 8)
			{
				temp = tree.min(tree.root);
				
				if (temp == null)
					System.out.println("Empty BST.");
				else
					System.out.println(temp.key);
			}
			else if (choice == 9)
			{
				temp = tree.max(tree.root);
				
				if (temp == null)
					System.out.println("Empty BST.");
				else
					System.out.println(temp.key);
			}
			else if (choice == 10)
			{
				System.out.print("ENTER KEY:");
				num = Integer.parseInt(kin.nextLine());
				
				temp = tree.search(num);
				
				if (temp == null)
					System.out.println("Key does not exist.");
				else
				{
					temp = tree.successor(temp);
					
					if (temp == null)
						System.out.println("Successor does not exist.");
					else
						System.out.println(temp.key);
				}
			}
			else if (choice == 11)
			{
				System.out.print("Enter key: ");
				num = Integer.parseInt(kin.nextLine());
				
				temp = tree.search(num);
				
				if (temp == null)
					System.out.println("Key does not exist.");
				else
				{
					temp = tree.predecessor(temp);
					
					if (temp == null)
						System.out.println("Predecessor does not exist.");
					else
						System.out.println(temp.key);
				}
			}
			if (choice == 12)
				System.out.println("Exiting.");
			
		} while (choice != 12);
	}
	
	public static int menu(final Scanner kin)
	{
		int choice;
		
		do
		{
			System.out.println("BINARYSEARCHTREE MENU");
			System.out.println("=====================================");
			System.out.println("1.) SEARCH");
			System.out.println("2.) INSERT");
			System.out.println("3.) DELETE");
			System.out.println("4.) IN-ORDER TRAVERSAL");
			System.out.println("5.) PRE-ORDER TRAVERSAL");
			System.out.println("6.) POST-ORDER TRAVERSAL");
			System.out.println("7.) LEVEL-ORDER TRAVERSAL");
			System.out.println("8.) FIND SMALLEST");
			System.out.println("9.) FIND LARGEST");
			System.out.println("10.) FIND SUCCESSOR");
			System.out.println("11.) FIND PREDECESSOR");
			System.out.println("12.) QUIT");
			
			System.out.println("CHOICE:");
			choice = Integer.parseInt(kin.nextLine());
			
			if (choice < 1 || choice > 12)
				System.out.println("Invalid choice.");
			} while (choice < 1 || choice > 12);
		
		return choice;
	}
	
	public static BinarySearchTree readFile(String filename) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File(filename));
		
		BinarySearchTree temp = new BinarySearchTree();
		
		while (fin.hasNext())
			temp.insert(Integer.parseInt(fin.nextLine()));
		
		fin.close();
		
		return temp;
	}
}