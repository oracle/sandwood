package org.sandwood.compiler.tests.parser;

interface GaussianMixtureTest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$alpha();
	public boolean get$fixedFlag$sample13();
	public void set$fixedFlag$sample13(boolean cv$value);
	public boolean get$fixedFlag$sample23();
	public void set$fixedFlag$sample23(boolean cv$value);
	public boolean get$fixedFlag$sample34();
	public void set$fixedFlag$sample34(boolean cv$value);
	public boolean get$fixedFlag$sample45();
	public void set$fixedFlag$sample45(boolean cv$value);
	public boolean get$fixedFlag$sample49();
	public void set$fixedFlag$sample49(boolean cv$value);
	public int get$k();
	public int get$length$xMeasured();
	public void set$length$xMeasured(int cv$value);
	public double get$logProbability$mu();
	public double get$logProbability$phi();
	public double get$logProbability$sigma();
	public double get$logProbability$x();
	public double get$logProbability$z();
	public double[] get$mu();
	public void set$mu(double[] cv$value);
	public double[] get$phi();
	public void set$phi(double[] cv$value);
	public double[] get$sigma();
	public void set$sigma(double[] cv$value);
	public double[] get$x();
	public void set$x(double[] cv$value);
	public double[] get$xMeasured();
	public void set$xMeasured(double[] cv$value);
	public int[] get$z();
	public void set$z(int[] cv$value);
	public void logEvidenceGeneration();
}