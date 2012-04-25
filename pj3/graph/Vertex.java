package graph;

public class Vertex extends Object{
        
        protected Object item;
		protected Vertex prev;
		protected Vertex next;
		protected int degree;
		protected Edge edges;
		
        //Empty constructor for a Vertex
        public Vertex() {
                item = null;
				prev = this;
				next = this;
				degree = 0;
				edges = new Edge();
        }
       
		 //Return the current vertex
        public Object vertex() {
                return item;  
        }
	
        //Return the next vertex
        public Vertex next() {
                return next;
        }
        
        //Return the previous vertex
        public Vertex prev() {
                return prev;
        }    
        
		//Return the degree
		public int degree() {
				return degree;
		}
		
		//Return the list of edges
		public Edge edges() {
				return edges;
		}
		
		//Return the 
		public Object item(){
			return item;
		}
		

}