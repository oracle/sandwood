package org.sandwood.compiler.tests.parser;

interface Flip2CoinsMK1$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public int get$coins();
	public boolean get$fixedFlag$sample16();
	public void set$fixedFlag$sample16(boolean cv$value);
	public boolean get$fixedFlag$sample32();
	public void set$fixedFlag$sample32(boolean cv$value);
	public boolean[][] get$flips();
	public void set$flips(boolean[][] cv$value);
	public boolean[][] get$flipsMeasured();
	public void set$flipsMeasured(boolean[][] cv$value);
	public int[] get$length$flipsMeasured();
	public void set$length$flipsMeasured(int[] cv$value);
	public double[] get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public void logEvidenceGeneration();
}