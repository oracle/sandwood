package org.sandwood.compiler.tests.parser;

interface HMMTestPart4b$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$bias();
	public void set$bias(double[] cv$value);
	public boolean get$fixedFlag$sample107();
	public void set$fixedFlag$sample107(boolean cv$value);
	public boolean get$fixedFlag$sample19();
	public void set$fixedFlag$sample19(boolean cv$value);
	public boolean get$fixedFlag$sample29();
	public void set$fixedFlag$sample29(boolean cv$value);
	public boolean get$fixedFlag$sample54();
	public void set$fixedFlag$sample54(boolean cv$value);
	public boolean get$fixedFlag$sample72();
	public void set$fixedFlag$sample72(boolean cv$value);
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