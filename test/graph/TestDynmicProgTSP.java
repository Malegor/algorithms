package graph;

import graph.base.Graph;
import graph.base.GraphReader;
import graph.base.GraphXYReader;
import graph.base.NodeXY;
import graph.tsp.DynamicProgTSPAlgorithm;

import java.io.IOException;

import org.junit.Test;

public class TestDynmicProgTSP {

    @Test
    public void testSmallGraph() throws IOException {
	final Graph graph = new Graph(4, 0, false);
	graph.addNode(new NodeXY(1, 1, 1));
	graph.addNode(new NodeXY(2, 1, 2));
	graph.addNode(new NodeXY(3, 1, 3));
	graph.addNode(new NodeXY(4, 1, 4));
	graph.addNode(new NodeXY(5, 2, 1));
	graph.addNode(new NodeXY(6, 3, 1));
	graph.addNode(new NodeXY(7, 4, 1));
	graph.addNode(new NodeXY(8, 5, 1));
	graph.addNode(new NodeXY(9, 6, 1));
	graph.addNode(new NodeXY(10, 7, 1));
	graph.addNode(new NodeXY(11, 8, 1));
	graph.addNode(new NodeXY(12, 9, 1));
	graph.addNode(new NodeXY(13, 10, 1));
	graph.addNode(new NodeXY(14, 11, 1));
	graph.addNode(new NodeXY(15, 12, 1));
	final DynamicProgTSPAlgorithm algorithm = new DynamicProgTSPAlgorithm();
	final double result = algorithm.execute(graph);
	System.out.println(result);
    }

    @Test
    public void testBigGraph() throws IOException {
	final GraphReader reader = new GraphXYReader();
	reader.read("tsp.txt");
	final Graph graph = reader.getGraph();
	final DynamicProgTSPAlgorithm algorithm = new DynamicProgTSPAlgorithm();
	final double result = algorithm.execute(graph);
	System.out.println(result);
    }
}
