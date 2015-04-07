package minspanningtree.comparator;

import graph.Node;

import java.util.Comparator;


public class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(final Node o1, final Node o2) {
	return o1.compareTo(o2);
    }
}
