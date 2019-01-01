package cscd211Game.Players;

import cscd211Game.Classes.Card;

public class Player
{
	private int count;
	private Card[] hand;
	private String name;
	
	public Player(final String name, final int totalCards)
	{
		if (name == null || name.equals(""))
			throw new IllegalArgumentException("Invalid name.");
		if (totalCards < 0)
			throw new IllegalArgumentException("Invalid hand size.");
		
		this.name = name;
		this.hand = new Card[totalCards];
		this.count = 0;
	}
	
	public String getName() { return this.name; }
	
	public void giveCard(final Card card)
	{
		if (card == null)
			throw new IllegalArgumentException("Card cannot be null.");
		
		this.hand[this.count] = card;
		this.count++;
	}
	
	public Card getCard(final int cardNum)
	{
		if (cardNum < 0 || cardNum > this.hand.length - 1)
			throw new IllegalArgumentException("Invalid card position.");
		
		return this.hand[cardNum];
	}
	
	public int getHandTotal()
	{
		int sum = 0;
		for (Card card: this.hand)
			sum += card.getCardValue().getValueRank();
		
		return sum;
	}
	
	public int getTotalCards() { return this.count; }
	
	public void resetPlayerHand()
	{
		for (int i = 0; i < this.hand.length; i++)
			this.hand[i] = null;
		this.count = 0;
	}
}
