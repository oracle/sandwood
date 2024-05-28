package org.sandwood.compiler.tests.parser;

interface Conditional5$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for a.
	public double get$a();

	// Setter for a.
	public void set$a(double cv$value);

	// Getter for b.
	public double get$b();

	// Setter for b.
	public void set$b(double cv$value);

	// Getter for fixedFlag$sample13.
	public boolean get$fixedFlag$sample13();

	// Setter for fixedFlag$sample13.
	public void set$fixedFlag$sample13(boolean cv$value);

	// Getter for fixedFlag$sample5.
	public boolean get$fixedFlag$sample5();

	// Setter for fixedFlag$sample5.
	public void set$fixedFlag$sample5(boolean cv$value);

	// Getter for fixedFlag$sample9.
	public boolean get$fixedFlag$sample9();

	// Setter for fixedFlag$sample9.
	public void set$fixedFlag$sample9(boolean cv$value);

	// Getter for guard.
	public boolean get$guard();

	// Setter for guard.
	public void set$guard(boolean cv$value);

	// Getter for logProbability$a.
	public double get$logProbability$a();

	// Getter for logProbability$b.
	public double get$logProbability$b();

	// Getter for logProbability$bernoulli.
	public double get$logProbability$bernoulli();

	// Getter for logProbability$guard.
	public double get$logProbability$guard();

	// Getter for logProbability$value.
	public double get$logProbability$value();

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

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}