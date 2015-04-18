package graph.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    private final Map<Long, Node> nodes;
    private final Set<Edge> edges;
    private final boolean isDirected;

    public Graph(final int numberOfNodes, final int numberOfEdges, final boolean directed) {
	this.nodes = new HashMap<Long, Node>(numberOfNodes);
	this.edges = new HashSet<Edge>(numberOfEdges);
	this.isDirected = directed;
    }

    public void addEdge(final long idNode1, final long idNode2, final int cost) {
	final Long key1 = Long.valueOf(idNode1);
	if (!this.nodes.containsKey(key1))
	    this.addNode(new Node(idNode1));
	final Node node1 = this.nodes.get(key1);
	final Long key2 = Long.valueOf(idNode2);
	if (!this.nodes.containsKey(key2))
	    this.addNode(new Node(idNode2));
	final Node node2 = this.nodes.get(key2);
	final Edge newEdge = new Edge(node1, node2, cost);
	this.edges.add(newEdge);
	node1.addNeighbourEdge(newEdge);
	if (!this.isDirected)
	    node2.addNeighbourEdge(newEdge);
    }

    public void addNode(final Node node) {
	this.nodes.put(Long.valueOf(node.getId()), node);
    }

    public Collection<Node> getNodes() {
	return this.nodes.values();
    }

    public Node getNodeById(final Long idNode) {
	return this.nodes.get(idNode);
    }

    public Set<Edge> getEdges() {
	return this.edges;
    }

    public boolean isDirected() {
	return this.isDirected;
    }
}
