package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModel;

interface RaggedArray$CoreInterface extends CoreModel {
	public double[][] get$a();
	public double[][] get$b();
	public boolean get$fixedFlag$sample73();
	public void set$fixedFlag$sample73(boolean cv$value, boolean allocated$);
	public int get$i();
	public void set$i(int cv$value, boolean allocated$);
	public int get$length$obs_measured();
	public void set$length$obs_measured(int cv$value, boolean allocated$);
	public double get$logProbability$i();
	public double get$logProbability$obs();
	public boolean[] get$obs();
	public boolean[] get$obs_measured();
	public void set$obs_measured(boolean[] cv$value, boolean allocated$);
	public double get$p();
	public int get$y();
	public void set$y(int cv$value, boolean allocated$);
}