package org.sandwood.compiler.tests.parser;

interface LogitRegressionTest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double get$bias();

	// Setter for bias.
	public void set$bias(double cv$value);

	// Getter for fixedFlag$sample31.
	public boolean get$fixedFlag$sample31();

	// Setter for fixedFlag$sample31.
	public void set$fixedFlag$sample31(boolean cv$value);

	// Getter for fixedFlag$sample38.
	public boolean get$fixedFlag$sample38();

	// Setter for fixedFlag$sample38.
	public void set$fixedFlag$sample38(boolean cv$value);

	// Getter for fixedFlag$sample71.
	public boolean get$fixedFlag$sample71();

	// Setter for fixedFlag$sample71.
	public void set$fixedFlag$sample71(boolean cv$value);

	// Getter for k.
	public int get$k();

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$weights.
	public double get$logProbability$weights();

	// Getter for logProbability$y.
	public double get$logProbability$y();

	// Getter for n.
	public int get$n();

	// Getter for weights.
	public double[] get$weights();

	// Setter for weights.
	public void set$weights(double[] cv$value);

	// Getter for x.
	public double[][] get$x();

	// Setter for x.
	public void set$x(double[][] cv$value);

	// Getter for y.
	public boolean[][] get$y();

	// Setter for y.
	public void set$y(boolean[][] cv$value);

	// Getter for yMeasured.
	public boolean[][] get$yMeasured();

	// Setter for yMeasured.
	public void set$yMeasured(boolean[][] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}