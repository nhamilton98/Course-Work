package shapes;

public abstract class Shape implements Comparable<Shape>
{
	protected String name;
	
	protected abstract double area();
	
	public int compareTo(Shape shape)
	{
		if (this.name.compareTo(shape.getName()) == 0)
			return ((Double) this.area()).compareTo((Double) shape.area());
		return this.name.compareTo(shape.getName());
	}
	
	protected String getName() { return this.name; }
	
	@Override
	public String toString() { return "Type: " + this.name + "   Area: " + this.area(); }
}
