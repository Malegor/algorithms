package graph.maxspacingclustering.hamming.graph;

import graph.basegraph.Graph;
import graph.basegraph.GraphReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HammingGraphReader extends GraphReader {

    private int currentNodeId;

    public HammingGraphReader() {
	this.currentNodeId = 1;
    }

    @Override
    protected void processLine(final String[] line) {
	final List<String> listOfNode = Arrays.asList(line);
	final List<Integer> nodeBits = new ArrayList<Integer>(listOfNode.size());
	for (final String string : listOfNode)
	    nodeBits.add(Integer.parseInt(string));
	this.graph.addNode(new HammingNode(this.currentNodeId++, nodeBits));
    }

    @Override
    protected void processFirstLine(final String[] line) {
	// The second argument is not used as the number of edges but the number
	// of bits.
	this.graph = new Graph(Integer.parseInt(line[0]), Integer.parseInt(line[0]));
    }

}
