package org.sandwood.compiler.tests.parser;

interface ParallelMK3$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for fixedFlag$sample19.
	public boolean get$fixedFlag$sample19();

	// Setter for fixedFlag$sample19.
	public void set$fixedFlag$sample19(boolean cv$value);

	// Getter for fixedFlag$sample30.
	public boolean get$fixedFlag$sample30();

	// Setter for fixedFlag$sample30.
	public void set$fixedFlag$sample30(boolean cv$value);

	// Getter for generated.
	public double[] get$generated();

	// Setter for generated.
	public void set$generated(double[] cv$value);

	// Getter for indirection.
	public double[] get$indirection();

	// Setter for indirection.
	public void set$indirection(double[] cv$value);

	// Getter for length$observed.
	public int get$length$observed();

	// Setter for length$observed.
	public void set$length$observed(int cv$value);

	// Getter for logProbability$generated.
	public double get$logProbability$generated();

	// Getter for logProbability$indirection.
	public double get$logProbability$indirection();

	// Getter for logProbability$sample.
	public double get$logProbability$sample();

	// Getter for observed.
	public double[] get$observed();

	// Setter for observed.
	public void set$observed(double[] cv$value);

	// Getter for sample.
	public double[] get$sample();

	// Setter for sample.
	public void set$sample(double[] cv$value);

	// Getter for v.
	public double[] get$v();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}