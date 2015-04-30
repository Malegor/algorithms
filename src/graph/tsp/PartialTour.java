package graph.tsp;

import graph.base.NodeXY;

import java.util.ArrayList;
import java.util.List;

public class PartialTour {
    private final List<NodeXY> nodes;
    private double tourLength;

    public PartialTour(final int expectedNumberOfNodes, final NodeXY firstNode) {
	this.nodes = new ArrayList<NodeXY>(expectedNumberOfNodes);
	this.nodes.add(firstNode);
	this.tourLength = 0;
    }

    public void addNode(final NodeXY newNode, final int index) {
	this.tourLength += this.getTourLengthIncrease(newNode, index);
	this.nodes.add(index, newNode);
    }

    public NodeXY removeNode(final int index) {
	final NodeXY nodeToRemove = this.nodes.get(index);
	this.nodes.remove(index);
	this.tourLength -= this.getTourLengthIncrease(nodeToRemove, index);
	return nodeToRemove;
    }

    private double getTourLengthIncrease(final NodeXY newNode, final int index) {
	final NodeXY previous = this.nodes.get(this.getIndexInsideRange(index - 1));
	final NodeXY next = this.nodes.get(this.getIndexInsideRange(index));
	return previous.getEuclidianDistance(newNode) + next.getEuclidianDistance(newNode)
		- previous.getEuclidianDistance(next);
    }

    private int getIndexInsideRange(final int index) {
	final int size = this.nodes.size();
	return (index % size + size) % size;
    }

    public int findBestIndexToAdd(final NodeXY node) {
	double currentIncrease, minTourLengthIncrease = Double.MAX_VALUE;
	int bestIndex = 0;
	for (int i = 0; i < this.nodes.size(); i++) {
	    currentIncrease = this.getTourLengthIncrease(node, i);
	    if (currentIncrease < minTourLengthIncrease) {
		minTourLengthIncrease = currentIncrease;
		bestIndex = i;
	    }
	}
	return bestIndex == 0 ? this.nodes.size() : bestIndex;
    }

    public double getTourLength() {
	return this.tourLength;
    }

    public int size() {
	return this.nodes.size();
    }

    @Override
    public String toString() {
	return this.nodes.toString();
    }
}
