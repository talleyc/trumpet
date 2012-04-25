package graph;

/**
*  Similar to a Vertex, an Edge is a node in a doubly-linked list
*  with a sentinal node. The sentinal node is kept by the Vertex object
*/

public class Edge{
	protected int weight;
	protected VertexPair vertices;
	protected Edge next;
	protected Edge prev;
	protected Edge partner;
	

	
	/**Makes a new Edge, with a null partner reference.
	  * @param w the weight of this edge
	  * @param a one of the objects being connected
	  * @param b the other object being connected
	  * @param parentGraph the graph which contains this edge and these objects
	 */
	public Edge(int w, Object a, Object b){
		weight = w;
		vertices = new VertexPair(a, b);
		partner = null;
		next = this;
		prev = this;
	}

	/**Makes a new Edge, with all pointers being null.
	 * If unchanged, this Edge will serve as a sentinal node
	 * in an Edge list
	 */
	public Edge(){
		vertices = null;
		next = this;
		prev = this;
		partner = null;
		weight = 0;
	}
	
	/**
	*  @return the weight of this Edge
	*/
	public int weight(){
		return weight;
	}
	
	/**
	*  Inserts another Edge object immediately before this Edge's
	*  next Edge.
	*  @param a the edge to be placed
	*/
	public void insertFront(Edge a){
		if(a!=null){
			Edge temp = next;
			next = a;
			a.next = temp;
			temp.prev = a;
			a.prev = this;
		}
	}

	
	
		
	/**
	  * @return the pair of vertices connected by this Edge
	 */
	public VertexPair vertices(){
		return vertices;
	}
	
	/**
	  * @return first vertex connected by this Edge
	 */
	public Object object1(){
		return vertices.object1;
	}
	
	/**
	  * @return second vertex connected by this Edge
	 */
	public Object object2(){
		return vertices.object2;
	}
	
	
	/**
	  * A partner is a mirror image of this Edge. If this Edge
	  * connects vertices U and V, and is stored in vertex U, then
	  * its partner connectes vertices V and U, and is stored in V
	  * @return this Edge's partner
	 */
	public Edge partner(){
		return partner;
	}
	
	/**
	  * Will cause this Edge to remove itself from its own list of
	  * Edges, as long as this Edge is not the sentinal node
	 */
	public void removeSelf(){
		if(prev != null){
			prev.next = next;
			next.prev = prev;
			prev = null;
			next = null;
		}
	}
}