package org.sandwood.compiler.tests.parser;

interface Deterministic$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for a.
	public int[] get$a();

	// Setter for a.
	public void set$a(int[] cv$value);

	// Getter for b.
	public int[] get$b();

	// Setter for b.
	public void set$b(int[] cv$value);

	// Getter for fixedFlag$sample18.
	public boolean get$fixedFlag$sample18();

	// Setter for fixedFlag$sample18.
	public void set$fixedFlag$sample18(boolean cv$value);

	// Getter for fixedFlag$sample35.
	public boolean get$fixedFlag$sample35();

	// Setter for fixedFlag$sample35.
	public void set$fixedFlag$sample35(boolean cv$value);

	// Getter for fixedFlag$sample48.
	public boolean get$fixedFlag$sample48();

	// Setter for fixedFlag$sample48.
	public void set$fixedFlag$sample48(boolean cv$value);

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