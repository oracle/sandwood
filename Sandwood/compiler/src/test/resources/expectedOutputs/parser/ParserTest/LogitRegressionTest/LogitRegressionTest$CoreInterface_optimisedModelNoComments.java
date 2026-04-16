package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface LogitRegressionTest$CoreInterface extends CoreModel {
	public double get$bias();
	public void set$bias(double cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample35();
	public void set$fixedFlag$sample35(boolean cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample42();
	public void set$fixedFlag$sample42(boolean cv$value, boolean allocated$);
	public int get$k();
	public double get$logProbability$bias();
	public double get$logProbability$weights();
	public double get$logProbability$y();
	public int get$n();
	public double[] get$weights();
	public void set$weights(double[] cv$value, boolean allocated$);
	public double[][] get$x();
	public void set$x(double[][] cv$value, boolean allocated$);
	public boolean[][] get$y();
	public boolean[][] get$yMeasured();
	public void set$yMeasured(boolean[][] cv$value, boolean allocated$);
}