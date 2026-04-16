package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface Flip1CoinMK1c$CoreInterface extends CoreModel {
	public double get$a();
	public void set$a(double cv$value, boolean allocated$);
	public double get$b();
	public void set$b(double cv$value, boolean allocated$);
	public boolean[] get$flips();
	public boolean[] get$flipsMeasured();
	public void set$flipsMeasured(boolean[] cv$value, boolean allocated$);
	public int get$length$flipsMeasured();
	public void set$length$flipsMeasured(int cv$value, boolean allocated$);
	public double get$logProbability$bernoulli();
	public double get$logProbability$flips();
	public int get$samples();
	public double get$var6();
	public void set$var6(double cv$value, boolean allocated$);
}