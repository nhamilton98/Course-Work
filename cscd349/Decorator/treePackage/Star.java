package treePackage;

public class Star extends TreeDecorator
{
	private static boolean starPresent;
	
	public Star(Tree tree) { this.tree = tree; }
	
	public static Tree addStar(Tree tree)
	{
		if (tree.getDescription().contains("star"))
			System.out.println("The tree already has a star.");
		else
			tree = new Star(tree);
		
		return tree;
	}
	
	@Override
	public String getDescription()
	{
		if (starPresent)
			return tree.getDescription();
		
		return this.tree.getDescription() + ", a star";
	}

	@Override
	public double cost()
	{
		if (starPresent)
			return this.tree.cost();
		
		return this.tree.cost() + 4.00;
	}
}
