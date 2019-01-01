package treePackage;

public class BallsSilver extends TreeDecorator
{
	public BallsSilver(Tree tree) { this.tree = tree; }
	
	@Override
	public String getDescription() { return this.tree.getDescription() + ", silver ball ornaments"; }

	@Override
	public double cost() { return this.tree.cost() + 3.00; }
}
