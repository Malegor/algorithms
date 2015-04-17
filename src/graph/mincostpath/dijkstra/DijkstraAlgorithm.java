package graph.mincostpath.dijkstra;

import graph.basegraph.Edge;
import graph.basegraph.Graph;
import graph.basegraph.Node;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkstraAlgorithm {
    public void execute(final Graph graph, final Node source) {
	final Collection<Node> allNodes = graph.getNodes();
	final PriorityQueue<Node> candidates = new PriorityQueue<Node>(allNodes.size());
	final Set<Node> visited = new HashSet<Node>(allNodes.size());
	for (final Node node : allNodes)
	    node.setLabel(Integer.MAX_VALUE);
	source.setLabel(0);
	candidates.add(source);
	Node bestCandidate, neighbour;
	while (!candidates.isEmpty()) {
	    bestCandidate = candidates.remove();
	    visited.add(bestCandidate);
	    // Update his neighbours
	    for (final Edge edge : bestCandidate.getNeigbourEdges()) {
		neighbour = edge.getEndNode();
		if (!visited.contains(neighbour) && bestCandidate.getLabel() + edge.getCost() < neighbour.getLabel()) {
		    if (candidates.contains(neighbour))
			candidates.remove(neighbour);
		    neighbour.setLabel(bestCandidate.getLabel() + edge.getCost());
		    candidates.add(neighbour);
		}
	    }
	}
	if (visited.size() < allNodes.size())
	    System.out.println("Disconnected graph!");// TODO: do something?
    }

    public List<Edge> computeShortestPathTo(final Node target) {
	return null;// TODO?
    }
}
