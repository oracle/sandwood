package org.sandwood.compiler.tests.parser;

interface LogitRegressionTest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$bias();
	public void set$bias(double cv$value);
	public boolean get$fixedFlag$sample32();
	public void set$fixedFlag$sample32(boolean cv$value);
	public boolean get$fixedFlag$sample39();
	public void set$fixedFlag$sample39(boolean cv$value);
	public boolean get$fixedFlag$sample72();
	public void set$fixedFlag$sample72(boolean cv$value);
	public int get$k();
	public double get$logProbability$bias();
	public double get$logProbability$weights();
	public double get$logProbability$y();
	public int get$n();
	public double[] get$weights();
	public void set$weights(double[] cv$value);
	public double[][] get$x();
	public void set$x(double[][] cv$value);
	public boolean[][] get$y();
	public void set$y(boolean[][] cv$value);
	public boolean[][] get$yMeasured();
	public void set$yMeasured(boolean[][] cv$value);
	public void logEvidenceGeneration();
}