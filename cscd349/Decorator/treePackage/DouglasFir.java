package treePackage;

public class DouglasFir extends Tree 
{
	public DouglasFir() { this.description = "Douglas Fir tree"; }
	
	@Override
	public double cost() { return 30.00; }
}
