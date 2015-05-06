package twosat.data;

public class Clause {

    // OBS: By definition, variable1 has a LOWER ID than variable2
    private final BooleanVariable variable1, variable2;
    private final boolean isStraight1, isStraight2;

    public Clause(final BooleanVariable var1, final boolean straight1, final BooleanVariable var2,
	    final boolean straight2) {
	final boolean var1hasLowerId = var1.getId() < var2.getId();
	this.variable1 = var1hasLowerId ? var1 : var2;
	this.variable2 = var1hasLowerId ? var2 : var1;
	this.isStraight1 = var1hasLowerId ? straight1 : straight2;
	this.isStraight2 = var1hasLowerId ? straight2 : straight1;
	this.variable1.addClause(this, straight1);
	this.variable2.addClause(this, straight2);
    }

    @Override
    public int hashCode() {
	final int factor = this.isStraight1 ? this.isStraight2 ? 1 : 2 : this.isStraight2 ? 3 : 4;
	return factor
		* (String.valueOf(this.variable1.getId()).hashCode() + String.valueOf(this.variable2.getId())
			.hashCode());
    }

    @Override
    public boolean equals(final Object obj) {
	final Clause other = (Clause) obj;
	return this.variable1.equals(other.variable1) && this.variable2.equals(other.variable2);
    }

    @Override
    public String toString() {
	final String negation = "NOT ";
	final String empty = "";
	return (!this.isStraight1 ? negation : empty) + this.variable1 + " OR "
		+ (!this.isStraight2 ? negation : empty) + this.variable2;
    }

    public boolean isSatisfied() {
	return (this.isStraight1 == this.variable1.getValue()) || (this.isStraight2 == this.variable2.getValue());
    }

    public BooleanVariable getVariable1() {
	return this.variable1;
    }

    public BooleanVariable getVariable2() {
	return this.variable2;
    }

    public BooleanVariable getOtherVariable(final BooleanVariable variable) {
	return variable.equals(this.variable1) ? this.variable2 : variable.equals(this.variable2) ? this.variable1
		: null;
    }
}
