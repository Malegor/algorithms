package scheduling.minweigthedcompletiontime.comparator;

import java.util.Comparator;

import scheduling.minweigthedcompletiontime.data.Job;


public class DecreasingDifferenceComparator implements Comparator<Job> {
    @Override
    public int compare(final Job job1, final Job job2) {
	final int diff = job1.getWeight() - job1.getLength() - (job2.getWeight() - job2.getLength());
	return diff < 0 ? 1 : diff > 0 ? -1 : job1.getWeight() < job2.getWeight() ? 1 : job1.getWeight() > job2
		.getWeight() ? -1 : 0;
    }
}
