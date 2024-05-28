package org.sandwood.compiler.tests.parser;

interface ReductionTest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] cv$value);

	// Getter for fixedFlag$sample34.
	public boolean get$fixedFlag$sample34();

	// Setter for fixedFlag$sample34.
	public void set$fixedFlag$sample34(boolean cv$value);

	// Getter for fixedFlag$sample51.
	public boolean get$fixedFlag$sample51();

	// Setter for fixedFlag$sample51.
	public void set$fixedFlag$sample51(boolean cv$value);

	// Getter for fixedFlag$sample66.
	public boolean get$fixedFlag$sample66();

	// Setter for fixedFlag$sample66.
	public void set$fixedFlag$sample66(boolean cv$value);

	// Getter for fixedFlag$sample91.
	public boolean get$fixedFlag$sample91();

	// Setter for fixedFlag$sample91.
	public void set$fixedFlag$sample91(boolean cv$value);

	// Getter for flips.
	public boolean[] get$flips();

	// Setter for flips.
	public void set$flips(boolean[] cv$value);

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