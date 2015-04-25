package graph.maxspacingclustering;

import graph.base.Edge;
import graph.base.Graph;
import graph.base.Node;
import graph.maxspacingclustering.graph.UnionFindClusters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GreedyClusterAlgorithm {

    private final Integer targetNumberOfClusters;
    private double maxSpacing;

    public GreedyClusterAlgorithm(final Integer targetNumberOfClusters, final Integer targetSpacing) {
	this.targetNumberOfClusters = targetNumberOfClusters;
	this.maxSpacing = targetSpacing;
    }

    public UnionFindClusters execute(final Graph graph) {
	final UnionFindClusters clusters = new UnionFindClusters(graph.getNodes());
	final List<Edge> edges = new ArrayList<Edge>(graph.getEdges());
	Collections.sort(edges);
	final Iterator<Edge> iterator = edges.iterator();
	Edge edge = new Edge(null, null, Integer.MIN_VALUE);
	Node startNode, endNode;
	while (iterator.hasNext() && !this.shouldAlgorithmStop(clusters, edge)) {
	    edge = iterator.next();
	    startNode = edge.getStartNode();
	    endNode = edge.getEndNode();
	    if (!clusters.find(startNode).equals(clusters.find(endNode)))
		clusters.union(startNode, endNode);
	}
	boolean sameCluster = true;
	while (sameCluster && iterator.hasNext()) {
	    edge = iterator.next();
	    startNode = edge.getStartNode();
	    endNode = edge.getEndNode();
	    sameCluster = clusters.find(startNode).equals(clusters.find(endNode));
	}
	this.maxSpacing = edge.getCost();
	System.out.println(edge);
	return clusters;
    }

    private boolean shouldAlgorithmStop(final UnionFindClusters clusters, final Edge currentEdge) {
	if (this.targetNumberOfClusters != null)
	    return this.targetNumberOfClusters.intValue() >= clusters.getNumberOfClusters();
	return this.maxSpacing < currentEdge.getCost();
    }

    public double getMaxSpacing() {
	return this.maxSpacing;
    }
}
