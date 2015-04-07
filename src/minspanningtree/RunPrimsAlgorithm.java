package minspanningtree;

import graph.Edge;

import java.io.IOException;
import java.util.Set;

import minspanningtree.graph.PrimGraph;
import minspanningtree.graph.PrimGraphReader;
import minspanningtree.primsalgorithm.PrimsAlgorithm;

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
