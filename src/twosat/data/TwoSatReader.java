package twosat.data;

import data.AbstractReader;

public class TwoSatReader extends AbstractReader {

    private LogicalSet logicalSet;

    @Override
    protected void processLine(final String[] line) {
	final int firstInfo = Integer.parseInt(line[0]);
	final int secondInfo = Integer.parseInt(line[1]);
	final boolean isStraight1 = firstInfo > 0;
	final boolean isStraight2 = secondInfo > 0;
	this.logicalSet.addClause(Math.abs(firstInfo), isStraight1, Math.abs(secondInfo), isStraight2);
    }

    @Override
    protected void processFirstLine(final String[] line) {
	this.logicalSet = new LogicalSet(Integer.parseInt(line[0]));
    }

    public LogicalSet getLogicalSet() {
	return this.logicalSet;
    }
}
