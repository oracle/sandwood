package org.sandwood.compiler.tests.parser;

interface LogitRegressionTest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$bias();
	public void set$bias(double cv$value);
	public boolean get$fixedFlag$sample105();
	public void set$fixedFlag$sample105(boolean cv$value);
	public boolean get$fixedFlag$sample46();
	public void set$fixedFlag$sample46(boolean cv$value);
	public boolean get$fixedFlag$sample53();
	public void set$fixedFlag$sample53(boolean cv$value);
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