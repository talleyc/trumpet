package graph;

public class Edge{
	private int weight;
	private VertexPair vertices; //NULL for sentinal node
	private Edge next;
	private Edge prev;
	private WUGraph graph;
	protected Edge partner;
	
	/**Makes a new edge, with a null partner reference.
	  * @param w the weight of this edge
	  * @param a one of the objects being connected
	  * @param b the other object being connected
	  * @param parentGraph the graph which contains this edge and these objects
	 */
	public Edge(int w, Object a, Object b, WUGraph parentGraph){
		if(!parentGraph.isEdge(a, b)){
			weight = w;
			vertices = new VertexPair(a, b);
			partner = null;
			graph = parentGraph;
		}
	}
	
	public int weight(){
		return weight;
	}
	
	public VertexPair vertices(){
		return vertices;
	}
	
	public Edge partner(){
		return partner;
	}
}