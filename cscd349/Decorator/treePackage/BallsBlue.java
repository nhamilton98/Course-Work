package treePackage;

public class BallsBlue extends TreeDecorator
{
	public BallsBlue(Tree tree) { this.tree = tree; }
	
	@Override
	public String getDescription() { return this.tree.getDescription() + ", blue ball ornaments"; }

	@Override
	public double cost() { return this.tree.cost() + 2.00; }
}
