package shapes;

public class Triangle extends Shape
{
	private int base;
	private int height;
	
	public Triangle(int base, int height)
	{
		this.name = "Triangle";
		this.base = base;
		this.height = height;
	}
	
	public double area() { return (this.base * this.height) / 2; }
}
