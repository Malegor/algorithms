package graph;

import graph.basegraph.Graph;
import graph.basegraph.GraphReader;
import graph.maxspacingclustering.GreedyClusterAlgorithm;
import graph.maxspacingclustering.graph.UnionFindClusters;
import graph.maxspacingclustering.hamming.graph.HammingGraphReader;
import graph.maxspacingclustering.hamming.preprocessing.ComputeSmallestHammingEdges;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

public class TestMaxSpacingClustering {

    @Test
    public void testGreedyAlgorithm() {
	final GraphReader reader = new GraphReader(false);
	try {
	    reader.read("clustering1.txt");
	} catch (final IOException e) {
	    e.printStackTrace();
	    return;
	}
	final Graph graph = reader.getGraph();
	final Integer numberOfClusters = Integer.valueOf(4);
	final GreedyClusterAlgorithm greedyAlgorithm = new GreedyClusterAlgorithm(numberOfClusters, -1);
	final UnionFindClusters clusters = greedyAlgorithm.execute(graph);
	System.out.println(clusters);
	System.out.println(greedyAlgorithm.getMaxSpacing());
    }

    @Ignore
    // FIXME
    @Test
    public void testHammingDistanceInstance() {
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
