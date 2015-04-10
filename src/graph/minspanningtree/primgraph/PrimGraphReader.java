package graph.minspanningtree.primgraph;

import graph.basegraph.GraphReader;

public class PrimGraphReader extends GraphReader {

    @Override
    protected void processFirstLine(final String[] line) {
	this.graph = new PrimGraph(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
    }

    @Override
    public PrimGraph getGraph() {
	return (PrimGraph) super.getGraph();
    }

}
