package org.sandwood.compiler.tests.parser;

interface DirichletBernoulli$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public boolean get$fixedFlag$sample17();
	public void set$fixedFlag$sample17(boolean cv$value);
	public boolean get$fixedFlag$sample38();
	public void set$fixedFlag$sample38(boolean cv$value);
	public boolean get$fixedFlag$sample51();
	public void set$fixedFlag$sample51(boolean cv$value);
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