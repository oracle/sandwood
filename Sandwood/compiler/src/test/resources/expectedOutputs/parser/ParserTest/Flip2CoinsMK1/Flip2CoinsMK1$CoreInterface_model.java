package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface Flip2CoinsMK1$CoreInterface extends CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] cv$value, boolean allocated$);

	// Getter for coins.
	public int get$coins();

	// Getter for fixedFlag$sample17.
	public boolean get$fixedFlag$sample17();

	// Setter for fixedFlag$sample17.
	public void set$fixedFlag$sample17(boolean cv$value, boolean allocated$);

	// Getter for flips.
	public boolean[][] get$flips();

	// Getter for flipsMeasured.
	public boolean[][] get$flipsMeasured();

	// Setter for flipsMeasured.
	public void set$flipsMeasured(boolean[][] cv$value, boolean allocated$);

	// Getter for length$flipsMeasured.
	public int[] get$length$flipsMeasured();

	// Setter for length$flipsMeasured.
	public void set$length$flipsMeasured(int[] cv$value, boolean allocated$);

	// Getter for logProbability$bernoulli.
	public double[] get$logProbability$bernoulli();

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$flips.
	public double get$logProbability$flips();
}