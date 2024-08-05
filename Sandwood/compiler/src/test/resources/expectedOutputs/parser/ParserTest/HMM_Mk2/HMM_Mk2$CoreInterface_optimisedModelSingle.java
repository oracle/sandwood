package org.sandwood.compiler.tests.parser;

interface HMM_Mk2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[][] get$bias();

	// Setter for bias.
	public void set$bias(double[][] cv$value);

	// Getter for events.
	public int[][] get$events();

	// Setter for events.
	public void set$events(int[][] cv$value);

	// Getter for eventsMeasured.
	public int[][] get$eventsMeasured();

	// Setter for eventsMeasured.
	public void set$eventsMeasured(int[][] cv$value);

	// Getter for fixedFlag$sample26.
	public boolean get$fixedFlag$sample26();

	// Setter for fixedFlag$sample26.
	public void set$fixedFlag$sample26(boolean cv$value);

	// Getter for fixedFlag$sample33.
	public boolean get$fixedFlag$sample33();

	// Setter for fixedFlag$sample33.
	public void set$fixedFlag$sample33(boolean cv$value);

	// Getter for fixedFlag$sample48.
	public boolean get$fixedFlag$sample48();

	// Setter for fixedFlag$sample48.
	public void set$fixedFlag$sample48(boolean cv$value);

	// Getter for fixedFlag$sample50.
	public boolean get$fixedFlag$sample50();

	// Setter for fixedFlag$sample50.
	public void set$fixedFlag$sample50(boolean cv$value);

	// Getter for fixedFlag$sample58.
	public boolean get$fixedFlag$sample58();

	// Setter for fixedFlag$sample58.
	public void set$fixedFlag$sample58(boolean cv$value);

	// Getter for fixedFlag$sample78.
	public boolean get$fixedFlag$sample78();

	// Setter for fixedFlag$sample78.
	public void set$fixedFlag$sample78(boolean cv$value);

	// Getter for fixedFlag$sample99.
	public boolean get$fixedFlag$sample99();

	// Setter for fixedFlag$sample99.
	public void set$fixedFlag$sample99(boolean cv$value);

	// Getter for initialState.
	public int get$initialState();

	// Setter for initialState.
	public void set$initialState(int cv$value);

	// Getter for length$eventsMeasured.
	public int[] get$length$eventsMeasured();

	// Setter for length$eventsMeasured.
	public void set$length$eventsMeasured(int[] cv$value);

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$events.
	public double get$logProbability$events();

	// Getter for logProbability$initialState.
	public double get$logProbability$initialState();

	// Getter for logProbability$m.
	public double get$logProbability$m();

	// Getter for logProbability$st.
	public double get$logProbability$st();

	// Getter for logProbability$weights.
	public double get$logProbability$weights();

	// Getter for m.
	public double[][] get$m();

	// Setter for m.
	public void set$m(double[][] cv$value);

	// Getter for noEvents.
	public int get$noEvents();

	// Setter for noEvents.
	public void set$noEvents(int cv$value);

	// Getter for noStates.
	public int get$noStates();

	// Setter for noStates.
	public void set$noStates(int cv$value);

	// Getter for samples.
	public int get$samples();

	// Getter for st.
	public int[][] get$st();

	// Setter for st.
	public void set$st(int[][] cv$value);

	// Getter for v.
	public double[] get$v();

	// Getter for v2.
	public double[] get$v2();

	// Getter for weights.
	public double[] get$weights();

	// Setter for weights.
	public void set$weights(double[] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}