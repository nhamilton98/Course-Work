package cscd211Game.Classes;

import java.util.Random;

import cscd211Utils.ArrayUtils;

public class Deck
{
	private Card[] cards;
	private int count;
	
	public Deck(final Card[] cards)
	{
		if (cards == null)
			throw new IllegalArgumentException("Invalid Card array.");
		
		this.cards = new Card[cards.length];
		for (int i = 0; i < cards.length; i++)
			this.cards[i] = new Card(cards[i].getCardSuit(), cards[i].getCardValue());
		this.count = 0;
	}
	
	public void shuffle(int times)
	{
		if (times < 1)
			throw new IllegalArgumentException("Must shuffle at least 1 time.");
		
		Random rand = new Random();
		int randPos;
		for (int i = 0; i < times; i++)
		{
			for (int j = 0; j < cards.length; j++)
			{
				randPos = rand.nextInt(this.cards.length);
				Card temp = this.cards[j];
				this.cards[j] = this.cards[randPos];
				this.cards[randPos] = temp;
			}
		}
	}
	
	public void resetDeck()
	{
		ArrayUtils.selectionSort(this.cards);
		this.count = 0;
	}
	
	public Card getNextCard()
	{
		Card card = this.cards[this.count];
		this.count++;
		return card;
	}
	
	public int getTotalCards() { return this.cards.length; }
	
	public int getCount() { return this.count; }
}
