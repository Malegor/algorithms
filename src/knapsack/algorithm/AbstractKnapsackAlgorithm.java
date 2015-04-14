package knapsack.algorithm;

import java.util.Set;

import knapsack.data.KnapsackItem;

public abstract class AbstractKnapsackAlgorithm {

    protected final int knapsackCapacity;

    public AbstractKnapsackAlgorithm(final int capacity) {
	this.knapsackCapacity = capacity;
    }

    public abstract Set<KnapsackItem> execute(final Set<KnapsackItem> items);

    public long getTotalValue(final Set<KnapsackItem> items) {
	long totalValue = 0;
	for (final KnapsackItem knapsackItem : items)
	    totalValue += knapsackItem.getValue();
	return totalValue;
    }

    public int getTotalWeight(final Set<KnapsackItem> items) {
	int totalWeight = 0;
	for (final KnapsackItem knapsackItem : items)
	    totalWeight += knapsackItem.getWeight();
	return totalWeight;
    }
}
