package org.sandwood.compiler.tests.parser;

interface Conditional2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for fixedFlag$sample21.
	public boolean get$fixedFlag$sample21();

	// Setter for fixedFlag$sample21.
	public void set$fixedFlag$sample21(boolean cv$value);

	// Getter for fixedFlag$sample4.
	public boolean get$fixedFlag$sample4();

	// Setter for fixedFlag$sample4.
	public void set$fixedFlag$sample4(boolean cv$value);

	// Getter for guard.
	public boolean get$guard();

	// Setter for guard.
	public void set$guard(boolean cv$value);

	// Getter for logProbability$bernoulli.
	public double get$logProbability$bernoulli();

	// Getter for logProbability$guard.
	public double get$logProbability$guard();

	// Getter for logProbability$value.
	public double get$logProbability$value();

	// Getter for logProbability$value2.
	public double get$logProbability$value2();

	// Getter for observedValue.
	public double[] get$observedValue();

	// Setter for observedValue.
	public void set$observedValue(double[] cv$value);

	// Getter for value.
	public double[] get$value();

	// Getter for value2.
	public double[] get$value2();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}