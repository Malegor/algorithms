package graph.maxspacingclustering.graph;

import graph.basegraph.Node;

import java.util.HashSet;
import java.util.Set;

public class Cluster {
    private final Node leader;
    private final Set<Node> nodes;

    public Cluster(final Node firstNode) {
	this.nodes = new HashSet<Node>(50);
	this.addNode(firstNode);
	this.leader = firstNode;
    }

    public void addNode(final Node node) {
	this.nodes.add(node);
    }

    @Override
    public int hashCode() {
	return this.leader.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
	return this.leader.equals(((Cluster) obj).leader);
    }

    public boolean containsNode(final Node node) {
	return this.nodes.contains(node);
    }

    /**
     * @param other
     */
    public void mergeWith(final Cluster other) {
	this.nodes.addAll(other.nodes);
    }

    @Override
    public String toString() {
	return this.nodes.toString();
    }
}
