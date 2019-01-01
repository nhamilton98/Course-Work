package shapes;

public class Rectangle extends Shape
{
	private int height;
	private int width;
	
	public Rectangle(int height, int width)
	{
		this.name = "Rectangle";
		this.height = height;
		this.width = width;
	}
	
	@Override
	public double area() { return this.height * this.width; }
}
