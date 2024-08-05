package org.sandwood.compiler.tests.parser;

interface GaussianMixtureTest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for alpha.
	public double[] get$alpha();

	// Getter for fixedFlag$sample13.
	public boolean get$fixedFlag$sample13();

	// Setter for fixedFlag$sample13.
	public void set$fixedFlag$sample13(boolean cv$value);

	// Getter for fixedFlag$sample23.
	public boolean get$fixedFlag$sample23();

	// Setter for fixedFlag$sample23.
	public void set$fixedFlag$sample23(boolean cv$value);

	// Getter for fixedFlag$sample34.
	public boolean get$fixedFlag$sample34();

	// Setter for fixedFlag$sample34.
	public void set$fixedFlag$sample34(boolean cv$value);

	// Getter for fixedFlag$sample45.
	public boolean get$fixedFlag$sample45();

	// Setter for fixedFlag$sample45.
	public void set$fixedFlag$sample45(boolean cv$value);

	// Getter for fixedFlag$sample49.
	public boolean get$fixedFlag$sample49();

	// Setter for fixedFlag$sample49.
	public void set$fixedFlag$sample49(boolean cv$value);

	// Getter for k.
	public int get$k();

	// Getter for length$xMeasured.
	public int get$length$xMeasured();

	// Setter for length$xMeasured.
	public void set$length$xMeasured(int cv$value);

	// Getter for logProbability$mu.
	public double get$logProbability$mu();

	// Getter for logProbability$phi.
	public double get$logProbability$phi();

	// Getter for logProbability$sigma.
	public double get$logProbability$sigma();

	// Getter for logProbability$x.
	public double get$logProbability$x();

	// Getter for logProbability$z.
	public double get$logProbability$z();

	// Getter for mu.
	public double[] get$mu();

	// Setter for mu.
	public void set$mu(double[] cv$value);

	// Getter for phi.
	public double[] get$phi();

	// Setter for phi.
	public void set$phi(double[] cv$value);

	// Getter for sigma.
	public double[] get$sigma();

	// Setter for sigma.
	public void set$sigma(double[] cv$value);

	// Getter for x.
	public double[] get$x();

	// Setter for x.
	public void set$x(double[] cv$value);

	// Getter for xMeasured.
	public double[] get$xMeasured();

	// Setter for xMeasured.
	public void set$xMeasured(double[] cv$value);

	// Getter for z.
	public int[] get$z();

	// Setter for z.
	public void set$z(int[] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}