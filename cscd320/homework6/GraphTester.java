public class GraphTester
{
	public static void main(String[] args)
	{
		WeightedGraph graph = new WeightedGraph(8);
		
		graph.addEdge(0, 1, 4);
		graph.addEdge(0, 2, 6);
		graph.addEdge(0, 3, 16);
		graph.addEdge(1, 0, 4);
		graph.addEdge(1, 7, 24);
		graph.addEdge(2, 0, 6);
		graph.addEdge(2, 3, 8);
		graph.addEdge(2, 5, 5);
		graph.addEdge(2, 7, 23);
		graph.addEdge(3, 0, 16);
		graph.addEdge(3, 2, 8);
		graph.addEdge(3, 4, 21);
		graph.addEdge(3, 5, 10);
		graph.addEdge(4, 3, 21);
		graph.addEdge(4, 5, 14);
		graph.addEdge(4, 6, 7);
		graph.addEdge(5, 2, 5);
		graph.addEdge(5, 3, 10);
		graph.addEdge(5, 4, 14);
		graph.addEdge(5, 6, 11);
		graph.addEdge(5, 7, 18);
		graph.addEdge(6, 4, 7);
		graph.addEdge(6, 5, 11);
		graph.addEdge(6, 7, 9);
		graph.addEdge(7, 1, 24);
		graph.addEdge(7, 2, 23);
		graph.addEdge(7, 5, 18);
		graph.addEdge(7, 6, 9);

		graph.matrix();
	}
}