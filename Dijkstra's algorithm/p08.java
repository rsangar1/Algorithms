import java.util.Scanner;

/**
 * @author Ramesh Sangaraju
 * Date: 28 - 04- 2015
 * Dijkstra's algorithm
 */
public class p08 {
	static int numberOfVertices = 0;
	static int numberOfEdges = 0;
	static Vertex[] vertices = null;
	static int heapSize = 0;
	static HeapNode[] heapArray = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		numberOfVertices = scanner.nextInt();
		numberOfEdges = scanner.nextInt();
		
		//creating array of vertices
		vertices = new Vertex[numberOfVertices];
		
		//initializing all the vertices 
		initializeSingleSource();
		
		//reading input and creating new edges
		for(int i=0;i<numberOfEdges;i++){
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			int length = scanner.nextInt();
			Edge newEdge1 = new Edge(u, v , length);
			addEdgeToVertex(u, newEdge1);
			Edge newEdge2 = new Edge(v, u, length);
			addEdgeToVertex(v, newEdge2);
		}
		heapSize = numberOfVertices;
		//calling dijkstras algorithm
		dijkstras(vertices);
		
	}
	
	//this method initializes all the vertices distance to infinity except source
	private static void initializeSingleSource() {
		for(int i=0;i<vertices.length;i++){
			Vertex newVertex = new Vertex(i);
			vertices[i] = newVertex;
		}
		vertices[0].distance = 0;
	}

	//adding the new edge to end of the vertex
	private static void addEdgeToVertex(int vertexNumber, Edge newEdge) {
		if(vertices[vertexNumber].head !=null){
			vertices[vertexNumber].tail.next = newEdge;
			vertices[vertexNumber].tail = newEdge;
		}else{
			vertices[vertexNumber].head = newEdge;
			vertices[vertexNumber].tail = newEdge;
		}
	}

	//Main dijkstra's algorithm implementation
	private static void dijkstras(Vertex[] vertices) {
		//iniitializing and creating array of heap nodes
		heapArray = new HeapNode[numberOfVertices];
		for(int i=0;i<numberOfVertices;i++){
			HeapNode heapNode = new HeapNode();
			heapNode.vertexNumber = i;
			heapNode.distance = vertices[i].distance;
			heapArray[i] = heapNode;
		}
		
		while(heapSize > 0){
			HeapNode currentNode = extractMin(heapArray);
			Vertex currentVertex = vertices[currentNode.vertexNumber];
			Edge e = currentVertex.head;
			while(e != null){
				int edgeLength = e.length;
				int heapIndex = vertices[e.finish].positionInHeap;
				HeapNode destNode = heapArray[heapIndex];
				relax(currentNode, destNode, edgeLength);
				e = e.next;
			}
		}
		
		//printing the result
		int farthersDistance = 0;
		int farthestVertex = 0;
		for(HeapNode h:heapArray){
			if(h.distance > farthersDistance){
				farthersDistance = h.distance;
				farthestVertex = h.vertexNumber;
			}
		}
		System.out.println(farthersDistance+" "+farthestVertex);
	}

	//This method extracts minimum element from the heap
	private static HeapNode extractMin(HeapNode[] heapArray) {
		HeapNode minHeapNode = heapArray[0];
		heapArray[0] = heapArray[heapSize-1];
		--heapSize;
		minHeapify(heapArray, 0);
		return minHeapNode;
	}

	//This method performs minHeapify operation
	private static void minHeapify(HeapNode[] heapArray, int index) {
		int leftIndex = 2*index + 1;
		int rightIndex = 2*index + 2;
		int smallest = index;
		HeapNode temp;
		if(leftIndex < heapSize &&
				heapArray[leftIndex].distance < heapArray[index].distance){
			smallest = leftIndex;
		}
		if(rightIndex < heapSize &&
				heapArray[rightIndex].distance < heapArray[index].distance){
			smallest = rightIndex;
		}
		if(smallest != index){
			temp = heapArray[index];
			//swapping the heap positions and also updating heap indexes
			heapArray[index] = heapArray[smallest];
			vertices[heapArray[index].vertexNumber].positionInHeap = smallest;
			heapArray[smallest] = temp;
			vertices[heapArray[smallest].vertexNumber].positionInHeap = index;
			minHeapify(heapArray, smallest);
		}
	}
	
	//This method updates the vertex distance if the distance is less than existing distance
	private static void relax(HeapNode currentNode, HeapNode destNode, int edgeCost){
		int newDistance = currentNode.distance + edgeCost;
		if(destNode.distance > newDistance){
			destNode.distance = newDistance;
			vertices[destNode.vertexNumber].distance = newDistance;
		}
	}

}


/**
 * @author Ramesh
 * This is Vertex or node of graph
 */
class Vertex{
	int distance;
	Edge head;
	Edge tail;
	int positionInHeap;
	
	public Vertex(int i){
		distance = 9999999;
		head = null;
		tail = null;
		positionInHeap = i;
	}
}

/**
 * @author Ramesh
 * This represents edge of a graph
 */
class Edge{
	int start, finish;
	int length;
	Edge next;
	
	public Edge(int i, int j, int l){
		start = i;
		finish = j;
		length = l;
		next = null;
	}
}

/**
 * @author Ramesh
 * This acts as heap node
 */
class HeapNode{
	int vertexNumber;
	int distance;
	
	public HeapNode(){}
}
