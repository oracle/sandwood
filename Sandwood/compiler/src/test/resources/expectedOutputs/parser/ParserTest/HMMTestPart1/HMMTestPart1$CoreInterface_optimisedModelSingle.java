package org.sandwood.compiler.tests.parser;

interface HMMTestPart1$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] cv$value);

	// Getter for fixedFlag$sample14.
	public boolean get$fixedFlag$sample14();

	// Setter for fixedFlag$sample14.
	public void set$fixedFlag$sample14(boolean cv$value);

	// Getter for fixedFlag$sample24.
	public boolean get$fixedFlag$sample24();

	// Setter for fixedFlag$sample24.
	public void set$fixedFlag$sample24(boolean cv$value);

	// Getter for fixedFlag$sample29.
	public boolean get$fixedFlag$sample29();

	// Setter for fixedFlag$sample29.
	public void set$fixedFlag$sample29(boolean cv$value);

	// Getter for fixedFlag$sample32.
	public boolean get$fixedFlag$sample32();

	// Setter for fixedFlag$sample32.
	public void set$fixedFlag$sample32(boolean cv$value);

	// Getter for flip.
	public boolean get$flip();

	// Setter for flip.
	public void set$flip(boolean cv$value);

	// Getter for flipMeasured.
	public boolean get$flipMeasured();

	// Setter for flipMeasured.
	public void set$flipMeasured(boolean cv$value);

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$flip.
	public double get$logProbability$flip();

	// Getter for logProbability$m.
	public double get$logProbability$m();

	// Getter for logProbability$st.
	public double get$logProbability$st();

	// Getter for m.
	public double[][] get$m();

	// Setter for m.
	public void set$m(double[][] cv$value);

	// Getter for st.
	public int get$st();

	// Setter for st.
	public void set$st(int cv$value);

	// Getter for states.
	public int get$states();

	// Getter for v.
	public double[] get$v();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}