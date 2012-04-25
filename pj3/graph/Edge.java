package graph;

public class Edge{
	private int weight;
	private VertexPair vertices; //NULL for sentinal node
	protected Edge next;
	protected Edge prev;
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
	public Edge(int w, Vertex a, Vertex b){
		if(!parentGraph.isEdge(a.getItem(), b.getItem())){
			weight = w;
			vertices = new VertexPair(a.getItem(), b.getItem());
			partner = null;
		}
	}
	
<<<<<<< HEAD
	public int getWeight(){
		return weight;
	}
	
	public VertexPair getVertices(){
=======
	public Edge(){
		vertices = null;
		next = null;
		prev = null;
		partner = null;
		weight = 0;
	}
	
	public int weight(){
		return weight;
	}
	
	public vertexPair vertices(){
>>>>>>> 0400c02ca1277a5134550bfc9d691d0ba0401aee
		return vertices;
	}
	
	public Edge getPartner(){
		return partner;
	}
	
	public removeSelf(){
		next.prev = prev;
		prev.next = next;
	}
}