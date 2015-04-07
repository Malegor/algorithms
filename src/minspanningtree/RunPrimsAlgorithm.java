package minspanningtree;

import graph.Edge;
import graph.Graph;
import graph.GraphReader;

import java.io.IOException;
import java.util.Set;


import minspanningtree.primsalgorithm.PrimsAlgorithm;

public class RunPrimsAlgorithm {
    public static void main(final String[] args) {
	final GraphReader reader = new GraphReader();
	Graph graph;
	try {
	    graph = reader.read("/home/sylvain/Documents/workspace/edges.txt");
	} catch (final IOException e) {
	    e.printStackTrace();
	    return;
	}
	final PrimsAlgorithm primsAlgorithm = new PrimsAlgorithm();
	final Set<Edge> minSpanningTree = primsAlgorithm.execute(graph);
	System.out.println(minSpanningTree);
	System.out.println(primsAlgorithm.calculateTotalCost(minSpanningTree));
    }
}
