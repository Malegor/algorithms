package graph.basegraph;

import data.AbstractReader;

public class GraphReader extends AbstractReader {

    private Graph graph;

    @Override
    protected void processLine(final String[] line) {
	this.graph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
    }

    @Override
    protected void processFirstLine(final String[] line) {
	this.graph = this.createGraph(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
    }

    protected Graph createGraph(final int numberOfNodes, final int numberOfEdges) {
	return new Graph(numberOfNodes, numberOfEdges);
    }

    public Graph getGraph() {
	return this.graph;
    }
}
