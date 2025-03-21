package org.sandwood.compiler.tests.parser;

interface Flip1CoinArrayCopyPassMK2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$a();
	public double get$b();
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public boolean get$fixedFlag$sample10();
	public void set$fixedFlag$sample10(boolean cv$value);
	public boolean[] get$flips();
	public boolean[] get$flipsMeasured();
	public void set$flipsMeasured(boolean[] cv$value);
	public double[] get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public int get$samples();
	public void set$samples(int cv$value);
	public void logEvidenceGeneration();
}