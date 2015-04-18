package graph;

import graph.base.Edge;
import graph.base.Graph;
import graph.base.GraphReader;
import graph.minspanningtree.PrimsAlgorithm;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;

public class TestMinSpanningTree {

    @Test
    public void testExecute() {
	final GraphReader reader = new GraphReader(false);
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
