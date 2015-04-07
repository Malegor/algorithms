package graph.maxspacingclustering;

import graph.basegraph.Graph;
import graph.basegraph.GraphReader;
import graph.maxspacingclustering.graph.Cluster;
import graph.maxspacingclustering.greedyalgorithm.GreedyClusterAlgorithm;

import java.io.IOException;
import java.util.Set;

public class RunGreedyClusterAlgorithm {
    public static void main(final String[] args) {
	final GraphReader reader = new GraphReader();
	Graph graph;
	try {
	    graph = reader.read("/home/sylvain/Documents/workspace/algorithms/test.txt");
	} catch (final IOException e) {
	    e.printStackTrace();
	    return;
	}
	final int numberOfClusters = 4;
	final GreedyClusterAlgorithm greedyAlgorithm = new GreedyClusterAlgorithm(numberOfClusters);
	final Set<Cluster> clusters = greedyAlgorithm.execute(graph);
	System.out.println(clusters);
	System.out.println(greedyAlgorithm.calculateMaxSpacing(clusters));
    }
}
