
public class Edge
{
	public int v, w;
	private final int weight;
	
	public Edge(int v, int w, int weight)
	{
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int getV() { return this.v; }
	
	public int getW() { return this.w; }
	
	public int weight() { return this.weight; }
}
