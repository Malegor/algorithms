package graph.shortestpath;

import graph.basegraph.Graph;
import graph.basegraph.GraphReader;

public class ShortestPathGraphReader extends GraphReader {

    public ShortestPathGraphReader() {
	super(false);
    }

    @Override
    protected void processLine(final String[] line) {
	if (line.length < 2)
	    return;
	final long idStartNode = Long.parseLong(line[0]);
	String[] newEdgeLine;
	for (int i = 1; i < line.length; i++) {
	    newEdgeLine = line[i].split(",");
	    this.graph.addEdge(idStartNode, Long.parseLong(newEdgeLine[0]), Integer.parseInt(newEdgeLine[1]));
	}
    }

    @Override
    protected void processFirstLine(final String[] line) {
	this.graph = new Graph(200, 1000, false); // TODO improve
	this.processLine(line);
    }

    @Override
    protected String getSeparator() {
	return "\t";
    }

}
