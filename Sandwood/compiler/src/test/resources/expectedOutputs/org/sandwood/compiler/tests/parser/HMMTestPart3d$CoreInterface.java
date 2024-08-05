package org.sandwood.compiler.tests.parser;

interface HMMTestPart3d$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] calculationVariable$value);

	// Getter for fixedFlag$sample17.
	public boolean get$fixedFlag$sample17();

	// Setter for fixedFlag$sample17.
	public void set$fixedFlag$sample17(boolean calculationVariable$value);

	// Getter for fixedFlag$sample26.
	public boolean get$fixedFlag$sample26();

	// Setter for fixedFlag$sample26.
	public void set$fixedFlag$sample26(boolean calculationVariable$value);

	// Getter for fixedFlag$sample36.
	public boolean get$fixedFlag$sample36();

	// Setter for fixedFlag$sample36.
	public void set$fixedFlag$sample36(boolean calculationVariable$value);

	// Getter for fixedFlag$sample52.
	public boolean get$fixedFlag$sample52();

	// Setter for fixedFlag$sample52.
	public void set$fixedFlag$sample52(boolean calculationVariable$value);

	// Getter for fixedFlag$sample79.
	public boolean get$fixedFlag$sample79();

	// Setter for fixedFlag$sample79.
	public void set$fixedFlag$sample79(boolean calculationVariable$value);

	// Getter for flips.
	public boolean[] get$flips();

	// Setter for flips.
	public void set$flips(boolean[] calculationVariable$value);

	// Getter for flipsMeasured.
	public boolean[] get$flipsMeasured();

	// Setter for flipsMeasured.
	public void set$flipsMeasured(boolean[] calculationVariable$value);

	// Getter for length$flipsMeasured.
	public int get$length$flipsMeasured();

	// Setter for length$flipsMeasured.
	public void set$length$flipsMeasured(int calculationVariable$value);

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
	public void set$m(double[][] calculationVariable$value);

	// Getter for samples.
	public int get$samples();

	// Getter for st.
	public int[] get$st();

	// Setter for st.
	public void set$st(int[] calculationVariable$value);

	// Getter for st2.
	public int[] get$st2();

	// Setter for st2.
	public void set$st2(int[] calculationVariable$value);

	// Getter for states.
	public int get$states();

	// Getter for v.
	public double[] get$v();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}