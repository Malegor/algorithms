package knapsack.data;

import java.util.HashSet;
import java.util.Set;

import data.AbstractReader;

public class KnapsackReader extends AbstractReader {

    private Set<KnapsackItem> items;
    private int knapsackCapacity;

    @Override
    protected void processLine(final String[] line) {
	this.items.add(new KnapsackItem(Integer.parseInt(line[1]), Integer.parseInt(line[0])));
    }

    @Override
    protected void processFirstLine(final String[] line) {
	this.knapsackCapacity = Integer.parseInt(line[0]);
	this.items = new HashSet<KnapsackItem>(Integer.parseInt(line[1]));
    }

    public Set<KnapsackItem> getItems() {
	return this.items;
    }

    public int getKnapsackCapacity() {
	return this.knapsackCapacity;
    }
}
