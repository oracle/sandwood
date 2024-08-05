package org.sandwood.compiler.tests.parser;

interface Flip2CoinsMK6$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] cv$value);

	// Getter for coins.
	public int get$coins();

	// Getter for fixedFlag$sample20.
	public boolean get$fixedFlag$sample20();

	// Setter for fixedFlag$sample20.
	public void set$fixedFlag$sample20(boolean cv$value);

	// Getter for fixedFlag$sample27.
	public boolean get$fixedFlag$sample27();

	// Setter for fixedFlag$sample27.
	public void set$fixedFlag$sample27(boolean cv$value);

	// Getter for flips.
	public boolean[][] get$flips();

	// Setter for flips.
	public void set$flips(boolean[][] cv$value);

	// Getter for flipsMeasured.
	public boolean[][] get$flipsMeasured();

	// Setter for flipsMeasured.
	public void set$flipsMeasured(boolean[][] cv$value);

	// Getter for logProbability$bernoulli.
	public double get$logProbability$bernoulli();

	// Getter for logProbability$beta.
	public double get$logProbability$beta();

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$flips.
	public double get$logProbability$flips();

	// Getter for shape.
	public int[] get$shape();

	// Setter for shape.
	public void set$shape(int[] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}