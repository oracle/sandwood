package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics4$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMMetrics4$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] current_metric_mean;
	private double[][] current_metric_valid_bias;
	private double[][] current_metric_var;
	private double[] cv$distributionAccumulator$var56;
	private double[] cv$var21$countGlobal;
	private double[] cv$var27$countGlobal;
	private double[] cv$var44$stateProbabilityGlobal;
	private double[] cv$var57$stateProbabilityGlobal;
	private double[][] distribution$sample48;
	private double[][][] distribution$sample61;
	private boolean fixedFlag$sample106 = false;
	private boolean fixedFlag$sample120 = false;
	private boolean fixedFlag$sample152 = false;
	private boolean fixedFlag$sample164 = false;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedFlag$sample30 = false;
	private boolean fixedFlag$sample48 = false;
	private boolean fixedFlag$sample61 = false;
	private boolean fixedFlag$sample92 = false;
	private boolean fixedProbFlag$sample106 = false;
	private boolean fixedProbFlag$sample120 = false;
	private boolean fixedProbFlag$sample152 = false;
	private boolean fixedProbFlag$sample164 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample30 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample61 = false;
	private boolean fixedProbFlag$sample92 = false;
	private boolean[][][] guard$sample48gaussian163$global;
	private boolean[][][] guard$sample61gaussian163$global;
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
	private double[][][] logProbability$sample152;
	private double[][][] logProbability$sample164;
	private double[] logProbability$sample48;
	private double[][] logProbability$sample61;
	private double logProbability$st;
	private double logProbability$var101;
	private double logProbability$var106;
	private double logProbability$var115;
	private double logProbability$var131;
	private double[][][] logProbability$var145;
	private double[][][] logProbability$var155;
	private double logProbability$var20;
	private double logProbability$var22;
	private double logProbability$var27;
	private double[] logProbability$var43;
	private double[][] logProbability$var56;
	private double logProbability$var78;
	private double logProbability$var87;
	private double logProbability$var92;
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

	public HMMMetrics4$SingleThreadCPU(ExecutionTarget target) {
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
		// Set current_metric_mean with flag to mark that it has been set so another array
		// doesn't need to be constructed
		current_metric_mean = cv$value;
		setFlag$current_metric_mean = true;
	}

	// Getter for current_metric_valid_bias.
	@Override
	public final double[][] get$current_metric_valid_bias() {
		return current_metric_valid_bias;
	}

	// Setter for current_metric_valid_bias.
	@Override
	public final void set$current_metric_valid_bias(double[][] cv$value) {
		// Set current_metric_valid_bias with flag to mark that it has been set so another
		// array doesn't need to be constructed
		current_metric_valid_bias = cv$value;
		setFlag$current_metric_valid_bias = true;
	}

	// Getter for current_metric_var.
	@Override
	public final double[][] get$current_metric_var() {
		return current_metric_var;
	}

	// Setter for current_metric_var.
	@Override
	public final void set$current_metric_var(double[][] cv$value) {
		// Set current_metric_var with flag to mark that it has been set so another array
		// doesn't need to be constructed
		current_metric_var = cv$value;
		setFlag$current_metric_var = true;
	}

	// Getter for fixedFlag$sample106.
	@Override
	public final boolean get$fixedFlag$sample106() {
		return fixedFlag$sample106;
	}

	// Setter for fixedFlag$sample106.
	@Override
	public final void set$fixedFlag$sample106(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample106 including if probabilities
		// need to be updated.
		fixedFlag$sample106 = cv$value;
		
		// Should the probability of sample 106 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample106" with its value "cv$value".
		fixedProbFlag$sample106 = (cv$value && fixedProbFlag$sample106);
		
		// Should the probability of sample 164 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample106" with its value "cv$value".
		fixedProbFlag$sample164 = (cv$value && fixedProbFlag$sample164);
	}

	// Getter for fixedFlag$sample120.
	@Override
	public final boolean get$fixedFlag$sample120() {
		return fixedFlag$sample120;
	}

	// Setter for fixedFlag$sample120.
	@Override
	public final void set$fixedFlag$sample120(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample120 including if probabilities
		// need to be updated.
		fixedFlag$sample120 = cv$value;
		
		// Should the probability of sample 120 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample120" with its value "cv$value".
		fixedProbFlag$sample120 = (cv$value && fixedProbFlag$sample120);
		
		// Should the probability of sample 152 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample120" with its value "cv$value".
		fixedProbFlag$sample152 = (cv$value && fixedProbFlag$sample152);
	}

	// Getter for fixedFlag$sample152.
	@Override
	public final boolean get$fixedFlag$sample152() {
		return fixedFlag$sample152;
	}

	// Setter for fixedFlag$sample152.
	@Override
	public final void set$fixedFlag$sample152(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample152 including if probabilities
		// need to be updated.
		fixedFlag$sample152 = cv$value;
		
		// Should the probability of sample 152 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample152" with its value "cv$value".
		fixedProbFlag$sample152 = (cv$value && fixedProbFlag$sample152);
	}

	// Getter for fixedFlag$sample164.
	@Override
	public final boolean get$fixedFlag$sample164() {
		return fixedFlag$sample164;
	}

	// Setter for fixedFlag$sample164.
	@Override
	public final void set$fixedFlag$sample164(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample164 including if probabilities
		// need to be updated.
		fixedFlag$sample164 = cv$value;
		
		// Should the probability of sample 164 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample164" with its value "cv$value".
		fixedProbFlag$sample164 = (cv$value && fixedProbFlag$sample164);
	}

	// Getter for fixedFlag$sample24.
	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	// Setter for fixedFlag$sample24.
	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample24 including if probabilities
		// need to be updated.
		fixedFlag$sample24 = cv$value;
		
		// Should the probability of sample 24 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample24" with its value "cv$value".
		fixedProbFlag$sample24 = (cv$value && fixedProbFlag$sample24);
		
		// Should the probability of sample 48 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample24" with its value "cv$value".
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
	}

	// Getter for fixedFlag$sample30.
	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	// Setter for fixedFlag$sample30.
	@Override
	public final void set$fixedFlag$sample30(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample30 including if probabilities
		// need to be updated.
		fixedFlag$sample30 = cv$value;
		
		// Should the probability of sample 30 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample30" with its value "cv$value".
		fixedProbFlag$sample30 = (cv$value && fixedProbFlag$sample30);
		
		// Should the probability of sample 61 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample30" with its value "cv$value".
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
	}

	// Getter for fixedFlag$sample48.
	@Override
	public final boolean get$fixedFlag$sample48() {
		return fixedFlag$sample48;
	}

	// Setter for fixedFlag$sample48.
	@Override
	public final void set$fixedFlag$sample48(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample48 including if probabilities
		// need to be updated.
		fixedFlag$sample48 = cv$value;
		
		// Should the probability of sample 48 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample48" with its value "cv$value".
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
		
		// Should the probability of sample 61 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample48" with its value "cv$value".
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
		
		// Should the probability of sample 152 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample48" with its value "cv$value".
		fixedProbFlag$sample152 = (cv$value && fixedProbFlag$sample152);
		
		// Should the probability of sample 164 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample48" with its value "cv$value".
		fixedProbFlag$sample164 = (cv$value && fixedProbFlag$sample164);
	}

	// Getter for fixedFlag$sample61.
	@Override
	public final boolean get$fixedFlag$sample61() {
		return fixedFlag$sample61;
	}

	// Setter for fixedFlag$sample61.
	@Override
	public final void set$fixedFlag$sample61(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample61 including if probabilities
		// need to be updated.
		fixedFlag$sample61 = cv$value;
		
		// Should the probability of sample 61 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample61" with its value "cv$value".
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
		
		// Should the probability of sample 152 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample61" with its value "cv$value".
		fixedProbFlag$sample152 = (cv$value && fixedProbFlag$sample152);
		
		// Should the probability of sample 164 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample61" with its value "cv$value".
		fixedProbFlag$sample164 = (cv$value && fixedProbFlag$sample164);
	}

	// Getter for fixedFlag$sample92.
	@Override
	public final boolean get$fixedFlag$sample92() {
		return fixedFlag$sample92;
	}

	// Setter for fixedFlag$sample92.
	@Override
	public final void set$fixedFlag$sample92(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample92 including if probabilities
		// need to be updated.
		fixedFlag$sample92 = cv$value;
		
		// Should the probability of sample 92 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample92" with its value "cv$value".
		fixedProbFlag$sample92 = (cv$value && fixedProbFlag$sample92);
		
		// Should the probability of sample 164 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample92" with its value "cv$value".
		fixedProbFlag$sample164 = (cv$value && fixedProbFlag$sample164);
	}

	// Getter for initialStateDistribution.
	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	// Setter for initialStateDistribution.
	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		// Set initialStateDistribution with flag to mark that it has been set so another
		// array doesn't need to be constructed
		initialStateDistribution = cv$value;
		setFlag$initialStateDistribution = true;
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
		// Set m with flag to mark that it has been set so another array doesn't need to be
		// constructed
		m = cv$value;
		setFlag$m = true;
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
		// Set metric_g with flag to mark that it has been set so another array doesn't need
		// to be constructed
		metric_g = cv$value;
		setFlag$metric_g = true;
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
		// Set st with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st = cv$value;
		setFlag$st = true;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Calculate the probability of the samples represented by sample152 using probability
	// distributions.
	private final void logProbabilityDistribution$sample152() {
		// Determine if we need to calculate the values for sample task 152 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample152) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 152 including any distribution
						// values.
						// 
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = metric_valid_g[sample$var120][server][timeStep$var140];
						
						// Enumerating the possible arguments for Bernoulli 145.
						if((0 == timeStep$var140)) {
							// Enumerating the possible arguments for Bernoulli 145.
							if(fixedFlag$sample48) {
								int var114 = st[sample$var120][0];
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if(((0 <= var114) && (var114 < noStates))) {
									// Store the value of the function call, so the function call is only made once.
									// 
									// Substituted "timeStep$var140" with its value "0".
									cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][st[sample$var120][0]]);
									
									// Add the probability of this distribution configuration to the accumulator.
									// 
									// An accumulator for the distributed probability space covered.
									cv$probabilityReached = 1.0;
								}
							} else {
								// Enumerating the possible outputs of Categorical 43.
								for(int index$sample48$4 = 0; index$sample48$4 < noStates; index$sample48$4 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "sample$var32" with its value "sample$var120".
									double cv$probabilitySample48Value5 = distribution$sample48[sample$var120][index$sample48$4];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample48Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][index$sample48$4]));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample48Value5);
								}
							}
						}
						
						// Enumerating the possible arguments for Bernoulli 145.
						if((1 <= timeStep$var140)) {
							// Enumerating the possible arguments for Bernoulli 145.
							if(fixedFlag$sample61) {
								int var114 = st[sample$var120][timeStep$var140];
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if(((0 <= var114) && (var114 < noStates))) {
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][st[sample$var120][timeStep$var140]]);
									
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
								// Enumerating the possible outputs of Categorical 56.
								for(int index$sample61$13 = 0; index$sample61$13 < noStates; index$sample61$13 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "sample$var32" with its value "sample$var120".
									double cv$probabilitySample61Value14 = distribution$sample61[sample$var120][(timeStep$var140 - 1)][index$sample61$13];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample61Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][index$sample61$13]));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample61Value14);
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
						logProbability$var145[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
						
						// Store the sample task probability
						logProbability$sample152[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
						
						// Update the variable probability
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
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
			fixedProbFlag$sample152 = (((fixedFlag$sample152 && fixedFlag$sample48) && fixedFlag$sample61) && fixedFlag$sample120);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						double cv$sampleValue = logProbability$sample152[sample$var120][server][timeStep$var140];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var145[sample$var120][server][timeStep$var140] = cv$sampleValue;
						
						// Update the variable probability
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
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

	// Calculate the probability of the samples represented by sample164 using probability
	// distributions.
	private final void logProbabilityDistribution$sample164() {
		// Determine if we need to calculate the values for sample task 164 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample164) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						if(metric_valid_g[sample$var120][server][timeStep$var140]) {
							// An accumulator for log probabilities.
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							
							// An accumulator for the distributed probability space covered.
							double cv$probabilityReached = 0.0;
							
							// Look for paths between the variable and the sample task 164 including any distribution
							// values.
							// 
							// The sample value to calculate the probability of generating
							double cv$sampleValue = metric_g[sample$var120][server][timeStep$var140];
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(((0 == timeStep$var140) && metric_valid_g[sample$var120][server][0])) {
								// Enumerating the possible arguments for Gaussian 155.
								// 
								// Enumerating the possible arguments for Gaussian 155.
								if(fixedFlag$sample48) {
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((0 <= st[sample$var120][0])) {
										int var86 = st[sample$var120][0];
										
										// Constraints moved from conditionals in inner loops/scopes/etc.
										if(((0 <= var86) && (var86 < noStates))) {
											// Store the value of the function call, so the function call is only made once.
											// 
											// Substituted "timeStep$var140" with its value "0".
											cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(cv$sampleValue, current_metric_mean[server][st[sample$var120][0]], current_metric_var[server][st[sample$var120][0]]);
											
											// Add the probability of this distribution configuration to the accumulator.
											// 
											// An accumulator for the distributed probability space covered.
											cv$probabilityReached = 1.0;
										}
									}
								} else {
									// Enumerating the possible outputs of Categorical 43.
									for(int index$sample48$4 = 0; index$sample48$4 < noStates; index$sample48$4 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "sample$var32" with its value "sample$var120".
										double cv$probabilitySample48Value5 = distribution$sample48[sample$var120][index$sample48$4];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(cv$probabilitySample48Value5) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, current_metric_mean[server][index$sample48$4], current_metric_var[server][index$sample48$4]));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample48Value5);
									}
								}
							}
							
							// Enumerating the possible arguments for Gaussian 155.
							if((1 <= timeStep$var140)) {
								// Enumerating the possible arguments for Gaussian 155.
								if(fixedFlag$sample61) {
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((0 <= st[sample$var120][timeStep$var140])) {
										int var86 = st[sample$var120][timeStep$var140];
										
										// Constraints moved from conditionals in inner loops/scopes/etc.
										if(((0 <= var86) && (var86 < noStates))) {
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = DistributionSampling.logProbabilityGaussian(cv$sampleValue, current_metric_mean[server][st[sample$var120][timeStep$var140]], current_metric_var[server][st[sample$var120][timeStep$var140]]);
											
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
									// Enumerating the possible outputs of Categorical 56.
									for(int index$sample61$49 = 0; index$sample61$49 < noStates; index$sample61$49 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "sample$var32" with its value "sample$var120".
										double cv$probabilitySample61Value50 = distribution$sample61[sample$var120][(timeStep$var140 - 1)][index$sample61$49];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(cv$probabilitySample61Value50) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, current_metric_mean[server][index$sample61$49], current_metric_var[server][index$sample61$49]));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample61Value50);
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
							logProbability$var155[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
							
							// Store the sample task probability
							logProbability$sample164[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
							
							// Update the variable probability
							logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$var131 = (logProbability$var131 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample164 = ((((fixedFlag$sample164 && fixedFlag$sample48) && fixedFlag$sample61) && fixedFlag$sample92) && fixedFlag$sample106);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						if(metric_valid_g[sample$var120][server][timeStep$var140]) {
							double cv$sampleValue = logProbability$sample164[sample$var120][server][timeStep$var140];
							cv$accumulator = (cv$accumulator + cv$sampleValue);
							logProbability$var155[sample$var120][server][timeStep$var140] = cv$sampleValue;
							
							// Update the variable probability
							logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$var131 = (logProbability$var131 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample48 using probability
	// distributions.
	private final void logProbabilityDistribution$sample48() {
		// Determine if we need to calculate the values for sample task 48 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample48) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample48) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
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
					double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(st[sample$var32][0], initialStateDistribution);
					
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
					logProbability$var43[sample$var32] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample48[sample$var32] = cv$distributionAccumulator;
				}
				
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample48" with its value "true".
				fixedProbFlag$sample48 = fixedFlag$sample24;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample48[sample$var32];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var43[sample$var32] = cv$rvAccumulator;
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample48)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample61 using probability
	// distributions.
	private final void logProbabilityDistribution$sample61() {
		// Determine if we need to calculate the values for sample task 61 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample61) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample61) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[sample$var32][timeStep$var49];
						
						// Enumerating the possible arguments for Categorical 56.
						if((1 == timeStep$var49)) {
							// Enumerating the possible arguments for Categorical 56.
							if(fixedFlag$sample48) {
								int var26 = st[sample$var32][0];
								
								// Substituted "timeStep$var49" with its value "1".
								if(((0 <= var26) && (var26 < noStates))) {
									// Store the value of the function call, so the function call is only made once.
									// 
									// Substituted "timeStep$var49" with its value "1".
									cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(cv$sampleValue, m[st[sample$var32][0]]);
									
									// Add the probability of this distribution configuration to the accumulator.
									// 
									// An accumulator for the distributed probability space covered.
									cv$probabilityReached = 1.0;
								}
							} else {
								// Enumerating the possible outputs of Categorical 43.
								for(int index$sample48$6 = 0; index$sample48$6 < noStates; index$sample48$6 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$5" with its value "sample$var32".
									double cv$probabilitySample48Value7 = distribution$sample48[sample$var32][index$sample48$6];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, m[index$sample48$6]));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample48Value7);
								}
							}
						}
						
						// Substituted "index$sample$13_1" with its value "sample$var32".
						// 
						// Substituted "index$timeStep$13_2" with its value "(timeStep$var49 - 1)".
						if((2 <= timeStep$var49)) {
							int var26 = st[sample$var32][(timeStep$var49 - 1)];
							if(((0 <= var26) && (var26 < noStates))) {
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = DistributionSampling.logProbabilityCategorical(cv$sampleValue, m[st[sample$var32][(timeStep$var49 - 1)]]);
								
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
						logProbability$var56[sample$var32][(timeStep$var49 - 1)] = cv$distributionAccumulator;
						
						// Store the sample task probability
						logProbability$sample61[sample$var32][(timeStep$var49 - 1)] = cv$distributionAccumulator;
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
				// Substituted "fixedFlag$sample61" with its value "true".
				fixedProbFlag$sample61 = (fixedFlag$sample30 && fixedFlag$sample48);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					// Variable declaration of cv$rvAccumulator moved.
					double cv$rvAccumulator = logProbability$sample61[sample$var32][(timeStep$var49 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var56[sample$var32][(timeStep$var49 - 1)] = cv$rvAccumulator;
				}
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample61)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample106 using sampled
	// values.
	private final void logProbabilityValue$sample106() {
		// Determine if we need to calculate the values for sample task 106 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample106) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				for(int var100 = 0; var100 < noStates; var100 += 1)
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
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(current_metric_var[var96][var100], 1.0, 1.0));
			}
			logProbability$var92 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var101 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample106)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample106 = fixedFlag$sample106;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var92 = logProbability$var101;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$current_metric_var = (logProbability$current_metric_var + logProbability$var101);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var101);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample106)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var101);
		}
	}

	// Calculate the probability of the samples represented by sample120 using sampled
	// values.
	private final void logProbabilityValue$sample120() {
		// Determine if we need to calculate the values for sample task 120 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample120) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				for(int var114 = 0; var114 < noStates; var114 += 1)
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
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(current_metric_valid_bias[var110][var114], 1.0, 1.0));
			}
			logProbability$var106 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var115 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample120)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample120 = fixedFlag$sample120;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var106 = logProbability$var115;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + logProbability$var115);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var115);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample120)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var115);
		}
	}

	// Calculate the probability of the samples represented by sample152 using sampled
	// values.
	private final void logProbabilityValue$sample152() {
		// Determine if we need to calculate the values for sample task 152 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample152) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
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
						double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], current_metric_valid_bias[server][st[sample$var120][timeStep$var140]]);
						
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
						logProbability$var145[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
						
						// Store the sample task probability
						logProbability$sample152[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
						
						// Update the variable probability
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
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
			fixedProbFlag$sample152 = (((fixedFlag$sample152 && fixedFlag$sample48) && fixedFlag$sample61) && fixedFlag$sample120);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						double cv$sampleValue = logProbability$sample152[sample$var120][server][timeStep$var140];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var145[sample$var120][server][timeStep$var140] = cv$sampleValue;
						
						// Update the variable probability
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
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

	// Calculate the probability of the samples represented by sample164 using sampled
	// values.
	private final void logProbabilityValue$sample164() {
		// Determine if we need to calculate the values for sample task 164 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample164) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						if(metric_valid_g[sample$var120][server][timeStep$var140]) {
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
							double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], current_metric_mean[server][st[sample$var120][timeStep$var140]], current_metric_var[server][st[sample$var120][timeStep$var140]]);
							
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
							logProbability$var155[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
							
							// Store the sample task probability
							logProbability$sample164[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
							
							// Update the variable probability
							logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$var131 = (logProbability$var131 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample164 = ((((fixedFlag$sample164 && fixedFlag$sample48) && fixedFlag$sample61) && fixedFlag$sample92) && fixedFlag$sample106);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						if(metric_valid_g[sample$var120][server][timeStep$var140]) {
							double cv$sampleValue = logProbability$sample164[sample$var120][server][timeStep$var140];
							cv$accumulator = (cv$accumulator + cv$sampleValue);
							logProbability$var155[sample$var120][server][timeStep$var140] = cv$sampleValue;
							
							// Update the variable probability
							logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$var131 = (logProbability$var131 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample24 using sampled
	// values.
	private final void logProbabilityValue$sample24() {
		// Determine if we need to calculate the values for sample task 24 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample24) {
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
			logProbability$var20 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample24)
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
			fixedProbFlag$sample24 = fixedFlag$sample24;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var20 = logProbability$initialStateDistribution;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample24)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	// Calculate the probability of the samples represented by sample30 using sampled
	// values.
	private final void logProbabilityValue$sample30() {
		// Determine if we need to calculate the values for sample task 30 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample30) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var26 = 0; var26 < noStates; var26 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var26], v));
			logProbability$var22 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var27 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample30)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample30 = fixedFlag$sample30;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var22 = logProbability$var27;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var27);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var27);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample30)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var27);
		}
	}

	// Calculate the probability of the samples represented by sample48 using sampled
	// values.
	private final void logProbabilityValue$sample48() {
		// Determine if we need to calculate the values for sample task 48 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample48) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(st[sample$var32][0], initialStateDistribution);
				
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
				logProbability$var43[sample$var32] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample48[sample$var32] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample48 = (fixedFlag$sample48 && fixedFlag$sample24);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample48[sample$var32];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var43[sample$var32] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample61 using sampled
	// values.
	private final void logProbabilityValue$sample61() {
		// Determine if we need to calculate the values for sample task 61 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample61) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
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
					double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(st[sample$var32][timeStep$var49], m[st[sample$var32][(timeStep$var49 - 1)]]);
					
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
					logProbability$var56[sample$var32][(timeStep$var49 - 1)] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample61[sample$var32][(timeStep$var49 - 1)] = cv$distributionAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample61 = ((fixedFlag$sample61 && fixedFlag$sample30) && fixedFlag$sample48);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					// Variable declaration of cv$rvAccumulator moved.
					double cv$rvAccumulator = logProbability$sample61[sample$var32][(timeStep$var49 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var56[sample$var32][(timeStep$var49 - 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample92 using sampled
	// values.
	private final void logProbabilityValue$sample92() {
		// Determine if we need to calculate the values for sample task 92 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample92) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var82 = 0; var82 < noServers; var82 += 1) {
				for(int var86 = 0; var86 < noStates; var86 += 1)
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
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityUniform(current_metric_mean[var82][var86], 0.0, max_metric));
			}
			logProbability$var78 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var87 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample92)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample92 = fixedFlag$sample92;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var78 = logProbability$var87;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$current_metric_mean = (logProbability$current_metric_mean + logProbability$var87);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var87);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample92)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var87);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 106 drawn from InverseGamma 92. Inference was performed using Metropolis-Hastings.
	private final void sample106(int var96, int var100) {
		// The original value of the sample
		double cv$originalValue = current_metric_var[var96][var100];
		
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
		double cv$proposedValue = DistributionSampling.sampleGaussian(RNG$, cv$originalValue, cv$var);
		
		// Unrolled loop
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var91" with its value "1.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			
			// Processing random variable 155.
			// 
			// Looking for a path between Sample 106 and consumer Gaussian 155.
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((metric_valid_g[sample$var120][var96][0] && (0 < length$metric[sample$var120][0]))) {
					if(fixedFlag$sample48) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var100 == st[sample$var120][0])) {
							// Processing sample task 164 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var86 = st[sample$var120][0];
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(((0 <= var86) && (var86 < noStates))) {
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$3$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], current_metric_mean[var96][st[sample$var120][0]], cv$originalValue);
								
								// Variable declaration of cv$temp$30$var152 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var96".
								double cv$temp$30$var152 = current_metric_mean[var96][st[sample$var120][0]];
								
								// Record the probability of sample task 164 generating output with current configuration.
								// 
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$31$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$30$var152, cv$originalValue) < cv$accumulatedConsumerProbabilities))
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$31$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$30$var152, cv$originalValue) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "server" with its value "var96".
										// 
										// Substituted "cv$temp$31$var154" with its value "var154".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$30$var152, cv$originalValue);
									else
										// Substituted "server" with its value "var96".
										// 
										// Substituted "cv$temp$31$var154" with its value "var154".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$30$var152, cv$originalValue))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$30$var152, cv$originalValue));
								}
								
								// Variable declaration of cv$temp$58$var152 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var96".
								double cv$temp$58$var152 = current_metric_mean[var96][st[sample$var120][0]];
								
								// Record the probability of sample task 164 generating output with current configuration.
								// 
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$59$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$58$var152, cv$originalValue) < cv$accumulatedConsumerProbabilities))
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$59$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$58$var152, cv$originalValue) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "server" with its value "var96".
										// 
										// Substituted "cv$temp$59$var154" with its value "var154".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$58$var152, cv$originalValue);
									else
										// Substituted "server" with its value "var96".
										// 
										// Substituted "cv$temp$59$var154" with its value "var154".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$58$var152, cv$originalValue))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$58$var152, cv$originalValue));
								}
								
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = -2.0;
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
						// Substituted "sample$var32" with its value "sample$var120".
						// 
						// Substituted "index$sample48$6" with its value "var100".
						double cv$probabilitySample48Value7 = distribution$sample48[sample$var120][var100];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// This value is not used before it is set again, so removing the value declaration.
						// 
						// Processing sample task 164 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "server" with its value "var96".
						// 
						// Substituted "cv$temp$9$var154" with its value "var154".
						// 
						// Constructing a random variable input for use later.
						// 
						// Set the current value to the current state of the tree.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var96".
						// 
						// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
						// the output of Sample task 106.
						// 
						// Substituted "index$sample48$6" with its value "var100".
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], current_metric_mean[var96][var100], cv$originalValue));
						{
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var96".
							// 
							// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
							// the output of Sample task 106.
							// 
							// Substituted "index$sample48$6" with its value "var100".
							double var152 = current_metric_mean[var96][var100];
							
							// Record the probability of sample task 164 generating output with current configuration.
							// 
							// Substituted "server" with its value "var96".
							// 
							// Substituted "cv$temp$37$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$originalValue)) < cv$accumulatedConsumerProbabilities))
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$37$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$originalValue)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								// 
								// The original value of the sample
								// 
								// The original value of the sample
								// 
								// The original value of the sample
								// 
								// The original value of the sample
								// 
								// The original value of the sample
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$37$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$originalValue));
								else
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$37$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$originalValue)))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$originalValue));
							}
						}
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var96".
						// 
						// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
						// the output of Sample task 106.
						// 
						// Substituted "index$sample48$6" with its value "var100".
						double var152 = current_metric_mean[var96][var100];
						
						// Record the probability of sample task 164 generating output with current configuration.
						// 
						// Substituted "server" with its value "var96".
						// 
						// Substituted "cv$temp$65$var154" with its value "var154".
						// 
						// Constructing a random variable input for use later.
						// 
						// Set the current value to the current state of the tree.
						if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$originalValue)) < cv$accumulatedConsumerProbabilities))
							// Substituted "server" with its value "var96".
							// 
							// Substituted "cv$temp$65$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$originalValue)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$65$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$originalValue));
							else
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$65$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$originalValue)))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$originalValue));
						}
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// This value is not used before it is set again, so removing the value declaration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample48Value7 + cv$probabilitySample48Value7) + cv$probabilitySample48Value7)), 0.0);
						
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
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int timeStep$var140 = 1; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
					// Substituted "server" with its value "var96".
					if(metric_valid_g[sample$var120][var96][timeStep$var140]) {
						if(fixedFlag$sample61) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var100 == st[sample$var120][timeStep$var140])) {
								// Processing sample task 164 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var86 = st[sample$var120][timeStep$var140];
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if(((0 <= var86) && (var86 < noStates))) {
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$21$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									// 
									// cv$temp$20$var152's comment
									// Variable declaration of cv$temp$20$var152 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var96".
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], current_metric_mean[var96][st[sample$var120][timeStep$var140]], cv$originalValue);
									
									// Variable declaration of cv$temp$48$var152 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var96".
									double cv$temp$48$var152 = current_metric_mean[var96][st[sample$var120][timeStep$var140]];
									
									// Record the probability of sample task 164 generating output with current configuration.
									// 
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$49$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$48$var152, cv$originalValue) < cv$accumulatedConsumerProbabilities))
										// Substituted "server" with its value "var96".
										// 
										// Substituted "cv$temp$49$var154" with its value "var154".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$48$var152, cv$originalValue) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										// 
										// The original value of the sample
										// 
										// The original value of the sample
										// 
										// The original value of the sample
										// 
										// The original value of the sample
										// 
										// The original value of the sample
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "server" with its value "var96".
											// 
											// Substituted "cv$temp$49$var154" with its value "var154".
											// 
											// Constructing a random variable input for use later.
											// 
											// Set the current value to the current state of the tree.
											cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$48$var152, cv$originalValue);
										else
											// Substituted "server" with its value "var96".
											// 
											// Substituted "cv$temp$49$var154" with its value "var154".
											// 
											// Constructing a random variable input for use later.
											// 
											// Set the current value to the current state of the tree.
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$48$var152, cv$originalValue))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$48$var152, cv$originalValue));
									}
									
									// Variable declaration of cv$temp$76$var152 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var96".
									double cv$temp$76$var152 = current_metric_mean[var96][st[sample$var120][timeStep$var140]];
									
									// Record the probability of sample task 164 generating output with current configuration.
									// 
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$77$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$76$var152, cv$originalValue) < cv$accumulatedConsumerProbabilities))
										// Substituted "server" with its value "var96".
										// 
										// Substituted "cv$temp$77$var154" with its value "var154".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$76$var152, cv$originalValue) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "server" with its value "var96".
											// 
											// Substituted "cv$temp$77$var154" with its value "var154".
											// 
											// Constructing a random variable input for use later.
											// 
											// Set the current value to the current state of the tree.
											cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$76$var152, cv$originalValue);
										else
											// Substituted "server" with its value "var96".
											// 
											// Substituted "cv$temp$77$var154" with its value "var154".
											// 
											// Constructing a random variable input for use later.
											// 
											// Set the current value to the current state of the tree.
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$76$var152, cv$originalValue))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$76$var152, cv$originalValue));
									}
									
									// Recorded the probability of reaching sample task 164 with the current configuration.
									// 
									// Recorded the probability of reaching sample task 164 with the current configuration.
									// 
									// Recorded the probability of reaching sample task 164 with the current configuration.
									// 
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									cv$consumerDistributionProbabilityAccumulator = -2.0;
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
							// Substituted "sample$var32" with its value "sample$var120".
							// 
							// Substituted "index$sample61$18" with its value "var100".
							double cv$probabilitySample61Value19 = distribution$sample61[sample$var120][(timeStep$var140 - 1)][var100];
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 164 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "server" with its value "var96".
							// 
							// Substituted "cv$temp$27$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var96".
							// 
							// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
							// the output of Sample task 106.
							// 
							// Substituted "index$sample61$18" with its value "var100".
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], current_metric_mean[var96][var100], cv$originalValue));
							{
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var96".
								// 
								// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
								// the output of Sample task 106.
								// 
								// Substituted "index$sample61$18" with its value "var100".
								double var152 = current_metric_mean[var96][var100];
								
								// Record the probability of sample task 164 generating output with current configuration.
								// 
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$55$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$originalValue)) < cv$accumulatedConsumerProbabilities))
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$55$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$originalValue)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "server" with its value "var96".
										// 
										// Substituted "cv$temp$55$var154" with its value "var154".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$originalValue));
									else
										// Substituted "server" with its value "var96".
										// 
										// Substituted "cv$temp$55$var154" with its value "var154".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$originalValue)))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$originalValue));
								}
							}
							
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var96".
							// 
							// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
							// the output of Sample task 106.
							// 
							// Substituted "index$sample61$18" with its value "var100".
							double var152 = current_metric_mean[var96][var100];
							
							// Record the probability of sample task 164 generating output with current configuration.
							// 
							// Substituted "server" with its value "var96".
							// 
							// Substituted "cv$temp$83$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$originalValue)) < cv$accumulatedConsumerProbabilities))
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$83$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$originalValue)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$83$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$originalValue));
								else
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$83$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$originalValue)))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$originalValue));
							}
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample61Value19 + cv$probabilitySample61Value19) + cv$probabilitySample61Value19)), 0.0);
							
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
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		current_metric_var[var96][var100] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var91" with its value "1.0".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
		
		// Processing random variable 155.
		// 
		// Looking for a path between Sample 106 and consumer Gaussian 155.
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((metric_valid_g[sample$var120][var96][0] && (0 < length$metric[sample$var120][0]))) {
				if(fixedFlag$sample48) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var100 == st[sample$var120][0])) {
						// Processing sample task 164 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var86 = st[sample$var120][0];
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(((0 <= var86) && (var86 < noStates))) {
							// Substituted "server" with its value "var96".
							// 
							// Substituted "cv$temp$3$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], current_metric_mean[var96][st[sample$var120][0]], cv$proposedValue);
							
							// Variable declaration of cv$temp$30$var152 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var96".
							double cv$temp$30$var152 = current_metric_mean[var96][st[sample$var120][0]];
							
							// Record the probability of sample task 164 generating output with current configuration.
							// 
							// Substituted "server" with its value "var96".
							// 
							// Substituted "cv$temp$31$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$30$var152, cv$proposedValue) < cv$accumulatedConsumerProbabilities))
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$31$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$30$var152, cv$proposedValue) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$31$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$30$var152, cv$proposedValue);
								else
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$31$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$30$var152, cv$proposedValue))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$30$var152, cv$proposedValue));
							}
							
							// Variable declaration of cv$temp$58$var152 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var96".
							double cv$temp$58$var152 = current_metric_mean[var96][st[sample$var120][0]];
							
							// Record the probability of sample task 164 generating output with current configuration.
							// 
							// Substituted "server" with its value "var96".
							// 
							// Substituted "cv$temp$59$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$58$var152, cv$proposedValue) < cv$accumulatedConsumerProbabilities))
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$59$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$58$var152, cv$proposedValue) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$59$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$58$var152, cv$proposedValue);
								else
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$59$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$58$var152, cv$proposedValue))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], cv$temp$58$var152, cv$proposedValue));
							}
							
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = -2.0;
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
					// Substituted "sample$var32" with its value "sample$var120".
					// 
					// Substituted "index$sample48$6" with its value "var100".
					double cv$probabilitySample48Value7 = distribution$sample48[sample$var120][var100];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Processing sample task 164 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "server" with its value "var96".
					// 
					// Substituted "cv$temp$9$var154" with its value "var154".
					// 
					// Constructing a random variable input for use later.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "server" with its value "var96".
					// 
					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
					// the output of Sample task 106.
					// 
					// Substituted "index$sample48$6" with its value "var100".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], current_metric_mean[var96][var100], cv$proposedValue));
					{
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var96".
						// 
						// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
						// the output of Sample task 106.
						// 
						// Substituted "index$sample48$6" with its value "var100".
						double var152 = current_metric_mean[var96][var100];
						
						// Record the probability of sample task 164 generating output with current configuration.
						// 
						// Substituted "server" with its value "var96".
						// 
						// Substituted "cv$temp$37$var154" with its value "var154".
						// 
						// Constructing a random variable input for use later.
						if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$proposedValue)) < cv$accumulatedConsumerProbabilities))
							// Substituted "server" with its value "var96".
							// 
							// Substituted "cv$temp$37$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$proposedValue)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							// 
							// The proposed new value for the sample
							// 
							// The proposed new value for the sample
							// 
							// The proposed new value for the sample
							// 
							// The proposed new value for the sample
							// 
							// The proposed new value for the sample
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$37$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$proposedValue));
							else
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$37$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$proposedValue)))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$proposedValue));
						}
					}
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "server" with its value "var96".
					// 
					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
					// the output of Sample task 106.
					// 
					// Substituted "index$sample48$6" with its value "var100".
					double var152 = current_metric_mean[var96][var100];
					
					// Record the probability of sample task 164 generating output with current configuration.
					// 
					// Substituted "server" with its value "var96".
					// 
					// Substituted "cv$temp$65$var154" with its value "var154".
					// 
					// Constructing a random variable input for use later.
					if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$proposedValue)) < cv$accumulatedConsumerProbabilities))
						// Substituted "server" with its value "var96".
						// 
						// Substituted "cv$temp$65$var154" with its value "var154".
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$proposedValue)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							// Substituted "server" with its value "var96".
							// 
							// Substituted "cv$temp$65$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$proposedValue));
						else
							// Substituted "server" with its value "var96".
							// 
							// Substituted "cv$temp$65$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$proposedValue)))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][0], var152, cv$proposedValue));
					}
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 164 with the current configuration.
					// 
					// Recorded the probability of reaching sample task 164 with the current configuration.
					// 
					// Recorded the probability of reaching sample task 164 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 164 with the current configuration.
					// 
					// Recorded the probability of reaching sample task 164 with the current configuration.
					// 
					// Recorded the probability of reaching sample task 164 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample48Value7 + cv$probabilitySample48Value7) + cv$probabilitySample48Value7)), 0.0);
					
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
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int timeStep$var140 = 1; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
				// Substituted "server" with its value "var96".
				if(metric_valid_g[sample$var120][var96][timeStep$var140]) {
					if(fixedFlag$sample61) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var100 == st[sample$var120][timeStep$var140])) {
							// Processing sample task 164 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var86 = st[sample$var120][timeStep$var140];
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(((0 <= var86) && (var86 < noStates))) {
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$21$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								// 
								// cv$temp$20$var152's comment
								// Variable declaration of cv$temp$20$var152 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var96".
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], current_metric_mean[var96][st[sample$var120][timeStep$var140]], cv$proposedValue);
								
								// Variable declaration of cv$temp$48$var152 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var96".
								double cv$temp$48$var152 = current_metric_mean[var96][st[sample$var120][timeStep$var140]];
								
								// Record the probability of sample task 164 generating output with current configuration.
								// 
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$49$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$48$var152, cv$proposedValue) < cv$accumulatedConsumerProbabilities))
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$49$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$48$var152, cv$proposedValue) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									// 
									// The proposed new value for the sample
									// 
									// The proposed new value for the sample
									// 
									// The proposed new value for the sample
									// 
									// The proposed new value for the sample
									// 
									// The proposed new value for the sample
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "server" with its value "var96".
										// 
										// Substituted "cv$temp$49$var154" with its value "var154".
										// 
										// Constructing a random variable input for use later.
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$48$var152, cv$proposedValue);
									else
										// Substituted "server" with its value "var96".
										// 
										// Substituted "cv$temp$49$var154" with its value "var154".
										// 
										// Constructing a random variable input for use later.
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$48$var152, cv$proposedValue))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$48$var152, cv$proposedValue));
								}
								
								// Variable declaration of cv$temp$76$var152 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var96".
								double cv$temp$76$var152 = current_metric_mean[var96][st[sample$var120][timeStep$var140]];
								
								// Record the probability of sample task 164 generating output with current configuration.
								// 
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$77$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$76$var152, cv$proposedValue) < cv$accumulatedConsumerProbabilities))
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$77$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$76$var152, cv$proposedValue) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "server" with its value "var96".
										// 
										// Substituted "cv$temp$77$var154" with its value "var154".
										// 
										// Constructing a random variable input for use later.
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$76$var152, cv$proposedValue);
									else
										// Substituted "server" with its value "var96".
										// 
										// Substituted "cv$temp$77$var154" with its value "var154".
										// 
										// Constructing a random variable input for use later.
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$76$var152, cv$proposedValue))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], cv$temp$76$var152, cv$proposedValue));
								}
								
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = -2.0;
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
						// Substituted "sample$var32" with its value "sample$var120".
						// 
						// Substituted "index$sample61$18" with its value "var100".
						double cv$probabilitySample61Value19 = distribution$sample61[sample$var120][(timeStep$var140 - 1)][var100];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// This value is not used before it is set again, so removing the value declaration.
						// 
						// Processing sample task 164 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "server" with its value "var96".
						// 
						// Substituted "cv$temp$27$var154" with its value "var154".
						// 
						// Constructing a random variable input for use later.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var96".
						// 
						// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
						// the output of Sample task 106.
						// 
						// Substituted "index$sample61$18" with its value "var100".
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], current_metric_mean[var96][var100], cv$proposedValue));
						{
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var96".
							// 
							// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
							// the output of Sample task 106.
							// 
							// Substituted "index$sample61$18" with its value "var100".
							double var152 = current_metric_mean[var96][var100];
							
							// Record the probability of sample task 164 generating output with current configuration.
							// 
							// Substituted "server" with its value "var96".
							// 
							// Substituted "cv$temp$55$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$proposedValue)) < cv$accumulatedConsumerProbabilities))
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$55$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$proposedValue)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$55$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$proposedValue));
								else
									// Substituted "server" with its value "var96".
									// 
									// Substituted "cv$temp$55$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$proposedValue)))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$proposedValue));
							}
						}
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var96".
						// 
						// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
						// the output of Sample task 106.
						// 
						// Substituted "index$sample61$18" with its value "var100".
						double var152 = current_metric_mean[var96][var100];
						
						// Record the probability of sample task 164 generating output with current configuration.
						// 
						// Substituted "server" with its value "var96".
						// 
						// Substituted "cv$temp$83$var154" with its value "var154".
						// 
						// Constructing a random variable input for use later.
						if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$proposedValue)) < cv$accumulatedConsumerProbabilities))
							// Substituted "server" with its value "var96".
							// 
							// Substituted "cv$temp$83$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$proposedValue)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$83$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$proposedValue));
							else
								// Substituted "server" with its value "var96".
								// 
								// Substituted "cv$temp$83$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$proposedValue)))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var96][timeStep$var140], var152, cv$proposedValue));
						}
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// This value is not used before it is set again, so removing the value declaration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample61Value19 + cv$probabilitySample61Value19) + cv$probabilitySample61Value19)), 0.0);
						
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$, 0.0, 1.0))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			current_metric_var[var96][var100] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 120 drawn from Beta 106. Inference was performed using a Beta to
	// Bernoulli/Binomial conjugate prior.
	private final void sample120(int var110, int var114) {
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		
		// Processing random variable 145.
		// 
		// Looking for a path between Sample 120 and consumer Bernoulli 145.
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < length$metric[sample$var120][0])) {
				if(fixedFlag$sample48) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var114 == st[sample$var120][0])) {
						// Processing sample task 152 of consumer random variable null.
						// 
						// Include the value sampled by task 152 from random variable var145.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						// 
						// Substituted "server" with its value "var110".
						if(metric_valid_g[sample$var120][var110][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "sample$var32" with its value "sample$var120".
					// 
					// Substituted "index$sample48$6" with its value "var114".
					double cv$probabilitySample48Value7 = distribution$sample48[sample$var120][var114];
					
					// Processing sample task 152 of consumer random variable null.
					// 
					// Include the value sampled by task 152 from random variable var145.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + cv$probabilitySample48Value7);
					
					// If the sample value was positive increase the count
					// 
					// Substituted "server" with its value "var110".
					if(metric_valid_g[sample$var120][var110][0])
						cv$sum = (cv$sum + cv$probabilitySample48Value7);
				}
			}
		}
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int timeStep$var140 = 1; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
				if(fixedFlag$sample61) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var114 == st[sample$var120][timeStep$var140])) {
						// Processing sample task 152 of consumer random variable null.
						// 
						// Include the value sampled by task 152 from random variable var145.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						// 
						// Substituted "server" with its value "var110".
						if(metric_valid_g[sample$var120][var110][timeStep$var140])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "sample$var32" with its value "sample$var120".
					// 
					// Substituted "index$sample61$18" with its value "var114".
					double cv$probabilitySample61Value19 = distribution$sample61[sample$var120][(timeStep$var140 - 1)][var114];
					
					// Processing sample task 152 of consumer random variable null.
					// 
					// Include the value sampled by task 152 from random variable var145.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + cv$probabilitySample61Value19);
					
					// If the sample value was positive increase the count
					// 
					// Substituted "server" with its value "var110".
					if(metric_valid_g[sample$var120][var110][timeStep$var140])
						cv$sum = (cv$sum + cv$probabilitySample61Value19);
				}
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		current_metric_valid_bias[var110][var114] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 24 drawn from Dirichlet 20. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample24() {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var21$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample48) {
			// Processing random variable 43.
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1)
				// Processing sample task 48 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 48 of random
				// variable var43
				// 
				// A local reference to the scratch space.
				cv$var21$countGlobal[st[sample$var32][0]] = (cv$var21$countGlobal[st[sample$var32][0]] + 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				// Processing sample task 48 of consumer random variable null.
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
					cv$var21$countGlobal[cv$loopIndex] = (cv$var21$countGlobal[cv$loopIndex] + distribution$sample48[sample$var32][cv$loopIndex]);
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var21$countGlobal, initialStateDistribution);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 30 drawn from Dirichlet 22. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample30(int var26) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var27$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample61) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < length$metric[sample$var32][0])) {
					if(fixedFlag$sample48) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var26 == st[sample$var32][0]))
							// Increment the sample counter with the value sampled by sample task 61 of random
							// variable var56
							// 
							// A local reference to the scratch space.
							cv$var27$countGlobal[st[sample$var32][1]] = (cv$var27$countGlobal[st[sample$var32][1]] + 1.0);
					} else
						// Processing sample task 61 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 61 of random
						// variable var56
						// 
						// A local reference to the scratch space.
						// 
						// Substituted "index$sample48$5" with its value "var26".
						cv$var27$countGlobal[st[sample$var32][1]] = (cv$var27$countGlobal[st[sample$var32][1]] + distribution$sample48[sample$var32][var26]);
				}
			}
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 2; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					if((var26 == st[sample$var32][(timeStep$var49 - 1)]))
						// Processing sample task 61 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 61 of random
						// variable var56
						// 
						// A local reference to the scratch space.
						cv$var27$countGlobal[st[sample$var32][timeStep$var49]] = (cv$var27$countGlobal[st[sample$var32][timeStep$var49]] + 1.0);
				}
			}
		}
		
		// Processing random variable 56.
		// 
		// Looking for a path between Sample 30 and consumer Categorical 56.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < length$metric[sample$var32][0])) {
					if(fixedFlag$sample48) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var26 == st[sample$var32][0])) {
							// Merge the distribution probabilities into the count
							// 
							// Get the length of the array
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								// A local reference to the scratch space.
								// 
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								cv$var27$countGlobal[cv$loopIndex] = (cv$var27$countGlobal[cv$loopIndex] + distribution$sample61[sample$var32][0][cv$loopIndex]);
						}
					} else {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Substituted "index$sample$41" with its value "sample$var32".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample48$42" with its value "var26".
						double cv$distributionProbability = distribution$sample48[sample$var32][var26];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							// A local reference to the scratch space.
							cv$var27$countGlobal[cv$loopIndex] = (cv$var27$countGlobal[cv$loopIndex] + (distribution$sample61[sample$var32][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					int index$timeStep$52 = (timeStep$var49 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$sample$51" with its value "sample$var32".
					if((1 <= index$timeStep$52)) {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Substituted "index$sample$51" with its value "sample$var32".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample61$53" with its value "var26".
						double cv$distributionProbability = distribution$sample61[sample$var32][(index$timeStep$52 - 1)][var26];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							// A local reference to the scratch space.
							cv$var27$countGlobal[cv$loopIndex] = (cv$var27$countGlobal[cv$loopIndex] + (distribution$sample61[sample$var32][(timeStep$var49 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var27$countGlobal, m[var26]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 48 drawn from Categorical 43. Inference was performed using variable
	// marginalization.
	private final void sample48(int sample$var32) {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$initialStateDistribution" with its value "initialStateDistribution".
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityCategorical(cv$valuePos, initialStateDistribution);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample61 && (1 < length$metric[sample$var32][0])))
				// Looking for a path between Sample 48 and consumer Categorical 56.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 61 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 61 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "index$sample$2_2" with its value "sample$var32".
				// 
				// cv$temp$1$var55's comment
				// Variable declaration of cv$temp$1$var55 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityCategorical(st[sample$var32][1], m[cv$valuePos]) + cv$accumulatedProbabilities);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < length$metric[sample$var32][0])) {
				// Processing random variable 145.
				// 
				// Looking for a path between Sample 48 and consumer Bernoulli 145.
				for(int server = 0; server < noServers; server += 1)
					// Processing sample task 152 of consumer random variable null.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 152 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "sample$var120" with its value "sample$var32".
					// 
					// cv$temp$2$var144's comment
					// Variable declaration of cv$temp$2$var144 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var32][server][0], current_metric_valid_bias[server][cv$valuePos]) + cv$accumulatedProbabilities);
				for(int server = 0; server < noServers; server += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample48gaussian163$global[sample$var32][server][0] = false;
				for(int server = 0; server < noServers; server += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample48gaussian163$global[sample$var32][server][0]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample48gaussian163$global[sample$var32][server][0] = true;
						
						// Processing sample task 164 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(metric_valid_g[sample$var32][server][0]) {
							// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
							// the output of Sample task 48.
							// Substituted "sample$var120" with its value "sample$var32".
							// 
							// cv$temp$4$var154's comment
							// Variable declaration of cv$temp$4$var154 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], current_metric_mean[server][cv$valuePos], current_metric_var[server][cv$valuePos]);
							
							// Variable declaration of cv$temp$19$var152 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$19$var152 = current_metric_mean[server][cv$valuePos];
							
							// Variable declaration of cv$temp$20$var154 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$20$var154 = current_metric_var[server][cv$valuePos];
							
							// Record the probability of sample task 164 generating output with current configuration.
							// 
							// Substituted "sample$var120" with its value "sample$var32".
							if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$19$var152, cv$temp$20$var154) < cv$accumulatedConsumerProbabilities))
								// Substituted "sample$var120" with its value "sample$var32".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$19$var152, cv$temp$20$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								// 
								// Substituted "sample$var120" with its value "sample$var32".
								// 
								// cv$temp$4$var154's comment
								// Variable declaration of cv$temp$4$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								// 
								// Substituted "sample$var120" with its value "sample$var32".
								// 
								// cv$temp$4$var154's comment
								// Variable declaration of cv$temp$4$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								// 
								// Substituted "sample$var120" with its value "sample$var32".
								// 
								// cv$temp$4$var154's comment
								// Variable declaration of cv$temp$4$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								// 
								// Substituted "sample$var120" with its value "sample$var32".
								// 
								// cv$temp$4$var154's comment
								// Variable declaration of cv$temp$4$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "sample$var120" with its value "sample$var32".
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$19$var152, cv$temp$20$var154);
								else
									// Substituted "sample$var120" with its value "sample$var32".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$19$var152, cv$temp$20$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$19$var152, cv$temp$20$var154));
							}
							
							// Variable declaration of cv$temp$35$var152 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$35$var152 = current_metric_mean[server][cv$valuePos];
							
							// Variable declaration of cv$temp$36$var154 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$36$var154 = current_metric_var[server][cv$valuePos];
							
							// Record the probability of sample task 164 generating output with current configuration.
							// 
							// Substituted "sample$var120" with its value "sample$var32".
							if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$35$var152, cv$temp$36$var154) < cv$accumulatedConsumerProbabilities))
								// Substituted "sample$var120" with its value "sample$var32".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$35$var152, cv$temp$36$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "sample$var120" with its value "sample$var32".
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$35$var152, cv$temp$36$var154);
								else
									// Substituted "sample$var120" with its value "sample$var32".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$35$var152, cv$temp$36$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$35$var152, cv$temp$36$var154));
							}
							
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = -2.0;
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
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample48gaussian163$global[sample$var32][server][0]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample48gaussian163$global[sample$var32][server][0] = true;
						
						// Processing sample task 164 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(metric_valid_g[sample$var32][server][0]) {
							// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
							// the output of Sample task 48.
							// Substituted "sample$var120" with its value "sample$var32".
							// 
							// cv$temp$12$var154's comment
							// Variable declaration of cv$temp$12$var154 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], current_metric_mean[server][cv$valuePos], current_metric_var[server][cv$valuePos]);
							
							// Variable declaration of cv$temp$27$var152 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$27$var152 = current_metric_mean[server][cv$valuePos];
							
							// Variable declaration of cv$temp$28$var154 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$28$var154 = current_metric_var[server][cv$valuePos];
							
							// Record the probability of sample task 164 generating output with current configuration.
							// 
							// Substituted "sample$var120" with its value "sample$var32".
							if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$27$var152, cv$temp$28$var154) < cv$accumulatedConsumerProbabilities))
								// Substituted "sample$var120" with its value "sample$var32".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$27$var152, cv$temp$28$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								// 
								// Substituted "sample$var120" with its value "sample$var32".
								// 
								// cv$temp$12$var154's comment
								// Variable declaration of cv$temp$12$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								// 
								// Substituted "sample$var120" with its value "sample$var32".
								// 
								// cv$temp$12$var154's comment
								// Variable declaration of cv$temp$12$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								// 
								// Substituted "sample$var120" with its value "sample$var32".
								// 
								// cv$temp$12$var154's comment
								// Variable declaration of cv$temp$12$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								// 
								// Substituted "sample$var120" with its value "sample$var32".
								// 
								// cv$temp$12$var154's comment
								// Variable declaration of cv$temp$12$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "sample$var120" with its value "sample$var32".
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$27$var152, cv$temp$28$var154);
								else
									// Substituted "sample$var120" with its value "sample$var32".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$27$var152, cv$temp$28$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$27$var152, cv$temp$28$var154));
							}
							
							// Variable declaration of cv$temp$43$var152 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$43$var152 = current_metric_mean[server][cv$valuePos];
							
							// Variable declaration of cv$temp$44$var154 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$44$var154 = current_metric_var[server][cv$valuePos];
							
							// Record the probability of sample task 164 generating output with current configuration.
							// 
							// Substituted "sample$var120" with its value "sample$var32".
							if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$43$var152, cv$temp$44$var154) < cv$accumulatedConsumerProbabilities))
								// Substituted "sample$var120" with its value "sample$var32".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$43$var152, cv$temp$44$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "sample$var120" with its value "sample$var32".
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$43$var152, cv$temp$44$var154);
								else
									// Substituted "sample$var120" with its value "sample$var32".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$43$var152, cv$temp$44$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][0], cv$temp$43$var152, cv$temp$44$var154));
							}
							
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = -2.0;
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
			if((!fixedFlag$sample61 && (1 < length$metric[sample$var32][0]))) {
				// Looking for a path between Sample 48 and consumer Categorical 56.
				// Processing sample task 61 of consumer random variable null.
				// 
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var56[cv$i] = 0.0;
				
				// Add the current distribution to the distribution accumulator.
				// 
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// Add the probability of this argument configuration.
				// 
				// Declare and zero an accumulator for tracking the reached source probability space.
				// 
				// cv$temp$51$var55's comment
				// Variable declaration of cv$temp$51$var55 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var56, 1.0, m[cv$valuePos]);
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$sample$82_2" with its value "sample$var32".
				double[] cv$sampleDistribution = distribution$sample61[sample$var32][0];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					// 
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					double cv$normalisedDistValue = cv$distributionAccumulator$var56[cv$i];
					
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
				// 
				// Record the reached distribution.
				// 
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// Add the probability of this argument configuration.
				// 
				// Declare and zero an accumulator for tracking the reached source probability space.
				cv$accumulatedDistributionProbabilities = Math.log(cv$overlap);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var44$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample48[sample$var32];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var44$stateProbabilityGlobal[0];
		
		// Find max value.
		// 
		// Get a local reference to the scratch space.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var44$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var44$stateProbabilityGlobal[cv$lseIndex];
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
			// 
			// Get a local reference to the scratch space.
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var44$stateProbabilityGlobal.length; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var44$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var44$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = (1.0 / cv$var44$stateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var44$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = Math.exp((cv$var44$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 61 drawn from Categorical 56. Inference was performed using variable
	// marginalization.
	private final void sample61(int sample$var32, int timeStep$var49) {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 56 creating
			// sample task 61.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Enumerating the possible arguments for Categorical 56.
			if((1 == timeStep$var49)) {
				// Enumerating the possible arguments for Categorical 56.
				if(fixedFlag$sample48) {
					int var26 = st[sample$var32][0];
					
					// Substituted "timeStep$var49" with its value "1".
					if(((0 <= var26) && (var26 < noStates))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						// 
						// cv$temp$0$var55's comment
						// Variable declaration of cv$temp$0$var55 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "timeStep$var49" with its value "1".
						double cv$accumulatedProbabilities = DistributionSampling.logProbabilityCategorical(cv$valuePos, m[st[sample$var32][0]]);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < length$metric[sample$var32][0])) {
							// Processing random variable 145.
							// 
							// Looking for a path between Sample 61 and consumer Bernoulli 145.
							for(int server = 0; server < noServers; server += 1)
								// Processing sample task 152 of consumer random variable null.
								// 
								// A check to ensure rounding of floating point values can never result in a negative
								// value.
								// 
								// Recorded the probability of reaching sample task 152 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								// 
								// Variable declaration of cv$accumulatedConsumerProbabilities moved.
								// Declaration comment was:
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								// 
								// Substituted "sample$var120" with its value "sample$var32".
								// 
								// Substituted "timeStep$var140" with its value "1".
								// 
								// cv$temp$4$var144's comment
								// Variable declaration of cv$temp$4$var144 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var32][server][1], current_metric_valid_bias[server][cv$valuePos]) + cv$accumulatedProbabilities);
							for(int server = 0; server < noServers; server += 1)
								// Set the flags to false
								// 
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								// 
								// Substituted "timeStep$var140" with its value "1".
								guard$sample61gaussian163$global[sample$var32][server][1] = false;
							for(int server = 0; server < noServers; server += 1) {
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								// 
								// Substituted "timeStep$var140" with its value "1".
								if(!guard$sample61gaussian163$global[sample$var32][server][1]) {
									// The body will execute, so should not be executed again
									// 
									// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var140" with its value "1".
									guard$sample61gaussian163$global[sample$var32][server][1] = true;
									
									// Processing sample task 164 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Constraints moved from conditionals in inner loops/scopes/etc.
									// 
									// Substituted "timeStep$var140" with its value "1".
									if(metric_valid_g[sample$var32][server][1]) {
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// Substituted "sample$var120" with its value "sample$var32".
										// 
										// Substituted "timeStep$var140" with its value "1".
										// 
										// cv$temp$11$var154's comment
										// Variable declaration of cv$temp$11$var154 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], current_metric_mean[server][cv$valuePos], current_metric_var[server][cv$valuePos]);
										
										// Variable declaration of cv$temp$74$var152 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double cv$temp$74$var152 = current_metric_mean[server][cv$valuePos];
										
										// Variable declaration of cv$temp$75$var154 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double cv$temp$75$var154 = current_metric_var[server][cv$valuePos];
										
										// Record the probability of sample task 164 generating output with current configuration.
										// 
										// Substituted "sample$var120" with its value "sample$var32".
										// 
										// Substituted "timeStep$var140" with its value "1".
										if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$74$var152, cv$temp$75$var154) < cv$accumulatedConsumerProbabilities))
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$74$var152, cv$temp$75$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											// If the second value is -infinity.
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$11$var154's comment
											// Variable declaration of cv$temp$11$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Value of the variable at this index
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$11$var154's comment
											// Variable declaration of cv$temp$11$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Value of the variable at this index
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$11$var154's comment
											// Variable declaration of cv$temp$11$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Value of the variable at this index
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$11$var154's comment
											// Variable declaration of cv$temp$11$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Value of the variable at this index
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$74$var152, cv$temp$75$var154);
											else
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$74$var152, cv$temp$75$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$74$var152, cv$temp$75$var154));
										}
										
										// Variable declaration of cv$temp$138$var152 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double cv$temp$138$var152 = current_metric_mean[server][cv$valuePos];
										
										// Variable declaration of cv$temp$139$var154 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double cv$temp$139$var154 = current_metric_var[server][cv$valuePos];
										
										// Record the probability of sample task 164 generating output with current configuration.
										// 
										// Substituted "sample$var120" with its value "sample$var32".
										// 
										// Substituted "timeStep$var140" with its value "1".
										if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$138$var152, cv$temp$139$var154) < cv$accumulatedConsumerProbabilities))
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$138$var152, cv$temp$139$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											// If the second value is -infinity.
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$138$var152, cv$temp$139$var154);
											else
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$138$var152, cv$temp$139$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$138$var152, cv$temp$139$var154));
										}
										
										// Recorded the probability of reaching sample task 164 with the current configuration.
										// 
										// Recorded the probability of reaching sample task 164 with the current configuration.
										// 
										// Recorded the probability of reaching sample task 164 with the current configuration.
										// 
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										cv$consumerDistributionProbabilityAccumulator = -2.0;
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
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								// 
								// Substituted "timeStep$var140" with its value "1".
								if(!guard$sample61gaussian163$global[sample$var32][server][1]) {
									// The body will execute, so should not be executed again
									// 
									// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var140" with its value "1".
									guard$sample61gaussian163$global[sample$var32][server][1] = true;
									
									// Processing sample task 164 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
									// the output of Sample task 61.
									// 
									// Constraints moved from conditionals in inner loops/scopes/etc.
									// 
									// Substituted "timeStep$var140" with its value "1".
									if(metric_valid_g[sample$var32][server][1]) {
										// Substituted "sample$var120" with its value "sample$var32".
										// 
										// Substituted "timeStep$var140" with its value "1".
										// 
										// cv$temp$43$var154's comment
										// Variable declaration of cv$temp$43$var154 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], current_metric_mean[server][cv$valuePos], current_metric_var[server][cv$valuePos]);
										
										// Variable declaration of cv$temp$106$var152 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// 
										// Value of the variable at this index
										double cv$temp$106$var152 = current_metric_mean[server][cv$valuePos];
										
										// Variable declaration of cv$temp$107$var154 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// 
										// Value of the variable at this index
										double cv$temp$107$var154 = current_metric_var[server][cv$valuePos];
										
										// Record the probability of sample task 164 generating output with current configuration.
										// 
										// Substituted "sample$var120" with its value "sample$var32".
										// 
										// Substituted "timeStep$var140" with its value "1".
										if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$106$var152, cv$temp$107$var154) < cv$accumulatedConsumerProbabilities))
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$106$var152, cv$temp$107$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											// If the second value is -infinity.
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$43$var154's comment
											// Variable declaration of cv$temp$43$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
											// the output of Sample task 61.
											// 
											// Value of the variable at this index
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$43$var154's comment
											// Variable declaration of cv$temp$43$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
											// the output of Sample task 61.
											// 
											// Value of the variable at this index
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$43$var154's comment
											// Variable declaration of cv$temp$43$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
											// the output of Sample task 61.
											// 
											// Value of the variable at this index
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$43$var154's comment
											// Variable declaration of cv$temp$43$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
											// the output of Sample task 61.
											// 
											// Value of the variable at this index
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$106$var152, cv$temp$107$var154);
											else
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$106$var152, cv$temp$107$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$106$var152, cv$temp$107$var154));
										}
										
										// Variable declaration of cv$temp$170$var152 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// 
										// Value of the variable at this index
										double cv$temp$170$var152 = current_metric_mean[server][cv$valuePos];
										
										// Variable declaration of cv$temp$171$var154 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// 
										// Value of the variable at this index
										double cv$temp$171$var154 = current_metric_var[server][cv$valuePos];
										
										// Record the probability of sample task 164 generating output with current configuration.
										// 
										// Substituted "sample$var120" with its value "sample$var32".
										// 
										// Substituted "timeStep$var140" with its value "1".
										if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$170$var152, cv$temp$171$var154) < cv$accumulatedConsumerProbabilities))
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$170$var152, cv$temp$171$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											// If the second value is -infinity.
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$170$var152, cv$temp$171$var154);
											else
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$170$var152, cv$temp$171$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$170$var152, cv$temp$171$var154));
										}
										
										// Recorded the probability of reaching sample task 164 with the current configuration.
										// 
										// Recorded the probability of reaching sample task 164 with the current configuration.
										// 
										// Recorded the probability of reaching sample task 164 with the current configuration.
										// 
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										cv$consumerDistributionProbabilityAccumulator = -2.0;
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
					// Enumerating the possible outputs of Categorical 43.
					for(int index$sample48$5 = 0; index$sample48$5 < noStates; index$sample48$5 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$4" with its value "sample$var32".
						double cv$probabilitySample48Value6 = distribution$sample48[sample$var32][index$sample48$5];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample48Value6);
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						// 
						// cv$temp$1$var55's comment
						// Variable declaration of cv$temp$1$var55 moved.
						// 
						// Constructing a random variable input for use later.
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample48Value6) + DistributionSampling.logProbabilityCategorical(cv$valuePos, m[index$sample48$5]));
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < length$metric[sample$var32][0])) {
							// Processing random variable 145.
							// 
							// Looking for a path between Sample 61 and consumer Bernoulli 145.
							for(int server = 0; server < noServers; server += 1)
								// Processing sample task 152 of consumer random variable null.
								// 
								// A check to ensure rounding of floating point values can never result in a negative
								// value.
								// 
								// Recorded the probability of reaching sample task 152 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								// 
								// Variable declaration of cv$accumulatedConsumerProbabilities moved.
								// Declaration comment was:
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								// 
								// Substituted "sample$var120" with its value "sample$var32".
								// 
								// Substituted "timeStep$var140" with its value "1".
								// 
								// cv$temp$5$var144's comment
								// Variable declaration of cv$temp$5$var144 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var32][server][1], current_metric_valid_bias[server][cv$valuePos]) + cv$accumulatedProbabilities);
							for(int server = 0; server < noServers; server += 1)
								// Set the flags to false
								// 
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								// 
								// Substituted "timeStep$var140" with its value "1".
								guard$sample61gaussian163$global[sample$var32][server][1] = false;
							for(int server = 0; server < noServers; server += 1) {
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								// 
								// Substituted "timeStep$var140" with its value "1".
								if(!guard$sample61gaussian163$global[sample$var32][server][1]) {
									// The body will execute, so should not be executed again
									// 
									// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var140" with its value "1".
									guard$sample61gaussian163$global[sample$var32][server][1] = true;
									
									// Processing sample task 164 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Constraints moved from conditionals in inner loops/scopes/etc.
									// 
									// Substituted "timeStep$var140" with its value "1".
									if(metric_valid_g[sample$var32][server][1]) {
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// Substituted "sample$var120" with its value "sample$var32".
										// 
										// Substituted "timeStep$var140" with its value "1".
										// 
										// cv$temp$19$var154's comment
										// Variable declaration of cv$temp$19$var154 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], current_metric_mean[server][cv$valuePos], current_metric_var[server][cv$valuePos]);
										
										// Variable declaration of cv$temp$82$var152 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double cv$temp$82$var152 = current_metric_mean[server][cv$valuePos];
										
										// Variable declaration of cv$temp$83$var154 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double cv$temp$83$var154 = current_metric_var[server][cv$valuePos];
										
										// Record the probability of sample task 164 generating output with current configuration.
										// 
										// Substituted "sample$var120" with its value "sample$var32".
										// 
										// Substituted "timeStep$var140" with its value "1".
										if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$82$var152, cv$temp$83$var154) < cv$accumulatedConsumerProbabilities))
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$82$var152, cv$temp$83$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											// If the second value is -infinity.
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$19$var154's comment
											// Variable declaration of cv$temp$19$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Value of the variable at this index
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$19$var154's comment
											// Variable declaration of cv$temp$19$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Value of the variable at this index
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$19$var154's comment
											// Variable declaration of cv$temp$19$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Value of the variable at this index
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$19$var154's comment
											// Variable declaration of cv$temp$19$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Value of the variable at this index
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$82$var152, cv$temp$83$var154);
											else
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$82$var152, cv$temp$83$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$82$var152, cv$temp$83$var154));
										}
										
										// Variable declaration of cv$temp$146$var152 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double cv$temp$146$var152 = current_metric_mean[server][cv$valuePos];
										
										// Variable declaration of cv$temp$147$var154 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double cv$temp$147$var154 = current_metric_var[server][cv$valuePos];
										
										// Record the probability of sample task 164 generating output with current configuration.
										// 
										// Substituted "sample$var120" with its value "sample$var32".
										// 
										// Substituted "timeStep$var140" with its value "1".
										if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$146$var152, cv$temp$147$var154) < cv$accumulatedConsumerProbabilities))
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$146$var152, cv$temp$147$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											// If the second value is -infinity.
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$146$var152, cv$temp$147$var154);
											else
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$146$var152, cv$temp$147$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$146$var152, cv$temp$147$var154));
										}
										
										// Recorded the probability of reaching sample task 164 with the current configuration.
										// 
										// Recorded the probability of reaching sample task 164 with the current configuration.
										// 
										// Recorded the probability of reaching sample task 164 with the current configuration.
										// 
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										cv$consumerDistributionProbabilityAccumulator = -2.0;
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
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								// 
								// Substituted "timeStep$var140" with its value "1".
								if(!guard$sample61gaussian163$global[sample$var32][server][1]) {
									// The body will execute, so should not be executed again
									// 
									// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var140" with its value "1".
									guard$sample61gaussian163$global[sample$var32][server][1] = true;
									
									// Processing sample task 164 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Substituted "sample$var120" with its value "sample$var32".
									// 
									// Constraints moved from conditionals in inner loops/scopes/etc.
									// 
									// Substituted "timeStep$var140" with its value "1".
									if(metric_valid_g[sample$var32][server][1]) {
										// Substituted "sample$var120" with its value "sample$var32".
										// 
										// Substituted "timeStep$var140" with its value "1".
										// 
										// cv$temp$51$var154's comment
										// Variable declaration of cv$temp$51$var154 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// 
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], current_metric_mean[server][cv$valuePos], current_metric_var[server][cv$valuePos]);
										
										// Variable declaration of cv$temp$114$var152 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// 
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// 
										// Value of the variable at this index
										double cv$temp$114$var152 = current_metric_mean[server][cv$valuePos];
										
										// Variable declaration of cv$temp$115$var154 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// 
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// 
										// Value of the variable at this index
										double cv$temp$115$var154 = current_metric_var[server][cv$valuePos];
										
										// Record the probability of sample task 164 generating output with current configuration.
										// 
										// Substituted "sample$var120" with its value "sample$var32".
										// 
										// Substituted "timeStep$var140" with its value "1".
										if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$114$var152, cv$temp$115$var154) < cv$accumulatedConsumerProbabilities))
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$114$var152, cv$temp$115$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											// If the second value is -infinity.
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$51$var154's comment
											// Variable declaration of cv$temp$51$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
											// the output of Sample task 61.
											// 
											// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
											// the output of Sample task 61.
											// 
											// Value of the variable at this index
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$51$var154's comment
											// Variable declaration of cv$temp$51$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
											// the output of Sample task 61.
											// 
											// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
											// the output of Sample task 61.
											// 
											// Value of the variable at this index
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$51$var154's comment
											// Variable declaration of cv$temp$51$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
											// the output of Sample task 61.
											// 
											// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
											// the output of Sample task 61.
											// 
											// Value of the variable at this index
											// 
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											// 
											// cv$temp$51$var154's comment
											// Variable declaration of cv$temp$51$var154 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
											// the output of Sample task 61.
											// 
											// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
											// the output of Sample task 61.
											// 
											// Value of the variable at this index
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$114$var152, cv$temp$115$var154);
											else
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$114$var152, cv$temp$115$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$114$var152, cv$temp$115$var154));
										}
										
										// Variable declaration of cv$temp$178$var152 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// 
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// 
										// Value of the variable at this index
										double cv$temp$178$var152 = current_metric_mean[server][cv$valuePos];
										
										// Variable declaration of cv$temp$179$var154 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// 
										// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
										// the output of Sample task 61.
										// 
										// Value of the variable at this index
										double cv$temp$179$var154 = current_metric_var[server][cv$valuePos];
										
										// Record the probability of sample task 164 generating output with current configuration.
										// 
										// Substituted "sample$var120" with its value "sample$var32".
										// 
										// Substituted "timeStep$var140" with its value "1".
										if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$178$var152, cv$temp$179$var154) < cv$accumulatedConsumerProbabilities))
											// Substituted "sample$var120" with its value "sample$var32".
											// 
											// Substituted "timeStep$var140" with its value "1".
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$178$var152, cv$temp$179$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											// If the second value is -infinity.
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$178$var152, cv$temp$179$var154);
											else
												// Substituted "sample$var120" with its value "sample$var32".
												// 
												// Substituted "timeStep$var140" with its value "1".
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$178$var152, cv$temp$179$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][1], cv$temp$178$var152, cv$temp$179$var154));
										}
										
										// Recorded the probability of reaching sample task 164 with the current configuration.
										// 
										// Recorded the probability of reaching sample task 164 with the current configuration.
										// 
										// Recorded the probability of reaching sample task 164 with the current configuration.
										// 
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										cv$consumerDistributionProbabilityAccumulator = -2.0;
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
			int index$timeStep$13 = (timeStep$var49 - 1);
			
			// index$timeStep$1's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var49 - 1)".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var49 - 1)".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var49 - 1)".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var49 - 1)".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var49 - 1)".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var49 - 1)".
			if(((1 <= index$timeStep$13) && !(index$timeStep$13 == timeStep$var49))) {
				// Enumerating the possible outputs of Categorical 56.
				for(int index$sample61$14 = 0; index$sample61$14 < noStates; index$sample61$14 += 1) {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$12" with its value "sample$var32".
					double cv$probabilitySample61Value15 = distribution$sample61[sample$var32][(index$timeStep$13 - 1)][index$sample61$14];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample61Value15);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					// 
					// cv$temp$3$var55's comment
					// Variable declaration of cv$temp$3$var55 moved.
					// 
					// Constructing a random variable input for use later.
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample61Value15) + DistributionSampling.logProbabilityCategorical(cv$valuePos, m[index$sample61$14]));
					for(int server = 0; server < noServers; server += 1)
						// Processing sample task 152 of consumer random variable null.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 152 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "sample$var120" with its value "sample$var32".
						// 
						// cv$temp$7$var144's comment
						// Variable declaration of cv$temp$7$var144 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var32][server][timeStep$var49], current_metric_valid_bias[server][cv$valuePos]) + cv$accumulatedProbabilities);
					for(int server = 0; server < noServers; server += 1)
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample61gaussian163$global[sample$var32][server][timeStep$var49] = false;
					for(int server = 0; server < noServers; server += 1) {
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						if(!guard$sample61gaussian163$global[sample$var32][server][timeStep$var49]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							guard$sample61gaussian163$global[sample$var32][server][timeStep$var49] = true;
							
							// Processing sample task 164 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(metric_valid_g[sample$var32][server][timeStep$var49]) {
								// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
								// the output of Sample task 61.
								// Substituted "sample$var120" with its value "sample$var32".
								// 
								// cv$temp$35$var154's comment
								// Variable declaration of cv$temp$35$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], current_metric_mean[server][cv$valuePos], current_metric_var[server][cv$valuePos]);
								
								// Variable declaration of cv$temp$98$var152 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$98$var152 = current_metric_mean[server][cv$valuePos];
								
								// Variable declaration of cv$temp$99$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$99$var154 = current_metric_var[server][cv$valuePos];
								
								// Record the probability of sample task 164 generating output with current configuration.
								// 
								// Substituted "sample$var120" with its value "sample$var32".
								if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$98$var152, cv$temp$99$var154) < cv$accumulatedConsumerProbabilities))
									// Substituted "sample$var120" with its value "sample$var32".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$98$var152, cv$temp$99$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									// 
									// Substituted "sample$var120" with its value "sample$var32".
									// 
									// cv$temp$35$var154's comment
									// Variable declaration of cv$temp$35$var154 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									// 
									// Substituted "sample$var120" with its value "sample$var32".
									// 
									// cv$temp$35$var154's comment
									// Variable declaration of cv$temp$35$var154 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									// 
									// Substituted "sample$var120" with its value "sample$var32".
									// 
									// cv$temp$35$var154's comment
									// Variable declaration of cv$temp$35$var154 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									// 
									// Substituted "sample$var120" with its value "sample$var32".
									// 
									// cv$temp$35$var154's comment
									// Variable declaration of cv$temp$35$var154 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "sample$var120" with its value "sample$var32".
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$98$var152, cv$temp$99$var154);
									else
										// Substituted "sample$var120" with its value "sample$var32".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$98$var152, cv$temp$99$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$98$var152, cv$temp$99$var154));
								}
								
								// Variable declaration of cv$temp$162$var152 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$162$var152 = current_metric_mean[server][cv$valuePos];
								
								// Variable declaration of cv$temp$163$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$163$var154 = current_metric_var[server][cv$valuePos];
								
								// Record the probability of sample task 164 generating output with current configuration.
								// 
								// Substituted "sample$var120" with its value "sample$var32".
								if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$162$var152, cv$temp$163$var154) < cv$accumulatedConsumerProbabilities))
									// Substituted "sample$var120" with its value "sample$var32".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$162$var152, cv$temp$163$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "sample$var120" with its value "sample$var32".
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$162$var152, cv$temp$163$var154);
									else
										// Substituted "sample$var120" with its value "sample$var32".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$162$var152, cv$temp$163$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$162$var152, cv$temp$163$var154));
								}
								
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = -2.0;
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
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						if(!guard$sample61gaussian163$global[sample$var32][server][timeStep$var49]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							guard$sample61gaussian163$global[sample$var32][server][timeStep$var49] = true;
							
							// Processing sample task 164 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(metric_valid_g[sample$var32][server][timeStep$var49]) {
								// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
								// the output of Sample task 61.
								// Substituted "sample$var120" with its value "sample$var32".
								// 
								// cv$temp$67$var154's comment
								// Variable declaration of cv$temp$67$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
								// the output of Sample task 61.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], current_metric_mean[server][cv$valuePos], current_metric_var[server][cv$valuePos]);
								
								// Variable declaration of cv$temp$130$var152 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
								// the output of Sample task 61.
								// 
								// Value of the variable at this index
								double cv$temp$130$var152 = current_metric_mean[server][cv$valuePos];
								
								// Variable declaration of cv$temp$131$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
								// the output of Sample task 61.
								// 
								// Value of the variable at this index
								double cv$temp$131$var154 = current_metric_var[server][cv$valuePos];
								
								// Record the probability of sample task 164 generating output with current configuration.
								// 
								// Substituted "sample$var120" with its value "sample$var32".
								if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$130$var152, cv$temp$131$var154) < cv$accumulatedConsumerProbabilities))
									// Substituted "sample$var120" with its value "sample$var32".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$130$var152, cv$temp$131$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									// 
									// Substituted "sample$var120" with its value "sample$var32".
									// 
									// cv$temp$67$var154's comment
									// Variable declaration of cv$temp$67$var154 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
									// the output of Sample task 61.
									// 
									// Value of the variable at this index
									// 
									// Substituted "sample$var120" with its value "sample$var32".
									// 
									// cv$temp$67$var154's comment
									// Variable declaration of cv$temp$67$var154 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
									// the output of Sample task 61.
									// 
									// Value of the variable at this index
									// 
									// Substituted "sample$var120" with its value "sample$var32".
									// 
									// cv$temp$67$var154's comment
									// Variable declaration of cv$temp$67$var154 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
									// the output of Sample task 61.
									// 
									// Value of the variable at this index
									// 
									// Substituted "sample$var120" with its value "sample$var32".
									// 
									// cv$temp$67$var154's comment
									// Variable declaration of cv$temp$67$var154 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
									// the output of Sample task 61.
									// 
									// Value of the variable at this index
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "sample$var120" with its value "sample$var32".
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$130$var152, cv$temp$131$var154);
									else
										// Substituted "sample$var120" with its value "sample$var32".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$130$var152, cv$temp$131$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$130$var152, cv$temp$131$var154));
								}
								
								// Variable declaration of cv$temp$194$var152 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
								// the output of Sample task 61.
								// 
								// Value of the variable at this index
								double cv$temp$194$var152 = current_metric_mean[server][cv$valuePos];
								
								// Variable declaration of cv$temp$195$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
								// the output of Sample task 61.
								// 
								// Value of the variable at this index
								double cv$temp$195$var154 = current_metric_var[server][cv$valuePos];
								
								// Record the probability of sample task 164 generating output with current configuration.
								// 
								// Substituted "sample$var120" with its value "sample$var32".
								if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$194$var152, cv$temp$195$var154) < cv$accumulatedConsumerProbabilities))
									// Substituted "sample$var120" with its value "sample$var32".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$194$var152, cv$temp$195$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "sample$var120" with its value "sample$var32".
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$194$var152, cv$temp$195$var154);
									else
										// Substituted "sample$var120" with its value "sample$var32".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$194$var152, cv$temp$195$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var32][server][timeStep$var49], cv$temp$194$var152, cv$temp$195$var154));
								}
								
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = -2.0;
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
			int index$timeStep$312_3 = (timeStep$var49 + 1);
			if((index$timeStep$312_3 < length$metric[sample$var32][0])) {
				// Processing sample task 61 of consumer random variable null.
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var56[cv$i] = 0.0;
				
				// Declare and zero an accumulator for tracking the reached source probability space.
				double scopeVariable$reachedSourceProbability = 0.0;
				
				// Enumerating the possible arguments for Categorical 56.
				if((1 == timeStep$var49)) {
					// Enumerating the possible arguments for Categorical 56.
					if(fixedFlag$sample48) {
						int index$var26$323_1 = st[sample$var32][0];
						
						// Substituted "timeStep$var49" with its value "1".
						if(((0 <= index$var26$323_1) && (index$var26$323_1 < noStates)))
							// Add the probability of this argument configuration.
							// 
							// Declare and zero an accumulator for tracking the reached source probability space.
							scopeVariable$reachedSourceProbability = 1.0;
					} else {
						// Enumerating the possible outputs of Categorical 43.
						for(int index$sample48$319 = 0; index$sample48$319 < noStates; index$sample48$319 += 1)
							// Add the probability of this argument configuration.
							// 
							// cv$probabilitySample48Value320's comment
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$318" with its value "sample$var32".
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample48[sample$var32][index$sample48$319]);
					}
				}
				int index$timeStep$327 = (timeStep$var49 - 1);
				
				// index$timeStep$314's comment
				// Copy of index so that its values can be safely substituted
				// 
				// Substituted "index$timeStep$312_3" with its value "(timeStep$var49 + 1)".
				// 
				// Substituted "index$timeStep$312_3" with its value "(timeStep$var49 + 1)".
				// 
				// Substituted "index$timeStep$312_3" with its value "(timeStep$var49 + 1)".
				// 
				// Substituted "index$timeStep$312_3" with its value "(timeStep$var49 + 1)".
				// 
				// Substituted "index$timeStep$312_3" with its value "(timeStep$var49 + 1)".
				// 
				// Substituted "index$timeStep$312_3" with its value "(timeStep$var49 + 1)".
				if((((1 <= index$timeStep$327) && !(index$timeStep$327 == timeStep$var49)) && !(index$timeStep$327 == index$timeStep$312_3))) {
					// Enumerating the possible outputs of Categorical 56.
					for(int index$sample61$328 = 0; index$sample61$328 < noStates; index$sample61$328 += 1)
						// Add the probability of this argument configuration.
						// 
						// cv$probabilitySample61Value329's comment
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$326" with its value "sample$var32".
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample61[sample$var32][(index$timeStep$327 - 1)][index$sample61$328]);
				}
				
				// Add the current distribution to the distribution accumulator.
				// 
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// cv$temp$200$var55's comment
				// Variable declaration of cv$temp$200$var55 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 56.
				// 
				// Looking for a path between Sample 61 and consumer Categorical 56.
				// 
				// Value of the variable at this index
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var56, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$sample$312_2" with its value "sample$var32".
				double[] cv$sampleDistribution = distribution$sample61[sample$var32][(index$timeStep$312_3 - 1)];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					// 
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Zero an accumulator to track the probabilities reached.
					double cv$normalisedDistValue = (cv$distributionAccumulator$var56[cv$i] / scopeVariable$reachedSourceProbability);
					
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
				// 
				// Record the reached distribution.
				// 
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// Zero an accumulator to track the probabilities reached.
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * scopeVariable$reachedSourceProbability) + 1.0) - Math.min(scopeVariable$reachedSourceProbability, 1.0)));
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			cv$var57$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample61[sample$var32][(timeStep$var49 - 1)];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var57$stateProbabilityGlobal[0];
		
		// Find max value.
		// 
		// Get a local reference to the scratch space.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var57$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var57$stateProbabilityGlobal[cv$lseIndex];
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
			// 
			// Get a local reference to the scratch space.
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var57$stateProbabilityGlobal.length; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var57$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var57$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = (1.0 / cv$var57$stateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var57$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = Math.exp((cv$var57$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 92 drawn from Uniform 78. Inference was performed using Metropolis-Hastings.
	private final void sample92(int var82, int var86) {
		// The original value of the sample
		double cv$originalValue = current_metric_mean[var82][var86];
		
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
		double cv$proposedValue = DistributionSampling.sampleGaussian(RNG$, cv$originalValue, cv$var);
		
		// Unrolled loop
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var77" with its value "(double)max_metric".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityUniform(cv$originalValue, 0.0, max_metric);
			
			// Processing random variable 155.
			// 
			// Looking for a path between Sample 92 and consumer Gaussian 155.
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((metric_valid_g[sample$var120][var82][0] && (0 < length$metric[sample$var120][0]))) {
					if(fixedFlag$sample48) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var86 == st[sample$var120][0])) {
							// Processing sample task 164 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var100 = st[sample$var120][0];
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(((0 <= var100) && (var100 < noStates))) {
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$2$var152" with its value "var152".
								// 
								// cv$temp$3$var154's comment
								// Variable declaration of cv$temp$3$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var82".
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, current_metric_var[var82][st[sample$var120][0]]);
								
								// Variable declaration of cv$temp$31$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var82".
								double cv$temp$31$var154 = current_metric_var[var82][st[sample$var120][0]];
								
								// Record the probability of sample task 164 generating output with current configuration.
								// 
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$30$var152" with its value "var152".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, cv$temp$31$var154) < cv$accumulatedConsumerProbabilities))
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$30$var152" with its value "var152".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, cv$temp$31$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "server" with its value "var82".
										// 
										// Substituted "cv$temp$30$var152" with its value "var152".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, cv$temp$31$var154);
									else
										// Substituted "server" with its value "var82".
										// 
										// Substituted "cv$temp$30$var152" with its value "var152".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, cv$temp$31$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, cv$temp$31$var154));
								}
								
								// Variable declaration of cv$temp$59$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var82".
								double cv$temp$59$var154 = current_metric_var[var82][st[sample$var120][0]];
								
								// Record the probability of sample task 164 generating output with current configuration.
								// 
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$58$var152" with its value "var152".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, cv$temp$59$var154) < cv$accumulatedConsumerProbabilities))
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$58$var152" with its value "var152".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, cv$temp$59$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "server" with its value "var82".
										// 
										// Substituted "cv$temp$58$var152" with its value "var152".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, cv$temp$59$var154);
									else
										// Substituted "server" with its value "var82".
										// 
										// Substituted "cv$temp$58$var152" with its value "var152".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, cv$temp$59$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, cv$temp$59$var154));
								}
								
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = -2.0;
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
						// Substituted "sample$var32" with its value "sample$var120".
						// 
						// Substituted "index$sample48$6" with its value "var86".
						double cv$probabilitySample48Value7 = distribution$sample48[sample$var120][var86];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// This value is not used before it is set again, so removing the value declaration.
						// 
						// Processing sample task 164 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "server" with its value "var82".
						// 
						// Substituted "cv$temp$9$var154" with its value "var154".
						// 
						// Constructing a random variable input for use later.
						// 
						// Set the current value to the current state of the tree.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var82".
						// 
						// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
						// the output of Sample task 92.
						// 
						// Substituted "index$sample48$6" with its value "var86".
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, current_metric_var[var82][var86]));
						{
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var82".
							// 
							// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
							// the output of Sample task 92.
							// 
							// Substituted "index$sample48$6" with its value "var86".
							double var154 = current_metric_var[var82][var86];
							
							// Record the probability of sample task 164 generating output with current configuration.
							// 
							// Substituted "server" with its value "var82".
							// 
							// Substituted "cv$temp$37$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, var154)) < cv$accumulatedConsumerProbabilities))
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$37$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								// 
								// The original value of the sample
								// 
								// The original value of the sample
								// 
								// The original value of the sample
								// 
								// The original value of the sample
								// 
								// The original value of the sample
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$37$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, var154));
								else
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$37$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, var154)))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, var154));
							}
						}
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var82".
						// 
						// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
						// the output of Sample task 92.
						// 
						// Substituted "index$sample48$6" with its value "var86".
						double var154 = current_metric_var[var82][var86];
						
						// Record the probability of sample task 164 generating output with current configuration.
						// 
						// Substituted "server" with its value "var82".
						// 
						// Substituted "cv$temp$65$var154" with its value "var154".
						// 
						// Constructing a random variable input for use later.
						// 
						// Set the current value to the current state of the tree.
						if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, var154)) < cv$accumulatedConsumerProbabilities))
							// Substituted "server" with its value "var82".
							// 
							// Substituted "cv$temp$65$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$65$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, var154));
							else
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$65$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, var154)))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$originalValue, var154));
						}
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// This value is not used before it is set again, so removing the value declaration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample48Value7 + cv$probabilitySample48Value7) + cv$probabilitySample48Value7)), 0.0);
						
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
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int timeStep$var140 = 1; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
					// Substituted "server" with its value "var82".
					if(metric_valid_g[sample$var120][var82][timeStep$var140]) {
						if(fixedFlag$sample61) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var86 == st[sample$var120][timeStep$var140])) {
								// Processing sample task 164 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var100 = st[sample$var120][timeStep$var140];
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if(((0 <= var100) && (var100 < noStates))) {
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$20$var152" with its value "var152".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									// 
									// cv$temp$21$var154's comment
									// Variable declaration of cv$temp$21$var154 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var82".
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, current_metric_var[var82][st[sample$var120][timeStep$var140]]);
									
									// Variable declaration of cv$temp$49$var154 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var82".
									double cv$temp$49$var154 = current_metric_var[var82][st[sample$var120][timeStep$var140]];
									
									// Record the probability of sample task 164 generating output with current configuration.
									// 
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$48$var152" with its value "var152".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, cv$temp$49$var154) < cv$accumulatedConsumerProbabilities))
										// Substituted "server" with its value "var82".
										// 
										// Substituted "cv$temp$48$var152" with its value "var152".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, cv$temp$49$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										// 
										// The original value of the sample
										// 
										// The original value of the sample
										// 
										// The original value of the sample
										// 
										// The original value of the sample
										// 
										// The original value of the sample
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "server" with its value "var82".
											// 
											// Substituted "cv$temp$48$var152" with its value "var152".
											// 
											// Constructing a random variable input for use later.
											// 
											// Set the current value to the current state of the tree.
											cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, cv$temp$49$var154);
										else
											// Substituted "server" with its value "var82".
											// 
											// Substituted "cv$temp$48$var152" with its value "var152".
											// 
											// Constructing a random variable input for use later.
											// 
											// Set the current value to the current state of the tree.
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, cv$temp$49$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, cv$temp$49$var154));
									}
									
									// Variable declaration of cv$temp$77$var154 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var82".
									double cv$temp$77$var154 = current_metric_var[var82][st[sample$var120][timeStep$var140]];
									
									// Record the probability of sample task 164 generating output with current configuration.
									// 
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$76$var152" with its value "var152".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, cv$temp$77$var154) < cv$accumulatedConsumerProbabilities))
										// Substituted "server" with its value "var82".
										// 
										// Substituted "cv$temp$76$var152" with its value "var152".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, cv$temp$77$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "server" with its value "var82".
											// 
											// Substituted "cv$temp$76$var152" with its value "var152".
											// 
											// Constructing a random variable input for use later.
											// 
											// Set the current value to the current state of the tree.
											cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, cv$temp$77$var154);
										else
											// Substituted "server" with its value "var82".
											// 
											// Substituted "cv$temp$76$var152" with its value "var152".
											// 
											// Constructing a random variable input for use later.
											// 
											// Set the current value to the current state of the tree.
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, cv$temp$77$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, cv$temp$77$var154));
									}
									
									// Recorded the probability of reaching sample task 164 with the current configuration.
									// 
									// Recorded the probability of reaching sample task 164 with the current configuration.
									// 
									// Recorded the probability of reaching sample task 164 with the current configuration.
									// 
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									cv$consumerDistributionProbabilityAccumulator = -2.0;
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
							// Substituted "sample$var32" with its value "sample$var120".
							// 
							// Substituted "index$sample61$18" with its value "var86".
							double cv$probabilitySample61Value19 = distribution$sample61[sample$var120][(timeStep$var140 - 1)][var86];
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 164 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "server" with its value "var82".
							// 
							// Substituted "cv$temp$27$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var82".
							// 
							// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
							// the output of Sample task 92.
							// 
							// Substituted "index$sample61$18" with its value "var86".
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, current_metric_var[var82][var86]));
							{
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var82".
								// 
								// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
								// the output of Sample task 92.
								// 
								// Substituted "index$sample61$18" with its value "var86".
								double var154 = current_metric_var[var82][var86];
								
								// Record the probability of sample task 164 generating output with current configuration.
								// 
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$55$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, var154)) < cv$accumulatedConsumerProbabilities))
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$55$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									// 
									// The original value of the sample
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "server" with its value "var82".
										// 
										// Substituted "cv$temp$55$var154" with its value "var154".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, var154));
									else
										// Substituted "server" with its value "var82".
										// 
										// Substituted "cv$temp$55$var154" with its value "var154".
										// 
										// Constructing a random variable input for use later.
										// 
										// Set the current value to the current state of the tree.
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, var154)))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, var154));
								}
							}
							
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var82".
							// 
							// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
							// the output of Sample task 92.
							// 
							// Substituted "index$sample61$18" with its value "var86".
							double var154 = current_metric_var[var82][var86];
							
							// Record the probability of sample task 164 generating output with current configuration.
							// 
							// Substituted "server" with its value "var82".
							// 
							// Substituted "cv$temp$83$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, var154)) < cv$accumulatedConsumerProbabilities))
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$83$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$83$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, var154));
								else
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$83$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, var154)))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$originalValue, var154));
							}
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample61Value19 + cv$probabilitySample61Value19) + cv$probabilitySample61Value19)), 0.0);
							
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
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		current_metric_mean[var82][var86] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var77" with its value "(double)max_metric".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityUniform(cv$proposedValue, 0.0, max_metric);
		
		// Processing random variable 155.
		// 
		// Looking for a path between Sample 92 and consumer Gaussian 155.
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((metric_valid_g[sample$var120][var82][0] && (0 < length$metric[sample$var120][0]))) {
				if(fixedFlag$sample48) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var86 == st[sample$var120][0])) {
						// Processing sample task 164 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var100 = st[sample$var120][0];
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(((0 <= var100) && (var100 < noStates))) {
							// Substituted "server" with its value "var82".
							// 
							// Substituted "cv$temp$2$var152" with its value "var152".
							// 
							// cv$temp$3$var154's comment
							// Variable declaration of cv$temp$3$var154 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var82".
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, current_metric_var[var82][st[sample$var120][0]]);
							
							// Variable declaration of cv$temp$31$var154 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var82".
							double cv$temp$31$var154 = current_metric_var[var82][st[sample$var120][0]];
							
							// Record the probability of sample task 164 generating output with current configuration.
							// 
							// Substituted "server" with its value "var82".
							// 
							// Substituted "cv$temp$30$var152" with its value "var152".
							// 
							// Constructing a random variable input for use later.
							if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, cv$temp$31$var154) < cv$accumulatedConsumerProbabilities))
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$30$var152" with its value "var152".
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, cv$temp$31$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$30$var152" with its value "var152".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, cv$temp$31$var154);
								else
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$30$var152" with its value "var152".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, cv$temp$31$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, cv$temp$31$var154));
							}
							
							// Variable declaration of cv$temp$59$var154 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var82".
							double cv$temp$59$var154 = current_metric_var[var82][st[sample$var120][0]];
							
							// Record the probability of sample task 164 generating output with current configuration.
							// 
							// Substituted "server" with its value "var82".
							// 
							// Substituted "cv$temp$58$var152" with its value "var152".
							// 
							// Constructing a random variable input for use later.
							if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, cv$temp$59$var154) < cv$accumulatedConsumerProbabilities))
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$58$var152" with its value "var152".
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, cv$temp$59$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$58$var152" with its value "var152".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, cv$temp$59$var154);
								else
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$58$var152" with its value "var152".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, cv$temp$59$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, cv$temp$59$var154));
							}
							
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Recorded the probability of reaching sample task 164 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = -2.0;
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
					// Substituted "sample$var32" with its value "sample$var120".
					// 
					// Substituted "index$sample48$6" with its value "var86".
					double cv$probabilitySample48Value7 = distribution$sample48[sample$var120][var86];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Processing sample task 164 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "server" with its value "var82".
					// 
					// Substituted "cv$temp$9$var154" with its value "var154".
					// 
					// Constructing a random variable input for use later.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "server" with its value "var82".
					// 
					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
					// the output of Sample task 92.
					// 
					// Substituted "index$sample48$6" with its value "var86".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, current_metric_var[var82][var86]));
					{
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var82".
						// 
						// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
						// the output of Sample task 92.
						// 
						// Substituted "index$sample48$6" with its value "var86".
						double var154 = current_metric_var[var82][var86];
						
						// Record the probability of sample task 164 generating output with current configuration.
						// 
						// Substituted "server" with its value "var82".
						// 
						// Substituted "cv$temp$37$var154" with its value "var154".
						// 
						// Constructing a random variable input for use later.
						if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, var154)) < cv$accumulatedConsumerProbabilities))
							// Substituted "server" with its value "var82".
							// 
							// Substituted "cv$temp$37$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							// 
							// The proposed new value for the sample
							// 
							// The proposed new value for the sample
							// 
							// The proposed new value for the sample
							// 
							// The proposed new value for the sample
							// 
							// The proposed new value for the sample
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$37$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, var154));
							else
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$37$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, var154)))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, var154));
						}
					}
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "server" with its value "var82".
					// 
					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
					// the output of Sample task 92.
					// 
					// Substituted "index$sample48$6" with its value "var86".
					double var154 = current_metric_var[var82][var86];
					
					// Record the probability of sample task 164 generating output with current configuration.
					// 
					// Substituted "server" with its value "var82".
					// 
					// Substituted "cv$temp$65$var154" with its value "var154".
					// 
					// Constructing a random variable input for use later.
					if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, var154)) < cv$accumulatedConsumerProbabilities))
						// Substituted "server" with its value "var82".
						// 
						// Substituted "cv$temp$65$var154" with its value "var154".
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							// Substituted "server" with its value "var82".
							// 
							// Substituted "cv$temp$65$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, var154));
						else
							// Substituted "server" with its value "var82".
							// 
							// Substituted "cv$temp$65$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, var154)))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][0], cv$proposedValue, var154));
					}
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 164 with the current configuration.
					// 
					// Recorded the probability of reaching sample task 164 with the current configuration.
					// 
					// Recorded the probability of reaching sample task 164 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 164 with the current configuration.
					// 
					// Recorded the probability of reaching sample task 164 with the current configuration.
					// 
					// Recorded the probability of reaching sample task 164 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample48Value7 + cv$probabilitySample48Value7) + cv$probabilitySample48Value7)), 0.0);
					
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
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int timeStep$var140 = 1; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
				// Substituted "server" with its value "var82".
				if(metric_valid_g[sample$var120][var82][timeStep$var140]) {
					if(fixedFlag$sample61) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var86 == st[sample$var120][timeStep$var140])) {
							// Processing sample task 164 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var100 = st[sample$var120][timeStep$var140];
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(((0 <= var100) && (var100 < noStates))) {
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$20$var152" with its value "var152".
								// 
								// Constructing a random variable input for use later.
								// 
								// cv$temp$21$var154's comment
								// Variable declaration of cv$temp$21$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var82".
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, current_metric_var[var82][st[sample$var120][timeStep$var140]]);
								
								// Variable declaration of cv$temp$49$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var82".
								double cv$temp$49$var154 = current_metric_var[var82][st[sample$var120][timeStep$var140]];
								
								// Record the probability of sample task 164 generating output with current configuration.
								// 
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$48$var152" with its value "var152".
								// 
								// Constructing a random variable input for use later.
								if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, cv$temp$49$var154) < cv$accumulatedConsumerProbabilities))
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$48$var152" with its value "var152".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, cv$temp$49$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									// 
									// The proposed new value for the sample
									// 
									// The proposed new value for the sample
									// 
									// The proposed new value for the sample
									// 
									// The proposed new value for the sample
									// 
									// The proposed new value for the sample
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "server" with its value "var82".
										// 
										// Substituted "cv$temp$48$var152" with its value "var152".
										// 
										// Constructing a random variable input for use later.
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, cv$temp$49$var154);
									else
										// Substituted "server" with its value "var82".
										// 
										// Substituted "cv$temp$48$var152" with its value "var152".
										// 
										// Constructing a random variable input for use later.
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, cv$temp$49$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, cv$temp$49$var154));
								}
								
								// Variable declaration of cv$temp$77$var154 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var82".
								double cv$temp$77$var154 = current_metric_var[var82][st[sample$var120][timeStep$var140]];
								
								// Record the probability of sample task 164 generating output with current configuration.
								// 
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$76$var152" with its value "var152".
								// 
								// Constructing a random variable input for use later.
								if((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, cv$temp$77$var154) < cv$accumulatedConsumerProbabilities))
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$76$var152" with its value "var152".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, cv$temp$77$var154) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "server" with its value "var82".
										// 
										// Substituted "cv$temp$76$var152" with its value "var152".
										// 
										// Constructing a random variable input for use later.
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, cv$temp$77$var154);
									else
										// Substituted "server" with its value "var82".
										// 
										// Substituted "cv$temp$76$var152" with its value "var152".
										// 
										// Constructing a random variable input for use later.
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, cv$temp$77$var154))) + 1)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, cv$temp$77$var154));
								}
								
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Recorded the probability of reaching sample task 164 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = -2.0;
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
						// Substituted "sample$var32" with its value "sample$var120".
						// 
						// Substituted "index$sample61$18" with its value "var86".
						double cv$probabilitySample61Value19 = distribution$sample61[sample$var120][(timeStep$var140 - 1)][var86];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// This value is not used before it is set again, so removing the value declaration.
						// 
						// Processing sample task 164 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "server" with its value "var82".
						// 
						// Substituted "cv$temp$27$var154" with its value "var154".
						// 
						// Constructing a random variable input for use later.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var82".
						// 
						// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
						// the output of Sample task 92.
						// 
						// Substituted "index$sample61$18" with its value "var86".
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, current_metric_var[var82][var86]));
						{
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var82".
							// 
							// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
							// the output of Sample task 92.
							// 
							// Substituted "index$sample61$18" with its value "var86".
							double var154 = current_metric_var[var82][var86];
							
							// Record the probability of sample task 164 generating output with current configuration.
							// 
							// Substituted "server" with its value "var82".
							// 
							// Substituted "cv$temp$55$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, var154)) < cv$accumulatedConsumerProbabilities))
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$55$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								// 
								// The proposed new value for the sample
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$55$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, var154));
								else
									// Substituted "server" with its value "var82".
									// 
									// Substituted "cv$temp$55$var154" with its value "var154".
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, var154)))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, var154));
							}
						}
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var82".
						// 
						// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
						// the output of Sample task 92.
						// 
						// Substituted "index$sample61$18" with its value "var86".
						double var154 = current_metric_var[var82][var86];
						
						// Record the probability of sample task 164 generating output with current configuration.
						// 
						// Substituted "server" with its value "var82".
						// 
						// Substituted "cv$temp$83$var154" with its value "var154".
						// 
						// Constructing a random variable input for use later.
						if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, var154)) < cv$accumulatedConsumerProbabilities))
							// Substituted "server" with its value "var82".
							// 
							// Substituted "cv$temp$83$var154" with its value "var154".
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$83$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, var154));
							else
								// Substituted "server" with its value "var82".
								// 
								// Substituted "cv$temp$83$var154" with its value "var154".
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, var154)))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][var82][timeStep$var140], cv$proposedValue, var154));
						}
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// This value is not used before it is set again, so removing the value declaration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Recorded the probability of reaching sample task 164 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample61Value19 + cv$probabilitySample61Value19) + cv$probabilitySample61Value19)), 0.0);
						
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$, 0.0, 1.0))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			current_metric_mean[var82][var86] = cv$originalValue;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var21$countGlobal
		// 
		// Allocation of cv$var21$countGlobal for single threaded execution
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		cv$var21$countGlobal = new double[Math.max(0, noStates)];
		
		// Constructor for cv$var27$countGlobal
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		int cv$max = 0;
		if((0 < noStates))
			cv$max = noStates;
		
		// Allocation of cv$var27$countGlobal for single threaded execution
		cv$var27$countGlobal = new double[cv$max];
		
		// Constructor for cv$distributionAccumulator$var56
		// 
		// Allocation of cv$distributionAccumulator$var56 for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 59. Initially set to the value
		// of putTask 31.
		cv$distributionAccumulator$var56 = new double[noStates];
		
		// Constructor for cv$var44$stateProbabilityGlobal
		// 
		// Allocation of cv$var44$stateProbabilityGlobal for single threaded execution
		cv$var44$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for guard$sample48gaussian163$global
		{
			// Calculate the largest index of server that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_server = 0;
			
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var140 = 0;
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				if((0 < length$metric[0].length))
					cv$max_timeStep$var140 = Math.max(cv$max_timeStep$var140, length$metric[sample$var120][0]);
				cv$max_server = Math.max(cv$max_server, length$metric[0].length);
			}
			
			// Allocation of guard$sample48gaussian163$global for single threaded execution
			guard$sample48gaussian163$global = new boolean[length$metric.length][cv$max_server][cv$max_timeStep$var140];
		}
		
		// Allocation of cv$var57$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 59. Initially set to the value
		// of putTask 31.
		cv$var57$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for guard$sample61gaussian163$global
		// 
		// Calculate the largest index of server that is possible and allocate an array to
		// hold the guard for each of these.
		int cv$max_server = 0;
		
		// Calculate the largest index of timeStep that is possible and allocate an array
		// to hold the guard for each of these.
		int cv$max_timeStep$var140 = 0;
		for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
			if((0 < length$metric[0].length))
				cv$max_timeStep$var140 = Math.max(cv$max_timeStep$var140, length$metric[sample$var120][0]);
			cv$max_server = Math.max(cv$max_server, length$metric[0].length);
		}
		
		// Allocation of guard$sample61gaussian163$global for single threaded execution
		guard$sample61gaussian163$global = new boolean[length$metric.length][cv$max_server][cv$max_timeStep$var140];
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
			for(int var26 = 0; var26 < noStates; var26 += 1)
				m[var26] = new double[noStates];
		}
		
		// If st has not been set already allocate space.
		if(!setFlag$st) {
			// Constructor for st
			st = new int[length$metric.length][];
			for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
				st[sample$var32] = new int[length$metric[sample$var32][0]];
		}
		
		// If metric_g has not been set already allocate space.
		if(!setFlag$metric_g) {
			// Constructor for metric_g
			metric_g = new double[length$metric.length][][];
			for(int var67 = 0; var67 < length$metric.length; var67 += 1)
				metric_g[var67] = new double[length$metric[0].length][];
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					metric_g[sample$var120][server] = new double[length$metric[sample$var120][0]];
			}
		}
		
		// If metric_valid_g has not been set already allocate space.
		if(!setFlag$metric_valid_g) {
			// Constructor for metric_valid_g
			metric_valid_g = new boolean[length$metric.length][][];
			for(int var73 = 0; var73 < length$metric.length; var73 += 1)
				metric_valid_g[var73] = new boolean[length$metric[0].length][];
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					metric_valid_g[sample$var120][server] = new boolean[length$metric[sample$var120][0]];
			}
		}
		
		// If current_metric_mean has not been set already allocate space.
		if(!setFlag$current_metric_mean) {
			// Constructor for current_metric_mean
			current_metric_mean = new double[length$metric[0].length][];
			for(int var82 = 0; var82 < length$metric[0].length; var82 += 1)
				current_metric_mean[var82] = new double[noStates];
		}
		
		// If current_metric_var has not been set already allocate space.
		if(!setFlag$current_metric_var) {
			// Constructor for current_metric_var
			current_metric_var = new double[length$metric[0].length][];
			for(int var96 = 0; var96 < length$metric[0].length; var96 += 1)
				current_metric_var[var96] = new double[noStates];
		}
		
		// If current_metric_valid_bias has not been set already allocate space.
		if(!setFlag$current_metric_valid_bias) {
			// Constructor for current_metric_valid_bias
			current_metric_valid_bias = new double[length$metric[0].length][];
			for(int var110 = 0; var110 < length$metric[0].length; var110 += 1)
				current_metric_valid_bias[var110] = new double[noStates];
		}
		
		// Constructor for distribution$sample61
		distribution$sample61 = new double[length$metric.length][][];
		for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1) {
			double[][] subarray$0 = new double[(length$metric[sample$var32][0] - 1)][];
			distribution$sample61[sample$var32] = subarray$0;
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
				subarray$0[(timeStep$var49 - 1)] = new double[noStates];
		}
		
		// Constructor for distribution$sample48
		distribution$sample48 = new double[length$metric.length][];
		for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
			distribution$sample48[sample$var32] = new double[noStates];
		
		// Constructor for logProbability$var43
		logProbability$var43 = new double[length$metric.length];
		
		// Constructor for logProbability$sample48
		logProbability$sample48 = new double[length$metric.length];
		
		// Constructor for logProbability$var56
		logProbability$var56 = new double[length$metric.length][];
		for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
			logProbability$var56[sample$var32] = new double[(length$metric[sample$var32][0] - 1)];
		
		// Constructor for logProbability$sample61
		logProbability$sample61 = new double[length$metric.length][];
		for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
			logProbability$sample61[sample$var32] = new double[(length$metric[sample$var32][0] - 1)];
		
		// Constructor for logProbability$var145
		logProbability$var145 = new double[length$metric.length][][];
		for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$var145[sample$var120] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var120][0]];
		}
		
		// Constructor for logProbability$sample152
		logProbability$sample152 = new double[length$metric.length][][];
		for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample152[sample$var120] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var120][0]];
		}
		
		// Constructor for logProbability$var155
		logProbability$var155 = new double[length$metric.length][][];
		for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$var155[sample$var120] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var120][0]];
		}
		
		// Constructor for logProbability$sample164
		logProbability$sample164 = new double[length$metric.length][][];
		for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample164[sample$var120] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var120][0]];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample30) {
			for(int var26 = 0; var26 < noStates; var26 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var26]);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			if(!fixedFlag$sample48)
				st[sample$var32][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample61) {
				int[] var50 = st[sample$var32];
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
					var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var32][(timeStep$var49 - 1)]]);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample92) {
			for(int var82 = 0; var82 < noServers; var82 += 1) {
				double[] var83 = current_metric_mean[var82];
				for(int var86 = 0; var86 < noStates; var86 += 1)
					var83[var86] = DistributionSampling.sampleUniform(RNG$, 0.0, max_metric);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample106) {
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				double[] var97 = current_metric_var[var96];
				for(int var100 = 0; var100 < noStates; var100 += 1)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample120) {
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				double[] var111 = current_metric_valid_bias[var110];
				for(int var114 = 0; var114 < noStates; var114 += 1)
					var111[var114] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				double[] metric_inner = metric_g[sample$var120][server];
				for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
					if(!fixedFlag$sample152)
						metric_valid_g[sample$var120][server][timeStep$var140] = DistributionSampling.sampleBernoulli(RNG$, current_metric_valid_bias[server][st[sample$var120][timeStep$var140]]);
					if((metric_valid_g[sample$var120][server][timeStep$var140] && !fixedFlag$sample164))
						metric_inner[timeStep$var140] = DistributionSampling.sampleGaussian(RNG$, current_metric_mean[server][st[sample$var120][timeStep$var140]], current_metric_var[server][st[sample$var120][timeStep$var140]]);
				}
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample30) {
			for(int var26 = 0; var26 < noStates; var26 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var26]);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample48) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample48 = distribution$sample48[sample$var32];
				for(int index$var43 = 0; index$var43 < noStates; index$var43 += 1)
					// Save the probability of each value
					// 
					// Probability for this value
					cv$distribution$sample48[index$var43] = DistributionSampling.probabilityCategorical(index$var43, initialStateDistribution);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample61) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					// Create local copy of variable probabilities.
					double[] cv$distribution$sample61 = distribution$sample61[sample$var32][(timeStep$var49 - 1)];
					for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
						// Zero the probability of each value
						cv$distribution$sample61[index$var56] = 0.0;
					
					// Iterate through possible values for var56's arguments.
					// 
					// Enumerating the possible arguments for Categorical 56.
					if((1 == timeStep$var49)) {
						// Iterate through possible values for var56's arguments.
						// 
						// Enumerating the possible arguments for Categorical 56.
						if(fixedFlag$sample48) {
							int var26 = st[sample$var32][0];
							
							// Substituted "timeStep$var49" with its value "1".
							if(((0 <= var26) && (var26 < noStates))) {
								// Substituted "timeStep$var49" with its value "1".
								double[] var55 = m[st[sample$var32][0]];
								for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
									// Save the probability of each value
									cv$distribution$sample61[index$var56] = (cv$distribution$sample61[index$var56] + DistributionSampling.probabilityCategorical(index$var56, var55));
							}
						} else {
							// Enumerating the possible outputs of Categorical 43.
							for(int index$sample48$3 = 0; index$sample48$3 < noStates; index$sample48$3 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$2" with its value "sample$var32".
								double cv$probabilitySample48Value4 = distribution$sample48[sample$var32][index$sample48$3];
								double[] var55 = m[index$sample48$3];
								for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
									// Save the probability of each value
									cv$distribution$sample61[index$var56] = (cv$distribution$sample61[index$var56] + (cv$probabilitySample48Value4 * DistributionSampling.probabilityCategorical(index$var56, var55)));
							}
						}
					}
					int index$timeStep$11 = (timeStep$var49 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$sample$10" with its value "sample$var32".
					if((1 <= index$timeStep$11)) {
						// Enumerating the possible outputs of Categorical 56.
						for(int index$sample61$12 = 0; index$sample61$12 < noStates; index$sample61$12 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$10" with its value "sample$var32".
							double cv$probabilitySample61Value13 = distribution$sample61[sample$var32][(index$timeStep$11 - 1)][index$sample61$12];
							double[] var55 = m[index$sample61$12];
							for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
								// Save the probability of each value
								cv$distribution$sample61[index$var56] = (cv$distribution$sample61[index$var56] + (cv$probabilitySample61Value13 * DistributionSampling.probabilityCategorical(index$var56, var55)));
						}
					}
					
					// Sum the values in the array
					double cv$var56$sum = 0.0;
					for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
						// sum the probability of each value
						cv$var56$sum = (cv$var56$sum + cv$distribution$sample61[index$var56]);
					for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
						// Normalise the probability of each value
						cv$distribution$sample61[index$var56] = (cv$distribution$sample61[index$var56] / cv$var56$sum);
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample92) {
			for(int var82 = 0; var82 < noServers; var82 += 1) {
				double[] var83 = current_metric_mean[var82];
				for(int var86 = 0; var86 < noStates; var86 += 1)
					var83[var86] = DistributionSampling.sampleUniform(RNG$, 0.0, max_metric);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample106) {
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				double[] var97 = current_metric_var[var96];
				for(int var100 = 0; var100 < noStates; var100 += 1)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample120) {
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				double[] var111 = current_metric_valid_bias[var110];
				for(int var114 = 0; var114 < noStates; var114 += 1)
					var111[var114] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample30) {
			for(int var26 = 0; var26 < noStates; var26 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var26]);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			if(!fixedFlag$sample48)
				st[sample$var32][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample61) {
				int[] var50 = st[sample$var32];
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
					var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var32][(timeStep$var49 - 1)]]);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample92) {
			for(int var82 = 0; var82 < noServers; var82 += 1) {
				double[] var83 = current_metric_mean[var82];
				for(int var86 = 0; var86 < noStates; var86 += 1)
					var83[var86] = DistributionSampling.sampleUniform(RNG$, 0.0, max_metric);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample106) {
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				double[] var97 = current_metric_var[var96];
				for(int var100 = 0; var100 < noStates; var100 += 1)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample120) {
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				double[] var111 = current_metric_valid_bias[var110];
				for(int var114 = 0; var114 < noStates; var114 += 1)
					var111[var114] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample24)
				sample24();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample30) {
				for(int var26 = 0; var26 < noStates; var26 += 1)
					sample30(var26);
			}
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				if(!fixedFlag$sample48)
					sample48(sample$var32);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample61) {
					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
						sample61(sample$var32, timeStep$var49);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample92) {
				for(int var82 = 0; var82 < noServers; var82 += 1) {
					for(int var86 = 0; var86 < noStates; var86 += 1)
						sample92(var82, var86);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample106) {
				for(int var96 = 0; var96 < noServers; var96 += 1) {
					for(int var100 = 0; var100 < noStates; var100 += 1)
						sample106(var96, var100);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample120) {
				for(int var110 = 0; var110 < noServers; var110 += 1) {
					for(int var114 = 0; var114 < noStates; var114 += 1)
						sample120(var110, var114);
				}
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample120) {
				for(int var110 = (noServers - 1); var110 >= 0; var110 -= 1) {
					for(int var114 = (noStates - 1); var114 >= 0; var114 -= 1)
						sample120(var110, var114);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample106) {
				for(int var96 = (noServers - 1); var96 >= 0; var96 -= 1) {
					for(int var100 = (noStates - 1); var100 >= 0; var100 -= 1)
						sample106(var96, var100);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample92) {
				for(int var82 = (noServers - 1); var82 >= 0; var82 -= 1) {
					for(int var86 = (noStates - 1); var86 >= 0; var86 -= 1)
						sample92(var82, var86);
				}
			}
			for(int sample$var32 = (noSamples - 1); sample$var32 >= 0; sample$var32 -= 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample61) {
					for(int timeStep$var49 = (length$metric[sample$var32][0] - 1); timeStep$var49 >= 1; timeStep$var49 -= 1)
						sample61(sample$var32, timeStep$var49);
				}
				if(!fixedFlag$sample48)
					sample48(sample$var32);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample30) {
				for(int var26 = (noStates - 1); var26 >= 0; var26 -= 1)
					sample30(var26);
			}
			if(!fixedFlag$sample24)
				sample24();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		noSamples = length$metric.length;
		for(int var17 = 0; var17 < noStates; var17 += 1)
			v[var17] = 0.1;
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
		logProbability$var20 = 0.0;
		if(!fixedProbFlag$sample24)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var22 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample30)
			logProbability$var27 = 0.0;
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1)
			logProbability$var43[sample$var32] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample48) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1)
				logProbability$sample48[sample$var32] = 0.0;
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
				logProbability$var56[sample$var32][(timeStep$var49 - 1)] = 0.0;
		}
		if(!fixedProbFlag$sample61) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
					logProbability$sample61[sample$var32][(timeStep$var49 - 1)] = 0.0;
			}
		}
		logProbability$var78 = 0.0;
		logProbability$current_metric_mean = 0.0;
		if(!fixedProbFlag$sample92)
			logProbability$var87 = 0.0;
		logProbability$var92 = 0.0;
		logProbability$current_metric_var = 0.0;
		if(!fixedProbFlag$sample106)
			logProbability$var101 = 0.0;
		logProbability$var106 = 0.0;
		logProbability$current_metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample120)
			logProbability$var115 = 0.0;
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
					logProbability$var145[sample$var120][server][timeStep$var140] = 0.0;
			}
		}
		logProbability$metric_valid_inner = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample152) {
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
						logProbability$sample152[sample$var120][server][timeStep$var140] = 0.0;
				}
			}
		}
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
					logProbability$var155[sample$var120][server][timeStep$var140] = 0.0;
			}
		}
		logProbability$var131 = 0.0;
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample164) {
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
						logProbability$sample164[sample$var120][server][timeStep$var140] = 0.0;
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
		if(fixedFlag$sample24)
			logProbabilityValue$sample24();
		if(fixedFlag$sample30)
			logProbabilityValue$sample30();
		if(fixedFlag$sample92)
			logProbabilityValue$sample92();
		if(fixedFlag$sample106)
			logProbabilityValue$sample106();
		if(fixedFlag$sample120)
			logProbabilityValue$sample120();
		logProbabilityValue$sample152();
		logProbabilityValue$sample164();
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
		logProbabilityValue$sample24();
		logProbabilityValue$sample30();
		logProbabilityDistribution$sample48();
		logProbabilityDistribution$sample61();
		logProbabilityValue$sample92();
		logProbabilityValue$sample106();
		logProbabilityValue$sample120();
		logProbabilityDistribution$sample152();
		logProbabilityDistribution$sample164();
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
		logProbabilityValue$sample24();
		logProbabilityValue$sample30();
		logProbabilityValue$sample48();
		logProbabilityValue$sample61();
		logProbabilityValue$sample92();
		logProbabilityValue$sample106();
		logProbabilityValue$sample120();
		logProbabilityValue$sample152();
		logProbabilityValue$sample164();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample30) {
			for(int var26 = 0; var26 < noStates; var26 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var26]);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			if(!fixedFlag$sample48)
				st[sample$var32][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample61) {
				int[] var50 = st[sample$var32];
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
					var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var32][(timeStep$var49 - 1)]]);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample92) {
			for(int var82 = 0; var82 < noServers; var82 += 1) {
				double[] var83 = current_metric_mean[var82];
				for(int var86 = 0; var86 < noStates; var86 += 1)
					var83[var86] = DistributionSampling.sampleUniform(RNG$, 0.0, max_metric);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample106) {
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				double[] var97 = current_metric_var[var96];
				for(int var100 = 0; var100 < noStates; var100 += 1)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample120) {
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				double[] var111 = current_metric_valid_bias[var110];
				for(int var114 = 0; var114 < noStates; var114 += 1)
					var111[var114] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMMetrics4(\n               double[][][] metric,\n               boolean[][][] metric_valid, \n               int max_metric,\n               int noStates) {\n    \n    int noSamples = metric.length;\n\n    // Construct arrays describing the probability of a move from 1 state to another.\n    double[] v = new double[noStates] <~ 0.1;\n    double[] initialStateDistribution = dirichlet(v).sample();\n    double[][] m = dirichlet(v).sample(noStates);\n    \n    //Calculate all the state transitions\n    int[][] st = new int[noSamples][];\n    for(int sample = 0; sample < noSamples; sample++) {\n        int streamLength = metric[sample][0].length;\n        \n        // Allocate space for the state.\n        st[sample] = new int[streamLength];\n        \n        // Set the initial state by sampling from a categorical with learnt weightings.\n        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n        \n        // Calculate the remaining weightings\n        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n    }\n    \n    // Calculate the number of servers\n    int noServers = metric[0].length;    \n    \n    // Allocate space for each generated metric.    \n    double[][][] metric_g = new double[noSamples][noServers][];\n    boolean[][][] metric_valid_g = new boolean[noSamples][noServers][];\n\n    // Calculate metric parameters\n    double[][] current_metric_mean = uniform(0.0, (double) max_metric).sample(noServers, noStates);\n    double[][] current_metric_var = inverseGamma(1.0, 1.0).sample(noServers, noStates);\n    double[][] current_metric_valid_bias = beta(1.0, 1.0).sample(noServers, noStates);\n    \n    // Compute the values of each metric\n    for(int sample = 0; sample < noSamples; sample++) {\n        int streamLength = metric[sample][0].length;\n        for(int server = 0; server < noServers; server++) {\n            //Allocate space for the time series\n            double[] metric_inner = new double[streamLength];\n            metric_g[sample][server] = metric_inner;\n            \n            boolean[] metric_valid_inner = new boolean[streamLength];\n            metric_valid_g[sample][server] = metric_valid_inner;\n            \n            //Generate values.\n            for(int timeStep = 0; timeStep < streamLength; timeStep++){\n                int currentState = st[sample][timeStep];\n                \n                metric_valid_inner[timeStep] = bernoulli(current_metric_valid_bias[server][currentState]).sample();\n                if(metric_valid_inner[timeStep])\n                    metric_inner[timeStep] = gaussian(current_metric_mean[server][currentState], current_metric_var[server][currentState]).sample();\n            }\n        }\n    }\n\n    //Tie the values to the measured values.\n    metric_valid_g.observe(metric_valid);\n    metric_g.observe(metric);\n}";
	}
}