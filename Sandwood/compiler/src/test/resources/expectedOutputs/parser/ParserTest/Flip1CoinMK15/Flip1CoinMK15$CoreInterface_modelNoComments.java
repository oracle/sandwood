package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK15$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$b();
	public void set$b(double cv$value);
	public double get$bias();
	public void set$bias(double cv$value);
	public boolean get$fixedFlag$sample12();
	public void set$fixedFlag$sample12(boolean cv$value);
	public boolean get$fixedFlag$sample40();
	public void set$fixedFlag$sample40(boolean cv$value);
	public boolean[] get$flips();
	public void set$flips(boolean[] cv$value);
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