package org.sandwood.compiler.tests.parser;

interface MultinomialBernoulli$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$beta();
	public boolean get$fixedFlag$sample17();
	public void set$fixedFlag$sample17(boolean cv$value);
	public boolean get$fixedFlag$sample20();
	public void set$fixedFlag$sample20(boolean cv$value);
	public boolean get$fixedFlag$sample42();
	public void set$fixedFlag$sample42(boolean cv$value);
	public boolean get$fixedFlag$sample47();
	public void set$fixedFlag$sample47(boolean cv$value);
	public boolean get$fixedFlag$sample52();
	public void set$fixedFlag$sample52(boolean cv$value);
	public int get$length();
	public int get$length$observed();
	public void set$length$observed(int cv$value);
	public double get$logProbability$b1();
	public double get$logProbability$b2();
	public double get$logProbability$b3();
	public double get$logProbability$output();
	public double get$logProbability$p();
	public double get$logProbability$prior();
	public int get$n();
	public boolean[] get$observed();
	public void set$observed(boolean[] cv$value);
	public boolean[] get$output();
	public void set$output(boolean[] cv$value);
	public double[] get$p();
	public void set$p(double[] cv$value);
	public int[] get$prior();
	public void set$prior(int[] cv$value);
	public void logEvidenceGeneration();
}