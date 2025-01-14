package org.sandwood.compiler.tests.parser;

interface HMMTestPart4$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] cv$value);

	// Getter for fixedFlag$sample109.
	public boolean get$fixedFlag$sample109();

	// Setter for fixedFlag$sample109.
	public void set$fixedFlag$sample109(boolean cv$value);

	// Getter for fixedFlag$sample19.
	public boolean get$fixedFlag$sample19();

	// Setter for fixedFlag$sample19.
	public void set$fixedFlag$sample19(boolean cv$value);

	// Getter for fixedFlag$sample29.
	public boolean get$fixedFlag$sample29();

	// Setter for fixedFlag$sample29.
	public void set$fixedFlag$sample29(boolean cv$value);

	// Getter for fixedFlag$sample56.
	public boolean get$fixedFlag$sample56();

	// Setter for fixedFlag$sample56.
	public void set$fixedFlag$sample56(boolean cv$value);

	// Getter for fixedFlag$sample74.
	public boolean get$fixedFlag$sample74();

	// Setter for fixedFlag$sample74.
	public void set$fixedFlag$sample74(boolean cv$value);

	// Getter for flips.
	public boolean[][][] get$flips();

	// Setter for flips.
	public void set$flips(boolean[][][] cv$value);

	// Getter for flipsMeasured.
	public boolean[][][] get$flipsMeasured();

	// Setter for flipsMeasured.
	public void set$flipsMeasured(boolean[][][] cv$value);

	// Getter for length$flipsMeasured.
	public int[][] get$length$flipsMeasured();

	// Setter for length$flipsMeasured.
	public void set$length$flipsMeasured(int[][] cv$value);

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

	// Getter for samples.
	public int get$samples();

	// Getter for st.
	public int[][][] get$st();

	// Setter for st.
	public void set$st(int[][][] cv$value);

	// Getter for states.
	public int get$states();

	// Getter for v.
	public double[] get$v();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}