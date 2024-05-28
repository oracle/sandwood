package org.sandwood.compiler.tests.parser;

interface Conditional3$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$bias();
	public boolean get$fixedFlag$sample16();
	public void set$fixedFlag$sample16(boolean cv$value);
	public boolean get$fixedFlag$sample20();
	public void set$fixedFlag$sample20(boolean cv$value);
	public boolean get$fixedFlag$sample4();
	public void set$fixedFlag$sample4(boolean cv$value);
	public boolean get$guard();
	public void set$guard(boolean cv$value);
	public double get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$guard();
	public double get$logProbability$value();
	public double get$observedValue();
	public void set$observedValue(double cv$value);
	public double get$value();
	public void set$value(double cv$value);
	public void logEvidenceGeneration();
}