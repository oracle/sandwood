package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK12$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$bias();
	public boolean get$fixedFlag$sample15();
	public void set$fixedFlag$sample15(boolean cv$value);
	public boolean get$fixedFlag$sample24();
	public void set$fixedFlag$sample24(boolean cv$value);
	public boolean get$fixedFlag$sample31();
	public void set$fixedFlag$sample31(boolean cv$value);
	public boolean get$fixedFlag$sample41();
	public void set$fixedFlag$sample41(boolean cv$value);
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