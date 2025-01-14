package org.sandwood.compiler.tests.parser;

interface HMMMetrics4$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for current_metric_mean.
	public double[][] get$current_metric_mean();

	// Setter for current_metric_mean.
	public void set$current_metric_mean(double[][] cv$value);

	// Getter for current_metric_valid_bias.
	public double[][] get$current_metric_valid_bias();

	// Setter for current_metric_valid_bias.
	public void set$current_metric_valid_bias(double[][] cv$value);

	// Getter for current_metric_var.
	public double[][] get$current_metric_var();

	// Setter for current_metric_var.
	public void set$current_metric_var(double[][] cv$value);

	// Getter for fixedFlag$sample111.
	public boolean get$fixedFlag$sample111();

	// Setter for fixedFlag$sample111.
	public void set$fixedFlag$sample111(boolean cv$value);

	// Getter for fixedFlag$sample126.
	public boolean get$fixedFlag$sample126();

	// Setter for fixedFlag$sample126.
	public void set$fixedFlag$sample126(boolean cv$value);

	// Getter for fixedFlag$sample161.
	public boolean get$fixedFlag$sample161();

	// Setter for fixedFlag$sample161.
	public void set$fixedFlag$sample161(boolean cv$value);

	// Getter for fixedFlag$sample173.
	public boolean get$fixedFlag$sample173();

	// Setter for fixedFlag$sample173.
	public void set$fixedFlag$sample173(boolean cv$value);

	// Getter for fixedFlag$sample24.
	public boolean get$fixedFlag$sample24();

	// Setter for fixedFlag$sample24.
	public void set$fixedFlag$sample24(boolean cv$value);

	// Getter for fixedFlag$sample30.
	public boolean get$fixedFlag$sample30();

	// Setter for fixedFlag$sample30.
	public void set$fixedFlag$sample30(boolean cv$value);

	// Getter for fixedFlag$sample50.
	public boolean get$fixedFlag$sample50();

	// Setter for fixedFlag$sample50.
	public void set$fixedFlag$sample50(boolean cv$value);

	// Getter for fixedFlag$sample63.
	public boolean get$fixedFlag$sample63();

	// Setter for fixedFlag$sample63.
	public void set$fixedFlag$sample63(boolean cv$value);

	// Getter for fixedFlag$sample96.
	public boolean get$fixedFlag$sample96();

	// Setter for fixedFlag$sample96.
	public void set$fixedFlag$sample96(boolean cv$value);

	// Getter for initialStateDistribution.
	public double[] get$initialStateDistribution();

	// Setter for initialStateDistribution.
	public void set$initialStateDistribution(double[] cv$value);

	// Getter for length$metric.
	public int[][] get$length$metric();

	// Setter for length$metric.
	public void set$length$metric(int[][] cv$value);

	// Getter for logProbability$current_metric_mean.
	public double get$logProbability$current_metric_mean();

	// Getter for logProbability$current_metric_valid_bias.
	public double get$logProbability$current_metric_valid_bias();

	// Getter for logProbability$current_metric_var.
	public double get$logProbability$current_metric_var();

	// Getter for logProbability$initialStateDistribution.
	public double get$logProbability$initialStateDistribution();

	// Getter for logProbability$m.
	public double get$logProbability$m();

	// Getter for logProbability$metric_g.
	public double get$logProbability$metric_g();

	// Getter for logProbability$metric_valid_g.
	public double get$logProbability$metric_valid_g();

	// Getter for logProbability$st.
	public double get$logProbability$st();

	// Getter for m.
	public double[][] get$m();

	// Setter for m.
	public void set$m(double[][] cv$value);

	// Getter for max_metric.
	public int get$max_metric();

	// Setter for max_metric.
	public void set$max_metric(int cv$value);

	// Getter for metric.
	public double[][][] get$metric();

	// Setter for metric.
	public void set$metric(double[][][] cv$value);

	// Getter for metric_g.
	public double[][][] get$metric_g();

	// Setter for metric_g.
	public void set$metric_g(double[][][] cv$value);

	// Getter for metric_valid.
	public boolean[][][] get$metric_valid();

	// Setter for metric_valid.
	public void set$metric_valid(boolean[][][] cv$value);

	// Getter for metric_valid_g.
	public boolean[][][] get$metric_valid_g();

	// Setter for metric_valid_g.
	public void set$metric_valid_g(boolean[][][] cv$value);

	// Getter for noSamples.
	public int get$noSamples();

	// Getter for noServers.
	public int get$noServers();

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