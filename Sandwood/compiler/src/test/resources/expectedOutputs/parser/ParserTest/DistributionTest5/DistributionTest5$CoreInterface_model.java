package org.sandwood.compiler.tests.parser;

interface DistributionTest5$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for fixedFlag$sample12.
	public boolean get$fixedFlag$sample12();

	// Setter for fixedFlag$sample12.
	public void set$fixedFlag$sample12(boolean cv$value);

	// Getter for fixedFlag$sample18.
	public boolean get$fixedFlag$sample18();

	// Setter for fixedFlag$sample18.
	public void set$fixedFlag$sample18(boolean cv$value);

	// Getter for fixedFlag$sample26.
	public boolean get$fixedFlag$sample26();

	// Setter for fixedFlag$sample26.
	public void set$fixedFlag$sample26(boolean cv$value);

	// Getter for fixedFlag$sample55.
	public boolean get$fixedFlag$sample55();

	// Setter for fixedFlag$sample55.
	public void set$fixedFlag$sample55(boolean cv$value);

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