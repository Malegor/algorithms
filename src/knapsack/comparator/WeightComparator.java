package knapsack.comparator;

import java.util.Comparator;

import knapsack.data.KnapsackItem;

public class WeightComparator implements Comparator<KnapsackItem> {

    @Override
    public int compare(final KnapsackItem o1, final KnapsackItem o2) {
	return o1.getWeight() < o2.getWeight() ? -1 : o1.getWeight() > o2.getWeight() ? 1 : o1.getValue() < o2
		.getValue() ? -1 : o1.getValue() > o2.getValue() ? 1 : 0;
    }
}
