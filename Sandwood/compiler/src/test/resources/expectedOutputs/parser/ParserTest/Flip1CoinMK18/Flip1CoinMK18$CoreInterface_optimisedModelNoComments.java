package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK18$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public int get$a();
	public void set$a(int cv$value);
	public int get$b();
	public void set$b(int cv$value);
	public double[][][] get$bias();
	public void set$bias(double[][][] cv$value);
	public int get$c();
	public void set$c(int cv$value);
	public boolean get$fixedFlag$sample14();
	public void set$fixedFlag$sample14(boolean cv$value);
	public boolean get$fixedFlag$sample20();
	public void set$fixedFlag$sample20(boolean cv$value);
	public boolean get$fixedFlag$sample85();
	public void set$fixedFlag$sample85(boolean cv$value);
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