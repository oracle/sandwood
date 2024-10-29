package org.sandwood.compiler.tests.parser;

interface LinearRegressionWrongNameFail$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for b0.
	public double get$b0();

	// Setter for b0.
	public void set$b0(double cv$value);

	// Getter for b1.
	public double get$b1();

	// Setter for b1.
	public void set$b1(double cv$value);

	// Getter for fixedFlag$sample14.
	public boolean get$fixedFlag$sample14();

	// Setter for fixedFlag$sample14.
	public void set$fixedFlag$sample14(boolean cv$value);

	// Getter for fixedFlag$sample18.
	public boolean get$fixedFlag$sample18();

	// Setter for fixedFlag$sample18.
	public void set$fixedFlag$sample18(boolean cv$value);

	// Getter for fixedFlag$sample22.
	public boolean get$fixedFlag$sample22();

	// Setter for fixedFlag$sample22.
	public void set$fixedFlag$sample22(boolean cv$value);

	// Getter for fixedFlag$sample31.
	public boolean get$fixedFlag$sample31();

	// Setter for fixedFlag$sample31.
	public void set$fixedFlag$sample31(boolean cv$value);

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