package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK14$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$b();
	public void set$b(double cv$value);
	public double get$bias();
	public boolean get$fixedFlag$sample8();
	public void set$fixedFlag$sample8(boolean cv$value);
	public boolean[] get$flips();
	public boolean[] get$flipsMeasured();
	public void set$flipsMeasured(boolean[] cv$value);
	public boolean get$guard1();
	public void set$guard1(boolean cv$value);
	public int get$length$flipsMeasured();
	public void set$length$flipsMeasured(int cv$value);
	public double get$logProbability$b();
	public double get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public int get$samples();
	public void logEvidenceGeneration();
}