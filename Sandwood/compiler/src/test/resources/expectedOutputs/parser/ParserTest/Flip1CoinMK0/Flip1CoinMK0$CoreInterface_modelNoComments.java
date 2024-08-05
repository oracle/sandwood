package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK0$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$bias();
	public void set$bias(double cv$value);
	public boolean get$fixedFlag$sample5();
	public void set$fixedFlag$sample5(boolean cv$value);
	public boolean get$fixedFlag$sample7();
	public void set$fixedFlag$sample7(boolean cv$value);
	public boolean get$flip();
	public void set$flip(boolean cv$value);
	public boolean get$flipMeasured();
	public void set$flipMeasured(boolean cv$value);
	public double get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$flip();
	public void logEvidenceGeneration();
}