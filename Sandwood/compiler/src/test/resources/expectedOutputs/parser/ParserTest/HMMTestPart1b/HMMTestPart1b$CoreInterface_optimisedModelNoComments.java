package org.sandwood.compiler.tests.parser;

interface HMMTestPart1b$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public boolean get$fixedFlag$sample14();
	public void set$fixedFlag$sample14(boolean cv$value);
	public boolean get$fixedFlag$sample24();
	public void set$fixedFlag$sample24(boolean cv$value);
	public boolean get$fixedFlag$sample29();
	public void set$fixedFlag$sample29(boolean cv$value);
	public boolean get$fixedFlag$sample32();
	public void set$fixedFlag$sample32(boolean cv$value);
	public boolean get$flip();
	public void set$flip(boolean cv$value);
	public boolean get$flipMeasured();
	public void set$flipMeasured(boolean cv$value);
	public double get$logProbability$bias();
	public double get$logProbability$flip();
	public double get$logProbability$m();
	public double get$logProbability$st();
	public double[][] get$m();
	public void set$m(double[][] cv$value);
	public int get$st();
	public void set$st(int cv$value);
	public int get$states();
	public double[] get$v();
	public void logEvidenceGeneration();
}