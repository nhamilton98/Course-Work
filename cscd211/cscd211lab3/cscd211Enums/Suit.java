package cscd211Enums;

public enum Suit
{
	CLUBS(1, "Clubs"),
	DIAMONDS(2, "Diamonds"),
	HEARTS(3, "Hearts"),
	SPADES(4, "Spades");
	
	private String name;
	private int rank;
	
	private Suit(int rank, String name)
	{
		this.name = name;
		this.rank = rank;
	}
	
	public Suit getSuit() { return this; }
	
	public String getSuitName() { return this.name(); }
	
	public int getSuitRank() { return this.rank; }
	
	@Override
	public String toString() { return this.name; }
}
