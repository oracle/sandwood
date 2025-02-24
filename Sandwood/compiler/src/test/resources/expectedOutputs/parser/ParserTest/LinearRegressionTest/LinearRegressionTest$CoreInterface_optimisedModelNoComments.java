package org.sandwood.compiler.tests.parser;

interface LinearRegressionTest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$bias();
	public void set$bias(double cv$value);
	public boolean get$fixedFlag$sample24();
	public void set$fixedFlag$sample24(boolean cv$value);
	public boolean get$fixedFlag$sample31();
	public void set$fixedFlag$sample31(boolean cv$value);
	public boolean get$fixedFlag$sample35();
	public void set$fixedFlag$sample35(boolean cv$value);
	public boolean get$fixedFlag$sample74();
	public void set$fixedFlag$sample74(boolean cv$value);
	public int get$k();
	public double get$logProbability$bias();
	public double get$logProbability$tau();
	public double get$logProbability$weights();
	public double get$logProbability$y();
	public int get$n();
	public double get$tau();
	public void set$tau(double cv$value);
	public double[] get$weights();
	public void set$weights(double[] cv$value);
	public double[][] get$x();
	public void set$x(double[][] cv$value);
	public double[] get$y();
	public void set$y(double[] cv$value);
	public double[] get$yMeasured();
	public void set$yMeasured(double[] cv$value);
	public void logEvidenceGeneration();
}