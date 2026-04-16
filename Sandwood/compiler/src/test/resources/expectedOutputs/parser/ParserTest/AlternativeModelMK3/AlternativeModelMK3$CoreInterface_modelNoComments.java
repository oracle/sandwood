package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface AlternativeModelMK3$CoreInterface extends CoreModel {
	public double get$bias();
	public void set$bias(double cv$value, boolean allocated$);
	public boolean get$fixedFlag$sample6();
	public void set$fixedFlag$sample6(boolean cv$value, boolean allocated$);
	public double get$logProbability$bias();
	public double get$logProbability$binomial();
	public double get$logProbability$positiveCount();
	public int get$observedPositiveCount();
	public void set$observedPositiveCount(int cv$value, boolean allocated$);
	public int get$observedSampleCount();
	public void set$observedSampleCount(int cv$value, boolean allocated$);
	public int get$positiveCount();
}