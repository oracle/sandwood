package org.sandwood.compiler.tests.parser;

interface UniformBernoulli$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for a.
	public double get$a();

	// Getter for b.
	public double get$b();

	// Getter for fixedFlag$sample16.
	public boolean get$fixedFlag$sample16();

	// Setter for fixedFlag$sample16.
	public void set$fixedFlag$sample16(boolean cv$value);

	// Getter for fixedFlag$sample8.
	public boolean get$fixedFlag$sample8();

	// Setter for fixedFlag$sample8.
	public void set$fixedFlag$sample8(boolean cv$value);

	// Getter for length$observed.
	public int get$length$observed();

	// Setter for length$observed.
	public void set$length$observed(int cv$value);

	// Getter for logProbability$bernoulli.
	public double get$logProbability$bernoulli();

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
	public double get$prior();

	// Setter for prior.
	public void set$prior(double cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}