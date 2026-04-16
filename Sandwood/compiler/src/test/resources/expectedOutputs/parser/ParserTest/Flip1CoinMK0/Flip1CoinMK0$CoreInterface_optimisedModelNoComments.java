package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface Flip1CoinMK0$CoreInterface extends CoreModel {
	public double get$bias();
	public void set$bias(double cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample5();
	public void set$fixedFlag$sample5(boolean cv$value, boolean allocated$);
	public boolean get$flip();
	public boolean get$flipMeasured();
	public void set$flipMeasured(boolean cv$value, boolean allocated$);
	public double get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$flip();
}