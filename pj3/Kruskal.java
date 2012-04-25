/* Kruskal.java */

import java.util.Hashtable;
import graph.*;
import set.*;

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
	Edge[] edges = g.getEdges();
	Object[] vertices = g.getVertices();
	WUGraph ans = new WUGraph();
	int count = 0;
	for(int i=0; i<vertices.length; i++){ //add all vertices from g to ans
		ans.addVertex(vertices[i]);
	}
	quicksort(edges);
	DisjointSets ds = new DisjointSets(vertices.length);//index i of vertices array maps to index i of ds
	while(ans.edgeCount() < vertices.length-1 && ans.edgeCount() < edges.length ){
		int locA = locationOf(edges[count].object1(),vertices);
		int locB = locationOf(edges[count].object2(),vertices);
		if(ds.find(locA) != ds.find(locB)){
			ans.copyEdge(edges[count]);
			ds.union(ds.find(locA),ds.find(locB));
		}
		count++;
	}
	return ans;
  }
  
  public static int locationOf(Object a, Object[] b){
	for(int i=0; i<b.length; i++){
		if(b[i] == a){
			return i;
		}
	} return -1; //signifies error
  }
 
  
  /**
   *  Quicksort algorithm.
   *  @param a an array of int items.
   **/
  public static void quicksort(Edge[] a) {
    quicksort(a, 0, a.length - 1);
  }

  /**
   *  Method to swap two ints in an array.
   *  @param a an array of ints.
   *  @param index1 the index of the first int to be swapped.
   *  @param index2 the index of the second int to be swapped.
   **/
  public static void swapReferences(Edge[] a, int index1, int index2) {
    Edge tmp = a[index1];
    a[index1] = a[index2];
    a[index2] = tmp;
  }

  /**
   *  This is a generic version of C.A.R Hoare's Quick Sort algorithm.  This
   *  will handle arrays that are already sorted, and arrays with duplicate
   *  keys.
   *
   *  If you think of an array as going from the lowest index on the left to
   *  the highest index on the right then the parameters to this function are
   *  lowest index or left and highest index or right.  The first time you call
   *  this function it will be with the parameters 0, a.length - 1.
   *
   *  @param a       an integer array
   *  @param lo0     left boundary of array partition
   *  @param hi0     right boundary of array partition
   **/
   public static void quicksort(Edge a[], int lo0, int hi0) {
     int lo = lo0;
     int hi = hi0;
     int mid;

     if (hi0 > lo0) {

       swapReferences(a, lo0, (lo0 + hi0)/2);
       mid = a[(lo0 + hi0) / 2].weight();

       while (lo <= hi) {
         while((lo < hi0) && (a[lo].weight() < mid)) {
           lo++;
         }

         while((hi > lo0) && (a[hi].weight() > mid)) {
           hi--;
         }
         if (lo <= hi) {
           swapReferences(a, lo, hi);
           lo++;
           hi--;
         }
       }

       if (lo0 < hi) {
         quicksort(a, lo0, hi);
       }

       if (lo < hi0) {
         quicksort(a, lo, hi0);
       }
     }
   }

}
