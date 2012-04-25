/* WUGraph.java */

package graph;
import dict.*;


/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {

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
		Vertex currVx = vList.next;
		for (int i = 0; i < vertexCount; i++) {
			vxList[i] = currVx.vertex();
			currVx = currVx.next;
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
			vertexTable.insert(vertex, newVx);
			vertexCount++;
		} 
	}

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
	public void removeVertex(Object vertex){
		if (isVertex(vertex)) {
			Vertex oldVx = (Vertex)vertexTable.find(vertex).value();
			Edge currEdge = oldVx.edges().next;
			Edge firstEdge = currEdge.next;
			oldVx.prev.next = oldVx.next;
			oldVx.next.prev = oldVx.prev;
			oldVx.next = null;
			oldVx.prev = null;
			while (currEdge.vertices() != null) {
				if(currEdge.partner() != currEdge){
					currEdge.partner().removeSelf();
				}
				currEdge.removeSelf();
				edgeTable.remove(currEdge.vertices);
				edgeCount--;
				((Vertex)(vertexTable.find(currEdge.vertices.object2).value())).degree--;
				currEdge = firstEdge;
				firstEdge = firstEdge.next;
			}
			vertexTable.remove(vertex);
			vertexCount--;
		}
	}
 
  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   *
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex){
		return vertexTable.find(vertex)!=null;
	}

  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   *
   * Running time:  O(1).
   */

  public int degree(Object vertex){
		if(!isVertex(vertex)){
			return 0;
		}
		Vertex vxEntry = (Vertex)vertexTable.find(vertex).value();
		if (vxEntry == null || this.isVertex(vertex) != true) {
			return 0;
		} else {
			return vxEntry.degree();
		}
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
		Neighbors res = new Neighbors();
		Vertex vxEntry = (Vertex)vertexTable.find(vertex).value();
		res.neighborList = new Object[vxEntry.degree()];
		res.weightList = new int[vxEntry.degree()];
		Edge currEdge = vxEntry.edges().next;
		for (int i = 0; i < vxEntry.degree(); i++) {
			res.neighborList[i] = currEdge.vertices().object2;
			res.weightList[i] = currEdge.weight();
			currEdge = currEdge.next;
		}
		if(res.neighborList.length==0){
			return null;
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
	if(isVertex(u) && isVertex(v) && !isEdge(u, v) && vertexTable.find(u) != vertexTable.find(v)){
		Edge first = new Edge(weight, u, v);
		Edge second = new Edge(weight, v, u);
		first.partner = second;
		second.partner = first;
		((Vertex) vertexTable.find(u).value()).edges().insertFront(first);
		((Vertex) vertexTable.find(v).value()).edges().insertFront(second);
		edgeTable.insert(new VertexPair(u, v), first);
		((Vertex)vertexTable.find(v).value()).degree++;
		if(vertexTable.find(v).value() != vertexTable.find(u).value()) {
			((Vertex)vertexTable.find(u).value()).degree++;
		}
		edgeCount++;
	} else if(isVertex(u) && isVertex(v) && !isEdge(u, v)){
		Edge first = new Edge(weight, u, v);
		first.partner = first;
		((Vertex) vertexTable.find(u).value()).edges().insertFront(first);
		edgeTable.insert(new VertexPair(u, v), first);
		((Vertex)vertexTable.find(v).value()).degree++;
		edgeCount++;
	}else if (isEdge(u,v)){
		Edge g = (Edge)edgeTable.find(new VertexPair(u,v)).value();
		g.weight = weight;
		g.partner.weight = weight;
	}
  }
  
  public void copyEdge(Edge a){
	this.addEdge(a.object1(), a.object2(), a.weight());
  }

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph partner
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
   */
  public void removeEdge(Object u, Object v){
	if(isVertex(u) && isVertex(v) && isEdge(u, v)){
		VertexPair pair = new VertexPair(u, v);
		if(edgeTable.find(pair) != null){
			Edge edge = (Edge) edgeTable.find(pair).value();
			edgeTable.remove(pair);
			edge.removeSelf();
			edge.partner.removeSelf();
			((Vertex)vertexTable.find(u).value()).degree--;
			if(vertexTable.find(v).value() != vertexTable.find(u).value()) {
				((Vertex)vertexTable.find(v).value()).degree--;
			}
			edgeCount--;
		}
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
		return edgeTable.find(edge)!=null;
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
			return ((Edge)edgeEntry.value()).weight();
		}
	}
	
	
	public Edge[] getEdges(){
		Edge[] ans = new Edge[edgeCount];
		Object[] a = edgeTable.getEntries();
		for(int i=0; i<edgeCount; i++){
			ans[i] = (Edge)((Entry)a[i]).value();
		}
		return ans;
	}

}
