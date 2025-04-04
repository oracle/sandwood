package org.sandwood.compiler.tests.parser;

interface Deterministic$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public int[] get$a();
	public void set$a(int[] cv$value);
	public int[] get$b();
	public boolean get$fixedFlag$sample18();
	public void set$fixedFlag$sample18(boolean cv$value);
	public boolean get$fixedFlag$sample36();
	public void set$fixedFlag$sample36(boolean cv$value);
	public boolean get$fixedFlag$sample49();
	public void set$fixedFlag$sample49(boolean cv$value);
	public boolean[] get$flips();
	public void set$flips(boolean[] cv$value);
	public boolean[] get$flipsMeasured();
	public void set$flipsMeasured(boolean[] cv$value);
	public double get$logProbability$a();
	public double get$logProbability$b();
	public double get$logProbability$flips();
	public double get$logProbability$m();
	public double[][] get$m();
	public void set$m(double[][] cv$value);
	public int get$n();
	public void set$n(int cv$value);
	public int get$states();
	public double[] get$v();
	public void logEvidenceGeneration();
}