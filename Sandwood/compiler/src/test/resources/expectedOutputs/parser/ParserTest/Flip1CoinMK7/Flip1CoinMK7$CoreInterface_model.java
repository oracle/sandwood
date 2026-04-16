package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface Flip1CoinMK7$CoreInterface extends CoreModel {

	// Getter for bias.
	public double get$bias();

	// Setter for bias.
	public void set$bias(double cv$value, boolean allocated$);

	// Getter for fixedFlag$sample7.
	public boolean get$fixedFlag$sample7();

	// Setter for fixedFlag$sample7.
	public void set$fixedFlag$sample7(boolean cv$value, boolean allocated$);

	// Getter for flips.
	public boolean[] get$flips();

	// Getter for flipsMeasured.
	public boolean[] get$flipsMeasured();

	// Setter for flipsMeasured.
	public void set$flipsMeasured(boolean[] cv$value, boolean allocated$);

	// Getter for length$flipsMeasured.
	public int get$length$flipsMeasured();

	// Setter for length$flipsMeasured.
	public void set$length$flipsMeasured(int cv$value, boolean allocated$);

	// Getter for logProbability$bernoulli.
	public double get$logProbability$bernoulli();

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$flips.
	public double get$logProbability$flips();

	// Getter for samples.
	public int get$samples();
}