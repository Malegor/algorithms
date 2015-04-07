package graph.maxspacingclustering.greedyalgorithm;

import graph.basegraph.Edge;
import graph.basegraph.Graph;
import graph.basegraph.Node;
import graph.maxspacingclustering.graph.Cluster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GreedyClusterAlgorithm {

    private final int numberOfClusters;

    public GreedyClusterAlgorithm(final int numberOfClusters) {
	this.numberOfClusters = numberOfClusters;
    }

    public Set<Cluster> execute(final Graph graph) {
	final Set<Cluster> clusters = new HashSet<Cluster>(graph.getNodes().size());
	for (final Node node : graph.getNodes())
	    clusters.add(new Cluster(node));
	final List<Edge> edges = new ArrayList<Edge>(graph.getEdges());
	Collections.sort(edges);
	// TODO
	return clusters;
    }

    public int calculateMaxSpacing(final Set<Cluster> clusters) {
	// TODO Auto-generated method stub
	return 0;
    }

}
