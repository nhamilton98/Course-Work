package cscd211Comparators;

import java.util.Comparator;

import cscd211Classes.Vehicle;

public class ModelComparator implements Comparator<Vehicle>
{
	@Override
	public int compare(Vehicle v1, Vehicle v2)
	{
		if (v1 == null || v2 == null)
			throw new IllegalArgumentException("Cannot compare to null.");
		
		if (v1.getModel().compareTo(v2.getModel()) != 0)
			return v1.getModel().compareTo(v2.getModel());
		else
			return v2.getModel().compareTo(v1.getModel());
	}
}
