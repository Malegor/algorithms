package graph.base;

import java.util.HashSet;
import java.util.Set;

public class Node implements Comparable<Node> {
    private final long id;
    private final Set<Edge> neighbourEdges;
    private long label;

    public Node(final long theId) {
	this.id = theId;
	this.neighbourEdges = new HashSet<Edge>(5);
	this.label = Integer.MAX_VALUE;
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

    public long getLabel() {
	return this.label;
    }

    public void setLabel(final long newLabel) {
	this.label = newLabel;
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
	final long diff = this.label - other.label;
	return diff < 0 ? -1 : diff > 0 ? 1 : Long.valueOf(this.id).compareTo(Long.valueOf(other.id));
    }
}
