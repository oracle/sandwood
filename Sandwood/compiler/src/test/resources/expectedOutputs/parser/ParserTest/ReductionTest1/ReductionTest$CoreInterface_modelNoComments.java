package org.sandwood.compiler.tests.parser;

interface ReductionTest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public int[][] get$ObsArr();
	public void set$ObsArr(int[][] cv$value);
	public int get$T();
	public void set$T(int cv$value);
	public double[][] get$TimeFeat();
	public void set$TimeFeat(double[][] cv$value);
	public int[][] get$arr();
	public boolean get$fixedFlag$sample101();
	public void set$fixedFlag$sample101(boolean cv$value);
	public double get$logProbability$arr();
	public double get$logProbability$sum_t();
	public double get$logProbability$time_coeff();
	public double get$logProbability$time_impact();
	public int get$n_ac();
	public void set$n_ac(int cv$value);
	public double[][] get$sum_t();
	public double[][] get$time_coeff();
	public void set$time_coeff(double[][] cv$value);
	public int get$time_dim();
	public double[][][] get$time_impact();
}