package org.sandwood.compiler.tests.parser;

interface Vulcano2012notNormalized$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public int[][] get$Avail();
	public void set$Avail(int[][] cv$value);
	public int[][] get$ObsSales();
	public void set$ObsSales(int[][] cv$value);
	public int[][] get$Sales();
	public int get$T();
	public void set$T(int cv$value);
	public int[] get$arrivals();
	public void set$arrivals(int[] cv$value);
	public double[] get$exped();
	public boolean get$fixedFlag$sample22();
	public void set$fixedFlag$sample22(boolean cv$value);
	public boolean get$fixedFlag$sample54();
	public void set$fixedFlag$sample54(boolean cv$value);
	public boolean get$fixedFlag$sample69();
	public void set$fixedFlag$sample69(boolean cv$value);
	public double[] get$lambda();
	public void set$lambda(double[] cv$value);
	public double get$logProbability$Sales();
	public double get$logProbability$arrivals();
	public double get$logProbability$exped();
	public double get$logProbability$lambda();
	public double get$logProbability$ut();
	public double get$logProbability$weekly_sales();
	public int get$noProducts();
	public void set$noProducts(int cv$value);
	public int get$s();
	public void set$s(int cv$value);
	public double[] get$ut();
	public void set$ut(double[] cv$value);
	public int[][] get$weekly_sales();
	public void logEvidenceGeneration();
}