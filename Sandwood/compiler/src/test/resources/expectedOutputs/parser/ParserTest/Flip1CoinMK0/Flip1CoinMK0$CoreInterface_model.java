package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface Flip1CoinMK0$CoreInterface extends CoreModel {

	// Getter for bias.
	public double get$bias();

	// Setter for bias.
	public void set$bias(double cv$value, boolean allocated$);

	// Getter for fixedFlag$sample5.
	public boolean get$fixedFlag$sample5();

	// Setter for fixedFlag$sample5.
	public void set$fixedFlag$sample5(boolean cv$value, boolean allocated$);

	// Getter for flip.
	public boolean get$flip();

	// Getter for flipMeasured.
	public boolean get$flipMeasured();

	// Setter for flipMeasured.
	public void set$flipMeasured(boolean cv$value, boolean allocated$);

	// Getter for logProbability$bernoulli.
	public double get$logProbability$bernoulli();

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$flip.
	public double get$logProbability$flip();
}