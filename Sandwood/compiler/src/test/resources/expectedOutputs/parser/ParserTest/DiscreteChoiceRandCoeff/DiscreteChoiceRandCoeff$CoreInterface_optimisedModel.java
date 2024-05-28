package org.sandwood.compiler.tests.parser;

interface DiscreteChoiceRandCoeff$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for ObsChoices.
	public int[] get$ObsChoices();

	// Setter for ObsChoices.
	public void set$ObsChoices(int[] cv$value);

	// Getter for Prices.
	public int[][] get$Prices();

	// Setter for Prices.
	public void set$Prices(int[][] cv$value);

	// Getter for b.
	public double get$b();

	// Setter for b.
	public void set$b(double cv$value);

	// Getter for beta.
	public double[] get$beta();

	// Setter for beta.
	public void set$beta(double[] cv$value);

	// Getter for choices.
	public int[] get$choices();

	// Setter for choices.
	public void set$choices(int[] cv$value);

	// Getter for fixedFlag$sample111.
	public boolean get$fixedFlag$sample111();

	// Setter for fixedFlag$sample111.
	public void set$fixedFlag$sample111(boolean cv$value);

	// Getter for fixedFlag$sample29.
	public boolean get$fixedFlag$sample29();

	// Setter for fixedFlag$sample29.
	public void set$fixedFlag$sample29(boolean cv$value);

	// Getter for fixedFlag$sample36.
	public boolean get$fixedFlag$sample36();

	// Setter for fixedFlag$sample36.
	public void set$fixedFlag$sample36(boolean cv$value);

	// Getter for fixedFlag$sample42.
	public boolean get$fixedFlag$sample42();

	// Setter for fixedFlag$sample42.
	public void set$fixedFlag$sample42(boolean cv$value);

	// Getter for fixedFlag$sample55.
	public boolean get$fixedFlag$sample55();

	// Setter for fixedFlag$sample55.
	public void set$fixedFlag$sample55(boolean cv$value);

	// Getter for logProbability$b.
	public double get$logProbability$b();

	// Getter for logProbability$beta.
	public double get$logProbability$beta();

	// Getter for logProbability$choices.
	public double get$logProbability$choices();

	// Getter for logProbability$prob.
	public double get$logProbability$prob();

	// Getter for logProbability$sigma.
	public double get$logProbability$sigma();

	// Getter for logProbability$ut.
	public double get$logProbability$ut();

	// Getter for noObs.
	public int get$noObs();

	// Setter for noObs.
	public void set$noObs(int cv$value);

	// Getter for noProducts.
	public int get$noProducts();

	// Setter for noProducts.
	public void set$noProducts(int cv$value);

	// Getter for prob.
	public double[][] get$prob();

	// Getter for sigma.
	public double get$sigma();

	// Setter for sigma.
	public void set$sigma(double cv$value);

	// Getter for ut.
	public double[] get$ut();

	// Setter for ut.
	public void set$ut(double[] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}