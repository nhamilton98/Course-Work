package treePackage;

public class Lights extends TreeDecorator
{
	public Lights(Tree tree) { this.tree = tree; }
	
	@Override
	public String getDescription() { return this.tree.getDescription() + ", lights"; }

	@Override
	public double cost() { return this.tree.cost() + 5.00; }
}
