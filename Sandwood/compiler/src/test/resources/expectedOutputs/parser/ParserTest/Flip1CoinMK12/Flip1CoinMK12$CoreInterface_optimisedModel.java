package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK12$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double get$bias();

	// Getter for fixedFlag$sample20.
	public boolean get$fixedFlag$sample20();

	// Setter for fixedFlag$sample20.
	public void set$fixedFlag$sample20(boolean cv$value);

	// Getter for fixedFlag$sample32.
	public boolean get$fixedFlag$sample32();

	// Setter for fixedFlag$sample32.
	public void set$fixedFlag$sample32(boolean cv$value);

	// Getter for fixedFlag$sample39.
	public boolean get$fixedFlag$sample39();

	// Setter for fixedFlag$sample39.
	public void set$fixedFlag$sample39(boolean cv$value);

	// Getter for fixedFlag$sample56.
	public boolean get$fixedFlag$sample56();

	// Setter for fixedFlag$sample56.
	public void set$fixedFlag$sample56(boolean cv$value);

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

	// Getter for guard2.
	public int get$guard2();

	// Setter for guard2.
	public void set$guard2(int cv$value);

	// Getter for length$flipsMeasured.
	public int get$length$flipsMeasured();

	// Setter for length$flipsMeasured.
	public void set$length$flipsMeasured(int cv$value);

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