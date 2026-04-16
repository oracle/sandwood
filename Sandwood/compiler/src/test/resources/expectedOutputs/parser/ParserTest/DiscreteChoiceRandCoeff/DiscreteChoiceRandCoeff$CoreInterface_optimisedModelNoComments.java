package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface DiscreteChoiceRandCoeff$CoreInterface extends CoreModel {
	public int[] get$ObsChoices();
	public void set$ObsChoices(int[] cv$value, boolean allocated$);
	public int[][] get$Prices();
	public void set$Prices(int[][] cv$value, boolean allocated$);
	public double get$b();
	public void set$b(double cv$value, boolean allocated$);
	public double[] get$beta();
	public void set$beta(double[] cv$value, boolean allocated$);
	public int[] get$choices();
	public boolean get$fixedFlag$sample21();
	public void set$fixedFlag$sample21(boolean cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample28();
	public void set$fixedFlag$sample28(boolean cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample34();
	public void set$fixedFlag$sample34(boolean cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample47();
	public void set$fixedFlag$sample47(boolean cv$value, boolean allocated$);
	public double get$logProbability$b();
	public double get$logProbability$beta();
	public double get$logProbability$choices();
	public double get$logProbability$prob();
	public double get$logProbability$sigma();
	public double get$logProbability$ut();
	public int get$noObs();
	public void set$noObs(int cv$value, boolean allocated$);
	public int get$noProducts();
	public void set$noProducts(int cv$value, boolean allocated$);
	public double[][] get$prob();
	public double get$sigma();
	public void set$sigma(double cv$value, boolean allocated$);
	public double[] get$ut();
	public void set$ut(double[] cv$value, boolean allocated$);
}