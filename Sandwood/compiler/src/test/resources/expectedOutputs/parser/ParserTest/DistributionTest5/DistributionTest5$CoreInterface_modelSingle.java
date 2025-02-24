package org.sandwood.compiler.tests.parser;

interface DistributionTest5$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for fixedFlag$sample11.
	public boolean get$fixedFlag$sample11();

	// Setter for fixedFlag$sample11.
	public void set$fixedFlag$sample11(boolean cv$value);

	// Getter for fixedFlag$sample27.
	public boolean get$fixedFlag$sample27();

	// Setter for fixedFlag$sample27.
	public void set$fixedFlag$sample27(boolean cv$value);

	// Getter for fixedFlag$sample5.
	public boolean get$fixedFlag$sample5();

	// Setter for fixedFlag$sample5.
	public void set$fixedFlag$sample5(boolean cv$value);

	// Getter for fixedFlag$sample70.
	public boolean get$fixedFlag$sample70();

	// Setter for fixedFlag$sample70.
	public void set$fixedFlag$sample70(boolean cv$value);

	// Getter for length$value.
	public int get$length$value();

	// Setter for length$value.
	public void set$length$value(int cv$value);

	// Getter for logProbability$v.
	public double get$logProbability$v();

	// Getter for logProbability$v1.
	public double get$logProbability$v1();

	// Getter for logProbability$v2.
	public double get$logProbability$v2();

	// Getter for logProbability$v3.
	public double get$logProbability$v3();

	// Getter for size.
	public int get$size();

	// Getter for v.
	public boolean[] get$v();

	// Setter for v.
	public void set$v(boolean[] cv$value);

	// Getter for v1.
	public int get$v1();

	// Setter for v1.
	public void set$v1(int cv$value);

	// Getter for v2.
	public int[] get$v2();

	// Setter for v2.
	public void set$v2(int[] cv$value);

	// Getter for v3.
	public int[] get$v3();

	// Getter for value.
	public boolean[] get$value();

	// Setter for value.
	public void set$value(boolean[] cv$value);

	// Getter for weightings.
	public double[] get$weightings();

	// Setter for weightings.
	public void set$weightings(double[] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}