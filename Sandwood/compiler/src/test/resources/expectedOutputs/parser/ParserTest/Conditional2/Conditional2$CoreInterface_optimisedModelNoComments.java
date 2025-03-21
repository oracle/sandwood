package org.sandwood.compiler.tests.parser;

interface Conditional2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public boolean get$fixedFlag$sample21();
	public void set$fixedFlag$sample21(boolean cv$value);
	public boolean get$fixedFlag$sample4();
	public void set$fixedFlag$sample4(boolean cv$value);
	public boolean get$guard();
	public void set$guard(boolean cv$value);
	public double get$logProbability$bernoulli();
	public double get$logProbability$guard();
	public double get$logProbability$value();
	public double get$logProbability$value2();
	public double[] get$observedValue();
	public void set$observedValue(double[] cv$value);
	public double[] get$value();
	public double[] get$value2();
	public void logEvidenceGeneration();
}