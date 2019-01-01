package shapes;

public class Circle extends Shape
{
	private int radius;
	
	public Circle(int radius)
	{
		this.name = "Circle";
		this.radius = radius;
	}
	
	@Override
	public double area() { return Math.pow(this.radius, 2.0) * Math.PI; }
}
