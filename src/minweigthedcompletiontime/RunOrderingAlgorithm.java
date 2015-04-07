package minweigthedcompletiontime;

import java.io.IOException;
import java.util.List;

import minweigthedcompletiontime.algorithm.JobOrderingAlgorithm;
import minweigthedcompletiontime.comparator.DecreasingDifferenceComparator;
import minweigthedcompletiontime.data.Job;
import minweigthedcompletiontime.data.JobsInputReader;

public class RunOrderingAlgorithm {
    public static void main(final String[] args) {
	final JobsInputReader reader = new JobsInputReader();
	List<Job> inputJobs;
	try {
	    inputJobs = reader.read("/home/sylvain/Documents/workspace/algorithms/jobs.txt");
	} catch (final IOException e) {
	    e.printStackTrace();
	    return;
	}
	final JobOrderingAlgorithm jobOrderingAlgorithm = new JobOrderingAlgorithm();
	final List<Job> outputJobs = jobOrderingAlgorithm.orderJobs(inputJobs, new DecreasingDifferenceComparator());
	// System.out.println(outputJobs);
	System.out.println(jobOrderingAlgorithm.calculateWeightedSum(outputJobs));
    }
}
