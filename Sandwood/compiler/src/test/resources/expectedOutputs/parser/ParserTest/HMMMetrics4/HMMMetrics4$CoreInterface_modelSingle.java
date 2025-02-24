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

	// Getter for fixedFlag$sample134.
	public boolean get$fixedFlag$sample134();

	// Setter for fixedFlag$sample134.
	public void set$fixedFlag$sample134(boolean cv$value);

	// Getter for fixedFlag$sample162.
	public boolean get$fixedFlag$sample162();

	// Setter for fixedFlag$sample162.
	public void set$fixedFlag$sample162(boolean cv$value);

	// Getter for fixedFlag$sample190.
	public boolean get$fixedFlag$sample190();

	// Setter for fixedFlag$sample190.
	public void set$fixedFlag$sample190(boolean cv$value);

	// Getter for fixedFlag$sample20.
	public boolean get$fixedFlag$sample20();

	// Setter for fixedFlag$sample20.
	public void set$fixedFlag$sample20(boolean cv$value);

	// Getter for fixedFlag$sample241.
	public boolean get$fixedFlag$sample241();

	// Setter for fixedFlag$sample241.
	public void set$fixedFlag$sample241(boolean cv$value);

	// Getter for fixedFlag$sample256.
	public boolean get$fixedFlag$sample256();

	// Setter for fixedFlag$sample256.
	public void set$fixedFlag$sample256(boolean cv$value);

	// Getter for fixedFlag$sample33.
	public boolean get$fixedFlag$sample33();

	// Setter for fixedFlag$sample33.
	public void set$fixedFlag$sample33(boolean cv$value);

	// Getter for fixedFlag$sample57.
	public boolean get$fixedFlag$sample57();

	// Setter for fixedFlag$sample57.
	public void set$fixedFlag$sample57(boolean cv$value);

	// Getter for fixedFlag$sample76.
	public boolean get$fixedFlag$sample76();

	// Setter for fixedFlag$sample76.
	public void set$fixedFlag$sample76(boolean cv$value);

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