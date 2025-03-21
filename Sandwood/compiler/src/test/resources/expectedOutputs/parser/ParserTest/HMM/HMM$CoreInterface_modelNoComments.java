package org.sandwood.compiler.tests.parser;

interface HMM$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public boolean get$fixedFlag$sample28();
	public void set$fixedFlag$sample28(boolean cv$value);
	public boolean get$fixedFlag$sample45();
	public void set$fixedFlag$sample45(boolean cv$value);
	public boolean get$fixedFlag$sample53();
	public void set$fixedFlag$sample53(boolean cv$value);
	public boolean get$fixedFlag$sample71();
	public void set$fixedFlag$sample71(boolean cv$value);
	public boolean[] get$flips();
	public int get$length$measured();
	public void set$length$measured(int cv$value);
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public double get$logProbability$m();
	public double get$logProbability$st();
	public double[][] get$m();
	public void set$m(double[][] cv$value);
	public boolean[] get$measured();
	public void set$measured(boolean[] cv$value);
	public int get$samples();
	public int[] get$st();
	public void set$st(int[] cv$value);
	public int get$states();
	public void set$states(int cv$value);
	public double[] get$v();
	public void logEvidenceGeneration();
}