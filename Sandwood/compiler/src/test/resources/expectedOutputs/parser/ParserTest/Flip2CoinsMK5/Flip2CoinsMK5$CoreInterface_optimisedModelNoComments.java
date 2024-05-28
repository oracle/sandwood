package org.sandwood.compiler.tests.parser;

interface Flip2CoinsMK5$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public int get$coins();
	public boolean get$fixedFlag$sample27();
	public void set$fixedFlag$sample27(boolean cv$value);
	public boolean get$fixedFlag$sample53();
	public void set$fixedFlag$sample53(boolean cv$value);
	public boolean[][] get$flips();
	public void set$flips(boolean[][] cv$value);
	public boolean[][] get$flipsMeasured();
	public void set$flipsMeasured(boolean[][] cv$value);
	public double[] get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public int[] get$shape();
	public void set$shape(int[] cv$value);
	public void logEvidenceGeneration();
}