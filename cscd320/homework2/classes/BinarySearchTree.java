// Nathan Hamilton
// CSCD 320-01
// HW2 - Option 1

package classes;

public class BinarySearchTree
{
	private class Node
	{
		public Node left;
		public Node right;
		public Comparable data;
		
		public Node()
		{
			this.left = null;
			this.right = null;
			this.data = null;
		}
		
		public Node(Comparable data)
		{
			if (data == null)
				throw new IllegalArgumentException("Invalid data");
			
			this.left = null;
			this.right = null;
			this.data = data;
		}
	}
	
	private Node root;
	
	public BinarySearchTree() { this.root = null; }
	
	public Node getRoot() { return this.root; }
	
	public void insert(Comparable data) { this.root = insert(this.root, data); }
	
	private Node insert(Node node, Comparable data)
	{
		if (node == null)
			node = new Node(data);
		else
		{
			if ((int) data < (int) node.data)
				node.left = insert(node.left, data);
			else
				node.right = insert(node.right, data);
		}
		
		return node;
	}
	
	public boolean delete(Integer data)
	{
		Node node = delete(this.root, data);
		
		if (node != null)
			return true;
		else
			return false;
	}
	
	private Node delete(Node root, Integer data)
	{
		if (root ==  null)
			return null;
		
		if (data.compareTo((Integer) root.data) < 0)
			root.left = delete(root.left, data);
		else
		{
			if (data.compareTo((Integer) root.data) > 0)
				root.right = delete(root.right, data);
			else if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;
			else
			{
				root.data = max(root);
				root.left = delete(root.left, (Integer) root.data);
				
				return root;
			}
		}
		
		return root;
	}
	
	public Integer max(Node root)
	{
		Node temp = root.left;
		
		while (temp.right != null)
			temp = temp.right;
		
		root.data = temp.data;
		temp = null;
		
		return (Integer) root.data;
	}
	
	public void inOrderTraversal()
	{
		System.out.print("{");
		inOrderTraversal(this.root);
		System.out.println("}");
	}
	
	private void inOrderTraversal(Node node)
	{
		if (node == null)
			return;
		
		inOrderTraversal(node.left);
		System.out.print(node.data + " ");
		inOrderTraversal(node.right);
	}
	
	public void postOrderTraversal()
	{
		System.out.print("{");
		postOrderTraversal(this.root);
		System.out.println("}");
	}
	
	private void postOrderTraversal(Node node)
	{
		if (node == null)
			return;
		
		postOrderTraversal(node.left);
		postOrderTraversal(node.right);
		System.out.print(node.data + " ");
	}
}