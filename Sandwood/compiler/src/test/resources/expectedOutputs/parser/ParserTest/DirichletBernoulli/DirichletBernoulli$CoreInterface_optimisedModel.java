package org.sandwood.compiler.tests.parser;

interface DirichletBernoulli$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for fixedFlag$sample20.
	public boolean get$fixedFlag$sample20();

	// Setter for fixedFlag$sample20.
	public void set$fixedFlag$sample20(boolean cv$value);

	// Getter for fixedFlag$sample42.
	public boolean get$fixedFlag$sample42();

	// Setter for fixedFlag$sample42.
	public void set$fixedFlag$sample42(boolean cv$value);

	// Getter for fixedFlag$sample55.
	public boolean get$fixedFlag$sample55();

	// Setter for fixedFlag$sample55.
	public void set$fixedFlag$sample55(boolean cv$value);

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

	// Setter for output.
	public void set$output(boolean[] cv$value);

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