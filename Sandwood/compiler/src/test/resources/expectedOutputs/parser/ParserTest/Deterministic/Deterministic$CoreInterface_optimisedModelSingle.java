package org.sandwood.compiler.tests.parser;

interface Deterministic$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for a.
	public int[] get$a();

	// Setter for a.
	public void set$a(int[] cv$value);

	// Getter for b.
	public int[] get$b();

	// Getter for fixedFlag$sample32.
	public boolean get$fixedFlag$sample32();

	// Setter for fixedFlag$sample32.
	public void set$fixedFlag$sample32(boolean cv$value);

	// Getter for fixedFlag$sample58.
	public boolean get$fixedFlag$sample58();

	// Setter for fixedFlag$sample58.
	public void set$fixedFlag$sample58(boolean cv$value);

	// Getter for fixedFlag$sample78.
	public boolean get$fixedFlag$sample78();

	// Setter for fixedFlag$sample78.
	public void set$fixedFlag$sample78(boolean cv$value);

	// Getter for flips.
	public boolean[] get$flips();

	// Setter for flips.
	public void set$flips(boolean[] cv$value);

	// Getter for flipsMeasured.
	public boolean[] get$flipsMeasured();

	// Setter for flipsMeasured.
	public void set$flipsMeasured(boolean[] cv$value);

	// Getter for logProbability$a.
	public double get$logProbability$a();

	// Getter for logProbability$b.
	public double get$logProbability$b();

	// Getter for logProbability$flips.
	public double get$logProbability$flips();

	// Getter for logProbability$m.
	public double get$logProbability$m();

	// Getter for m.
	public double[][] get$m();

	// Setter for m.
	public void set$m(double[][] cv$value);

	// Getter for n.
	public int get$n();

	// Setter for n.
	public void set$n(int cv$value);

	// Getter for states.
	public int get$states();

	// Getter for v.
	public double[] get$v();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}