package org.sandwood.compiler.tests.parser;

interface HMMTestPart3d$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] cv$value);

	// Getter for fixedFlag$sample123.
	public boolean get$fixedFlag$sample123();

	// Setter for fixedFlag$sample123.
	public void set$fixedFlag$sample123(boolean cv$value);

	// Getter for fixedFlag$sample31.
	public boolean get$fixedFlag$sample31();

	// Setter for fixedFlag$sample31.
	public void set$fixedFlag$sample31(boolean cv$value);

	// Getter for fixedFlag$sample48.
	public boolean get$fixedFlag$sample48();

	// Setter for fixedFlag$sample48.
	public void set$fixedFlag$sample48(boolean cv$value);

	// Getter for fixedFlag$sample58.
	public boolean get$fixedFlag$sample58();

	// Setter for fixedFlag$sample58.
	public void set$fixedFlag$sample58(boolean cv$value);

	// Getter for fixedFlag$sample83.
	public boolean get$fixedFlag$sample83();

	// Setter for fixedFlag$sample83.
	public void set$fixedFlag$sample83(boolean cv$value);

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