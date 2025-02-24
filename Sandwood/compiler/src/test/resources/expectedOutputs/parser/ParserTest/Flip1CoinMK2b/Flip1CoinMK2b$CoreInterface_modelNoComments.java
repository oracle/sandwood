package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK2b$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$a();
	public double get$b();
	public double get$bias();
	public void set$bias(double cv$value);
	public boolean get$fixedFlag$sample19();
	public void set$fixedFlag$sample19(boolean cv$value);
	public boolean get$fixedFlag$sample5();
	public void set$fixedFlag$sample5(boolean cv$value);
	public boolean[] get$flips();
	public void set$flips(boolean[] cv$value);
	public boolean[] get$flipsMeasured();
	public void set$flipsMeasured(boolean[] cv$value);
	public int get$length$flipsMeasured();
	public void set$length$flipsMeasured(int cv$value);
	public double get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public int get$samples();
	public void logEvidenceGeneration();
}