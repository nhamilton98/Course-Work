package cscd211Game.Classes;

import cscd211Enums.Suit;
import cscd211Enums.Value;

public class Card implements Comparable<Card>
{
	private Suit suit;
	private Value value;
	
	public Card(final Suit suit, final Value value)
	{
		this.suit = suit;
		this.value = value;
	}
	
	public Value getCardValue() { return this.value.getValue(); }
	
	public Suit getCardSuit() { return this.suit.getSuit(); }
	
	@Override
	public int compareTo(final Card anotherCard)
	{
		if (Integer.valueOf(this.suit.getSuitRank()).compareTo(anotherCard.suit.getSuitRank()) > 0)
			return 1;
		else if (Integer.valueOf(this.suit.getSuitRank()).compareTo(anotherCard.suit.getSuitRank()) < 0)
			return -1;
		else
		{
			if (Integer.valueOf(this.value.getValueRank()).compareTo(anotherCard.value.getValueRank()) > 0)
				return 1;
			else if (Integer.valueOf(this.value.getValueRank()).compareTo(anotherCard.value.getValueRank()) < 0)
				return -1;
			else
				return 0;
		}
	}
	
	@Override
	public String toString() { return this.value.getValueName() + " of " + this.suit.getSuitName(); }
}
