package org.sandwood.compiler.tests.parser;

interface DirichletBernoulli$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public boolean get$fixedFlag$sample13();
	public void set$fixedFlag$sample13(boolean cv$value);
	public boolean get$fixedFlag$sample28();
	public void set$fixedFlag$sample28(boolean cv$value);
	public boolean get$fixedFlag$sample34();
	public void set$fixedFlag$sample34(boolean cv$value);
	public int get$length();
	public int get$length$observed();
	public void set$length$observed(int cv$value);
	public double get$logProbability$b1();
	public double get$logProbability$b2();
	public double get$logProbability$output();
	public double get$logProbability$prior();
	public boolean[] get$observed();
	public void set$observed(boolean[] cv$value);
	public boolean[] get$output();
	public void set$output(boolean[] cv$value);
	public double[] get$prior();
	public void set$prior(double[] cv$value);
	public double[] get$v();
	public void logEvidenceGeneration();
}