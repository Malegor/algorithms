package numbers.data;

import java.util.ArrayList;
import java.util.List;

import data.AbstractReader;

public class IntegerReader extends AbstractReader {
    private List<Integer> numbers;

    @Override
    protected void processLine(final String[] line) {
	this.numbers.add(Integer.parseInt(line[0]));
    }

    @Override
    protected void processFirstLine(final String[] line) {
	this.numbers = new ArrayList<Integer>(10000);
	this.processLine(line);
    }

    public List<Integer> getNumbers() {
	return this.numbers;
    }

}
