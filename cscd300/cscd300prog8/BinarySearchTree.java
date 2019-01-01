public class BinarySearchTree
{
	public BSTNode root;
	
	public BinarySearchTree() { this.root = null; }
	
	public BSTNode search(int key)
	{
		BSTNode temp = this.root;
		
		while (temp != null && key != temp.key)
		{
			if (key < temp.key)
				temp = temp.left;
			else
				temp = temp.right;
		}
		
		return temp;
	}
	
	public BSTNode insert(int key)
	{
		BSTNode cur = this.root, prev = null;
		BSTNode newNode = new BSTNode(key);
		
		while (cur != null)
		{
			prev = cur;
			
			if (key == cur.key)
				return null;
			else if (key < cur.key)
				cur = cur.left;
			else
				cur = cur.right;
		}	
			
		newNode.parent = prev;
		
		if (prev == null)
			this.root = newNode;
		else if (key < prev.key)
			prev.left = newNode;
		else
			prev.right = newNode;
		
		return newNode;
	}
	
	public BSTNode delete(int key)
	{
		BSTNode node = this.search(key);
		
		if (node != null)
			this.delete(node);
		
		return node;
	}
	
	public void delete(BSTNode node)
	{
		if (node.left == null && node.right == null)
			this.transplant(node, null);
		else if (node.left == null)
			this.transplant(node,  node.right);
		else if (node.right == null)
			this.transplant(node, node.left);
		else
		{
			BSTNode temp = this.min(node.right);
			
			if (temp.parent != node)
			{
				this.transplant(temp, temp.right);
				temp.right = node.right;
				temp.right.parent = temp;
			}
			
			this.transplant(node, temp);
			temp.left = node.left;
			temp.left.parent = temp;
		}
	}
	
	public void transplant(BSTNode oldRoot, BSTNode newRoot)
	{
		if (oldRoot.parent == null)
			this.root = newRoot;
		else if (oldRoot == oldRoot.parent.left)
			oldRoot.parent.left = newRoot;
		else
			oldRoot.parent.right = newRoot;
		
		if (newRoot != null)
			newRoot.parent = oldRoot.parent;
	}
	
	public BSTNode min( BSTNode start)
	{
		if (start == null)
			return null;
		
		BSTNode temp = start;
		
		while (temp.left != null)
			temp = temp.left;
		
		return temp;
	}
	
	public BSTNode max(BSTNode start)
	{
		if (start == null)
			return null;
		
		BSTNode temp = start;
		
		while (temp.right != null)
			temp = temp.right;
		
		return temp;
	}
	
	public BSTNode successor(BSTNode node)
	{
		if (node == null)
			return null;
		
		BSTNode cur = node;
		
		if (cur.right != null)
			return min(cur.right);
		
		BSTNode prev = cur.parent;
		
		while(prev != null && cur == prev.right)
		{
			cur = prev;
			prev = prev.parent;
		}
		
		return prev;
	}
	
	public BSTNode predecessor(BSTNode node)
	{
		
		if (node == null)
			return null;
		
		BSTNode cur = node;
		
		if (cur.left != null)
			return max(cur.left);
		
		BSTNode prev = cur.parent;
		
		while (prev != null && cur == prev.left)
		{
			cur = prev;
			prev = prev.parent;
		}
		
		return prev;
	}
	
	public void inOrderTraversal(BSTNode start)
	{
		if (start != null)
		{
			inOrderTraversal(start.left);
			System.out.println(start.key);
			inOrderTraversal(start.right);
		}
	}
	
	public void preOrderTraversal(BSTNode start)
	{
		if (start != null)
		{
			System.out.println(start.key);
			preOrderTraversal(start.left);
			preOrderTraversal(start.right);
		}
	}
	
	public void postOrderTraversal(BSTNode start)
	{
		if (start != null)
		{
			preOrderTraversal(start.left);
			preOrderTraversal(start.right);
			System.out.println(start.key);
		}
	}
	
	public void levelOrderTraversal(BSTNode start)
	{
		ListQueue queue = new ListQueue();
		
		queue.enqueue(start);
		
		while (queue.size() > 0)
		{
			BSTNode node = queue.dequeue();
			
			System.out.println(node.key);
			
			if (node.left != null)
				queue.enqueue(node.left);
			if (node.right != null)
				queue.enqueue(node.right);
		}
	}
}
