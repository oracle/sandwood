package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface ParallelMK3$CoreInterface extends CoreModel {
	public boolean get$fixedFlag$sample21();
	public void set$fixedFlag$sample21(boolean cv$value, boolean allocated$);
	public double[] get$generated();
	public double[] get$indirection();
	public int get$length$observed();
	public void set$length$observed(int cv$value, boolean allocated$);
	public double get$logProbability$generated();
	public double get$logProbability$indirection();
	public double get$logProbability$sample();
	public double[] get$observed();
	public void set$observed(double[] cv$value, boolean allocated$);
	public double[] get$sample();
	public void set$sample(double[] cv$value, boolean allocated$);
	public double[] get$v();
}