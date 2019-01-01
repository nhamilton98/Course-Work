package treePackage;

public class LEDs extends TreeDecorator
{
	public LEDs(Tree tree) { this.tree = tree; }
	
	@Override
	public String getDescription() { return this.tree.getDescription() + ", LEDs"; }	

	@Override
	public double cost() { return this.tree.cost() + 10.00; }
}
