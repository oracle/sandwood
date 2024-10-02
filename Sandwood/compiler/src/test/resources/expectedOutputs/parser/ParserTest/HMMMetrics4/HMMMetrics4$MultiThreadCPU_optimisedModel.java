package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics4$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMMetrics4$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] current_metric_mean;
	private double[][] current_metric_valid_bias;
	private double[][] current_metric_var;
	private double[][] cv$distributionAccumulator$var83;
	private double[][] cv$var245$stateProbabilityGlobal;
	private double[] cv$var28$countGlobal;
	private double[][] cv$var41$countGlobal;
	private double[][] cv$var65$stateProbabilityGlobal;
	private double[][] cv$var84$stateProbabilityGlobal;
	private double[][] distribution$sample71;
	private double[][][] distribution$sample90;
	private boolean fixedFlag$sample150 = false;
	private boolean fixedFlag$sample178 = false;
	private boolean fixedFlag$sample206 = false;
	private boolean fixedFlag$sample260 = false;
	private boolean fixedFlag$sample275 = false;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample44 = false;
	private boolean fixedFlag$sample71 = false;
	private boolean fixedFlag$sample90 = false;
	private boolean fixedProbFlag$sample150 = false;
	private boolean fixedProbFlag$sample178 = false;
	private boolean fixedProbFlag$sample206 = false;
	private boolean fixedProbFlag$sample260 = false;
	private boolean fixedProbFlag$sample275 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample44 = false;
	private boolean fixedProbFlag$sample71 = false;
	private boolean fixedProbFlag$sample90 = false;
	private boolean[][][][] guard$sample71gaussian274$global;
	private boolean[][][][] guard$sample90gaussian274$global;
	private double[] initialStateDistribution;
	private int[][] length$metric;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$current_metric_mean;
	private double logProbability$current_metric_valid_bias;
	private double logProbability$current_metric_var;
	private double logProbability$initialStateDistribution;
	private double logProbability$m;
	private double logProbability$metric_g;
	private double logProbability$metric_valid_g;
	private double logProbability$metric_valid_inner;
	private double[][][] logProbability$sample260;
	private double[][][] logProbability$sample275;
	private double[] logProbability$sample71;
	private double[][] logProbability$sample90;
	private double logProbability$st;
	private double logProbability$var119;
	private double logProbability$var141;
	private double logProbability$var146;
	private double logProbability$var168;
	private double logProbability$var173;
	private double logProbability$var195;
	private double[][][] logProbability$var244;
	private double[][][] logProbability$var257;
	private double logProbability$var27;
	private double logProbability$var29;
	private double logProbability$var41;
	private double[] logProbability$var64;
	private double[][] logProbability$var83;
	private double[][] m;
	private int max_metric;
	private double[][][] metric;
	private double[][][] metric_g;
	private boolean[][][] metric_valid;
	private boolean[][][] metric_valid_g;
	private int noSamples;
	private int noServers;
	private int noStates;
	private boolean setFlag$current_metric_mean = false;
	private boolean setFlag$current_metric_valid_bias = false;
	private boolean setFlag$current_metric_var = false;
	private boolean setFlag$initialStateDistribution = false;
	private boolean setFlag$m = false;
	private boolean setFlag$metric_g = false;
	private boolean setFlag$metric_valid_g = false;
	private boolean setFlag$st = false;
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMMetrics4$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for current_metric_mean.
	@Override
	public final double[][] get$current_metric_mean() {
		return current_metric_mean;
	}

	// Setter for current_metric_mean.
	@Override
	public final void set$current_metric_mean(double[][] cv$value) {
		// Set flags for all the side effects of current_metric_mean including if probabilities
		// need to be updated.
		// Set current_metric_mean with flag to mark that it has been set so another array
		// doesn't need to be constructed
		current_metric_mean = cv$value;
		setFlag$current_metric_mean = true;
		
		// Unset the fixed probability flag for sample 150 as it depends on current_metric_mean.
		fixedProbFlag$sample150 = false;
		
		// Unset the fixed probability flag for sample 275 as it depends on current_metric_mean.
		fixedProbFlag$sample275 = false;
	}

	// Getter for current_metric_valid_bias.
	@Override
	public final double[][] get$current_metric_valid_bias() {
		return current_metric_valid_bias;
	}

	// Setter for current_metric_valid_bias.
	@Override
	public final void set$current_metric_valid_bias(double[][] cv$value) {
		// Set flags for all the side effects of current_metric_valid_bias including if probabilities
		// need to be updated.
		// Set current_metric_valid_bias with flag to mark that it has been set so another
		// array doesn't need to be constructed
		current_metric_valid_bias = cv$value;
		setFlag$current_metric_valid_bias = true;
		
		// Unset the fixed probability flag for sample 206 as it depends on current_metric_valid_bias.
		fixedProbFlag$sample206 = false;
		
		// Unset the fixed probability flag for sample 260 as it depends on current_metric_valid_bias.
		fixedProbFlag$sample260 = false;
	}

	// Getter for current_metric_var.
	@Override
	public final double[][] get$current_metric_var() {
		return current_metric_var;
	}

	// Setter for current_metric_var.
	@Override
	public final void set$current_metric_var(double[][] cv$value) {
		// Set flags for all the side effects of current_metric_var including if probabilities
		// need to be updated.
		// Set current_metric_var with flag to mark that it has been set so another array
		// doesn't need to be constructed
		current_metric_var = cv$value;
		setFlag$current_metric_var = true;
		
		// Unset the fixed probability flag for sample 178 as it depends on current_metric_var.
		fixedProbFlag$sample178 = false;
		
		// Unset the fixed probability flag for sample 275 as it depends on current_metric_var.
		fixedProbFlag$sample275 = false;
	}

	// Getter for fixedFlag$sample150.
	@Override
	public final boolean get$fixedFlag$sample150() {
		return fixedFlag$sample150;
	}

	// Setter for fixedFlag$sample150.
	@Override
	public final void set$fixedFlag$sample150(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample150 including if probabilities
		// need to be updated.
		fixedFlag$sample150 = cv$value;
		
		// Should the probability of sample 150 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample150" with its value "cv$value".
		fixedProbFlag$sample150 = (cv$value && fixedProbFlag$sample150);
		
		// Should the probability of sample 275 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample150" with its value "cv$value".
		fixedProbFlag$sample275 = (cv$value && fixedProbFlag$sample275);
	}

	// Getter for fixedFlag$sample178.
	@Override
	public final boolean get$fixedFlag$sample178() {
		return fixedFlag$sample178;
	}

	// Setter for fixedFlag$sample178.
	@Override
	public final void set$fixedFlag$sample178(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample178 including if probabilities
		// need to be updated.
		fixedFlag$sample178 = cv$value;
		
		// Should the probability of sample 178 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample178" with its value "cv$value".
		fixedProbFlag$sample178 = (cv$value && fixedProbFlag$sample178);
		
		// Should the probability of sample 275 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample178" with its value "cv$value".
		fixedProbFlag$sample275 = (cv$value && fixedProbFlag$sample275);
	}

	// Getter for fixedFlag$sample206.
	@Override
	public final boolean get$fixedFlag$sample206() {
		return fixedFlag$sample206;
	}

	// Setter for fixedFlag$sample206.
	@Override
	public final void set$fixedFlag$sample206(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample206 including if probabilities
		// need to be updated.
		fixedFlag$sample206 = cv$value;
		
		// Should the probability of sample 206 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample206" with its value "cv$value".
		fixedProbFlag$sample206 = (cv$value && fixedProbFlag$sample206);
		
		// Should the probability of sample 260 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample206" with its value "cv$value".
		fixedProbFlag$sample260 = (cv$value && fixedProbFlag$sample260);
	}

	// Getter for fixedFlag$sample260.
	@Override
	public final boolean get$fixedFlag$sample260() {
		return fixedFlag$sample260;
	}

	// Setter for fixedFlag$sample260.
	@Override
	public final void set$fixedFlag$sample260(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample260 including if probabilities
		// need to be updated.
		fixedFlag$sample260 = cv$value;
		
		// Should the probability of sample 260 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample260" with its value "cv$value".
		fixedProbFlag$sample260 = (cv$value && fixedProbFlag$sample260);
	}

	// Getter for fixedFlag$sample275.
	@Override
	public final boolean get$fixedFlag$sample275() {
		return fixedFlag$sample275;
	}

	// Setter for fixedFlag$sample275.
	@Override
	public final void set$fixedFlag$sample275(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample275 including if probabilities
		// need to be updated.
		fixedFlag$sample275 = cv$value;
		
		// Should the probability of sample 275 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample275" with its value "cv$value".
		fixedProbFlag$sample275 = (cv$value && fixedProbFlag$sample275);
	}

	// Getter for fixedFlag$sample31.
	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	// Setter for fixedFlag$sample31.
	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample31 including if probabilities
		// need to be updated.
		fixedFlag$sample31 = cv$value;
		
		// Should the probability of sample 31 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
		
		// Should the probability of sample 71 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
	}

	// Getter for fixedFlag$sample44.
	@Override
	public final boolean get$fixedFlag$sample44() {
		return fixedFlag$sample44;
	}

	// Setter for fixedFlag$sample44.
	@Override
	public final void set$fixedFlag$sample44(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample44 including if probabilities
		// need to be updated.
		fixedFlag$sample44 = cv$value;
		
		// Should the probability of sample 44 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample44" with its value "cv$value".
		fixedProbFlag$sample44 = (cv$value && fixedProbFlag$sample44);
		
		// Should the probability of sample 90 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample44" with its value "cv$value".
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
	}

	// Getter for fixedFlag$sample71.
	@Override
	public final boolean get$fixedFlag$sample71() {
		return fixedFlag$sample71;
	}

	// Setter for fixedFlag$sample71.
	@Override
	public final void set$fixedFlag$sample71(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample71 including if probabilities
		// need to be updated.
		fixedFlag$sample71 = cv$value;
		
		// Should the probability of sample 71 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample71" with its value "cv$value".
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
		
		// Should the probability of sample 90 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample71" with its value "cv$value".
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
		
		// Should the probability of sample 260 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample71" with its value "cv$value".
		fixedProbFlag$sample260 = (cv$value && fixedProbFlag$sample260);
		
		// Should the probability of sample 275 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample71" with its value "cv$value".
		fixedProbFlag$sample275 = (cv$value && fixedProbFlag$sample275);
	}

	// Getter for fixedFlag$sample90.
	@Override
	public final boolean get$fixedFlag$sample90() {
		return fixedFlag$sample90;
	}

	// Setter for fixedFlag$sample90.
	@Override
	public final void set$fixedFlag$sample90(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample90 including if probabilities
		// need to be updated.
		fixedFlag$sample90 = cv$value;
		
		// Should the probability of sample 90 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample90" with its value "cv$value".
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
		
		// Should the probability of sample 260 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample90" with its value "cv$value".
		fixedProbFlag$sample260 = (cv$value && fixedProbFlag$sample260);
		
		// Should the probability of sample 275 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample90" with its value "cv$value".
		fixedProbFlag$sample275 = (cv$value && fixedProbFlag$sample275);
	}

	// Getter for initialStateDistribution.
	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	// Setter for initialStateDistribution.
	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		// Set flags for all the side effects of initialStateDistribution including if probabilities
		// need to be updated.
		// Set initialStateDistribution with flag to mark that it has been set so another
		// array doesn't need to be constructed
		initialStateDistribution = cv$value;
		setFlag$initialStateDistribution = true;
		
		// Unset the fixed probability flag for sample 31 as it depends on initialStateDistribution.
		fixedProbFlag$sample31 = false;
		
		// Unset the fixed probability flag for sample 71 as it depends on initialStateDistribution.
		fixedProbFlag$sample71 = false;
	}

	// Getter for length$metric.
	@Override
	public final int[][] get$length$metric() {
		return length$metric;
	}

	// Setter for length$metric.
	@Override
	public final void set$length$metric(int[][] cv$value) {
		// Set length$metric with flag to mark that it has been set so another array doesn't
		// need to be constructed
		length$metric = cv$value;
	}

	// Getter for logProbability$$evidence.
	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	// Getter for the probability of logProbability$$model.
	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	// Getter for logProbability$current_metric_mean.
	@Override
	public final double get$logProbability$current_metric_mean() {
		return logProbability$current_metric_mean;
	}

	// Getter for logProbability$current_metric_valid_bias.
	@Override
	public final double get$logProbability$current_metric_valid_bias() {
		return logProbability$current_metric_valid_bias;
	}

	// Getter for logProbability$current_metric_var.
	@Override
	public final double get$logProbability$current_metric_var() {
		return logProbability$current_metric_var;
	}

	// Getter for logProbability$initialStateDistribution.
	@Override
	public final double get$logProbability$initialStateDistribution() {
		return logProbability$initialStateDistribution;
	}

	// Getter for logProbability$m.
	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	// Getter for logProbability$metric_g.
	@Override
	public final double get$logProbability$metric_g() {
		return logProbability$metric_g;
	}

	// Getter for logProbability$metric_valid_g.
	@Override
	public final double get$logProbability$metric_valid_g() {
		return logProbability$metric_valid_g;
	}

	// Getter for logProbability$st.
	@Override
	public final double get$logProbability$st() {
		return logProbability$st;
	}

	// Getter for m.
	@Override
	public final double[][] get$m() {
		return m;
	}

	// Setter for m.
	@Override
	public final void set$m(double[][] cv$value) {
		// Set flags for all the side effects of m including if probabilities need to be updated.
		// Set m with flag to mark that it has been set so another array doesn't need to be
		// constructed
		m = cv$value;
		setFlag$m = true;
		
		// Unset the fixed probability flag for sample 44 as it depends on m.
		fixedProbFlag$sample44 = false;
		
		// Unset the fixed probability flag for sample 90 as it depends on m.
		fixedProbFlag$sample90 = false;
	}

	// Getter for max_metric.
	@Override
	public final int get$max_metric() {
		return max_metric;
	}

	// Setter for max_metric.
	@Override
	public final void set$max_metric(int cv$value) {
		max_metric = cv$value;
	}

	// Getter for metric.
	@Override
	public final double[][][] get$metric() {
		return metric;
	}

	// Setter for metric.
	@Override
	public final void set$metric(double[][][] cv$value) {
		// Set metric with flag to mark that it has been set so another array doesn't need
		// to be constructed
		metric = cv$value;
	}

	// Getter for metric_g.
	@Override
	public final double[][][] get$metric_g() {
		return metric_g;
	}

	// Setter for metric_g.
	@Override
	public final void set$metric_g(double[][][] cv$value) {
		// Set flags for all the side effects of metric_g including if probabilities need
		// to be updated.
		// Set metric_g with flag to mark that it has been set so another array doesn't need
		// to be constructed
		metric_g = cv$value;
		setFlag$metric_g = true;
		
		// Unset the fixed probability flag for sample 275 as it depends on metric_g.
		fixedProbFlag$sample275 = false;
	}

	// Getter for metric_valid.
	@Override
	public final boolean[][][] get$metric_valid() {
		return metric_valid;
	}

	// Setter for metric_valid.
	@Override
	public final void set$metric_valid(boolean[][][] cv$value) {
		// Set metric_valid with flag to mark that it has been set so another array doesn't
		// need to be constructed
		metric_valid = cv$value;
	}

	// Getter for metric_valid_g.
	@Override
	public final boolean[][][] get$metric_valid_g() {
		return metric_valid_g;
	}

	// Setter for metric_valid_g.
	@Override
	public final void set$metric_valid_g(boolean[][][] cv$value) {
		// Set metric_valid_g with flag to mark that it has been set so another array doesn't
		// need to be constructed
		metric_valid_g = cv$value;
		setFlag$metric_valid_g = true;
	}

	// Getter for noSamples.
	@Override
	public final int get$noSamples() {
		return noSamples;
	}

	// Getter for noServers.
	@Override
	public final int get$noServers() {
		return noServers;
	}

	// Getter for noStates.
	@Override
	public final int get$noStates() {
		return noStates;
	}

	// Setter for noStates.
	@Override
	public final void set$noStates(int cv$value) {
		noStates = cv$value;
	}

	// Getter for st.
	@Override
	public final int[][] get$st() {
		return st;
	}

	// Setter for st.
	@Override
	public final void set$st(int[][] cv$value) {
		// Set flags for all the side effects of st including if probabilities need to be
		// updated.
		// Set st with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st = cv$value;
		setFlag$st = true;
		
		// Unset the fixed probability flag for sample 71 as it depends on st.
		fixedProbFlag$sample71 = false;
		
		// Unset the fixed probability flag for sample 90 as it depends on st.
		fixedProbFlag$sample90 = false;
		
		// Unset the fixed probability flag for sample 260 as it depends on st.
		fixedProbFlag$sample260 = false;
		
		// Unset the fixed probability flag for sample 275 as it depends on st.
		fixedProbFlag$sample275 = false;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Calculate the probability of the samples represented by sample260 using probability
	// distributions.
	private final void logProbabilityDistribution$sample260() {
		// Determine if we need to calculate the values for sample task 260 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample260) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 260 including any distribution
						// values.
						// 
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = metric_valid_g[sample$var207][server][timeStep$var239];
						
						// Enumerating the possible arguments for Bernoulli 244.
						if((0 == timeStep$var239)) {
							// Enumerating the possible arguments for Bernoulli 244.
							if(fixedFlag$sample71) {
								int var194 = st[sample$var207][0];
								if(((0 <= var194) && (var194 < noStates))) {
									// Store the value of the function call, so the function call is only made once.
									// 
									// Substituted "timeStep$var239" with its value "0".
									cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][st[sample$var207][0]]);
									
									// Add the probability of this distribution configuration to the accumulator.
									// 
									// An accumulator for the distributed probability space covered.
									cv$probabilityReached = 1.0;
								}
							} else {
								// Enumerating the possible outputs of Categorical 64.
								for(int index$sample71$4 = 0; index$sample71$4 < noStates; index$sample71$4 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "sample$var53" with its value "sample$var207".
									double cv$probabilitySample71Value5 = distribution$sample71[sample$var207][index$sample71$4];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample71Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][index$sample71$4]));
									
									// Add the probability of this sample task to the distribution accumulator.
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										// If the second value is -infinity.
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									
									// Add the probability of this distribution configuration to the accumulator.
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value5);
								}
							}
						}
						
						// Enumerating the possible arguments for Bernoulli 244.
						if((1 <= timeStep$var239)) {
							// Enumerating the possible arguments for Bernoulli 244.
							if(fixedFlag$sample90) {
								int var194 = st[sample$var207][timeStep$var239];
								if(((0 <= var194) && (var194 < noStates))) {
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][st[sample$var207][timeStep$var239]]);
									
									// Add the probability of this sample task to the distribution accumulator.
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										// If the second value is -infinity.
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									
									// Add the probability of this distribution configuration to the accumulator.
									cv$probabilityReached = (cv$probabilityReached + 1.0);
								}
							} else {
								// Enumerating the possible outputs of Categorical 83.
								for(int index$sample90$13 = 0; index$sample90$13 < noStates; index$sample90$13 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "sample$var53" with its value "sample$var207".
									double cv$probabilitySample90Value14 = distribution$sample90[sample$var207][(timeStep$var239 - 1)][index$sample90$13];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample90Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][index$sample90$13]));
									
									// Add the probability of this sample task to the distribution accumulator.
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										// If the second value is -infinity.
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									
									// Add the probability of this distribution configuration to the accumulator.
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample90Value14);
								}
							}
						}
						if((cv$probabilityReached == 0.0))
							// Return negative infinity if no distribution probability space is reached.
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							// Scale the probability relative to the observed distribution space.
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						
						// Add the probability of this instance of the random variable to the probability
						// of all instances of the random variable.
						// 
						// Add the probability of this sample task to the sample task accumulator.
						// 
						// Accumulator for sample probabilities for a specific instance of the random variable.
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						
						// Add the probability of this sample task to the sample task accumulator.
						// 
						// Accumulator for sample probabilities for a specific instance of the random variable.
						logProbability$var244[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
						
						// Store the sample task probability
						logProbability$sample260[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
						
						// Update the variable probability
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
						
						// Update the variable probability
						logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
					}
				}
			}
			
			// Update the variable probability
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample260 = (((fixedFlag$sample260 && fixedFlag$sample71) && fixedFlag$sample90) && fixedFlag$sample206);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						double cv$sampleValue = logProbability$sample260[sample$var207][server][timeStep$var239];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var244[sample$var207][server][timeStep$var239] = cv$sampleValue;
						
						// Update the variable probability
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
						
						// Update the variable probability
						logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
					}
				}
			}
			
			// Update the variable probability
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample275 using probability
	// distributions.
	private final void logProbabilityDistribution$sample275() {
		// Determine if we need to calculate the values for sample task 275 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample275) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						if(metric_valid_g[sample$var207][server][timeStep$var239]) {
							// An accumulator for log probabilities.
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							
							// An accumulator for the distributed probability space covered.
							double cv$probabilityReached = 0.0;
							
							// Look for paths between the variable and the sample task 275 including any distribution
							// values.
							// 
							// The sample value to calculate the probability of generating
							double cv$sampleValue = metric_g[sample$var207][server][timeStep$var239];
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(((0 == timeStep$var239) && metric_valid_g[sample$var207][server][0])) {
								// Enumerating the possible arguments for Gaussian 257.
								// 
								// Enumerating the possible arguments for Gaussian 257.
								if(fixedFlag$sample71) {
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((0 <= st[sample$var207][0])) {
										int var140 = st[sample$var207][0];
										
										// Substituted "timeStep$var239" with its value "0".
										if(((0 <= var140) && (var140 < noStates))) {
											// Substituted "timeStep$var239" with its value "0".
											double var256 = current_metric_var[server][st[sample$var207][0]];
											
											// Store the value of the function call, so the function call is only made once.
											// 
											// Substituted "timeStep$var239" with its value "0".
											cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var207][0]]) / Math.sqrt(var256))) - (Math.log(var256) * 0.5));
											
											// Add the probability of this distribution configuration to the accumulator.
											// 
											// An accumulator for the distributed probability space covered.
											cv$probabilityReached = 1.0;
										}
									}
								} else {
									// Enumerating the possible outputs of Categorical 64.
									for(int index$sample71$4 = 0; index$sample71$4 < noStates; index$sample71$4 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "sample$var53" with its value "sample$var207".
										double cv$probabilitySample71Value5 = distribution$sample71[sample$var207][index$sample71$4];
										double var256 = current_metric_var[server][index$sample71$4];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = ((Math.log(cv$probabilitySample71Value5) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][index$sample71$4]) / Math.sqrt(var256)))) - (Math.log(var256) * 0.5));
										
										// Add the probability of this sample task to the distribution accumulator.
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											// If the second value is -infinity.
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										
										// Add the probability of this distribution configuration to the accumulator.
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value5);
									}
								}
							}
							
							// Enumerating the possible arguments for Gaussian 257.
							if((1 <= timeStep$var239)) {
								// Enumerating the possible arguments for Gaussian 257.
								if(fixedFlag$sample90) {
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((0 <= st[sample$var207][timeStep$var239])) {
										int var140 = st[sample$var207][timeStep$var239];
										if(((0 <= var140) && (var140 < noStates))) {
											double var256 = current_metric_var[server][st[sample$var207][timeStep$var239]];
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var207][timeStep$var239]]) / Math.sqrt(var256))) - (Math.log(var256) * 0.5));
											
											// Add the probability of this sample task to the distribution accumulator.
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												// If the second value is -infinity.
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											
											// Add the probability of this distribution configuration to the accumulator.
											cv$probabilityReached = (cv$probabilityReached + 1.0);
										}
									}
								} else {
									// Enumerating the possible outputs of Categorical 83.
									for(int index$sample90$49 = 0; index$sample90$49 < noStates; index$sample90$49 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "sample$var53" with its value "sample$var207".
										double cv$probabilitySample90Value50 = distribution$sample90[sample$var207][(timeStep$var239 - 1)][index$sample90$49];
										double var256 = current_metric_var[server][index$sample90$49];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = ((Math.log(cv$probabilitySample90Value50) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][index$sample90$49]) / Math.sqrt(var256)))) - (Math.log(var256) * 0.5));
										
										// Add the probability of this sample task to the distribution accumulator.
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											// If the second value is -infinity.
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										
										// Add the probability of this distribution configuration to the accumulator.
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample90Value50);
									}
								}
							}
							if((cv$probabilityReached == 0.0))
								// Return negative infinity if no distribution probability space is reached.
								cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							else
								// Scale the probability relative to the observed distribution space.
								cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
							
							// Add the probability of this instance of the random variable to the probability
							// of all instances of the random variable.
							// 
							// Add the probability of this sample task to the sample task accumulator.
							// 
							// Accumulator for sample probabilities for a specific instance of the random variable.
							cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
							
							// Add the probability of this sample task to the sample task accumulator.
							// 
							// Accumulator for sample probabilities for a specific instance of the random variable.
							logProbability$var257[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
							
							// Store the sample task probability
							logProbability$sample275[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample275 = ((((fixedFlag$sample275 && fixedFlag$sample71) && fixedFlag$sample90) && fixedFlag$sample150) && fixedFlag$sample178);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						if(metric_valid_g[sample$var207][server][timeStep$var239]) {
							// Variable declaration of cv$rvAccumulator moved.
							double cv$rvAccumulator = logProbability$sample275[sample$var207][server][timeStep$var239];
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var257[sample$var207][server][timeStep$var239] = cv$rvAccumulator;
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample71 using probability
	// distributions.
	private final void logProbabilityDistribution$sample71() {
		// Determine if we need to calculate the values for sample task 71 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample71) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample71) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[sample$var53][0];
					
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// An accumulator for log probabilities.
					// 
					// Store the value of the function call, so the function call is only made once.
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					// 
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// An accumulator for log probabilities.
					// 
					// Store the value of the function call, so the function call is only made once.
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					logProbability$var64[sample$var53] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample71[sample$var53] = cv$distributionAccumulator;
				}
				
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample71" with its value "true".
				fixedProbFlag$sample71 = fixedFlag$sample31;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample71[sample$var53];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var64[sample$var53] = cv$rvAccumulator;
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample71)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample90 using probability
	// distributions.
	private final void logProbabilityDistribution$sample90() {
		// Determine if we need to calculate the values for sample task 90 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample90) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample90) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
					for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[sample$var53][timeStep$var76];
						
						// Enumerating the possible arguments for Categorical 83.
						if((1 == timeStep$var76)) {
							// Enumerating the possible arguments for Categorical 83.
							if(fixedFlag$sample71) {
								int var40 = st[sample$var53][0];
								
								// Substituted "timeStep$var76" with its value "1".
								if(((0 <= var40) && (var40 < noStates))) {
									// Substituted "timeStep$var76" with its value "1".
									double[] var82 = m[st[sample$var53][0]];
									
									// Store the value of the function call, so the function call is only made once.
									cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var82.length))?Math.log(var82[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									
									// Add the probability of this distribution configuration to the accumulator.
									// 
									// An accumulator for the distributed probability space covered.
									cv$probabilityReached = 1.0;
								}
							} else {
								// Enumerating the possible outputs of Categorical 64.
								for(int index$sample71$6 = 0; index$sample71$6 < noStates; index$sample71$6 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$5" with its value "sample$var53".
									double cv$probabilitySample71Value7 = distribution$sample71[sample$var53][index$sample71$6];
									double[] var82 = m[index$sample71$6];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample71Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var82.length))?Math.log(var82[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									
									// Add the probability of this sample task to the distribution accumulator.
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										// If the second value is -infinity.
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									
									// Add the probability of this distribution configuration to the accumulator.
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value7);
								}
							}
						}
						
						// Substituted "index$sample$13_1" with its value "sample$var53".
						// 
						// Substituted "index$timeStep$13_2" with its value "(timeStep$var76 - 1)".
						if((2 <= timeStep$var76)) {
							int var40 = st[sample$var53][(timeStep$var76 - 1)];
							if(((0 <= var40) && (var40 < noStates))) {
								double[] var82 = m[st[sample$var53][(timeStep$var76 - 1)]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var82.length))?Math.log(var82[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
						if((cv$probabilityReached == 0.0))
							// Return negative infinity if no distribution probability space is reached.
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							// Scale the probability relative to the observed distribution space.
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						
						// Add the probability of this instance of the random variable to the probability
						// of all instances of the random variable.
						// 
						// Add the probability of this sample task to the sample task accumulator.
						// 
						// Accumulator for sample probabilities for a specific instance of the random variable.
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						
						// Add the probability of this sample task to the sample task accumulator.
						// 
						// Accumulator for sample probabilities for a specific instance of the random variable.
						logProbability$var83[sample$var53][(timeStep$var76 - 1)] = cv$distributionAccumulator;
						
						// Store the sample task probability
						logProbability$sample90[sample$var53][(timeStep$var76 - 1)] = cv$distributionAccumulator;
					}
				}
				
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample90" with its value "true".
				fixedProbFlag$sample90 = (fixedFlag$sample44 && fixedFlag$sample71);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
					// Variable declaration of cv$rvAccumulator moved.
					double cv$rvAccumulator = logProbability$sample90[sample$var53][(timeStep$var76 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var83[sample$var53][(timeStep$var76 - 1)] = cv$rvAccumulator;
				}
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample90)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample150 using sampled
	// values.
	private final void logProbabilityValue$sample150() {
		// Determine if we need to calculate the values for sample task 150 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample150) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var130 = 0; var130 < noServers; var130 += 1) {
				for(int var140 = 0; var140 < noStates; var140 += 1) {
					// The sample value to calculate the probability of generating
					double cv$sampleValue = current_metric_mean[var130][var140];
					
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					// 
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// An accumulator for log probabilities.
					// 
					// Store the value of the function call, so the function call is only made once.
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue <= (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY));
				}
			}
			logProbability$var119 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var141 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample150)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample150 = fixedFlag$sample150;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var119 = logProbability$var141;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$current_metric_mean = (logProbability$current_metric_mean + logProbability$var141);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var141);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample150)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var141);
		}
	}

	// Calculate the probability of the samples represented by sample178 using sampled
	// values.
	private final void logProbabilityValue$sample178() {
		// Determine if we need to calculate the values for sample task 178 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample178) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var157 = 0; var157 < noServers; var157 += 1) {
				for(int var167 = 0; var167 < noStates; var167 += 1)
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					// 
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// An accumulator for log probabilities.
					// 
					// Store the value of the function call, so the function call is only made once.
					// 
					// The sample value to calculate the probability of generating
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(current_metric_var[var157][var167], 1.0, 1.0));
			}
			logProbability$var146 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var168 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample178)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample178 = fixedFlag$sample178;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var146 = logProbability$var168;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$current_metric_var = (logProbability$current_metric_var + logProbability$var168);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var168);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample178)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var168);
		}
	}

	// Calculate the probability of the samples represented by sample206 using sampled
	// values.
	private final void logProbabilityValue$sample206() {
		// Determine if we need to calculate the values for sample task 206 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample206) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var184 = 0; var184 < noServers; var184 += 1) {
				for(int var194 = 0; var194 < noStates; var194 += 1)
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					// 
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// An accumulator for log probabilities.
					// 
					// Store the value of the function call, so the function call is only made once.
					// 
					// The sample value to calculate the probability of generating
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(current_metric_valid_bias[var184][var194], 1.0, 1.0));
			}
			logProbability$var173 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var195 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample206)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample206 = fixedFlag$sample206;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var173 = logProbability$var195;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + logProbability$var195);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var195);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample206)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var195);
		}
	}

	// Calculate the probability of the samples represented by sample260 using sampled
	// values.
	private final void logProbabilityValue$sample260() {
		// Determine if we need to calculate the values for sample task 260 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample260) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						// Variable declaration of cv$distributionAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$distributionAccumulator moved.
						// Declaration comment was:
						// An accumulator for log probabilities.
						// 
						// Store the value of the function call, so the function call is only made once.
						// 
						// The sample value to calculate the probability of generating
						// 
						// Scale the probability relative to the observed distribution space.
						// 
						// Add the probability of this distribution configuration to the accumulator.
						// 
						// An accumulator for the distributed probability space covered.
						// 
						// Variable declaration of cv$distributionAccumulator moved.
						// Declaration comment was:
						// An accumulator for log probabilities.
						// 
						// Store the value of the function call, so the function call is only made once.
						// 
						// The sample value to calculate the probability of generating
						double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], current_metric_valid_bias[server][st[sample$var207][timeStep$var239]]);
						
						// Add the probability of this instance of the random variable to the probability
						// of all instances of the random variable.
						// 
						// Add the probability of this sample task to the sample task accumulator.
						// 
						// Accumulator for sample probabilities for a specific instance of the random variable.
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						
						// Add the probability of this sample task to the sample task accumulator.
						// 
						// Accumulator for sample probabilities for a specific instance of the random variable.
						logProbability$var244[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
						
						// Store the sample task probability
						logProbability$sample260[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
						
						// Update the variable probability
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
						
						// Update the variable probability
						logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
					}
				}
			}
			
			// Update the variable probability
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample260 = (((fixedFlag$sample260 && fixedFlag$sample71) && fixedFlag$sample90) && fixedFlag$sample206);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						double cv$sampleValue = logProbability$sample260[sample$var207][server][timeStep$var239];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var244[sample$var207][server][timeStep$var239] = cv$sampleValue;
						
						// Update the variable probability
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
						
						// Update the variable probability
						logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
					}
				}
			}
			
			// Update the variable probability
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample275 using sampled
	// values.
	private final void logProbabilityValue$sample275() {
		// Determine if we need to calculate the values for sample task 275 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample275) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						if(metric_valid_g[sample$var207][server][timeStep$var239]) {
							double var256 = current_metric_var[server][st[sample$var207][timeStep$var239]];
							
							// Variable declaration of cv$distributionAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$distributionAccumulator moved.
							// Declaration comment was:
							// An accumulator for log probabilities.
							// 
							// Store the value of the function call, so the function call is only made once.
							// 
							// The sample value to calculate the probability of generating
							// 
							// Scale the probability relative to the observed distribution space.
							// 
							// Add the probability of this distribution configuration to the accumulator.
							// 
							// An accumulator for the distributed probability space covered.
							// 
							// Variable declaration of cv$distributionAccumulator moved.
							// Declaration comment was:
							// An accumulator for log probabilities.
							// 
							// Store the value of the function call, so the function call is only made once.
							// 
							// The sample value to calculate the probability of generating
							double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - current_metric_mean[server][st[sample$var207][timeStep$var239]]) / Math.sqrt(var256))) - (Math.log(var256) * 0.5));
							
							// Add the probability of this instance of the random variable to the probability
							// of all instances of the random variable.
							// 
							// Add the probability of this sample task to the sample task accumulator.
							// 
							// Accumulator for sample probabilities for a specific instance of the random variable.
							cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
							
							// Add the probability of this sample task to the sample task accumulator.
							// 
							// Accumulator for sample probabilities for a specific instance of the random variable.
							logProbability$var257[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
							
							// Store the sample task probability
							logProbability$sample275[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample275 = ((((fixedFlag$sample275 && fixedFlag$sample71) && fixedFlag$sample90) && fixedFlag$sample150) && fixedFlag$sample178);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						if(metric_valid_g[sample$var207][server][timeStep$var239]) {
							// Variable declaration of cv$rvAccumulator moved.
							double cv$rvAccumulator = logProbability$sample275[sample$var207][server][timeStep$var239];
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var257[sample$var207][server][timeStep$var239] = cv$rvAccumulator;
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample31 using sampled
	// values.
	private final void logProbabilityValue$sample31() {
		// Determine if we need to calculate the values for sample task 31 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample31) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var27 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$initialStateDistribution = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample31 = fixedFlag$sample31;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var27 = logProbability$initialStateDistribution;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	// Calculate the probability of the samples represented by sample44 using sampled
	// values.
	private final void logProbabilityValue$sample44() {
		// Determine if we need to calculate the values for sample task 44 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample44) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var40 = 0; var40 < noStates; var40 += 1)
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var40], v));
			logProbability$var29 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var41 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample44)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample44 = fixedFlag$sample44;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var29 = logProbability$var41;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var41);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var41);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample44)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var41);
		}
	}

	// Calculate the probability of the samples represented by sample71 using sampled
	// values.
	private final void logProbabilityValue$sample71() {
		// Determine if we need to calculate the values for sample task 71 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample71) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[sample$var53][0];
				
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var64[sample$var53] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample71[sample$var53] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample71 = (fixedFlag$sample71 && fixedFlag$sample31);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample71[sample$var53];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var64[sample$var53] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample90 using sampled
	// values.
	private final void logProbabilityValue$sample90() {
		// Determine if we need to calculate the values for sample task 90 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample90) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[sample$var53][timeStep$var76];
					double[] var82 = m[st[sample$var53][(timeStep$var76 - 1)]];
					
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// An accumulator for log probabilities.
					// 
					// Store the value of the function call, so the function call is only made once.
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					// 
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// An accumulator for log probabilities.
					// 
					// Store the value of the function call, so the function call is only made once.
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var82.length))?Math.log(var82[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					logProbability$var83[sample$var53][(timeStep$var76 - 1)] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample90[sample$var53][(timeStep$var76 - 1)] = cv$distributionAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample90 = ((fixedFlag$sample90 && fixedFlag$sample44) && fixedFlag$sample71);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
					// Variable declaration of cv$rvAccumulator moved.
					double cv$rvAccumulator = logProbability$sample90[sample$var53][(timeStep$var76 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var83[sample$var53][(timeStep$var76 - 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 150 drawn from Uniform 119. Inference was performed using Metropolis-Hastings.
	private final void sample150(int var130, int var140, int threadID$cv$var140, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = current_metric_mean[var130][var140];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$var117" with its value "0.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue <= (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
			
			// Processing random variable 257.
			// 
			// Looking for a path between Sample 150 and consumer Gaussian 257.
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((metric_valid_g[sample$var207][var130][0] && (0 < length$metric[sample$var207][0]))) {
					if(fixedFlag$sample71) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var140 == st[sample$var207][0])) {
							// Processing sample task 275 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var167 = st[sample$var207][0];
							
							// Substituted "server" with its value "var130".
							if(((0 <= var167) && (var167 < noStates))) {
								// Variable declaration of cv$temp$3$var256 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var130".
								double cv$temp$3$var256 = current_metric_var[var130][st[sample$var207][0]];
								
								// Substituted "server" with its value "var130".
								// 
								// cv$temp$2$var254's comment
								// Variable declaration of cv$temp$2$var254 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][0] - cv$originalValue) / Math.sqrt(cv$temp$3$var256))) - (Math.log(cv$temp$3$var256) * 0.5));
								
								// Recorded the probability of reaching sample task 275 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
							}
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "sample$var53" with its value "sample$var207".
						// 
						// Substituted "index$sample71$6" with its value "var140".
						double cv$probabilitySample71Value7 = distribution$sample71[sample$var207][var140];
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var130".
						// 
						// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
						// the output of Sample task 150.
						// 
						// Substituted "index$sample71$6" with its value "var140".
						double var256 = current_metric_var[var130][var140];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 275 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "server" with its value "var130".
						// 
						// Substituted "cv$temp$9$var256" with its value "var256".
						// 
						// cv$temp$8$var254's comment
						// Variable declaration of cv$temp$8$var254 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Set the current value to the current state of the tree.
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample71Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][0] - cv$originalValue) / Math.sqrt(var256)))) - (Math.log(var256) * 0.5));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 275 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 275 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample71Value7), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
							// Substituted "server" with its value "var130".
							// 
							// Substituted "cv$temp$9$var256" with its value "var256".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var130".
							// 
							// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
							// the output of Sample task 150.
							// 
							// Substituted "index$sample71$6" with its value "var140".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var130".
							// 
							// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
							// the output of Sample task 150.
							// 
							// Substituted "index$sample71$6" with its value "var140".
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int timeStep$var239 = 1; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
					// Substituted "server" with its value "var130".
					if(metric_valid_g[sample$var207][var130][timeStep$var239]) {
						if(fixedFlag$sample90) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var140 == st[sample$var207][timeStep$var239])) {
								// Processing sample task 275 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var167 = st[sample$var207][timeStep$var239];
								
								// Substituted "server" with its value "var130".
								if(((0 <= var167) && (var167 < noStates))) {
									// Variable declaration of cv$temp$21$var256 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var130".
									double cv$temp$21$var256 = current_metric_var[var130][st[sample$var207][timeStep$var239]];
									
									// Substituted "server" with its value "var130".
									// 
									// cv$temp$20$var254's comment
									// Variable declaration of cv$temp$20$var254 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][timeStep$var239] - cv$originalValue) / Math.sqrt(cv$temp$21$var256))) - (Math.log(cv$temp$21$var256) * 0.5));
									
									// Recorded the probability of reaching sample task 275 with the current configuration.
									// 
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
								
								// A check to ensure rounding of floating point values can never result in a negative
								// value.
								cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
								
								// Multiply (log space add) in the probability of the sample task to the overall probability
								// for this configuration of the source random variable.
								if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
									else
										cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
								}
							}
						} else {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "sample$var53" with its value "sample$var207".
							// 
							// Substituted "index$sample90$18" with its value "var140".
							double cv$probabilitySample90Value19 = distribution$sample90[sample$var207][(timeStep$var239 - 1)][var140];
							
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var130".
							// 
							// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
							// the output of Sample task 150.
							// 
							// Substituted "index$sample90$18" with its value "var140".
							double var256 = current_metric_var[var130][var140];
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Processing sample task 275 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "server" with its value "var130".
							// 
							// Substituted "cv$temp$27$var256" with its value "var256".
							// 
							// cv$temp$26$var254's comment
							// Variable declaration of cv$temp$26$var254 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample90Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][timeStep$var239] - cv$originalValue) / Math.sqrt(var256)))) - (Math.log(var256) * 0.5));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 275 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 275 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample90Value19), 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var130".
								// 
								// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
								// the output of Sample task 150.
								// 
								// Substituted "index$sample90$18" with its value "var140".
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var130".
								// 
								// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
								// the output of Sample task 150.
								// 
								// Substituted "index$sample90$18" with its value "var140".
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		current_metric_mean[var130][var140] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$var117" with its value "0.0".
		double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue <= (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
		
		// Processing random variable 257.
		// 
		// Looking for a path between Sample 150 and consumer Gaussian 257.
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((metric_valid_g[sample$var207][var130][0] && (0 < length$metric[sample$var207][0]))) {
				if(fixedFlag$sample71) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var140 == st[sample$var207][0])) {
						// Processing sample task 275 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var167 = st[sample$var207][0];
						
						// Substituted "server" with its value "var130".
						if(((0 <= var167) && (var167 < noStates))) {
							// Variable declaration of cv$temp$3$var256 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var130".
							double cv$temp$3$var256 = current_metric_var[var130][st[sample$var207][0]];
							
							// Substituted "server" with its value "var130".
							// 
							// cv$temp$2$var254's comment
							// Variable declaration of cv$temp$2$var254 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var256))) - (Math.log(cv$temp$3$var256) * 0.5));
							
							// Recorded the probability of reaching sample task 275 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "sample$var53" with its value "sample$var207".
					// 
					// Substituted "index$sample71$6" with its value "var140".
					double cv$probabilitySample71Value7 = distribution$sample71[sample$var207][var140];
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "server" with its value "var130".
					// 
					// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
					// the output of Sample task 150.
					// 
					// Substituted "index$sample71$6" with its value "var140".
					double var256 = current_metric_var[var130][var140];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 275 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "server" with its value "var130".
					// 
					// Substituted "cv$temp$9$var256" with its value "var256".
					// 
					// cv$temp$8$var254's comment
					// Variable declaration of cv$temp$8$var254 moved.
					// 
					// Constructing a random variable input for use later.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample71Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][0] - cv$proposedValue) / Math.sqrt(var256)))) - (Math.log(var256) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 275 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 275 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample71Value7), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Substituted "server" with its value "var130".
						// 
						// Substituted "cv$temp$9$var256" with its value "var256".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var130".
						// 
						// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
						// the output of Sample task 150.
						// 
						// Substituted "index$sample71$6" with its value "var140".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var130".
						// 
						// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
						// the output of Sample task 150.
						// 
						// Substituted "index$sample71$6" with its value "var140".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
		}
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			for(int timeStep$var239 = 1; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
				// Substituted "server" with its value "var130".
				if(metric_valid_g[sample$var207][var130][timeStep$var239]) {
					if(fixedFlag$sample90) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var140 == st[sample$var207][timeStep$var239])) {
							// Processing sample task 275 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var167 = st[sample$var207][timeStep$var239];
							
							// Substituted "server" with its value "var130".
							if(((0 <= var167) && (var167 < noStates))) {
								// Variable declaration of cv$temp$21$var256 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var130".
								double cv$temp$21$var256 = current_metric_var[var130][st[sample$var207][timeStep$var239]];
								
								// Substituted "server" with its value "var130".
								// 
								// cv$temp$20$var254's comment
								// Variable declaration of cv$temp$20$var254 moved.
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][timeStep$var239] - cv$proposedValue) / Math.sqrt(cv$temp$21$var256))) - (Math.log(cv$temp$21$var256) * 0.5));
								
								// Recorded the probability of reaching sample task 275 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
							}
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "sample$var53" with its value "sample$var207".
						// 
						// Substituted "index$sample90$18" with its value "var140".
						double cv$probabilitySample90Value19 = distribution$sample90[sample$var207][(timeStep$var239 - 1)][var140];
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var130".
						// 
						// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
						// the output of Sample task 150.
						// 
						// Substituted "index$sample90$18" with its value "var140".
						double var256 = current_metric_var[var130][var140];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 275 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "server" with its value "var130".
						// 
						// Substituted "cv$temp$27$var256" with its value "var256".
						// 
						// cv$temp$26$var254's comment
						// Variable declaration of cv$temp$26$var254 moved.
						// 
						// Constructing a random variable input for use later.
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample90Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][timeStep$var239] - cv$proposedValue) / Math.sqrt(var256)))) - (Math.log(var256) * 0.5));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 275 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 275 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample90Value19), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var130".
							// 
							// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
							// the output of Sample task 150.
							// 
							// Substituted "index$sample90$18" with its value "var140".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var130".
							// 
							// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
							// the output of Sample task 150.
							// 
							// Substituted "index$sample90$18" with its value "var140".
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
		}
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			current_metric_mean[var130][var140] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 178 drawn from InverseGamma 146. Inference was performed using Metropolis-Hastings.
	private final void sample178(int var157, int var167, int threadID$cv$var167, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = current_metric_var[var157][var167];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var145" with its value "1.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			
			// Processing random variable 257.
			// 
			// Looking for a path between Sample 178 and consumer Gaussian 257.
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((metric_valid_g[sample$var207][var157][0] && (0 < length$metric[sample$var207][0]))) {
					if(fixedFlag$sample71) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var167 == st[sample$var207][0])) {
							// Processing sample task 275 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var140 = st[sample$var207][0];
							
							// Substituted "server" with its value "var157".
							if(((0 <= var140) && (var140 < noStates))) {
								// Substituted "server" with its value "var157".
								// 
								// cv$temp$2$var254's comment
								// Variable declaration of cv$temp$2$var254 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var157".
								// 
								// cv$temp$3$var256's comment
								// Variable declaration of cv$temp$3$var256 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][0] - current_metric_mean[var157][st[sample$var207][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
								
								// Recorded the probability of reaching sample task 275 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
							}
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "sample$var53" with its value "sample$var207".
						// 
						// Substituted "index$sample71$6" with its value "var167".
						double cv$probabilitySample71Value7 = distribution$sample71[sample$var207][var167];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 275 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "server" with its value "var157".
						// 
						// Substituted "cv$temp$8$var254" with its value "var254".
						// 
						// cv$temp$9$var256's comment
						// Variable declaration of cv$temp$9$var256 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Set the current value to the current state of the tree.
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample71Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][0] - current_metric_mean[var157][var167]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 275 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 275 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample71Value7), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
							// Substituted "server" with its value "var157".
							// 
							// Substituted "cv$temp$8$var254" with its value "var254".
							// 
							// The original value of the sample
							// 
							// The original value of the sample
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int timeStep$var239 = 1; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
					// Substituted "server" with its value "var157".
					if(metric_valid_g[sample$var207][var157][timeStep$var239]) {
						if(fixedFlag$sample90) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var167 == st[sample$var207][timeStep$var239])) {
								// Processing sample task 275 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var140 = st[sample$var207][timeStep$var239];
								
								// Substituted "server" with its value "var157".
								if(((0 <= var140) && (var140 < noStates))) {
									// Substituted "server" with its value "var157".
									// 
									// cv$temp$21$var256's comment
									// Variable declaration of cv$temp$21$var256 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][timeStep$var239] - current_metric_mean[var157][st[sample$var207][timeStep$var239]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
									
									// Recorded the probability of reaching sample task 275 with the current configuration.
									// 
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
								
								// A check to ensure rounding of floating point values can never result in a negative
								// value.
								cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
								
								// Multiply (log space add) in the probability of the sample task to the overall probability
								// for this configuration of the source random variable.
								if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
									else
										cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
								}
							}
						} else {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "sample$var53" with its value "sample$var207".
							// 
							// Substituted "index$sample90$18" with its value "var167".
							double cv$probabilitySample90Value19 = distribution$sample90[sample$var207][(timeStep$var239 - 1)][var167];
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Processing sample task 275 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "server" with its value "var157".
							// 
							// Substituted "cv$temp$26$var254" with its value "var254".
							// 
							// cv$temp$27$var256's comment
							// Variable declaration of cv$temp$27$var256 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample90Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][timeStep$var239] - current_metric_mean[var157][var167]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 275 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 275 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample90Value19), 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								// 
								// The original value of the sample
								// 
								// The original value of the sample
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		current_metric_var[var157][var167] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var145" with its value "1.0".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
		
		// Processing random variable 257.
		// 
		// Looking for a path between Sample 178 and consumer Gaussian 257.
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((metric_valid_g[sample$var207][var157][0] && (0 < length$metric[sample$var207][0]))) {
				if(fixedFlag$sample71) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var167 == st[sample$var207][0])) {
						// Processing sample task 275 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var140 = st[sample$var207][0];
						
						// Substituted "server" with its value "var157".
						if(((0 <= var140) && (var140 < noStates))) {
							// Substituted "server" with its value "var157".
							// 
							// cv$temp$2$var254's comment
							// Variable declaration of cv$temp$2$var254 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var157".
							// 
							// cv$temp$3$var256's comment
							// Variable declaration of cv$temp$3$var256 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][0] - current_metric_mean[var157][st[sample$var207][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
							
							// Recorded the probability of reaching sample task 275 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "sample$var53" with its value "sample$var207".
					// 
					// Substituted "index$sample71$6" with its value "var167".
					double cv$probabilitySample71Value7 = distribution$sample71[sample$var207][var167];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 275 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "server" with its value "var157".
					// 
					// Substituted "cv$temp$8$var254" with its value "var254".
					// 
					// cv$temp$9$var256's comment
					// Variable declaration of cv$temp$9$var256 moved.
					// 
					// Constructing a random variable input for use later.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample71Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][0] - current_metric_mean[var157][var167]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 275 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 275 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample71Value7), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Substituted "server" with its value "var157".
						// 
						// Substituted "cv$temp$8$var254" with its value "var254".
						// 
						// The proposed new value for the sample
						// 
						// The proposed new value for the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
		}
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			for(int timeStep$var239 = 1; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
				// Substituted "server" with its value "var157".
				if(metric_valid_g[sample$var207][var157][timeStep$var239]) {
					if(fixedFlag$sample90) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var167 == st[sample$var207][timeStep$var239])) {
							// Processing sample task 275 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var140 = st[sample$var207][timeStep$var239];
							
							// Substituted "server" with its value "var157".
							if(((0 <= var140) && (var140 < noStates))) {
								// Substituted "server" with its value "var157".
								// 
								// cv$temp$21$var256's comment
								// Variable declaration of cv$temp$21$var256 moved.
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][timeStep$var239] - current_metric_mean[var157][st[sample$var207][timeStep$var239]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
								
								// Recorded the probability of reaching sample task 275 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
							}
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "sample$var53" with its value "sample$var207".
						// 
						// Substituted "index$sample90$18" with its value "var167".
						double cv$probabilitySample90Value19 = distribution$sample90[sample$var207][(timeStep$var239 - 1)][var167];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 275 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "server" with its value "var157".
						// 
						// Substituted "cv$temp$26$var254" with its value "var254".
						// 
						// cv$temp$27$var256's comment
						// Variable declaration of cv$temp$27$var256 moved.
						// 
						// Constructing a random variable input for use later.
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample90Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][timeStep$var239] - current_metric_mean[var157][var167]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 275 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 275 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample90Value19), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
							// The proposed new value for the sample
							// 
							// The proposed new value for the sample
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
		}
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			current_metric_var[var157][var167] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 206 drawn from Beta 173. Inference was performed using a Beta to
	// Bernoulli/Binomial conjugate prior.
	private final void sample206(int var184, int var194, int threadID$cv$var194, Rng RNG$) {
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		
		// Processing random variable 244.
		// 
		// Looking for a path between Sample 206 and consumer Bernoulli 244.
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < length$metric[sample$var207][0])) {
				if(fixedFlag$sample71) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var194 == st[sample$var207][0])) {
						// Processing sample task 260 of consumer random variable null.
						// 
						// Include the value sampled by task 260 from random variable var244.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						// 
						// Substituted "server" with its value "var184".
						if(metric_valid_g[sample$var207][var184][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "sample$var53" with its value "sample$var207".
					// 
					// Substituted "index$sample71$6" with its value "var194".
					double cv$probabilitySample71Value7 = distribution$sample71[sample$var207][var194];
					
					// Processing sample task 260 of consumer random variable null.
					// 
					// Include the value sampled by task 260 from random variable var244.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + cv$probabilitySample71Value7);
					
					// If the sample value was positive increase the count
					// 
					// Substituted "server" with its value "var184".
					if(metric_valid_g[sample$var207][var184][0])
						cv$sum = (cv$sum + cv$probabilitySample71Value7);
				}
			}
		}
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			for(int timeStep$var239 = 1; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
				if(fixedFlag$sample90) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var194 == st[sample$var207][timeStep$var239])) {
						// Processing sample task 260 of consumer random variable null.
						// 
						// Include the value sampled by task 260 from random variable var244.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						// 
						// Substituted "server" with its value "var184".
						if(metric_valid_g[sample$var207][var184][timeStep$var239])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "sample$var53" with its value "sample$var207".
					// 
					// Substituted "index$sample90$18" with its value "var194".
					double cv$probabilitySample90Value19 = distribution$sample90[sample$var207][(timeStep$var239 - 1)][var194];
					
					// Processing sample task 260 of consumer random variable null.
					// 
					// Include the value sampled by task 260 from random variable var244.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + cv$probabilitySample90Value19);
					
					// If the sample value was positive increase the count
					// 
					// Substituted "server" with its value "var184".
					if(metric_valid_g[sample$var207][var184][timeStep$var239])
						cv$sum = (cv$sum + cv$probabilitySample90Value19);
				}
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		current_metric_valid_bias[var184][var194] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 260 drawn from Bernoulli 244. Inference was performed using variable
	// marginalization.
	private final void sample260(int sample$var207, int server, int timeStep$var239, int threadID$cv$timeStep$var239, Rng RNG$) {}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 31 drawn from Dirichlet 27. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample31() {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var28$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample71) {
			// Processing random variable 64.
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1)
				// Processing sample task 71 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 71 of random
				// variable var64
				// 
				// A local reference to the scratch space.
				cv$var28$countGlobal[st[sample$var53][0]] = (cv$var28$countGlobal[st[sample$var53][0]] + 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				// Processing sample task 71 of consumer random variable null.
				// 
				// Merge the distribution probabilities into the count
				// 
				// Get the length of the array
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					// A local reference to the scratch space.
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					cv$var28$countGlobal[cv$loopIndex] = (cv$var28$countGlobal[cv$loopIndex] + distribution$sample71[sample$var53][cv$loopIndex]);
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var28$countGlobal, initialStateDistribution);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 44 drawn from Dirichlet 29. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample44(int var40, int threadID$cv$var40, Rng RNG$) {
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var41$countGlobal[threadID$cv$var40];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample90) {
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < length$metric[sample$var53][0])) {
					if(fixedFlag$sample71) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var40 == st[sample$var53][0]))
							// Increment the sample counter with the value sampled by sample task 90 of random
							// variable var83
							// 
							// Substituted "timeStep$var76" with its value "1".
							cv$countLocal[st[sample$var53][1]] = (cv$countLocal[st[sample$var53][1]] + 1.0);
					} else
						// Processing sample task 90 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 90 of random
						// variable var83
						// 
						// Substituted "index$sample$4" with its value "sample$var53".
						// 
						// Substituted "index$sample71$5" with its value "var40".
						cv$countLocal[st[sample$var53][1]] = (cv$countLocal[st[sample$var53][1]] + distribution$sample71[sample$var53][var40]);
				}
			}
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				for(int timeStep$var76 = 2; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
					if((var40 == st[sample$var53][(timeStep$var76 - 1)]))
						// Processing sample task 90 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 90 of random
						// variable var83
						cv$countLocal[st[sample$var53][timeStep$var76]] = (cv$countLocal[st[sample$var53][timeStep$var76]] + 1.0);
				}
			}
		}
		
		// Processing random variable 83.
		// 
		// Looking for a path between Sample 44 and consumer Categorical 83.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < length$metric[sample$var53][0])) {
					if(fixedFlag$sample71) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var40 == st[sample$var53][0])) {
							// Merge the distribution probabilities into the count
							// 
							// Get the length of the array
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								// The probability of reaching the consumer with this set of consumer arguments
								// 
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample90[sample$var53][0][cv$loopIndex]);
						}
					} else {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Substituted "index$sample$41" with its value "sample$var53".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample71$42" with its value "var40".
						double cv$distributionProbability = distribution$sample71[sample$var53][var40];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							// Substituted "timeStep$var76" with its value "1".
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample90[sample$var53][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
					int index$timeStep$52 = (timeStep$var76 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$sample$51" with its value "sample$var53".
					if((1 <= index$timeStep$52)) {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Substituted "index$sample$51" with its value "sample$var53".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample90$53" with its value "var40".
						double cv$distributionProbability = distribution$sample90[sample$var53][(index$timeStep$52 - 1)][var40];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample90[sample$var53][(timeStep$var76 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var40]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 71 drawn from Categorical 64. Inference was performed using variable
	// marginalization.
	private final void sample71(int sample$var53, int threadID$cv$sample$var53, Rng RNG$) {
		// Variable declaration of cv$noStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$noStates's comment
		// Calculate the number of states to evaluate.
		int cv$noStates = Math.max(0, noStates);
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var65$stateProbabilityGlobal[threadID$cv$sample$var53];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$initialStateDistribution" with its value "initialStateDistribution".
			double cv$accumulatedProbabilities = ((cv$valuePos < initialStateDistribution.length)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample90 && (1 < length$metric[sample$var53][0]))) {
				// Looking for a path between Sample 71 and consumer Categorical 83.
				// Processing sample task 90 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Value of the variable at this index
				if((cv$valuePos < noStates)) {
					// Variable declaration of cv$temp$1$var82 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					double[] cv$temp$1$var82 = m[cv$valuePos];
					
					// Substituted "index$sample$3_2" with its value "sample$var53".
					cv$accumulatedConsumerProbabilities = (((0.0 <= st[sample$var53][1]) && (st[sample$var53][1] < cv$temp$1$var82.length))?Math.log(cv$temp$1$var82[st[sample$var53][1]]):Double.NEGATIVE_INFINITY);
					
					// Recorded the probability of reaching sample task 90 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				}
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < length$metric[sample$var53][0])) {
				// Looking for a path between Sample 71 and consumer Bernoulli 244.
				for(int server = 0; server < noServers; server += 1) {
					// Processing sample task 260 of consumer random variable null.
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Value of the variable at this index
					if((cv$valuePos < noStates)) {
						// Substituted "sample$var207" with its value "sample$var53".
						// 
						// cv$temp$2$var243's comment
						// Variable declaration of cv$temp$2$var243 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var53][server][0], current_metric_valid_bias[server][cv$valuePos]);
						
						// Recorded the probability of reaching sample task 260 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
				
				// Processing random variable 257.
				// 
				// Looking for a path between Sample 71 and consumer Gaussian 257.
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[][][] guard$sample71gaussian274 = guard$sample71gaussian274$global[threadID$cv$sample$var53];
				for(int server = 0; server < noServers; server += 1) {
					// Substituted "sample$var207" with its value "sample$var53".
					if(metric_valid_g[sample$var53][server][0])
						// Set the flags to false
						// 
						// Substituted "sample$var207" with its value "sample$var53".
						guard$sample71gaussian274[sample$var53][server][0] = false;
				}
				for(int server = 0; server < noServers; server += 1) {
					// Substituted "sample$var207" with its value "sample$var53".
					if(metric_valid_g[sample$var53][server][0])
						// Set the flags to false
						// 
						// Substituted "sample$var207" with its value "sample$var53".
						guard$sample71gaussian274[sample$var53][server][0] = false;
				}
				for(int server = 0; server < noServers; server += 1) {
					// Substituted "sample$var207" with its value "sample$var53".
					if((metric_valid_g[sample$var53][server][0] && !guard$sample71gaussian274[sample$var53][server][0])) {
						// The body will execute, so should not be executed again
						// 
						// Substituted "sample$var207" with its value "sample$var53".
						guard$sample71gaussian274[sample$var53][server][0] = true;
						
						// Processing sample task 275 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((cv$valuePos < noStates)) {
							// Variable declaration of cv$temp$4$var256 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$4$var256 = current_metric_var[server][cv$valuePos];
							
							// Substituted "sample$var207" with its value "sample$var53".
							// 
							// cv$temp$3$var254's comment
							// Variable declaration of cv$temp$3$var254 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$4$var256))) - (Math.log(cv$temp$4$var256) * 0.5));
							
							// Recorded the probability of reaching sample task 275 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
				for(int server = 0; server < noServers; server += 1) {
					// Substituted "sample$var207" with its value "sample$var53".
					if((metric_valid_g[sample$var53][server][0] && !guard$sample71gaussian274[sample$var53][server][0])) {
						// The body will execute, so should not be executed again
						// 
						// Substituted "sample$var207" with its value "sample$var53".
						guard$sample71gaussian274[sample$var53][server][0] = true;
						
						// Processing sample task 275 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Substituted "sample$var207" with its value "sample$var53".
						// 
						// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
						// the output of Sample task 71.
						// 
						// Value of the variable at this index
						if((cv$valuePos < noStates)) {
							// Variable declaration of cv$temp$12$var256 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
							// the output of Sample task 71.
							// 
							// Value of the variable at this index
							double cv$temp$12$var256 = current_metric_var[server][cv$valuePos];
							
							// Substituted "sample$var207" with its value "sample$var53".
							// 
							// cv$temp$11$var254's comment
							// Variable declaration of cv$temp$11$var254 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
							// the output of Sample task 71.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$12$var256))) - (Math.log(cv$temp$12$var256) * 0.5));
							
							// Recorded the probability of reaching sample task 275 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!fixedFlag$sample90 && (1 < length$metric[sample$var53][0]))) {
				// Looking for a path between Sample 71 and consumer Categorical 83.
				// Processing sample task 90 of consumer random variable null.
				// 
				// A local array to hold the accumulated distributions of the sample tasks for each
				// configuration of distributions.
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var83[threadID$cv$sample$var53];
				
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Value of the variable at this index
				if((cv$valuePos < noStates)) {
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					cv$reachedDistributionProbability = 1.0;
					
					// Add the current distribution to the distribution accumulator.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					// 
					// cv$temp$19$var82's comment
					// Variable declaration of cv$temp$19$var82 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, 1.0, m[cv$valuePos]);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$sample$67_2" with its value "sample$var53".
				double[] cv$sampleDistribution = distribution$sample90[sample$var53][0];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
					
					// Corresponding value from the sample distribution
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					
					// Calculate the overlap and store the result
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					
					// Calculate the overlap and store the result
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				
				// Scale and add the result to the combined results so far. A min is taken over the
				// reached distributions so that rounding cannot result in a value greater than one
				// as for a small probability this could give a negative value
				// 
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$stateProbabilityLocal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample71[sample$var53];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		double cv$lseMax = cv$stateProbabilityLocal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		
		// If the maximum value is -infinity return -infinity.
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		
		// Sum the values in the array.
		else {
			// Initialise the sum of the array elements
			double cv$lseSum = 0.0;
			
			// Offset values, move to normal space, and sum.
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 90 drawn from Categorical 83. Inference was performed using variable
	// marginalization.
	private final void sample90(int sample$var53, int timeStep$var76, int threadID$cv$sample$var53, Rng RNG$) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Enumerating the possible arguments for Categorical 83.
		if((1 == timeStep$var76)) {
			// Enumerating the possible arguments for Categorical 83.
			if(fixedFlag$sample71) {
				int var40 = st[sample$var53][0];
				
				// Substituted "timeStep$var76" with its value "1".
				if(((0 <= var40) && (var40 < noStates)))
					// variable marginalization
					// 
					// cv$noStates's comment
					// Calculate the number of states to evaluate.
					cv$noStates = Math.max(0, noStates);
			} else {
				// Enumerating the possible outputs of Categorical 64.
				if((0 < noStates))
					// variable marginalization
					cv$noStates = noStates;
			}
		}
		if(fixedFlag$sample90) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((2 <= timeStep$var76)) {
				int var40 = st[sample$var53][(timeStep$var76 - 1)];
				if(((0 <= var40) && (var40 < noStates)))
					// variable marginalization
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		} else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < noStates)) {
				int index$timeStep$14 = (timeStep$var76 - 1);
				
				// index$timeStep$1's comment
				// Exploring all the possible state counts for random variable 83.
				// 
				// Copy of index so that its values can be safely substituted
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var76 - 1)".
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var76 - 1)".
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var76 - 1)".
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var76 - 1)".
				if(((1 <= index$timeStep$14) && !(index$timeStep$14 == timeStep$var76)))
					// variable marginalization
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var84$stateProbabilityGlobal[threadID$cv$sample$var53];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 83 creating
			// sample task 90.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Enumerating the possible arguments for Categorical 83.
			if((1 == timeStep$var76)) {
				// Enumerating the possible arguments for Categorical 83.
				if(fixedFlag$sample71) {
					int var40 = st[sample$var53][0];
					
					// Substituted "timeStep$var76" with its value "1".
					if(((0 <= var40) && (var40 < noStates))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// Variable declaration of cv$temp$0$var82 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "timeStep$var76" with its value "1".
						double[] cv$temp$0$var82 = m[st[sample$var53][0]];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var82.length)?Math.log(cv$temp$0$var82[cv$valuePos]):Double.NEGATIVE_INFINITY);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < length$metric[sample$var53][0])) {
							// Looking for a path between Sample 90 and consumer Bernoulli 244.
							for(int server = 0; server < noServers; server += 1) {
								// Processing sample task 260 of consumer random variable null.
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Value of the variable at this index
								if((cv$valuePos < noStates)) {
									// Substituted "sample$var207" with its value "sample$var53".
									// 
									// Substituted "timeStep$var239" with its value "1".
									// 
									// cv$temp$4$var243's comment
									// Variable declaration of cv$temp$4$var243 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var53][server][1], current_metric_valid_bias[server][cv$valuePos]);
									
									// Recorded the probability of reaching sample task 260 with the current configuration.
									// 
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
								
								// A check to ensure rounding of floating point values can never result in a negative
								// value.
								cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
								
								// Multiply (log space add) in the probability of the sample task to the overall probability
								// for this configuration of the source random variable.
								if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
									else
										cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
								}
							}
							
							// Processing random variable 257.
							// 
							// Looking for a path between Sample 90 and consumer Gaussian 257.
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[][][] guard$sample90gaussian274 = guard$sample90gaussian274$global[threadID$cv$sample$var53];
							for(int server = 0; server < noServers; server += 1) {
								// Substituted "sample$var207" with its value "sample$var53".
								// 
								// Substituted "timeStep$var239" with its value "1".
								if(metric_valid_g[sample$var53][server][1])
									// Set the flags to false
									// 
									// Substituted "sample$var207" with its value "sample$var53".
									// 
									// Substituted "timeStep$var239" with its value "1".
									guard$sample90gaussian274[sample$var53][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								// Substituted "sample$var207" with its value "sample$var53".
								// 
								// Substituted "timeStep$var239" with its value "1".
								if(metric_valid_g[sample$var53][server][1])
									// Set the flags to false
									// 
									// Substituted "sample$var207" with its value "sample$var53".
									// 
									// Substituted "timeStep$var239" with its value "1".
									guard$sample90gaussian274[sample$var53][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								// Substituted "sample$var207" with its value "sample$var53".
								// 
								// Substituted "timeStep$var239" with its value "1".
								if((metric_valid_g[sample$var53][server][1] && !guard$sample90gaussian274[sample$var53][server][1])) {
									// The body will execute, so should not be executed again
									// 
									// Substituted "sample$var207" with its value "sample$var53".
									// 
									// Substituted "timeStep$var239" with its value "1".
									guard$sample90gaussian274[sample$var53][server][1] = true;
									
									// Processing sample task 275 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Substituted "sample$var207" with its value "sample$var53".
									// 
									// Value of the variable at this index
									if((cv$valuePos < noStates)) {
										// Variable declaration of cv$temp$11$var256 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double cv$temp$11$var256 = current_metric_var[server][cv$valuePos];
										
										// Substituted "sample$var207" with its value "sample$var53".
										// 
										// Substituted "timeStep$var239" with its value "1".
										// 
										// cv$temp$10$var254's comment
										// Variable declaration of cv$temp$10$var254 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$11$var256))) - (Math.log(cv$temp$11$var256) * 0.5));
										
										// Recorded the probability of reaching sample task 275 with the current configuration.
										// 
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										cv$consumerDistributionProbabilityAccumulator = 0.0;
									}
									
									// A check to ensure rounding of floating point values can never result in a negative
									// value.
									cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
									
									// Multiply (log space add) in the probability of the sample task to the overall probability
									// for this configuration of the source random variable.
									if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
										else
											cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
									}
								}
							}
							for(int server = 0; server < noServers; server += 1) {
								// Substituted "sample$var207" with its value "sample$var53".
								// 
								// Substituted "timeStep$var239" with its value "1".
								if((metric_valid_g[sample$var53][server][1] && !guard$sample90gaussian274[sample$var53][server][1])) {
									// The body will execute, so should not be executed again
									// 
									// Substituted "sample$var207" with its value "sample$var53".
									// 
									// Substituted "timeStep$var239" with its value "1".
									guard$sample90gaussian274[sample$var53][server][1] = true;
									
									// Processing sample task 275 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((cv$valuePos < noStates)) {
										// Variable declaration of cv$temp$43$var256 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
										// the output of Sample task 90.
										// 
										// Value of the variable at this index
										double cv$temp$43$var256 = current_metric_var[server][cv$valuePos];
										
										// Substituted "sample$var207" with its value "sample$var53".
										// 
										// Substituted "timeStep$var239" with its value "1".
										// 
										// cv$temp$42$var254's comment
										// Variable declaration of cv$temp$42$var254 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
										// the output of Sample task 90.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$43$var256))) - (Math.log(cv$temp$43$var256) * 0.5));
										
										// Recorded the probability of reaching sample task 275 with the current configuration.
										// 
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										cv$consumerDistributionProbabilityAccumulator = 0.0;
									}
									
									// A check to ensure rounding of floating point values can never result in a negative
									// value.
									cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
									
									// Multiply (log space add) in the probability of the sample task to the overall probability
									// for this configuration of the source random variable.
									if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
										else
											cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
									}
								}
							}
						}
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					// Enumerating the possible outputs of Categorical 64.
					for(int index$sample71$26 = 0; index$sample71$26 < noStates; index$sample71$26 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$25" with its value "sample$var53".
						double cv$probabilitySample71Value27 = distribution$sample71[sample$var53][index$sample71$26];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample71Value27);
						
						// Variable declaration of cv$temp$1$var82 moved.
						// 
						// Constructing a random variable input for use later.
						double[] cv$temp$1$var82 = m[index$sample71$26];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample71Value27) + ((cv$valuePos < cv$temp$1$var82.length)?Math.log(cv$temp$1$var82[cv$valuePos]):Double.NEGATIVE_INFINITY));
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < length$metric[sample$var53][0])) {
							// Looking for a path between Sample 90 and consumer Bernoulli 244.
							for(int server = 0; server < noServers; server += 1) {
								// Processing sample task 260 of consumer random variable null.
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Value of the variable at this index
								if((cv$valuePos < noStates)) {
									// Substituted "sample$var207" with its value "sample$var53".
									// 
									// Substituted "timeStep$var239" with its value "1".
									// 
									// cv$temp$5$var243's comment
									// Variable declaration of cv$temp$5$var243 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var53][server][1], current_metric_valid_bias[server][cv$valuePos]);
									
									// Recorded the probability of reaching sample task 260 with the current configuration.
									// 
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
								
								// A check to ensure rounding of floating point values can never result in a negative
								// value.
								cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
								
								// Multiply (log space add) in the probability of the sample task to the overall probability
								// for this configuration of the source random variable.
								if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
									else
										cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
								}
							}
							
							// Processing random variable 257.
							// 
							// Looking for a path between Sample 90 and consumer Gaussian 257.
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[][][] guard$sample90gaussian274 = guard$sample90gaussian274$global[threadID$cv$sample$var53];
							for(int server = 0; server < noServers; server += 1) {
								// Substituted "sample$var207" with its value "sample$var53".
								// 
								// Substituted "timeStep$var239" with its value "1".
								if(metric_valid_g[sample$var53][server][1])
									// Set the flags to false
									// 
									// Substituted "sample$var207" with its value "sample$var53".
									// 
									// Substituted "timeStep$var239" with its value "1".
									guard$sample90gaussian274[sample$var53][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								// Substituted "sample$var207" with its value "sample$var53".
								// 
								// Substituted "timeStep$var239" with its value "1".
								if(metric_valid_g[sample$var53][server][1])
									// Set the flags to false
									// 
									// Substituted "sample$var207" with its value "sample$var53".
									// 
									// Substituted "timeStep$var239" with its value "1".
									guard$sample90gaussian274[sample$var53][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								// Substituted "sample$var207" with its value "sample$var53".
								// 
								// Substituted "timeStep$var239" with its value "1".
								if((metric_valid_g[sample$var53][server][1] && !guard$sample90gaussian274[sample$var53][server][1])) {
									// The body will execute, so should not be executed again
									// 
									// Substituted "sample$var207" with its value "sample$var53".
									// 
									// Substituted "timeStep$var239" with its value "1".
									guard$sample90gaussian274[sample$var53][server][1] = true;
									
									// Processing sample task 275 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Substituted "sample$var207" with its value "sample$var53".
									// 
									// Value of the variable at this index
									if((cv$valuePos < noStates)) {
										// Variable declaration of cv$temp$19$var256 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double cv$temp$19$var256 = current_metric_var[server][cv$valuePos];
										
										// Substituted "sample$var207" with its value "sample$var53".
										// 
										// Substituted "timeStep$var239" with its value "1".
										// 
										// cv$temp$18$var254's comment
										// Variable declaration of cv$temp$18$var254 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$19$var256))) - (Math.log(cv$temp$19$var256) * 0.5));
										
										// Recorded the probability of reaching sample task 275 with the current configuration.
										// 
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										cv$consumerDistributionProbabilityAccumulator = 0.0;
									}
									
									// A check to ensure rounding of floating point values can never result in a negative
									// value.
									cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
									
									// Multiply (log space add) in the probability of the sample task to the overall probability
									// for this configuration of the source random variable.
									if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
										else
											cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
									}
								}
							}
							for(int server = 0; server < noServers; server += 1) {
								// Substituted "sample$var207" with its value "sample$var53".
								// 
								// Substituted "timeStep$var239" with its value "1".
								if((metric_valid_g[sample$var53][server][1] && !guard$sample90gaussian274[sample$var53][server][1])) {
									// The body will execute, so should not be executed again
									// 
									// Substituted "sample$var207" with its value "sample$var53".
									// 
									// Substituted "timeStep$var239" with its value "1".
									guard$sample90gaussian274[sample$var53][server][1] = true;
									
									// Processing sample task 275 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((cv$valuePos < noStates)) {
										// Variable declaration of cv$temp$51$var256 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
										// the output of Sample task 90.
										// 
										// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
										// the output of Sample task 90.
										// 
										// Value of the variable at this index
										double cv$temp$51$var256 = current_metric_var[server][cv$valuePos];
										
										// Substituted "sample$var207" with its value "sample$var53".
										// 
										// Substituted "timeStep$var239" with its value "1".
										// 
										// cv$temp$50$var254's comment
										// Variable declaration of cv$temp$50$var254 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
										// the output of Sample task 90.
										// 
										// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
										// the output of Sample task 90.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$51$var256))) - (Math.log(cv$temp$51$var256) * 0.5));
										
										// Recorded the probability of reaching sample task 275 with the current configuration.
										// 
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										cv$consumerDistributionProbabilityAccumulator = 0.0;
									}
									
									// A check to ensure rounding of floating point values can never result in a negative
									// value.
									cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
									
									// Multiply (log space add) in the probability of the sample task to the overall probability
									// for this configuration of the source random variable.
									if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
										else
											cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
									}
								}
							}
						}
						
						// Add the values for the source and any standard consumers for this configuration
						// of arguments to the source.
						if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
						else {
							// If the second value is -infinity.
							if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
								cv$stateProbabilityValue = cv$accumulatedProbabilities;
							else
								cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			int index$timeStep$34 = (timeStep$var76 - 1);
			
			// index$timeStep$22's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var76 - 1)".
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var76 - 1)".
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var76 - 1)".
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var76 - 1)".
			if(((1 <= index$timeStep$34) && !(index$timeStep$34 == timeStep$var76))) {
				// Enumerating the possible outputs of Categorical 83.
				for(int index$sample90$35 = 0; index$sample90$35 < noStates; index$sample90$35 += 1) {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$33" with its value "sample$var53".
					double cv$probabilitySample90Value36 = distribution$sample90[sample$var53][(index$timeStep$34 - 1)][index$sample90$35];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample90Value36);
					
					// Variable declaration of cv$temp$3$var82 moved.
					// 
					// Constructing a random variable input for use later.
					double[] cv$temp$3$var82 = m[index$sample90$35];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample90Value36) + ((cv$valuePos < cv$temp$3$var82.length)?Math.log(cv$temp$3$var82[cv$valuePos]):Double.NEGATIVE_INFINITY));
					for(int server = 0; server < noServers; server += 1) {
						// Processing sample task 260 of consumer random variable null.
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Value of the variable at this index
						if((cv$valuePos < noStates)) {
							// Substituted "sample$var207" with its value "sample$var53".
							// 
							// cv$temp$7$var243's comment
							// Variable declaration of cv$temp$7$var243 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var53][server][timeStep$var76], current_metric_valid_bias[server][cv$valuePos]);
							
							// Recorded the probability of reaching sample task 260 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
					
					// Processing random variable 257.
					// 
					// Looking for a path between Sample 90 and consumer Gaussian 257.
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					boolean[][][] guard$sample90gaussian274 = guard$sample90gaussian274$global[threadID$cv$sample$var53];
					for(int server = 0; server < noServers; server += 1) {
						// Substituted "sample$var207" with its value "sample$var53".
						if(metric_valid_g[sample$var53][server][timeStep$var76])
							// Set the flags to false
							// 
							// Substituted "sample$var207" with its value "sample$var53".
							guard$sample90gaussian274[sample$var53][server][timeStep$var76] = false;
					}
					for(int server = 0; server < noServers; server += 1) {
						// Substituted "sample$var207" with its value "sample$var53".
						if(metric_valid_g[sample$var53][server][timeStep$var76])
							// Set the flags to false
							// 
							// Substituted "sample$var207" with its value "sample$var53".
							guard$sample90gaussian274[sample$var53][server][timeStep$var76] = false;
					}
					for(int server = 0; server < noServers; server += 1) {
						// Substituted "sample$var207" with its value "sample$var53".
						if((metric_valid_g[sample$var53][server][timeStep$var76] && !guard$sample90gaussian274[sample$var53][server][timeStep$var76])) {
							// The body will execute, so should not be executed again
							// 
							// Substituted "sample$var207" with its value "sample$var53".
							guard$sample90gaussian274[sample$var53][server][timeStep$var76] = true;
							
							// Processing sample task 275 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Substituted "sample$var207" with its value "sample$var53".
							// 
							// Value of the variable at this index
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$35$var256 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$35$var256 = current_metric_var[server][cv$valuePos];
								
								// Substituted "sample$var207" with its value "sample$var53".
								// 
								// cv$temp$34$var254's comment
								// Variable declaration of cv$temp$34$var254 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][timeStep$var76] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$35$var256))) - (Math.log(cv$temp$35$var256) * 0.5));
								
								// Recorded the probability of reaching sample task 275 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
							}
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
					}
					for(int server = 0; server < noServers; server += 1) {
						// Substituted "sample$var207" with its value "sample$var53".
						if((metric_valid_g[sample$var53][server][timeStep$var76] && !guard$sample90gaussian274[sample$var53][server][timeStep$var76])) {
							// The body will execute, so should not be executed again
							// 
							// Substituted "sample$var207" with its value "sample$var53".
							guard$sample90gaussian274[sample$var53][server][timeStep$var76] = true;
							
							// Processing sample task 275 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$67$var256 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
								// the output of Sample task 90.
								// 
								// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
								// the output of Sample task 90.
								// 
								// Value of the variable at this index
								double cv$temp$67$var256 = current_metric_var[server][cv$valuePos];
								
								// Substituted "sample$var207" with its value "sample$var53".
								// 
								// cv$temp$66$var254's comment
								// Variable declaration of cv$temp$66$var254 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
								// the output of Sample task 90.
								// 
								// Enumerating the possible arguments for the variable Gaussian 257 which is consuming
								// the output of Sample task 90.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][timeStep$var76] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$67$var256))) - (Math.log(cv$temp$67$var256) * 0.5));
								
								// Recorded the probability of reaching sample task 275 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
							}
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
			}
			int index$timeStep$269_3 = (timeStep$var76 + 1);
			if((index$timeStep$269_3 < length$metric[sample$var53][0])) {
				// Processing sample task 90 of consumer random variable null.
				// A local array to hold the accumulated distributions of the sample tasks for each
				// configuration of distributions.
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var83[threadID$cv$sample$var53];
				
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Processing random variable 83.
				// 
				// Looking for a path between Sample 90 and consumer Categorical 83.
				// 
				// Value of the variable at this index
				if((cv$valuePos < noStates)) {
					// Declare and zero an accumulator for tracking the reached source probability space.
					double scopeVariable$reachedSourceProbability = 0.0;
					
					// Enumerating the possible arguments for Categorical 83.
					if((1 == timeStep$var76)) {
						// Enumerating the possible arguments for Categorical 83.
						if(fixedFlag$sample71) {
							int index$var40$280_1 = st[sample$var53][0];
							
							// Substituted "timeStep$var76" with its value "1".
							if(((0 <= index$var40$280_1) && (index$var40$280_1 < noStates)))
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 64.
							for(int index$sample71$276 = 0; index$sample71$276 < noStates; index$sample71$276 += 1)
								// Add the probability of this argument configuration.
								// 
								// cv$probabilitySample71Value277's comment
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$275" with its value "sample$var53".
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample71[sample$var53][index$sample71$276]);
						}
					}
					int index$timeStep$284 = (timeStep$var76 - 1);
					
					// index$timeStep$271's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var76 + 1)".
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var76 + 1)".
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var76 + 1)".
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var76 + 1)".
					if((((1 <= index$timeStep$284) && !(index$timeStep$284 == timeStep$var76)) && !(index$timeStep$284 == index$timeStep$269_3))) {
						// Enumerating the possible outputs of Categorical 83.
						for(int index$sample90$285 = 0; index$sample90$285 < noStates; index$sample90$285 += 1)
							// Add the probability of this argument configuration.
							// 
							// cv$probabilitySample90Value286's comment
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$283" with its value "sample$var53".
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample90[sample$var53][(index$timeStep$284 - 1)][index$sample90$285]);
					}
					
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Zero an accumulator to track the probabilities reached.
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					
					// Add the current distribution to the distribution accumulator.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// cv$temp$72$var82's comment
					// Variable declaration of cv$temp$72$var82 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Processing random variable 83.
					// 
					// Looking for a path between Sample 90 and consumer Categorical 83.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$sample$269_2" with its value "sample$var53".
				double[] cv$sampleDistribution = distribution$sample90[sample$var53][(index$timeStep$269_3 - 1)];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
					
					// Corresponding value from the sample distribution
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					
					// Calculate the overlap and store the result
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					
					// Calculate the overlap and store the result
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				
				// Scale and add the result to the combined results so far. A min is taken over the
				// reached distributions so that rounding cannot result in a value greater than one
				// as for a small probability this could give a negative value
				// 
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			
			// Save the calculated index value into the array of index value probabilities
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample90[sample$var53][(timeStep$var76 - 1)];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		double cv$lseMax = cv$stateProbabilityLocal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		
		// If the maximum value is -infinity return -infinity.
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		
		// Sum the values in the array.
		else {
			// Initialise the sum of the array elements
			double cv$lseSum = 0.0;
			
			// Offset values, move to normal space, and sum.
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var28$countGlobal
		// 
		// Allocation of cv$var28$countGlobal for single threaded execution
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		cv$var28$countGlobal = new double[Math.max(0, noStates)];
		
		// Constructor for cv$var41$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			if((0 < noStates))
				cv$max = noStates;
			
			// Allocation of cv$var41$countGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var41$countGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var41$countGlobal[cv$index] = new double[cv$max];
		}
		
		// Constructor for cv$distributionAccumulator$var83
		{
			// Allocation of cv$distributionAccumulator$var83 for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$distributionAccumulator$var83 = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				// Variable to record the maximum value of Task Get 88. Initially set to the value
				// of putTask 45.
				cv$distributionAccumulator$var83[cv$index] = new double[noStates];
		}
		
		// Constructor for cv$var65$stateProbabilityGlobal
		{
			// Allocation of cv$var65$stateProbabilityGlobal for multithreaded execution
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var65$stateProbabilityGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var65$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		
		// Constructor for guard$sample71gaussian274$global
		{
			// Calculate the largest index of server that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_server = 0;
			
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var239 = 0;
			for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
				if((0 < length$metric[0].length))
					cv$max_timeStep$var239 = Math.max(cv$max_timeStep$var239, length$metric[sample$var207][0]);
				cv$max_server = Math.max(cv$max_server, length$metric[0].length);
			}
			
			// Variable declaration of cv$max_sample$var207 moved.
			// Declaration comment was:
			// Calculate the largest index of sample that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_sample$var207 = length$metric.length;
			
			// Allocation of guard$sample71gaussian274$global for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			guard$sample71gaussian274$global = new boolean[cv$threadCount][][][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample71gaussian274$global[cv$index] = new boolean[cv$max_sample$var207][cv$max_server][cv$max_timeStep$var239];
		}
		
		// Constructor for cv$var84$stateProbabilityGlobal
		{
			// Allocation of cv$var84$stateProbabilityGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var84$stateProbabilityGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				// Variable to record the maximum value of Task Get 88. Initially set to the value
				// of putTask 45.
				cv$var84$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		
		// Constructor for guard$sample90gaussian274$global
		{
			// Calculate the largest index of server that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_server = 0;
			
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var239 = 0;
			for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
				if((0 < length$metric[0].length))
					cv$max_timeStep$var239 = Math.max(cv$max_timeStep$var239, length$metric[sample$var207][0]);
				cv$max_server = Math.max(cv$max_server, length$metric[0].length);
			}
			
			// Variable declaration of cv$max_sample$var207 moved.
			// Declaration comment was:
			// Calculate the largest index of sample that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_sample$var207 = length$metric.length;
			
			// Allocation of guard$sample90gaussian274$global for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			guard$sample90gaussian274$global = new boolean[cv$threadCount][][][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample90gaussian274$global[cv$index] = new boolean[cv$max_sample$var207][cv$max_server][cv$max_timeStep$var239];
		}
		
		// Constructor for cv$var245$stateProbabilityGlobal
		// 
		// Allocation of cv$var245$stateProbabilityGlobal for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		cv$var245$stateProbabilityGlobal = new double[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var245$stateProbabilityGlobal[cv$index] = new double[2];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		v = new double[noStates];
		
		// If initialStateDistribution has not been set already allocate space.
		if(!setFlag$initialStateDistribution)
			// Constructor for initialStateDistribution
			initialStateDistribution = new double[noStates];
		
		// If m has not been set already allocate space.
		if(!setFlag$m) {
			// Constructor for m
			m = new double[noStates][];
			for(int var40 = 0; var40 < noStates; var40 += 1)
				m[var40] = new double[noStates];
		}
		
		// If st has not been set already allocate space.
		if(!setFlag$st) {
			// Constructor for st
			st = new int[length$metric.length][];
			for(int sample$var53 = 0; sample$var53 < length$metric.length; sample$var53 += 1)
				st[sample$var53] = new int[length$metric[sample$var53][0]];
		}
		
		// If metric_g has not been set already allocate space.
		if(!setFlag$metric_g) {
			// Constructor for metric_g
			metric_g = new double[length$metric.length][][];
			for(int var101 = 0; var101 < length$metric.length; var101 += 1)
				metric_g[var101] = new double[length$metric[0].length][];
			for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					metric_g[sample$var207][server] = new double[length$metric[sample$var207][0]];
			}
		}
		
		// If metric_valid_g has not been set already allocate space.
		if(!setFlag$metric_valid_g) {
			// Constructor for metric_valid_g
			metric_valid_g = new boolean[length$metric.length][][];
			for(int var114 = 0; var114 < length$metric.length; var114 += 1)
				metric_valid_g[var114] = new boolean[length$metric[0].length][];
			for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					metric_valid_g[sample$var207][server] = new boolean[length$metric[sample$var207][0]];
			}
		}
		
		// If current_metric_mean has not been set already allocate space.
		if(!setFlag$current_metric_mean) {
			// Constructor for current_metric_mean
			current_metric_mean = new double[length$metric[0].length][];
			for(int var130 = 0; var130 < length$metric[0].length; var130 += 1)
				current_metric_mean[var130] = new double[noStates];
		}
		
		// If current_metric_var has not been set already allocate space.
		if(!setFlag$current_metric_var) {
			// Constructor for current_metric_var
			current_metric_var = new double[length$metric[0].length][];
			for(int var157 = 0; var157 < length$metric[0].length; var157 += 1)
				current_metric_var[var157] = new double[noStates];
		}
		
		// If current_metric_valid_bias has not been set already allocate space.
		if(!setFlag$current_metric_valid_bias) {
			// Constructor for current_metric_valid_bias
			current_metric_valid_bias = new double[length$metric[0].length][];
			for(int var184 = 0; var184 < length$metric[0].length; var184 += 1)
				current_metric_valid_bias[var184] = new double[noStates];
		}
		
		// Constructor for distribution$sample71
		distribution$sample71 = new double[length$metric.length][];
		for(int sample$var53 = 0; sample$var53 < length$metric.length; sample$var53 += 1)
			distribution$sample71[sample$var53] = new double[noStates];
		
		// Constructor for distribution$sample90
		distribution$sample90 = new double[length$metric.length][][];
		for(int sample$var53 = 0; sample$var53 < length$metric.length; sample$var53 += 1) {
			double[][] subarray$0 = new double[(length$metric[sample$var53][0] - 1)][];
			distribution$sample90[sample$var53] = subarray$0;
			for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
				subarray$0[(timeStep$var76 - 1)] = new double[noStates];
		}
		
		// Constructor for logProbability$var64
		logProbability$var64 = new double[length$metric.length];
		
		// Constructor for logProbability$sample71
		logProbability$sample71 = new double[length$metric.length];
		
		// Constructor for logProbability$var83
		logProbability$var83 = new double[length$metric.length][];
		for(int sample$var53 = 0; sample$var53 < length$metric.length; sample$var53 += 1)
			logProbability$var83[sample$var53] = new double[(length$metric[sample$var53][0] - 1)];
		
		// Constructor for logProbability$sample90
		logProbability$sample90 = new double[length$metric.length][];
		for(int sample$var53 = 0; sample$var53 < length$metric.length; sample$var53 += 1)
			logProbability$sample90[sample$var53] = new double[(length$metric[sample$var53][0] - 1)];
		
		// Constructor for logProbability$var244
		logProbability$var244 = new double[length$metric.length][][];
		for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$var244[sample$var207] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var207][0]];
		}
		
		// Constructor for logProbability$sample260
		logProbability$sample260 = new double[length$metric.length][][];
		for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample260[sample$var207] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var207][0]];
		}
		
		// Constructor for logProbability$var257
		logProbability$var257 = new double[length$metric.length][][];
		for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$var257[sample$var207] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var207][0]];
		}
		
		// Constructor for logProbability$sample275
		logProbability$sample275 = new double[length$metric.length][][];
		for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample275[sample$var207] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var207][0]];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample44)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var40]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var53, int forEnd$sample$var53, int threadID$sample$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample$var53 = forStart$sample$var53; sample$var53 < forEnd$sample$var53; sample$var53 += 1) {
						if(!fixedFlag$sample71)
							st[sample$var53][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample90) {
							int[] var77 = st[sample$var53];
							for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
								var77[timeStep$var76] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample$var53][(timeStep$var76 - 1)]]);
						}
					}
			}
		);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample150)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var130, int forEnd$var130, int threadID$var130, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var130 = forStart$var130; var130 < forEnd$var130; var130 += 1) {
							double[] var131 = current_metric_mean[var130];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var140, int forEnd$var140, int threadID$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var140 = forStart$var140; var140 < forEnd$var140; var140 += 1)
											var131[var140] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$2));
								}
							);
						}
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample178)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var157, int forEnd$var157, int threadID$var157, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var157 = forStart$var157; var157 < forEnd$var157; var157 += 1) {
							double[] var158 = current_metric_var[var157];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var167, int forEnd$var167, int threadID$var167, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var167 = forStart$var167; var167 < forEnd$var167; var167 += 1)
											var158[var167] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample206)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var184, int forEnd$var184, int threadID$var184, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var184 = forStart$var184; var184 < forEnd$var184; var184 += 1) {
							double[] var185 = current_metric_valid_bias[var184];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var194, int forEnd$var194, int threadID$var194, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var194 = forStart$var194; var194 < forEnd$var194; var194 += 1)
											var185[var194] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$index$sample$var207, int forEnd$index$sample$var207, int threadID$index$sample$var207, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$sample$var207 = forStart$index$sample$var207; index$sample$var207 < forEnd$index$sample$var207; index$sample$var207 += 1) {
						int sample$var207 = index$sample$var207;
						int threadID$sample$var207 = threadID$index$sample$var207;
						boolean[][] var228 = metric_valid_g[sample$var207];
						double[][] var224 = metric_g[sample$var207];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noServers, 1,
							(int forStart$index$server, int forEnd$index$server, int threadID$index$server, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$server = forStart$index$server; index$server < forEnd$index$server; index$server += 1) {
										int server = index$server;
										int threadID$server = threadID$index$server;
										boolean[] metric_valid_inner = metric_valid_g[sample$var207][server];
										double[] metric_inner = var224[server];
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, length$metric[sample$var207][0], 1,
											(int forStart$timeStep$var239, int forEnd$timeStep$var239, int threadID$timeStep$var239, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int timeStep$var239 = forStart$timeStep$var239; timeStep$var239 < forEnd$timeStep$var239; timeStep$var239 += 1) {
														if(!fixedFlag$sample260)
															metric_valid_inner[timeStep$var239] = DistributionSampling.sampleBernoulli(RNG$3, current_metric_valid_bias[server][st[sample$var207][timeStep$var239]]);
														if((var228[server][timeStep$var239] && (!fixedFlag$sample260 || !fixedFlag$sample275)))
															metric_inner[timeStep$var239] = ((Math.sqrt(current_metric_var[server][st[sample$var207][timeStep$var239]]) * DistributionSampling.sampleGaussian(RNG$3)) + current_metric_mean[server][st[sample$var207][timeStep$var239]]);
													}
											}
										);
									}
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample44)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var40]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var53, int forEnd$sample$var53, int threadID$sample$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample$var53 = forStart$sample$var53; sample$var53 < forEnd$sample$var53; sample$var53 += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample71) {
							// Create local copy of variable probabilities.
							double[] cv$distribution$sample71 = distribution$sample71[sample$var53];
							for(int index$var64 = 0; index$var64 < noStates; index$var64 += 1)
								// Save the probability of each value
								// 
								// Probability for this value
								cv$distribution$sample71[index$var64] = ((index$var64 < initialStateDistribution.length)?initialStateDistribution[index$var64]:0.0);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample90) {
							for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
								// Create local copy of variable probabilities.
								double[] cv$distribution$sample90 = distribution$sample90[sample$var53][(timeStep$var76 - 1)];
								for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
									// Zero the probability of each value
									cv$distribution$sample90[index$var83] = 0.0;
								
								// Iterate through possible values for var83's arguments.
								// 
								// Enumerating the possible arguments for Categorical 83.
								if((1 == timeStep$var76)) {
									// Iterate through possible values for var83's arguments.
									// 
									// Enumerating the possible arguments for Categorical 83.
									if(fixedFlag$sample71) {
										int var40 = st[sample$var53][0];
										
										// Substituted "timeStep$var76" with its value "1".
										if(((0 <= var40) && (var40 < noStates))) {
											// Substituted "timeStep$var76" with its value "1".
											double[] var82 = m[st[sample$var53][0]];
											for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
												// Save the probability of each value
												cv$distribution$sample90[index$var83] = (cv$distribution$sample90[index$var83] + ((index$var83 < var82.length)?var82[index$var83]:0.0));
										}
									} else {
										// Enumerating the possible outputs of Categorical 64.
										for(int index$sample71$3 = 0; index$sample71$3 < noStates; index$sample71$3 += 1) {
											// Update the probability of sampling this value from the distribution value.
											// 
											// Substituted "index$sample$2" with its value "sample$var53".
											double cv$probabilitySample71Value4 = distribution$sample71[sample$var53][index$sample71$3];
											double[] var82 = m[index$sample71$3];
											for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
												// Save the probability of each value
												cv$distribution$sample90[index$var83] = (cv$distribution$sample90[index$var83] + (cv$probabilitySample71Value4 * ((index$var83 < var82.length)?var82[index$var83]:0.0)));
										}
									}
								}
								int index$timeStep$11 = (timeStep$var76 - 1);
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								// 
								// Substituted "index$sample$10" with its value "sample$var53".
								if((1 <= index$timeStep$11)) {
									// Enumerating the possible outputs of Categorical 83.
									for(int index$sample90$12 = 0; index$sample90$12 < noStates; index$sample90$12 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "index$sample$10" with its value "sample$var53".
										double cv$probabilitySample90Value13 = distribution$sample90[sample$var53][(index$timeStep$11 - 1)][index$sample90$12];
										double[] var82 = m[index$sample90$12];
										for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
											// Save the probability of each value
											cv$distribution$sample90[index$var83] = (cv$distribution$sample90[index$var83] + (cv$probabilitySample90Value13 * ((index$var83 < var82.length)?var82[index$var83]:0.0)));
									}
								}
								
								// Sum the values in the array
								double cv$var83$sum = 0.0;
								for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
									// sum the probability of each value
									cv$var83$sum = (cv$var83$sum + cv$distribution$sample90[index$var83]);
								for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
									// Normalise the probability of each value
									cv$distribution$sample90[index$var83] = (cv$distribution$sample90[index$var83] / cv$var83$sum);
							}
						}
					}
			}
		);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample150)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var130, int forEnd$var130, int threadID$var130, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var130 = forStart$var130; var130 < forEnd$var130; var130 += 1) {
							double[] var131 = current_metric_mean[var130];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var140, int forEnd$var140, int threadID$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var140 = forStart$var140; var140 < forEnd$var140; var140 += 1)
											var131[var140] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$2));
								}
							);
						}
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample178)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var157, int forEnd$var157, int threadID$var157, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var157 = forStart$var157; var157 < forEnd$var157; var157 += 1) {
							double[] var158 = current_metric_var[var157];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var167, int forEnd$var167, int threadID$var167, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var167 = forStart$var167; var167 < forEnd$var167; var167 += 1)
											var158[var167] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample206)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var184, int forEnd$var184, int threadID$var184, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var184 = forStart$var184; var184 < forEnd$var184; var184 += 1) {
							double[] var185 = current_metric_valid_bias[var184];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var194, int forEnd$var194, int threadID$var194, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var194 = forStart$var194; var194 < forEnd$var194; var194 += 1)
											var185[var194] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample260)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$index$sample$var207, int forEnd$index$sample$var207, int threadID$index$sample$var207, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$sample$var207 = forStart$index$sample$var207; index$sample$var207 < forEnd$index$sample$var207; index$sample$var207 += 1) {
							int sample$var207 = index$sample$var207;
							int threadID$sample$var207 = threadID$index$sample$var207;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noServers, 1,
								(int forStart$index$server, int forEnd$index$server, int threadID$index$server, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$server = forStart$index$server; index$server < forEnd$index$server; index$server += 1) {
											int server = index$server;
											int threadID$server = threadID$index$server;
											boolean[] metric_valid_inner = metric_valid_g[sample$var207][server];
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, length$metric[sample$var207][0], 1,
												(int forStart$timeStep$var239, int forEnd$timeStep$var239, int threadID$timeStep$var239, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int timeStep$var239 = forStart$timeStep$var239; timeStep$var239 < forEnd$timeStep$var239; timeStep$var239 += 1)
															metric_valid_inner[timeStep$var239] = DistributionSampling.sampleBernoulli(RNG$3, current_metric_valid_bias[server][st[sample$var207][timeStep$var239]]);
												}
											);
										}
								}
							);
						}
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample44)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var40]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var53, int forEnd$sample$var53, int threadID$sample$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample$var53 = forStart$sample$var53; sample$var53 < forEnd$sample$var53; sample$var53 += 1) {
						if(!fixedFlag$sample71)
							st[sample$var53][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample90) {
							int[] var77 = st[sample$var53];
							for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
								var77[timeStep$var76] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample$var53][(timeStep$var76 - 1)]]);
						}
					}
			}
		);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample150)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var130, int forEnd$var130, int threadID$var130, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var130 = forStart$var130; var130 < forEnd$var130; var130 += 1) {
							double[] var131 = current_metric_mean[var130];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var140, int forEnd$var140, int threadID$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var140 = forStart$var140; var140 < forEnd$var140; var140 += 1)
											var131[var140] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$2));
								}
							);
						}
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample178)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var157, int forEnd$var157, int threadID$var157, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var157 = forStart$var157; var157 < forEnd$var157; var157 += 1) {
							double[] var158 = current_metric_var[var157];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var167, int forEnd$var167, int threadID$var167, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var167 = forStart$var167; var167 < forEnd$var167; var167 += 1)
											var158[var167] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample206)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var184, int forEnd$var184, int threadID$var184, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var184 = forStart$var184; var184 < forEnd$var184; var184 += 1) {
							double[] var185 = current_metric_valid_bias[var184];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var194, int forEnd$var194, int threadID$var194, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var194 = forStart$var194; var194 < forEnd$var194; var194 += 1)
											var185[var194] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample260)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$index$sample$var207, int forEnd$index$sample$var207, int threadID$index$sample$var207, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$sample$var207 = forStart$index$sample$var207; index$sample$var207 < forEnd$index$sample$var207; index$sample$var207 += 1) {
							int sample$var207 = index$sample$var207;
							int threadID$sample$var207 = threadID$index$sample$var207;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noServers, 1,
								(int forStart$index$server, int forEnd$index$server, int threadID$index$server, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$server = forStart$index$server; index$server < forEnd$index$server; index$server += 1) {
											int server = index$server;
											int threadID$server = threadID$index$server;
											boolean[] metric_valid_inner = metric_valid_g[sample$var207][server];
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, length$metric[sample$var207][0], 1,
												(int forStart$timeStep$var239, int forEnd$timeStep$var239, int threadID$timeStep$var239, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int timeStep$var239 = forStart$timeStep$var239; timeStep$var239 < forEnd$timeStep$var239; timeStep$var239 += 1)
															metric_valid_inner[timeStep$var239] = DistributionSampling.sampleBernoulli(RNG$3, current_metric_valid_bias[server][st[sample$var207][timeStep$var239]]);
												}
											);
										}
								}
							);
						}
				}
			);

	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample31)
				sample31();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample44)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
								sample44(var40, threadID$var40, RNG$1);
					}
				);

			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample$var53, int forEnd$sample$var53, int threadID$sample$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int sample$var53 = forStart$sample$var53; sample$var53 < forEnd$sample$var53; sample$var53 += 1) {
							if(!fixedFlag$sample71)
								sample71(sample$var53, threadID$sample$var53, RNG$1);
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample90) {
								for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
									sample90(sample$var53, timeStep$var76, threadID$sample$var53, RNG$1);
							}
						}
				}
			);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample150)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var130, int forEnd$index$var130, int threadID$index$var130, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$var130 = forStart$index$var130; index$var130 < forEnd$index$var130; index$var130 += 1) {
								int var130 = index$var130;
								int threadID$var130 = threadID$index$var130;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var140, int forEnd$var140, int threadID$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int var140 = forStart$var140; var140 < forEnd$var140; var140 += 1)
												sample150(var130, var140, threadID$var140, RNG$2);
									}
								);
							}
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample178)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var157, int forEnd$index$var157, int threadID$index$var157, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$var157 = forStart$index$var157; index$var157 < forEnd$index$var157; index$var157 += 1) {
								int var157 = index$var157;
								int threadID$var157 = threadID$index$var157;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var167, int forEnd$var167, int threadID$var167, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int var167 = forStart$var167; var167 < forEnd$var167; var167 += 1)
												sample178(var157, var167, threadID$var167, RNG$2);
									}
								);
							}
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample206)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var184, int forEnd$index$var184, int threadID$index$var184, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$var184 = forStart$index$var184; index$var184 < forEnd$index$var184; index$var184 += 1) {
								int var184 = index$var184;
								int threadID$var184 = threadID$index$var184;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var194, int forEnd$var194, int threadID$var194, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int var194 = forStart$var194; var194 < forEnd$var194; var194 += 1)
												sample206(var184, var194, threadID$var194, RNG$2);
									}
								);
							}
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample260)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noSamples, 1,
					(int forStart$index$sample$var207, int forEnd$index$sample$var207, int threadID$index$sample$var207, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$sample$var207 = forStart$index$sample$var207; index$sample$var207 < forEnd$index$sample$var207; index$sample$var207 += 1) {
								int sample$var207 = index$sample$var207;
								int threadID$sample$var207 = threadID$index$sample$var207;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, noServers, 1,
									(int forStart$index$server, int forEnd$index$server, int threadID$index$server, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int index$server = forStart$index$server; index$server < forEnd$index$server; index$server += 1) {
												int server = index$server;
												int threadID$server = threadID$index$server;
												
												//  Outer loop for dispatching multiple batches of iterations to execute in parallel
												parallelFor(RNG$2, 0, length$metric[sample$var207][0], 1,
													(int forStart$timeStep$var239, int forEnd$timeStep$var239, int threadID$timeStep$var239, org.sandwood.random.internal.Rng RNG$3) -> { 
														
															// Inner loop for running batches of iterations, each batch has its own random number
															// generator.
															for(int timeStep$var239 = forStart$timeStep$var239; timeStep$var239 < forEnd$timeStep$var239; timeStep$var239 += 1)
																sample260(sample$var207, server, timeStep$var239, threadID$timeStep$var239, RNG$3);
													}
												);
											}
									}
								);
							}
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample260)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noSamples, 1,
					(int forStart$index$sample$var207, int forEnd$index$sample$var207, int threadID$index$sample$var207, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$sample$var207 = forStart$index$sample$var207; index$sample$var207 < forEnd$index$sample$var207; index$sample$var207 += 1) {
								int sample$var207 = index$sample$var207;
								int threadID$sample$var207 = threadID$index$sample$var207;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, noServers, 1,
									(int forStart$index$server, int forEnd$index$server, int threadID$index$server, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int index$server = forStart$index$server; index$server < forEnd$index$server; index$server += 1) {
												int server = index$server;
												int threadID$server = threadID$index$server;
												
												//  Outer loop for dispatching multiple batches of iterations to execute in parallel
												parallelFor(RNG$2, 0, length$metric[sample$var207][0], 1,
													(int forStart$timeStep$var239, int forEnd$timeStep$var239, int threadID$timeStep$var239, org.sandwood.random.internal.Rng RNG$3) -> { 
														
															// Inner loop for running batches of iterations, each batch has its own random number
															// generator.
															for(int timeStep$var239 = forStart$timeStep$var239; timeStep$var239 < forEnd$timeStep$var239; timeStep$var239 += 1)
																sample260(sample$var207, server, timeStep$var239, threadID$timeStep$var239, RNG$3);
													}
												);
											}
									}
								);
							}
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample206)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var184, int forEnd$index$var184, int threadID$index$var184, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$var184 = forStart$index$var184; index$var184 < forEnd$index$var184; index$var184 += 1) {
								int var184 = index$var184;
								int threadID$var184 = threadID$index$var184;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var194, int forEnd$var194, int threadID$var194, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int var194 = forStart$var194; var194 < forEnd$var194; var194 += 1)
												sample206(var184, var194, threadID$var194, RNG$2);
									}
								);
							}
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample178)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var157, int forEnd$index$var157, int threadID$index$var157, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$var157 = forStart$index$var157; index$var157 < forEnd$index$var157; index$var157 += 1) {
								int var157 = index$var157;
								int threadID$var157 = threadID$index$var157;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var167, int forEnd$var167, int threadID$var167, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int var167 = forStart$var167; var167 < forEnd$var167; var167 += 1)
												sample178(var157, var167, threadID$var167, RNG$2);
									}
								);
							}
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample150)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var130, int forEnd$index$var130, int threadID$index$var130, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$var130 = forStart$index$var130; index$var130 < forEnd$index$var130; index$var130 += 1) {
								int var130 = index$var130;
								int threadID$var130 = threadID$index$var130;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var140, int forEnd$var140, int threadID$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int var140 = forStart$var140; var140 < forEnd$var140; var140 += 1)
												sample150(var130, var140, threadID$var140, RNG$2);
									}
								);
							}
					}
				);

			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample$var53, int forEnd$sample$var53, int threadID$sample$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int sample$var53 = forStart$sample$var53; sample$var53 < forEnd$sample$var53; sample$var53 += 1) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample90) {
								for(int timeStep$var76 = (length$metric[sample$var53][0] - 1); timeStep$var76 >= 1; timeStep$var76 -= 1)
									sample90(sample$var53, timeStep$var76, threadID$sample$var53, RNG$1);
							}
							if(!fixedFlag$sample71)
								sample71(sample$var53, threadID$sample$var53, RNG$1);
						}
				}
			);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample44)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
								sample44(var40, threadID$var40, RNG$1);
					}
				);

			if(!fixedFlag$sample31)
				sample31();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		noSamples = length$metric.length;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
						v[var24] = 0.1;
			}
		);
		noServers = length$metric[0].length;
	}

	// A method to initialize all the probabilities in the model to 0/Log(1) ready for
	// the current probabilities to be calculated by calculating the probability of each
	// sample task, and its effect on the rest of the model.
	private final void initializeLogProbabilityFields() {
		// Set the probabilities of the random variable, and the model as a whole to ready
		// them to be reconstructed by the probability calls for each sample. Sample probabilities
		// are only reset for samples that are not fixed at a value that has already been
		// calculated.
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var27 = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var29 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample44)
			logProbability$var41 = 0.0;
		for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1)
			logProbability$var64[sample$var53] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample71) {
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1)
				logProbability$sample71[sample$var53] = 0.0;
		}
		for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
			for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
				logProbability$var83[sample$var53][(timeStep$var76 - 1)] = 0.0;
		}
		if(!fixedProbFlag$sample90) {
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
					logProbability$sample90[sample$var53][(timeStep$var76 - 1)] = 0.0;
			}
		}
		logProbability$var119 = 0.0;
		logProbability$current_metric_mean = 0.0;
		if(!fixedProbFlag$sample150)
			logProbability$var141 = 0.0;
		logProbability$var146 = 0.0;
		logProbability$current_metric_var = 0.0;
		if(!fixedProbFlag$sample178)
			logProbability$var168 = 0.0;
		logProbability$var173 = 0.0;
		logProbability$current_metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample206)
			logProbability$var195 = 0.0;
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1)
					logProbability$var244[sample$var207][server][timeStep$var239] = 0.0;
			}
		}
		logProbability$metric_g = 0.0;
		logProbability$metric_valid_inner = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample260) {
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1)
						logProbability$sample260[sample$var207][server][timeStep$var239] = 0.0;
				}
			}
		}
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1)
					logProbability$var257[sample$var207][server][timeStep$var239] = 0.0;
			}
		}
		if(!fixedProbFlag$sample275) {
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1)
						logProbability$sample275[sample$var207][server][timeStep$var239] = 0.0;
				}
			}
		}
	}

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	@Override
	public final void logEvidenceGeneration() {
		// Generate values for all the samples in the model that were not fixed or observed.
		forwardGenerationValuesNoOutputs();
		
		// Calculate the probability for the resulting model.
		logEvidenceProbabilities();
	}

	// Construct the evidence probabilities.
	private final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample44)
			logProbabilityValue$sample44();
		if(fixedFlag$sample150)
			logProbabilityValue$sample150();
		if(fixedFlag$sample178)
			logProbabilityValue$sample178();
		if(fixedFlag$sample206)
			logProbabilityValue$sample206();
		logProbabilityValue$sample260();
		logProbabilityValue$sample275();
	}

	// Method to calculate the probabilities of all the samples in the model including
	// those generating fixed data. In the process probabilities for all the random variables
	// and for the model as a whole will be calculated. This model uses distributions
	// when possible.
	@Override
	public final void logModelProbabilitiesDist() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using distributions where
		// appropriate.
		// 
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
		logProbabilityValue$sample31();
		logProbabilityValue$sample44();
		logProbabilityDistribution$sample71();
		logProbabilityDistribution$sample90();
		logProbabilityValue$sample150();
		logProbabilityValue$sample178();
		logProbabilityValue$sample206();
		logProbabilityDistribution$sample260();
		logProbabilityDistribution$sample275();
	}

	// Method to calculate the probabilities of all the samples in the model including
	// those generating fixed data. In the process probabilities for all the random variables
	// and for the model as a whole will be calculated. This model only uses values.
	@Override
	public final void logModelProbabilitiesVal() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using distributions where
		// appropriate.
		// 
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
		logProbabilityValue$sample31();
		logProbabilityValue$sample44();
		logProbabilityValue$sample71();
		logProbabilityValue$sample90();
		logProbabilityValue$sample150();
		logProbabilityValue$sample178();
		logProbabilityValue$sample206();
		logProbabilityValue$sample260();
		logProbabilityValue$sample275();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample44)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var40]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var53, int forEnd$sample$var53, int threadID$sample$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample$var53 = forStart$sample$var53; sample$var53 < forEnd$sample$var53; sample$var53 += 1) {
						if(!fixedFlag$sample71)
							st[sample$var53][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample90) {
							int[] var77 = st[sample$var53];
							for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
								var77[timeStep$var76] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample$var53][(timeStep$var76 - 1)]]);
						}
					}
			}
		);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample150)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var130, int forEnd$var130, int threadID$var130, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var130 = forStart$var130; var130 < forEnd$var130; var130 += 1) {
							double[] var131 = current_metric_mean[var130];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var140, int forEnd$var140, int threadID$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var140 = forStart$var140; var140 < forEnd$var140; var140 += 1)
											var131[var140] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$2));
								}
							);
						}
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample178)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var157, int forEnd$var157, int threadID$var157, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var157 = forStart$var157; var157 < forEnd$var157; var157 += 1) {
							double[] var158 = current_metric_var[var157];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var167, int forEnd$var167, int threadID$var167, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var167 = forStart$var167; var167 < forEnd$var167; var167 += 1)
											var158[var167] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample206)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var184, int forEnd$var184, int threadID$var184, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var184 = forStart$var184; var184 < forEnd$var184; var184 += 1) {
							double[] var185 = current_metric_valid_bias[var184];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var194, int forEnd$var194, int threadID$var194, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var194 = forStart$var194; var194 < forEnd$var194; var194 += 1)
											var185[var194] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		{
			// Deep copy between arrays
			int cv$length1 = metric_valid_g.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				boolean[][] cv$source2 = metric_valid[cv$index1];
				boolean[][] cv$target2 = metric_valid_g[cv$index1];
				int cv$length2 = cv$target2.length;
				for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
					boolean[] cv$source3 = cv$source2[cv$index2];
					boolean[] cv$target3 = cv$target2[cv$index2];
					int cv$length3 = cv$target3.length;
					for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
						cv$target3[cv$index3] = cv$source3[cv$index3];
				}
			}
		}
		int cv$length1 = metric_g.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			double[][] cv$source2 = metric[cv$index1];
			double[][] cv$target2 = metric_g[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
				double[] cv$source3 = cv$source2[cv$index2];
				double[] cv$target3 = cv$target2[cv$index2];
				int cv$length3 = cv$target3.length;
				for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
					cv$target3[cv$index3] = cv$source3[cv$index3];
			}
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model HMMMetrics4(\n"
		     + "               double[][][] metric,\n"
		     + "               boolean[][][] metric_valid, \n"
		     + "               int max_metric,\n"
		     + "               int noStates) {\n"
		     + "    \n"
		     + "    int noSamples = metric.length;\n"
		     + "\n"
		     + "    // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "    double[] v = new double[noStates] <~ 0.1;\n"
		     + "    double[] initialStateDistribution = dirichlet(v).sample();\n"
		     + "    double[][] m = dirichlet(v).sample(noStates);\n"
		     + "    \n"
		     + "    //Calculate all the state transitions\n"
		     + "    int[][] st = new int[noSamples][];\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        int streamLength = metric[sample][0].length;\n"
		     + "        \n"
		     + "        // Allocate space for the state.\n"
		     + "        st[sample] = new int[streamLength];\n"
		     + "        \n"
		     + "        // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n"
		     + "        \n"
		     + "        // Calculate the remaining weightings\n"
		     + "        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n"
		     + "            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n"
		     + "    }\n"
		     + "    \n"
		     + "    // Calculate the number of servers\n"
		     + "    int noServers = metric[0].length;    \n"
		     + "    \n"
		     + "    // Allocate space for each generated metric.    \n"
		     + "    double[][][] metric_g = new double[noSamples][noServers][];\n"
		     + "    boolean[][][] metric_valid_g = new boolean[noSamples][noServers][];\n"
		     + "\n"
		     + "    // Calculate metric parameters\n"
		     + "    double[][] current_metric_mean = uniform(0.0, (double) max_metric).sample(noServers, noStates);\n"
		     + "    double[][] current_metric_var = inverseGamma(1.0, 1.0).sample(noServers, noStates);\n"
		     + "    double[][] current_metric_valid_bias = beta(1.0, 1.0).sample(noServers, noStates);\n"
		     + "    \n"
		     + "    // Compute the values of each metric\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        int streamLength = metric[sample][0].length;\n"
		     + "        for(int server = 0; server < noServers; server++) {\n"
		     + "            //Allocate space for the time series\n"
		     + "            double[] metric_inner = new double[streamLength];\n"
		     + "            metric_g[sample][server] = metric_inner;\n"
		     + "            \n"
		     + "            boolean[] metric_valid_inner = new boolean[streamLength];\n"
		     + "            metric_valid_g[sample][server] = metric_valid_inner;\n"
		     + "            \n"
		     + "            //Generate values.\n"
		     + "            for(int timeStep = 0; timeStep < streamLength; timeStep++){\n"
		     + "                int currentState = st[sample][timeStep];\n"
		     + "                \n"
		     + "                metric_valid_inner[timeStep] = bernoulli(current_metric_valid_bias[server][currentState]).sample();\n"
		     + "                if(metric_valid_inner[timeStep])\n"
		     + "                    metric_inner[timeStep] = gaussian(current_metric_mean[server][currentState], current_metric_var[server][currentState]).sample();\n"
		     + "            }\n"
		     + "        }\n"
		     + "    }\n"
		     + "\n"
		     + "    //Tie the values to the measured values.\n"
		     + "    metric_valid_g.observe(metric_valid);\n"
		     + "    metric_g.observe(metric);\n"
		     + "}";
	}
}