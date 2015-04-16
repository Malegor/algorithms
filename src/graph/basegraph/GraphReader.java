package graph.basegraph;

import data.AbstractReader;

public class GraphReader extends AbstractReader {

    protected Graph graph;

    private final boolean isDirected;

    public GraphReader(final boolean directedGraph) {
	this.isDirected = directedGraph;
    }

    @Override
    protected void processLine(final String[] line) {
	this.graph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
    }

    @Override
    protected void processFirstLine(final String[] line) {
	this.graph = new Graph(Integer.parseInt(line[0]), Integer.parseInt(line[1]), this.isDirected);
    }

    public Graph getGraph() {
	return this.graph;
    }
}
