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

	// Setter for denom.
	public void set$denom(double cv$value);

	// Getter for exped.
	public double[] get$exped();

	// Setter for exped.
	public void set$exped(double[] cv$value);

	// Getter for fixedFlag$sample25.
	public boolean get$fixedFlag$sample25();

	// Setter for fixedFlag$sample25.
	public void set$fixedFlag$sample25(boolean cv$value);

	// Getter for fixedFlag$sample53.
	public boolean get$fixedFlag$sample53();

	// Setter for fixedFlag$sample53.
	public void set$fixedFlag$sample53(boolean cv$value);

	// Getter for fixedFlag$sample61.
	public boolean get$fixedFlag$sample61();

	// Setter for fixedFlag$sample61.
	public void set$fixedFlag$sample61(boolean cv$value);

	// Getter for fixedFlag$sample85.
	public boolean get$fixedFlag$sample85();

	// Setter for fixedFlag$sample85.
	public void set$fixedFlag$sample85(boolean cv$value);

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