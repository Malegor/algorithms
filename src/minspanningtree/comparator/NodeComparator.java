package minspanningtree.comparator;

import java.util.Comparator;

import minspanningtree.graph.Node;

public class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(final Node o1, final Node o2) {
	return o1.compareTo(o2);
    }
}
