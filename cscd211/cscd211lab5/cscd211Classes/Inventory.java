package cscd211Classes;

import java.util.ArrayList;
import java.util.Collections;

import cscd211Comparators.InventoryPriceComparator;
import cscd211Comparators.InventorySKUComparator;

public class Inventory
{
	public static final int DEFAULT_ITEMS = 5;
	
	private ArrayList<InventoryItem> inventory;
	
	public Inventory() { this.inventory = new ArrayList<>(Inventory.DEFAULT_ITEMS); }
	
	public Inventory(final int total)
	{
		if (total < 1)
			throw new IllegalArgumentException("Invalid total.");
		
		this.inventory = new ArrayList<>(total);
	}
	
	public int size() { return this.inventory.size(); }
	
	public InventoryItem getInventoryItem(final int index)
	{
		if (index < 0 || index > this.inventory.size() - 1)
			throw new IllegalArgumentException("Invalid index.");
		
		return this.inventory.get(index);
	}
	
	public void addInventoryItem(final InventoryItem item)
	{
		if (item == null)
			throw new IllegalArgumentException("Invalid item.");
		
		ArrayList<InventoryItem> copy = new ArrayList<>(this.inventory.size());
		for (InventoryItem i: this.inventory)
			copy.add(new InventoryItem(i.getItem().getName(), i.getItem().getSKU(), i.getQuantity(), i.getPrice()));
		copy.add(item);
		
		this.inventory = copy;
	}
	
	public void sortInventory() { Collections.sort(this.inventory); }
	
	public void sortInventoryBySKU() { Collections.sort(this.inventory, new InventorySKUComparator()); }
	
	public void sortInventoryByPrice() { Collections.sort(this.inventory, new InventoryPriceComparator()); }
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		for (InventoryItem item: this.inventory)
			builder.append(item.toString());
		
		return builder.toString();
	}
}
