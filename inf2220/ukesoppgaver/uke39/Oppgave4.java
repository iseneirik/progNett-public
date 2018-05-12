import java.util.HashMap;
import java.util.LinkedList;

class DistanceGraph {
	HashMap<String, Node> graph;

	DistanceGraph(){
		graph = new HashMap<String, Node>();
	}

	void addVertex(String id){
		graph.put(id, new Node(id));
	}

	boolean addEdge(String from, String to){
		Node fromNode = graph.get(from);
		Node toNode = graph.get(to);
		if(fromNode == null || toNode == null){
			System.err.printf(" %s : %s",
				"DistanceGraph.addEdge",
				"could not find both nodes\n");
			return false;
		}
		return fromNode.addEdge(toNode);
	}

	private void removeVisitedFlags(){
		for(Node n : graph.values()){
			n.visited = false;
		}
	}

	public LinkedList<Node> shortestPathFrom(String id){
		// first we have to clear all flags..
		removeVisitedFlags();
		// make a que and a done list
		LinkedList<Node> que = new LinkedList<Node>();
		LinkedList<Node> done = new LinkedList<Node>();
		
		Node firstNode = graph.get(id);
		firstNode.distance = 0;
		firstNode.visited = true;
		que.add(firstNode);

		for (int i = 0; i < que.size(); i++) {
			Node currNode = que.get(i);
			done.add(currNode);

			for (Node neighbour : currNode.getNeighbours()) {
				if (!neighbour.visited) {
					neighbour.distance = currNode.distance + 1;
					neighbour.visited = true;
					que.add(neighbour);
				}
			}

		}

		return done;
	}
	
	// testing it with the graph from slide 27 last lecture
	public static void main(String[] args){
		DistanceGraph dg = new DistanceGraph();

		for(int i = 1; i < 8; i++){
			dg.addVertex(String.format("V%d",i));
		}
		dg.addEdge("V1", "V2");
		dg.addEdge("V1", "V4");
		dg.addEdge("V2", "V5");
		dg.addEdge("V2", "V4");
		dg.addEdge("V3", "V6");
		dg.addEdge("V3", "V1");
		dg.addEdge("V4", "V3");
		dg.addEdge("V4", "V6");
		dg.addEdge("V4", "V7");
		dg.addEdge("V4", "V5");
		dg.addEdge("V5", "V7");
		dg.addEdge("V7", "V6");

		for(Node n : dg.shortestPathFrom("V3")){
			System.out.println(n);
		}
		/* should produce something similar to this..
		Node id: V3, distance 0
		Node id: V6, distance 1
		Node id: V1, distance 1
		Node id: V2, distance 2
		Node id: V4, distance 2
		Node id: V5, distance 3
		Node id: V7, distance 3
		*/
		System.out.println(" ------------------------");
		for(Node n : dg.shortestPathFrom("V4")){
			System.out.println(n);
		}
		/* should produce somthing like this
		Node id: V4, distance 0
		Node id: V3, distance 1
		Node id: V6, distance 1
		Node id: V7, distance 1
		Node id: V5, distance 1
		Node id: V1, distance 2
		Node id: V2, distance 3
		*/
	}
}

class Node{
	String id;
	LinkedList<Node> neighbours;
	boolean visited;
	int distance;

	Node(String _id){
		id = _id;
		neighbours = new LinkedList<Node>();
		visited = false;
	}

	public boolean addEdge(Node to){
		return neighbours.add(to);
	}

	public LinkedList<Node> getNeighbours(){
		return neighbours;
	}

	public String toString(){
		return String.format(" Node id: %2s, distance %d ",id,distance);
	}
}