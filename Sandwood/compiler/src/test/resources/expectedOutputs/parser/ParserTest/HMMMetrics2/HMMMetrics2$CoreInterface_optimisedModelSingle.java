package org.sandwood.compiler.tests.parser;

interface HMMMetrics2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for fixedFlag$sample117.
	public boolean get$fixedFlag$sample117();

	// Setter for fixedFlag$sample117.
	public void set$fixedFlag$sample117(boolean cv$value);

	// Getter for fixedFlag$sample136.
	public boolean get$fixedFlag$sample136();

	// Setter for fixedFlag$sample136.
	public void set$fixedFlag$sample136(boolean cv$value);

	// Getter for fixedFlag$sample158.
	public boolean get$fixedFlag$sample158();

	// Setter for fixedFlag$sample158.
	public void set$fixedFlag$sample158(boolean cv$value);

	// Getter for fixedFlag$sample170.
	public boolean get$fixedFlag$sample170();

	// Setter for fixedFlag$sample170.
	public void set$fixedFlag$sample170(boolean cv$value);

	// Getter for fixedFlag$sample30.
	public boolean get$fixedFlag$sample30();

	// Setter for fixedFlag$sample30.
	public void set$fixedFlag$sample30(boolean cv$value);

	// Getter for fixedFlag$sample43.
	public boolean get$fixedFlag$sample43();

	// Setter for fixedFlag$sample43.
	public void set$fixedFlag$sample43(boolean cv$value);

	// Getter for fixedFlag$sample63.
	public boolean get$fixedFlag$sample63();

	// Setter for fixedFlag$sample63.
	public void set$fixedFlag$sample63(boolean cv$value);

	// Getter for fixedFlag$sample79.
	public boolean get$fixedFlag$sample79();

	// Setter for fixedFlag$sample79.
	public void set$fixedFlag$sample79(boolean cv$value);

	// Getter for fixedFlag$sample95.
	public boolean get$fixedFlag$sample95();

	// Setter for fixedFlag$sample95.
	public void set$fixedFlag$sample95(boolean cv$value);

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