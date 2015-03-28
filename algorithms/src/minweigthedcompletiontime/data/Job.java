package minweigthedcompletiontime.data;

public class Job {
	private final int weight;
	private final int length;

	public Job(final int theWeight, final int theLength) {
		this.weight = theWeight;
		this.length = theLength;
	}

	public int getWeight() {
		return this.weight;
	}

	public int getLength() {
		return this.length;
	}

	@Override
	public String toString() {
		return "(w=" + this.weight + ",l=" + this.length + ")";
	}
}
