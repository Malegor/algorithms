package graph.maxspacingclustering.hamming.preprocessing;

import graph.basegraph.Graph;
import graph.basegraph.Node;
import graph.maxspacingclustering.hamming.graph.HammingNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ComputeSmallestHammingEdges {

    // TODO calculate in function of the data.
    private final int NUMBER_OF_BITS = 24;
    private final Graph graph;
    private final int maxSpacing;

    public ComputeSmallestHammingEdges(final Graph theGraph, final int spacing) {
	this.graph = theGraph;
	this.maxSpacing = spacing;
    }

    /**
     * Add to the graph all the edges such that the cost is lower than
     * maxSpacing.
     */
    public void execute() {
	final Collection<? extends Node> nodes = this.graph.getNodes();
	// Compute the sum over all the bits for all the nodes, and classify
	// them according to the result.

	final List<Set<HammingNode>> nodesBySum = new ArrayList<Set<HammingNode>>(this.NUMBER_OF_BITS + 1);
	for (int i = 0; i < this.NUMBER_OF_BITS + 1; i++)
	    nodesBySum.add(new HashSet<HammingNode>(20000));
	int sum;
	for (final Node hammingNode : nodes) {
	    sum = ((HammingNode) hammingNode).getBitSum();
	    nodesBySum.get(sum).add((HammingNode) hammingNode);
	}
	// The possible edges are necessarily within maxSpacing in difference.
	int costIJ;
	for (int i = 0; i < this.NUMBER_OF_BITS + 1; i++) {
	    for (final HammingNode nodeI : nodesBySum.get(i))
		for (int j = i; j < this.NUMBER_OF_BITS + 1 && j < i + this.maxSpacing; j++)
		    for (final HammingNode nodeJ : nodesBySum.get(j))
			if (!nodeJ.equals(nodeI)) {
			    costIJ = this.calculateCost(nodeI, nodeJ);
			    if (costIJ <= this.maxSpacing)
				this.graph.addEdge(nodeI.getId(), nodeJ.getId(), costIJ);
			}
	    System.out.println("Done i=" + i);
	}
    }

    /**
     * @param nodeI
     * @param nodeJ
     * @return O custo do arco entre os dois nos, um valor alto se for superior
     *         ao maxSpacing
     */
    private int calculateCost(final HammingNode nodeI, final HammingNode nodeJ) {
	int result = 0;
	final List<Integer> nodeBitsI = nodeI.getNodeBits();
	final List<Integer> nodeBitsJ = nodeJ.getNodeBits();
	int diff;
	for (int i = 0; i < this.NUMBER_OF_BITS; i++) {
	    diff = Math.abs(nodeBitsI.get(i).intValue() - nodeBitsJ.get(i).intValue());
	    result += diff;
	    if (result > this.maxSpacing)
		return this.maxSpacing + 1;
	}
	return result;
    }
}
