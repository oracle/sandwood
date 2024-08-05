package org.sandwood.compiler.tests.parser;

interface NullModelMK3$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$bias();
	public void set$bias(double cv$value);
	public double get$eta();
	public void set$eta(double cv$value);
	public boolean get$fixedFlag$sample10();
	public void set$fixedFlag$sample10(boolean cv$value);
	public boolean get$fixedFlag$sample12();
	public void set$fixedFlag$sample12(boolean cv$value);
	public double get$logProbability$bias();
	public double get$logProbability$binomial();
	public double get$logProbability$positiveCount();
	public double get$min();
	public int get$observedPositiveCount();
	public void set$observedPositiveCount(int cv$value);
	public int get$observedSampleCount();
	public void set$observedSampleCount(int cv$value);
	public int get$positiveCount();
	public void set$positiveCount(int cv$value);
	public void logEvidenceGeneration();
}