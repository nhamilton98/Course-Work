import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WeightedGraph 
{
	private LinkedList<Integer>[] adjacent;
	private Set<Integer> vertices;
	private ArrayList<Edge> edges;
	private int numEdges;
	int[][] matrix;
	
	public WeightedGraph(int size)
	{
		this.adjacent = new LinkedList[size];
		this.vertices = new HashSet<Integer>();
		this.edges = new ArrayList<>();
		this.numEdges = 0;
		this.matrix = new int[size][size];
	}
	
	public void addEdge(int vertex1, int vertex2, int weight)
	{
		Edge edge = new Edge(vertex1, vertex2, weight);
		
		if (this.adjacent[edge.getV()] == null)
			this.adjacent[edge.getV()] = new LinkedList<Integer>();
		
		this.adjacent[edge.getV()].add(edge.getW());
		this.edges.add(edge);
		this.matrix[edge.getV()][edge.getW()] = edge.weight();
		
		this.numEdges++;
	}
	
	public void BFS()
	{
		Queue<Integer> queue = new LinkedList<Integer>();
		
		this.vertices.add(0);
		queue.add(0);
		
		while(!queue.isEmpty())
		{
			int dequeue = queue.remove();
			System.out.print(dequeue + "  ");
			
			for (int x = 0; x < this.adjacent[dequeue].size(); x++)
			{
				int add = this.adjacent[dequeue].get(x);
				
				if (!this.vertices.contains(add))
				{
					queue.add(add);
					this.vertices.add(add);
				}
			}
		}
	}
	
	public void matrix()
	{
		System.out.println("    0 1 2 3 4 5 6 7\n");
		for (int x = 0; x < adjacent.length; x++)
		{
			System.out.print(x + "   ");
			for (int y = 0; y < this.adjacent.length; y++)
				System.out.print(this.matrix[x][y] + " ");
			
			System.out.println();
		}
	}
}
