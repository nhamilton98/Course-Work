package cscd211Comparators;

import java.util.Comparator;

import cscd211Classes.InventoryItem;

public class InventorySKUComparator implements Comparator<InventoryItem>
{
	@Override
	public int compare(InventoryItem i1, InventoryItem i2)
	{
		if (Integer.valueOf(i1.getItem().getSKU()).compareTo(Integer.valueOf(i2.getItem().getSKU())) != 0)
			return Integer.valueOf(i1.getItem().getSKU()).compareTo(Integer.valueOf(i2.getItem().getSKU()));
		else
			return Integer.valueOf(i2.getItem().getSKU()).compareTo(Integer.valueOf(i1.getItem().getSKU()));
	}
}
