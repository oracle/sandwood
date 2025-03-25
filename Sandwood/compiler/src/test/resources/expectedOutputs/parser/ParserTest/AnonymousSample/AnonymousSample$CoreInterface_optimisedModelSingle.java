package org.sandwood.compiler.tests.parser;

interface AnonymousSample$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for amounts1.
	public double[] get$amounts1();

	// Getter for amounts2.
	public double[] get$amounts2();

	// Getter for fixedFlag$sample15.
	public boolean get$fixedFlag$sample15();

	// Setter for fixedFlag$sample15.
	public void set$fixedFlag$sample15(boolean cv$value);

	// Getter for fixedFlag$sample21.
	public boolean get$fixedFlag$sample21();

	// Setter for fixedFlag$sample21.
	public void set$fixedFlag$sample21(boolean cv$value);

	// Getter for fixedFlag$sample9.
	public boolean get$fixedFlag$sample9();

	// Setter for fixedFlag$sample9.
	public void set$fixedFlag$sample9(boolean cv$value);

	// Getter for length$obsAmounts1.
	public int get$length$obsAmounts1();

	// Setter for length$obsAmounts1.
	public void set$length$obsAmounts1(int cv$value);

	// Getter for logProbability$amounts1.
	public double get$logProbability$amounts1();

	// Getter for logProbability$amounts2.
	public double get$logProbability$amounts2();

	// Getter for logProbability$mean1.
	public double get$logProbability$mean1();

	// Getter for logProbability$mean2.
	public double get$logProbability$mean2();

	// Getter for logProbability$priorSigma2.
	public double get$logProbability$priorSigma2();

	// Getter for mean1.
	public double get$mean1();

	// Setter for mean1.
	public void set$mean1(double cv$value);

	// Getter for mean2.
	public double get$mean2();

	// Setter for mean2.
	public void set$mean2(double cv$value);

	// Getter for n.
	public int get$n();

	// Getter for obsAmounts1.
	public double[] get$obsAmounts1();

	// Setter for obsAmounts1.
	public void set$obsAmounts1(double[] cv$value);

	// Getter for obsAmounts2.
	public double[] get$obsAmounts2();

	// Setter for obsAmounts2.
	public void set$obsAmounts2(double[] cv$value);

	// Getter for priorSigma2.
	public double get$priorSigma2();

	// Setter for priorSigma2.
	public void set$priorSigma2(double cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}