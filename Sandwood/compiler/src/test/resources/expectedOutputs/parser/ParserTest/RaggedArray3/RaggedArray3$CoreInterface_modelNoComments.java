package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface RaggedArray3$CoreInterface extends CoreModel {
	public double[][] get$a();
	public double[] get$d();
	public void set$d(double[] cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample39();
	public void set$fixedFlag$sample39(boolean cv$value, boolean allocated$);
	public int get$length$obs_measured();
	public void set$length$obs_measured(int cv$value, boolean allocated$);
	public double get$logProbability$d();
	public double get$logProbability$obs();
	public int[] get$obs();
	public int[] get$obs_measured();
	public void set$obs_measured(int[] cv$value, boolean allocated$);
	public int get$y();
	public void set$y(int cv$value, boolean allocated$);
}