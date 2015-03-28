package minspanningtree.graph;

import java.util.HashSet;
import java.util.Set;

public class Node implements Comparable<Node> {
    private final long id;
    private final Set<Edge> neighbourEdges;
    private int minIncomingCost;

    public Node(final long theId) {
	this.id = theId;
	this.neighbourEdges = new HashSet<Edge>(5);
	this.setMinIncomingCost(Integer.MAX_VALUE);
    }

    public void addNeighbourEdge(final Edge edge) {
	this.neighbourEdges.add(edge);
    }

    public Set<Edge> getNeigbourEdges() {
	return this.neighbourEdges;
    }

    public long getId() {
	return this.id;
    }

    public int getMinIncomingCost() {
	return this.minIncomingCost;
    }

    public void setMinIncomingCost(final int minIncomingCost) {
	this.minIncomingCost = minIncomingCost;
    }

    @Override
    public int hashCode() {
	return Long.valueOf(this.id).hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
	return this == obj || this.id == ((Node) obj).getId();
    }

    @Override
    public String toString() {
	return String.valueOf(this.id);
    }

    @Override
    public int compareTo(final Node other) {
	return this.minIncomingCost < other.getMinIncomingCost() ? -1 : this.minIncomingCost > other
		.getMinIncomingCost() ? 1 : this.id < other.getId() ? -1 : this.id > other.getId() ? 1 : 0;
    }
}
