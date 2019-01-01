import java.util.*;

import shapes.*;

public class ShapeTester
{
	public static void main(String[] args)
	{
		ShapeFactory factory = new ShapeFactory();
		ArrayList<Shape> shapes = new ArrayList<>();
		
		shapes.add(factory.createShape("circle", 2));
		shapes.add(factory.createShape("circle", 6));
		shapes.add(factory.createShape("square", 10));
		shapes.add(factory.createShape("rectangle", 2, 10));
		shapes.add(factory.createShape("triangle", 4, 6));
		shapes.add(factory.createShape("circle", 10));
		shapes.add(factory.createShape("rectangle", 7, 4));
		shapes.add(factory.createShape("square", 4));
		shapes.add(factory.createShape("square", 3));
		shapes.add(factory.createShape("triangle", 4, 4));
		shapes.add(factory.createShape("rectangle", 7, 8));
		shapes.add(factory.createShape("triangle", 8, 9));
		
		System.out.println("Before Sort:");
		printList(shapes);
		System.out.println("After Sort:");
		Collections.sort(shapes);
		printList(shapes);
	}
	
	public static void printList(ArrayList<Shape> list)
	{
		for (int x = 0; x < list.size(); x++)
			System.out.println(list.get(x));
		System.out.println();
	}
}
