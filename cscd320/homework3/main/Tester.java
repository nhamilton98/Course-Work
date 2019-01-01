// Nathan Hamilton
// CSCD 320 - 01
// HW3

package main;

import classes.BinaryTree;
import classes.BinaryTree.Node;

public class Tester
{
	public static void main(String[] args)
	{
		Object[] inOrderSequence = {9, 5, 1, 7, 2, 12, 8, 4, 3, 11};
		Object[] postOrderSequence = {9, 1, 2, 12, 7, 5, 3, 11, 4, 8};
		
		System.out.println("Given sequences:");
		printArray(inOrderSequence);
		printArray(postOrderSequence);
		
		BinaryTree tree = buildTree(inOrderSequence, postOrderSequence);
		
		System.out.println("\nTree sequences:");
		tree.inOrderTraversal(tree.getRoot());
		System.out.println();
		tree.postOrderTraversal(tree.getRoot());
	}
	
	public static BinaryTree buildTree(Object inOrderSequence[], Object postOrderSequence[])
	{
		int iStart = 0;
		int iEnd = inOrderSequence.length - 1;
		int pStart = 0;
		int pEnd = postOrderSequence.length - 1;
		
		BinaryTree ret = new BinaryTree();
		ret.setRoot(buildTree(inOrderSequence, iStart, iEnd, postOrderSequence, pStart, pEnd));
		
		return ret;
	}
	
	private static Node buildTree(Object inOrderSequence[], int iStart, int iEnd, Object postOrderSequence[], int pStart, int pEnd)
	{
		if (iStart > iEnd || pStart > pEnd)
			return null;
		
		BinaryTree temp = new BinaryTree();
		int rootVal = (int) postOrderSequence[pEnd];
		Node root = temp.new Node(rootVal);
		
		int x = 0;
		for (int i = 0; i < inOrderSequence.length; i++)
		{
			if ((int) inOrderSequence[i] == rootVal)
			{
				x = i;
				break;
			}
		}
		
		root.left = buildTree(inOrderSequence, iStart, x - 1, postOrderSequence, pStart, pStart + x - (iStart + 1));
		root.right = buildTree(inOrderSequence, x + 1, iEnd, postOrderSequence, pStart + x - iStart, pEnd - 1);
		
		return root;
	}

	public static void printArray(Object array[])
	{
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
		
		System.out.println();
	}
}