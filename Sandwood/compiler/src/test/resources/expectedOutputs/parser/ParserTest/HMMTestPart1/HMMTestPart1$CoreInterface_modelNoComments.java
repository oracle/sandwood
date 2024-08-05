package org.sandwood.compiler.tests.parser;

interface HMMTestPart1$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public boolean get$fixedFlag$sample14();
	public void set$fixedFlag$sample14(boolean cv$value);
	public boolean get$fixedFlag$sample23();
	public void set$fixedFlag$sample23(boolean cv$value);
	public boolean get$fixedFlag$sample28();
	public void set$fixedFlag$sample28(boolean cv$value);
	public boolean get$fixedFlag$sample31();
	public void set$fixedFlag$sample31(boolean cv$value);
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