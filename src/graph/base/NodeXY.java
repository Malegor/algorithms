package graph.base;

import java.util.HashMap;
import java.util.Map;

public class NodeXY extends Node {
    private final double x, y;
    private final Map<Node, Double> euclidianDistance;

    public NodeXY(final long theId, final double theX, final double theY) {
	super(theId);
	this.x = theX;
	this.y = theY;
	this.euclidianDistance = new HashMap<Node, Double>(25);
    }

    public double getX() {
	return this.x;
    }

    public double getY() {
	return this.y;
    }

    public double getEuclidianDistance(final NodeXY otherNode) {
	if (!this.euclidianDistance.containsKey(otherNode))
	    this.euclidianDistance.put(otherNode, Math.sqrt(Math.pow(this.getX() - otherNode.getX(), 2)
		    + Math.pow(this.getY() - otherNode.getY(), 2)));
	return this.euclidianDistance.get(otherNode);
    }

}
