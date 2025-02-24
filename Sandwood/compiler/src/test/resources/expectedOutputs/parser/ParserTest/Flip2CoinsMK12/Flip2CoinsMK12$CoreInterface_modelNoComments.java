package org.sandwood.compiler.tests.parser;

interface Flip2CoinsMK12$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public int get$coins();
	public boolean get$fixedFlag$sample10();
	public void set$fixedFlag$sample10(boolean cv$value);
	public boolean get$fixedFlag$sample23();
	public void set$fixedFlag$sample23(boolean cv$value);
	public boolean get$fixedFlag$sample50();
	public void set$fixedFlag$sample50(boolean cv$value);
	public boolean get$fixedFlag$sample78();
	public void set$fixedFlag$sample78(boolean cv$value);
	public boolean[][] get$flips();
	public void set$flips(boolean[][] cv$value);
	public boolean[][] get$flipsMeasured();
	public void set$flipsMeasured(boolean[][] cv$value);
	public boolean[][] get$intermediateFlips();
	public int[] get$length$flipsMeasured();
	public void set$length$flipsMeasured(int[] cv$value);
	public double[] get$logProbability$bernoulli1();
	public double[] get$logProbability$bernoulli2();
	public double get$logProbability$beta();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public void logEvidenceGeneration();
}