package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface Flip2CoinsMK1$CoreInterface extends CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value, boolean allocated$);
	public int get$coins();
	public boolean get$fixedFlag$sample17();
	public void set$fixedFlag$sample17(boolean cv$value, boolean allocated$);
	public boolean[][] get$flips();
	public boolean[][] get$flipsMeasured();
	public void set$flipsMeasured(boolean[][] cv$value, boolean allocated$);
	public int[] get$length$flipsMeasured();
	public void set$length$flipsMeasured(int[] cv$value, boolean allocated$);
	public double[] get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
}