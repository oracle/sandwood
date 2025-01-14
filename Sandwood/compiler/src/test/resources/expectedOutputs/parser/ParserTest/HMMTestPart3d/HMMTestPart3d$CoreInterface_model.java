package org.sandwood.compiler.tests.parser;

interface HMMTestPart3d$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] cv$value);

	// Getter for fixedFlag$sample17.
	public boolean get$fixedFlag$sample17();

	// Setter for fixedFlag$sample17.
	public void set$fixedFlag$sample17(boolean cv$value);

	// Getter for fixedFlag$sample27.
	public boolean get$fixedFlag$sample27();

	// Setter for fixedFlag$sample27.
	public void set$fixedFlag$sample27(boolean cv$value);

	// Getter for fixedFlag$sample37.
	public boolean get$fixedFlag$sample37();

	// Setter for fixedFlag$sample37.
	public void set$fixedFlag$sample37(boolean cv$value);

	// Getter for fixedFlag$sample53.
	public boolean get$fixedFlag$sample53();

	// Setter for fixedFlag$sample53.
	public void set$fixedFlag$sample53(boolean cv$value);

	// Getter for fixedFlag$sample80.
	public boolean get$fixedFlag$sample80();

	// Setter for fixedFlag$sample80.
	public void set$fixedFlag$sample80(boolean cv$value);

	// Getter for flips.
	public boolean[] get$flips();

	// Setter for flips.
	public void set$flips(boolean[] cv$value);

	// Getter for flipsMeasured.
	public boolean[] get$flipsMeasured();

	// Setter for flipsMeasured.
	public void set$flipsMeasured(boolean[] cv$value);

	// Getter for length$flipsMeasured.
	public int get$length$flipsMeasured();

	// Setter for length$flipsMeasured.
	public void set$length$flipsMeasured(int cv$value);

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$flips.
	public double get$logProbability$flips();

	// Getter for logProbability$m.
	public double get$logProbability$m();

	// Getter for logProbability$st.
	public double get$logProbability$st();

	// Getter for logProbability$st2.
	public double get$logProbability$st2();

	// Getter for m.
	public double[][] get$m();

	// Setter for m.
	public void set$m(double[][] cv$value);

	// Getter for samples.
	public int get$samples();

	// Getter for st.
	public int[] get$st();

	// Setter for st.
	public void set$st(int[] cv$value);

	// Getter for st2.
	public int[] get$st2();

	// Getter for states.
	public int get$states();

	// Getter for v.
	public double[] get$v();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}