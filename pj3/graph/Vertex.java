package graph;


/**
*  Similar to an Edge, a Vertex is a node in a doubly-linked list
*  with a sentinal node. The sentinal node is kept by the WUGraph object
*/
public class Vertex extends Object{
        
        protected Object item;
		protected Vertex prev;
		protected Vertex next;
		protected int degree;
		protected Edge edges;
		
	/**
	*  Constructs an empty Vertex.
	*/
        public Vertex() {
                item = null;
				prev = this;
				next = this;
				degree = 0;
				edges = new Edge();
        }
       

	/**
	*  @return the next vertex in this linked list of vertices.
	*/
        public Vertex next() {
                return next;
        }
        
	/**
	*  @return the previous vertex in this linked list of vertices.
	*/
        public Vertex prev() {
                return prev;
        }    
        
	/**
	*  @return the degree, or number of unique edges touching this node
	*/
		public int degree() {
				return degree;
		}
		
	/**
	*  @return the sentinal node Edge in this vertex's linked list of edges.
	*/
		public Edge edges() {
				return edges;
		}
		
	/**
	*  @return the item stored by this vertex.
	*/
		public Object item(){
			return item;
		}
		

}