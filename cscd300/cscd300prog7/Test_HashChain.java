// Nathan Hamilton
// CSCD300-02
// Dr. Bojian Xu
// HashChain

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class Test_HashChain 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		HashChain hash = readFile(args[0]); // Filename, "int,String" per line
		
		Scanner kin = new Scanner(System.in);
		int choice, ID;
		String name;
		Student temp;
		Node cur;
		
		do
		{
			choice = menu(kin);
			
			switch(choice)
			{
				case 1:
					System.out.println("ENTER STUDENT ID:");
					ID = Integer.parseInt(kin.nextLine());
					System.out.println("ENTER STUDENT NAME:");
					name = kin.nextLine();
					hash.put(new Student(ID, name));
					break;
						
				case 2:
					System.out.println("ENTER STUDENT ID:");
					ID = Integer.parseInt(kin.nextLine());
					hash.remove(ID);
						
						break;
						
				case 3:
					System.out.println("ENTER STUDENT ID:");
					ID = Integer.parseInt(kin.nextLine());
					temp = hash.get(ID);
						
					if (temp == null)
						System.out.println("RECORD DOES NOT EXIST");
					else
						System.out.println(temp);
					
					break;
						
				case 4: for (int x = 0; x < hash.size(); x++)
						{
							cur = hash.table[x].head();
							
							while (cur != null)
							{
								cur.toString();
								cur = cur.next;
							}
							
							System.out.println();
						}
						break;
						
				case 5:
					break;
			}
		} while (choice != 5);
	}
	
	public static int menu(final Scanner kb)
	{
		int choice;
		
		do
		{
			System.out.println("HASHCHAIN MENU");
			System.out.println("------------------");
			System.out.println("1.) INSERT/UPDATE");
			System.out.println("2.) DELETE");
			System.out.println("3.) SEARCH");
			System.out.println("4.) PRINT RECORDS");
			System.out.println("5.) QUIT");
			System.out.println("------------------");
			System.out.println("CHOICE:");
			choice = kb.nextInt();
			System.out.println();
			
			if (choice < 1 || choice > 5)
			{
				System.out.println("Invalid choice.");
				System.out.println();
			}
			
		} while (choice < 1 || choice > 5);
		
		return choice;
	}
	
	public static HashChain readFile(String filename) throws FileNotFoundException
	{
		HashChain hash = new HashChain(5);
		
		Scanner fin = new Scanner(new File(filename));
		String[] input = new String[2];
		
		while (fin.hasNext())
		{
			input = fin.nextLine().split(",");
			
			Student temp = new Student(Integer.parseInt(input[0]), input[1]);
			
			hash.put(temp);
		}
		
		fin.close();
		
		return hash;
	}
}