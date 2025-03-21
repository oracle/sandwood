package org.sandwood.compiler.tests.parser;

interface UniformBernoulli$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$a();
	public double get$b();
	public boolean get$fixedFlag$sample5();
	public void set$fixedFlag$sample5(boolean cv$value);
	public int get$length$observed();
	public void set$length$observed(int cv$value);
	public double get$logProbability$bernoulli();
	public double get$logProbability$output();
	public double get$logProbability$prior();
	public boolean[] get$observed();
	public void set$observed(boolean[] cv$value);
	public boolean[] get$output();
	public double get$prior();
	public void set$prior(double cv$value);
	public void logEvidenceGeneration();
}