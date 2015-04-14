package knapsack.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import knapsack.comparator.WeightComparator;
import knapsack.data.KnapsackItem;

public class DynamicProgramKnapsack extends AbstractKnapsackAlgorithm {

    private List<KnapsackItem> allItems;
    private Map<String, Long> knapsackValue;

    public DynamicProgramKnapsack(final int capacity) {
	super(capacity);
    }

    @Override
    public Set<KnapsackItem> execute(final Set<KnapsackItem> items) {
	this.allItems = new ArrayList<KnapsackItem>(items);
	Collections.sort(this.allItems, new WeightComparator());
	this.knapsackValue = new HashMap<String, Long>(this.allItems.size());
	final long bestValue = this.calculateBestValue(this.allItems.size() - 1, this.knapsackCapacity);
	return this.findItemsFromValue(bestValue);
    }

    private long calculateBestValue(final int itemIndex, final int totalWeight) {
	final String key = itemIndex + ";" + totalWeight;
	if (!this.knapsackValue.containsKey(key))
	    if (itemIndex <= -1 || totalWeight <= 0)
		this.knapsackValue.put(key, Long.valueOf(0));
	    else {
		final KnapsackItem knapsackItem = this.allItems.get(itemIndex);
		final long bestValueWithoutItem = this.calculateBestValue(itemIndex - 1, totalWeight);
		final long bestValue = totalWeight < knapsackItem.getWeight() ? bestValueWithoutItem : Math.max(
			bestValueWithoutItem,
			this.calculateBestValue(itemIndex - 1, totalWeight - knapsackItem.getWeight())
				+ knapsackItem.getValue());
		this.knapsackValue.put(key, Long.valueOf(bestValue));
	    }
	return this.knapsackValue.get(key).longValue();
    }

    private Set<KnapsackItem> findItemsFromValue(final long bestValue) {
	final Set<KnapsackItem> result = new HashSet<KnapsackItem>(this.allItems.size());
	int currentWeight = this.knapsackCapacity;
	long currentValue = bestValue;
	int itemIndex = this.allItems.size() - 1;
	String key;
	KnapsackItem itemInResult;
	while (currentValue > 0) {
	    key = (itemIndex - 1) + ";" + currentWeight;
	    if (this.knapsackValue.get(key).longValue() < currentValue) {
		itemInResult = this.allItems.get(itemIndex);
		result.add(itemInResult);
		currentValue -= itemInResult.getValue();
		currentWeight -= itemInResult.getWeight();
	    }
	    itemIndex--;
	}
	return result;
    }
}
