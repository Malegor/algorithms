package graph.maxspacingclustering.graph;

import graph.base.Node;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UnionFindClusters {
    // TODO: use array and real union-find data structure
    private final Set<Cluster> clusters;

    public UnionFindClusters(final Collection<? extends Node> nodes) {
	this.clusters = new HashSet<Cluster>(nodes.size());
	for (final Node node : nodes)
	    this.clusters.add(new Cluster(node));
    }

    /**
     * @return The number of clusters
     */
    public int getNumberOfClusters() {
	return this.clusters.size();
    }

    /**
     * @param node
     * @return The cluster to which the informed node belongs
     */
    public Cluster find(final Node node) {
	return this.find(node, false);
    }

    private Cluster find(final Node node, final boolean removeCluster) {
	Cluster cluster;
	for (final Iterator<Cluster> iterator = this.clusters.iterator(); iterator.hasNext();) {
	    cluster = iterator.next();
	    if (cluster.containsNode(node)) {
		if (removeCluster)
		    iterator.remove();
		return cluster;
	    }
	}
	return null;
    }

    /**
     * This method makes the union of the clusters related to the informed nodes
     * 
     * @param startNode
     * @param endNode
     */
    public void union(final Node startNode, final Node endNode) {
	// Remove one cluster and merge the other.
	final Cluster clusterStart = this.find(startNode);
	final int oldNumberOfClusters = this.getNumberOfClusters();
	final Cluster clusterEnd = this.find(endNode, true);
	if (this.getNumberOfClusters() == oldNumberOfClusters)
	    System.out.println("Oops! Cluster " + clusterEnd + " not removed!");
	clusterStart.mergeWith(clusterEnd);
    }

    @Override
    public String toString() {
	return this.clusters.toString();
    }
}
