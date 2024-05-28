package org.sandwood.compiler.tests.parser;

interface RaggedArray3$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for a.
	public double[][] get$a();

	// Getter for d.
	public double[] get$d();

	// Setter for d.
	public void set$d(double[] cv$value);

	// Getter for fixedFlag$sample42.
	public boolean get$fixedFlag$sample42();

	// Setter for fixedFlag$sample42.
	public void set$fixedFlag$sample42(boolean cv$value);

	// Getter for fixedFlag$sample57.
	public boolean get$fixedFlag$sample57();

	// Setter for fixedFlag$sample57.
	public void set$fixedFlag$sample57(boolean cv$value);

	// Getter for length$obs_measured.
	public int get$length$obs_measured();

	// Setter for length$obs_measured.
	public void set$length$obs_measured(int cv$value);

	// Getter for logProbability$d.
	public double get$logProbability$d();

	// Getter for logProbability$obs.
	public double get$logProbability$obs();

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