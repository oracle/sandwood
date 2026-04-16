package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface MultinomialBernoulli$CoreInterface extends CoreModel {
	public double[] get$beta();
	public boolean get$fixedFlag$sample17();
	public void set$fixedFlag$sample17(boolean cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample20();
	public void set$fixedFlag$sample20(boolean cv$value, boolean allocated$);
	public int get$length();
	public int get$length$observed();
	public void set$length$observed(int cv$value, boolean allocated$);
	public double get$logProbability$b1();
	public double get$logProbability$b2();
	public double get$logProbability$b3();
	public double get$logProbability$output();
	public double get$logProbability$p();
	public double get$logProbability$prior();
	public int get$n();
	public boolean[] get$observed();
	public void set$observed(boolean[] cv$value, boolean allocated$);
	public boolean[] get$output();
	public double[] get$p();
	public void set$p(double[] cv$value, boolean allocated$);
	public int[] get$prior();
	public void set$prior(int[] cv$value, boolean allocated$);
}