package org.sandwood.compiler.tests.parser;

interface Vulcano2012basicDG$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for ObsSales.
	public int[][] get$ObsSales();

	// Setter for ObsSales.
	public void set$ObsSales(int[][] cv$value);

	// Getter for arrivals.
	public int[] get$arrivals();

	// Setter for arrivals.
	public void set$arrivals(int[] cv$value);

	// Getter for avail.
	public boolean[][] get$avail();

	// Setter for avail.
	public void set$avail(boolean[][] cv$value);

	// Getter for fixedFlag$sample125.
	public boolean get$fixedFlag$sample125();

	// Setter for fixedFlag$sample125.
	public void set$fixedFlag$sample125(boolean cv$value);

	// Getter for fixedFlag$sample34.
	public boolean get$fixedFlag$sample34();

	// Setter for fixedFlag$sample34.
	public void set$fixedFlag$sample34(boolean cv$value);

	// Getter for fixedFlag$sample88.
	public boolean get$fixedFlag$sample88();

	// Setter for fixedFlag$sample88.
	public void set$fixedFlag$sample88(boolean cv$value);

	// Getter for fixedFlag$sample90.
	public boolean get$fixedFlag$sample90();

	// Setter for fixedFlag$sample90.
	public void set$fixedFlag$sample90(boolean cv$value);

	// Getter for lambda.
	public double[] get$lambda();

	// Setter for lambda.
	public void set$lambda(double[] cv$value);

	// Getter for logProbability$arrivals.
	public double get$logProbability$arrivals();

	// Getter for logProbability$lambda.
	public double get$logProbability$lambda();

	// Getter for logProbability$weekly_sales.
	public double get$logProbability$weekly_sales();

	// Getter for numTimeSteps.
	public int get$numTimeSteps();

	// Getter for r.
	public double get$r();

	// Getter for weekly_sales.
	public int[][] get$weekly_sales();

	// Setter for weekly_sales.
	public void set$weekly_sales(int[][] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}