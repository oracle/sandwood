package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface ExponentialDecayMK1$CoreInterface extends CoreModel {
	public double get$a();
	public void set$a(double cv$value, boolean allocated$);
	public double get$b();
	public void set$b(double cv$value, boolean allocated$);
	public double[] get$decay();
	public double[] get$decayDetected();
	public void set$decayDetected(double[] cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample6();
	public void set$fixedFlag$sample6(boolean cv$value, boolean allocated$);
	public int get$length$decayDetected();
	public void set$length$decayDetected(int cv$value, boolean allocated$);
	public double get$logProbability$decay();
	public double get$logProbability$exponential();
	public double get$logProbability$rate();
	public double get$rate();
	public void set$rate(double cv$value, boolean allocated$);
	public int get$samples();
}