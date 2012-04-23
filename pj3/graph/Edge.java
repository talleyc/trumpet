package graph;

public class Edge{
	private int weight;
	private VertexPair vertices; //NULL for sentinal node
	private Edge next;
	private Edge prev;
	private WUGraph graph;
	protected Edge partner;
	
	// Chris! You need to make a no argument constructor for the sentinal. Also, my code for 
	// getNeighbors requires that your vertices (VertexPair) has the first vertex as itself and 
	// the second vertex as the partner. That way, I can always access the neighboring vertices
	// by calling VertexPair.object2.
	
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
	
	public int getWeight(){
		return weight;
	}
	
	public VertexPair getVertices(){
		return vertices;
	}
	
	public Edge getPartner(){
		return partner;
	}
}