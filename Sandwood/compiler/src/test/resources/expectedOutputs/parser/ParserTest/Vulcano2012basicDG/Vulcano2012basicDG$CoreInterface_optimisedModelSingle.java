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

	// Getter for fixedFlag$sample127.
	public boolean get$fixedFlag$sample127();

	// Setter for fixedFlag$sample127.
	public void set$fixedFlag$sample127(boolean cv$value);

	// Getter for fixedFlag$sample129.
	public boolean get$fixedFlag$sample129();

	// Setter for fixedFlag$sample129.
	public void set$fixedFlag$sample129(boolean cv$value);

	// Getter for fixedFlag$sample181.
	public boolean get$fixedFlag$sample181();

	// Setter for fixedFlag$sample181.
	public void set$fixedFlag$sample181(boolean cv$value);

	// Getter for fixedFlag$sample45.
	public boolean get$fixedFlag$sample45();

	// Setter for fixedFlag$sample45.
	public void set$fixedFlag$sample45(boolean cv$value);

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