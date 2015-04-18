package graph.maxspacingclustering.hamming.graph;

import graph.base.Node;

import java.util.List;

public class HammingNode extends Node {

    private final List<Integer> nodeBits;

    public HammingNode(final long theId, final List<Integer> bits) {
	super(theId);
	this.nodeBits = bits;
    }

    public int getBitSum() {
	int result = 0;
	for (final Integer bit : this.nodeBits)
	    result += bit.intValue();
	return result;
    }

    public List<Integer> getNodeBits() {
	return this.nodeBits;
    }
}
