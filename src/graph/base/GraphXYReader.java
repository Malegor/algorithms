package graph.base;

import java.util.Collection;

public class GraphXYReader extends GraphReader {

    public GraphXYReader() {
	super(false);
    }

    @Override
    protected void processLine(final String[] line) {
	final Collection<Node> nodes = this.graph.getNodes();
	final long newNodeId = nodes.size() + 1;
	final NodeXY newNode = new NodeXY(newNodeId, Double.parseDouble(line[0]), Double.parseDouble(line[1]));
	this.graph.addNode(newNode);
	NodeXY otherNode;
	// Perform the next procedure only after reading the file?
	for (final Node existingNode : nodes) {
	    otherNode = (NodeXY) existingNode;
	    this.graph.addEdge(
		    otherNode.getId(),
		    newNodeId,
		    Math.sqrt(Math.pow(otherNode.getX() - newNode.getX(), 2)
			    + Math.pow(otherNode.getY() - newNode.getY(), 2)));
	}
    }

    @Override
    protected void processFirstLine(final String[] line) {
	final int numberOfNodes = Integer.parseInt(line[0]);
	this.graph = new Graph(numberOfNodes, numberOfNodes * (numberOfNodes - 1) / 2, false);
    }

}
