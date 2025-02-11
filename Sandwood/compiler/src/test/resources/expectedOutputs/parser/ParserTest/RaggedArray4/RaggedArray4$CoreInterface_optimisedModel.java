package org.sandwood.compiler.tests.parser;

interface RaggedArray4$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for a.
	public double[][] get$a();

	// Getter for b.
	public double[] get$b();

	// Getter for d.
	public double[] get$d();

	// Setter for d.
	public void set$d(double[] cv$value);

	// Getter for fixedFlag$sample41.
	public boolean get$fixedFlag$sample41();

	// Setter for fixedFlag$sample41.
	public void set$fixedFlag$sample41(boolean cv$value);

	// Getter for fixedFlag$sample44.
	public boolean get$fixedFlag$sample44();

	// Setter for fixedFlag$sample44.
	public void set$fixedFlag$sample44(boolean cv$value);

	// Getter for fixedFlag$sample52.
	public boolean get$fixedFlag$sample52();

	// Setter for fixedFlag$sample52.
	public void set$fixedFlag$sample52(boolean cv$value);

	// Getter for length$obs_measured.
	public int get$length$obs_measured();

	// Setter for length$obs_measured.
	public void set$length$obs_measured(int cv$value);

	// Getter for logProbability$d.
	public double get$logProbability$d();

	// Getter for logProbability$obs.
	public double get$logProbability$obs();

	// Getter for logProbability$y.
	public double get$logProbability$y();

	// Getter for obs.
	public int[] get$obs();

	// Setter for obs.
	public void set$obs(int[] cv$value);

	// Getter for obs_measured.
	public int[] get$obs_measured();

	// Setter for obs_measured.
	public void set$obs_measured(int[] cv$value);

	// Getter for y.
	public int get$y();

	// Setter for y.
	public void set$y(int cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}