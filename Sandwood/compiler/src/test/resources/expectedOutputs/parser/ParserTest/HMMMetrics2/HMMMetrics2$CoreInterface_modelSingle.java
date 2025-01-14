package org.sandwood.compiler.tests.parser;

interface HMMMetrics2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for fixedFlag$sample104.
	public boolean get$fixedFlag$sample104();

	// Setter for fixedFlag$sample104.
	public void set$fixedFlag$sample104(boolean cv$value);

	// Getter for fixedFlag$sample113.
	public boolean get$fixedFlag$sample113();

	// Setter for fixedFlag$sample113.
	public void set$fixedFlag$sample113(boolean cv$value);

	// Getter for fixedFlag$sample23.
	public boolean get$fixedFlag$sample23();

	// Setter for fixedFlag$sample23.
	public void set$fixedFlag$sample23(boolean cv$value);

	// Getter for fixedFlag$sample29.
	public boolean get$fixedFlag$sample29();

	// Setter for fixedFlag$sample29.
	public void set$fixedFlag$sample29(boolean cv$value);

	// Getter for fixedFlag$sample42.
	public boolean get$fixedFlag$sample42();

	// Setter for fixedFlag$sample42.
	public void set$fixedFlag$sample42(boolean cv$value);

	// Getter for fixedFlag$sample51.
	public boolean get$fixedFlag$sample51();

	// Setter for fixedFlag$sample51.
	public void set$fixedFlag$sample51(boolean cv$value);

	// Getter for fixedFlag$sample60.
	public boolean get$fixedFlag$sample60();

	// Setter for fixedFlag$sample60.
	public void set$fixedFlag$sample60(boolean cv$value);

	// Getter for fixedFlag$sample75.
	public boolean get$fixedFlag$sample75();

	// Setter for fixedFlag$sample75.
	public void set$fixedFlag$sample75(boolean cv$value);

	// Getter for fixedFlag$sample88.
	public boolean get$fixedFlag$sample88();

	// Setter for fixedFlag$sample88.
	public void set$fixedFlag$sample88(boolean cv$value);

	// Getter for initialStateDistribution.
	public double[] get$initialStateDistribution();

	// Setter for initialStateDistribution.
	public void set$initialStateDistribution(double[] cv$value);

	// Getter for length$metric.
	public int[] get$length$metric();

	// Setter for length$metric.
	public void set$length$metric(int[] cv$value);

	// Getter for logProbability$initialStateDistribution.
	public double get$logProbability$initialStateDistribution();

	// Getter for logProbability$m.
	public double get$logProbability$m();

	// Getter for logProbability$metric_g.
	public double get$logProbability$metric_g();

	// Getter for logProbability$metric_mean.
	public double get$logProbability$metric_mean();

	// Getter for logProbability$metric_valid_bias.
	public double get$logProbability$metric_valid_bias();

	// Getter for logProbability$metric_valid_g.
	public double get$logProbability$metric_valid_g();

	// Getter for logProbability$metric_var.
	public double get$logProbability$metric_var();

	// Getter for logProbability$st.
	public double get$logProbability$st();

	// Getter for m.
	public double[][] get$m();

	// Setter for m.
	public void set$m(double[][] cv$value);

	// Getter for metric.
	public double[][] get$metric();

	// Setter for metric.
	public void set$metric(double[][] cv$value);

	// Getter for metric_g.
	public double[][] get$metric_g();

	// Setter for metric_g.
	public void set$metric_g(double[][] cv$value);

	// Getter for metric_mean.
	public double[] get$metric_mean();

	// Setter for metric_mean.
	public void set$metric_mean(double[] cv$value);

	// Getter for metric_valid.
	public boolean[][] get$metric_valid();

	// Setter for metric_valid.
	public void set$metric_valid(boolean[][] cv$value);

	// Getter for metric_valid_bias.
	public double[] get$metric_valid_bias();

	// Setter for metric_valid_bias.
	public void set$metric_valid_bias(double[] cv$value);

	// Getter for metric_valid_g.
	public boolean[][] get$metric_valid_g();

	// Setter for metric_valid_g.
	public void set$metric_valid_g(boolean[][] cv$value);

	// Getter for metric_var.
	public double[] get$metric_var();

	// Setter for metric_var.
	public void set$metric_var(double[] cv$value);

	// Getter for noSamples.
	public int get$noSamples();

	// Getter for noStates.
	public int get$noStates();

	// Setter for noStates.
	public void set$noStates(int cv$value);

	// Getter for st.
	public int[][] get$st();

	// Setter for st.
	public void set$st(int[][] cv$value);

	// Getter for v.
	public double[] get$v();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}