package twosat.data;

import java.util.HashSet;
import java.util.Set;

public class BooleanVariable {
    private final long id;
    private final Set<Clause> straightClauses, negatedClauses;
    private boolean value;

    public BooleanVariable(final long theID) {
	this.id = theID;
	this.negatedClauses = new HashSet<Clause>(4);
	this.straightClauses = new HashSet<Clause>(4);
	this.value = false;
    }

    public void addClause(final Clause clause, final boolean isStraight) {
	if (isStraight)
	    this.straightClauses.add(clause);
	else
	    this.negatedClauses.add(clause);
    }

    @Override
    public int hashCode() {
	return Long.valueOf(this.id).hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
	final BooleanVariable other = (BooleanVariable) obj;
	return other.id == this.id;
    }

    @Override
    public String toString() {
	return String.valueOf(this.id) + "=" + this.value;
    }

    public long getId() {
	return this.id;
    }

    public boolean getValue() {
	return this.value;
    }

    public void setValue(final boolean value) {
	this.value = value;
    }

    public Set<Clause> getStraightClauses() {
	return this.straightClauses;
    }

    public Set<Clause> getNegatedClauses() {
	return this.negatedClauses;
    }
}
