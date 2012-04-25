/* WUGraph.java */

package graph;
import dict.*;


/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph implements Edge, Vertex{

	protected HashTableChained vertexTable;
	protected HashTableChained edgeTable;
	protected Vertex vList;
	protected int edgeCount;
	protected int vertexCount;

  /**
   * WUGraph() constructs a graph having no vertices or edges.
   *
   * Running time:  O(1).
   */
  public WUGraph(){
		vertexTable = new HashTableChained(2);
		edgeTable = new HashTableChained(2);
		vList = new Vertex();
		edgeCount = 0;
		vertexCount = 0;
	}

  /**
   * vertexCount() returns the number of vertices in the graph.
   *
   * Running time:  O(1).
   */
  public int vertexCount(){
		return vertexCount;
	}
	
  /**
   * edgeCount() returns the number of edges in the graph.
   *
   * Running time:  O(1).
   */
  public int edgeCount(){
		return edgeCount;
	}
	
  /**
   * getVertices() returns an array containing all the objects that serve
   * as vertices of the graph.  The array's length is exactly equal to the
   * number of vertices.  If the graph has no vertices, the array has length
   * zero.
   *
   * (NOTE:  Do not return any internal data structure you use to represent
   * vertices!  Return only the same objects that were provided by the
   * calling application in calls to addVertex().)
   *
   * Running time:  O(|V|).
   */
  public Object[] getVertices(){
		Object[] vxList = new Object[vertexCount];
		Vertex currVx = vList.nextVertex();
		for (int i = 0; i < vertexCount; i++) {
			Object[i] = currVx.getVertex();
			currVx = currVx.nextVertex();
		}
		return vxList;
	}
	
  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.  The
   * vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(1).
   */
  public void addVertex(Object vertex){
		if (!isVertex(vertex)) {
			Vertex newVx = new Vertex();
			newVx.item = vertex;
			newVx.prev = vList.prev;
			vList.prev.next = newVx;
			newVx.next = vList;
			vList.prev = newVx;
			vertextable.insert(vertex, newVx);
		}
		else return;

	if (isVertex(vertex)) {
		Vertex newvx = new Vertex();
		newvx.item = vertex;
		newvx.prev = this;
		this.next = newvx;	

	}
	else return;
  }

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public void removeVertex(Object vertex){
	if (isVertex(vertex))
	{
		if (vertexTable.find(vertex) != null) 
		{
			Vertex oldVx = vertex;
			oldVx.next.prev = oldVx.prev;
			oldVx.prev.next = oldVx.next;
			vertextable.remove(vertex);
		}
		else return;
	}
	else return;
  }
 
 
  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   *
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex){
		return vertexTable.find(vertex);
	}

  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   *
   * Running time:  O(1).
   */

  public int degree(Object vertex){
		Entry vxEntry = vertexTable.find(vertex);
		if (vxEntry == null || this.isVertex(vertex) != true) {
			return 0;
		} else {
			return vxEntry.value.GetDegree();
		}
	}
	

  public int degree(Object vertex) {
			return vertex.degree;
	}	

  /**
   * getNeighbors() returns a new Neighbors object referencing two arrays.  The
   * Neighbors.neighborList array contains each object that is connected to the
   * input object by an edge.  The Neighbors.weightList array contains the
   * weights of the corresponding edges.  The length of both arrays is equal to
   * the number of edges incident on the input vertex.  If the vertex has
   * degree zero, or if the parameter "vertex" does not represent a vertex of
   * the graph, null is returned (instead of a Neighbors object).
   *
   * The returned Neighbors object, and the two arrays, are both newly created.
   * No previously existing Neighbors object or array is changed.
   *
   * (NOTE:  In the neighborList array, do not return any internal data
   * structure you use to represent vertices!  Return only the same objects
   * that were provided by the calling application in calls to addVertex().)
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public Neighbors getNeighbors(Object vertex){
		Neighbors res;
		res.neigborList = new Object[degree];
		res.weightList = new int[degree];
		Vertex vxEntry = vertexTable.find(vertex).value;
		Edge currEdge = vxEntry.getEdges().next;
		for (int i = 0; i < vxEntry.getDegree(); i++) {
			res.neighborList[i] = getVertices().object2;
			res.weightList[i] = getWeight();
			currEdge = currEdge.next;
		}
		return res;
	}

  /**
   * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
   * u and v does not represent a vertex of the graph, the graph is unchanged.
   * The edge is assigned a weight of "weight".  If the edge is already
   * contained in the graph, the weight is updated to reflect the new value.
   * Self-edges (where u == v) are allowed.
   *
   * Running time:  O(1).
   */
  public void addEdge(Object u, Object v, int weight){
	if(isVertex(u) && isVertex(v) && !isEdge(u, v)){
		Edge first = new Edge(weight, u, v, this);
		Edge second = new Edge(weight, v, u, this);
		first.partner = second;
		second.partner = first;
		vertexTable.get(u).edges.insertFront(first);
		vertexTable.get(v).edges.insertFront(second);
		edgeTable.put(new VertexPair(vertexTable.get(u), vertexTable.get(v)), first);
		v.degree++;
		u.degree++;
	}
  
  }

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
   */
  public void removeEdge(Object u, Object v){
	if(isVertex(u) && isVertex(v) && !isEdge(u, v)){
		VertexPair pair = new VertexPair(vertexTable.get(u), vertexTable.get(v));
		Edge edge = (Edge) edgeTable.get(pair);
		edgeTable.remove(pair);
		edge.removeSelf();
		edge.partner.removeSelf();
		u.degree--;
		v.degree--;
	}
  }

  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
  public boolean isEdge(Object u, Object v){
		VertexPair edge = new VertexPair(u, v);
		return edgeTable.find(edge);
	}

  /**
   * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
   * an edge (including the case where either of the parameters u and v does
   * not represent a vertex of the graph).
   *
   * (NOTE:  A well-behaved application should try to avoid calling this
   * method for an edge that is not in the graph, and should certainly not
   * treat the result as if it actually represents an edge with weight zero.
   * However, some sort of default response is necessary for missing edges,
   * so we return zero.  An exception would be more appropriate, but
   * also more annoying.)
   *
   * Running time:  O(1).
   */
  public int weight(Object u, Object v){
		VertexPair edge = new VertexPair(u, v);
		Entry edgeEntry = edgeTable.find(edge);
		if (edgeEntry == null || this.isVertex(u) != true || this.isVertex(v) != true) {
			return 0;
		} else {
			return edgeEntry.value.weight();
		}
	}

}
