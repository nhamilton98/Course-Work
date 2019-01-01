
public class Edge implements Comparable<Edge>
{
	public int mV1, mV2;
	private final int mWeight;
	
	public Edge(int v1, int v2, int weight)
	{
		mV1 = v1;
		mV2 = v2;
		mWeight = weight;
	}
	
	public int getV1() { return this.mV1; }
	
	public int getV2() { return this.mV2; }
	
	public int weight() { return mWeight; }
	
	@Override
	public int compareTo(Edge edge)
	{
		if (mWeight < edge.weight())
			return -1;
		else if (mWeight > edge.weight())
			return 1;
		else
			return 0;
	}
}