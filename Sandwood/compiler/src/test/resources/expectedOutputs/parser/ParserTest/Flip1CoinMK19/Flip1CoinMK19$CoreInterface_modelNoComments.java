package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface Flip1CoinMK19$CoreInterface extends CoreModel {
	public int get$a();
	public void set$a(int cv$value, boolean allocated$);
	public int get$b();
	public void set$b(int cv$value, boolean allocated$);
	public double[][] get$bias();
	public boolean get$fixedFlag$sample10();
	public void set$fixedFlag$sample10(boolean cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample16();
	public void set$fixedFlag$sample16(boolean cv$value, boolean allocated$);
	public boolean[] get$flips();
	public boolean[] get$flipsMeasured();
	public void set$flipsMeasured(boolean[] cv$value, boolean allocated$);
	public double get$logProbability$bernoulli();
	public double get$logProbability$bias();
	public double get$logProbability$flips();
	public double get$logProbability$q();
	public double get$logProbability$t();
	public double get$q();
	public void set$q(double cv$value, boolean allocated$);
	public int get$samples();
	public void set$samples(int cv$value, boolean allocated$);
	public double get$t();
	public void set$t(double cv$value, boolean allocated$);
}