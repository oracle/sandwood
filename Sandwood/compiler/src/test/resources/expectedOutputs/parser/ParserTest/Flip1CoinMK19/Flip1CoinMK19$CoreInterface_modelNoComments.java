package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK19$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public int get$a();
	public void set$a(int cv$value);
	public int get$b();
	public void set$b(int cv$value);
	public double[][] get$bias();
	public boolean get$fixedFlag$sample13();
	public void set$fixedFlag$sample13(boolean cv$value);
	public boolean get$fixedFlag$sample19();
	public void set$fixedFlag$sample19(boolean cv$value);
	public boolean get$fixedFlag$sample40();
	public void set$fixedFlag$sample40(boolean cv$value);
	public boolean[] get$flips();
	public void set$flips(boolean[] cv$value);
	public boolean[] get$flipsMeasured();
	public void set$flipsMeasured(boolean[] cv$value);
	public double get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public double get$logProbability$q();
	public double get$logProbability$t();
	public double get$q();
	public void set$q(double cv$value);
	public int get$samples();
	public void set$samples(int cv$value);
	public double get$t();
	public void set$t(double cv$value);
	public void logEvidenceGeneration();
}