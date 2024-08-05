package org.sandwood.compiler.tests.parser;

interface UniformBernoulli$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$a();
	public double get$b();
	public boolean get$fixedFlag$sample16();
	public void set$fixedFlag$sample16(boolean cv$value);
	public boolean get$fixedFlag$sample8();
	public void set$fixedFlag$sample8(boolean cv$value);
	public int get$length$observed();
	public void set$length$observed(int cv$value);
	public double get$logProbability$bernoulli();
	public double get$logProbability$output();
	public double get$logProbability$prior();
	public boolean[] get$observed();
	public void set$observed(boolean[] cv$value);
	public boolean[] get$output();
	public void set$output(boolean[] cv$value);
	public double get$prior();
	public void set$prior(double cv$value);
	public void logEvidenceGeneration();
}