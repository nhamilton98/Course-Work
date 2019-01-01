package cscd211Methods;

import java.io.PrintStream;
import java.util.Scanner;

import cscd211Enums.Suit;
import cscd211Enums.Value;
import cscd211Game.Classes.Card;
import cscd211Game.Classes.Deck;
import cscd211Game.Players.Player;

public class Lab3Methods
{
	public static Card[] createCards()
	{
		Card[] cards = new Card[52];
		int cur = 0;
		for (Suit suit: Suit.values())
		{
			for (Value val: Value.values())
			{
				cards[cur] = new Card(suit, val);
				cur++;
			}
		}
		
		return cards;
	}
	
	public static Card[] createCards(final int total, final int suitsPer, final int... cardValues)
	{
		if (total < 1)
			throw new IllegalArgumentException("Invalid card total.");
		if (suitsPer < 1)
			throw new IllegalArgumentException("Invalid number of suits per deck.");
		if (cardValues == null)
			throw new IllegalArgumentException("Invalid card values");
		
		Card[] cards = new Card[total];
		int cur = 0;
		for (int i = 0; i < suitsPer; i++)
		{
			for (Suit suit: Suit.values())
			{
				for (int val: cardValues)
				{
					for (Value value: Value.values())
					{
						if (value.getValueRank() == val)
						{
							cards[cur] = new Card(suit, value);
							cur++;
						}
					}
				}
			}
		}
		
		return cards;
	}
	
	public static Player[] createPlayers(final int totalPlayers, final int totalCards, final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		if (totalPlayers < 1)
			throw new IllegalArgumentException("Must be at least one player.");
		if (totalCards < 1)
			throw new IllegalArgumentException("Invalid number of cards.");
		
		Player[] players = new Player[totalPlayers];
		String name;
		for (int i = 0; i < players.length; i++)
		{
			System.out.println("PLAYER " + (i + 1) + "'S NAME:");
			name = kin.nextLine();
			players[i] = new Player(name, totalCards);
		}
		
		return players;
	}
	
	public static boolean goAgain(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		String choice;
		do
		{
			System.out.println("GO AGAIN? (Yes/No)");
			choice = kin.nextLine();
			
			if (!choice.equalsIgnoreCase("yes") || !choice.equalsIgnoreCase("no"))
				System.out.println("Invalid choice.");
		} while (!choice.equalsIgnoreCase("yes") || !choice.equalsIgnoreCase("no"));
		
		if (choice.equalsIgnoreCase("yes"))
			return true;
		else
			return false;
	}
	
	public static void printDeck(final Deck deck, final PrintStream fout, final String message)
	{
		if (deck == null)
			throw new IllegalArgumentException("Invalid Deck.");
		if (fout == null)
			throw new IllegalArgumentException("Invalid file PrintStream.");
		if (message == null || message.equals(""))
			throw new IllegalArgumentException("Invalid message.");
		
		for (int i = 0; i < deck.getTotalCards(); i++)
			fout.println(deck.getNextCard());
	}
	
	public static int readCardsToDeal(final Scanner kin, int maxPlayerCards)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		if (maxPlayerCards < 1)
			throw new IllegalArgumentException("Invalid number of max cards per hand.");
		
		int num;
		do
		{
			System.out.println("NUMBER OF CARDS TO DEAL:");
			num = Integer.parseInt(kin.nextLine());
			
			if (num < 1)
				System.out.println("Invalid number of cards to deal.");
			if (num > maxPlayerCards)
				System.out.println("Invalid number of cards to deal. Must be smaller than max cards per hand.");
		} while (num < 1 || num > maxPlayerCards);
		
		return num;
	}
	
	public static int readMaxPlayerCards(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		int num;
		do
		{
			System.out.println("MAX CARDS PER HAND:");
			num = Integer.parseInt(kin.nextLine());
			
			if (num < 1)
				System.out.println("Invalid number of max cards per hand.");
		} while (num < 1);
		
		return num;
	}
	
	public static int readTimesToShuffle(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		int num;
		do
		{
			System.out.println("NUMBER OF SHUFFLES:");
			num = Integer.parseInt(kin.nextLine());
			
			if (num < 1)
				System.out.println("Invalid number of shuffles.");
		} while (num < 1);
		
		return num;
	}
	
	public static int readTotalPlayers(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		int num;
		do
		{
			System.out.println("NUMBER OF PLAYERS:");
			num = Integer.parseInt(kin.nextLine());
			
			if (num < 2)
				System.out.println("Must be at least 2 players.");
		} while (num < 2);
		
		return num;
	}
}
