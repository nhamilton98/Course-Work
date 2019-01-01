// Nathan Hamilton
// CSCD 320-01
// HW4
// Trie

package hw4;

import java.util.*;

public class Trie2
{	
	private class TrieNode
	{
		Map<Character, TrieNode> children = new TreeMap<>();
		boolean aword = false;
	}
	
	private TrieNode root;
	
	public Trie2() { this.root = new TrieNode(); }

	public void insertString(String s) { insertString(root, s); }
	
	private void insertString(TrieNode root, String s)
	{
		TrieNode cur = root;
		
		for (char ch: s.toCharArray())
		{
			TrieNode next = cur.children.get(ch);
			if (next == null)
				cur.children.put(ch, next = new TrieNode());
			cur = next;
		}
		
		cur.aword = true;
	}
	
	public void printSorted() { printSorted(root, ""); }

	private void printSorted(TrieNode node, String s)
	{
		if (node.aword)
			System.out.println(s);
		
		for (Character ch : node.children.keySet())
			printSorted(node.children.get(ch), s + ch);
	}
	
	public boolean findWord(String s) { return findWord(root, s); }
	
	private boolean findWord(TrieNode node, String s)
	{
		if(s != null)
		{
			String rest = s.substring(1);
			char ch = s.charAt(0);
			TrieNode child = node.children.get(ch);
			
			if(s.length() == 1 && child != null)
				return true;
			
			if(child == null)
				return false;
			else
				return findWord(child, rest);
		}
		
		return false;
	}
	
	public LinkedList<String> wordsPrefixedBy(String prefix) { return wordsPrefixedBy(this.root, prefix); }
	
	private LinkedList<String> wordsPrefixedBy(TrieNode node, String prefix)
	{
		char[] word = prefix.toCharArray();
		LinkedList<String> ret = new LinkedList<>();
		
		for (int x = 0; x < word.length; x++)
		{
			if (!node.children.containsKey(word[x]))
				return ret;
			
			node = node.children.get(word[x]);
		}
		
		String postfix = "";
		
		return builder(node, prefix, postfix, new LinkedList<String>());
	}
	
	private LinkedList<String> builder(TrieNode node, String prefix, String postfix, LinkedList<String> list)
	{
		if (node.aword)
			list.add(prefix + postfix);
		
		for (char ch: node.children.keySet())
		{
			builder(node.children.get(ch), prefix, postfix += ch, list);
			postfix = postfix.substring(0, postfix.length() - 1);
		}
		
		return list;
	}
}
