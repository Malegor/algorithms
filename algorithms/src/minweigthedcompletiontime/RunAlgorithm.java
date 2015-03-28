package minweigthedcompletiontime;

import java.io.IOException;
import java.util.List;

import minweigthedcompletiontime.algorithm.JobOrderingAlgorithm;
import minweigthedcompletiontime.comparator.DecreasingDifferenceComparator;
import minweigthedcompletiontime.data.InputReader;
import minweigthedcompletiontime.data.Job;

public class RunAlgorithm {
	public static void main(final String[] args) {
		final InputReader reader = new InputReader();
		List<Job> inputJobs;
		try {
			inputJobs = reader
					.read("/home/sylvain/Documents/workspace/jobs.txt");
		} catch (final IOException e) {
			e.printStackTrace();
			return;
		}
		final JobOrderingAlgorithm jobOrderingAlgorithm = new JobOrderingAlgorithm();
		final List<Job> outputJobs = jobOrderingAlgorithm.orderJobs(inputJobs,
				new DecreasingDifferenceComparator());
		// System.out.println("INPUT:");
		// System.out.println(inputJobs);
		// System.out
		// .println(jobOrderingAlgorithm.calculateWeightedSum(inputJobs));
		// System.out.println("OUTPUT:");
		// System.out.println(outputJobs);
		System.out.println(jobOrderingAlgorithm
				.calculateWeightedSum(outputJobs));
	}
}
