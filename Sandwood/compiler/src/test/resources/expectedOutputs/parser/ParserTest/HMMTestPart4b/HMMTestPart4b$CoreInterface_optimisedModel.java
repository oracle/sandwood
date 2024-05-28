package org.sandwood.compiler.tests.parser;

interface HMMTestPart4b$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] cv$value);

	// Getter for fixedFlag$sample128.
	public boolean get$fixedFlag$sample128();

	// Setter for fixedFlag$sample128.
	public void set$fixedFlag$sample128(boolean cv$value);

	// Getter for fixedFlag$sample195.
	public boolean get$fixedFlag$sample195();

	// Setter for fixedFlag$sample195.
	public void set$fixedFlag$sample195(boolean cv$value);

	// Getter for fixedFlag$sample33.
	public boolean get$fixedFlag$sample33();

	// Setter for fixedFlag$sample33.
	public void set$fixedFlag$sample33(boolean cv$value);

	// Getter for fixedFlag$sample50.
	public boolean get$fixedFlag$sample50();

	// Setter for fixedFlag$sample50.
	public void set$fixedFlag$sample50(boolean cv$value);

	// Getter for fixedFlag$sample88.
	public boolean get$fixedFlag$sample88();

	// Setter for fixedFlag$sample88.
	public void set$fixedFlag$sample88(boolean cv$value);

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