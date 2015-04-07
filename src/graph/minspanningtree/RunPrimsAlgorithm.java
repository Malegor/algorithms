package graph.minspanningtree;


import graph.basegraph.Edge;
import graph.minspanningtree.graph.PrimGraph;
import graph.minspanningtree.graph.PrimGraphReader;
import graph.minspanningtree.primsalgorithm.PrimsAlgorithm;

import java.io.IOException;
import java.util.Set;



public class RunPrimsAlgorithm {
    public static void main(final String[] args) {
	final PrimGraphReader reader = new PrimGraphReader();
	PrimGraph graph;
	try {
	    graph = (PrimGraph) reader.read("/home/sylvain/Documents/workspace/algorithms/edges.txt");
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
