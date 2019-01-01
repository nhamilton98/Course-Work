public class BSTNode
{
	public int key;
	public BSTNode parent;
	public BSTNode right;
	public BSTNode left;
	
	public BSTNode(int key)
	{
		this.key = key;
		this.parent = null;
		this.right = null;
		this.left = null;
	}
}