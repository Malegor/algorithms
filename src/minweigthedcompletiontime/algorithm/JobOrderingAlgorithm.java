package minweigthedcompletiontime.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import minweigthedcompletiontime.data.Job;

public class JobOrderingAlgorithm {
    public List<Job> orderJobs(final List<Job> jobs, final Comparator<Job> comparator) {
	final List<Job> ordered = new ArrayList<Job>(jobs);
	Collections.sort(ordered, comparator);
	return ordered;
    }

    public long calculateWeightedSum(final List<Job> jobs) {
	long weightedSum = 0;
	long currentTime = 0;
	for (final Job job : jobs) {
	    currentTime += job.getLength();
	    weightedSum += job.getWeight() * currentTime;
	    if (weightedSum < 0) {
		System.out.println("Problem with job " + job + " : " + weightedSum);
		return weightedSum;
	    }
	}
	return weightedSum;
    }
}
