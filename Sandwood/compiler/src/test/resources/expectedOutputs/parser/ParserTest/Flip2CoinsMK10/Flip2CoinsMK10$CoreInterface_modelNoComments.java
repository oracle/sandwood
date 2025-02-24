package org.sandwood.compiler.tests.parser;

interface Flip2CoinsMK10$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public int get$coins();
	public boolean get$fixedFlag$sample10();
	public void set$fixedFlag$sample10(boolean cv$value);
	public boolean get$fixedFlag$sample23();
	public void set$fixedFlag$sample23(boolean cv$value);
	public boolean get$fixedFlag$sample48();
	public void set$fixedFlag$sample48(boolean cv$value);
	public boolean[][] get$flips();
	public void set$flips(boolean[][] cv$value);
	public boolean[][] get$flipsMeasured();
	public void set$flipsMeasured(boolean[][] cv$value);
	public double[] get$logProbability$bernoulli();
	public double get$logProbability$beta();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public int[] get$shape();
	public void set$shape(int[] cv$value);
	public void logEvidenceGeneration();
}