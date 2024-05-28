package org.sandwood.compiler.tests.parser;

interface GaussianMixtureTest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$alpha();
	public boolean get$fixedFlag$sample20();
	public void set$fixedFlag$sample20(boolean cv$value);
	public boolean get$fixedFlag$sample37();
	public void set$fixedFlag$sample37(boolean cv$value);
	public boolean get$fixedFlag$sample55();
	public void set$fixedFlag$sample55(boolean cv$value);
	public boolean get$fixedFlag$sample73();
	public void set$fixedFlag$sample73(boolean cv$value);
	public boolean get$fixedFlag$sample77();
	public void set$fixedFlag$sample77(boolean cv$value);
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