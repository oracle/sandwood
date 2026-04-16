package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface Flip1CoinMK20$CoreInterface extends CoreModel {
	public double get$bias();
	public void set$bias(double cv$value, boolean allocated$);
	public int get$count1();
	public int get$count2();
	public boolean get$fixedFlag$sample11();
	public void set$fixedFlag$sample11(boolean cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample12();
	public void set$fixedFlag$sample12(boolean cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample8();
	public void set$fixedFlag$sample8(boolean cv$value, boolean allocated$);
	public double get$logProbability$bias();
	public double get$logProbability$binomial();
	public double get$logProbability$count1();
	public double get$logProbability$count2();
	public int get$obs1();
	public void set$obs1(int cv$value, boolean allocated$);
	public int get$obs2();
	public void set$obs2(int cv$value, boolean allocated$);
	public int get$total();
}