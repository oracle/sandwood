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
	private double[][] distribution$sample50;
	private double[][][] distribution$sample63;
	private boolean fixedFlag$sample111 = false;
	private boolean fixedFlag$sample126 = false;
	private boolean fixedFlag$sample161 = false;
	private boolean fixedFlag$sample173 = false;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedFlag$sample30 = false;
	private boolean fixedFlag$sample50 = false;
	private boolean fixedFlag$sample63 = false;
	private boolean fixedFlag$sample96 = false;
	private boolean fixedProbFlag$sample111 = false;
	private boolean fixedProbFlag$sample126 = false;
	private boolean fixedProbFlag$sample161 = false;
	private boolean fixedProbFlag$sample173 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample30 = false;
	private boolean fixedProbFlag$sample50 = false;
	private boolean fixedProbFlag$sample63 = false;
	private boolean fixedProbFlag$sample96 = false;
	private boolean[][][] guard$sample50gaussian172$global;
	private boolean[][][] guard$sample63gaussian172$global;
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
	private double[][][] logProbability$sample161;
	private double[][][] logProbability$sample173;
	private double[] logProbability$sample50;
	private double[][] logProbability$sample63;
	private double logProbability$st;
	private double logProbability$var101;
	private double logProbability$var106;
	private double logProbability$var115;
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

	// Getter for fixedFlag$sample111.
	@Override
	public final boolean get$fixedFlag$sample111() {
		return fixedFlag$sample111;
	}

	// Setter for fixedFlag$sample111.
	@Override
	public final void set$fixedFlag$sample111(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample111 including if probabilities
		// need to be updated.
		fixedFlag$sample111 = cv$value;
		
		// Should the probability of sample 111 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample111 = (fixedFlag$sample111 && fixedProbFlag$sample111);
		
		// Should the probability of sample 173 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample173 = (fixedFlag$sample111 && fixedProbFlag$sample173);
	}

	// Getter for fixedFlag$sample126.
	@Override
	public final boolean get$fixedFlag$sample126() {
		return fixedFlag$sample126;
	}

	// Setter for fixedFlag$sample126.
	@Override
	public final void set$fixedFlag$sample126(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample126 including if probabilities
		// need to be updated.
		fixedFlag$sample126 = cv$value;
		
		// Should the probability of sample 126 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample126 = (fixedFlag$sample126 && fixedProbFlag$sample126);
		
		// Should the probability of sample 161 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample161 = (fixedFlag$sample126 && fixedProbFlag$sample161);
	}

	// Getter for fixedFlag$sample161.
	@Override
	public final boolean get$fixedFlag$sample161() {
		return fixedFlag$sample161;
	}

	// Setter for fixedFlag$sample161.
	@Override
	public final void set$fixedFlag$sample161(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample161 including if probabilities
		// need to be updated.
		fixedFlag$sample161 = cv$value;
		
		// Should the probability of sample 161 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample161 = (fixedFlag$sample161 && fixedProbFlag$sample161);
	}

	// Getter for fixedFlag$sample173.
	@Override
	public final boolean get$fixedFlag$sample173() {
		return fixedFlag$sample173;
	}

	// Setter for fixedFlag$sample173.
	@Override
	public final void set$fixedFlag$sample173(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample173 including if probabilities
		// need to be updated.
		fixedFlag$sample173 = cv$value;
		
		// Should the probability of sample 173 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample173 = (fixedFlag$sample173 && fixedProbFlag$sample173);
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
		fixedProbFlag$sample24 = (fixedFlag$sample24 && fixedProbFlag$sample24);
		
		// Should the probability of sample 50 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample50 = (fixedFlag$sample24 && fixedProbFlag$sample50);
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
		fixedProbFlag$sample30 = (fixedFlag$sample30 && fixedProbFlag$sample30);
		
		// Should the probability of sample 63 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample63 = (fixedFlag$sample30 && fixedProbFlag$sample63);
	}

	// Getter for fixedFlag$sample50.
	@Override
	public final boolean get$fixedFlag$sample50() {
		return fixedFlag$sample50;
	}

	// Setter for fixedFlag$sample50.
	@Override
	public final void set$fixedFlag$sample50(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample50 including if probabilities
		// need to be updated.
		fixedFlag$sample50 = cv$value;
		
		// Should the probability of sample 50 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedProbFlag$sample50);
		
		// Should the probability of sample 63 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample63 = (fixedFlag$sample50 && fixedProbFlag$sample63);
		
		// Should the probability of sample 161 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample161 = (fixedFlag$sample50 && fixedProbFlag$sample161);
		
		// Should the probability of sample 173 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample173 = (fixedFlag$sample50 && fixedProbFlag$sample173);
	}

	// Getter for fixedFlag$sample63.
	@Override
	public final boolean get$fixedFlag$sample63() {
		return fixedFlag$sample63;
	}

	// Setter for fixedFlag$sample63.
	@Override
	public final void set$fixedFlag$sample63(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample63 including if probabilities
		// need to be updated.
		fixedFlag$sample63 = cv$value;
		
		// Should the probability of sample 63 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample63 = (fixedFlag$sample63 && fixedProbFlag$sample63);
		
		// Should the probability of sample 161 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample161 = (fixedFlag$sample63 && fixedProbFlag$sample161);
		
		// Should the probability of sample 173 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample173 = (fixedFlag$sample63 && fixedProbFlag$sample173);
	}

	// Getter for fixedFlag$sample96.
	@Override
	public final boolean get$fixedFlag$sample96() {
		return fixedFlag$sample96;
	}

	// Setter for fixedFlag$sample96.
	@Override
	public final void set$fixedFlag$sample96(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample96 including if probabilities
		// need to be updated.
		fixedFlag$sample96 = cv$value;
		
		// Should the probability of sample 96 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample96 = (fixedFlag$sample96 && fixedProbFlag$sample96);
		
		// Should the probability of sample 173 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample173 = (fixedFlag$sample96 && fixedProbFlag$sample173);
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

	// Calculate the probability of the samples represented by sample161 using probability
	// distributions.
	private final void logProbabilityDistribution$sample161() {
		// Determine if we need to calculate the values for sample task 161 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample161) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						// Accumulator for sample probabilities for a specific instance of the random variable.
						double cv$sampleAccumulator = 0.0;
						
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 161 including any distribution
						// values.
						{
							// The sample value to calculate the probability of generating
							boolean cv$sampleValue = metric_valid_g[sample$var120][server][timeStep$var140];
							
							// Enumerating the possible arguments for Bernoulli 145.
							if(fixedFlag$sample50) {
								for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
									if((sample$var32 == sample$var120)) {
										if((0 == timeStep$var140)) {
											for(int var114 = 0; var114 < noStates; var114 += 1) {
												for(int var110 = 0; var110 < noServers; var110 += 1) {
													if((var110 == server)) {
														if((var114 == st[sample$var120][timeStep$var140])) {
															{
																double var144 = current_metric_valid_bias[server][st[sample$var120][timeStep$var140]];
																
																// Store the value of the function call, so the function call is only made once.
																double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var144));
																
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
													}
												}
											}
										}
									}
								}
							} else {
								for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 43.
										for(int index$sample50$4 = 0; index$sample50$4 < noStates; index$sample50$4 += 1) {
											int distributionTempVariable$var44$6 = index$sample50$4;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample50Value5 = (1.0 * distribution$sample50[((sample$var32 - 0) / 1)][index$sample50$4]);
											int traceTempVariable$currentState$7_1 = distributionTempVariable$var44$6;
											if((sample$var32 == sample$var120)) {
												if((0 == timeStep$var140)) {
													for(int var114 = 0; var114 < noStates; var114 += 1) {
														for(int var110 = 0; var110 < noServers; var110 += 1) {
															if((var110 == server)) {
																if((var114 == traceTempVariable$currentState$7_1)) {
																	{
																		double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$7_1];
																		
																		// Store the value of the function call, so the function call is only made once.
																		double cv$weightedProbability = (Math.log(cv$probabilitySample50Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var144));
																		
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
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample50Value5);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							
							// Enumerating the possible arguments for Bernoulli 145.
							if(fixedFlag$sample63) {
								for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
									for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
										if((sample$var32 == sample$var120)) {
											if((timeStep$var49 == timeStep$var140)) {
												for(int var114 = 0; var114 < noStates; var114 += 1) {
													for(int var110 = 0; var110 < noServers; var110 += 1) {
														if((var110 == server)) {
															if((var114 == st[sample$var120][timeStep$var140])) {
																{
																	double var144 = current_metric_valid_bias[server][st[sample$var120][timeStep$var140]];
																	
																	// Store the value of the function call, so the function call is only made once.
																	double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var144));
																	
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
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
									for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 56.
											for(int index$sample63$13 = 0; index$sample63$13 < noStates; index$sample63$13 += 1) {
												int distributionTempVariable$var57$15 = index$sample63$13;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample63Value14 = (1.0 * distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$13]);
												int traceTempVariable$currentState$16_1 = distributionTempVariable$var57$15;
												if((sample$var32 == sample$var120)) {
													if((timeStep$var49 == timeStep$var140)) {
														for(int var114 = 0; var114 < noStates; var114 += 1) {
															for(int var110 = 0; var110 < noServers; var110 += 1) {
																if((var110 == server)) {
																	if((var114 == traceTempVariable$currentState$16_1)) {
																		{
																			double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$16_1];
																			
																			// Store the value of the function call, so the function call is only made once.
																			double cv$weightedProbability = (Math.log(cv$probabilitySample63Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var144));
																			
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
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample63Value14);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
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
						double cv$sampleProbability = cv$distributionAccumulator;
						
						// Add the probability of this sample task to the sample task accumulator.
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
						
						// Add the probability of this instance of the random variable to the probability
						// of all instances of the random variable.
						cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
						logProbability$var145[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleAccumulator;
						
						// Store the sample task probability
						logProbability$sample161[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleProbability;
						
						// Guard to ensure that metric_valid_g is only updated once for this probability.
						boolean cv$guard$metric_valid_g = false;
						
						// Add probability to constructed variables that have guards, so need per sample probabilities
						// from the combined probability
						{
							// If the probability of the variable has not already been updated
							if(!cv$guard$metric_valid_g) {
								// Set the guard so the update is only applied once.
								cv$guard$metric_valid_g = true;
								
								// Update the variable probability
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
							}
						}
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
			fixedProbFlag$sample161 = (((fixedFlag$sample161 && fixedFlag$sample50) && fixedFlag$sample63) && fixedFlag$sample126);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample161[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var145[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$rvAccumulator;
						
						// Guard to ensure that metric_valid_g is only updated once for this probability.
						boolean cv$guard$metric_valid_g = false;
						
						// Add probability to constructed variables that have guards, so need per sample probabilities
						// from the combined probability
						{
							// If the probability of the variable has not already been updated
							if(!cv$guard$metric_valid_g) {
								// Set the guard so the update is only applied once.
								cv$guard$metric_valid_g = true;
								
								// Update the variable probability
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
							}
						}
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

	// Calculate the probability of the samples represented by sample173 using probability
	// distributions.
	private final void logProbabilityDistribution$sample173() {
		// Determine if we need to calculate the values for sample task 173 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample173) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						if(metric_valid_g[sample$var120][server][timeStep$var140]) {
							// Accumulator for sample probabilities for a specific instance of the random variable.
							double cv$sampleAccumulator = 0.0;
							
							// An accumulator for log probabilities.
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							
							// An accumulator for the distributed probability space covered.
							double cv$probabilityReached = 0.0;
							
							// Look for paths between the variable and the sample task 173 including any distribution
							// values.
							{
								// The sample value to calculate the probability of generating
								double cv$sampleValue = metric_g[sample$var120][server][timeStep$var140];
								
								// Enumerating the possible arguments for Gaussian 155.
								if(fixedFlag$sample50) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										if((sample$var32 == sample$var120)) {
											if((0 == timeStep$var140)) {
												for(int var86 = 0; var86 < noStates; var86 += 1) {
													for(int var82 = 0; var82 < noServers; var82 += 1) {
														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
															if((var82 == server)) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var86 == st[sample$var120][timeStep$var140])) {
																		for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
																			if((index$sample$10_1 == sample$var120)) {
																				if((0 == timeStep$var140)) {
																					for(int var100 = 0; var100 < noStates; var100 += 1) {
																						for(int var96 = 0; var96 < noServers; var96 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var96 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var100 == st[sample$var120][timeStep$var140])) {
																											{
																												double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																												double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																												
																												// Store the value of the function call, so the function call is only made once.
																												double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																												
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
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 43.
											for(int index$sample50$4 = 0; index$sample50$4 < noStates; index$sample50$4 += 1) {
												int distributionTempVariable$var44$6 = index$sample50$4;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample50Value5 = (1.0 * distribution$sample50[((sample$var32 - 0) / 1)][index$sample50$4]);
												int traceTempVariable$currentState$7_1 = distributionTempVariable$var44$6;
												if((sample$var32 == sample$var120)) {
													if((0 == timeStep$var140)) {
														for(int var86 = 0; var86 < noStates; var86 += 1) {
															for(int var82 = 0; var82 < noServers; var82 += 1) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var82 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var86 == traceTempVariable$currentState$7_1)) {
																				int traceTempVariable$currentState$11_1 = distributionTempVariable$var44$6;
																				if((sample$var32 == sample$var120)) {
																					if((0 == timeStep$var140)) {
																						for(int var100 = 0; var100 < noStates; var100 += 1) {
																							for(int var96 = 0; var96 < noServers; var96 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var96 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var100 == traceTempVariable$currentState$11_1)) {
																												{
																													double var152 = current_metric_mean[server][traceTempVariable$currentState$11_1];
																													double var154 = current_metric_var[server][traceTempVariable$currentState$11_1];
																													
																													// Store the value of the function call, so the function call is only made once.
																													double cv$weightedProbability = (Math.log(cv$probabilitySample50Value5) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																													
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
																													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample50Value5);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				for(int index$sample$12 = 0; index$sample$12 < noSamples; index$sample$12 += 1) {
																					if(!(index$sample$12 == sample$var32)) {
																						// Enumerating the possible outputs of Categorical 43.
																						for(int index$sample50$13 = 0; index$sample50$13 < noStates; index$sample50$13 += 1) {
																							int distributionTempVariable$var44$15 = index$sample50$13;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample50Value14 = (cv$probabilitySample50Value5 * distribution$sample50[((index$sample$12 - 0) / 1)][index$sample50$13]);
																							int traceTempVariable$currentState$16_1 = distributionTempVariable$var44$15;
																							if((index$sample$12 == sample$var120)) {
																								if((0 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$16_1)) {
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$16_1];
																																double var154 = current_metric_var[server][traceTempVariable$currentState$16_1];
																																
																																// Store the value of the function call, so the function call is only made once.
																																double cv$weightedProbability = (Math.log(cv$probabilitySample50Value14) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																
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
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample50Value14);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								
								// Enumerating the possible arguments for Gaussian 155.
								if(fixedFlag$sample50) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										if((sample$var32 == sample$var120)) {
											if((0 == timeStep$var140)) {
												for(int var86 = 0; var86 < noStates; var86 += 1) {
													for(int var82 = 0; var82 < noServers; var82 += 1) {
														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
															if((var82 == server)) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var86 == st[sample$var120][timeStep$var140])) {
																		if(fixedFlag$sample63) {
																			for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$28_1][0]; timeStep$var49 += 1) {
																					if((index$sample$28_1 == sample$var120)) {
																						if((timeStep$var49 == timeStep$var140)) {
																							for(int var100 = 0; var100 < noStates; var100 += 1) {
																								for(int var96 = 0; var96 < noServers; var96 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var96 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var100 == st[sample$var120][timeStep$var140])) {
																													{
																														double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																														double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																														
																														// Store the value of the function call, so the function call is only made once.
																														double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																														
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
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$29][0]; timeStep$var49 += 1) {
																					if(true) {
																						// Enumerating the possible outputs of Categorical 56.
																						for(int index$sample63$31 = 0; index$sample63$31 < noStates; index$sample63$31 += 1) {
																							int distributionTempVariable$var57$33 = index$sample63$31;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample63Value32 = (1.0 * distribution$sample63[((index$sample$29 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$31]);
																							int traceTempVariable$currentState$34_1 = distributionTempVariable$var57$33;
																							if((index$sample$29 == sample$var120)) {
																								if((timeStep$var49 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$34_1)) {
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$34_1];
																																double var154 = current_metric_var[server][traceTempVariable$currentState$34_1];
																																
																																// Store the value of the function call, so the function call is only made once.
																																double cv$weightedProbability = (Math.log(cv$probabilitySample63Value32) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																
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
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample63Value32);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 43.
											for(int index$sample50$22 = 0; index$sample50$22 < noStates; index$sample50$22 += 1) {
												int distributionTempVariable$var44$24 = index$sample50$22;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample50Value23 = (1.0 * distribution$sample50[((sample$var32 - 0) / 1)][index$sample50$22]);
												int traceTempVariable$currentState$25_1 = distributionTempVariable$var44$24;
												if((sample$var32 == sample$var120)) {
													if((0 == timeStep$var140)) {
														for(int var86 = 0; var86 < noStates; var86 += 1) {
															for(int var82 = 0; var82 < noServers; var82 += 1) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var82 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var86 == traceTempVariable$currentState$25_1)) {
																				if(fixedFlag$sample63) {
																					for(int index$sample$35_1 = 0; index$sample$35_1 < noSamples; index$sample$35_1 += 1) {
																						for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$35_1][0]; timeStep$var49 += 1) {
																							if((index$sample$35_1 == sample$var120)) {
																								if((timeStep$var49 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$25_1)) {
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$25_1];
																																double var154 = current_metric_var[server][traceTempVariable$currentState$25_1];
																																
																																// Store the value of the function call, so the function call is only made once.
																																double cv$weightedProbability = (Math.log(cv$probabilitySample50Value23) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																
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
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample50Value23);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int index$sample$36 = 0; index$sample$36 < noSamples; index$sample$36 += 1) {
																						for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$36][0]; timeStep$var49 += 1) {
																							if(true) {
																								// Enumerating the possible outputs of Categorical 56.
																								for(int index$sample63$38 = 0; index$sample63$38 < noStates; index$sample63$38 += 1) {
																									int distributionTempVariable$var57$40 = index$sample63$38;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample63Value39 = (cv$probabilitySample50Value23 * distribution$sample63[((index$sample$36 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$38]);
																									int traceTempVariable$currentState$41_1 = distributionTempVariable$var57$40;
																									if((index$sample$36 == sample$var120)) {
																										if((timeStep$var49 == timeStep$var140)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$41_1)) {
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$41_1];
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$41_1];
																																		
																																		// Store the value of the function call, so the function call is only made once.
																																		double cv$weightedProbability = (Math.log(cv$probabilitySample63Value39) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																		
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
																																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample63Value39);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								
								// Enumerating the possible arguments for Gaussian 155.
								if(fixedFlag$sample63) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
											if((sample$var32 == sample$var120)) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int var86 = 0; var86 < noStates; var86 += 1) {
														for(int var82 = 0; var82 < noServers; var82 += 1) {
															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																if((var82 == server)) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var86 == st[sample$var120][timeStep$var140])) {
																			for(int index$sample$55_1 = 0; index$sample$55_1 < noSamples; index$sample$55_1 += 1) {
																				for(int index$timeStep$55_2 = 1; index$timeStep$55_2 < length$metric[index$sample$55_1][0]; index$timeStep$55_2 += 1) {
																					if((index$sample$55_1 == sample$var120)) {
																						if((index$timeStep$55_2 == timeStep$var140)) {
																							for(int var100 = 0; var100 < noStates; var100 += 1) {
																								for(int var96 = 0; var96 < noServers; var96 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var96 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var100 == st[sample$var120][timeStep$var140])) {
																													{
																														double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																														double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																														
																														// Store the value of the function call, so the function call is only made once.
																														double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																														
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
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 56.
												for(int index$sample63$49 = 0; index$sample63$49 < noStates; index$sample63$49 += 1) {
													int distributionTempVariable$var57$51 = index$sample63$49;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample63Value50 = (1.0 * distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$49]);
													int traceTempVariable$currentState$52_1 = distributionTempVariable$var57$51;
													if((sample$var32 == sample$var120)) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int var86 = 0; var86 < noStates; var86 += 1) {
																for(int var82 = 0; var82 < noServers; var82 += 1) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var82 == server)) {
																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																				if((var86 == traceTempVariable$currentState$52_1)) {
																					int traceTempVariable$currentState$56_1 = distributionTempVariable$var57$51;
																					if((sample$var32 == sample$var120)) {
																						if((timeStep$var49 == timeStep$var140)) {
																							for(int var100 = 0; var100 < noStates; var100 += 1) {
																								for(int var96 = 0; var96 < noServers; var96 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var96 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var100 == traceTempVariable$currentState$56_1)) {
																													{
																														double var152 = current_metric_mean[server][traceTempVariable$currentState$56_1];
																														double var154 = current_metric_var[server][traceTempVariable$currentState$56_1];
																														
																														// Store the value of the function call, so the function call is only made once.
																														double cv$weightedProbability = (Math.log(cv$probabilitySample63Value50) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																														
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
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample63Value50);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$57 = 0; index$sample$57 < noSamples; index$sample$57 += 1) {
																						for(int index$timeStep$58 = 1; index$timeStep$58 < length$metric[index$sample$57][0]; index$timeStep$58 += 1) {
																							if(!((index$sample$57 == sample$var32) && (index$timeStep$58 == timeStep$var49))) {
																								// Enumerating the possible outputs of Categorical 56.
																								for(int index$sample63$59 = 0; index$sample63$59 < noStates; index$sample63$59 += 1) {
																									int distributionTempVariable$var57$61 = index$sample63$59;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample63Value60 = (cv$probabilitySample63Value50 * distribution$sample63[((index$sample$57 - 0) / 1)][((index$timeStep$58 - 1) / 1)][index$sample63$59]);
																									int traceTempVariable$currentState$62_1 = distributionTempVariable$var57$61;
																									if((index$sample$57 == sample$var120)) {
																										if((index$timeStep$58 == timeStep$var140)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$62_1)) {
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$62_1];
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$62_1];
																																		
																																		// Store the value of the function call, so the function call is only made once.
																																		double cv$weightedProbability = (Math.log(cv$probabilitySample63Value60) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																		
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
																																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample63Value60);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								
								// Enumerating the possible arguments for Gaussian 155.
								if(fixedFlag$sample63) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
											if((sample$var32 == sample$var120)) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int var86 = 0; var86 < noStates; var86 += 1) {
														for(int var82 = 0; var82 < noServers; var82 += 1) {
															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																if((var82 == server)) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var86 == st[sample$var120][timeStep$var140])) {
																			if(fixedFlag$sample50) {
																				for(int index$sample$75_1 = 0; index$sample$75_1 < noSamples; index$sample$75_1 += 1) {
																					if((index$sample$75_1 == sample$var120)) {
																						if((0 == timeStep$var140)) {
																							for(int var100 = 0; var100 < noStates; var100 += 1) {
																								for(int var96 = 0; var96 < noServers; var96 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var96 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var100 == st[sample$var120][timeStep$var140])) {
																													{
																														double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																														double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																														
																														// Store the value of the function call, so the function call is only made once.
																														double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																														
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
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				for(int index$sample$76 = 0; index$sample$76 < noSamples; index$sample$76 += 1) {
																					if(true) {
																						// Enumerating the possible outputs of Categorical 43.
																						for(int index$sample50$77 = 0; index$sample50$77 < noStates; index$sample50$77 += 1) {
																							int distributionTempVariable$var44$79 = index$sample50$77;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample50Value78 = (1.0 * distribution$sample50[((index$sample$76 - 0) / 1)][index$sample50$77]);
																							int traceTempVariable$currentState$80_1 = distributionTempVariable$var44$79;
																							if((index$sample$76 == sample$var120)) {
																								if((0 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$80_1)) {
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$80_1];
																																double var154 = current_metric_var[server][traceTempVariable$currentState$80_1];
																																
																																// Store the value of the function call, so the function call is only made once.
																																double cv$weightedProbability = (Math.log(cv$probabilitySample50Value78) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																
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
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample50Value78);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 56.
												for(int index$sample63$69 = 0; index$sample63$69 < noStates; index$sample63$69 += 1) {
													int distributionTempVariable$var57$71 = index$sample63$69;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample63Value70 = (1.0 * distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$69]);
													int traceTempVariable$currentState$72_1 = distributionTempVariable$var57$71;
													if((sample$var32 == sample$var120)) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int var86 = 0; var86 < noStates; var86 += 1) {
																for(int var82 = 0; var82 < noServers; var82 += 1) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var82 == server)) {
																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																				if((var86 == traceTempVariable$currentState$72_1)) {
																					if(fixedFlag$sample50) {
																						for(int index$sample$81_1 = 0; index$sample$81_1 < noSamples; index$sample$81_1 += 1) {
																							if((index$sample$81_1 == sample$var120)) {
																								if((0 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$72_1)) {
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$72_1];
																																double var154 = current_metric_var[server][traceTempVariable$currentState$72_1];
																																
																																// Store the value of the function call, so the function call is only made once.
																																double cv$weightedProbability = (Math.log(cv$probabilitySample63Value70) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																
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
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample63Value70);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					} else {
																						for(int index$sample$82 = 0; index$sample$82 < noSamples; index$sample$82 += 1) {
																							if(true) {
																								// Enumerating the possible outputs of Categorical 43.
																								for(int index$sample50$83 = 0; index$sample50$83 < noStates; index$sample50$83 += 1) {
																									int distributionTempVariable$var44$85 = index$sample50$83;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample50Value84 = (cv$probabilitySample63Value70 * distribution$sample50[((index$sample$82 - 0) / 1)][index$sample50$83]);
																									int traceTempVariable$currentState$86_1 = distributionTempVariable$var44$85;
																									if((index$sample$82 == sample$var120)) {
																										if((0 == timeStep$var140)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$86_1)) {
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$86_1];
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$86_1];
																																		
																																		// Store the value of the function call, so the function call is only made once.
																																		double cv$weightedProbability = (Math.log(cv$probabilitySample50Value84) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																		
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
																																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample50Value84);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
							double cv$sampleProbability = cv$distributionAccumulator;
							
							// Add the probability of this sample task to the sample task accumulator.
							cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
							
							// Add the probability of this instance of the random variable to the probability
							// of all instances of the random variable.
							cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
							logProbability$var155[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleAccumulator;
							
							// Store the sample task probability
							logProbability$sample173[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleProbability;
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
			fixedProbFlag$sample173 = ((((fixedFlag$sample173 && fixedFlag$sample50) && fixedFlag$sample63) && fixedFlag$sample96) && fixedFlag$sample111);
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
							double cv$rvAccumulator = 0.0;
							double cv$sampleValue = logProbability$sample173[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)];
							cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var155[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$rvAccumulator;
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

	// Calculate the probability of the samples represented by sample50 using probability
	// distributions.
	private final void logProbabilityDistribution$sample50() {
		// Determine if we need to calculate the values for sample task 50 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample50) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample50) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Copy of index so that its values can be safely substituted
					int index$sample$1 = sample$var32;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[sample$var32][0];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
					}
					if((cv$probabilityReached == 0.0))
						// Return negative infinity if no distribution probability space is reached.
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						// Scale the probability relative to the observed distribution space.
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var43[((sample$var32 - 0) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample50[((sample$var32 - 0) / 1)] = cv$sampleProbability;
				}
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample50)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample50)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedFlag$sample24);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample50[((sample$var32 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var43[((sample$var32 - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample50)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample63 using probability
	// distributions.
	private final void logProbabilityDistribution$sample63() {
		// Determine if we need to calculate the values for sample task 63 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample63) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample63) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
						// Accumulator for sample probabilities for a specific instance of the random variable.
						double cv$sampleAccumulator = 0.0;
						
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 63 including any distribution
						// values.
						// 
						// Copy of index so that its values can be safely substituted
						int index$timeStep$1 = timeStep$var49;
						
						// Copy of index so that its values can be safely substituted
						int index$sample$2 = sample$var32;
						{
							// The sample value to calculate the probability of generating
							int cv$sampleValue = st[sample$var32][timeStep$var49];
							
							// Enumerating the possible arguments for Categorical 56.
							if(fixedFlag$sample50) {
								for(int index$sample$4_1 = 0; index$sample$4_1 < noSamples; index$sample$4_1 += 1) {
									if((index$sample$4_1 == sample$var32)) {
										if((0 == (timeStep$var49 - 1))) {
											for(int var26 = 0; var26 < noStates; var26 += 1) {
												if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
													{
														double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														
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
											}
										}
									}
								}
							} else {
								for(int index$sample$5 = 0; index$sample$5 < noSamples; index$sample$5 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 43.
										for(int index$sample50$6 = 0; index$sample50$6 < noStates; index$sample50$6 += 1) {
											int distributionTempVariable$var44$8 = index$sample50$6;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample50Value7 = (1.0 * distribution$sample50[((index$sample$5 - 0) / 1)][index$sample50$6]);
											int traceTempVariable$var54$9_1 = distributionTempVariable$var44$8;
											if((index$sample$5 == sample$var32)) {
												if((0 == (timeStep$var49 - 1))) {
													for(int var26 = 0; var26 < noStates; var26 += 1) {
														if((var26 == traceTempVariable$var54$9_1)) {
															{
																double[] var55 = m[traceTempVariable$var54$9_1];
																
																// Store the value of the function call, so the function call is only made once.
																double cv$weightedProbability = (Math.log(cv$probabilitySample50Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																
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
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample50Value7);
															}
														}
													}
												}
											}
										}
									}
								}
							}
							
							// Enumerating the possible arguments for Categorical 56.
							int traceTempVariable$var54$12_1 = DistributionSampling.sampleCategorical(RNG$, m[st[index$sample$2][(index$timeStep$1 - 1)]]);
							if((index$sample$2 == sample$var32)) {
								if((index$timeStep$1 == (timeStep$var49 - 1))) {
									for(int var26 = 0; var26 < noStates; var26 += 1) {
										if((var26 == traceTempVariable$var54$12_1)) {
											{
												double[] var55 = m[traceTempVariable$var54$12_1];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY));
												
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
									}
								}
							}
							if(fixedFlag$sample63) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample$var32)) {
											if((index$timeStep$13_2 == (timeStep$var49 - 1))) {
												for(int var26 = 0; var26 < noStates; var26 += 1) {
													if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
														{
															double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
															
															// Store the value of the function call, so the function call is only made once.
															double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY));
															
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
												}
											}
										}
									}
								}
							} else {
								for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
									for(int index$timeStep$15 = 1; index$timeStep$15 < length$metric[index$sample$14][0]; index$timeStep$15 += 1) {
										if(!((index$sample$14 == index$sample$2) && (index$timeStep$15 == index$timeStep$1))) {
											// Enumerating the possible outputs of Categorical 56.
											for(int index$sample63$16 = 0; index$sample63$16 < noStates; index$sample63$16 += 1) {
												int distributionTempVariable$var57$18 = index$sample63$16;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample63Value17 = (1.0 * distribution$sample63[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample63$16]);
												int traceTempVariable$var54$19_1 = distributionTempVariable$var57$18;
												if((index$sample$14 == sample$var32)) {
													if((index$timeStep$15 == (timeStep$var49 - 1))) {
														for(int var26 = 0; var26 < noStates; var26 += 1) {
															if((var26 == traceTempVariable$var54$19_1)) {
																{
																	double[] var55 = m[traceTempVariable$var54$19_1];
																	
																	// Store the value of the function call, so the function call is only made once.
																	double cv$weightedProbability = (Math.log(cv$probabilitySample63Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	
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
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample63Value17);
																}
															}
														}
													}
												}
											}
										}
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
						double cv$sampleProbability = cv$distributionAccumulator;
						
						// Add the probability of this sample task to the sample task accumulator.
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
						
						// Add the probability of this instance of the random variable to the probability
						// of all instances of the random variable.
						cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
						logProbability$var56[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$sampleAccumulator;
						
						// Store the sample task probability
						logProbability$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$sampleProbability;
					}
				}
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample63)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample63)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample63 = ((fixedFlag$sample63 && fixedFlag$sample30) && fixedFlag$sample50);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var56[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample63)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample63)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample111 using sampled
	// values.
	private final void logProbabilityValue$sample111() {
		// Determine if we need to calculate the values for sample task 111 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample111) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				for(int var100 = 0; var100 < noStates; var100 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = current_metric_var[var96][var100];
						{
							{
								double var90 = 1.0;
								double var91 = 1.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var90, var91));
								
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
					}
					if((cv$probabilityReached == 0.0))
						// Return negative infinity if no distribution probability space is reached.
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						// Scale the probability relative to the observed distribution space.
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var92 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var101 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample111)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample111 = fixedFlag$sample111;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var101;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var92 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample111)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample126 using sampled
	// values.
	private final void logProbabilityValue$sample126() {
		// Determine if we need to calculate the values for sample task 126 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample126) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				for(int var114 = 0; var114 < noStates; var114 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = current_metric_valid_bias[var110][var114];
						{
							{
								double var104 = 1.0;
								double var105 = 1.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var104, var105));
								
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
					}
					if((cv$probabilityReached == 0.0))
						// Return negative infinity if no distribution probability space is reached.
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						// Scale the probability relative to the observed distribution space.
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var106 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var115 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample126 = fixedFlag$sample126;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var115;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var106 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample161 using sampled
	// values.
	private final void logProbabilityValue$sample161() {
		// Determine if we need to calculate the values for sample task 161 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample161) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						// Accumulator for sample probabilities for a specific instance of the random variable.
						double cv$sampleAccumulator = 0.0;
						
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						{
							// The sample value to calculate the probability of generating
							boolean cv$sampleValue = metric_valid_g[sample$var120][server][timeStep$var140];
							{
								{
									double var144 = current_metric_valid_bias[server][st[sample$var120][timeStep$var140]];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var144));
									
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
						}
						if((cv$probabilityReached == 0.0))
							// Return negative infinity if no distribution probability space is reached.
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							// Scale the probability relative to the observed distribution space.
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						double cv$sampleProbability = cv$distributionAccumulator;
						
						// Add the probability of this sample task to the sample task accumulator.
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
						
						// Add the probability of this instance of the random variable to the probability
						// of all instances of the random variable.
						cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
						logProbability$var145[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleAccumulator;
						
						// Store the sample task probability
						logProbability$sample161[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleProbability;
						
						// Guard to ensure that metric_valid_g is only updated once for this probability.
						boolean cv$guard$metric_valid_g = false;
						
						// Add probability to constructed variables that have guards, so need per sample probabilities
						// from the combined probability
						{
							// If the probability of the variable has not already been updated
							if(!cv$guard$metric_valid_g) {
								// Set the guard so the update is only applied once.
								cv$guard$metric_valid_g = true;
								
								// Update the variable probability
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
							}
						}
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
			fixedProbFlag$sample161 = (((fixedFlag$sample161 && fixedFlag$sample50) && fixedFlag$sample63) && fixedFlag$sample126);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample161[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var145[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$rvAccumulator;
						
						// Guard to ensure that metric_valid_g is only updated once for this probability.
						boolean cv$guard$metric_valid_g = false;
						
						// Add probability to constructed variables that have guards, so need per sample probabilities
						// from the combined probability
						{
							// If the probability of the variable has not already been updated
							if(!cv$guard$metric_valid_g) {
								// Set the guard so the update is only applied once.
								cv$guard$metric_valid_g = true;
								
								// Update the variable probability
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
							}
						}
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

	// Calculate the probability of the samples represented by sample173 using sampled
	// values.
	private final void logProbabilityValue$sample173() {
		// Determine if we need to calculate the values for sample task 173 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample173) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						if(metric_valid_g[sample$var120][server][timeStep$var140]) {
							// Accumulator for sample probabilities for a specific instance of the random variable.
							double cv$sampleAccumulator = 0.0;
							
							// An accumulator for log probabilities.
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							
							// An accumulator for the distributed probability space covered.
							double cv$probabilityReached = 0.0;
							{
								// The sample value to calculate the probability of generating
								double cv$sampleValue = metric_g[sample$var120][server][timeStep$var140];
								{
									{
										double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
										double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
										
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
							}
							if((cv$probabilityReached == 0.0))
								// Return negative infinity if no distribution probability space is reached.
								cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							else
								// Scale the probability relative to the observed distribution space.
								cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
							double cv$sampleProbability = cv$distributionAccumulator;
							
							// Add the probability of this sample task to the sample task accumulator.
							cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
							
							// Add the probability of this instance of the random variable to the probability
							// of all instances of the random variable.
							cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
							logProbability$var155[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleAccumulator;
							
							// Store the sample task probability
							logProbability$sample173[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleProbability;
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
			fixedProbFlag$sample173 = ((((fixedFlag$sample173 && fixedFlag$sample50) && fixedFlag$sample63) && fixedFlag$sample96) && fixedFlag$sample111);
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
							double cv$rvAccumulator = 0.0;
							double cv$sampleValue = logProbability$sample173[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)];
							cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var155[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$rvAccumulator;
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

	// Calculate the probability of the samples represented by sample24 using sampled
	// values.
	private final void logProbabilityValue$sample24() {
		// Determine if we need to calculate the values for sample task 24 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample24) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				// The sample value to calculate the probability of generating
				double[] cv$sampleValue = initialStateDistribution;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v));
						
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
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			
			// Add the probability of this sample task to the sample task accumulator.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var20 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$initialStateDistribution = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample24 = fixedFlag$sample24;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var20 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample30 using sampled
	// values.
	private final void logProbabilityValue$sample30() {
		// Determine if we need to calculate the values for sample task 30 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample30) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var26 = 0; var26 < noStates; var26 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = m[var26];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v));
							
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
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var22 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var27 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample30 = fixedFlag$sample30;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var27;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var22 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample50 using sampled
	// values.
	private final void logProbabilityValue$sample50() {
		// Determine if we need to calculate the values for sample task 50 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample50) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Copy of index so that its values can be safely substituted
				int index$sample$1 = sample$var32;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[sample$var32][0];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var43[((sample$var32 - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample50[((sample$var32 - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedFlag$sample24);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample50[((sample$var32 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var43[((sample$var32 - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample63 using sampled
	// values.
	private final void logProbabilityValue$sample63() {
		// Determine if we need to calculate the values for sample task 63 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample63) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Copy of index so that its values can be safely substituted
					int index$timeStep$1 = timeStep$var49;
					
					// Copy of index so that its values can be safely substituted
					int index$sample$2 = sample$var32;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[sample$var32][timeStep$var49];
						{
							{
								double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
					}
					if((cv$probabilityReached == 0.0))
						// Return negative infinity if no distribution probability space is reached.
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						// Scale the probability relative to the observed distribution space.
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var56[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample63)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample63 = ((fixedFlag$sample63 && fixedFlag$sample30) && fixedFlag$sample50);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var56[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample63)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample96 using sampled
	// values.
	private final void logProbabilityValue$sample96() {
		// Determine if we need to calculate the values for sample task 96 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample96) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var82 = 0; var82 < noServers; var82 += 1) {
				for(int var86 = 0; var86 < noStates; var86 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = current_metric_mean[var82][var86];
						{
							{
								double var76 = 0.0;
								double var77 = (double)max_metric;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((var76 <= cv$sampleValue) && (cv$sampleValue <= var77))?(-Math.log((var77 - var76))):Double.NEGATIVE_INFINITY));
								
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
					}
					if((cv$probabilityReached == 0.0))
						// Return negative infinity if no distribution probability space is reached.
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						// Scale the probability relative to the observed distribution space.
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var78 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var87 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample96)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample96 = fixedFlag$sample96;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var87;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var78 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample96)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 111 drawn from InverseGamma 92. Inference was performed using Metropolis-Hastings.
	private final void sample111(int var96, int var100) {
		// The original value of the sample
		double cv$originalValue = current_metric_var[var96][var100];
		
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability = 0.0;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		
		// Ensure the variance is at least 0.01
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		
		// The probability of the random variable generating the new sample value.
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// The value currently being tested
			double cv$currentValue;
			if((cv$valuePos == 0))
				// Set the current value to the current state of the tree.
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				
				// Update Sample and intermediate values
				{
					// Write out the value of the sample to a temporary variable prior to updating the
					// intermediate variables.
					double var101 = cv$proposedValue;
					double[] var97 = current_metric_var[var96];
					var97[var100] = cv$currentValue;
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var90;
				{
					cv$temp$0$var90 = 1.0;
				}
				double cv$temp$1$var91;
				{
					cv$temp$1$var91 = 1.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var90, cv$temp$1$var91));
				
				// Processing random variable 155.
				{
					// Looking for a path between Sample 111 and consumer Gaussian 155.
					{
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if(fixedFlag$sample50) {
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											if((sample$var32 == sample$var120)) {
												if((0 == timeStep$var140)) {
													double traceTempVariable$var154$10_1 = cv$currentValue;
													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
														if((var96 == server)) {
															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																if((var100 == st[sample$var120][timeStep$var140])) {
																	// Processing sample task 173 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																			// the output of Sample task 111.
																			for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																				if((index$sample$28_1 == sample$var120)) {
																					if((0 == timeStep$var140)) {
																						for(int var86 = 0; var86 < noStates; var86 += 1) {
																							for(int var82 = 0; var82 < noServers; var82 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var82 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var86 == st[sample$var120][timeStep$var140])) {
																												{
																													{
																														double cv$temp$2$var152;
																														{
																															// Constructing a random variable input for use later.
																															double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																															cv$temp$2$var152 = var152;
																														}
																														double cv$temp$3$var154;
																														{
																															// Constructing a random variable input for use later.
																															double var154 = traceTempVariable$var154$10_1;
																															cv$temp$3$var154 = var154;
																														}
																														
																														// Record the probability of sample task 173 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))));
																														}
																														
																														// Recorded the probability of reaching sample task 173 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			
																			// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																			// the output of Sample task 111.
																			if(fixedFlag$sample63) {
																				for(int index$sample$30_1 = 0; index$sample$30_1 < noSamples; index$sample$30_1 += 1) {
																					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$30_1][0]; timeStep$var49 += 1) {
																						if((index$sample$30_1 == sample$var120)) {
																							if((timeStep$var49 == timeStep$var140)) {
																								for(int var86 = 0; var86 < noStates; var86 += 1) {
																									for(int var82 = 0; var82 < noServers; var82 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var82 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var86 == st[sample$var120][timeStep$var140])) {
																														{
																															{
																																double cv$temp$4$var152;
																																{
																																	// Constructing a random variable input for use later.
																																	double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$4$var152 = var152;
																																}
																																double cv$temp$5$var154;
																																{
																																	// Constructing a random variable input for use later.
																																	double var154 = traceTempVariable$var154$10_1;
																																	cv$temp$5$var154 = var154;
																																}
																																
																																// Record the probability of sample task 173 generating output with current configuration.
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))));
																																}
																																
																																// Recorded the probability of reaching sample task 173 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				for(int index$sample$31 = 0; index$sample$31 < noSamples; index$sample$31 += 1) {
																					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$31][0]; timeStep$var49 += 1) {
																						if(true) {
																							// Enumerating the possible outputs of Categorical 56.
																							for(int index$sample63$33 = 0; index$sample63$33 < noStates; index$sample63$33 += 1) {
																								int distributionTempVariable$var57$35 = index$sample63$33;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample63Value34 = (1.0 * distribution$sample63[((index$sample$31 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$33]);
																								int traceTempVariable$currentState$36_1 = distributionTempVariable$var57$35;
																								if((index$sample$31 == sample$var120)) {
																									if((timeStep$var49 == timeStep$var140)) {
																										for(int var86 = 0; var86 < noStates; var86 += 1) {
																											for(int var82 = 0; var82 < noServers; var82 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var82 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var86 == traceTempVariable$currentState$36_1)) {
																																{
																																	{
																																		double cv$temp$6$var152;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$36_1];
																																			cv$temp$6$var152 = var152;
																																		}
																																		double cv$temp$7$var154;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var154 = traceTempVariable$var154$10_1;
																																			cv$temp$7$var154 = var154;
																																		}
																																		
																																		// Record the probability of sample task 173 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 173 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value34);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
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
														}
													}
												}
											}
										}
									} else {
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 43.
												for(int index$sample50$6 = 0; index$sample50$6 < noStates; index$sample50$6 += 1) {
													int distributionTempVariable$var44$8 = index$sample50$6;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample50Value7 = (1.0 * distribution$sample50[((sample$var32 - 0) / 1)][index$sample50$6]);
													int traceTempVariable$currentState$9_1 = distributionTempVariable$var44$8;
													if((sample$var32 == sample$var120)) {
														if((0 == timeStep$var140)) {
															double traceTempVariable$var154$11_1 = cv$currentValue;
															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																if((var96 == server)) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var100 == traceTempVariable$currentState$9_1)) {
																			// Processing sample task 173 of consumer random variable null.
																			{
																				// Set an accumulator to sum the probabilities for each possible configuration of
																				// inputs.
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				
																				// Set an accumulator to record the consumer distributions not seen. Initially set
																				// to 1 as seen values will be deducted from this value.
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																					// the output of Sample task 111.
																					int traceTempVariable$currentState$39_1 = distributionTempVariable$var44$8;
																					if((sample$var32 == sample$var120)) {
																						if((0 == timeStep$var140)) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$39_1)) {
																													{
																														{
																															double cv$temp$8$var152;
																															{
																																// Constructing a random variable input for use later.
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$39_1];
																																cv$temp$8$var152 = var152;
																															}
																															double cv$temp$9$var154;
																															{
																																// Constructing a random variable input for use later.
																																double var154 = traceTempVariable$var154$11_1;
																																cv$temp$9$var154 = var154;
																															}
																															
																															// Record the probability of sample task 173 generating output with current configuration.
																															if(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																// If the second value is -infinity.
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))));
																															}
																															
																															// Recorded the probability of reaching sample task 173 with the current configuration.
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value7);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$40 = 0; index$sample$40 < noSamples; index$sample$40 += 1) {
																						if(!(index$sample$40 == sample$var32)) {
																							// Enumerating the possible outputs of Categorical 43.
																							for(int index$sample50$41 = 0; index$sample50$41 < noStates; index$sample50$41 += 1) {
																								int distributionTempVariable$var44$43 = index$sample50$41;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample50Value42 = (cv$probabilitySample50Value7 * distribution$sample50[((index$sample$40 - 0) / 1)][index$sample50$41]);
																								int traceTempVariable$currentState$44_1 = distributionTempVariable$var44$43;
																								if((index$sample$40 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var86 = 0; var86 < noStates; var86 += 1) {
																											for(int var82 = 0; var82 < noServers; var82 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var82 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var86 == traceTempVariable$currentState$44_1)) {
																																{
																																	{
																																		double cv$temp$10$var152;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$44_1];
																																			cv$temp$10$var152 = var152;
																																		}
																																		double cv$temp$11$var154;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var154 = traceTempVariable$var154$11_1;
																																			cv$temp$11$var154 = var154;
																																		}
																																		
																																		// Record the probability of sample task 173 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 173 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value42);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					
																					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																					// the output of Sample task 111.
																					if(fixedFlag$sample63) {
																						for(int index$sample$47_1 = 0; index$sample$47_1 < noSamples; index$sample$47_1 += 1) {
																							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$47_1][0]; timeStep$var49 += 1) {
																								if((index$sample$47_1 == sample$var120)) {
																									if((timeStep$var49 == timeStep$var140)) {
																										for(int var86 = 0; var86 < noStates; var86 += 1) {
																											for(int var82 = 0; var82 < noServers; var82 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var82 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var86 == traceTempVariable$currentState$9_1)) {
																																{
																																	{
																																		double cv$temp$12$var152;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$9_1];
																																			cv$temp$12$var152 = var152;
																																		}
																																		double cv$temp$13$var154;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var154 = traceTempVariable$var154$11_1;
																																			cv$temp$13$var154 = var154;
																																		}
																																		
																																		// Record the probability of sample task 173 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 173 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value7);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					} else {
																						for(int index$sample$48 = 0; index$sample$48 < noSamples; index$sample$48 += 1) {
																							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$48][0]; timeStep$var49 += 1) {
																								if(true) {
																									// Enumerating the possible outputs of Categorical 56.
																									for(int index$sample63$50 = 0; index$sample63$50 < noStates; index$sample63$50 += 1) {
																										int distributionTempVariable$var57$52 = index$sample63$50;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample63Value51 = (cv$probabilitySample50Value7 * distribution$sample63[((index$sample$48 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$50]);
																										int traceTempVariable$currentState$53_1 = distributionTempVariable$var57$52;
																										if((index$sample$48 == sample$var120)) {
																											if((timeStep$var49 == timeStep$var140)) {
																												for(int var86 = 0; var86 < noStates; var86 += 1) {
																													for(int var82 = 0; var82 < noServers; var82 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var82 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var86 == traceTempVariable$currentState$53_1)) {
																																		{
																																			{
																																				double cv$temp$14$var152;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$53_1];
																																					cv$temp$14$var152 = var152;
																																				}
																																				double cv$temp$15$var154;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var154 = traceTempVariable$var154$11_1;
																																					cv$temp$15$var154 = var154;
																																				}
																																				
																																				// Record the probability of sample task 173 generating output with current configuration.
																																				if(((Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))));
																																				}
																																				
																																				// Recorded the probability of reaching sample task 173 with the current configuration.
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value51);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if(fixedFlag$sample63) {
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
												if((sample$var32 == sample$var120)) {
													if((timeStep$var49 == timeStep$var140)) {
														double traceTempVariable$var154$22_1 = cv$currentValue;
														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
															if((var96 == server)) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var100 == st[sample$var120][timeStep$var140])) {
																		// Processing sample task 173 of consumer random variable null.
																		{
																			// Set an accumulator to sum the probabilities for each possible configuration of
																			// inputs.
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			
																			// Set an accumulator to record the consumer distributions not seen. Initially set
																			// to 1 as seen values will be deducted from this value.
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																				// the output of Sample task 111.
																				if(fixedFlag$sample50) {
																					for(int index$sample$56_1 = 0; index$sample$56_1 < noSamples; index$sample$56_1 += 1) {
																						if((index$sample$56_1 == sample$var120)) {
																							if((0 == timeStep$var140)) {
																								for(int var86 = 0; var86 < noStates; var86 += 1) {
																									for(int var82 = 0; var82 < noServers; var82 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var82 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var86 == st[sample$var120][timeStep$var140])) {
																														{
																															{
																																double cv$temp$16$var152;
																																{
																																	// Constructing a random variable input for use later.
																																	double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$16$var152 = var152;
																																}
																																double cv$temp$17$var154;
																																{
																																	// Constructing a random variable input for use later.
																																	double var154 = traceTempVariable$var154$22_1;
																																	cv$temp$17$var154 = var154;
																																}
																																
																																// Record the probability of sample task 173 generating output with current configuration.
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))));
																																}
																																
																																// Recorded the probability of reaching sample task 173 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int index$sample$57 = 0; index$sample$57 < noSamples; index$sample$57 += 1) {
																						if(true) {
																							// Enumerating the possible outputs of Categorical 43.
																							for(int index$sample50$58 = 0; index$sample50$58 < noStates; index$sample50$58 += 1) {
																								int distributionTempVariable$var44$60 = index$sample50$58;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample50Value59 = (1.0 * distribution$sample50[((index$sample$57 - 0) / 1)][index$sample50$58]);
																								int traceTempVariable$currentState$61_1 = distributionTempVariable$var44$60;
																								if((index$sample$57 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var86 = 0; var86 < noStates; var86 += 1) {
																											for(int var82 = 0; var82 < noServers; var82 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var82 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var86 == traceTempVariable$currentState$61_1)) {
																																{
																																	{
																																		double cv$temp$18$var152;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$61_1];
																																			cv$temp$18$var152 = var152;
																																		}
																																		double cv$temp$19$var154;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var154 = traceTempVariable$var154$22_1;
																																			cv$temp$19$var154 = var154;
																																		}
																																		
																																		// Record the probability of sample task 173 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 173 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value59);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				
																				// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																				// the output of Sample task 111.
																				for(int index$sample$64_1 = 0; index$sample$64_1 < noSamples; index$sample$64_1 += 1) {
																					for(int index$timeStep$64_2 = 1; index$timeStep$64_2 < length$metric[index$sample$64_1][0]; index$timeStep$64_2 += 1) {
																						if((index$sample$64_1 == sample$var120)) {
																							if((index$timeStep$64_2 == timeStep$var140)) {
																								for(int var86 = 0; var86 < noStates; var86 += 1) {
																									for(int var82 = 0; var82 < noServers; var82 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var82 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var86 == st[sample$var120][timeStep$var140])) {
																														{
																															{
																																double cv$temp$20$var152;
																																{
																																	// Constructing a random variable input for use later.
																																	double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$20$var152 = var152;
																																}
																																double cv$temp$21$var154;
																																{
																																	// Constructing a random variable input for use later.
																																	double var154 = traceTempVariable$var154$22_1;
																																	cv$temp$21$var154 = var154;
																																}
																																
																																// Record the probability of sample task 173 generating output with current configuration.
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))));
																																}
																																
																																// Recorded the probability of reaching sample task 173 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
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
															}
														}
													}
												}
											}
										}
									} else {
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
												if(true) {
													// Enumerating the possible outputs of Categorical 56.
													for(int index$sample63$18 = 0; index$sample63$18 < noStates; index$sample63$18 += 1) {
														int distributionTempVariable$var57$20 = index$sample63$18;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample63Value19 = (1.0 * distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$18]);
														int traceTempVariable$currentState$21_1 = distributionTempVariable$var57$20;
														if((sample$var32 == sample$var120)) {
															if((timeStep$var49 == timeStep$var140)) {
																double traceTempVariable$var154$23_1 = cv$currentValue;
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var96 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var100 == traceTempVariable$currentState$21_1)) {
																				// Processing sample task 173 of consumer random variable null.
																				{
																					// Set an accumulator to sum the probabilities for each possible configuration of
																					// inputs.
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					
																					// Set an accumulator to record the consumer distributions not seen. Initially set
																					// to 1 as seen values will be deducted from this value.
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																						// the output of Sample task 111.
																						if(fixedFlag$sample50) {
																							for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
																								if((index$sample$66_1 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var86 = 0; var86 < noStates; var86 += 1) {
																											for(int var82 = 0; var82 < noServers; var82 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var82 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var86 == traceTempVariable$currentState$21_1)) {
																																{
																																	{
																																		double cv$temp$22$var152;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$21_1];
																																			cv$temp$22$var152 = var152;
																																		}
																																		double cv$temp$23$var154;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var154 = traceTempVariable$var154$23_1;
																																			cv$temp$23$var154 = var154;
																																		}
																																		
																																		// Record the probability of sample task 173 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 173 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value19);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						} else {
																							for(int index$sample$67 = 0; index$sample$67 < noSamples; index$sample$67 += 1) {
																								if(true) {
																									// Enumerating the possible outputs of Categorical 43.
																									for(int index$sample50$68 = 0; index$sample50$68 < noStates; index$sample50$68 += 1) {
																										int distributionTempVariable$var44$70 = index$sample50$68;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample50Value69 = (cv$probabilitySample63Value19 * distribution$sample50[((index$sample$67 - 0) / 1)][index$sample50$68]);
																										int traceTempVariable$currentState$71_1 = distributionTempVariable$var44$70;
																										if((index$sample$67 == sample$var120)) {
																											if((0 == timeStep$var140)) {
																												for(int var86 = 0; var86 < noStates; var86 += 1) {
																													for(int var82 = 0; var82 < noServers; var82 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var82 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var86 == traceTempVariable$currentState$71_1)) {
																																		{
																																			{
																																				double cv$temp$24$var152;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$71_1];
																																					cv$temp$24$var152 = var152;
																																				}
																																				double cv$temp$25$var154;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var154 = traceTempVariable$var154$23_1;
																																					cv$temp$25$var154 = var154;
																																				}
																																				
																																				// Record the probability of sample task 173 generating output with current configuration.
																																				if(((Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))));
																																				}
																																				
																																				// Recorded the probability of reaching sample task 173 with the current configuration.
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value69);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						
																						// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																						// the output of Sample task 111.
																						int traceTempVariable$currentState$74_1 = distributionTempVariable$var57$20;
																						if((sample$var32 == sample$var120)) {
																							if((timeStep$var49 == timeStep$var140)) {
																								for(int var86 = 0; var86 < noStates; var86 += 1) {
																									for(int var82 = 0; var82 < noServers; var82 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var82 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var86 == traceTempVariable$currentState$74_1)) {
																														{
																															{
																																double cv$temp$26$var152;
																																{
																																	// Constructing a random variable input for use later.
																																	double var152 = current_metric_mean[server][traceTempVariable$currentState$74_1];
																																	cv$temp$26$var152 = var152;
																																}
																																double cv$temp$27$var154;
																																{
																																	// Constructing a random variable input for use later.
																																	double var154 = traceTempVariable$var154$23_1;
																																	cv$temp$27$var154 = var154;
																																}
																																
																																// Record the probability of sample task 173 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))));
																																}
																																
																																// Recorded the probability of reaching sample task 173 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value19);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$sample$75 = 0; index$sample$75 < noSamples; index$sample$75 += 1) {
																							for(int index$timeStep$76 = 1; index$timeStep$76 < length$metric[index$sample$75][0]; index$timeStep$76 += 1) {
																								if(!((index$sample$75 == sample$var32) && (index$timeStep$76 == timeStep$var49))) {
																									// Enumerating the possible outputs of Categorical 56.
																									for(int index$sample63$77 = 0; index$sample63$77 < noStates; index$sample63$77 += 1) {
																										int distributionTempVariable$var57$79 = index$sample63$77;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample63Value78 = (cv$probabilitySample63Value19 * distribution$sample63[((index$sample$75 - 0) / 1)][((index$timeStep$76 - 1) / 1)][index$sample63$77]);
																										int traceTempVariable$currentState$80_1 = distributionTempVariable$var57$79;
																										if((index$sample$75 == sample$var120)) {
																											if((index$timeStep$76 == timeStep$var140)) {
																												for(int var86 = 0; var86 < noStates; var86 += 1) {
																													for(int var82 = 0; var82 < noServers; var82 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var82 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var86 == traceTempVariable$currentState$80_1)) {
																																		{
																																			{
																																				double cv$temp$28$var152;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$80_1];
																																					cv$temp$28$var152 = var152;
																																				}
																																				double cv$temp$29$var154;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var154 = traceTempVariable$var154$23_1;
																																					cv$temp$29$var154 = var154;
																																				}
																																				
																																				// Record the probability of sample task 173 generating output with current configuration.
																																				if(((Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))));
																																				}
																																				
																																				// Recorded the probability of reaching sample task 173 with the current configuration.
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value78);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
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
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
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
			
			// Save the probability of the original value.
			if((cv$valuePos == 0))
				cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			
			// Save the probability of the proposed value.
			else
				cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// The probability ration for the proposed value and the current value.
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			double var101 = cv$originalValue;
			double[] var97 = current_metric_var[var96];
			var97[var100] = var101;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 126 drawn from Beta 106. Inference was performed using a Beta to
	// Bernoulli/Binomial conjugate prior.
	private final void sample126(int var110, int var114) {
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		{
			// Processing random variable 145.
			{
				// Looking for a path between Sample 126 and consumer Bernoulli 145.
				{
					for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
						for(int server = 0; server < noServers; server += 1) {
							for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
								if(fixedFlag$sample50) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										if((sample$var32 == sample$var120)) {
											if((0 == timeStep$var140)) {
												if((var110 == server)) {
													if((var114 == st[sample$var120][timeStep$var140])) {
														// Processing sample task 161 of consumer random variable null.
														{
															{
																{
																	{
																		{
																			// Include the value sampled by task 161 from random variable var145.
																			// Increment the number of samples.
																			cv$count = (cv$count + 1.0);
																			
																			// If the sample value was positive increase the count
																			if(metric_valid_g[sample$var120][server][timeStep$var140])
																				cv$sum = (cv$sum + 1.0);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 43.
											for(int index$sample50$6 = 0; index$sample50$6 < noStates; index$sample50$6 += 1) {
												int distributionTempVariable$var44$8 = index$sample50$6;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample50Value7 = (1.0 * distribution$sample50[((sample$var32 - 0) / 1)][index$sample50$6]);
												int traceTempVariable$currentState$9_1 = distributionTempVariable$var44$8;
												if((sample$var32 == sample$var120)) {
													if((0 == timeStep$var140)) {
														if((var110 == server)) {
															if((var114 == traceTempVariable$currentState$9_1)) {
																// Processing sample task 161 of consumer random variable null.
																{
																	{
																		{
																			{
																				{
																					// Include the value sampled by task 161 from random variable var145.
																					// Increment the number of samples.
																					cv$count = (cv$count + cv$probabilitySample50Value7);
																					
																					// If the sample value was positive increase the count
																					if(metric_valid_g[sample$var120][server][timeStep$var140])
																						cv$sum = (cv$sum + cv$probabilitySample50Value7);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
						for(int server = 0; server < noServers; server += 1) {
							for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
								if(fixedFlag$sample63) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
											if((sample$var32 == sample$var120)) {
												if((timeStep$var49 == timeStep$var140)) {
													if((var110 == server)) {
														if((var114 == st[sample$var120][timeStep$var140])) {
															// Processing sample task 161 of consumer random variable null.
															{
																{
																	{
																		{
																			{
																				// Include the value sampled by task 161 from random variable var145.
																				// Increment the number of samples.
																				cv$count = (cv$count + 1.0);
																				
																				// If the sample value was positive increase the count
																				if(metric_valid_g[sample$var120][server][timeStep$var140])
																					cv$sum = (cv$sum + 1.0);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 56.
												for(int index$sample63$18 = 0; index$sample63$18 < noStates; index$sample63$18 += 1) {
													int distributionTempVariable$var57$20 = index$sample63$18;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample63Value19 = (1.0 * distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$18]);
													int traceTempVariable$currentState$21_1 = distributionTempVariable$var57$20;
													if((sample$var32 == sample$var120)) {
														if((timeStep$var49 == timeStep$var140)) {
															if((var110 == server)) {
																if((var114 == traceTempVariable$currentState$21_1)) {
																	// Processing sample task 161 of consumer random variable null.
																	{
																		{
																			{
																				{
																					{
																						// Include the value sampled by task 161 from random variable var145.
																						// Increment the number of samples.
																						cv$count = (cv$count + cv$probabilitySample63Value19);
																						
																						// If the sample value was positive increase the count
																						if(metric_valid_g[sample$var120][server][timeStep$var140])
																							cv$sum = (cv$sum + cv$probabilitySample63Value19);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		double var115 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		double[] var111 = current_metric_valid_bias[var110];
		var111[var114] = var115;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 24 drawn from Dirichlet 20. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample24() {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = initialStateDistribution;
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var21$countGlobal;
		
		// Get the length of the array
		int cv$arrayLength = noStates;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 43.
			{
				{
					for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
						if(fixedFlag$sample50) {
							// Processing sample task 50 of consumer random variable null.
							{
								// Copy of index so that its values can be safely substituted
								int index$sample$3 = sample$var32;
								{
									{
										{
											{
												// Increment the sample counter with the value sampled by sample task 50 of random
												// variable var43
												cv$countLocal[st[sample$var32][0]] = (cv$countLocal[st[sample$var32][0]] + 1.0);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 43.
		{
			{
				for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
					if(!fixedFlag$sample50) {
						// Processing sample task 50 of consumer random variable null.
						{
							// Copy of index so that its values can be safely substituted
							int index$sample$7 = sample$var32;
							{
								{
									// Declare and zero an accumulator for tracking the reached source probability space.
									double scopeVariable$reachedSourceProbability = 0.0;
									{
										// Add the probability of this argument configuration.
										scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
									}
									
									// The probability of reaching the consumer with this set of consumer arguments
									double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
									
									// Merge the distribution probabilities into the count
									for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
										cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample50[((sample$var32 - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
								}
							}
						}
					}
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 30 drawn from Dirichlet 22. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample30(int var26) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = m[var26];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var27$countGlobal;
		
		// Get the length of the array
		int cv$arrayLength = noStates;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 56.
			{
				// Looking for a path between Sample 30 and consumer Categorical 56.
				{
					for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
						for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
							if(fixedFlag$sample50) {
								for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
									if((index$sample$3_1 == sample$var32)) {
										if((0 == (timeStep$var49 - 1))) {
											if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
												if(fixedFlag$sample63) {
													// Processing sample task 63 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$timeStep$23 = timeStep$var49;
														
														// Copy of index so that its values can be safely substituted
														int index$sample$24 = sample$var32;
														{
															{
																{
																	{
																		// Increment the sample counter with the value sampled by sample task 63 of random
																		// variable var56
																		cv$countLocal[st[sample$var32][timeStep$var49]] = (cv$countLocal[st[sample$var32][timeStep$var49]] + 1.0);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 43.
										for(int index$sample50$5 = 0; index$sample50$5 < noStates; index$sample50$5 += 1) {
											int distributionTempVariable$var44$7 = index$sample50$5;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample50Value6 = (1.0 * distribution$sample50[((index$sample$4 - 0) / 1)][index$sample50$5]);
											int traceTempVariable$var54$8_1 = distributionTempVariable$var44$7;
											if((index$sample$4 == sample$var32)) {
												if((0 == (timeStep$var49 - 1))) {
													if((var26 == traceTempVariable$var54$8_1)) {
														if(fixedFlag$sample63) {
															// Processing sample task 63 of consumer random variable null.
															{
																// Copy of index so that its values can be safely substituted
																int index$timeStep$26 = timeStep$var49;
																
																// Copy of index so that its values can be safely substituted
																int index$sample$27 = sample$var32;
																{
																	{
																		{
																			{
																				// Increment the sample counter with the value sampled by sample task 63 of random
																				// variable var56
																				cv$countLocal[st[sample$var32][timeStep$var49]] = (cv$countLocal[st[sample$var32][timeStep$var49]] + cv$probabilitySample50Value6);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
						for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
							if(fixedFlag$sample63) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample$var32)) {
											if((index$timeStep$13_2 == (timeStep$var49 - 1))) {
												if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
													if(fixedFlag$sample63) {
														// Processing sample task 63 of consumer random variable null.
														{
															// Copy of index so that its values can be safely substituted
															int index$timeStep$29 = timeStep$var49;
															
															// Copy of index so that its values can be safely substituted
															int index$sample$30 = sample$var32;
															{
																{
																	{
																		{
																			// Increment the sample counter with the value sampled by sample task 63 of random
																			// variable var56
																			cv$countLocal[st[sample$var32][timeStep$var49]] = (cv$countLocal[st[sample$var32][timeStep$var49]] + 1.0);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
									for(int index$timeStep$15 = 1; index$timeStep$15 < length$metric[index$sample$14][0]; index$timeStep$15 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 56.
											for(int index$sample63$16 = 0; index$sample63$16 < noStates; index$sample63$16 += 1) {
												int distributionTempVariable$var57$18 = index$sample63$16;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample63Value17 = (1.0 * distribution$sample63[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample63$16]);
												int traceTempVariable$var54$19_1 = distributionTempVariable$var57$18;
												if((index$sample$14 == sample$var32)) {
													if((index$timeStep$15 == (timeStep$var49 - 1))) {
														if((var26 == traceTempVariable$var54$19_1)) {
															if(fixedFlag$sample63) {
																// Processing sample task 63 of consumer random variable null.
																{
																	// Copy of index so that its values can be safely substituted
																	int index$timeStep$32 = timeStep$var49;
																	
																	// Copy of index so that its values can be safely substituted
																	int index$sample$33 = sample$var32;
																	{
																		{
																			{
																				{
																					// Increment the sample counter with the value sampled by sample task 63 of random
																					// variable var56
																					cv$countLocal[st[sample$var32][timeStep$var49]] = (cv$countLocal[st[sample$var32][timeStep$var49]] + cv$probabilitySample63Value17);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 56.
		{
			// Looking for a path between Sample 30 and consumer Categorical 56.
			{
				for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
						if(fixedFlag$sample50) {
							for(int index$sample$40_1 = 0; index$sample$40_1 < noSamples; index$sample$40_1 += 1) {
								if((index$sample$40_1 == sample$var32)) {
									if((0 == (timeStep$var49 - 1))) {
										if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
											if(!fixedFlag$sample63) {
												// Processing sample task 63 of consumer random variable null.
												{
													// Copy of index so that its values can be safely substituted
													int index$timeStep$60 = timeStep$var49;
													
													// Copy of index so that its values can be safely substituted
													int index$sample$61 = sample$var32;
													{
														{
															// Declare and zero an accumulator for tracking the reached source probability space.
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																// Add the probability of this argument configuration.
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															
															// The probability of reaching the consumer with this set of consumer arguments
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															
															// Merge the distribution probabilities into the count
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							for(int index$sample$41 = 0; index$sample$41 < noSamples; index$sample$41 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 43.
									for(int index$sample50$42 = 0; index$sample50$42 < noStates; index$sample50$42 += 1) {
										int distributionTempVariable$var44$44 = index$sample50$42;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample50Value43 = (1.0 * distribution$sample50[((index$sample$41 - 0) / 1)][index$sample50$42]);
										int traceTempVariable$var54$45_1 = distributionTempVariable$var44$44;
										if((index$sample$41 == sample$var32)) {
											if((0 == (timeStep$var49 - 1))) {
												if((var26 == traceTempVariable$var54$45_1)) {
													if(!fixedFlag$sample63) {
														// Processing sample task 63 of consumer random variable null.
														{
															// Copy of index so that its values can be safely substituted
															int index$timeStep$63 = timeStep$var49;
															
															// Copy of index so that its values can be safely substituted
															int index$sample$64 = sample$var32;
															{
																{
																	// Declare and zero an accumulator for tracking the reached source probability space.
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		// Add the probability of this argument configuration.
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	
																	// The probability of reaching the consumer with this set of consumer arguments
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample50Value43);
																	
																	// Merge the distribution probabilities into the count
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
						if(fixedFlag$sample63) {
							for(int index$sample$50_1 = 0; index$sample$50_1 < noSamples; index$sample$50_1 += 1) {
								for(int index$timeStep$50_2 = 1; index$timeStep$50_2 < length$metric[index$sample$50_1][0]; index$timeStep$50_2 += 1) {
									if((index$sample$50_1 == sample$var32)) {
										if((index$timeStep$50_2 == (timeStep$var49 - 1))) {
											if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
												if(!fixedFlag$sample63) {
													// Processing sample task 63 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$timeStep$66 = timeStep$var49;
														
														// Copy of index so that its values can be safely substituted
														int index$sample$67 = sample$var32;
														{
															{
																// Declare and zero an accumulator for tracking the reached source probability space.
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	// Add the probability of this argument configuration.
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																
																// The probability of reaching the consumer with this set of consumer arguments
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																
																// Merge the distribution probabilities into the count
																for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
															}
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							for(int index$sample$51 = 0; index$sample$51 < noSamples; index$sample$51 += 1) {
								for(int index$timeStep$52 = 1; index$timeStep$52 < length$metric[index$sample$51][0]; index$timeStep$52 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 56.
										for(int index$sample63$53 = 0; index$sample63$53 < noStates; index$sample63$53 += 1) {
											int distributionTempVariable$var57$55 = index$sample63$53;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample63Value54 = (1.0 * distribution$sample63[((index$sample$51 - 0) / 1)][((index$timeStep$52 - 1) / 1)][index$sample63$53]);
											int traceTempVariable$var54$56_1 = distributionTempVariable$var57$55;
											if((index$sample$51 == sample$var32)) {
												if((index$timeStep$52 == (timeStep$var49 - 1))) {
													if((var26 == traceTempVariable$var54$56_1)) {
														if(!fixedFlag$sample63) {
															// Processing sample task 63 of consumer random variable null.
															{
																// Copy of index so that its values can be safely substituted
																int index$timeStep$69 = timeStep$var49;
																
																// Copy of index so that its values can be safely substituted
																int index$sample$70 = sample$var32;
																{
																	{
																		// Declare and zero an accumulator for tracking the reached source probability space.
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			// Add the probability of this argument configuration.
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		
																		// The probability of reaching the consumer with this set of consumer arguments
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample63Value54);
																		
																		// Merge the distribution probabilities into the count
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 50 drawn from Categorical 43. Inference was performed using variable
	// marginalization.
	private final void sample50(int sample$var32) {
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var44$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			// Copy of index so that its values can be safely substituted
			int index$sample$1 = sample$var32;
			
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// The value currently being tested
			int cv$currentValue;
			
			// Value of the variable at this index
			cv$currentValue = cv$valuePos;
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$initialStateDistribution;
				{
					cv$temp$0$initialStateDistribution = initialStateDistribution;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$initialStateDistribution.length))?Math.log(cv$temp$0$initialStateDistribution[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 56.
				{
					// Looking for a path between Sample 50 and consumer Categorical 56.
					{
						int traceTempVariable$var54$2_1 = cv$currentValue;
						for(int index$sample$2_2 = 0; index$sample$2_2 < noSamples; index$sample$2_2 += 1) {
							if((sample$var32 == index$sample$2_2)) {
								for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$2_2][0]; timeStep$var49 += 1) {
									if((0 == (timeStep$var49 - 1))) {
										if(fixedFlag$sample63) {
											// Processing sample task 63 of consumer random variable null.
											{
												// Copy of index so that its values can be safely substituted
												int index$timeStep$4 = timeStep$var49;
												
												// Copy of index so that its values can be safely substituted
												int index$sample$5 = index$sample$2_2;
												
												// Set an accumulator to sum the probabilities for each possible configuration of
												// inputs.
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												
												// Set an accumulator to record the consumer distributions not seen. Initially set
												// to 1 as seen values will be deducted from this value.
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													// Enumerating the possible arguments for the variable Categorical 56 which is consuming
													// the output of Sample task 50.
													for(int var26 = 0; var26 < noStates; var26 += 1) {
														if((var26 == traceTempVariable$var54$2_1)) {
															{
																{
																	double[] cv$temp$1$var55;
																	{
																		// Constructing a random variable input for use later.
																		double[] var55 = m[traceTempVariable$var54$2_1];
																		cv$temp$1$var55 = var55;
																	}
																	
																	// Record the probability of sample task 63 generating output with current configuration.
																	if(((Math.log(1.0) + (((0.0 <= st[index$sample$2_2][timeStep$var49]) && (st[index$sample$2_2][timeStep$var49] < cv$temp$1$var55.length))?Math.log(cv$temp$1$var55[st[index$sample$2_2][timeStep$var49]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$sample$2_2][timeStep$var49]) && (st[index$sample$2_2][timeStep$var49] < cv$temp$1$var55.length))?Math.log(cv$temp$1$var55[st[index$sample$2_2][timeStep$var49]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$sample$2_2][timeStep$var49]) && (st[index$sample$2_2][timeStep$var49] < cv$temp$1$var55.length))?Math.log(cv$temp$1$var55[st[index$sample$2_2][timeStep$var49]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$sample$2_2][timeStep$var49]) && (st[index$sample$2_2][timeStep$var49] < cv$temp$1$var55.length))?Math.log(cv$temp$1$var55[st[index$sample$2_2][timeStep$var49]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$sample$2_2][timeStep$var49]) && (st[index$sample$2_2][timeStep$var49] < cv$temp$1$var55.length))?Math.log(cv$temp$1$var55[st[index$sample$2_2][timeStep$var49]]):Double.NEGATIVE_INFINITY)));
																	}
																	
																	// Recorded the probability of reaching sample task 63 with the current configuration.
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
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
								}
							}
						}
					}
				}
				
				// Processing random variable 145.
				{
					// Looking for a path between Sample 50 and consumer Bernoulli 145.
					{
						int traceTempVariable$currentState$8_1 = cv$currentValue;
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							if((sample$var32 == sample$var120)) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if((0 == timeStep$var140)) {
										for(int server = 0; server < noServers; server += 1) {
											// Set an accumulator to sum the probabilities for each possible configuration of
											// inputs.
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											
											// Set an accumulator to record the consumer distributions not seen. Initially set
											// to 1 as seen values will be deducted from this value.
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												// Enumerating the possible arguments for the variable Bernoulli 145 which is consuming
												// the output of Sample task 50.
												for(int var114 = 0; var114 < noStates; var114 += 1) {
													for(int var110 = 0; var110 < noServers; var110 += 1) {
														if((var110 == server)) {
															if((var114 == traceTempVariable$currentState$8_1)) {
																{
																	{
																		double cv$temp$2$var144;
																		{
																			// Constructing a random variable input for use later.
																			double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$8_1];
																			cv$temp$2$var144 = var144;
																		}
																		
																		// Record the probability of sample task 161 generating output with current configuration.
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$2$var144)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$2$var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$2$var144));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$2$var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$2$var144)));
																		}
																		
																		// Recorded the probability of reaching sample task 161 with the current configuration.
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
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
							}
						}
					}
				}
				
				// Processing random variable 155.
				{
					// Looking for a path between Sample 50 and consumer Gaussian 155.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][][] guard$sample50gaussian172 = guard$sample50gaussian172$global;
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							if((sample$var32 == sample$var120)) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if((0 == timeStep$var140)) {
										for(int server = 0; server < noServers; server += 1)
											// Set the flags to false
											guard$sample50gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							if((sample$var32 == sample$var120)) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if((0 == timeStep$var140)) {
										for(int server = 0; server < noServers; server += 1)
											// Set the flags to false
											guard$sample50gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
									}
								}
							}
						}
						int traceTempVariable$currentState$14_1 = cv$currentValue;
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							if((sample$var32 == sample$var120)) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if((0 == timeStep$var140)) {
										for(int server = 0; server < noServers; server += 1) {
											if(!guard$sample50gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample50gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
												
												// Processing sample task 173 of consumer random variable null.
												{
													// Set an accumulator to sum the probabilities for each possible configuration of
													// inputs.
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													
													// Set an accumulator to record the consumer distributions not seen. Initially set
													// to 1 as seen values will be deducted from this value.
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
														// the output of Sample task 50.
														for(int var86 = 0; var86 < noStates; var86 += 1) {
															for(int var82 = 0; var82 < noServers; var82 += 1) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var82 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var86 == traceTempVariable$currentState$14_1)) {
																				int traceTempVariable$currentState$19_1 = cv$currentValue;
																				if((index$sample$1 == sample$var120)) {
																					if((0 == timeStep$var140)) {
																						for(int var100 = 0; var100 < noStates; var100 += 1) {
																							for(int var96 = 0; var96 < noServers; var96 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var96 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var100 == traceTempVariable$currentState$19_1)) {
																												{
																													{
																														double cv$temp$3$var152;
																														{
																															// Constructing a random variable input for use later.
																															double var152 = current_metric_mean[server][traceTempVariable$currentState$19_1];
																															cv$temp$3$var152 = var152;
																														}
																														double cv$temp$4$var154;
																														{
																															// Constructing a random variable input for use later.
																															double var154 = current_metric_var[server][traceTempVariable$currentState$19_1];
																															cv$temp$4$var154 = var154;
																														}
																														
																														// Record the probability of sample task 173 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$3$var152) / Math.sqrt(cv$temp$4$var154))) - (0.5 * Math.log(cv$temp$4$var154)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$3$var152) / Math.sqrt(cv$temp$4$var154))) - (0.5 * Math.log(cv$temp$4$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$3$var152) / Math.sqrt(cv$temp$4$var154))) - (0.5 * Math.log(cv$temp$4$var154))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$3$var152) / Math.sqrt(cv$temp$4$var154))) - (0.5 * Math.log(cv$temp$4$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$3$var152) / Math.sqrt(cv$temp$4$var154))) - (0.5 * Math.log(cv$temp$4$var154)))));
																														}
																														
																														// Recorded the probability of reaching sample task 173 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				for(int index$sample$20 = 0; index$sample$20 < noSamples; index$sample$20 += 1) {
																					if(!(index$sample$20 == index$sample$1)) {
																						// Enumerating the possible outputs of Categorical 43.
																						for(int index$sample50$21 = 0; index$sample50$21 < noStates; index$sample50$21 += 1) {
																							int distributionTempVariable$var44$23 = index$sample50$21;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample50Value22 = (1.0 * distribution$sample50[((index$sample$20 - 0) / 1)][index$sample50$21]);
																							int traceTempVariable$currentState$24_1 = distributionTempVariable$var44$23;
																							if((index$sample$20 == sample$var120)) {
																								if((0 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$24_1)) {
																															{
																																{
																																	double cv$temp$5$var152;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$24_1];
																																		cv$temp$5$var152 = var152;
																																	}
																																	double cv$temp$6$var154;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$24_1];
																																		cv$temp$6$var154 = var154;
																																	}
																																	
																																	// Record the probability of sample task 173 generating output with current configuration.
																																	if(((Math.log(cv$probabilitySample50Value22) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$5$var152) / Math.sqrt(cv$temp$6$var154))) - (0.5 * Math.log(cv$temp$6$var154)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value22) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$5$var152) / Math.sqrt(cv$temp$6$var154))) - (0.5 * Math.log(cv$temp$6$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		// If the second value is -infinity.
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value22) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$5$var152) / Math.sqrt(cv$temp$6$var154))) - (0.5 * Math.log(cv$temp$6$var154))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value22) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$5$var152) / Math.sqrt(cv$temp$6$var154))) - (0.5 * Math.log(cv$temp$6$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value22) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$5$var152) / Math.sqrt(cv$temp$6$var154))) - (0.5 * Math.log(cv$temp$6$var154)))));
																																	}
																																	
																																	// Recorded the probability of reaching sample task 173 with the current configuration.
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value22);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														
														// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
														// the output of Sample task 50.
														for(int var86 = 0; var86 < noStates; var86 += 1) {
															for(int var82 = 0; var82 < noServers; var82 += 1) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var82 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var86 == traceTempVariable$currentState$14_1)) {
																				if(fixedFlag$sample63) {
																					for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																						for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$28_1][0]; timeStep$var49 += 1) {
																							if((index$sample$28_1 == sample$var120)) {
																								if((timeStep$var49 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$14_1)) {
																															{
																																{
																																	double cv$temp$7$var152;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$14_1];
																																		cv$temp$7$var152 = var152;
																																	}
																																	double cv$temp$8$var154;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$14_1];
																																		cv$temp$8$var154 = var154;
																																	}
																																	
																																	// Record the probability of sample task 173 generating output with current configuration.
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$7$var152) / Math.sqrt(cv$temp$8$var154))) - (0.5 * Math.log(cv$temp$8$var154)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$7$var152) / Math.sqrt(cv$temp$8$var154))) - (0.5 * Math.log(cv$temp$8$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		// If the second value is -infinity.
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$7$var152) / Math.sqrt(cv$temp$8$var154))) - (0.5 * Math.log(cv$temp$8$var154))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$7$var152) / Math.sqrt(cv$temp$8$var154))) - (0.5 * Math.log(cv$temp$8$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$7$var152) / Math.sqrt(cv$temp$8$var154))) - (0.5 * Math.log(cv$temp$8$var154)))));
																																	}
																																	
																																	// Recorded the probability of reaching sample task 173 with the current configuration.
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																						for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$29][0]; timeStep$var49 += 1) {
																							if(true) {
																								// Enumerating the possible outputs of Categorical 56.
																								for(int index$sample63$31 = 0; index$sample63$31 < noStates; index$sample63$31 += 1) {
																									int distributionTempVariable$var57$33 = index$sample63$31;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample63Value32 = (1.0 * distribution$sample63[((index$sample$29 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$31]);
																									int traceTempVariable$currentState$34_1 = distributionTempVariable$var57$33;
																									if((index$sample$29 == sample$var120)) {
																										if((timeStep$var49 == timeStep$var140)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$34_1)) {
																																	{
																																		{
																																			double cv$temp$9$var152;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$34_1];
																																				cv$temp$9$var152 = var152;
																																			}
																																			double cv$temp$10$var154;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$34_1];
																																				cv$temp$10$var154 = var154;
																																			}
																																			
																																			// Record the probability of sample task 173 generating output with current configuration.
																																			if(((Math.log(cv$probabilitySample63Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$9$var152) / Math.sqrt(cv$temp$10$var154))) - (0.5 * Math.log(cv$temp$10$var154)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$9$var152) / Math.sqrt(cv$temp$10$var154))) - (0.5 * Math.log(cv$temp$10$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$9$var152) / Math.sqrt(cv$temp$10$var154))) - (0.5 * Math.log(cv$temp$10$var154))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$9$var152) / Math.sqrt(cv$temp$10$var154))) - (0.5 * Math.log(cv$temp$10$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$9$var152) / Math.sqrt(cv$temp$10$var154))) - (0.5 * Math.log(cv$temp$10$var154)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 173 with the current configuration.
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value32);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
									}
								}
							}
						}
						int traceTempVariable$currentState$15_1 = cv$currentValue;
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							if((sample$var32 == sample$var120)) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if((0 == timeStep$var140)) {
										for(int server = 0; server < noServers; server += 1) {
											if(!guard$sample50gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample50gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
												
												// Processing sample task 173 of consumer random variable null.
												{
													// Set an accumulator to sum the probabilities for each possible configuration of
													// inputs.
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													
													// Set an accumulator to record the consumer distributions not seen. Initially set
													// to 1 as seen values will be deducted from this value.
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
														// the output of Sample task 50.
														int traceTempVariable$currentState$37_1 = cv$currentValue;
														if((index$sample$1 == sample$var120)) {
															if((0 == timeStep$var140)) {
																for(int var86 = 0; var86 < noStates; var86 += 1) {
																	for(int var82 = 0; var82 < noServers; var82 += 1) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var82 == server)) {
																				if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																					if((var86 == traceTempVariable$currentState$37_1)) {
																						for(int var100 = 0; var100 < noStates; var100 += 1) {
																							for(int var96 = 0; var96 < noServers; var96 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var96 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var100 == traceTempVariable$currentState$37_1)) {
																												{
																													{
																														double cv$temp$11$var152;
																														{
																															// Constructing a random variable input for use later.
																															double var152 = current_metric_mean[server][traceTempVariable$currentState$37_1];
																															cv$temp$11$var152 = var152;
																														}
																														double cv$temp$12$var154;
																														{
																															// Constructing a random variable input for use later.
																															double var154 = current_metric_var[server][traceTempVariable$currentState$37_1];
																															cv$temp$12$var154 = var154;
																														}
																														
																														// Record the probability of sample task 173 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$11$var152) / Math.sqrt(cv$temp$12$var154))) - (0.5 * Math.log(cv$temp$12$var154)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$11$var152) / Math.sqrt(cv$temp$12$var154))) - (0.5 * Math.log(cv$temp$12$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$11$var152) / Math.sqrt(cv$temp$12$var154))) - (0.5 * Math.log(cv$temp$12$var154))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$11$var152) / Math.sqrt(cv$temp$12$var154))) - (0.5 * Math.log(cv$temp$12$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$11$var152) / Math.sqrt(cv$temp$12$var154))) - (0.5 * Math.log(cv$temp$12$var154)))));
																														}
																														
																														// Recorded the probability of reaching sample task 173 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$sample$38 = 0; index$sample$38 < noSamples; index$sample$38 += 1) {
															if(!(index$sample$38 == index$sample$1)) {
																// Enumerating the possible outputs of Categorical 43.
																for(int index$sample50$39 = 0; index$sample50$39 < noStates; index$sample50$39 += 1) {
																	int distributionTempVariable$var44$41 = index$sample50$39;
																	
																	// Update the probability of sampling this value from the distribution value.
																	double cv$probabilitySample50Value40 = (1.0 * distribution$sample50[((index$sample$38 - 0) / 1)][index$sample50$39]);
																	int traceTempVariable$currentState$42_1 = distributionTempVariable$var44$41;
																	if((index$sample$38 == sample$var120)) {
																		if((0 == timeStep$var140)) {
																			for(int var86 = 0; var86 < noStates; var86 += 1) {
																				for(int var82 = 0; var82 < noServers; var82 += 1) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var82 == server)) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var86 == traceTempVariable$currentState$42_1)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$42_1)) {
																															{
																																{
																																	double cv$temp$13$var152;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$42_1];
																																		cv$temp$13$var152 = var152;
																																	}
																																	double cv$temp$14$var154;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$42_1];
																																		cv$temp$14$var154 = var154;
																																	}
																																	
																																	// Record the probability of sample task 173 generating output with current configuration.
																																	if(((Math.log(cv$probabilitySample50Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$13$var152) / Math.sqrt(cv$temp$14$var154))) - (0.5 * Math.log(cv$temp$14$var154)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$13$var152) / Math.sqrt(cv$temp$14$var154))) - (0.5 * Math.log(cv$temp$14$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		// If the second value is -infinity.
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$13$var152) / Math.sqrt(cv$temp$14$var154))) - (0.5 * Math.log(cv$temp$14$var154))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$13$var152) / Math.sqrt(cv$temp$14$var154))) - (0.5 * Math.log(cv$temp$14$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$13$var152) / Math.sqrt(cv$temp$14$var154))) - (0.5 * Math.log(cv$temp$14$var154)))));
																																	}
																																	
																																	// Recorded the probability of reaching sample task 173 with the current configuration.
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value40);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														
														// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
														// the output of Sample task 50.
														if(fixedFlag$sample63) {
															for(int index$sample$47_1 = 0; index$sample$47_1 < noSamples; index$sample$47_1 += 1) {
																for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$47_1][0]; timeStep$var49 += 1) {
																	if((index$sample$47_1 == sample$var120)) {
																		if((timeStep$var49 == timeStep$var140)) {
																			for(int var86 = 0; var86 < noStates; var86 += 1) {
																				for(int var82 = 0; var82 < noServers; var82 += 1) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var82 == server)) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var86 == traceTempVariable$currentState$15_1)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$15_1)) {
																															{
																																{
																																	double cv$temp$15$var152;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$15_1];
																																		cv$temp$15$var152 = var152;
																																	}
																																	double cv$temp$16$var154;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$15_1];
																																		cv$temp$16$var154 = var154;
																																	}
																																	
																																	// Record the probability of sample task 173 generating output with current configuration.
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$15$var152) / Math.sqrt(cv$temp$16$var154))) - (0.5 * Math.log(cv$temp$16$var154)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$15$var152) / Math.sqrt(cv$temp$16$var154))) - (0.5 * Math.log(cv$temp$16$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		// If the second value is -infinity.
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$15$var152) / Math.sqrt(cv$temp$16$var154))) - (0.5 * Math.log(cv$temp$16$var154))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$15$var152) / Math.sqrt(cv$temp$16$var154))) - (0.5 * Math.log(cv$temp$16$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$15$var152) / Math.sqrt(cv$temp$16$var154))) - (0.5 * Math.log(cv$temp$16$var154)))));
																																	}
																																	
																																	// Recorded the probability of reaching sample task 173 with the current configuration.
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int index$sample$48 = 0; index$sample$48 < noSamples; index$sample$48 += 1) {
																for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$48][0]; timeStep$var49 += 1) {
																	if(true) {
																		// Enumerating the possible outputs of Categorical 56.
																		for(int index$sample63$50 = 0; index$sample63$50 < noStates; index$sample63$50 += 1) {
																			int distributionTempVariable$var57$52 = index$sample63$50;
																			
																			// Update the probability of sampling this value from the distribution value.
																			double cv$probabilitySample63Value51 = (1.0 * distribution$sample63[((index$sample$48 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$50]);
																			int traceTempVariable$currentState$53_1 = distributionTempVariable$var57$52;
																			if((index$sample$48 == sample$var120)) {
																				if((timeStep$var49 == timeStep$var140)) {
																					for(int var86 = 0; var86 < noStates; var86 += 1) {
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$53_1)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$53_1)) {
																																	{
																																		{
																																			double cv$temp$17$var152;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$53_1];
																																				cv$temp$17$var152 = var152;
																																			}
																																			double cv$temp$18$var154;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$53_1];
																																				cv$temp$18$var154 = var154;
																																			}
																																			
																																			// Record the probability of sample task 173 generating output with current configuration.
																																			if(((Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$17$var152) / Math.sqrt(cv$temp$18$var154))) - (0.5 * Math.log(cv$temp$18$var154)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$17$var152) / Math.sqrt(cv$temp$18$var154))) - (0.5 * Math.log(cv$temp$18$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$17$var152) / Math.sqrt(cv$temp$18$var154))) - (0.5 * Math.log(cv$temp$18$var154))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$17$var152) / Math.sqrt(cv$temp$18$var154))) - (0.5 * Math.log(cv$temp$18$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$17$var152) / Math.sqrt(cv$temp$18$var154))) - (0.5 * Math.log(cv$temp$18$var154)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 173 with the current configuration.
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value51);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
									}
								}
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
			
			// Processing random variable 56.
			{
				// Looking for a path between Sample 50 and consumer Categorical 56.
				{
					int traceTempVariable$var54$66_1 = cv$currentValue;
					for(int index$sample$66_2 = 0; index$sample$66_2 < noSamples; index$sample$66_2 += 1) {
						if((sample$var32 == index$sample$66_2)) {
							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$66_2][0]; timeStep$var49 += 1) {
								if((0 == (timeStep$var49 - 1))) {
									if(!fixedFlag$sample63) {
										// Processing sample task 63 of consumer random variable null.
										{
											// Copy of index so that its values can be safely substituted
											int index$timeStep$68 = timeStep$var49;
											
											// Copy of index so that its values can be safely substituted
											int index$sample$69 = index$sample$66_2;
											
											// A local array to hold the accumulated distributions of the sample tasks for each
											// configuration of distributions.
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var56;
											
											// Zero all the elements in the distribution accumulator
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											
											// Zero an accumulator to track the probabilities reached.
											double cv$reachedDistributionProbability = 0.0;
											
											// Enumerating the possible arguments for the variable Categorical 56 which is consuming
											// the output of Sample task 50.
											for(int var26 = 0; var26 < noStates; var26 += 1) {
												if((var26 == traceTempVariable$var54$66_1)) {
													{
														// Declare and zero an accumulator for tracking the reached source probability space.
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															// Add the probability of this argument configuration.
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double[] cv$temp$19$var55;
														{
															// Constructing a random variable input for use later.
															double[] var55 = m[traceTempVariable$var54$66_1];
															cv$temp$19$var55 = var55;
														}
														
														// The probability of reaching the consumer with this set of consumer arguments
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														
														// Record the reached distribution.
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														
														// Add the current distribution to the distribution accumulator.
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$19$var55);
													}
												}
											}
											
											// A local copy of the samples' distribution.
											double[] cv$sampleDistribution = distribution$sample63[((index$sample$66_2 - 0) / 1)][((timeStep$var49 - 1) / 1)];
											
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
											cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(((cv$overlap * cv$reachedDistributionProbability) + (1.0 - Math.min(cv$reachedDistributionProbability, 1.0)))));
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample50[((sample$var32 - 0) / 1)];
		
		// The sum of all the probabilities in log space
		double cv$logSum = 0.0;
		
		// Sum all the values
		{
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
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
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
	// by sample task 63 drawn from Categorical 56. Inference was performed using variable
	// marginalization.
	private final void sample63(int sample$var32, int timeStep$var49) {
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var57$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 56 creating
			// sample task 63.
			// Copy of index so that its values can be safely substituted
			int index$timeStep$1 = timeStep$var49;
			
			// Copy of index so that its values can be safely substituted
			int index$sample$2 = sample$var32;
			
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// The value currently being tested
			int cv$currentValue;
			
			// Value of the variable at this index
			cv$currentValue = cv$valuePos;
			
			// Enumerating the possible arguments for Categorical 56.
			if(fixedFlag$sample50) {
				for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
					if((index$sample$3_1 == sample$var32)) {
						if((0 == (timeStep$var49 - 1))) {
							for(int var26 = 0; var26 < noStates; var26 += 1) {
								if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
									// Record the reached probability density.
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] cv$temp$0$var55;
									{
										// Constructing a random variable input for use later.
										double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
										cv$temp$0$var55 = var55;
									}
									
									// An accumulator to allow the value for each distribution to be constructed before
									// it is added to the index probabilities.
									double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var55.length))?Math.log(cv$temp$0$var55[cv$currentValue]):Double.NEGATIVE_INFINITY));
									
									// Processing random variable 56.
									{
										// Looking for a path between Sample 63 and consumer Categorical 56.
										{
											int traceTempVariable$var54$20_1 = cv$currentValue;
										}
									}
									
									// Processing random variable 145.
									{
										// Looking for a path between Sample 63 and consumer Bernoulli 145.
										{
											int traceTempVariable$currentState$24_1 = cv$currentValue;
											for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
												if((sample$var32 == sample$var120)) {
													for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int server = 0; server < noServers; server += 1) {
																// Set an accumulator to sum the probabilities for each possible configuration of
																// inputs.
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																
																// Set an accumulator to record the consumer distributions not seen. Initially set
																// to 1 as seen values will be deducted from this value.
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	// Enumerating the possible arguments for the variable Bernoulli 145 which is consuming
																	// the output of Sample task 63.
																	for(int var114 = 0; var114 < noStates; var114 += 1) {
																		for(int var110 = 0; var110 < noServers; var110 += 1) {
																			if((var110 == server)) {
																				if((var114 == traceTempVariable$currentState$24_1)) {
																					{
																						{
																							double cv$temp$4$var144;
																							{
																								// Constructing a random variable input for use later.
																								double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$24_1];
																								cv$temp$4$var144 = var144;
																							}
																							
																							// Record the probability of sample task 161 generating output with current configuration.
																							if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$4$var144)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$4$var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$4$var144));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$4$var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$4$var144)));
																							}
																							
																							// Recorded the probability of reaching sample task 161 with the current configuration.
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
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
												}
											}
										}
									}
									
									// Processing random variable 155.
									{
										// Looking for a path between Sample 63 and consumer Gaussian 155.
										{
											// Guard to check that at most one copy of the code is executed for a given random
											// variable instance.
											boolean[][][] guard$sample63gaussian172 = guard$sample63gaussian172$global;
											for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
												if((sample$var32 == sample$var120)) {
													for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int server = 0; server < noServers; server += 1)
																// Set the flags to false
																guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
														}
													}
												}
											}
											for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
												if((sample$var32 == sample$var120)) {
													for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int server = 0; server < noServers; server += 1)
																// Set the flags to false
																guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
														}
													}
												}
											}
											int traceTempVariable$currentState$48_1 = cv$currentValue;
											for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
												if((sample$var32 == sample$var120)) {
													for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int server = 0; server < noServers; server += 1) {
																if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																	// The body will execute, so should not be executed again
																	guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																	
																	// Processing sample task 173 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																			// the output of Sample task 63.
																			for(int var86 = 0; var86 < noStates; var86 += 1) {
																				for(int var82 = 0; var82 < noServers; var82 += 1) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var82 == server)) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var86 == traceTempVariable$currentState$48_1)) {
																									for(int index$sample$65_1 = 0; index$sample$65_1 < noSamples; index$sample$65_1 += 1) {
																										if((index$sample$65_1 == sample$var120)) {
																											if((0 == timeStep$var140)) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var96 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var100 == traceTempVariable$currentState$48_1)) {
																																		{
																																			{
																																				double cv$temp$8$var152;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$48_1];
																																					cv$temp$8$var152 = var152;
																																				}
																																				double cv$temp$9$var154;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$48_1];
																																					cv$temp$9$var154 = var154;
																																				}
																																				
																																				// Record the probability of sample task 173 generating output with current configuration.
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))));
																																				}
																																				
																																				// Recorded the probability of reaching sample task 173 with the current configuration.
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			
																			// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																			// the output of Sample task 63.
																			for(int var86 = 0; var86 < noStates; var86 += 1) {
																				for(int var82 = 0; var82 < noServers; var82 += 1) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var82 == server)) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var86 == traceTempVariable$currentState$48_1)) {
																									int traceTempVariable$currentState$68_1 = cv$currentValue;
																									if((index$sample$2 == sample$var120)) {
																										if((index$timeStep$1 == timeStep$var140)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$68_1)) {
																																	{
																																		{
																																			double cv$temp$10$var152;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$68_1];
																																				cv$temp$10$var152 = var152;
																																			}
																																			double cv$temp$11$var154;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$68_1];
																																				cv$temp$11$var154 = var154;
																																			}
																																			
																																			// Record the probability of sample task 173 generating output with current configuration.
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 173 with the current configuration.
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									for(int index$sample$69 = 0; index$sample$69 < noSamples; index$sample$69 += 1) {
																										for(int index$timeStep$70 = 1; index$timeStep$70 < length$metric[index$sample$69][0]; index$timeStep$70 += 1) {
																											if(!((index$sample$69 == index$sample$2) && (index$timeStep$70 == index$timeStep$1))) {
																												// Enumerating the possible outputs of Categorical 56.
																												for(int index$sample63$71 = 0; index$sample63$71 < noStates; index$sample63$71 += 1) {
																													int distributionTempVariable$var57$73 = index$sample63$71;
																													
																													// Update the probability of sampling this value from the distribution value.
																													double cv$probabilitySample63Value72 = (1.0 * distribution$sample63[((index$sample$69 - 0) / 1)][((index$timeStep$70 - 1) / 1)][index$sample63$71]);
																													int traceTempVariable$currentState$74_1 = distributionTempVariable$var57$73;
																													if((index$sample$69 == sample$var120)) {
																														if((index$timeStep$70 == timeStep$var140)) {
																															for(int var100 = 0; var100 < noStates; var100 += 1) {
																																for(int var96 = 0; var96 < noServers; var96 += 1) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var96 == server)) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var100 == traceTempVariable$currentState$74_1)) {
																																					{
																																						{
																																							double cv$temp$12$var152;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$74_1];
																																								cv$temp$12$var152 = var152;
																																							}
																																							double cv$temp$13$var154;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$74_1];
																																								cv$temp$13$var154 = var154;
																																							}
																																							
																																							// Record the probability of sample task 173 generating output with current configuration.
																																							if(((Math.log(cv$probabilitySample63Value72) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value72) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								// If the second value is -infinity.
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value72) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value72) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value72) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))));
																																							}
																																							
																																							// Recorded the probability of reaching sample task 173 with the current configuration.
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value72);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
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
														}
													}
												}
											}
											int traceTempVariable$currentState$52_1 = cv$currentValue;
											for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
												if((sample$var32 == sample$var120)) {
													for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int server = 0; server < noServers; server += 1) {
																if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																	// The body will execute, so should not be executed again
																	guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																	
																	// Processing sample task 173 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																			// the output of Sample task 63.
																			for(int index$sample$136_1 = 0; index$sample$136_1 < noSamples; index$sample$136_1 += 1) {
																				if((index$sample$136_1 == sample$var120)) {
																					if((0 == timeStep$var140)) {
																						for(int var86 = 0; var86 < noStates; var86 += 1) {
																							for(int var82 = 0; var82 < noServers; var82 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var82 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var86 == traceTempVariable$currentState$52_1)) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var96 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var100 == traceTempVariable$currentState$52_1)) {
																																		{
																																			{
																																				double cv$temp$40$var152;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$52_1];
																																					cv$temp$40$var152 = var152;
																																				}
																																				double cv$temp$41$var154;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$52_1];
																																					cv$temp$41$var154 = var154;
																																				}
																																				
																																				// Record the probability of sample task 173 generating output with current configuration.
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$40$var152) / Math.sqrt(cv$temp$41$var154))) - (0.5 * Math.log(cv$temp$41$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$40$var152) / Math.sqrt(cv$temp$41$var154))) - (0.5 * Math.log(cv$temp$41$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$40$var152) / Math.sqrt(cv$temp$41$var154))) - (0.5 * Math.log(cv$temp$41$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$40$var152) / Math.sqrt(cv$temp$41$var154))) - (0.5 * Math.log(cv$temp$41$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$40$var152) / Math.sqrt(cv$temp$41$var154))) - (0.5 * Math.log(cv$temp$41$var154)))));
																																				}
																																				
																																				// Recorded the probability of reaching sample task 173 with the current configuration.
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			
																			// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																			// the output of Sample task 63.
																			int traceTempVariable$currentState$139_1 = cv$currentValue;
																			if((index$sample$2 == sample$var120)) {
																				if((index$timeStep$1 == timeStep$var140)) {
																					for(int var86 = 0; var86 < noStates; var86 += 1) {
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$139_1)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$139_1)) {
																																	{
																																		{
																																			double cv$temp$42$var152;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$139_1];
																																				cv$temp$42$var152 = var152;
																																			}
																																			double cv$temp$43$var154;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$139_1];
																																				cv$temp$43$var154 = var154;
																																			}
																																			
																																			// Record the probability of sample task 173 generating output with current configuration.
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$42$var152) / Math.sqrt(cv$temp$43$var154))) - (0.5 * Math.log(cv$temp$43$var154)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$42$var152) / Math.sqrt(cv$temp$43$var154))) - (0.5 * Math.log(cv$temp$43$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$42$var152) / Math.sqrt(cv$temp$43$var154))) - (0.5 * Math.log(cv$temp$43$var154))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$42$var152) / Math.sqrt(cv$temp$43$var154))) - (0.5 * Math.log(cv$temp$43$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$42$var152) / Math.sqrt(cv$temp$43$var154))) - (0.5 * Math.log(cv$temp$43$var154)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 173 with the current configuration.
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			for(int index$sample$140 = 0; index$sample$140 < noSamples; index$sample$140 += 1) {
																				for(int index$timeStep$141 = 1; index$timeStep$141 < length$metric[index$sample$140][0]; index$timeStep$141 += 1) {
																					if(!((index$sample$140 == index$sample$2) && (index$timeStep$141 == index$timeStep$1))) {
																						// Enumerating the possible outputs of Categorical 56.
																						for(int index$sample63$142 = 0; index$sample63$142 < noStates; index$sample63$142 += 1) {
																							int distributionTempVariable$var57$144 = index$sample63$142;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample63Value143 = (1.0 * distribution$sample63[((index$sample$140 - 0) / 1)][((index$timeStep$141 - 1) / 1)][index$sample63$142]);
																							int traceTempVariable$currentState$145_1 = distributionTempVariable$var57$144;
																							if((index$sample$140 == sample$var120)) {
																								if((index$timeStep$141 == timeStep$var140)) {
																									for(int var86 = 0; var86 < noStates; var86 += 1) {
																										for(int var82 = 0; var82 < noServers; var82 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var82 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var86 == traceTempVariable$currentState$145_1)) {
																															for(int var100 = 0; var100 < noStates; var100 += 1) {
																																for(int var96 = 0; var96 < noServers; var96 += 1) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var96 == server)) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var100 == traceTempVariable$currentState$145_1)) {
																																					{
																																						{
																																							double cv$temp$44$var152;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$145_1];
																																								cv$temp$44$var152 = var152;
																																							}
																																							double cv$temp$45$var154;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$145_1];
																																								cv$temp$45$var154 = var154;
																																							}
																																							
																																							// Record the probability of sample task 173 generating output with current configuration.
																																							if(((Math.log(cv$probabilitySample63Value143) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$44$var152) / Math.sqrt(cv$temp$45$var154))) - (0.5 * Math.log(cv$temp$45$var154)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value143) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$44$var152) / Math.sqrt(cv$temp$45$var154))) - (0.5 * Math.log(cv$temp$45$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								// If the second value is -infinity.
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value143) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$44$var152) / Math.sqrt(cv$temp$45$var154))) - (0.5 * Math.log(cv$temp$45$var154))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value143) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$44$var152) / Math.sqrt(cv$temp$45$var154))) - (0.5 * Math.log(cv$temp$45$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value143) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$44$var152) / Math.sqrt(cv$temp$45$var154))) - (0.5 * Math.log(cv$temp$45$var154)))));
																																							}
																																							
																																							// Recorded the probability of reaching sample task 173 with the current configuration.
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value143);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
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
														}
													}
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
				}
			} else {
				for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
					if(true) {
						// Enumerating the possible outputs of Categorical 43.
						for(int index$sample50$5 = 0; index$sample50$5 < noStates; index$sample50$5 += 1) {
							int distributionTempVariable$var44$7 = index$sample50$5;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample50Value6 = (1.0 * distribution$sample50[((index$sample$4 - 0) / 1)][index$sample50$5]);
							int traceTempVariable$var54$8_1 = distributionTempVariable$var44$7;
							if((index$sample$4 == sample$var32)) {
								if((0 == (timeStep$var49 - 1))) {
									for(int var26 = 0; var26 < noStates; var26 += 1) {
										if((var26 == traceTempVariable$var54$8_1)) {
											// Record the reached probability density.
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample50Value6);
											double[] cv$temp$1$var55;
											{
												// Constructing a random variable input for use later.
												double[] var55 = m[traceTempVariable$var54$8_1];
												cv$temp$1$var55 = var55;
											}
											
											// An accumulator to allow the value for each distribution to be constructed before
											// it is added to the index probabilities.
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample50Value6) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var55.length))?Math.log(cv$temp$1$var55[cv$currentValue]):Double.NEGATIVE_INFINITY));
											
											// Processing random variable 56.
											{
												// Looking for a path between Sample 63 and consumer Categorical 56.
												{
													int traceTempVariable$var54$21_1 = cv$currentValue;
												}
											}
											
											// Processing random variable 145.
											{
												// Looking for a path between Sample 63 and consumer Bernoulli 145.
												{
													int traceTempVariable$currentState$25_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Bernoulli 145 which is consuming
																			// the output of Sample task 63.
																			for(int var114 = 0; var114 < noStates; var114 += 1) {
																				for(int var110 = 0; var110 < noServers; var110 += 1) {
																					if((var110 == server)) {
																						if((var114 == traceTempVariable$currentState$25_1)) {
																							{
																								{
																									double cv$temp$5$var144;
																									{
																										// Constructing a random variable input for use later.
																										double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$25_1];
																										cv$temp$5$var144 = var144;
																									}
																									
																									// Record the probability of sample task 161 generating output with current configuration.
																									if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$5$var144)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$5$var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$5$var144));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$5$var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$5$var144)));
																									}
																									
																									// Recorded the probability of reaching sample task 161 with the current configuration.
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																								}
																							}
																						}
																					}
																				}
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
														}
													}
												}
											}
											
											// Processing random variable 155.
											{
												// Looking for a path between Sample 63 and consumer Gaussian 155.
												{
													// Guard to check that at most one copy of the code is executed for a given random
													// variable instance.
													boolean[][][] guard$sample63gaussian172 = guard$sample63gaussian172$global;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1)
																		// Set the flags to false
																		guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
																}
															}
														}
													}
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1)
																		// Set the flags to false
																		guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
																}
															}
														}
													}
													int traceTempVariable$currentState$49_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																			
																			// Processing sample task 173 of consumer random variable null.
																			{
																				// Set an accumulator to sum the probabilities for each possible configuration of
																				// inputs.
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				
																				// Set an accumulator to record the consumer distributions not seen. Initially set
																				// to 1 as seen values will be deducted from this value.
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																					// the output of Sample task 63.
																					for(int var86 = 0; var86 < noStates; var86 += 1) {
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$49_1)) {
																											int traceTempVariable$currentState$78_1 = distributionTempVariable$var44$7;
																											if((index$sample$4 == sample$var120)) {
																												if((0 == timeStep$var140)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$78_1)) {
																																			{
																																				{
																																					double cv$temp$14$var152;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$78_1];
																																						cv$temp$14$var152 = var152;
																																					}
																																					double cv$temp$15$var154;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$78_1];
																																						cv$temp$15$var154 = var154;
																																					}
																																					
																																					// Record the probability of sample task 173 generating output with current configuration.
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 173 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																											for(int index$sample$79 = 0; index$sample$79 < noSamples; index$sample$79 += 1) {
																												if(!(index$sample$79 == index$sample$4)) {
																													// Enumerating the possible outputs of Categorical 43.
																													for(int index$sample50$80 = 0; index$sample50$80 < noStates; index$sample50$80 += 1) {
																														int distributionTempVariable$var44$82 = index$sample50$80;
																														
																														// Update the probability of sampling this value from the distribution value.
																														double cv$probabilitySample50Value81 = (1.0 * distribution$sample50[((index$sample$79 - 0) / 1)][index$sample50$80]);
																														int traceTempVariable$currentState$83_1 = distributionTempVariable$var44$82;
																														if((index$sample$79 == sample$var120)) {
																															if((0 == timeStep$var140)) {
																																for(int var100 = 0; var100 < noStates; var100 += 1) {
																																	for(int var96 = 0; var96 < noServers; var96 += 1) {
																																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																			if((var96 == server)) {
																																				if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																					if((var100 == traceTempVariable$currentState$83_1)) {
																																						{
																																							{
																																								double cv$temp$16$var152;
																																								{
																																									// Constructing a random variable input for use later.
																																									double var152 = current_metric_mean[server][traceTempVariable$currentState$83_1];
																																									cv$temp$16$var152 = var152;
																																								}
																																								double cv$temp$17$var154;
																																								{
																																									// Constructing a random variable input for use later.
																																									double var154 = current_metric_var[server][traceTempVariable$currentState$83_1];
																																									cv$temp$17$var154 = var154;
																																								}
																																								
																																								// Record the probability of sample task 173 generating output with current configuration.
																																								if(((Math.log(cv$probabilitySample50Value81) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value81) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value81) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154))));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value81) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value81) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))));
																																								}
																																								
																																								// Recorded the probability of reaching sample task 173 with the current configuration.
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value81);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					
																					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																					// the output of Sample task 63.
																					for(int var86 = 0; var86 < noStates; var86 += 1) {
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$49_1)) {
																											int traceTempVariable$currentState$87_1 = cv$currentValue;
																											if((index$sample$2 == sample$var120)) {
																												if((index$timeStep$1 == timeStep$var140)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$87_1)) {
																																			{
																																				{
																																					double cv$temp$18$var152;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$87_1];
																																						cv$temp$18$var152 = var152;
																																					}
																																					double cv$temp$19$var154;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$87_1];
																																						cv$temp$19$var154 = var154;
																																					}
																																					
																																					// Record the probability of sample task 173 generating output with current configuration.
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 173 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																											for(int index$sample$88 = 0; index$sample$88 < noSamples; index$sample$88 += 1) {
																												for(int index$timeStep$89 = 1; index$timeStep$89 < length$metric[index$sample$88][0]; index$timeStep$89 += 1) {
																													if(!((index$sample$88 == index$sample$2) && (index$timeStep$89 == index$timeStep$1))) {
																														// Enumerating the possible outputs of Categorical 56.
																														for(int index$sample63$90 = 0; index$sample63$90 < noStates; index$sample63$90 += 1) {
																															int distributionTempVariable$var57$92 = index$sample63$90;
																															
																															// Update the probability of sampling this value from the distribution value.
																															double cv$probabilitySample63Value91 = (1.0 * distribution$sample63[((index$sample$88 - 0) / 1)][((index$timeStep$89 - 1) / 1)][index$sample63$90]);
																															int traceTempVariable$currentState$93_1 = distributionTempVariable$var57$92;
																															if((index$sample$88 == sample$var120)) {
																																if((index$timeStep$89 == timeStep$var140)) {
																																	for(int var100 = 0; var100 < noStates; var100 += 1) {
																																		for(int var96 = 0; var96 < noServers; var96 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$93_1)) {
																																							{
																																								{
																																									double cv$temp$20$var152;
																																									{
																																										// Constructing a random variable input for use later.
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$93_1];
																																										cv$temp$20$var152 = var152;
																																									}
																																									double cv$temp$21$var154;
																																									{
																																										// Constructing a random variable input for use later.
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$93_1];
																																										cv$temp$21$var154 = var154;
																																									}
																																									
																																									// Record the probability of sample task 173 generating output with current configuration.
																																									if(((Math.log(cv$probabilitySample63Value91) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value91) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										// If the second value is -infinity.
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value91) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value91) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value91) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))));
																																									}
																																									
																																									// Recorded the probability of reaching sample task 173 with the current configuration.
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value91);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
																}
															}
														}
													}
													int traceTempVariable$currentState$53_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																			
																			// Processing sample task 173 of consumer random variable null.
																			{
																				// Set an accumulator to sum the probabilities for each possible configuration of
																				// inputs.
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				
																				// Set an accumulator to record the consumer distributions not seen. Initially set
																				// to 1 as seen values will be deducted from this value.
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																					// the output of Sample task 63.
																					int traceTempVariable$currentState$150_1 = distributionTempVariable$var44$7;
																					if((index$sample$4 == sample$var120)) {
																						if((0 == timeStep$var140)) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$150_1)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$150_1)) {
																																			{
																																				{
																																					double cv$temp$46$var152;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$150_1];
																																						cv$temp$46$var152 = var152;
																																					}
																																					double cv$temp$47$var154;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$150_1];
																																						cv$temp$47$var154 = var154;
																																					}
																																					
																																					// Record the probability of sample task 173 generating output with current configuration.
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$46$var152) / Math.sqrt(cv$temp$47$var154))) - (0.5 * Math.log(cv$temp$47$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$46$var152) / Math.sqrt(cv$temp$47$var154))) - (0.5 * Math.log(cv$temp$47$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$46$var152) / Math.sqrt(cv$temp$47$var154))) - (0.5 * Math.log(cv$temp$47$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$46$var152) / Math.sqrt(cv$temp$47$var154))) - (0.5 * Math.log(cv$temp$47$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$46$var152) / Math.sqrt(cv$temp$47$var154))) - (0.5 * Math.log(cv$temp$47$var154)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 173 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$151 = 0; index$sample$151 < noSamples; index$sample$151 += 1) {
																						if(!(index$sample$151 == index$sample$4)) {
																							// Enumerating the possible outputs of Categorical 43.
																							for(int index$sample50$152 = 0; index$sample50$152 < noStates; index$sample50$152 += 1) {
																								int distributionTempVariable$var44$154 = index$sample50$152;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample50Value153 = (1.0 * distribution$sample50[((index$sample$151 - 0) / 1)][index$sample50$152]);
																								int traceTempVariable$currentState$155_1 = distributionTempVariable$var44$154;
																								if((index$sample$151 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var86 = 0; var86 < noStates; var86 += 1) {
																											for(int var82 = 0; var82 < noServers; var82 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var82 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var86 == traceTempVariable$currentState$155_1)) {
																																for(int var100 = 0; var100 < noStates; var100 += 1) {
																																	for(int var96 = 0; var96 < noServers; var96 += 1) {
																																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																			if((var96 == server)) {
																																				if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																					if((var100 == traceTempVariable$currentState$155_1)) {
																																						{
																																							{
																																								double cv$temp$48$var152;
																																								{
																																									// Constructing a random variable input for use later.
																																									double var152 = current_metric_mean[server][traceTempVariable$currentState$155_1];
																																									cv$temp$48$var152 = var152;
																																								}
																																								double cv$temp$49$var154;
																																								{
																																									// Constructing a random variable input for use later.
																																									double var154 = current_metric_var[server][traceTempVariable$currentState$155_1];
																																									cv$temp$49$var154 = var154;
																																								}
																																								
																																								// Record the probability of sample task 173 generating output with current configuration.
																																								if(((Math.log(cv$probabilitySample50Value153) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$temp$49$var154))) - (0.5 * Math.log(cv$temp$49$var154)))) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value153) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$temp$49$var154))) - (0.5 * Math.log(cv$temp$49$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value153) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$temp$49$var154))) - (0.5 * Math.log(cv$temp$49$var154))));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value153) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$temp$49$var154))) - (0.5 * Math.log(cv$temp$49$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value153) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$temp$49$var154))) - (0.5 * Math.log(cv$temp$49$var154)))));
																																								}
																																								
																																								// Recorded the probability of reaching sample task 173 with the current configuration.
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value153);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					
																					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																					// the output of Sample task 63.
																					int traceTempVariable$currentState$160_1 = cv$currentValue;
																					if((index$sample$2 == sample$var120)) {
																						if((index$timeStep$1 == timeStep$var140)) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$160_1)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$160_1)) {
																																			{
																																				{
																																					double cv$temp$50$var152;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$160_1];
																																						cv$temp$50$var152 = var152;
																																					}
																																					double cv$temp$51$var154;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$160_1];
																																						cv$temp$51$var154 = var154;
																																					}
																																					
																																					// Record the probability of sample task 173 generating output with current configuration.
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$50$var152) / Math.sqrt(cv$temp$51$var154))) - (0.5 * Math.log(cv$temp$51$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$50$var152) / Math.sqrt(cv$temp$51$var154))) - (0.5 * Math.log(cv$temp$51$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$50$var152) / Math.sqrt(cv$temp$51$var154))) - (0.5 * Math.log(cv$temp$51$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$50$var152) / Math.sqrt(cv$temp$51$var154))) - (0.5 * Math.log(cv$temp$51$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$50$var152) / Math.sqrt(cv$temp$51$var154))) - (0.5 * Math.log(cv$temp$51$var154)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 173 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$161 = 0; index$sample$161 < noSamples; index$sample$161 += 1) {
																						for(int index$timeStep$162 = 1; index$timeStep$162 < length$metric[index$sample$161][0]; index$timeStep$162 += 1) {
																							if(!((index$sample$161 == index$sample$2) && (index$timeStep$162 == index$timeStep$1))) {
																								// Enumerating the possible outputs of Categorical 56.
																								for(int index$sample63$163 = 0; index$sample63$163 < noStates; index$sample63$163 += 1) {
																									int distributionTempVariable$var57$165 = index$sample63$163;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample63Value164 = (1.0 * distribution$sample63[((index$sample$161 - 0) / 1)][((index$timeStep$162 - 1) / 1)][index$sample63$163]);
																									int traceTempVariable$currentState$166_1 = distributionTempVariable$var57$165;
																									if((index$sample$161 == sample$var120)) {
																										if((index$timeStep$162 == timeStep$var140)) {
																											for(int var86 = 0; var86 < noStates; var86 += 1) {
																												for(int var82 = 0; var82 < noServers; var82 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var82 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var86 == traceTempVariable$currentState$166_1)) {
																																	for(int var100 = 0; var100 < noStates; var100 += 1) {
																																		for(int var96 = 0; var96 < noServers; var96 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$166_1)) {
																																							{
																																								{
																																									double cv$temp$52$var152;
																																									{
																																										// Constructing a random variable input for use later.
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$166_1];
																																										cv$temp$52$var152 = var152;
																																									}
																																									double cv$temp$53$var154;
																																									{
																																										// Constructing a random variable input for use later.
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$166_1];
																																										cv$temp$53$var154 = var154;
																																									}
																																									
																																									// Record the probability of sample task 173 generating output with current configuration.
																																									if(((Math.log(cv$probabilitySample63Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$52$var152) / Math.sqrt(cv$temp$53$var154))) - (0.5 * Math.log(cv$temp$53$var154)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$52$var152) / Math.sqrt(cv$temp$53$var154))) - (0.5 * Math.log(cv$temp$53$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										// If the second value is -infinity.
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$52$var152) / Math.sqrt(cv$temp$53$var154))) - (0.5 * Math.log(cv$temp$53$var154))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$52$var152) / Math.sqrt(cv$temp$53$var154))) - (0.5 * Math.log(cv$temp$53$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$52$var152) / Math.sqrt(cv$temp$53$var154))) - (0.5 * Math.log(cv$temp$53$var154)))));
																																									}
																																									
																																									// Recorded the probability of reaching sample task 173 with the current configuration.
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value164);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
																}
															}
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
						}
					}
				}
			}
			
			// Enumerating the possible arguments for Categorical 56.
			int traceTempVariable$var54$11_1 = cv$currentValue;
			if((index$sample$2 == sample$var32)) {
				if((index$timeStep$1 == (timeStep$var49 - 1))) {
					for(int var26 = 0; var26 < noStates; var26 += 1) {
						if((var26 == traceTempVariable$var54$11_1)) {
							// Record the reached probability density.
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$2$var55;
							{
								// Constructing a random variable input for use later.
								double[] var55 = m[traceTempVariable$var54$11_1];
								cv$temp$2$var55 = var55;
							}
							
							// An accumulator to allow the value for each distribution to be constructed before
							// it is added to the index probabilities.
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var55.length))?Math.log(cv$temp$2$var55[cv$currentValue]):Double.NEGATIVE_INFINITY));
							
							// Processing random variable 56.
							{
								// Looking for a path between Sample 63 and consumer Categorical 56.
								{
									int traceTempVariable$var54$22_1 = cv$currentValue;
								}
							}
							
							// Processing random variable 145.
							{
								// Looking for a path between Sample 63 and consumer Bernoulli 145.
								{
									int traceTempVariable$currentState$26_1 = cv$currentValue;
									for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
										if((sample$var32 == sample$var120)) {
											for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int server = 0; server < noServers; server += 1) {
														// Set an accumulator to sum the probabilities for each possible configuration of
														// inputs.
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														
														// Set an accumulator to record the consumer distributions not seen. Initially set
														// to 1 as seen values will be deducted from this value.
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															// Enumerating the possible arguments for the variable Bernoulli 145 which is consuming
															// the output of Sample task 63.
															for(int var114 = 0; var114 < noStates; var114 += 1) {
																for(int var110 = 0; var110 < noServers; var110 += 1) {
																	if((var110 == server)) {
																		if((var114 == traceTempVariable$currentState$26_1)) {
																			{
																				{
																					double cv$temp$6$var144;
																					{
																						// Constructing a random variable input for use later.
																						double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$26_1];
																						cv$temp$6$var144 = var144;
																					}
																					
																					// Record the probability of sample task 161 generating output with current configuration.
																					if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$6$var144)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$6$var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$6$var144));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$6$var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$6$var144)));
																					}
																					
																					// Recorded the probability of reaching sample task 161 with the current configuration.
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
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
										}
									}
								}
							}
							
							// Processing random variable 155.
							{
								// Looking for a path between Sample 63 and consumer Gaussian 155.
								{
									// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									boolean[][][] guard$sample63gaussian172 = guard$sample63gaussian172$global;
									for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
										if((sample$var32 == sample$var120)) {
											for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int server = 0; server < noServers; server += 1)
														// Set the flags to false
														guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
												}
											}
										}
									}
									for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
										if((sample$var32 == sample$var120)) {
											for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int server = 0; server < noServers; server += 1)
														// Set the flags to false
														guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
												}
											}
										}
									}
									int traceTempVariable$currentState$50_1 = cv$currentValue;
									for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
										if((sample$var32 == sample$var120)) {
											for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int server = 0; server < noServers; server += 1) {
														if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
															
															// Processing sample task 173 of consumer random variable null.
															{
																// Set an accumulator to sum the probabilities for each possible configuration of
																// inputs.
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																
																// Set an accumulator to record the consumer distributions not seen. Initially set
																// to 1 as seen values will be deducted from this value.
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																	// the output of Sample task 63.
																	for(int var86 = 0; var86 < noStates; var86 += 1) {
																		for(int var82 = 0; var82 < noServers; var82 += 1) {
																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																				if((var82 == server)) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var86 == traceTempVariable$currentState$50_1)) {
																							if(fixedFlag$sample50) {
																								for(int index$sample$97_1 = 0; index$sample$97_1 < noSamples; index$sample$97_1 += 1) {
																									if((index$sample$97_1 == sample$var120)) {
																										if((0 == timeStep$var140)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$50_1)) {
																																	{
																																		{
																																			double cv$temp$22$var152;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$50_1];
																																				cv$temp$22$var152 = var152;
																																			}
																																			double cv$temp$23$var154;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$50_1];
																																				cv$temp$23$var154 = var154;
																																			}
																																			
																																			// Record the probability of sample task 173 generating output with current configuration.
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 173 with the current configuration.
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							} else {
																								for(int index$sample$98 = 0; index$sample$98 < noSamples; index$sample$98 += 1) {
																									if(true) {
																										// Enumerating the possible outputs of Categorical 43.
																										for(int index$sample50$99 = 0; index$sample50$99 < noStates; index$sample50$99 += 1) {
																											int distributionTempVariable$var44$101 = index$sample50$99;
																											
																											// Update the probability of sampling this value from the distribution value.
																											double cv$probabilitySample50Value100 = (1.0 * distribution$sample50[((index$sample$98 - 0) / 1)][index$sample50$99]);
																											int traceTempVariable$currentState$102_1 = distributionTempVariable$var44$101;
																											if((index$sample$98 == sample$var120)) {
																												if((0 == timeStep$var140)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$102_1)) {
																																			{
																																				{
																																					double cv$temp$24$var152;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$102_1];
																																						cv$temp$24$var152 = var152;
																																					}
																																					double cv$temp$25$var154;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$102_1];
																																						cv$temp$25$var154 = var154;
																																					}
																																					
																																					// Record the probability of sample task 173 generating output with current configuration.
																																					if(((Math.log(cv$probabilitySample50Value100) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value100) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value100) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value100) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value100) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 173 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value100);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	
																	// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																	// the output of Sample task 63.
																	for(int var86 = 0; var86 < noStates; var86 += 1) {
																		for(int var82 = 0; var82 < noServers; var82 += 1) {
																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																				if((var82 == server)) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var86 == traceTempVariable$currentState$50_1)) {
																							int traceTempVariable$currentState$106_1 = cv$currentValue;
																							if((index$sample$2 == sample$var120)) {
																								if((index$timeStep$1 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$106_1)) {
																															{
																																{
																																	double cv$temp$26$var152;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$106_1];
																																		cv$temp$26$var152 = var152;
																																	}
																																	double cv$temp$27$var154;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$106_1];
																																		cv$temp$27$var154 = var154;
																																	}
																																	
																																	// Record the probability of sample task 173 generating output with current configuration.
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		// If the second value is -infinity.
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))));
																																	}
																																	
																																	// Recorded the probability of reaching sample task 173 with the current configuration.
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int index$sample$107 = 0; index$sample$107 < noSamples; index$sample$107 += 1) {
																								for(int index$timeStep$108 = 1; index$timeStep$108 < length$metric[index$sample$107][0]; index$timeStep$108 += 1) {
																									if(!((index$sample$107 == index$sample$2) && (index$timeStep$108 == index$timeStep$1))) {
																										// Enumerating the possible outputs of Categorical 56.
																										for(int index$sample63$109 = 0; index$sample63$109 < noStates; index$sample63$109 += 1) {
																											int distributionTempVariable$var57$111 = index$sample63$109;
																											
																											// Update the probability of sampling this value from the distribution value.
																											double cv$probabilitySample63Value110 = (1.0 * distribution$sample63[((index$sample$107 - 0) / 1)][((index$timeStep$108 - 1) / 1)][index$sample63$109]);
																											int traceTempVariable$currentState$112_1 = distributionTempVariable$var57$111;
																											if((index$sample$107 == sample$var120)) {
																												if((index$timeStep$108 == timeStep$var140)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$112_1)) {
																																			{
																																				{
																																					double cv$temp$28$var152;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$112_1];
																																						cv$temp$28$var152 = var152;
																																					}
																																					double cv$temp$29$var154;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$112_1];
																																						cv$temp$29$var154 = var154;
																																					}
																																					
																																					// Record the probability of sample task 173 generating output with current configuration.
																																					if(((Math.log(cv$probabilitySample63Value110) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value110) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value110) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value110) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value110) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 173 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value110);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
												}
											}
										}
									}
									int traceTempVariable$currentState$54_1 = cv$currentValue;
									for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
										if((sample$var32 == sample$var120)) {
											for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int server = 0; server < noServers; server += 1) {
														if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
															
															// Processing sample task 173 of consumer random variable null.
															{
																// Set an accumulator to sum the probabilities for each possible configuration of
																// inputs.
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																
																// Set an accumulator to record the consumer distributions not seen. Initially set
																// to 1 as seen values will be deducted from this value.
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																	// the output of Sample task 63.
																	if(fixedFlag$sample50) {
																		for(int index$sample$171_1 = 0; index$sample$171_1 < noSamples; index$sample$171_1 += 1) {
																			if((index$sample$171_1 == sample$var120)) {
																				if((0 == timeStep$var140)) {
																					for(int var86 = 0; var86 < noStates; var86 += 1) {
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$54_1)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$54_1)) {
																																	{
																																		{
																																			double cv$temp$54$var152;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$54_1];
																																				cv$temp$54$var152 = var152;
																																			}
																																			double cv$temp$55$var154;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$54_1];
																																				cv$temp$55$var154 = var154;
																																			}
																																			
																																			// Record the probability of sample task 173 generating output with current configuration.
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$54$var152) / Math.sqrt(cv$temp$55$var154))) - (0.5 * Math.log(cv$temp$55$var154)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$54$var152) / Math.sqrt(cv$temp$55$var154))) - (0.5 * Math.log(cv$temp$55$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$54$var152) / Math.sqrt(cv$temp$55$var154))) - (0.5 * Math.log(cv$temp$55$var154))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$54$var152) / Math.sqrt(cv$temp$55$var154))) - (0.5 * Math.log(cv$temp$55$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$54$var152) / Math.sqrt(cv$temp$55$var154))) - (0.5 * Math.log(cv$temp$55$var154)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 173 with the current configuration.
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	} else {
																		for(int index$sample$172 = 0; index$sample$172 < noSamples; index$sample$172 += 1) {
																			if(true) {
																				// Enumerating the possible outputs of Categorical 43.
																				for(int index$sample50$173 = 0; index$sample50$173 < noStates; index$sample50$173 += 1) {
																					int distributionTempVariable$var44$175 = index$sample50$173;
																					
																					// Update the probability of sampling this value from the distribution value.
																					double cv$probabilitySample50Value174 = (1.0 * distribution$sample50[((index$sample$172 - 0) / 1)][index$sample50$173]);
																					int traceTempVariable$currentState$176_1 = distributionTempVariable$var44$175;
																					if((index$sample$172 == sample$var120)) {
																						if((0 == timeStep$var140)) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$176_1)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$176_1)) {
																																			{
																																				{
																																					double cv$temp$56$var152;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$176_1];
																																						cv$temp$56$var152 = var152;
																																					}
																																					double cv$temp$57$var154;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$176_1];
																																						cv$temp$57$var154 = var154;
																																					}
																																					
																																					// Record the probability of sample task 173 generating output with current configuration.
																																					if(((Math.log(cv$probabilitySample50Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$56$var152) / Math.sqrt(cv$temp$57$var154))) - (0.5 * Math.log(cv$temp$57$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$56$var152) / Math.sqrt(cv$temp$57$var154))) - (0.5 * Math.log(cv$temp$57$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$56$var152) / Math.sqrt(cv$temp$57$var154))) - (0.5 * Math.log(cv$temp$57$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$56$var152) / Math.sqrt(cv$temp$57$var154))) - (0.5 * Math.log(cv$temp$57$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$56$var152) / Math.sqrt(cv$temp$57$var154))) - (0.5 * Math.log(cv$temp$57$var154)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 173 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value174);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	
																	// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																	// the output of Sample task 63.
																	int traceTempVariable$currentState$181_1 = cv$currentValue;
																	if((index$sample$2 == sample$var120)) {
																		if((index$timeStep$1 == timeStep$var140)) {
																			for(int var86 = 0; var86 < noStates; var86 += 1) {
																				for(int var82 = 0; var82 < noServers; var82 += 1) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var82 == server)) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var86 == traceTempVariable$currentState$181_1)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$181_1)) {
																															{
																																{
																																	double cv$temp$58$var152;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$181_1];
																																		cv$temp$58$var152 = var152;
																																	}
																																	double cv$temp$59$var154;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$181_1];
																																		cv$temp$59$var154 = var154;
																																	}
																																	
																																	// Record the probability of sample task 173 generating output with current configuration.
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$58$var152) / Math.sqrt(cv$temp$59$var154))) - (0.5 * Math.log(cv$temp$59$var154)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$58$var152) / Math.sqrt(cv$temp$59$var154))) - (0.5 * Math.log(cv$temp$59$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		// If the second value is -infinity.
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$58$var152) / Math.sqrt(cv$temp$59$var154))) - (0.5 * Math.log(cv$temp$59$var154))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$58$var152) / Math.sqrt(cv$temp$59$var154))) - (0.5 * Math.log(cv$temp$59$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$58$var152) / Math.sqrt(cv$temp$59$var154))) - (0.5 * Math.log(cv$temp$59$var154)))));
																																	}
																																	
																																	// Recorded the probability of reaching sample task 173 with the current configuration.
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	for(int index$sample$182 = 0; index$sample$182 < noSamples; index$sample$182 += 1) {
																		for(int index$timeStep$183 = 1; index$timeStep$183 < length$metric[index$sample$182][0]; index$timeStep$183 += 1) {
																			if(!((index$sample$182 == index$sample$2) && (index$timeStep$183 == index$timeStep$1))) {
																				// Enumerating the possible outputs of Categorical 56.
																				for(int index$sample63$184 = 0; index$sample63$184 < noStates; index$sample63$184 += 1) {
																					int distributionTempVariable$var57$186 = index$sample63$184;
																					
																					// Update the probability of sampling this value from the distribution value.
																					double cv$probabilitySample63Value185 = (1.0 * distribution$sample63[((index$sample$182 - 0) / 1)][((index$timeStep$183 - 1) / 1)][index$sample63$184]);
																					int traceTempVariable$currentState$187_1 = distributionTempVariable$var57$186;
																					if((index$sample$182 == sample$var120)) {
																						if((index$timeStep$183 == timeStep$var140)) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$187_1)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$187_1)) {
																																			{
																																				{
																																					double cv$temp$60$var152;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$187_1];
																																						cv$temp$60$var152 = var152;
																																					}
																																					double cv$temp$61$var154;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$187_1];
																																						cv$temp$61$var154 = var154;
																																					}
																																					
																																					// Record the probability of sample task 173 generating output with current configuration.
																																					if(((Math.log(cv$probabilitySample63Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$60$var152) / Math.sqrt(cv$temp$61$var154))) - (0.5 * Math.log(cv$temp$61$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$60$var152) / Math.sqrt(cv$temp$61$var154))) - (0.5 * Math.log(cv$temp$61$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$60$var152) / Math.sqrt(cv$temp$61$var154))) - (0.5 * Math.log(cv$temp$61$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$60$var152) / Math.sqrt(cv$temp$61$var154))) - (0.5 * Math.log(cv$temp$61$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$60$var152) / Math.sqrt(cv$temp$61$var154))) - (0.5 * Math.log(cv$temp$61$var154)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 173 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value185);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
												}
											}
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
			for(int index$sample$12 = 0; index$sample$12 < noSamples; index$sample$12 += 1) {
				for(int index$timeStep$13 = 1; index$timeStep$13 < length$metric[index$sample$12][0]; index$timeStep$13 += 1) {
					if(!((index$sample$12 == index$sample$2) && (index$timeStep$13 == index$timeStep$1))) {
						// Enumerating the possible outputs of Categorical 56.
						for(int index$sample63$14 = 0; index$sample63$14 < noStates; index$sample63$14 += 1) {
							int distributionTempVariable$var57$16 = index$sample63$14;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample63Value15 = (1.0 * distribution$sample63[((index$sample$12 - 0) / 1)][((index$timeStep$13 - 1) / 1)][index$sample63$14]);
							int traceTempVariable$var54$17_1 = distributionTempVariable$var57$16;
							if((index$sample$12 == sample$var32)) {
								if((index$timeStep$13 == (timeStep$var49 - 1))) {
									for(int var26 = 0; var26 < noStates; var26 += 1) {
										if((var26 == traceTempVariable$var54$17_1)) {
											// Record the reached probability density.
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample63Value15);
											double[] cv$temp$3$var55;
											{
												// Constructing a random variable input for use later.
												double[] var55 = m[traceTempVariable$var54$17_1];
												cv$temp$3$var55 = var55;
											}
											
											// An accumulator to allow the value for each distribution to be constructed before
											// it is added to the index probabilities.
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample63Value15) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var55.length))?Math.log(cv$temp$3$var55[cv$currentValue]):Double.NEGATIVE_INFINITY));
											
											// Processing random variable 56.
											{
												// Looking for a path between Sample 63 and consumer Categorical 56.
												{
													int traceTempVariable$var54$23_1 = cv$currentValue;
												}
											}
											
											// Processing random variable 145.
											{
												// Looking for a path between Sample 63 and consumer Bernoulli 145.
												{
													int traceTempVariable$currentState$27_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Bernoulli 145 which is consuming
																			// the output of Sample task 63.
																			for(int var114 = 0; var114 < noStates; var114 += 1) {
																				for(int var110 = 0; var110 < noServers; var110 += 1) {
																					if((var110 == server)) {
																						if((var114 == traceTempVariable$currentState$27_1)) {
																							{
																								{
																									double cv$temp$7$var144;
																									{
																										// Constructing a random variable input for use later.
																										double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$27_1];
																										cv$temp$7$var144 = var144;
																									}
																									
																									// Record the probability of sample task 161 generating output with current configuration.
																									if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$7$var144)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$7$var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$7$var144));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$7$var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$7$var144)));
																									}
																									
																									// Recorded the probability of reaching sample task 161 with the current configuration.
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																								}
																							}
																						}
																					}
																				}
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
														}
													}
												}
											}
											
											// Processing random variable 155.
											{
												// Looking for a path between Sample 63 and consumer Gaussian 155.
												{
													// Guard to check that at most one copy of the code is executed for a given random
													// variable instance.
													boolean[][][] guard$sample63gaussian172 = guard$sample63gaussian172$global;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1)
																		// Set the flags to false
																		guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
																}
															}
														}
													}
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1)
																		// Set the flags to false
																		guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
																}
															}
														}
													}
													int traceTempVariable$currentState$51_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																			
																			// Processing sample task 173 of consumer random variable null.
																			{
																				// Set an accumulator to sum the probabilities for each possible configuration of
																				// inputs.
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				
																				// Set an accumulator to record the consumer distributions not seen. Initially set
																				// to 1 as seen values will be deducted from this value.
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																					// the output of Sample task 63.
																					for(int var86 = 0; var86 < noStates; var86 += 1) {
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$51_1)) {
																											if(fixedFlag$sample50) {
																												for(int index$sample$116_1 = 0; index$sample$116_1 < noSamples; index$sample$116_1 += 1) {
																													if((index$sample$116_1 == sample$var120)) {
																														if((0 == timeStep$var140)) {
																															for(int var100 = 0; var100 < noStates; var100 += 1) {
																																for(int var96 = 0; var96 < noServers; var96 += 1) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var96 == server)) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var100 == traceTempVariable$currentState$51_1)) {
																																					{
																																						{
																																							double cv$temp$30$var152;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$51_1];
																																								cv$temp$30$var152 = var152;
																																							}
																																							double cv$temp$31$var154;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$51_1];
																																								cv$temp$31$var154 = var154;
																																							}
																																							
																																							// Record the probability of sample task 173 generating output with current configuration.
																																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$30$var152) / Math.sqrt(cv$temp$31$var154))) - (0.5 * Math.log(cv$temp$31$var154)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$30$var152) / Math.sqrt(cv$temp$31$var154))) - (0.5 * Math.log(cv$temp$31$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								// If the second value is -infinity.
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$30$var152) / Math.sqrt(cv$temp$31$var154))) - (0.5 * Math.log(cv$temp$31$var154))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$30$var152) / Math.sqrt(cv$temp$31$var154))) - (0.5 * Math.log(cv$temp$31$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$30$var152) / Math.sqrt(cv$temp$31$var154))) - (0.5 * Math.log(cv$temp$31$var154)))));
																																							}
																																							
																																							// Recorded the probability of reaching sample task 173 with the current configuration.
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											} else {
																												for(int index$sample$117 = 0; index$sample$117 < noSamples; index$sample$117 += 1) {
																													if(true) {
																														// Enumerating the possible outputs of Categorical 43.
																														for(int index$sample50$118 = 0; index$sample50$118 < noStates; index$sample50$118 += 1) {
																															int distributionTempVariable$var44$120 = index$sample50$118;
																															
																															// Update the probability of sampling this value from the distribution value.
																															double cv$probabilitySample50Value119 = (1.0 * distribution$sample50[((index$sample$117 - 0) / 1)][index$sample50$118]);
																															int traceTempVariable$currentState$121_1 = distributionTempVariable$var44$120;
																															if((index$sample$117 == sample$var120)) {
																																if((0 == timeStep$var140)) {
																																	for(int var100 = 0; var100 < noStates; var100 += 1) {
																																		for(int var96 = 0; var96 < noServers; var96 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$121_1)) {
																																							{
																																								{
																																									double cv$temp$32$var152;
																																									{
																																										// Constructing a random variable input for use later.
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$121_1];
																																										cv$temp$32$var152 = var152;
																																									}
																																									double cv$temp$33$var154;
																																									{
																																										// Constructing a random variable input for use later.
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$121_1];
																																										cv$temp$33$var154 = var154;
																																									}
																																									
																																									// Record the probability of sample task 173 generating output with current configuration.
																																									if(((Math.log(cv$probabilitySample50Value119) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$32$var152) / Math.sqrt(cv$temp$33$var154))) - (0.5 * Math.log(cv$temp$33$var154)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value119) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$32$var152) / Math.sqrt(cv$temp$33$var154))) - (0.5 * Math.log(cv$temp$33$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										// If the second value is -infinity.
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value119) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$32$var152) / Math.sqrt(cv$temp$33$var154))) - (0.5 * Math.log(cv$temp$33$var154))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value119) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$32$var152) / Math.sqrt(cv$temp$33$var154))) - (0.5 * Math.log(cv$temp$33$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value119) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$32$var152) / Math.sqrt(cv$temp$33$var154))) - (0.5 * Math.log(cv$temp$33$var154)))));
																																									}
																																									
																																									// Recorded the probability of reaching sample task 173 with the current configuration.
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value119);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					
																					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																					// the output of Sample task 63.
																					for(int var86 = 0; var86 < noStates; var86 += 1) {
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$51_1)) {
																											int traceTempVariable$currentState$125_1 = cv$currentValue;
																											if((index$sample$2 == sample$var120)) {
																												if((index$timeStep$1 == timeStep$var140)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$125_1)) {
																																			{
																																				{
																																					double cv$temp$34$var152;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$125_1];
																																						cv$temp$34$var152 = var152;
																																					}
																																					double cv$temp$35$var154;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$125_1];
																																						cv$temp$35$var154 = var154;
																																					}
																																					
																																					// Record the probability of sample task 173 generating output with current configuration.
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$34$var152) / Math.sqrt(cv$temp$35$var154))) - (0.5 * Math.log(cv$temp$35$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$34$var152) / Math.sqrt(cv$temp$35$var154))) - (0.5 * Math.log(cv$temp$35$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$34$var152) / Math.sqrt(cv$temp$35$var154))) - (0.5 * Math.log(cv$temp$35$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$34$var152) / Math.sqrt(cv$temp$35$var154))) - (0.5 * Math.log(cv$temp$35$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$34$var152) / Math.sqrt(cv$temp$35$var154))) - (0.5 * Math.log(cv$temp$35$var154)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 173 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																											int traceTempVariable$currentState$126_1 = distributionTempVariable$var57$16;
																											if((index$sample$12 == sample$var120)) {
																												if((index$timeStep$13 == timeStep$var140)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$126_1)) {
																																			{
																																				{
																																					double cv$temp$36$var152;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$126_1];
																																						cv$temp$36$var152 = var152;
																																					}
																																					double cv$temp$37$var154;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$126_1];
																																						cv$temp$37$var154 = var154;
																																					}
																																					
																																					// Record the probability of sample task 173 generating output with current configuration.
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$36$var152) / Math.sqrt(cv$temp$37$var154))) - (0.5 * Math.log(cv$temp$37$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$36$var152) / Math.sqrt(cv$temp$37$var154))) - (0.5 * Math.log(cv$temp$37$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$36$var152) / Math.sqrt(cv$temp$37$var154))) - (0.5 * Math.log(cv$temp$37$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$36$var152) / Math.sqrt(cv$temp$37$var154))) - (0.5 * Math.log(cv$temp$37$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$36$var152) / Math.sqrt(cv$temp$37$var154))) - (0.5 * Math.log(cv$temp$37$var154)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 173 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																											for(int index$sample$127 = 0; index$sample$127 < noSamples; index$sample$127 += 1) {
																												for(int index$timeStep$128 = 1; index$timeStep$128 < length$metric[index$sample$127][0]; index$timeStep$128 += 1) {
																													if((!((index$sample$127 == index$sample$2) && (index$timeStep$128 == index$timeStep$1)) && !((index$sample$127 == index$sample$12) && (index$timeStep$128 == index$timeStep$13)))) {
																														// Enumerating the possible outputs of Categorical 56.
																														for(int index$sample63$129 = 0; index$sample63$129 < noStates; index$sample63$129 += 1) {
																															int distributionTempVariable$var57$131 = index$sample63$129;
																															
																															// Update the probability of sampling this value from the distribution value.
																															double cv$probabilitySample63Value130 = (1.0 * distribution$sample63[((index$sample$127 - 0) / 1)][((index$timeStep$128 - 1) / 1)][index$sample63$129]);
																															int traceTempVariable$currentState$132_1 = distributionTempVariable$var57$131;
																															if((index$sample$127 == sample$var120)) {
																																if((index$timeStep$128 == timeStep$var140)) {
																																	for(int var100 = 0; var100 < noStates; var100 += 1) {
																																		for(int var96 = 0; var96 < noServers; var96 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$132_1)) {
																																							{
																																								{
																																									double cv$temp$38$var152;
																																									{
																																										// Constructing a random variable input for use later.
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$132_1];
																																										cv$temp$38$var152 = var152;
																																									}
																																									double cv$temp$39$var154;
																																									{
																																										// Constructing a random variable input for use later.
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$132_1];
																																										cv$temp$39$var154 = var154;
																																									}
																																									
																																									// Record the probability of sample task 173 generating output with current configuration.
																																									if(((Math.log(cv$probabilitySample63Value130) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$38$var152) / Math.sqrt(cv$temp$39$var154))) - (0.5 * Math.log(cv$temp$39$var154)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value130) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$38$var152) / Math.sqrt(cv$temp$39$var154))) - (0.5 * Math.log(cv$temp$39$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										// If the second value is -infinity.
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value130) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$38$var152) / Math.sqrt(cv$temp$39$var154))) - (0.5 * Math.log(cv$temp$39$var154))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value130) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$38$var152) / Math.sqrt(cv$temp$39$var154))) - (0.5 * Math.log(cv$temp$39$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value130) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$38$var152) / Math.sqrt(cv$temp$39$var154))) - (0.5 * Math.log(cv$temp$39$var154)))));
																																									}
																																									
																																									// Recorded the probability of reaching sample task 173 with the current configuration.
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value130);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
																}
															}
														}
													}
													int traceTempVariable$currentState$55_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																			
																			// Processing sample task 173 of consumer random variable null.
																			{
																				// Set an accumulator to sum the probabilities for each possible configuration of
																				// inputs.
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				
																				// Set an accumulator to record the consumer distributions not seen. Initially set
																				// to 1 as seen values will be deducted from this value.
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																					// the output of Sample task 63.
																					if(fixedFlag$sample50) {
																						for(int index$sample$192_1 = 0; index$sample$192_1 < noSamples; index$sample$192_1 += 1) {
																							if((index$sample$192_1 == sample$var120)) {
																								if((0 == timeStep$var140)) {
																									for(int var86 = 0; var86 < noStates; var86 += 1) {
																										for(int var82 = 0; var82 < noServers; var82 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var82 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var86 == traceTempVariable$currentState$55_1)) {
																															for(int var100 = 0; var100 < noStates; var100 += 1) {
																																for(int var96 = 0; var96 < noServers; var96 += 1) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var96 == server)) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var100 == traceTempVariable$currentState$55_1)) {
																																					{
																																						{
																																							double cv$temp$62$var152;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$55_1];
																																								cv$temp$62$var152 = var152;
																																							}
																																							double cv$temp$63$var154;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$55_1];
																																								cv$temp$63$var154 = var154;
																																							}
																																							
																																							// Record the probability of sample task 173 generating output with current configuration.
																																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$62$var152) / Math.sqrt(cv$temp$63$var154))) - (0.5 * Math.log(cv$temp$63$var154)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$62$var152) / Math.sqrt(cv$temp$63$var154))) - (0.5 * Math.log(cv$temp$63$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								// If the second value is -infinity.
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$62$var152) / Math.sqrt(cv$temp$63$var154))) - (0.5 * Math.log(cv$temp$63$var154))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$62$var152) / Math.sqrt(cv$temp$63$var154))) - (0.5 * Math.log(cv$temp$63$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$62$var152) / Math.sqrt(cv$temp$63$var154))) - (0.5 * Math.log(cv$temp$63$var154)))));
																																							}
																																							
																																							// Recorded the probability of reaching sample task 173 with the current configuration.
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					} else {
																						for(int index$sample$193 = 0; index$sample$193 < noSamples; index$sample$193 += 1) {
																							if(true) {
																								// Enumerating the possible outputs of Categorical 43.
																								for(int index$sample50$194 = 0; index$sample50$194 < noStates; index$sample50$194 += 1) {
																									int distributionTempVariable$var44$196 = index$sample50$194;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample50Value195 = (1.0 * distribution$sample50[((index$sample$193 - 0) / 1)][index$sample50$194]);
																									int traceTempVariable$currentState$197_1 = distributionTempVariable$var44$196;
																									if((index$sample$193 == sample$var120)) {
																										if((0 == timeStep$var140)) {
																											for(int var86 = 0; var86 < noStates; var86 += 1) {
																												for(int var82 = 0; var82 < noServers; var82 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var82 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var86 == traceTempVariable$currentState$197_1)) {
																																	for(int var100 = 0; var100 < noStates; var100 += 1) {
																																		for(int var96 = 0; var96 < noServers; var96 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$197_1)) {
																																							{
																																								{
																																									double cv$temp$64$var152;
																																									{
																																										// Constructing a random variable input for use later.
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$197_1];
																																										cv$temp$64$var152 = var152;
																																									}
																																									double cv$temp$65$var154;
																																									{
																																										// Constructing a random variable input for use later.
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$197_1];
																																										cv$temp$65$var154 = var154;
																																									}
																																									
																																									// Record the probability of sample task 173 generating output with current configuration.
																																									if(((Math.log(cv$probabilitySample50Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$64$var152) / Math.sqrt(cv$temp$65$var154))) - (0.5 * Math.log(cv$temp$65$var154)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$64$var152) / Math.sqrt(cv$temp$65$var154))) - (0.5 * Math.log(cv$temp$65$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										// If the second value is -infinity.
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$64$var152) / Math.sqrt(cv$temp$65$var154))) - (0.5 * Math.log(cv$temp$65$var154))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$64$var152) / Math.sqrt(cv$temp$65$var154))) - (0.5 * Math.log(cv$temp$65$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$64$var152) / Math.sqrt(cv$temp$65$var154))) - (0.5 * Math.log(cv$temp$65$var154)))));
																																									}
																																									
																																									// Recorded the probability of reaching sample task 173 with the current configuration.
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value195);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					
																					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																					// the output of Sample task 63.
																					int traceTempVariable$currentState$202_1 = cv$currentValue;
																					if((index$sample$2 == sample$var120)) {
																						if((index$timeStep$1 == timeStep$var140)) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$202_1)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$202_1)) {
																																			{
																																				{
																																					double cv$temp$66$var152;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$202_1];
																																						cv$temp$66$var152 = var152;
																																					}
																																					double cv$temp$67$var154;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$202_1];
																																						cv$temp$67$var154 = var154;
																																					}
																																					
																																					// Record the probability of sample task 173 generating output with current configuration.
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$66$var152) / Math.sqrt(cv$temp$67$var154))) - (0.5 * Math.log(cv$temp$67$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$66$var152) / Math.sqrt(cv$temp$67$var154))) - (0.5 * Math.log(cv$temp$67$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$66$var152) / Math.sqrt(cv$temp$67$var154))) - (0.5 * Math.log(cv$temp$67$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$66$var152) / Math.sqrt(cv$temp$67$var154))) - (0.5 * Math.log(cv$temp$67$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$66$var152) / Math.sqrt(cv$temp$67$var154))) - (0.5 * Math.log(cv$temp$67$var154)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 173 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					int traceTempVariable$currentState$203_1 = distributionTempVariable$var57$16;
																					if((index$sample$12 == sample$var120)) {
																						if((index$timeStep$13 == timeStep$var140)) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$203_1)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$203_1)) {
																																			{
																																				{
																																					double cv$temp$68$var152;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$203_1];
																																						cv$temp$68$var152 = var152;
																																					}
																																					double cv$temp$69$var154;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$203_1];
																																						cv$temp$69$var154 = var154;
																																					}
																																					
																																					// Record the probability of sample task 173 generating output with current configuration.
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$68$var152) / Math.sqrt(cv$temp$69$var154))) - (0.5 * Math.log(cv$temp$69$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$68$var152) / Math.sqrt(cv$temp$69$var154))) - (0.5 * Math.log(cv$temp$69$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$68$var152) / Math.sqrt(cv$temp$69$var154))) - (0.5 * Math.log(cv$temp$69$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$68$var152) / Math.sqrt(cv$temp$69$var154))) - (0.5 * Math.log(cv$temp$69$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$68$var152) / Math.sqrt(cv$temp$69$var154))) - (0.5 * Math.log(cv$temp$69$var154)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 173 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$204 = 0; index$sample$204 < noSamples; index$sample$204 += 1) {
																						for(int index$timeStep$205 = 1; index$timeStep$205 < length$metric[index$sample$204][0]; index$timeStep$205 += 1) {
																							if((!((index$sample$204 == index$sample$2) && (index$timeStep$205 == index$timeStep$1)) && !((index$sample$204 == index$sample$12) && (index$timeStep$205 == index$timeStep$13)))) {
																								// Enumerating the possible outputs of Categorical 56.
																								for(int index$sample63$206 = 0; index$sample63$206 < noStates; index$sample63$206 += 1) {
																									int distributionTempVariable$var57$208 = index$sample63$206;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample63Value207 = (1.0 * distribution$sample63[((index$sample$204 - 0) / 1)][((index$timeStep$205 - 1) / 1)][index$sample63$206]);
																									int traceTempVariable$currentState$209_1 = distributionTempVariable$var57$208;
																									if((index$sample$204 == sample$var120)) {
																										if((index$timeStep$205 == timeStep$var140)) {
																											for(int var86 = 0; var86 < noStates; var86 += 1) {
																												for(int var82 = 0; var82 < noServers; var82 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var82 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var86 == traceTempVariable$currentState$209_1)) {
																																	for(int var100 = 0; var100 < noStates; var100 += 1) {
																																		for(int var96 = 0; var96 < noServers; var96 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$209_1)) {
																																							{
																																								{
																																									double cv$temp$70$var152;
																																									{
																																										// Constructing a random variable input for use later.
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$209_1];
																																										cv$temp$70$var152 = var152;
																																									}
																																									double cv$temp$71$var154;
																																									{
																																										// Constructing a random variable input for use later.
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$209_1];
																																										cv$temp$71$var154 = var154;
																																									}
																																									
																																									// Record the probability of sample task 173 generating output with current configuration.
																																									if(((Math.log(cv$probabilitySample63Value207) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$70$var152) / Math.sqrt(cv$temp$71$var154))) - (0.5 * Math.log(cv$temp$71$var154)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value207) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$70$var152) / Math.sqrt(cv$temp$71$var154))) - (0.5 * Math.log(cv$temp$71$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										// If the second value is -infinity.
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value207) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$70$var152) / Math.sqrt(cv$temp$71$var154))) - (0.5 * Math.log(cv$temp$71$var154))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value207) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$70$var152) / Math.sqrt(cv$temp$71$var154))) - (0.5 * Math.log(cv$temp$71$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value207) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$70$var152) / Math.sqrt(cv$temp$71$var154))) - (0.5 * Math.log(cv$temp$71$var154)))));
																																									}
																																									
																																									// Recorded the probability of reaching sample task 173 with the current configuration.
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value207);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
																}
															}
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
						}
					}
				}
			}
			
			// Processing random variable 56.
			{
				// Looking for a path between Sample 63 and consumer Categorical 56.
				{
					int traceTempVariable$var54$248_1 = cv$currentValue;
					for(int index$sample$248_2 = 0; index$sample$248_2 < noSamples; index$sample$248_2 += 1) {
						if((sample$var32 == index$sample$248_2)) {
							for(int index$timeStep$248_3 = 1; index$timeStep$248_3 < length$metric[index$sample$248_2][0]; index$timeStep$248_3 += 1) {
								if((timeStep$var49 == (index$timeStep$248_3 - 1))) {
									// Processing sample task 63 of consumer random variable null.
									{
										// Copy of index so that its values can be safely substituted
										int index$timeStep$250 = index$timeStep$248_3;
										
										// Copy of index so that its values can be safely substituted
										int index$sample$251 = index$sample$248_2;
										
										// A local array to hold the accumulated distributions of the sample tasks for each
										// configuration of distributions.
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var56;
										
										// Zero all the elements in the distribution accumulator
										for(int cv$i = 0; cv$i < noStates; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										
										// Zero an accumulator to track the probabilities reached.
										double cv$reachedDistributionProbability = 0.0;
										
										// Enumerating the possible arguments for the variable Categorical 56 which is consuming
										// the output of Sample task 63.
										for(int var26 = 0; var26 < noStates; var26 += 1) {
											if((var26 == traceTempVariable$var54$248_1)) {
												{
													// Declare and zero an accumulator for tracking the reached source probability space.
													double scopeVariable$reachedSourceProbability = 0.0;
													
													// Enumerating the possible arguments for Categorical 56.
													if(fixedFlag$sample50) {
														for(int index$sample$253_1 = 0; index$sample$253_1 < noSamples; index$sample$253_1 += 1) {
															if((index$sample$253_1 == sample$var32)) {
																if((0 == (timeStep$var49 - 1))) {
																	for(int index$var26$259_1 = 0; index$var26$259_1 < noStates; index$var26$259_1 += 1) {
																		if((index$var26$259_1 == st[sample$var32][(timeStep$var49 - 1)]))
																			// Add the probability of this argument configuration.
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
													} else {
														for(int index$sample$254 = 0; index$sample$254 < noSamples; index$sample$254 += 1) {
															if(true) {
																// Enumerating the possible outputs of Categorical 43.
																for(int index$sample50$255 = 0; index$sample50$255 < noStates; index$sample50$255 += 1) {
																	int distributionTempVariable$var44$257 = index$sample50$255;
																	
																	// Update the probability of sampling this value from the distribution value.
																	double cv$probabilitySample50Value256 = (1.0 * distribution$sample50[((index$sample$254 - 0) / 1)][index$sample50$255]);
																	int traceTempVariable$var54$258_1 = distributionTempVariable$var44$257;
																	if((index$sample$254 == sample$var32)) {
																		if((0 == (timeStep$var49 - 1))) {
																			for(int index$var26$260_1 = 0; index$var26$260_1 < noStates; index$var26$260_1 += 1) {
																				if((index$var26$260_1 == traceTempVariable$var54$258_1))
																					// Add the probability of this argument configuration.
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample50Value256);
																			}
																		}
																	}
																}
															}
														}
													}
													
													// Enumerating the possible arguments for Categorical 56.
													int traceTempVariable$var54$261_1 = cv$currentValue;
													if((index$sample$2 == sample$var32)) {
														if((index$timeStep$1 == (timeStep$var49 - 1))) {
															for(int index$var26$268_1 = 0; index$var26$268_1 < noStates; index$var26$268_1 += 1) {
																if((index$var26$268_1 == traceTempVariable$var54$261_1))
																	// Add the probability of this argument configuration.
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
														}
													}
													for(int index$sample$262 = 0; index$sample$262 < noSamples; index$sample$262 += 1) {
														for(int index$timeStep$263 = 1; index$timeStep$263 < length$metric[index$sample$262][0]; index$timeStep$263 += 1) {
															if((!((index$sample$262 == index$sample$2) && (index$timeStep$263 == index$timeStep$1)) && !((index$sample$262 == index$sample$251) && (index$timeStep$263 == index$timeStep$250)))) {
																// Enumerating the possible outputs of Categorical 56.
																for(int index$sample63$264 = 0; index$sample63$264 < noStates; index$sample63$264 += 1) {
																	int distributionTempVariable$var57$266 = index$sample63$264;
																	
																	// Update the probability of sampling this value from the distribution value.
																	double cv$probabilitySample63Value265 = (1.0 * distribution$sample63[((index$sample$262 - 0) / 1)][((index$timeStep$263 - 1) / 1)][index$sample63$264]);
																	int traceTempVariable$var54$267_1 = distributionTempVariable$var57$266;
																	if((index$sample$262 == sample$var32)) {
																		if((index$timeStep$263 == (timeStep$var49 - 1))) {
																			for(int index$var26$269_1 = 0; index$var26$269_1 < noStates; index$var26$269_1 += 1) {
																				if((index$var26$269_1 == traceTempVariable$var54$267_1))
																					// Add the probability of this argument configuration.
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample63Value265);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$72$var55;
													{
														// Constructing a random variable input for use later.
														double[] var55 = m[traceTempVariable$var54$248_1];
														cv$temp$72$var55 = var55;
													}
													
													// The probability of reaching the consumer with this set of consumer arguments
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													
													// Record the reached distribution.
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													
													// Add the current distribution to the distribution accumulator.
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$72$var55);
												}
											}
										}
										
										// A local copy of the samples' distribution.
										double[] cv$sampleDistribution = distribution$sample63[((index$sample$248_2 - 0) / 1)][((index$timeStep$248_3 - 1) / 1)];
										
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
										cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(((cv$overlap * cv$reachedDistributionProbability) + (1.0 - Math.min(cv$reachedDistributionProbability, 1.0)))));
									}
								}
							}
						}
					}
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)];
		
		// The sum of all the probabilities in log space
		double cv$logSum = 0.0;
		
		// Sum all the values
		{
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
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
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
	// by sample task 96 drawn from Uniform 78. Inference was performed using Metropolis-Hastings.
	private final void sample96(int var82, int var86) {
		// The original value of the sample
		double cv$originalValue = current_metric_mean[var82][var86];
		
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability = 0.0;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		
		// Ensure the variance is at least 0.01
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		
		// The probability of the random variable generating the new sample value.
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// The value currently being tested
			double cv$currentValue;
			if((cv$valuePos == 0))
				// Set the current value to the current state of the tree.
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				
				// Update Sample and intermediate values
				{
					// Write out the value of the sample to a temporary variable prior to updating the
					// intermediate variables.
					double var87 = cv$proposedValue;
					double[] var83 = current_metric_mean[var82];
					var83[var86] = cv$currentValue;
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var76;
				{
					cv$temp$0$var76 = 0.0;
				}
				double cv$temp$1$var77;
				{
					cv$temp$1$var77 = max_metric;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var76 <= cv$currentValue) && (cv$currentValue <= cv$temp$1$var77))?(-Math.log((cv$temp$1$var77 - cv$temp$0$var76))):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 155.
				{
					// Looking for a path between Sample 96 and consumer Gaussian 155.
					{
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if(fixedFlag$sample50) {
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											if((sample$var32 == sample$var120)) {
												if((0 == timeStep$var140)) {
													double traceTempVariable$var152$10_1 = cv$currentValue;
													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
														if((var82 == server)) {
															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																if((var86 == st[sample$var120][timeStep$var140])) {
																	// Processing sample task 173 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																			// the output of Sample task 96.
																			for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																				if((index$sample$28_1 == sample$var120)) {
																					if((0 == timeStep$var140)) {
																						for(int var100 = 0; var100 < noStates; var100 += 1) {
																							for(int var96 = 0; var96 < noServers; var96 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var96 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var100 == st[sample$var120][timeStep$var140])) {
																												{
																													{
																														double cv$temp$2$var152;
																														{
																															// Constructing a random variable input for use later.
																															double var152 = traceTempVariable$var152$10_1;
																															cv$temp$2$var152 = var152;
																														}
																														double cv$temp$3$var154;
																														{
																															// Constructing a random variable input for use later.
																															double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																															cv$temp$3$var154 = var154;
																														}
																														
																														// Record the probability of sample task 173 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))));
																														}
																														
																														// Recorded the probability of reaching sample task 173 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			
																			// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																			// the output of Sample task 96.
																			if(fixedFlag$sample63) {
																				for(int index$sample$30_1 = 0; index$sample$30_1 < noSamples; index$sample$30_1 += 1) {
																					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$30_1][0]; timeStep$var49 += 1) {
																						if((index$sample$30_1 == sample$var120)) {
																							if((timeStep$var49 == timeStep$var140)) {
																								for(int var100 = 0; var100 < noStates; var100 += 1) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var96 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var100 == st[sample$var120][timeStep$var140])) {
																														{
																															{
																																double cv$temp$4$var152;
																																{
																																	// Constructing a random variable input for use later.
																																	double var152 = traceTempVariable$var152$10_1;
																																	cv$temp$4$var152 = var152;
																																}
																																double cv$temp$5$var154;
																																{
																																	// Constructing a random variable input for use later.
																																	double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$5$var154 = var154;
																																}
																																
																																// Record the probability of sample task 173 generating output with current configuration.
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))));
																																}
																																
																																// Recorded the probability of reaching sample task 173 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				for(int index$sample$31 = 0; index$sample$31 < noSamples; index$sample$31 += 1) {
																					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$31][0]; timeStep$var49 += 1) {
																						if(true) {
																							// Enumerating the possible outputs of Categorical 56.
																							for(int index$sample63$33 = 0; index$sample63$33 < noStates; index$sample63$33 += 1) {
																								int distributionTempVariable$var57$35 = index$sample63$33;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample63Value34 = (1.0 * distribution$sample63[((index$sample$31 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$33]);
																								int traceTempVariable$currentState$36_1 = distributionTempVariable$var57$35;
																								if((index$sample$31 == sample$var120)) {
																									if((timeStep$var49 == timeStep$var140)) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var96 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var100 == traceTempVariable$currentState$36_1)) {
																																{
																																	{
																																		double cv$temp$6$var152;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var152 = traceTempVariable$var152$10_1;
																																			cv$temp$6$var152 = var152;
																																		}
																																		double cv$temp$7$var154;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$36_1];
																																			cv$temp$7$var154 = var154;
																																		}
																																		
																																		// Record the probability of sample task 173 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 173 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value34);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
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
														}
													}
												}
											}
										}
									} else {
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 43.
												for(int index$sample50$6 = 0; index$sample50$6 < noStates; index$sample50$6 += 1) {
													int distributionTempVariable$var44$8 = index$sample50$6;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample50Value7 = (1.0 * distribution$sample50[((sample$var32 - 0) / 1)][index$sample50$6]);
													int traceTempVariable$currentState$9_1 = distributionTempVariable$var44$8;
													if((sample$var32 == sample$var120)) {
														if((0 == timeStep$var140)) {
															double traceTempVariable$var152$11_1 = cv$currentValue;
															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																if((var82 == server)) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var86 == traceTempVariable$currentState$9_1)) {
																			// Processing sample task 173 of consumer random variable null.
																			{
																				// Set an accumulator to sum the probabilities for each possible configuration of
																				// inputs.
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				
																				// Set an accumulator to record the consumer distributions not seen. Initially set
																				// to 1 as seen values will be deducted from this value.
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																					// the output of Sample task 96.
																					int traceTempVariable$currentState$39_1 = distributionTempVariable$var44$8;
																					if((sample$var32 == sample$var120)) {
																						if((0 == timeStep$var140)) {
																							for(int var100 = 0; var100 < noStates; var100 += 1) {
																								for(int var96 = 0; var96 < noServers; var96 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var96 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var100 == traceTempVariable$currentState$39_1)) {
																													{
																														{
																															double cv$temp$8$var152;
																															{
																																// Constructing a random variable input for use later.
																																double var152 = traceTempVariable$var152$11_1;
																																cv$temp$8$var152 = var152;
																															}
																															double cv$temp$9$var154;
																															{
																																// Constructing a random variable input for use later.
																																double var154 = current_metric_var[server][traceTempVariable$currentState$39_1];
																																cv$temp$9$var154 = var154;
																															}
																															
																															// Record the probability of sample task 173 generating output with current configuration.
																															if(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																// If the second value is -infinity.
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))));
																															}
																															
																															// Recorded the probability of reaching sample task 173 with the current configuration.
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value7);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$40 = 0; index$sample$40 < noSamples; index$sample$40 += 1) {
																						if(!(index$sample$40 == sample$var32)) {
																							// Enumerating the possible outputs of Categorical 43.
																							for(int index$sample50$41 = 0; index$sample50$41 < noStates; index$sample50$41 += 1) {
																								int distributionTempVariable$var44$43 = index$sample50$41;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample50Value42 = (cv$probabilitySample50Value7 * distribution$sample50[((index$sample$40 - 0) / 1)][index$sample50$41]);
																								int traceTempVariable$currentState$44_1 = distributionTempVariable$var44$43;
																								if((index$sample$40 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var96 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var100 == traceTempVariable$currentState$44_1)) {
																																{
																																	{
																																		double cv$temp$10$var152;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var152 = traceTempVariable$var152$11_1;
																																			cv$temp$10$var152 = var152;
																																		}
																																		double cv$temp$11$var154;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$44_1];
																																			cv$temp$11$var154 = var154;
																																		}
																																		
																																		// Record the probability of sample task 173 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 173 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value42);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					
																					// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																					// the output of Sample task 96.
																					if(fixedFlag$sample63) {
																						for(int index$sample$47_1 = 0; index$sample$47_1 < noSamples; index$sample$47_1 += 1) {
																							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$47_1][0]; timeStep$var49 += 1) {
																								if((index$sample$47_1 == sample$var120)) {
																									if((timeStep$var49 == timeStep$var140)) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var96 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var100 == traceTempVariable$currentState$9_1)) {
																																{
																																	{
																																		double cv$temp$12$var152;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var152 = traceTempVariable$var152$11_1;
																																			cv$temp$12$var152 = var152;
																																		}
																																		double cv$temp$13$var154;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$9_1];
																																			cv$temp$13$var154 = var154;
																																		}
																																		
																																		// Record the probability of sample task 173 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 173 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value7);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					} else {
																						for(int index$sample$48 = 0; index$sample$48 < noSamples; index$sample$48 += 1) {
																							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$48][0]; timeStep$var49 += 1) {
																								if(true) {
																									// Enumerating the possible outputs of Categorical 56.
																									for(int index$sample63$50 = 0; index$sample63$50 < noStates; index$sample63$50 += 1) {
																										int distributionTempVariable$var57$52 = index$sample63$50;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample63Value51 = (cv$probabilitySample50Value7 * distribution$sample63[((index$sample$48 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$50]);
																										int traceTempVariable$currentState$53_1 = distributionTempVariable$var57$52;
																										if((index$sample$48 == sample$var120)) {
																											if((timeStep$var49 == timeStep$var140)) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var96 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var100 == traceTempVariable$currentState$53_1)) {
																																		{
																																			{
																																				double cv$temp$14$var152;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var152 = traceTempVariable$var152$11_1;
																																					cv$temp$14$var152 = var152;
																																				}
																																				double cv$temp$15$var154;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$53_1];
																																					cv$temp$15$var154 = var154;
																																				}
																																				
																																				// Record the probability of sample task 173 generating output with current configuration.
																																				if(((Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))));
																																				}
																																				
																																				// Recorded the probability of reaching sample task 173 with the current configuration.
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value51);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if(fixedFlag$sample63) {
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
												if((sample$var32 == sample$var120)) {
													if((timeStep$var49 == timeStep$var140)) {
														double traceTempVariable$var152$22_1 = cv$currentValue;
														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
															if((var82 == server)) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var86 == st[sample$var120][timeStep$var140])) {
																		// Processing sample task 173 of consumer random variable null.
																		{
																			// Set an accumulator to sum the probabilities for each possible configuration of
																			// inputs.
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			
																			// Set an accumulator to record the consumer distributions not seen. Initially set
																			// to 1 as seen values will be deducted from this value.
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																				// the output of Sample task 96.
																				if(fixedFlag$sample50) {
																					for(int index$sample$56_1 = 0; index$sample$56_1 < noSamples; index$sample$56_1 += 1) {
																						if((index$sample$56_1 == sample$var120)) {
																							if((0 == timeStep$var140)) {
																								for(int var100 = 0; var100 < noStates; var100 += 1) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var96 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var100 == st[sample$var120][timeStep$var140])) {
																														{
																															{
																																double cv$temp$16$var152;
																																{
																																	// Constructing a random variable input for use later.
																																	double var152 = traceTempVariable$var152$22_1;
																																	cv$temp$16$var152 = var152;
																																}
																																double cv$temp$17$var154;
																																{
																																	// Constructing a random variable input for use later.
																																	double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$17$var154 = var154;
																																}
																																
																																// Record the probability of sample task 173 generating output with current configuration.
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))));
																																}
																																
																																// Recorded the probability of reaching sample task 173 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int index$sample$57 = 0; index$sample$57 < noSamples; index$sample$57 += 1) {
																						if(true) {
																							// Enumerating the possible outputs of Categorical 43.
																							for(int index$sample50$58 = 0; index$sample50$58 < noStates; index$sample50$58 += 1) {
																								int distributionTempVariable$var44$60 = index$sample50$58;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample50Value59 = (1.0 * distribution$sample50[((index$sample$57 - 0) / 1)][index$sample50$58]);
																								int traceTempVariable$currentState$61_1 = distributionTempVariable$var44$60;
																								if((index$sample$57 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var96 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var100 == traceTempVariable$currentState$61_1)) {
																																{
																																	{
																																		double cv$temp$18$var152;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var152 = traceTempVariable$var152$22_1;
																																			cv$temp$18$var152 = var152;
																																		}
																																		double cv$temp$19$var154;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$61_1];
																																			cv$temp$19$var154 = var154;
																																		}
																																		
																																		// Record the probability of sample task 173 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 173 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value59);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				
																				// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																				// the output of Sample task 96.
																				for(int index$sample$64_1 = 0; index$sample$64_1 < noSamples; index$sample$64_1 += 1) {
																					for(int index$timeStep$64_2 = 1; index$timeStep$64_2 < length$metric[index$sample$64_1][0]; index$timeStep$64_2 += 1) {
																						if((index$sample$64_1 == sample$var120)) {
																							if((index$timeStep$64_2 == timeStep$var140)) {
																								for(int var100 = 0; var100 < noStates; var100 += 1) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var96 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var100 == st[sample$var120][timeStep$var140])) {
																														{
																															{
																																double cv$temp$20$var152;
																																{
																																	// Constructing a random variable input for use later.
																																	double var152 = traceTempVariable$var152$22_1;
																																	cv$temp$20$var152 = var152;
																																}
																																double cv$temp$21$var154;
																																{
																																	// Constructing a random variable input for use later.
																																	double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$21$var154 = var154;
																																}
																																
																																// Record the probability of sample task 173 generating output with current configuration.
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))));
																																}
																																
																																// Recorded the probability of reaching sample task 173 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
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
															}
														}
													}
												}
											}
										}
									} else {
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
												if(true) {
													// Enumerating the possible outputs of Categorical 56.
													for(int index$sample63$18 = 0; index$sample63$18 < noStates; index$sample63$18 += 1) {
														int distributionTempVariable$var57$20 = index$sample63$18;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample63Value19 = (1.0 * distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$18]);
														int traceTempVariable$currentState$21_1 = distributionTempVariable$var57$20;
														if((sample$var32 == sample$var120)) {
															if((timeStep$var49 == timeStep$var140)) {
																double traceTempVariable$var152$23_1 = cv$currentValue;
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var82 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var86 == traceTempVariable$currentState$21_1)) {
																				// Processing sample task 173 of consumer random variable null.
																				{
																					// Set an accumulator to sum the probabilities for each possible configuration of
																					// inputs.
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					
																					// Set an accumulator to record the consumer distributions not seen. Initially set
																					// to 1 as seen values will be deducted from this value.
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																						// the output of Sample task 96.
																						if(fixedFlag$sample50) {
																							for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
																								if((index$sample$66_1 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var96 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var100 == traceTempVariable$currentState$21_1)) {
																																{
																																	{
																																		double cv$temp$22$var152;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var152 = traceTempVariable$var152$23_1;
																																			cv$temp$22$var152 = var152;
																																		}
																																		double cv$temp$23$var154;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$21_1];
																																			cv$temp$23$var154 = var154;
																																		}
																																		
																																		// Record the probability of sample task 173 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 173 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value19);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						} else {
																							for(int index$sample$67 = 0; index$sample$67 < noSamples; index$sample$67 += 1) {
																								if(true) {
																									// Enumerating the possible outputs of Categorical 43.
																									for(int index$sample50$68 = 0; index$sample50$68 < noStates; index$sample50$68 += 1) {
																										int distributionTempVariable$var44$70 = index$sample50$68;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample50Value69 = (cv$probabilitySample63Value19 * distribution$sample50[((index$sample$67 - 0) / 1)][index$sample50$68]);
																										int traceTempVariable$currentState$71_1 = distributionTempVariable$var44$70;
																										if((index$sample$67 == sample$var120)) {
																											if((0 == timeStep$var140)) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var96 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var100 == traceTempVariable$currentState$71_1)) {
																																		{
																																			{
																																				double cv$temp$24$var152;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var152 = traceTempVariable$var152$23_1;
																																					cv$temp$24$var152 = var152;
																																				}
																																				double cv$temp$25$var154;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$71_1];
																																					cv$temp$25$var154 = var154;
																																				}
																																				
																																				// Record the probability of sample task 173 generating output with current configuration.
																																				if(((Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))));
																																				}
																																				
																																				// Recorded the probability of reaching sample task 173 with the current configuration.
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value69);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						
																						// Enumerating the possible arguments for the variable Gaussian 155 which is consuming
																						// the output of Sample task 96.
																						int traceTempVariable$currentState$74_1 = distributionTempVariable$var57$20;
																						if((sample$var32 == sample$var120)) {
																							if((timeStep$var49 == timeStep$var140)) {
																								for(int var100 = 0; var100 < noStates; var100 += 1) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var96 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var100 == traceTempVariable$currentState$74_1)) {
																														{
																															{
																																double cv$temp$26$var152;
																																{
																																	// Constructing a random variable input for use later.
																																	double var152 = traceTempVariable$var152$23_1;
																																	cv$temp$26$var152 = var152;
																																}
																																double cv$temp$27$var154;
																																{
																																	// Constructing a random variable input for use later.
																																	double var154 = current_metric_var[server][traceTempVariable$currentState$74_1];
																																	cv$temp$27$var154 = var154;
																																}
																																
																																// Record the probability of sample task 173 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))));
																																}
																																
																																// Recorded the probability of reaching sample task 173 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value19);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$sample$75 = 0; index$sample$75 < noSamples; index$sample$75 += 1) {
																							for(int index$timeStep$76 = 1; index$timeStep$76 < length$metric[index$sample$75][0]; index$timeStep$76 += 1) {
																								if(!((index$sample$75 == sample$var32) && (index$timeStep$76 == timeStep$var49))) {
																									// Enumerating the possible outputs of Categorical 56.
																									for(int index$sample63$77 = 0; index$sample63$77 < noStates; index$sample63$77 += 1) {
																										int distributionTempVariable$var57$79 = index$sample63$77;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample63Value78 = (cv$probabilitySample63Value19 * distribution$sample63[((index$sample$75 - 0) / 1)][((index$timeStep$76 - 1) / 1)][index$sample63$77]);
																										int traceTempVariable$currentState$80_1 = distributionTempVariable$var57$79;
																										if((index$sample$75 == sample$var120)) {
																											if((index$timeStep$76 == timeStep$var140)) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var96 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var100 == traceTempVariable$currentState$80_1)) {
																																		{
																																			{
																																				double cv$temp$28$var152;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var152 = traceTempVariable$var152$23_1;
																																					cv$temp$28$var152 = var152;
																																				}
																																				double cv$temp$29$var154;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$80_1];
																																					cv$temp$29$var154 = var154;
																																				}
																																				
																																				// Record the probability of sample task 173 generating output with current configuration.
																																				if(((Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))));
																																				}
																																				
																																				// Recorded the probability of reaching sample task 173 with the current configuration.
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value78);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
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
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
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
			
			// Save the probability of the original value.
			if((cv$valuePos == 0))
				cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			
			// Save the probability of the proposed value.
			else
				cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// The probability ration for the proposed value and the current value.
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			double var87 = cv$originalValue;
			double[] var83 = current_metric_mean[var82];
			var83[var86] = var87;
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var21$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			cv$max = Math.max(cv$max, noStates);
			
			// Allocation of cv$var21$countGlobal for single threaded execution
			cv$var21$countGlobal = new double[cv$max];
		}
		
		// Constructor for cv$var27$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			for(int var26 = 0; var26 < noStates; var26 += 1)
				cv$max = Math.max(cv$max, noStates);
			
			// Allocation of cv$var27$countGlobal for single threaded execution
			cv$var27$countGlobal = new double[cv$max];
		}
		
		// Constructor for cv$distributionAccumulator$var56
		{
			// Variable to record the maximum value of Task Get 61. Initially set to the value
			// of putTask 31.
			int cv$var28$max = noStates;
			
			// Allocation of cv$distributionAccumulator$var56 for single threaded execution
			cv$distributionAccumulator$var56 = new double[cv$var28$max];
		}
		
		// Constructor for cv$var44$stateProbabilityGlobal
		{
			// Allocation of cv$var44$stateProbabilityGlobal for single threaded execution
			cv$var44$stateProbabilityGlobal = new double[noStates];
		}
		
		// Constructor for guard$sample50gaussian172$global
		{
			// Calculate the largest index of sample that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_sample$var120 = 0;
			
			// Calculate the largest index of server that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_server = 0;
			
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var140 = 0;
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					cv$max_timeStep$var140 = Math.max(cv$max_timeStep$var140, ((length$metric[sample$var120][0] - 0) / 1));
				cv$max_server = Math.max(cv$max_server, ((length$metric[0].length - 0) / 1));
			}
			cv$max_sample$var120 = Math.max(cv$max_sample$var120, ((length$metric.length - 0) / 1));
			
			// Allocation of guard$sample50gaussian172$global for single threaded execution
			guard$sample50gaussian172$global = new boolean[cv$max_sample$var120][cv$max_server][cv$max_timeStep$var140];
		}
		
		// Constructor for cv$var57$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 61. Initially set to the value
			// of putTask 31.
			int cv$var28$max = noStates;
			
			// Allocation of cv$var57$stateProbabilityGlobal for single threaded execution
			cv$var57$stateProbabilityGlobal = new double[cv$var28$max];
		}
		
		// Constructor for guard$sample63gaussian172$global
		{
			// Calculate the largest index of sample that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_sample$var120 = 0;
			
			// Calculate the largest index of server that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_server = 0;
			
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var140 = 0;
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					cv$max_timeStep$var140 = Math.max(cv$max_timeStep$var140, ((length$metric[sample$var120][0] - 0) / 1));
				cv$max_server = Math.max(cv$max_server, ((length$metric[0].length - 0) / 1));
			}
			cv$max_sample$var120 = Math.max(cv$max_sample$var120, ((length$metric.length - 0) / 1));
			
			// Allocation of guard$sample63gaussian172$global for single threaded execution
			guard$sample63gaussian172$global = new boolean[cv$max_sample$var120][cv$max_server][cv$max_timeStep$var140];
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		{
			v = new double[noStates];
		}
		
		// If initialStateDistribution has not been set already allocate space.
		if(!setFlag$initialStateDistribution) {
			// Constructor for initialStateDistribution
			{
				initialStateDistribution = new double[noStates];
			}
		}
		
		// If m has not been set already allocate space.
		if(!setFlag$m) {
			// Constructor for m
			{
				m = new double[noStates][];
				for(int var26 = 0; var26 < noStates; var26 += 1)
					m[var26] = new double[noStates];
			}
		}
		
		// If st has not been set already allocate space.
		if(!setFlag$st) {
			// Constructor for st
			{
				st = new int[length$metric.length][];
				for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
					st[sample$var32] = new int[length$metric[sample$var32][0]];
			}
		}
		
		// If metric_g has not been set already allocate space.
		if(!setFlag$metric_g) {
			// Constructor for metric_g
			{
				metric_g = new double[length$metric.length][][];
				for(int var67 = 0; var67 < length$metric.length; var67 += 1) {
					double[][] subarray$0 = new double[length$metric[0].length][];
					metric_g[var67] = subarray$0;
				}
				for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
					for(int server = 0; server < length$metric[0].length; server += 1) {
						double[][] subarray$1 = metric_g[sample$var120];
						subarray$1[server] = new double[length$metric[sample$var120][0]];
					}
				}
			}
		}
		
		// If metric_valid_g has not been set already allocate space.
		if(!setFlag$metric_valid_g) {
			// Constructor for metric_valid_g
			{
				metric_valid_g = new boolean[length$metric.length][][];
				for(int var73 = 0; var73 < length$metric.length; var73 += 1) {
					boolean[][] subarray$0 = new boolean[length$metric[0].length][];
					metric_valid_g[var73] = subarray$0;
				}
				for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
					for(int server = 0; server < length$metric[0].length; server += 1) {
						boolean[][] subarray$1 = metric_valid_g[sample$var120];
						subarray$1[server] = new boolean[length$metric[sample$var120][0]];
					}
				}
			}
		}
		
		// If current_metric_mean has not been set already allocate space.
		if(!setFlag$current_metric_mean) {
			// Constructor for current_metric_mean
			{
				current_metric_mean = new double[length$metric[0].length][];
				for(int var82 = 0; var82 < length$metric[0].length; var82 += 1)
					current_metric_mean[var82] = new double[noStates];
			}
		}
		
		// If current_metric_var has not been set already allocate space.
		if(!setFlag$current_metric_var) {
			// Constructor for current_metric_var
			{
				current_metric_var = new double[length$metric[0].length][];
				for(int var96 = 0; var96 < length$metric[0].length; var96 += 1)
					current_metric_var[var96] = new double[noStates];
			}
		}
		
		// If current_metric_valid_bias has not been set already allocate space.
		if(!setFlag$current_metric_valid_bias) {
			// Constructor for current_metric_valid_bias
			{
				current_metric_valid_bias = new double[length$metric[0].length][];
				for(int var110 = 0; var110 < length$metric[0].length; var110 += 1)
					current_metric_valid_bias[var110] = new double[noStates];
			}
		}
		
		// Constructor for distribution$sample50
		{
			distribution$sample50 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
				distribution$sample50[((sample$var32 - 0) / 1)] = new double[noStates];
		}
		
		// Constructor for distribution$sample63
		{
			distribution$sample63 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1) {
				double[][] subarray$0 = new double[((((length$metric[sample$var32][0] - 1) - 1) / 1) + 1)][];
				distribution$sample63[((sample$var32 - 0) / 1)] = subarray$0;
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
					subarray$0[((timeStep$var49 - 1) / 1)] = new double[noStates];
			}
		}
		
		// Constructor for logProbability$var43
		{
			logProbability$var43 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample50
		{
			logProbability$sample50 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var56
		{
			logProbability$var56 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
				logProbability$var56[((sample$var32 - 0) / 1)] = new double[((((length$metric[sample$var32][0] - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample63
		{
			logProbability$sample63 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
				logProbability$sample63[((sample$var32 - 0) / 1)] = new double[((((length$metric[sample$var32][0] - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$var145
		{
			logProbability$var145 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$var145[((sample$var120 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var120][0] - 1) - 0) / 1) + 1)];
			}
		}
		
		// Constructor for logProbability$sample161
		{
			logProbability$sample161 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$sample161[((sample$var120 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var120][0] - 1) - 0) / 1) + 1)];
			}
		}
		
		// Constructor for logProbability$var155
		{
			logProbability$var155 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$var155[((sample$var120 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var120][0] - 1) - 0) / 1) + 1)];
			}
		}
		
		// Constructor for logProbability$sample173
		{
			logProbability$sample173 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$sample173[((sample$var120 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var120][0] - 1) - 0) / 1) + 1)];
			}
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var26 = 0; var26 < noStates; var26 += 1) {
			double[] var27 = m[var26];
			if(!fixedFlag$sample30)
				DistributionSampling.sampleDirichlet(RNG$, v, var27);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			int[] var41 = st[sample$var32];
			if(!fixedFlag$sample50)
				var41[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var50 = st[sample$var32];
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
				if(!fixedFlag$sample63)
					var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var32][(timeStep$var49 - 1)]]);
			}
		}
		for(int var82 = 0; var82 < noServers; var82 += 1) {
			double[] var83 = current_metric_mean[var82];
			for(int var86 = 0; var86 < noStates; var86 += 1) {
				if(!fixedFlag$sample96)
					var83[var86] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var96 = 0; var96 < noServers; var96 += 1) {
			double[] var97 = current_metric_var[var96];
			for(int var100 = 0; var100 < noStates; var100 += 1) {
				if(!fixedFlag$sample111)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var110 = 0; var110 < noServers; var110 += 1) {
			double[] var111 = current_metric_valid_bias[var110];
			for(int var114 = 0; var114 < noStates; var114 += 1) {
				if(!fixedFlag$sample126)
					var111[var114] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			boolean[][] var135 = metric_valid_g[sample$var120];
			double[][] var131 = metric_g[sample$var120];
			for(int server = 0; server < noServers; server += 1) {
				boolean[] metric_valid_inner = metric_valid_g[sample$var120][server];
				double[] metric_inner = var131[server];
				for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
					if(!fixedFlag$sample161)
						metric_valid_inner[timeStep$var140] = DistributionSampling.sampleBernoulli(RNG$, current_metric_valid_bias[server][st[sample$var120][timeStep$var140]]);
					if(var135[server][timeStep$var140]) {
						if(!fixedFlag$sample173)
							metric_inner[timeStep$var140] = ((Math.sqrt(current_metric_var[server][st[sample$var120][timeStep$var140]]) * DistributionSampling.sampleGaussian(RNG$)) + current_metric_mean[server][st[sample$var120][timeStep$var140]]);
					}
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
		for(int var26 = 0; var26 < noStates; var26 += 1) {
			double[] var27 = m[var26];
			if(!fixedFlag$sample30)
				DistributionSampling.sampleDirichlet(RNG$, v, var27);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			// Create local copy of variable probabilities.
			double[] cv$distribution$sample50 = distribution$sample50[((sample$var32 - 0) / 1)];
			for(int index$var43 = 0; index$var43 < noStates; index$var43 += 1) {
				// Probability for this value
				double cv$value = (((0.0 <= index$var43) && (index$var43 < initialStateDistribution.length))?initialStateDistribution[index$var43]:0.0);
				if(!fixedFlag$sample50)
					// Save the probability of each value
					cv$distribution$sample50[index$var43] = cv$value;
			}
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample63 = distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)];
				for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1) {
					if(!fixedFlag$sample63)
						// Zero the probability of each value
						cv$distribution$sample63[index$var56] = 0.0;
				}
				
				// Iterate through possible values for var56's arguments.
				// 
				// Enumerating the possible arguments for Categorical 56.
				if(fixedFlag$sample50) {
					for(int index$sample$1_1 = 0; index$sample$1_1 < noSamples; index$sample$1_1 += 1) {
						if((index$sample$1_1 == sample$var32)) {
							if((0 == (timeStep$var49 - 1))) {
								for(int var26 = 0; var26 < noStates; var26 += 1) {
									if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
										{
											if(!fixedFlag$sample63) {
												double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
												for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
													// Save the probability of each value
													cv$distribution$sample63[index$var56] = (cv$distribution$sample63[index$var56] + (1.0 * (((0.0 <= index$var56) && (index$var56 < var55.length))?var55[index$var56]:0.0)));
											}
										}
									}
								}
							}
						}
					}
				} else {
					for(int index$sample$2 = 0; index$sample$2 < noSamples; index$sample$2 += 1) {
						if(true) {
							// Enumerating the possible outputs of Categorical 43.
							for(int index$sample50$3 = 0; index$sample50$3 < noStates; index$sample50$3 += 1) {
								int distributionTempVariable$var44$5 = index$sample50$3;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample50Value4 = (1.0 * distribution$sample50[((index$sample$2 - 0) / 1)][index$sample50$3]);
								int traceTempVariable$var54$6_1 = distributionTempVariable$var44$5;
								if((index$sample$2 == sample$var32)) {
									if((0 == (timeStep$var49 - 1))) {
										for(int var26 = 0; var26 < noStates; var26 += 1) {
											if((var26 == traceTempVariable$var54$6_1)) {
												{
													if(!fixedFlag$sample63) {
														double[] var55 = m[traceTempVariable$var54$6_1];
														for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
															// Save the probability of each value
															cv$distribution$sample63[index$var56] = (cv$distribution$sample63[index$var56] + (cv$probabilitySample50Value4 * (((0.0 <= index$var56) && (index$var56 < var55.length))?var55[index$var56]:0.0)));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Categorical 56.
				if(fixedFlag$sample63) {
					for(int index$sample$9_1 = 0; index$sample$9_1 < noSamples; index$sample$9_1 += 1) {
						for(int index$timeStep$9_2 = 1; index$timeStep$9_2 < length$metric[index$sample$9_1][0]; index$timeStep$9_2 += 1) {
							if((index$sample$9_1 == sample$var32)) {
								if((index$timeStep$9_2 == (timeStep$var49 - 1))) {
									for(int var26 = 0; var26 < noStates; var26 += 1) {
										if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
											{
												if(!fixedFlag$sample63) {
													double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
													for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
														// Save the probability of each value
														cv$distribution$sample63[index$var56] = (cv$distribution$sample63[index$var56] + (1.0 * (((0.0 <= index$var56) && (index$var56 < var55.length))?var55[index$var56]:0.0)));
												}
											}
										}
									}
								}
							}
						}
					}
				} else {
					for(int index$sample$10 = 0; index$sample$10 < noSamples; index$sample$10 += 1) {
						for(int index$timeStep$11 = 1; index$timeStep$11 < length$metric[index$sample$10][0]; index$timeStep$11 += 1) {
							if(true) {
								// Enumerating the possible outputs of Categorical 56.
								for(int index$sample63$12 = 0; index$sample63$12 < noStates; index$sample63$12 += 1) {
									int distributionTempVariable$var57$14 = index$sample63$12;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample63Value13 = (1.0 * distribution$sample63[((index$sample$10 - 0) / 1)][((index$timeStep$11 - 1) / 1)][index$sample63$12]);
									int traceTempVariable$var54$15_1 = distributionTempVariable$var57$14;
									if((index$sample$10 == sample$var32)) {
										if((index$timeStep$11 == (timeStep$var49 - 1))) {
											for(int var26 = 0; var26 < noStates; var26 += 1) {
												if((var26 == traceTempVariable$var54$15_1)) {
													{
														if(!fixedFlag$sample63) {
															double[] var55 = m[traceTempVariable$var54$15_1];
															for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
																// Save the probability of each value
																cv$distribution$sample63[index$var56] = (cv$distribution$sample63[index$var56] + (cv$probabilitySample63Value13 * (((0.0 <= index$var56) && (index$var56 < var55.length))?var55[index$var56]:0.0)));
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				
				// Sum the values in the array
				double cv$var56$sum = 0.0;
				for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1) {
					if(!fixedFlag$sample63)
						// sum the probability of each value
						cv$var56$sum = (cv$var56$sum + cv$distribution$sample63[index$var56]);
				}
				for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1) {
					if(!fixedFlag$sample63)
						// Normalise the probability of each value
						cv$distribution$sample63[index$var56] = (cv$distribution$sample63[index$var56] / cv$var56$sum);
				}
			}
		}
		for(int var82 = 0; var82 < noServers; var82 += 1) {
			double[] var83 = current_metric_mean[var82];
			for(int var86 = 0; var86 < noStates; var86 += 1) {
				if(!fixedFlag$sample96)
					var83[var86] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var96 = 0; var96 < noServers; var96 += 1) {
			double[] var97 = current_metric_var[var96];
			for(int var100 = 0; var100 < noStates; var100 += 1) {
				if(!fixedFlag$sample111)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var110 = 0; var110 < noServers; var110 += 1) {
			double[] var111 = current_metric_valid_bias[var110];
			for(int var114 = 0; var114 < noStates; var114 += 1) {
				if(!fixedFlag$sample126)
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
		for(int var26 = 0; var26 < noStates; var26 += 1) {
			double[] var27 = m[var26];
			if(!fixedFlag$sample30)
				DistributionSampling.sampleDirichlet(RNG$, v, var27);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			int[] var41 = st[sample$var32];
			if(!fixedFlag$sample50)
				var41[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var50 = st[sample$var32];
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
				if(!fixedFlag$sample63)
					var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var32][(timeStep$var49 - 1)]]);
			}
		}
		for(int var82 = 0; var82 < noServers; var82 += 1) {
			double[] var83 = current_metric_mean[var82];
			for(int var86 = 0; var86 < noStates; var86 += 1) {
				if(!fixedFlag$sample96)
					var83[var86] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var96 = 0; var96 < noServers; var96 += 1) {
			double[] var97 = current_metric_var[var96];
			for(int var100 = 0; var100 < noStates; var100 += 1) {
				if(!fixedFlag$sample111)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var110 = 0; var110 < noServers; var110 += 1) {
			double[] var111 = current_metric_valid_bias[var110];
			for(int var114 = 0; var114 < noStates; var114 += 1) {
				if(!fixedFlag$sample126)
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
			for(int var26 = 0; var26 < noStates; var26 += 1) {
				if(!fixedFlag$sample30)
					sample30(var26);
			}
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				if(!fixedFlag$sample50)
					sample50(sample$var32);
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					if(!fixedFlag$sample63)
						sample63(sample$var32, timeStep$var49);
				}
			}
			for(int var82 = 0; var82 < noServers; var82 += 1) {
				for(int var86 = 0; var86 < noStates; var86 += 1) {
					if(!fixedFlag$sample96)
						sample96(var82, var86);
				}
			}
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				for(int var100 = 0; var100 < noStates; var100 += 1) {
					if(!fixedFlag$sample111)
						sample111(var96, var100);
				}
			}
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				for(int var114 = 0; var114 < noStates; var114 += 1) {
					if(!fixedFlag$sample126)
						sample126(var110, var114);
				}
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int var110 = (noServers - ((((noServers - 1) - 0) % 1) + 1)); var110 >= ((0 - 1) + 1); var110 -= 1) {
				for(int var114 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var114 >= ((0 - 1) + 1); var114 -= 1) {
					if(!fixedFlag$sample126)
						sample126(var110, var114);
				}
			}
			for(int var96 = (noServers - ((((noServers - 1) - 0) % 1) + 1)); var96 >= ((0 - 1) + 1); var96 -= 1) {
				for(int var100 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var100 >= ((0 - 1) + 1); var100 -= 1) {
					if(!fixedFlag$sample111)
						sample111(var96, var100);
				}
			}
			for(int var82 = (noServers - ((((noServers - 1) - 0) % 1) + 1)); var82 >= ((0 - 1) + 1); var82 -= 1) {
				for(int var86 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var86 >= ((0 - 1) + 1); var86 -= 1) {
					if(!fixedFlag$sample96)
						sample96(var82, var86);
				}
			}
			for(int sample$var32 = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample$var32 >= ((0 - 1) + 1); sample$var32 -= 1) {
				for(int timeStep$var49 = (length$metric[sample$var32][0] - ((((length$metric[sample$var32][0] - 1) - 1) % 1) + 1)); timeStep$var49 >= ((1 - 1) + 1); timeStep$var49 -= 1) {
					if(!fixedFlag$sample63)
						sample63(sample$var32, timeStep$var49);
				}
				if(!fixedFlag$sample50)
					sample50(sample$var32);
			}
			for(int var26 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var26 >= ((0 - 1) + 1); var26 -= 1) {
				if(!fixedFlag$sample30)
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
			logProbability$var43[((sample$var32 - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample50) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1)
				logProbability$sample50[((sample$var32 - 0) / 1)] = 0.0;
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
				logProbability$var56[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample63) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
					logProbability$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = 0.0;
			}
		}
		logProbability$var78 = 0.0;
		logProbability$current_metric_mean = 0.0;
		if(!fixedProbFlag$sample96)
			logProbability$var87 = 0.0;
		logProbability$var92 = 0.0;
		logProbability$current_metric_var = 0.0;
		if(!fixedProbFlag$sample111)
			logProbability$var101 = 0.0;
		logProbability$var106 = 0.0;
		logProbability$current_metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample126)
			logProbability$var115 = 0.0;
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
					logProbability$var145[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = 0.0;
			}
		}
		logProbability$metric_valid_inner = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample161) {
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
						logProbability$sample161[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = 0.0;
				}
			}
		}
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
					logProbability$var155[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = 0.0;
			}
		}
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample173) {
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
						logProbability$sample173[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample96)
			logProbabilityValue$sample96();
		if(fixedFlag$sample111)
			logProbabilityValue$sample111();
		if(fixedFlag$sample126)
			logProbabilityValue$sample126();
		logProbabilityValue$sample161();
		logProbabilityValue$sample173();
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
		logProbabilityDistribution$sample50();
		logProbabilityDistribution$sample63();
		logProbabilityValue$sample96();
		logProbabilityValue$sample111();
		logProbabilityValue$sample126();
		logProbabilityDistribution$sample161();
		logProbabilityDistribution$sample173();
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
		logProbabilityValue$sample50();
		logProbabilityValue$sample63();
		logProbabilityValue$sample96();
		logProbabilityValue$sample111();
		logProbabilityValue$sample126();
		logProbabilityValue$sample161();
		logProbabilityValue$sample173();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var26 = 0; var26 < noStates; var26 += 1) {
			double[] var27 = m[var26];
			if(!fixedFlag$sample30)
				DistributionSampling.sampleDirichlet(RNG$, v, var27);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			int[] var41 = st[sample$var32];
			if(!fixedFlag$sample50)
				var41[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var50 = st[sample$var32];
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
				if(!fixedFlag$sample63)
					var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var32][(timeStep$var49 - 1)]]);
			}
		}
		for(int var82 = 0; var82 < noServers; var82 += 1) {
			double[] var83 = current_metric_mean[var82];
			for(int var86 = 0; var86 < noStates; var86 += 1) {
				if(!fixedFlag$sample96)
					var83[var86] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var96 = 0; var96 < noServers; var96 += 1) {
			double[] var97 = current_metric_var[var96];
			for(int var100 = 0; var100 < noStates; var100 += 1) {
				if(!fixedFlag$sample111)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var110 = 0; var110 < noServers; var110 += 1) {
			double[] var111 = current_metric_valid_bias[var110];
			for(int var114 = 0; var114 < noStates; var114 += 1) {
				if(!fixedFlag$sample126)
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
		{
			// Deep copy between arrays
			boolean[][][] cv$source1 = metric_valid;
			boolean[][][] cv$target1 = metric_valid_g;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				boolean[][] cv$source2 = cv$source1[cv$index1];
				boolean[][] cv$target2 = cv$target1[cv$index1];
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
		{
			// Deep copy between arrays
			double[][][] cv$source1 = metric;
			double[][][] cv$target1 = metric_g;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				double[][] cv$source2 = cv$source1[cv$index1];
				double[][] cv$target2 = cv$target1[cv$index1];
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