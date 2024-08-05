package org.sandwood.compiler.tests.parser;

interface ParallelMK2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public boolean get$fixedFlag$sample24();
	public void set$fixedFlag$sample24(boolean cv$value);
	public boolean get$fixedFlag$sample30();
	public void set$fixedFlag$sample30(boolean cv$value);
	public double[] get$generated();
	public void set$generated(double[] cv$value);
	public double[] get$indirection();
	public void set$indirection(double[] cv$value);
	public int get$length$observed();
	public void set$length$observed(int cv$value);
	public double get$logProbability$generated();
	public double get$logProbability$indirection();
	public double get$logProbability$sample();
	public double[] get$observed();
	public void set$observed(double[] cv$value);
	public double[] get$sample();
	public void set$sample(double[] cv$value);
	public void logEvidenceGeneration();
}