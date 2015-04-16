package graph.minspanningtree;

import graph.basegraph.Edge;
import graph.basegraph.Graph;
import graph.basegraph.GraphReader;
import graph.minspanningtree.primsalgorithm.PrimsAlgorithm;

import java.io.IOException;
import java.util.Set;

public class RunPrimsAlgorithm {
    public static void main(final String[] args) {
	final GraphReader reader = new GraphReader();
	try {
	    reader.read("edges.txt");
	} catch (final IOException e) {
	    e.printStackTrace();
	    return;
	}
	final Graph graph = reader.getGraph();
	final PrimsAlgorithm primsAlgorithm = new PrimsAlgorithm();
	final Set<Edge> minSpanningTree = primsAlgorithm.execute(graph);
	System.out.println(minSpanningTree);
	System.out.println(primsAlgorithm.calculateTotalCost(minSpanningTree));
    }
}
