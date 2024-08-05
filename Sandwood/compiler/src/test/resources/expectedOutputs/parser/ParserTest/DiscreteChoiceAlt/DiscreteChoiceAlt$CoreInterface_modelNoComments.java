package org.sandwood.compiler.tests.parser;

interface DiscreteChoiceAlt$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public int[] get$ObsChoices();
	public void set$ObsChoices(int[] cv$value);
	public int[] get$choices();
	public void set$choices(int[] cv$value);
	public double[] get$exped();
	public void set$exped(double[] cv$value);
	public boolean get$fixedFlag$sample19();
	public void set$fixedFlag$sample19(boolean cv$value);
	public boolean get$fixedFlag$sample49();
	public void set$fixedFlag$sample49(boolean cv$value);
	public double get$logProbability$choices();
	public double get$logProbability$exped();
	public double get$logProbability$prob();
	public double get$logProbability$sum();
	public double get$logProbability$ut();
	public int get$noObs();
	public void set$noObs(int cv$value);
	public int get$noProducts();
	public void set$noProducts(int cv$value);
	public double[] get$prob();
	public void set$prob(double[] cv$value);
	public double get$sum();
	public void set$sum(double cv$value);
	public double[] get$ut();
	public void set$ut(double[] cv$value);
	public void logEvidenceGeneration();
}