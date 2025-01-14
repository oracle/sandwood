package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK18$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for a.
	public int get$a();

	// Setter for a.
	public void set$a(int cv$value);

	// Getter for b.
	public int get$b();

	// Setter for b.
	public void set$b(int cv$value);

	// Getter for bias.
	public double[][][] get$bias();

	// Getter for c.
	public int get$c();

	// Setter for c.
	public void set$c(int cv$value);

	// Getter for fixedFlag$sample14.
	public boolean get$fixedFlag$sample14();

	// Setter for fixedFlag$sample14.
	public void set$fixedFlag$sample14(boolean cv$value);

	// Getter for fixedFlag$sample20.
	public boolean get$fixedFlag$sample20();

	// Setter for fixedFlag$sample20.
	public void set$fixedFlag$sample20(boolean cv$value);

	// Getter for fixedFlag$sample85.
	public boolean get$fixedFlag$sample85();

	// Setter for fixedFlag$sample85.
	public void set$fixedFlag$sample85(boolean cv$value);

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