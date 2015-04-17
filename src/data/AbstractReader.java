package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class AbstractReader {
    private final String datafilesLocation = "/home/sylvain/Documents/workspace/algorithms/datafiles/";

    public void read(final String fileName) throws IOException {
	final String sep = this.getSeparator();
	final BufferedReader reader = new BufferedReader(new FileReader(this.datafilesLocation + fileName));
	String line = reader.readLine();
	this.processFirstLine(line.split(sep));
	while ((line = reader.readLine()) != null)
	    // use space as separator
	    this.processLine(line.split(sep));
	reader.close();
    }

    protected String getSeparator() {
	return " ";
    }

    protected abstract void processLine(String[] line);

    protected abstract void processFirstLine(String[] line);
}
