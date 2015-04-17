package graph.mincostpath.dijkstra;

import graph.basegraph.Graph;
import graph.basegraph.GraphReader;

import java.io.IOException;

public class RunDijkstraAlgorithm {
    public static void main(final String[] args) {
	final GraphReader reader = new DijkstraGraphReader();
	try {
	    reader.read("dijkstraData.txt");
	} catch (final IOException e) {
	    e.printStackTrace();
	    return;
	}
	final Graph graph = reader.getGraph();
	final DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
	final Integer[] targets = new Integer[] { 7, 37, 59, 82, 99, 115, 133, 165, 188, 197 };
	dijkstraAlgorithm.execute(graph, graph.getNodeById(Long.valueOf(1)));
	for (final Integer integer : targets)
	    System.out.print(graph.getNodeById(Long.valueOf(integer)).getLabel() + ",");
	System.out.println();
    }
}
