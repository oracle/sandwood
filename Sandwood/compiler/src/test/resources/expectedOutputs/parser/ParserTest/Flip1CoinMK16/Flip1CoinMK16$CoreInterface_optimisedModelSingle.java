package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK16$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double get$bias();

	// Setter for bias.
	public void set$bias(double cv$value);

	// Getter for fixedFlag$sample11.
	public boolean get$fixedFlag$sample11();

	// Setter for fixedFlag$sample11.
	public void set$fixedFlag$sample11(boolean cv$value);

	// Getter for fixedFlag$sample9.
	public boolean get$fixedFlag$sample9();

	// Setter for fixedFlag$sample9.
	public void set$fixedFlag$sample9(boolean cv$value);

	// Getter for flip.
	public boolean get$flip();

	// Setter for flip.
	public void set$flip(boolean cv$value);

	// Getter for flipMeasured.
	public boolean get$flipMeasured();

	// Setter for flipMeasured.
	public void set$flipMeasured(boolean cv$value);

	// Getter for guard.
	public double get$guard();

	// Setter for guard.
	public void set$guard(double cv$value);

	// Getter for logProbability$bernoulli.
	public double get$logProbability$bernoulli();

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$flip.
	public double get$logProbability$flip();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}