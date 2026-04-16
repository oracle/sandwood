package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface Flip1CoinMK16$CoreInterface extends CoreModel {
	public double get$bias();
	public void set$bias(double cv$value, boolean allocated$);
	public boolean get$flipMeasured();
	public void set$flipMeasured(boolean cv$value, boolean allocated$);
	public double get$guard();
	public void set$guard(double cv$value, boolean allocated$);
	public double get$logProbability$bernoulli();
}