package org.sandwood.compiler.tests.parser;

interface AlternativeModelMK2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$bias();
	public void set$bias(double cv$value);
	public boolean get$fixedFlag$sample6();
	public void set$fixedFlag$sample6(boolean cv$value);
	public boolean get$fixedFlag$sample8();
	public void set$fixedFlag$sample8(boolean cv$value);
	public double get$logProbability$bias();
	public double get$logProbability$binomial();
	public double get$logProbability$positiveCount();
	public int get$observedPositiveCount();
	public void set$observedPositiveCount(int cv$value);
	public int get$observedSampleCount();
	public void set$observedSampleCount(int cv$value);
	public int get$positiveCount();
	public void set$positiveCount(int cv$value);
	public void logEvidenceGeneration();
}