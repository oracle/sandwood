package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface LDATest$CoreInterface extends CoreModel {
	public double[] get$alpha();
	public double[] get$beta();
	public int[][] get$documents();
	public void set$documents(int[][] cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample42();
	public void set$fixedFlag$sample42(boolean cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample58();
	public void set$fixedFlag$sample58(boolean cv$value, boolean allocated$);
	public int[] get$length$documents();
	public void set$length$documents(int[] cv$value, boolean allocated$);
	public double get$logProbability$phi();
	public double get$logProbability$theta();
	public double get$logProbability$w();
	public int get$noTopics();
	public void set$noTopics(int cv$value, boolean allocated$);
	public double[][] get$phi();
	public void set$phi(double[][] cv$value, boolean allocated$);
	public double[][] get$theta();
	public void set$theta(double[][] cv$value, boolean allocated$);
	public int get$vocabSize();
	public void set$vocabSize(int cv$value, boolean allocated$);
	public int[][] get$w();
	public int[][] get$z();
	public void set$z(int[][] cv$value, boolean allocated$);
}