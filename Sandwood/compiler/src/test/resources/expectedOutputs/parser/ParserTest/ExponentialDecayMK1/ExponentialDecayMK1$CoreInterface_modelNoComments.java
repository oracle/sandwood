package org.sandwood.compiler.tests.parser;

interface ExponentialDecayMK1$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$a();
	public void set$a(double cv$value);
	public double get$b();
	public void set$b(double cv$value);
	public double[] get$decay();
	public void set$decay(double[] cv$value);
	public double[] get$decayDetected();
	public void set$decayDetected(double[] cv$value);
	public boolean get$fixedFlag$sample10();
	public void set$fixedFlag$sample10(boolean cv$value);
	public boolean get$fixedFlag$sample16();
	public void set$fixedFlag$sample16(boolean cv$value);
	public int get$length$decayDetected();
	public void set$length$decayDetected(int cv$value);
	public double get$logProbability$decay();
	public double get$logProbability$exponential();
	public double get$logProbability$rate();
	public double get$rate();
	public void set$rate(double cv$value);
	public int get$samples();
	public void logEvidenceGeneration();
}