package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK14$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for b.
	public double get$b();

	// Setter for b.
	public void set$b(double cv$value);

	// Getter for bias.
	public double get$bias();

	// Getter for fixedFlag$sample12.
	public boolean get$fixedFlag$sample12();

	// Setter for fixedFlag$sample12.
	public void set$fixedFlag$sample12(boolean cv$value);

	// Getter for fixedFlag$sample29.
	public boolean get$fixedFlag$sample29();

	// Setter for fixedFlag$sample29.
	public void set$fixedFlag$sample29(boolean cv$value);

	// Getter for flips.
	public boolean[] get$flips();

	// Setter for flips.
	public void set$flips(boolean[] cv$value);

	// Getter for flipsMeasured.
	public boolean[] get$flipsMeasured();

	// Setter for flipsMeasured.
	public void set$flipsMeasured(boolean[] cv$value);

	// Getter for guard1.
	public boolean get$guard1();

	// Setter for guard1.
	public void set$guard1(boolean cv$value);

	// Getter for length$flipsMeasured.
	public int get$length$flipsMeasured();

	// Setter for length$flipsMeasured.
	public void set$length$flipsMeasured(int cv$value);

	// Getter for logProbability$b.
	public double get$logProbability$b();

	// Getter for logProbability$bernoulli.
	public double get$logProbability$bernoulli();

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$flips.
	public double get$logProbability$flips();

	// Getter for samples.
	public int get$samples();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}