package cscd211Classes;

public class InventoryItem implements Comparable<InventoryItem>
{
	private Item item;
	private double price;
	private int quantity;
	
	public InventoryItem(final String name, final int sku, final int quantity, final double price)
	{
		if (quantity < 1)
			throw new IllegalArgumentException("Invalid quantity.");
		if (price < 0.01)
			throw new IllegalArgumentException("Invalid price.");
		
		this.item = new Item(name, sku);
		this.price = price;
		this.quantity = quantity;
	}
	
	public Item getItem() { return new Item(this.item.getName(), this.item.getSKU()); }
	
	public double getPrice() { return this.price; }
	
	public int getQuantity() { return this.quantity; }
	
	@Override
	public int compareTo(InventoryItem anotherItem)
	{
		if (this.item.compareTo(anotherItem.item) > 0)
			return 1;
		else if (this.item.compareTo(anotherItem.item) < 0)
			return -1;
		else
		{
			if (Integer.valueOf(this.quantity).compareTo(Integer.valueOf(anotherItem.quantity)) > 0)
				return 1;
			else if (Integer.valueOf(this.quantity).compareTo(Integer.valueOf(anotherItem.quantity)) < 0)
				return -1;
			else
			{
				if (Double.valueOf(this.price).compareTo(Double.valueOf(anotherItem.quantity)) > 0)
					return 1;
				else if (Double.valueOf(this.price).compareTo(Double.valueOf(anotherItem.quantity)) < 0)
					return -1;
				else
					return 0;
			}
		}
	}
	
	@Override
	public String toString() { return "NAME: " + this.item.getName() + "\nSKU: " + this.item.getSKU() + "\nQuantity: " + this.quantity + "\nPrice: $" + String.format("%.2f", this.price) + "\n"; }
}
