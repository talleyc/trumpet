package graph;

public class Edge{
	protected int weight;
	protected VertexPair vertices; //NULL for sentinal node
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
	public Edge(int w, Object a, Object b){
		weight = w;
		vertices = new VertexPair(a, b);
		partner = null;
		next = this;
		prev = this;
	}
	
	public int weight(){
		return weight;
	}
	
	public void insertFront(Edge a){
		if(a!=null){
			Edge temp = next;
			next = a;
			a.next = temp;
			temp.prev = a;
			a.prev = this;
		}
	}
	
	public Edge(){
		vertices = null;
		next = this;
		prev = this;
		partner = null;
		weight = 0;
	}
	
	
		
	
	public VertexPair vertices(){
		return vertices;
	}
	
	public Object object1(){
		return vertices.object1;
	}
	
	public Object object2(){
		return vertices.object2;
	}
	
	public Edge partner(){
		return partner;
	}
	
	public void removeSelf(){
		if(prev != null){
			prev.next = next;
			next.prev = prev;
			prev = null;
			next = null;
		}
	}
}