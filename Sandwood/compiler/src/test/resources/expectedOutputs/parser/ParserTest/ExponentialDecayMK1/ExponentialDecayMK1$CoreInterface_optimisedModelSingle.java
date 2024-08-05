package org.sandwood.compiler.tests.parser;

interface ExponentialDecayMK1$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for a.
	public double get$a();

	// Setter for a.
	public void set$a(double cv$value);

	// Getter for b.
	public double get$b();

	// Setter for b.
	public void set$b(double cv$value);

	// Getter for decay.
	public double[] get$decay();

	// Setter for decay.
	public void set$decay(double[] cv$value);

	// Getter for decayDetected.
	public double[] get$decayDetected();

	// Setter for decayDetected.
	public void set$decayDetected(double[] cv$value);

	// Getter for fixedFlag$sample10.
	public boolean get$fixedFlag$sample10();

	// Setter for fixedFlag$sample10.
	public void set$fixedFlag$sample10(boolean cv$value);

	// Getter for fixedFlag$sample16.
	public boolean get$fixedFlag$sample16();

	// Setter for fixedFlag$sample16.
	public void set$fixedFlag$sample16(boolean cv$value);

	// Getter for length$decayDetected.
	public int get$length$decayDetected();

	// Setter for length$decayDetected.
	public void set$length$decayDetected(int cv$value);

	// Getter for logProbability$decay.
	public double get$logProbability$decay();

	// Getter for logProbability$exponential.
	public double get$logProbability$exponential();

	// Getter for logProbability$rate.
	public double get$logProbability$rate();

	// Getter for rate.
	public double get$rate();

	// Setter for rate.
	public void set$rate(double cv$value);

	// Getter for samples.
	public int get$samples();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}