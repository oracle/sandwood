package org.sandwood.compiler.tests.parser;

interface HMMTestPart4$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public boolean get$fixedFlag$sample130();
	public void set$fixedFlag$sample130(boolean cv$value);
	public boolean get$fixedFlag$sample197();
	public void set$fixedFlag$sample197(boolean cv$value);
	public boolean get$fixedFlag$sample33();
	public void set$fixedFlag$sample33(boolean cv$value);
	public boolean get$fixedFlag$sample50();
	public void set$fixedFlag$sample50(boolean cv$value);
	public boolean get$fixedFlag$sample90();
	public void set$fixedFlag$sample90(boolean cv$value);
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