package org.sandwood.compiler.tests.parser;

interface HMMTestPart4b$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public boolean get$fixedFlag$sample128();
	public void set$fixedFlag$sample128(boolean cv$value);
	public boolean get$fixedFlag$sample195();
	public void set$fixedFlag$sample195(boolean cv$value);
	public boolean get$fixedFlag$sample33();
	public void set$fixedFlag$sample33(boolean cv$value);
	public boolean get$fixedFlag$sample50();
	public void set$fixedFlag$sample50(boolean cv$value);
	public boolean get$fixedFlag$sample88();
	public void set$fixedFlag$sample88(boolean cv$value);
	public boolean[][][] get$flips();
	public void set$flips(boolean[][][] cv$value);
	public boolean[][][] get$flipsMeasured();
	public void set$flipsMeasured(boolean[][][] cv$value);
	public int[][] get$length$flipsMeasured();
	public void set$length$flipsMeasured(int[][] cv$value);
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public double get$logProbability$m();
	public double get$logProbability$st();
	public double[][] get$m();
	public void set$m(double[][] cv$value);
	public int get$samples();
	public int[][][] get$st();
	public void set$st(int[][][] cv$value);
	public int get$states();
	public double[] get$v();
	public void logEvidenceGeneration();
}