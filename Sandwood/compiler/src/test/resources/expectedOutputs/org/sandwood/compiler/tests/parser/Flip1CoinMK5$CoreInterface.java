package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK5$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double get$bias();

	// Setter for bias.
	public void set$bias(double calculationVariable$value);

	// Getter for fixedFlag$sample17.
	public boolean get$fixedFlag$sample17();

	// Setter for fixedFlag$sample17.
	public void set$fixedFlag$sample17(boolean calculationVariable$value);

	// Getter for fixedFlag$sample23.
	public boolean get$fixedFlag$sample23();

	// Setter for fixedFlag$sample23.
	public void set$fixedFlag$sample23(boolean calculationVariable$value);

	// Getter for fixedFlag$sample30.
	public boolean get$fixedFlag$sample30();

	// Setter for fixedFlag$sample30.
	public void set$fixedFlag$sample30(boolean calculationVariable$value);

	// Getter for flips1.
	public boolean[] get$flips1();

	// Setter for flips1.
	public void set$flips1(boolean[] calculationVariable$value);

	// Getter for flips2.
	public boolean[] get$flips2();

	// Setter for flips2.
	public void set$flips2(boolean[] calculationVariable$value);

	// Getter for flipsMeasured1.
	public boolean[] get$flipsMeasured1();

	// Setter for flipsMeasured1.
	public void set$flipsMeasured1(boolean[] calculationVariable$value);

	// Getter for flipsMeasured2.
	public boolean[] get$flipsMeasured2();

	// Setter for flipsMeasured2.
	public void set$flipsMeasured2(boolean[] calculationVariable$value);

	// Getter for length$flipsMeasured1.
	public int get$length$flipsMeasured1();

	// Setter for length$flipsMeasured1.
	public void set$length$flipsMeasured1(int calculationVariable$value);

	// Getter for length$flipsMeasured2.
	public int get$length$flipsMeasured2();

	// Setter for length$flipsMeasured2.
	public void set$length$flipsMeasured2(int calculationVariable$value);

	// Getter for logProbability$bernoulli1.
	public double get$logProbability$bernoulli1();

	// Getter for logProbability$bernoulli2.
	public double get$logProbability$bernoulli2();

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$flips1.
	public double get$logProbability$flips1();

	// Getter for logProbability$flips2.
	public double get$logProbability$flips2();

	// Getter for samples1.
	public int get$samples1();

	// Getter for samples2.
	public int get$samples2();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}