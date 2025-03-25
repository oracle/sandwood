package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK12b$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$bias();
	public boolean get$fixedFlag$sample16();
	public void set$fixedFlag$sample16(boolean cv$value);
	public boolean get$fixedFlag$sample28();
	public void set$fixedFlag$sample28(boolean cv$value);
	public boolean get$fixedFlag$sample35();
	public void set$fixedFlag$sample35(boolean cv$value);
	public boolean[] get$flips();
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
	public double get$var14();
	public void set$var14(double cv$value);
	public double get$var26();
	public void set$var26(double cv$value);
	public double get$var33();
	public void set$var33(double cv$value);
	public void logEvidenceGeneration();
}