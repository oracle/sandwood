package org.sandwood.compiler.tests.parser;

interface Flip2CoinsMK12$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] cv$value);

	// Getter for coins.
	public int get$coins();

	// Getter for fixedFlag$sample16.
	public boolean get$fixedFlag$sample16();

	// Setter for fixedFlag$sample16.
	public void set$fixedFlag$sample16(boolean cv$value);

	// Getter for fixedFlag$sample21.
	public boolean get$fixedFlag$sample21();

	// Setter for fixedFlag$sample21.
	public void set$fixedFlag$sample21(boolean cv$value);

	// Getter for fixedFlag$sample37.
	public boolean get$fixedFlag$sample37();

	// Setter for fixedFlag$sample37.
	public void set$fixedFlag$sample37(boolean cv$value);

	// Getter for fixedFlag$sample54.
	public boolean get$fixedFlag$sample54();

	// Setter for fixedFlag$sample54.
	public void set$fixedFlag$sample54(boolean cv$value);

	// Getter for flips.
	public boolean[][] get$flips();

	// Setter for flips.
	public void set$flips(boolean[][] cv$value);

	// Getter for flipsMeasured.
	public boolean[][] get$flipsMeasured();

	// Setter for flipsMeasured.
	public void set$flipsMeasured(boolean[][] cv$value);

	// Getter for intermediateFlips.
	public boolean[][] get$intermediateFlips();

	// Getter for length$flipsMeasured.
	public int[] get$length$flipsMeasured();

	// Setter for length$flipsMeasured.
	public void set$length$flipsMeasured(int[] cv$value);

	// Getter for logProbability$bernoulli1.
	public double[] get$logProbability$bernoulli1();

	// Getter for logProbability$bernoulli2.
	public double[] get$logProbability$bernoulli2();

	// Getter for logProbability$beta.
	public double get$logProbability$beta();

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$flips.
	public double get$logProbability$flips();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}