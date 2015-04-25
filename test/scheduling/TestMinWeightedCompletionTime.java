package scheduling;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import scheduling.minweigthedcompletiontime.algorithm.JobOrderingAlgorithm;
import scheduling.minweigthedcompletiontime.comparator.DecreasingDifferenceComparator;
import scheduling.minweigthedcompletiontime.data.Job;
import scheduling.minweigthedcompletiontime.data.JobsInputReader;

public class TestMinWeightedCompletionTime {

    @Test
    public void testAlgorithm() {
	final JobsInputReader reader = new JobsInputReader();
	try {
	    reader.read("jobs.txt");
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