package org.sandwood.compiler.tests.parser;

interface HMMMetrics2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for fixedFlag$sample100.
	public boolean get$fixedFlag$sample100();

	// Setter for fixedFlag$sample100.
	public void set$fixedFlag$sample100(boolean cv$value);

	// Getter for fixedFlag$sample109.
	public boolean get$fixedFlag$sample109();

	// Setter for fixedFlag$sample109.
	public void set$fixedFlag$sample109(boolean cv$value);

	// Getter for fixedFlag$sample23.
	public boolean get$fixedFlag$sample23();

	// Setter for fixedFlag$sample23.
	public void set$fixedFlag$sample23(boolean cv$value);

	// Getter for fixedFlag$sample29.
	public boolean get$fixedFlag$sample29();

	// Setter for fixedFlag$sample29.
	public void set$fixedFlag$sample29(boolean cv$value);

	// Getter for fixedFlag$sample41.
	public boolean get$fixedFlag$sample41();

	// Setter for fixedFlag$sample41.
	public void set$fixedFlag$sample41(boolean cv$value);

	// Getter for fixedFlag$sample50.
	public boolean get$fixedFlag$sample50();

	// Setter for fixedFlag$sample50.
	public void set$fixedFlag$sample50(boolean cv$value);

	// Getter for fixedFlag$sample59.
	public boolean get$fixedFlag$sample59();

	// Setter for fixedFlag$sample59.
	public void set$fixedFlag$sample59(boolean cv$value);

	// Getter for fixedFlag$sample73.
	public boolean get$fixedFlag$sample73();

	// Setter for fixedFlag$sample73.
	public void set$fixedFlag$sample73(boolean cv$value);

	// Getter for fixedFlag$sample86.
	public boolean get$fixedFlag$sample86();

	// Setter for fixedFlag$sample86.
	public void set$fixedFlag$sample86(boolean cv$value);

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