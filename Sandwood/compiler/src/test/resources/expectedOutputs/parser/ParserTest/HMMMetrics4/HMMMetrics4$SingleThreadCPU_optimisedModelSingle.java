package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics4$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMMetrics4$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] current_metric_mean;
	private double[][] current_metric_valid_bias;
	private double[][] current_metric_var;
	private double[] cv$distributionAccumulator$var73;
	private double[] cv$var20$countGlobal;
	private double[] cv$var33$countGlobal;
	private double[] cv$var55$stateProbabilityGlobal;
	private double[] cv$var74$stateProbabilityGlobal;
	private double[][] distribution$sample57;
	private double[][][] distribution$sample76;
	private boolean fixedFlag$sample134 = false;
	private boolean fixedFlag$sample162 = false;
	private boolean fixedFlag$sample190 = false;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample33 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample76 = false;
	private boolean fixedProbFlag$sample134 = false;
	private boolean fixedProbFlag$sample162 = false;
	private boolean fixedProbFlag$sample190 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample241 = false;
	private boolean fixedProbFlag$sample256 = false;
	private boolean fixedProbFlag$sample33 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample76 = false;
	private boolean[][][] guard$sample57gaussian255$global;
	private boolean[][][] guard$sample76gaussian255$global;
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
	private double logProbability$st;
	private double logProbability$var108;
	private double logProbability$var130;
	private double logProbability$var135;
	private double logProbability$var157;
	private double logProbability$var162;
	private double logProbability$var184;
	private double logProbability$var19;
	private double logProbability$var21;
	private double logProbability$var231;
	private double logProbability$var232;
	private double logProbability$var244;
	private double logProbability$var245;
	private double logProbability$var33;
	private double logProbability$var54;
	private double logProbability$var55;
	private double logProbability$var73;
	private double logProbability$var74;
	private double[][] m;
	private int max_metric;
	private double[][][] metric;
	private double[][][] metric_g;
	private boolean[][][] metric_valid;
	private boolean[][][] metric_valid_g;
	private int noSamples;
	private int noServers;
	private int noStates;
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;
	private double[][][] var245;

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
		// Set flags for all the side effects of current_metric_mean including if probabilities
		// need to be updated.
		// Set current_metric_mean
		current_metric_mean = cv$value;
		
		// Unset the fixed probability flag for sample 134 as it depends on current_metric_mean.
		fixedProbFlag$sample134 = false;
		
		// Unset the fixed probability flag for sample 256 as it depends on current_metric_mean.
		fixedProbFlag$sample256 = false;
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
		// Set current_metric_valid_bias
		current_metric_valid_bias = cv$value;
		
		// Unset the fixed probability flag for sample 190 as it depends on current_metric_valid_bias.
		fixedProbFlag$sample190 = false;
		
		// Unset the fixed probability flag for sample 241 as it depends on current_metric_valid_bias.
		fixedProbFlag$sample241 = false;
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
		// Set current_metric_var
		current_metric_var = cv$value;
		
		// Unset the fixed probability flag for sample 162 as it depends on current_metric_var.
		fixedProbFlag$sample162 = false;
		
		// Unset the fixed probability flag for sample 256 as it depends on current_metric_var.
		fixedProbFlag$sample256 = false;
	}

	// Getter for distribution$sample57.
	@Override
	public final double[][] get$distribution$sample57() {
		return distribution$sample57;
	}

	// Setter for distribution$sample57.
	@Override
	public final void set$distribution$sample57(double[][] cv$value) {
		// Set distribution$sample57
		distribution$sample57 = cv$value;
	}

	// Getter for distribution$sample76.
	@Override
	public final double[][][] get$distribution$sample76() {
		return distribution$sample76;
	}

	// Setter for distribution$sample76.
	@Override
	public final void set$distribution$sample76(double[][][] cv$value) {
		// Set distribution$sample76
		distribution$sample76 = cv$value;
	}

	// Getter for fixedFlag$sample134.
	@Override
	public final boolean get$fixedFlag$sample134() {
		return fixedFlag$sample134;
	}

	// Setter for fixedFlag$sample134.
	@Override
	public final void set$fixedFlag$sample134(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample134 including if probabilities
		// need to be updated.
		fixedFlag$sample134 = cv$value;
		
		// Should the probability of sample 134 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample134" with its value "cv$value".
		fixedProbFlag$sample134 = (cv$value && fixedProbFlag$sample134);
		
		// Should the probability of sample 256 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample134" with its value "cv$value".
		fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
	}

	// Getter for fixedFlag$sample162.
	@Override
	public final boolean get$fixedFlag$sample162() {
		return fixedFlag$sample162;
	}

	// Setter for fixedFlag$sample162.
	@Override
	public final void set$fixedFlag$sample162(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample162 including if probabilities
		// need to be updated.
		fixedFlag$sample162 = cv$value;
		
		// Should the probability of sample 162 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample162" with its value "cv$value".
		fixedProbFlag$sample162 = (cv$value && fixedProbFlag$sample162);
		
		// Should the probability of sample 256 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample162" with its value "cv$value".
		fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
	}

	// Getter for fixedFlag$sample190.
	@Override
	public final boolean get$fixedFlag$sample190() {
		return fixedFlag$sample190;
	}

	// Setter for fixedFlag$sample190.
	@Override
	public final void set$fixedFlag$sample190(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample190 including if probabilities
		// need to be updated.
		fixedFlag$sample190 = cv$value;
		
		// Should the probability of sample 190 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample190" with its value "cv$value".
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
		
		// Should the probability of sample 241 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample190" with its value "cv$value".
		fixedProbFlag$sample241 = (cv$value && fixedProbFlag$sample241);
	}

	// Getter for fixedFlag$sample20.
	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	// Setter for fixedFlag$sample20.
	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample20 including if probabilities
		// need to be updated.
		fixedFlag$sample20 = cv$value;
		
		// Should the probability of sample 20 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample20" with its value "cv$value".
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
		
		// Should the probability of sample 57 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample20" with its value "cv$value".
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
	}

	// Getter for fixedFlag$sample33.
	@Override
	public final boolean get$fixedFlag$sample33() {
		return fixedFlag$sample33;
	}

	// Setter for fixedFlag$sample33.
	@Override
	public final void set$fixedFlag$sample33(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample33 including if probabilities
		// need to be updated.
		fixedFlag$sample33 = cv$value;
		
		// Should the probability of sample 33 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample33" with its value "cv$value".
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
		
		// Should the probability of sample 76 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample33" with its value "cv$value".
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
	}

	// Getter for fixedFlag$sample57.
	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	// Setter for fixedFlag$sample57.
	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample57 including if probabilities
		// need to be updated.
		fixedFlag$sample57 = cv$value;
		
		// Should the probability of sample 57 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		
		// Should the probability of sample 76 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
		
		// Should the probability of sample 241 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample241 = (cv$value && fixedProbFlag$sample241);
		
		// Should the probability of sample 256 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
	}

	// Getter for fixedFlag$sample76.
	@Override
	public final boolean get$fixedFlag$sample76() {
		return fixedFlag$sample76;
	}

	// Setter for fixedFlag$sample76.
	@Override
	public final void set$fixedFlag$sample76(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample76 including if probabilities
		// need to be updated.
		fixedFlag$sample76 = cv$value;
		
		// Should the probability of sample 76 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample76" with its value "cv$value".
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
		
		// Should the probability of sample 241 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample76" with its value "cv$value".
		fixedProbFlag$sample241 = (cv$value && fixedProbFlag$sample241);
		
		// Should the probability of sample 256 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample76" with its value "cv$value".
		fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
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
		// Set initialStateDistribution
		initialStateDistribution = cv$value;
		
		// Unset the fixed probability flag for sample 20 as it depends on initialStateDistribution.
		fixedProbFlag$sample20 = false;
		
		// Unset the fixed probability flag for sample 57 as it depends on initialStateDistribution.
		fixedProbFlag$sample57 = false;
	}

	// Getter for length$metric.
	@Override
	public final int[][] get$length$metric() {
		return length$metric;
	}

	// Setter for length$metric.
	@Override
	public final void set$length$metric(int[][] cv$value) {
		// Set length$metric
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
		// Set m
		m = cv$value;
		
		// Unset the fixed probability flag for sample 33 as it depends on m.
		fixedProbFlag$sample33 = false;
		
		// Unset the fixed probability flag for sample 76 as it depends on m.
		fixedProbFlag$sample76 = false;
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
		// Set metric
		metric = cv$value;
	}

	// Getter for metric_g.
	@Override
	public final double[][][] get$metric_g() {
		return metric_g;
	}

	// Getter for metric_valid.
	@Override
	public final boolean[][][] get$metric_valid() {
		return metric_valid;
	}

	// Setter for metric_valid.
	@Override
	public final void set$metric_valid(boolean[][][] cv$value) {
		// Set metric_valid
		metric_valid = cv$value;
	}

	// Getter for metric_valid_g.
	@Override
	public final boolean[][][] get$metric_valid_g() {
		return metric_valid_g;
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
		// Set st
		st = cv$value;
		
		// Unset the fixed probability flag for sample 57 as it depends on st.
		fixedProbFlag$sample57 = false;
		
		// Unset the fixed probability flag for sample 76 as it depends on st.
		fixedProbFlag$sample76 = false;
		
		// Unset the fixed probability flag for sample 241 as it depends on st.
		fixedProbFlag$sample241 = false;
		
		// Unset the fixed probability flag for sample 256 as it depends on st.
		fixedProbFlag$sample256 = false;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Calculate the probability of the samples represented by sample241 using probability
	// distributions.
	private final void logProbabilityDistribution$sample241() {
		// Determine if we need to calculate the values for sample task 241 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample241) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 241 including any distribution
						// values.
						// 
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = metric_valid_g[sample$var196][server][timeStep$var226];
						
						// Enumerating the possible arguments for Bernoulli 231.
						if((0 == timeStep$var226)) {
							// Enumerating the possible arguments for Bernoulli 231.
							if(fixedFlag$sample57) {
								int var183 = st[sample$var196][0];
								if(((0 <= var183) && (var183 < noStates))) {
									// Substituted "timeStep$var226" with its value "0".
									double var230 = current_metric_valid_bias[server][st[sample$var196][0]];
									
									// Store the value of the function call, so the function call is only made once.
									cv$distributionAccumulator = Math.log((cv$sampleValue?var230:(1.0 - var230)));
									
									// Add the probability of this distribution configuration to the accumulator.
									// 
									// An accumulator for the distributed probability space covered.
									cv$probabilityReached = 1.0;
								}
							} else {
								// Enumerating the possible outputs of Categorical 54.
								for(int index$sample57$4 = 0; index$sample57$4 < noStates; index$sample57$4 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "sample$var45" with its value "sample$var196".
									double cv$probabilitySample57Value5 = distribution$sample57[sample$var196][index$sample57$4];
									int var183 = st[sample$var196][0];
									if(((0 <= var183) && (var183 < noStates))) {
										// Substituted "timeStep$var226" with its value "0".
										double var230 = current_metric_valid_bias[server][st[sample$var196][0]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + Math.log((cv$sampleValue?var230:(1.0 - var230))));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value5);
									}
								}
							}
						}
						
						// Enumerating the possible arguments for Bernoulli 231.
						if((1 <= timeStep$var226)) {
							// Enumerating the possible arguments for Bernoulli 231.
							if(fixedFlag$sample76) {
								int var183 = st[sample$var196][timeStep$var226];
								if(((0 <= var183) && (var183 < noStates))) {
									double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = Math.log((cv$sampleValue?var230:(1.0 - var230)));
									
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
								// Enumerating the possible outputs of Categorical 73.
								for(int index$sample76$13 = 0; index$sample76$13 < noStates; index$sample76$13 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "sample$var45" with its value "sample$var196".
									double cv$probabilitySample76Value14 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$13];
									int var183 = st[sample$var196][timeStep$var226];
									if(((0 <= var183) && (var183 < noStates))) {
										double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(cv$probabilitySample76Value14) + Math.log((cv$sampleValue?var230:(1.0 - var230))));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value14);
									}
								}
							}
						}
						if((cv$probabilityReached == 0.0))
							// Return negative infinity if no distribution probability space is reached.
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							// Scale the probability relative to the observed distribution space.
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						
						// Add the probability of this sample task to the sample task accumulator.
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
					}
				}
			}
			logProbability$var231 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var232 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$sampleAccumulator);
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample241 = ((fixedFlag$sample57 && fixedFlag$sample76) && fixedFlag$sample190);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var231 = logProbability$var232;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + logProbability$var232);
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_valid_g = (logProbability$metric_valid_g + logProbability$var232);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var232);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var232);
		}
	}

	// Calculate the probability of the samples represented by sample256 using probability
	// distributions.
	private final void logProbabilityDistribution$sample256() {
		// Determine if we need to calculate the values for sample task 256 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample256) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(metric_valid_g[sample$var196][server][timeStep$var226]) {
							// An accumulator for log probabilities.
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							
							// An accumulator for the distributed probability space covered.
							double cv$probabilityReached = 0.0;
							
							// Look for paths between the variable and the sample task 256 including any distribution
							// values.
							// 
							// The sample value to calculate the probability of generating
							double cv$sampleValue = var245[sample$var196][server][timeStep$var226];
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(((0 == timeStep$var226) && (0 <= st[sample$var196][0]))) {
								// Enumerating the possible arguments for Gaussian 244.
								if(fixedFlag$sample57) {
									int var129 = st[sample$var196][0];
									if(((0 <= var129) && (var129 < noStates))) {
										// Substituted "timeStep$var226" with its value "0".
										double var243 = current_metric_var[server][st[sample$var196][0]];
										
										// Store the value of the function call, so the function call is only made once.
										// 
										// Substituted "timeStep$var226" with its value "0".
										cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var196][0]]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5));
										
										// Add the probability of this distribution configuration to the accumulator.
										// 
										// An accumulator for the distributed probability space covered.
										cv$probabilityReached = 1.0;
									}
								} else {
									// Enumerating the possible outputs of Categorical 54.
									for(int index$sample57$4 = 0; index$sample57$4 < noStates; index$sample57$4 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "sample$var45" with its value "sample$var196".
										double cv$probabilitySample57Value5 = distribution$sample57[sample$var196][index$sample57$4];
										int var129 = st[sample$var196][0];
										if(((0 <= var129) && (var129 < noStates))) {
											// Substituted "timeStep$var226" with its value "0".
											double var243 = current_metric_var[server][st[sample$var196][0]];
											
											// Store the value of the function call, so the function call is only made once.
											// 
											// Substituted "timeStep$var226" with its value "0".
											double cv$weightedProbability = ((Math.log(cv$probabilitySample57Value5) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var196][0]]) / Math.sqrt(var243)))) - (Math.log(var243) * 0.5));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value5);
										}
									}
								}
							}
							
							// Enumerating the possible arguments for Gaussian 244.
							if(((1 <= timeStep$var226) && (0 <= st[sample$var196][timeStep$var226]))) {
								// Enumerating the possible arguments for Gaussian 244.
								if(fixedFlag$sample76) {
									int var129 = st[sample$var196][timeStep$var226];
									if(((0 <= var129) && (var129 < noStates))) {
										double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var196][timeStep$var226]]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5));
										
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
									// Enumerating the possible outputs of Categorical 73.
									for(int index$sample76$49 = 0; index$sample76$49 < noStates; index$sample76$49 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "sample$var45" with its value "sample$var196".
										double cv$probabilitySample76Value50 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$49];
										int var129 = st[sample$var196][timeStep$var226];
										if(((0 <= var129) && (var129 < noStates))) {
											double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = ((Math.log(cv$probabilitySample76Value50) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var196][timeStep$var226]]) / Math.sqrt(var243)))) - (Math.log(var243) * 0.5));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value50);
										}
									}
								}
							}
							if((cv$probabilityReached == 0.0))
								// Return negative infinity if no distribution probability space is reached.
								cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							else
								// Scale the probability relative to the observed distribution space.
								cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
							
							// Add the probability of this sample task to the sample task accumulator.
							cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
						}
					}
				}
			}
			logProbability$var244 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var245 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_g = (logProbability$metric_g + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample256 = (((fixedFlag$sample57 && fixedFlag$sample76) && fixedFlag$sample134) && fixedFlag$sample162);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var244 = logProbability$var245;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_g = (logProbability$metric_g + logProbability$var245);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var245);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var245);
		}
	}

	// Calculate the probability of the samples represented by sample57 using probability
	// distributions.
	private final void logProbabilityDistribution$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample57) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample57) {
				// Generating probabilities for sample task
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[sample$var45][0];
					
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
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
				}
				logProbability$var54 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var55 = cv$sampleAccumulator;
				
				// Update the variable probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$st = (logProbability$st + cv$sampleAccumulator);
				
				// Add probability to model
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample57" with its value "true".
				fixedProbFlag$sample57 = fixedFlag$sample20;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var54 = logProbability$var55;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample57)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var55);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var55);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var55);
		}
	}

	// Calculate the probability of the samples represented by sample76 using probability
	// distributions.
	private final void logProbabilityDistribution$sample76() {
		// Determine if we need to calculate the values for sample task 76 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample76) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample76) {
				// Generating probabilities for sample task
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
					for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[sample$var45][timeStep$var66];
						
						// Enumerating the possible arguments for Categorical 73.
						if((1 == timeStep$var66)) {
							// Enumerating the possible arguments for Categorical 73.
							if(fixedFlag$sample57) {
								int var32 = st[sample$var45][0];
								
								// Substituted "timeStep$var66" with its value "1".
								if(((0 <= var32) && (var32 < noStates))) {
									// Store the value of the function call, so the function call is only made once.
									// 
									// Substituted "timeStep$var66" with its value "1".
									cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[sample$var45][0]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
									
									// Add the probability of this distribution configuration to the accumulator.
									// 
									// An accumulator for the distributed probability space covered.
									cv$probabilityReached = 1.0;
								}
							} else {
								// Enumerating the possible outputs of Categorical 54.
								for(int index$sample57$6 = 0; index$sample57$6 < noStates; index$sample57$6 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$5" with its value "sample$var45".
									double cv$probabilitySample57Value7 = distribution$sample57[sample$var45][index$sample57$6];
									int var32 = st[sample$var45][0];
									
									// Substituted "timeStep$var66" with its value "1".
									if(((0 <= var32) && (var32 < noStates))) {
										// Store the value of the function call, so the function call is only made once.
										// 
										// Substituted "timeStep$var66" with its value "1".
										double cv$weightedProbability = (Math.log(cv$probabilitySample57Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[sample$var45][0]][cv$sampleValue]):Double.NEGATIVE_INFINITY));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value7);
									}
								}
							}
						}
						
						// Substituted "index$sample$13_1" with its value "sample$var45".
						// 
						// Substituted "index$timeStep$13_2" with its value "(timeStep$var66 - 1)".
						if((2 <= timeStep$var66)) {
							int var32 = st[sample$var45][(timeStep$var66 - 1)];
							if(((0 <= var32) && (var32 < noStates))) {
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[sample$var45][(timeStep$var66 - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
								
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
						
						// Add the probability of this sample task to the sample task accumulator.
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
					}
				}
				logProbability$var73 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var74 = cv$sampleAccumulator;
				
				// Update the variable probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$st = (logProbability$st + cv$sampleAccumulator);
				
				// Add probability to model
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample76" with its value "true".
				fixedProbFlag$sample76 = (fixedFlag$sample33 && fixedFlag$sample57);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var73 = logProbability$var74;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample76)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var74);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var74);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample76)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var74);
		}
	}

	// Calculate the probability of the samples represented by sample134 using sampled
	// values.
	private final void logProbabilityValue$sample134() {
		// Determine if we need to calculate the values for sample task 134 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample134) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				for(int var129 = 0; var129 < noStates; var129 += 1) {
					// The sample value to calculate the probability of generating
					double cv$sampleValue = current_metric_mean[var119][var129];
					
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
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY));
				}
			}
			logProbability$var108 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var130 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample134)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample134 = fixedFlag$sample134;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var108 = logProbability$var130;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$current_metric_mean = (logProbability$current_metric_mean + logProbability$var130);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var130);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample134)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var130);
		}
	}

	// Calculate the probability of the samples represented by sample162 using sampled
	// values.
	private final void logProbabilityValue$sample162() {
		// Determine if we need to calculate the values for sample task 162 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample162) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				for(int var156 = 0; var156 < noStates; var156 += 1)
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
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(current_metric_var[var146][var156], 1.0, 1.0));
			}
			logProbability$var135 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var157 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample162)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample162 = fixedFlag$sample162;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var135 = logProbability$var157;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$current_metric_var = (logProbability$current_metric_var + logProbability$var157);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var157);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample162)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var157);
		}
	}

	// Calculate the probability of the samples represented by sample190 using sampled
	// values.
	private final void logProbabilityValue$sample190() {
		// Determine if we need to calculate the values for sample task 190 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample190) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				for(int var183 = 0; var183 < noStates; var183 += 1)
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
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(current_metric_valid_bias[var173][var183], 1.0, 1.0));
			}
			logProbability$var162 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var184 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample190)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample190 = fixedFlag$sample190;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var162 = logProbability$var184;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + logProbability$var184);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var184);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample190)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var184);
		}
	}

	// Calculate the probability of the samples represented by sample20 using sampled
	// values.
	private final void logProbabilityValue$sample20() {
		// Determine if we need to calculate the values for sample task 20 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample20) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v, noStates);
			
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
			if(fixedFlag$sample20)
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
			fixedProbFlag$sample20 = fixedFlag$sample20;
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
			if(fixedFlag$sample20)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	// Calculate the probability of the samples represented by sample241 using sampled
	// values.
	private final void logProbabilityValue$sample241() {
		// Determine if we need to calculate the values for sample task 241 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample241) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
						
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
						cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))));
					}
				}
			}
			logProbability$var231 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var232 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$sampleAccumulator);
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample241 = ((fixedFlag$sample57 && fixedFlag$sample76) && fixedFlag$sample190);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var231 = logProbability$var232;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + logProbability$var232);
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_valid_g = (logProbability$metric_valid_g + logProbability$var232);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var232);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var232);
		}
	}

	// Calculate the probability of the samples represented by sample256 using sampled
	// values.
	private final void logProbabilityValue$sample256() {
		// Determine if we need to calculate the values for sample task 256 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample256) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(metric_valid_g[sample$var196][server][timeStep$var226]) {
							double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
							
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
							cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((var245[sample$var196][server][timeStep$var226] - current_metric_mean[server][st[sample$var196][timeStep$var226]]) / Math.sqrt(var243)))) - (Math.log(var243) * 0.5));
						}
					}
				}
			}
			logProbability$var244 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var245 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_g = (logProbability$metric_g + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample256 = (((fixedFlag$sample57 && fixedFlag$sample76) && fixedFlag$sample134) && fixedFlag$sample162);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var244 = logProbability$var245;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_g = (logProbability$metric_g + logProbability$var245);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var245);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var245);
		}
	}

	// Calculate the probability of the samples represented by sample33 using sampled
	// values.
	private final void logProbabilityValue$sample33() {
		// Determine if we need to calculate the values for sample task 33 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample33) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var32 = 0; var32 < noStates; var32 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var32], v, noStates));
			logProbability$var21 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var33 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample33)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample33 = fixedFlag$sample33;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var21 = logProbability$var33;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var33);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var33);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample33)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var33);
		}
	}

	// Calculate the probability of the samples represented by sample57 using sampled
	// values.
	private final void logProbabilityValue$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample57) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[sample$var45][0];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var54 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var55 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$st = (logProbability$st + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedFlag$sample20);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var54 = logProbability$var55;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var55);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var55);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var55);
		}
	}

	// Calculate the probability of the samples represented by sample76 using sampled
	// values.
	private final void logProbabilityValue$sample76() {
		// Determine if we need to calculate the values for sample task 76 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample76) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[sample$var45][timeStep$var66];
					
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
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[sample$var45][(timeStep$var66 - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY));
				}
			}
			logProbability$var73 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var74 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$st = (logProbability$st + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample76)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample76 = ((fixedFlag$sample76 && fixedFlag$sample33) && fixedFlag$sample57);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var73 = logProbability$var74;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var74);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var74);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample76)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var74);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 134 drawn from Uniform 108. Inference was performed using Metropolis-Hastings.
	private final void sample134(int var119, int var129) {
		// The original value of the sample
		double cv$originalValue = current_metric_mean[var119][var129];
		
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
			// Substituted "cv$temp$0$var106" with its value "0.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue < (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
			
			// Processing random variable 244.
			// 
			// Looking for a path between Sample 134 and consumer Gaussian 244.
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((((var129 == st[sample$var196][0]) && (0 < length$metric[sample$var196][0])) && metric_valid_g[sample$var196][var119][0])) {
					if(fixedFlag$sample57) {
						// Processing sample task 256 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var156 = st[sample$var196][0];
						
						// Substituted "server" with its value "var119".
						if(((0 <= var156) && (var156 < noStates))) {
							// Variable declaration of cv$temp$3$var243 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							double cv$temp$3$var243 = current_metric_var[var119][st[sample$var196][0]];
							
							// Substituted "server" with its value "var119".
							// 
							// cv$temp$2$var241's comment
							// Variable declaration of cv$temp$2$var241 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$originalValue) / Math.sqrt(cv$temp$3$var243))) - (Math.log(cv$temp$3$var243) * 0.5));
							
							// Recorded the probability of reaching sample task 256 with the current configuration.
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
					} else {
						// Enumerating the possible outputs of Categorical 54.
						for(int index$sample57$7 = 0; index$sample57$7 < noStates; index$sample57$7 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "sample$var45" with its value "sample$var196".
							double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][index$sample57$7];
							
							// Processing sample task 256 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
							// the output of Sample task 134.
							int var156 = st[sample$var196][0];
							
							// Substituted "server" with its value "var119".
							if(((0 <= var156) && (var156 < noStates))) {
								// Variable declaration of cv$temp$9$var243 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								double cv$temp$9$var243 = current_metric_var[var119][st[sample$var196][0]];
								
								// Substituted "server" with its value "var119".
								// 
								// cv$temp$8$var241's comment
								// Variable declaration of cv$temp$8$var241 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value8) + DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$originalValue) / Math.sqrt(cv$temp$9$var243)))) - (Math.log(cv$temp$9$var243) * 0.5));
								
								// Recorded the probability of reaching sample task 256 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value8);
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
			}
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					// Substituted "server" with its value "var119".
					if((metric_valid_g[sample$var196][var119][timeStep$var226] && (var129 == st[sample$var196][timeStep$var226]))) {
						if(fixedFlag$sample76) {
							// Processing sample task 256 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var156 = st[sample$var196][timeStep$var226];
							
							// Substituted "server" with its value "var119".
							if(((0 <= var156) && (var156 < noStates))) {
								// Variable declaration of cv$temp$21$var243 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								double cv$temp$21$var243 = current_metric_var[var119][st[sample$var196][timeStep$var226]];
								
								// Substituted "server" with its value "var119".
								// 
								// cv$temp$20$var241's comment
								// Variable declaration of cv$temp$20$var241 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$originalValue) / Math.sqrt(cv$temp$21$var243))) - (Math.log(cv$temp$21$var243) * 0.5));
								
								// Recorded the probability of reaching sample task 256 with the current configuration.
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
						} else {
							// Enumerating the possible outputs of Categorical 73.
							for(int index$sample76$19 = 0; index$sample76$19 < noStates; index$sample76$19 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "sample$var45" with its value "sample$var196".
								double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$19];
								
								// Processing sample task 256 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var156 = st[sample$var196][timeStep$var226];
								
								// Substituted "server" with its value "var119".
								if(((0 <= var156) && (var156 < noStates))) {
									// Variable declaration of cv$temp$27$var243 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var119".
									double cv$temp$27$var243 = current_metric_var[var119][st[sample$var196][timeStep$var226]];
									
									// Substituted "server" with its value "var119".
									// 
									// cv$temp$26$var241's comment
									// Variable declaration of cv$temp$26$var241 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample76Value20) + DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$originalValue) / Math.sqrt(cv$temp$27$var243)))) - (Math.log(cv$temp$27$var243) * 0.5));
									
									// Recorded the probability of reaching sample task 256 with the current configuration.
									// 
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample76Value20);
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
		current_metric_mean[var119][var129] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$var106" with its value "0.0".
		double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
		
		// Processing random variable 244.
		// 
		// Looking for a path between Sample 134 and consumer Gaussian 244.
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((((var129 == st[sample$var196][0]) && (0 < length$metric[sample$var196][0])) && metric_valid_g[sample$var196][var119][0])) {
				if(fixedFlag$sample57) {
					// Processing sample task 256 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var156 = st[sample$var196][0];
					
					// Substituted "server" with its value "var119".
					if(((0 <= var156) && (var156 < noStates))) {
						// Variable declaration of cv$temp$3$var243 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var119".
						double cv$temp$3$var243 = current_metric_var[var119][st[sample$var196][0]];
						
						// Substituted "server" with its value "var119".
						// 
						// cv$temp$2$var241's comment
						// Variable declaration of cv$temp$2$var241 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var243))) - (Math.log(cv$temp$3$var243) * 0.5));
						
						// Recorded the probability of reaching sample task 256 with the current configuration.
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
				} else {
					// Enumerating the possible outputs of Categorical 54.
					for(int index$sample57$7 = 0; index$sample57$7 < noStates; index$sample57$7 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "sample$var45" with its value "sample$var196".
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][index$sample57$7];
						
						// Processing sample task 256 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
						// the output of Sample task 134.
						int var156 = st[sample$var196][0];
						
						// Substituted "server" with its value "var119".
						if(((0 <= var156) && (var156 < noStates))) {
							// Variable declaration of cv$temp$9$var243 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							double cv$temp$9$var243 = current_metric_var[var119][st[sample$var196][0]];
							
							// Substituted "server" with its value "var119".
							// 
							// cv$temp$8$var241's comment
							// Variable declaration of cv$temp$8$var241 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value8) + DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var243)))) - (Math.log(cv$temp$9$var243) * 0.5));
							
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value8);
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
		}
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
				// Substituted "server" with its value "var119".
				if((metric_valid_g[sample$var196][var119][timeStep$var226] && (var129 == st[sample$var196][timeStep$var226]))) {
					if(fixedFlag$sample76) {
						// Processing sample task 256 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var156 = st[sample$var196][timeStep$var226];
						
						// Substituted "server" with its value "var119".
						if(((0 <= var156) && (var156 < noStates))) {
							// Variable declaration of cv$temp$21$var243 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							double cv$temp$21$var243 = current_metric_var[var119][st[sample$var196][timeStep$var226]];
							
							// Substituted "server" with its value "var119".
							// 
							// cv$temp$20$var241's comment
							// Variable declaration of cv$temp$20$var241 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$proposedValue) / Math.sqrt(cv$temp$21$var243))) - (Math.log(cv$temp$21$var243) * 0.5));
							
							// Recorded the probability of reaching sample task 256 with the current configuration.
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
					} else {
						// Enumerating the possible outputs of Categorical 73.
						for(int index$sample76$19 = 0; index$sample76$19 < noStates; index$sample76$19 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "sample$var45" with its value "sample$var196".
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$19];
							
							// Processing sample task 256 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var156 = st[sample$var196][timeStep$var226];
							
							// Substituted "server" with its value "var119".
							if(((0 <= var156) && (var156 < noStates))) {
								// Variable declaration of cv$temp$27$var243 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								double cv$temp$27$var243 = current_metric_var[var119][st[sample$var196][timeStep$var226]];
								
								// Substituted "server" with its value "var119".
								// 
								// cv$temp$26$var241's comment
								// Variable declaration of cv$temp$26$var241 moved.
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample76Value20) + DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$proposedValue) / Math.sqrt(cv$temp$27$var243)))) - (Math.log(cv$temp$27$var243) * 0.5));
								
								// Recorded the probability of reaching sample task 256 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample76Value20);
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
			current_metric_mean[var119][var129] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 162 drawn from InverseGamma 135. Inference was performed using Metropolis-Hastings.
	private final void sample162(int var146, int var156) {
		// The original value of the sample
		double cv$originalValue = current_metric_var[var146][var156];
		
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
			// Substituted "cv$temp$1$var134" with its value "1.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			
			// Processing random variable 244.
			// 
			// Looking for a path between Sample 162 and consumer Gaussian 244.
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((((var156 == st[sample$var196][0]) && (0 < length$metric[sample$var196][0])) && metric_valid_g[sample$var196][var146][0])) {
					if(fixedFlag$sample57) {
						// Processing sample task 256 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var129 = st[sample$var196][0];
						
						// Substituted "server" with its value "var146".
						if(((0 <= var129) && (var129 < noStates))) {
							// Substituted "server" with its value "var146".
							// 
							// cv$temp$2$var241's comment
							// Variable declaration of cv$temp$2$var241 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var146".
							// 
							// cv$temp$3$var243's comment
							// Variable declaration of cv$temp$3$var243 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][st[sample$var196][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							
							// Recorded the probability of reaching sample task 256 with the current configuration.
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
					} else {
						// Enumerating the possible outputs of Categorical 54.
						for(int index$sample57$7 = 0; index$sample57$7 < noStates; index$sample57$7 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "sample$var45" with its value "sample$var196".
							double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][index$sample57$7];
							
							// Processing sample task 256 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
							// the output of Sample task 162.
							int var129 = st[sample$var196][0];
							
							// Substituted "server" with its value "var146".
							if(((0 <= var129) && (var129 < noStates))) {
								// Substituted "server" with its value "var146".
								// 
								// cv$temp$8$var241's comment
								// Variable declaration of cv$temp$8$var241 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var146".
								// 
								// cv$temp$9$var243's comment
								// Variable declaration of cv$temp$9$var243 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value8) + DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][st[sample$var196][0]]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
								
								// Recorded the probability of reaching sample task 256 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value8);
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
			}
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					// Substituted "server" with its value "var146".
					if((metric_valid_g[sample$var196][var146][timeStep$var226] && (var156 == st[sample$var196][timeStep$var226]))) {
						if(fixedFlag$sample76) {
							// Processing sample task 256 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var129 = st[sample$var196][timeStep$var226];
							
							// Substituted "server" with its value "var146".
							if(((0 <= var129) && (var129 < noStates))) {
								// Substituted "server" with its value "var146".
								// 
								// cv$temp$21$var243's comment
								// Variable declaration of cv$temp$21$var243 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
								
								// Recorded the probability of reaching sample task 256 with the current configuration.
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
						} else {
							// Enumerating the possible outputs of Categorical 73.
							for(int index$sample76$19 = 0; index$sample76$19 < noStates; index$sample76$19 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "sample$var45" with its value "sample$var196".
								double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$19];
								
								// Processing sample task 256 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var129 = st[sample$var196][timeStep$var226];
								
								// Substituted "server" with its value "var146".
								if(((0 <= var129) && (var129 < noStates))) {
									// Substituted "server" with its value "var146".
									// 
									// cv$temp$27$var243's comment
									// Variable declaration of cv$temp$27$var243 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample76Value20) + DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
									
									// Recorded the probability of reaching sample task 256 with the current configuration.
									// 
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample76Value20);
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
		current_metric_var[var146][var156] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var134" with its value "1.0".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
		
		// Processing random variable 244.
		// 
		// Looking for a path between Sample 162 and consumer Gaussian 244.
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((((var156 == st[sample$var196][0]) && (0 < length$metric[sample$var196][0])) && metric_valid_g[sample$var196][var146][0])) {
				if(fixedFlag$sample57) {
					// Processing sample task 256 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var129 = st[sample$var196][0];
					
					// Substituted "server" with its value "var146".
					if(((0 <= var129) && (var129 < noStates))) {
						// Substituted "server" with its value "var146".
						// 
						// cv$temp$2$var241's comment
						// Variable declaration of cv$temp$2$var241 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var146".
						// 
						// cv$temp$3$var243's comment
						// Variable declaration of cv$temp$3$var243 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][st[sample$var196][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Recorded the probability of reaching sample task 256 with the current configuration.
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
				} else {
					// Enumerating the possible outputs of Categorical 54.
					for(int index$sample57$7 = 0; index$sample57$7 < noStates; index$sample57$7 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "sample$var45" with its value "sample$var196".
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][index$sample57$7];
						
						// Processing sample task 256 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
						// the output of Sample task 162.
						int var129 = st[sample$var196][0];
						
						// Substituted "server" with its value "var146".
						if(((0 <= var129) && (var129 < noStates))) {
							// Substituted "server" with its value "var146".
							// 
							// cv$temp$8$var241's comment
							// Variable declaration of cv$temp$8$var241 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var146".
							// 
							// cv$temp$9$var243's comment
							// Variable declaration of cv$temp$9$var243 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value8) + DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][st[sample$var196][0]]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
							
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value8);
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
		}
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
				// Substituted "server" with its value "var146".
				if((metric_valid_g[sample$var196][var146][timeStep$var226] && (var156 == st[sample$var196][timeStep$var226]))) {
					if(fixedFlag$sample76) {
						// Processing sample task 256 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var129 = st[sample$var196][timeStep$var226];
						
						// Substituted "server" with its value "var146".
						if(((0 <= var129) && (var129 < noStates))) {
							// Substituted "server" with its value "var146".
							// 
							// cv$temp$21$var243's comment
							// Variable declaration of cv$temp$21$var243 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
							
							// Recorded the probability of reaching sample task 256 with the current configuration.
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
					} else {
						// Enumerating the possible outputs of Categorical 73.
						for(int index$sample76$19 = 0; index$sample76$19 < noStates; index$sample76$19 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "sample$var45" with its value "sample$var196".
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$19];
							
							// Processing sample task 256 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var129 = st[sample$var196][timeStep$var226];
							
							// Substituted "server" with its value "var146".
							if(((0 <= var129) && (var129 < noStates))) {
								// Substituted "server" with its value "var146".
								// 
								// cv$temp$27$var243's comment
								// Variable declaration of cv$temp$27$var243 moved.
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample76Value20) + DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
								
								// Recorded the probability of reaching sample task 256 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample76Value20);
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
			current_metric_var[var146][var156] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 190 drawn from Beta 162. Inference was performed using a Beta to
	// Bernoulli/Binomial conjugate prior.
	private final void sample190(int var173, int var183) {
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		
		// Processing random variable 231.
		// 
		// Looking for a path between Sample 190 and consumer Bernoulli 231.
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((var183 == st[sample$var196][0]) && (0 < length$metric[sample$var196][0]))) {
				if(fixedFlag$sample57) {
					// Processing sample task 241 of consumer random variable null.
					// 
					// Include the value sampled by task 241 from random variable var231.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + 1.0);
					
					// If the sample value was positive increase the count
					// 
					// Substituted "server" with its value "var173".
					if(metric_valid_g[sample$var196][var173][0])
						cv$sum = (cv$sum + 1.0);
				} else {
					// Enumerating the possible outputs of Categorical 54.
					for(int index$sample57$6 = 0; index$sample57$6 < noStates; index$sample57$6 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "sample$var45" with its value "sample$var196".
						double cv$probabilitySample57Value7 = distribution$sample57[sample$var196][index$sample57$6];
						
						// Processing sample task 241 of consumer random variable null.
						// 
						// Include the value sampled by task 241 from random variable var231.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + cv$probabilitySample57Value7);
						
						// If the sample value was positive increase the count
						// 
						// Substituted "server" with its value "var173".
						if(metric_valid_g[sample$var196][var173][0])
							cv$sum = (cv$sum + cv$probabilitySample57Value7);
					}
				}
			}
		}
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
				if((var183 == st[sample$var196][timeStep$var226])) {
					if(fixedFlag$sample76) {
						// Processing sample task 241 of consumer random variable null.
						// 
						// Include the value sampled by task 241 from random variable var231.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						// 
						// Substituted "server" with its value "var173".
						if(metric_valid_g[sample$var196][var173][timeStep$var226])
							cv$sum = (cv$sum + 1.0);
					} else {
						// Enumerating the possible outputs of Categorical 73.
						for(int index$sample76$18 = 0; index$sample76$18 < noStates; index$sample76$18 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "sample$var45" with its value "sample$var196".
							double cv$probabilitySample76Value19 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$18];
							
							// Processing sample task 241 of consumer random variable null.
							// 
							// Include the value sampled by task 241 from random variable var231.
							// 
							// Increment the number of samples.
							cv$count = (cv$count + cv$probabilitySample76Value19);
							
							// If the sample value was positive increase the count
							// 
							// Substituted "server" with its value "var173".
							if(metric_valid_g[sample$var196][var173][timeStep$var226])
								cv$sum = (cv$sum + cv$probabilitySample76Value19);
						}
					}
				}
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		current_metric_valid_bias[var173][var183] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 20 drawn from Dirichlet 19. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample20() {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var20$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample57) {
			// Processing random variable 54.
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1)
				// Processing sample task 57 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 57 of random
				// variable var54
				// 
				// A local reference to the scratch space.
				cv$var20$countGlobal[st[sample$var45][0]] = (cv$var20$countGlobal[st[sample$var45][0]] + 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				// Processing sample task 57 of consumer random variable null.
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
					cv$var20$countGlobal[cv$loopIndex] = (cv$var20$countGlobal[cv$loopIndex] + distribution$sample57[sample$var45][cv$loopIndex]);
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var20$countGlobal, initialStateDistribution, noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 33 drawn from Dirichlet 21. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample33(int var32) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var33$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample76) {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((var32 == st[sample$var45][0]) && (1 < length$metric[sample$var45][0]))) {
					if(fixedFlag$sample57)
						// Increment the sample counter with the value sampled by sample task 76 of random
						// variable var73
						// 
						// A local reference to the scratch space.
						cv$var33$countGlobal[st[sample$var45][1]] = (cv$var33$countGlobal[st[sample$var45][1]] + 1.0);
					else {
						// Enumerating the possible outputs of Categorical 54.
						for(int index$sample57$5 = 0; index$sample57$5 < noStates; index$sample57$5 += 1)
							// Increment the sample counter with the value sampled by sample task 76 of random
							// variable var73
							// 
							// A local reference to the scratch space.
							cv$var33$countGlobal[st[sample$var45][1]] = (cv$var33$countGlobal[st[sample$var45][1]] + distribution$sample57[sample$var45][index$sample57$5]);
					}
				}
			}
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 2; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					if((var32 == st[sample$var45][(timeStep$var66 - 1)]))
						// Processing sample task 76 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 76 of random
						// variable var73
						// 
						// A local reference to the scratch space.
						cv$var33$countGlobal[st[sample$var45][timeStep$var66]] = (cv$var33$countGlobal[st[sample$var45][timeStep$var66]] + 1.0);
				}
			}
		}
		
		// Processing random variable 73.
		// 
		// Looking for a path between Sample 33 and consumer Categorical 73.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((var32 == st[sample$var45][0]) && (1 < length$metric[sample$var45][0]))) {
					if(fixedFlag$sample57) {
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							// A local reference to the scratch space.
							// 
							// Add the probability of this argument configuration.
							// 
							// Declare and zero an accumulator for tracking the reached source probability space.
							cv$var33$countGlobal[cv$loopIndex] = (cv$var33$countGlobal[cv$loopIndex] + distribution$sample76[sample$var45][0][cv$loopIndex]);
					} else {
						// Enumerating the possible outputs of Categorical 54.
						for(int index$sample57$42 = 0; index$sample57$42 < noStates; index$sample57$42 += 1) {
							// The probability of reaching the consumer with this set of consumer arguments
							// 
							// Substituted "index$sample$41" with its value "sample$var45".
							// 
							// Add the probability of this argument configuration.
							// 
							// Declare and zero an accumulator for tracking the reached source probability space.
							double cv$distributionProbability = distribution$sample57[sample$var45][index$sample57$42];
							
							// Merge the distribution probabilities into the count
							// 
							// Get the length of the array
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								// A local reference to the scratch space.
								cv$var33$countGlobal[cv$loopIndex] = (cv$var33$countGlobal[cv$loopIndex] + (distribution$sample76[sample$var45][0][cv$loopIndex] * cv$distributionProbability));
						}
					}
				}
			}
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
						int index$timeStep$52 = (timeStep$var66 - 1);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Substituted "index$sample$51" with its value "sample$var45".
						if((1 <= index$timeStep$52)) {
							// Enumerating the possible outputs of Categorical 73.
							for(int index$sample76$53 = 0; index$sample76$53 < noStates; index$sample76$53 += 1) {
								// The probability of reaching the consumer with this set of consumer arguments
								// 
								// Substituted "index$sample$51" with its value "sample$var45".
								// 
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								double cv$distributionProbability = distribution$sample76[sample$var45][(index$timeStep$52 - 1)][index$sample76$53];
								
								// Merge the distribution probabilities into the count
								// 
								// Get the length of the array
								for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
									// A local reference to the scratch space.
									cv$var33$countGlobal[cv$loopIndex] = (cv$var33$countGlobal[cv$loopIndex] + (distribution$sample76[sample$var45][(timeStep$var66 - 1)][cv$loopIndex] * cv$distributionProbability));
							}
						}
					}
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var33$countGlobal, m[var32], noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 57 drawn from Categorical 54. Inference was performed using variable
	// marginalization.
	private final void sample57(int sample$var45) {
		// Variable declaration of cv$numNumStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$numNumStates's comment
		// Calculate the number of states to evaluate.
		int cv$numNumStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$initialStateDistribution" with its value "initialStateDistribution".
			// 
			// cv$temp$1$$var654's comment
			// 
			// $var654's comment
			// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = ((cv$valuePos < noStates)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample76 && (1 < length$metric[sample$var45][0]))) {
				// Looking for a path between Sample 57 and consumer Categorical 73.
				// Processing sample task 76 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				int var32 = st[sample$var45][0];
				
				// Substituted "index$sample$3_2" with its value "sample$var45".
				if(((0 <= var32) && (var32 < noStates))) {
					// Substituted "index$sample$3_2" with its value "sample$var45".
					// 
					// cv$temp$3$$var667's comment
					// 
					// $var667's comment
					// Constructing a random variable input for use later.
					// 
					// cv$temp$2$var72's comment
					// Variable declaration of cv$temp$2$var72 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					cv$accumulatedConsumerProbabilities = (((0.0 <= st[sample$var45][1]) && (st[sample$var45][1] < noStates))?Math.log(m[cv$valuePos][st[sample$var45][1]]):Double.NEGATIVE_INFINITY);
					
					// Recorded the probability of reaching sample task 76 with the current configuration.
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
			if((0 < length$metric[sample$var45][0])) {
				// Processing random variable 231.
				// 
				// Looking for a path between Sample 57 and consumer Bernoulli 231.
				for(int server = 0; server < noServers; server += 1) {
					// Processing sample task 241 of consumer random variable null.
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var183 = st[sample$var45][0];
					if(((0 <= var183) && (var183 < noStates))) {
						// Variable declaration of cv$temp$4$var230 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$4$var230 = current_metric_valid_bias[server][cv$valuePos];
						
						// Substituted "sample$var196" with its value "sample$var45".
						cv$accumulatedConsumerProbabilities = Math.log((metric_valid_g[sample$var45][server][0]?cv$temp$4$var230:(1.0 - cv$temp$4$var230)));
						
						// Recorded the probability of reaching sample task 241 with the current configuration.
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
				for(int server = 0; server < noServers; server += 1) {
					// Substituted "sample$var196" with its value "sample$var45".
					if(metric_valid_g[sample$var45][server][0])
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample57gaussian255$global[sample$var45][server][0] = false;
				}
				for(int server = 0; server < noServers; server += 1) {
					// Substituted "sample$var196" with its value "sample$var45".
					if(metric_valid_g[sample$var45][server][0])
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample57gaussian255$global[sample$var45][server][0] = false;
				}
				for(int server = 0; server < noServers; server += 1) {
					// Substituted "sample$var196" with its value "sample$var45".
					if((metric_valid_g[sample$var45][server][0] && !guard$sample57gaussian255$global[sample$var45][server][0])) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample57gaussian255$global[sample$var45][server][0] = true;
						
						// Processing sample task 256 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[sample$var45][0])) {
							int var129 = st[sample$var45][0];
							if(((0 <= var129) && (var129 < noStates))) {
								// Variable declaration of cv$temp$6$var243 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$6$var243 = current_metric_var[server][cv$valuePos];
								
								// Substituted "sample$var196" with its value "sample$var45".
								// 
								// cv$temp$5$var241's comment
								// Variable declaration of cv$temp$5$var241 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$6$var243))) - (Math.log(cv$temp$6$var243) * 0.5));
								
								// Recorded the probability of reaching sample task 256 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
							}
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
					// Substituted "sample$var196" with its value "sample$var45".
					if((metric_valid_g[sample$var45][server][0] && !guard$sample57gaussian255$global[sample$var45][server][0])) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample57gaussian255$global[sample$var45][server][0] = true;
						
						// Processing sample task 256 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[sample$var45][0])) {
							int var129 = st[sample$var45][0];
							if(((0 <= var129) && (var129 < noStates))) {
								// Variable declaration of cv$temp$14$var243 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
								// the output of Sample task 57.
								// 
								// Value of the variable at this index
								double cv$temp$14$var243 = current_metric_var[server][cv$valuePos];
								
								// Substituted "sample$var196" with its value "sample$var45".
								// 
								// cv$temp$13$var241's comment
								// Variable declaration of cv$temp$13$var241 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
								// the output of Sample task 57.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$14$var243))) - (Math.log(cv$temp$14$var243) * 0.5));
								
								// Recorded the probability of reaching sample task 256 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
							}
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
			if((!fixedFlag$sample76 && (1 < length$metric[sample$var45][0]))) {
				// Looking for a path between Sample 57 and consumer Categorical 73.
				// Processing sample task 76 of consumer random variable null.
				// 
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var73[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				int var32 = st[sample$var45][0];
				
				// Substituted "index$sample$67_2" with its value "sample$var45".
				if(((0 <= var32) && (var32 < noStates))) {
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
					// cv$temp$22$$var880's comment
					// 
					// $var880's comment
					// Constructing a random variable input for use later.
					// 
					// cv$temp$21$var72's comment
					// Variable declaration of cv$temp$21$var72 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var73, 1.0, m[cv$valuePos], noStates);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$sample$67_2" with its value "sample$var45".
				double[] cv$sampleDistribution = distribution$sample76[sample$var45][0];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					double cv$normalisedDistValue = (cv$distributionAccumulator$var73[cv$i] / cv$reachedDistributionProbability);
					
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
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var55$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample57[sample$var45];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var55$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var55$stateProbabilityGlobal[cv$lseIndex];
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var55$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = Math.exp((cv$var55$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var55$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 76 drawn from Categorical 73. Inference was performed using variable
	// marginalization.
	private final void sample76(int sample$var45, int timeStep$var66) {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		
		// Enumerating the possible arguments for Categorical 73.
		if((1 == timeStep$var66)) {
			// Enumerating the possible arguments for Categorical 73.
			if(fixedFlag$sample57) {
				int var32 = st[sample$var45][0];
				
				// Substituted "timeStep$var66" with its value "1".
				if(((0 <= var32) && (var32 < noStates)))
					// variable marginalization
					// 
					// cv$numNumStates's comment
					// Calculate the number of states to evaluate.
					cv$numNumStates = Math.max(0, noStates);
			} else {
				// Enumerating the possible outputs of Categorical 54.
				if((0 < noStates)) {
					int var32 = st[sample$var45][0];
					
					// Substituted "timeStep$var66" with its value "1".
					if(((0 <= var32) && (var32 < noStates)))
						// variable marginalization
						cv$numNumStates = noStates;
				}
			}
		}
		if(fixedFlag$sample76) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((2 <= timeStep$var66)) {
				int var32 = st[sample$var45][(timeStep$var66 - 1)];
				if(((0 <= var32) && (var32 < noStates)))
					// variable marginalization
					cv$numNumStates = Math.max(cv$numNumStates, noStates);
			}
		} else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < noStates)) {
				int index$timeStep$14 = (timeStep$var66 - 1);
				
				// index$timeStep$1's comment
				// Exploring all the possible state counts for random variable 73.
				// 
				// Copy of index so that its values can be safely substituted
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var66 - 1)".
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var66 - 1)".
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var66 - 1)".
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var66 - 1)".
				if(((1 <= index$timeStep$14) && !(index$timeStep$14 == timeStep$var66))) {
					int var32 = st[sample$var45][(timeStep$var66 - 1)];
					if(((0 <= var32) && (var32 < noStates)))
						// variable marginalization
						cv$numNumStates = Math.max(cv$numNumStates, noStates);
				}
			}
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 73 creating
			// sample task 76.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Enumerating the possible arguments for Categorical 73.
			if((1 == timeStep$var66)) {
				// Enumerating the possible arguments for Categorical 73.
				if(fixedFlag$sample57) {
					int var32 = st[sample$var45][0];
					
					// Substituted "timeStep$var66" with its value "1".
					if(((0 <= var32) && (var32 < noStates))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						// 
						// cv$temp$1$$var966's comment
						// 
						// $var966's comment
						// Constructing a random variable input for use later.
						// 
						// cv$temp$0$var72's comment
						// Variable declaration of cv$temp$0$var72 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "timeStep$var66" with its value "1".
						double cv$accumulatedProbabilities = ((cv$valuePos < noStates)?Math.log(m[st[sample$var45][0]][cv$valuePos]):Double.NEGATIVE_INFINITY);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < length$metric[sample$var45][0])) {
							// Processing random variable 231.
							// 
							// Looking for a path between Sample 76 and consumer Bernoulli 231.
							for(int server = 0; server < noServers; server += 1) {
								// Processing sample task 241 of consumer random variable null.
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Substituted "timeStep$var226" with its value "1".
								int var183 = st[sample$var45][1];
								if(((0 <= var183) && (var183 < noStates))) {
									// Variable declaration of cv$temp$8$var230 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double cv$temp$8$var230 = current_metric_valid_bias[server][cv$valuePos];
									
									// Substituted "sample$var196" with its value "sample$var45".
									// 
									// Substituted "timeStep$var226" with its value "1".
									cv$accumulatedConsumerProbabilities = Math.log((metric_valid_g[sample$var45][server][1]?cv$temp$8$var230:(1.0 - cv$temp$8$var230)));
									
									// Recorded the probability of reaching sample task 241 with the current configuration.
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
							for(int server = 0; server < noServers; server += 1) {
								// Substituted "sample$var196" with its value "sample$var45".
								// 
								// Substituted "timeStep$var226" with its value "1".
								if(metric_valid_g[sample$var45][server][1])
									// Set the flags to false
									// 
									// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var226" with its value "1".
									guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								// Substituted "sample$var196" with its value "sample$var45".
								// 
								// Substituted "timeStep$var226" with its value "1".
								if(metric_valid_g[sample$var45][server][1])
									// Set the flags to false
									// 
									// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var226" with its value "1".
									guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								// Substituted "sample$var196" with its value "sample$var45".
								// 
								// Substituted "timeStep$var226" with its value "1".
								if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255$global[sample$var45][server][1])) {
									// The body will execute, so should not be executed again
									// 
									// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var226" with its value "1".
									guard$sample76gaussian255$global[sample$var45][server][1] = true;
									
									// Processing sample task 256 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((0 <= st[sample$var45][1])) {
										// Substituted "timeStep$var226" with its value "1".
										int var129 = st[sample$var45][1];
										if(((0 <= var129) && (var129 < noStates))) {
											// Variable declaration of cv$temp$15$var243 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Value of the variable at this index
											double cv$temp$15$var243 = current_metric_var[server][cv$valuePos];
											
											// Substituted "sample$var196" with its value "sample$var45".
											// 
											// Substituted "timeStep$var226" with its value "1".
											// 
											// cv$temp$14$var241's comment
											// Variable declaration of cv$temp$14$var241 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Value of the variable at this index
											cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$15$var243))) - (Math.log(cv$temp$15$var243) * 0.5));
											
											// Recorded the probability of reaching sample task 256 with the current configuration.
											// 
											// Set an accumulator to record the consumer distributions not seen. Initially set
											// to 1 as seen values will be deducted from this value.
											cv$consumerDistributionProbabilityAccumulator = 0.0;
										}
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
								// Substituted "sample$var196" with its value "sample$var45".
								// 
								// Substituted "timeStep$var226" with its value "1".
								if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255$global[sample$var45][server][1])) {
									// The body will execute, so should not be executed again
									// 
									// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var226" with its value "1".
									guard$sample76gaussian255$global[sample$var45][server][1] = true;
									
									// Processing sample task 256 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((0 <= st[sample$var45][1])) {
										// Substituted "timeStep$var226" with its value "1".
										int var129 = st[sample$var45][1];
										if(((0 <= var129) && (var129 < noStates))) {
											// Variable declaration of cv$temp$47$var243 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
											// the output of Sample task 76.
											// 
											// Value of the variable at this index
											double cv$temp$47$var243 = current_metric_var[server][cv$valuePos];
											
											// Substituted "sample$var196" with its value "sample$var45".
											// 
											// Substituted "timeStep$var226" with its value "1".
											// 
											// cv$temp$46$var241's comment
											// Variable declaration of cv$temp$46$var241 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
											// the output of Sample task 76.
											// 
											// Value of the variable at this index
											cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$47$var243))) - (Math.log(cv$temp$47$var243) * 0.5));
											
											// Recorded the probability of reaching sample task 256 with the current configuration.
											// 
											// Set an accumulator to record the consumer distributions not seen. Initially set
											// to 1 as seen values will be deducted from this value.
											cv$consumerDistributionProbabilityAccumulator = 0.0;
										}
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
					// Enumerating the possible outputs of Categorical 54.
					for(int index$sample57$26 = 0; index$sample57$26 < noStates; index$sample57$26 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$25" with its value "sample$var45".
						double cv$probabilitySample57Value27 = distribution$sample57[sample$var45][index$sample57$26];
						int var32 = st[sample$var45][0];
						
						// Substituted "timeStep$var66" with its value "1".
						if(((0 <= var32) && (var32 < noStates))) {
							// Record the reached probability density.
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value27);
							
							// An accumulator to allow the value for each distribution to be constructed before
							// it is added to the index probabilities.
							// 
							// Value of the variable at this index
							// 
							// cv$temp$3$$var967's comment
							// 
							// $var967's comment
							// Constructing a random variable input for use later.
							// 
							// cv$temp$2$var72's comment
							// Variable declaration of cv$temp$2$var72 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "timeStep$var66" with its value "1".
							double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value27) + ((cv$valuePos < noStates)?Math.log(m[st[sample$var45][0]][cv$valuePos]):Double.NEGATIVE_INFINITY));
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((1 < length$metric[sample$var45][0])) {
								// Processing random variable 231.
								// 
								// Looking for a path between Sample 76 and consumer Bernoulli 231.
								for(int server = 0; server < noServers; server += 1) {
									// Processing sample task 241 of consumer random variable null.
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Substituted "timeStep$var226" with its value "1".
									int var183 = st[sample$var45][1];
									if(((0 <= var183) && (var183 < noStates))) {
										// Variable declaration of cv$temp$9$var230 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double cv$temp$9$var230 = current_metric_valid_bias[server][cv$valuePos];
										
										// Substituted "sample$var196" with its value "sample$var45".
										// 
										// Substituted "timeStep$var226" with its value "1".
										cv$accumulatedConsumerProbabilities = Math.log((metric_valid_g[sample$var45][server][1]?cv$temp$9$var230:(1.0 - cv$temp$9$var230)));
										
										// Recorded the probability of reaching sample task 241 with the current configuration.
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
								for(int server = 0; server < noServers; server += 1) {
									// Substituted "sample$var196" with its value "sample$var45".
									// 
									// Substituted "timeStep$var226" with its value "1".
									if(metric_valid_g[sample$var45][server][1])
										// Set the flags to false
										// 
										// Guard to check that at most one copy of the code is executed for a given random
										// variable instance.
										// 
										// Substituted "timeStep$var226" with its value "1".
										guard$sample76gaussian255$global[sample$var45][server][1] = false;
								}
								for(int server = 0; server < noServers; server += 1) {
									// Substituted "sample$var196" with its value "sample$var45".
									// 
									// Substituted "timeStep$var226" with its value "1".
									if(metric_valid_g[sample$var45][server][1])
										// Set the flags to false
										// 
										// Guard to check that at most one copy of the code is executed for a given random
										// variable instance.
										// 
										// Substituted "timeStep$var226" with its value "1".
										guard$sample76gaussian255$global[sample$var45][server][1] = false;
								}
								for(int server = 0; server < noServers; server += 1) {
									// Substituted "sample$var196" with its value "sample$var45".
									// 
									// Substituted "timeStep$var226" with its value "1".
									if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255$global[sample$var45][server][1])) {
										// The body will execute, so should not be executed again
										// 
										// Guard to check that at most one copy of the code is executed for a given random
										// variable instance.
										// 
										// Substituted "timeStep$var226" with its value "1".
										guard$sample76gaussian255$global[sample$var45][server][1] = true;
										
										// Processing sample task 256 of consumer random variable null.
										// 
										// Set an accumulator to sum the probabilities for each possible configuration of
										// inputs.
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										
										// Constraints moved from conditionals in inner loops/scopes/etc.
										if((0 <= st[sample$var45][1])) {
											// Substituted "timeStep$var226" with its value "1".
											int var129 = st[sample$var45][1];
											if(((0 <= var129) && (var129 < noStates))) {
												// Variable declaration of cv$temp$23$var243 moved.
												// 
												// Constructing a random variable input for use later.
												// 
												// Value of the variable at this index
												double cv$temp$23$var243 = current_metric_var[server][cv$valuePos];
												
												// Substituted "sample$var196" with its value "sample$var45".
												// 
												// Substituted "timeStep$var226" with its value "1".
												// 
												// cv$temp$22$var241's comment
												// Variable declaration of cv$temp$22$var241 moved.
												// 
												// Constructing a random variable input for use later.
												// 
												// Value of the variable at this index
												cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$23$var243))) - (Math.log(cv$temp$23$var243) * 0.5));
												
												// Recorded the probability of reaching sample task 256 with the current configuration.
												// 
												// Set an accumulator to record the consumer distributions not seen. Initially set
												// to 1 as seen values will be deducted from this value.
												cv$consumerDistributionProbabilityAccumulator = 0.0;
											}
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
									// Substituted "sample$var196" with its value "sample$var45".
									// 
									// Substituted "timeStep$var226" with its value "1".
									if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255$global[sample$var45][server][1])) {
										// The body will execute, so should not be executed again
										// 
										// Guard to check that at most one copy of the code is executed for a given random
										// variable instance.
										// 
										// Substituted "timeStep$var226" with its value "1".
										guard$sample76gaussian255$global[sample$var45][server][1] = true;
										
										// Processing sample task 256 of consumer random variable null.
										// 
										// Set an accumulator to sum the probabilities for each possible configuration of
										// inputs.
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										
										// Constraints moved from conditionals in inner loops/scopes/etc.
										if((0 <= st[sample$var45][1])) {
											// Substituted "timeStep$var226" with its value "1".
											int var129 = st[sample$var45][1];
											if(((0 <= var129) && (var129 < noStates))) {
												// Variable declaration of cv$temp$55$var243 moved.
												// 
												// Constructing a random variable input for use later.
												// 
												// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
												// the output of Sample task 76.
												// 
												// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
												// the output of Sample task 76.
												// 
												// Value of the variable at this index
												double cv$temp$55$var243 = current_metric_var[server][cv$valuePos];
												
												// Substituted "sample$var196" with its value "sample$var45".
												// 
												// Substituted "timeStep$var226" with its value "1".
												// 
												// cv$temp$54$var241's comment
												// Variable declaration of cv$temp$54$var241 moved.
												// 
												// Constructing a random variable input for use later.
												// 
												// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
												// the output of Sample task 76.
												// 
												// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
												// the output of Sample task 76.
												// 
												// Value of the variable at this index
												cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$55$var243))) - (Math.log(cv$temp$55$var243) * 0.5));
												
												// Recorded the probability of reaching sample task 256 with the current configuration.
												// 
												// Set an accumulator to record the consumer distributions not seen. Initially set
												// to 1 as seen values will be deducted from this value.
												cv$consumerDistributionProbabilityAccumulator = 0.0;
											}
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
			}
			int index$timeStep$34 = (timeStep$var66 - 1);
			
			// index$timeStep$22's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var66 - 1)".
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var66 - 1)".
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var66 - 1)".
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var66 - 1)".
			if(((1 <= index$timeStep$34) && !(index$timeStep$34 == timeStep$var66))) {
				// Enumerating the possible outputs of Categorical 73.
				for(int index$sample76$35 = 0; index$sample76$35 < noStates; index$sample76$35 += 1) {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$33" with its value "sample$var45".
					double cv$probabilitySample76Value36 = distribution$sample76[sample$var45][(index$timeStep$34 - 1)][index$sample76$35];
					int var32 = st[sample$var45][(timeStep$var66 - 1)];
					if(((0 <= var32) && (var32 < noStates))) {
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample76Value36);
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						// 
						// cv$temp$7$$var969's comment
						// 
						// $var969's comment
						// Constructing a random variable input for use later.
						// 
						// cv$temp$6$var72's comment
						// Variable declaration of cv$temp$6$var72 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample76Value36) + ((cv$valuePos < noStates)?Math.log(m[cv$valuePos][cv$valuePos]):Double.NEGATIVE_INFINITY));
						for(int server = 0; server < noServers; server += 1) {
							// Processing sample task 241 of consumer random variable null.
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var183 = st[sample$var45][timeStep$var66];
							if(((0 <= var183) && (var183 < noStates))) {
								// Variable declaration of cv$temp$11$var230 moved.
								// 
								// Constructing a random variable input for use later.
								double cv$temp$11$var230 = current_metric_valid_bias[server][index$sample76$35];
								
								// Substituted "sample$var196" with its value "sample$var45".
								cv$accumulatedConsumerProbabilities = Math.log((metric_valid_g[sample$var45][server][timeStep$var66]?cv$temp$11$var230:(1.0 - cv$temp$11$var230)));
								
								// Recorded the probability of reaching sample task 241 with the current configuration.
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
						for(int server = 0; server < noServers; server += 1) {
							// Substituted "sample$var196" with its value "sample$var45".
							if(metric_valid_g[sample$var45][server][timeStep$var66])
								// Set the flags to false
								// 
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = false;
						}
						for(int server = 0; server < noServers; server += 1) {
							// Substituted "sample$var196" with its value "sample$var45".
							if(metric_valid_g[sample$var45][server][timeStep$var66])
								// Set the flags to false
								// 
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = false;
						}
						for(int server = 0; server < noServers; server += 1) {
							// Substituted "sample$var196" with its value "sample$var45".
							if((metric_valid_g[sample$var45][server][timeStep$var66] && !guard$sample76gaussian255$global[sample$var45][server][timeStep$var66])) {
								// The body will execute, so should not be executed again
								// 
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = true;
								
								// Processing sample task 256 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if((0 <= st[sample$var45][timeStep$var66])) {
									int var129 = st[sample$var45][timeStep$var66];
									if(((0 <= var129) && (var129 < noStates))) {
										// Variable declaration of cv$temp$39$var243 moved.
										// 
										// Constructing a random variable input for use later.
										double cv$temp$39$var243 = current_metric_var[server][index$sample76$35];
										
										// Substituted "sample$var196" with its value "sample$var45".
										// 
										// cv$temp$38$var241's comment
										// Variable declaration of cv$temp$38$var241 moved.
										// 
										// Constructing a random variable input for use later.
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][timeStep$var66] - current_metric_mean[server][index$sample76$35]) / Math.sqrt(cv$temp$39$var243))) - (Math.log(cv$temp$39$var243) * 0.5));
										
										// Recorded the probability of reaching sample task 256 with the current configuration.
										// 
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										cv$consumerDistributionProbabilityAccumulator = 0.0;
									}
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
							// Substituted "sample$var196" with its value "sample$var45".
							if((metric_valid_g[sample$var45][server][timeStep$var66] && !guard$sample76gaussian255$global[sample$var45][server][timeStep$var66])) {
								// The body will execute, so should not be executed again
								// 
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = true;
								
								// Processing sample task 256 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if((0 <= st[sample$var45][timeStep$var66])) {
									int var129 = st[sample$var45][timeStep$var66];
									if(((0 <= var129) && (var129 < noStates))) {
										// Variable declaration of cv$temp$71$var243 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
										// the output of Sample task 76.
										// 
										// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
										// the output of Sample task 76.
										double cv$temp$71$var243 = current_metric_var[server][index$sample76$35];
										
										// Substituted "sample$var196" with its value "sample$var45".
										// 
										// cv$temp$70$var241's comment
										// Variable declaration of cv$temp$70$var241 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
										// the output of Sample task 76.
										// 
										// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
										// the output of Sample task 76.
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][timeStep$var66] - current_metric_mean[server][index$sample76$35]) / Math.sqrt(cv$temp$71$var243))) - (Math.log(cv$temp$71$var243) * 0.5));
										
										// Recorded the probability of reaching sample task 256 with the current configuration.
										// 
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										cv$consumerDistributionProbabilityAccumulator = 0.0;
									}
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
			int index$timeStep$269_3 = (timeStep$var66 + 1);
			if((index$timeStep$269_3 < length$metric[sample$var45][0])) {
				// Processing sample task 76 of consumer random variable null.
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var73[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				int var32 = st[sample$var45][(index$timeStep$269_3 - 1)];
				
				// Substituted "index$sample$269_2" with its value "sample$var45".
				if(((0 <= var32) && (var32 < noStates))) {
					// Declare and zero an accumulator for tracking the reached source probability space.
					double scopeVariable$reachedSourceProbability = 0.0;
					
					// Enumerating the possible arguments for Categorical 73.
					if((1 == timeStep$var66)) {
						// Enumerating the possible arguments for Categorical 73.
						if(fixedFlag$sample57) {
							int index$var32$280_1 = st[sample$var45][0];
							
							// Substituted "timeStep$var66" with its value "1".
							if(((0 <= index$var32$280_1) && (index$var32$280_1 < noStates)))
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 54.
							for(int index$sample57$276 = 0; index$sample57$276 < noStates; index$sample57$276 += 1) {
								int index$var32$281_1 = st[sample$var45][0];
								
								// Substituted "timeStep$var66" with its value "1".
								if(((0 <= index$var32$281_1) && (index$var32$281_1 < noStates)))
									// Add the probability of this argument configuration.
									// 
									// cv$probabilitySample57Value277's comment
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$275" with its value "sample$var45".
									scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample57[sample$var45][index$sample57$276]);
							}
						}
					}
					int index$timeStep$284 = (timeStep$var66 - 1);
					
					// index$timeStep$271's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var66 + 1)".
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var66 + 1)".
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var66 + 1)".
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var66 + 1)".
					if((((1 <= index$timeStep$284) && !(index$timeStep$284 == timeStep$var66)) && !(index$timeStep$284 == index$timeStep$269_3))) {
						// Enumerating the possible outputs of Categorical 73.
						for(int index$sample76$285 = 0; index$sample76$285 < noStates; index$sample76$285 += 1) {
							int index$var32$290_1 = st[sample$var45][(timeStep$var66 - 1)];
							if(((0 <= index$var32$290_1) && (index$var32$290_1 < noStates)))
								// Add the probability of this argument configuration.
								// 
								// cv$probabilitySample76Value286's comment
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$283" with its value "sample$var45".
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample76[sample$var45][(index$timeStep$284 - 1)][index$sample76$285]);
						}
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
					// cv$temp$77$$var1661's comment
					// 
					// $var1661's comment
					// Constructing a random variable input for use later.
					// 
					// cv$temp$76$var72's comment
					// Variable declaration of cv$temp$76$var72 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Processing random variable 73.
					// 
					// Looking for a path between Sample 76 and consumer Categorical 73.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var73, scopeVariable$reachedSourceProbability, m[cv$valuePos], noStates);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$sample$269_2" with its value "sample$var45".
				double[] cv$sampleDistribution = distribution$sample76[sample$var45][(index$timeStep$269_3 - 1)];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					double cv$normalisedDistValue = (cv$distributionAccumulator$var73[cv$i] / cv$reachedDistributionProbability);
					
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
			// Get a local reference to the scratch space.
			cv$var74$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample76[sample$var45][(timeStep$var66 - 1)];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var74$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var74$stateProbabilityGlobal[cv$lseIndex];
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var74$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = Math.exp((cv$var74$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var74$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
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
		cv$var20$countGlobal = new double[noStates];
		
		// Constructor for cv$var33$countGlobal
		// 
		// Allocation of cv$var33$countGlobal for single threaded execution
		cv$var33$countGlobal = new double[noStates];
		
		// Constructor for cv$distributionAccumulator$var73
		// 
		// Allocation of cv$distributionAccumulator$var73 for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 74. Initially set to the value
		// of putTask 34.
		cv$distributionAccumulator$var73 = new double[noStates];
		
		// Constructor for cv$var55$stateProbabilityGlobal
		// 
		// Allocation of cv$var55$stateProbabilityGlobal for single threaded execution
		cv$var55$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for guard$sample57gaussian255$global
		{
			// Calculate the largest index of server that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_server = 0;
			
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var226 = 0;
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				if((0 < length$metric[0].length))
					cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, length$metric[sample$var196][0]);
				cv$max_server = Math.max(cv$max_server, length$metric[0].length);
			}
			
			// Allocation of guard$sample57gaussian255$global for single threaded execution
			guard$sample57gaussian255$global = new boolean[length$metric.length][cv$max_server][cv$max_timeStep$var226];
		}
		
		// Allocation of cv$var74$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 74. Initially set to the value
		// of putTask 34.
		cv$var74$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for guard$sample76gaussian255$global
		// 
		// Calculate the largest index of server that is possible and allocate an array to
		// hold the guard for each of these.
		int cv$max_server = 0;
		
		// Calculate the largest index of timeStep that is possible and allocate an array
		// to hold the guard for each of these.
		int cv$max_timeStep$var226 = 0;
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			if((0 < length$metric[0].length))
				cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, length$metric[sample$var196][0]);
			cv$max_server = Math.max(cv$max_server, length$metric[0].length);
		}
		
		// Allocation of guard$sample76gaussian255$global for single threaded execution
		guard$sample76gaussian255$global = new boolean[length$metric.length][cv$max_server][cv$max_timeStep$var226];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		v = new double[noStates];
		
		// If initialStateDistribution has not been set already allocate space.
		if(!fixedFlag$sample20)
			// Constructor for initialStateDistribution
			initialStateDistribution = new double[noStates];
		
		// If m has not been set already allocate space.
		if(!fixedFlag$sample33) {
			// Constructor for m
			m = new double[noStates][];
			for(int var32 = 0; var32 < noStates; var32 += 1)
				m[var32] = new double[noStates];
		}
		
		// If st has not been set already allocate space.
		if((!fixedFlag$sample57 || !fixedFlag$sample76)) {
			// Constructor for st
			st = new int[length$metric.length][];
			for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
				st[sample$var45] = new int[length$metric[sample$var45][0]];
		}
		
		// Constructor for metric_g
		metric_g = new double[length$metric.length][][];
		for(int var90 = 0; var90 < length$metric.length; var90 += 1)
			metric_g[var90] = new double[length$metric[0].length][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			for(int server = 0; server < length$metric[0].length; server += 1)
				metric_g[sample$var196][server] = new double[length$metric[sample$var196][0]];
		}
		
		// Constructor for metric_valid_g
		metric_valid_g = new boolean[length$metric.length][][];
		for(int var103 = 0; var103 < length$metric.length; var103 += 1)
			metric_valid_g[var103] = new boolean[length$metric[0].length][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			for(int server = 0; server < length$metric[0].length; server += 1)
				metric_valid_g[sample$var196][server] = new boolean[length$metric[sample$var196][0]];
		}
		
		// If current_metric_mean has not been set already allocate space.
		if(!fixedFlag$sample134) {
			// Constructor for current_metric_mean
			current_metric_mean = new double[length$metric[0].length][];
			for(int var119 = 0; var119 < length$metric[0].length; var119 += 1)
				current_metric_mean[var119] = new double[noStates];
		}
		
		// If current_metric_var has not been set already allocate space.
		if(!fixedFlag$sample162) {
			// Constructor for current_metric_var
			current_metric_var = new double[length$metric[0].length][];
			for(int var146 = 0; var146 < length$metric[0].length; var146 += 1)
				current_metric_var[var146] = new double[noStates];
		}
		
		// If current_metric_valid_bias has not been set already allocate space.
		if(!fixedFlag$sample190) {
			// Constructor for current_metric_valid_bias
			current_metric_valid_bias = new double[length$metric[0].length][];
			for(int var173 = 0; var173 < length$metric[0].length; var173 += 1)
				current_metric_valid_bias[var173] = new double[noStates];
		}
		
		// Constructor for var245
		var245 = new double[length$metric.length][][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			var245[sample$var196] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var196][0]];
		}
		
		// Constructor for distribution$sample57
		distribution$sample57 = new double[length$metric.length][];
		for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
			distribution$sample57[sample$var45] = new double[noStates];
		
		// Constructor for distribution$sample76
		distribution$sample76 = new double[length$metric.length][][];
		for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1) {
			double[][] subarray$0 = new double[(length$metric[sample$var45][0] - 1)][];
			distribution$sample76[sample$var45] = subarray$0;
			for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
				subarray$0[(timeStep$var66 - 1)] = new double[noStates];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample33) {
			for(int var32 = 0; var32 < noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			if(!fixedFlag$sample57)
				st[sample$var45][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample76) {
				int[] var67 = st[sample$var45];
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample134) {
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				double[] var120 = current_metric_mean[var119];
				for(int var129 = 0; var129 < noStates; var129 += 1)
					var120[var129] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample162) {
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				double[] var147 = current_metric_var[var146];
				for(int var156 = 0; var156 < noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample190) {
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				double[] var174 = current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			boolean[][] var215 = metric_valid_g[sample$var196];
			double[][] var211 = metric_g[sample$var196];
			for(int server = 0; server < noServers; server += 1) {
				boolean[] metric_valid_inner = metric_valid_g[sample$var196][server];
				double[] metric_inner = var211[server];
				for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(RNG$, current_metric_valid_bias[server][st[sample$var196][timeStep$var226]]);
					if(var215[server][timeStep$var226]) {
						var245[sample$var196][server][timeStep$var226] = ((Math.sqrt(current_metric_var[server][st[sample$var196][timeStep$var226]]) * DistributionSampling.sampleGaussian(RNG$)) + current_metric_mean[server][st[sample$var196][timeStep$var226]]);
						metric_inner[timeStep$var226] = var245[sample$var196][server][timeStep$var226];
					}
				}
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample33) {
			for(int var32 = 0; var32 < noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample57) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample57 = distribution$sample57[sample$var45];
				for(int index$var54 = 0; index$var54 < noStates; index$var54 += 1)
					// Save the probability of each value
					// 
					// Probability for this value
					cv$distribution$sample57[index$var54] = initialStateDistribution[index$var54];
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample76) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					// Create local copy of variable probabilities.
					double[] cv$distribution$sample76 = distribution$sample76[sample$var45][(timeStep$var66 - 1)];
					for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
						// Zero the probability of each value
						cv$distribution$sample76[index$var73] = 0.0;
					
					// Iterate through possible values for var73's arguments.
					// 
					// Enumerating the possible arguments for Categorical 73.
					if((1 == timeStep$var66)) {
						// Iterate through possible values for var73's arguments.
						// 
						// Enumerating the possible arguments for Categorical 73.
						if(fixedFlag$sample57) {
							int var32 = st[sample$var45][0];
							
							// Substituted "timeStep$var66" with its value "1".
							if(((0 <= var32) && (var32 < noStates))) {
								// Substituted "timeStep$var66" with its value "1".
								double[] var72 = m[st[sample$var45][0]];
								for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
									// Save the probability of each value
									cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + var72[index$var73]);
							}
						} else {
							// Enumerating the possible outputs of Categorical 54.
							for(int index$sample57$3 = 0; index$sample57$3 < noStates; index$sample57$3 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$2" with its value "sample$var45".
								double cv$probabilitySample57Value4 = distribution$sample57[sample$var45][index$sample57$3];
								int var32 = st[sample$var45][0];
								
								// Substituted "timeStep$var66" with its value "1".
								if(((0 <= var32) && (var32 < noStates))) {
									// Substituted "timeStep$var66" with its value "1".
									double[] var72 = m[st[sample$var45][0]];
									for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
										// Save the probability of each value
										cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample57Value4 * var72[index$var73]));
								}
							}
						}
					}
					int index$timeStep$11 = (timeStep$var66 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$sample$10" with its value "sample$var45".
					if((1 <= index$timeStep$11)) {
						// Enumerating the possible outputs of Categorical 73.
						for(int index$sample76$12 = 0; index$sample76$12 < noStates; index$sample76$12 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$10" with its value "sample$var45".
							double cv$probabilitySample76Value13 = distribution$sample76[sample$var45][(index$timeStep$11 - 1)][index$sample76$12];
							int var32 = st[sample$var45][(timeStep$var66 - 1)];
							if(((0 <= var32) && (var32 < noStates))) {
								double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
								for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
									// Save the probability of each value
									cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample76Value13 * var72[index$var73]));
							}
						}
					}
					
					// Sum the values in the array
					double cv$var73$sum = 0.0;
					for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
						// sum the probability of each value
						cv$var73$sum = (cv$var73$sum + cv$distribution$sample76[index$var73]);
					for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
						// Normalise the probability of each value
						cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] / cv$var73$sum);
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample134) {
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				double[] var120 = current_metric_mean[var119];
				for(int var129 = 0; var129 < noStates; var129 += 1)
					var120[var129] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample162) {
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				double[] var147 = current_metric_var[var146];
				for(int var156 = 0; var156 < noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample190) {
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				double[] var174 = current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample33) {
			for(int var32 = 0; var32 < noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			if(!fixedFlag$sample57)
				st[sample$var45][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample76) {
				int[] var67 = st[sample$var45];
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample134) {
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				double[] var120 = current_metric_mean[var119];
				for(int var129 = 0; var129 < noStates; var129 += 1)
					var120[var129] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample162) {
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				double[] var147 = current_metric_var[var146];
				for(int var156 = 0; var156 < noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample190) {
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				double[] var174 = current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample20)
				sample20();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample33) {
				for(int var32 = 0; var32 < noStates; var32 += 1)
					sample33(var32);
			}
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				if(!fixedFlag$sample57)
					sample57(sample$var45);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample76) {
					for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
						sample76(sample$var45, timeStep$var66);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample134) {
				for(int var119 = 0; var119 < noServers; var119 += 1) {
					for(int var129 = 0; var129 < noStates; var129 += 1)
						sample134(var119, var129);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample162) {
				for(int var146 = 0; var146 < noServers; var146 += 1) {
					for(int var156 = 0; var156 < noStates; var156 += 1)
						sample162(var146, var156);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample190) {
				for(int var173 = 0; var173 < noServers; var173 += 1) {
					for(int var183 = 0; var183 < noStates; var183 += 1)
						sample190(var173, var183);
				}
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample190) {
				for(int var173 = (noServers - 1); var173 >= 0; var173 -= 1) {
					for(int var183 = (noStates - 1); var183 >= 0; var183 -= 1)
						sample190(var173, var183);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample162) {
				for(int var146 = (noServers - 1); var146 >= 0; var146 -= 1) {
					for(int var156 = (noStates - 1); var156 >= 0; var156 -= 1)
						sample162(var146, var156);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample134) {
				for(int var119 = (noServers - 1); var119 >= 0; var119 -= 1) {
					for(int var129 = (noStates - 1); var129 >= 0; var129 -= 1)
						sample134(var119, var129);
				}
			}
			for(int sample$var45 = (noSamples - 1); sample$var45 >= 0; sample$var45 -= 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample76) {
					for(int timeStep$var66 = (length$metric[sample$var45][0] - 1); timeStep$var66 >= 1; timeStep$var66 -= 1)
						sample76(sample$var45, timeStep$var66);
				}
				if(!fixedFlag$sample57)
					sample57(sample$var45);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample33) {
				for(int var32 = (noStates - 1); var32 >= 0; var32 -= 1)
					sample33(var32);
			}
			if(!fixedFlag$sample20)
				sample20();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		noSamples = length$metric.length;
		for(int var16 = 0; var16 < noStates; var16 += 1)
			v[var16] = 0.1;
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
		logProbability$var19 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var21 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample33)
			logProbability$var33 = 0.0;
		logProbability$var54 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var55 = 0.0;
		logProbability$var73 = 0.0;
		if(!fixedProbFlag$sample76)
			logProbability$var74 = 0.0;
		logProbability$var108 = 0.0;
		logProbability$current_metric_mean = 0.0;
		if(!fixedProbFlag$sample134)
			logProbability$var130 = 0.0;
		logProbability$var135 = 0.0;
		logProbability$current_metric_var = 0.0;
		if(!fixedProbFlag$sample162)
			logProbability$var157 = 0.0;
		logProbability$var162 = 0.0;
		logProbability$current_metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample190)
			logProbability$var184 = 0.0;
		logProbability$var231 = 0.0;
		logProbability$metric_valid_inner = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample241)
			logProbability$var232 = 0.0;
		logProbability$var244 = 0.0;
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample256)
			logProbability$var245 = 0.0;
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
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(fixedFlag$sample33)
			logProbabilityValue$sample33();
		if(fixedFlag$sample134)
			logProbabilityValue$sample134();
		if(fixedFlag$sample162)
			logProbabilityValue$sample162();
		if(fixedFlag$sample190)
			logProbabilityValue$sample190();
		logProbabilityValue$sample241();
		logProbabilityValue$sample256();
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
		logProbabilityValue$sample20();
		logProbabilityValue$sample33();
		logProbabilityDistribution$sample57();
		logProbabilityDistribution$sample76();
		logProbabilityValue$sample134();
		logProbabilityValue$sample162();
		logProbabilityValue$sample190();
		logProbabilityDistribution$sample241();
		logProbabilityDistribution$sample256();
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
		logProbabilityValue$sample20();
		logProbabilityValue$sample33();
		logProbabilityValue$sample57();
		logProbabilityValue$sample76();
		logProbabilityValue$sample134();
		logProbabilityValue$sample162();
		logProbabilityValue$sample190();
		logProbabilityValue$sample241();
		logProbabilityValue$sample256();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample33) {
			for(int var32 = 0; var32 < noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			if(!fixedFlag$sample57)
				st[sample$var45][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample76) {
				int[] var67 = st[sample$var45];
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample134) {
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				double[] var120 = current_metric_mean[var119];
				for(int var129 = 0; var129 < noStates; var129 += 1)
					var120[var129] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample162) {
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				double[] var147 = current_metric_var[var146];
				for(int var156 = 0; var156 < noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample190) {
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				double[] var174 = current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
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
		for(int sample$var196 = (noSamples - 1); sample$var196 >= 0; sample$var196 -= 1) {
			for(int server = (noServers - 1); server >= 0; server -= 1) {
				for(int timeStep$var226 = (length$metric[sample$var196][0] - 1); timeStep$var226 >= 0; timeStep$var226 -= 1) {
					if(metric_valid_g[sample$var196][server][timeStep$var226])
						// metric_inner's comment
						// Variable declaration of metric_inner moved.
						var245[sample$var196][server][timeStep$var226] = metric_g[sample$var196][server][timeStep$var226];
				}
			}
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
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