package org.sandwood.compiler.tests.parser;

interface Vulcano2012basic$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

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

	// Getter for exped.
	public double[] get$exped();

	// Getter for expedNorm.
	public double[] get$expedNorm();

	// Getter for fixedFlag$sample26.
	public boolean get$fixedFlag$sample26();

	// Setter for fixedFlag$sample26.
	public void set$fixedFlag$sample26(boolean cv$value);

	// Getter for logProbability$Sales.
	public double get$logProbability$Sales();

	// Getter for logProbability$exped.
	public double get$logProbability$exped();

	// Getter for logProbability$expedNorm.
	public double get$logProbability$expedNorm();

	// Getter for logProbability$sum.
	public double get$logProbability$sum();

	// Getter for logProbability$ut.
	public double get$logProbability$ut();

	// Getter for noProducts.
	public int get$noProducts();

	// Setter for noProducts.
	public void set$noProducts(int cv$value);

	// Getter for r.
	public double get$r();

	// Setter for r.
	public void set$r(double cv$value);

	// Getter for sales_sum.
	public int[] get$sales_sum();

	// Getter for sum.
	public double get$sum();

	// Getter for ut.
	public double[] get$ut();

	// Setter for ut.
	public void set$ut(double[] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}