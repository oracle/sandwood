package org.sandwood.compiler.tests.parser;

interface ParallelMK5$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public boolean get$fixedFlag$sample39();
	public void set$fixedFlag$sample39(boolean cv$value);
	public boolean get$fixedFlag$sample63();
	public void set$fixedFlag$sample63(boolean cv$value);
	public int[] get$generated();
	public void set$generated(int[] cv$value);
	public double[][] get$indirection1();
	public void set$indirection1(double[][] cv$value);
	public double[][] get$indirection2();
	public void set$indirection2(double[][] cv$value);
	public int get$length$observed();
	public void set$length$observed(int cv$value);
	public double get$logProbability$generated();
	public double get$logProbability$indirection1();
	public double get$logProbability$indirection2();
	public int[] get$observed();
	public void set$observed(int[] cv$value);
	public void logEvidenceGeneration();
}