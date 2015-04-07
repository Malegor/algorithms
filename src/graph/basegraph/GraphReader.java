package graph.basegraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphReader {
    public Graph read(final String fileName) throws IOException {
	final String space = " ";
	final BufferedReader reader = new BufferedReader(new FileReader(fileName));
	String line = reader.readLine();
	final String[] graphSize = line.split(space);
	final Graph graph = this.createGraph(Integer.parseInt(graphSize[0]), Integer.parseInt(graphSize[1]));
	while ((line = reader.readLine()) != null) {
	    // use space as separator
	    final String[] edgeData = line.split(space);
	    graph.addEdge(Integer.parseInt(edgeData[0]), Integer.parseInt(edgeData[1]), Integer.parseInt(edgeData[2]));
	}
	reader.close();
	return graph;
    }

    protected Graph createGraph(final int numberOfNodes, final int numberOfEdges) {
	return new Graph(numberOfNodes, numberOfEdges);
    }
}
