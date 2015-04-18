package graph.shortestpath;

import graph.basegraph.Edge;
import graph.basegraph.Graph;
import graph.basegraph.Node;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BellmanFordAlgorithm {

    /**
     * @param graph
     * @param source
     * @return True if the algorithm found an optimal solution.
     * False if the graph has a negative cycle.
     */
    public boolean execute(final Graph graph, final Node source) {
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
		return true;
	    existUnvisitedNodes = visited.addAll(toVisitNext);
	}
	return !isModified;// in fact it can't possibly be false here.
    }
}
