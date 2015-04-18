package graph;

import graph.basegraph.Graph;
import graph.basegraph.GraphReader;
import graph.shortestpath.BellmanFordAlgorithm;
import graph.shortestpath.DijkstraAlgorithm;
import graph.shortestpath.ShortestPathGraphReader;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class TestShortestPath {

    // TODO: assert that both algorithms give the same result

    @Test
    public void testDijkstraAlgorithm() throws IOException {
	final Graph graph = this.readGraph();
	this.executeDijkstraAlgorithm(graph);
	this.checkResult(graph);
    }

    @Test
    public void testBellmanFordAlgorithm() throws IOException {
	final Graph graph = this.readGraph();
	this.executeBellmanFordAlgorithm(graph);
	this.checkResult(graph);
    }

    private void checkResult(final Graph graph) {
	final Integer[] targets = new Integer[] { 7, 37, 59, 82, 99, 115, 133, 165, 188, 197 };
	for (final Integer integer : targets)
	    System.out.print(graph.getNodeById(Long.valueOf(integer)).getLabel() + ",");
	System.out.println();
    }

    private Graph readGraph() throws IOException {
	final GraphReader reader = new ShortestPathGraphReader();
	reader.read("dijkstraData.txt");
	final Graph graph = reader.getGraph();
	return graph;
    }

    private void executeDijkstraAlgorithm(final Graph graph) {
	final DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
	dijkstraAlgorithm.execute(graph, graph.getNodeById(Long.valueOf(1)));
    }

    private void executeBellmanFordAlgorithm(final Graph graph) {
	final BellmanFordAlgorithm bellmanFordAlgorithm = new BellmanFordAlgorithm();
	final boolean hasSolution = bellmanFordAlgorithm.execute(graph, graph.getNodeById(Long.valueOf(1)));
	Assert.assertTrue(hasSolution);
    }
}
