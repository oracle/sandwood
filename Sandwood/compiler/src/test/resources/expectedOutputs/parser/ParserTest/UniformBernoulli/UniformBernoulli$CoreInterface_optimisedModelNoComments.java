package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface UniformBernoulli$CoreInterface extends CoreModel {
	public double get$a();
	public double get$b();
	public boolean get$fixedFlag$sample5();
	public void set$fixedFlag$sample5(boolean cv$value, boolean allocated$);
	public int get$length$observed();
	public void set$length$observed(int cv$value, boolean allocated$);
	public double get$logProbability$bernoulli();
	public double get$logProbability$output();
	public double get$logProbability$prior();
	public boolean[] get$observed();
	public void set$observed(boolean[] cv$value, boolean allocated$);
	public boolean[] get$output();
	public double get$prior();
	public void set$prior(double cv$value, boolean allocated$);
}