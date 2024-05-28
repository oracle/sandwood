package org.sandwood.compiler.tests.parser;

interface Vulcano2012basic$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for Avail.
	public int[][] get$Avail();

	// Setter for Avail.
	public void set$Avail(int[][] cv$value);

	// Getter for ObsSales.
	public double[][] get$ObsSales();

	// Setter for ObsSales.
	public void set$ObsSales(double[][] cv$value);

	// Getter for Sales.
	public double[][] get$Sales();

	// Setter for Sales.
	public void set$Sales(double[][] cv$value);

	// Getter for T.
	public int get$T();

	// Setter for T.
	public void set$T(int cv$value);

	// Getter for arrivals.
	public int[] get$arrivals();

	// Setter for arrivals.
	public void set$arrivals(int[] cv$value);

	// Getter for denom.
	public double get$denom();

	// Getter for exped.
	public double[] get$exped();

	// Getter for fixedFlag$sample137.
	public boolean get$fixedFlag$sample137();

	// Setter for fixedFlag$sample137.
	public void set$fixedFlag$sample137(boolean cv$value);

	// Getter for fixedFlag$sample32.
	public boolean get$fixedFlag$sample32();

	// Setter for fixedFlag$sample32.
	public void set$fixedFlag$sample32(boolean cv$value);

	// Getter for fixedFlag$sample77.
	public boolean get$fixedFlag$sample77();

	// Setter for fixedFlag$sample77.
	public void set$fixedFlag$sample77(boolean cv$value);

	// Getter for fixedFlag$sample92.
	public boolean get$fixedFlag$sample92();

	// Setter for fixedFlag$sample92.
	public void set$fixedFlag$sample92(boolean cv$value);

	// Getter for lambda.
	public double[] get$lambda();

	// Setter for lambda.
	public void set$lambda(double[] cv$value);

	// Getter for logProbability$Sales.
	public double get$logProbability$Sales();

	// Getter for logProbability$arrivals.
	public double get$logProbability$arrivals();

	// Getter for logProbability$denom.
	public double get$logProbability$denom();

	// Getter for logProbability$exped.
	public double get$logProbability$exped();

	// Getter for logProbability$lambda.
	public double get$logProbability$lambda();

	// Getter for logProbability$sum.
	public double get$logProbability$sum();

	// Getter for logProbability$ut.
	public double get$logProbability$ut();

	// Getter for noProducts.
	public int get$noProducts();

	// Setter for noProducts.
	public void set$noProducts(int cv$value);

	// Getter for s.
	public int get$s();

	// Setter for s.
	public void set$s(int cv$value);

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