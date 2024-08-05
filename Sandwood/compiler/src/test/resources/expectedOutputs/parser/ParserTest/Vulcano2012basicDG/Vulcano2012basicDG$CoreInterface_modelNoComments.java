package org.sandwood.compiler.tests.parser;

interface Vulcano2012basicDG$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public int[][] get$ObsSales();
	public void set$ObsSales(int[][] cv$value);
	public int[] get$arrivals();
	public void set$arrivals(int[] cv$value);
	public boolean[][] get$avail();
	public void set$avail(boolean[][] cv$value);
	public boolean get$fixedFlag$sample124();
	public void set$fixedFlag$sample124(boolean cv$value);
	public boolean get$fixedFlag$sample34();
	public void set$fixedFlag$sample34(boolean cv$value);
	public boolean get$fixedFlag$sample87();
	public void set$fixedFlag$sample87(boolean cv$value);
	public boolean get$fixedFlag$sample89();
	public void set$fixedFlag$sample89(boolean cv$value);
	public double[] get$lambda();
	public void set$lambda(double[] cv$value);
	public double get$logProbability$arrivals();
	public double get$logProbability$lambda();
	public double get$logProbability$weekly_sales();
	public int get$numTimeSteps();
	public double get$r();
	public int[][] get$weekly_sales();
	public void set$weekly_sales(int[][] cv$value);
	public void logEvidenceGeneration();
}