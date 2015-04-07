package graph.minspanningtree.primgraph;

import graph.basegraph.GraphReader;

public class PrimGraphReader extends GraphReader {

    @Override
    protected PrimGraph createGraph(final int numberOfNodes, final int numberOfEdges) {
	return new PrimGraph(numberOfNodes, numberOfEdges);
    }
}
