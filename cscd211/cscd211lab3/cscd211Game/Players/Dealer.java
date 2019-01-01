package cscd211Game.Players;

import cscd211Game.Classes.Deck;

public class Dealer
{
	private int cardsToDeal;
	private Deck deck;
	private Player[] players;
	
	public Dealer(final Player[] players, final Deck deck, final int cardsToDeal)
	{
		if (players == null)
			throw new IllegalArgumentException("Invalid Player array.");
		if (deck == null)
			throw new IllegalArgumentException("Invalid Deck.");
		if (cardsToDeal < 1)
			throw new IllegalArgumentException("Invalid number of dealable cards.");
		
		this.players = new Player[players.length];
		for (int i = 0; i < this.players.length; i++)
			this.players[i] = players[i];
		this.deck = deck;
		this.cardsToDeal = cardsToDeal;
	}
	
	public void deal()
	{
		for (int i = 0; i < this.cardsToDeal; i++)
		{
			for (int j = 0; j < this.players.length; j++)
				this.players[j].giveCard(this.deck.getNextCard());
		}
	}
	
	public void displayHand()
	{
		for (Player player: this.players)
		{
			System.out.println(player.getName() + "'s Hand:");
			for (int i = 0; i < player.getTotalCards(); i++)
				System.out.println(player.getCard(i));
			System.out.println("Total: " + player.getHandTotal() + "\n");
		}
	}
	
	public boolean cardsRemaining()
	{
		int dealt = 0;
		for (Player player: this.players)
			dealt += player.getHandTotal();
		
		if (this.deck.getTotalCards() - dealt > this.cardsToDeal)
			return true;
		else
			return false;
	}
	
	public void reset()
	{
		for (Player player: this.players)
			player.resetPlayerHand();
		this.deck.resetDeck();
	}
	
	public void determineWinner()
	{
		boolean tie = false;
		int high = 0;
		String winner = "";
		for (Player player: this.players)
		{
			if (player.getHandTotal() == high)
				tie = true;
			else if (player.getHandTotal() > high)
			{
				high = player.getHandTotal();
				tie = false;
				winner = player.getName();
			}
		}
		
		if (tie)
			System.out.println("Tie! No winner.");
		else
			System.out.println(winner + " wins with a hand total of " + high + "!");
	}
}
