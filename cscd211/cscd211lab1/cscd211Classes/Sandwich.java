package cscd211Classes;

public class Sandwich implements Comparable<Sandwich>
{
	private int calories;
	private String name;
	private String[] toppings;
	
	public Sandwich(final String name, final int calories, final String[] toppings)
	{
		if (name == null || name.equals(""))
			throw new IllegalArgumentException("Invalid name.");
		if (calories < 0)
			throw new IllegalArgumentException("Invalid calorie count.");
		if (toppings.length < 1)
			throw new IllegalArgumentException("Invalid list of toppings.");
		
		this.calories = calories;
		this.name = name;
		this.toppings = toppings;
	}
	
	public int getCalories() { return this.calories; }
	
	public String[] getToppings()
	{
		String[] clone = new String[this.toppings.length];
		for (int i = 0; i < clone.length; i++)
			clone[i] = this.toppings[i];
		
		return clone;
	}
	
	@Override
	public int compareTo(final Sandwich anotherSandwich)
	{
		if (anotherSandwich == null)
			throw new IllegalArgumentException("Cannot compare to null.");
		
		if (this.name.compareTo(anotherSandwich.name) > 0)
			return 1;
		else if (this.name.compareTo(anotherSandwich.name) < 0)
			return -1;
		else
		{
			if (this.calories > anotherSandwich.calories)
				return 1;
			else if (this.calories > anotherSandwich.calories)
				return -1;
			else
				return 0;
		}
	}
	
	@Override
	public boolean equals(final Object obj)
	{
		if (this.compareTo((Sandwich) obj) == 0)
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(this.name + " ");
		for (int i = 0; i < this.toppings.length; i++)
		{
			builder.append(this.toppings[i]);
			if (i != this.toppings.length - 1)
				builder.append(" ");
		}
		
		return builder.toString().hashCode();
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("Your " + this.name + " sandwich contains ");
		for (String str: this.toppings)
			builder.append(str + ", ");
		builder.append("and is " + this.calories + " calorie(s).");
		
		return builder.toString();
	}
}
