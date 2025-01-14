package org.sandwood.compiler.tests.parser;

interface Vulcano2012notNormalized$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for Avail.
	public int[][] get$Avail();

	// Setter for Avail.
	public void set$Avail(int[][] cv$value);

	// Getter for ObsSales.
	public int[][] get$ObsSales();

	// Setter for ObsSales.
	public void set$ObsSales(int[][] cv$value);

	// Getter for Sales.
	public int[][] get$Sales();

	// Getter for T.
	public int get$T();

	// Setter for T.
	public void set$T(int cv$value);

	// Getter for arrivals.
	public int[] get$arrivals();

	// Setter for arrivals.
	public void set$arrivals(int[] cv$value);

	// Getter for exped.
	public double[] get$exped();

	// Getter for fixedFlag$sample25.
	public boolean get$fixedFlag$sample25();

	// Setter for fixedFlag$sample25.
	public void set$fixedFlag$sample25(boolean cv$value);

	// Getter for fixedFlag$sample43.
	public boolean get$fixedFlag$sample43();

	// Setter for fixedFlag$sample43.
	public void set$fixedFlag$sample43(boolean cv$value);

	// Getter for fixedFlag$sample51.
	public boolean get$fixedFlag$sample51();

	// Setter for fixedFlag$sample51.
	public void set$fixedFlag$sample51(boolean cv$value);

	// Getter for fixedFlag$sample98.
	public boolean get$fixedFlag$sample98();

	// Setter for fixedFlag$sample98.
	public void set$fixedFlag$sample98(boolean cv$value);

	// Getter for lambda.
	public double[] get$lambda();

	// Setter for lambda.
	public void set$lambda(double[] cv$value);

	// Getter for logProbability$Sales.
	public double get$logProbability$Sales();

	// Getter for logProbability$arrivals.
	public double get$logProbability$arrivals();

	// Getter for logProbability$exped.
	public double get$logProbability$exped();

	// Getter for logProbability$lambda.
	public double get$logProbability$lambda();

	// Getter for logProbability$ut.
	public double get$logProbability$ut();

	// Getter for logProbability$weekly_sales.
	public double get$logProbability$weekly_sales();

	// Getter for noProducts.
	public int get$noProducts();

	// Setter for noProducts.
	public void set$noProducts(int cv$value);

	// Getter for s.
	public int get$s();

	// Setter for s.
	public void set$s(int cv$value);

	// Getter for ut.
	public double[] get$ut();

	// Setter for ut.
	public void set$ut(double[] cv$value);

	// Getter for weekly_sales.
	public int[][] get$weekly_sales();

	// Setter for weekly_sales.
	public void set$weekly_sales(int[][] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}