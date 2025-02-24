package org.sandwood.compiler.tests.parser;

interface RaggedArray$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for a.
	public double[][] get$a();

	// Getter for b.
	public double[][] get$b();

	// Getter for fixedFlag$sample73.
	public boolean get$fixedFlag$sample73();

	// Setter for fixedFlag$sample73.
	public void set$fixedFlag$sample73(boolean cv$value);

	// Getter for fixedFlag$sample89.
	public boolean get$fixedFlag$sample89();

	// Setter for fixedFlag$sample89.
	public void set$fixedFlag$sample89(boolean cv$value);

	// Getter for i.
	public int get$i();

	// Setter for i.
	public void set$i(int cv$value);

	// Getter for length$obs_measured.
	public int get$length$obs_measured();

	// Setter for length$obs_measured.
	public void set$length$obs_measured(int cv$value);

	// Getter for logProbability$i.
	public double get$logProbability$i();

	// Getter for logProbability$obs.
	public double get$logProbability$obs();

	// Getter for logProbability$p.
	public double get$logProbability$p();

	// Getter for obs.
	public boolean[] get$obs();

	// Setter for obs.
	public void set$obs(boolean[] cv$value);

	// Getter for obs_measured.
	public boolean[] get$obs_measured();

	// Setter for obs_measured.
	public void set$obs_measured(boolean[] cv$value);

	// Getter for p.
	public double get$p();

	// Getter for y.
	public int get$y();

	// Setter for y.
	public void set$y(int cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}