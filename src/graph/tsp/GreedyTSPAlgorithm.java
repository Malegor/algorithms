package graph.tsp;

import graph.base.Graph;
import graph.base.Node;
import graph.base.NodeXY;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreedyTSPAlgorithm {
    public double execute(final Graph graph) {
	final List<Node> nodes = new ArrayList<Node>(graph.getNodes());
	Collections.shuffle(nodes);
	final PartialTour tour = new PartialTour(nodes.size(), (NodeXY) nodes.get(0));
	NodeXY currentNodeToAdd;
	int bestIndexToAdd;
	// Construction heuristic
	for (int i = 1; i < nodes.size(); i++) {
	    currentNodeToAdd = (NodeXY) nodes.get(i);
	    bestIndexToAdd = tour.findBestIndexToAdd(currentNodeToAdd);
	    tour.addNode(currentNodeToAdd, bestIndexToAdd);
	}
	// Improvement heuristic
	boolean solutionImproved = true;
	while (solutionImproved)
	    solutionImproved = this.improveSolution(tour);
	return tour.getTourLength();
    }

    private boolean improveSolution(final PartialTour tour) {
	NodeXY relocatedNode;
	int bestIndexToAdd;
	final int size = tour.size();
	for (int i = 0; i < size; i++) {
	    relocatedNode = tour.removeNode(i);
	    bestIndexToAdd = tour.findBestIndexToAdd(relocatedNode);
	    // Try to relocate at the same place
	    if (i == 0 && bestIndexToAdd == size - 1)
		bestIndexToAdd = i;
	    tour.addNode(relocatedNode, bestIndexToAdd);
	    if (i != bestIndexToAdd)
		return true;
	}
	return false;
    }
}
