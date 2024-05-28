package org.sandwood.compiler.tests.parser;

interface LogitRegressionTest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double get$bias();

	// Setter for bias.
	public void set$bias(double cv$value);

	// Getter for fixedFlag$sample105.
	public boolean get$fixedFlag$sample105();

	// Setter for fixedFlag$sample105.
	public void set$fixedFlag$sample105(boolean cv$value);

	// Getter for fixedFlag$sample46.
	public boolean get$fixedFlag$sample46();

	// Setter for fixedFlag$sample46.
	public void set$fixedFlag$sample46(boolean cv$value);

	// Getter for fixedFlag$sample53.
	public boolean get$fixedFlag$sample53();

	// Setter for fixedFlag$sample53.
	public void set$fixedFlag$sample53(boolean cv$value);

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