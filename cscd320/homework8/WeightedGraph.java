import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

public class WeightedGraph
{
	private Set<Edge>[] mAdjacencyList;
	private int mSize;
	
	@SuppressWarnings("unchecked")
	public WeightedGraph(int size)
	{
		mSize = size;
		mAdjacencyList = (Set<Edge>[]) new Set[mSize];
		for (int x = 0; x < mSize; x++)
			mAdjacencyList[x] = new HashSet<Edge>();
	}
	
	public void addEdge(int v1, int v2, int weight)
	{
		Edge edge = new Edge(v1, v2, weight);
		mAdjacencyList[edge.getV1()].add(edge);
	}
	
	public void shortestPath(int start)
	{
		boolean[] visited = new boolean[mSize];
		String[] path = new String[mSize];
		for (int x = 0; x < mSize; x++)
			path[x] = "";
		path[start] += start + "  ";
		int[] distance = new int[mSize];
		for (int x = 0; x < mSize; x++)
			distance[x] = Integer.MAX_VALUE;
		distance[start] = 0;
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		Iterator<Edge> iterator = mAdjacencyList[start].iterator();
		while (iterator.hasNext())
			queue.add(iterator.next());
		
		while (!queue.isEmpty())
		{
			Edge minEdge = queue.poll();
			int nextVertex = minEdge.getV2();
			
			distance[nextVertex] = minEdge.weight();
			path[nextVertex] += nextVertex + "  ";
			visited[nextVertex] = true;
			
			iterator = mAdjacencyList[nextVertex].iterator();
			while(iterator.hasNext())
			{
				Edge edge = iterator.next();
				
				distance[edge.getV2()] = edge.weight();
				if (edge.weight() < distance[edge.getV2()] + edge.weight())
				{
					distance[edge.getV2()] = distance[edge.getV1()] + edge.weight();
					path[edge.getV2()] += edge.getV1() + "  ";
					visited[edge.getV2()] = true;
				}
			}
		}
		
		for (int x = 1; x < mSize; x++)
			System.out.println("Path " + x + ") Vertex " + start + " to Vertex " + x + ", " + start + "  " + path[x] + "with a distance of " + distance[x]);
	}
}