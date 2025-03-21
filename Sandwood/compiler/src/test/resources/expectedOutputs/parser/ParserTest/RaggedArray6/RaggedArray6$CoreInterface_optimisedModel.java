package org.sandwood.compiler.tests.parser;

interface RaggedArray6$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for a.
	public double[][] get$a();

	// Getter for b.
	public double[] get$b();

	// Getter for d.
	public double[] get$d();

	// Setter for d.
	public void set$d(double[] cv$value);

	// Getter for fixedFlag$sample47.
	public boolean get$fixedFlag$sample47();

	// Setter for fixedFlag$sample47.
	public void set$fixedFlag$sample47(boolean cv$value);

	// Getter for fixedFlag$sample50.
	public boolean get$fixedFlag$sample50();

	// Setter for fixedFlag$sample50.
	public void set$fixedFlag$sample50(boolean cv$value);

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
	public boolean[] get$obs();

	// Getter for obs_measured.
	public boolean[] get$obs_measured();

	// Setter for obs_measured.
	public void set$obs_measured(boolean[] cv$value);

	// Getter for y.
	public int get$y();

	// Setter for y.
	public void set$y(int cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}