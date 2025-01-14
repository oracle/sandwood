package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK19$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for a.
	public int get$a();

	// Setter for a.
	public void set$a(int cv$value);

	// Getter for b.
	public int get$b();

	// Setter for b.
	public void set$b(int cv$value);

	// Getter for bias.
	public double[][] get$bias();

	// Setter for bias.
	public void set$bias(double[][] cv$value);

	// Getter for fixedFlag$sample13.
	public boolean get$fixedFlag$sample13();

	// Setter for fixedFlag$sample13.
	public void set$fixedFlag$sample13(boolean cv$value);

	// Getter for fixedFlag$sample19.
	public boolean get$fixedFlag$sample19();

	// Setter for fixedFlag$sample19.
	public void set$fixedFlag$sample19(boolean cv$value);

	// Getter for fixedFlag$sample40.
	public boolean get$fixedFlag$sample40();

	// Setter for fixedFlag$sample40.
	public void set$fixedFlag$sample40(boolean cv$value);

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

	// Getter for logProbability$q.
	public double get$logProbability$q();

	// Getter for logProbability$t.
	public double get$logProbability$t();

	// Getter for q.
	public double get$q();

	// Setter for q.
	public void set$q(double cv$value);

	// Getter for samples.
	public int get$samples();

	// Setter for samples.
	public void set$samples(int cv$value);

	// Getter for t.
	public double get$t();

	// Setter for t.
	public void set$t(double cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}