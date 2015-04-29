package graph.tsp;

import graph.base.Node;

import java.util.Set;

public class PartialPath {
    private final Set<Node> nodes;

    private final Node lastNode;

    public PartialPath(final Set<Node> theNodes, final Node finalNode) {
	this.nodes = theNodes;
	this.lastNode = finalNode;
    }

    public boolean containsNode(final Node node) {
	return this.nodes.contains(node);
    }

    public Set<Node> getNodes() {
	return this.nodes;
    }

    public Node getLastNode() {
	return this.lastNode;
    }

    public int getSize() {
	return this.nodes.size();
    }

    @Override
    public int hashCode() {
	return this.nodes.toString().hashCode() + this.lastNode.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
	final PartialPath otherPath = (PartialPath) obj;
	return this.nodes.equals(otherPath.getNodes()) && this.lastNode.equals(otherPath.getLastNode());
    }

    @Override
    public String toString() {
	return this.nodes.toString() + " - " + this.lastNode.toString();
    }
}
