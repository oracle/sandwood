package org.sandwood.compiler.tests.parser;

interface DistributionTest1b$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for fixedFlag$sample10.
	public boolean get$fixedFlag$sample10();

	// Setter for fixedFlag$sample10.
	public void set$fixedFlag$sample10(boolean cv$value);

	// Getter for fixedFlag$sample16.
	public boolean get$fixedFlag$sample16();

	// Setter for fixedFlag$sample16.
	public void set$fixedFlag$sample16(boolean cv$value);

	// Getter for fixedFlag$sample7.
	public boolean get$fixedFlag$sample7();

	// Setter for fixedFlag$sample7.
	public void set$fixedFlag$sample7(boolean cv$value);

	// Getter for fixedFlag$sample9.
	public boolean get$fixedFlag$sample9();

	// Setter for fixedFlag$sample9.
	public void set$fixedFlag$sample9(boolean cv$value);

	// Getter for logProbability$c.
	public double get$logProbability$c();

	// Getter for logProbability$v.
	public double get$logProbability$v();

	// Getter for logProbability$v1.
	public double get$logProbability$v1();

	// Getter for logProbability$v2.
	public double get$logProbability$v2();

	// Getter for logProbability$v3.
	public double get$logProbability$v3();

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

	// Getter for v3.
	public int get$v3();

	// Setter for v3.
	public void set$v3(int cv$value);

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