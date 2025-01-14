package org.sandwood.compiler.tests.parser;

interface ParallelMK5$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for fixedFlag$sample41.
	public boolean get$fixedFlag$sample41();

	// Setter for fixedFlag$sample41.
	public void set$fixedFlag$sample41(boolean cv$value);

	// Getter for fixedFlag$sample65.
	public boolean get$fixedFlag$sample65();

	// Setter for fixedFlag$sample65.
	public void set$fixedFlag$sample65(boolean cv$value);

	// Getter for generated.
	public int[] get$generated();

	// Setter for generated.
	public void set$generated(int[] cv$value);

	// Getter for indirection1.
	public double[][] get$indirection1();

	// Setter for indirection1.
	public void set$indirection1(double[][] cv$value);

	// Getter for indirection2.
	public double[][] get$indirection2();

	// Getter for length$observed.
	public int get$length$observed();

	// Setter for length$observed.
	public void set$length$observed(int cv$value);

	// Getter for logProbability$generated.
	public double get$logProbability$generated();

	// Getter for logProbability$indirection1.
	public double get$logProbability$indirection1();

	// Getter for logProbability$indirection2.
	public double get$logProbability$indirection2();

	// Getter for observed.
	public int[] get$observed();

	// Setter for observed.
	public void set$observed(int[] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}