package twosat;

import java.io.IOException;

import org.junit.Test;

import twosat.algorithm.PapadimTwoSatAlgorithm;
import twosat.data.BooleanVariable;
import twosat.data.LogicalSet;
import twosat.data.TwoSatReader;

public class TestTwoSat {

    @Test
    public void testSmallInstance() {
	final LogicalSet logicalSet = new LogicalSet(4);
	logicalSet.addClause(1, false, 2, true);
	logicalSet.addClause(3, false, 2, true);
	logicalSet.addClause(2, false, 4, true);
	logicalSet.addClause(1, true, 4, true);
	final PapadimTwoSatAlgorithm algorithm = new PapadimTwoSatAlgorithm(10, 3);
	if (!algorithm.execute(logicalSet)) {
	    System.out.println("No assignment found!");
	    return;
	}
	System.out.println("Assignment found!");
	for (final BooleanVariable variable : logicalSet.getVariables())
	    System.out.println(variable);
    }

    public void testInstance(final String fileName) throws IOException {
	System.out.println("Testing " + fileName + "...");
	final TwoSatReader reader = new TwoSatReader();
	reader.read(fileName);
	final LogicalSet logicalSet = reader.getLogicalSet();
	final PapadimTwoSatAlgorithm algorithm = new PapadimTwoSatAlgorithm(200000, 20);
	if (!algorithm.execute(logicalSet)) {
	    System.out.println("No assignment found!");
	    return;
	}
	System.out.println("Assignment found!");
    }

    @Test
    public void testAllInstances() throws IOException {
	this.testInstance("2sat/2sat1.txt");
	this.testInstance("2sat/2sat2.txt");
	this.testInstance("2sat/2sat3.txt");
	this.testInstance("2sat/2sat4.txt");
	this.testInstance("2sat/2sat5.txt");
	this.testInstance("2sat/2sat6.txt");
    }
}
