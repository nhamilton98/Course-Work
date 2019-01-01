package cscd211Enums;

public enum Value
{
	ACE(1, "Ace"),
	TWO(2, "2"),
	THREE(3, "3"),
	FOUR(4, "4"),
	FIVE(5, "5"),
	SIX(6, "6"),
	SEVEN(7, "7"),
	EIGHT(8, "8"),
	NINE(9, "9"),
	TEN(10, "10"),
	JACK(11, "Jack"),
	QUEEN(12, "Queen"),
	KING(13, "King");
	
	private String name;
	private int rank;
	
	private Value(final int rank, final String name)
	{
		this.name = name;
		this.rank = rank;
	}
	
	public Value getValue() { return this; }
	
	public String getValueName() { return this.name; }
	
	public int getValueRank() { return this.rank; }
	
	@Override
	public String toString() { return this.name; }
}
