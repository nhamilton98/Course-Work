package shapes;

public class ShapeFactory
{
	private static final String SQUARE = "square";
	private static final String CIRCLE = "circle";
	private static final String RECTANGLE = "rectangle";
	private static final String TRIANGLE = "triangle";
	
	public Shape createShape(String name, int arg1)
	{
		if (name.equalsIgnoreCase(SQUARE))
			return new Square(arg1);
		else if (name.equalsIgnoreCase(CIRCLE))
			return new Circle(arg1);
		else
			return null;
	}
	
	public Shape createShape(String name, int arg1, int arg2)
	{
		if (name.equalsIgnoreCase(RECTANGLE))
			return new Rectangle(arg1, arg2);
		else if (name.equalsIgnoreCase(TRIANGLE))
			return new Triangle(arg1, arg2);
		else
			return null;
	}
}
