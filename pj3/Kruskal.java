/* Kruskal.java */

import java.util.Hashtable;
import graph.*;
import set.*;
import dict.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   */
  public static WUGraph minSpanTree(WUGraph g){
	WUGraph temp = new WUGraph();	//the purpose of the temp graph is to count all edges
	Edge[] edges = new Edge[g.edgeCount()];
	Neighbors neighbors;
	Object[] vertices = g.getVertices();
	Object[] object1Arr = new Object[g.edgeCount()];
	Object[] object2Arr= new Object[g.edgeCount()];
	int[] weightArr = new int[g.edgeCount()];
	HashTableChained vertexTable = new HashTableChained();
	WUGraph ans = new WUGraph();
	int count = 0;
	
	//put all vertices in the ans and temp graphs
	for(int i=0; i<vertices.length; i++){
		ans.addVertex(vertices[i]);
		vertexTable.insert(vertices[i], new Integer(i));
		temp.addVertex(vertices[i]);
	}
	
	//Acquire an array of all edges
	for(int i=0; i<vertices.length; i++){ //add all vertices from g to ans, and hash them with an identifying Integer value
		neighbors = g.getNeighbors(vertices[i]);
		for(int j=0; j<neighbors.neighborList.length; j++){
			if(!temp.isEdge(vertices[i], neighbors.neighborList[j])){
				temp.addEdge(vertices[i], neighbors.neighborList[j], neighbors.weightList[j]);
				//edges[count] = new Edge( neighbors.weightList[j], neighbors.neighborList[j], vertices[i]);
				object2Arr[count] = neighbors.neighborList[j];
				object1Arr[count] = vertices[i];
				weightArr[count] = neighbors.weightList[j];
				count++;
			}
		}
	}

	//apply Kruskal's algorithm
	count = 0;
	//quicksort(edges);
	quicksort(weightArr, object1Arr, object2Arr);
	DisjointSets ds = new DisjointSets(vertices.length);//vertex in vertexTable which maps to i corresponds to element i of ds
	while(ans.edgeCount() < vertices.length-1 && ans.edgeCount() < edges.length ){
		int locA = locationOf(object1Arr[count],vertexTable);
		int locB = locationOf(object2Arr[count],vertexTable);
		if(ds.find(locA) != ds.find(locB)){
			ans.addEdge(object1Arr[count], object2Arr[count], weightArr[count]);
			ds.union(ds.find(locA),ds.find(locB));
		}
		count++;
	}
	return ans;
  }
  
  /**
   * Returns the index of the disjoint sets object that the given object corresponds to
   * @param key the object whose index is being found
   * @param table the hash table which contains the necessary key-value pairs
   *      where the keys are the objects and the values are their corresponding indices in the
   *      disjoint sets object.
   * @return the index of the disjoint sets data structure that corresponds to @param key
   */
  public static int locationOf(Object key, HashTableChained table){
	return ((Integer)table.find(key).value()).intValue();
  }
 
  
  /**
   *  Quicksort algorithm.
   *  @param a an array of Edge items.
   **/
  public static void quicksort(int[] a, Object[] b, Object[] c) {
    quicksort(a, 0, a.length - 1 , b, c);
  }

  /**
   *  Method to swap two Edges in an array.
   *  @param a an array of Edges.
   *  @param index1 the index of the first Edge to be swapped.
   *  @param index2 the index of the second Edge to be swapped.
   **/
  public static void swapReferences(int[] a, Object[] b, Object[] c, int index1, int index2) {
    Object tmp = b[index1];
    b[index1] = b[index2];
    b[index2] = tmp;
	tmp = c[index1];
    c[index1] = c[index2];
    c[index2] = tmp;
	int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }

  /**
   *  @param a       an Edge array
   *  @param lo0     left boundary of array partition
   *  @param hi0     right boundary of array partition
   **/
   public static void quicksort(int a[], int lo0, int hi0, Object[] b, Object[] c) {
     int lo = lo0;
     int hi = hi0;
     int mid;

     if (hi0 > lo0) {

       swapReferences(a, b, c, lo0, (lo0 + hi0)/2);
       mid = a[(lo0 + hi0) / 2];

       while (lo <= hi) {
         while((lo < hi0) && (a[lo] < mid)) {
           lo++;
         }

         while((hi > lo0) && (a[hi] > mid)) {
           hi--;
         }
         if (lo <= hi) {
           swapReferences(a, b, c, lo, hi);
           lo++;
           hi--;
         }
       }

       if (lo0 < hi) {
         quicksort(a, lo0, hi, b, c);
       }

       if (lo < hi0) {
         quicksort(a, lo, hi0, b, c);
       }
     }
   }

}
