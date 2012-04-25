package graph;

public class Edge{
	private int weight;
	private VertexPair vertices; //NULL for sentinal node
	protected Edge next;
	protected Edge prev;
	protected Edge partner;
	
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
		return vertices;
	}
	
	public Edge partner(){
		return partner;
	}
	
	public removeSelf(){
		next.prev = prev;
		prev.next = next;
	}
}