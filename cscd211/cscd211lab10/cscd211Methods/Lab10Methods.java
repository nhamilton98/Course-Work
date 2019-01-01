package cscd211Methods;

import java.util.ArrayList;
import java.util.Scanner;

import cscd211Inheritance.Players.BaseballPlayer;
import cscd211Inheritance.Players.FootballPlayer;
import cscd211Inheritance.Players.Player;
import cscd211Inheritance.Team.Team;

public class Lab10Methods
{
	public static void fill(final Scanner fin, final ArrayList<Team> teams) throws CloneNotSupportedException
	{
		String city, teamName, ssn, position, stat;
		String[] name;
		int salary;
		Player[] players;
		
		while (fin.hasNext())
		{
			city = fin.nextLine();
			teamName = fin.nextLine();
			
			players = new Player[Integer.parseInt(fin.nextLine())];
			for (int i = 0; i < players.length; i++)
			{
				name = fin.nextLine().split(" ");
				ssn = fin.nextLine();
				salary = Integer.parseInt(fin.nextLine());
				position = fin.nextLine();
				stat = fin.nextLine();
				
				if (stat.contains("."))
					players[i] = new BaseballPlayer(name[0], name[1], ssn, salary, position, Double.parseDouble(stat));
				else
					players[i] = new FootballPlayer(name[0], name[1], ssn, salary, position, Integer.parseInt(stat));
			}
			
			teams.add(new Team(city, teamName, players));
		}
	}
	
	public static int menu(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		int choice;
		do
		{
			System.out.println("TEAM MENU");
			System.out.println("------------------------");
			System.out.println("1.) PRINT TEAMS");
			System.out.println("2.) SORT PLAYERS");
			System.out.println("3.) SORT TEAM BY CITY");
			System.out.println("4.) SORT TEAM BY PAYROLL");
			System.out.println("\n5.) QUIT");
			System.out.println("------------------------");
			System.out.println("CHOICE:");
			choice = Integer.parseInt(kin.nextLine());
			
			if (choice < 0 || choice > 5)
				System.out.println("Invalid choice.");
		} while (choice < 0 || choice > 5);
		
		return choice;
	}
}
