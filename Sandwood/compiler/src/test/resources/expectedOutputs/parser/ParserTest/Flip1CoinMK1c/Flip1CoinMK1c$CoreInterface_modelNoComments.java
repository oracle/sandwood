package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK1c$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$a();
	public void set$a(double cv$value);
	public double get$b();
	public void set$b(double cv$value);
	public boolean get$fixedFlag$sample10();
	public void set$fixedFlag$sample10(boolean cv$value);
	public boolean get$fixedFlag$sample16();
	public void set$fixedFlag$sample16(boolean cv$value);
	public boolean[] get$flips();
	public void set$flips(boolean[] cv$value);
	public boolean[] get$flipsMeasured();
	public void set$flipsMeasured(boolean[] cv$value);
	public int get$length$flipsMeasured();
	public void set$length$flipsMeasured(int cv$value);
	public double get$logProbability$bernoulli();
	public double get$logProbability$flips();
	public int get$samples();
	public void logEvidenceGeneration();
}