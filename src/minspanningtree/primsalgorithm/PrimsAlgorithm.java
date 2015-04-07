package minspanningtree.primsalgorithm;

import graph.Edge;
import graph.Node;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import minspanningtree.graph.PrimGraph;
import minspanningtree.graph.PrimNode;

public class PrimsAlgorithm {
    public Set<Edge> execute(final PrimGraph graph) {
	// create candidate set (heap)
	final TreeSet<PrimNode> candidates = new TreeSet<PrimNode>();
	final Collection<PrimNode> nodes = graph.getNodes();
	final Set<PrimNode> done = new HashSet<PrimNode>(nodes.size());
	// Set all nodes to infinity
	for (final PrimNode node : nodes)
	    node.setMinIncomingCost(Integer.MAX_VALUE);
	PrimNode currentNode = nodes.iterator().next(); // Any node
	currentNode.setMinIncomingCost(0);
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

    private Edge findEdge(final Set<PrimNode> done, final Node currentNode) {
	for (final Edge edge : currentNode.getNeigbourEdges())
	    if ((done.contains(edge.getStartNode()) || done.contains(edge.getEndNode()))
		    && edge.getCost() == ((PrimNode) currentNode).getMinIncomingCost())
		return edge;
	return null;
    }

    private void updateNeighbours(final PrimNode currentNode, final TreeSet<PrimNode> candidates,
	    final Set<PrimNode> done) {
	PrimNode neighbour;
	for (final Edge edge : currentNode.getNeigbourEdges()) {
	    neighbour = (PrimNode) (edge.getStartNode().equals(currentNode) ? edge.getEndNode() : edge.getStartNode());
	    if (!done.contains(neighbour)) {
		final int minCost = neighbour.getMinIncomingCost();
		if (edge.getCost() < minCost) {
		    if (minCost < Integer.MAX_VALUE)
			candidates.remove(neighbour);
		    neighbour.setMinIncomingCost(edge.getCost());
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
