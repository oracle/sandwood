package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK12$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$bias();
	public boolean get$fixedFlag$sample20();
	public void set$fixedFlag$sample20(boolean cv$value);
	public boolean get$fixedFlag$sample32();
	public void set$fixedFlag$sample32(boolean cv$value);
	public boolean get$fixedFlag$sample39();
	public void set$fixedFlag$sample39(boolean cv$value);
	public boolean get$fixedFlag$sample56();
	public void set$fixedFlag$sample56(boolean cv$value);
	public boolean[] get$flips();
	public void set$flips(boolean[] cv$value);
	public boolean[] get$flipsMeasured();
	public void set$flipsMeasured(boolean[] cv$value);
	public boolean get$guard1();
	public void set$guard1(boolean cv$value);
	public int get$guard2();
	public void set$guard2(int cv$value);
	public int get$length$flipsMeasured();
	public void set$length$flipsMeasured(int cv$value);
	public double get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public int get$samples();
	public void logEvidenceGeneration();
}