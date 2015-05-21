import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Ramesh Sangaraju 
 * Date: 18-04-2015
 */
public class p07 {
	static int minimumCost;
	static int spanningTreeEdges;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numberOfVertices = scanner.nextInt();
		int numberOfEdges = scanner.nextInt();

		// creating array of vertices
		Vertex[] vertices = new Vertex[numberOfVertices];
		for (int i = 0; i < numberOfVertices; i++) {
			vertices[i] = makeSet(i);
		}

		// reading edges and storing in an array list
		List<Edge> edges = new LinkedList<Edge>();
		while (numberOfEdges != 0) {
			Edge newEdge = new Edge();
			newEdge.fromVertex = vertices[scanner.nextInt()];
			newEdge.toVertex = vertices[scanner.nextInt()];
			newEdge.cost = scanner.nextInt();
			edges.add(newEdge);
			numberOfEdges--;
		}

		// sorting the array list based on the cost of edge
		Collections.sort(edges);

		// checking all the edges to obtain minimum spanning tree
		kruskals(edges, vertices);

		// printing cost of minimum spanning tree
		System.out.println("Total cost: " + minimumCost);
	}

	// The logic of kruskal's algorithm using findSet and union methods
	private static void kruskals(List<Edge> edges, Vertex[] vertices) {
		for (Edge edge : edges) {
			Vertex v1 = edge.fromVertex;
			Vertex v2 = edge.toVertex;
			if (!findSet(v1).equals(findSet(v2))) {
				union(v1, v2);
				minimumCost = minimumCost + edge.cost;
				spanningTreeEdges++;
				System.out.println(edge);
			}
			// breaking the loop if all the vertices are visited
			if (spanningTreeEdges == vertices.length - 1)
				break;
		}
	}

	// This method merges two disjoint sets (trees)
	private static void union(Vertex v1, Vertex v2) {
		if (v1.rank > v2.rank)
			v2.parent = v1;
		else {
			v1.parent = v2;
			if (v1.rank == v2.rank)
				v2.rank++;
		}
	}

	// This method returns the parent of vertex
	private static Vertex findSet(Vertex vertex) {
		if (!vertex.equals(vertex.parent)) {
			vertex.parent = findSet(vertex.parent);
		}
		return vertex.parent;
	}

	// This method creates a new disjoint set
	private static Vertex makeSet(int i) {
		Vertex newVertex = new Vertex(i);
		newVertex.parent = newVertex;
		return newVertex;
	}
}

/**
 * @author Ramesh This class represents a node or vertex in a tree
 */
class Vertex {
	int vertexNumber;
	int rank;
	Vertex parent;

	public Vertex(int vertextNumber) {
		this.vertexNumber = vertextNumber;
		this.rank = 0;
	}
}

/**
 * @author Ramesh This class represents edge in a tree
 */
class Edge implements Comparable<Edge> {
	Vertex fromVertex;
	Vertex toVertex;
	int cost;

	/**
	 * This method sorts the edges based on cost of edge
	 */
	@Override
	public int compareTo(Edge edge) {
		return this.cost - edge.cost;
	}

	/**
	 * This method gives string representation of edge
	 */
	@Override
	public String toString() {
		return fromVertex.vertexNumber + " " + toVertex.vertexNumber + " "
				+ this.cost;
	}
}