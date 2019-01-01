package cscd211Comparators;

import java.util.Comparator;

import cscd211Classes.Vehicle;

public class ManufacturerComparator implements Comparator<Vehicle>
{
	@Override
	public int compare(final Vehicle v1, final Vehicle v2)
	{
		if (v1 == null || v2 == null)
			throw new IllegalArgumentException("Cannot compare to null.");
		
		if (v1.getManufacturer().compareTo(v2.getManufacturer()) != 0)
			return v1.getManufacturer().compareTo(v2.getManufacturer());
		else
			return v2.getManufacturer().compareTo(v1.getManufacturer());
	}
}
