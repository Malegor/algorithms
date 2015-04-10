package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class AbstractReader {
    public void read(final String fileName) throws IOException {
	final String space = " ";
	final BufferedReader reader = new BufferedReader(new FileReader(fileName));
	String line = reader.readLine();
	this.processFirstLine(line.split(space));
	while ((line = reader.readLine()) != null)
	    // use space as separator
	    this.processLine(line.split(space));
	reader.close();
    }

    protected abstract void processLine(String[] line);

    protected abstract void processFirstLine(String[] line);
}
