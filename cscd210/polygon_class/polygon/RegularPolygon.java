package polygon;

public class RegularPolygon implements Comparable<RegularPolygon>
{
	private int sides;
	private double sideLength;
	
	public RegularPolygon()
	{
		this.sides = 3;
		this.sideLength = 1;
	}
	
	public RegularPolygon(int sides)
	{
		if (sides < 3)
			throw new IllegalArgumentException("Invalid number of sides.");
		
		this.sides = sides;
		this.sideLength = 1;
	}
	
	public RegularPolygon(double sideLength)
	{
		if (sideLength <= 0)
			throw new IllegalArgumentException("Invalid side length.");
		
		this.sides = 3;
		this.sideLength = sideLength;
	}
	
	public RegularPolygon(int sides, double sideLength)
	{
		if (sides < 3)
			throw new IllegalArgumentException("Invalid number of sides.");
		if (sideLength <= 0)
			throw new IllegalArgumentException("Invalid side length.");
		
		this.sides = sides;
		this.sideLength = sideLength;
	}
	
	public int getSides() { return this.sides; }
	
	public void setSides(int sides)
	{
		if (sides < 3)
			throw new IllegalArgumentException("Invalid number of sides.");
		
		this.sides = sides;
	}
	
	public double getSideLength() { return this.getSideLength(); }
	
	public void setSideLength(double sideLength)
	{
		if (sideLength <= 0)
			throw new IllegalArgumentException("Invalid side length.");
		
		this.sideLength = sideLength;
	}
	
	public double getApothem() { return this.sideLength / (2 * Math.tan(Math.PI / this.sides)); }
	
	public double getArea() { return this.getApothem() * this.getPerimeter() / 2; }
	
	public double getPerimeter() { return this.sides * this.sideLength; }
	
	@Override
	public int compareTo(RegularPolygon compare)
	{
		if (this.sides > compare.getSides())
			return 1;
		else if (this.sides < compare.getSides())
			return -1;
		else
		{
			if (this.sideLength > compare.getSideLength())
				return 1;
			else if (this.sideLength < compare.getSideLength())
				return -1;
			else
				return 0;
		}
	}
	
	@Override
	public String toString() { return this.sides + " sides of length " + this.sideLength + "."; }
}
