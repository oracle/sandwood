package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for a.
	public double get$a();

	// Getter for b.
	public double get$b();

	// Getter for bias.
	public double get$bias();

	// Setter for bias.
	public void set$bias(double cv$value);

	// Getter for fixedFlag$sample15.
	public boolean get$fixedFlag$sample15();

	// Setter for fixedFlag$sample15.
	public void set$fixedFlag$sample15(boolean cv$value);

	// Getter for fixedFlag$sample9.
	public boolean get$fixedFlag$sample9();

	// Setter for fixedFlag$sample9.
	public void set$fixedFlag$sample9(boolean cv$value);

	// Getter for flips.
	public boolean[] get$flips();

	// Setter for flips.
	public void set$flips(boolean[] cv$value);

	// Getter for flipsMeasured.
	public boolean[] get$flipsMeasured();

	// Setter for flipsMeasured.
	public void set$flipsMeasured(boolean[] cv$value);

	// Getter for logProbability$bernoulli.
	public double get$logProbability$bernoulli();

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$flips.
	public double get$logProbability$flips();

	// Getter for samples.
	public int get$samples();

	// Setter for samples.
	public void set$samples(int cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}