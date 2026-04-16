package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface PoissonDecayMK1$CoreInterface extends CoreModel {
	public double get$a();
	public void set$a(double cv$value, boolean allocated$);
	public double get$b();
	public void set$b(double cv$value, boolean allocated$);
	public int[] get$decay();
	public int[] get$decayDetected();
	public void set$decayDetected(int[] cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample6();
	public void set$fixedFlag$sample6(boolean cv$value, boolean allocated$);
	public int get$length$decayDetected();
	public void set$length$decayDetected(int cv$value, boolean allocated$);
	public double get$logProbability$decay();
	public double get$logProbability$poisson();
	public double get$logProbability$rate();
	public double get$rate();
	public void set$rate(double cv$value, boolean allocated$);
	public int get$samples();
}