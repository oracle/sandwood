package org.sandwood.compiler.tests.parser;

interface ReductionTest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public boolean get$fixedFlag$sample20();
	public void set$fixedFlag$sample20(boolean cv$value);
	public boolean get$fixedFlag$sample30();
	public void set$fixedFlag$sample30(boolean cv$value);
	public boolean get$fixedFlag$sample38();
	public void set$fixedFlag$sample38(boolean cv$value);
	public boolean get$fixedFlag$sample54();
	public void set$fixedFlag$sample54(boolean cv$value);
	public boolean[] get$flips();
	public void set$flips(boolean[] cv$value);
	public boolean[] get$flipsMeasured();
	public void set$flipsMeasured(boolean[] cv$value);
	public int get$length$flipsMeasured();
	public void set$length$flipsMeasured(int cv$value);
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public double get$logProbability$m();
	public double get$logProbability$st();
	public double[][] get$m();
	public void set$m(double[][] cv$value);
	public int get$noCats();
	public void set$noCats(int cv$value);
	public int get$noFlips();
	public int get$noStates();
	public int[] get$st();
	public void set$st(int[] cv$value);
	public double[] get$v();
	public void logEvidenceGeneration();
}