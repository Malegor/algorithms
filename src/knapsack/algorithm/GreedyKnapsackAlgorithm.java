package knapsack.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import knapsack.data.KnapsackItem;

public class GreedyKnapsackAlgorithm extends AbstractKnapsackAlgorithm {
    public GreedyKnapsackAlgorithm(final int capacity) {
	super(capacity);
    }

    @Override
    public Set<KnapsackItem> execute(final Set<KnapsackItem> items) {
	final List<KnapsackItem> orderedItems = new ArrayList<KnapsackItem>(items);
	Collections.sort(orderedItems);
	int currentKnapsackWeight = 0;
	final Set<KnapsackItem> result = new HashSet<KnapsackItem>(10);
	for (final KnapsackItem knapsackItem : orderedItems)
	    if (currentKnapsackWeight + knapsackItem.getWeight() <= this.knapsackCapacity) {
		result.add(knapsackItem);
		currentKnapsackWeight += knapsackItem.getWeight();
		if (currentKnapsackWeight == this.knapsackCapacity)
		    return result;
	    }
	return result;
    }
}
