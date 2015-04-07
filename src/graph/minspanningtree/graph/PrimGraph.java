package graph.minspanningtree.graph;


import graph.basegraph.Graph;

import java.util.Collection;


public class PrimGraph extends Graph {
    public PrimGraph(final int numberOfNodes, final int numberOfEdges) {
	super(numberOfNodes, numberOfEdges);
    }

    @Override
    protected PrimNode createNote(final long idNode) {
	return new PrimNode(idNode);
    }

    @Override
    public Collection<PrimNode> getNodes() {
	return (Collection<PrimNode>) super.getNodes();
    }
}
