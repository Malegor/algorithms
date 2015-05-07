package twosat.algorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import twosat.data.BooleanVariable;
import twosat.data.Clause;
import twosat.data.LogicalSet;

public class PapadimTwoSatAlgorithm {

    private final long numberOfRandomWalks, numberOfDiversifications, numberOfAttemptsWithOneUnsat;

    public PapadimTwoSatAlgorithm(final long nbOfRandomWalks, final long nbOfDiversifications) {
	this.numberOfRandomWalks = nbOfRandomWalks;
	this.numberOfDiversifications = nbOfDiversifications;
	this.numberOfAttemptsWithOneUnsat = 200;
    }

    public PapadimTwoSatAlgorithm(final long numberOfVariables) {
	this(2 * numberOfVariables * numberOfVariables, Math.round(Math.log(numberOfVariables)));
    }

    public boolean execute(final LogicalSet logicalSet) {
	this.deleteObviousAssignments(logicalSet);
	if (logicalSet.getVariables().isEmpty())
	    return true;
	for (int diversification = 0; diversification < this.numberOfDiversifications; diversification++) {
	    System.out.println("Attempt " + (diversification + 1));
	    logicalSet.setRandomAssignment();
	    // Generate from stratch a random assignment
	    if (this.improveCurrentAssignment(logicalSet))
		return true;
	}
	return false;
    }

    private boolean improveCurrentAssignment(final LogicalSet logicalSet) {
	int onlyOneUnsatisfied = 0;
	Clause toSatisfy;
	Set<Clause> unsatisfiedClauses;
	final Random random = new Random();
	for (int improvement = 0; improvement < this.numberOfRandomWalks; improvement++) {
	    if (logicalSet.isSatisfied())
		return true;
	    unsatisfiedClauses = logicalSet.getUnsatisfiedClauses();
	    if (unsatisfiedClauses.size() == 1) {
		onlyOneUnsatisfied++;
		if (onlyOneUnsatisfied > this.numberOfAttemptsWithOneUnsat)
		    return false;
	    }
	    toSatisfy = this.randomSelect(unsatisfiedClauses);
	    logicalSet.satisfyClause(toSatisfy, random.nextBoolean());
	}
	return logicalSet.isSatisfied();
    }

    private void deleteObviousAssignments(final LogicalSet logicalSet) {
	Set<BooleanVariable> variablesToDelete = this.findVariablesWithObviousAssignment(logicalSet);
	int numberOfDeleted = 0;
	while (!variablesToDelete.isEmpty()) {
	    logicalSet.deleteVariables(variablesToDelete);
	    numberOfDeleted += variablesToDelete.size();
	    variablesToDelete = this.findVariablesWithObviousAssignment(logicalSet);
	}
	System.out.println(numberOfDeleted + " deleted variables.");
    }

    private Set<BooleanVariable> findVariablesWithObviousAssignment(final LogicalSet logicalSet) {
	final Collection<BooleanVariable> allVariables = logicalSet.getVariables();
	final Set<BooleanVariable> toRemove = new HashSet<BooleanVariable>(allVariables.size());
	for (final BooleanVariable variable : allVariables)
	    if (variable.getNegatedClauses().isEmpty() || variable.getStraightClauses().isEmpty())
		//System.out.println("Found " + variable);
		toRemove.add(variable);
	return toRemove;
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
