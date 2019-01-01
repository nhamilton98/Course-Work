package treePackage;

public class Ruffles extends TreeDecorator
{
	public Ruffles(Tree tree) { this.tree = tree; }
	
	@Override
	public String getDescription() { return this.tree.getDescription() + ", ruffles"; }

	@Override
	public double cost() { return this.tree.cost() + 1.00; }
}
