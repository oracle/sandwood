package org.sandwood.compiler.tests.parser;

interface Flip1CoinMK16$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$bias();
	public void set$bias(double cv$value);
	public boolean get$fixedFlag$sample11();
	public void set$fixedFlag$sample11(boolean cv$value);
	public boolean get$fixedFlag$sample9();
	public void set$fixedFlag$sample9(boolean cv$value);
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