package org.sandwood.compiler.tests.parser;

interface LinearRegressionBasic$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double get$b0();
	public void set$b0(double cv$value);
	public double get$b1();
	public void set$b1(double cv$value);
	public boolean get$fixedFlag$sample14();
	public void set$fixedFlag$sample14(boolean cv$value);
	public boolean get$fixedFlag$sample18();
	public void set$fixedFlag$sample18(boolean cv$value);
	public boolean get$fixedFlag$sample22();
	public void set$fixedFlag$sample22(boolean cv$value);
	public boolean get$fixedFlag$sample31();
	public void set$fixedFlag$sample31(boolean cv$value);
	public double get$logProbability$b0();
	public double get$logProbability$b1();
	public double get$logProbability$variance();
	public double get$logProbability$y();
	public int get$noSamples();
	public double get$variance();
	public void set$variance(double cv$value);
	public double[] get$x();
	public void set$x(double[] cv$value);
	public double[] get$y();
	public void set$y(double[] cv$value);
	public double[] get$yMeasured();
	public void set$yMeasured(double[] cv$value);
	public void logEvidenceGeneration();
}