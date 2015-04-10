package scheduling.minweigthedcompletiontime.data;

import java.util.ArrayList;
import java.util.List;

import data.AbstractReader;

public class JobsInputReader extends AbstractReader {
    private List<Job> jobs;

    @Override
    protected void processLine(final String[] line) {
	this.jobs.add(new Job(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
    }

    @Override
    protected void processFirstLine(final String[] line) {
	this.jobs = new ArrayList<Job>(Integer.parseInt(line[0]));
    }

    public List<Job> getJobs() {
	return this.jobs;
    }
}
