package graph.minspanningtree;

import graph.base.Edge;
import graph.base.Graph;
import graph.base.Node;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class PrimsAlgorithm {
    public Set<Edge> execute(final Graph graph) {
	// create candidate set (heap)
	final TreeSet<Node> candidates = new TreeSet<Node>();
	final Collection<Node> nodes = graph.getNodes();
	final Set<Node> done = new HashSet<Node>(nodes.size());
	// Set all nodes to infinity
	for (final Node node : nodes)
	    node.setLabel(Integer.MAX_VALUE);
	Node currentNode = nodes.iterator().next(); // Any node
	currentNode.setLabel(0);
	candidates.add(currentNode);
	final Set<Edge> result = new HashSet<Edge>(nodes.size());
	Edge currentEdge;
	while (done.size() < nodes.size()) {
	    currentNode = candidates.pollFirst();
	    currentEdge = this.findEdge(done, currentNode);
	    if (currentEdge != null)
		result.add(currentEdge);
	    this.updateNeighbours(currentNode, candidates, done);
	    done.add(currentNode);
	}
	return result;
    }

    private Edge findEdge(final Set<Node> done, final Node currentNode) {
	for (final Edge edge : currentNode.getNeigbourEdges())
	    if ((done.contains(edge.getStartNode()) || done.contains(edge.getEndNode()))
		    && Math.abs(edge.getCost() - currentNode.getLabel()) < Graph.EPSILON)
		return edge;
	return null;
    }

    private void updateNeighbours(final Node currentNode, final TreeSet<Node> candidates, final Set<Node> done) {
	Node neighbour;
	double minCost;
	for (final Edge edge : currentNode.getNeigbourEdges()) {
	    neighbour = edge.getStartNode().equals(currentNode) ? edge.getEndNode() : edge.getStartNode();
	    if (!done.contains(neighbour)) {
		minCost = neighbour.getLabel();
		if (edge.getCost() < minCost) {
		    if (minCost < Integer.MAX_VALUE)
			candidates.remove(neighbour);
		    neighbour.setLabel(edge.getCost());
		    candidates.add(neighbour);
		}
	    }
	}
    }

    public long calculateTotalCost(final Set<Edge> edges) {
	long cost = 0;
	for (final Edge edge : edges)
	    cost += edge.getCost();
	return cost;
    }
}
