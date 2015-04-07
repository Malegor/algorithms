package graph.maxspacingclustering.graph;

import graph.basegraph.Node;

import java.util.HashSet;
import java.util.Set;

public class Cluster {
    private final Set<Node> nodes;

    public Cluster(final Node firstNode) {
	this.nodes = new HashSet<Node>(50);
	this.addNode(firstNode);
    }

    public void addNode(final Node node) {
	this.nodes.add(node);
    }

    @Override
    public int hashCode() {
	return this.nodes.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
	return this.nodes.equals(((Cluster) obj).nodes);
    }
}
