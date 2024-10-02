package org.sandwood.compiler.tests.parser;

interface Conditional6$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for a.
	public double get$a();

	// Getter for b.
	public double get$b();

	// Getter for fixedFlag$sample5.
	public boolean get$fixedFlag$sample5();

	// Setter for fixedFlag$sample5.
	public void set$fixedFlag$sample5(boolean cv$value);

	// Getter for guard.
	public boolean get$guard();

	// Setter for guard.
	public void set$guard(boolean cv$value);

	// Getter for logProbability$bernoulli.
	public double get$logProbability$bernoulli();

	// Getter for logProbability$guard.
	public double get$logProbability$guard();

	// Getter for observedGuard.
	public boolean get$observedGuard();

	// Setter for observedGuard.
	public void set$observedGuard(boolean cv$value);

	// Getter for observedValue.
	public double get$observedValue();

	// Setter for observedValue.
	public void set$observedValue(double cv$value);

	// Getter for value.
	public double get$value();

	// Getter for value2.
	public double get$value2();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}