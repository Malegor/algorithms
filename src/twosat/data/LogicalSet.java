package twosat.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class LogicalSet {
    private final Map<Long, BooleanVariable> variables;
    private final Set<Clause> clauses;
    private final Set<Clause> unsatisfiedClauses;

    public LogicalSet(final int size) {
	this.variables = new HashMap<Long, BooleanVariable>(size);
	this.clauses = new HashSet<Clause>(size);
	this.unsatisfiedClauses = new HashSet<Clause>(size);
    }

    public void addClause(final long varId1, final boolean isStraight1, final long varId2, final boolean isStraight2) {
	final BooleanVariable var1 = this.findOrCreateVariableOfId(varId1);
	final BooleanVariable var2 = this.findOrCreateVariableOfId(varId2);
	final Clause clause = new Clause(var1, isStraight1, var2, isStraight2);
	this.clauses.add(clause);
    }

    private BooleanVariable findOrCreateVariableOfId(final long varId1) {
	final Long key = Long.valueOf(varId1);
	if (this.containsVariableOfId(varId1))
	    return this.variables.get(key);
	final BooleanVariable newVar = new BooleanVariable(varId1);
	this.variables.put(key, newVar);
	return newVar;
    }

    private boolean containsVariableOfId(final long varId) {
	return this.variables.containsKey(Long.valueOf(varId));
    }

    public Set<Clause> getUnsatisfiedClauses() {
	return this.unsatisfiedClauses;
    }

    public boolean isSatisfied() {
	return this.unsatisfiedClauses.isEmpty();
    }

    public void setRandomAssignment() {
	final Random random = new Random();
	for (final BooleanVariable variable : this.variables.values())
	    variable.setValue(random.nextBoolean());
	this.resetUnsatisfiedClauses();
    }

    private void resetUnsatisfiedClauses() {
	this.unsatisfiedClauses.clear();
	for (final Clause clause : this.clauses)
	    if (!clause.isSatisfied())
		this.unsatisfiedClauses.add(clause);
    }

    public void satisfyClause(final Clause clause, final boolean useFirstVariableOfClause) {
	final BooleanVariable variable = useFirstVariableOfClause ? clause.getVariable1() : clause.getVariable2();
	final boolean newValue = !variable.getValue();
	variable.setValue(newValue);
	// Update the set of unsatisfied clauses
	final Set<Clause> clausesNowSatisfied = newValue ? variable.getStraightClauses() : variable.getNegatedClauses();
	final Set<Clause> clausesMaybeNotSatisfiedAnyMore = newValue ? variable.getNegatedClauses() : variable
		.getStraightClauses();
	this.unsatisfiedClauses.removeAll(clausesNowSatisfied);
	for (final Clause changedClause : clausesMaybeNotSatisfiedAnyMore)
	    if (!changedClause.isSatisfied())
		this.unsatisfiedClauses.add(changedClause);
    }

    public Collection<BooleanVariable> getVariables() {
	return this.variables.values();
    }

    public void deleteVariable(final BooleanVariable variableToDelete) {
	this.variables.remove(Long.valueOf(variableToDelete.getId()));
	final Set<Clause> relatedClauses = new HashSet<Clause>(variableToDelete.getNegatedClauses());
	relatedClauses.addAll(variableToDelete.getStraightClauses());
	BooleanVariable otherVariable;
	for (final Clause clause : relatedClauses) {
	    otherVariable = clause.getOtherVariable(variableToDelete);
	    otherVariable.getNegatedClauses().remove(clause);
	    otherVariable.getStraightClauses().remove(clause);
	}
	this.clauses.removeAll(relatedClauses);
    }
}
