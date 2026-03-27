package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK4$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$bias();
	public void set$bias(double cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample6();
	public void set$fixedFlag$sample6(boolean cv$value, boolean allocated$);
	public boolean[] get$flips();
	public boolean[] get$flipsMeasured();
	public void set$flipsMeasured(boolean[] cv$value, boolean allocated$);
	public int get$length$flipsMeasured();
	public void set$length$flipsMeasured(int cv$value, boolean allocated$);
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public int get$samples();
}