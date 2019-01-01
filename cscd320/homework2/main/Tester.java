// Nathan Hamilton
// CSCD 320-01
// HW2 - Option 1

package main;

import classes.BinarySearchTree;

public class Tester 
{
	public static void main(String[] args)
	{
		BinarySearchTree BST = new BinarySearchTree();
		
		BST.insert(new Integer(8));
		BST.insert(new Integer(3));
		BST.insert(new Integer(6));
		BST.insert(new Integer(10));
		BST.insert(new Integer(14));
		BST.insert(new Integer(1));
		BST.insert(new Integer(4));
		BST.insert(new Integer(7));
		BST.insert(new Integer(13));
		
		BST.inOrderTraversal();
		BST.postOrderTraversal();
		BST.delete(8);
		BST.inOrderTraversal();
		BST.postOrderTraversal();
		BST.delete(10);
		BST.inOrderTraversal();
		BST.postOrderTraversal();
	}
}

// postorderTraversal still works because it traverses through the left subtree of the root node
// first, by depth, followed by the right subtree of the root node, by depth. So long as the
// new root with each recursive call being passed in is not null (which shouldn't happen if the
// code is written properly), it will always be able to backtrack properly and print the
// elements in postorder.