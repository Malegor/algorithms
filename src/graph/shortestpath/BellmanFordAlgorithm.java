package graph.shortestpath;

import exception.NegativeCycleInGraphException;
import graph.base.Edge;
import graph.base.Graph;
import graph.base.Node;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BellmanFordAlgorithm {
    public void execute(final Graph graph, final Node source) throws NegativeCycleInGraphException {
	final Collection<Node> allNodes = graph.getNodes();
	for (final Node node : allNodes)
	    node.setLabel(Integer.MAX_VALUE);
	source.setLabel(0);
	final Set<Node> visited = new HashSet<Node>(allNodes.size());
	visited.add(source);
	final Set<Node> toVisitNext = new HashSet<Node>(allNodes.size());
	Node neighbourNode;
	boolean isModified = false;
	boolean existUnvisitedNodes = true;
	for (int i = 0; i < allNodes.size() + 1; i++) {
	    toVisitNext.clear();
	    isModified = false;
	    for (final Node node : visited)
		for (final Edge edge : node.getNeigbourEdges()) {
		    // The next line is necessary as the graph can be undirected:
		    neighbourNode = node.equals(edge.getStartNode()) ? edge.getEndNode() : edge.getStartNode();
		    if (existUnvisitedNodes)
			toVisitNext.add(neighbourNode);
		    if (neighbourNode.getLabel() > node.getLabel() + edge.getCost()) {
			neighbourNode.setLabel(node.getLabel() + edge.getCost());
			isModified = true;
		    }
		}
	    if (!isModified)
		return;
	    existUnvisitedNodes = visited.addAll(toVisitNext);
	}
	throw new NegativeCycleInGraphException();
    }
}
