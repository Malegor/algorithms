package scheduling.minweigthedcompletiontime;

import java.io.IOException;
import java.util.List;

import scheduling.minweigthedcompletiontime.algorithm.JobOrderingAlgorithm;
import scheduling.minweigthedcompletiontime.comparator.DecreasingDifferenceComparator;
import scheduling.minweigthedcompletiontime.data.Job;
import scheduling.minweigthedcompletiontime.data.JobsInputReader;

public class RunOrderingAlgorithm {
    public static void main(final String[] args) {
	final JobsInputReader reader = new JobsInputReader();
	try {
	    reader.read("/home/sylvain/Documents/workspace/algorithms/jobs.txt");
	} catch (final IOException e) {
	    e.printStackTrace();
	    return;
	}
	final List<Job> inputJobs = reader.getJobs();
	final JobOrderingAlgorithm jobOrderingAlgorithm = new JobOrderingAlgorithm();
	final List<Job> outputJobs = jobOrderingAlgorithm.orderJobs(inputJobs, new DecreasingDifferenceComparator());
	// System.out.println(outputJobs);
	System.out.println(jobOrderingAlgorithm.calculateWeightedSum(outputJobs));
    }
}
