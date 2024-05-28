package org.sandwood.compiler.tests.parser;

interface DirichletBernoulli$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public boolean get$fixedFlag$sample20();
	public void set$fixedFlag$sample20(boolean cv$value);
	public boolean get$fixedFlag$sample42();
	public void set$fixedFlag$sample42(boolean cv$value);
	public boolean get$fixedFlag$sample55();
	public void set$fixedFlag$sample55(boolean cv$value);
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