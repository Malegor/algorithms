package graph;

import java.io.IOException;

import org.junit.Test;

import exception.NegativeCycleInGraphException;
import exception.NegativeEdgeInGraphException;
import graph.base.Graph;
import graph.base.GraphReader;
import graph.shortestpath.BellmanFordAlgorithm;
import graph.shortestpath.DijkstraAlgorithm;
import graph.shortestpath.JohnsonAlgorithm;
import graph.shortestpath.ShortestPathGraphReader;

public class TestShortestPath {

    // TODO: assert that both Dijkstra and Bellman algorithms give the same result in a graph without negative edge.

    @Test
    public void runDijkstraAlgorithm() throws IOException, NegativeEdgeInGraphException {
	final Graph graph = this.readGraph("dijkstraData.txt");
	this.executeDijkstraAlgorithm(graph);
	this.checkResult(graph);
    }

    @Test
    public void runBellmanFordAlgorithm() throws IOException, NegativeCycleInGraphException {
	final Graph graph = this.readGraph("dijkstraData.txt");
	this.executeBellmanFordAlgorithm(graph);
	this.checkResult(graph);
    }

    @Test
    public void testJohnsonOnSmallGraph() throws NegativeCycleInGraphException, NegativeEdgeInGraphException {
	final Graph graph = new Graph(4, 6, true);
	graph.addEdge(1, 2, -1);
	graph.addEdge(1, 3, 1);
	graph.addEdge(2, 3, 0);
	graph.addEdge(3, 4, -1);
	graph.addEdge(2, 4, 1);
	graph.addEdge(4, 1, 3);
	final JohnsonAlgorithm johnson = new JohnsonAlgorithm();
	final long solutionValue = johnson.execute(graph);
	System.out.println(solutionValue);
    }

    @Test
    public void runJohnsonAlgorithm() throws IOException, NegativeEdgeInGraphException {
	this.runJonhsonOnFile("g1.txt");
	this.runJonhsonOnFile("g2.txt");
	this.runJonhsonOnFile("g3.txt");
    }

    private void runJonhsonOnFile(final String fileName) throws IOException, NegativeEdgeInGraphException {
	final GraphReader reader = new GraphReader(true);
	reader.read(fileName);
	final JohnsonAlgorithm johnson = new JohnsonAlgorithm();
	long solutionValue;
	try {
	    solutionValue = johnson.execute(reader.getGraph());
	    System.out.println(fileName + " : " + solutionValue);
	} catch (final NegativeCycleInGraphException e) {
	    System.out.println(fileName + " : No solution");
	}
    }

    private void checkResult(final Graph graph) {
	final Integer[] targets = new Integer[] { 7, 37, 59, 82, 99, 115, 133, 165, 188, 197 };
	for (final Integer integer : targets)
	    System.out.print(graph.getNodeById(Long.valueOf(integer)).getLabel() + ",");
	System.out.println();
    }

    private Graph readGraph(final String fileName) throws IOException {
	final GraphReader reader = new ShortestPathGraphReader();
	reader.read(fileName);
	return reader.getGraph();
    }

    private void executeDijkstraAlgorithm(final Graph graph) throws NegativeEdgeInGraphException {
	final DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
	dijkstraAlgorithm.execute(graph, graph.getNodeById(Long.valueOf(1)));
    }

    private void executeBellmanFordAlgorithm(final Graph graph) throws NegativeCycleInGraphException {
	final BellmanFordAlgorithm bellmanFordAlgorithm = new BellmanFordAlgorithm();
	bellmanFordAlgorithm.execute(graph, graph.getNodeById(Long.valueOf(1)));
    }
}
