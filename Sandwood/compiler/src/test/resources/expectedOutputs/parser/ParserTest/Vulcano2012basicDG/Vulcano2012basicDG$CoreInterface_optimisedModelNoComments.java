package org.sandwood.compiler.tests.parser;

interface Vulcano2012basicDG$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public int[][] get$ObsSales();
	public void set$ObsSales(int[][] cv$value);
	public int[] get$arrivals();
	public void set$arrivals(int[] cv$value);
	public boolean[][] get$avail();
	public void set$avail(boolean[][] cv$value);
	public boolean get$fixedFlag$sample112();
	public void set$fixedFlag$sample112(boolean cv$value);
	public boolean get$fixedFlag$sample114();
	public void set$fixedFlag$sample114(boolean cv$value);
	public boolean get$fixedFlag$sample166();
	public void set$fixedFlag$sample166(boolean cv$value);
	public boolean get$fixedFlag$sample32();
	public void set$fixedFlag$sample32(boolean cv$value);
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