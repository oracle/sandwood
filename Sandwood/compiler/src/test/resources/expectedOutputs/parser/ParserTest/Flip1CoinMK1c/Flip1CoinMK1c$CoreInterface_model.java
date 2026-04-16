package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface Flip1CoinMK1c$CoreInterface extends CoreModel {

	// Getter for a.
	public double get$a();

	// Setter for a.
	public void set$a(double cv$value, boolean allocated$);

	// Getter for b.
	public double get$b();

	// Setter for b.
	public void set$b(double cv$value, boolean allocated$);

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

	// Getter for logProbability$flips.
	public double get$logProbability$flips();

	// Getter for samples.
	public int get$samples();

	// Getter for var6.
	public double get$var6();

	// Setter for var6.
	public void set$var6(double cv$value, boolean allocated$);
}