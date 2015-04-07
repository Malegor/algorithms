package minspanningtree.graph;

import graph.Node;

public class PrimNode extends Node {

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
    public int compareTo(final Node other) {
	final PrimNode otherPrimNode = (PrimNode) other;
	return this.minIncomingCost < otherPrimNode.getMinIncomingCost() ? -1 : this.minIncomingCost > otherPrimNode
		.getMinIncomingCost() ? 1 : this.getId() < other.getId() ? -1 : this.getId() > other.getId() ? 1 : 0;
    }

}
