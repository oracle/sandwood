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

	// Getter for fixedFlag$sample103.
	public boolean get$fixedFlag$sample103();

	// Setter for fixedFlag$sample103.
	public void set$fixedFlag$sample103(boolean cv$value);

	// Getter for fixedFlag$sample136.
	public boolean get$fixedFlag$sample136();

	// Setter for fixedFlag$sample136.
	public void set$fixedFlag$sample136(boolean cv$value);

	// Getter for fixedFlag$sample171.
	public boolean get$fixedFlag$sample171();

	// Setter for fixedFlag$sample171.
	public void set$fixedFlag$sample171(boolean cv$value);

	// Getter for fixedFlag$sample47.
	public boolean get$fixedFlag$sample47();

	// Setter for fixedFlag$sample47.
	public void set$fixedFlag$sample47(boolean cv$value);

	// Getter for fixedFlag$sample62.
	public boolean get$fixedFlag$sample62();

	// Setter for fixedFlag$sample62.
	public void set$fixedFlag$sample62(boolean cv$value);

	// Getter for fixedFlag$sample86.
	public boolean get$fixedFlag$sample86();

	// Setter for fixedFlag$sample86.
	public void set$fixedFlag$sample86(boolean cv$value);

	// Getter for fixedFlag$sample88.
	public boolean get$fixedFlag$sample88();

	// Setter for fixedFlag$sample88.
	public void set$fixedFlag$sample88(boolean cv$value);

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