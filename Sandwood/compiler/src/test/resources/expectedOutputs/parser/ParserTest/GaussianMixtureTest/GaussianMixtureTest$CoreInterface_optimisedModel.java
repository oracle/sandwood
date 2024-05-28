package org.sandwood.compiler.tests.parser;

interface GaussianMixtureTest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for alpha.
	public double[] get$alpha();

	// Getter for fixedFlag$sample20.
	public boolean get$fixedFlag$sample20();

	// Setter for fixedFlag$sample20.
	public void set$fixedFlag$sample20(boolean cv$value);

	// Getter for fixedFlag$sample37.
	public boolean get$fixedFlag$sample37();

	// Setter for fixedFlag$sample37.
	public void set$fixedFlag$sample37(boolean cv$value);

	// Getter for fixedFlag$sample55.
	public boolean get$fixedFlag$sample55();

	// Setter for fixedFlag$sample55.
	public void set$fixedFlag$sample55(boolean cv$value);

	// Getter for fixedFlag$sample73.
	public boolean get$fixedFlag$sample73();

	// Setter for fixedFlag$sample73.
	public void set$fixedFlag$sample73(boolean cv$value);

	// Getter for fixedFlag$sample77.
	public boolean get$fixedFlag$sample77();

	// Setter for fixedFlag$sample77.
	public void set$fixedFlag$sample77(boolean cv$value);

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