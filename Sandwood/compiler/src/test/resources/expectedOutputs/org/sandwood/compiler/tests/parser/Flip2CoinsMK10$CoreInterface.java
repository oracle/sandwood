package org.sandwood.compiler.tests.parser;

interface Flip2CoinsMK10$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] calculationVariable$value);

	// Getter for coins.
	public int get$coins();

	// Getter for fixedFlag$sample19.
	public boolean get$fixedFlag$sample19();

	// Setter for fixedFlag$sample19.
	public void set$fixedFlag$sample19(boolean calculationVariable$value);

	// Getter for fixedFlag$sample24.
	public boolean get$fixedFlag$sample24();

	// Setter for fixedFlag$sample24.
	public void set$fixedFlag$sample24(boolean calculationVariable$value);

	// Getter for fixedFlag$sample36.
	public boolean get$fixedFlag$sample36();

	// Setter for fixedFlag$sample36.
	public void set$fixedFlag$sample36(boolean calculationVariable$value);

	// Getter for flips.
	public boolean[][] get$flips();

	// Setter for flips.
	public void set$flips(boolean[][] calculationVariable$value);

	// Getter for flipsMeasured.
	public boolean[][] get$flipsMeasured();

	// Setter for flipsMeasured.
	public void set$flipsMeasured(boolean[][] calculationVariable$value);

	// Getter for logProbability$bernoulli.
	public double[] get$logProbability$bernoulli();

	// Getter for logProbability$beta.
	public double get$logProbability$beta();

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$flips.
	public double get$logProbability$flips();

	// Getter for shape.
	public int[] get$shape();

	// Setter for shape.
	public void set$shape(int[] calculationVariable$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}