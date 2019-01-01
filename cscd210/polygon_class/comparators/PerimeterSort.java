package comparators;

import java.util.Comparator;

import polygon.RegularPolygon;

public class PerimeterSort implements Comparator<RegularPolygon>
{
	@Override
	public int compare(RegularPolygon rp1, RegularPolygon rp2)
	{
		if (Double.valueOf(rp1.getPerimeter()).compareTo(Double.valueOf(rp2.getPerimeter())) != 0)
			return Double.valueOf(rp1.getPerimeter()).compareTo(Double.valueOf(rp2.getPerimeter()));
		else
			return Double.valueOf(rp2.getPerimeter()).compareTo(Double.valueOf(rp1.getPerimeter()));
	}
}
