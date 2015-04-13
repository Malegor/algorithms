package graph.maxspacingclustering.hamming;

import graph.basegraph.Graph;
import graph.basegraph.GraphReader;
import graph.maxspacingclustering.graph.UnionFindClusters;
import graph.maxspacingclustering.greedyalgorithm.GreedyClusterAlgorithm;
import graph.maxspacingclustering.hamming.graph.HammingGraphReader;
import graph.maxspacingclustering.hamming.preprocessing.ComputeSmallestHammingEdges;

import java.io.IOException;

public class RunHammingClusterAlgorithm {
    public static void main(final String[] args) {
	final GraphReader reader = new HammingGraphReader();
	try {
	    reader.read("clustering_big.txt");
	} catch (final IOException e) {
	    e.printStackTrace();
	    return;
	}
	final Graph graph = reader.getGraph();
	final int maxSpacing = 2;
	new ComputeSmallestHammingEdges(graph, maxSpacing).execute();
	final GreedyClusterAlgorithm greedyAlgorithm = new GreedyClusterAlgorithm(null, maxSpacing);
	final UnionFindClusters clusters = greedyAlgorithm.execute(graph);
	// System.out.println(clusters);
	System.out.println("Max spacing : " + greedyAlgorithm.getMaxSpacing());
	System.out.println("Number of clusters : " + clusters.getNumberOfClusters());
    }
}
