package org.sandwood.compiler.tests.parser;

interface HMMTestPart3d$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public boolean get$fixedFlag$sample17();
	public void set$fixedFlag$sample17(boolean cv$value);
	public boolean get$fixedFlag$sample27();
	public void set$fixedFlag$sample27(boolean cv$value);
	public boolean get$fixedFlag$sample37();
	public void set$fixedFlag$sample37(boolean cv$value);
	public boolean get$fixedFlag$sample53();
	public void set$fixedFlag$sample53(boolean cv$value);
	public boolean get$fixedFlag$sample80();
	public void set$fixedFlag$sample80(boolean cv$value);
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
	public double get$logProbability$st2();
	public double[][] get$m();
	public void set$m(double[][] cv$value);
	public int get$samples();
	public int[] get$st();
	public void set$st(int[] cv$value);
	public int[] get$st2();
	public void set$st2(int[] cv$value);
	public int get$states();
	public double[] get$v();
	public void logEvidenceGeneration();
}