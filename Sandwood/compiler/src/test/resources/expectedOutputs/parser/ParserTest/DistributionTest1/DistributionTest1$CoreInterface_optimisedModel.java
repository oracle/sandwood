package org.sandwood.compiler.tests.parser;

interface DistributionTest1$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for fixedFlag$sample11.
	public boolean get$fixedFlag$sample11();

	// Setter for fixedFlag$sample11.
	public void set$fixedFlag$sample11(boolean cv$value);

	// Getter for fixedFlag$sample4.
	public boolean get$fixedFlag$sample4();

	// Setter for fixedFlag$sample4.
	public void set$fixedFlag$sample4(boolean cv$value);

	// Getter for fixedFlag$sample6.
	public boolean get$fixedFlag$sample6();

	// Setter for fixedFlag$sample6.
	public void set$fixedFlag$sample6(boolean cv$value);

	// Getter for logProbability$v.
	public double get$logProbability$v();

	// Getter for logProbability$v1.
	public double get$logProbability$v1();

	// Getter for logProbability$v2.
	public double get$logProbability$v2();

	// Getter for v.
	public boolean get$v();

	// Setter for v.
	public void set$v(boolean cv$value);

	// Getter for v1.
	public int get$v1();

	// Setter for v1.
	public void set$v1(int cv$value);

	// Getter for v2.
	public int get$v2();

	// Setter for v2.
	public void set$v2(int cv$value);

	// Getter for value.
	public boolean get$value();

	// Setter for value.
	public void set$value(boolean cv$value);

	// Getter for weightings.
	public double[] get$weightings();

	// Setter for weightings.
	public void set$weightings(double[] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}