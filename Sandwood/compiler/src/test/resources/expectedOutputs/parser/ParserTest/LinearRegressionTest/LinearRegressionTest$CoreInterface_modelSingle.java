package org.sandwood.compiler.tests.parser;

interface LinearRegressionTest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double get$bias();

	// Setter for bias.
	public void set$bias(double cv$value);

	// Getter for fixedFlag$sample28.
	public boolean get$fixedFlag$sample28();

	// Setter for fixedFlag$sample28.
	public void set$fixedFlag$sample28(boolean cv$value);

	// Getter for fixedFlag$sample35.
	public boolean get$fixedFlag$sample35();

	// Setter for fixedFlag$sample35.
	public void set$fixedFlag$sample35(boolean cv$value);

	// Getter for fixedFlag$sample39.
	public boolean get$fixedFlag$sample39();

	// Setter for fixedFlag$sample39.
	public void set$fixedFlag$sample39(boolean cv$value);

	// Getter for fixedFlag$sample63.
	public boolean get$fixedFlag$sample63();

	// Setter for fixedFlag$sample63.
	public void set$fixedFlag$sample63(boolean cv$value);

	// Getter for k.
	public int get$k();

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$tau.
	public double get$logProbability$tau();

	// Getter for logProbability$weights.
	public double get$logProbability$weights();

	// Getter for logProbability$y.
	public double get$logProbability$y();

	// Getter for n.
	public int get$n();

	// Getter for tau.
	public double get$tau();

	// Setter for tau.
	public void set$tau(double cv$value);

	// Getter for weights.
	public double[] get$weights();

	// Setter for weights.
	public void set$weights(double[] cv$value);

	// Getter for x.
	public double[][] get$x();

	// Setter for x.
	public void set$x(double[][] cv$value);

	// Getter for y.
	public double[] get$y();

	// Setter for y.
	public void set$y(double[] cv$value);

	// Getter for yMeasured.
	public double[] get$yMeasured();

	// Setter for yMeasured.
	public void set$yMeasured(double[] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}