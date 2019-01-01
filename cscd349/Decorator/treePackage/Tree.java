package treePackage;

public abstract class Tree
{
	protected String description = "";
	
	public String getDescription() { return this.description; }
	
	public abstract double cost();
}
