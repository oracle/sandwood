package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface Flip1CoinMK8$CoreInterface extends CoreModel {
	public double get$bias();
	public void set$bias(double cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample7();
	public void set$fixedFlag$sample7(boolean cv$value, boolean allocated$);
	public boolean[] get$flips();
	public boolean[] get$flipsMeasured();
	public void set$flipsMeasured(boolean[] cv$value, boolean allocated$);
	public int get$length$flipsMeasured();
	public void set$length$flipsMeasured(int cv$value, boolean allocated$);
	public double get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public int get$samples();
}