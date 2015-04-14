package knapsack.data;

public class KnapsackItem implements Comparable<KnapsackItem> {
    private final int weight;
    private final int value;

    public KnapsackItem(final int theWeight, final int theValue) {
	this.weight = theWeight;
	this.value = theValue;
    }

    public int getWeight() {
	return this.weight;
    }

    public int getValue() {
	return this.value;
    }

    @Override
    public int compareTo(final KnapsackItem other) {
	final double diff = (double) this.weight / this.value - (double) other.weight / other.value;
	return diff < 0 ? -1 : diff > 0 ? 1 : this.weight < other.weight ? -1 : this.weight > other.weight ? 1 : 0;
    }

    @Override
    public String toString() {
	return "(w=" + this.weight + " ; v=" + this.value + ")";
    }
}
