package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK15$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for b.
	public double get$b();

	// Setter for b.
	public void set$b(double cv$value);

	// Getter for bias.
	public double get$bias();

	// Getter for fixedFlag$sample8.
	public boolean get$fixedFlag$sample8();

	// Setter for fixedFlag$sample8.
	public void set$fixedFlag$sample8(boolean cv$value);

	// Getter for flips.
	public boolean[] get$flips();

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