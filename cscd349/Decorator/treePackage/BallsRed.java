package treePackage;

public class BallsRed extends TreeDecorator
{
	public BallsRed(Tree tree) { this.tree = tree; }
	
	@Override
	public String getDescription() { return this.tree.getDescription() + ", red ball ornaments"; }

	@Override
	public double cost() { return this.tree.cost() + 1.00; }
}
