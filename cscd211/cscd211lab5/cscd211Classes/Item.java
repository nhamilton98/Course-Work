package cscd211Classes;

public class Item implements Comparable<Item>
{
	private String name;
	private int SKU;
	
	public Item(final String name, final int sku)
	{
		if (name == null || name.equals(""))
			throw new IllegalArgumentException("Invalid name.");
		if (sku < 0)
			throw new IllegalArgumentException("Invalid SKU.");
		
		this.name = name;
		this.SKU = sku;
	}
	
	public String getName() { return this.name; }
	
	public int getSKU() { return this.SKU; }
	
	@Override
	public int compareTo(Item anotherItem)
	{
		if (this.name.compareTo(anotherItem.name) > 0)
			return 1;
		else if (this.name.compareTo(anotherItem.name) < 0)
			return -1;
		else
		{
			if (Integer.valueOf(this.SKU).compareTo(Integer.valueOf(anotherItem.SKU)) > 0)
				return 1;
			else if (Integer.valueOf(this.SKU).compareTo(Integer.valueOf(anotherItem.SKU)) < 0)
				return -1;
			else
				return 0;
		}
	}
	
	@Override
	public String toString() { return this.name + " - " + this.SKU; }
}
