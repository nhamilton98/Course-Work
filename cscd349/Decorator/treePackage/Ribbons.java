package treePackage;

public class Ribbons extends TreeDecorator
{
	public Ribbons(Tree tree) { this.tree = tree; }
	
	@Override
	public String getDescription() { return this.tree.getDescription() + ", ribbons"; }

	@Override
	public double cost() { return this.tree.cost() + 2.00; }
}
