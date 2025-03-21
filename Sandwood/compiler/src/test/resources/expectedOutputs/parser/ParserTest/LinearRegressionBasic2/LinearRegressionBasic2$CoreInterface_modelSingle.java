package org.sandwood.compiler.tests.parser;

interface LinearRegressionBasic2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for b0.
	public double get$b0();

	// Setter for b0.
	public void set$b0(double cv$value);

	// Getter for b1.
	public double get$b1();

	// Setter for b1.
	public void set$b1(double cv$value);

	// Getter for fixedFlag$sample11.
	public boolean get$fixedFlag$sample11();

	// Setter for fixedFlag$sample11.
	public void set$fixedFlag$sample11(boolean cv$value);

	// Getter for fixedFlag$sample16.
	public boolean get$fixedFlag$sample16();

	// Setter for fixedFlag$sample16.
	public void set$fixedFlag$sample16(boolean cv$value);

	// Getter for fixedFlag$sample7.
	public boolean get$fixedFlag$sample7();

	// Setter for fixedFlag$sample7.
	public void set$fixedFlag$sample7(boolean cv$value);

	// Getter for logProbability$b0.
	public double get$logProbability$b0();

	// Getter for logProbability$b1.
	public double get$logProbability$b1();

	// Getter for logProbability$variance.
	public double get$logProbability$variance();

	// Getter for logProbability$y.
	public double get$logProbability$y();

	// Getter for noSamples.
	public int get$noSamples();

	// Getter for variance.
	public double get$variance();

	// Setter for variance.
	public void set$variance(double cv$value);

	// Getter for x.
	public double[] get$x();

	// Setter for x.
	public void set$x(double[] cv$value);

	// Getter for y.
	public double[] get$y();

	// Getter for yMeasured.
	public double[] get$yMeasured();

	// Setter for yMeasured.
	public void set$yMeasured(double[] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}