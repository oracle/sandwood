package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMMetrics2$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] cv$distributionAccumulator$var81;
	private double[] cv$var20$countGlobal;
	private double[][] cv$var26$countGlobal;
	private double[][] cv$var69$stateProbabilityGlobal;
	private double[][] cv$var82$stateProbabilityGlobal;
	private double[][] distribution$sample75;
	private double[][][] distribution$sample88;
	private boolean fixedFlag$sample104 = false;
	private boolean fixedFlag$sample113 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample42 = false;
	private boolean fixedFlag$sample51 = false;
	private boolean fixedFlag$sample60 = false;
	private boolean fixedFlag$sample75 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedProbFlag$sample104 = false;
	private boolean fixedProbFlag$sample113 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample42 = false;
	private boolean fixedProbFlag$sample51 = false;
	private boolean fixedProbFlag$sample60 = false;
	private boolean fixedProbFlag$sample75 = false;
	private boolean fixedProbFlag$sample88 = false;
	private boolean[][][] guard$sample75gaussian112$global;
	private boolean[][][] guard$sample88gaussian112$global;
	private double[] initialStateDistribution;
	private int[] length$metric;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$initialStateDistribution;
	private double logProbability$m;
	private double logProbability$metric_1d;
	private double logProbability$metric_g;
	private double logProbability$metric_mean;
	private double logProbability$metric_valid_1d;
	private double logProbability$metric_valid_bias;
	private double logProbability$metric_valid_g;
	private double logProbability$metric_var;
	private double[][] logProbability$sample104;
	private double[][] logProbability$sample113;
	private double[] logProbability$sample75;
	private double[][] logProbability$sample88;
	private double logProbability$st;
	private double[][] logProbability$var102;
	private double logProbability$var19;
	private double logProbability$var21;
	private double logProbability$var26;
	private double logProbability$var33;
	private double logProbability$var38;
	private double logProbability$var42;
	private double logProbability$var47;
	private double logProbability$var51;
	private double logProbability$var56;
	private double[] logProbability$var68;
	private double[][] logProbability$var81;
	private double[][] logProbability$var95;
	private double[][] m;
	private double[][] metric;
	private double[][] metric_g;
	private double[] metric_mean;
	private boolean[][] metric_valid;
	private double[] metric_valid_bias;
	private boolean[][] metric_valid_g;
	private double[] metric_var;
	private int noSamples;
	private int noStates;
	private boolean setFlag$initialStateDistribution = false;
	private boolean setFlag$m = false;
	private boolean setFlag$metric_g = false;
	private boolean setFlag$metric_mean = false;
	private boolean setFlag$metric_valid_bias = false;
	private boolean setFlag$metric_valid_g = false;
	private boolean setFlag$metric_var = false;
	private boolean setFlag$st = false;
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMMetrics2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for fixedFlag$sample104.
	@Override
	public final boolean get$fixedFlag$sample104() {
		return fixedFlag$sample104;
	}

	// Setter for fixedFlag$sample104.
	@Override
	public final void set$fixedFlag$sample104(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample104 including if probabilities
		// need to be updated.
		fixedFlag$sample104 = cv$value;
		
		// Should the probability of sample 104 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample104" with its value "cv$value".
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
	}

	// Getter for fixedFlag$sample113.
	@Override
	public final boolean get$fixedFlag$sample113() {
		return fixedFlag$sample113;
	}

	// Setter for fixedFlag$sample113.
	@Override
	public final void set$fixedFlag$sample113(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample113 including if probabilities
		// need to be updated.
		fixedFlag$sample113 = cv$value;
		
		// Should the probability of sample 113 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample113" with its value "cv$value".
		fixedProbFlag$sample113 = (cv$value && fixedProbFlag$sample113);
	}

	// Getter for fixedFlag$sample23.
	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	// Setter for fixedFlag$sample23.
	@Override
	public final void set$fixedFlag$sample23(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample23 including if probabilities
		// need to be updated.
		fixedFlag$sample23 = cv$value;
		
		// Should the probability of sample 23 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample23" with its value "cv$value".
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample23" with its value "cv$value".
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
	}

	// Getter for fixedFlag$sample29.
	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	// Setter for fixedFlag$sample29.
	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample29 including if probabilities
		// need to be updated.
		fixedFlag$sample29 = cv$value;
		
		// Should the probability of sample 29 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample29" with its value "cv$value".
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
		
		// Should the probability of sample 88 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample29" with its value "cv$value".
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
	}

	// Getter for fixedFlag$sample42.
	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	// Setter for fixedFlag$sample42.
	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample42 including if probabilities
		// need to be updated.
		fixedFlag$sample42 = cv$value;
		
		// Should the probability of sample 42 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample42" with its value "cv$value".
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
		
		// Should the probability of sample 113 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample42" with its value "cv$value".
		fixedProbFlag$sample113 = (cv$value && fixedProbFlag$sample113);
	}

	// Getter for fixedFlag$sample51.
	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	// Setter for fixedFlag$sample51.
	@Override
	public final void set$fixedFlag$sample51(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample51 including if probabilities
		// need to be updated.
		fixedFlag$sample51 = cv$value;
		
		// Should the probability of sample 51 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample51" with its value "cv$value".
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
		
		// Should the probability of sample 113 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample51" with its value "cv$value".
		fixedProbFlag$sample113 = (cv$value && fixedProbFlag$sample113);
	}

	// Getter for fixedFlag$sample60.
	@Override
	public final boolean get$fixedFlag$sample60() {
		return fixedFlag$sample60;
	}

	// Setter for fixedFlag$sample60.
	@Override
	public final void set$fixedFlag$sample60(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample60 including if probabilities
		// need to be updated.
		fixedFlag$sample60 = cv$value;
		
		// Should the probability of sample 60 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample60" with its value "cv$value".
		fixedProbFlag$sample60 = (cv$value && fixedProbFlag$sample60);
		
		// Should the probability of sample 104 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample60" with its value "cv$value".
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
	}

	// Getter for fixedFlag$sample75.
	@Override
	public final boolean get$fixedFlag$sample75() {
		return fixedFlag$sample75;
	}

	// Setter for fixedFlag$sample75.
	@Override
	public final void set$fixedFlag$sample75(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample75 including if probabilities
		// need to be updated.
		fixedFlag$sample75 = cv$value;
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample75" with its value "cv$value".
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
		
		// Should the probability of sample 88 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample75" with its value "cv$value".
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
		
		// Should the probability of sample 104 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample75" with its value "cv$value".
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
		
		// Should the probability of sample 113 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample75" with its value "cv$value".
		fixedProbFlag$sample113 = (cv$value && fixedProbFlag$sample113);
	}

	// Getter for fixedFlag$sample88.
	@Override
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	// Setter for fixedFlag$sample88.
	@Override
	public final void set$fixedFlag$sample88(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample88 including if probabilities
		// need to be updated.
		fixedFlag$sample88 = cv$value;
		
		// Should the probability of sample 88 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample88" with its value "cv$value".
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
		
		// Should the probability of sample 104 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample88" with its value "cv$value".
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
		
		// Should the probability of sample 113 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample88" with its value "cv$value".
		fixedProbFlag$sample113 = (cv$value && fixedProbFlag$sample113);
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
	public final int[] get$length$metric() {
		return length$metric;
	}

	// Setter for length$metric.
	@Override
	public final void set$length$metric(int[] cv$value) {
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

	// Getter for logProbability$metric_mean.
	@Override
	public final double get$logProbability$metric_mean() {
		return logProbability$metric_mean;
	}

	// Getter for logProbability$metric_valid_bias.
	@Override
	public final double get$logProbability$metric_valid_bias() {
		return logProbability$metric_valid_bias;
	}

	// Getter for logProbability$metric_valid_g.
	@Override
	public final double get$logProbability$metric_valid_g() {
		return logProbability$metric_valid_g;
	}

	// Getter for logProbability$metric_var.
	@Override
	public final double get$logProbability$metric_var() {
		return logProbability$metric_var;
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

	// Getter for metric.
	@Override
	public final double[][] get$metric() {
		return metric;
	}

	// Setter for metric.
	@Override
	public final void set$metric(double[][] cv$value) {
		// Set metric with flag to mark that it has been set so another array doesn't need
		// to be constructed
		metric = cv$value;
	}

	// Getter for metric_g.
	@Override
	public final double[][] get$metric_g() {
		return metric_g;
	}

	// Setter for metric_g.
	@Override
	public final void set$metric_g(double[][] cv$value) {
		// Set metric_g with flag to mark that it has been set so another array doesn't need
		// to be constructed
		metric_g = cv$value;
		setFlag$metric_g = true;
	}

	// Getter for metric_mean.
	@Override
	public final double[] get$metric_mean() {
		return metric_mean;
	}

	// Setter for metric_mean.
	@Override
	public final void set$metric_mean(double[] cv$value) {
		// Set metric_mean with flag to mark that it has been set so another array doesn't
		// need to be constructed
		metric_mean = cv$value;
		setFlag$metric_mean = true;
	}

	// Getter for metric_valid.
	@Override
	public final boolean[][] get$metric_valid() {
		return metric_valid;
	}

	// Setter for metric_valid.
	@Override
	public final void set$metric_valid(boolean[][] cv$value) {
		// Set metric_valid with flag to mark that it has been set so another array doesn't
		// need to be constructed
		metric_valid = cv$value;
	}

	// Getter for metric_valid_bias.
	@Override
	public final double[] get$metric_valid_bias() {
		return metric_valid_bias;
	}

	// Setter for metric_valid_bias.
	@Override
	public final void set$metric_valid_bias(double[] cv$value) {
		// Set metric_valid_bias with flag to mark that it has been set so another array doesn't
		// need to be constructed
		metric_valid_bias = cv$value;
		setFlag$metric_valid_bias = true;
	}

	// Getter for metric_valid_g.
	@Override
	public final boolean[][] get$metric_valid_g() {
		return metric_valid_g;
	}

	// Setter for metric_valid_g.
	@Override
	public final void set$metric_valid_g(boolean[][] cv$value) {
		// Set metric_valid_g with flag to mark that it has been set so another array doesn't
		// need to be constructed
		metric_valid_g = cv$value;
		setFlag$metric_valid_g = true;
	}

	// Getter for metric_var.
	@Override
	public final double[] get$metric_var() {
		return metric_var;
	}

	// Setter for metric_var.
	@Override
	public final void set$metric_var(double[] cv$value) {
		// Set metric_var with flag to mark that it has been set so another array doesn't
		// need to be constructed
		metric_var = cv$value;
		setFlag$metric_var = true;
	}

	// Getter for noSamples.
	@Override
	public final int get$noSamples() {
		return noSamples;
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

	// Calculate the probability of the samples represented by sample104 using probability
	// distributions.
	private final void logProbabilityDistribution$sample104() {
		// Determine if we need to calculate the values for sample task 104 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample104) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Look for paths between the variable and the sample task 104 including any distribution
					// values.
					// 
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = metric_valid_g[sample][timeStep$var91];
					
					// Enumerating the possible arguments for Bernoulli 95.
					if((0 == timeStep$var91)) {
						// Enumerating the possible arguments for Bernoulli 95.
						if(fixedFlag$sample75) {
							int var55 = st[sample][0];
							
							// Substituted "timeStep$var91" with its value "0".
							if(((0 <= var55) && (var55 < noStates))) {
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "timeStep$var91" with its value "0".
								cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[st[sample][0]]);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						} else {
							// Enumerating the possible outputs of Categorical 68.
							for(int index$sample75$4 = 0; index$sample75$4 < noStates; index$sample75$4 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$3" with its value "sample".
								double cv$probabilitySample75Value5 = distribution$sample75[sample][index$sample75$4];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample75Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[index$sample75$4]));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value5);
							}
						}
					}
					
					// Enumerating the possible arguments for Bernoulli 95.
					if((1 <= timeStep$var91)) {
						// Enumerating the possible arguments for Bernoulli 95.
						if(fixedFlag$sample88) {
							int var55 = st[sample][timeStep$var91];
							if(((0 <= var55) && (var55 < noStates))) {
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[st[sample][timeStep$var91]]);
								
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
							// Enumerating the possible outputs of Categorical 81.
							for(int index$sample88$13 = 0; index$sample88$13 < noStates; index$sample88$13 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$11" with its value "sample".
								double cv$probabilitySample88Value14 = distribution$sample88[sample][(timeStep$var91 - 1)][index$sample88$13];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample88Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[index$sample88$13]));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample88Value14);
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
					logProbability$var95[sample][timeStep$var91] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample104[sample][timeStep$var91] = cv$distributionAccumulator;
					
					// Update the variable probability
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
				}
			}
			
			// Update the variable probability
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample104 = (((fixedFlag$sample104 && fixedFlag$sample60) && fixedFlag$sample75) && fixedFlag$sample88);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					double cv$sampleValue = logProbability$sample104[sample][timeStep$var91];
					cv$accumulator = (cv$accumulator + cv$sampleValue);
					logProbability$var95[sample][timeStep$var91] = cv$sampleValue;
					
					// Update the variable probability
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
				}
			}
			
			// Update the variable probability
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample113 using probability
	// distributions.
	private final void logProbabilityDistribution$sample113() {
		// Determine if we need to calculate the values for sample task 113 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample113) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					if(metric_valid_g[sample][timeStep$var91]) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 113 including any distribution
						// values.
						// 
						// The sample value to calculate the probability of generating
						double cv$sampleValue = metric_g[sample][timeStep$var91];
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(((0 == timeStep$var91) && metric_valid_g[sample][0])) {
							// Enumerating the possible arguments for Gaussian 102.
							// 
							// Enumerating the possible arguments for Gaussian 102.
							if(fixedFlag$sample75) {
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if((0 <= st[sample][0])) {
									int var37 = st[sample][0];
									
									// Substituted "timeStep$var91" with its value "0".
									if(((0 <= var37) && (var37 < noStates))) {
										// Substituted "timeStep$var91" with its value "0".
										double var101 = metric_var[st[sample][0]];
										
										// Store the value of the function call, so the function call is only made once.
										// 
										// Substituted "timeStep$var91" with its value "0".
										cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[st[sample][0]]) / Math.sqrt(var101))) - (Math.log(var101) * 0.5));
										
										// Add the probability of this distribution configuration to the accumulator.
										// 
										// An accumulator for the distributed probability space covered.
										cv$probabilityReached = 1.0;
									}
								}
							} else {
								// Enumerating the possible outputs of Categorical 68.
								for(int index$sample75$4 = 0; index$sample75$4 < noStates; index$sample75$4 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$3" with its value "sample".
									double cv$probabilitySample75Value5 = distribution$sample75[sample][index$sample75$4];
									double var101 = metric_var[index$sample75$4];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = ((Math.log(cv$probabilitySample75Value5) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[index$sample75$4]) / Math.sqrt(var101)))) - (Math.log(var101) * 0.5));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value5);
								}
							}
						}
						
						// Enumerating the possible arguments for Gaussian 102.
						if((1 <= timeStep$var91)) {
							// Enumerating the possible arguments for Gaussian 102.
							if(fixedFlag$sample88) {
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if((0 <= st[sample][timeStep$var91])) {
									int var37 = st[sample][timeStep$var91];
									if(((0 <= var37) && (var37 < noStates))) {
										double var101 = metric_var[st[sample][timeStep$var91]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[st[sample][timeStep$var91]]) / Math.sqrt(var101))) - (Math.log(var101) * 0.5));
										
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
								// Enumerating the possible outputs of Categorical 81.
								for(int index$sample88$49 = 0; index$sample88$49 < noStates; index$sample88$49 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$47" with its value "sample".
									double cv$probabilitySample88Value50 = distribution$sample88[sample][(timeStep$var91 - 1)][index$sample88$49];
									double var101 = metric_var[index$sample88$49];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = ((Math.log(cv$probabilitySample88Value50) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[index$sample88$49]) / Math.sqrt(var101)))) - (Math.log(var101) * 0.5));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample88Value50);
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
						logProbability$var102[sample][timeStep$var91] = cv$distributionAccumulator;
						
						// Store the sample task probability
						logProbability$sample113[sample][timeStep$var91] = cv$distributionAccumulator;
						
						// Update the variable probability
						logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
					}
				}
			}
			
			// Update the variable probability
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample113 = ((((fixedFlag$sample113 && fixedFlag$sample42) && fixedFlag$sample51) && fixedFlag$sample75) && fixedFlag$sample88);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					if(metric_valid_g[sample][timeStep$var91]) {
						double cv$sampleValue = logProbability$sample113[sample][timeStep$var91];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var102[sample][timeStep$var91] = cv$sampleValue;
						
						// Update the variable probability
						logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
					}
				}
			}
			
			// Update the variable probability
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample75 using probability
	// distributions.
	private final void logProbabilityDistribution$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample75) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample75) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int sample = 0; sample < noSamples; sample += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[sample][0];
					
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
					logProbability$var68[sample] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample75[sample] = cv$distributionAccumulator;
				}
				
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample75" with its value "true".
				fixedProbFlag$sample75 = fixedFlag$sample23;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample75[sample];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var68[sample] = cv$rvAccumulator;
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample75)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample88 using probability
	// distributions.
	private final void logProbabilityDistribution$sample88() {
		// Determine if we need to calculate the values for sample task 88 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample88) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample88) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int sample = 0; sample < noSamples; sample += 1) {
					for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[sample][timeStep$var74];
						
						// Enumerating the possible arguments for Categorical 81.
						if((1 == timeStep$var74)) {
							// Enumerating the possible arguments for Categorical 81.
							if(fixedFlag$sample75) {
								int var25 = st[sample][0];
								
								// Substituted "timeStep$var74" with its value "1".
								if(((0 <= var25) && (var25 < noStates))) {
									// Substituted "timeStep$var74" with its value "1".
									double[] var80 = m[st[sample][0]];
									
									// Store the value of the function call, so the function call is only made once.
									cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									
									// Add the probability of this distribution configuration to the accumulator.
									// 
									// An accumulator for the distributed probability space covered.
									cv$probabilityReached = 1.0;
								}
							} else {
								// Enumerating the possible outputs of Categorical 68.
								for(int index$sample75$6 = 0; index$sample75$6 < noStates; index$sample75$6 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$5" with its value "sample".
									double cv$probabilitySample75Value7 = distribution$sample75[sample][index$sample75$6];
									double[] var80 = m[index$sample75$6];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample75Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value7);
								}
							}
						}
						
						// Substituted "index$sample$13_1" with its value "sample".
						// 
						// Substituted "index$timeStep$13_2" with its value "(timeStep$var74 - 1)".
						if((2 <= timeStep$var74)) {
							int var25 = st[sample][(timeStep$var74 - 1)];
							if(((0 <= var25) && (var25 < noStates))) {
								double[] var80 = m[st[sample][(timeStep$var74 - 1)]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								
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
						logProbability$var81[sample][(timeStep$var74 - 1)] = cv$distributionAccumulator;
						
						// Store the sample task probability
						logProbability$sample88[sample][(timeStep$var74 - 1)] = cv$distributionAccumulator;
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
				// Substituted "fixedFlag$sample88" with its value "true".
				fixedProbFlag$sample88 = (fixedFlag$sample29 && fixedFlag$sample75);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
					// Variable declaration of cv$rvAccumulator moved.
					double cv$rvAccumulator = logProbability$sample88[sample][(timeStep$var74 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var81[sample][(timeStep$var74 - 1)] = cv$rvAccumulator;
				}
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample88)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample104 using sampled
	// values.
	private final void logProbabilityValue$sample104() {
		// Determine if we need to calculate the values for sample task 104 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample104) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
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
					double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][timeStep$var91], metric_valid_bias[st[sample][timeStep$var91]]);
					
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
					logProbability$var95[sample][timeStep$var91] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample104[sample][timeStep$var91] = cv$distributionAccumulator;
					
					// Update the variable probability
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
				}
			}
			
			// Update the variable probability
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample104 = (((fixedFlag$sample104 && fixedFlag$sample60) && fixedFlag$sample75) && fixedFlag$sample88);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					double cv$sampleValue = logProbability$sample104[sample][timeStep$var91];
					cv$accumulator = (cv$accumulator + cv$sampleValue);
					logProbability$var95[sample][timeStep$var91] = cv$sampleValue;
					
					// Update the variable probability
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
				}
			}
			
			// Update the variable probability
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample113 using sampled
	// values.
	private final void logProbabilityValue$sample113() {
		// Determine if we need to calculate the values for sample task 113 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample113) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					if(metric_valid_g[sample][timeStep$var91]) {
						double var101 = metric_var[st[sample][timeStep$var91]];
						
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
						double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - metric_mean[st[sample][timeStep$var91]]) / Math.sqrt(var101))) - (Math.log(var101) * 0.5));
						
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
						logProbability$var102[sample][timeStep$var91] = cv$distributionAccumulator;
						
						// Store the sample task probability
						logProbability$sample113[sample][timeStep$var91] = cv$distributionAccumulator;
						
						// Update the variable probability
						logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
					}
				}
			}
			
			// Update the variable probability
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample113 = ((((fixedFlag$sample113 && fixedFlag$sample42) && fixedFlag$sample51) && fixedFlag$sample75) && fixedFlag$sample88);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					if(metric_valid_g[sample][timeStep$var91]) {
						double cv$sampleValue = logProbability$sample113[sample][timeStep$var91];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var102[sample][timeStep$var91] = cv$sampleValue;
						
						// Update the variable probability
						logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
					}
				}
			}
			
			// Update the variable probability
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample23 using sampled
	// values.
	private final void logProbabilityValue$sample23() {
		// Determine if we need to calculate the values for sample task 23 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample23) {
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
			logProbability$var19 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample23)
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
			fixedProbFlag$sample23 = fixedFlag$sample23;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var19 = logProbability$initialStateDistribution;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample23)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	// Calculate the probability of the samples represented by sample29 using sampled
	// values.
	private final void logProbabilityValue$sample29() {
		// Determine if we need to calculate the values for sample task 29 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample29) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var25 = 0; var25 < noStates; var25 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var25], v));
			logProbability$var21 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var26 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample29)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample29 = fixedFlag$sample29;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var21 = logProbability$var26;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var26);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var26);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample29)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var26);
		}
	}

	// Calculate the probability of the samples represented by sample42 using sampled
	// values.
	private final void logProbabilityValue$sample42() {
		// Determine if we need to calculate the values for sample task 42 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample42) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var37 = 0; var37 < noStates; var37 += 1) {
				// The sample value to calculate the probability of generating
				double cv$sampleValue = metric_mean[var37];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY));
			}
			logProbability$var33 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var38 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_mean = (logProbability$metric_mean + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample42)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample42 = fixedFlag$sample42;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var33 = logProbability$var38;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_mean = (logProbability$metric_mean + logProbability$var38);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var38);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample42)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var38);
		}
	}

	// Calculate the probability of the samples represented by sample51 using sampled
	// values.
	private final void logProbabilityValue$sample51() {
		// Determine if we need to calculate the values for sample task 51 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample51) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < noStates; var46 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(metric_var[var46], 1.0, 1.0));
			logProbability$var42 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var47 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_var = (logProbability$metric_var + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample51)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample51 = fixedFlag$sample51;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var42 = logProbability$var47;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_var = (logProbability$metric_var + logProbability$var47);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var47);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample51)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var47);
		}
	}

	// Calculate the probability of the samples represented by sample60 using sampled
	// values.
	private final void logProbabilityValue$sample60() {
		// Determine if we need to calculate the values for sample task 60 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample60) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var55 = 0; var55 < noStates; var55 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(metric_valid_bias[var55], 1.0, 1.0));
			logProbability$var51 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var56 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample60)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample60 = fixedFlag$sample60;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var51 = logProbability$var56;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + logProbability$var56);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var56);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample60)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var56);
		}
	}

	// Calculate the probability of the samples represented by sample75 using sampled
	// values.
	private final void logProbabilityValue$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample75) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[sample][0];
				
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
				logProbability$var68[sample] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample75[sample] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample75 = (fixedFlag$sample75 && fixedFlag$sample23);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample75[sample];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var68[sample] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample88 using sampled
	// values.
	private final void logProbabilityValue$sample88() {
		// Determine if we need to calculate the values for sample task 88 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample88) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[sample][timeStep$var74];
					double[] var80 = m[st[sample][(timeStep$var74 - 1)]];
					
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
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					
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
					logProbability$var81[sample][(timeStep$var74 - 1)] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample88[sample][(timeStep$var74 - 1)] = cv$distributionAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample88 = ((fixedFlag$sample88 && fixedFlag$sample29) && fixedFlag$sample75);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
					// Variable declaration of cv$rvAccumulator moved.
					double cv$rvAccumulator = logProbability$sample88[sample][(timeStep$var74 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var81[sample][(timeStep$var74 - 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 23 drawn from Dirichlet 19. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample23() {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var20$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample75) {
			// Processing random variable 68.
			for(int sample = 0; sample < noSamples; sample += 1)
				// Processing sample task 75 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 75 of random
				// variable var68
				// 
				// A local reference to the scratch space.
				cv$var20$countGlobal[st[sample][0]] = (cv$var20$countGlobal[st[sample][0]] + 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Processing sample task 75 of consumer random variable null.
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
					cv$var20$countGlobal[cv$loopIndex] = (cv$var20$countGlobal[cv$loopIndex] + distribution$sample75[sample][cv$loopIndex]);
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var20$countGlobal, initialStateDistribution);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 29 drawn from Dirichlet 21. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample29(int var25, int threadID$cv$var25, Rng RNG$) {
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var26$countGlobal[threadID$cv$var25];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample88) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < length$metric[sample])) {
					if(fixedFlag$sample75) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var25 == st[sample][0]))
							// Increment the sample counter with the value sampled by sample task 88 of random
							// variable var81
							// 
							// Substituted "timeStep$var74" with its value "1".
							cv$countLocal[st[sample][1]] = (cv$countLocal[st[sample][1]] + 1.0);
					} else
						// Processing sample task 88 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 88 of random
						// variable var81
						// 
						// Substituted "index$sample$4" with its value "sample".
						// 
						// Substituted "index$sample75$5" with its value "var25".
						cv$countLocal[st[sample][1]] = (cv$countLocal[st[sample][1]] + distribution$sample75[sample][var25]);
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 2; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
					if((var25 == st[sample][(timeStep$var74 - 1)]))
						// Processing sample task 88 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 88 of random
						// variable var81
						cv$countLocal[st[sample][timeStep$var74]] = (cv$countLocal[st[sample][timeStep$var74]] + 1.0);
				}
			}
		}
		
		// Processing random variable 81.
		// 
		// Looking for a path between Sample 29 and consumer Categorical 81.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < length$metric[sample])) {
					if(fixedFlag$sample75) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var25 == st[sample][0])) {
							// Merge the distribution probabilities into the count
							// 
							// Get the length of the array
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								// The probability of reaching the consumer with this set of consumer arguments
								// 
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample88[sample][0][cv$loopIndex]);
						}
					} else {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Substituted "index$sample$41" with its value "sample".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample75$42" with its value "var25".
						double cv$distributionProbability = distribution$sample75[sample][var25];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							// Substituted "timeStep$var74" with its value "1".
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample88[sample][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
					int index$timeStep$52 = (timeStep$var74 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$sample$51" with its value "sample".
					if((1 <= index$timeStep$52)) {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Substituted "index$sample$51" with its value "sample".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample88$53" with its value "var25".
						double cv$distributionProbability = distribution$sample88[sample][(index$timeStep$52 - 1)][var25];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample88[sample][(timeStep$var74 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var25]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 42 drawn from Uniform 33. Inference was performed using Metropolis-Hastings.
	private final void sample42(int var37, int threadID$cv$var37, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = metric_mean[var37];
		
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
			// Substituted "cv$temp$0$var31" with its value "0.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue <= 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
			
			// Processing random variable 102.
			// 
			// Looking for a path between Sample 42 and consumer Gaussian 102.
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
					if(fixedFlag$sample75) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var37 == st[sample][0])) {
							// Processing sample task 113 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var46 = st[sample][0];
							
							// Substituted "timeStep$var91" with its value "0".
							if(((0 <= var46) && (var46 < noStates))) {
								// Variable declaration of cv$temp$3$var101 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "timeStep$var91" with its value "0".
								double cv$temp$3$var101 = metric_var[st[sample][0]];
								
								// Substituted "timeStep$var91" with its value "0".
								// 
								// Substituted "cv$temp$2$var100" with its value "var100".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$originalValue) / Math.sqrt(cv$temp$3$var101))) - (Math.log(cv$temp$3$var101) * 0.5));
								
								// Recorded the probability of reaching sample task 113 with the current configuration.
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
						// Substituted "index$sample$4" with its value "sample".
						// 
						// Substituted "index$sample75$5" with its value "var37".
						double cv$probabilitySample75Value6 = distribution$sample75[sample][var37];
						
						// Variable declaration of cv$temp$9$var101 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
						// the output of Sample task 42.
						// 
						// Substituted "index$sample75$5" with its value "var37".
						double cv$temp$9$var101 = metric_var[var37];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 113 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "timeStep$var91" with its value "0".
						// 
						// Substituted "cv$temp$8$var100" with its value "var100".
						// 
						// Constructing a random variable input for use later.
						// 
						// Set the current value to the current state of the tree.
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample75Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$originalValue) / Math.sqrt(cv$temp$9$var101)))) - (Math.log(cv$temp$9$var101) * 0.5));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 113 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 113 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample75Value6), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
							// cv$temp$9$var101's comment
							// Variable declaration of cv$temp$9$var101 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
							// the output of Sample task 42.
							// 
							// Substituted "index$sample75$5" with its value "var37".
							// 
							// cv$temp$9$var101's comment
							// Variable declaration of cv$temp$9$var101 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
							// the output of Sample task 42.
							// 
							// Substituted "index$sample75$5" with its value "var37".
							// 
							// cv$temp$9$var101's comment
							// Variable declaration of cv$temp$9$var101 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
							// the output of Sample task 42.
							// 
							// Substituted "index$sample75$5" with its value "var37".
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 1; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					if(metric_valid_g[sample][timeStep$var91]) {
						if(fixedFlag$sample88) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var37 == st[sample][timeStep$var91])) {
								// Processing sample task 113 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var46 = st[sample][timeStep$var91];
								if(((0 <= var46) && (var46 < noStates))) {
									// Variable declaration of cv$temp$21$var101 moved.
									// 
									// Constructing a random variable input for use later.
									double cv$temp$21$var101 = metric_var[st[sample][timeStep$var91]];
									
									// Substituted "cv$temp$20$var100" with its value "var100".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$originalValue) / Math.sqrt(cv$temp$21$var101))) - (Math.log(cv$temp$21$var101) * 0.5));
									
									// Recorded the probability of reaching sample task 113 with the current configuration.
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
							// Substituted "index$sample$14" with its value "sample".
							// 
							// Substituted "index$sample88$16" with its value "var37".
							double cv$probabilitySample88Value17 = distribution$sample88[sample][(timeStep$var91 - 1)][var37];
							
							// Variable declaration of cv$temp$27$var101 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
							// the output of Sample task 42.
							// 
							// Substituted "index$sample88$16" with its value "var37".
							double cv$temp$27$var101 = metric_var[var37];
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Processing sample task 113 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "cv$temp$26$var100" with its value "var100".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample88Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$originalValue) / Math.sqrt(cv$temp$27$var101)))) - (Math.log(cv$temp$27$var101) * 0.5));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 113 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 113 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample88Value17), 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								// 
								// cv$temp$27$var101's comment
								// Variable declaration of cv$temp$27$var101 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
								// the output of Sample task 42.
								// 
								// Substituted "index$sample88$16" with its value "var37".
								// 
								// cv$temp$27$var101's comment
								// Variable declaration of cv$temp$27$var101 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
								// the output of Sample task 42.
								// 
								// Substituted "index$sample88$16" with its value "var37".
								// 
								// cv$temp$27$var101's comment
								// Variable declaration of cv$temp$27$var101 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
								// the output of Sample task 42.
								// 
								// Substituted "index$sample88$16" with its value "var37".
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
		
		// Update Sample and intermediate values
		metric_mean[var37] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$var31" with its value "0.0".
		double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
		
		// Processing random variable 102.
		// 
		// Looking for a path between Sample 42 and consumer Gaussian 102.
		for(int sample = 0; sample < noSamples; sample += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
				if(fixedFlag$sample75) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var37 == st[sample][0])) {
						// Processing sample task 113 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var46 = st[sample][0];
						
						// Substituted "timeStep$var91" with its value "0".
						if(((0 <= var46) && (var46 < noStates))) {
							// Variable declaration of cv$temp$3$var101 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "timeStep$var91" with its value "0".
							double cv$temp$3$var101 = metric_var[st[sample][0]];
							
							// Substituted "timeStep$var91" with its value "0".
							// 
							// Substituted "cv$temp$2$var100" with its value "var100".
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var101))) - (Math.log(cv$temp$3$var101) * 0.5));
							
							// Recorded the probability of reaching sample task 113 with the current configuration.
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
					// Substituted "index$sample$4" with its value "sample".
					// 
					// Substituted "index$sample75$5" with its value "var37".
					double cv$probabilitySample75Value6 = distribution$sample75[sample][var37];
					
					// Variable declaration of cv$temp$9$var101 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
					// the output of Sample task 42.
					// 
					// Substituted "index$sample75$5" with its value "var37".
					double cv$temp$9$var101 = metric_var[var37];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 113 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "timeStep$var91" with its value "0".
					// 
					// Substituted "cv$temp$8$var100" with its value "var100".
					// 
					// Constructing a random variable input for use later.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample75Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var101)))) - (Math.log(cv$temp$9$var101) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 113 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 113 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample75Value6), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// cv$temp$9$var101's comment
						// Variable declaration of cv$temp$9$var101 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
						// the output of Sample task 42.
						// 
						// Substituted "index$sample75$5" with its value "var37".
						// 
						// cv$temp$9$var101's comment
						// Variable declaration of cv$temp$9$var101 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
						// the output of Sample task 42.
						// 
						// Substituted "index$sample75$5" with its value "var37".
						// 
						// cv$temp$9$var101's comment
						// Variable declaration of cv$temp$9$var101 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
						// the output of Sample task 42.
						// 
						// Substituted "index$sample75$5" with its value "var37".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var91 = 1; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
				if(metric_valid_g[sample][timeStep$var91]) {
					if(fixedFlag$sample88) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var37 == st[sample][timeStep$var91])) {
							// Processing sample task 113 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var46 = st[sample][timeStep$var91];
							if(((0 <= var46) && (var46 < noStates))) {
								// Variable declaration of cv$temp$21$var101 moved.
								// 
								// Constructing a random variable input for use later.
								double cv$temp$21$var101 = metric_var[st[sample][timeStep$var91]];
								
								// Substituted "cv$temp$20$var100" with its value "var100".
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$proposedValue) / Math.sqrt(cv$temp$21$var101))) - (Math.log(cv$temp$21$var101) * 0.5));
								
								// Recorded the probability of reaching sample task 113 with the current configuration.
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
						// Substituted "index$sample$14" with its value "sample".
						// 
						// Substituted "index$sample88$16" with its value "var37".
						double cv$probabilitySample88Value17 = distribution$sample88[sample][(timeStep$var91 - 1)][var37];
						
						// Variable declaration of cv$temp$27$var101 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
						// the output of Sample task 42.
						// 
						// Substituted "index$sample88$16" with its value "var37".
						double cv$temp$27$var101 = metric_var[var37];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 113 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "cv$temp$26$var100" with its value "var100".
						// 
						// Constructing a random variable input for use later.
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample88Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$proposedValue) / Math.sqrt(cv$temp$27$var101)))) - (Math.log(cv$temp$27$var101) * 0.5));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 113 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 113 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample88Value17), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
							// cv$temp$27$var101's comment
							// Variable declaration of cv$temp$27$var101 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
							// the output of Sample task 42.
							// 
							// Substituted "index$sample88$16" with its value "var37".
							// 
							// cv$temp$27$var101's comment
							// Variable declaration of cv$temp$27$var101 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
							// the output of Sample task 42.
							// 
							// Substituted "index$sample88$16" with its value "var37".
							// 
							// cv$temp$27$var101's comment
							// Variable declaration of cv$temp$27$var101 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
							// the output of Sample task 42.
							// 
							// Substituted "index$sample88$16" with its value "var37".
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
		// Variable declaration of cv$proposedProbability moved.
		// Declaration comment was:
		// The probability of the random variable generating the new sample value.
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
			metric_mean[var37] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 51 drawn from InverseGamma 42. Inference was performed using Metropolis-Hastings.
	private final void sample51(int var46, int threadID$cv$var46, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = metric_var[var46];
		
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
			// Substituted "cv$temp$1$var41" with its value "1.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			
			// Processing random variable 102.
			// 
			// Looking for a path between Sample 51 and consumer Gaussian 102.
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
					if(fixedFlag$sample75) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var46 == st[sample][0])) {
							// Processing sample task 113 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var37 = st[sample][0];
							
							// Substituted "timeStep$var91" with its value "0".
							if(((0 <= var37) && (var37 < noStates))) {
								// Substituted "timeStep$var91" with its value "0".
								// 
								// Substituted "cv$temp$3$var101" with its value "var101".
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[st[sample][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
								
								// Recorded the probability of reaching sample task 113 with the current configuration.
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
						// Substituted "index$sample$4" with its value "sample".
						// 
						// Substituted "index$sample75$5" with its value "var46".
						double cv$probabilitySample75Value6 = distribution$sample75[sample][var46];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 113 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "timeStep$var91" with its value "0".
						// 
						// Substituted "cv$temp$9$var101" with its value "var101".
						// 
						// Constructing a random variable input for use later.
						// 
						// Set the current value to the current state of the tree.
						// 
						// cv$temp$8$var100's comment
						// Variable declaration of cv$temp$8$var100 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
						// the output of Sample task 51.
						// 
						// Substituted "index$sample75$5" with its value "var46".
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample75Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[var46]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 113 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 113 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample75Value6), 0.0);
						
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
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 1; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					if(metric_valid_g[sample][timeStep$var91]) {
						if(fixedFlag$sample88) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var46 == st[sample][timeStep$var91])) {
								// Processing sample task 113 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var37 = st[sample][timeStep$var91];
								if(((0 <= var37) && (var37 < noStates))) {
									// Substituted "cv$temp$21$var101" with its value "var101".
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									// 
									// cv$temp$20$var100's comment
									// Variable declaration of cv$temp$20$var100 moved.
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - metric_mean[st[sample][timeStep$var91]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
									
									// Recorded the probability of reaching sample task 113 with the current configuration.
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
							// Substituted "index$sample$14" with its value "sample".
							// 
							// Substituted "index$sample88$16" with its value "var46".
							double cv$probabilitySample88Value17 = distribution$sample88[sample][(timeStep$var91 - 1)][var46];
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Processing sample task 113 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "cv$temp$27$var101" with its value "var101".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							// 
							// cv$temp$26$var100's comment
							// Variable declaration of cv$temp$26$var100 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
							// the output of Sample task 51.
							// 
							// Substituted "index$sample88$16" with its value "var46".
							double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample88Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - metric_mean[var46]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 113 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 113 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample88Value17), 0.0);
							
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
		
		// Update Sample and intermediate values
		metric_var[var46] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var41" with its value "1.0".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
		
		// Processing random variable 102.
		// 
		// Looking for a path between Sample 51 and consumer Gaussian 102.
		for(int sample = 0; sample < noSamples; sample += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
				if(fixedFlag$sample75) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var46 == st[sample][0])) {
						// Processing sample task 113 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var37 = st[sample][0];
						
						// Substituted "timeStep$var91" with its value "0".
						if(((0 <= var37) && (var37 < noStates))) {
							// Substituted "timeStep$var91" with its value "0".
							// 
							// Substituted "cv$temp$3$var101" with its value "var101".
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[st[sample][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
							
							// Recorded the probability of reaching sample task 113 with the current configuration.
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
					// Substituted "index$sample$4" with its value "sample".
					// 
					// Substituted "index$sample75$5" with its value "var46".
					double cv$probabilitySample75Value6 = distribution$sample75[sample][var46];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 113 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "timeStep$var91" with its value "0".
					// 
					// Substituted "cv$temp$9$var101" with its value "var101".
					// 
					// Constructing a random variable input for use later.
					// 
					// cv$temp$8$var100's comment
					// Variable declaration of cv$temp$8$var100 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
					// the output of Sample task 51.
					// 
					// Substituted "index$sample75$5" with its value "var46".
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample75Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[var46]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 113 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 113 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample75Value6), 0.0);
					
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
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var91 = 1; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
				if(metric_valid_g[sample][timeStep$var91]) {
					if(fixedFlag$sample88) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var46 == st[sample][timeStep$var91])) {
							// Processing sample task 113 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var37 = st[sample][timeStep$var91];
							if(((0 <= var37) && (var37 < noStates))) {
								// Substituted "cv$temp$21$var101" with its value "var101".
								// 
								// Constructing a random variable input for use later.
								// 
								// cv$temp$20$var100's comment
								// Variable declaration of cv$temp$20$var100 moved.
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - metric_mean[st[sample][timeStep$var91]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
								
								// Recorded the probability of reaching sample task 113 with the current configuration.
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
						// Substituted "index$sample$14" with its value "sample".
						// 
						// Substituted "index$sample88$16" with its value "var46".
						double cv$probabilitySample88Value17 = distribution$sample88[sample][(timeStep$var91 - 1)][var46];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 113 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "cv$temp$27$var101" with its value "var101".
						// 
						// Constructing a random variable input for use later.
						// 
						// cv$temp$26$var100's comment
						// Variable declaration of cv$temp$26$var100 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
						// the output of Sample task 51.
						// 
						// Substituted "index$sample88$16" with its value "var46".
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample88Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - metric_mean[var46]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 113 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 113 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample88Value17), 0.0);
						
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
		// Variable declaration of cv$proposedProbability moved.
		// Declaration comment was:
		// The probability of the random variable generating the new sample value.
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
			metric_var[var46] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 60 drawn from Beta 51. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample60(int var55, int threadID$cv$var55, Rng RNG$) {
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		
		// Processing random variable 95.
		// 
		// Looking for a path between Sample 60 and consumer Bernoulli 95.
		for(int sample = 0; sample < noSamples; sample += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < length$metric[sample])) {
				if(fixedFlag$sample75) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var55 == st[sample][0])) {
						// Processing sample task 104 of consumer random variable null.
						// 
						// Include the value sampled by task 104 from random variable var95.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						// 
						// Substituted "timeStep$var91" with its value "0".
						if(metric_valid_g[sample][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$4" with its value "sample".
					// 
					// Substituted "index$sample75$5" with its value "var55".
					double cv$probabilitySample75Value6 = distribution$sample75[sample][var55];
					
					// Processing sample task 104 of consumer random variable null.
					// 
					// Include the value sampled by task 104 from random variable var95.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + cv$probabilitySample75Value6);
					
					// If the sample value was positive increase the count
					// 
					// Substituted "timeStep$var91" with its value "0".
					if(metric_valid_g[sample][0])
						cv$sum = (cv$sum + cv$probabilitySample75Value6);
				}
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var91 = 1; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
				if(fixedFlag$sample88) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var55 == st[sample][timeStep$var91])) {
						// Processing sample task 104 of consumer random variable null.
						// 
						// Include the value sampled by task 104 from random variable var95.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						if(metric_valid_g[sample][timeStep$var91])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$14" with its value "sample".
					// 
					// Substituted "index$sample88$16" with its value "var55".
					double cv$probabilitySample88Value17 = distribution$sample88[sample][(timeStep$var91 - 1)][var55];
					
					// Processing sample task 104 of consumer random variable null.
					// 
					// Include the value sampled by task 104 from random variable var95.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + cv$probabilitySample88Value17);
					
					// If the sample value was positive increase the count
					if(metric_valid_g[sample][timeStep$var91])
						cv$sum = (cv$sum + cv$probabilitySample88Value17);
				}
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		metric_valid_bias[var55] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 75 drawn from Categorical 68. Inference was performed using variable
	// marginalization.
	private final void sample75(int sample, int threadID$cv$sample, Rng RNG$) {
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var69$stateProbabilityGlobal[threadID$cv$sample];
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$initialStateDistribution" with its value "initialStateDistribution".
			double cv$accumulatedProbabilities = ((cv$valuePos < initialStateDistribution.length)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample88 && (1 < length$metric[sample]))) {
				// Looking for a path between Sample 75 and consumer Categorical 81.
				// Variable declaration of cv$temp$1$var80 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				double[] cv$temp$1$var80 = m[cv$valuePos];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 88 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 88 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "index$sample$2_2" with its value "sample".
				cv$accumulatedProbabilities = ((((0.0 <= st[sample][1]) && (st[sample][1] < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[st[sample][1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < length$metric[sample])) {
				// Looking for a path between Sample 75 and consumer Bernoulli 95.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 104 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 104 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "index$sample$8_2" with its value "sample".
				// 
				// cv$temp$2$var94's comment
				// Variable declaration of cv$temp$2$var94 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][0], metric_valid_bias[cv$valuePos]) + cv$accumulatedProbabilities);
				
				// Processing random variable 102.
				// 
				// Looking for a path between Sample 75 and consumer Gaussian 102.
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[][] guard$sample75gaussian112 = guard$sample75gaussian112$global[threadID$cv$sample];
				
				// Set the flags to false
				// 
				// Substituted "timeStep$var91" with its value "0".
				guard$sample75gaussian112[sample][0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample75gaussian112[sample][0]) {
					// The body will execute, so should not be executed again
					// 
					// Substituted "timeStep$var91" with its value "0".
					guard$sample75gaussian112[sample][0] = true;
					
					// Processing sample task 113 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(metric_valid_g[sample][0]) {
						// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
						// the output of Sample task 75.
						// Variable declaration of cv$temp$4$var101 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$4$var101 = metric_var[cv$valuePos];
						
						// Substituted "index$sample$14_2" with its value "sample".
						// 
						// cv$temp$3$var100's comment
						// Variable declaration of cv$temp$3$var100 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$4$var101))) - (Math.log(cv$temp$4$var101) * 0.5));
						
						// Recorded the probability of reaching sample task 113 with the current configuration.
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
				
				// Substituted "timeStep$var91" with its value "0".
				if(!guard$sample75gaussian112[sample][0]) {
					// The body will execute, so should not be executed again
					// 
					// Substituted "timeStep$var91" with its value "0".
					guard$sample75gaussian112[sample][0] = true;
					
					// Processing sample task 113 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(metric_valid_g[sample][0]) {
						// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
						// the output of Sample task 75.
						// Variable declaration of cv$temp$12$var101 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$12$var101 = metric_var[cv$valuePos];
						
						// Substituted "index$sample$15_2" with its value "sample".
						// 
						// cv$temp$11$var100's comment
						// Variable declaration of cv$temp$11$var100 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$12$var101))) - (Math.log(cv$temp$12$var101) * 0.5));
						
						// Recorded the probability of reaching sample task 113 with the current configuration.
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
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!fixedFlag$sample88 && (1 < length$metric[sample]))) {
				// Looking for a path between Sample 75 and consumer Categorical 81.
				// Processing sample task 88 of consumer random variable null.
				// 
				// A local array to hold the accumulated distributions of the sample tasks for each
				// configuration of distributions.
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var81[threadID$cv$sample];
				
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				
				// Add the current distribution to the distribution accumulator.
				// 
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// Add the probability of this argument configuration.
				// 
				// Declare and zero an accumulator for tracking the reached source probability space.
				// 
				// cv$temp$19$var80's comment
				// Variable declaration of cv$temp$19$var80 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, 1.0, m[cv$valuePos]);
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$sample$66_2" with its value "sample".
				double[] cv$sampleDistribution = distribution$sample88[sample][0];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					double cv$normalisedDistValue = cv$accumulatedConsumerDistributions[cv$i];
					
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
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$stateProbabilityLocal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample75[sample];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		double cv$lseMax = cv$stateProbabilityLocal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1) {
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 88 drawn from Categorical 81. Inference was performed using variable
	// marginalization.
	private final void sample88(int sample, int timeStep$var74, int threadID$cv$sample, Rng RNG$) {
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var82$stateProbabilityGlobal[threadID$cv$sample];
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 81 creating
			// sample task 88.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Enumerating the possible arguments for Categorical 81.
			if((1 == timeStep$var74)) {
				// Enumerating the possible arguments for Categorical 81.
				if(fixedFlag$sample75) {
					int var25 = st[sample][0];
					
					// Substituted "timeStep$var74" with its value "1".
					if(((0 <= var25) && (var25 < noStates))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// Variable declaration of cv$temp$0$var80 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "timeStep$var74" with its value "1".
						double[] cv$temp$0$var80 = m[st[sample][0]];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var80.length)?Math.log(cv$temp$0$var80[cv$valuePos]):Double.NEGATIVE_INFINITY);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < length$metric[sample])) {
							// Looking for a path between Sample 88 and consumer Bernoulli 95.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 104 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Processing sample task 104 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "index$sample$24_2" with its value "sample".
							// 
							// Substituted "timeStep$var91" with its value "1".
							// 
							// cv$temp$4$var94's comment
							// Variable declaration of cv$temp$4$var94 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][1], metric_valid_bias[cv$valuePos]) + cv$accumulatedProbabilities);
							
							// Processing random variable 102.
							// 
							// Looking for a path between Sample 88 and consumer Gaussian 102.
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[][] guard$sample88gaussian112 = guard$sample88gaussian112$global[threadID$cv$sample];
							
							// Set the flags to false
							// 
							// Substituted "timeStep$var91" with its value "1".
							guard$sample88gaussian112[sample][1] = false;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!guard$sample88gaussian112[sample][1]) {
								// The body will execute, so should not be executed again
								// 
								// Substituted "timeStep$var91" with its value "1".
								guard$sample88gaussian112[sample][1] = true;
								
								// Processing sample task 113 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								// 
								// Substituted "timeStep$var91" with its value "1".
								if(metric_valid_g[sample][1]) {
									// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
									// the output of Sample task 88.
									// Variable declaration of cv$temp$11$var101 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double cv$temp$11$var101 = metric_var[cv$valuePos];
									
									// Substituted "index$sample$48_2" with its value "sample".
									// 
									// Substituted "timeStep$var91" with its value "1".
									// 
									// cv$temp$10$var100's comment
									// Variable declaration of cv$temp$10$var100 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$11$var101))) - (Math.log(cv$temp$11$var101) * 0.5));
									
									// Recorded the probability of reaching sample task 113 with the current configuration.
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
							
							// Substituted "timeStep$var74" with its value "1".
							// 
							// Substituted "timeStep$var91" with its value "1".
							if(!guard$sample88gaussian112[sample][1]) {
								// The body will execute, so should not be executed again
								// 
								// Substituted "timeStep$var91" with its value "1".
								guard$sample88gaussian112[sample][1] = true;
								
								// Processing sample task 113 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
								// the output of Sample task 88.
								// 
								// Constraints moved from conditionals in inner loops/scopes/etc.
								// 
								// Substituted "timeStep$var91" with its value "1".
								if(metric_valid_g[sample][1]) {
									// Variable declaration of cv$temp$43$var101 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
									// the output of Sample task 88.
									// 
									// Value of the variable at this index
									double cv$temp$43$var101 = metric_var[cv$valuePos];
									
									// Substituted "index$sample$52_2" with its value "sample".
									// 
									// Substituted "timeStep$var91" with its value "1".
									// 
									// cv$temp$42$var100's comment
									// Variable declaration of cv$temp$42$var100 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
									// the output of Sample task 88.
									// 
									// Value of the variable at this index
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$43$var101))) - (Math.log(cv$temp$43$var101) * 0.5));
									
									// Recorded the probability of reaching sample task 113 with the current configuration.
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
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					// Enumerating the possible outputs of Categorical 68.
					for(int index$sample75$5 = 0; index$sample75$5 < noStates; index$sample75$5 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$4" with its value "sample".
						double cv$probabilitySample75Value6 = distribution$sample75[sample][index$sample75$5];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample75Value6);
						
						// Variable declaration of cv$temp$1$var80 moved.
						// 
						// Constructing a random variable input for use later.
						double[] cv$temp$1$var80 = m[index$sample75$5];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample75Value6) + ((cv$valuePos < cv$temp$1$var80.length)?Math.log(cv$temp$1$var80[cv$valuePos]):Double.NEGATIVE_INFINITY));
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < length$metric[sample])) {
							// Looking for a path between Sample 88 and consumer Bernoulli 95.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 104 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Processing sample task 104 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "index$sample$25_2" with its value "sample".
							// 
							// Substituted "timeStep$var91" with its value "1".
							// 
							// cv$temp$5$var94's comment
							// Variable declaration of cv$temp$5$var94 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][1], metric_valid_bias[cv$valuePos]) + cv$accumulatedProbabilities);
							
							// Processing random variable 102.
							// 
							// Looking for a path between Sample 88 and consumer Gaussian 102.
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[][] guard$sample88gaussian112 = guard$sample88gaussian112$global[threadID$cv$sample];
							
							// Set the flags to false
							// 
							// Substituted "timeStep$var91" with its value "1".
							guard$sample88gaussian112[sample][1] = false;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!guard$sample88gaussian112[sample][1]) {
								// The body will execute, so should not be executed again
								// 
								// Substituted "timeStep$var91" with its value "1".
								guard$sample88gaussian112[sample][1] = true;
								
								// Processing sample task 113 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								// 
								// Substituted "timeStep$var91" with its value "1".
								if(metric_valid_g[sample][1]) {
									// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
									// the output of Sample task 88.
									// Variable declaration of cv$temp$19$var101 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double cv$temp$19$var101 = metric_var[cv$valuePos];
									
									// Substituted "index$sample$49_2" with its value "sample".
									// 
									// Substituted "timeStep$var91" with its value "1".
									// 
									// cv$temp$18$var100's comment
									// Variable declaration of cv$temp$18$var100 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$19$var101))) - (Math.log(cv$temp$19$var101) * 0.5));
									
									// Recorded the probability of reaching sample task 113 with the current configuration.
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
							
							// Substituted "timeStep$var74" with its value "1".
							// 
							// Substituted "timeStep$var91" with its value "1".
							if(!guard$sample88gaussian112[sample][1]) {
								// The body will execute, so should not be executed again
								// 
								// Substituted "timeStep$var91" with its value "1".
								guard$sample88gaussian112[sample][1] = true;
								
								// Processing sample task 113 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Substituted "index$sample$53_2" with its value "sample".
								// 
								// Constraints moved from conditionals in inner loops/scopes/etc.
								// 
								// Substituted "timeStep$var91" with its value "1".
								if(metric_valid_g[sample][1]) {
									// Variable declaration of cv$temp$51$var101 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
									// the output of Sample task 88.
									// 
									// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
									// the output of Sample task 88.
									// 
									// Value of the variable at this index
									double cv$temp$51$var101 = metric_var[cv$valuePos];
									
									// Substituted "index$sample$53_2" with its value "sample".
									// 
									// Substituted "timeStep$var91" with its value "1".
									// 
									// cv$temp$50$var100's comment
									// Variable declaration of cv$temp$50$var100 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
									// the output of Sample task 88.
									// 
									// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
									// the output of Sample task 88.
									// 
									// Value of the variable at this index
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$51$var101))) - (Math.log(cv$temp$51$var101) * 0.5));
									
									// Recorded the probability of reaching sample task 113 with the current configuration.
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
			}
			int index$timeStep$13 = (timeStep$var74 - 1);
			
			// index$sample$2's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$sample$12" with its value "sample".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var74 - 1)".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var74 - 1)".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var74 - 1)".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var74 - 1)".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var74 - 1)".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var74 - 1)".
			if(((1 <= index$timeStep$13) && !(index$timeStep$13 == timeStep$var74))) {
				// Enumerating the possible outputs of Categorical 81.
				for(int index$sample88$14 = 0; index$sample88$14 < noStates; index$sample88$14 += 1) {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$12" with its value "sample".
					double cv$probabilitySample88Value15 = distribution$sample88[sample][(index$timeStep$13 - 1)][index$sample88$14];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample88Value15);
					
					// Variable declaration of cv$temp$3$var80 moved.
					// 
					// Constructing a random variable input for use later.
					double[] cv$temp$3$var80 = m[index$sample88$14];
					
					// Variable declaration of cv$accumulatedProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					// 
					// Processing random variable 95.
					// 
					// Looking for a path between Sample 88 and consumer Bernoulli 95.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 104 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					double cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][timeStep$var74], metric_valid_bias[cv$valuePos]) + Math.log(cv$probabilitySample88Value15)) + ((cv$valuePos < cv$temp$3$var80.length)?Math.log(cv$temp$3$var80[cv$valuePos]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 102.
					// 
					// Looking for a path between Sample 88 and consumer Gaussian 102.
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					boolean[][] guard$sample88gaussian112 = guard$sample88gaussian112$global[threadID$cv$sample];
					
					// Set the flags to false
					// 
					// Substituted "timeStep$var91" with its value "timeStep$var74".
					guard$sample88gaussian112[sample][timeStep$var74] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample88gaussian112[sample][timeStep$var74]) {
						// The body will execute, so should not be executed again
						// 
						// Substituted "timeStep$var91" with its value "timeStep$var74".
						guard$sample88gaussian112[sample][timeStep$var74] = true;
						
						// Processing sample task 113 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(metric_valid_g[sample][timeStep$var74]) {
							// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
							// the output of Sample task 88.
							// Variable declaration of cv$temp$35$var101 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$35$var101 = metric_var[cv$valuePos];
							
							// Substituted "index$sample$51_2" with its value "sample".
							// 
							// cv$temp$34$var100's comment
							// Variable declaration of cv$temp$34$var100 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var74] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$35$var101))) - (Math.log(cv$temp$35$var101) * 0.5));
							
							// Recorded the probability of reaching sample task 113 with the current configuration.
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
					if(!guard$sample88gaussian112[sample][timeStep$var74]) {
						// The body will execute, so should not be executed again
						// 
						// Substituted "timeStep$var91" with its value "timeStep$var74".
						guard$sample88gaussian112[sample][timeStep$var74] = true;
						
						// Processing sample task 113 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(metric_valid_g[sample][timeStep$var74]) {
							// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
							// the output of Sample task 88.
							// Variable declaration of cv$temp$67$var101 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
							// the output of Sample task 88.
							// 
							// Value of the variable at this index
							double cv$temp$67$var101 = metric_var[cv$valuePos];
							
							// Substituted "index$sample$55_2" with its value "sample".
							// 
							// cv$temp$66$var100's comment
							// Variable declaration of cv$temp$66$var100 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 102 which is consuming
							// the output of Sample task 88.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var74] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$67$var101))) - (Math.log(cv$temp$67$var101) * 0.5));
							
							// Recorded the probability of reaching sample task 113 with the current configuration.
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
			int index$timeStep$248_3 = (timeStep$var74 + 1);
			if((index$timeStep$248_3 < length$metric[sample])) {
				// Processing sample task 88 of consumer random variable null.
				// A local array to hold the accumulated distributions of the sample tasks for each
				// configuration of distributions.
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var81[threadID$cv$sample];
				
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				
				// Declare and zero an accumulator for tracking the reached source probability space.
				double scopeVariable$reachedSourceProbability = 0.0;
				
				// Enumerating the possible arguments for Categorical 81.
				if((1 == timeStep$var74)) {
					// Enumerating the possible arguments for Categorical 81.
					if(fixedFlag$sample75) {
						int index$var25$259_1 = st[sample][0];
						
						// Substituted "timeStep$var74" with its value "1".
						if(((0 <= index$var25$259_1) && (index$var25$259_1 < noStates)))
							// Add the probability of this argument configuration.
							// 
							// Declare and zero an accumulator for tracking the reached source probability space.
							scopeVariable$reachedSourceProbability = 1.0;
					} else {
						// Enumerating the possible outputs of Categorical 68.
						for(int index$sample75$255 = 0; index$sample75$255 < noStates; index$sample75$255 += 1)
							// Add the probability of this argument configuration.
							// 
							// cv$probabilitySample75Value256's comment
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$254" with its value "sample".
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample75[sample][index$sample75$255]);
					}
				}
				int index$timeStep$263 = (timeStep$var74 - 1);
				
				// index$timeStep$250's comment
				// Copy of index so that its values can be safely substituted
				// 
				// index$sample$251's comment
				// Copy of index so that its values can be safely substituted
				// 
				// Substituted "index$sample$248_2" with its value "sample".
				// 
				// Substituted "index$timeStep$248_3" with its value "(timeStep$var74 + 1)".
				// 
				// Substituted "index$timeStep$248_3" with its value "(timeStep$var74 + 1)".
				// 
				// Substituted "index$timeStep$248_3" with its value "(timeStep$var74 + 1)".
				// 
				// Substituted "index$timeStep$248_3" with its value "(timeStep$var74 + 1)".
				// 
				// Substituted "index$timeStep$248_3" with its value "(timeStep$var74 + 1)".
				// 
				// Substituted "index$timeStep$248_3" with its value "(timeStep$var74 + 1)".
				if((((1 <= index$timeStep$263) && !(index$timeStep$263 == timeStep$var74)) && !(index$timeStep$263 == index$timeStep$248_3))) {
					// Enumerating the possible outputs of Categorical 81.
					for(int index$sample88$264 = 0; index$sample88$264 < noStates; index$sample88$264 += 1)
						// Add the probability of this argument configuration.
						// 
						// cv$probabilitySample88Value265's comment
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$262" with its value "sample".
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample88[sample][(index$timeStep$263 - 1)][index$sample88$264]);
				}
				
				// Add the current distribution to the distribution accumulator.
				// 
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// cv$temp$72$var80's comment
				// Variable declaration of cv$temp$72$var80 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 81.
				// 
				// Looking for a path between Sample 88 and consumer Categorical 81.
				// 
				// Value of the variable at this index
				DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$sample$248_2" with its value "sample".
				double[] cv$sampleDistribution = distribution$sample88[sample][(index$timeStep$248_3 - 1)];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Zero an accumulator to track the probabilities reached.
					double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / scopeVariable$reachedSourceProbability);
					
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
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample88[sample][(timeStep$var74 - 1)];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		double cv$lseMax = cv$stateProbabilityLocal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1) {
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var20$countGlobal
		// 
		// Allocation of cv$var20$countGlobal for single threaded execution
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		cv$var20$countGlobal = new double[Math.max(0, noStates)];
		
		// Constructor for cv$var26$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			if((0 < noStates))
				cv$max = noStates;
			
			// Allocation of cv$var26$countGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var26$countGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var26$countGlobal[cv$index] = new double[cv$max];
		}
		
		// Constructor for cv$distributionAccumulator$var81
		{
			// Allocation of cv$distributionAccumulator$var81 for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$distributionAccumulator$var81 = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				// Variable to record the maximum value of Task Get 86. Initially set to the value
				// of putTask 30.
				cv$distributionAccumulator$var81[cv$index] = new double[noStates];
		}
		
		// Constructor for cv$var69$stateProbabilityGlobal
		{
			// Allocation of cv$var69$stateProbabilityGlobal for multithreaded execution
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var69$stateProbabilityGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var69$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		
		// Constructor for guard$sample75gaussian112$global
		{
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var91 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var91 = Math.max(cv$max_timeStep$var91, length$metric[sample]);
			
			// Variable declaration of cv$max_sample moved.
			// Declaration comment was:
			// Calculate the largest index of sample that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_sample = length$metric.length;
			
			// Allocation of guard$sample75gaussian112$global for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			guard$sample75gaussian112$global = new boolean[cv$threadCount][][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample75gaussian112$global[cv$index] = new boolean[cv$max_sample][cv$max_timeStep$var91];
		}
		
		// Constructor for cv$var82$stateProbabilityGlobal
		{
			// Allocation of cv$var82$stateProbabilityGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var82$stateProbabilityGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				// Variable to record the maximum value of Task Get 86. Initially set to the value
				// of putTask 30.
				cv$var82$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		
		// Constructor for guard$sample88gaussian112$global
		// 
		// Calculate the largest index of timeStep that is possible and allocate an array
		// to hold the guard for each of these.
		int cv$max_timeStep$var91 = 0;
		for(int sample = 0; sample < length$metric.length; sample += 1)
			cv$max_timeStep$var91 = Math.max(cv$max_timeStep$var91, length$metric[sample]);
		
		// Variable declaration of cv$max_sample moved.
		// Declaration comment was:
		// Calculate the largest index of sample that is possible and allocate an array to
		// hold the guard for each of these.
		int cv$max_sample = length$metric.length;
		
		// Allocation of guard$sample88gaussian112$global for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		guard$sample88gaussian112$global = new boolean[cv$threadCount][][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			guard$sample88gaussian112$global[cv$index] = new boolean[cv$max_sample][cv$max_timeStep$var91];
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
			for(int var25 = 0; var25 < noStates; var25 += 1)
				m[var25] = new double[noStates];
		}
		
		// If st has not been set already allocate space.
		if(!setFlag$st) {
			// Constructor for st
			st = new int[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				st[sample] = new int[length$metric[sample]];
		}
		
		// If metric_g has not been set already allocate space.
		if(!setFlag$metric_g) {
			// Constructor for metric_g
			metric_g = new double[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				metric_g[sample] = new double[length$metric[sample]];
		}
		
		// If metric_valid_g has not been set already allocate space.
		if(!setFlag$metric_valid_g) {
			// Constructor for metric_valid_g
			metric_valid_g = new boolean[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				metric_valid_g[sample] = new boolean[length$metric[sample]];
		}
		
		// If metric_mean has not been set already allocate space.
		if(!setFlag$metric_mean)
			// Constructor for metric_mean
			metric_mean = new double[noStates];
		
		// If metric_var has not been set already allocate space.
		if(!setFlag$metric_var)
			// Constructor for metric_var
			metric_var = new double[noStates];
		
		// If metric_valid_bias has not been set already allocate space.
		if(!setFlag$metric_valid_bias)
			// Constructor for metric_valid_bias
			metric_valid_bias = new double[noStates];
		
		// Constructor for distribution$sample88
		distribution$sample88 = new double[length$metric.length][][];
		for(int sample = 0; sample < length$metric.length; sample += 1) {
			double[][] subarray$0 = new double[(length$metric[sample] - 1)][];
			distribution$sample88[sample] = subarray$0;
			for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
				subarray$0[(timeStep$var74 - 1)] = new double[noStates];
		}
		
		// Constructor for distribution$sample75
		distribution$sample75 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			distribution$sample75[sample] = new double[noStates];
		
		// Constructor for logProbability$var68
		logProbability$var68 = new double[length$metric.length];
		
		// Constructor for logProbability$sample75
		logProbability$sample75 = new double[length$metric.length];
		
		// Constructor for logProbability$var81
		logProbability$var81 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$var81[sample] = new double[(length$metric[sample] - 1)];
		
		// Constructor for logProbability$sample88
		logProbability$sample88 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$sample88[sample] = new double[(length$metric[sample] - 1)];
		
		// Constructor for logProbability$var95
		logProbability$var95 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$var95[sample] = new double[length$metric[sample]];
		
		// Constructor for logProbability$sample104
		logProbability$sample104 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$sample104[sample] = new double[length$metric[sample]];
		
		// Constructor for logProbability$var102
		logProbability$var102 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$var102[sample] = new double[length$metric[sample]];
		
		// Constructor for logProbability$sample113
		logProbability$sample113 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$sample113[sample] = new double[length$metric[sample]];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample23)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var25, int forEnd$var25, int threadID$var25, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var25 = forStart$var25; var25 < forEnd$var25; var25 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var25]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample42)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var37, int forEnd$var37, int threadID$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var37 = forStart$var37; var37 < forEnd$var37; var37 += 1)
							metric_mean[var37] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample51)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample60)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$index$sample, int forEnd$index$sample, int threadID$index$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$sample = forStart$index$sample; index$sample < forEnd$index$sample; index$sample += 1) {
						int sample = index$sample;
						if(!fixedFlag$sample75)
							st[sample][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample88) {
							int[] var75 = st[sample];
							for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
								var75[timeStep$var74] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var74 - 1)]]);
						}
						boolean[] metric_valid_1d = metric_valid_g[sample];
						double[] metric_1d = metric_g[sample];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, length$metric[sample], 1,
							(int forStart$timeStep$var91, int forEnd$timeStep$var91, int threadID$timeStep$var91, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int timeStep$var91 = forStart$timeStep$var91; timeStep$var91 < forEnd$timeStep$var91; timeStep$var91 += 1) {
										if(!fixedFlag$sample104)
											metric_valid_1d[timeStep$var91] = DistributionSampling.sampleBernoulli(RNG$2, metric_valid_bias[st[sample][timeStep$var91]]);
										if((metric_valid_g[sample][timeStep$var91] && !fixedFlag$sample113))
											metric_1d[timeStep$var91] = ((Math.sqrt(metric_var[st[sample][timeStep$var91]]) * DistributionSampling.sampleGaussian(RNG$2)) + metric_mean[st[sample][timeStep$var91]]);
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
		if(!fixedFlag$sample23)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var25, int forEnd$var25, int threadID$var25, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var25 = forStart$var25; var25 < forEnd$var25; var25 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var25]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample42)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var37, int forEnd$var37, int threadID$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var37 = forStart$var37; var37 < forEnd$var37; var37 += 1)
							metric_mean[var37] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample51)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample60)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample75) {
							// Create local copy of variable probabilities.
							double[] cv$distribution$sample75 = distribution$sample75[sample];
							for(int index$var68 = 0; index$var68 < noStates; index$var68 += 1)
								// Save the probability of each value
								// 
								// Probability for this value
								cv$distribution$sample75[index$var68] = ((index$var68 < initialStateDistribution.length)?initialStateDistribution[index$var68]:0.0);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample88) {
							for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
								// Create local copy of variable probabilities.
								double[] cv$distribution$sample88 = distribution$sample88[sample][(timeStep$var74 - 1)];
								for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
									// Zero the probability of each value
									cv$distribution$sample88[index$var81] = 0.0;
								
								// Iterate through possible values for var81's arguments.
								// 
								// Enumerating the possible arguments for Categorical 81.
								if((1 == timeStep$var74)) {
									// Iterate through possible values for var81's arguments.
									// 
									// Enumerating the possible arguments for Categorical 81.
									if(fixedFlag$sample75) {
										int var25 = st[sample][0];
										
										// Substituted "timeStep$var74" with its value "1".
										if(((0 <= var25) && (var25 < noStates))) {
											// Substituted "timeStep$var74" with its value "1".
											double[] var80 = m[st[sample][0]];
											for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
												// Save the probability of each value
												cv$distribution$sample88[index$var81] = (cv$distribution$sample88[index$var81] + ((index$var81 < var80.length)?var80[index$var81]:0.0));
										}
									} else {
										// Enumerating the possible outputs of Categorical 68.
										for(int index$sample75$3 = 0; index$sample75$3 < noStates; index$sample75$3 += 1) {
											// Update the probability of sampling this value from the distribution value.
											// 
											// Substituted "index$sample$2" with its value "sample".
											double cv$probabilitySample75Value4 = distribution$sample75[sample][index$sample75$3];
											double[] var80 = m[index$sample75$3];
											for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
												// Save the probability of each value
												cv$distribution$sample88[index$var81] = (cv$distribution$sample88[index$var81] + (cv$probabilitySample75Value4 * ((index$var81 < var80.length)?var80[index$var81]:0.0)));
										}
									}
								}
								int index$timeStep$11 = (timeStep$var74 - 1);
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								// 
								// Substituted "index$sample$10" with its value "sample".
								if((1 <= index$timeStep$11)) {
									// Enumerating the possible outputs of Categorical 81.
									for(int index$sample88$12 = 0; index$sample88$12 < noStates; index$sample88$12 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "index$sample$10" with its value "sample".
										double cv$probabilitySample88Value13 = distribution$sample88[sample][(index$timeStep$11 - 1)][index$sample88$12];
										double[] var80 = m[index$sample88$12];
										for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
											// Save the probability of each value
											cv$distribution$sample88[index$var81] = (cv$distribution$sample88[index$var81] + (cv$probabilitySample88Value13 * ((index$var81 < var80.length)?var80[index$var81]:0.0)));
									}
								}
								
								// Sum the values in the array
								double cv$var81$sum = 0.0;
								for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
									// sum the probability of each value
									cv$var81$sum = (cv$var81$sum + cv$distribution$sample88[index$var81]);
								for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
									// Normalise the probability of each value
									cv$distribution$sample88[index$var81] = (cv$distribution$sample88[index$var81] / cv$var81$sum);
							}
						}
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample23)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var25, int forEnd$var25, int threadID$var25, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var25 = forStart$var25; var25 < forEnd$var25; var25 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var25]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample42)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var37, int forEnd$var37, int threadID$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var37 = forStart$var37; var37 < forEnd$var37; var37 += 1)
							metric_mean[var37] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample51)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample60)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						if(!fixedFlag$sample75)
							st[sample][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample88) {
							int[] var75 = st[sample];
							for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
								var75[timeStep$var74] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var74 - 1)]]);
						}
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample23)
				sample23();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample29)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var25, int forEnd$var25, int threadID$var25, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var25 = forStart$var25; var25 < forEnd$var25; var25 += 1)
								sample29(var25, threadID$var25, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample42)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var37, int forEnd$var37, int threadID$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var37 = forStart$var37; var37 < forEnd$var37; var37 += 1)
								sample42(var37, threadID$var37, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample51)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
								sample51(var46, threadID$var46, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample60)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
								sample60(var55, threadID$var55, RNG$1);
					}
				);

			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
							if(!fixedFlag$sample75)
								sample75(sample, threadID$sample, RNG$1);
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample88) {
								for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
									sample88(sample, timeStep$var74, threadID$sample, RNG$1);
							}
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample88) {
								for(int timeStep$var74 = (length$metric[sample] - 1); timeStep$var74 >= 1; timeStep$var74 -= 1)
									sample88(sample, timeStep$var74, threadID$sample, RNG$1);
							}
							if(!fixedFlag$sample75)
								sample75(sample, threadID$sample, RNG$1);
						}
				}
			);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample60)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
								sample60(var55, threadID$var55, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample51)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
								sample51(var46, threadID$var46, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample42)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var37, int forEnd$var37, int threadID$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var37 = forStart$var37; var37 < forEnd$var37; var37 += 1)
								sample42(var37, threadID$var37, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample29)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var25, int forEnd$var25, int threadID$var25, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var25 = forStart$var25; var25 < forEnd$var25; var25 += 1)
								sample29(var25, threadID$var25, RNG$1);
					}
				);

			if(!fixedFlag$sample23)
				sample23();
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
			(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
						v[var16] = 0.1;
			}
		);
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
		logProbability$var19 = 0.0;
		if(!fixedProbFlag$sample23)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var21 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var26 = 0.0;
		logProbability$var33 = 0.0;
		logProbability$metric_mean = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$var38 = 0.0;
		logProbability$var42 = 0.0;
		logProbability$metric_var = 0.0;
		if(!fixedProbFlag$sample51)
			logProbability$var47 = 0.0;
		logProbability$var51 = 0.0;
		logProbability$metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample60)
			logProbability$var56 = 0.0;
		for(int sample = 0; sample < noSamples; sample += 1)
			logProbability$var68[sample] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample75) {
			for(int sample = 0; sample < noSamples; sample += 1)
				logProbability$sample75[sample] = 0.0;
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
				logProbability$var81[sample][(timeStep$var74 - 1)] = 0.0;
		}
		if(!fixedProbFlag$sample88) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
					logProbability$sample88[sample][(timeStep$var74 - 1)] = 0.0;
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
				logProbability$var95[sample][timeStep$var91] = 0.0;
		}
		logProbability$metric_valid_1d = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample104) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
					logProbability$sample104[sample][timeStep$var91] = 0.0;
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
				logProbability$var102[sample][timeStep$var91] = 0.0;
		}
		logProbability$metric_1d = 0.0;
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample113) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
					logProbability$sample113[sample][timeStep$var91] = 0.0;
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
		if(fixedFlag$sample23)
			logProbabilityValue$sample23();
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(fixedFlag$sample51)
			logProbabilityValue$sample51();
		if(fixedFlag$sample60)
			logProbabilityValue$sample60();
		logProbabilityValue$sample104();
		logProbabilityValue$sample113();
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
		logProbabilityValue$sample23();
		logProbabilityValue$sample29();
		logProbabilityValue$sample42();
		logProbabilityValue$sample51();
		logProbabilityValue$sample60();
		logProbabilityDistribution$sample75();
		logProbabilityDistribution$sample88();
		logProbabilityDistribution$sample104();
		logProbabilityDistribution$sample113();
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
		logProbabilityValue$sample23();
		logProbabilityValue$sample29();
		logProbabilityValue$sample42();
		logProbabilityValue$sample51();
		logProbabilityValue$sample60();
		logProbabilityValue$sample75();
		logProbabilityValue$sample88();
		logProbabilityValue$sample104();
		logProbabilityValue$sample113();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample23)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var25, int forEnd$var25, int threadID$var25, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var25 = forStart$var25; var25 < forEnd$var25; var25 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var25]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample42)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var37, int forEnd$var37, int threadID$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var37 = forStart$var37; var37 < forEnd$var37; var37 += 1)
							metric_mean[var37] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample51)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample60)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						if(!fixedFlag$sample75)
							st[sample][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample88) {
							int[] var75 = st[sample];
							for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
								var75[timeStep$var74] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var74 - 1)]]);
						}
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
		int cv$length1 = metric_valid_g.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = metric_valid[cv$index1];
			boolean[] cv$target2 = metric_valid_g[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
		for(int sample = (noSamples - 1); sample >= 0; sample -= 1) {
			for(int timeStep$var91 = (length$metric[sample] - 1); timeStep$var91 >= 0; timeStep$var91 -= 1)
				metric_g[sample][timeStep$var91] = metric[sample][timeStep$var91];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMMetrics2(\n               double[][] metric,\n               boolean[][] metric_valid, \n               int noStates) {\n    \n    int noSamples = metric.length;\n\n    // Construct arrays describing the probability of a move from 1 state to another.\n    double[] v = new double[noStates] <~ 0.1;\n    double[] initialStateDistribution = dirichlet(v).sample();\n    double[][] m = dirichlet(v).sample(noStates);\n\n    //Allocate space for states\n    int[][] st = new int[noSamples][];\n\n    //Allocate space for generated metrics \n    double[][] metric_g = new double[noSamples][];\n    boolean[][] metric_valid_g = new boolean[noSamples][];\n    \n    //Calculate priors for the metric\n    double[] metric_mean = uniform(0.0, 100.0).sample(noStates);\n    double[] metric_var = inverseGamma(1.0, 1.0).sample(noStates);\n    double[] metric_valid_bias = beta(1.0, 1.0).sample(noStates);\n    \n    // Compute the values of each metric value\n    for(int sample = 0; sample < noSamples; sample++) {\n        //Calculate all the state transitions\n        int streamLength = metric[sample].length;\n        \n        // Allocate space for the state.\n        st[sample] = new int[streamLength];\n        \n        // Set the initial state by sampling from a categorical with learnt weightings.\n        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n        \n        // Calculate the remaining weightings\n        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n        \n        //Calculate metric values\n        double[] metric_1d = new double[streamLength];\n        metric_g[sample] = metric_1d;\n\n        boolean[] metric_valid_1d = new boolean[streamLength];\n        metric_valid_g[sample] = metric_valid_1d;\n\n        //Generate values.\n        for(int timeStep = 0; timeStep < streamLength; timeStep++){\n            int currentState = st[sample][timeStep];\n            \n            metric_valid_1d[timeStep] = bernoulli(metric_valid_bias[currentState]).sample();\n            if(metric_valid_1d[timeStep])\n                metric_1d[timeStep] = gaussian(metric_mean[currentState], metric_var[currentState]).sample();\n            // Observing here as a cast is required and doing it inside the for loops removes the need duplicate them later.\n            metric_1d[timeStep].observe(metric[sample][timeStep]);\n        }\n    }\n\n    //Tie the values to the measured values.\n    metric_valid_g.observe(metric_valid);\n}";
	}
}