package twosat.algorithm;

import java.util.Random;
import java.util.Set;

import twosat.data.BooleanVariable;
import twosat.data.Clause;
import twosat.data.LogicalSet;

public class PapadimTwoSatAlgorithm {

    private final long numberOfRandomWalks, numberOfDiversifications;

    public PapadimTwoSatAlgorithm(final long nbOfRandomWalks, final long nbOfDiversifications) {
	this.numberOfRandomWalks = nbOfRandomWalks;
	this.numberOfDiversifications = nbOfDiversifications;
    }

    public PapadimTwoSatAlgorithm(final long numberOfVariables) {
	this(2 * numberOfVariables * numberOfVariables, Math.round(Math.log(numberOfVariables)));
    }

    public boolean execute(final LogicalSet logicalSet) {
	this.deleteObviousAssignments(logicalSet);
	if (logicalSet.getVariables().isEmpty())
	    return true;
	Clause toSatisfy;
	final Random random = new Random();
	for (int diversification = 0; diversification < this.numberOfDiversifications; diversification++) {
	    System.out.println("Attempt " + (diversification + 1));
	    logicalSet.setRandomAssignment();
	    // Generate from stratch a random assignment
	    for (int improvement = 0; improvement < this.numberOfRandomWalks; improvement++) {
		if (logicalSet.isSatisfied())
		    return true;
		toSatisfy = this.randomSelect(logicalSet.getUnsatisfiedClauses());
		logicalSet.satisfyClause(toSatisfy, random.nextBoolean());
	    }
	    if (logicalSet.isSatisfied())
		return true;
	}
	return false;
    }

    private void deleteObviousAssignments(final LogicalSet logicalSet) {
	BooleanVariable variableToDelete = this.findVariableWithObviousAssignment(logicalSet);
	int numberOfDeleted = 0;
	while (variableToDelete != null) {
	    logicalSet.deleteVariable(variableToDelete);
	    variableToDelete = this.findVariableWithObviousAssignment(logicalSet);
	    numberOfDeleted++;
	}
	System.out.println(numberOfDeleted + " deleted variables.");
    }

    private BooleanVariable findVariableWithObviousAssignment(final LogicalSet logicalSet) {
	for (final BooleanVariable variable : logicalSet.getVariables())
	    if (variable.getNegatedClauses().isEmpty() || variable.getStraightClauses().isEmpty())
		//System.out.println("Found " + variable);
		return variable;
	return null;
    }

    private Clause randomSelect(final Set<Clause> unsatisfiedClauses) {
	final int numberOfUnsatisfiedClauses = unsatisfiedClauses.size();
	final int elementIndex = new Random().nextInt(numberOfUnsatisfiedClauses);
	//System.out.println(numberOfUnsatisfiedClauses + " unsat.");
	int i = 0;
	for (final Clause clause : unsatisfiedClauses)
	    if (i++ == elementIndex)
		return clause;
	return null;
    }
}
