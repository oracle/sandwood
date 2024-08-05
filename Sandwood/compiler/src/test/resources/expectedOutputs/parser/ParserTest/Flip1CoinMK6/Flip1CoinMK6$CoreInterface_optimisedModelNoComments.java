package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK6$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$bias();
	public void set$bias(double cv$value);
	public boolean get$fixedFlag$sample17();
	public void set$fixedFlag$sample17(boolean cv$value);
	public boolean get$fixedFlag$sample23();
	public void set$fixedFlag$sample23(boolean cv$value);
	public boolean get$fixedFlag$sample29();
	public void set$fixedFlag$sample29(boolean cv$value);
	public boolean[] get$flips1();
	public void set$flips1(boolean[] cv$value);
	public boolean[] get$flips2();
	public void set$flips2(boolean[] cv$value);
	public boolean[] get$flipsMeasured1();
	public void set$flipsMeasured1(boolean[] cv$value);
	public boolean[] get$flipsMeasured2();
	public void set$flipsMeasured2(boolean[] cv$value);
	public int get$length$flipsMeasured1();
	public void set$length$flipsMeasured1(int cv$value);
	public int get$length$flipsMeasured2();
	public void set$length$flipsMeasured2(int cv$value);
	public double get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$flips1();
	public double get$logProbability$flips2();
	public int get$samples1();
	public int get$samples2();
	public void logEvidenceGeneration();
}