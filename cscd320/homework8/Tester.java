
public class Tester
{
	public static void main(String[] args)
	{
		System.out.println("Extra Credit: I used a PriorityQueue to represent Q in my solution.\n");
		
		WeightedGraph graph = new WeightedGraph(9);
		graph.addEdge(1, 2, 9);
		graph.addEdge(1, 6, 14);
		graph.addEdge(1, 7, 15);
		graph.addEdge(2, 3, 24);
		graph.addEdge(3, 5, 2);
		graph.addEdge(3, 8, 19);
		graph.addEdge(4, 3, 6);
		graph.addEdge(4, 8, 6);
		graph.addEdge(5, 4, 11);
		graph.addEdge(5, 8, 16);
		graph.addEdge(6, 3, 18);
		graph.addEdge(6, 5, 30);
		graph.addEdge(6, 7, 5);
		graph.addEdge(7, 5, 20);
		graph.addEdge(7, 8, 44);
		
		System.out.println("Dijkstra's Algorithm:");
		graph.shortestPath(1);
	}
}
