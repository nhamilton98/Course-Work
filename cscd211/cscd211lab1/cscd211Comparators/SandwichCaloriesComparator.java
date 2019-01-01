package cscd211Comparators;

import java.util.Comparator;

import cscd211Classes.Sandwich;

public class SandwichCaloriesComparator implements Comparator<Sandwich>
{
	@Override
	public int compare(final Sandwich s1, final Sandwich s2)
	{
		if (s1 == null || s2 == null)
			throw new IllegalArgumentException("Cannot compare to null.");
		
		if (Integer.valueOf(s1.getCalories()).compareTo(Integer.valueOf(s2.getCalories())) != 0)
			return Integer.valueOf(s1.getCalories()).compareTo(Integer.valueOf(s2.getCalories()));
		else
			return Integer.valueOf(s2.getCalories()).compareTo(Integer.valueOf(s1.getCalories()));
	}
}
