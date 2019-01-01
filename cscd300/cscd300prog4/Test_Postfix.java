// Nathan Hamilton
// CSCD300-02
// Dr. Bojian Xu
// Stack

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test_Postfix 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		String filename = args[0]; // Filename - one [int,+,-,*,/] per line
		
		Stack stack = new Stack();
		System.out.println(postfix(filename, stack)); // Calculate postfix
	}
	
	public static int postfix(String filename, Stack stack) throws FileNotFoundException
	{
		File file = new File(filename);
		Scanner fin = new Scanner(file);
		
		final String PLUS = "+", MINUS = "-", MULT = "*", DIV = "/"; // Operator constants
		
		int num1, num2;
		String line;
		
		while (fin.hasNextLine())
		{
			line = fin.nextLine();
			if (line.equals(PLUS)) // Add
				stack.push(stack.pop() + stack.pop());
			else if (line.equals(MINUS)) // Subtract
			{
				num1 = stack.pop();
				num2 = stack.pop();
				stack.push(num2 - num1);
			}
			else if (line.equals(MULT)) // Multiply
				stack.push(stack.pop() * stack.pop());
			else if (line.equals(DIV)) // Divide
			{
				num1 = stack.pop();
				num2 = stack.pop();
				stack.push(num2 / num1);
			}
			else
				stack.push(Integer.parseInt(line)); // Add to Stack
		}
		
		fin.close();
		
		return stack.top();
	}
}