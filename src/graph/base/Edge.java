package graph.base;

import java.util.HashSet;
import java.util.Set;

public class Edge implements Comparable<Edge> {
    private final Node startNode, endNode;
    private final double cost;

    public Edge(final Node start, final Node end, final double theCost) {
	this.startNode = start;
	this.endNode = end;
	this.cost = theCost;
    }

    public Node getStartNode() {
	return this.startNode;
    }

    public Node getEndNode() {
	return this.endNode;
    }

    public double getCost() {
	return this.cost;
    }

    @Override
    public String toString() {
	return this.startNode.toString() + " - " + this.endNode.toString() + " : " + this.cost;
    }

    @Override
    public int hashCode() {
	return (this.startNode.toString() + this.endNode.toString()).hashCode();// FIXME
    }

    @Override
    public boolean equals(final Object obj) {
	final Set<Node> extremities = new HashSet<Node>(2);
	extremities.add(this.startNode);
	extremities.add(this.endNode);
	final Edge other = (Edge) obj;
	return extremities.contains(other.getStartNode()) && extremities.contains(other.getEndNode());
    }

    @Override
    public int compareTo(final Edge other) {
	return Math.abs(this.cost - other.cost) < Graph.EPSILON ? 0 : this.cost < other.cost ? -1 : 1;
    }
}
