package org.sandwood.compiler.tests.parser;

interface Flip2CoinsMK2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$a();
	public void set$a(double cv$value);
	public double get$b();
	public void set$b(double cv$value);
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public int get$coins();
	public boolean get$fixedFlag$sample28();
	public void set$fixedFlag$sample28(boolean cv$value);
	public boolean get$fixedFlag$sample53();
	public void set$fixedFlag$sample53(boolean cv$value);
	public boolean[][] get$flips();
	public void set$flips(boolean[][] cv$value);
	public boolean[][] get$flipsMeasured();
	public void set$flipsMeasured(boolean[][] cv$value);
	public int[] get$length$flipsMeasured();
	public void set$length$flipsMeasured(int[] cv$value);
	public double[][] get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public int get$samples();
	public void logEvidenceGeneration();
}