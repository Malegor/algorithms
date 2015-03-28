package minweigthedcompletiontime.data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
	public List<Job> read(final String fileName) throws IOException {
		String space = " ";
		final BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = reader.readLine();
		final List<Job> jobs = new ArrayList<Job>(Integer.parseInt(line));
		while ((line = reader.readLine()) != null) {
		    // use space as separator
			String[] jobData = line.split(space);
			jobs.add(new Job(Integer.parseInt(jobData[0]), Integer.parseInt(jobData[1])));
		}
		reader.close();
		return jobs;
	}
}
