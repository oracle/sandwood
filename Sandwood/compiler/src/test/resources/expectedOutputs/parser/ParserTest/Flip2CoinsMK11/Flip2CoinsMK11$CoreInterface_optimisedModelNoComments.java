package org.sandwood.compiler.tests.parser;

interface Flip2CoinsMK11$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public int get$coins();
	public boolean get$fixedFlag$sample22();
	public void set$fixedFlag$sample22(boolean cv$value);
	public boolean get$fixedFlag$sample9();
	public void set$fixedFlag$sample9(boolean cv$value);
	public boolean[][] get$flips();
	public boolean[][] get$flipsMeasured();
	public void set$flipsMeasured(boolean[][] cv$value);
	public int[] get$length$flipsMeasured();
	public void set$length$flipsMeasured(int[] cv$value);
	public double[] get$logProbability$bernoulli1();
	public double[] get$logProbability$bernoulli2();
	public double get$logProbability$beta();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
}