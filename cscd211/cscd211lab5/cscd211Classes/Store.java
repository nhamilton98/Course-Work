package cscd211Classes;

public class Store
{
	private String name;
	private Inventory inventory;
	
	public Store(final String name, final Inventory inventory)
	{
		if (name == null || name.equals(""))
			throw new IllegalArgumentException("Invalid name.");
		if (inventory == null)
			throw new IllegalArgumentException("Invalid inventory.");
		
		this.name = name;
		this.inventory = inventory;
	}
	
	public void sortInventory() { this.inventory.sortInventory(); }
	
	public void sortInventoryBySKU() { this.inventory.sortInventoryBySKU(); }
	
	public void sortInventoryByPrice() { this.inventory.sortInventoryByPrice(); }
	
	@Override
	public String toString() { return this.name + "\n" + this.inventory.toString(); }
}
