package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK20$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double get$bias();

	// Setter for bias.
	public void set$bias(double cv$value);

	// Getter for count1.
	public int get$count1();

	// Getter for count2.
	public int get$count2();

	// Getter for fixedFlag$sample8.
	public boolean get$fixedFlag$sample8();

	// Setter for fixedFlag$sample8.
	public void set$fixedFlag$sample8(boolean cv$value);

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$binomial.
	public double get$logProbability$binomial();

	// Getter for logProbability$count1.
	public double get$logProbability$count1();

	// Getter for logProbability$count2.
	public double get$logProbability$count2();

	// Getter for obs1.
	public int get$obs1();

	// Setter for obs1.
	public void set$obs1(int cv$value);

	// Getter for obs2.
	public int get$obs2();

	// Setter for obs2.
	public void set$obs2(int cv$value);

	// Getter for total.
	public int get$total();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}