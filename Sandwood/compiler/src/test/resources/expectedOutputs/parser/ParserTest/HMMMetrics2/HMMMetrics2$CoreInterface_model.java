package org.sandwood.compiler.tests.parser;

interface HMMMetrics2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for fixedFlag$sample104.
	public boolean get$fixedFlag$sample104();

	// Setter for fixedFlag$sample104.
	public void set$fixedFlag$sample104(boolean cv$value);

	// Getter for fixedFlag$sample123.
	public boolean get$fixedFlag$sample123();

	// Setter for fixedFlag$sample123.
	public void set$fixedFlag$sample123(boolean cv$value);

	// Getter for fixedFlag$sample157.
	public boolean get$fixedFlag$sample157();

	// Setter for fixedFlag$sample157.
	public void set$fixedFlag$sample157(boolean cv$value);

	// Getter for fixedFlag$sample19.
	public boolean get$fixedFlag$sample19();

	// Setter for fixedFlag$sample19.
	public void set$fixedFlag$sample19(boolean cv$value);

	// Getter for fixedFlag$sample32.
	public boolean get$fixedFlag$sample32();

	// Setter for fixedFlag$sample32.
	public void set$fixedFlag$sample32(boolean cv$value);

	// Getter for fixedFlag$sample52.
	public boolean get$fixedFlag$sample52();

	// Setter for fixedFlag$sample52.
	public void set$fixedFlag$sample52(boolean cv$value);

	// Getter for fixedFlag$sample68.
	public boolean get$fixedFlag$sample68();

	// Setter for fixedFlag$sample68.
	public void set$fixedFlag$sample68(boolean cv$value);

	// Getter for fixedFlag$sample84.
	public boolean get$fixedFlag$sample84();

	// Setter for fixedFlag$sample84.
	public void set$fixedFlag$sample84(boolean cv$value);

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