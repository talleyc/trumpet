package graph;
import list.*;

public class Vertex extends Object{
        
        protected Object item;
		private Vertex prev;
		private Vertex next;
		protected int degree;
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
        public Object getVertex() {
                return item;  
        }
	
        //Return the next vertex
        public Vertex nextVertex() {
                return next;
        }
        
        //Return the previous vertex
        public Vertex prevVertex() {
                return prev;
        }    
        
		//Return the degree
		public int getDegree() {
				return degree;
		}
		
		//Return the list of edges
		public Edge getEdges() {
				return edges;
		}
		
		//Return the 
		public Object GetItem(){
			return item;
		}
		

}