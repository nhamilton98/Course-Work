package cscd211Inheritance.Team;

import java.util.ArrayList;

import cscd211Inheritance.Players.Player;
import cscd211Interfaces.Taxable;

public class Team implements Taxable, Comparable<Team>
{
	public static final int BASE_PAYROLL = 200000;
	
	protected String city;
	protected int payroll;
	protected ArrayList<Comparable> players;
	protected String teamName;
	
	public Team(final String city, final String teamName, final Player[] players) throws CloneNotSupportedException
	{
		if (city == null || city.equals(""))
			throw new IllegalArgumentException("Invalid city.");
		if (teamName == null || teamName.equals(""))
			throw new IllegalArgumentException("Invalid team name.");
		if (players == null)
			throw new IllegalArgumentException("Invalid list of players.");
		
		this.city = city;
		this.teamName = teamName;
		this.players = new ArrayList<>();
		
		for (Player player: players)
			this.players.add(player.clone());
		
		this.payroll = Team.BASE_PAYROLL + this.sumPayroll();
	}
	
	public String getTeamName() { return this.teamName; }
	
	public ArrayList<Comparable> getPlayers() { return this.players; }
	
	public String getCity() { return this.city; }
	
	public int getPayroll() { return this.payroll; }
	
	protected int sumPayroll()
	{
		int payroll = 0;
		for (Comparable comp: this.players)
		{
			Player player = (Player)comp;
			payroll += player.getSalary();
		}
		
		return payroll;
	}
	
	@Override
	public double calculateTaxes()
	{
		double taxes = 0;
		for (Comparable comp: this.players)
		{
			Player player = (Player)comp;
			if (player.getSalary() > 250000)
				taxes += player.getSalary() * Taxable.BASE_TAX_RATE;
			else
				taxes += player.getSalary() * (Taxable.BASE_TAX_RATE / 2);
		}
		
		return taxes;
	}
	
	@Override
	public int compareTo(Team anotherTeam)
	{
		if (this.city.compareTo(anotherTeam.city) != 0)
			return this.city.compareTo(anotherTeam.city);
		else
			return this.teamName.compareTo(anotherTeam.teamName);
	}
	
	@Override
	public String toString()
	{
		StringBuilder build = new StringBuilder();
		build.append(this.city + " " + this.teamName + "\nPAYROLL: $" + this.payroll + ".00\nTAXES: " + this.calculateTaxes() + "\nPLAYER INFO:\n");
		
		for (Comparable comp: this.players)
		{
			Player player = (Player)comp;
			build.append(player.toString());
		}
		
		return build.toString();
	}
}
