package shapes;

public class Square extends Shape
{
	private int side;
	
	public Square(int side)
	{
		this.name = "Square";
		this.side = side;
	}
	
	public double area() { return Math.pow(this.side, 2.0); }
}
