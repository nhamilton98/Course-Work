// Nathan Hamilton
// CSCD 320 - 01
// Homework 3
// BinaryTree

package classes;

public class BinaryTree
{
	public class Node
	{
		public Node right;
		public Node left;
		public int data;
		
		public Node(int num) { this.data = num; }
	}
	
	private Node root;
	
	public BinaryTree() { this.root = null; }
	
	public Node getRoot() { return this.root; }
	
	public void setRoot(Node root) { this.root = root; }
	
	public void inOrderTraversal(Node node)
	{
		if (node == null)
			return;
		
		inOrderTraversal(node.left);
		System.out.print(node.data + " ");
		inOrderTraversal(node.right);
	}
	
	public void postOrderTraversal(Node node)
	{
		if (node == null)
			return;
		
		postOrderTraversal(node.left);
		postOrderTraversal(node.right);
		System.out.print(node.data + " ");
	}
}