package graph.shortestpath;

import exception.NegativeCycleInGraphException;
import exception.NegativeEdgeInGraphException;
import graph.base.Edge;
import graph.base.Graph;
import graph.base.Node;

import java.util.HashSet;
import java.util.Set;

public class JohnsonAlgorithm {

    public long execute(final Graph graph) throws NegativeCycleInGraphException, NegativeEdgeInGraphException {
	final Graph graphWithoutNegativeEdge = this.updateEdgesCosts(graph);
	final DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
	long bestValue = Long.MAX_VALUE, currentValue;
	for (final Node source : graphWithoutNegativeEdge.getNodes()) {
	    dijkstra.execute(graphWithoutNegativeEdge, source);
	    for (final Node target : graphWithoutNegativeEdge.getNodes()) {
		// Update the value with costs in the original graph
		currentValue = target.getLabel() - graph.getNodeById(source.getId()).getLabel()
			+ graph.getNodeById(target.getId()).getLabel();
		if (currentValue < bestValue)
		    bestValue = currentValue;
	    }
	}
	// If needed, return the source and target nodes as well
	return bestValue;
    }

    private Graph updateEdgesCosts(final Graph graph) throws NegativeCycleInGraphException {
	final Set<Node> allNodes = new HashSet<Node>(graph.getNodes());
	// TODO: case where this ID is already taken by some node
	final long idNotUsedInGraph = allNodes.size() + 1;
	for (final Node node : allNodes)
	    // Add fake edge to be able to apply Bellman-Ford algorithm
	    graph.addEdge(idNotUsedInGraph, node.getId(), 0);
	final Node fakeSource = graph.getNodeById(idNotUsedInGraph);
	final BellmanFordAlgorithm bfAlgorithm = new BellmanFordAlgorithm();
	bfAlgorithm.execute(graph, fakeSource);
	final Graph updated = new Graph(allNodes.size(), graph.getEdges().size(), graph.isDirected());
	long idNode1, idNode2;
	int cost;
	for (final Edge edge : graph.getEdges()) {
	    idNode1 = edge.getStartNode().getId();
	    idNode2 = edge.getEndNode().getId();
	    // TODO: define cost as long as well?
	    cost = (int) (edge.getCost() + edge.getStartNode().getLabel() - edge.getEndNode().getLabel());
	    if (idNode1 != fakeSource.getId() && idNode2 != fakeSource.getId())
		updated.addEdge(idNode1, idNode2, cost);
	}
	return updated;
    }
}
