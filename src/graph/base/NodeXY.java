package graph.base;

public class NodeXY extends Node {
    private final double x, y;

    public NodeXY(final long theId, final double theX, final double theY) {
	super(theId);
	this.x = theX;
	this.y = theY;
    }

    public double getX() {
	return this.x;
    }

    public double getY() {
	return this.y;
    }

}
