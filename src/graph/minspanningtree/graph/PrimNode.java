package graph.minspanningtree.graph;

import graph.basegraph.Node;

public class PrimNode extends Node implements Comparable<PrimNode> {

    private int minIncomingCost;

    public PrimNode(final long theId) {
	super(theId);
	this.setMinIncomingCost(Integer.MAX_VALUE);
    }

    public int getMinIncomingCost() {
	return this.minIncomingCost;
    }

    public void setMinIncomingCost(final int minIncomingCost) {
	this.minIncomingCost = minIncomingCost;
    }

    @Override
    public int compareTo(final PrimNode other) {
	return this.minIncomingCost < other.getMinIncomingCost() ? -1 : this.minIncomingCost > other
		.getMinIncomingCost() ? 1 : this.getId() < other.getId() ? -1 : this.getId() > other.getId() ? 1 : 0;
    }

}
