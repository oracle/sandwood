package org.sandwood.compiler.tests.parser;

interface Flip2CoinsMK11$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public int get$coins();
	public boolean get$fixedFlag$sample15();
	public void set$fixedFlag$sample15(boolean cv$value);
	public boolean get$fixedFlag$sample20();
	public void set$fixedFlag$sample20(boolean cv$value);
	public boolean get$fixedFlag$sample36();
	public void set$fixedFlag$sample36(boolean cv$value);
	public boolean get$fixedFlag$sample53();
	public void set$fixedFlag$sample53(boolean cv$value);
	public boolean[][] get$flips();
	public void set$flips(boolean[][] cv$value);
	public boolean[][] get$flipsMeasured();
	public void set$flipsMeasured(boolean[][] cv$value);
	public int[] get$length$flipsMeasured();
	public void set$length$flipsMeasured(int[] cv$value);
	public double[] get$logProbability$bernoulli1();
	public double[] get$logProbability$bernoulli2();
	public double get$logProbability$beta();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public void logEvidenceGeneration();
}