package org.sandwood.compiler.tests.parser;

interface MultinomialBernoulli$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for beta.
	public double[] get$beta();

	// Getter for fixedFlag$sample17.
	public boolean get$fixedFlag$sample17();

	// Setter for fixedFlag$sample17.
	public void set$fixedFlag$sample17(boolean cv$value);

	// Getter for fixedFlag$sample20.
	public boolean get$fixedFlag$sample20();

	// Setter for fixedFlag$sample20.
	public void set$fixedFlag$sample20(boolean cv$value);

	// Getter for fixedFlag$sample42.
	public boolean get$fixedFlag$sample42();

	// Setter for fixedFlag$sample42.
	public void set$fixedFlag$sample42(boolean cv$value);

	// Getter for fixedFlag$sample47.
	public boolean get$fixedFlag$sample47();

	// Setter for fixedFlag$sample47.
	public void set$fixedFlag$sample47(boolean cv$value);

	// Getter for fixedFlag$sample52.
	public boolean get$fixedFlag$sample52();

	// Setter for fixedFlag$sample52.
	public void set$fixedFlag$sample52(boolean cv$value);

	// Getter for length.
	public int get$length();

	// Getter for length$observed.
	public int get$length$observed();

	// Setter for length$observed.
	public void set$length$observed(int cv$value);

	// Getter for logProbability$b1.
	public double get$logProbability$b1();

	// Getter for logProbability$b2.
	public double get$logProbability$b2();

	// Getter for logProbability$b3.
	public double get$logProbability$b3();

	// Getter for logProbability$output.
	public double get$logProbability$output();

	// Getter for logProbability$p.
	public double get$logProbability$p();

	// Getter for logProbability$prior.
	public double get$logProbability$prior();

	// Getter for n.
	public int get$n();

	// Getter for observed.
	public boolean[] get$observed();

	// Setter for observed.
	public void set$observed(boolean[] cv$value);

	// Getter for output.
	public boolean[] get$output();

	// Setter for output.
	public void set$output(boolean[] cv$value);

	// Getter for p.
	public double[] get$p();

	// Setter for p.
	public void set$p(double[] cv$value);

	// Getter for prior.
	public int[] get$prior();

	// Setter for prior.
	public void set$prior(int[] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}