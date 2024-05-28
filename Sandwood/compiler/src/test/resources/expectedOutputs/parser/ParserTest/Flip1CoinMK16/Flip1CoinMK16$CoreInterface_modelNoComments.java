package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK16$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$bias();
	public void set$bias(double cv$value);
	public boolean get$fixedFlag$sample14();
	public void set$fixedFlag$sample14(boolean cv$value);
	public boolean get$fixedFlag$sample16();
	public void set$fixedFlag$sample16(boolean cv$value);
	public boolean get$flip();
	public void set$flip(boolean cv$value);
	public boolean get$flipMeasured();
	public void set$flipMeasured(boolean cv$value);
	public double get$guard();
	public void set$guard(double cv$value);
	public double get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$flip();
	public void logEvidenceGeneration();
}