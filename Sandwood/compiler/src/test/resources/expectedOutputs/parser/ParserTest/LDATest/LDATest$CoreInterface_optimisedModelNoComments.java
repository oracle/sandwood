package org.sandwood.compiler.tests.parser;

interface LDATest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public double[] get$alpha();
	public double[] get$beta();
	public int[][] get$documents();
	public void set$documents(int[][] cv$value);
	public boolean get$fixedFlag$sample26();
	public void set$fixedFlag$sample26(boolean cv$value);
	public boolean get$fixedFlag$sample35();
	public void set$fixedFlag$sample35(boolean cv$value);
	public boolean get$fixedFlag$sample59();
	public void set$fixedFlag$sample59(boolean cv$value);
	public boolean get$fixedFlag$sample62();
	public void set$fixedFlag$sample62(boolean cv$value);
	public int[] get$length$documents();
	public void set$length$documents(int[] cv$value);
	public double get$logProbability$phi();
	public double get$logProbability$theta();
	public double get$logProbability$w();
	public double get$logProbability$z();
	public int get$noTopics();
	public void set$noTopics(int cv$value);
	public double[][] get$phi();
	public void set$phi(double[][] cv$value);
	public double[][] get$theta();
	public void set$theta(double[][] cv$value);
	public int get$vocabSize();
	public void set$vocabSize(int cv$value);
	public int[][] get$w();
	public void set$w(int[][] cv$value);
	public int[][] get$z();
	public void set$z(int[][] cv$value);
	public void logEvidenceGeneration();
}