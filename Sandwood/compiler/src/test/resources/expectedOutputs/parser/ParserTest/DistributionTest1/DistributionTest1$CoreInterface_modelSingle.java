package org.sandwood.compiler.tests.parser;

interface DistributionTest1$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for fixedFlag$sample14.
	public boolean get$fixedFlag$sample14();

	// Setter for fixedFlag$sample14.
	public void set$fixedFlag$sample14(boolean cv$value);

	// Getter for fixedFlag$sample7.
	public boolean get$fixedFlag$sample7();

	// Setter for fixedFlag$sample7.
	public void set$fixedFlag$sample7(boolean cv$value);

	// Getter for fixedFlag$sample9.
	public boolean get$fixedFlag$sample9();

	// Setter for fixedFlag$sample9.
	public void set$fixedFlag$sample9(boolean cv$value);

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