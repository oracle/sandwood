package org.sandwood.compiler.tests.parser;

interface DiscreteChoice$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for ObsChoices.
	public int[] get$ObsChoices();

	// Setter for ObsChoices.
	public void set$ObsChoices(int[] cv$value);

	// Getter for choices.
	public int[] get$choices();

	// Setter for choices.
	public void set$choices(int[] cv$value);

	// Getter for exped.
	public double[] get$exped();

	// Setter for exped.
	public void set$exped(double[] cv$value);

	// Getter for fixedFlag$sample19.
	public boolean get$fixedFlag$sample19();

	// Setter for fixedFlag$sample19.
	public void set$fixedFlag$sample19(boolean cv$value);

	// Getter for fixedFlag$sample49.
	public boolean get$fixedFlag$sample49();

	// Setter for fixedFlag$sample49.
	public void set$fixedFlag$sample49(boolean cv$value);

	// Getter for logProbability$choices.
	public double get$logProbability$choices();

	// Getter for logProbability$exped.
	public double get$logProbability$exped();

	// Getter for logProbability$prob.
	public double get$logProbability$prob();

	// Getter for logProbability$sum.
	public double get$logProbability$sum();

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
	public double[] get$prob();

	// Setter for prob.
	public void set$prob(double[] cv$value);

	// Getter for sum.
	public double get$sum();

	// Setter for sum.
	public void set$sum(double cv$value);

	// Getter for ut.
	public double[] get$ut();

	// Setter for ut.
	public void set$ut(double[] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}