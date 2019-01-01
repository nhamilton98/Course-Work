package cscd211Comparators;

import java.util.Comparator;

import cscd211Classes.InventoryItem;

public class InventoryPriceComparator implements Comparator<InventoryItem>
{
	@Override
	public int compare(InventoryItem i1, InventoryItem i2)
	{
		if (Double.valueOf(i1.getPrice()).compareTo(Double.valueOf(i2.getPrice())) != 0)
			return Double.valueOf(i1.getPrice()).compareTo(Double.valueOf(i2.getPrice()));
		else
			return Double.valueOf(i2.getPrice()).compareTo(Double.valueOf(i1.getPrice()));
	}
}
