package graph.maxspacingclustering;

import graph.basegraph.Graph;
import graph.basegraph.GraphReader;
import graph.maxspacingclustering.graph.UnionFindClusters;
import graph.maxspacingclustering.greedyalgorithm.GreedyClusterAlgorithm;

import java.io.IOException;

public class RunGreedyClusterAlgorithm {
    public static void main(final String[] args) {
	final GraphReader reader = new GraphReader();
	try {
	    reader.read("/home/sylvain/Documents/workspace/algorithms/clustering1.txt");
	} catch (final IOException e) {
	    e.printStackTrace();
	    return;
	}
	final Graph graph = reader.getGraph();
	final Integer numberOfClusters = Integer.valueOf(4);
	final GreedyClusterAlgorithm greedyAlgorithm = new GreedyClusterAlgorithm(numberOfClusters, null);
	final UnionFindClusters clusters = greedyAlgorithm.execute(graph);
	System.out.println(clusters);
	System.out.println(greedyAlgorithm.getMaxSpacing());
    }
}
