package org.sandwood.compiler.tests.parser;

interface DirichletBernoulli$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for fixedFlag$sample17.
	public boolean get$fixedFlag$sample17();

	// Setter for fixedFlag$sample17.
	public void set$fixedFlag$sample17(boolean cv$value);

	// Getter for length.
	public int get$length();

	// Getter for length$observed.
	public int get$length$observed();

	// Setter for length$observed.
	public void set$length$observed(int cv$value);

	// Getter for logProbability$b1.
	public double get$logProbability$b1();

	// Getter for logProbability$b2.
	public double get$logProbability$b2();

	// Getter for logProbability$output.
	public double get$logProbability$output();

	// Getter for logProbability$prior.
	public double get$logProbability$prior();

	// Getter for observed.
	public boolean[] get$observed();

	// Setter for observed.
	public void set$observed(boolean[] cv$value);

	// Getter for output.
	public boolean[] get$output();

	// Getter for prior.
	public double[] get$prior();

	// Setter for prior.
	public void set$prior(double[] cv$value);

	// Getter for v.
	public double[] get$v();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}