package graph.tsp;

import graph.base.Graph;
import graph.base.Node;
import graph.base.NodeXY;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DynamicProgTSPAlgorithm {

    private Node firstNode;

    public double execute(final Graph graph) {
	final Collection<Node> nodes = new HashSet<Node>(graph.getNodes());
	final int numberOfNodes = nodes.size();
	final Map<PartialPath, Double> bestValueNext = new HashMap<PartialPath, Double>(numberOfNodes * numberOfNodes);
	final Map<PartialPath, Double> bestValue = new HashMap<PartialPath, Double>(numberOfNodes * numberOfNodes);
	final Set<Node> firstSet = new HashSet<Node>(1);
	this.firstNode = graph.getNodeById(Long.valueOf(1));
	firstSet.add(this.firstNode);
	nodes.remove(this.firstNode);
	final PartialPath firstPartialPath = new PartialPath(firstSet, this.firstNode);
	bestValue.put(firstPartialPath, Double.valueOf(0));
	PartialPath newPPwithSameLastNode, newPPwithOtherLastNode;
	Set<Node> pathNodes;
	for (int m = 1; m < numberOfNodes; m++) {
	    System.out.println("m=" + m);
	    for (final PartialPath partialPath : bestValue.keySet())
		for (final Node newNode : nodes)
		    if (!partialPath.containsNode(newNode)) {
			// Create two new partial paths
			pathNodes = new HashSet<Node>(partialPath.getNodes());
			pathNodes.add(newNode);
			newPPwithSameLastNode = new PartialPath(pathNodes, partialPath.getLastNode());
			newPPwithOtherLastNode = new PartialPath(pathNodes, newNode);
			// Calculate their value
			if (!bestValueNext.containsKey(newPPwithSameLastNode)
				&& !this.firstNode.equals(partialPath.getLastNode()))
			    bestValueNext.put(newPPwithSameLastNode,
				    this.calculateValue(newPPwithSameLastNode, bestValue));
			if (!bestValueNext.containsKey(newPPwithOtherLastNode))
			    bestValueNext.put(newPPwithOtherLastNode,
				    this.calculateValue(newPPwithOtherLastNode, bestValue));
		    }
	    // The values to use in the next iteration are the ones we just computed.
	    bestValue.clear();
	    bestValue.putAll(bestValueNext);
	    bestValueNext.clear();
	}
	double currentValue, minValue = Double.MAX_VALUE;
	for (final PartialPath partialPath : bestValue.keySet()) {
	    currentValue = bestValue.get(partialPath)
		    + this.getCost((NodeXY) partialPath.getLastNode(), (NodeXY) this.firstNode);
	    if (minValue > currentValue)
		minValue = currentValue;
	}
	return minValue;
    }

    private Double calculateValue(final PartialPath partialPath, final Map<PartialPath, Double> bestValue) {
	final Node lastNode = partialPath.getLastNode();
	double currentValue, minValue = Double.MAX_VALUE;
	PartialPath path;
	final Set<Node> previousNodes = new HashSet<Node>(partialPath.getNodes());
	previousNodes.remove(lastNode);
	final Set<Node> newNodes = new HashSet<Node>(previousNodes);
	if (partialPath.getSize() > 2)
	    previousNodes.remove(this.firstNode);
	for (final Node previousLastNode : previousNodes) {
	    path = new PartialPath(newNodes, previousLastNode);
	    currentValue = bestValue.get(path).doubleValue()
		    + this.getCost((NodeXY) previousLastNode, (NodeXY) lastNode);
	    if (currentValue < minValue)
		minValue = currentValue;
	}
	return Double.valueOf(minValue);
    }

    private double getCost(final NodeXY node1, final NodeXY node2) {
	return node1.compareTo(node2) < 0 ? node1.getEuclidianDistance(node2) : node2.getEuclidianDistance(node1);
    }
}
