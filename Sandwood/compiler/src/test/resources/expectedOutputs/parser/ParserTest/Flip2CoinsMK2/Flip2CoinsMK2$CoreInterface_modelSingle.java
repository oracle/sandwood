package org.sandwood.compiler.tests.parser;

interface Flip2CoinsMK2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for a.
	public double get$a();

	// Setter for a.
	public void set$a(double cv$value);

	// Getter for b.
	public double get$b();

	// Setter for b.
	public void set$b(double cv$value);

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] cv$value);

	// Getter for coins.
	public int get$coins();

	// Getter for fixedFlag$sample21.
	public boolean get$fixedFlag$sample21();

	// Setter for fixedFlag$sample21.
	public void set$fixedFlag$sample21(boolean cv$value);

	// Getter for fixedFlag$sample33.
	public boolean get$fixedFlag$sample33();

	// Setter for fixedFlag$sample33.
	public void set$fixedFlag$sample33(boolean cv$value);

	// Getter for flips.
	public boolean[][] get$flips();

	// Setter for flips.
	public void set$flips(boolean[][] cv$value);

	// Getter for flipsMeasured.
	public boolean[][] get$flipsMeasured();

	// Setter for flipsMeasured.
	public void set$flipsMeasured(boolean[][] cv$value);

	// Getter for length$flipsMeasured.
	public int[] get$length$flipsMeasured();

	// Setter for length$flipsMeasured.
	public void set$length$flipsMeasured(int[] cv$value);

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