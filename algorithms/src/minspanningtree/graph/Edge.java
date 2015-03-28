package minspanningtree.graph;

import java.util.HashSet;
import java.util.Set;

public class Edge {
	private final Node startNode, endNode;
	private final int cost;

	public Edge(final Node start, final Node end, final int theCost) {
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

	public int getCost() {
		return this.cost;
	}

	@Override
	public String toString() {
		return this.startNode.toString() + " - " + this.endNode.toString()
				+ " : " + this.cost;
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
		return extremities.contains(other.getStartNode())
				&& extremities.contains(other.getEndNode());
	}
}
