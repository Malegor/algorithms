package graph.basegraph;

import java.util.HashSet;
import java.util.Set;

public class Node {
    private final long id;
    private final Set<Edge> neighbourEdges;

    public Node(final long theId) {
	this.id = theId;
	this.neighbourEdges = new HashSet<Edge>(5);
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
}
