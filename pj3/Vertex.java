package graph;
import list.*;

public class Vertex extends Object{
        
        private Object item;
		private Vertex prev;
		private Vertex next;
		private int degree;
		private Edge edges;
        
        //Empty constructor for a Vertex
        public Vertex() {
                item = null;
				prev = this;
				next = this;
				degree = 0;
				edges = new Edge();
        }
       
		 //Return the current vertex
        public Object GetVertex() {
                return item;  
        }
	
        //Return the next vertex
        public Vertex NextVertex() {
                return next;
        }
        
        //Return the previous vertex
        public Vertex PrevVertex() {
                return prev;
        }    
        
		//Return the degree
		public int GetDegree() {
				return degree;
		}
		
		//Return the list of edges
		public Edge GetEdges() {
				return edges;
		}
		

}