package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface Flip2CoinsMK12$CoreInterface extends CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value, boolean allocated$);
	public int get$coins();
	public boolean get$fixedFlag$sample10();
	public void set$fixedFlag$sample10(boolean cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample23();
	public void set$fixedFlag$sample23(boolean cv$value, boolean allocated$);
	public boolean[][] get$flips();
	public boolean[][] get$flipsMeasured();
	public void set$flipsMeasured(boolean[][] cv$value, boolean allocated$);
	public boolean[][] get$intermediateFlips();
	public int[] get$length$flipsMeasured();
	public void set$length$flipsMeasured(int[] cv$value, boolean allocated$);
	public double[] get$logProbability$bernoulli1();
	public double[] get$logProbability$bernoulli2();
	public double get$logProbability$beta();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
}