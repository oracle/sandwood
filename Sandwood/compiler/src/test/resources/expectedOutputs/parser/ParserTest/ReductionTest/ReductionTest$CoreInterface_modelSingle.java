package org.sandwood.compiler.tests.parser;

interface ReductionTest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] cv$value);

	// Getter for fixedFlag$sample30.
	public boolean get$fixedFlag$sample30();

	// Setter for fixedFlag$sample30.
	public void set$fixedFlag$sample30(boolean cv$value);

	// Getter for fixedFlag$sample47.
	public boolean get$fixedFlag$sample47();

	// Setter for fixedFlag$sample47.
	public void set$fixedFlag$sample47(boolean cv$value);

	// Getter for fixedFlag$sample62.
	public boolean get$fixedFlag$sample62();

	// Setter for fixedFlag$sample62.
	public void set$fixedFlag$sample62(boolean cv$value);

	// Getter for flips.
	public boolean[] get$flips();

	// Getter for flipsMeasured.
	public boolean[] get$flipsMeasured();

	// Setter for flipsMeasured.
	public void set$flipsMeasured(boolean[] cv$value);

	// Getter for length$flipsMeasured.
	public int get$length$flipsMeasured();

	// Setter for length$flipsMeasured.
	public void set$length$flipsMeasured(int cv$value);

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$flips.
	public double get$logProbability$flips();

	// Getter for logProbability$m.
	public double get$logProbability$m();

	// Getter for logProbability$st.
	public double get$logProbability$st();

	// Getter for m.
	public double[][] get$m();

	// Setter for m.
	public void set$m(double[][] cv$value);

	// Getter for noCats.
	public int get$noCats();

	// Setter for noCats.
	public void set$noCats(int cv$value);

	// Getter for noFlips.
	public int get$noFlips();

	// Getter for noStates.
	public int get$noStates();

	// Getter for st.
	public int[] get$st();

	// Setter for st.
	public void set$st(int[] cv$value);

	// Getter for v.
	public double[] get$v();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}