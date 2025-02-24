package org.sandwood.compiler.tests.parser;

interface Flip2CoinsMK11$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] cv$value);

	// Getter for coins.
	public int get$coins();

	// Getter for fixedFlag$sample22.
	public boolean get$fixedFlag$sample22();

	// Setter for fixedFlag$sample22.
	public void set$fixedFlag$sample22(boolean cv$value);

	// Getter for fixedFlag$sample49.
	public boolean get$fixedFlag$sample49();

	// Setter for fixedFlag$sample49.
	public void set$fixedFlag$sample49(boolean cv$value);

	// Getter for fixedFlag$sample77.
	public boolean get$fixedFlag$sample77();

	// Setter for fixedFlag$sample77.
	public void set$fixedFlag$sample77(boolean cv$value);

	// Getter for fixedFlag$sample9.
	public boolean get$fixedFlag$sample9();

	// Setter for fixedFlag$sample9.
	public void set$fixedFlag$sample9(boolean cv$value);

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