package minweigthedcompletiontime.comparator;

import java.util.Comparator;

import minweigthedcompletiontime.data.Job;

public class DecreasingRatioComparator implements Comparator<Job> {
	@Override
	public int compare(final Job job1, final Job job2) {
		final double ratio = (double) job1.getWeight() / job1.getLength()
				- ((double) job2.getWeight() / job2.getLength());
		return ratio < 0 ? 1 : ratio > 0 ? -1 : 0;
	}
}
