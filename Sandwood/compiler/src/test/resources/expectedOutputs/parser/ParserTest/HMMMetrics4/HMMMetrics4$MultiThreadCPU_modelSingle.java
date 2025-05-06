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
	private double[][] cv$distributionAccumulator$var73;
	private double[] cv$var20$countGlobal;
	private double[][] cv$var33$countGlobal;
	private double[][] cv$var55$stateProbabilityGlobal;
	private double[][] cv$var74$stateProbabilityGlobal;
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
	private boolean[][][][] guard$sample57gaussian255$global;
	private boolean[][][][] guard$sample76gaussian255$global;
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
		fixedProbFlag$sample134 = (fixedFlag$sample134 && fixedProbFlag$sample134);
		
		// Should the probability of sample 256 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample256 = (fixedFlag$sample134 && fixedProbFlag$sample256);
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
		fixedProbFlag$sample162 = (fixedFlag$sample162 && fixedProbFlag$sample162);
		
		// Should the probability of sample 256 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample256 = (fixedFlag$sample162 && fixedProbFlag$sample256);
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
		fixedProbFlag$sample190 = (fixedFlag$sample190 && fixedProbFlag$sample190);
		
		// Should the probability of sample 241 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample241 = (fixedFlag$sample190 && fixedProbFlag$sample241);
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
		fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedProbFlag$sample20);
		
		// Should the probability of sample 57 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample57 = (fixedFlag$sample20 && fixedProbFlag$sample57);
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
		fixedProbFlag$sample33 = (fixedFlag$sample33 && fixedProbFlag$sample33);
		
		// Should the probability of sample 76 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample76 = (fixedFlag$sample33 && fixedProbFlag$sample76);
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
		fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedProbFlag$sample57);
		
		// Should the probability of sample 76 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample76 = (fixedFlag$sample57 && fixedProbFlag$sample76);
		
		// Should the probability of sample 241 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample241 = (fixedFlag$sample57 && fixedProbFlag$sample241);
		
		// Should the probability of sample 256 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample256 = (fixedFlag$sample57 && fixedProbFlag$sample256);
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
		fixedProbFlag$sample76 = (fixedFlag$sample76 && fixedProbFlag$sample76);
		
		// Should the probability of sample 241 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample241 = (fixedFlag$sample76 && fixedProbFlag$sample241);
		
		// Should the probability of sample 256 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample256 = (fixedFlag$sample76 && fixedProbFlag$sample256);
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
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 241 including any distribution
						// values.
						{
							// The sample value to calculate the probability of generating
							boolean cv$sampleValue = metric_valid_g[sample$var196][server][timeStep$var226];
							
							// Enumerating the possible arguments for Bernoulli 231.
							if(fixedFlag$sample57) {
								for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
									if((sample$var45 == sample$var196)) {
										if((0 == timeStep$var226)) {
											for(int var173 = 0; var173 < noServers; var173 += 1) {
												for(int var183 = 0; var183 < noStates; var183 += 1) {
													if((var173 == server)) {
														if((var183 == st[sample$var196][timeStep$var226])) {
															{
																double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
																
																// Store the value of the function call, so the function call is only made once.
																double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var230:(1.0 - var230))));
																
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
								for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 54.
										for(int index$sample57$4 = 0; index$sample57$4 < noStates; index$sample57$4 += 1) {
											int distributionTempVariable$var55$6 = index$sample57$4;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample57Value5 = (1.0 * distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$4]);
											if((sample$var45 == sample$var196)) {
												if((0 == timeStep$var226)) {
													for(int var173 = 0; var173 < noServers; var173 += 1) {
														for(int var183 = 0; var183 < noStates; var183 += 1) {
															if((var173 == server)) {
																if((var183 == st[sample$var196][timeStep$var226])) {
																	{
																		double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
																		
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
													}
												}
											}
										}
									}
								}
							}
							
							// Enumerating the possible arguments for Bernoulli 231.
							if(fixedFlag$sample76) {
								for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
									for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
										if((sample$var45 == sample$var196)) {
											if((timeStep$var66 == timeStep$var226)) {
												for(int var173 = 0; var173 < noServers; var173 += 1) {
													for(int var183 = 0; var183 < noStates; var183 += 1) {
														if((var173 == server)) {
															if((var183 == st[sample$var196][timeStep$var226])) {
																{
																	double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
																	
																	// Store the value of the function call, so the function call is only made once.
																	double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var230:(1.0 - var230))));
																	
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
								for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
									for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 73.
											for(int index$sample76$13 = 0; index$sample76$13 < noStates; index$sample76$13 += 1) {
												int distributionTempVariable$var74$15 = index$sample76$13;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample76Value14 = (1.0 * distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$13]);
												if((sample$var45 == sample$var196)) {
													if((timeStep$var66 == timeStep$var226)) {
														for(int var173 = 0; var173 < noServers; var173 += 1) {
															for(int var183 = 0; var183 < noStates; var183 += 1) {
																if((var173 == server)) {
																	if((var183 == st[sample$var196][timeStep$var226])) {
																		{
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
						
						// Record that the sample was reached.
						cv$sampleReached = true;
						
						// Add the probability of this sample task to the sample task accumulator.
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			if(cv$sampleReached)
				logProbability$var231 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var232 = cv$accumulator;
			
			// Guard to ensure that metric_valid_g is only updated once for this probability.
			boolean cv$guard$metric_valid_g = false;
			
			// Update the variable probability
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$metric_valid_g) {
					// Set the guard so the update is only applied once.
					cv$guard$metric_valid_g = true;
					
					// Update the variable probability
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample241 = ((fixedFlag$sample57 && fixedFlag$sample76) && fixedFlag$sample190);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1)
						// Record that the sample was reached.
						cv$sampleReached = true;
				}
			}
			double cv$sampleValue = logProbability$var232;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var231 = cv$rvAccumulator;
			
			// Guard to ensure that metric_valid_g is only updated once for this probability.
			boolean cv$guard$metric_valid_g = false;
			
			// Update the variable probability
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$metric_valid_g) {
					// Set the guard so the update is only applied once.
					cv$guard$metric_valid_g = true;
					
					// Update the variable probability
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample256 using probability
	// distributions.
	private final void logProbabilityDistribution$sample256() {
		// Determine if we need to calculate the values for sample task 256 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample256) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
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
							{
								// The sample value to calculate the probability of generating
								double cv$sampleValue = var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
								
								// Enumerating the possible arguments for Gaussian 244.
								if(fixedFlag$sample57) {
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										if((sample$var45 == sample$var196)) {
											if((0 == timeStep$var226)) {
												for(int var119 = 0; var119 < noServers; var119 += 1) {
													for(int var129 = 0; var129 < noStates; var129 += 1) {
														if((var119 == server)) {
															if((var129 == st[sample$var196][timeStep$var226])) {
																for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
																	if((index$sample$10_1 == sample$var196)) {
																		if((0 == timeStep$var226)) {
																			for(int var146 = 0; var146 < noServers; var146 += 1) {
																				for(int var156 = 0; var156 < noStates; var156 += 1) {
																					if((var146 == server)) {
																						if((var156 == st[sample$var196][timeStep$var226])) {
																							{
																								double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																								double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																								
																								// Store the value of the function call, so the function call is only made once.
																								double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																								
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
								} else {
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 54.
											for(int index$sample57$4 = 0; index$sample57$4 < noStates; index$sample57$4 += 1) {
												int distributionTempVariable$var55$6 = index$sample57$4;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample57Value5 = (1.0 * distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$4]);
												if((sample$var45 == sample$var196)) {
													if((0 == timeStep$var226)) {
														for(int var119 = 0; var119 < noServers; var119 += 1) {
															for(int var129 = 0; var129 < noStates; var129 += 1) {
																if((var119 == server)) {
																	if((var129 == st[sample$var196][timeStep$var226])) {
																		if((sample$var45 == sample$var196)) {
																			if((0 == timeStep$var226)) {
																				for(int var146 = 0; var146 < noServers; var146 += 1) {
																					for(int var156 = 0; var156 < noStates; var156 += 1) {
																						if((var146 == server)) {
																							if((var156 == st[sample$var196][timeStep$var226])) {
																								{
																									double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																									double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																									
																									// Store the value of the function call, so the function call is only made once.
																									double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																									
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
																				}
																			}
																		}
																		for(int index$sample$12 = 0; index$sample$12 < noSamples; index$sample$12 += 1) {
																			if(!(index$sample$12 == sample$var45)) {
																				// Enumerating the possible outputs of Categorical 54.
																				for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
																					int distributionTempVariable$var55$15 = index$sample57$13;
																					
																					// Update the probability of sampling this value from the distribution value.
																					double cv$probabilitySample57Value14 = (cv$probabilitySample57Value5 * distribution$sample57[((index$sample$12 - 0) / 1)][index$sample57$13]);
																					if((index$sample$12 == sample$var196)) {
																						if((0 == timeStep$var226)) {
																							for(int var146 = 0; var146 < noServers; var146 += 1) {
																								for(int var156 = 0; var156 < noStates; var156 += 1) {
																									if((var146 == server)) {
																										if((var156 == st[sample$var196][timeStep$var226])) {
																											{
																												double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																												double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																												
																												// Store the value of the function call, so the function call is only made once.
																												double cv$weightedProbability = (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																												
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
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value14);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								
								// Enumerating the possible arguments for Gaussian 244.
								if(fixedFlag$sample57) {
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										if((sample$var45 == sample$var196)) {
											if((0 == timeStep$var226)) {
												for(int var119 = 0; var119 < noServers; var119 += 1) {
													for(int var129 = 0; var129 < noStates; var129 += 1) {
														if((var119 == server)) {
															if((var129 == st[sample$var196][timeStep$var226])) {
																if(fixedFlag$sample76) {
																	for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																		for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$28_1][0]; timeStep$var66 += 1) {
																			if((index$sample$28_1 == sample$var196)) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int var146 = 0; var146 < noServers; var146 += 1) {
																						for(int var156 = 0; var156 < noStates; var156 += 1) {
																							if((var146 == server)) {
																								if((var156 == st[sample$var196][timeStep$var226])) {
																									{
																										double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																										double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																										
																										// Store the value of the function call, so the function call is only made once.
																										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																										
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
																	for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																		for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$29][0]; timeStep$var66 += 1) {
																			if(true) {
																				// Enumerating the possible outputs of Categorical 73.
																				for(int index$sample76$31 = 0; index$sample76$31 < noStates; index$sample76$31 += 1) {
																					int distributionTempVariable$var74$33 = index$sample76$31;
																					
																					// Update the probability of sampling this value from the distribution value.
																					double cv$probabilitySample76Value32 = (1.0 * distribution$sample76[((index$sample$29 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$31]);
																					if((index$sample$29 == sample$var196)) {
																						if((timeStep$var66 == timeStep$var226)) {
																							for(int var146 = 0; var146 < noServers; var146 += 1) {
																								for(int var156 = 0; var156 < noStates; var156 += 1) {
																									if((var146 == server)) {
																										if((var156 == st[sample$var196][timeStep$var226])) {
																											{
																												double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																												double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																												
																												// Store the value of the function call, so the function call is only made once.
																												double cv$weightedProbability = (Math.log(cv$probabilitySample76Value32) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																												
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
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value32);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 54.
											for(int index$sample57$22 = 0; index$sample57$22 < noStates; index$sample57$22 += 1) {
												int distributionTempVariable$var55$24 = index$sample57$22;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample57Value23 = (1.0 * distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$22]);
												if((sample$var45 == sample$var196)) {
													if((0 == timeStep$var226)) {
														for(int var119 = 0; var119 < noServers; var119 += 1) {
															for(int var129 = 0; var129 < noStates; var129 += 1) {
																if((var119 == server)) {
																	if((var129 == st[sample$var196][timeStep$var226])) {
																		if(fixedFlag$sample76) {
																			for(int index$sample$35_1 = 0; index$sample$35_1 < noSamples; index$sample$35_1 += 1) {
																				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$35_1][0]; timeStep$var66 += 1) {
																					if((index$sample$35_1 == sample$var196)) {
																						if((timeStep$var66 == timeStep$var226)) {
																							for(int var146 = 0; var146 < noServers; var146 += 1) {
																								for(int var156 = 0; var156 < noStates; var156 += 1) {
																									if((var146 == server)) {
																										if((var156 == st[sample$var196][timeStep$var226])) {
																											{
																												double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																												double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																												
																												// Store the value of the function call, so the function call is only made once.
																												double cv$weightedProbability = (Math.log(cv$probabilitySample57Value23) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																												
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
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value23);
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
																				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$36][0]; timeStep$var66 += 1) {
																					if(true) {
																						// Enumerating the possible outputs of Categorical 73.
																						for(int index$sample76$38 = 0; index$sample76$38 < noStates; index$sample76$38 += 1) {
																							int distributionTempVariable$var74$40 = index$sample76$38;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample76Value39 = (cv$probabilitySample57Value23 * distribution$sample76[((index$sample$36 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$38]);
																							if((index$sample$36 == sample$var196)) {
																								if((timeStep$var66 == timeStep$var226)) {
																									for(int var146 = 0; var146 < noServers; var146 += 1) {
																										for(int var156 = 0; var156 < noStates; var156 += 1) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																														double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																														
																														// Store the value of the function call, so the function call is only made once.
																														double cv$weightedProbability = (Math.log(cv$probabilitySample76Value39) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																														
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
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value39);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								
								// Enumerating the possible arguments for Gaussian 244.
								if(fixedFlag$sample76) {
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
											if((sample$var45 == sample$var196)) {
												if((timeStep$var66 == timeStep$var226)) {
													for(int var119 = 0; var119 < noServers; var119 += 1) {
														for(int var129 = 0; var129 < noStates; var129 += 1) {
															if((var119 == server)) {
																if((var129 == st[sample$var196][timeStep$var226])) {
																	for(int index$sample$55_1 = 0; index$sample$55_1 < noSamples; index$sample$55_1 += 1) {
																		for(int index$timeStep$55_2 = 1; index$timeStep$55_2 < length$metric[index$sample$55_1][0]; index$timeStep$55_2 += 1) {
																			if((index$sample$55_1 == sample$var196)) {
																				if((index$timeStep$55_2 == timeStep$var226)) {
																					for(int var146 = 0; var146 < noServers; var146 += 1) {
																						for(int var156 = 0; var156 < noStates; var156 += 1) {
																							if((var146 == server)) {
																								if((var156 == st[sample$var196][timeStep$var226])) {
																									{
																										double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																										double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																										
																										// Store the value of the function call, so the function call is only made once.
																										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																										
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
								} else {
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 73.
												for(int index$sample76$49 = 0; index$sample76$49 < noStates; index$sample76$49 += 1) {
													int distributionTempVariable$var74$51 = index$sample76$49;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample76Value50 = (1.0 * distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$49]);
													if((sample$var45 == sample$var196)) {
														if((timeStep$var66 == timeStep$var226)) {
															for(int var119 = 0; var119 < noServers; var119 += 1) {
																for(int var129 = 0; var129 < noStates; var129 += 1) {
																	if((var119 == server)) {
																		if((var129 == st[sample$var196][timeStep$var226])) {
																			if((sample$var45 == sample$var196)) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int var146 = 0; var146 < noServers; var146 += 1) {
																						for(int var156 = 0; var156 < noStates; var156 += 1) {
																							if((var146 == server)) {
																								if((var156 == st[sample$var196][timeStep$var226])) {
																									{
																										double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																										double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																										
																										// Store the value of the function call, so the function call is only made once.
																										double cv$weightedProbability = (Math.log(cv$probabilitySample76Value50) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																										
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
																					}
																				}
																			}
																			for(int index$sample$57 = 0; index$sample$57 < noSamples; index$sample$57 += 1) {
																				for(int index$timeStep$58 = 1; index$timeStep$58 < length$metric[index$sample$57][0]; index$timeStep$58 += 1) {
																					if(!((index$sample$57 == sample$var45) && (index$timeStep$58 == timeStep$var66))) {
																						// Enumerating the possible outputs of Categorical 73.
																						for(int index$sample76$59 = 0; index$sample76$59 < noStates; index$sample76$59 += 1) {
																							int distributionTempVariable$var74$61 = index$sample76$59;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample76Value60 = (cv$probabilitySample76Value50 * distribution$sample76[((index$sample$57 - 0) / 1)][((index$timeStep$58 - 1) / 1)][index$sample76$59]);
																							if((index$sample$57 == sample$var196)) {
																								if((index$timeStep$58 == timeStep$var226)) {
																									for(int var146 = 0; var146 < noServers; var146 += 1) {
																										for(int var156 = 0; var156 < noStates; var156 += 1) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																														double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																														
																														// Store the value of the function call, so the function call is only made once.
																														double cv$weightedProbability = (Math.log(cv$probabilitySample76Value60) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																														
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
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value60);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								
								// Enumerating the possible arguments for Gaussian 244.
								if(fixedFlag$sample76) {
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
											if((sample$var45 == sample$var196)) {
												if((timeStep$var66 == timeStep$var226)) {
													for(int var119 = 0; var119 < noServers; var119 += 1) {
														for(int var129 = 0; var129 < noStates; var129 += 1) {
															if((var119 == server)) {
																if((var129 == st[sample$var196][timeStep$var226])) {
																	if(fixedFlag$sample57) {
																		for(int index$sample$75_1 = 0; index$sample$75_1 < noSamples; index$sample$75_1 += 1) {
																			if((index$sample$75_1 == sample$var196)) {
																				if((0 == timeStep$var226)) {
																					for(int var146 = 0; var146 < noServers; var146 += 1) {
																						for(int var156 = 0; var156 < noStates; var156 += 1) {
																							if((var146 == server)) {
																								if((var156 == st[sample$var196][timeStep$var226])) {
																									{
																										double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																										double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																										
																										// Store the value of the function call, so the function call is only made once.
																										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																										
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
																		for(int index$sample$76 = 0; index$sample$76 < noSamples; index$sample$76 += 1) {
																			if(true) {
																				// Enumerating the possible outputs of Categorical 54.
																				for(int index$sample57$77 = 0; index$sample57$77 < noStates; index$sample57$77 += 1) {
																					int distributionTempVariable$var55$79 = index$sample57$77;
																					
																					// Update the probability of sampling this value from the distribution value.
																					double cv$probabilitySample57Value78 = (1.0 * distribution$sample57[((index$sample$76 - 0) / 1)][index$sample57$77]);
																					if((index$sample$76 == sample$var196)) {
																						if((0 == timeStep$var226)) {
																							for(int var146 = 0; var146 < noServers; var146 += 1) {
																								for(int var156 = 0; var156 < noStates; var156 += 1) {
																									if((var146 == server)) {
																										if((var156 == st[sample$var196][timeStep$var226])) {
																											{
																												double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																												double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																												
																												// Store the value of the function call, so the function call is only made once.
																												double cv$weightedProbability = (Math.log(cv$probabilitySample57Value78) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																												
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
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value78);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 73.
												for(int index$sample76$69 = 0; index$sample76$69 < noStates; index$sample76$69 += 1) {
													int distributionTempVariable$var74$71 = index$sample76$69;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample76Value70 = (1.0 * distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$69]);
													if((sample$var45 == sample$var196)) {
														if((timeStep$var66 == timeStep$var226)) {
															for(int var119 = 0; var119 < noServers; var119 += 1) {
																for(int var129 = 0; var129 < noStates; var129 += 1) {
																	if((var119 == server)) {
																		if((var129 == st[sample$var196][timeStep$var226])) {
																			if(fixedFlag$sample57) {
																				for(int index$sample$81_1 = 0; index$sample$81_1 < noSamples; index$sample$81_1 += 1) {
																					if((index$sample$81_1 == sample$var196)) {
																						if((0 == timeStep$var226)) {
																							for(int var146 = 0; var146 < noServers; var146 += 1) {
																								for(int var156 = 0; var156 < noStates; var156 += 1) {
																									if((var146 == server)) {
																										if((var156 == st[sample$var196][timeStep$var226])) {
																											{
																												double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																												double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																												
																												// Store the value of the function call, so the function call is only made once.
																												double cv$weightedProbability = (Math.log(cv$probabilitySample76Value70) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																												
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
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value70);
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
																						// Enumerating the possible outputs of Categorical 54.
																						for(int index$sample57$83 = 0; index$sample57$83 < noStates; index$sample57$83 += 1) {
																							int distributionTempVariable$var55$85 = index$sample57$83;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample57Value84 = (cv$probabilitySample76Value70 * distribution$sample57[((index$sample$82 - 0) / 1)][index$sample57$83]);
																							if((index$sample$82 == sample$var196)) {
																								if((0 == timeStep$var226)) {
																									for(int var146 = 0; var146 < noServers; var146 += 1) {
																										for(int var156 = 0; var156 < noStates; var156 += 1) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																														double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																														
																														// Store the value of the function call, so the function call is only made once.
																														double cv$weightedProbability = (Math.log(cv$probabilitySample57Value84) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																														
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
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value84);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
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
							
							// Record that the sample was reached.
							cv$sampleReached = true;
							
							// Add the probability of this sample task to the sample task accumulator.
							cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			if(cv$sampleReached)
				logProbability$var244 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var245 = cv$accumulator;
			
			// Guard to ensure that metric_g is only updated once for this probability.
			boolean cv$guard$metric_g = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$metric_g) {
					// Set the guard so the update is only applied once.
					cv$guard$metric_g = true;
					
					// Update the variable probability
					logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample256 = (((fixedFlag$sample57 && fixedFlag$sample76) && fixedFlag$sample134) && fixedFlag$sample162);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(metric_valid_g[sample$var196][server][timeStep$var226])
							// Record that the sample was reached.
							cv$sampleReached = true;
					}
				}
			}
			double cv$sampleValue = logProbability$var245;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var244 = cv$rvAccumulator;
			
			// Guard to ensure that metric_g is only updated once for this probability.
			boolean cv$guard$metric_g = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$metric_g) {
					// Set the guard so the update is only applied once.
					cv$guard$metric_g = true;
					
					// Update the variable probability
					logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
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
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// A guard to check if the sample value is ever reached.
				boolean cv$sampleReached = false;
				for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Copy of index so that its values can be safely substituted
					int index$sample$1 = sample$var45;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[sample$var45][0];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				}
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				if(cv$sampleReached)
					logProbability$var54 = cv$sampleAccumulator;
				
				// Only update the sample if it was reached, otherwise the NaN will be
				// erroneously over written.
				if(cv$sampleReached)
					// Store the random variable instance probability
					logProbability$var55 = cv$accumulator;
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample57)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample57)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedFlag$sample20);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var55;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var54 = cv$rvAccumulator;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample57)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
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
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// A guard to check if the sample value is ever reached.
				boolean cv$sampleReached = false;
				for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
					for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 76 including any distribution
						// values.
						// 
						// Copy of index so that its values can be safely substituted
						int index$timeStep$1 = timeStep$var66;
						
						// Copy of index so that its values can be safely substituted
						int index$sample$2 = sample$var45;
						{
							// The sample value to calculate the probability of generating
							int cv$sampleValue = st[sample$var45][timeStep$var66];
							
							// Enumerating the possible arguments for Categorical 73.
							if(fixedFlag$sample57) {
								for(int index$sample$4_1 = 0; index$sample$4_1 < noSamples; index$sample$4_1 += 1) {
									if((index$sample$4_1 == sample$var45)) {
										if((0 == (timeStep$var66 - 1))) {
											for(int var32 = 0; var32 < noStates; var32 += 1) {
												if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
													{
														double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														
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
										// Enumerating the possible outputs of Categorical 54.
										for(int index$sample57$6 = 0; index$sample57$6 < noStates; index$sample57$6 += 1) {
											int distributionTempVariable$var55$8 = index$sample57$6;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample57Value7 = (1.0 * distribution$sample57[((index$sample$5 - 0) / 1)][index$sample57$6]);
											if((index$sample$5 == sample$var45)) {
												if((0 == (timeStep$var66 - 1))) {
													for(int var32 = 0; var32 < noStates; var32 += 1) {
														if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
															{
																double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
																
																// Store the value of the function call, so the function call is only made once.
																double cv$weightedProbability = (Math.log(cv$probabilitySample57Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																
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
											}
										}
									}
								}
							}
							
							// Enumerating the possible arguments for Categorical 73.
							if((index$sample$2 == sample$var45)) {
								if((index$timeStep$1 == (timeStep$var66 - 1))) {
									for(int var32 = 0; var32 < noStates; var32 += 1) {
										if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
											{
												double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
												
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
							if(fixedFlag$sample76) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample$var45)) {
											if((index$timeStep$13_2 == (timeStep$var66 - 1))) {
												for(int var32 = 0; var32 < noStates; var32 += 1) {
													if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
														{
															double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
															
															// Store the value of the function call, so the function call is only made once.
															double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
															
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
											// Enumerating the possible outputs of Categorical 73.
											for(int index$sample76$16 = 0; index$sample76$16 < noStates; index$sample76$16 += 1) {
												int distributionTempVariable$var74$18 = index$sample76$16;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample76Value17 = (1.0 * distribution$sample76[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample76$16]);
												if((index$sample$14 == sample$var45)) {
													if((index$timeStep$15 == (timeStep$var66 - 1))) {
														for(int var32 = 0; var32 < noStates; var32 += 1) {
															if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
																{
																	double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
																	
																	// Store the value of the function call, so the function call is only made once.
																	double cv$weightedProbability = (Math.log(cv$probabilitySample76Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	
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
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value17);
																}
															}
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
						
						// Record that the sample was reached.
						cv$sampleReached = true;
						
						// Add the probability of this sample task to the sample task accumulator.
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					}
				}
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				if(cv$sampleReached)
					logProbability$var73 = cv$sampleAccumulator;
				
				// Only update the sample if it was reached, otherwise the NaN will be
				// erroneously over written.
				if(cv$sampleReached)
					// Store the random variable instance probability
					logProbability$var74 = cv$accumulator;
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample76)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample76)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample76 = ((fixedFlag$sample76 && fixedFlag$sample33) && fixedFlag$sample57);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					// Record that the sample was reached.
					cv$sampleReached = true;
			}
			double cv$sampleValue = logProbability$var74;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var73 = cv$rvAccumulator;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample76)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample76)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample134 using sampled
	// values.
	private final void logProbabilityValue$sample134() {
		// Determine if we need to calculate the values for sample task 134 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample134) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				for(int var129 = 0; var129 < noStates; var129 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = current_metric_mean[var119][var129];
						{
							{
								double var106 = 0.0;
								double var107 = (double)max_metric;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((var106 <= cv$sampleValue) && (cv$sampleValue < var107))?(-Math.log((var107 - var106))):Double.NEGATIVE_INFINITY));
								
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
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			if(cv$sampleReached)
				logProbability$var108 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var130 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample134)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample134 = fixedFlag$sample134;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				for(int var129 = 0; var129 < noStates; var129 += 1)
					// Record that the sample was reached.
					cv$sampleReached = true;
			}
			double cv$sampleValue = logProbability$var130;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var108 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample134)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample162 using sampled
	// values.
	private final void logProbabilityValue$sample162() {
		// Determine if we need to calculate the values for sample task 162 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample162) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				for(int var156 = 0; var156 < noStates; var156 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = current_metric_var[var146][var156];
						{
							{
								double var133 = 1.0;
								double var134 = 1.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var133, var134));
								
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
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			if(cv$sampleReached)
				logProbability$var135 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var157 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample162)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample162 = fixedFlag$sample162;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				for(int var156 = 0; var156 < noStates; var156 += 1)
					// Record that the sample was reached.
					cv$sampleReached = true;
			}
			double cv$sampleValue = logProbability$var157;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var135 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample162)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample190 using sampled
	// values.
	private final void logProbabilityValue$sample190() {
		// Determine if we need to calculate the values for sample task 190 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample190) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				for(int var183 = 0; var183 < noStates; var183 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = current_metric_valid_bias[var173][var183];
						{
							{
								double var160 = 1.0;
								double var161 = 1.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var160, var161));
								
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
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			if(cv$sampleReached)
				logProbability$var162 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var184 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample190)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample190 = fixedFlag$sample190;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				for(int var183 = 0; var183 < noStates; var183 += 1)
					// Record that the sample was reached.
					cv$sampleReached = true;
			}
			double cv$sampleValue = logProbability$var184;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var162 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample190)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample20 using sampled
	// values.
	private final void logProbabilityValue$sample20() {
		// Determine if we need to calculate the values for sample task 20 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample20) {
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
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, noStates));
						
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
			logProbability$var19 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$initialStateDistribution = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample20 = fixedFlag$sample20;
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
			logProbability$var19 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample241 using sampled
	// values.
	private final void logProbabilityValue$sample241() {
		// Determine if we need to calculate the values for sample task 241 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample241) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						{
							// The sample value to calculate the probability of generating
							boolean cv$sampleValue = metric_valid_g[sample$var196][server][timeStep$var226];
							{
								{
									double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var230:(1.0 - var230))));
									
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
						
						// Record that the sample was reached.
						cv$sampleReached = true;
						
						// Add the probability of this sample task to the sample task accumulator.
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			if(cv$sampleReached)
				logProbability$var231 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var232 = cv$accumulator;
			
			// Guard to ensure that metric_valid_g is only updated once for this probability.
			boolean cv$guard$metric_valid_g = false;
			
			// Update the variable probability
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$metric_valid_g) {
					// Set the guard so the update is only applied once.
					cv$guard$metric_valid_g = true;
					
					// Update the variable probability
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample241 = ((fixedFlag$sample57 && fixedFlag$sample76) && fixedFlag$sample190);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1)
						// Record that the sample was reached.
						cv$sampleReached = true;
				}
			}
			double cv$sampleValue = logProbability$var232;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var231 = cv$rvAccumulator;
			
			// Guard to ensure that metric_valid_g is only updated once for this probability.
			boolean cv$guard$metric_valid_g = false;
			
			// Update the variable probability
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$metric_valid_g) {
					// Set the guard so the update is only applied once.
					cv$guard$metric_valid_g = true;
					
					// Update the variable probability
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample256 using sampled
	// values.
	private final void logProbabilityValue$sample256() {
		// Determine if we need to calculate the values for sample task 256 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample256) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(metric_valid_g[sample$var196][server][timeStep$var226]) {
							// An accumulator for log probabilities.
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							
							// An accumulator for the distributed probability space covered.
							double cv$probabilityReached = 0.0;
							{
								// The sample value to calculate the probability of generating
								double cv$sampleValue = var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
								{
									{
										double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
										double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
										
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
							
							// Record that the sample was reached.
							cv$sampleReached = true;
							
							// Add the probability of this sample task to the sample task accumulator.
							cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			if(cv$sampleReached)
				logProbability$var244 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var245 = cv$accumulator;
			
			// Guard to ensure that metric_g is only updated once for this probability.
			boolean cv$guard$metric_g = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$metric_g) {
					// Set the guard so the update is only applied once.
					cv$guard$metric_g = true;
					
					// Update the variable probability
					logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample256 = (((fixedFlag$sample57 && fixedFlag$sample76) && fixedFlag$sample134) && fixedFlag$sample162);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(metric_valid_g[sample$var196][server][timeStep$var226])
							// Record that the sample was reached.
							cv$sampleReached = true;
					}
				}
			}
			double cv$sampleValue = logProbability$var245;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var244 = cv$rvAccumulator;
			
			// Guard to ensure that metric_g is only updated once for this probability.
			boolean cv$guard$metric_g = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$metric_g) {
					// Set the guard so the update is only applied once.
					cv$guard$metric_g = true;
					
					// Update the variable probability
					logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample33 using sampled
	// values.
	private final void logProbabilityValue$sample33() {
		// Determine if we need to calculate the values for sample task 33 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample33) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var32 = 0; var32 < noStates; var32 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = m[var32];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, noStates));
							
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
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			if(cv$sampleReached)
				logProbability$var21 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var33 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample33)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample33 = fixedFlag$sample33;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var32 = 0; var32 < noStates; var32 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var33;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var21 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample33)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample57 using sampled
	// values.
	private final void logProbabilityValue$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample57) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Copy of index so that its values can be safely substituted
				int index$sample$1 = sample$var45;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[sample$var45][0];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			if(cv$sampleReached)
				logProbability$var54 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var55 = cv$accumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedFlag$sample20);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var55;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var54 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample76 using sampled
	// values.
	private final void logProbabilityValue$sample76() {
		// Determine if we need to calculate the values for sample task 76 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample76) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Copy of index so that its values can be safely substituted
					int index$timeStep$1 = timeStep$var66;
					
					// Copy of index so that its values can be safely substituted
					int index$sample$2 = sample$var45;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[sample$var45][timeStep$var66];
						{
							{
								double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			if(cv$sampleReached)
				logProbability$var73 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var74 = cv$accumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample76)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample76 = ((fixedFlag$sample76 && fixedFlag$sample33) && fixedFlag$sample57);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					// Record that the sample was reached.
					cv$sampleReached = true;
			}
			double cv$sampleValue = logProbability$var74;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var73 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample76)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 134 drawn from Uniform 108. Inference was performed using Metropolis-Hastings.
	private final void sample134(int var119, int var129, int threadID$cv$var129, Rng RNG$) {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			{
				// Metropolis-Hastings
				cv$numNumStates = Math.max(cv$numNumStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = current_metric_mean[var119][var129];
			
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
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
						double var130 = cv$proposedValue;
						
						// Guards to ensure that current_metric_mean is only updated when there is a valid
						// path.
						{
							{
								double[] var120 = current_metric_mean[var119];
								var120[var129] = cv$currentValue;
							}
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$temp$0$var106;
					{
						cv$temp$0$var106 = 0.0;
					}
					double cv$temp$1$var107;
					{
						cv$temp$1$var107 = max_metric;
					}
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var106 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var107))?(-Math.log((cv$temp$1$var107 - cv$temp$0$var106))):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 244.
					{
						// Looking for a path between Sample 134 and consumer Gaussian 244.
						{
							for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
								for(int server = 0; server < noServers; server += 1) {
									for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
											if(fixedFlag$sample57) {
												for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
													if((sample$var45 == sample$var196)) {
														if((0 == timeStep$var226)) {
															double traceTempVariable$var241$11_1 = cv$currentValue;
															if((var119 == server)) {
																if((var129 == st[sample$var196][timeStep$var226])) {
																	// Processing sample task 256 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																			// the output of Sample task 134.
																			for(int index$sample$29_1 = 0; index$sample$29_1 < noSamples; index$sample$29_1 += 1) {
																				if((index$sample$29_1 == sample$var196)) {
																					if((0 == timeStep$var226)) {
																						for(int var146 = 0; var146 < noServers; var146 += 1) {
																							for(int var156 = 0; var156 < noStates; var156 += 1) {
																								if((var146 == server)) {
																									if((var156 == st[sample$var196][timeStep$var226])) {
																										{
																											{
																												double cv$temp$2$var241;
																												{
																													// Constructing a random variable input for use later.
																													double var241 = traceTempVariable$var241$11_1;
																													cv$temp$2$var241 = var241;
																												}
																												double cv$temp$3$var243;
																												{
																													// Constructing a random variable input for use later.
																													double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																													cv$temp$3$var243 = var243;
																												}
																												
																												// Record the probability of sample task 256 generating output with current configuration.
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))));
																												}
																												
																												// Recorded the probability of reaching sample task 256 with the current configuration.
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
																			
																			// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																			// the output of Sample task 134.
																			if(fixedFlag$sample76) {
																				for(int index$sample$31_1 = 0; index$sample$31_1 < noSamples; index$sample$31_1 += 1) {
																					for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$31_1][0]; timeStep$var66 += 1) {
																						if((index$sample$31_1 == sample$var196)) {
																							if((timeStep$var66 == timeStep$var226)) {
																								for(int var146 = 0; var146 < noServers; var146 += 1) {
																									for(int var156 = 0; var156 < noStates; var156 += 1) {
																										if((var146 == server)) {
																											if((var156 == st[sample$var196][timeStep$var226])) {
																												{
																													{
																														double cv$temp$4$var241;
																														{
																															// Constructing a random variable input for use later.
																															double var241 = traceTempVariable$var241$11_1;
																															cv$temp$4$var241 = var241;
																														}
																														double cv$temp$5$var243;
																														{
																															// Constructing a random variable input for use later.
																															double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																															cv$temp$5$var243 = var243;
																														}
																														
																														// Record the probability of sample task 256 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))));
																														}
																														
																														// Recorded the probability of reaching sample task 256 with the current configuration.
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
																			} else {
																				for(int index$sample$32 = 0; index$sample$32 < noSamples; index$sample$32 += 1) {
																					for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$32][0]; timeStep$var66 += 1) {
																						if(true) {
																							// Enumerating the possible outputs of Categorical 73.
																							for(int index$sample76$34 = 0; index$sample76$34 < noStates; index$sample76$34 += 1) {
																								int distributionTempVariable$var74$36 = index$sample76$34;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample76Value35 = (1.0 * distribution$sample76[((index$sample$32 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$34]);
																								if((index$sample$32 == sample$var196)) {
																									if((timeStep$var66 == timeStep$var226)) {
																										for(int var146 = 0; var146 < noServers; var146 += 1) {
																											for(int var156 = 0; var156 < noStates; var156 += 1) {
																												if((var146 == server)) {
																													if((var156 == st[sample$var196][timeStep$var226])) {
																														{
																															{
																																double cv$temp$6$var241;
																																{
																																	// Constructing a random variable input for use later.
																																	double var241 = traceTempVariable$var241$11_1;
																																	cv$temp$6$var241 = var241;
																																}
																																double cv$temp$7$var243;
																																{
																																	// Constructing a random variable input for use later.
																																	double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																	cv$temp$7$var243 = var243;
																																}
																																
																																// Record the probability of sample task 256 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample76Value35) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value35) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value35) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value35) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value35) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))));
																																}
																																
																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value35);
																															}
																														}
																													}
																												}
																											}
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
											} else {
												for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
													if(true) {
														// Enumerating the possible outputs of Categorical 54.
														for(int index$sample57$7 = 0; index$sample57$7 < noStates; index$sample57$7 += 1) {
															int distributionTempVariable$var55$9 = index$sample57$7;
															
															// Update the probability of sampling this value from the distribution value.
															double cv$probabilitySample57Value8 = (1.0 * distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$7]);
															if((sample$var45 == sample$var196)) {
																if((0 == timeStep$var226)) {
																	double traceTempVariable$var241$12_1 = cv$currentValue;
																	if((var119 == server)) {
																		if((var129 == st[sample$var196][timeStep$var226])) {
																			// Processing sample task 256 of consumer random variable null.
																			{
																				// Set an accumulator to sum the probabilities for each possible configuration of
																				// inputs.
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				
																				// Set an accumulator to record the consumer distributions not seen. Initially set
																				// to 1 as seen values will be deducted from this value.
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																					// the output of Sample task 134.
																					if((sample$var45 == sample$var196)) {
																						if((0 == timeStep$var226)) {
																							for(int var146 = 0; var146 < noServers; var146 += 1) {
																								for(int var156 = 0; var156 < noStates; var156 += 1) {
																									if((var146 == server)) {
																										if((var156 == st[sample$var196][timeStep$var226])) {
																											{
																												{
																													double cv$temp$8$var241;
																													{
																														// Constructing a random variable input for use later.
																														double var241 = traceTempVariable$var241$12_1;
																														cv$temp$8$var241 = var241;
																													}
																													double cv$temp$9$var243;
																													{
																														// Constructing a random variable input for use later.
																														double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																														cv$temp$9$var243 = var243;
																													}
																													
																													// Record the probability of sample task 256 generating output with current configuration.
																													if(((Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														// If the second value is -infinity.
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))));
																													}
																													
																													// Recorded the probability of reaching sample task 256 with the current configuration.
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value8);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$41 = 0; index$sample$41 < noSamples; index$sample$41 += 1) {
																						if(!(index$sample$41 == sample$var45)) {
																							// Enumerating the possible outputs of Categorical 54.
																							for(int index$sample57$42 = 0; index$sample57$42 < noStates; index$sample57$42 += 1) {
																								int distributionTempVariable$var55$44 = index$sample57$42;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample57Value43 = (cv$probabilitySample57Value8 * distribution$sample57[((index$sample$41 - 0) / 1)][index$sample57$42]);
																								if((index$sample$41 == sample$var196)) {
																									if((0 == timeStep$var226)) {
																										for(int var146 = 0; var146 < noServers; var146 += 1) {
																											for(int var156 = 0; var156 < noStates; var156 += 1) {
																												if((var146 == server)) {
																													if((var156 == st[sample$var196][timeStep$var226])) {
																														{
																															{
																																double cv$temp$10$var241;
																																{
																																	// Constructing a random variable input for use later.
																																	double var241 = traceTempVariable$var241$12_1;
																																	cv$temp$10$var241 = var241;
																																}
																																double cv$temp$11$var243;
																																{
																																	// Constructing a random variable input for use later.
																																	double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																	cv$temp$11$var243 = var243;
																																}
																																
																																// Record the probability of sample task 256 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))));
																																}
																																
																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value43);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					
																					// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																					// the output of Sample task 134.
																					if(fixedFlag$sample76) {
																						for(int index$sample$48_1 = 0; index$sample$48_1 < noSamples; index$sample$48_1 += 1) {
																							for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$48_1][0]; timeStep$var66 += 1) {
																								if((index$sample$48_1 == sample$var196)) {
																									if((timeStep$var66 == timeStep$var226)) {
																										for(int var146 = 0; var146 < noServers; var146 += 1) {
																											for(int var156 = 0; var156 < noStates; var156 += 1) {
																												if((var146 == server)) {
																													if((var156 == st[sample$var196][timeStep$var226])) {
																														{
																															{
																																double cv$temp$12$var241;
																																{
																																	// Constructing a random variable input for use later.
																																	double var241 = traceTempVariable$var241$12_1;
																																	cv$temp$12$var241 = var241;
																																}
																																double cv$temp$13$var243;
																																{
																																	// Constructing a random variable input for use later.
																																	double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																	cv$temp$13$var243 = var243;
																																}
																																
																																// Record the probability of sample task 256 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))));
																																}
																																
																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value8);
																															}
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
																						for(int index$sample$49 = 0; index$sample$49 < noSamples; index$sample$49 += 1) {
																							for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$49][0]; timeStep$var66 += 1) {
																								if(true) {
																									// Enumerating the possible outputs of Categorical 73.
																									for(int index$sample76$51 = 0; index$sample76$51 < noStates; index$sample76$51 += 1) {
																										int distributionTempVariable$var74$53 = index$sample76$51;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample76Value52 = (cv$probabilitySample57Value8 * distribution$sample76[((index$sample$49 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$51]);
																										if((index$sample$49 == sample$var196)) {
																											if((timeStep$var66 == timeStep$var226)) {
																												for(int var146 = 0; var146 < noServers; var146 += 1) {
																													for(int var156 = 0; var156 < noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == st[sample$var196][timeStep$var226])) {
																																{
																																	{
																																		double cv$temp$14$var241;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var241 = traceTempVariable$var241$12_1;
																																			cv$temp$14$var241 = var241;
																																		}
																																		double cv$temp$15$var243;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																			cv$temp$15$var243 = var243;
																																		}
																																		
																																		// Record the probability of sample task 256 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 256 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value52);
																																	}
																																}
																															}
																														}
																													}
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
							for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
								for(int server = 0; server < noServers; server += 1) {
									for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
											if(fixedFlag$sample76) {
												for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
													for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
														if((sample$var45 == sample$var196)) {
															if((timeStep$var66 == timeStep$var226)) {
																double traceTempVariable$var241$23_1 = cv$currentValue;
																if((var119 == server)) {
																	if((var129 == st[sample$var196][timeStep$var226])) {
																		// Processing sample task 256 of consumer random variable null.
																		{
																			// Set an accumulator to sum the probabilities for each possible configuration of
																			// inputs.
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			
																			// Set an accumulator to record the consumer distributions not seen. Initially set
																			// to 1 as seen values will be deducted from this value.
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																				// the output of Sample task 134.
																				if(fixedFlag$sample57) {
																					for(int index$sample$57_1 = 0; index$sample$57_1 < noSamples; index$sample$57_1 += 1) {
																						if((index$sample$57_1 == sample$var196)) {
																							if((0 == timeStep$var226)) {
																								for(int var146 = 0; var146 < noServers; var146 += 1) {
																									for(int var156 = 0; var156 < noStates; var156 += 1) {
																										if((var146 == server)) {
																											if((var156 == st[sample$var196][timeStep$var226])) {
																												{
																													{
																														double cv$temp$16$var241;
																														{
																															// Constructing a random variable input for use later.
																															double var241 = traceTempVariable$var241$23_1;
																															cv$temp$16$var241 = var241;
																														}
																														double cv$temp$17$var243;
																														{
																															// Constructing a random variable input for use later.
																															double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																															cv$temp$17$var243 = var243;
																														}
																														
																														// Record the probability of sample task 256 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))));
																														}
																														
																														// Recorded the probability of reaching sample task 256 with the current configuration.
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
																				} else {
																					for(int index$sample$58 = 0; index$sample$58 < noSamples; index$sample$58 += 1) {
																						if(true) {
																							// Enumerating the possible outputs of Categorical 54.
																							for(int index$sample57$59 = 0; index$sample57$59 < noStates; index$sample57$59 += 1) {
																								int distributionTempVariable$var55$61 = index$sample57$59;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample57Value60 = (1.0 * distribution$sample57[((index$sample$58 - 0) / 1)][index$sample57$59]);
																								if((index$sample$58 == sample$var196)) {
																									if((0 == timeStep$var226)) {
																										for(int var146 = 0; var146 < noServers; var146 += 1) {
																											for(int var156 = 0; var156 < noStates; var156 += 1) {
																												if((var146 == server)) {
																													if((var156 == st[sample$var196][timeStep$var226])) {
																														{
																															{
																																double cv$temp$18$var241;
																																{
																																	// Constructing a random variable input for use later.
																																	double var241 = traceTempVariable$var241$23_1;
																																	cv$temp$18$var241 = var241;
																																}
																																double cv$temp$19$var243;
																																{
																																	// Constructing a random variable input for use later.
																																	double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																	cv$temp$19$var243 = var243;
																																}
																																
																																// Record the probability of sample task 256 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample57Value60) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value60) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value60) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value60) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value60) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))));
																																}
																																
																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value60);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				
																				// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																				// the output of Sample task 134.
																				for(int index$sample$65_1 = 0; index$sample$65_1 < noSamples; index$sample$65_1 += 1) {
																					for(int index$timeStep$65_2 = 1; index$timeStep$65_2 < length$metric[index$sample$65_1][0]; index$timeStep$65_2 += 1) {
																						if((index$sample$65_1 == sample$var196)) {
																							if((index$timeStep$65_2 == timeStep$var226)) {
																								for(int var146 = 0; var146 < noServers; var146 += 1) {
																									for(int var156 = 0; var156 < noStates; var156 += 1) {
																										if((var146 == server)) {
																											if((var156 == st[sample$var196][timeStep$var226])) {
																												{
																													{
																														double cv$temp$20$var241;
																														{
																															// Constructing a random variable input for use later.
																															double var241 = traceTempVariable$var241$23_1;
																															cv$temp$20$var241 = var241;
																														}
																														double cv$temp$21$var243;
																														{
																															// Constructing a random variable input for use later.
																															double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																															cv$temp$21$var243 = var243;
																														}
																														
																														// Record the probability of sample task 256 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))));
																														}
																														
																														// Recorded the probability of reaching sample task 256 with the current configuration.
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
											} else {
												for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
													for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
														if(true) {
															// Enumerating the possible outputs of Categorical 73.
															for(int index$sample76$19 = 0; index$sample76$19 < noStates; index$sample76$19 += 1) {
																int distributionTempVariable$var74$21 = index$sample76$19;
																
																// Update the probability of sampling this value from the distribution value.
																double cv$probabilitySample76Value20 = (1.0 * distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$19]);
																if((sample$var45 == sample$var196)) {
																	if((timeStep$var66 == timeStep$var226)) {
																		double traceTempVariable$var241$24_1 = cv$currentValue;
																		if((var119 == server)) {
																			if((var129 == st[sample$var196][timeStep$var226])) {
																				// Processing sample task 256 of consumer random variable null.
																				{
																					// Set an accumulator to sum the probabilities for each possible configuration of
																					// inputs.
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					
																					// Set an accumulator to record the consumer distributions not seen. Initially set
																					// to 1 as seen values will be deducted from this value.
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																						// the output of Sample task 134.
																						if(fixedFlag$sample57) {
																							for(int index$sample$67_1 = 0; index$sample$67_1 < noSamples; index$sample$67_1 += 1) {
																								if((index$sample$67_1 == sample$var196)) {
																									if((0 == timeStep$var226)) {
																										for(int var146 = 0; var146 < noServers; var146 += 1) {
																											for(int var156 = 0; var156 < noStates; var156 += 1) {
																												if((var146 == server)) {
																													if((var156 == st[sample$var196][timeStep$var226])) {
																														{
																															{
																																double cv$temp$22$var241;
																																{
																																	// Constructing a random variable input for use later.
																																	double var241 = traceTempVariable$var241$24_1;
																																	cv$temp$22$var241 = var241;
																																}
																																double cv$temp$23$var243;
																																{
																																	// Constructing a random variable input for use later.
																																	double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																	cv$temp$23$var243 = var243;
																																}
																																
																																// Record the probability of sample task 256 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))));
																																}
																																
																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value20);
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
																							for(int index$sample$68 = 0; index$sample$68 < noSamples; index$sample$68 += 1) {
																								if(true) {
																									// Enumerating the possible outputs of Categorical 54.
																									for(int index$sample57$69 = 0; index$sample57$69 < noStates; index$sample57$69 += 1) {
																										int distributionTempVariable$var55$71 = index$sample57$69;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample57Value70 = (cv$probabilitySample76Value20 * distribution$sample57[((index$sample$68 - 0) / 1)][index$sample57$69]);
																										if((index$sample$68 == sample$var196)) {
																											if((0 == timeStep$var226)) {
																												for(int var146 = 0; var146 < noServers; var146 += 1) {
																													for(int var156 = 0; var156 < noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == st[sample$var196][timeStep$var226])) {
																																{
																																	{
																																		double cv$temp$24$var241;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var241 = traceTempVariable$var241$24_1;
																																			cv$temp$24$var241 = var241;
																																		}
																																		double cv$temp$25$var243;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																			cv$temp$25$var243 = var243;
																																		}
																																		
																																		// Record the probability of sample task 256 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample57Value70) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value70) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value70) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value70) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value70) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 256 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value70);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						
																						// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																						// the output of Sample task 134.
																						if((sample$var45 == sample$var196)) {
																							if((timeStep$var66 == timeStep$var226)) {
																								for(int var146 = 0; var146 < noServers; var146 += 1) {
																									for(int var156 = 0; var156 < noStates; var156 += 1) {
																										if((var146 == server)) {
																											if((var156 == st[sample$var196][timeStep$var226])) {
																												{
																													{
																														double cv$temp$26$var241;
																														{
																															// Constructing a random variable input for use later.
																															double var241 = traceTempVariable$var241$24_1;
																															cv$temp$26$var241 = var241;
																														}
																														double cv$temp$27$var243;
																														{
																															// Constructing a random variable input for use later.
																															double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																															cv$temp$27$var243 = var243;
																														}
																														
																														// Record the probability of sample task 256 generating output with current configuration.
																														if(((Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))));
																														}
																														
																														// Recorded the probability of reaching sample task 256 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value20);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$sample$76 = 0; index$sample$76 < noSamples; index$sample$76 += 1) {
																							for(int index$timeStep$77 = 1; index$timeStep$77 < length$metric[index$sample$76][0]; index$timeStep$77 += 1) {
																								if(!((index$sample$76 == sample$var45) && (index$timeStep$77 == timeStep$var66))) {
																									// Enumerating the possible outputs of Categorical 73.
																									for(int index$sample76$78 = 0; index$sample76$78 < noStates; index$sample76$78 += 1) {
																										int distributionTempVariable$var74$80 = index$sample76$78;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample76Value79 = (cv$probabilitySample76Value20 * distribution$sample76[((index$sample$76 - 0) / 1)][((index$timeStep$77 - 1) / 1)][index$sample76$78]);
																										if((index$sample$76 == sample$var196)) {
																											if((index$timeStep$77 == timeStep$var226)) {
																												for(int var146 = 0; var146 < noServers; var146 += 1) {
																													for(int var156 = 0; var156 < noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == st[sample$var196][timeStep$var226])) {
																																{
																																	{
																																		double cv$temp$28$var241;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var241 = traceTempVariable$var241$24_1;
																																			cv$temp$28$var241 = var241;
																																		}
																																		double cv$temp$29$var243;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																			cv$temp$29$var243 = var243;
																																		}
																																		
																																		// Record the probability of sample task 256 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample76Value79) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value79) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value79) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value79) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value79) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 256 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value79);
																																	}
																																}
																															}
																														}
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
				double var130 = cv$originalValue;
				
				// Guards to ensure that current_metric_mean is only updated when there is a valid
				// path.
				{
					{
						double[] var120 = current_metric_mean[var119];
						var120[var129] = var130;
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 162 drawn from InverseGamma 135. Inference was performed using Metropolis-Hastings.
	private final void sample162(int var146, int var156, int threadID$cv$var156, Rng RNG$) {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			{
				// Metropolis-Hastings
				cv$numNumStates = Math.max(cv$numNumStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = current_metric_var[var146][var156];
			
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
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
						double var157 = cv$proposedValue;
						
						// Guards to ensure that current_metric_var is only updated when there is a valid
						// path.
						{
							{
								double[] var147 = current_metric_var[var146];
								var147[var156] = cv$currentValue;
							}
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$temp$0$var133;
					{
						cv$temp$0$var133 = 1.0;
					}
					double cv$temp$1$var134;
					{
						cv$temp$1$var134 = 1.0;
					}
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var133, cv$temp$1$var134));
					
					// Processing random variable 244.
					{
						// Looking for a path between Sample 162 and consumer Gaussian 244.
						{
							for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
								for(int server = 0; server < noServers; server += 1) {
									for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
											if(fixedFlag$sample57) {
												for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
													if((sample$var45 == sample$var196)) {
														if((0 == timeStep$var226)) {
															double traceTempVariable$var243$11_1 = cv$currentValue;
															if((var146 == server)) {
																if((var156 == st[sample$var196][timeStep$var226])) {
																	// Processing sample task 256 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																			// the output of Sample task 162.
																			for(int index$sample$29_1 = 0; index$sample$29_1 < noSamples; index$sample$29_1 += 1) {
																				if((index$sample$29_1 == sample$var196)) {
																					if((0 == timeStep$var226)) {
																						for(int var119 = 0; var119 < noServers; var119 += 1) {
																							for(int var129 = 0; var129 < noStates; var129 += 1) {
																								if((var119 == server)) {
																									if((var129 == st[sample$var196][timeStep$var226])) {
																										{
																											{
																												double cv$temp$2$var241;
																												{
																													// Constructing a random variable input for use later.
																													double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																													cv$temp$2$var241 = var241;
																												}
																												double cv$temp$3$var243;
																												{
																													// Constructing a random variable input for use later.
																													double var243 = traceTempVariable$var243$11_1;
																													cv$temp$3$var243 = var243;
																												}
																												
																												// Record the probability of sample task 256 generating output with current configuration.
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))));
																												}
																												
																												// Recorded the probability of reaching sample task 256 with the current configuration.
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
																			
																			// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																			// the output of Sample task 162.
																			if(fixedFlag$sample76) {
																				for(int index$sample$31_1 = 0; index$sample$31_1 < noSamples; index$sample$31_1 += 1) {
																					for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$31_1][0]; timeStep$var66 += 1) {
																						if((index$sample$31_1 == sample$var196)) {
																							if((timeStep$var66 == timeStep$var226)) {
																								for(int var119 = 0; var119 < noServers; var119 += 1) {
																									for(int var129 = 0; var129 < noStates; var129 += 1) {
																										if((var119 == server)) {
																											if((var129 == st[sample$var196][timeStep$var226])) {
																												{
																													{
																														double cv$temp$4$var241;
																														{
																															// Constructing a random variable input for use later.
																															double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																															cv$temp$4$var241 = var241;
																														}
																														double cv$temp$5$var243;
																														{
																															// Constructing a random variable input for use later.
																															double var243 = traceTempVariable$var243$11_1;
																															cv$temp$5$var243 = var243;
																														}
																														
																														// Record the probability of sample task 256 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))));
																														}
																														
																														// Recorded the probability of reaching sample task 256 with the current configuration.
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
																			} else {
																				for(int index$sample$32 = 0; index$sample$32 < noSamples; index$sample$32 += 1) {
																					for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$32][0]; timeStep$var66 += 1) {
																						if(true) {
																							// Enumerating the possible outputs of Categorical 73.
																							for(int index$sample76$34 = 0; index$sample76$34 < noStates; index$sample76$34 += 1) {
																								int distributionTempVariable$var74$36 = index$sample76$34;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample76Value35 = (1.0 * distribution$sample76[((index$sample$32 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$34]);
																								if((index$sample$32 == sample$var196)) {
																									if((timeStep$var66 == timeStep$var226)) {
																										for(int var119 = 0; var119 < noServers; var119 += 1) {
																											for(int var129 = 0; var129 < noStates; var129 += 1) {
																												if((var119 == server)) {
																													if((var129 == st[sample$var196][timeStep$var226])) {
																														{
																															{
																																double cv$temp$6$var241;
																																{
																																	// Constructing a random variable input for use later.
																																	double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																	cv$temp$6$var241 = var241;
																																}
																																double cv$temp$7$var243;
																																{
																																	// Constructing a random variable input for use later.
																																	double var243 = traceTempVariable$var243$11_1;
																																	cv$temp$7$var243 = var243;
																																}
																																
																																// Record the probability of sample task 256 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample76Value35) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value35) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value35) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value35) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value35) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))));
																																}
																																
																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value35);
																															}
																														}
																													}
																												}
																											}
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
											} else {
												for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
													if(true) {
														// Enumerating the possible outputs of Categorical 54.
														for(int index$sample57$7 = 0; index$sample57$7 < noStates; index$sample57$7 += 1) {
															int distributionTempVariable$var55$9 = index$sample57$7;
															
															// Update the probability of sampling this value from the distribution value.
															double cv$probabilitySample57Value8 = (1.0 * distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$7]);
															if((sample$var45 == sample$var196)) {
																if((0 == timeStep$var226)) {
																	double traceTempVariable$var243$12_1 = cv$currentValue;
																	if((var146 == server)) {
																		if((var156 == st[sample$var196][timeStep$var226])) {
																			// Processing sample task 256 of consumer random variable null.
																			{
																				// Set an accumulator to sum the probabilities for each possible configuration of
																				// inputs.
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				
																				// Set an accumulator to record the consumer distributions not seen. Initially set
																				// to 1 as seen values will be deducted from this value.
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																					// the output of Sample task 162.
																					if((sample$var45 == sample$var196)) {
																						if((0 == timeStep$var226)) {
																							for(int var119 = 0; var119 < noServers; var119 += 1) {
																								for(int var129 = 0; var129 < noStates; var129 += 1) {
																									if((var119 == server)) {
																										if((var129 == st[sample$var196][timeStep$var226])) {
																											{
																												{
																													double cv$temp$8$var241;
																													{
																														// Constructing a random variable input for use later.
																														double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																														cv$temp$8$var241 = var241;
																													}
																													double cv$temp$9$var243;
																													{
																														// Constructing a random variable input for use later.
																														double var243 = traceTempVariable$var243$12_1;
																														cv$temp$9$var243 = var243;
																													}
																													
																													// Record the probability of sample task 256 generating output with current configuration.
																													if(((Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														// If the second value is -infinity.
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))));
																													}
																													
																													// Recorded the probability of reaching sample task 256 with the current configuration.
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value8);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$41 = 0; index$sample$41 < noSamples; index$sample$41 += 1) {
																						if(!(index$sample$41 == sample$var45)) {
																							// Enumerating the possible outputs of Categorical 54.
																							for(int index$sample57$42 = 0; index$sample57$42 < noStates; index$sample57$42 += 1) {
																								int distributionTempVariable$var55$44 = index$sample57$42;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample57Value43 = (cv$probabilitySample57Value8 * distribution$sample57[((index$sample$41 - 0) / 1)][index$sample57$42]);
																								if((index$sample$41 == sample$var196)) {
																									if((0 == timeStep$var226)) {
																										for(int var119 = 0; var119 < noServers; var119 += 1) {
																											for(int var129 = 0; var129 < noStates; var129 += 1) {
																												if((var119 == server)) {
																													if((var129 == st[sample$var196][timeStep$var226])) {
																														{
																															{
																																double cv$temp$10$var241;
																																{
																																	// Constructing a random variable input for use later.
																																	double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																	cv$temp$10$var241 = var241;
																																}
																																double cv$temp$11$var243;
																																{
																																	// Constructing a random variable input for use later.
																																	double var243 = traceTempVariable$var243$12_1;
																																	cv$temp$11$var243 = var243;
																																}
																																
																																// Record the probability of sample task 256 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))));
																																}
																																
																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value43);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					
																					// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																					// the output of Sample task 162.
																					if(fixedFlag$sample76) {
																						for(int index$sample$48_1 = 0; index$sample$48_1 < noSamples; index$sample$48_1 += 1) {
																							for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$48_1][0]; timeStep$var66 += 1) {
																								if((index$sample$48_1 == sample$var196)) {
																									if((timeStep$var66 == timeStep$var226)) {
																										for(int var119 = 0; var119 < noServers; var119 += 1) {
																											for(int var129 = 0; var129 < noStates; var129 += 1) {
																												if((var119 == server)) {
																													if((var129 == st[sample$var196][timeStep$var226])) {
																														{
																															{
																																double cv$temp$12$var241;
																																{
																																	// Constructing a random variable input for use later.
																																	double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																	cv$temp$12$var241 = var241;
																																}
																																double cv$temp$13$var243;
																																{
																																	// Constructing a random variable input for use later.
																																	double var243 = traceTempVariable$var243$12_1;
																																	cv$temp$13$var243 = var243;
																																}
																																
																																// Record the probability of sample task 256 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value8) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))));
																																}
																																
																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value8);
																															}
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
																						for(int index$sample$49 = 0; index$sample$49 < noSamples; index$sample$49 += 1) {
																							for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$49][0]; timeStep$var66 += 1) {
																								if(true) {
																									// Enumerating the possible outputs of Categorical 73.
																									for(int index$sample76$51 = 0; index$sample76$51 < noStates; index$sample76$51 += 1) {
																										int distributionTempVariable$var74$53 = index$sample76$51;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample76Value52 = (cv$probabilitySample57Value8 * distribution$sample76[((index$sample$49 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$51]);
																										if((index$sample$49 == sample$var196)) {
																											if((timeStep$var66 == timeStep$var226)) {
																												for(int var119 = 0; var119 < noServers; var119 += 1) {
																													for(int var129 = 0; var129 < noStates; var129 += 1) {
																														if((var119 == server)) {
																															if((var129 == st[sample$var196][timeStep$var226])) {
																																{
																																	{
																																		double cv$temp$14$var241;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																			cv$temp$14$var241 = var241;
																																		}
																																		double cv$temp$15$var243;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var243 = traceTempVariable$var243$12_1;
																																			cv$temp$15$var243 = var243;
																																		}
																																		
																																		// Record the probability of sample task 256 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 256 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value52);
																																	}
																																}
																															}
																														}
																													}
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
							for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
								for(int server = 0; server < noServers; server += 1) {
									for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
											if(fixedFlag$sample76) {
												for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
													for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
														if((sample$var45 == sample$var196)) {
															if((timeStep$var66 == timeStep$var226)) {
																double traceTempVariable$var243$23_1 = cv$currentValue;
																if((var146 == server)) {
																	if((var156 == st[sample$var196][timeStep$var226])) {
																		// Processing sample task 256 of consumer random variable null.
																		{
																			// Set an accumulator to sum the probabilities for each possible configuration of
																			// inputs.
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			
																			// Set an accumulator to record the consumer distributions not seen. Initially set
																			// to 1 as seen values will be deducted from this value.
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																				// the output of Sample task 162.
																				if(fixedFlag$sample57) {
																					for(int index$sample$57_1 = 0; index$sample$57_1 < noSamples; index$sample$57_1 += 1) {
																						if((index$sample$57_1 == sample$var196)) {
																							if((0 == timeStep$var226)) {
																								for(int var119 = 0; var119 < noServers; var119 += 1) {
																									for(int var129 = 0; var129 < noStates; var129 += 1) {
																										if((var119 == server)) {
																											if((var129 == st[sample$var196][timeStep$var226])) {
																												{
																													{
																														double cv$temp$16$var241;
																														{
																															// Constructing a random variable input for use later.
																															double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																															cv$temp$16$var241 = var241;
																														}
																														double cv$temp$17$var243;
																														{
																															// Constructing a random variable input for use later.
																															double var243 = traceTempVariable$var243$23_1;
																															cv$temp$17$var243 = var243;
																														}
																														
																														// Record the probability of sample task 256 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))));
																														}
																														
																														// Recorded the probability of reaching sample task 256 with the current configuration.
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
																				} else {
																					for(int index$sample$58 = 0; index$sample$58 < noSamples; index$sample$58 += 1) {
																						if(true) {
																							// Enumerating the possible outputs of Categorical 54.
																							for(int index$sample57$59 = 0; index$sample57$59 < noStates; index$sample57$59 += 1) {
																								int distributionTempVariable$var55$61 = index$sample57$59;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample57Value60 = (1.0 * distribution$sample57[((index$sample$58 - 0) / 1)][index$sample57$59]);
																								if((index$sample$58 == sample$var196)) {
																									if((0 == timeStep$var226)) {
																										for(int var119 = 0; var119 < noServers; var119 += 1) {
																											for(int var129 = 0; var129 < noStates; var129 += 1) {
																												if((var119 == server)) {
																													if((var129 == st[sample$var196][timeStep$var226])) {
																														{
																															{
																																double cv$temp$18$var241;
																																{
																																	// Constructing a random variable input for use later.
																																	double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																	cv$temp$18$var241 = var241;
																																}
																																double cv$temp$19$var243;
																																{
																																	// Constructing a random variable input for use later.
																																	double var243 = traceTempVariable$var243$23_1;
																																	cv$temp$19$var243 = var243;
																																}
																																
																																// Record the probability of sample task 256 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample57Value60) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value60) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value60) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value60) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value60) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))));
																																}
																																
																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value60);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				
																				// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																				// the output of Sample task 162.
																				for(int index$sample$65_1 = 0; index$sample$65_1 < noSamples; index$sample$65_1 += 1) {
																					for(int index$timeStep$65_2 = 1; index$timeStep$65_2 < length$metric[index$sample$65_1][0]; index$timeStep$65_2 += 1) {
																						if((index$sample$65_1 == sample$var196)) {
																							if((index$timeStep$65_2 == timeStep$var226)) {
																								for(int var119 = 0; var119 < noServers; var119 += 1) {
																									for(int var129 = 0; var129 < noStates; var129 += 1) {
																										if((var119 == server)) {
																											if((var129 == st[sample$var196][timeStep$var226])) {
																												{
																													{
																														double cv$temp$20$var241;
																														{
																															// Constructing a random variable input for use later.
																															double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																															cv$temp$20$var241 = var241;
																														}
																														double cv$temp$21$var243;
																														{
																															// Constructing a random variable input for use later.
																															double var243 = traceTempVariable$var243$23_1;
																															cv$temp$21$var243 = var243;
																														}
																														
																														// Record the probability of sample task 256 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))));
																														}
																														
																														// Recorded the probability of reaching sample task 256 with the current configuration.
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
											} else {
												for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
													for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
														if(true) {
															// Enumerating the possible outputs of Categorical 73.
															for(int index$sample76$19 = 0; index$sample76$19 < noStates; index$sample76$19 += 1) {
																int distributionTempVariable$var74$21 = index$sample76$19;
																
																// Update the probability of sampling this value from the distribution value.
																double cv$probabilitySample76Value20 = (1.0 * distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$19]);
																if((sample$var45 == sample$var196)) {
																	if((timeStep$var66 == timeStep$var226)) {
																		double traceTempVariable$var243$24_1 = cv$currentValue;
																		if((var146 == server)) {
																			if((var156 == st[sample$var196][timeStep$var226])) {
																				// Processing sample task 256 of consumer random variable null.
																				{
																					// Set an accumulator to sum the probabilities for each possible configuration of
																					// inputs.
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					
																					// Set an accumulator to record the consumer distributions not seen. Initially set
																					// to 1 as seen values will be deducted from this value.
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																						// the output of Sample task 162.
																						if(fixedFlag$sample57) {
																							for(int index$sample$67_1 = 0; index$sample$67_1 < noSamples; index$sample$67_1 += 1) {
																								if((index$sample$67_1 == sample$var196)) {
																									if((0 == timeStep$var226)) {
																										for(int var119 = 0; var119 < noServers; var119 += 1) {
																											for(int var129 = 0; var129 < noStates; var129 += 1) {
																												if((var119 == server)) {
																													if((var129 == st[sample$var196][timeStep$var226])) {
																														{
																															{
																																double cv$temp$22$var241;
																																{
																																	// Constructing a random variable input for use later.
																																	double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																	cv$temp$22$var241 = var241;
																																}
																																double cv$temp$23$var243;
																																{
																																	// Constructing a random variable input for use later.
																																	double var243 = traceTempVariable$var243$24_1;
																																	cv$temp$23$var243 = var243;
																																}
																																
																																// Record the probability of sample task 256 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))));
																																}
																																
																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value20);
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
																							for(int index$sample$68 = 0; index$sample$68 < noSamples; index$sample$68 += 1) {
																								if(true) {
																									// Enumerating the possible outputs of Categorical 54.
																									for(int index$sample57$69 = 0; index$sample57$69 < noStates; index$sample57$69 += 1) {
																										int distributionTempVariable$var55$71 = index$sample57$69;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample57Value70 = (cv$probabilitySample76Value20 * distribution$sample57[((index$sample$68 - 0) / 1)][index$sample57$69]);
																										if((index$sample$68 == sample$var196)) {
																											if((0 == timeStep$var226)) {
																												for(int var119 = 0; var119 < noServers; var119 += 1) {
																													for(int var129 = 0; var129 < noStates; var129 += 1) {
																														if((var119 == server)) {
																															if((var129 == st[sample$var196][timeStep$var226])) {
																																{
																																	{
																																		double cv$temp$24$var241;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																			cv$temp$24$var241 = var241;
																																		}
																																		double cv$temp$25$var243;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var243 = traceTempVariable$var243$24_1;
																																			cv$temp$25$var243 = var243;
																																		}
																																		
																																		// Record the probability of sample task 256 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample57Value70) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value70) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value70) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value70) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value70) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 256 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value70);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						
																						// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																						// the output of Sample task 162.
																						if((sample$var45 == sample$var196)) {
																							if((timeStep$var66 == timeStep$var226)) {
																								for(int var119 = 0; var119 < noServers; var119 += 1) {
																									for(int var129 = 0; var129 < noStates; var129 += 1) {
																										if((var119 == server)) {
																											if((var129 == st[sample$var196][timeStep$var226])) {
																												{
																													{
																														double cv$temp$26$var241;
																														{
																															// Constructing a random variable input for use later.
																															double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																															cv$temp$26$var241 = var241;
																														}
																														double cv$temp$27$var243;
																														{
																															// Constructing a random variable input for use later.
																															double var243 = traceTempVariable$var243$24_1;
																															cv$temp$27$var243 = var243;
																														}
																														
																														// Record the probability of sample task 256 generating output with current configuration.
																														if(((Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value20) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))));
																														}
																														
																														// Recorded the probability of reaching sample task 256 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value20);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$sample$76 = 0; index$sample$76 < noSamples; index$sample$76 += 1) {
																							for(int index$timeStep$77 = 1; index$timeStep$77 < length$metric[index$sample$76][0]; index$timeStep$77 += 1) {
																								if(!((index$sample$76 == sample$var45) && (index$timeStep$77 == timeStep$var66))) {
																									// Enumerating the possible outputs of Categorical 73.
																									for(int index$sample76$78 = 0; index$sample76$78 < noStates; index$sample76$78 += 1) {
																										int distributionTempVariable$var74$80 = index$sample76$78;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample76Value79 = (cv$probabilitySample76Value20 * distribution$sample76[((index$sample$76 - 0) / 1)][((index$timeStep$77 - 1) / 1)][index$sample76$78]);
																										if((index$sample$76 == sample$var196)) {
																											if((index$timeStep$77 == timeStep$var226)) {
																												for(int var119 = 0; var119 < noServers; var119 += 1) {
																													for(int var129 = 0; var129 < noStates; var129 += 1) {
																														if((var119 == server)) {
																															if((var129 == st[sample$var196][timeStep$var226])) {
																																{
																																	{
																																		double cv$temp$28$var241;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																			cv$temp$28$var241 = var241;
																																		}
																																		double cv$temp$29$var243;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var243 = traceTempVariable$var243$24_1;
																																			cv$temp$29$var243 = var243;
																																		}
																																		
																																		// Record the probability of sample task 256 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample76Value79) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value79) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value79) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value79) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value79) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 256 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value79);
																																	}
																																}
																															}
																														}
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
				double var157 = cv$originalValue;
				
				// Guards to ensure that current_metric_var is only updated when there is a valid
				// path.
				{
					{
						double[] var147 = current_metric_var[var146];
						var147[var156] = var157;
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 190 drawn from Beta 162. Inference was performed using a Beta to
	// Bernoulli/Binomial conjugate prior.
	private final void sample190(int var173, int var183, int threadID$cv$var183, Rng RNG$) {
		if(true) {
			// Local variable to record the number of true samples.
			double cv$sum = 0.0;
			
			// Local variable to record the number of samples.
			double cv$count = 0.0;
			{
				// Processing random variable 231.
				{
					// Looking for a path between Sample 190 and consumer Bernoulli 231.
					{
						for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
									if(fixedFlag$sample57) {
										for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
											if((sample$var45 == sample$var196)) {
												if((0 == timeStep$var226)) {
													if((var173 == server)) {
														if((var183 == st[sample$var196][timeStep$var226])) {
															// Processing sample task 241 of consumer random variable null.
															{
																{
																	{
																		{
																			{
																				// Include the value sampled by task 241 from random variable var231.
																				// Increment the number of samples.
																				cv$count = (cv$count + 1.0);
																				
																				// If the sample value was positive increase the count
																				if(metric_valid_g[sample$var196][server][timeStep$var226])
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
										for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 54.
												for(int index$sample57$6 = 0; index$sample57$6 < noStates; index$sample57$6 += 1) {
													int distributionTempVariable$var55$8 = index$sample57$6;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample57Value7 = (1.0 * distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$6]);
													if((sample$var45 == sample$var196)) {
														if((0 == timeStep$var226)) {
															if((var173 == server)) {
																if((var183 == st[sample$var196][timeStep$var226])) {
																	// Processing sample task 241 of consumer random variable null.
																	{
																		{
																			{
																				{
																					{
																						// Include the value sampled by task 241 from random variable var231.
																						// Increment the number of samples.
																						cv$count = (cv$count + cv$probabilitySample57Value7);
																						
																						// If the sample value was positive increase the count
																						if(metric_valid_g[sample$var196][server][timeStep$var226])
																							cv$sum = (cv$sum + cv$probabilitySample57Value7);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
									if(fixedFlag$sample76) {
										for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
											for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
												if((sample$var45 == sample$var196)) {
													if((timeStep$var66 == timeStep$var226)) {
														if((var173 == server)) {
															if((var183 == st[sample$var196][timeStep$var226])) {
																// Processing sample task 241 of consumer random variable null.
																{
																	{
																		{
																			{
																				{
																					// Include the value sampled by task 241 from random variable var231.
																					// Increment the number of samples.
																					cv$count = (cv$count + 1.0);
																					
																					// If the sample value was positive increase the count
																					if(metric_valid_g[sample$var196][server][timeStep$var226])
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
										for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
											for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
												if(true) {
													// Enumerating the possible outputs of Categorical 73.
													for(int index$sample76$18 = 0; index$sample76$18 < noStates; index$sample76$18 += 1) {
														int distributionTempVariable$var74$20 = index$sample76$18;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample76Value19 = (1.0 * distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$18]);
														if((sample$var45 == sample$var196)) {
															if((timeStep$var66 == timeStep$var226)) {
																if((var173 == server)) {
																	if((var183 == st[sample$var196][timeStep$var226])) {
																		// Processing sample task 241 of consumer random variable null.
																		{
																			{
																				{
																					{
																						{
																							// Include the value sampled by task 241 from random variable var231.
																							// Increment the number of samples.
																							cv$count = (cv$count + cv$probabilitySample76Value19);
																							
																							// If the sample value was positive increase the count
																							if(metric_valid_g[sample$var196][server][timeStep$var226])
																								cv$sum = (cv$sum + cv$probabilitySample76Value19);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
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
			double var184 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
			
			// Guards to ensure that current_metric_valid_bias is only updated when there is a
			// valid path.
			{
				{
					double[] var174 = current_metric_valid_bias[var173];
					var174[var183] = var184;
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 20 drawn from Dirichlet 19. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample20() {
		if(true) {
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = initialStateDistribution;
			
			// A local reference to the scratch space.
			double[] cv$countLocal = cv$var20$countGlobal;
			
			// Get the length of the array
			int cv$arrayLength = noStates;
			
			// Initialize the array values to 0.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				// Processing random variable 54.
				{
					{
						for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
							if(fixedFlag$sample57) {
								// Processing sample task 57 of consumer random variable null.
								{
									// Copy of index so that its values can be safely substituted
									int index$sample$3 = sample$var45;
									{
										{
											{
												{
													// Increment the sample counter with the value sampled by sample task 57 of random
													// variable var54
													cv$countLocal[st[sample$var45][0]] = (cv$countLocal[st[sample$var45][0]] + 1.0);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 54.
			{
				{
					for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
						if(!fixedFlag$sample57) {
							// Processing sample task 57 of consumer random variable null.
							{
								// Copy of index so that its values can be safely substituted
								int index$sample$7 = sample$var45;
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
											cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample57[((sample$var45 - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
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
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 33 drawn from Dirichlet 21. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample33(int var32, int threadID$cv$var32, Rng RNG$) {
		if(true) {
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = m[var32];
			
			// A local reference to the scratch space.
			double[] cv$countLocal = cv$var33$countGlobal[threadID$cv$var32];
			
			// Get the length of the array
			int cv$arrayLength = noStates;
			
			// Initialize the array values to 0.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				// Processing random variable 73.
				{
					// Looking for a path between Sample 33 and consumer Categorical 73.
					{
						for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
							for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
								if(fixedFlag$sample57) {
									for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
										if((index$sample$3_1 == sample$var45)) {
											if((0 == (timeStep$var66 - 1))) {
												if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
													if(fixedFlag$sample76) {
														// Processing sample task 76 of consumer random variable null.
														{
															// Copy of index so that its values can be safely substituted
															int index$timeStep$23 = timeStep$var66;
															
															// Copy of index so that its values can be safely substituted
															int index$sample$24 = sample$var45;
															{
																{
																	{
																		{
																			// Increment the sample counter with the value sampled by sample task 76 of random
																			// variable var73
																			cv$countLocal[st[sample$var45][timeStep$var66]] = (cv$countLocal[st[sample$var45][timeStep$var66]] + 1.0);
																		}
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
											// Enumerating the possible outputs of Categorical 54.
											for(int index$sample57$5 = 0; index$sample57$5 < noStates; index$sample57$5 += 1) {
												int distributionTempVariable$var55$7 = index$sample57$5;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample57Value6 = (1.0 * distribution$sample57[((index$sample$4 - 0) / 1)][index$sample57$5]);
												if((index$sample$4 == sample$var45)) {
													if((0 == (timeStep$var66 - 1))) {
														if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
															if(fixedFlag$sample76) {
																// Processing sample task 76 of consumer random variable null.
																{
																	// Copy of index so that its values can be safely substituted
																	int index$timeStep$26 = timeStep$var66;
																	
																	// Copy of index so that its values can be safely substituted
																	int index$sample$27 = sample$var45;
																	{
																		{
																			{
																				{
																					// Increment the sample counter with the value sampled by sample task 76 of random
																					// variable var73
																					cv$countLocal[st[sample$var45][timeStep$var66]] = (cv$countLocal[st[sample$var45][timeStep$var66]] + cv$probabilitySample57Value6);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
							for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
								if(fixedFlag$sample76) {
									for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
										for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
											if((index$sample$13_1 == sample$var45)) {
												if((index$timeStep$13_2 == (timeStep$var66 - 1))) {
													if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
														if(fixedFlag$sample76) {
															// Processing sample task 76 of consumer random variable null.
															{
																// Copy of index so that its values can be safely substituted
																int index$timeStep$29 = timeStep$var66;
																
																// Copy of index so that its values can be safely substituted
																int index$sample$30 = sample$var45;
																{
																	{
																		{
																			{
																				// Increment the sample counter with the value sampled by sample task 76 of random
																				// variable var73
																				cv$countLocal[st[sample$var45][timeStep$var66]] = (cv$countLocal[st[sample$var45][timeStep$var66]] + 1.0);
																			}
																		}
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
												// Enumerating the possible outputs of Categorical 73.
												for(int index$sample76$16 = 0; index$sample76$16 < noStates; index$sample76$16 += 1) {
													int distributionTempVariable$var74$18 = index$sample76$16;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample76Value17 = (1.0 * distribution$sample76[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample76$16]);
													if((index$sample$14 == sample$var45)) {
														if((index$timeStep$15 == (timeStep$var66 - 1))) {
															if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
																if(fixedFlag$sample76) {
																	// Processing sample task 76 of consumer random variable null.
																	{
																		// Copy of index so that its values can be safely substituted
																		int index$timeStep$32 = timeStep$var66;
																		
																		// Copy of index so that its values can be safely substituted
																		int index$sample$33 = sample$var45;
																		{
																			{
																				{
																					{
																						// Increment the sample counter with the value sampled by sample task 76 of random
																						// variable var73
																						cv$countLocal[st[sample$var45][timeStep$var66]] = (cv$countLocal[st[sample$var45][timeStep$var66]] + cv$probabilitySample76Value17);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 73.
			{
				// Looking for a path between Sample 33 and consumer Categorical 73.
				{
					for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(fixedFlag$sample57) {
								for(int index$sample$40_1 = 0; index$sample$40_1 < noSamples; index$sample$40_1 += 1) {
									if((index$sample$40_1 == sample$var45)) {
										if((0 == (timeStep$var66 - 1))) {
											if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
												if(!fixedFlag$sample76) {
													// Processing sample task 76 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$timeStep$60 = timeStep$var66;
														
														// Copy of index so that its values can be safely substituted
														int index$sample$61 = sample$var45;
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
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
										// Enumerating the possible outputs of Categorical 54.
										for(int index$sample57$42 = 0; index$sample57$42 < noStates; index$sample57$42 += 1) {
											int distributionTempVariable$var55$44 = index$sample57$42;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample57Value43 = (1.0 * distribution$sample57[((index$sample$41 - 0) / 1)][index$sample57$42]);
											if((index$sample$41 == sample$var45)) {
												if((0 == (timeStep$var66 - 1))) {
													if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
														if(!fixedFlag$sample76) {
															// Processing sample task 76 of consumer random variable null.
															{
																// Copy of index so that its values can be safely substituted
																int index$timeStep$63 = timeStep$var66;
																
																// Copy of index so that its values can be safely substituted
																int index$sample$64 = sample$var45;
																{
																	{
																		// Declare and zero an accumulator for tracking the reached source probability space.
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			// Add the probability of this argument configuration.
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		
																		// The probability of reaching the consumer with this set of consumer arguments
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample57Value43);
																		
																		// Merge the distribution probabilities into the count
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(fixedFlag$sample76) {
								for(int index$sample$50_1 = 0; index$sample$50_1 < noSamples; index$sample$50_1 += 1) {
									for(int index$timeStep$50_2 = 1; index$timeStep$50_2 < length$metric[index$sample$50_1][0]; index$timeStep$50_2 += 1) {
										if((index$sample$50_1 == sample$var45)) {
											if((index$timeStep$50_2 == (timeStep$var66 - 1))) {
												if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
													if(!fixedFlag$sample76) {
														// Processing sample task 76 of consumer random variable null.
														{
															// Copy of index so that its values can be safely substituted
															int index$timeStep$66 = timeStep$var66;
															
															// Copy of index so that its values can be safely substituted
															int index$sample$67 = sample$var45;
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
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
											// Enumerating the possible outputs of Categorical 73.
											for(int index$sample76$53 = 0; index$sample76$53 < noStates; index$sample76$53 += 1) {
												int distributionTempVariable$var74$55 = index$sample76$53;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample76Value54 = (1.0 * distribution$sample76[((index$sample$51 - 0) / 1)][((index$timeStep$52 - 1) / 1)][index$sample76$53]);
												if((index$sample$51 == sample$var45)) {
													if((index$timeStep$52 == (timeStep$var66 - 1))) {
														if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
															if(!fixedFlag$sample76) {
																// Processing sample task 76 of consumer random variable null.
																{
																	// Copy of index so that its values can be safely substituted
																	int index$timeStep$69 = timeStep$var66;
																	
																	// Copy of index so that its values can be safely substituted
																	int index$sample$70 = sample$var45;
																	{
																		{
																			// Declare and zero an accumulator for tracking the reached source probability space.
																			double scopeVariable$reachedSourceProbability = 0.0;
																			{
																				// Add the probability of this argument configuration.
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																			}
																			
																			// The probability of reaching the consumer with this set of consumer arguments
																			double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample76Value54);
																			
																			// Merge the distribution probabilities into the count
																			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																				cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																		}
																	}
																}
															}
														}
													}
												}
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
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 57 drawn from Categorical 54. Inference was performed using variable
	// marginalization.
	private final void sample57(int sample$var45, int threadID$cv$sample$var45, Rng RNG$) {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			
			// Copy of index so that its values can be safely substituted
			int index$sample$1 = sample$var45;
			{
				// variable marginalization
				cv$numNumStates = Math.max(cv$numNumStates, noStates);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var55$stateProbabilityGlobal[threadID$cv$sample$var45];
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				// Copy of index so that its values can be safely substituted
				int index$sample$2 = sample$var45;
				
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
					int cv$temp$1$$var3018;
					{
						// Constructing a random variable input for use later.
						int $var3018 = noStates;
						cv$temp$1$$var3018 = $var3018;
					}
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var3018))?Math.log(cv$temp$0$initialStateDistribution[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 73.
					{
						// Looking for a path between Sample 57 and consumer Categorical 73.
						{
							int traceTempVariable$var71$3_1 = cv$currentValue;
							for(int index$sample$3_2 = 0; index$sample$3_2 < noSamples; index$sample$3_2 += 1) {
								if((sample$var45 == index$sample$3_2)) {
									for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$3_2][0]; timeStep$var66 += 1) {
										if((0 == (timeStep$var66 - 1))) {
											if(fixedFlag$sample76) {
												// Processing sample task 76 of consumer random variable null.
												{
													// Copy of index so that its values can be safely substituted
													int index$timeStep$5 = timeStep$var66;
													
													// Copy of index so that its values can be safely substituted
													int index$sample$6 = index$sample$3_2;
													
													// Set an accumulator to sum the probabilities for each possible configuration of
													// inputs.
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													
													// Set an accumulator to record the consumer distributions not seen. Initially set
													// to 1 as seen values will be deducted from this value.
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														// Enumerating the possible arguments for the variable Categorical 73 which is consuming
														// the output of Sample task 57.
														for(int var32 = 0; var32 < noStates; var32 += 1) {
															if((var32 == st[index$sample$3_2][(timeStep$var66 - 1)])) {
																{
																	{
																		double[] cv$temp$2$var72;
																		{
																			// Constructing a random variable input for use later.
																			double[] var72 = m[traceTempVariable$var71$3_1];
																			cv$temp$2$var72 = var72;
																		}
																		int cv$temp$3$$var3031;
																		{
																			// Constructing a random variable input for use later.
																			int $var3031 = noStates;
																			cv$temp$3$$var3031 = $var3031;
																		}
																		
																		// Record the probability of sample task 76 generating output with current configuration.
																		if(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var66]) && (st[index$sample$3_2][timeStep$var66] < cv$temp$3$$var3031))?Math.log(cv$temp$2$var72[st[index$sample$3_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var66]) && (st[index$sample$3_2][timeStep$var66] < cv$temp$3$$var3031))?Math.log(cv$temp$2$var72[st[index$sample$3_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var66]) && (st[index$sample$3_2][timeStep$var66] < cv$temp$3$$var3031))?Math.log(cv$temp$2$var72[st[index$sample$3_2][timeStep$var66]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var66]) && (st[index$sample$3_2][timeStep$var66] < cv$temp$3$$var3031))?Math.log(cv$temp$2$var72[st[index$sample$3_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var66]) && (st[index$sample$3_2][timeStep$var66] < cv$temp$3$$var3031))?Math.log(cv$temp$2$var72[st[index$sample$3_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)));
																		}
																		
																		// Recorded the probability of reaching sample task 76 with the current configuration.
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
					
					// Processing random variable 231.
					{
						// Looking for a path between Sample 57 and consumer Bernoulli 231.
						{
							int traceTempVariable$currentState$9_1 = cv$currentValue;
							for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
								if((sample$var45 == sample$var196)) {
									for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
										if((0 == timeStep$var226)) {
											for(int server = 0; server < noServers; server += 1) {
												// Set an accumulator to sum the probabilities for each possible configuration of
												// inputs.
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												
												// Set an accumulator to record the consumer distributions not seen. Initially set
												// to 1 as seen values will be deducted from this value.
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													// Enumerating the possible arguments for the variable Bernoulli 231 which is consuming
													// the output of Sample task 57.
													for(int var173 = 0; var173 < noServers; var173 += 1) {
														for(int var183 = 0; var183 < noStates; var183 += 1) {
															if((var173 == server)) {
																if((var183 == st[sample$var196][timeStep$var226])) {
																	{
																		{
																			double cv$temp$4$var230;
																			{
																				// Constructing a random variable input for use later.
																				double var230 = current_metric_valid_bias[server][traceTempVariable$currentState$9_1];
																				cv$temp$4$var230 = var230;
																			}
																			
																			// Record the probability of sample task 241 generating output with current configuration.
																			if(((Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$4$var230:(1.0 - cv$temp$4$var230)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$4$var230:(1.0 - cv$temp$4$var230)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$4$var230:(1.0 - cv$temp$4$var230))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$4$var230:(1.0 - cv$temp$4$var230)))))) + 1)) + (Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$4$var230:(1.0 - cv$temp$4$var230)))));
																			}
																			
																			// Recorded the probability of reaching sample task 241 with the current configuration.
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
					
					// Processing random variable 244.
					{
						// Looking for a path between Sample 57 and consumer Gaussian 244.
						{
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[][][] guard$sample57gaussian255 = guard$sample57gaussian255$global[threadID$cv$sample$var45];
							for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
								if((sample$var45 == sample$var196)) {
									for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
										if((0 == timeStep$var226)) {
											for(int server = 0; server < noServers; server += 1) {
												if(metric_valid_g[sample$var196][server][timeStep$var226])
													// Set the flags to false
													guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
								if((sample$var45 == sample$var196)) {
									for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
										if((0 == timeStep$var226)) {
											for(int server = 0; server < noServers; server += 1) {
												if(metric_valid_g[sample$var196][server][timeStep$var226])
													// Set the flags to false
													guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							int traceTempVariable$currentState$15_1 = cv$currentValue;
							for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
								if((sample$var45 == sample$var196)) {
									for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
										if((0 == timeStep$var226)) {
											for(int server = 0; server < noServers; server += 1) {
												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
													if(!guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
														
														// Processing sample task 256 of consumer random variable null.
														{
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																// the output of Sample task 57.
																for(int var119 = 0; var119 < noServers; var119 += 1) {
																	for(int var129 = 0; var129 < noStates; var129 += 1) {
																		if((var119 == server)) {
																			if((var129 == st[sample$var196][timeStep$var226])) {
																				int traceTempVariable$currentState$20_1 = cv$currentValue;
																				if((index$sample$2 == sample$var196)) {
																					if((0 == timeStep$var226)) {
																						for(int var146 = 0; var146 < noServers; var146 += 1) {
																							for(int var156 = 0; var156 < noStates; var156 += 1) {
																								if((var146 == server)) {
																									if((var156 == st[sample$var196][timeStep$var226])) {
																										{
																											{
																												double cv$temp$5$var241;
																												{
																													// Constructing a random variable input for use later.
																													double var241 = current_metric_mean[server][traceTempVariable$currentState$20_1];
																													cv$temp$5$var241 = var241;
																												}
																												double cv$temp$6$var243;
																												{
																													// Constructing a random variable input for use later.
																													double var243 = current_metric_var[server][traceTempVariable$currentState$20_1];
																													cv$temp$6$var243 = var243;
																												}
																												
																												// Record the probability of sample task 256 generating output with current configuration.
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$5$var241) / Math.sqrt(cv$temp$6$var243))) - (0.5 * Math.log(cv$temp$6$var243)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$5$var241) / Math.sqrt(cv$temp$6$var243))) - (0.5 * Math.log(cv$temp$6$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$5$var241) / Math.sqrt(cv$temp$6$var243))) - (0.5 * Math.log(cv$temp$6$var243))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$5$var241) / Math.sqrt(cv$temp$6$var243))) - (0.5 * Math.log(cv$temp$6$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$5$var241) / Math.sqrt(cv$temp$6$var243))) - (0.5 * Math.log(cv$temp$6$var243)))));
																												}
																												
																												// Recorded the probability of reaching sample task 256 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				for(int index$sample$21 = 0; index$sample$21 < noSamples; index$sample$21 += 1) {
																					if(!(index$sample$21 == index$sample$2)) {
																						// Enumerating the possible outputs of Categorical 54.
																						for(int index$sample57$22 = 0; index$sample57$22 < noStates; index$sample57$22 += 1) {
																							int distributionTempVariable$var55$24 = index$sample57$22;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample57Value23 = (1.0 * distribution$sample57[((index$sample$21 - 0) / 1)][index$sample57$22]);
																							int traceTempVariable$currentState$25_1 = cv$currentValue;
																							if((index$sample$21 == sample$var196)) {
																								if((0 == timeStep$var226)) {
																									for(int var146 = 0; var146 < noServers; var146 += 1) {
																										for(int var156 = 0; var156 < noStates; var156 += 1) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														{
																															double cv$temp$7$var241;
																															{
																																// Constructing a random variable input for use later.
																																double var241 = current_metric_mean[server][traceTempVariable$currentState$25_1];
																																cv$temp$7$var241 = var241;
																															}
																															double cv$temp$8$var243;
																															{
																																// Constructing a random variable input for use later.
																																double var243 = current_metric_var[server][traceTempVariable$currentState$25_1];
																																cv$temp$8$var243 = var243;
																															}
																															
																															// Record the probability of sample task 256 generating output with current configuration.
																															if(((Math.log(cv$probabilitySample57Value23) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$7$var241) / Math.sqrt(cv$temp$8$var243))) - (0.5 * Math.log(cv$temp$8$var243)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value23) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$7$var241) / Math.sqrt(cv$temp$8$var243))) - (0.5 * Math.log(cv$temp$8$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																// If the second value is -infinity.
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value23) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$7$var241) / Math.sqrt(cv$temp$8$var243))) - (0.5 * Math.log(cv$temp$8$var243))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value23) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$7$var241) / Math.sqrt(cv$temp$8$var243))) - (0.5 * Math.log(cv$temp$8$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value23) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$7$var241) / Math.sqrt(cv$temp$8$var243))) - (0.5 * Math.log(cv$temp$8$var243)))));
																															}
																															
																															// Recorded the probability of reaching sample task 256 with the current configuration.
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value23);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																
																// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																// the output of Sample task 57.
																for(int var119 = 0; var119 < noServers; var119 += 1) {
																	for(int var129 = 0; var129 < noStates; var129 += 1) {
																		if((var119 == server)) {
																			if((var129 == st[sample$var196][timeStep$var226])) {
																				if(fixedFlag$sample76) {
																					for(int index$sample$29_1 = 0; index$sample$29_1 < noSamples; index$sample$29_1 += 1) {
																						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$29_1][0]; timeStep$var66 += 1) {
																							if((index$sample$29_1 == sample$var196)) {
																								if((timeStep$var66 == timeStep$var226)) {
																									for(int var146 = 0; var146 < noServers; var146 += 1) {
																										for(int var156 = 0; var156 < noStates; var156 += 1) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														{
																															double cv$temp$9$var241;
																															{
																																// Constructing a random variable input for use later.
																																double var241 = current_metric_mean[server][traceTempVariable$currentState$15_1];
																																cv$temp$9$var241 = var241;
																															}
																															double cv$temp$10$var243;
																															{
																																// Constructing a random variable input for use later.
																																double var243 = current_metric_var[server][traceTempVariable$currentState$15_1];
																																cv$temp$10$var243 = var243;
																															}
																															
																															// Record the probability of sample task 256 generating output with current configuration.
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$9$var241) / Math.sqrt(cv$temp$10$var243))) - (0.5 * Math.log(cv$temp$10$var243)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$9$var241) / Math.sqrt(cv$temp$10$var243))) - (0.5 * Math.log(cv$temp$10$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																// If the second value is -infinity.
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$9$var241) / Math.sqrt(cv$temp$10$var243))) - (0.5 * Math.log(cv$temp$10$var243))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$9$var241) / Math.sqrt(cv$temp$10$var243))) - (0.5 * Math.log(cv$temp$10$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$9$var241) / Math.sqrt(cv$temp$10$var243))) - (0.5 * Math.log(cv$temp$10$var243)))));
																															}
																															
																															// Recorded the probability of reaching sample task 256 with the current configuration.
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
																				} else {
																					for(int index$sample$30 = 0; index$sample$30 < noSamples; index$sample$30 += 1) {
																						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$30][0]; timeStep$var66 += 1) {
																							if(true) {
																								// Enumerating the possible outputs of Categorical 73.
																								for(int index$sample76$32 = 0; index$sample76$32 < noStates; index$sample76$32 += 1) {
																									int distributionTempVariable$var74$34 = index$sample76$32;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample76Value33 = (1.0 * distribution$sample76[((index$sample$30 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$32]);
																									if((index$sample$30 == sample$var196)) {
																										if((timeStep$var66 == timeStep$var226)) {
																											for(int var146 = 0; var146 < noServers; var146 += 1) {
																												for(int var156 = 0; var156 < noStates; var156 += 1) {
																													if((var146 == server)) {
																														if((var156 == st[sample$var196][timeStep$var226])) {
																															{
																																{
																																	double cv$temp$11$var241;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var241 = current_metric_mean[server][traceTempVariable$currentState$15_1];
																																		cv$temp$11$var241 = var241;
																																	}
																																	double cv$temp$12$var243;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var243 = current_metric_var[server][traceTempVariable$currentState$15_1];
																																		cv$temp$12$var243 = var243;
																																	}
																																	
																																	// Record the probability of sample task 256 generating output with current configuration.
																																	if(((Math.log(cv$probabilitySample76Value33) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$11$var241) / Math.sqrt(cv$temp$12$var243))) - (0.5 * Math.log(cv$temp$12$var243)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value33) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$11$var241) / Math.sqrt(cv$temp$12$var243))) - (0.5 * Math.log(cv$temp$12$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		// If the second value is -infinity.
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value33) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$11$var241) / Math.sqrt(cv$temp$12$var243))) - (0.5 * Math.log(cv$temp$12$var243))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value33) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$11$var241) / Math.sqrt(cv$temp$12$var243))) - (0.5 * Math.log(cv$temp$12$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value33) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$11$var241) / Math.sqrt(cv$temp$12$var243))) - (0.5 * Math.log(cv$temp$12$var243)))));
																																	}
																																	
																																	// Recorded the probability of reaching sample task 256 with the current configuration.
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value33);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
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
							int traceTempVariable$currentState$16_1 = cv$currentValue;
							for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
								if((sample$var45 == sample$var196)) {
									for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
										if((0 == timeStep$var226)) {
											for(int server = 0; server < noServers; server += 1) {
												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
													if(!guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
														
														// Processing sample task 256 of consumer random variable null.
														{
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																// the output of Sample task 57.
																int traceTempVariable$currentState$38_1 = cv$currentValue;
																if((index$sample$2 == sample$var196)) {
																	if((0 == timeStep$var226)) {
																		for(int var119 = 0; var119 < noServers; var119 += 1) {
																			for(int var129 = 0; var129 < noStates; var129 += 1) {
																				if((var119 == server)) {
																					if((var129 == st[sample$var196][timeStep$var226])) {
																						for(int var146 = 0; var146 < noServers; var146 += 1) {
																							for(int var156 = 0; var156 < noStates; var156 += 1) {
																								if((var146 == server)) {
																									if((var156 == st[sample$var196][timeStep$var226])) {
																										{
																											{
																												double cv$temp$13$var241;
																												{
																													// Constructing a random variable input for use later.
																													double var241 = current_metric_mean[server][traceTempVariable$currentState$38_1];
																													cv$temp$13$var241 = var241;
																												}
																												double cv$temp$14$var243;
																												{
																													// Constructing a random variable input for use later.
																													double var243 = current_metric_var[server][traceTempVariable$currentState$38_1];
																													cv$temp$14$var243 = var243;
																												}
																												
																												// Record the probability of sample task 256 generating output with current configuration.
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$13$var241) / Math.sqrt(cv$temp$14$var243))) - (0.5 * Math.log(cv$temp$14$var243)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$13$var241) / Math.sqrt(cv$temp$14$var243))) - (0.5 * Math.log(cv$temp$14$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$13$var241) / Math.sqrt(cv$temp$14$var243))) - (0.5 * Math.log(cv$temp$14$var243))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$13$var241) / Math.sqrt(cv$temp$14$var243))) - (0.5 * Math.log(cv$temp$14$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$13$var241) / Math.sqrt(cv$temp$14$var243))) - (0.5 * Math.log(cv$temp$14$var243)))));
																												}
																												
																												// Recorded the probability of reaching sample task 256 with the current configuration.
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
																for(int index$sample$39 = 0; index$sample$39 < noSamples; index$sample$39 += 1) {
																	if(!(index$sample$39 == index$sample$2)) {
																		// Enumerating the possible outputs of Categorical 54.
																		for(int index$sample57$40 = 0; index$sample57$40 < noStates; index$sample57$40 += 1) {
																			int distributionTempVariable$var55$42 = index$sample57$40;
																			
																			// Update the probability of sampling this value from the distribution value.
																			double cv$probabilitySample57Value41 = (1.0 * distribution$sample57[((index$sample$39 - 0) / 1)][index$sample57$40]);
																			int traceTempVariable$currentState$43_1 = cv$currentValue;
																			if((index$sample$39 == sample$var196)) {
																				if((0 == timeStep$var226)) {
																					for(int var119 = 0; var119 < noServers; var119 += 1) {
																						for(int var129 = 0; var129 < noStates; var129 += 1) {
																							if((var119 == server)) {
																								if((var129 == st[sample$var196][timeStep$var226])) {
																									for(int var146 = 0; var146 < noServers; var146 += 1) {
																										for(int var156 = 0; var156 < noStates; var156 += 1) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														{
																															double cv$temp$15$var241;
																															{
																																// Constructing a random variable input for use later.
																																double var241 = current_metric_mean[server][traceTempVariable$currentState$43_1];
																																cv$temp$15$var241 = var241;
																															}
																															double cv$temp$16$var243;
																															{
																																// Constructing a random variable input for use later.
																																double var243 = current_metric_var[server][traceTempVariable$currentState$43_1];
																																cv$temp$16$var243 = var243;
																															}
																															
																															// Record the probability of sample task 256 generating output with current configuration.
																															if(((Math.log(cv$probabilitySample57Value41) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$15$var241) / Math.sqrt(cv$temp$16$var243))) - (0.5 * Math.log(cv$temp$16$var243)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value41) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$15$var241) / Math.sqrt(cv$temp$16$var243))) - (0.5 * Math.log(cv$temp$16$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																// If the second value is -infinity.
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value41) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$15$var241) / Math.sqrt(cv$temp$16$var243))) - (0.5 * Math.log(cv$temp$16$var243))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value41) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$15$var241) / Math.sqrt(cv$temp$16$var243))) - (0.5 * Math.log(cv$temp$16$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value41) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$15$var241) / Math.sqrt(cv$temp$16$var243))) - (0.5 * Math.log(cv$temp$16$var243)))));
																															}
																															
																															// Recorded the probability of reaching sample task 256 with the current configuration.
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value41);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																
																// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																// the output of Sample task 57.
																if(fixedFlag$sample76) {
																	for(int index$sample$48_1 = 0; index$sample$48_1 < noSamples; index$sample$48_1 += 1) {
																		for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$48_1][0]; timeStep$var66 += 1) {
																			if((index$sample$48_1 == sample$var196)) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int var119 = 0; var119 < noServers; var119 += 1) {
																						for(int var129 = 0; var129 < noStates; var129 += 1) {
																							if((var119 == server)) {
																								if((var129 == st[sample$var196][timeStep$var226])) {
																									for(int var146 = 0; var146 < noServers; var146 += 1) {
																										for(int var156 = 0; var156 < noStates; var156 += 1) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														{
																															double cv$temp$17$var241;
																															{
																																// Constructing a random variable input for use later.
																																double var241 = current_metric_mean[server][traceTempVariable$currentState$16_1];
																																cv$temp$17$var241 = var241;
																															}
																															double cv$temp$18$var243;
																															{
																																// Constructing a random variable input for use later.
																																double var243 = current_metric_var[server][traceTempVariable$currentState$16_1];
																																cv$temp$18$var243 = var243;
																															}
																															
																															// Record the probability of sample task 256 generating output with current configuration.
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$17$var241) / Math.sqrt(cv$temp$18$var243))) - (0.5 * Math.log(cv$temp$18$var243)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$17$var241) / Math.sqrt(cv$temp$18$var243))) - (0.5 * Math.log(cv$temp$18$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																// If the second value is -infinity.
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$17$var241) / Math.sqrt(cv$temp$18$var243))) - (0.5 * Math.log(cv$temp$18$var243))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$17$var241) / Math.sqrt(cv$temp$18$var243))) - (0.5 * Math.log(cv$temp$18$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$17$var241) / Math.sqrt(cv$temp$18$var243))) - (0.5 * Math.log(cv$temp$18$var243)))));
																															}
																															
																															// Recorded the probability of reaching sample task 256 with the current configuration.
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
																} else {
																	for(int index$sample$49 = 0; index$sample$49 < noSamples; index$sample$49 += 1) {
																		for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$49][0]; timeStep$var66 += 1) {
																			if(true) {
																				// Enumerating the possible outputs of Categorical 73.
																				for(int index$sample76$51 = 0; index$sample76$51 < noStates; index$sample76$51 += 1) {
																					int distributionTempVariable$var74$53 = index$sample76$51;
																					
																					// Update the probability of sampling this value from the distribution value.
																					double cv$probabilitySample76Value52 = (1.0 * distribution$sample76[((index$sample$49 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$51]);
																					if((index$sample$49 == sample$var196)) {
																						if((timeStep$var66 == timeStep$var226)) {
																							for(int var119 = 0; var119 < noServers; var119 += 1) {
																								for(int var129 = 0; var129 < noStates; var129 += 1) {
																									if((var119 == server)) {
																										if((var129 == st[sample$var196][timeStep$var226])) {
																											for(int var146 = 0; var146 < noServers; var146 += 1) {
																												for(int var156 = 0; var156 < noStates; var156 += 1) {
																													if((var146 == server)) {
																														if((var156 == st[sample$var196][timeStep$var226])) {
																															{
																																{
																																	double cv$temp$19$var241;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var241 = current_metric_mean[server][traceTempVariable$currentState$16_1];
																																		cv$temp$19$var241 = var241;
																																	}
																																	double cv$temp$20$var243;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var243 = current_metric_var[server][traceTempVariable$currentState$16_1];
																																		cv$temp$20$var243 = var243;
																																	}
																																	
																																	// Record the probability of sample task 256 generating output with current configuration.
																																	if(((Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$19$var241) / Math.sqrt(cv$temp$20$var243))) - (0.5 * Math.log(cv$temp$20$var243)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$19$var241) / Math.sqrt(cv$temp$20$var243))) - (0.5 * Math.log(cv$temp$20$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		// If the second value is -infinity.
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$19$var241) / Math.sqrt(cv$temp$20$var243))) - (0.5 * Math.log(cv$temp$20$var243))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$19$var241) / Math.sqrt(cv$temp$20$var243))) - (0.5 * Math.log(cv$temp$20$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$19$var241) / Math.sqrt(cv$temp$20$var243))) - (0.5 * Math.log(cv$temp$20$var243)))));
																																	}
																																	
																																	// Recorded the probability of reaching sample task 256 with the current configuration.
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value52);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
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
				
				// Processing random variable 73.
				{
					// Looking for a path between Sample 57 and consumer Categorical 73.
					{
						int traceTempVariable$var71$67_1 = cv$currentValue;
						for(int index$sample$67_2 = 0; index$sample$67_2 < noSamples; index$sample$67_2 += 1) {
							if((sample$var45 == index$sample$67_2)) {
								for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$67_2][0]; timeStep$var66 += 1) {
									if((0 == (timeStep$var66 - 1))) {
										if(!fixedFlag$sample76) {
											// Processing sample task 76 of consumer random variable null.
											{
												// Copy of index so that its values can be safely substituted
												int index$timeStep$69 = timeStep$var66;
												
												// Copy of index so that its values can be safely substituted
												int index$sample$70 = index$sample$67_2;
												
												// A local array to hold the accumulated distributions of the sample tasks for each
												// configuration of distributions.
												double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var73[threadID$cv$sample$var45];
												
												// Zero all the elements in the distribution accumulator
												for(int cv$i = 0; cv$i < noStates; cv$i += 1)
													cv$accumulatedConsumerDistributions[cv$i] = 0.0;
												
												// Zero an accumulator to track the probabilities reached.
												double cv$reachedDistributionProbability = 0.0;
												
												// Enumerating the possible arguments for the variable Categorical 73 which is consuming
												// the output of Sample task 57.
												for(int var32 = 0; var32 < noStates; var32 += 1) {
													if((var32 == st[index$sample$67_2][(timeStep$var66 - 1)])) {
														{
															// Declare and zero an accumulator for tracking the reached source probability space.
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																// Add the probability of this argument configuration.
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double[] cv$temp$21$var72;
															{
																// Constructing a random variable input for use later.
																double[] var72 = m[traceTempVariable$var71$67_1];
																cv$temp$21$var72 = var72;
															}
															int cv$temp$22$$var3244;
															{
																// Constructing a random variable input for use later.
																int $var3244 = noStates;
																cv$temp$22$$var3244 = $var3244;
															}
															
															// The probability of reaching the consumer with this set of consumer arguments
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															
															// Record the reached distribution.
															cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
															
															// Add the current distribution to the distribution accumulator.
															DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$21$var72, cv$temp$22$$var3244);
														}
													}
												}
												
												// A local copy of the samples' distribution.
												double[] cv$sampleDistribution = distribution$sample76[((index$sample$67_2 - 0) / 1)][((timeStep$var66 - 1) / 1)];
												
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
			double[] cv$localProbability = distribution$sample57[((sample$var45 - 0) / 1)];
			
			// The sum of all the probabilities in log space
			double cv$logSum = 0.0;
			
			// Sum all the values
			{
				// Initialise the max to the first element.
				double cv$lseMax = cv$stateProbabilityLocal[0];
				
				// Find max value.
				for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
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
					for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
						cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
					
					// Increment the value of the target, moving the value back into log space.
					cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
				}
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 76 drawn from Categorical 73. Inference was performed using variable
	// marginalization.
	private final void sample76(int sample$var45, int timeStep$var66, int threadID$cv$sample$var45, Rng RNG$) {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			
			// Exploring all the possible state counts for random variable 73.
			// 
			// Copy of index so that its values can be safely substituted
			int index$timeStep$1 = timeStep$var66;
			
			// Copy of index so that its values can be safely substituted
			int index$sample$2 = sample$var45;
			
			// Enumerating the possible arguments for Categorical 73.
			if(fixedFlag$sample57) {
				for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
					if((index$sample$3_1 == sample$var45)) {
						if((0 == (timeStep$var66 - 1))) {
							for(int var32 = 0; var32 < noStates; var32 += 1) {
								if((var32 == st[sample$var45][(timeStep$var66 - 1)]))
									// variable marginalization
									cv$numNumStates = Math.max(cv$numNumStates, noStates);
							}
						}
					}
				}
			} else {
				for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
					if(true) {
						// Enumerating the possible outputs of Categorical 54.
						for(int index$sample57$5 = 0; index$sample57$5 < noStates; index$sample57$5 += 1) {
							int distributionTempVariable$var55$7 = index$sample57$5;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample57Value6 = (1.0 * distribution$sample57[((index$sample$4 - 0) / 1)][index$sample57$5]);
							if((index$sample$4 == sample$var45)) {
								if((0 == (timeStep$var66 - 1))) {
									for(int var32 = 0; var32 < noStates; var32 += 1) {
										if((var32 == st[sample$var45][(timeStep$var66 - 1)]))
											// variable marginalization
											cv$numNumStates = Math.max(cv$numNumStates, noStates);
									}
								}
							}
						}
					}
				}
			}
			
			// Enumerating the possible arguments for Categorical 73.
			if((index$sample$2 == sample$var45)) {
				if((index$timeStep$1 == (timeStep$var66 - 1))) {
					for(int var32 = 0; var32 < noStates; var32 += 1) {
						if((var32 == st[sample$var45][(timeStep$var66 - 1)]))
							// variable marginalization
							cv$numNumStates = Math.max(cv$numNumStates, noStates);
					}
				}
			}
			if(fixedFlag$sample76) {
				for(int index$sample$12_1 = 0; index$sample$12_1 < noSamples; index$sample$12_1 += 1) {
					for(int index$timeStep$12_2 = 1; index$timeStep$12_2 < length$metric[index$sample$12_1][0]; index$timeStep$12_2 += 1) {
						if((index$sample$12_1 == sample$var45)) {
							if((index$timeStep$12_2 == (timeStep$var66 - 1))) {
								for(int var32 = 0; var32 < noStates; var32 += 1) {
									if((var32 == st[sample$var45][(timeStep$var66 - 1)]))
										// variable marginalization
										cv$numNumStates = Math.max(cv$numNumStates, noStates);
								}
							}
						}
					}
				}
			} else {
				for(int index$sample$13 = 0; index$sample$13 < noSamples; index$sample$13 += 1) {
					for(int index$timeStep$14 = 1; index$timeStep$14 < length$metric[index$sample$13][0]; index$timeStep$14 += 1) {
						if(!((index$sample$13 == index$sample$2) && (index$timeStep$14 == index$timeStep$1))) {
							// Enumerating the possible outputs of Categorical 73.
							for(int index$sample76$15 = 0; index$sample76$15 < noStates; index$sample76$15 += 1) {
								int distributionTempVariable$var74$17 = index$sample76$15;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample76Value16 = (1.0 * distribution$sample76[((index$sample$13 - 0) / 1)][((index$timeStep$14 - 1) / 1)][index$sample76$15]);
								if((index$sample$13 == sample$var45)) {
									if((index$timeStep$14 == (timeStep$var66 - 1))) {
										for(int var32 = 0; var32 < noStates; var32 += 1) {
											if((var32 == st[sample$var45][(timeStep$var66 - 1)]))
												// variable marginalization
												cv$numNumStates = Math.max(cv$numNumStates, noStates);
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var74$stateProbabilityGlobal[threadID$cv$sample$var45];
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				// Exploring all the possible distribution values for random variable 73 creating
				// sample task 76.
				// Copy of index so that its values can be safely substituted
				int index$timeStep$22 = timeStep$var66;
				
				// Copy of index so that its values can be safely substituted
				int index$sample$23 = sample$var45;
				
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
				
				// Enumerating the possible arguments for Categorical 73.
				if(fixedFlag$sample57) {
					for(int index$sample$24_1 = 0; index$sample$24_1 < noSamples; index$sample$24_1 += 1) {
						if((index$sample$24_1 == sample$var45)) {
							if((0 == (timeStep$var66 - 1))) {
								for(int var32 = 0; var32 < noStates; var32 += 1) {
									if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
										// Record the reached probability density.
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
										double[] cv$temp$0$var72;
										{
											// Constructing a random variable input for use later.
											double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
											cv$temp$0$var72 = var72;
										}
										int cv$temp$1$$var3330;
										{
											// Constructing a random variable input for use later.
											int $var3330 = noStates;
											cv$temp$1$$var3330 = $var3330;
										}
										
										// An accumulator to allow the value for each distribution to be constructed before
										// it is added to the index probabilities.
										double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var3330))?Math.log(cv$temp$0$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
										
										// Processing random variable 73.
										{
											// Looking for a path between Sample 76 and consumer Categorical 73.
											{
												int traceTempVariable$var71$41_1 = cv$currentValue;
											}
										}
										
										// Processing random variable 231.
										{
											// Looking for a path between Sample 76 and consumer Bernoulli 231.
											{
												int traceTempVariable$currentState$45_1 = cv$currentValue;
												for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
													if((sample$var45 == sample$var196)) {
														for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
															if((timeStep$var66 == timeStep$var226)) {
																for(int server = 0; server < noServers; server += 1) {
																	// Set an accumulator to sum the probabilities for each possible configuration of
																	// inputs.
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	
																	// Set an accumulator to record the consumer distributions not seen. Initially set
																	// to 1 as seen values will be deducted from this value.
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		// Enumerating the possible arguments for the variable Bernoulli 231 which is consuming
																		// the output of Sample task 76.
																		for(int var173 = 0; var173 < noServers; var173 += 1) {
																			for(int var183 = 0; var183 < noStates; var183 += 1) {
																				if((var173 == server)) {
																					if((var183 == st[sample$var196][timeStep$var226])) {
																						{
																							{
																								double cv$temp$8$var230;
																								{
																									// Constructing a random variable input for use later.
																									double var230 = current_metric_valid_bias[server][traceTempVariable$currentState$45_1];
																									cv$temp$8$var230 = var230;
																								}
																								
																								// Record the probability of sample task 241 generating output with current configuration.
																								if(((Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$8$var230:(1.0 - cv$temp$8$var230)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$8$var230:(1.0 - cv$temp$8$var230)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$8$var230:(1.0 - cv$temp$8$var230))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$8$var230:(1.0 - cv$temp$8$var230)))))) + 1)) + (Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$8$var230:(1.0 - cv$temp$8$var230)))));
																								}
																								
																								// Recorded the probability of reaching sample task 241 with the current configuration.
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
										
										// Processing random variable 244.
										{
											// Looking for a path between Sample 76 and consumer Gaussian 244.
											{
												// Guard to check that at most one copy of the code is executed for a given random
												// variable instance.
												boolean[][][] guard$sample76gaussian255 = guard$sample76gaussian255$global[threadID$cv$sample$var45];
												for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
													if((sample$var45 == sample$var196)) {
														for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
															if((timeStep$var66 == timeStep$var226)) {
																for(int server = 0; server < noServers; server += 1) {
																	if(metric_valid_g[sample$var196][server][timeStep$var226])
																		// Set the flags to false
																		guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
													if((sample$var45 == sample$var196)) {
														for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
															if((timeStep$var66 == timeStep$var226)) {
																for(int server = 0; server < noServers; server += 1) {
																	if(metric_valid_g[sample$var196][server][timeStep$var226])
																		// Set the flags to false
																		guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												int traceTempVariable$currentState$69_1 = cv$currentValue;
												for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
													if((sample$var45 == sample$var196)) {
														for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
															if((timeStep$var66 == timeStep$var226)) {
																for(int server = 0; server < noServers; server += 1) {
																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																		if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																			
																			// Processing sample task 256 of consumer random variable null.
																			{
																				// Set an accumulator to sum the probabilities for each possible configuration of
																				// inputs.
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				
																				// Set an accumulator to record the consumer distributions not seen. Initially set
																				// to 1 as seen values will be deducted from this value.
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																					// the output of Sample task 76.
																					for(int var119 = 0; var119 < noServers; var119 += 1) {
																						for(int var129 = 0; var129 < noStates; var129 += 1) {
																							if((var119 == server)) {
																								if((var129 == st[sample$var196][timeStep$var226])) {
																									for(int index$sample$86_1 = 0; index$sample$86_1 < noSamples; index$sample$86_1 += 1) {
																										if((index$sample$86_1 == sample$var196)) {
																											if((0 == timeStep$var226)) {
																												for(int var146 = 0; var146 < noServers; var146 += 1) {
																													for(int var156 = 0; var156 < noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == st[sample$var196][timeStep$var226])) {
																																{
																																	{
																																		double cv$temp$12$var241;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var241 = current_metric_mean[server][traceTempVariable$currentState$69_1];
																																			cv$temp$12$var241 = var241;
																																		}
																																		double cv$temp$13$var243;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var243 = current_metric_var[server][traceTempVariable$currentState$69_1];
																																			cv$temp$13$var243 = var243;
																																		}
																																		
																																		// Record the probability of sample task 256 generating output with current configuration.
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 256 with the current configuration.
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
																					
																					// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																					// the output of Sample task 76.
																					for(int var119 = 0; var119 < noServers; var119 += 1) {
																						for(int var129 = 0; var129 < noStates; var129 += 1) {
																							if((var119 == server)) {
																								if((var129 == st[sample$var196][timeStep$var226])) {
																									int traceTempVariable$currentState$89_1 = cv$currentValue;
																									if((index$sample$23 == sample$var196)) {
																										if((index$timeStep$22 == timeStep$var226)) {
																											for(int var146 = 0; var146 < noServers; var146 += 1) {
																												for(int var156 = 0; var156 < noStates; var156 += 1) {
																													if((var146 == server)) {
																														if((var156 == st[sample$var196][timeStep$var226])) {
																															{
																																{
																																	double cv$temp$14$var241;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var241 = current_metric_mean[server][traceTempVariable$currentState$89_1];
																																		cv$temp$14$var241 = var241;
																																	}
																																	double cv$temp$15$var243;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var243 = current_metric_var[server][traceTempVariable$currentState$89_1];
																																		cv$temp$15$var243 = var243;
																																	}
																																	
																																	// Record the probability of sample task 256 generating output with current configuration.
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		// If the second value is -infinity.
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))));
																																	}
																																	
																																	// Recorded the probability of reaching sample task 256 with the current configuration.
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									for(int index$sample$90 = 0; index$sample$90 < noSamples; index$sample$90 += 1) {
																										for(int index$timeStep$91 = 1; index$timeStep$91 < length$metric[index$sample$90][0]; index$timeStep$91 += 1) {
																											if(!((index$sample$90 == index$sample$23) && (index$timeStep$91 == index$timeStep$22))) {
																												// Enumerating the possible outputs of Categorical 73.
																												for(int index$sample76$92 = 0; index$sample76$92 < noStates; index$sample76$92 += 1) {
																													int distributionTempVariable$var74$94 = index$sample76$92;
																													
																													// Update the probability of sampling this value from the distribution value.
																													double cv$probabilitySample76Value93 = (1.0 * distribution$sample76[((index$sample$90 - 0) / 1)][((index$timeStep$91 - 1) / 1)][index$sample76$92]);
																													int traceTempVariable$currentState$95_1 = cv$currentValue;
																													if((index$sample$90 == sample$var196)) {
																														if((index$timeStep$91 == timeStep$var226)) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					double cv$temp$16$var241;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var241 = current_metric_mean[server][traceTempVariable$currentState$95_1];
																																						cv$temp$16$var241 = var241;
																																					}
																																					double cv$temp$17$var243;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var243 = current_metric_var[server][traceTempVariable$currentState$95_1];
																																						cv$temp$17$var243 = var243;
																																					}
																																					
																																					// Record the probability of sample task 256 generating output with current configuration.
																																					if(((Math.log(cv$probabilitySample76Value93) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value93) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value93) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value93) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value93) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 256 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value93);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
												int traceTempVariable$currentState$73_1 = cv$currentValue;
												for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
													if((sample$var45 == sample$var196)) {
														for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
															if((timeStep$var66 == timeStep$var226)) {
																for(int server = 0; server < noServers; server += 1) {
																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																		if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																			
																			// Processing sample task 256 of consumer random variable null.
																			{
																				// Set an accumulator to sum the probabilities for each possible configuration of
																				// inputs.
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				
																				// Set an accumulator to record the consumer distributions not seen. Initially set
																				// to 1 as seen values will be deducted from this value.
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																					// the output of Sample task 76.
																					for(int index$sample$157_1 = 0; index$sample$157_1 < noSamples; index$sample$157_1 += 1) {
																						if((index$sample$157_1 == sample$var196)) {
																							if((0 == timeStep$var226)) {
																								for(int var119 = 0; var119 < noServers; var119 += 1) {
																									for(int var129 = 0; var129 < noStates; var129 += 1) {
																										if((var119 == server)) {
																											if((var129 == st[sample$var196][timeStep$var226])) {
																												for(int var146 = 0; var146 < noServers; var146 += 1) {
																													for(int var156 = 0; var156 < noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == st[sample$var196][timeStep$var226])) {
																																{
																																	{
																																		double cv$temp$44$var241;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var241 = current_metric_mean[server][traceTempVariable$currentState$73_1];
																																			cv$temp$44$var241 = var241;
																																		}
																																		double cv$temp$45$var243;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var243 = current_metric_var[server][traceTempVariable$currentState$73_1];
																																			cv$temp$45$var243 = var243;
																																		}
																																		
																																		// Record the probability of sample task 256 generating output with current configuration.
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$44$var241) / Math.sqrt(cv$temp$45$var243))) - (0.5 * Math.log(cv$temp$45$var243)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$44$var241) / Math.sqrt(cv$temp$45$var243))) - (0.5 * Math.log(cv$temp$45$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$44$var241) / Math.sqrt(cv$temp$45$var243))) - (0.5 * Math.log(cv$temp$45$var243))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$44$var241) / Math.sqrt(cv$temp$45$var243))) - (0.5 * Math.log(cv$temp$45$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$44$var241) / Math.sqrt(cv$temp$45$var243))) - (0.5 * Math.log(cv$temp$45$var243)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 256 with the current configuration.
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
																					
																					// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																					// the output of Sample task 76.
																					int traceTempVariable$currentState$160_1 = cv$currentValue;
																					if((index$sample$23 == sample$var196)) {
																						if((index$timeStep$22 == timeStep$var226)) {
																							for(int var119 = 0; var119 < noServers; var119 += 1) {
																								for(int var129 = 0; var129 < noStates; var129 += 1) {
																									if((var119 == server)) {
																										if((var129 == st[sample$var196][timeStep$var226])) {
																											for(int var146 = 0; var146 < noServers; var146 += 1) {
																												for(int var156 = 0; var156 < noStates; var156 += 1) {
																													if((var146 == server)) {
																														if((var156 == st[sample$var196][timeStep$var226])) {
																															{
																																{
																																	double cv$temp$46$var241;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var241 = current_metric_mean[server][traceTempVariable$currentState$160_1];
																																		cv$temp$46$var241 = var241;
																																	}
																																	double cv$temp$47$var243;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var243 = current_metric_var[server][traceTempVariable$currentState$160_1];
																																		cv$temp$47$var243 = var243;
																																	}
																																	
																																	// Record the probability of sample task 256 generating output with current configuration.
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$46$var241) / Math.sqrt(cv$temp$47$var243))) - (0.5 * Math.log(cv$temp$47$var243)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$46$var241) / Math.sqrt(cv$temp$47$var243))) - (0.5 * Math.log(cv$temp$47$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		// If the second value is -infinity.
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$46$var241) / Math.sqrt(cv$temp$47$var243))) - (0.5 * Math.log(cv$temp$47$var243))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$46$var241) / Math.sqrt(cv$temp$47$var243))) - (0.5 * Math.log(cv$temp$47$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$46$var241) / Math.sqrt(cv$temp$47$var243))) - (0.5 * Math.log(cv$temp$47$var243)))));
																																	}
																																	
																																	// Recorded the probability of reaching sample task 256 with the current configuration.
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
																					for(int index$sample$161 = 0; index$sample$161 < noSamples; index$sample$161 += 1) {
																						for(int index$timeStep$162 = 1; index$timeStep$162 < length$metric[index$sample$161][0]; index$timeStep$162 += 1) {
																							if(!((index$sample$161 == index$sample$23) && (index$timeStep$162 == index$timeStep$22))) {
																								// Enumerating the possible outputs of Categorical 73.
																								for(int index$sample76$163 = 0; index$sample76$163 < noStates; index$sample76$163 += 1) {
																									int distributionTempVariable$var74$165 = index$sample76$163;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample76Value164 = (1.0 * distribution$sample76[((index$sample$161 - 0) / 1)][((index$timeStep$162 - 1) / 1)][index$sample76$163]);
																									int traceTempVariable$currentState$166_1 = cv$currentValue;
																									if((index$sample$161 == sample$var196)) {
																										if((index$timeStep$162 == timeStep$var226)) {
																											for(int var119 = 0; var119 < noServers; var119 += 1) {
																												for(int var129 = 0; var129 < noStates; var129 += 1) {
																													if((var119 == server)) {
																														if((var129 == st[sample$var196][timeStep$var226])) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					double cv$temp$48$var241;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var241 = current_metric_mean[server][traceTempVariable$currentState$166_1];
																																						cv$temp$48$var241 = var241;
																																					}
																																					double cv$temp$49$var243;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var243 = current_metric_var[server][traceTempVariable$currentState$166_1];
																																						cv$temp$49$var243 = var243;
																																					}
																																					
																																					// Record the probability of sample task 256 generating output with current configuration.
																																					if(((Math.log(cv$probabilitySample76Value164) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$48$var241) / Math.sqrt(cv$temp$49$var243))) - (0.5 * Math.log(cv$temp$49$var243)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value164) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$48$var241) / Math.sqrt(cv$temp$49$var243))) - (0.5 * Math.log(cv$temp$49$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value164) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$48$var241) / Math.sqrt(cv$temp$49$var243))) - (0.5 * Math.log(cv$temp$49$var243))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value164) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$48$var241) / Math.sqrt(cv$temp$49$var243))) - (0.5 * Math.log(cv$temp$49$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value164) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$48$var241) / Math.sqrt(cv$temp$49$var243))) - (0.5 * Math.log(cv$temp$49$var243)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 256 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value164);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
					for(int index$sample$25 = 0; index$sample$25 < noSamples; index$sample$25 += 1) {
						if(true) {
							// Enumerating the possible outputs of Categorical 54.
							for(int index$sample57$26 = 0; index$sample57$26 < noStates; index$sample57$26 += 1) {
								int distributionTempVariable$var55$28 = index$sample57$26;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample57Value27 = (1.0 * distribution$sample57[((index$sample$25 - 0) / 1)][index$sample57$26]);
								if((index$sample$25 == sample$var45)) {
									if((0 == (timeStep$var66 - 1))) {
										for(int var32 = 0; var32 < noStates; var32 += 1) {
											if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
												// Record the reached probability density.
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value27);
												double[] cv$temp$2$var72;
												{
													// Constructing a random variable input for use later.
													double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
													cv$temp$2$var72 = var72;
												}
												int cv$temp$3$$var3331;
												{
													// Constructing a random variable input for use later.
													int $var3331 = noStates;
													cv$temp$3$$var3331 = $var3331;
												}
												
												// An accumulator to allow the value for each distribution to be constructed before
												// it is added to the index probabilities.
												double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value27) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$$var3331))?Math.log(cv$temp$2$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
												
												// Processing random variable 73.
												{
													// Looking for a path between Sample 76 and consumer Categorical 73.
													{
														int traceTempVariable$var71$42_1 = cv$currentValue;
													}
												}
												
												// Processing random variable 231.
												{
													// Looking for a path between Sample 76 and consumer Bernoulli 231.
													{
														int traceTempVariable$currentState$46_1 = cv$currentValue;
														for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
															if((sample$var45 == sample$var196)) {
																for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																	if((timeStep$var66 == timeStep$var226)) {
																		for(int server = 0; server < noServers; server += 1) {
																			// Set an accumulator to sum the probabilities for each possible configuration of
																			// inputs.
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			
																			// Set an accumulator to record the consumer distributions not seen. Initially set
																			// to 1 as seen values will be deducted from this value.
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				// Enumerating the possible arguments for the variable Bernoulli 231 which is consuming
																				// the output of Sample task 76.
																				for(int var173 = 0; var173 < noServers; var173 += 1) {
																					for(int var183 = 0; var183 < noStates; var183 += 1) {
																						if((var173 == server)) {
																							if((var183 == st[sample$var196][timeStep$var226])) {
																								{
																									{
																										double cv$temp$9$var230;
																										{
																											// Constructing a random variable input for use later.
																											double var230 = current_metric_valid_bias[server][traceTempVariable$currentState$46_1];
																											cv$temp$9$var230 = var230;
																										}
																										
																										// Record the probability of sample task 241 generating output with current configuration.
																										if(((Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$9$var230:(1.0 - cv$temp$9$var230)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$9$var230:(1.0 - cv$temp$9$var230)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											// If the second value is -infinity.
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$9$var230:(1.0 - cv$temp$9$var230))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$9$var230:(1.0 - cv$temp$9$var230)))))) + 1)) + (Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$9$var230:(1.0 - cv$temp$9$var230)))));
																										}
																										
																										// Recorded the probability of reaching sample task 241 with the current configuration.
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
												
												// Processing random variable 244.
												{
													// Looking for a path between Sample 76 and consumer Gaussian 244.
													{
														// Guard to check that at most one copy of the code is executed for a given random
														// variable instance.
														boolean[][][] guard$sample76gaussian255 = guard$sample76gaussian255$global[threadID$cv$sample$var45];
														for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
															if((sample$var45 == sample$var196)) {
																for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																	if((timeStep$var66 == timeStep$var226)) {
																		for(int server = 0; server < noServers; server += 1) {
																			if(metric_valid_g[sample$var196][server][timeStep$var226])
																				// Set the flags to false
																				guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
														for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
															if((sample$var45 == sample$var196)) {
																for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																	if((timeStep$var66 == timeStep$var226)) {
																		for(int server = 0; server < noServers; server += 1) {
																			if(metric_valid_g[sample$var196][server][timeStep$var226])
																				// Set the flags to false
																				guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
														int traceTempVariable$currentState$70_1 = cv$currentValue;
														for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
															if((sample$var45 == sample$var196)) {
																for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																	if((timeStep$var66 == timeStep$var226)) {
																		for(int server = 0; server < noServers; server += 1) {
																			if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																				if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																					// The body will execute, so should not be executed again
																					guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																					
																					// Processing sample task 256 of consumer random variable null.
																					{
																						// Set an accumulator to sum the probabilities for each possible configuration of
																						// inputs.
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						
																						// Set an accumulator to record the consumer distributions not seen. Initially set
																						// to 1 as seen values will be deducted from this value.
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																							// the output of Sample task 76.
																							for(int var119 = 0; var119 < noServers; var119 += 1) {
																								for(int var129 = 0; var129 < noStates; var129 += 1) {
																									if((var119 == server)) {
																										if((var129 == st[sample$var196][timeStep$var226])) {
																											int traceTempVariable$currentState$99_1 = distributionTempVariable$var55$28;
																											if((index$sample$25 == sample$var196)) {
																												if((0 == timeStep$var226)) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$18$var241;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var241 = current_metric_mean[server][traceTempVariable$currentState$99_1];
																																				cv$temp$18$var241 = var241;
																																			}
																																			double cv$temp$19$var243;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var243 = current_metric_var[server][traceTempVariable$currentState$99_1];
																																				cv$temp$19$var243 = var243;
																																			}
																																			
																																			// Record the probability of sample task 256 generating output with current configuration.
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 256 with the current configuration.
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																											for(int index$sample$100 = 0; index$sample$100 < noSamples; index$sample$100 += 1) {
																												if(!(index$sample$100 == index$sample$25)) {
																													// Enumerating the possible outputs of Categorical 54.
																													for(int index$sample57$101 = 0; index$sample57$101 < noStates; index$sample57$101 += 1) {
																														int distributionTempVariable$var55$103 = index$sample57$101;
																														
																														// Update the probability of sampling this value from the distribution value.
																														double cv$probabilitySample57Value102 = (1.0 * distribution$sample57[((index$sample$100 - 0) / 1)][index$sample57$101]);
																														int traceTempVariable$currentState$104_1 = distributionTempVariable$var55$28;
																														if((index$sample$100 == sample$var196)) {
																															if((0 == timeStep$var226)) {
																																for(int var146 = 0; var146 < noServers; var146 += 1) {
																																	for(int var156 = 0; var156 < noStates; var156 += 1) {
																																		if((var146 == server)) {
																																			if((var156 == st[sample$var196][timeStep$var226])) {
																																				{
																																					{
																																						double cv$temp$20$var241;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var241 = current_metric_mean[server][traceTempVariable$currentState$104_1];
																																							cv$temp$20$var241 = var241;
																																						}
																																						double cv$temp$21$var243;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var243 = current_metric_var[server][traceTempVariable$currentState$104_1];
																																							cv$temp$21$var243 = var243;
																																						}
																																						
																																						// Record the probability of sample task 256 generating output with current configuration.
																																						if(((Math.log(cv$probabilitySample57Value102) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value102) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value102) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value102) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value102) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))));
																																						}
																																						
																																						// Recorded the probability of reaching sample task 256 with the current configuration.
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value102);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							
																							// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																							// the output of Sample task 76.
																							for(int var119 = 0; var119 < noServers; var119 += 1) {
																								for(int var129 = 0; var129 < noStates; var129 += 1) {
																									if((var119 == server)) {
																										if((var129 == st[sample$var196][timeStep$var226])) {
																											int traceTempVariable$currentState$108_1 = cv$currentValue;
																											if((index$sample$23 == sample$var196)) {
																												if((index$timeStep$22 == timeStep$var226)) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$22$var241;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var241 = current_metric_mean[server][traceTempVariable$currentState$108_1];
																																				cv$temp$22$var241 = var241;
																																			}
																																			double cv$temp$23$var243;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var243 = current_metric_var[server][traceTempVariable$currentState$108_1];
																																				cv$temp$23$var243 = var243;
																																			}
																																			
																																			// Record the probability of sample task 256 generating output with current configuration.
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 256 with the current configuration.
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																											for(int index$sample$109 = 0; index$sample$109 < noSamples; index$sample$109 += 1) {
																												for(int index$timeStep$110 = 1; index$timeStep$110 < length$metric[index$sample$109][0]; index$timeStep$110 += 1) {
																													if(!((index$sample$109 == index$sample$23) && (index$timeStep$110 == index$timeStep$22))) {
																														// Enumerating the possible outputs of Categorical 73.
																														for(int index$sample76$111 = 0; index$sample76$111 < noStates; index$sample76$111 += 1) {
																															int distributionTempVariable$var74$113 = index$sample76$111;
																															
																															// Update the probability of sampling this value from the distribution value.
																															double cv$probabilitySample76Value112 = (1.0 * distribution$sample76[((index$sample$109 - 0) / 1)][((index$timeStep$110 - 1) / 1)][index$sample76$111]);
																															int traceTempVariable$currentState$114_1 = cv$currentValue;
																															if((index$sample$109 == sample$var196)) {
																																if((index$timeStep$110 == timeStep$var226)) {
																																	for(int var146 = 0; var146 < noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == st[sample$var196][timeStep$var226])) {
																																					{
																																						{
																																							double cv$temp$24$var241;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var241 = current_metric_mean[server][traceTempVariable$currentState$114_1];
																																								cv$temp$24$var241 = var241;
																																							}
																																							double cv$temp$25$var243;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var243 = current_metric_var[server][traceTempVariable$currentState$114_1];
																																								cv$temp$25$var243 = var243;
																																							}
																																							
																																							// Record the probability of sample task 256 generating output with current configuration.
																																							if(((Math.log(cv$probabilitySample76Value112) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value112) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								// If the second value is -infinity.
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value112) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value112) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value112) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))));
																																							}
																																							
																																							// Recorded the probability of reaching sample task 256 with the current configuration.
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value112);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
														int traceTempVariable$currentState$74_1 = cv$currentValue;
														for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
															if((sample$var45 == sample$var196)) {
																for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																	if((timeStep$var66 == timeStep$var226)) {
																		for(int server = 0; server < noServers; server += 1) {
																			if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																				if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																					// The body will execute, so should not be executed again
																					guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																					
																					// Processing sample task 256 of consumer random variable null.
																					{
																						// Set an accumulator to sum the probabilities for each possible configuration of
																						// inputs.
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						
																						// Set an accumulator to record the consumer distributions not seen. Initially set
																						// to 1 as seen values will be deducted from this value.
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																							// the output of Sample task 76.
																							int traceTempVariable$currentState$171_1 = distributionTempVariable$var55$28;
																							if((index$sample$25 == sample$var196)) {
																								if((0 == timeStep$var226)) {
																									for(int var119 = 0; var119 < noServers; var119 += 1) {
																										for(int var129 = 0; var129 < noStates; var129 += 1) {
																											if((var119 == server)) {
																												if((var129 == st[sample$var196][timeStep$var226])) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$50$var241;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var241 = current_metric_mean[server][traceTempVariable$currentState$171_1];
																																				cv$temp$50$var241 = var241;
																																			}
																																			double cv$temp$51$var243;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var243 = current_metric_var[server][traceTempVariable$currentState$171_1];
																																				cv$temp$51$var243 = var243;
																																			}
																																			
																																			// Record the probability of sample task 256 generating output with current configuration.
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$50$var241) / Math.sqrt(cv$temp$51$var243))) - (0.5 * Math.log(cv$temp$51$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$50$var241) / Math.sqrt(cv$temp$51$var243))) - (0.5 * Math.log(cv$temp$51$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$50$var241) / Math.sqrt(cv$temp$51$var243))) - (0.5 * Math.log(cv$temp$51$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$50$var241) / Math.sqrt(cv$temp$51$var243))) - (0.5 * Math.log(cv$temp$51$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$50$var241) / Math.sqrt(cv$temp$51$var243))) - (0.5 * Math.log(cv$temp$51$var243)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 256 with the current configuration.
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
																							for(int index$sample$172 = 0; index$sample$172 < noSamples; index$sample$172 += 1) {
																								if(!(index$sample$172 == index$sample$25)) {
																									// Enumerating the possible outputs of Categorical 54.
																									for(int index$sample57$173 = 0; index$sample57$173 < noStates; index$sample57$173 += 1) {
																										int distributionTempVariable$var55$175 = index$sample57$173;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample57Value174 = (1.0 * distribution$sample57[((index$sample$172 - 0) / 1)][index$sample57$173]);
																										int traceTempVariable$currentState$176_1 = distributionTempVariable$var55$28;
																										if((index$sample$172 == sample$var196)) {
																											if((0 == timeStep$var226)) {
																												for(int var119 = 0; var119 < noServers; var119 += 1) {
																													for(int var129 = 0; var129 < noStates; var129 += 1) {
																														if((var119 == server)) {
																															if((var129 == st[sample$var196][timeStep$var226])) {
																																for(int var146 = 0; var146 < noServers; var146 += 1) {
																																	for(int var156 = 0; var156 < noStates; var156 += 1) {
																																		if((var146 == server)) {
																																			if((var156 == st[sample$var196][timeStep$var226])) {
																																				{
																																					{
																																						double cv$temp$52$var241;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var241 = current_metric_mean[server][traceTempVariable$currentState$176_1];
																																							cv$temp$52$var241 = var241;
																																						}
																																						double cv$temp$53$var243;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var243 = current_metric_var[server][traceTempVariable$currentState$176_1];
																																							cv$temp$53$var243 = var243;
																																						}
																																						
																																						// Record the probability of sample task 256 generating output with current configuration.
																																						if(((Math.log(cv$probabilitySample57Value174) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$52$var241) / Math.sqrt(cv$temp$53$var243))) - (0.5 * Math.log(cv$temp$53$var243)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value174) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$52$var241) / Math.sqrt(cv$temp$53$var243))) - (0.5 * Math.log(cv$temp$53$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value174) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$52$var241) / Math.sqrt(cv$temp$53$var243))) - (0.5 * Math.log(cv$temp$53$var243))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value174) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$52$var241) / Math.sqrt(cv$temp$53$var243))) - (0.5 * Math.log(cv$temp$53$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value174) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$52$var241) / Math.sqrt(cv$temp$53$var243))) - (0.5 * Math.log(cv$temp$53$var243)))));
																																						}
																																						
																																						// Recorded the probability of reaching sample task 256 with the current configuration.
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value174);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							
																							// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																							// the output of Sample task 76.
																							int traceTempVariable$currentState$181_1 = cv$currentValue;
																							if((index$sample$23 == sample$var196)) {
																								if((index$timeStep$22 == timeStep$var226)) {
																									for(int var119 = 0; var119 < noServers; var119 += 1) {
																										for(int var129 = 0; var129 < noStates; var129 += 1) {
																											if((var119 == server)) {
																												if((var129 == st[sample$var196][timeStep$var226])) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$54$var241;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var241 = current_metric_mean[server][traceTempVariable$currentState$181_1];
																																				cv$temp$54$var241 = var241;
																																			}
																																			double cv$temp$55$var243;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var243 = current_metric_var[server][traceTempVariable$currentState$181_1];
																																				cv$temp$55$var243 = var243;
																																			}
																																			
																																			// Record the probability of sample task 256 generating output with current configuration.
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$54$var241) / Math.sqrt(cv$temp$55$var243))) - (0.5 * Math.log(cv$temp$55$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$54$var241) / Math.sqrt(cv$temp$55$var243))) - (0.5 * Math.log(cv$temp$55$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$54$var241) / Math.sqrt(cv$temp$55$var243))) - (0.5 * Math.log(cv$temp$55$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$54$var241) / Math.sqrt(cv$temp$55$var243))) - (0.5 * Math.log(cv$temp$55$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$54$var241) / Math.sqrt(cv$temp$55$var243))) - (0.5 * Math.log(cv$temp$55$var243)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 256 with the current configuration.
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
																							for(int index$sample$182 = 0; index$sample$182 < noSamples; index$sample$182 += 1) {
																								for(int index$timeStep$183 = 1; index$timeStep$183 < length$metric[index$sample$182][0]; index$timeStep$183 += 1) {
																									if(!((index$sample$182 == index$sample$23) && (index$timeStep$183 == index$timeStep$22))) {
																										// Enumerating the possible outputs of Categorical 73.
																										for(int index$sample76$184 = 0; index$sample76$184 < noStates; index$sample76$184 += 1) {
																											int distributionTempVariable$var74$186 = index$sample76$184;
																											
																											// Update the probability of sampling this value from the distribution value.
																											double cv$probabilitySample76Value185 = (1.0 * distribution$sample76[((index$sample$182 - 0) / 1)][((index$timeStep$183 - 1) / 1)][index$sample76$184]);
																											int traceTempVariable$currentState$187_1 = cv$currentValue;
																											if((index$sample$182 == sample$var196)) {
																												if((index$timeStep$183 == timeStep$var226)) {
																													for(int var119 = 0; var119 < noServers; var119 += 1) {
																														for(int var129 = 0; var129 < noStates; var129 += 1) {
																															if((var119 == server)) {
																																if((var129 == st[sample$var196][timeStep$var226])) {
																																	for(int var146 = 0; var146 < noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == st[sample$var196][timeStep$var226])) {
																																					{
																																						{
																																							double cv$temp$56$var241;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var241 = current_metric_mean[server][traceTempVariable$currentState$187_1];
																																								cv$temp$56$var241 = var241;
																																							}
																																							double cv$temp$57$var243;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var243 = current_metric_var[server][traceTempVariable$currentState$187_1];
																																								cv$temp$57$var243 = var243;
																																							}
																																							
																																							// Record the probability of sample task 256 generating output with current configuration.
																																							if(((Math.log(cv$probabilitySample76Value185) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$56$var241) / Math.sqrt(cv$temp$57$var243))) - (0.5 * Math.log(cv$temp$57$var243)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value185) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$56$var241) / Math.sqrt(cv$temp$57$var243))) - (0.5 * Math.log(cv$temp$57$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								// If the second value is -infinity.
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value185) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$56$var241) / Math.sqrt(cv$temp$57$var243))) - (0.5 * Math.log(cv$temp$57$var243))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value185) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$56$var241) / Math.sqrt(cv$temp$57$var243))) - (0.5 * Math.log(cv$temp$57$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value185) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$56$var241) / Math.sqrt(cv$temp$57$var243))) - (0.5 * Math.log(cv$temp$57$var243)))));
																																							}
																																							
																																							// Recorded the probability of reaching sample task 256 with the current configuration.
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value185);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
				
				// Enumerating the possible arguments for Categorical 73.
				int traceTempVariable$var71$32_1 = cv$currentValue;
				if((index$sample$23 == sample$var45)) {
					if((index$timeStep$22 == (timeStep$var66 - 1))) {
						for(int var32 = 0; var32 < noStates; var32 += 1) {
							if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
								// Record the reached probability density.
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double[] cv$temp$4$var72;
								{
									// Constructing a random variable input for use later.
									double[] var72 = m[traceTempVariable$var71$32_1];
									cv$temp$4$var72 = var72;
								}
								int cv$temp$5$$var3332;
								{
									// Constructing a random variable input for use later.
									int $var3332 = noStates;
									cv$temp$5$$var3332 = $var3332;
								}
								
								// An accumulator to allow the value for each distribution to be constructed before
								// it is added to the index probabilities.
								double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$5$$var3332))?Math.log(cv$temp$4$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
								
								// Processing random variable 73.
								{
									// Looking for a path between Sample 76 and consumer Categorical 73.
									{
										int traceTempVariable$var71$43_1 = cv$currentValue;
									}
								}
								
								// Processing random variable 231.
								{
									// Looking for a path between Sample 76 and consumer Bernoulli 231.
									{
										int traceTempVariable$currentState$47_1 = cv$currentValue;
										for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
											if((sample$var45 == sample$var196)) {
												for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
													if((timeStep$var66 == timeStep$var226)) {
														for(int server = 0; server < noServers; server += 1) {
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																// Enumerating the possible arguments for the variable Bernoulli 231 which is consuming
																// the output of Sample task 76.
																for(int var173 = 0; var173 < noServers; var173 += 1) {
																	for(int var183 = 0; var183 < noStates; var183 += 1) {
																		if((var173 == server)) {
																			if((var183 == st[sample$var196][timeStep$var226])) {
																				{
																					{
																						double cv$temp$10$var230;
																						{
																							// Constructing a random variable input for use later.
																							double var230 = current_metric_valid_bias[server][traceTempVariable$currentState$47_1];
																							cv$temp$10$var230 = var230;
																						}
																						
																						// Record the probability of sample task 241 generating output with current configuration.
																						if(((Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$10$var230:(1.0 - cv$temp$10$var230)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$10$var230:(1.0 - cv$temp$10$var230)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							// If the second value is -infinity.
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$10$var230:(1.0 - cv$temp$10$var230))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$10$var230:(1.0 - cv$temp$10$var230)))))) + 1)) + (Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$10$var230:(1.0 - cv$temp$10$var230)))));
																						}
																						
																						// Recorded the probability of reaching sample task 241 with the current configuration.
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
								
								// Processing random variable 244.
								{
									// Looking for a path between Sample 76 and consumer Gaussian 244.
									{
										// Guard to check that at most one copy of the code is executed for a given random
										// variable instance.
										boolean[][][] guard$sample76gaussian255 = guard$sample76gaussian255$global[threadID$cv$sample$var45];
										for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
											if((sample$var45 == sample$var196)) {
												for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
													if((timeStep$var66 == timeStep$var226)) {
														for(int server = 0; server < noServers; server += 1) {
															if(metric_valid_g[sample$var196][server][timeStep$var226])
																// Set the flags to false
																guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
														}
													}
												}
											}
										}
										for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
											if((sample$var45 == sample$var196)) {
												for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
													if((timeStep$var66 == timeStep$var226)) {
														for(int server = 0; server < noServers; server += 1) {
															if(metric_valid_g[sample$var196][server][timeStep$var226])
																// Set the flags to false
																guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
														}
													}
												}
											}
										}
										int traceTempVariable$currentState$71_1 = cv$currentValue;
										for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
											if((sample$var45 == sample$var196)) {
												for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
													if((timeStep$var66 == timeStep$var226)) {
														for(int server = 0; server < noServers; server += 1) {
															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																	// The body will execute, so should not be executed again
																	guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																	
																	// Processing sample task 256 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																			// the output of Sample task 76.
																			for(int var119 = 0; var119 < noServers; var119 += 1) {
																				for(int var129 = 0; var129 < noStates; var129 += 1) {
																					if((var119 == server)) {
																						if((var129 == st[sample$var196][timeStep$var226])) {
																							if(fixedFlag$sample57) {
																								for(int index$sample$118_1 = 0; index$sample$118_1 < noSamples; index$sample$118_1 += 1) {
																									if((index$sample$118_1 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											for(int var146 = 0; var146 < noServers; var146 += 1) {
																												for(int var156 = 0; var156 < noStates; var156 += 1) {
																													if((var146 == server)) {
																														if((var156 == st[sample$var196][timeStep$var226])) {
																															{
																																{
																																	double cv$temp$26$var241;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var241 = current_metric_mean[server][traceTempVariable$currentState$71_1];
																																		cv$temp$26$var241 = var241;
																																	}
																																	double cv$temp$27$var243;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var243 = current_metric_var[server][traceTempVariable$currentState$71_1];
																																		cv$temp$27$var243 = var243;
																																	}
																																	
																																	// Record the probability of sample task 256 generating output with current configuration.
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		// If the second value is -infinity.
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))));
																																	}
																																	
																																	// Recorded the probability of reaching sample task 256 with the current configuration.
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
																							} else {
																								for(int index$sample$119 = 0; index$sample$119 < noSamples; index$sample$119 += 1) {
																									if(true) {
																										// Enumerating the possible outputs of Categorical 54.
																										for(int index$sample57$120 = 0; index$sample57$120 < noStates; index$sample57$120 += 1) {
																											int distributionTempVariable$var55$122 = index$sample57$120;
																											
																											// Update the probability of sampling this value from the distribution value.
																											double cv$probabilitySample57Value121 = (1.0 * distribution$sample57[((index$sample$119 - 0) / 1)][index$sample57$120]);
																											if((index$sample$119 == sample$var196)) {
																												if((0 == timeStep$var226)) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$28$var241;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var241 = current_metric_mean[server][traceTempVariable$currentState$71_1];
																																				cv$temp$28$var241 = var241;
																																			}
																																			double cv$temp$29$var243;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var243 = current_metric_var[server][traceTempVariable$currentState$71_1];
																																				cv$temp$29$var243 = var243;
																																			}
																																			
																																			// Record the probability of sample task 256 generating output with current configuration.
																																			if(((Math.log(cv$probabilitySample57Value121) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value121) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value121) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value121) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value121) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 256 with the current configuration.
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value121);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			
																			// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																			// the output of Sample task 76.
																			for(int var119 = 0; var119 < noServers; var119 += 1) {
																				for(int var129 = 0; var129 < noStates; var129 += 1) {
																					if((var119 == server)) {
																						if((var129 == st[sample$var196][timeStep$var226])) {
																							int traceTempVariable$currentState$127_1 = cv$currentValue;
																							if((index$sample$23 == sample$var196)) {
																								if((index$timeStep$22 == timeStep$var226)) {
																									for(int var146 = 0; var146 < noServers; var146 += 1) {
																										for(int var156 = 0; var156 < noStates; var156 += 1) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														{
																															double cv$temp$30$var241;
																															{
																																// Constructing a random variable input for use later.
																																double var241 = current_metric_mean[server][traceTempVariable$currentState$127_1];
																																cv$temp$30$var241 = var241;
																															}
																															double cv$temp$31$var243;
																															{
																																// Constructing a random variable input for use later.
																																double var243 = current_metric_var[server][traceTempVariable$currentState$127_1];
																																cv$temp$31$var243 = var243;
																															}
																															
																															// Record the probability of sample task 256 generating output with current configuration.
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$30$var241) / Math.sqrt(cv$temp$31$var243))) - (0.5 * Math.log(cv$temp$31$var243)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$30$var241) / Math.sqrt(cv$temp$31$var243))) - (0.5 * Math.log(cv$temp$31$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																// If the second value is -infinity.
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$30$var241) / Math.sqrt(cv$temp$31$var243))) - (0.5 * Math.log(cv$temp$31$var243))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$30$var241) / Math.sqrt(cv$temp$31$var243))) - (0.5 * Math.log(cv$temp$31$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$30$var241) / Math.sqrt(cv$temp$31$var243))) - (0.5 * Math.log(cv$temp$31$var243)))));
																															}
																															
																															// Recorded the probability of reaching sample task 256 with the current configuration.
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int index$sample$128 = 0; index$sample$128 < noSamples; index$sample$128 += 1) {
																								for(int index$timeStep$129 = 1; index$timeStep$129 < length$metric[index$sample$128][0]; index$timeStep$129 += 1) {
																									if(!((index$sample$128 == index$sample$23) && (index$timeStep$129 == index$timeStep$22))) {
																										// Enumerating the possible outputs of Categorical 73.
																										for(int index$sample76$130 = 0; index$sample76$130 < noStates; index$sample76$130 += 1) {
																											int distributionTempVariable$var74$132 = index$sample76$130;
																											
																											// Update the probability of sampling this value from the distribution value.
																											double cv$probabilitySample76Value131 = (1.0 * distribution$sample76[((index$sample$128 - 0) / 1)][((index$timeStep$129 - 1) / 1)][index$sample76$130]);
																											int traceTempVariable$currentState$133_1 = cv$currentValue;
																											if((index$sample$128 == sample$var196)) {
																												if((index$timeStep$129 == timeStep$var226)) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$32$var241;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var241 = current_metric_mean[server][traceTempVariable$currentState$133_1];
																																				cv$temp$32$var241 = var241;
																																			}
																																			double cv$temp$33$var243;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var243 = current_metric_var[server][traceTempVariable$currentState$133_1];
																																				cv$temp$33$var243 = var243;
																																			}
																																			
																																			// Record the probability of sample task 256 generating output with current configuration.
																																			if(((Math.log(cv$probabilitySample76Value131) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$32$var241) / Math.sqrt(cv$temp$33$var243))) - (0.5 * Math.log(cv$temp$33$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value131) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$32$var241) / Math.sqrt(cv$temp$33$var243))) - (0.5 * Math.log(cv$temp$33$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value131) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$32$var241) / Math.sqrt(cv$temp$33$var243))) - (0.5 * Math.log(cv$temp$33$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value131) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$32$var241) / Math.sqrt(cv$temp$33$var243))) - (0.5 * Math.log(cv$temp$33$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value131) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$32$var241) / Math.sqrt(cv$temp$33$var243))) - (0.5 * Math.log(cv$temp$33$var243)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 256 with the current configuration.
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value131);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
										int traceTempVariable$currentState$75_1 = cv$currentValue;
										for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
											if((sample$var45 == sample$var196)) {
												for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
													if((timeStep$var66 == timeStep$var226)) {
														for(int server = 0; server < noServers; server += 1) {
															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																	// The body will execute, so should not be executed again
																	guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																	
																	// Processing sample task 256 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																			// the output of Sample task 76.
																			if(fixedFlag$sample57) {
																				for(int index$sample$192_1 = 0; index$sample$192_1 < noSamples; index$sample$192_1 += 1) {
																					if((index$sample$192_1 == sample$var196)) {
																						if((0 == timeStep$var226)) {
																							for(int var119 = 0; var119 < noServers; var119 += 1) {
																								for(int var129 = 0; var129 < noStates; var129 += 1) {
																									if((var119 == server)) {
																										if((var129 == st[sample$var196][timeStep$var226])) {
																											for(int var146 = 0; var146 < noServers; var146 += 1) {
																												for(int var156 = 0; var156 < noStates; var156 += 1) {
																													if((var146 == server)) {
																														if((var156 == st[sample$var196][timeStep$var226])) {
																															{
																																{
																																	double cv$temp$58$var241;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var241 = current_metric_mean[server][traceTempVariable$currentState$75_1];
																																		cv$temp$58$var241 = var241;
																																	}
																																	double cv$temp$59$var243;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var243 = current_metric_var[server][traceTempVariable$currentState$75_1];
																																		cv$temp$59$var243 = var243;
																																	}
																																	
																																	// Record the probability of sample task 256 generating output with current configuration.
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$58$var241) / Math.sqrt(cv$temp$59$var243))) - (0.5 * Math.log(cv$temp$59$var243)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$58$var241) / Math.sqrt(cv$temp$59$var243))) - (0.5 * Math.log(cv$temp$59$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		// If the second value is -infinity.
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$58$var241) / Math.sqrt(cv$temp$59$var243))) - (0.5 * Math.log(cv$temp$59$var243))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$58$var241) / Math.sqrt(cv$temp$59$var243))) - (0.5 * Math.log(cv$temp$59$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$58$var241) / Math.sqrt(cv$temp$59$var243))) - (0.5 * Math.log(cv$temp$59$var243)))));
																																	}
																																	
																																	// Recorded the probability of reaching sample task 256 with the current configuration.
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
																			} else {
																				for(int index$sample$193 = 0; index$sample$193 < noSamples; index$sample$193 += 1) {
																					if(true) {
																						// Enumerating the possible outputs of Categorical 54.
																						for(int index$sample57$194 = 0; index$sample57$194 < noStates; index$sample57$194 += 1) {
																							int distributionTempVariable$var55$196 = index$sample57$194;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample57Value195 = (1.0 * distribution$sample57[((index$sample$193 - 0) / 1)][index$sample57$194]);
																							if((index$sample$193 == sample$var196)) {
																								if((0 == timeStep$var226)) {
																									for(int var119 = 0; var119 < noServers; var119 += 1) {
																										for(int var129 = 0; var129 < noStates; var129 += 1) {
																											if((var119 == server)) {
																												if((var129 == st[sample$var196][timeStep$var226])) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$60$var241;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var241 = current_metric_mean[server][traceTempVariable$currentState$75_1];
																																				cv$temp$60$var241 = var241;
																																			}
																																			double cv$temp$61$var243;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var243 = current_metric_var[server][traceTempVariable$currentState$75_1];
																																				cv$temp$61$var243 = var243;
																																			}
																																			
																																			// Record the probability of sample task 256 generating output with current configuration.
																																			if(((Math.log(cv$probabilitySample57Value195) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$60$var241) / Math.sqrt(cv$temp$61$var243))) - (0.5 * Math.log(cv$temp$61$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value195) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$60$var241) / Math.sqrt(cv$temp$61$var243))) - (0.5 * Math.log(cv$temp$61$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value195) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$60$var241) / Math.sqrt(cv$temp$61$var243))) - (0.5 * Math.log(cv$temp$61$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value195) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$60$var241) / Math.sqrt(cv$temp$61$var243))) - (0.5 * Math.log(cv$temp$61$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value195) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$60$var241) / Math.sqrt(cv$temp$61$var243))) - (0.5 * Math.log(cv$temp$61$var243)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 256 with the current configuration.
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value195);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			
																			// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																			// the output of Sample task 76.
																			int traceTempVariable$currentState$202_1 = cv$currentValue;
																			if((index$sample$23 == sample$var196)) {
																				if((index$timeStep$22 == timeStep$var226)) {
																					for(int var119 = 0; var119 < noServers; var119 += 1) {
																						for(int var129 = 0; var129 < noStates; var129 += 1) {
																							if((var119 == server)) {
																								if((var129 == st[sample$var196][timeStep$var226])) {
																									for(int var146 = 0; var146 < noServers; var146 += 1) {
																										for(int var156 = 0; var156 < noStates; var156 += 1) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														{
																															double cv$temp$62$var241;
																															{
																																// Constructing a random variable input for use later.
																																double var241 = current_metric_mean[server][traceTempVariable$currentState$202_1];
																																cv$temp$62$var241 = var241;
																															}
																															double cv$temp$63$var243;
																															{
																																// Constructing a random variable input for use later.
																																double var243 = current_metric_var[server][traceTempVariable$currentState$202_1];
																																cv$temp$63$var243 = var243;
																															}
																															
																															// Record the probability of sample task 256 generating output with current configuration.
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$62$var241) / Math.sqrt(cv$temp$63$var243))) - (0.5 * Math.log(cv$temp$63$var243)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$62$var241) / Math.sqrt(cv$temp$63$var243))) - (0.5 * Math.log(cv$temp$63$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																// If the second value is -infinity.
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$62$var241) / Math.sqrt(cv$temp$63$var243))) - (0.5 * Math.log(cv$temp$63$var243))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$62$var241) / Math.sqrt(cv$temp$63$var243))) - (0.5 * Math.log(cv$temp$63$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$62$var241) / Math.sqrt(cv$temp$63$var243))) - (0.5 * Math.log(cv$temp$63$var243)))));
																															}
																															
																															// Recorded the probability of reaching sample task 256 with the current configuration.
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
																			for(int index$sample$203 = 0; index$sample$203 < noSamples; index$sample$203 += 1) {
																				for(int index$timeStep$204 = 1; index$timeStep$204 < length$metric[index$sample$203][0]; index$timeStep$204 += 1) {
																					if(!((index$sample$203 == index$sample$23) && (index$timeStep$204 == index$timeStep$22))) {
																						// Enumerating the possible outputs of Categorical 73.
																						for(int index$sample76$205 = 0; index$sample76$205 < noStates; index$sample76$205 += 1) {
																							int distributionTempVariable$var74$207 = index$sample76$205;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample76Value206 = (1.0 * distribution$sample76[((index$sample$203 - 0) / 1)][((index$timeStep$204 - 1) / 1)][index$sample76$205]);
																							int traceTempVariable$currentState$208_1 = cv$currentValue;
																							if((index$sample$203 == sample$var196)) {
																								if((index$timeStep$204 == timeStep$var226)) {
																									for(int var119 = 0; var119 < noServers; var119 += 1) {
																										for(int var129 = 0; var129 < noStates; var129 += 1) {
																											if((var119 == server)) {
																												if((var129 == st[sample$var196][timeStep$var226])) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$64$var241;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var241 = current_metric_mean[server][traceTempVariable$currentState$208_1];
																																				cv$temp$64$var241 = var241;
																																			}
																																			double cv$temp$65$var243;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var243 = current_metric_var[server][traceTempVariable$currentState$208_1];
																																				cv$temp$65$var243 = var243;
																																			}
																																			
																																			// Record the probability of sample task 256 generating output with current configuration.
																																			if(((Math.log(cv$probabilitySample76Value206) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$64$var241) / Math.sqrt(cv$temp$65$var243))) - (0.5 * Math.log(cv$temp$65$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value206) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$64$var241) / Math.sqrt(cv$temp$65$var243))) - (0.5 * Math.log(cv$temp$65$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value206) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$64$var241) / Math.sqrt(cv$temp$65$var243))) - (0.5 * Math.log(cv$temp$65$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value206) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$64$var241) / Math.sqrt(cv$temp$65$var243))) - (0.5 * Math.log(cv$temp$65$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value206) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$64$var241) / Math.sqrt(cv$temp$65$var243))) - (0.5 * Math.log(cv$temp$65$var243)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 256 with the current configuration.
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value206);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
				for(int index$sample$33 = 0; index$sample$33 < noSamples; index$sample$33 += 1) {
					for(int index$timeStep$34 = 1; index$timeStep$34 < length$metric[index$sample$33][0]; index$timeStep$34 += 1) {
						if(!((index$sample$33 == index$sample$23) && (index$timeStep$34 == index$timeStep$22))) {
							// Enumerating the possible outputs of Categorical 73.
							for(int index$sample76$35 = 0; index$sample76$35 < noStates; index$sample76$35 += 1) {
								int distributionTempVariable$var74$37 = index$sample76$35;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample76Value36 = (1.0 * distribution$sample76[((index$sample$33 - 0) / 1)][((index$timeStep$34 - 1) / 1)][index$sample76$35]);
								int traceTempVariable$var71$38_1 = cv$currentValue;
								if((index$sample$33 == sample$var45)) {
									if((index$timeStep$34 == (timeStep$var66 - 1))) {
										for(int var32 = 0; var32 < noStates; var32 += 1) {
											if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
												// Record the reached probability density.
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample76Value36);
												double[] cv$temp$6$var72;
												{
													// Constructing a random variable input for use later.
													double[] var72 = m[traceTempVariable$var71$38_1];
													cv$temp$6$var72 = var72;
												}
												int cv$temp$7$$var3333;
												{
													// Constructing a random variable input for use later.
													int $var3333 = noStates;
													cv$temp$7$$var3333 = $var3333;
												}
												
												// An accumulator to allow the value for each distribution to be constructed before
												// it is added to the index probabilities.
												double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample76Value36) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$7$$var3333))?Math.log(cv$temp$6$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
												
												// Processing random variable 73.
												{
													// Looking for a path between Sample 76 and consumer Categorical 73.
													{
														int traceTempVariable$var71$44_1 = distributionTempVariable$var74$37;
													}
												}
												
												// Processing random variable 231.
												{
													// Looking for a path between Sample 76 and consumer Bernoulli 231.
													{
														int traceTempVariable$currentState$48_1 = distributionTempVariable$var74$37;
														for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
															if((sample$var45 == sample$var196)) {
																for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																	if((timeStep$var66 == timeStep$var226)) {
																		for(int server = 0; server < noServers; server += 1) {
																			// Set an accumulator to sum the probabilities for each possible configuration of
																			// inputs.
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			
																			// Set an accumulator to record the consumer distributions not seen. Initially set
																			// to 1 as seen values will be deducted from this value.
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				// Enumerating the possible arguments for the variable Bernoulli 231 which is consuming
																				// the output of Sample task 76.
																				for(int var173 = 0; var173 < noServers; var173 += 1) {
																					for(int var183 = 0; var183 < noStates; var183 += 1) {
																						if((var173 == server)) {
																							if((var183 == st[sample$var196][timeStep$var226])) {
																								{
																									{
																										double cv$temp$11$var230;
																										{
																											// Constructing a random variable input for use later.
																											double var230 = current_metric_valid_bias[server][traceTempVariable$currentState$48_1];
																											cv$temp$11$var230 = var230;
																										}
																										
																										// Record the probability of sample task 241 generating output with current configuration.
																										if(((Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$11$var230:(1.0 - cv$temp$11$var230)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$11$var230:(1.0 - cv$temp$11$var230)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											// If the second value is -infinity.
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$11$var230:(1.0 - cv$temp$11$var230))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$11$var230:(1.0 - cv$temp$11$var230)))))) + 1)) + (Math.log(1.0) + Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?cv$temp$11$var230:(1.0 - cv$temp$11$var230)))));
																										}
																										
																										// Recorded the probability of reaching sample task 241 with the current configuration.
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
												
												// Processing random variable 244.
												{
													// Looking for a path between Sample 76 and consumer Gaussian 244.
													{
														// Guard to check that at most one copy of the code is executed for a given random
														// variable instance.
														boolean[][][] guard$sample76gaussian255 = guard$sample76gaussian255$global[threadID$cv$sample$var45];
														for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
															if((sample$var45 == sample$var196)) {
																for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																	if((timeStep$var66 == timeStep$var226)) {
																		for(int server = 0; server < noServers; server += 1) {
																			if(metric_valid_g[sample$var196][server][timeStep$var226])
																				// Set the flags to false
																				guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
														for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
															if((sample$var45 == sample$var196)) {
																for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																	if((timeStep$var66 == timeStep$var226)) {
																		for(int server = 0; server < noServers; server += 1) {
																			if(metric_valid_g[sample$var196][server][timeStep$var226])
																				// Set the flags to false
																				guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
														int traceTempVariable$currentState$72_1 = distributionTempVariable$var74$37;
														for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
															if((sample$var45 == sample$var196)) {
																for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																	if((timeStep$var66 == timeStep$var226)) {
																		for(int server = 0; server < noServers; server += 1) {
																			if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																				if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																					// The body will execute, so should not be executed again
																					guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																					
																					// Processing sample task 256 of consumer random variable null.
																					{
																						// Set an accumulator to sum the probabilities for each possible configuration of
																						// inputs.
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						
																						// Set an accumulator to record the consumer distributions not seen. Initially set
																						// to 1 as seen values will be deducted from this value.
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																							// the output of Sample task 76.
																							for(int var119 = 0; var119 < noServers; var119 += 1) {
																								for(int var129 = 0; var129 < noStates; var129 += 1) {
																									if((var119 == server)) {
																										if((var129 == st[sample$var196][timeStep$var226])) {
																											if(fixedFlag$sample57) {
																												for(int index$sample$137_1 = 0; index$sample$137_1 < noSamples; index$sample$137_1 += 1) {
																													if((index$sample$137_1 == sample$var196)) {
																														if((0 == timeStep$var226)) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					double cv$temp$34$var241;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var241 = current_metric_mean[server][traceTempVariable$currentState$72_1];
																																						cv$temp$34$var241 = var241;
																																					}
																																					double cv$temp$35$var243;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var243 = current_metric_var[server][traceTempVariable$currentState$72_1];
																																						cv$temp$35$var243 = var243;
																																					}
																																					
																																					// Record the probability of sample task 256 generating output with current configuration.
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$34$var241) / Math.sqrt(cv$temp$35$var243))) - (0.5 * Math.log(cv$temp$35$var243)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$34$var241) / Math.sqrt(cv$temp$35$var243))) - (0.5 * Math.log(cv$temp$35$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$34$var241) / Math.sqrt(cv$temp$35$var243))) - (0.5 * Math.log(cv$temp$35$var243))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$34$var241) / Math.sqrt(cv$temp$35$var243))) - (0.5 * Math.log(cv$temp$35$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$34$var241) / Math.sqrt(cv$temp$35$var243))) - (0.5 * Math.log(cv$temp$35$var243)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 256 with the current configuration.
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
																											} else {
																												for(int index$sample$138 = 0; index$sample$138 < noSamples; index$sample$138 += 1) {
																													if(true) {
																														// Enumerating the possible outputs of Categorical 54.
																														for(int index$sample57$139 = 0; index$sample57$139 < noStates; index$sample57$139 += 1) {
																															int distributionTempVariable$var55$141 = index$sample57$139;
																															
																															// Update the probability of sampling this value from the distribution value.
																															double cv$probabilitySample57Value140 = (1.0 * distribution$sample57[((index$sample$138 - 0) / 1)][index$sample57$139]);
																															if((index$sample$138 == sample$var196)) {
																																if((0 == timeStep$var226)) {
																																	for(int var146 = 0; var146 < noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == st[sample$var196][timeStep$var226])) {
																																					{
																																						{
																																							double cv$temp$36$var241;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var241 = current_metric_mean[server][traceTempVariable$currentState$72_1];
																																								cv$temp$36$var241 = var241;
																																							}
																																							double cv$temp$37$var243;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var243 = current_metric_var[server][traceTempVariable$currentState$72_1];
																																								cv$temp$37$var243 = var243;
																																							}
																																							
																																							// Record the probability of sample task 256 generating output with current configuration.
																																							if(((Math.log(cv$probabilitySample57Value140) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$36$var241) / Math.sqrt(cv$temp$37$var243))) - (0.5 * Math.log(cv$temp$37$var243)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value140) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$36$var241) / Math.sqrt(cv$temp$37$var243))) - (0.5 * Math.log(cv$temp$37$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								// If the second value is -infinity.
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value140) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$36$var241) / Math.sqrt(cv$temp$37$var243))) - (0.5 * Math.log(cv$temp$37$var243))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value140) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$36$var241) / Math.sqrt(cv$temp$37$var243))) - (0.5 * Math.log(cv$temp$37$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value140) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$36$var241) / Math.sqrt(cv$temp$37$var243))) - (0.5 * Math.log(cv$temp$37$var243)))));
																																							}
																																							
																																							// Recorded the probability of reaching sample task 256 with the current configuration.
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value140);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							
																							// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																							// the output of Sample task 76.
																							for(int var119 = 0; var119 < noServers; var119 += 1) {
																								for(int var129 = 0; var129 < noStates; var129 += 1) {
																									if((var119 == server)) {
																										if((var129 == st[sample$var196][timeStep$var226])) {
																											int traceTempVariable$currentState$146_1 = distributionTempVariable$var74$37;
																											if((index$sample$23 == sample$var196)) {
																												if((index$timeStep$22 == timeStep$var226)) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$38$var241;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var241 = current_metric_mean[server][traceTempVariable$currentState$146_1];
																																				cv$temp$38$var241 = var241;
																																			}
																																			double cv$temp$39$var243;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var243 = current_metric_var[server][traceTempVariable$currentState$146_1];
																																				cv$temp$39$var243 = var243;
																																			}
																																			
																																			// Record the probability of sample task 256 generating output with current configuration.
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$38$var241) / Math.sqrt(cv$temp$39$var243))) - (0.5 * Math.log(cv$temp$39$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$38$var241) / Math.sqrt(cv$temp$39$var243))) - (0.5 * Math.log(cv$temp$39$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$38$var241) / Math.sqrt(cv$temp$39$var243))) - (0.5 * Math.log(cv$temp$39$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$38$var241) / Math.sqrt(cv$temp$39$var243))) - (0.5 * Math.log(cv$temp$39$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$38$var241) / Math.sqrt(cv$temp$39$var243))) - (0.5 * Math.log(cv$temp$39$var243)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 256 with the current configuration.
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																											int traceTempVariable$currentState$147_1 = distributionTempVariable$var74$37;
																											if((index$sample$33 == sample$var196)) {
																												if((index$timeStep$34 == timeStep$var226)) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$40$var241;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var241 = current_metric_mean[server][traceTempVariable$currentState$147_1];
																																				cv$temp$40$var241 = var241;
																																			}
																																			double cv$temp$41$var243;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var243 = current_metric_var[server][traceTempVariable$currentState$147_1];
																																				cv$temp$41$var243 = var243;
																																			}
																																			
																																			// Record the probability of sample task 256 generating output with current configuration.
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$40$var241) / Math.sqrt(cv$temp$41$var243))) - (0.5 * Math.log(cv$temp$41$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$40$var241) / Math.sqrt(cv$temp$41$var243))) - (0.5 * Math.log(cv$temp$41$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$40$var241) / Math.sqrt(cv$temp$41$var243))) - (0.5 * Math.log(cv$temp$41$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$40$var241) / Math.sqrt(cv$temp$41$var243))) - (0.5 * Math.log(cv$temp$41$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$40$var241) / Math.sqrt(cv$temp$41$var243))) - (0.5 * Math.log(cv$temp$41$var243)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 256 with the current configuration.
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																											for(int index$sample$148 = 0; index$sample$148 < noSamples; index$sample$148 += 1) {
																												for(int index$timeStep$149 = 1; index$timeStep$149 < length$metric[index$sample$148][0]; index$timeStep$149 += 1) {
																													if((!((index$sample$148 == index$sample$23) && (index$timeStep$149 == index$timeStep$22)) && !((index$sample$148 == index$sample$33) && (index$timeStep$149 == index$timeStep$34)))) {
																														// Enumerating the possible outputs of Categorical 73.
																														for(int index$sample76$150 = 0; index$sample76$150 < noStates; index$sample76$150 += 1) {
																															int distributionTempVariable$var74$152 = index$sample76$150;
																															
																															// Update the probability of sampling this value from the distribution value.
																															double cv$probabilitySample76Value151 = (1.0 * distribution$sample76[((index$sample$148 - 0) / 1)][((index$timeStep$149 - 1) / 1)][index$sample76$150]);
																															int traceTempVariable$currentState$153_1 = distributionTempVariable$var74$37;
																															if((index$sample$148 == sample$var196)) {
																																if((index$timeStep$149 == timeStep$var226)) {
																																	for(int var146 = 0; var146 < noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == st[sample$var196][timeStep$var226])) {
																																					{
																																						{
																																							double cv$temp$42$var241;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var241 = current_metric_mean[server][traceTempVariable$currentState$153_1];
																																								cv$temp$42$var241 = var241;
																																							}
																																							double cv$temp$43$var243;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var243 = current_metric_var[server][traceTempVariable$currentState$153_1];
																																								cv$temp$43$var243 = var243;
																																							}
																																							
																																							// Record the probability of sample task 256 generating output with current configuration.
																																							if(((Math.log(cv$probabilitySample76Value151) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$42$var241) / Math.sqrt(cv$temp$43$var243))) - (0.5 * Math.log(cv$temp$43$var243)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value151) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$42$var241) / Math.sqrt(cv$temp$43$var243))) - (0.5 * Math.log(cv$temp$43$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								// If the second value is -infinity.
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value151) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$42$var241) / Math.sqrt(cv$temp$43$var243))) - (0.5 * Math.log(cv$temp$43$var243))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value151) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$42$var241) / Math.sqrt(cv$temp$43$var243))) - (0.5 * Math.log(cv$temp$43$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value151) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$42$var241) / Math.sqrt(cv$temp$43$var243))) - (0.5 * Math.log(cv$temp$43$var243)))));
																																							}
																																							
																																							// Recorded the probability of reaching sample task 256 with the current configuration.
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value151);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
														int traceTempVariable$currentState$76_1 = distributionTempVariable$var74$37;
														for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
															if((sample$var45 == sample$var196)) {
																for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																	if((timeStep$var66 == timeStep$var226)) {
																		for(int server = 0; server < noServers; server += 1) {
																			if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																				if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																					// The body will execute, so should not be executed again
																					guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																					
																					// Processing sample task 256 of consumer random variable null.
																					{
																						// Set an accumulator to sum the probabilities for each possible configuration of
																						// inputs.
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						
																						// Set an accumulator to record the consumer distributions not seen. Initially set
																						// to 1 as seen values will be deducted from this value.
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																							// the output of Sample task 76.
																							if(fixedFlag$sample57) {
																								for(int index$sample$213_1 = 0; index$sample$213_1 < noSamples; index$sample$213_1 += 1) {
																									if((index$sample$213_1 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											for(int var119 = 0; var119 < noServers; var119 += 1) {
																												for(int var129 = 0; var129 < noStates; var129 += 1) {
																													if((var119 == server)) {
																														if((var129 == st[sample$var196][timeStep$var226])) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					double cv$temp$66$var241;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var241 = current_metric_mean[server][traceTempVariable$currentState$76_1];
																																						cv$temp$66$var241 = var241;
																																					}
																																					double cv$temp$67$var243;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var243 = current_metric_var[server][traceTempVariable$currentState$76_1];
																																						cv$temp$67$var243 = var243;
																																					}
																																					
																																					// Record the probability of sample task 256 generating output with current configuration.
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$66$var241) / Math.sqrt(cv$temp$67$var243))) - (0.5 * Math.log(cv$temp$67$var243)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$66$var241) / Math.sqrt(cv$temp$67$var243))) - (0.5 * Math.log(cv$temp$67$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$66$var241) / Math.sqrt(cv$temp$67$var243))) - (0.5 * Math.log(cv$temp$67$var243))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$66$var241) / Math.sqrt(cv$temp$67$var243))) - (0.5 * Math.log(cv$temp$67$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$66$var241) / Math.sqrt(cv$temp$67$var243))) - (0.5 * Math.log(cv$temp$67$var243)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 256 with the current configuration.
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
																							} else {
																								for(int index$sample$214 = 0; index$sample$214 < noSamples; index$sample$214 += 1) {
																									if(true) {
																										// Enumerating the possible outputs of Categorical 54.
																										for(int index$sample57$215 = 0; index$sample57$215 < noStates; index$sample57$215 += 1) {
																											int distributionTempVariable$var55$217 = index$sample57$215;
																											
																											// Update the probability of sampling this value from the distribution value.
																											double cv$probabilitySample57Value216 = (1.0 * distribution$sample57[((index$sample$214 - 0) / 1)][index$sample57$215]);
																											if((index$sample$214 == sample$var196)) {
																												if((0 == timeStep$var226)) {
																													for(int var119 = 0; var119 < noServers; var119 += 1) {
																														for(int var129 = 0; var129 < noStates; var129 += 1) {
																															if((var119 == server)) {
																																if((var129 == st[sample$var196][timeStep$var226])) {
																																	for(int var146 = 0; var146 < noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == st[sample$var196][timeStep$var226])) {
																																					{
																																						{
																																							double cv$temp$68$var241;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var241 = current_metric_mean[server][traceTempVariable$currentState$76_1];
																																								cv$temp$68$var241 = var241;
																																							}
																																							double cv$temp$69$var243;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var243 = current_metric_var[server][traceTempVariable$currentState$76_1];
																																								cv$temp$69$var243 = var243;
																																							}
																																							
																																							// Record the probability of sample task 256 generating output with current configuration.
																																							if(((Math.log(cv$probabilitySample57Value216) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$68$var241) / Math.sqrt(cv$temp$69$var243))) - (0.5 * Math.log(cv$temp$69$var243)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value216) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$68$var241) / Math.sqrt(cv$temp$69$var243))) - (0.5 * Math.log(cv$temp$69$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								// If the second value is -infinity.
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value216) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$68$var241) / Math.sqrt(cv$temp$69$var243))) - (0.5 * Math.log(cv$temp$69$var243))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value216) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$68$var241) / Math.sqrt(cv$temp$69$var243))) - (0.5 * Math.log(cv$temp$69$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value216) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$68$var241) / Math.sqrt(cv$temp$69$var243))) - (0.5 * Math.log(cv$temp$69$var243)))));
																																							}
																																							
																																							// Recorded the probability of reaching sample task 256 with the current configuration.
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value216);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							
																							// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																							// the output of Sample task 76.
																							int traceTempVariable$currentState$223_1 = distributionTempVariable$var74$37;
																							if((index$sample$23 == sample$var196)) {
																								if((index$timeStep$22 == timeStep$var226)) {
																									for(int var119 = 0; var119 < noServers; var119 += 1) {
																										for(int var129 = 0; var129 < noStates; var129 += 1) {
																											if((var119 == server)) {
																												if((var129 == st[sample$var196][timeStep$var226])) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$70$var241;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var241 = current_metric_mean[server][traceTempVariable$currentState$223_1];
																																				cv$temp$70$var241 = var241;
																																			}
																																			double cv$temp$71$var243;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var243 = current_metric_var[server][traceTempVariable$currentState$223_1];
																																				cv$temp$71$var243 = var243;
																																			}
																																			
																																			// Record the probability of sample task 256 generating output with current configuration.
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$70$var241) / Math.sqrt(cv$temp$71$var243))) - (0.5 * Math.log(cv$temp$71$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$70$var241) / Math.sqrt(cv$temp$71$var243))) - (0.5 * Math.log(cv$temp$71$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$70$var241) / Math.sqrt(cv$temp$71$var243))) - (0.5 * Math.log(cv$temp$71$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$70$var241) / Math.sqrt(cv$temp$71$var243))) - (0.5 * Math.log(cv$temp$71$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$70$var241) / Math.sqrt(cv$temp$71$var243))) - (0.5 * Math.log(cv$temp$71$var243)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 256 with the current configuration.
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
																							int traceTempVariable$currentState$224_1 = distributionTempVariable$var74$37;
																							if((index$sample$33 == sample$var196)) {
																								if((index$timeStep$34 == timeStep$var226)) {
																									for(int var119 = 0; var119 < noServers; var119 += 1) {
																										for(int var129 = 0; var129 < noStates; var129 += 1) {
																											if((var119 == server)) {
																												if((var129 == st[sample$var196][timeStep$var226])) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$72$var241;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var241 = current_metric_mean[server][traceTempVariable$currentState$224_1];
																																				cv$temp$72$var241 = var241;
																																			}
																																			double cv$temp$73$var243;
																																			{
																																				// Constructing a random variable input for use later.
																																				double var243 = current_metric_var[server][traceTempVariable$currentState$224_1];
																																				cv$temp$73$var243 = var243;
																																			}
																																			
																																			// Record the probability of sample task 256 generating output with current configuration.
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$72$var241) / Math.sqrt(cv$temp$73$var243))) - (0.5 * Math.log(cv$temp$73$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$72$var241) / Math.sqrt(cv$temp$73$var243))) - (0.5 * Math.log(cv$temp$73$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$72$var241) / Math.sqrt(cv$temp$73$var243))) - (0.5 * Math.log(cv$temp$73$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$72$var241) / Math.sqrt(cv$temp$73$var243))) - (0.5 * Math.log(cv$temp$73$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$72$var241) / Math.sqrt(cv$temp$73$var243))) - (0.5 * Math.log(cv$temp$73$var243)))));
																																			}
																																			
																																			// Recorded the probability of reaching sample task 256 with the current configuration.
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
																							for(int index$sample$225 = 0; index$sample$225 < noSamples; index$sample$225 += 1) {
																								for(int index$timeStep$226 = 1; index$timeStep$226 < length$metric[index$sample$225][0]; index$timeStep$226 += 1) {
																									if((!((index$sample$225 == index$sample$23) && (index$timeStep$226 == index$timeStep$22)) && !((index$sample$225 == index$sample$33) && (index$timeStep$226 == index$timeStep$34)))) {
																										// Enumerating the possible outputs of Categorical 73.
																										for(int index$sample76$227 = 0; index$sample76$227 < noStates; index$sample76$227 += 1) {
																											int distributionTempVariable$var74$229 = index$sample76$227;
																											
																											// Update the probability of sampling this value from the distribution value.
																											double cv$probabilitySample76Value228 = (1.0 * distribution$sample76[((index$sample$225 - 0) / 1)][((index$timeStep$226 - 1) / 1)][index$sample76$227]);
																											int traceTempVariable$currentState$230_1 = distributionTempVariable$var74$37;
																											if((index$sample$225 == sample$var196)) {
																												if((index$timeStep$226 == timeStep$var226)) {
																													for(int var119 = 0; var119 < noServers; var119 += 1) {
																														for(int var129 = 0; var129 < noStates; var129 += 1) {
																															if((var119 == server)) {
																																if((var129 == st[sample$var196][timeStep$var226])) {
																																	for(int var146 = 0; var146 < noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == st[sample$var196][timeStep$var226])) {
																																					{
																																						{
																																							double cv$temp$74$var241;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var241 = current_metric_mean[server][traceTempVariable$currentState$230_1];
																																								cv$temp$74$var241 = var241;
																																							}
																																							double cv$temp$75$var243;
																																							{
																																								// Constructing a random variable input for use later.
																																								double var243 = current_metric_var[server][traceTempVariable$currentState$230_1];
																																								cv$temp$75$var243 = var243;
																																							}
																																							
																																							// Record the probability of sample task 256 generating output with current configuration.
																																							if(((Math.log(cv$probabilitySample76Value228) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$74$var241) / Math.sqrt(cv$temp$75$var243))) - (0.5 * Math.log(cv$temp$75$var243)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value228) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$74$var241) / Math.sqrt(cv$temp$75$var243))) - (0.5 * Math.log(cv$temp$75$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								// If the second value is -infinity.
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value228) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$74$var241) / Math.sqrt(cv$temp$75$var243))) - (0.5 * Math.log(cv$temp$75$var243))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value228) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$74$var241) / Math.sqrt(cv$temp$75$var243))) - (0.5 * Math.log(cv$temp$75$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value228) + (DistributionSampling.logProbabilityGaussian(((var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - cv$temp$74$var241) / Math.sqrt(cv$temp$75$var243))) - (0.5 * Math.log(cv$temp$75$var243)))));
																																							}
																																							
																																							// Recorded the probability of reaching sample task 256 with the current configuration.
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value228);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
				
				// Processing random variable 73.
				{
					// Looking for a path between Sample 76 and consumer Categorical 73.
					{
						int traceTempVariable$var71$269_1 = cv$currentValue;
						for(int index$sample$269_2 = 0; index$sample$269_2 < noSamples; index$sample$269_2 += 1) {
							if((sample$var45 == index$sample$269_2)) {
								for(int index$timeStep$269_3 = 1; index$timeStep$269_3 < length$metric[index$sample$269_2][0]; index$timeStep$269_3 += 1) {
									if((timeStep$var66 == (index$timeStep$269_3 - 1))) {
										// Processing sample task 76 of consumer random variable null.
										{
											// Copy of index so that its values can be safely substituted
											int index$timeStep$271 = index$timeStep$269_3;
											
											// Copy of index so that its values can be safely substituted
											int index$sample$272 = index$sample$269_2;
											
											// A local array to hold the accumulated distributions of the sample tasks for each
											// configuration of distributions.
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var73[threadID$cv$sample$var45];
											
											// Zero all the elements in the distribution accumulator
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											
											// Zero an accumulator to track the probabilities reached.
											double cv$reachedDistributionProbability = 0.0;
											
											// Enumerating the possible arguments for the variable Categorical 73 which is consuming
											// the output of Sample task 76.
											for(int var32 = 0; var32 < noStates; var32 += 1) {
												if((var32 == st[index$sample$269_2][(index$timeStep$269_3 - 1)])) {
													{
														// Declare and zero an accumulator for tracking the reached source probability space.
														double scopeVariable$reachedSourceProbability = 0.0;
														
														// Enumerating the possible arguments for Categorical 73.
														if(fixedFlag$sample57) {
															for(int index$sample$274_1 = 0; index$sample$274_1 < noSamples; index$sample$274_1 += 1) {
																if((index$sample$274_1 == sample$var45)) {
																	if((0 == (timeStep$var66 - 1))) {
																		for(int index$var32$280_1 = 0; index$var32$280_1 < noStates; index$var32$280_1 += 1) {
																			if((index$var32$280_1 == st[sample$var45][(timeStep$var66 - 1)]))
																				// Add the probability of this argument configuration.
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																	}
																}
															}
														} else {
															for(int index$sample$275 = 0; index$sample$275 < noSamples; index$sample$275 += 1) {
																if(true) {
																	// Enumerating the possible outputs of Categorical 54.
																	for(int index$sample57$276 = 0; index$sample57$276 < noStates; index$sample57$276 += 1) {
																		int distributionTempVariable$var55$278 = index$sample57$276;
																		
																		// Update the probability of sampling this value from the distribution value.
																		double cv$probabilitySample57Value277 = (1.0 * distribution$sample57[((index$sample$275 - 0) / 1)][index$sample57$276]);
																		if((index$sample$275 == sample$var45)) {
																			if((0 == (timeStep$var66 - 1))) {
																				for(int index$var32$281_1 = 0; index$var32$281_1 < noStates; index$var32$281_1 += 1) {
																					if((index$var32$281_1 == st[sample$var45][(timeStep$var66 - 1)]))
																						// Add the probability of this argument configuration.
																						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample57Value277);
																				}
																			}
																		}
																	}
																}
															}
														}
														
														// Enumerating the possible arguments for Categorical 73.
														int traceTempVariable$var71$282_1 = cv$currentValue;
														if((index$sample$23 == sample$var45)) {
															if((index$timeStep$22 == (timeStep$var66 - 1))) {
																for(int index$var32$289_1 = 0; index$var32$289_1 < noStates; index$var32$289_1 += 1) {
																	if((index$var32$289_1 == st[sample$var45][(timeStep$var66 - 1)]))
																		// Add the probability of this argument configuration.
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
															}
														}
														for(int index$sample$283 = 0; index$sample$283 < noSamples; index$sample$283 += 1) {
															for(int index$timeStep$284 = 1; index$timeStep$284 < length$metric[index$sample$283][0]; index$timeStep$284 += 1) {
																if((!((index$sample$283 == index$sample$23) && (index$timeStep$284 == index$timeStep$22)) && !((index$sample$283 == index$sample$272) && (index$timeStep$284 == index$timeStep$271)))) {
																	// Enumerating the possible outputs of Categorical 73.
																	for(int index$sample76$285 = 0; index$sample76$285 < noStates; index$sample76$285 += 1) {
																		int distributionTempVariable$var74$287 = index$sample76$285;
																		
																		// Update the probability of sampling this value from the distribution value.
																		double cv$probabilitySample76Value286 = (1.0 * distribution$sample76[((index$sample$283 - 0) / 1)][((index$timeStep$284 - 1) / 1)][index$sample76$285]);
																		int traceTempVariable$var71$288_1 = cv$currentValue;
																		if((index$sample$283 == sample$var45)) {
																			if((index$timeStep$284 == (timeStep$var66 - 1))) {
																				for(int index$var32$290_1 = 0; index$var32$290_1 < noStates; index$var32$290_1 += 1) {
																					if((index$var32$290_1 == st[sample$var45][(timeStep$var66 - 1)]))
																						// Add the probability of this argument configuration.
																						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample76Value286);
																				}
																			}
																		}
																	}
																}
															}
														}
														double[] cv$temp$76$var72;
														{
															// Constructing a random variable input for use later.
															double[] var72 = m[traceTempVariable$var71$269_1];
															cv$temp$76$var72 = var72;
														}
														int cv$temp$77$$var4025;
														{
															// Constructing a random variable input for use later.
															int $var4025 = noStates;
															cv$temp$77$$var4025 = $var4025;
														}
														
														// The probability of reaching the consumer with this set of consumer arguments
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														
														// Record the reached distribution.
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														
														// Add the current distribution to the distribution accumulator.
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$76$var72, cv$temp$77$$var4025);
													}
												}
											}
											
											// A local copy of the samples' distribution.
											double[] cv$sampleDistribution = distribution$sample76[((index$sample$269_2 - 0) / 1)][((index$timeStep$269_3 - 1) / 1)];
											
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
			double[] cv$localProbability = distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)];
			
			// The sum of all the probabilities in log space
			double cv$logSum = 0.0;
			
			// Sum all the values
			{
				// Initialise the max to the first element.
				double cv$lseMax = cv$stateProbabilityLocal[0];
				
				// Find max value.
				for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
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
					for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
						cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
					
					// Increment the value of the target, moving the value back into log space.
					cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
				}
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var20$countGlobal
		{
			// Allocation of cv$var20$countGlobal for single threaded execution
			cv$var20$countGlobal = new double[noStates];
		}
		
		// Constructor for cv$var33$countGlobal
		{
			// Allocation of cv$var33$countGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var33$countGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var33$countGlobal[cv$index] = new double[noStates];
			}
		}
		
		// Constructor for cv$distributionAccumulator$var73
		{
			// Variable to record the maximum value of Task Get 74. Initially set to the value
			// of putTask 34.
			int cv$var34$max = noStates;
			
			// Allocation of cv$distributionAccumulator$var73 for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$distributionAccumulator$var73 = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$distributionAccumulator$var73[cv$index] = new double[cv$var34$max];
			}
		}
		
		// Constructor for cv$var55$stateProbabilityGlobal
		{
			// Allocation of cv$var55$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var55$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var55$stateProbabilityGlobal[cv$index] = new double[noStates];
			}
		}
		
		// Constructor for guard$sample57gaussian255$global
		{
			// Calculate the largest index of sample that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_sample$var196 = 0;
			
			// Calculate the largest index of server that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_server = 0;
			
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var226 = 0;
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, ((length$metric[sample$var196][0] - 0) / 1));
				cv$max_server = Math.max(cv$max_server, ((length$metric[0].length - 0) / 1));
			}
			cv$max_sample$var196 = Math.max(cv$max_sample$var196, ((length$metric.length - 0) / 1));
			
			// Allocation of guard$sample57gaussian255$global for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				guard$sample57gaussian255$global = new boolean[cv$threadCount][][][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample57gaussian255$global[cv$index] = new boolean[cv$max_sample$var196][cv$max_server][cv$max_timeStep$var226];
			}
		}
		
		// Constructor for cv$var74$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 74. Initially set to the value
			// of putTask 34.
			int cv$var34$max = noStates;
			
			// Allocation of cv$var74$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var74$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var74$stateProbabilityGlobal[cv$index] = new double[cv$var34$max];
			}
		}
		
		// Constructor for guard$sample76gaussian255$global
		{
			// Calculate the largest index of sample that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_sample$var196 = 0;
			
			// Calculate the largest index of server that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_server = 0;
			
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var226 = 0;
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, ((length$metric[sample$var196][0] - 0) / 1));
				cv$max_server = Math.max(cv$max_server, ((length$metric[0].length - 0) / 1));
			}
			cv$max_sample$var196 = Math.max(cv$max_sample$var196, ((length$metric.length - 0) / 1));
			
			// Allocation of guard$sample76gaussian255$global for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				guard$sample76gaussian255$global = new boolean[cv$threadCount][][][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample76gaussian255$global[cv$index] = new boolean[cv$max_sample$var196][cv$max_server][cv$max_timeStep$var226];
			}
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
		if(!fixedFlag$sample20) {
			// Constructor for initialStateDistribution
			{
				initialStateDistribution = new double[noStates];
			}
		}
		
		// If m has not been set already allocate space.
		if(!fixedFlag$sample33) {
			// Constructor for m
			{
				m = new double[noStates][];
				for(int var32 = 0; var32 < noStates; var32 += 1)
					m[var32] = new double[noStates];
			}
		}
		
		// If st has not been set already allocate space.
		if((!fixedFlag$sample57 || !fixedFlag$sample76)) {
			// Constructor for st
			{
				st = new int[length$metric.length][];
				for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
					st[sample$var45] = new int[length$metric[sample$var45][0]];
			}
		}
		
		// Constructor for metric_g
		{
			metric_g = new double[length$metric.length][][];
			for(int var90 = 0; var90 < length$metric.length; var90 += 1) {
				double[][] subarray$0 = new double[length$metric[0].length][];
				metric_g[var90] = subarray$0;
			}
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1) {
					double[][] subarray$1 = metric_g[sample$var196];
					subarray$1[server] = new double[length$metric[sample$var196][0]];
				}
			}
		}
		
		// Constructor for metric_valid_g
		{
			metric_valid_g = new boolean[length$metric.length][][];
			for(int var103 = 0; var103 < length$metric.length; var103 += 1) {
				boolean[][] subarray$0 = new boolean[length$metric[0].length][];
				metric_valid_g[var103] = subarray$0;
			}
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1) {
					boolean[][] subarray$1 = metric_valid_g[sample$var196];
					subarray$1[server] = new boolean[length$metric[sample$var196][0]];
				}
			}
		}
		
		// If current_metric_mean has not been set already allocate space.
		if(!fixedFlag$sample134) {
			// Constructor for current_metric_mean
			{
				current_metric_mean = new double[length$metric[0].length][];
				for(int var119 = 0; var119 < length$metric[0].length; var119 += 1)
					current_metric_mean[var119] = new double[noStates];
			}
		}
		
		// If current_metric_var has not been set already allocate space.
		if(!fixedFlag$sample162) {
			// Constructor for current_metric_var
			{
				current_metric_var = new double[length$metric[0].length][];
				for(int var146 = 0; var146 < length$metric[0].length; var146 += 1)
					current_metric_var[var146] = new double[noStates];
			}
		}
		
		// If current_metric_valid_bias has not been set already allocate space.
		if(!fixedFlag$sample190) {
			// Constructor for current_metric_valid_bias
			{
				current_metric_valid_bias = new double[length$metric[0].length][];
				for(int var173 = 0; var173 < length$metric[0].length; var173 += 1)
					current_metric_valid_bias[var173] = new double[noStates];
			}
		}
		
		// Constructor for var245
		{
			var245 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				var245[((sample$var196 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var196][0] - 1) - 0) / 1) + 1)];
			}
		}
		
		// Constructor for distribution$sample57
		{
			distribution$sample57 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
				distribution$sample57[((sample$var45 - 0) / 1)] = new double[noStates];
		}
		
		// Constructor for distribution$sample76
		{
			distribution$sample76 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1) {
				double[][] subarray$0 = new double[((((length$metric[sample$var45][0] - 1) - 1) / 1) + 1)][];
				distribution$sample76[((sample$var45 - 0) / 1)] = subarray$0;
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					subarray$0[((timeStep$var66 - 1) / 1)] = new double[noStates];
			}
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
						double[] var33 = m[var32];
						if(!fixedFlag$sample33)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var33);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
						int[] var52 = st[sample$var45];
						if(!fixedFlag$sample57)
							var52[0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						int[] var67 = st[sample$var45];
						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(!fixedFlag$sample76)
								var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noServers, 1,
			(int forStart$var119, int forEnd$var119, int threadID$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var119 = forStart$var119; var119 < forEnd$var119; var119 += 1) {
						double[] var120 = current_metric_mean[var119];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noStates, 1,
							(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1) {
										if(!fixedFlag$sample134)
											var120[var129] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$2)));
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noServers, 1,
			(int forStart$var146, int forEnd$var146, int threadID$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var146 = forStart$var146; var146 < forEnd$var146; var146 += 1) {
						double[] var147 = current_metric_var[var146];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noStates, 1,
							(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1) {
										if(!fixedFlag$sample162)
											var147[var156] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noServers, 1,
			(int forStart$var173, int forEnd$var173, int threadID$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var173 = forStart$var173; var173 < forEnd$var173; var173 += 1) {
						double[] var174 = current_metric_valid_bias[var173];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noStates, 1,
							(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1) {
										if(!fixedFlag$sample190)
											var174[var183] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$index$sample$var196, int forEnd$index$sample$var196, int threadID$index$sample$var196, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$sample$var196 = forStart$index$sample$var196; index$sample$var196 < forEnd$index$sample$var196; index$sample$var196 += 1) {
						int sample$var196 = index$sample$var196;
						int threadID$sample$var196 = threadID$index$sample$var196;
						boolean[][] var215 = metric_valid_g[sample$var196];
						double[][] var211 = metric_g[sample$var196];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noServers, 1,
							(int forStart$index$server, int forEnd$index$server, int threadID$index$server, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$server = forStart$index$server; index$server < forEnd$index$server; index$server += 1) {
										int server = index$server;
										int threadID$server = threadID$index$server;
										boolean[] metric_valid_inner = metric_valid_g[sample$var196][server];
										double[] metric_inner = var211[server];
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, length$metric[sample$var196][0], 1,
											(int forStart$timeStep$var226, int forEnd$timeStep$var226, int threadID$timeStep$var226, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int timeStep$var226 = forStart$timeStep$var226; timeStep$var226 < forEnd$timeStep$var226; timeStep$var226 += 1) {
														metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(RNG$3, current_metric_valid_bias[server][st[sample$var196][timeStep$var226]]);
														if(metric_valid_inner[timeStep$var226]) {
															var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = ((Math.sqrt(current_metric_var[server][st[sample$var196][timeStep$var226]]) * DistributionSampling.sampleGaussian(RNG$3)) + current_metric_mean[server][st[sample$var196][timeStep$var226]]);
															metric_inner[timeStep$var226] = var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
														}
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
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
						double[] var33 = m[var32];
						if(!fixedFlag$sample33)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var33);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
						// Create local copy of variable probabilities.
						double[] cv$distribution$sample57 = distribution$sample57[((sample$var45 - 0) / 1)];
						for(int index$var54 = 0; index$var54 < noStates; index$var54 += 1) {
							// Probability for this value
							double cv$value = (((0.0 <= index$var54) && (index$var54 < noStates))?initialStateDistribution[index$var54]:0.0);
							if(!fixedFlag$sample57)
								// Save the probability of each value
								cv$distribution$sample57[index$var54] = cv$value;
						}
						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
							// Create local copy of variable probabilities.
							double[] cv$distribution$sample76 = distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)];
							for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1) {
								if(!fixedFlag$sample76)
									// Zero the probability of each value
									cv$distribution$sample76[index$var73] = 0.0;
							}
							
							// Iterate through possible values for var73's arguments.
							// 
							// Enumerating the possible arguments for Categorical 73.
							if(fixedFlag$sample57) {
								for(int index$sample$1_1 = 0; index$sample$1_1 < noSamples; index$sample$1_1 += 1) {
									if((index$sample$1_1 == sample$var45)) {
										if((0 == (timeStep$var66 - 1))) {
											for(int var32 = 0; var32 < noStates; var32 += 1) {
												if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
													{
														if(!fixedFlag$sample76) {
															double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
															for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
																// Save the probability of each value
																cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (1.0 * (((0.0 <= index$var73) && (index$var73 < noStates))?var72[index$var73]:0.0)));
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
										// Enumerating the possible outputs of Categorical 54.
										for(int index$sample57$3 = 0; index$sample57$3 < noStates; index$sample57$3 += 1) {
											int distributionTempVariable$var55$5 = index$sample57$3;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample57Value4 = (1.0 * distribution$sample57[((index$sample$2 - 0) / 1)][index$sample57$3]);
											if((index$sample$2 == sample$var45)) {
												if((0 == (timeStep$var66 - 1))) {
													for(int var32 = 0; var32 < noStates; var32 += 1) {
														if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
															{
																if(!fixedFlag$sample76) {
																	double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
																	for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
																		// Save the probability of each value
																		cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample57Value4 * (((0.0 <= index$var73) && (index$var73 < noStates))?var72[index$var73]:0.0)));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							
							// Enumerating the possible arguments for Categorical 73.
							if(fixedFlag$sample76) {
								for(int index$sample$9_1 = 0; index$sample$9_1 < noSamples; index$sample$9_1 += 1) {
									for(int index$timeStep$9_2 = 1; index$timeStep$9_2 < length$metric[index$sample$9_1][0]; index$timeStep$9_2 += 1) {
										if((index$sample$9_1 == sample$var45)) {
											if((index$timeStep$9_2 == (timeStep$var66 - 1))) {
												for(int var32 = 0; var32 < noStates; var32 += 1) {
													if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
														{
															if(!fixedFlag$sample76) {
																double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
																for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
																	// Save the probability of each value
																	cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (1.0 * (((0.0 <= index$var73) && (index$var73 < noStates))?var72[index$var73]:0.0)));
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
											// Enumerating the possible outputs of Categorical 73.
											for(int index$sample76$12 = 0; index$sample76$12 < noStates; index$sample76$12 += 1) {
												int distributionTempVariable$var74$14 = index$sample76$12;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample76Value13 = (1.0 * distribution$sample76[((index$sample$10 - 0) / 1)][((index$timeStep$11 - 1) / 1)][index$sample76$12]);
												if((index$sample$10 == sample$var45)) {
													if((index$timeStep$11 == (timeStep$var66 - 1))) {
														for(int var32 = 0; var32 < noStates; var32 += 1) {
															if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
																{
																	if(!fixedFlag$sample76) {
																		double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
																		for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
																			// Save the probability of each value
																			cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample76Value13 * (((0.0 <= index$var73) && (index$var73 < noStates))?var72[index$var73]:0.0)));
																	}
																}
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
							double cv$var73$sum = 0.0;
							for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1) {
								if(!fixedFlag$sample76)
									// sum the probability of each value
									cv$var73$sum = (cv$var73$sum + cv$distribution$sample76[index$var73]);
							}
							for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1) {
								if(!fixedFlag$sample76)
									// Normalise the probability of each value
									cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] / cv$var73$sum);
							}
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noServers, 1,
			(int forStart$var119, int forEnd$var119, int threadID$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var119 = forStart$var119; var119 < forEnd$var119; var119 += 1) {
						double[] var120 = current_metric_mean[var119];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noStates, 1,
							(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1) {
										if(!fixedFlag$sample134)
											var120[var129] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$2)));
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noServers, 1,
			(int forStart$var146, int forEnd$var146, int threadID$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var146 = forStart$var146; var146 < forEnd$var146; var146 += 1) {
						double[] var147 = current_metric_var[var146];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noStates, 1,
							(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1) {
										if(!fixedFlag$sample162)
											var147[var156] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noServers, 1,
			(int forStart$var173, int forEnd$var173, int threadID$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var173 = forStart$var173; var173 < forEnd$var173; var173 += 1) {
						double[] var174 = current_metric_valid_bias[var173];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noStates, 1,
							(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1) {
										if(!fixedFlag$sample190)
											var174[var183] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
						double[] var33 = m[var32];
						if(!fixedFlag$sample33)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var33);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
						int[] var52 = st[sample$var45];
						if(!fixedFlag$sample57)
							var52[0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						int[] var67 = st[sample$var45];
						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(!fixedFlag$sample76)
								var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noServers, 1,
			(int forStart$var119, int forEnd$var119, int threadID$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var119 = forStart$var119; var119 < forEnd$var119; var119 += 1) {
						double[] var120 = current_metric_mean[var119];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noStates, 1,
							(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1) {
										if(!fixedFlag$sample134)
											var120[var129] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$2)));
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noServers, 1,
			(int forStart$var146, int forEnd$var146, int threadID$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var146 = forStart$var146; var146 < forEnd$var146; var146 += 1) {
						double[] var147 = current_metric_var[var146];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noStates, 1,
							(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1) {
										if(!fixedFlag$sample162)
											var147[var156] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noServers, 1,
			(int forStart$var173, int forEnd$var173, int threadID$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var173 = forStart$var173; var173 < forEnd$var173; var173 += 1) {
						double[] var174 = current_metric_valid_bias[var173];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noStates, 1,
							(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1) {
										if(!fixedFlag$sample190)
											var174[var183] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$index$sample$var196, int forEnd$index$sample$var196, int threadID$index$sample$var196, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$sample$var196 = forStart$index$sample$var196; index$sample$var196 < forEnd$index$sample$var196; index$sample$var196 += 1) {
						int sample$var196 = index$sample$var196;
						int threadID$sample$var196 = threadID$index$sample$var196;
						boolean[][] var215 = metric_valid_g[sample$var196];
						double[][] var211 = metric_g[sample$var196];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noServers, 1,
							(int forStart$index$server, int forEnd$index$server, int threadID$index$server, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$server = forStart$index$server; index$server < forEnd$index$server; index$server += 1) {
										int server = index$server;
										int threadID$server = threadID$index$server;
										boolean[] metric_valid_inner = metric_valid_g[sample$var196][server];
										double[] metric_inner = var211[server];
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, length$metric[sample$var196][0], 1,
											(int forStart$timeStep$var226, int forEnd$timeStep$var226, int threadID$timeStep$var226, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int timeStep$var226 = forStart$timeStep$var226; timeStep$var226 < forEnd$timeStep$var226; timeStep$var226 += 1) {
														metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(RNG$3, current_metric_valid_bias[server][st[sample$var196][timeStep$var226]]);
														if(metric_valid_inner[timeStep$var226]) {
															var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = ((Math.sqrt(current_metric_var[server][st[sample$var196][timeStep$var226]]) * DistributionSampling.sampleGaussian(RNG$3)) + current_metric_mean[server][st[sample$var196][timeStep$var226]]);
															metric_inner[timeStep$var226] = var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
														}
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
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
						double[] var33 = m[var32];
						if(!fixedFlag$sample33)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var33);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
						int[] var52 = st[sample$var45];
						if(!fixedFlag$sample57)
							var52[0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						int[] var67 = st[sample$var45];
						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(!fixedFlag$sample76)
								var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noServers, 1,
			(int forStart$var119, int forEnd$var119, int threadID$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var119 = forStart$var119; var119 < forEnd$var119; var119 += 1) {
						double[] var120 = current_metric_mean[var119];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noStates, 1,
							(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1) {
										if(!fixedFlag$sample134)
											var120[var129] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$2)));
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noServers, 1,
			(int forStart$var146, int forEnd$var146, int threadID$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var146 = forStart$var146; var146 < forEnd$var146; var146 += 1) {
						double[] var147 = current_metric_var[var146];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noStates, 1,
							(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1) {
										if(!fixedFlag$sample162)
											var147[var156] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noServers, 1,
			(int forStart$var173, int forEnd$var173, int threadID$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var173 = forStart$var173; var173 < forEnd$var173; var173 += 1) {
						double[] var174 = current_metric_valid_bias[var173];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noStates, 1,
							(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1) {
										if(!fixedFlag$sample190)
											var174[var183] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
						double[] var33 = m[var32];
						if(!fixedFlag$sample33)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var33);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
						int[] var52 = st[sample$var45];
						if(!fixedFlag$sample57)
							var52[0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						int[] var67 = st[sample$var45];
						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(!fixedFlag$sample76)
								var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noServers, 1,
			(int forStart$var119, int forEnd$var119, int threadID$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var119 = forStart$var119; var119 < forEnd$var119; var119 += 1) {
						double[] var120 = current_metric_mean[var119];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noStates, 1,
							(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1) {
										if(!fixedFlag$sample134)
											var120[var129] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$2)));
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noServers, 1,
			(int forStart$var146, int forEnd$var146, int threadID$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var146 = forStart$var146; var146 < forEnd$var146; var146 += 1) {
						double[] var147 = current_metric_var[var146];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noStates, 1,
							(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1) {
										if(!fixedFlag$sample162)
											var147[var156] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noServers, 1,
			(int forStart$var173, int forEnd$var173, int threadID$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var173 = forStart$var173; var173 < forEnd$var173; var173 += 1) {
						double[] var174 = current_metric_valid_bias[var173];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noStates, 1,
							(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1) {
										if(!fixedFlag$sample190)
											var174[var183] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
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
			if(!fixedFlag$sample20)
				sample20();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
							if(!fixedFlag$sample33)
								sample33(var32, threadID$var32, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
							if(!fixedFlag$sample57)
								sample57(sample$var45, threadID$sample$var45, RNG$1);
							for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
								if(!fixedFlag$sample76)
									sample76(sample$var45, timeStep$var66, threadID$sample$var45, RNG$1);
							}
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$index$var119, int forEnd$index$var119, int threadID$index$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$var119 = forStart$index$var119; index$var119 < forEnd$index$var119; index$var119 += 1) {
							int var119 = index$var119;
							int threadID$var119 = threadID$index$var119;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1) {
											if(!fixedFlag$sample134)
												sample134(var119, var129, threadID$var129, RNG$2);
										}
								}
							);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$index$var146, int forEnd$index$var146, int threadID$index$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$var146 = forStart$index$var146; index$var146 < forEnd$index$var146; index$var146 += 1) {
							int var146 = index$var146;
							int threadID$var146 = threadID$index$var146;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1) {
											if(!fixedFlag$sample162)
												sample162(var146, var156, threadID$var156, RNG$2);
										}
								}
							);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$index$var173, int forEnd$index$var173, int threadID$index$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$var173 = forStart$index$var173; index$var173 < forEnd$index$var173; index$var173 += 1) {
							int var173 = index$var173;
							int threadID$var173 = threadID$index$var173;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1) {
											if(!fixedFlag$sample190)
												sample190(var173, var183, threadID$var183, RNG$2);
										}
								}
							);
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$index$var173, int forEnd$index$var173, int threadID$index$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$var173 = forStart$index$var173; index$var173 < forEnd$index$var173; index$var173 += 1) {
							int var173 = index$var173;
							int threadID$var173 = threadID$index$var173;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1) {
											if(!fixedFlag$sample190)
												sample190(var173, var183, threadID$var183, RNG$2);
										}
								}
							);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$index$var146, int forEnd$index$var146, int threadID$index$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$var146 = forStart$index$var146; index$var146 < forEnd$index$var146; index$var146 += 1) {
							int var146 = index$var146;
							int threadID$var146 = threadID$index$var146;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1) {
											if(!fixedFlag$sample162)
												sample162(var146, var156, threadID$var156, RNG$2);
										}
								}
							);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$index$var119, int forEnd$index$var119, int threadID$index$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$var119 = forStart$index$var119; index$var119 < forEnd$index$var119; index$var119 += 1) {
							int var119 = index$var119;
							int threadID$var119 = threadID$index$var119;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1) {
											if(!fixedFlag$sample134)
												sample134(var119, var129, threadID$var129, RNG$2);
										}
								}
							);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
							for(int timeStep$var66 = (length$metric[sample$var45][0] - ((((length$metric[sample$var45][0] - 1) - 1) % 1) + 1)); timeStep$var66 >= ((1 - 1) + 1); timeStep$var66 -= 1) {
								if(!fixedFlag$sample76)
									sample76(sample$var45, timeStep$var66, threadID$sample$var45, RNG$1);
							}
							if(!fixedFlag$sample57)
								sample57(sample$var45, threadID$sample$var45, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
							if(!fixedFlag$sample33)
								sample33(var32, threadID$var32, RNG$1);
						}
				}
			);
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
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
						v[var16] = 0.1;
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
		logProbability$var19 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$initialStateDistribution = Double.NaN;
		logProbability$var21 = Double.NaN;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample33)
			logProbability$var33 = Double.NaN;
		logProbability$var54 = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var55 = Double.NaN;
		logProbability$var73 = Double.NaN;
		if(!fixedProbFlag$sample76)
			logProbability$var74 = Double.NaN;
		logProbability$var108 = Double.NaN;
		logProbability$current_metric_mean = 0.0;
		if(!fixedProbFlag$sample134)
			logProbability$var130 = Double.NaN;
		logProbability$var135 = Double.NaN;
		logProbability$current_metric_var = 0.0;
		if(!fixedProbFlag$sample162)
			logProbability$var157 = Double.NaN;
		logProbability$var162 = Double.NaN;
		logProbability$current_metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample190)
			logProbability$var184 = Double.NaN;
		logProbability$var231 = Double.NaN;
		logProbability$metric_valid_inner = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample241)
			logProbability$var232 = Double.NaN;
		logProbability$var244 = Double.NaN;
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample256)
			logProbability$var245 = Double.NaN;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
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
		for(int sample$var196 = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample$var196 >= ((0 - 1) + 1); sample$var196 -= 1) {
			for(int server = (noServers - ((((noServers - 1) - 0) % 1) + 1)); server >= ((0 - 1) + 1); server -= 1) {
				for(int timeStep$var226 = (length$metric[sample$var196][0] - ((((length$metric[sample$var196][0] - 1) - 0) % 1) + 1)); timeStep$var226 >= ((0 - 1) + 1); timeStep$var226 -= 1) {
					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
						{
							{
								double[][] var211;
								var211 = metric_g[sample$var196];
								double[] metric_inner;
								metric_inner = var211[server];
								var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = metric_inner[timeStep$var226];
							}
						}
					}
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