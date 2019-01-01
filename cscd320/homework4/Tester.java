// Nathan Hamilton
// CSCD 320-01
// HW4

package hw4;

import java.util.*;

public class Tester
{
	public static void main(String[] args)
	{
		Trie2 myTrie = new Trie2();
		
		myTrie.insertString("apple");
		myTrie.insertString("bike");
		myTrie.insertString("pen");
		myTrie.insertString("did");
		myTrie.insertString("ape");
		myTrie.insertString("child");
		myTrie.insertString("cat");
		myTrie.insertString("file");
		myTrie.insertString("hello");
		myTrie.insertString("he");
		myTrie.insertString("hell");
		
		LinkedList<String> ap = myTrie.wordsPrefixedBy("ap");
		LinkedList<String> he = myTrie.wordsPrefixedBy("he");
		
		System.out.println("Words in myTrie:");
		myTrie.printSorted();
		System.out.println();
		System.out.println("Words with prefix \"ap\":");
		printIterator(ap.listIterator(0));
		System.out.println("Words with prefix \"he\":");
		printIterator(he.listIterator(0));
	}
	
	public static void printIterator(Iterator<String> iterator)
	{
		while (iterator.hasNext())
			System.out.println(iterator.next());
		
		System.out.println();
	}
}
