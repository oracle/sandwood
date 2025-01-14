package org.sandwood.compiler.tests.parser;

interface HMM$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] cv$value);

	// Getter for fixedFlag$sample17.
	public boolean get$fixedFlag$sample17();

	// Setter for fixedFlag$sample17.
	public void set$fixedFlag$sample17(boolean cv$value);

	// Getter for fixedFlag$sample27.
	public boolean get$fixedFlag$sample27();

	// Setter for fixedFlag$sample27.
	public void set$fixedFlag$sample27(boolean cv$value);

	// Getter for fixedFlag$sample36.
	public boolean get$fixedFlag$sample36();

	// Setter for fixedFlag$sample36.
	public void set$fixedFlag$sample36(boolean cv$value);

	// Getter for fixedFlag$sample46.
	public boolean get$fixedFlag$sample46();

	// Setter for fixedFlag$sample46.
	public void set$fixedFlag$sample46(boolean cv$value);

	// Getter for fixedFlag$sample55.
	public boolean get$fixedFlag$sample55();

	// Setter for fixedFlag$sample55.
	public void set$fixedFlag$sample55(boolean cv$value);

	// Getter for flips.
	public boolean[] get$flips();

	// Setter for flips.
	public void set$flips(boolean[] cv$value);

	// Getter for length$measured.
	public int get$length$measured();

	// Setter for length$measured.
	public void set$length$measured(int cv$value);

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

	// Getter for measured.
	public boolean[] get$measured();

	// Setter for measured.
	public void set$measured(boolean[] cv$value);

	// Getter for samples.
	public int get$samples();

	// Getter for st.
	public int[] get$st();

	// Setter for st.
	public void set$st(int[] cv$value);

	// Getter for states.
	public int get$states();

	// Setter for states.
	public void set$states(int cv$value);

	// Getter for v.
	public double[] get$v();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}