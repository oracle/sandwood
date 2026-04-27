package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMMetrics4$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMMetrics4.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMMetrics4$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[][] cv$distributionAccumulator$var73;
		double[] cv$var20$countGlobal;
		double[][] cv$var33$countGlobal;
		double[][] cv$var55$stateProbabilityGlobal;
		double[][] cv$var74$stateProbabilityGlobal;
		boolean[][][][] guard$sample57gaussian255$global;
		boolean[][][][] guard$sample76gaussian255$global;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var20$countGlobal
			{
				// Allocation of cv$var20$countGlobal for single threaded execution
				cv$var20$countGlobal = new double[state.noStates];
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
						cv$var33$countGlobal[cv$index] = new double[state.noStates];
				}
			}
			
			// Constructor for cv$distributionAccumulator$var73
			{
				// Variable to record the maximum value of Task Get 74. Initially set to the value
				// of putTask 34.
				int cv$var34$max = state.noStates;
				
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
						cv$var55$stateProbabilityGlobal[cv$index] = new double[state.noStates];
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
				for(int sample$var196 = 0; sample$var196 < state.length$metric.length; sample$var196 += 1) {
					for(int server = 0; server < state.length$metric[0].length; server += 1)
						cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, ((state.length$metric[sample$var196][0] - 0) / 1));
					cv$max_server = Math.max(cv$max_server, ((state.length$metric[0].length - 0) / 1));
				}
				cv$max_sample$var196 = Math.max(cv$max_sample$var196, ((state.length$metric.length - 0) / 1));
				
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
				int cv$var34$max = state.noStates;
				
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
				for(int sample$var196 = 0; sample$var196 < state.length$metric.length; sample$var196 += 1) {
					for(int server = 0; server < state.length$metric[0].length; server += 1)
						cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, ((state.length$metric[sample$var196][0] - 0) / 1));
					cv$max_server = Math.max(cv$max_server, ((state.length$metric[0].length - 0) / 1));
				}
				cv$max_sample$var196 = Math.max(cv$max_sample$var196, ((state.length$metric.length - 0) / 1));
				
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
	}


	public HMMMetrics4$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample134
	private final void drawValueSample134(int var119, int var129, int threadID$cv$var129, Rng RNG$) {
		double[] var120 = state.current_metric_mean[var119];
		var120[var129] = (0.0 + (((double)state.max_metric - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
	}

	// Pick a value from the distribution for the unconditioned variable from sample162
	private final void drawValueSample162(int var146, int var156, int threadID$cv$var156, Rng RNG$) {
		double[] var147 = state.current_metric_var[var146];
		var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample190
	private final void drawValueSample190(int var173, int var183, int threadID$cv$var183, Rng RNG$) {
		double[] var174 = state.current_metric_valid_bias[var173];
		var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample20
	private final void drawValueSample20() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
	}

	// Pick a value from the distribution for the unconditioned variable from sample33
	private final void drawValueSample33(int var32, int threadID$cv$var32, Rng RNG$) {
		double[] var33 = state.m[var32];
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, var33);
	}

	// Pick a value from the distribution for the unconditioned variable from sample57
	private final void drawValueSample57(int sample$var45, int threadID$cv$sample$var45, Rng RNG$) {
		// Copy of index so that its values can be safely substituted
		int index$sample$1 = sample$var45;
		int[] var52 = state.st[sample$var45];
		var52[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
	}

	// Pick a value from the distribution for the unconditioned variable from sample76
	private final void drawValueSample76(int sample$var45, int timeStep$var66, int threadID$cv$sample$var45, Rng RNG$) {
		// Copy of index so that its values can be safely substituted
		int index$timeStep$1 = timeStep$var66;
		
		// Copy of index so that its values can be safely substituted
		int index$sample$2 = sample$var45;
		int[] var67 = state.st[sample$var45];
		var67[timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 134 drawn from Uniform 108. Inference was performed using Metropolis-Hastings.
	private final void inferSample134(int var119, int var129, int threadID$cv$var129, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample134[((var119 - 0) / 1)][((var129 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// Metropolis-Hastings
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = state.current_metric_mean[var119][var129];
			
			// The probability of the random variable generating the originally sampled value
			double cv$originalProbability = 0.0;
			
			// Calculate a proposed variance.
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			
			// Ensure the variance is at least 0.01
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			
			// The proposed new value for the sample
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			
			// The probability of the random variable generating the new sample value.
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample134[((var119 - 0) / 1)][((var129 - 0) / 1)] || (cv$valuePos == 0))) {
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
						// 
						// Write out the value of the sample to a temporary variable prior to updating the
						// intermediate variables.
						double var130 = cv$proposedValue;
						
						// Guards to ensure that current_metric_mean is only updated when there is a valid
						// path.
						{
							{
								{
									double[] var120 = state.current_metric_mean[var119];
									var120[var129] = cv$currentValue;
								}
							}
						}
					}
					{
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						
						// Constructing a random variable input for use later.
						double var107 = (double)state.max_metric;
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < var107))?(-Math.log((var107 - 0.0))):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 244.
						{
							// Looking for a path between Sample 134 and consumer Gaussian 244.
							{
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									for(int server = 0; server < state.noServers; server += 1) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
												if(state.fixedFlag$sample57) {
													{
														for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
															if((sample$var45 == sample$var196)) {
																if((0 == timeStep$var226)) {
																	{
																		double traceTempVariable$var241$11_1 = cv$currentValue;
																		if((var119 == server)) {
																			if((var129 == state.st[sample$var196][timeStep$var226])) {
																				// Processing sample task 256 of consumer random variable null.
																				{
																					{
																						// Flag recording if this sample task of the consuming random variable is constrained.
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							// Mark that the sample has observed constrained data.
																							state.constrainedFlag$sample134[((var119 - 0) / 1)][((var129 - 0) / 1)] = true;
																							
																							// Set an accumulator to sum the probabilities for each possible configuration of
																							// inputs.
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							
																							// Set an accumulator to record the consumer distributions not seen. Initially set
																							// to 1 as seen values will be deducted from this value.
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																								// the output of Sample task 134.
																								{
																									for(int index$sample$29_1 = 0; index$sample$29_1 < state.noSamples; index$sample$29_1 += 1) {
																										if((index$sample$29_1 == sample$var196)) {
																											if((0 == timeStep$var226)) {
																												{
																													for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																														for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																															if((var146 == server)) {
																																if((var156 == state.st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			{
																																				// Constructing a random variable input for use later.
																																				double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																																				
																																				// Record the probability of sample task 256 generating output with current configuration.
																																				if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																								
																								// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																								// the output of Sample task 134.
																								if(state.fixedFlag$sample76) {
																									{
																										for(int index$sample$31_1 = 0; index$sample$31_1 < state.noSamples; index$sample$31_1 += 1) {
																											for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$31_1][0]; timeStep$var66 += 1) {
																												if((index$sample$31_1 == sample$var196)) {
																													if((timeStep$var66 == timeStep$var226)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == state.st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					{
																																						// Constructing a random variable input for use later.
																																						double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																																						
																																						// Record the probability of sample task 256 generating output with current configuration.
																																						if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																									for(int index$sample$32 = 0; index$sample$32 < state.noSamples; index$sample$32 += 1) {
																										for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$32][0]; timeStep$var66 += 1) {
																											if(true) {
																												// Enumerating the possible outputs of Categorical 73.
																												for(int index$sample76$34 = 0; index$sample76$34 < state.noStates; index$sample76$34 += 1) {
																													int distributionTempVariable$var74$36 = index$sample76$34;
																													
																													// Update the probability of sampling this value from the distribution value.
																													double cv$probabilitySample76Value35 = (1.0 * state.distribution$sample76[((index$sample$32 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$34]);
																													{
																														int traceTempVariable$currentState$37_1 = distributionTempVariable$var74$36;
																														if((index$sample$32 == sample$var196)) {
																															if((timeStep$var66 == timeStep$var226)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$37_1)) {
																																					{
																																						{
																																							{
																																								// Constructing a random variable input for use later.
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$37_1];
																																								
																																								// Record the probability of sample task 256 generating output with current configuration.
																																								if(((Math.log(cv$probabilitySample76Value35) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value35) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value35) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value35) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value35) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
												} else {
													for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
														if(true) {
															// Enumerating the possible outputs of Categorical 54.
															for(int index$sample57$7 = 0; index$sample57$7 < state.noStates; index$sample57$7 += 1) {
																int distributionTempVariable$var55$9 = index$sample57$7;
																
																// Update the probability of sampling this value from the distribution value.
																double cv$probabilitySample57Value8 = (1.0 * state.distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$7]);
																{
																	int traceTempVariable$currentState$10_1 = distributionTempVariable$var55$9;
																	if((sample$var45 == sample$var196)) {
																		if((0 == timeStep$var226)) {
																			{
																				double traceTempVariable$var241$12_1 = cv$currentValue;
																				if((var119 == server)) {
																					if((var129 == traceTempVariable$currentState$10_1)) {
																						// Processing sample task 256 of consumer random variable null.
																						{
																							{
																								// Flag recording if this sample task of the consuming random variable is constrained.
																								boolean cv$sampleConstrained = true;
																								if(cv$sampleConstrained) {
																									// Mark that the sample has observed constrained data.
																									state.constrainedFlag$sample134[((var119 - 0) / 1)][((var129 - 0) / 1)] = true;
																									
																									// Set an accumulator to sum the probabilities for each possible configuration of
																									// inputs.
																									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																									
																									// Set an accumulator to record the consumer distributions not seen. Initially set
																									// to 1 as seen values will be deducted from this value.
																									double cv$consumerDistributionProbabilityAccumulator = 1.0;
																									{
																										// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																										// the output of Sample task 134.
																										{
																											int traceTempVariable$currentState$40_1 = distributionTempVariable$var55$9;
																											if((sample$var45 == sample$var196)) {
																												if((0 == timeStep$var226)) {
																													{
																														for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																															for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																if((var146 == server)) {
																																	if((var156 == traceTempVariable$currentState$40_1)) {
																																		{
																																			{
																																				{
																																					// Constructing a random variable input for use later.
																																					double var243 = state.current_metric_var[server][traceTempVariable$currentState$40_1];
																																					
																																					// Record the probability of sample task 256 generating output with current configuration.
																																					if(((Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																										}
																										for(int index$sample$41 = 0; index$sample$41 < state.noSamples; index$sample$41 += 1) {
																											if(!(index$sample$41 == sample$var45)) {
																												// Enumerating the possible outputs of Categorical 54.
																												for(int index$sample57$42 = 0; index$sample57$42 < state.noStates; index$sample57$42 += 1) {
																													int distributionTempVariable$var55$44 = index$sample57$42;
																													
																													// Update the probability of sampling this value from the distribution value.
																													double cv$probabilitySample57Value43 = (cv$probabilitySample57Value8 * state.distribution$sample57[((index$sample$41 - 0) / 1)][index$sample57$42]);
																													{
																														int traceTempVariable$currentState$45_1 = distributionTempVariable$var55$44;
																														if((index$sample$41 == sample$var196)) {
																															if((0 == timeStep$var226)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$45_1)) {
																																					{
																																						{
																																							{
																																								// Constructing a random variable input for use later.
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$45_1];
																																								
																																								// Record the probability of sample task 256 generating output with current configuration.
																																								if(((Math.log(cv$probabilitySample57Value43) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value43) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																												}
																											}
																										}
																										
																										// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																										// the output of Sample task 134.
																										if(state.fixedFlag$sample76) {
																											{
																												for(int index$sample$48_1 = 0; index$sample$48_1 < state.noSamples; index$sample$48_1 += 1) {
																													for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$48_1][0]; timeStep$var66 += 1) {
																														if((index$sample$48_1 == sample$var196)) {
																															if((timeStep$var66 == timeStep$var226)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$10_1)) {
																																					{
																																						{
																																							{
																																								// Constructing a random variable input for use later.
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$10_1];
																																								
																																								// Record the probability of sample task 256 generating output with current configuration.
																																								if(((Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																													}
																												}
																											}
																										} else {
																											for(int index$sample$49 = 0; index$sample$49 < state.noSamples; index$sample$49 += 1) {
																												for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$49][0]; timeStep$var66 += 1) {
																													if(true) {
																														// Enumerating the possible outputs of Categorical 73.
																														for(int index$sample76$51 = 0; index$sample76$51 < state.noStates; index$sample76$51 += 1) {
																															int distributionTempVariable$var74$53 = index$sample76$51;
																															
																															// Update the probability of sampling this value from the distribution value.
																															double cv$probabilitySample76Value52 = (cv$probabilitySample57Value8 * state.distribution$sample76[((index$sample$49 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$51]);
																															{
																																int traceTempVariable$currentState$54_1 = distributionTempVariable$var74$53;
																																if((index$sample$49 == sample$var196)) {
																																	if((timeStep$var66 == timeStep$var226)) {
																																		{
																																			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																					if((var146 == server)) {
																																						if((var156 == traceTempVariable$currentState$54_1)) {
																																							{
																																								{
																																									{
																																										// Constructing a random variable input for use later.
																																										double var243 = state.current_metric_var[server][traceTempVariable$currentState$54_1];
																																										
																																										// Record the probability of sample task 256 generating output with current configuration.
																																										if(((Math.log(cv$probabilitySample76Value52) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value52) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											// If the second value is -infinity.
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value52) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value52) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value52) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									for(int server = 0; server < state.noServers; server += 1) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
												if(state.fixedFlag$sample76) {
													{
														for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
															for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
																if((sample$var45 == sample$var196)) {
																	if((timeStep$var66 == timeStep$var226)) {
																		{
																			double traceTempVariable$var241$23_1 = cv$currentValue;
																			if((var119 == server)) {
																				if((var129 == state.st[sample$var196][timeStep$var226])) {
																					// Processing sample task 256 of consumer random variable null.
																					{
																						{
																							// Flag recording if this sample task of the consuming random variable is constrained.
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								// Mark that the sample has observed constrained data.
																								state.constrainedFlag$sample134[((var119 - 0) / 1)][((var129 - 0) / 1)] = true;
																								
																								// Set an accumulator to sum the probabilities for each possible configuration of
																								// inputs.
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								
																								// Set an accumulator to record the consumer distributions not seen. Initially set
																								// to 1 as seen values will be deducted from this value.
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																									// the output of Sample task 134.
																									if(state.fixedFlag$sample57) {
																										{
																											for(int index$sample$57_1 = 0; index$sample$57_1 < state.noSamples; index$sample$57_1 += 1) {
																												if((index$sample$57_1 == sample$var196)) {
																													if((0 == timeStep$var226)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == state.st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					{
																																						// Constructing a random variable input for use later.
																																						double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																																						
																																						// Record the probability of sample task 256 generating output with current configuration.
																																						if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																									} else {
																										for(int index$sample$58 = 0; index$sample$58 < state.noSamples; index$sample$58 += 1) {
																											if(true) {
																												// Enumerating the possible outputs of Categorical 54.
																												for(int index$sample57$59 = 0; index$sample57$59 < state.noStates; index$sample57$59 += 1) {
																													int distributionTempVariable$var55$61 = index$sample57$59;
																													
																													// Update the probability of sampling this value from the distribution value.
																													double cv$probabilitySample57Value60 = (1.0 * state.distribution$sample57[((index$sample$58 - 0) / 1)][index$sample57$59]);
																													{
																														int traceTempVariable$currentState$62_1 = distributionTempVariable$var55$61;
																														if((index$sample$58 == sample$var196)) {
																															if((0 == timeStep$var226)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$62_1)) {
																																					{
																																						{
																																							{
																																								// Constructing a random variable input for use later.
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$62_1];
																																								
																																								// Record the probability of sample task 256 generating output with current configuration.
																																								if(((Math.log(cv$probabilitySample57Value60) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value60) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value60) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value60) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value60) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																											}
																										}
																									}
																									
																									// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																									// the output of Sample task 134.
																									{
																										for(int index$sample$65_1 = 0; index$sample$65_1 < state.noSamples; index$sample$65_1 += 1) {
																											for(int index$timeStep$65_2 = 1; index$timeStep$65_2 < state.length$metric[index$sample$65_1][0]; index$timeStep$65_2 += 1) {
																												if((index$sample$65_1 == sample$var196)) {
																													if((index$timeStep$65_2 == timeStep$var226)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == state.st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					{
																																						// Constructing a random variable input for use later.
																																						double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																																						
																																						// Record the probability of sample task 256 generating output with current configuration.
																																						if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
												} else {
													for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
														for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
															if(true) {
																// Enumerating the possible outputs of Categorical 73.
																for(int index$sample76$19 = 0; index$sample76$19 < state.noStates; index$sample76$19 += 1) {
																	int distributionTempVariable$var74$21 = index$sample76$19;
																	
																	// Update the probability of sampling this value from the distribution value.
																	double cv$probabilitySample76Value20 = (1.0 * state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$19]);
																	{
																		int traceTempVariable$currentState$22_1 = distributionTempVariable$var74$21;
																		if((sample$var45 == sample$var196)) {
																			if((timeStep$var66 == timeStep$var226)) {
																				{
																					double traceTempVariable$var241$24_1 = cv$currentValue;
																					if((var119 == server)) {
																						if((var129 == traceTempVariable$currentState$22_1)) {
																							// Processing sample task 256 of consumer random variable null.
																							{
																								{
																									// Flag recording if this sample task of the consuming random variable is constrained.
																									boolean cv$sampleConstrained = true;
																									if(cv$sampleConstrained) {
																										// Mark that the sample has observed constrained data.
																										state.constrainedFlag$sample134[((var119 - 0) / 1)][((var129 - 0) / 1)] = true;
																										
																										// Set an accumulator to sum the probabilities for each possible configuration of
																										// inputs.
																										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																										
																										// Set an accumulator to record the consumer distributions not seen. Initially set
																										// to 1 as seen values will be deducted from this value.
																										double cv$consumerDistributionProbabilityAccumulator = 1.0;
																										{
																											// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																											// the output of Sample task 134.
																											if(state.fixedFlag$sample57) {
																												{
																													for(int index$sample$67_1 = 0; index$sample$67_1 < state.noSamples; index$sample$67_1 += 1) {
																														if((index$sample$67_1 == sample$var196)) {
																															if((0 == timeStep$var226)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$22_1)) {
																																					{
																																						{
																																							{
																																								// Constructing a random variable input for use later.
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$22_1];
																																								
																																								// Record the probability of sample task 256 generating output with current configuration.
																																								if(((Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																														}
																													}
																												}
																											} else {
																												for(int index$sample$68 = 0; index$sample$68 < state.noSamples; index$sample$68 += 1) {
																													if(true) {
																														// Enumerating the possible outputs of Categorical 54.
																														for(int index$sample57$69 = 0; index$sample57$69 < state.noStates; index$sample57$69 += 1) {
																															int distributionTempVariable$var55$71 = index$sample57$69;
																															
																															// Update the probability of sampling this value from the distribution value.
																															double cv$probabilitySample57Value70 = (cv$probabilitySample76Value20 * state.distribution$sample57[((index$sample$68 - 0) / 1)][index$sample57$69]);
																															{
																																int traceTempVariable$currentState$72_1 = distributionTempVariable$var55$71;
																																if((index$sample$68 == sample$var196)) {
																																	if((0 == timeStep$var226)) {
																																		{
																																			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																					if((var146 == server)) {
																																						if((var156 == traceTempVariable$currentState$72_1)) {
																																							{
																																								{
																																									{
																																										// Constructing a random variable input for use later.
																																										double var243 = state.current_metric_var[server][traceTempVariable$currentState$72_1];
																																										
																																										// Record the probability of sample task 256 generating output with current configuration.
																																										if(((Math.log(cv$probabilitySample57Value70) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value70) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											// If the second value is -infinity.
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value70) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value70) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value70) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																													}
																												}
																											}
																											
																											// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																											// the output of Sample task 134.
																											{
																												int traceTempVariable$currentState$75_1 = distributionTempVariable$var74$21;
																												if((sample$var45 == sample$var196)) {
																													if((timeStep$var66 == timeStep$var226)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == traceTempVariable$currentState$75_1)) {
																																			{
																																				{
																																					{
																																						// Constructing a random variable input for use later.
																																						double var243 = state.current_metric_var[server][traceTempVariable$currentState$75_1];
																																						
																																						// Record the probability of sample task 256 generating output with current configuration.
																																						if(((Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																												}
																											}
																											for(int index$sample$76 = 0; index$sample$76 < state.noSamples; index$sample$76 += 1) {
																												for(int index$timeStep$77 = 1; index$timeStep$77 < state.length$metric[index$sample$76][0]; index$timeStep$77 += 1) {
																													if(!((index$timeStep$77 == timeStep$var66) && (index$sample$76 == sample$var45))) {
																														// Enumerating the possible outputs of Categorical 73.
																														for(int index$sample76$78 = 0; index$sample76$78 < state.noStates; index$sample76$78 += 1) {
																															int distributionTempVariable$var74$80 = index$sample76$78;
																															
																															// Update the probability of sampling this value from the distribution value.
																															double cv$probabilitySample76Value79 = (cv$probabilitySample76Value20 * state.distribution$sample76[((index$sample$76 - 0) / 1)][((index$timeStep$77 - 1) / 1)][index$sample76$78]);
																															{
																																int traceTempVariable$currentState$81_1 = distributionTempVariable$var74$80;
																																if((index$sample$76 == sample$var196)) {
																																	if((index$timeStep$77 == timeStep$var226)) {
																																		{
																																			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																					if((var146 == server)) {
																																						if((var156 == traceTempVariable$currentState$81_1)) {
																																							{
																																								{
																																									{
																																										// Constructing a random variable input for use later.
																																										double var243 = state.current_metric_var[server][traceTempVariable$currentState$81_1];
																																										
																																										// Record the probability of sample task 256 generating output with current configuration.
																																										if(((Math.log(cv$probabilitySample76Value79) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value79) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											// If the second value is -infinity.
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value79) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value79) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value79) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
					
					// The probability ration for the proposed value and the current value.
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					
					// Test if the probability of the sample is sufficient to keep the value. This needs
					// to be less than or equal as otherwise if the proposed value is not possible and
					// the random value is 0 an impossible value will be accepted.
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio))) {
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
									{
										double[] var120 = state.current_metric_mean[var119];
										var120[var129] = var130;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 162 drawn from InverseGamma 135. Inference was performed using Metropolis-Hastings.
	private final void inferSample162(int var146, int var156, int threadID$cv$var156, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample162[((var146 - 0) / 1)][((var156 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// Metropolis-Hastings
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = state.current_metric_var[var146][var156];
			
			// The probability of the random variable generating the originally sampled value
			double cv$originalProbability = 0.0;
			
			// Calculate a proposed variance.
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			
			// Ensure the variance is at least 0.01
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			
			// The proposed new value for the sample
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			
			// The probability of the random variable generating the new sample value.
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample162[((var146 - 0) / 1)][((var156 - 0) / 1)] || (cv$valuePos == 0))) {
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
						// 
						// Write out the value of the sample to a temporary variable prior to updating the
						// intermediate variables.
						double var157 = cv$proposedValue;
						
						// Guards to ensure that current_metric_var is only updated when there is a valid
						// path.
						{
							{
								{
									double[] var147 = state.current_metric_var[var146];
									var147[var156] = cv$currentValue;
								}
							}
						}
					}
					{
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, 1.0, 1.0));
						
						// Processing random variable 244.
						{
							// Looking for a path between Sample 162 and consumer Gaussian 244.
							{
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									for(int server = 0; server < state.noServers; server += 1) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
												if(state.fixedFlag$sample57) {
													{
														for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
															if((sample$var45 == sample$var196)) {
																if((0 == timeStep$var226)) {
																	{
																		double traceTempVariable$var243$11_1 = cv$currentValue;
																		if((var146 == server)) {
																			if((var156 == state.st[sample$var196][timeStep$var226])) {
																				// Processing sample task 256 of consumer random variable null.
																				{
																					{
																						// Flag recording if this sample task of the consuming random variable is constrained.
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							// Mark that the sample has observed constrained data.
																							state.constrainedFlag$sample162[((var146 - 0) / 1)][((var156 - 0) / 1)] = true;
																							
																							// Set an accumulator to sum the probabilities for each possible configuration of
																							// inputs.
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							
																							// Set an accumulator to record the consumer distributions not seen. Initially set
																							// to 1 as seen values will be deducted from this value.
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																								// the output of Sample task 162.
																								{
																									for(int index$sample$29_1 = 0; index$sample$29_1 < state.noSamples; index$sample$29_1 += 1) {
																										if((index$sample$29_1 == sample$var196)) {
																											if((0 == timeStep$var226)) {
																												{
																													for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																														for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																															if((var119 == server)) {
																																if((var129 == state.st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			{
																																				// Constructing a random variable input for use later.
																																				double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																																				
																																				// Record the probability of sample task 256 generating output with current configuration.
																																				if(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)));
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
																								
																								// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																								// the output of Sample task 162.
																								if(state.fixedFlag$sample76) {
																									{
																										for(int index$sample$31_1 = 0; index$sample$31_1 < state.noSamples; index$sample$31_1 += 1) {
																											for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$31_1][0]; timeStep$var66 += 1) {
																												if((index$sample$31_1 == sample$var196)) {
																													if((timeStep$var66 == timeStep$var226)) {
																														{
																															for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																	if((var119 == server)) {
																																		if((var129 == state.st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					{
																																						// Constructing a random variable input for use later.
																																						double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																																						
																																						// Record the probability of sample task 256 generating output with current configuration.
																																						if(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)));
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
																									for(int index$sample$32 = 0; index$sample$32 < state.noSamples; index$sample$32 += 1) {
																										for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$32][0]; timeStep$var66 += 1) {
																											if(true) {
																												// Enumerating the possible outputs of Categorical 73.
																												for(int index$sample76$34 = 0; index$sample76$34 < state.noStates; index$sample76$34 += 1) {
																													int distributionTempVariable$var74$36 = index$sample76$34;
																													
																													// Update the probability of sampling this value from the distribution value.
																													double cv$probabilitySample76Value35 = (1.0 * state.distribution$sample76[((index$sample$32 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$34]);
																													{
																														int traceTempVariable$currentState$37_1 = distributionTempVariable$var74$36;
																														if((index$sample$32 == sample$var196)) {
																															if((timeStep$var66 == timeStep$var226)) {
																																{
																																	for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																		for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																			if((var119 == server)) {
																																				if((var129 == traceTempVariable$currentState$37_1)) {
																																					{
																																						{
																																							{
																																								// Constructing a random variable input for use later.
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$37_1];
																																								
																																								// Record the probability of sample task 256 generating output with current configuration.
																																								if(((Math.log(cv$probabilitySample76Value35) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value35) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value35) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value35) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value35) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)));
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
												} else {
													for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
														if(true) {
															// Enumerating the possible outputs of Categorical 54.
															for(int index$sample57$7 = 0; index$sample57$7 < state.noStates; index$sample57$7 += 1) {
																int distributionTempVariable$var55$9 = index$sample57$7;
																
																// Update the probability of sampling this value from the distribution value.
																double cv$probabilitySample57Value8 = (1.0 * state.distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$7]);
																{
																	int traceTempVariable$currentState$10_1 = distributionTempVariable$var55$9;
																	if((sample$var45 == sample$var196)) {
																		if((0 == timeStep$var226)) {
																			{
																				double traceTempVariable$var243$12_1 = cv$currentValue;
																				if((var146 == server)) {
																					if((var156 == traceTempVariable$currentState$10_1)) {
																						// Processing sample task 256 of consumer random variable null.
																						{
																							{
																								// Flag recording if this sample task of the consuming random variable is constrained.
																								boolean cv$sampleConstrained = true;
																								if(cv$sampleConstrained) {
																									// Mark that the sample has observed constrained data.
																									state.constrainedFlag$sample162[((var146 - 0) / 1)][((var156 - 0) / 1)] = true;
																									
																									// Set an accumulator to sum the probabilities for each possible configuration of
																									// inputs.
																									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																									
																									// Set an accumulator to record the consumer distributions not seen. Initially set
																									// to 1 as seen values will be deducted from this value.
																									double cv$consumerDistributionProbabilityAccumulator = 1.0;
																									{
																										// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																										// the output of Sample task 162.
																										{
																											int traceTempVariable$currentState$40_1 = distributionTempVariable$var55$9;
																											if((sample$var45 == sample$var196)) {
																												if((0 == timeStep$var226)) {
																													{
																														for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																															for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																if((var119 == server)) {
																																	if((var129 == traceTempVariable$currentState$40_1)) {
																																		{
																																			{
																																				{
																																					// Constructing a random variable input for use later.
																																					double var241 = state.current_metric_mean[server][traceTempVariable$currentState$40_1];
																																					
																																					// Record the probability of sample task 256 generating output with current configuration.
																																					if(((Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)));
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
																										}
																										for(int index$sample$41 = 0; index$sample$41 < state.noSamples; index$sample$41 += 1) {
																											if(!(index$sample$41 == sample$var45)) {
																												// Enumerating the possible outputs of Categorical 54.
																												for(int index$sample57$42 = 0; index$sample57$42 < state.noStates; index$sample57$42 += 1) {
																													int distributionTempVariable$var55$44 = index$sample57$42;
																													
																													// Update the probability of sampling this value from the distribution value.
																													double cv$probabilitySample57Value43 = (cv$probabilitySample57Value8 * state.distribution$sample57[((index$sample$41 - 0) / 1)][index$sample57$42]);
																													{
																														int traceTempVariable$currentState$45_1 = distributionTempVariable$var55$44;
																														if((index$sample$41 == sample$var196)) {
																															if((0 == timeStep$var226)) {
																																{
																																	for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																		for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																			if((var119 == server)) {
																																				if((var129 == traceTempVariable$currentState$45_1)) {
																																					{
																																						{
																																							{
																																								// Constructing a random variable input for use later.
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$45_1];
																																								
																																								// Record the probability of sample task 256 generating output with current configuration.
																																								if(((Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)));
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
																												}
																											}
																										}
																										
																										// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																										// the output of Sample task 162.
																										if(state.fixedFlag$sample76) {
																											{
																												for(int index$sample$48_1 = 0; index$sample$48_1 < state.noSamples; index$sample$48_1 += 1) {
																													for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$48_1][0]; timeStep$var66 += 1) {
																														if((index$sample$48_1 == sample$var196)) {
																															if((timeStep$var66 == timeStep$var226)) {
																																{
																																	for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																		for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																			if((var119 == server)) {
																																				if((var129 == traceTempVariable$currentState$10_1)) {
																																					{
																																						{
																																							{
																																								// Constructing a random variable input for use later.
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$10_1];
																																								
																																								// Record the probability of sample task 256 generating output with current configuration.
																																								if(((Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)));
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
																													}
																												}
																											}
																										} else {
																											for(int index$sample$49 = 0; index$sample$49 < state.noSamples; index$sample$49 += 1) {
																												for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$49][0]; timeStep$var66 += 1) {
																													if(true) {
																														// Enumerating the possible outputs of Categorical 73.
																														for(int index$sample76$51 = 0; index$sample76$51 < state.noStates; index$sample76$51 += 1) {
																															int distributionTempVariable$var74$53 = index$sample76$51;
																															
																															// Update the probability of sampling this value from the distribution value.
																															double cv$probabilitySample76Value52 = (cv$probabilitySample57Value8 * state.distribution$sample76[((index$sample$49 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$51]);
																															{
																																int traceTempVariable$currentState$54_1 = distributionTempVariable$var74$53;
																																if((index$sample$49 == sample$var196)) {
																																	if((timeStep$var66 == timeStep$var226)) {
																																		{
																																			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																				for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																					if((var119 == server)) {
																																						if((var129 == traceTempVariable$currentState$54_1)) {
																																							{
																																								{
																																									{
																																										// Constructing a random variable input for use later.
																																										double var241 = state.current_metric_mean[server][traceTempVariable$currentState$54_1];
																																										
																																										// Record the probability of sample task 256 generating output with current configuration.
																																										if(((Math.log(cv$probabilitySample76Value52) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value52) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											// If the second value is -infinity.
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value52) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value52) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value52) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)));
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
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									for(int server = 0; server < state.noServers; server += 1) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
												if(state.fixedFlag$sample76) {
													{
														for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
															for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
																if((sample$var45 == sample$var196)) {
																	if((timeStep$var66 == timeStep$var226)) {
																		{
																			double traceTempVariable$var243$23_1 = cv$currentValue;
																			if((var146 == server)) {
																				if((var156 == state.st[sample$var196][timeStep$var226])) {
																					// Processing sample task 256 of consumer random variable null.
																					{
																						{
																							// Flag recording if this sample task of the consuming random variable is constrained.
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								// Mark that the sample has observed constrained data.
																								state.constrainedFlag$sample162[((var146 - 0) / 1)][((var156 - 0) / 1)] = true;
																								
																								// Set an accumulator to sum the probabilities for each possible configuration of
																								// inputs.
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								
																								// Set an accumulator to record the consumer distributions not seen. Initially set
																								// to 1 as seen values will be deducted from this value.
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																									// the output of Sample task 162.
																									if(state.fixedFlag$sample57) {
																										{
																											for(int index$sample$57_1 = 0; index$sample$57_1 < state.noSamples; index$sample$57_1 += 1) {
																												if((index$sample$57_1 == sample$var196)) {
																													if((0 == timeStep$var226)) {
																														{
																															for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																	if((var119 == server)) {
																																		if((var129 == state.st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					{
																																						// Constructing a random variable input for use later.
																																						double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																																						
																																						// Record the probability of sample task 256 generating output with current configuration.
																																						if(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)));
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
																									} else {
																										for(int index$sample$58 = 0; index$sample$58 < state.noSamples; index$sample$58 += 1) {
																											if(true) {
																												// Enumerating the possible outputs of Categorical 54.
																												for(int index$sample57$59 = 0; index$sample57$59 < state.noStates; index$sample57$59 += 1) {
																													int distributionTempVariable$var55$61 = index$sample57$59;
																													
																													// Update the probability of sampling this value from the distribution value.
																													double cv$probabilitySample57Value60 = (1.0 * state.distribution$sample57[((index$sample$58 - 0) / 1)][index$sample57$59]);
																													{
																														int traceTempVariable$currentState$62_1 = distributionTempVariable$var55$61;
																														if((index$sample$58 == sample$var196)) {
																															if((0 == timeStep$var226)) {
																																{
																																	for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																		for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																			if((var119 == server)) {
																																				if((var129 == traceTempVariable$currentState$62_1)) {
																																					{
																																						{
																																							{
																																								// Constructing a random variable input for use later.
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$62_1];
																																								
																																								// Record the probability of sample task 256 generating output with current configuration.
																																								if(((Math.log(cv$probabilitySample57Value60) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value60) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value60) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value60) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value60) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)));
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
																											}
																										}
																									}
																									
																									// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																									// the output of Sample task 162.
																									{
																										for(int index$sample$65_1 = 0; index$sample$65_1 < state.noSamples; index$sample$65_1 += 1) {
																											for(int index$timeStep$65_2 = 1; index$timeStep$65_2 < state.length$metric[index$sample$65_1][0]; index$timeStep$65_2 += 1) {
																												if((index$sample$65_1 == sample$var196)) {
																													if((index$timeStep$65_2 == timeStep$var226)) {
																														{
																															for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																	if((var119 == server)) {
																																		if((var129 == state.st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					{
																																						// Constructing a random variable input for use later.
																																						double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																																						
																																						// Record the probability of sample task 256 generating output with current configuration.
																																						if(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)));
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
												} else {
													for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
														for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
															if(true) {
																// Enumerating the possible outputs of Categorical 73.
																for(int index$sample76$19 = 0; index$sample76$19 < state.noStates; index$sample76$19 += 1) {
																	int distributionTempVariable$var74$21 = index$sample76$19;
																	
																	// Update the probability of sampling this value from the distribution value.
																	double cv$probabilitySample76Value20 = (1.0 * state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$19]);
																	{
																		int traceTempVariable$currentState$22_1 = distributionTempVariable$var74$21;
																		if((sample$var45 == sample$var196)) {
																			if((timeStep$var66 == timeStep$var226)) {
																				{
																					double traceTempVariable$var243$24_1 = cv$currentValue;
																					if((var146 == server)) {
																						if((var156 == traceTempVariable$currentState$22_1)) {
																							// Processing sample task 256 of consumer random variable null.
																							{
																								{
																									// Flag recording if this sample task of the consuming random variable is constrained.
																									boolean cv$sampleConstrained = true;
																									if(cv$sampleConstrained) {
																										// Mark that the sample has observed constrained data.
																										state.constrainedFlag$sample162[((var146 - 0) / 1)][((var156 - 0) / 1)] = true;
																										
																										// Set an accumulator to sum the probabilities for each possible configuration of
																										// inputs.
																										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																										
																										// Set an accumulator to record the consumer distributions not seen. Initially set
																										// to 1 as seen values will be deducted from this value.
																										double cv$consumerDistributionProbabilityAccumulator = 1.0;
																										{
																											// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																											// the output of Sample task 162.
																											if(state.fixedFlag$sample57) {
																												{
																													for(int index$sample$67_1 = 0; index$sample$67_1 < state.noSamples; index$sample$67_1 += 1) {
																														if((index$sample$67_1 == sample$var196)) {
																															if((0 == timeStep$var226)) {
																																{
																																	for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																		for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																			if((var119 == server)) {
																																				if((var129 == traceTempVariable$currentState$22_1)) {
																																					{
																																						{
																																							{
																																								// Constructing a random variable input for use later.
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$22_1];
																																								
																																								// Record the probability of sample task 256 generating output with current configuration.
																																								if(((Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)));
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
																														}
																													}
																												}
																											} else {
																												for(int index$sample$68 = 0; index$sample$68 < state.noSamples; index$sample$68 += 1) {
																													if(true) {
																														// Enumerating the possible outputs of Categorical 54.
																														for(int index$sample57$69 = 0; index$sample57$69 < state.noStates; index$sample57$69 += 1) {
																															int distributionTempVariable$var55$71 = index$sample57$69;
																															
																															// Update the probability of sampling this value from the distribution value.
																															double cv$probabilitySample57Value70 = (cv$probabilitySample76Value20 * state.distribution$sample57[((index$sample$68 - 0) / 1)][index$sample57$69]);
																															{
																																int traceTempVariable$currentState$72_1 = distributionTempVariable$var55$71;
																																if((index$sample$68 == sample$var196)) {
																																	if((0 == timeStep$var226)) {
																																		{
																																			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																				for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																					if((var119 == server)) {
																																						if((var129 == traceTempVariable$currentState$72_1)) {
																																							{
																																								{
																																									{
																																										// Constructing a random variable input for use later.
																																										double var241 = state.current_metric_mean[server][traceTempVariable$currentState$72_1];
																																										
																																										// Record the probability of sample task 256 generating output with current configuration.
																																										if(((Math.log(cv$probabilitySample57Value70) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value70) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											// If the second value is -infinity.
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value70) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value70) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value70) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)));
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
																													}
																												}
																											}
																											
																											// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																											// the output of Sample task 162.
																											{
																												int traceTempVariable$currentState$75_1 = distributionTempVariable$var74$21;
																												if((sample$var45 == sample$var196)) {
																													if((timeStep$var66 == timeStep$var226)) {
																														{
																															for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																	if((var119 == server)) {
																																		if((var129 == traceTempVariable$currentState$75_1)) {
																																			{
																																				{
																																					{
																																						// Constructing a random variable input for use later.
																																						double var241 = state.current_metric_mean[server][traceTempVariable$currentState$75_1];
																																						
																																						// Record the probability of sample task 256 generating output with current configuration.
																																						if(((Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)));
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
																												}
																											}
																											for(int index$sample$76 = 0; index$sample$76 < state.noSamples; index$sample$76 += 1) {
																												for(int index$timeStep$77 = 1; index$timeStep$77 < state.length$metric[index$sample$76][0]; index$timeStep$77 += 1) {
																													if(!((index$timeStep$77 == timeStep$var66) && (index$sample$76 == sample$var45))) {
																														// Enumerating the possible outputs of Categorical 73.
																														for(int index$sample76$78 = 0; index$sample76$78 < state.noStates; index$sample76$78 += 1) {
																															int distributionTempVariable$var74$80 = index$sample76$78;
																															
																															// Update the probability of sampling this value from the distribution value.
																															double cv$probabilitySample76Value79 = (cv$probabilitySample76Value20 * state.distribution$sample76[((index$sample$76 - 0) / 1)][((index$timeStep$77 - 1) / 1)][index$sample76$78]);
																															{
																																int traceTempVariable$currentState$81_1 = distributionTempVariable$var74$80;
																																if((index$sample$76 == sample$var196)) {
																																	if((index$timeStep$77 == timeStep$var226)) {
																																		{
																																			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																				for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																					if((var119 == server)) {
																																						if((var129 == traceTempVariable$currentState$81_1)) {
																																							{
																																								{
																																									{
																																										// Constructing a random variable input for use later.
																																										double var241 = state.current_metric_mean[server][traceTempVariable$currentState$81_1];
																																										
																																										// Record the probability of sample task 256 generating output with current configuration.
																																										if(((Math.log(cv$probabilitySample76Value79) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value79) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											// If the second value is -infinity.
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value79) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value79) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value79) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)));
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
					
					// The probability ration for the proposed value and the current value.
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					
					// Test if the probability of the sample is sufficient to keep the value. This needs
					// to be less than or equal as otherwise if the proposed value is not possible and
					// the random value is 0 an impossible value will be accepted.
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio))) {
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
									{
										double[] var147 = state.current_metric_var[var146];
										var147[var156] = var157;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 190 drawn from Beta 162. Inference was performed using a Beta to
	// Bernoulli/Binomial conjugate prior.
	private final void inferSample190(int var173, int var183, int threadID$cv$var183, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample190[((var173 - 0) / 1)][((var183 - 0) / 1)] = false;
			
			// Local variable to record the number of true samples.
			double cv$sum = 0.0;
			
			// Local variable to record the number of samples.
			double cv$count = 0.0;
			{
				// Processing random variable 231.
				{
					// Looking for a path between Sample 190 and consumer Bernoulli 231.
					{
						for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
							for(int server = 0; server < state.noServers; server += 1) {
								for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
									if(state.fixedFlag$sample57) {
										{
											for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
												if((sample$var45 == sample$var196)) {
													if((0 == timeStep$var226)) {
														{
															if((var173 == server)) {
																if((var183 == state.st[sample$var196][timeStep$var226])) {
																	// Processing sample task 241 of consumer random variable null.
																	{
																		{
																			// Flag recording if this sample task of the consuming random variable is constrained.
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				// Mark that the sample has observed constrained data.
																				state.constrainedFlag$sample190[((var173 - 0) / 1)][((var183 - 0) / 1)] = true;
																				{
																					{
																						{
																							{
																								{
																									// Include the value sampled by task 241 from random variable var231.
																									// Increment the number of samples.
																									cv$count = (cv$count + 1.0);
																									
																									// If the sample value was positive increase the count
																									if(state.metric_valid_g[sample$var196][server][timeStep$var226])
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
													}
												}
											}
										}
									} else {
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 54.
												for(int index$sample57$6 = 0; index$sample57$6 < state.noStates; index$sample57$6 += 1) {
													int distributionTempVariable$var55$8 = index$sample57$6;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample57Value7 = (1.0 * state.distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$6]);
													{
														int traceTempVariable$currentState$9_1 = distributionTempVariable$var55$8;
														if((sample$var45 == sample$var196)) {
															if((0 == timeStep$var226)) {
																{
																	if((var173 == server)) {
																		if((var183 == traceTempVariable$currentState$9_1)) {
																			// Processing sample task 241 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						state.constrainedFlag$sample190[((var173 - 0) / 1)][((var183 - 0) / 1)] = true;
																						{
																							{
																								{
																									{
																										{
																											// Include the value sampled by task 241 from random variable var231.
																											// Increment the number of samples.
																											cv$count = (cv$count + cv$probabilitySample57Value7);
																											
																											// If the sample value was positive increase the count
																											if(state.metric_valid_g[sample$var196][server][timeStep$var226])
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
										}
									}
								}
							}
						}
						for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
							for(int server = 0; server < state.noServers; server += 1) {
								for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
									if(state.fixedFlag$sample76) {
										{
											for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
												for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
													if((sample$var45 == sample$var196)) {
														if((timeStep$var66 == timeStep$var226)) {
															{
																if((var173 == server)) {
																	if((var183 == state.st[sample$var196][timeStep$var226])) {
																		// Processing sample task 241 of consumer random variable null.
																		{
																			{
																				// Flag recording if this sample task of the consuming random variable is constrained.
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					// Mark that the sample has observed constrained data.
																					state.constrainedFlag$sample190[((var173 - 0) / 1)][((var183 - 0) / 1)] = true;
																					{
																						{
																							{
																								{
																									{
																										// Include the value sampled by task 241 from random variable var231.
																										// Increment the number of samples.
																										cv$count = (cv$count + 1.0);
																										
																										// If the sample value was positive increase the count
																										if(state.metric_valid_g[sample$var196][server][timeStep$var226])
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
														}
													}
												}
											}
										}
									} else {
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
												if(true) {
													// Enumerating the possible outputs of Categorical 73.
													for(int index$sample76$18 = 0; index$sample76$18 < state.noStates; index$sample76$18 += 1) {
														int distributionTempVariable$var74$20 = index$sample76$18;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample76Value19 = (1.0 * state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$18]);
														{
															int traceTempVariable$currentState$21_1 = distributionTempVariable$var74$20;
															if((sample$var45 == sample$var196)) {
																if((timeStep$var66 == timeStep$var226)) {
																	{
																		if((var173 == server)) {
																			if((var183 == traceTempVariable$currentState$21_1)) {
																				// Processing sample task 241 of consumer random variable null.
																				{
																					{
																						// Flag recording if this sample task of the consuming random variable is constrained.
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							// Mark that the sample has observed constrained data.
																							state.constrainedFlag$sample190[((var173 - 0) / 1)][((var183 - 0) / 1)] = true;
																							{
																								{
																									{
																										{
																											{
																												// Include the value sampled by task 241 from random variable var231.
																												// Increment the number of samples.
																												cv$count = (cv$count + cv$probabilitySample76Value19);
																												
																												// If the sample value was positive increase the count
																												if(state.metric_valid_g[sample$var196][server][timeStep$var226])
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
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample190[((var173 - 0) / 1)][((var183 - 0) / 1)]) {
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				double var184 = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
				
				// Guards to ensure that current_metric_valid_bias is only updated when there is a
				// valid path.
				{
					{
						{
							double[] var174 = state.current_metric_valid_bias[var173];
							var174[var183] = var184;
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 20 drawn from Dirichlet 19. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample20() {
		if(true) {
			state.constrainedFlag$sample20 = false;
			
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = state.initialStateDistribution;
			
			// A local reference to the scratch space.
			double[] cv$countLocal = scratch.cv$var20$countGlobal;
			
			// Get the length of the array
			int cv$arrayLength = state.noStates;
			
			// Initialize the array values to 0.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				// Processing random variable 54.
				{
					{
						{
							for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
								if(state.fixedFlag$sample57) {
									// Processing sample task 57 of consumer random variable null.
									{
										{
											// Copy of index so that its values can be safely substituted
											int index$sample$3 = sample$var45;
											
											// Flag recording if this sample task of the consuming random variable is constrained.
											boolean cv$sampleConstrained = (state.fixedFlag$sample57 || state.constrainedFlag$sample57[((sample$var45 - 0) / 1)]);
											if(cv$sampleConstrained) {
												// Mark that the sample has observed constrained data.
												state.constrainedFlag$sample20 = true;
												{
													{
														{
															{
																{
																	// Increment the sample counter with the value sampled by sample task 57 of random
																	// variable var54
																	cv$countLocal[state.st[sample$var45][0]] = (cv$countLocal[state.st[sample$var45][0]] + 1.0);
																}
															}
														}
													}
												}
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
					{
						for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
							if(!state.fixedFlag$sample57) {
								// Processing sample task 57 of consumer random variable null.
								{
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
													cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample57[((sample$var45 - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample20)
				// Calculate the new sample value
				// 
				// Calculate a new sample value and write it into cv$targetLocal.
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, cv$countLocal, cv$targetLocal, state.noStates);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 33 drawn from Dirichlet 21. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample33(int var32, int threadID$cv$var32, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample33[((var32 - 0) / 1)] = false;
			
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = state.m[var32];
			
			// A local reference to the scratch space.
			double[] cv$countLocal = scratch.cv$var33$countGlobal[threadID$cv$var32];
			
			// Get the length of the array
			int cv$arrayLength = state.noStates;
			
			// Initialize the array values to 0.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				// Processing random variable 73.
				{
					// Looking for a path between Sample 33 and consumer Categorical 73.
					{
						for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
							for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
								if(state.fixedFlag$sample57) {
									{
										for(int index$sample$3_1 = 0; index$sample$3_1 < state.noSamples; index$sample$3_1 += 1) {
											if((index$sample$3_1 == sample$var45)) {
												if((0 == (timeStep$var66 - 1))) {
													{
														if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
															if(state.fixedFlag$sample76) {
																// Processing sample task 76 of consumer random variable null.
																{
																	{
																		// Copy of index so that its values can be safely substituted
																		int index$timeStep$23 = timeStep$var66;
																		
																		// Copy of index so that its values can be safely substituted
																		int index$sample$24 = sample$var45;
																		
																		// Flag recording if this sample task of the consuming random variable is constrained.
																		boolean cv$sampleConstrained = (state.fixedFlag$sample76 || state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)]);
																		if(cv$sampleConstrained) {
																			// Mark that the sample has observed constrained data.
																			state.constrainedFlag$sample33[((var32 - 0) / 1)] = true;
																			{
																				{
																					{
																						{
																							{
																								// Increment the sample counter with the value sampled by sample task 76 of random
																								// variable var73
																								cv$countLocal[state.st[sample$var45][timeStep$var66]] = (cv$countLocal[state.st[sample$var45][timeStep$var66]] + 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
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
									for(int index$sample$4 = 0; index$sample$4 < state.noSamples; index$sample$4 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 54.
											for(int index$sample57$5 = 0; index$sample57$5 < state.noStates; index$sample57$5 += 1) {
												int distributionTempVariable$var55$7 = index$sample57$5;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample57Value6 = (1.0 * state.distribution$sample57[((index$sample$4 - 0) / 1)][index$sample57$5]);
												{
													int traceTempVariable$var71$8_1 = distributionTempVariable$var55$7;
													if((index$sample$4 == sample$var45)) {
														if((0 == (timeStep$var66 - 1))) {
															{
																if((var32 == traceTempVariable$var71$8_1)) {
																	if(state.fixedFlag$sample76) {
																		// Processing sample task 76 of consumer random variable null.
																		{
																			{
																				// Copy of index so that its values can be safely substituted
																				int index$timeStep$26 = timeStep$var66;
																				
																				// Copy of index so that its values can be safely substituted
																				int index$sample$27 = sample$var45;
																				
																				// Flag recording if this sample task of the consuming random variable is constrained.
																				boolean cv$sampleConstrained = (state.fixedFlag$sample76 || state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)]);
																				if(cv$sampleConstrained) {
																					// Mark that the sample has observed constrained data.
																					state.constrainedFlag$sample33[((var32 - 0) / 1)] = true;
																					{
																						{
																							{
																								{
																									{
																										// Increment the sample counter with the value sampled by sample task 76 of random
																										// variable var73
																										cv$countLocal[state.st[sample$var45][timeStep$var66]] = (cv$countLocal[state.st[sample$var45][timeStep$var66]] + cv$probabilitySample57Value6);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
							for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
								if(state.fixedFlag$sample76) {
									{
										for(int index$sample$13_1 = 0; index$sample$13_1 < state.noSamples; index$sample$13_1 += 1) {
											for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < state.length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
												if((index$sample$13_1 == sample$var45)) {
													if((index$timeStep$13_2 == (timeStep$var66 - 1))) {
														{
															if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
																if(state.fixedFlag$sample76) {
																	// Processing sample task 76 of consumer random variable null.
																	{
																		{
																			// Copy of index so that its values can be safely substituted
																			int index$timeStep$29 = timeStep$var66;
																			
																			// Copy of index so that its values can be safely substituted
																			int index$sample$30 = sample$var45;
																			
																			// Flag recording if this sample task of the consuming random variable is constrained.
																			boolean cv$sampleConstrained = (state.fixedFlag$sample76 || state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)]);
																			if(cv$sampleConstrained) {
																				// Mark that the sample has observed constrained data.
																				state.constrainedFlag$sample33[((var32 - 0) / 1)] = true;
																				{
																					{
																						{
																							{
																								{
																									// Increment the sample counter with the value sampled by sample task 76 of random
																									// variable var73
																									cv$countLocal[state.st[sample$var45][timeStep$var66]] = (cv$countLocal[state.st[sample$var45][timeStep$var66]] + 1.0);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
									for(int index$sample$14 = 0; index$sample$14 < state.noSamples; index$sample$14 += 1) {
										for(int index$timeStep$15 = 1; index$timeStep$15 < state.length$metric[index$sample$14][0]; index$timeStep$15 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 73.
												for(int index$sample76$16 = 0; index$sample76$16 < state.noStates; index$sample76$16 += 1) {
													int distributionTempVariable$var74$18 = index$sample76$16;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample76Value17 = (1.0 * state.distribution$sample76[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample76$16]);
													{
														int traceTempVariable$var71$19_1 = distributionTempVariable$var74$18;
														if((index$sample$14 == sample$var45)) {
															if((index$timeStep$15 == (timeStep$var66 - 1))) {
																{
																	if((var32 == traceTempVariable$var71$19_1)) {
																		if(state.fixedFlag$sample76) {
																			// Processing sample task 76 of consumer random variable null.
																			{
																				{
																					// Copy of index so that its values can be safely substituted
																					int index$timeStep$32 = timeStep$var66;
																					
																					// Copy of index so that its values can be safely substituted
																					int index$sample$33 = sample$var45;
																					
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = (state.fixedFlag$sample76 || state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)]);
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						state.constrainedFlag$sample33[((var32 - 0) / 1)] = true;
																						{
																							{
																								{
																									{
																										{
																											// Increment the sample counter with the value sampled by sample task 76 of random
																											// variable var73
																											cv$countLocal[state.st[sample$var45][timeStep$var66]] = (cv$countLocal[state.st[sample$var45][timeStep$var66]] + cv$probabilitySample76Value17);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
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
					for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
						for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(state.fixedFlag$sample57) {
								{
									for(int index$sample$40_1 = 0; index$sample$40_1 < state.noSamples; index$sample$40_1 += 1) {
										if((index$sample$40_1 == sample$var45)) {
											if((0 == (timeStep$var66 - 1))) {
												{
													if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
														if(!state.fixedFlag$sample76) {
															// Processing sample task 76 of consumer random variable null.
															{
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
																				cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																		}
																	}
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
								for(int index$sample$41 = 0; index$sample$41 < state.noSamples; index$sample$41 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 54.
										for(int index$sample57$42 = 0; index$sample57$42 < state.noStates; index$sample57$42 += 1) {
											int distributionTempVariable$var55$44 = index$sample57$42;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample57Value43 = (1.0 * state.distribution$sample57[((index$sample$41 - 0) / 1)][index$sample57$42]);
											{
												int traceTempVariable$var71$45_1 = distributionTempVariable$var55$44;
												if((index$sample$41 == sample$var45)) {
													if((0 == (timeStep$var66 - 1))) {
														{
															if((var32 == traceTempVariable$var71$45_1)) {
																if(!state.fixedFlag$sample76) {
																	// Processing sample task 76 of consumer random variable null.
																	{
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
																						cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
						for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(state.fixedFlag$sample76) {
								{
									for(int index$sample$50_1 = 0; index$sample$50_1 < state.noSamples; index$sample$50_1 += 1) {
										for(int index$timeStep$50_2 = 1; index$timeStep$50_2 < state.length$metric[index$sample$50_1][0]; index$timeStep$50_2 += 1) {
											if((index$sample$50_1 == sample$var45)) {
												if((index$timeStep$50_2 == (timeStep$var66 - 1))) {
													{
														if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
															if(!state.fixedFlag$sample76) {
																// Processing sample task 76 of consumer random variable null.
																{
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
																					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																			}
																		}
																	}
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
								for(int index$sample$51 = 0; index$sample$51 < state.noSamples; index$sample$51 += 1) {
									for(int index$timeStep$52 = 1; index$timeStep$52 < state.length$metric[index$sample$51][0]; index$timeStep$52 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 73.
											for(int index$sample76$53 = 0; index$sample76$53 < state.noStates; index$sample76$53 += 1) {
												int distributionTempVariable$var74$55 = index$sample76$53;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample76Value54 = (1.0 * state.distribution$sample76[((index$sample$51 - 0) / 1)][((index$timeStep$52 - 1) / 1)][index$sample76$53]);
												{
													int traceTempVariable$var71$56_1 = distributionTempVariable$var74$55;
													if((index$sample$51 == sample$var45)) {
														if((index$timeStep$52 == (timeStep$var66 - 1))) {
															{
																if((var32 == traceTempVariable$var71$56_1)) {
																	if(!state.fixedFlag$sample76) {
																		// Processing sample task 76 of consumer random variable null.
																		{
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
																							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample33[((var32 - 0) / 1)])
				// Calculate the new sample value
				// 
				// Calculate a new sample value and write it into cv$targetLocal.
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, cv$countLocal, cv$targetLocal, state.noStates);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 57 drawn from Categorical 54. Inference was performed using variable
	// marginalization.
	private final void inferSample57(int sample$var45, int threadID$cv$sample$var45, Rng RNG$) {
		// Copy of index so that its values can be safely substituted
		int index$sample$1 = sample$var45;
		if(true) {
			state.constrainedFlag$sample57[((sample$var45 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, state.noStates);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var55$stateProbabilityGlobal[threadID$cv$sample$var45];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
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
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$currentValue])) && (state.initialStateDistribution[cv$currentValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 73.
					{
						// Looking for a path between Sample 57 and consumer Categorical 73.
						{
							{
								int traceTempVariable$var71$2_1 = cv$currentValue;
								for(int index$sample$2_2 = 0; index$sample$2_2 < state.noSamples; index$sample$2_2 += 1) {
									if((sample$var45 == index$sample$2_2)) {
										for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$2_2][0]; timeStep$var66 += 1) {
											if((0 == (timeStep$var66 - 1))) {
												if(state.fixedFlag$sample76) {
													// Processing sample task 76 of consumer random variable null.
													{
														{
															// Copy of index so that its values can be safely substituted
															int index$timeStep$4 = timeStep$var66;
															
															// Copy of index so that its values can be safely substituted
															int index$sample$5 = index$sample$2_2;
															
															// Flag recording if this sample task of the consuming random variable is constrained.
															boolean cv$sampleConstrained = (state.fixedFlag$sample76 || state.constrainedFlag$sample76[((index$sample$2_2 - 0) / 1)][((timeStep$var66 - 1) / 1)]);
															if(cv$sampleConstrained) {
																// Mark that the sample has observed constrained data.
																state.constrainedFlag$sample57[((sample$var45 - 0) / 1)] = true;
																
																// Set an accumulator to sum the probabilities for each possible configuration of
																// inputs.
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																
																// Set an accumulator to record the consumer distributions not seen. Initially set
																// to 1 as seen values will be deducted from this value.
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	// Enumerating the possible arguments for the variable Categorical 73 which is consuming
																	// the output of Sample task 57.
																	{
																		for(int var32 = 0; var32 < state.noStates; var32 += 1) {
																			if((var32 == traceTempVariable$var71$2_1)) {
																				{
																					{
																						{
																							// Constructing a random variable input for use later.
																							double[] var72 = state.m[traceTempVariable$var71$2_1];
																							
																							// Record the probability of sample task 76 generating output with current configuration.
																							if(((Math.log(1.0) + ((((((0.0 <= state.st[index$sample$2_2][timeStep$var66]) && (state.st[index$sample$2_2][timeStep$var66] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[state.st[index$sample$2_2][timeStep$var66]])) && (var72[state.st[index$sample$2_2][timeStep$var66]] <= 1.0))?Math.log(var72[state.st[index$sample$2_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.st[index$sample$2_2][timeStep$var66]) && (state.st[index$sample$2_2][timeStep$var66] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[state.st[index$sample$2_2][timeStep$var66]])) && (var72[state.st[index$sample$2_2][timeStep$var66]] <= 1.0))?Math.log(var72[state.st[index$sample$2_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.st[index$sample$2_2][timeStep$var66]) && (state.st[index$sample$2_2][timeStep$var66] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[state.st[index$sample$2_2][timeStep$var66]])) && (var72[state.st[index$sample$2_2][timeStep$var66]] <= 1.0))?Math.log(var72[state.st[index$sample$2_2][timeStep$var66]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.st[index$sample$2_2][timeStep$var66]) && (state.st[index$sample$2_2][timeStep$var66] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[state.st[index$sample$2_2][timeStep$var66]])) && (var72[state.st[index$sample$2_2][timeStep$var66]] <= 1.0))?Math.log(var72[state.st[index$sample$2_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.st[index$sample$2_2][timeStep$var66]) && (state.st[index$sample$2_2][timeStep$var66] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[state.st[index$sample$2_2][timeStep$var66]])) && (var72[state.st[index$sample$2_2][timeStep$var66]] <= 1.0))?Math.log(var72[state.st[index$sample$2_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 76 with the current configuration.
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
								}
							}
						}
					}
					
					// Processing random variable 231.
					{
						// Looking for a path between Sample 57 and consumer Bernoulli 231.
						{
							{
								int traceTempVariable$currentState$8_1 = cv$currentValue;
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									if((sample$var45 == sample$var196)) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if((0 == timeStep$var226)) {
												for(int server = 0; server < state.noServers; server += 1) {
													// Flag recording if this sample task of the consuming random variable is constrained.
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														// Mark that the sample has observed constrained data.
														state.constrainedFlag$sample57[((sample$var45 - 0) / 1)] = true;
														
														// Set an accumulator to sum the probabilities for each possible configuration of
														// inputs.
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														
														// Set an accumulator to record the consumer distributions not seen. Initially set
														// to 1 as seen values will be deducted from this value.
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															// Enumerating the possible arguments for the variable Bernoulli 231 which is consuming
															// the output of Sample task 57.
															{
																for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																	for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																		if((var173 == server)) {
																			if((var183 == traceTempVariable$currentState$8_1)) {
																				{
																					{
																						{
																							// Constructing a random variable input for use later.
																							double var230 = state.current_metric_valid_bias[server][traceTempVariable$currentState$8_1];
																							
																							// Record the probability of sample task 241 generating output with current configuration.
																							if(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)));
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
					
					// Processing random variable 244.
					{
						// Looking for a path between Sample 57 and consumer Gaussian 244.
						{
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[][][] guard$sample57gaussian255 = scratch.guard$sample57gaussian255$global[threadID$cv$sample$var45];
							{
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									if((sample$var45 == sample$var196)) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if((0 == timeStep$var226)) {
												for(int server = 0; server < state.noServers; server += 1) {
													if(state.metric_valid_g[sample$var196][server][timeStep$var226])
														// Set the flags to false
														guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
							{
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									if((sample$var45 == sample$var196)) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if((0 == timeStep$var226)) {
												for(int server = 0; server < state.noServers; server += 1) {
													if(state.metric_valid_g[sample$var196][server][timeStep$var226])
														// Set the flags to false
														guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
							{
								int traceTempVariable$currentState$14_1 = cv$currentValue;
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									if((sample$var45 == sample$var196)) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if((0 == timeStep$var226)) {
												for(int server = 0; server < state.noServers; server += 1) {
													if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
														if(!guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
															
															// Processing sample task 256 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		state.constrainedFlag$sample57[((sample$var45 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																			// the output of Sample task 57.
																			{
																				for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																					for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																						if((var119 == server)) {
																							if((var129 == traceTempVariable$currentState$14_1)) {
																								{
																									int traceTempVariable$currentState$19_1 = cv$currentValue;
																									if((index$sample$1 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											{
																												for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																													for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == traceTempVariable$currentState$19_1)) {
																																{
																																	{
																																		{
																																			// Constructing a random variable input for use later.
																																			double var241 = state.current_metric_mean[server][traceTempVariable$currentState$19_1];
																																			
																																			// Constructing a random variable input for use later.
																																			double var243 = state.current_metric_var[server][traceTempVariable$currentState$19_1];
																																			
																																			// Record the probability of sample task 256 generating output with current configuration.
																																			if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																								for(int index$sample$20 = 0; index$sample$20 < state.noSamples; index$sample$20 += 1) {
																									if(!(index$sample$20 == index$sample$1)) {
																										// Enumerating the possible outputs of Categorical 54.
																										for(int index$sample57$21 = 0; index$sample57$21 < state.noStates; index$sample57$21 += 1) {
																											int distributionTempVariable$var55$23 = index$sample57$21;
																											
																											// Update the probability of sampling this value from the distribution value.
																											double cv$probabilitySample57Value22 = (1.0 * state.distribution$sample57[((index$sample$20 - 0) / 1)][index$sample57$21]);
																											{
																												int traceTempVariable$currentState$24_1 = distributionTempVariable$var55$23;
																												if((index$sample$20 == sample$var196)) {
																													if((0 == timeStep$var226)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == traceTempVariable$currentState$24_1)) {
																																			{
																																				{
																																					{
																																						// Constructing a random variable input for use later.
																																						double var241 = state.current_metric_mean[server][traceTempVariable$currentState$24_1];
																																						
																																						// Constructing a random variable input for use later.
																																						double var243 = state.current_metric_var[server][traceTempVariable$currentState$24_1];
																																						
																																						// Record the probability of sample task 256 generating output with current configuration.
																																						if(((Math.log(cv$probabilitySample57Value22) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value22) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value22) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value22) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value22) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																						}
																																						
																																						// Recorded the probability of reaching sample task 256 with the current configuration.
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value22);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
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
																			{
																				for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																					for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																						if((var119 == server)) {
																							if((var129 == traceTempVariable$currentState$14_1)) {
																								if(state.fixedFlag$sample76) {
																									{
																										for(int index$sample$28_1 = 0; index$sample$28_1 < state.noSamples; index$sample$28_1 += 1) {
																											for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$28_1][0]; timeStep$var66 += 1) {
																												if((index$sample$28_1 == sample$var196)) {
																													if((timeStep$var66 == timeStep$var226)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == traceTempVariable$currentState$14_1)) {
																																			{
																																				{
																																					{
																																						// Constructing a random variable input for use later.
																																						double var241 = state.current_metric_mean[server][traceTempVariable$currentState$14_1];
																																						
																																						// Constructing a random variable input for use later.
																																						double var243 = state.current_metric_var[server][traceTempVariable$currentState$14_1];
																																						
																																						// Record the probability of sample task 256 generating output with current configuration.
																																						if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																									for(int index$sample$29 = 0; index$sample$29 < state.noSamples; index$sample$29 += 1) {
																										for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$29][0]; timeStep$var66 += 1) {
																											if(true) {
																												// Enumerating the possible outputs of Categorical 73.
																												for(int index$sample76$31 = 0; index$sample76$31 < state.noStates; index$sample76$31 += 1) {
																													int distributionTempVariable$var74$33 = index$sample76$31;
																													
																													// Update the probability of sampling this value from the distribution value.
																													double cv$probabilitySample76Value32 = (1.0 * state.distribution$sample76[((index$sample$29 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$31]);
																													{
																														int traceTempVariable$currentState$34_1 = distributionTempVariable$var74$33;
																														if((index$sample$29 == sample$var196)) {
																															if((timeStep$var66 == timeStep$var226)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$34_1)) {
																																					{
																																						{
																																							{
																																								// Constructing a random variable input for use later.
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$34_1];
																																								
																																								// Constructing a random variable input for use later.
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$34_1];
																																								
																																								// Record the probability of sample task 256 generating output with current configuration.
																																								if(((Math.log(cv$probabilitySample76Value32) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value32) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value32) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value32) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value32) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																								}
																																								
																																								// Recorded the probability of reaching sample task 256 with the current configuration.
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value32);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
							{
								int traceTempVariable$currentState$15_1 = cv$currentValue;
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									if((sample$var45 == sample$var196)) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if((0 == timeStep$var226)) {
												for(int server = 0; server < state.noServers; server += 1) {
													if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
														if(!guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
															
															// Processing sample task 256 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		state.constrainedFlag$sample57[((sample$var45 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																			// the output of Sample task 57.
																			{
																				int traceTempVariable$currentState$37_1 = cv$currentValue;
																				if((index$sample$1 == sample$var196)) {
																					if((0 == timeStep$var226)) {
																						{
																							for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																								for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																									if((var119 == server)) {
																										if((var129 == traceTempVariable$currentState$37_1)) {
																											{
																												for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																													for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == traceTempVariable$currentState$37_1)) {
																																{
																																	{
																																		{
																																			// Constructing a random variable input for use later.
																																			double var241 = state.current_metric_mean[server][traceTempVariable$currentState$37_1];
																																			
																																			// Constructing a random variable input for use later.
																																			double var243 = state.current_metric_var[server][traceTempVariable$currentState$37_1];
																																			
																																			// Record the probability of sample task 256 generating output with current configuration.
																																			if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				// If the second value is -infinity.
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																				}
																			}
																			for(int index$sample$38 = 0; index$sample$38 < state.noSamples; index$sample$38 += 1) {
																				if(!(index$sample$38 == index$sample$1)) {
																					// Enumerating the possible outputs of Categorical 54.
																					for(int index$sample57$39 = 0; index$sample57$39 < state.noStates; index$sample57$39 += 1) {
																						int distributionTempVariable$var55$41 = index$sample57$39;
																						
																						// Update the probability of sampling this value from the distribution value.
																						double cv$probabilitySample57Value40 = (1.0 * state.distribution$sample57[((index$sample$38 - 0) / 1)][index$sample57$39]);
																						{
																							int traceTempVariable$currentState$42_1 = distributionTempVariable$var55$41;
																							if((index$sample$38 == sample$var196)) {
																								if((0 == timeStep$var226)) {
																									{
																										for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																											for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																												if((var119 == server)) {
																													if((var129 == traceTempVariable$currentState$42_1)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == traceTempVariable$currentState$42_1)) {
																																			{
																																				{
																																					{
																																						// Constructing a random variable input for use later.
																																						double var241 = state.current_metric_mean[server][traceTempVariable$currentState$42_1];
																																						
																																						// Constructing a random variable input for use later.
																																						double var243 = state.current_metric_var[server][traceTempVariable$currentState$42_1];
																																						
																																						// Record the probability of sample task 256 generating output with current configuration.
																																						if(((Math.log(cv$probabilitySample57Value40) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value40) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value40) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value40) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value40) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																						}
																																						
																																						// Recorded the probability of reaching sample task 256 with the current configuration.
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value40);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
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
																			if(state.fixedFlag$sample76) {
																				{
																					for(int index$sample$47_1 = 0; index$sample$47_1 < state.noSamples; index$sample$47_1 += 1) {
																						for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$47_1][0]; timeStep$var66 += 1) {
																							if((index$sample$47_1 == sample$var196)) {
																								if((timeStep$var66 == timeStep$var226)) {
																									{
																										for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																											for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																												if((var119 == server)) {
																													if((var129 == traceTempVariable$currentState$15_1)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == traceTempVariable$currentState$15_1)) {
																																			{
																																				{
																																					{
																																						// Constructing a random variable input for use later.
																																						double var241 = state.current_metric_mean[server][traceTempVariable$currentState$15_1];
																																						
																																						// Constructing a random variable input for use later.
																																						double var243 = state.current_metric_var[server][traceTempVariable$currentState$15_1];
																																						
																																						// Record the probability of sample task 256 generating output with current configuration.
																																						if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																							}
																						}
																					}
																				}
																			} else {
																				for(int index$sample$48 = 0; index$sample$48 < state.noSamples; index$sample$48 += 1) {
																					for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$48][0]; timeStep$var66 += 1) {
																						if(true) {
																							// Enumerating the possible outputs of Categorical 73.
																							for(int index$sample76$50 = 0; index$sample76$50 < state.noStates; index$sample76$50 += 1) {
																								int distributionTempVariable$var74$52 = index$sample76$50;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample76Value51 = (1.0 * state.distribution$sample76[((index$sample$48 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$50]);
																								{
																									int traceTempVariable$currentState$53_1 = distributionTempVariable$var74$52;
																									if((index$sample$48 == sample$var196)) {
																										if((timeStep$var66 == timeStep$var226)) {
																											{
																												for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																													for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																														if((var119 == server)) {
																															if((var129 == traceTempVariable$currentState$53_1)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$53_1)) {
																																					{
																																						{
																																							{
																																								// Constructing a random variable input for use later.
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$53_1];
																																								
																																								// Constructing a random variable input for use later.
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$53_1];
																																								
																																								// Record the probability of sample task 256 generating output with current configuration.
																																								if(((Math.log(cv$probabilitySample76Value51) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value51) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value51) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value51) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value51) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																								}
																																								
																																								// Recorded the probability of reaching sample task 256 with the current configuration.
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value51);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
						{
							int traceTempVariable$var71$66_1 = cv$currentValue;
							for(int index$sample$66_2 = 0; index$sample$66_2 < state.noSamples; index$sample$66_2 += 1) {
								if((sample$var45 == index$sample$66_2)) {
									for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$66_2][0]; timeStep$var66 += 1) {
										if((0 == (timeStep$var66 - 1))) {
											if(!state.fixedFlag$sample76) {
												// Processing sample task 76 of consumer random variable null.
												{
													{
														// Copy of index so that its values can be safely substituted
														int index$timeStep$68 = timeStep$var66;
														
														// Copy of index so that its values can be safely substituted
														int index$sample$69 = index$sample$66_2;
														
														// A local array to hold the accumulated distributions of the sample tasks for each
														// configuration of distributions.
														double[] cv$accumulatedConsumerDistributions = scratch.cv$distributionAccumulator$var73[threadID$cv$sample$var45];
														
														// Zero all the elements in the distribution accumulator
														for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
															cv$accumulatedConsumerDistributions[cv$i] = 0.0;
														
														// Zero an accumulator to track the probabilities reached.
														double cv$reachedDistributionProbability = 0.0;
														
														// Enumerating the possible arguments for the variable Categorical 73 which is consuming
														// the output of Sample task 57.
														{
															for(int var32 = 0; var32 < state.noStates; var32 += 1) {
																if((var32 == traceTempVariable$var71$66_1)) {
																	{
																		// Declare and zero an accumulator for tracking the reached source probability space.
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			// Add the probability of this argument configuration.
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		
																		// Constructing a random variable input for use later.
																		double[] var72 = state.m[traceTempVariable$var71$66_1];
																		
																		// The probability of reaching the consumer with this set of consumer arguments
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																		
																		// Record the reached distribution.
																		cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
																		
																		// Add the current distribution to the distribution accumulator.
																		DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var72, state.noStates);
																	}
																}
															}
														}
														
														// A local copy of the samples' distribution.
														double[] cv$sampleDistribution = state.distribution$sample76[((index$sample$66_2 - 0) / 1)][((timeStep$var66 - 1) / 1)];
														
														// The overlap of the distributions so far.
														double cv$overlap = 0.0;
														
														// Calculate the overlap for each element in the distribution
														for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
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
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample57[((sample$var45 - 0) / 1)]) {
				// Set the calculated probabilities to be the distribution values, and normalize
				// Local copy of the probability array
				double[] cv$localProbability = state.distribution$sample57[((sample$var45 - 0) / 1)];
				
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialise the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
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
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 76 drawn from Categorical 73. Inference was performed using variable
	// marginalization.
	private final void inferSample76(int sample$var45, int timeStep$var66, int threadID$cv$sample$var45, Rng RNG$) {
		// Copy of index so that its values can be safely substituted
		int index$timeStep$1 = timeStep$var66;
		
		// Copy of index so that its values can be safely substituted
		int index$sample$2 = sample$var45;
		if(true) {
			state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			
			// Exploring all the possible state counts for random variable 73.
			// 
			// Enumerating the possible arguments for Categorical 73.
			if(state.fixedFlag$sample57) {
				{
					for(int index$sample$3_1 = 0; index$sample$3_1 < state.noSamples; index$sample$3_1 += 1) {
						if((index$sample$3_1 == sample$var45)) {
							if((0 == (timeStep$var66 - 1))) {
								{
									for(int var32 = 0; var32 < state.noStates; var32 += 1) {
										if((var32 == state.st[sample$var45][(timeStep$var66 - 1)]))
											// variable marginalization
											cv$numStates = Math.max(cv$numStates, state.noStates);
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$sample$4 = 0; index$sample$4 < state.noSamples; index$sample$4 += 1) {
					if(true) {
						// Enumerating the possible outputs of Categorical 54.
						for(int index$sample57$5 = 0; index$sample57$5 < state.noStates; index$sample57$5 += 1) {
							int distributionTempVariable$var55$7 = index$sample57$5;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample57Value6 = (1.0 * state.distribution$sample57[((index$sample$4 - 0) / 1)][index$sample57$5]);
							{
								int traceTempVariable$var71$8_1 = distributionTempVariable$var55$7;
								if((index$sample$4 == sample$var45)) {
									if((0 == (timeStep$var66 - 1))) {
										{
											for(int var32 = 0; var32 < state.noStates; var32 += 1) {
												if((var32 == traceTempVariable$var71$8_1))
													// variable marginalization
													cv$numStates = Math.max(cv$numStates, state.noStates);
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
			{
				if((index$sample$2 == sample$var45)) {
					if((index$timeStep$1 == (timeStep$var66 - 1))) {
						{
							for(int var32 = 0; var32 < state.noStates; var32 += 1) {
								if((var32 == state.st[sample$var45][(timeStep$var66 - 1)]))
									// variable marginalization
									cv$numStates = Math.max(cv$numStates, state.noStates);
							}
						}
					}
				}
			}
			for(int index$sample$12 = 0; index$sample$12 < state.noSamples; index$sample$12 += 1) {
				for(int index$timeStep$13 = 1; index$timeStep$13 < state.length$metric[index$sample$12][0]; index$timeStep$13 += 1) {
					if(!((index$timeStep$13 == index$timeStep$1) && (index$sample$12 == index$sample$2))) {
						// Enumerating the possible outputs of Categorical 73.
						for(int index$sample76$14 = 0; index$sample76$14 < state.noStates; index$sample76$14 += 1) {
							int distributionTempVariable$var74$16 = index$sample76$14;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample76Value15 = (1.0 * state.distribution$sample76[((index$sample$12 - 0) / 1)][((index$timeStep$13 - 1) / 1)][index$sample76$14]);
							{
								int traceTempVariable$var71$17_1 = distributionTempVariable$var74$16;
								if((index$sample$12 == sample$var45)) {
									if((index$timeStep$13 == (timeStep$var66 - 1))) {
										{
											for(int var32 = 0; var32 < state.noStates; var32 += 1) {
												if((var32 == traceTempVariable$var71$17_1))
													// variable marginalization
													cv$numStates = Math.max(cv$numStates, state.noStates);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var74$stateProbabilityGlobal[threadID$cv$sample$var45];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Exploring all the possible distribution values for random variable 73 creating
				// sample task 76.
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
				if(state.fixedFlag$sample57) {
					{
						for(int index$sample$20_1 = 0; index$sample$20_1 < state.noSamples; index$sample$20_1 += 1) {
							if((index$sample$20_1 == sample$var45)) {
								if((0 == (timeStep$var66 - 1))) {
									{
										for(int var32 = 0; var32 < state.noStates; var32 += 1) {
											if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
												// Record the reached probability density.
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
												
												// Constructing a random variable input for use later.
												double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
												
												// An accumulator to allow the value for each distribution to be constructed before
												// it is added to the index probabilities.
												double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$currentValue])) && (var72[cv$currentValue] <= 1.0))?Math.log(var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
												
												// Processing random variable 73.
												{
													// Looking for a path between Sample 76 and consumer Categorical 73.
													{
														{
															int traceTempVariable$var71$37_1 = cv$currentValue;
														}
													}
												}
												
												// Processing random variable 231.
												{
													// Looking for a path between Sample 76 and consumer Bernoulli 231.
													{
														{
															int traceTempVariable$currentState$41_1 = cv$currentValue;
															for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																if((sample$var45 == sample$var196)) {
																	for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																		if((timeStep$var66 == timeStep$var226)) {
																			for(int server = 0; server < state.noServers; server += 1) {
																				// Flag recording if this sample task of the consuming random variable is constrained.
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					// Mark that the sample has observed constrained data.
																					state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																					
																					// Set an accumulator to sum the probabilities for each possible configuration of
																					// inputs.
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					
																					// Set an accumulator to record the consumer distributions not seen. Initially set
																					// to 1 as seen values will be deducted from this value.
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						// Enumerating the possible arguments for the variable Bernoulli 231 which is consuming
																						// the output of Sample task 76.
																						{
																							for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																								for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																									if((var173 == server)) {
																										if((var183 == traceTempVariable$currentState$41_1)) {
																											{
																												{
																													{
																														// Constructing a random variable input for use later.
																														double var230 = state.current_metric_valid_bias[server][traceTempVariable$currentState$41_1];
																														
																														// Record the probability of sample task 241 generating output with current configuration.
																														if(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)));
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
												
												// Processing random variable 244.
												{
													// Looking for a path between Sample 76 and consumer Gaussian 244.
													{
														// Guard to check that at most one copy of the code is executed for a given random
														// variable instance.
														boolean[][][] guard$sample76gaussian255 = scratch.guard$sample76gaussian255$global[threadID$cv$sample$var45];
														{
															for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																if((sample$var45 == sample$var196)) {
																	for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																		if((timeStep$var66 == timeStep$var226)) {
																			for(int server = 0; server < state.noServers; server += 1) {
																				if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																					// Set the flags to false
																					guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																			}
																		}
																	}
																}
															}
														}
														{
															for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																if((sample$var45 == sample$var196)) {
																	for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																		if((timeStep$var66 == timeStep$var226)) {
																			for(int server = 0; server < state.noServers; server += 1) {
																				if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																					// Set the flags to false
																					guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																			}
																		}
																	}
																}
															}
														}
														{
															int traceTempVariable$currentState$65_1 = cv$currentValue;
															for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																if((sample$var45 == sample$var196)) {
																	for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																		if((timeStep$var66 == timeStep$var226)) {
																			for(int server = 0; server < state.noServers; server += 1) {
																				if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																					if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																						// The body will execute, so should not be executed again
																						guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																						
																						// Processing sample task 256 of consumer random variable null.
																						{
																							{
																								// Flag recording if this sample task of the consuming random variable is constrained.
																								boolean cv$sampleConstrained = true;
																								if(cv$sampleConstrained) {
																									// Mark that the sample has observed constrained data.
																									state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																									
																									// Set an accumulator to sum the probabilities for each possible configuration of
																									// inputs.
																									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																									
																									// Set an accumulator to record the consumer distributions not seen. Initially set
																									// to 1 as seen values will be deducted from this value.
																									double cv$consumerDistributionProbabilityAccumulator = 1.0;
																									{
																										// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																										// the output of Sample task 76.
																										{
																											for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																												for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																													if((var119 == server)) {
																														if((var129 == traceTempVariable$currentState$65_1)) {
																															{
																																for(int index$sample$82_1 = 0; index$sample$82_1 < state.noSamples; index$sample$82_1 += 1) {
																																	if((index$sample$82_1 == sample$var196)) {
																																		if((0 == timeStep$var226)) {
																																			{
																																				for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																					for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																						if((var146 == server)) {
																																							if((var156 == traceTempVariable$currentState$65_1)) {
																																								{
																																									{
																																										{
																																											// Constructing a random variable input for use later.
																																											double var241 = state.current_metric_mean[server][traceTempVariable$currentState$65_1];
																																											
																																											// Constructing a random variable input for use later.
																																											double var243 = state.current_metric_var[server][traceTempVariable$currentState$65_1];
																																											
																																											// Record the probability of sample task 256 generating output with current configuration.
																																											if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												// If the second value is -infinity.
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																												}
																											}
																										}
																										
																										// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																										// the output of Sample task 76.
																										{
																											for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																												for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																													if((var119 == server)) {
																														if((var129 == traceTempVariable$currentState$65_1)) {
																															{
																																int traceTempVariable$currentState$85_1 = cv$currentValue;
																																if((index$sample$2 == sample$var196)) {
																																	if((index$timeStep$1 == timeStep$var226)) {
																																		{
																																			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																					if((var146 == server)) {
																																						if((var156 == traceTempVariable$currentState$85_1)) {
																																							{
																																								{
																																									{
																																										// Constructing a random variable input for use later.
																																										double var241 = state.current_metric_mean[server][traceTempVariable$currentState$85_1];
																																										
																																										// Constructing a random variable input for use later.
																																										double var243 = state.current_metric_var[server][traceTempVariable$currentState$85_1];
																																										
																																										// Record the probability of sample task 256 generating output with current configuration.
																																										if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											// If the second value is -infinity.
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																															for(int index$sample$86 = 0; index$sample$86 < state.noSamples; index$sample$86 += 1) {
																																for(int index$timeStep$87 = 1; index$timeStep$87 < state.length$metric[index$sample$86][0]; index$timeStep$87 += 1) {
																																	if(!((index$timeStep$87 == index$timeStep$1) && (index$sample$86 == index$sample$2))) {
																																		// Enumerating the possible outputs of Categorical 73.
																																		for(int index$sample76$88 = 0; index$sample76$88 < state.noStates; index$sample76$88 += 1) {
																																			int distributionTempVariable$var74$90 = index$sample76$88;
																																			
																																			// Update the probability of sampling this value from the distribution value.
																																			double cv$probabilitySample76Value89 = (1.0 * state.distribution$sample76[((index$sample$86 - 0) / 1)][((index$timeStep$87 - 1) / 1)][index$sample76$88]);
																																			{
																																				int traceTempVariable$currentState$91_1 = distributionTempVariable$var74$90;
																																				if((index$sample$86 == sample$var196)) {
																																					if((index$timeStep$87 == timeStep$var226)) {
																																						{
																																							for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																								for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																									if((var146 == server)) {
																																										if((var156 == traceTempVariable$currentState$91_1)) {
																																											{
																																												{
																																													{
																																														// Constructing a random variable input for use later.
																																														double var241 = state.current_metric_mean[server][traceTempVariable$currentState$91_1];
																																														
																																														// Constructing a random variable input for use later.
																																														double var243 = state.current_metric_var[server][traceTempVariable$currentState$91_1];
																																														
																																														// Record the probability of sample task 256 generating output with current configuration.
																																														if(((Math.log(cv$probabilitySample76Value89) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value89) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																														else {
																																															// If the second value is -infinity.
																																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value89) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																															else
																																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value89) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value89) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																														}
																																														
																																														// Recorded the probability of reaching sample task 256 with the current configuration.
																																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value89);
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
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
														{
															int traceTempVariable$currentState$69_1 = cv$currentValue;
															for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																if((sample$var45 == sample$var196)) {
																	for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																		if((timeStep$var66 == timeStep$var226)) {
																			for(int server = 0; server < state.noServers; server += 1) {
																				if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																					if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																						// The body will execute, so should not be executed again
																						guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																						
																						// Processing sample task 256 of consumer random variable null.
																						{
																							{
																								// Flag recording if this sample task of the consuming random variable is constrained.
																								boolean cv$sampleConstrained = true;
																								if(cv$sampleConstrained) {
																									// Mark that the sample has observed constrained data.
																									state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																									
																									// Set an accumulator to sum the probabilities for each possible configuration of
																									// inputs.
																									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																									
																									// Set an accumulator to record the consumer distributions not seen. Initially set
																									// to 1 as seen values will be deducted from this value.
																									double cv$consumerDistributionProbabilityAccumulator = 1.0;
																									{
																										// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																										// the output of Sample task 76.
																										{
																											for(int index$sample$153_1 = 0; index$sample$153_1 < state.noSamples; index$sample$153_1 += 1) {
																												if((index$sample$153_1 == sample$var196)) {
																													if((0 == timeStep$var226)) {
																														{
																															for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																	if((var119 == server)) {
																																		if((var129 == traceTempVariable$currentState$69_1)) {
																																			{
																																				for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																					for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																						if((var146 == server)) {
																																							if((var156 == traceTempVariable$currentState$69_1)) {
																																								{
																																									{
																																										{
																																											// Constructing a random variable input for use later.
																																											double var241 = state.current_metric_mean[server][traceTempVariable$currentState$69_1];
																																											
																																											// Constructing a random variable input for use later.
																																											double var243 = state.current_metric_var[server][traceTempVariable$currentState$69_1];
																																											
																																											// Record the probability of sample task 256 generating output with current configuration.
																																											if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												// If the second value is -infinity.
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																												}
																											}
																										}
																										
																										// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																										// the output of Sample task 76.
																										{
																											int traceTempVariable$currentState$156_1 = cv$currentValue;
																											if((index$sample$2 == sample$var196)) {
																												if((index$timeStep$1 == timeStep$var226)) {
																													{
																														for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																															for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																if((var119 == server)) {
																																	if((var129 == traceTempVariable$currentState$156_1)) {
																																		{
																																			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																					if((var146 == server)) {
																																						if((var156 == traceTempVariable$currentState$156_1)) {
																																							{
																																								{
																																									{
																																										// Constructing a random variable input for use later.
																																										double var241 = state.current_metric_mean[server][traceTempVariable$currentState$156_1];
																																										
																																										// Constructing a random variable input for use later.
																																										double var243 = state.current_metric_var[server][traceTempVariable$currentState$156_1];
																																										
																																										// Record the probability of sample task 256 generating output with current configuration.
																																										if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											// If the second value is -infinity.
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																											}
																										}
																										for(int index$sample$157 = 0; index$sample$157 < state.noSamples; index$sample$157 += 1) {
																											for(int index$timeStep$158 = 1; index$timeStep$158 < state.length$metric[index$sample$157][0]; index$timeStep$158 += 1) {
																												if(!((index$timeStep$158 == index$timeStep$1) && (index$sample$157 == index$sample$2))) {
																													// Enumerating the possible outputs of Categorical 73.
																													for(int index$sample76$159 = 0; index$sample76$159 < state.noStates; index$sample76$159 += 1) {
																														int distributionTempVariable$var74$161 = index$sample76$159;
																														
																														// Update the probability of sampling this value from the distribution value.
																														double cv$probabilitySample76Value160 = (1.0 * state.distribution$sample76[((index$sample$157 - 0) / 1)][((index$timeStep$158 - 1) / 1)][index$sample76$159]);
																														{
																															int traceTempVariable$currentState$162_1 = distributionTempVariable$var74$161;
																															if((index$sample$157 == sample$var196)) {
																																if((index$timeStep$158 == timeStep$var226)) {
																																	{
																																		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																				if((var119 == server)) {
																																					if((var129 == traceTempVariable$currentState$162_1)) {
																																						{
																																							for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																								for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																									if((var146 == server)) {
																																										if((var156 == traceTempVariable$currentState$162_1)) {
																																											{
																																												{
																																													{
																																														// Constructing a random variable input for use later.
																																														double var241 = state.current_metric_mean[server][traceTempVariable$currentState$162_1];
																																														
																																														// Constructing a random variable input for use later.
																																														double var243 = state.current_metric_var[server][traceTempVariable$currentState$162_1];
																																														
																																														// Record the probability of sample task 256 generating output with current configuration.
																																														if(((Math.log(cv$probabilitySample76Value160) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value160) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																														else {
																																															// If the second value is -infinity.
																																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value160) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																															else
																																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value160) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value160) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																														}
																																														
																																														// Recorded the probability of reaching sample task 256 with the current configuration.
																																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value160);
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
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
				} else {
					for(int index$sample$21 = 0; index$sample$21 < state.noSamples; index$sample$21 += 1) {
						if(true) {
							// Enumerating the possible outputs of Categorical 54.
							for(int index$sample57$22 = 0; index$sample57$22 < state.noStates; index$sample57$22 += 1) {
								int distributionTempVariable$var55$24 = index$sample57$22;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample57Value23 = (1.0 * state.distribution$sample57[((index$sample$21 - 0) / 1)][index$sample57$22]);
								{
									int traceTempVariable$var71$25_1 = distributionTempVariable$var55$24;
									if((index$sample$21 == sample$var45)) {
										if((0 == (timeStep$var66 - 1))) {
											{
												for(int var32 = 0; var32 < state.noStates; var32 += 1) {
													if((var32 == traceTempVariable$var71$25_1)) {
														// Record the reached probability density.
														cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value23);
														
														// Constructing a random variable input for use later.
														double[] var72 = state.m[traceTempVariable$var71$25_1];
														
														// An accumulator to allow the value for each distribution to be constructed before
														// it is added to the index probabilities.
														double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value23) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$currentValue])) && (var72[cv$currentValue] <= 1.0))?Math.log(var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
														
														// Processing random variable 73.
														{
															// Looking for a path between Sample 76 and consumer Categorical 73.
															{
																{
																	int traceTempVariable$var71$38_1 = cv$currentValue;
																}
															}
														}
														
														// Processing random variable 231.
														{
															// Looking for a path between Sample 76 and consumer Bernoulli 231.
															{
																{
																	int traceTempVariable$currentState$42_1 = cv$currentValue;
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						// Flag recording if this sample task of the consuming random variable is constrained.
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							// Mark that the sample has observed constrained data.
																							state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																							
																							// Set an accumulator to sum the probabilities for each possible configuration of
																							// inputs.
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							
																							// Set an accumulator to record the consumer distributions not seen. Initially set
																							// to 1 as seen values will be deducted from this value.
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								// Enumerating the possible arguments for the variable Bernoulli 231 which is consuming
																								// the output of Sample task 76.
																								{
																									for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																										for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																											if((var173 == server)) {
																												if((var183 == traceTempVariable$currentState$42_1)) {
																													{
																														{
																															{
																																// Constructing a random variable input for use later.
																																double var230 = state.current_metric_valid_bias[server][traceTempVariable$currentState$42_1];
																																
																																// Record the probability of sample task 241 generating output with current configuration.
																																if(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)));
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
														
														// Processing random variable 244.
														{
															// Looking for a path between Sample 76 and consumer Gaussian 244.
															{
																// Guard to check that at most one copy of the code is executed for a given random
																// variable instance.
																boolean[][][] guard$sample76gaussian255 = scratch.guard$sample76gaussian255$global[threadID$cv$sample$var45];
																{
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																							// Set the flags to false
																							guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																					}
																				}
																			}
																		}
																	}
																}
																{
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																							// Set the flags to false
																							guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																					}
																				}
																			}
																		}
																	}
																}
																{
																	int traceTempVariable$currentState$66_1 = cv$currentValue;
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																							if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																								// The body will execute, so should not be executed again
																								guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																								
																								// Processing sample task 256 of consumer random variable null.
																								{
																									{
																										// Flag recording if this sample task of the consuming random variable is constrained.
																										boolean cv$sampleConstrained = true;
																										if(cv$sampleConstrained) {
																											// Mark that the sample has observed constrained data.
																											state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																											
																											// Set an accumulator to sum the probabilities for each possible configuration of
																											// inputs.
																											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																											
																											// Set an accumulator to record the consumer distributions not seen. Initially set
																											// to 1 as seen values will be deducted from this value.
																											double cv$consumerDistributionProbabilityAccumulator = 1.0;
																											{
																												// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																												// the output of Sample task 76.
																												{
																													for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																														for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																															if((var119 == server)) {
																																if((var129 == traceTempVariable$currentState$66_1)) {
																																	{
																																		int traceTempVariable$currentState$95_1 = distributionTempVariable$var55$24;
																																		if((index$sample$21 == sample$var196)) {
																																			if((0 == timeStep$var226)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$95_1)) {
																																									{
																																										{
																																											{
																																												// Constructing a random variable input for use later.
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$95_1];
																																												
																																												// Constructing a random variable input for use later.
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$95_1];
																																												
																																												// Record the probability of sample task 256 generating output with current configuration.
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													// If the second value is -infinity.
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																																	for(int index$sample$96 = 0; index$sample$96 < state.noSamples; index$sample$96 += 1) {
																																		if(!(index$sample$96 == index$sample$21)) {
																																			// Enumerating the possible outputs of Categorical 54.
																																			for(int index$sample57$97 = 0; index$sample57$97 < state.noStates; index$sample57$97 += 1) {
																																				int distributionTempVariable$var55$99 = index$sample57$97;
																																				
																																				// Update the probability of sampling this value from the distribution value.
																																				double cv$probabilitySample57Value98 = (1.0 * state.distribution$sample57[((index$sample$96 - 0) / 1)][index$sample57$97]);
																																				{
																																					int traceTempVariable$currentState$100_1 = distributionTempVariable$var55$99;
																																					if((index$sample$96 == sample$var196)) {
																																						if((0 == timeStep$var226)) {
																																							{
																																								for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																									for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																										if((var146 == server)) {
																																											if((var156 == traceTempVariable$currentState$100_1)) {
																																												{
																																													{
																																														{
																																															// Constructing a random variable input for use later.
																																															double var241 = state.current_metric_mean[server][traceTempVariable$currentState$100_1];
																																															
																																															// Constructing a random variable input for use later.
																																															double var243 = state.current_metric_var[server][traceTempVariable$currentState$100_1];
																																															
																																															// Record the probability of sample task 256 generating output with current configuration.
																																															if(((Math.log(cv$probabilitySample57Value98) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value98) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																															else {
																																																// If the second value is -infinity.
																																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value98) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																else
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value98) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value98) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																															}
																																															
																																															// Recorded the probability of reaching sample task 256 with the current configuration.
																																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value98);
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
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
																												{
																													for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																														for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																															if((var119 == server)) {
																																if((var129 == traceTempVariable$currentState$66_1)) {
																																	{
																																		int traceTempVariable$currentState$104_1 = cv$currentValue;
																																		if((index$sample$2 == sample$var196)) {
																																			if((index$timeStep$1 == timeStep$var226)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$104_1)) {
																																									{
																																										{
																																											{
																																												// Constructing a random variable input for use later.
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$104_1];
																																												
																																												// Constructing a random variable input for use later.
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$104_1];
																																												
																																												// Record the probability of sample task 256 generating output with current configuration.
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													// If the second value is -infinity.
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																																	for(int index$sample$105 = 0; index$sample$105 < state.noSamples; index$sample$105 += 1) {
																																		for(int index$timeStep$106 = 1; index$timeStep$106 < state.length$metric[index$sample$105][0]; index$timeStep$106 += 1) {
																																			if(!((index$timeStep$106 == index$timeStep$1) && (index$sample$105 == index$sample$2))) {
																																				// Enumerating the possible outputs of Categorical 73.
																																				for(int index$sample76$107 = 0; index$sample76$107 < state.noStates; index$sample76$107 += 1) {
																																					int distributionTempVariable$var74$109 = index$sample76$107;
																																					
																																					// Update the probability of sampling this value from the distribution value.
																																					double cv$probabilitySample76Value108 = (1.0 * state.distribution$sample76[((index$sample$105 - 0) / 1)][((index$timeStep$106 - 1) / 1)][index$sample76$107]);
																																					{
																																						int traceTempVariable$currentState$110_1 = distributionTempVariable$var74$109;
																																						if((index$sample$105 == sample$var196)) {
																																							if((index$timeStep$106 == timeStep$var226)) {
																																								{
																																									for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																										for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																											if((var146 == server)) {
																																												if((var156 == traceTempVariable$currentState$110_1)) {
																																													{
																																														{
																																															{
																																																// Constructing a random variable input for use later.
																																																double var241 = state.current_metric_mean[server][traceTempVariable$currentState$110_1];
																																																
																																																// Constructing a random variable input for use later.
																																																double var243 = state.current_metric_var[server][traceTempVariable$currentState$110_1];
																																																
																																																// Record the probability of sample task 256 generating output with current configuration.
																																																if(((Math.log(cv$probabilitySample76Value108) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value108) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																																else {
																																																	// If the second value is -infinity.
																																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value108) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																	else
																																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value108) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value108) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																																}
																																																
																																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value108);
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
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
																{
																	int traceTempVariable$currentState$70_1 = cv$currentValue;
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																							if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																								// The body will execute, so should not be executed again
																								guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																								
																								// Processing sample task 256 of consumer random variable null.
																								{
																									{
																										// Flag recording if this sample task of the consuming random variable is constrained.
																										boolean cv$sampleConstrained = true;
																										if(cv$sampleConstrained) {
																											// Mark that the sample has observed constrained data.
																											state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																											
																											// Set an accumulator to sum the probabilities for each possible configuration of
																											// inputs.
																											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																											
																											// Set an accumulator to record the consumer distributions not seen. Initially set
																											// to 1 as seen values will be deducted from this value.
																											double cv$consumerDistributionProbabilityAccumulator = 1.0;
																											{
																												// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																												// the output of Sample task 76.
																												{
																													int traceTempVariable$currentState$167_1 = distributionTempVariable$var55$24;
																													if((index$sample$21 == sample$var196)) {
																														if((0 == timeStep$var226)) {
																															{
																																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																		if((var119 == server)) {
																																			if((var129 == traceTempVariable$currentState$167_1)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$167_1)) {
																																									{
																																										{
																																											{
																																												// Constructing a random variable input for use later.
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$167_1];
																																												
																																												// Constructing a random variable input for use later.
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$167_1];
																																												
																																												// Record the probability of sample task 256 generating output with current configuration.
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													// If the second value is -infinity.
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																													}
																												}
																												for(int index$sample$168 = 0; index$sample$168 < state.noSamples; index$sample$168 += 1) {
																													if(!(index$sample$168 == index$sample$21)) {
																														// Enumerating the possible outputs of Categorical 54.
																														for(int index$sample57$169 = 0; index$sample57$169 < state.noStates; index$sample57$169 += 1) {
																															int distributionTempVariable$var55$171 = index$sample57$169;
																															
																															// Update the probability of sampling this value from the distribution value.
																															double cv$probabilitySample57Value170 = (1.0 * state.distribution$sample57[((index$sample$168 - 0) / 1)][index$sample57$169]);
																															{
																																int traceTempVariable$currentState$172_1 = distributionTempVariable$var55$171;
																																if((index$sample$168 == sample$var196)) {
																																	if((0 == timeStep$var226)) {
																																		{
																																			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																				for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																					if((var119 == server)) {
																																						if((var129 == traceTempVariable$currentState$172_1)) {
																																							{
																																								for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																									for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																										if((var146 == server)) {
																																											if((var156 == traceTempVariable$currentState$172_1)) {
																																												{
																																													{
																																														{
																																															// Constructing a random variable input for use later.
																																															double var241 = state.current_metric_mean[server][traceTempVariable$currentState$172_1];
																																															
																																															// Constructing a random variable input for use later.
																																															double var243 = state.current_metric_var[server][traceTempVariable$currentState$172_1];
																																															
																																															// Record the probability of sample task 256 generating output with current configuration.
																																															if(((Math.log(cv$probabilitySample57Value170) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value170) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																															else {
																																																// If the second value is -infinity.
																																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value170) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																else
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value170) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value170) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																															}
																																															
																																															// Recorded the probability of reaching sample task 256 with the current configuration.
																																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value170);
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
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
																												{
																													int traceTempVariable$currentState$177_1 = cv$currentValue;
																													if((index$sample$2 == sample$var196)) {
																														if((index$timeStep$1 == timeStep$var226)) {
																															{
																																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																		if((var119 == server)) {
																																			if((var129 == traceTempVariable$currentState$177_1)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$177_1)) {
																																									{
																																										{
																																											{
																																												// Constructing a random variable input for use later.
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$177_1];
																																												
																																												// Constructing a random variable input for use later.
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$177_1];
																																												
																																												// Record the probability of sample task 256 generating output with current configuration.
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													// If the second value is -infinity.
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																													}
																												}
																												for(int index$sample$178 = 0; index$sample$178 < state.noSamples; index$sample$178 += 1) {
																													for(int index$timeStep$179 = 1; index$timeStep$179 < state.length$metric[index$sample$178][0]; index$timeStep$179 += 1) {
																														if(!((index$timeStep$179 == index$timeStep$1) && (index$sample$178 == index$sample$2))) {
																															// Enumerating the possible outputs of Categorical 73.
																															for(int index$sample76$180 = 0; index$sample76$180 < state.noStates; index$sample76$180 += 1) {
																																int distributionTempVariable$var74$182 = index$sample76$180;
																																
																																// Update the probability of sampling this value from the distribution value.
																																double cv$probabilitySample76Value181 = (1.0 * state.distribution$sample76[((index$sample$178 - 0) / 1)][((index$timeStep$179 - 1) / 1)][index$sample76$180]);
																																{
																																	int traceTempVariable$currentState$183_1 = distributionTempVariable$var74$182;
																																	if((index$sample$178 == sample$var196)) {
																																		if((index$timeStep$179 == timeStep$var226)) {
																																			{
																																				for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																					for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																						if((var119 == server)) {
																																							if((var129 == traceTempVariable$currentState$183_1)) {
																																								{
																																									for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																										for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																											if((var146 == server)) {
																																												if((var156 == traceTempVariable$currentState$183_1)) {
																																													{
																																														{
																																															{
																																																// Constructing a random variable input for use later.
																																																double var241 = state.current_metric_mean[server][traceTempVariable$currentState$183_1];
																																																
																																																// Constructing a random variable input for use later.
																																																double var243 = state.current_metric_var[server][traceTempVariable$currentState$183_1];
																																																
																																																// Record the probability of sample task 256 generating output with current configuration.
																																																if(((Math.log(cv$probabilitySample76Value181) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value181) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																																else {
																																																	// If the second value is -infinity.
																																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value181) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																	else
																																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value181) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value181) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																																}
																																																
																																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value181);
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
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
					}
				}
				
				// Enumerating the possible arguments for Categorical 73.
				{
					int traceTempVariable$var71$28_1 = cv$currentValue;
					if((index$sample$2 == sample$var45)) {
						if((index$timeStep$1 == (timeStep$var66 - 1))) {
							{
								for(int var32 = 0; var32 < state.noStates; var32 += 1) {
									if((var32 == traceTempVariable$var71$28_1)) {
										// Record the reached probability density.
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
										
										// Constructing a random variable input for use later.
										double[] var72 = state.m[traceTempVariable$var71$28_1];
										
										// An accumulator to allow the value for each distribution to be constructed before
										// it is added to the index probabilities.
										double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$currentValue])) && (var72[cv$currentValue] <= 1.0))?Math.log(var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
										
										// Processing random variable 73.
										{
											// Looking for a path between Sample 76 and consumer Categorical 73.
											{
												{
													int traceTempVariable$var71$39_1 = cv$currentValue;
												}
											}
										}
										
										// Processing random variable 231.
										{
											// Looking for a path between Sample 76 and consumer Bernoulli 231.
											{
												{
													int traceTempVariable$currentState$43_1 = cv$currentValue;
													for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < state.noServers; server += 1) {
																		// Flag recording if this sample task of the consuming random variable is constrained.
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			// Mark that the sample has observed constrained data.
																			state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																			
																			// Set an accumulator to sum the probabilities for each possible configuration of
																			// inputs.
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			
																			// Set an accumulator to record the consumer distributions not seen. Initially set
																			// to 1 as seen values will be deducted from this value.
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				// Enumerating the possible arguments for the variable Bernoulli 231 which is consuming
																				// the output of Sample task 76.
																				{
																					for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																						for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																							if((var173 == server)) {
																								if((var183 == traceTempVariable$currentState$43_1)) {
																									{
																										{
																											{
																												// Constructing a random variable input for use later.
																												double var230 = state.current_metric_valid_bias[server][traceTempVariable$currentState$43_1];
																												
																												// Record the probability of sample task 241 generating output with current configuration.
																												if(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)));
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
										
										// Processing random variable 244.
										{
											// Looking for a path between Sample 76 and consumer Gaussian 244.
											{
												// Guard to check that at most one copy of the code is executed for a given random
												// variable instance.
												boolean[][][] guard$sample76gaussian255 = scratch.guard$sample76gaussian255$global[threadID$cv$sample$var45];
												{
													for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < state.noServers; server += 1) {
																		if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																			// Set the flags to false
																			guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																	}
																}
															}
														}
													}
												}
												{
													for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < state.noServers; server += 1) {
																		if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																			// Set the flags to false
																			guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																	}
																}
															}
														}
													}
												}
												{
													int traceTempVariable$currentState$67_1 = cv$currentValue;
													for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < state.noServers; server += 1) {
																		if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																			if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																				// The body will execute, so should not be executed again
																				guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																				
																				// Processing sample task 256 of consumer random variable null.
																				{
																					{
																						// Flag recording if this sample task of the consuming random variable is constrained.
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							// Mark that the sample has observed constrained data.
																							state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																							
																							// Set an accumulator to sum the probabilities for each possible configuration of
																							// inputs.
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							
																							// Set an accumulator to record the consumer distributions not seen. Initially set
																							// to 1 as seen values will be deducted from this value.
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																								// the output of Sample task 76.
																								{
																									for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																										for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																											if((var119 == server)) {
																												if((var129 == traceTempVariable$currentState$67_1)) {
																													if(state.fixedFlag$sample57) {
																														{
																															for(int index$sample$114_1 = 0; index$sample$114_1 < state.noSamples; index$sample$114_1 += 1) {
																																if((index$sample$114_1 == sample$var196)) {
																																	if((0 == timeStep$var226)) {
																																		{
																																			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																					if((var146 == server)) {
																																						if((var156 == traceTempVariable$currentState$67_1)) {
																																							{
																																								{
																																									{
																																										// Constructing a random variable input for use later.
																																										double var241 = state.current_metric_mean[server][traceTempVariable$currentState$67_1];
																																										
																																										// Constructing a random variable input for use later.
																																										double var243 = state.current_metric_var[server][traceTempVariable$currentState$67_1];
																																										
																																										// Record the probability of sample task 256 generating output with current configuration.
																																										if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											// If the second value is -infinity.
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																													} else {
																														for(int index$sample$115 = 0; index$sample$115 < state.noSamples; index$sample$115 += 1) {
																															if(true) {
																																// Enumerating the possible outputs of Categorical 54.
																																for(int index$sample57$116 = 0; index$sample57$116 < state.noStates; index$sample57$116 += 1) {
																																	int distributionTempVariable$var55$118 = index$sample57$116;
																																	
																																	// Update the probability of sampling this value from the distribution value.
																																	double cv$probabilitySample57Value117 = (1.0 * state.distribution$sample57[((index$sample$115 - 0) / 1)][index$sample57$116]);
																																	{
																																		int traceTempVariable$currentState$119_1 = distributionTempVariable$var55$118;
																																		if((index$sample$115 == sample$var196)) {
																																			if((0 == timeStep$var226)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$119_1)) {
																																									{
																																										{
																																											{
																																												// Constructing a random variable input for use later.
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$119_1];
																																												
																																												// Constructing a random variable input for use later.
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$119_1];
																																												
																																												// Record the probability of sample task 256 generating output with current configuration.
																																												if(((Math.log(cv$probabilitySample57Value117) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value117) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													// If the second value is -infinity.
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value117) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value117) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value117) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																												}
																																												
																																												// Recorded the probability of reaching sample task 256 with the current configuration.
																																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value117);
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
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
																								{
																									for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																										for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																											if((var119 == server)) {
																												if((var129 == traceTempVariable$currentState$67_1)) {
																													{
																														int traceTempVariable$currentState$123_1 = cv$currentValue;
																														if((index$sample$2 == sample$var196)) {
																															if((index$timeStep$1 == timeStep$var226)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$123_1)) {
																																					{
																																						{
																																							{
																																								// Constructing a random variable input for use later.
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$123_1];
																																								
																																								// Constructing a random variable input for use later.
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$123_1];
																																								
																																								// Record the probability of sample task 256 generating output with current configuration.
																																								if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																													for(int index$sample$124 = 0; index$sample$124 < state.noSamples; index$sample$124 += 1) {
																														for(int index$timeStep$125 = 1; index$timeStep$125 < state.length$metric[index$sample$124][0]; index$timeStep$125 += 1) {
																															if(!((index$timeStep$125 == index$timeStep$1) && (index$sample$124 == index$sample$2))) {
																																// Enumerating the possible outputs of Categorical 73.
																																for(int index$sample76$126 = 0; index$sample76$126 < state.noStates; index$sample76$126 += 1) {
																																	int distributionTempVariable$var74$128 = index$sample76$126;
																																	
																																	// Update the probability of sampling this value from the distribution value.
																																	double cv$probabilitySample76Value127 = (1.0 * state.distribution$sample76[((index$sample$124 - 0) / 1)][((index$timeStep$125 - 1) / 1)][index$sample76$126]);
																																	{
																																		int traceTempVariable$currentState$129_1 = distributionTempVariable$var74$128;
																																		if((index$sample$124 == sample$var196)) {
																																			if((index$timeStep$125 == timeStep$var226)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$129_1)) {
																																									{
																																										{
																																											{
																																												// Constructing a random variable input for use later.
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$129_1];
																																												
																																												// Constructing a random variable input for use later.
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$129_1];
																																												
																																												// Record the probability of sample task 256 generating output with current configuration.
																																												if(((Math.log(cv$probabilitySample76Value127) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value127) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													// If the second value is -infinity.
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value127) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value127) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value127) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																												}
																																												
																																												// Recorded the probability of reaching sample task 256 with the current configuration.
																																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value127);
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
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
												{
													int traceTempVariable$currentState$71_1 = cv$currentValue;
													for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < state.noServers; server += 1) {
																		if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																			if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																				// The body will execute, so should not be executed again
																				guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																				
																				// Processing sample task 256 of consumer random variable null.
																				{
																					{
																						// Flag recording if this sample task of the consuming random variable is constrained.
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							// Mark that the sample has observed constrained data.
																							state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																							
																							// Set an accumulator to sum the probabilities for each possible configuration of
																							// inputs.
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							
																							// Set an accumulator to record the consumer distributions not seen. Initially set
																							// to 1 as seen values will be deducted from this value.
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																								// the output of Sample task 76.
																								if(state.fixedFlag$sample57) {
																									{
																										for(int index$sample$188_1 = 0; index$sample$188_1 < state.noSamples; index$sample$188_1 += 1) {
																											if((index$sample$188_1 == sample$var196)) {
																												if((0 == timeStep$var226)) {
																													{
																														for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																															for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																if((var119 == server)) {
																																	if((var129 == traceTempVariable$currentState$71_1)) {
																																		{
																																			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																					if((var146 == server)) {
																																						if((var156 == traceTempVariable$currentState$71_1)) {
																																							{
																																								{
																																									{
																																										// Constructing a random variable input for use later.
																																										double var241 = state.current_metric_mean[server][traceTempVariable$currentState$71_1];
																																										
																																										// Constructing a random variable input for use later.
																																										double var243 = state.current_metric_var[server][traceTempVariable$currentState$71_1];
																																										
																																										// Record the probability of sample task 256 generating output with current configuration.
																																										if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											// If the second value is -infinity.
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																											}
																										}
																									}
																								} else {
																									for(int index$sample$189 = 0; index$sample$189 < state.noSamples; index$sample$189 += 1) {
																										if(true) {
																											// Enumerating the possible outputs of Categorical 54.
																											for(int index$sample57$190 = 0; index$sample57$190 < state.noStates; index$sample57$190 += 1) {
																												int distributionTempVariable$var55$192 = index$sample57$190;
																												
																												// Update the probability of sampling this value from the distribution value.
																												double cv$probabilitySample57Value191 = (1.0 * state.distribution$sample57[((index$sample$189 - 0) / 1)][index$sample57$190]);
																												{
																													int traceTempVariable$currentState$193_1 = distributionTempVariable$var55$192;
																													if((index$sample$189 == sample$var196)) {
																														if((0 == timeStep$var226)) {
																															{
																																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																		if((var119 == server)) {
																																			if((var129 == traceTempVariable$currentState$193_1)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$193_1)) {
																																									{
																																										{
																																											{
																																												// Constructing a random variable input for use later.
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$193_1];
																																												
																																												// Constructing a random variable input for use later.
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$193_1];
																																												
																																												// Record the probability of sample task 256 generating output with current configuration.
																																												if(((Math.log(cv$probabilitySample57Value191) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value191) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													// If the second value is -infinity.
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value191) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value191) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value191) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																												}
																																												
																																												// Recorded the probability of reaching sample task 256 with the current configuration.
																																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value191);
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
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
																								{
																									int traceTempVariable$currentState$198_1 = cv$currentValue;
																									if((index$sample$2 == sample$var196)) {
																										if((index$timeStep$1 == timeStep$var226)) {
																											{
																												for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																													for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																														if((var119 == server)) {
																															if((var129 == traceTempVariable$currentState$198_1)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$198_1)) {
																																					{
																																						{
																																							{
																																								// Constructing a random variable input for use later.
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$198_1];
																																								
																																								// Constructing a random variable input for use later.
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$198_1];
																																								
																																								// Record the probability of sample task 256 generating output with current configuration.
																																								if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									// If the second value is -infinity.
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																									}
																								}
																								for(int index$sample$199 = 0; index$sample$199 < state.noSamples; index$sample$199 += 1) {
																									for(int index$timeStep$200 = 1; index$timeStep$200 < state.length$metric[index$sample$199][0]; index$timeStep$200 += 1) {
																										if(!((index$timeStep$200 == index$timeStep$1) && (index$sample$199 == index$sample$2))) {
																											// Enumerating the possible outputs of Categorical 73.
																											for(int index$sample76$201 = 0; index$sample76$201 < state.noStates; index$sample76$201 += 1) {
																												int distributionTempVariable$var74$203 = index$sample76$201;
																												
																												// Update the probability of sampling this value from the distribution value.
																												double cv$probabilitySample76Value202 = (1.0 * state.distribution$sample76[((index$sample$199 - 0) / 1)][((index$timeStep$200 - 1) / 1)][index$sample76$201]);
																												{
																													int traceTempVariable$currentState$204_1 = distributionTempVariable$var74$203;
																													if((index$sample$199 == sample$var196)) {
																														if((index$timeStep$200 == timeStep$var226)) {
																															{
																																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																		if((var119 == server)) {
																																			if((var129 == traceTempVariable$currentState$204_1)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$204_1)) {
																																									{
																																										{
																																											{
																																												// Constructing a random variable input for use later.
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$204_1];
																																												
																																												// Constructing a random variable input for use later.
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$204_1];
																																												
																																												// Record the probability of sample task 256 generating output with current configuration.
																																												if(((Math.log(cv$probabilitySample76Value202) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value202) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													// If the second value is -infinity.
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value202) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value202) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value202) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																												}
																																												
																																												// Recorded the probability of reaching sample task 256 with the current configuration.
																																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value202);
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
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
				for(int index$sample$29 = 0; index$sample$29 < state.noSamples; index$sample$29 += 1) {
					for(int index$timeStep$30 = 1; index$timeStep$30 < state.length$metric[index$sample$29][0]; index$timeStep$30 += 1) {
						if(!((index$timeStep$30 == index$timeStep$1) && (index$sample$29 == index$sample$2))) {
							// Enumerating the possible outputs of Categorical 73.
							for(int index$sample76$31 = 0; index$sample76$31 < state.noStates; index$sample76$31 += 1) {
								int distributionTempVariable$var74$33 = index$sample76$31;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample76Value32 = (1.0 * state.distribution$sample76[((index$sample$29 - 0) / 1)][((index$timeStep$30 - 1) / 1)][index$sample76$31]);
								{
									int traceTempVariable$var71$34_1 = distributionTempVariable$var74$33;
									if((index$sample$29 == sample$var45)) {
										if((index$timeStep$30 == (timeStep$var66 - 1))) {
											{
												for(int var32 = 0; var32 < state.noStates; var32 += 1) {
													if((var32 == traceTempVariable$var71$34_1)) {
														// Record the reached probability density.
														cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample76Value32);
														
														// Constructing a random variable input for use later.
														double[] var72 = state.m[traceTempVariable$var71$34_1];
														
														// An accumulator to allow the value for each distribution to be constructed before
														// it is added to the index probabilities.
														double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample76Value32) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$currentValue])) && (var72[cv$currentValue] <= 1.0))?Math.log(var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
														
														// Processing random variable 73.
														{
															// Looking for a path between Sample 76 and consumer Categorical 73.
															{
																{
																	int traceTempVariable$var71$40_1 = distributionTempVariable$var74$33;
																}
															}
														}
														
														// Processing random variable 231.
														{
															// Looking for a path between Sample 76 and consumer Bernoulli 231.
															{
																{
																	int traceTempVariable$currentState$44_1 = distributionTempVariable$var74$33;
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						// Flag recording if this sample task of the consuming random variable is constrained.
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							// Mark that the sample has observed constrained data.
																							state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																							
																							// Set an accumulator to sum the probabilities for each possible configuration of
																							// inputs.
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							
																							// Set an accumulator to record the consumer distributions not seen. Initially set
																							// to 1 as seen values will be deducted from this value.
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								// Enumerating the possible arguments for the variable Bernoulli 231 which is consuming
																								// the output of Sample task 76.
																								{
																									for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																										for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																											if((var173 == server)) {
																												if((var183 == traceTempVariable$currentState$44_1)) {
																													{
																														{
																															{
																																// Constructing a random variable input for use later.
																																double var230 = state.current_metric_valid_bias[server][traceTempVariable$currentState$44_1];
																																
																																// Record the probability of sample task 241 generating output with current configuration.
																																if(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)));
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
														
														// Processing random variable 244.
														{
															// Looking for a path between Sample 76 and consumer Gaussian 244.
															{
																// Guard to check that at most one copy of the code is executed for a given random
																// variable instance.
																boolean[][][] guard$sample76gaussian255 = scratch.guard$sample76gaussian255$global[threadID$cv$sample$var45];
																{
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																							// Set the flags to false
																							guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																					}
																				}
																			}
																		}
																	}
																}
																{
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																							// Set the flags to false
																							guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																					}
																				}
																			}
																		}
																	}
																}
																{
																	int traceTempVariable$currentState$68_1 = distributionTempVariable$var74$33;
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																							if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																								// The body will execute, so should not be executed again
																								guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																								
																								// Processing sample task 256 of consumer random variable null.
																								{
																									{
																										// Flag recording if this sample task of the consuming random variable is constrained.
																										boolean cv$sampleConstrained = true;
																										if(cv$sampleConstrained) {
																											// Mark that the sample has observed constrained data.
																											state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																											
																											// Set an accumulator to sum the probabilities for each possible configuration of
																											// inputs.
																											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																											
																											// Set an accumulator to record the consumer distributions not seen. Initially set
																											// to 1 as seen values will be deducted from this value.
																											double cv$consumerDistributionProbabilityAccumulator = 1.0;
																											{
																												// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																												// the output of Sample task 76.
																												{
																													for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																														for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																															if((var119 == server)) {
																																if((var129 == traceTempVariable$currentState$68_1)) {
																																	if(state.fixedFlag$sample57) {
																																		{
																																			for(int index$sample$133_1 = 0; index$sample$133_1 < state.noSamples; index$sample$133_1 += 1) {
																																				if((index$sample$133_1 == sample$var196)) {
																																					if((0 == timeStep$var226)) {
																																						{
																																							for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																								for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																									if((var146 == server)) {
																																										if((var156 == traceTempVariable$currentState$68_1)) {
																																											{
																																												{
																																													{
																																														// Constructing a random variable input for use later.
																																														double var241 = state.current_metric_mean[server][traceTempVariable$currentState$68_1];
																																														
																																														// Constructing a random variable input for use later.
																																														double var243 = state.current_metric_var[server][traceTempVariable$currentState$68_1];
																																														
																																														// Record the probability of sample task 256 generating output with current configuration.
																																														if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																														else {
																																															// If the second value is -infinity.
																																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																															else
																																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																																	} else {
																																		for(int index$sample$134 = 0; index$sample$134 < state.noSamples; index$sample$134 += 1) {
																																			if(true) {
																																				// Enumerating the possible outputs of Categorical 54.
																																				for(int index$sample57$135 = 0; index$sample57$135 < state.noStates; index$sample57$135 += 1) {
																																					int distributionTempVariable$var55$137 = index$sample57$135;
																																					
																																					// Update the probability of sampling this value from the distribution value.
																																					double cv$probabilitySample57Value136 = (1.0 * state.distribution$sample57[((index$sample$134 - 0) / 1)][index$sample57$135]);
																																					{
																																						int traceTempVariable$currentState$138_1 = distributionTempVariable$var55$137;
																																						if((index$sample$134 == sample$var196)) {
																																							if((0 == timeStep$var226)) {
																																								{
																																									for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																										for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																											if((var146 == server)) {
																																												if((var156 == traceTempVariable$currentState$138_1)) {
																																													{
																																														{
																																															{
																																																// Constructing a random variable input for use later.
																																																double var241 = state.current_metric_mean[server][traceTempVariable$currentState$138_1];
																																																
																																																// Constructing a random variable input for use later.
																																																double var243 = state.current_metric_var[server][traceTempVariable$currentState$138_1];
																																																
																																																// Record the probability of sample task 256 generating output with current configuration.
																																																if(((Math.log(cv$probabilitySample57Value136) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value136) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																																else {
																																																	// If the second value is -infinity.
																																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value136) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																	else
																																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value136) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value136) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																																}
																																																
																																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value136);
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
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
																												{
																													for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																														for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																															if((var119 == server)) {
																																if((var129 == traceTempVariable$currentState$68_1)) {
																																	{
																																		int traceTempVariable$currentState$142_1 = cv$currentValue;
																																		if((index$sample$2 == sample$var196)) {
																																			if((index$timeStep$1 == timeStep$var226)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$142_1)) {
																																									{
																																										{
																																											{
																																												// Constructing a random variable input for use later.
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$142_1];
																																												
																																												// Constructing a random variable input for use later.
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$142_1];
																																												
																																												// Record the probability of sample task 256 generating output with current configuration.
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													// If the second value is -infinity.
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																																	{
																																		int traceTempVariable$currentState$143_1 = distributionTempVariable$var74$33;
																																		if((index$sample$29 == sample$var196)) {
																																			if((index$timeStep$30 == timeStep$var226)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$143_1)) {
																																									{
																																										{
																																											{
																																												// Constructing a random variable input for use later.
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$143_1];
																																												
																																												// Constructing a random variable input for use later.
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$143_1];
																																												
																																												// Record the probability of sample task 256 generating output with current configuration.
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													// If the second value is -infinity.
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																																	for(int index$sample$144 = 0; index$sample$144 < state.noSamples; index$sample$144 += 1) {
																																		for(int index$timeStep$145 = 1; index$timeStep$145 < state.length$metric[index$sample$144][0]; index$timeStep$145 += 1) {
																																			if((!((index$timeStep$145 == index$timeStep$1) && (index$sample$144 == index$sample$2)) && !((index$timeStep$145 == index$timeStep$30) && (index$sample$144 == index$sample$29)))) {
																																				// Enumerating the possible outputs of Categorical 73.
																																				for(int index$sample76$146 = 0; index$sample76$146 < state.noStates; index$sample76$146 += 1) {
																																					int distributionTempVariable$var74$148 = index$sample76$146;
																																					
																																					// Update the probability of sampling this value from the distribution value.
																																					double cv$probabilitySample76Value147 = (1.0 * state.distribution$sample76[((index$sample$144 - 0) / 1)][((index$timeStep$145 - 1) / 1)][index$sample76$146]);
																																					{
																																						int traceTempVariable$currentState$149_1 = distributionTempVariable$var74$148;
																																						if((index$sample$144 == sample$var196)) {
																																							if((index$timeStep$145 == timeStep$var226)) {
																																								{
																																									for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																										for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																											if((var146 == server)) {
																																												if((var156 == traceTempVariable$currentState$149_1)) {
																																													{
																																														{
																																															{
																																																// Constructing a random variable input for use later.
																																																double var241 = state.current_metric_mean[server][traceTempVariable$currentState$149_1];
																																																
																																																// Constructing a random variable input for use later.
																																																double var243 = state.current_metric_var[server][traceTempVariable$currentState$149_1];
																																																
																																																// Record the probability of sample task 256 generating output with current configuration.
																																																if(((Math.log(cv$probabilitySample76Value147) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value147) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																																else {
																																																	// If the second value is -infinity.
																																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value147) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																	else
																																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value147) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value147) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																																}
																																																
																																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value147);
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
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
																{
																	int traceTempVariable$currentState$72_1 = distributionTempVariable$var74$33;
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																							if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																								// The body will execute, so should not be executed again
																								guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																								
																								// Processing sample task 256 of consumer random variable null.
																								{
																									{
																										// Flag recording if this sample task of the consuming random variable is constrained.
																										boolean cv$sampleConstrained = true;
																										if(cv$sampleConstrained) {
																											// Mark that the sample has observed constrained data.
																											state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																											
																											// Set an accumulator to sum the probabilities for each possible configuration of
																											// inputs.
																											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																											
																											// Set an accumulator to record the consumer distributions not seen. Initially set
																											// to 1 as seen values will be deducted from this value.
																											double cv$consumerDistributionProbabilityAccumulator = 1.0;
																											{
																												// Enumerating the possible arguments for the variable Gaussian 244 which is consuming
																												// the output of Sample task 76.
																												if(state.fixedFlag$sample57) {
																													{
																														for(int index$sample$209_1 = 0; index$sample$209_1 < state.noSamples; index$sample$209_1 += 1) {
																															if((index$sample$209_1 == sample$var196)) {
																																if((0 == timeStep$var226)) {
																																	{
																																		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																				if((var119 == server)) {
																																					if((var129 == traceTempVariable$currentState$72_1)) {
																																						{
																																							for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																								for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																									if((var146 == server)) {
																																										if((var156 == traceTempVariable$currentState$72_1)) {
																																											{
																																												{
																																													{
																																														// Constructing a random variable input for use later.
																																														double var241 = state.current_metric_mean[server][traceTempVariable$currentState$72_1];
																																														
																																														// Constructing a random variable input for use later.
																																														double var243 = state.current_metric_var[server][traceTempVariable$currentState$72_1];
																																														
																																														// Record the probability of sample task 256 generating output with current configuration.
																																														if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																														else {
																																															// If the second value is -infinity.
																																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																															else
																																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																															}
																														}
																													}
																												} else {
																													for(int index$sample$210 = 0; index$sample$210 < state.noSamples; index$sample$210 += 1) {
																														if(true) {
																															// Enumerating the possible outputs of Categorical 54.
																															for(int index$sample57$211 = 0; index$sample57$211 < state.noStates; index$sample57$211 += 1) {
																																int distributionTempVariable$var55$213 = index$sample57$211;
																																
																																// Update the probability of sampling this value from the distribution value.
																																double cv$probabilitySample57Value212 = (1.0 * state.distribution$sample57[((index$sample$210 - 0) / 1)][index$sample57$211]);
																																{
																																	int traceTempVariable$currentState$214_1 = distributionTempVariable$var55$213;
																																	if((index$sample$210 == sample$var196)) {
																																		if((0 == timeStep$var226)) {
																																			{
																																				for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																					for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																						if((var119 == server)) {
																																							if((var129 == traceTempVariable$currentState$214_1)) {
																																								{
																																									for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																										for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																											if((var146 == server)) {
																																												if((var156 == traceTempVariable$currentState$214_1)) {
																																													{
																																														{
																																															{
																																																// Constructing a random variable input for use later.
																																																double var241 = state.current_metric_mean[server][traceTempVariable$currentState$214_1];
																																																
																																																// Constructing a random variable input for use later.
																																																double var243 = state.current_metric_var[server][traceTempVariable$currentState$214_1];
																																																
																																																// Record the probability of sample task 256 generating output with current configuration.
																																																if(((Math.log(cv$probabilitySample57Value212) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value212) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																																else {
																																																	// If the second value is -infinity.
																																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value212) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																	else
																																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value212) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value212) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																																}
																																																
																																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value212);
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
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
																												{
																													int traceTempVariable$currentState$219_1 = cv$currentValue;
																													if((index$sample$2 == sample$var196)) {
																														if((index$timeStep$1 == timeStep$var226)) {
																															{
																																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																		if((var119 == server)) {
																																			if((var129 == traceTempVariable$currentState$219_1)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$219_1)) {
																																									{
																																										{
																																											{
																																												// Constructing a random variable input for use later.
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$219_1];
																																												
																																												// Constructing a random variable input for use later.
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$219_1];
																																												
																																												// Record the probability of sample task 256 generating output with current configuration.
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													// If the second value is -infinity.
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																													}
																												}
																												{
																													int traceTempVariable$currentState$220_1 = distributionTempVariable$var74$33;
																													if((index$sample$29 == sample$var196)) {
																														if((index$timeStep$30 == timeStep$var226)) {
																															{
																																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																		if((var119 == server)) {
																																			if((var129 == traceTempVariable$currentState$220_1)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$220_1)) {
																																									{
																																										{
																																											{
																																												// Constructing a random variable input for use later.
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$220_1];
																																												
																																												// Constructing a random variable input for use later.
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$220_1];
																																												
																																												// Record the probability of sample task 256 generating output with current configuration.
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													// If the second value is -infinity.
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																													}
																												}
																												for(int index$sample$221 = 0; index$sample$221 < state.noSamples; index$sample$221 += 1) {
																													for(int index$timeStep$222 = 1; index$timeStep$222 < state.length$metric[index$sample$221][0]; index$timeStep$222 += 1) {
																														if((!((index$timeStep$222 == index$timeStep$1) && (index$sample$221 == index$sample$2)) && !((index$timeStep$222 == index$timeStep$30) && (index$sample$221 == index$sample$29)))) {
																															// Enumerating the possible outputs of Categorical 73.
																															for(int index$sample76$223 = 0; index$sample76$223 < state.noStates; index$sample76$223 += 1) {
																																int distributionTempVariable$var74$225 = index$sample76$223;
																																
																																// Update the probability of sampling this value from the distribution value.
																																double cv$probabilitySample76Value224 = (1.0 * state.distribution$sample76[((index$sample$221 - 0) / 1)][((index$timeStep$222 - 1) / 1)][index$sample76$223]);
																																{
																																	int traceTempVariable$currentState$226_1 = distributionTempVariable$var74$225;
																																	if((index$sample$221 == sample$var196)) {
																																		if((index$timeStep$222 == timeStep$var226)) {
																																			{
																																				for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																					for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																						if((var119 == server)) {
																																							if((var129 == traceTempVariable$currentState$226_1)) {
																																								{
																																									for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																										for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																											if((var146 == server)) {
																																												if((var156 == traceTempVariable$currentState$226_1)) {
																																													{
																																														{
																																															{
																																																// Constructing a random variable input for use later.
																																																double var241 = state.current_metric_mean[server][traceTempVariable$currentState$226_1];
																																																
																																																// Constructing a random variable input for use later.
																																																double var243 = state.current_metric_var[server][traceTempVariable$currentState$226_1];
																																																
																																																// Record the probability of sample task 256 generating output with current configuration.
																																																if(((Math.log(cv$probabilitySample76Value224) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value224) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																																else {
																																																	// If the second value is -infinity.
																																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value224) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																	else
																																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value224) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value224) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																																}
																																																
																																																// Recorded the probability of reaching sample task 256 with the current configuration.
																																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value224);
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
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
					}
				}
				
				// Processing random variable 73.
				{
					// Looking for a path between Sample 76 and consumer Categorical 73.
					{
						{
							int traceTempVariable$var71$265_1 = cv$currentValue;
							for(int index$sample$265_2 = 0; index$sample$265_2 < state.noSamples; index$sample$265_2 += 1) {
								if((sample$var45 == index$sample$265_2)) {
									for(int index$timeStep$265_3 = 1; index$timeStep$265_3 < state.length$metric[index$sample$265_2][0]; index$timeStep$265_3 += 1) {
										if((timeStep$var66 == (index$timeStep$265_3 - 1))) {
											// Processing sample task 76 of consumer random variable null.
											{
												{
													// Copy of index so that its values can be safely substituted
													int index$timeStep$267 = index$timeStep$265_3;
													
													// Copy of index so that its values can be safely substituted
													int index$sample$268 = index$sample$265_2;
													
													// A local array to hold the accumulated distributions of the sample tasks for each
													// configuration of distributions.
													double[] cv$accumulatedConsumerDistributions = scratch.cv$distributionAccumulator$var73[threadID$cv$sample$var45];
													
													// Zero all the elements in the distribution accumulator
													for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
														cv$accumulatedConsumerDistributions[cv$i] = 0.0;
													
													// Zero an accumulator to track the probabilities reached.
													double cv$reachedDistributionProbability = 0.0;
													
													// Enumerating the possible arguments for the variable Categorical 73 which is consuming
													// the output of Sample task 76.
													{
														for(int var32 = 0; var32 < state.noStates; var32 += 1) {
															if((var32 == traceTempVariable$var71$265_1)) {
																{
																	// Declare and zero an accumulator for tracking the reached source probability space.
																	double scopeVariable$reachedSourceProbability = 0.0;
																	
																	// Enumerating the possible arguments for Categorical 73.
																	if(state.fixedFlag$sample57) {
																		{
																			for(int index$sample$270_1 = 0; index$sample$270_1 < state.noSamples; index$sample$270_1 += 1) {
																				if((index$sample$270_1 == sample$var45)) {
																					if((0 == (timeStep$var66 - 1))) {
																						{
																							for(int index$var32$276_1 = 0; index$var32$276_1 < state.noStates; index$var32$276_1 += 1) {
																								if((index$var32$276_1 == state.st[sample$var45][(timeStep$var66 - 1)]))
																									// Add the probability of this argument configuration.
																									scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																	} else {
																		for(int index$sample$271 = 0; index$sample$271 < state.noSamples; index$sample$271 += 1) {
																			if(true) {
																				// Enumerating the possible outputs of Categorical 54.
																				for(int index$sample57$272 = 0; index$sample57$272 < state.noStates; index$sample57$272 += 1) {
																					int distributionTempVariable$var55$274 = index$sample57$272;
																					
																					// Update the probability of sampling this value from the distribution value.
																					double cv$probabilitySample57Value273 = (1.0 * state.distribution$sample57[((index$sample$271 - 0) / 1)][index$sample57$272]);
																					{
																						int traceTempVariable$var71$275_1 = distributionTempVariable$var55$274;
																						if((index$sample$271 == sample$var45)) {
																							if((0 == (timeStep$var66 - 1))) {
																								{
																									for(int index$var32$277_1 = 0; index$var32$277_1 < state.noStates; index$var32$277_1 += 1) {
																										if((index$var32$277_1 == traceTempVariable$var71$275_1))
																											// Add the probability of this argument configuration.
																											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample57Value273);
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
																	{
																		int traceTempVariable$var71$278_1 = cv$currentValue;
																		if((index$sample$2 == sample$var45)) {
																			if((index$timeStep$1 == (timeStep$var66 - 1))) {
																				{
																					for(int index$var32$285_1 = 0; index$var32$285_1 < state.noStates; index$var32$285_1 += 1) {
																						if((index$var32$285_1 == traceTempVariable$var71$278_1))
																							// Add the probability of this argument configuration.
																							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																					}
																				}
																			}
																		}
																	}
																	for(int index$sample$279 = 0; index$sample$279 < state.noSamples; index$sample$279 += 1) {
																		for(int index$timeStep$280 = 1; index$timeStep$280 < state.length$metric[index$sample$279][0]; index$timeStep$280 += 1) {
																			if((!((index$timeStep$280 == index$timeStep$1) && (index$sample$279 == index$sample$2)) && !((index$timeStep$280 == index$timeStep$267) && (index$sample$279 == index$sample$268)))) {
																				// Enumerating the possible outputs of Categorical 73.
																				for(int index$sample76$281 = 0; index$sample76$281 < state.noStates; index$sample76$281 += 1) {
																					int distributionTempVariable$var74$283 = index$sample76$281;
																					
																					// Update the probability of sampling this value from the distribution value.
																					double cv$probabilitySample76Value282 = (1.0 * state.distribution$sample76[((index$sample$279 - 0) / 1)][((index$timeStep$280 - 1) / 1)][index$sample76$281]);
																					{
																						int traceTempVariable$var71$284_1 = distributionTempVariable$var74$283;
																						if((index$sample$279 == sample$var45)) {
																							if((index$timeStep$280 == (timeStep$var66 - 1))) {
																								{
																									for(int index$var32$286_1 = 0; index$var32$286_1 < state.noStates; index$var32$286_1 += 1) {
																										if((index$var32$286_1 == traceTempVariable$var71$284_1))
																											// Add the probability of this argument configuration.
																											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample76Value282);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	
																	// Constructing a random variable input for use later.
																	double[] var72 = state.m[traceTempVariable$var71$265_1];
																	
																	// The probability of reaching the consumer with this set of consumer arguments
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																	
																	// Record the reached distribution.
																	cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
																	
																	// Add the current distribution to the distribution accumulator.
																	DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var72, state.noStates);
																}
															}
														}
													}
													
													// A local copy of the samples' distribution.
													double[] cv$sampleDistribution = state.distribution$sample76[((index$sample$265_2 - 0) / 1)][((index$timeStep$265_3 - 1) / 1)];
													
													// The overlap of the distributions so far.
													double cv$overlap = 0.0;
													
													// Calculate the overlap for each element in the distribution
													for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
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
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)]) {
				// Set the calculated probabilities to be the distribution values, and normalize
				// Local copy of the probability array
				double[] cv$localProbability = state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)];
				
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialise the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
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
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
			}
		}
	}

	// Calculate the probability of the samples represented by sample241 using probability
	// distributions.
	private final void logProbabilityDistribution$sample241() {
		// Determine if we need to calculate the values for sample task 241 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample241) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						// Accumulator for sample probabilities for a specific instance of the random variable.
						double cv$sampleAccumulator = 0.0;
						
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 241 including any distribution
						// values.
						{
							{
								// The sample value to calculate the probability of generating
								boolean cv$sampleValue = state.metric_valid_g[sample$var196][server][timeStep$var226];
								
								// Enumerating the possible arguments for Bernoulli 231.
								if(state.fixedFlag$sample57) {
									{
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											if((sample$var45 == sample$var196)) {
												if((0 == timeStep$var226)) {
													{
														for(int var173 = 0; var173 < state.noServers; var173 += 1) {
															for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																if((var173 == server)) {
																	if((var183 == state.st[sample$var196][timeStep$var226])) {
																		{
																			double var230 = state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]];
																			
																			// Store the value of the function call, so the function call is only made once.
																			double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																			
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
									for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 54.
											for(int index$sample57$4 = 0; index$sample57$4 < state.noStates; index$sample57$4 += 1) {
												int distributionTempVariable$var55$6 = index$sample57$4;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample57Value5 = (1.0 * state.distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$4]);
												{
													int traceTempVariable$currentState$7_1 = distributionTempVariable$var55$6;
													if((sample$var45 == sample$var196)) {
														if((0 == timeStep$var226)) {
															{
																for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																	for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																		if((var173 == server)) {
																			if((var183 == traceTempVariable$currentState$7_1)) {
																				{
																					double var230 = state.current_metric_valid_bias[server][traceTempVariable$currentState$7_1];
																					
																					// Store the value of the function call, so the function call is only made once.
																					double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																					
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
									}
								}
								
								// Enumerating the possible arguments for Bernoulli 231.
								if(state.fixedFlag$sample76) {
									{
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
												if((sample$var45 == sample$var196)) {
													if((timeStep$var66 == timeStep$var226)) {
														{
															for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																	if((var173 == server)) {
																		if((var183 == state.st[sample$var196][timeStep$var226])) {
																			{
																				double var230 = state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]];
																				
																				// Store the value of the function call, so the function call is only made once.
																				double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																				
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
									for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
										for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 73.
												for(int index$sample76$13 = 0; index$sample76$13 < state.noStates; index$sample76$13 += 1) {
													int distributionTempVariable$var74$15 = index$sample76$13;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample76Value14 = (1.0 * state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$13]);
													{
														int traceTempVariable$currentState$16_1 = distributionTempVariable$var74$15;
														if((sample$var45 == sample$var196)) {
															if((timeStep$var66 == timeStep$var226)) {
																{
																	for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																		for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																			if((var173 == server)) {
																				if((var183 == traceTempVariable$currentState$16_1)) {
																					{
																						double var230 = state.current_metric_valid_bias[server][traceTempVariable$currentState$16_1];
																						
																						// Store the value of the function call, so the function call is only made once.
																						double cv$weightedProbability = (Math.log(cv$probabilitySample76Value14) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																						
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
						
						// Add the probability of this instance of the random variable to the probability
						// of all instances of the random variable.
						cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
						
						// Store the sample task probability
						state.logProbability$sample241[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			
			// Guard to ensure that metric_valid_g is only updated once for this probability.
			boolean cv$guard$metric_valid_g = false;
			
			// Update the variable probability
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				{
					// If the probability of the variable has not already been updated
					if(!cv$guard$metric_valid_g) {
						// Set the guard so the update is only applied once.
						cv$guard$metric_valid_g = true;
						
						// Update the variable probability
						state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
					}
				}
			}
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample241 = ((state.fixedFlag$sample57 && state.fixedFlag$sample76) && state.fixedFlag$sample190);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = state.logProbability$sample241[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						
						// Record that the sample was reached.
						cv$sampleReached = true;
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					}
				}
			}
			
			// Guard to ensure that metric_valid_g is only updated once for this probability.
			boolean cv$guard$metric_valid_g = false;
			
			// Update the variable probability
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				{
					// If the probability of the variable has not already been updated
					if(!cv$guard$metric_valid_g) {
						// Set the guard so the update is only applied once.
						cv$guard$metric_valid_g = true;
						
						// Update the variable probability
						state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
					}
				}
			}
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample256 using probability
	// distributions.
	private final void logProbabilityDistribution$sample256() {
		// Determine if we need to calculate the values for sample task 256 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample256) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
							// Accumulator for sample probabilities for a specific instance of the random variable.
							double cv$sampleAccumulator = 0.0;
							
							// An accumulator for log probabilities.
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							
							// An accumulator for the distributed probability space covered.
							double cv$probabilityReached = 0.0;
							
							// Look for paths between the variable and the sample task 256 including any distribution
							// values.
							{
								{
									// The sample value to calculate the probability of generating
									double cv$sampleValue = state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
									
									// Enumerating the possible arguments for Gaussian 244.
									if(state.fixedFlag$sample57) {
										{
											for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
												if((sample$var45 == sample$var196)) {
													if((0 == timeStep$var226)) {
														{
															for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																	if((var119 == server)) {
																		if((var129 == state.st[sample$var196][timeStep$var226])) {
																			{
																				for(int index$sample$10_1 = 0; index$sample$10_1 < state.noSamples; index$sample$10_1 += 1) {
																					if((index$sample$10_1 == sample$var196)) {
																						if((0 == timeStep$var226)) {
																							{
																								for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																									for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																										if((var146 == server)) {
																											if((var156 == state.st[sample$var196][timeStep$var226])) {
																												{
																													double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																													double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																													
																													// Store the value of the function call, so the function call is only made once.
																													double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																													
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
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 54.
												for(int index$sample57$4 = 0; index$sample57$4 < state.noStates; index$sample57$4 += 1) {
													int distributionTempVariable$var55$6 = index$sample57$4;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample57Value5 = (1.0 * state.distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$4]);
													{
														int traceTempVariable$currentState$7_1 = distributionTempVariable$var55$6;
														if((sample$var45 == sample$var196)) {
															if((0 == timeStep$var226)) {
																{
																	for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																		for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																			if((var119 == server)) {
																				if((var129 == traceTempVariable$currentState$7_1)) {
																					{
																						int traceTempVariable$currentState$11_1 = distributionTempVariable$var55$6;
																						if((sample$var45 == sample$var196)) {
																							if((0 == timeStep$var226)) {
																								{
																									for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																										for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																											if((var146 == server)) {
																												if((var156 == traceTempVariable$currentState$11_1)) {
																													{
																														double var241 = state.current_metric_mean[server][traceTempVariable$currentState$11_1];
																														double var243 = state.current_metric_var[server][traceTempVariable$currentState$11_1];
																														
																														// Store the value of the function call, so the function call is only made once.
																														double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																														
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
																					for(int index$sample$12 = 0; index$sample$12 < state.noSamples; index$sample$12 += 1) {
																						if(!(index$sample$12 == sample$var45)) {
																							// Enumerating the possible outputs of Categorical 54.
																							for(int index$sample57$13 = 0; index$sample57$13 < state.noStates; index$sample57$13 += 1) {
																								int distributionTempVariable$var55$15 = index$sample57$13;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample57Value14 = (cv$probabilitySample57Value5 * state.distribution$sample57[((index$sample$12 - 0) / 1)][index$sample57$13]);
																								{
																									int traceTempVariable$currentState$16_1 = distributionTempVariable$var55$15;
																									if((index$sample$12 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											{
																												for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																													for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == traceTempVariable$currentState$16_1)) {
																																{
																																	double var241 = state.current_metric_mean[server][traceTempVariable$currentState$16_1];
																																	double var243 = state.current_metric_var[server][traceTempVariable$currentState$16_1];
																																	
																																	// Store the value of the function call, so the function call is only made once.
																																	double cv$weightedProbability = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																	
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
												}
											}
										}
									}
									
									// Enumerating the possible arguments for Gaussian 244.
									if(state.fixedFlag$sample57) {
										{
											for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
												if((sample$var45 == sample$var196)) {
													if((0 == timeStep$var226)) {
														{
															for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																	if((var119 == server)) {
																		if((var129 == state.st[sample$var196][timeStep$var226])) {
																			if(state.fixedFlag$sample76) {
																				{
																					for(int index$sample$28_1 = 0; index$sample$28_1 < state.noSamples; index$sample$28_1 += 1) {
																						for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$28_1][0]; timeStep$var66 += 1) {
																							if((index$sample$28_1 == sample$var196)) {
																								if((timeStep$var66 == timeStep$var226)) {
																									{
																										for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																											for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																												if((var146 == server)) {
																													if((var156 == state.st[sample$var196][timeStep$var226])) {
																														{
																															double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																															double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																															
																															// Store the value of the function call, so the function call is only made once.
																															double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																															
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
																				for(int index$sample$29 = 0; index$sample$29 < state.noSamples; index$sample$29 += 1) {
																					for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$29][0]; timeStep$var66 += 1) {
																						if(true) {
																							// Enumerating the possible outputs of Categorical 73.
																							for(int index$sample76$31 = 0; index$sample76$31 < state.noStates; index$sample76$31 += 1) {
																								int distributionTempVariable$var74$33 = index$sample76$31;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample76Value32 = (1.0 * state.distribution$sample76[((index$sample$29 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$31]);
																								{
																									int traceTempVariable$currentState$34_1 = distributionTempVariable$var74$33;
																									if((index$sample$29 == sample$var196)) {
																										if((timeStep$var66 == timeStep$var226)) {
																											{
																												for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																													for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == traceTempVariable$currentState$34_1)) {
																																{
																																	double var241 = state.current_metric_mean[server][traceTempVariable$currentState$34_1];
																																	double var243 = state.current_metric_var[server][traceTempVariable$currentState$34_1];
																																	
																																	// Store the value of the function call, so the function call is only made once.
																																	double cv$weightedProbability = (Math.log(cv$probabilitySample76Value32) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																	
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
													}
												}
											}
										}
									} else {
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 54.
												for(int index$sample57$22 = 0; index$sample57$22 < state.noStates; index$sample57$22 += 1) {
													int distributionTempVariable$var55$24 = index$sample57$22;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample57Value23 = (1.0 * state.distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$22]);
													{
														int traceTempVariable$currentState$25_1 = distributionTempVariable$var55$24;
														if((sample$var45 == sample$var196)) {
															if((0 == timeStep$var226)) {
																{
																	for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																		for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																			if((var119 == server)) {
																				if((var129 == traceTempVariable$currentState$25_1)) {
																					if(state.fixedFlag$sample76) {
																						{
																							for(int index$sample$35_1 = 0; index$sample$35_1 < state.noSamples; index$sample$35_1 += 1) {
																								for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$35_1][0]; timeStep$var66 += 1) {
																									if((index$sample$35_1 == sample$var196)) {
																										if((timeStep$var66 == timeStep$var226)) {
																											{
																												for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																													for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == traceTempVariable$currentState$25_1)) {
																																{
																																	double var241 = state.current_metric_mean[server][traceTempVariable$currentState$25_1];
																																	double var243 = state.current_metric_var[server][traceTempVariable$currentState$25_1];
																																	
																																	// Store the value of the function call, so the function call is only made once.
																																	double cv$weightedProbability = (Math.log(cv$probabilitySample57Value23) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																	
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
																							}
																						}
																					} else {
																						for(int index$sample$36 = 0; index$sample$36 < state.noSamples; index$sample$36 += 1) {
																							for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$36][0]; timeStep$var66 += 1) {
																								if(true) {
																									// Enumerating the possible outputs of Categorical 73.
																									for(int index$sample76$38 = 0; index$sample76$38 < state.noStates; index$sample76$38 += 1) {
																										int distributionTempVariable$var74$40 = index$sample76$38;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample76Value39 = (cv$probabilitySample57Value23 * state.distribution$sample76[((index$sample$36 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$38]);
																										{
																											int traceTempVariable$currentState$41_1 = distributionTempVariable$var74$40;
																											if((index$sample$36 == sample$var196)) {
																												if((timeStep$var66 == timeStep$var226)) {
																													{
																														for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																															for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																if((var146 == server)) {
																																	if((var156 == traceTempVariable$currentState$41_1)) {
																																		{
																																			double var241 = state.current_metric_mean[server][traceTempVariable$currentState$41_1];
																																			double var243 = state.current_metric_var[server][traceTempVariable$currentState$41_1];
																																			
																																			// Store the value of the function call, so the function call is only made once.
																																			double cv$weightedProbability = (Math.log(cv$probabilitySample76Value39) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																			
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
												}
											}
										}
									}
									
									// Enumerating the possible arguments for Gaussian 244.
									if(state.fixedFlag$sample76) {
										{
											for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
												for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
													if((sample$var45 == sample$var196)) {
														if((timeStep$var66 == timeStep$var226)) {
															{
																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																		if((var119 == server)) {
																			if((var129 == state.st[sample$var196][timeStep$var226])) {
																				{
																					for(int index$sample$55_1 = 0; index$sample$55_1 < state.noSamples; index$sample$55_1 += 1) {
																						for(int index$timeStep$55_2 = 1; index$timeStep$55_2 < state.length$metric[index$sample$55_1][0]; index$timeStep$55_2 += 1) {
																							if((index$sample$55_1 == sample$var196)) {
																								if((index$timeStep$55_2 == timeStep$var226)) {
																									{
																										for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																											for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																												if((var146 == server)) {
																													if((var156 == state.st[sample$var196][timeStep$var226])) {
																														{
																															double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																															double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																															
																															// Store the value of the function call, so the function call is only made once.
																															double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																															
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
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
												if(true) {
													// Enumerating the possible outputs of Categorical 73.
													for(int index$sample76$49 = 0; index$sample76$49 < state.noStates; index$sample76$49 += 1) {
														int distributionTempVariable$var74$51 = index$sample76$49;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample76Value50 = (1.0 * state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$49]);
														{
															int traceTempVariable$currentState$52_1 = distributionTempVariable$var74$51;
															if((sample$var45 == sample$var196)) {
																if((timeStep$var66 == timeStep$var226)) {
																	{
																		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																				if((var119 == server)) {
																					if((var129 == traceTempVariable$currentState$52_1)) {
																						{
																							int traceTempVariable$currentState$56_1 = distributionTempVariable$var74$51;
																							if((sample$var45 == sample$var196)) {
																								if((timeStep$var66 == timeStep$var226)) {
																									{
																										for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																											for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																												if((var146 == server)) {
																													if((var156 == traceTempVariable$currentState$56_1)) {
																														{
																															double var241 = state.current_metric_mean[server][traceTempVariable$currentState$56_1];
																															double var243 = state.current_metric_var[server][traceTempVariable$currentState$56_1];
																															
																															// Store the value of the function call, so the function call is only made once.
																															double cv$weightedProbability = (Math.log(cv$probabilitySample76Value50) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																															
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
																							}
																						}
																						for(int index$sample$57 = 0; index$sample$57 < state.noSamples; index$sample$57 += 1) {
																							for(int index$timeStep$58 = 1; index$timeStep$58 < state.length$metric[index$sample$57][0]; index$timeStep$58 += 1) {
																								if(!((index$timeStep$58 == timeStep$var66) && (index$sample$57 == sample$var45))) {
																									// Enumerating the possible outputs of Categorical 73.
																									for(int index$sample76$59 = 0; index$sample76$59 < state.noStates; index$sample76$59 += 1) {
																										int distributionTempVariable$var74$61 = index$sample76$59;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample76Value60 = (cv$probabilitySample76Value50 * state.distribution$sample76[((index$sample$57 - 0) / 1)][((index$timeStep$58 - 1) / 1)][index$sample76$59]);
																										{
																											int traceTempVariable$currentState$62_1 = distributionTempVariable$var74$61;
																											if((index$sample$57 == sample$var196)) {
																												if((index$timeStep$58 == timeStep$var226)) {
																													{
																														for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																															for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																if((var146 == server)) {
																																	if((var156 == traceTempVariable$currentState$62_1)) {
																																		{
																																			double var241 = state.current_metric_mean[server][traceTempVariable$currentState$62_1];
																																			double var243 = state.current_metric_var[server][traceTempVariable$currentState$62_1];
																																			
																																			// Store the value of the function call, so the function call is only made once.
																																			double cv$weightedProbability = (Math.log(cv$probabilitySample76Value60) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																			
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
												}
											}
										}
									}
									
									// Enumerating the possible arguments for Gaussian 244.
									if(state.fixedFlag$sample76) {
										{
											for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
												for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
													if((sample$var45 == sample$var196)) {
														if((timeStep$var66 == timeStep$var226)) {
															{
																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																		if((var119 == server)) {
																			if((var129 == state.st[sample$var196][timeStep$var226])) {
																				if(state.fixedFlag$sample57) {
																					{
																						for(int index$sample$75_1 = 0; index$sample$75_1 < state.noSamples; index$sample$75_1 += 1) {
																							if((index$sample$75_1 == sample$var196)) {
																								if((0 == timeStep$var226)) {
																									{
																										for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																											for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																												if((var146 == server)) {
																													if((var156 == state.st[sample$var196][timeStep$var226])) {
																														{
																															double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																															double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																															
																															// Store the value of the function call, so the function call is only made once.
																															double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																															
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
																					for(int index$sample$76 = 0; index$sample$76 < state.noSamples; index$sample$76 += 1) {
																						if(true) {
																							// Enumerating the possible outputs of Categorical 54.
																							for(int index$sample57$77 = 0; index$sample57$77 < state.noStates; index$sample57$77 += 1) {
																								int distributionTempVariable$var55$79 = index$sample57$77;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample57Value78 = (1.0 * state.distribution$sample57[((index$sample$76 - 0) / 1)][index$sample57$77]);
																								{
																									int traceTempVariable$currentState$80_1 = distributionTempVariable$var55$79;
																									if((index$sample$76 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											{
																												for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																													for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == traceTempVariable$currentState$80_1)) {
																																{
																																	double var241 = state.current_metric_mean[server][traceTempVariable$currentState$80_1];
																																	double var243 = state.current_metric_var[server][traceTempVariable$currentState$80_1];
																																	
																																	// Store the value of the function call, so the function call is only made once.
																																	double cv$weightedProbability = (Math.log(cv$probabilitySample57Value78) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																	
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
													}
												}
											}
										}
									} else {
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
												if(true) {
													// Enumerating the possible outputs of Categorical 73.
													for(int index$sample76$69 = 0; index$sample76$69 < state.noStates; index$sample76$69 += 1) {
														int distributionTempVariable$var74$71 = index$sample76$69;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample76Value70 = (1.0 * state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$69]);
														{
															int traceTempVariable$currentState$72_1 = distributionTempVariable$var74$71;
															if((sample$var45 == sample$var196)) {
																if((timeStep$var66 == timeStep$var226)) {
																	{
																		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																				if((var119 == server)) {
																					if((var129 == traceTempVariable$currentState$72_1)) {
																						if(state.fixedFlag$sample57) {
																							{
																								for(int index$sample$81_1 = 0; index$sample$81_1 < state.noSamples; index$sample$81_1 += 1) {
																									if((index$sample$81_1 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											{
																												for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																													for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == traceTempVariable$currentState$72_1)) {
																																{
																																	double var241 = state.current_metric_mean[server][traceTempVariable$currentState$72_1];
																																	double var243 = state.current_metric_var[server][traceTempVariable$currentState$72_1];
																																	
																																	// Store the value of the function call, so the function call is only made once.
																																	double cv$weightedProbability = (Math.log(cv$probabilitySample76Value70) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																	
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
																								}
																							}
																						} else {
																							for(int index$sample$82 = 0; index$sample$82 < state.noSamples; index$sample$82 += 1) {
																								if(true) {
																									// Enumerating the possible outputs of Categorical 54.
																									for(int index$sample57$83 = 0; index$sample57$83 < state.noStates; index$sample57$83 += 1) {
																										int distributionTempVariable$var55$85 = index$sample57$83;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample57Value84 = (cv$probabilitySample76Value70 * state.distribution$sample57[((index$sample$82 - 0) / 1)][index$sample57$83]);
																										{
																											int traceTempVariable$currentState$86_1 = distributionTempVariable$var55$85;
																											if((index$sample$82 == sample$var196)) {
																												if((0 == timeStep$var226)) {
																													{
																														for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																															for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																if((var146 == server)) {
																																	if((var156 == traceTempVariable$currentState$86_1)) {
																																		{
																																			double var241 = state.current_metric_mean[server][traceTempVariable$currentState$86_1];
																																			double var243 = state.current_metric_var[server][traceTempVariable$currentState$86_1];
																																			
																																			// Store the value of the function call, so the function call is only made once.
																																			double cv$weightedProbability = (Math.log(cv$probabilitySample57Value84) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																			
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
							
							// Add the probability of this instance of the random variable to the probability
							// of all instances of the random variable.
							cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
							
							// Store the sample task probability
							state.logProbability$sample256[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleProbability;
						}
					}
				}
			}
			
			// Guard to ensure that metric_g is only updated once for this probability.
			boolean cv$guard$metric_g = false;
			
			// Update the variable probability
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				{
					// If the probability of the variable has not already been updated
					if(!cv$guard$metric_g) {
						// Set the guard so the update is only applied once.
						cv$guard$metric_g = true;
						
						// Update the variable probability
						state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
					}
				}
			}
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample256 = (((state.fixedFlag$sample57 && state.fixedFlag$sample76) && state.fixedFlag$sample134) && state.fixedFlag$sample162);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
							double cv$rvAccumulator = 0.0;
							double cv$sampleValue = state.logProbability$sample256[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
							cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
							
							// Record that the sample was reached.
							cv$sampleReached = true;
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						}
					}
				}
			}
			
			// Guard to ensure that metric_g is only updated once for this probability.
			boolean cv$guard$metric_g = false;
			
			// Update the variable probability
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				{
					// If the probability of the variable has not already been updated
					if(!cv$guard$metric_g) {
						// Set the guard so the update is only applied once.
						cv$guard$metric_g = true;
						
						// Update the variable probability
						state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
					}
				}
			}
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample57 using probability
	// distributions.
	private final void logProbabilityDistribution$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample57) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(state.fixedFlag$sample57) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				
				// A guard to check if the sample value is ever reached.
				boolean cv$sampleReached = false;
				for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Copy of index so that its values can be safely substituted
					int index$sample$1 = sample$var45;
					{
						{
							// The sample value to calculate the probability of generating
							int cv$sampleValue = state.st[sample$var45][0];
							{
								{
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$sampleValue])) && (state.initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									
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
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					
					// Store the sample task probability
					state.logProbability$sample57[((sample$var45 - 0) / 1)] = cv$sampleProbability;
				}
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(state.fixedFlag$sample57)
					// Update the variable probability
					state.logProbability$st = (state.logProbability$st + cv$accumulator);
				
				// Add probability to model
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(state.fixedFlag$sample57)
					state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				state.fixedProbFlag$sample57 = (state.fixedFlag$sample57 && state.fixedFlag$sample20);
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample57[((sample$var45 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(state.fixedFlag$sample57)
				// Update the variable probability
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample76 using probability
	// distributions.
	private final void logProbabilityDistribution$sample76() {
		// Determine if we need to calculate the values for sample task 76 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample76) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(state.fixedFlag$sample76) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				
				// A guard to check if the sample value is ever reached.
				boolean cv$sampleReached = false;
				for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
					for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
						// Accumulator for sample probabilities for a specific instance of the random variable.
						double cv$sampleAccumulator = 0.0;
						
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
							{
								// The sample value to calculate the probability of generating
								int cv$sampleValue = state.st[sample$var45][timeStep$var66];
								
								// Enumerating the possible arguments for Categorical 73.
								if(state.fixedFlag$sample57) {
									{
										for(int index$sample$4_1 = 0; index$sample$4_1 < state.noSamples; index$sample$4_1 += 1) {
											if((index$sample$4_1 == sample$var45)) {
												if((0 == (timeStep$var66 - 1))) {
													{
														for(int var32 = 0; var32 < state.noStates; var32 += 1) {
															if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
																{
																	double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
																	
																	// Store the value of the function call, so the function call is only made once.
																	double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	
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
									for(int index$sample$5 = 0; index$sample$5 < state.noSamples; index$sample$5 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 54.
											for(int index$sample57$6 = 0; index$sample57$6 < state.noStates; index$sample57$6 += 1) {
												int distributionTempVariable$var55$8 = index$sample57$6;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample57Value7 = (1.0 * state.distribution$sample57[((index$sample$5 - 0) / 1)][index$sample57$6]);
												{
													int traceTempVariable$var71$9_1 = distributionTempVariable$var55$8;
													if((index$sample$5 == sample$var45)) {
														if((0 == (timeStep$var66 - 1))) {
															{
																for(int var32 = 0; var32 < state.noStates; var32 += 1) {
																	if((var32 == traceTempVariable$var71$9_1)) {
																		{
																			double[] var72 = state.m[traceTempVariable$var71$9_1];
																			
																			// Store the value of the function call, so the function call is only made once.
																			double cv$weightedProbability = (Math.log(cv$probabilitySample57Value7) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																			
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
									}
								}
								
								// Enumerating the possible arguments for Categorical 73.
								{
									if((index$sample$2 == sample$var45)) {
										if((index$timeStep$1 == (timeStep$var66 - 1))) {
											{
												for(int var32 = 0; var32 < state.noStates; var32 += 1) {
													if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
														{
															double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
															
															// Store the value of the function call, so the function call is only made once.
															double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
															
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
								if(state.fixedFlag$sample76) {
									{
										for(int index$sample$13_1 = 0; index$sample$13_1 < state.noSamples; index$sample$13_1 += 1) {
											for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < state.length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
												if((index$sample$13_1 == sample$var45)) {
													if((index$timeStep$13_2 == (timeStep$var66 - 1))) {
														{
															for(int var32 = 0; var32 < state.noStates; var32 += 1) {
																if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
																	{
																		double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
																		
																		// Store the value of the function call, so the function call is only made once.
																		double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																		
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
									for(int index$sample$14 = 0; index$sample$14 < state.noSamples; index$sample$14 += 1) {
										for(int index$timeStep$15 = 1; index$timeStep$15 < state.length$metric[index$sample$14][0]; index$timeStep$15 += 1) {
											if(!((index$timeStep$15 == index$timeStep$1) && (index$sample$14 == index$sample$2))) {
												// Enumerating the possible outputs of Categorical 73.
												for(int index$sample76$16 = 0; index$sample76$16 < state.noStates; index$sample76$16 += 1) {
													int distributionTempVariable$var74$18 = index$sample76$16;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample76Value17 = (1.0 * state.distribution$sample76[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample76$16]);
													{
														int traceTempVariable$var71$19_1 = distributionTempVariable$var74$18;
														if((index$sample$14 == sample$var45)) {
															if((index$timeStep$15 == (timeStep$var66 - 1))) {
																{
																	for(int var32 = 0; var32 < state.noStates; var32 += 1) {
																		if((var32 == traceTempVariable$var71$19_1)) {
																			{
																				double[] var72 = state.m[traceTempVariable$var71$19_1];
																				
																				// Store the value of the function call, so the function call is only made once.
																				double cv$weightedProbability = (Math.log(cv$probabilitySample76Value17) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																				
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
						
						// Add the probability of this instance of the random variable to the probability
						// of all instances of the random variable.
						cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
						
						// Store the sample task probability
						state.logProbability$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = cv$sampleProbability;
					}
				}
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(state.fixedFlag$sample76)
					// Update the variable probability
					state.logProbability$st = (state.logProbability$st + cv$accumulator);
				
				// Add probability to model
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(state.fixedFlag$sample76)
					state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				state.fixedProbFlag$sample76 = ((state.fixedFlag$sample76 && state.fixedFlag$sample33) && state.fixedFlag$sample57);
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(state.fixedFlag$sample76)
				// Update the variable probability
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample76)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample134 using sampled
	// values.
	private final void logProbabilityValue$sample134() {
		// Determine if we need to calculate the values for sample task 134 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample134) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				for(int var129 = 0; var129 < state.noStates; var129 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						{
							// The sample value to calculate the probability of generating
							double cv$sampleValue = state.current_metric_mean[var119][var129];
							{
								{
									double var106 = 0.0;
									double var107 = (double)state.max_metric;
									
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
			
			// Store the random variable instance probability
			state.logProbability$var130 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$current_metric_mean = (state.logProbability$current_metric_mean + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample134)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample134 = state.fixedFlag$sample134;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				for(int var129 = 0; var129 < state.noStates; var129 += 1)
					// Record that the sample was reached.
					cv$sampleReached = true;
			}
			double cv$sampleValue = state.logProbability$var130;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$current_metric_mean = (state.logProbability$current_metric_mean + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample134)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample162 using sampled
	// values.
	private final void logProbabilityValue$sample162() {
		// Determine if we need to calculate the values for sample task 162 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample162) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						{
							// The sample value to calculate the probability of generating
							double cv$sampleValue = state.current_metric_var[var146][var156];
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
			
			// Store the random variable instance probability
			state.logProbability$var157 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$current_metric_var = (state.logProbability$current_metric_var + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample162)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample162 = state.fixedFlag$sample162;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				for(int var156 = 0; var156 < state.noStates; var156 += 1)
					// Record that the sample was reached.
					cv$sampleReached = true;
			}
			double cv$sampleValue = state.logProbability$var157;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$current_metric_var = (state.logProbability$current_metric_var + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample162)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample190 using sampled
	// values.
	private final void logProbabilityValue$sample190() {
		// Determine if we need to calculate the values for sample task 190 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample190) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				for(int var183 = 0; var183 < state.noStates; var183 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						{
							// The sample value to calculate the probability of generating
							double cv$sampleValue = state.current_metric_valid_bias[var173][var183];
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
			
			// Store the random variable instance probability
			state.logProbability$var184 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$current_metric_valid_bias = (state.logProbability$current_metric_valid_bias + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample190)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample190 = state.fixedFlag$sample190;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				for(int var183 = 0; var183 < state.noStates; var183 += 1)
					// Record that the sample was reached.
					cv$sampleReached = true;
			}
			double cv$sampleValue = state.logProbability$var184;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$current_metric_valid_bias = (state.logProbability$current_metric_valid_bias + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample190)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample20 using sampled
	// values.
	private final void logProbabilityValue$sample20() {
		// Determine if we need to calculate the values for sample task 20 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample20) {
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
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = state.initialStateDistribution;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.v, state.noStates));
							
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
			
			// Store the sample task probability
			state.logProbability$initialStateDistribution = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample20)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample20 = state.fixedFlag$sample20;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample20)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample241 using sampled
	// values.
	private final void logProbabilityValue$sample241() {
		// Determine if we need to calculate the values for sample task 241 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample241) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						// Accumulator for sample probabilities for a specific instance of the random variable.
						double cv$sampleAccumulator = 0.0;
						
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						{
							{
								// The sample value to calculate the probability of generating
								boolean cv$sampleValue = state.metric_valid_g[sample$var196][server][timeStep$var226];
								{
									{
										double var230 = state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
										
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
						
						// Add the probability of this instance of the random variable to the probability
						// of all instances of the random variable.
						cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
						
						// Store the sample task probability
						state.logProbability$sample241[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			
			// Guard to ensure that metric_valid_g is only updated once for this probability.
			boolean cv$guard$metric_valid_g = false;
			
			// Update the variable probability
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				{
					// If the probability of the variable has not already been updated
					if(!cv$guard$metric_valid_g) {
						// Set the guard so the update is only applied once.
						cv$guard$metric_valid_g = true;
						
						// Update the variable probability
						state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
					}
				}
			}
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample241 = ((state.fixedFlag$sample57 && state.fixedFlag$sample76) && state.fixedFlag$sample190);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = state.logProbability$sample241[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						
						// Record that the sample was reached.
						cv$sampleReached = true;
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					}
				}
			}
			
			// Guard to ensure that metric_valid_g is only updated once for this probability.
			boolean cv$guard$metric_valid_g = false;
			
			// Update the variable probability
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				{
					// If the probability of the variable has not already been updated
					if(!cv$guard$metric_valid_g) {
						// Set the guard so the update is only applied once.
						cv$guard$metric_valid_g = true;
						
						// Update the variable probability
						state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
					}
				}
			}
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample256 using sampled
	// values.
	private final void logProbabilityValue$sample256() {
		// Determine if we need to calculate the values for sample task 256 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample256) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
							// Accumulator for sample probabilities for a specific instance of the random variable.
							double cv$sampleAccumulator = 0.0;
							
							// An accumulator for log probabilities.
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							
							// An accumulator for the distributed probability space covered.
							double cv$probabilityReached = 0.0;
							{
								{
									// The sample value to calculate the probability of generating
									double cv$sampleValue = state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
									{
										{
											double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
											double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
											
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
							
							// Add the probability of this instance of the random variable to the probability
							// of all instances of the random variable.
							cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
							
							// Store the sample task probability
							state.logProbability$sample256[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleProbability;
						}
					}
				}
			}
			
			// Guard to ensure that metric_g is only updated once for this probability.
			boolean cv$guard$metric_g = false;
			
			// Update the variable probability
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				{
					// If the probability of the variable has not already been updated
					if(!cv$guard$metric_g) {
						// Set the guard so the update is only applied once.
						cv$guard$metric_g = true;
						
						// Update the variable probability
						state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
					}
				}
			}
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample256 = (((state.fixedFlag$sample57 && state.fixedFlag$sample76) && state.fixedFlag$sample134) && state.fixedFlag$sample162);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
							double cv$rvAccumulator = 0.0;
							double cv$sampleValue = state.logProbability$sample256[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
							cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
							
							// Record that the sample was reached.
							cv$sampleReached = true;
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						}
					}
				}
			}
			
			// Guard to ensure that metric_g is only updated once for this probability.
			boolean cv$guard$metric_g = false;
			
			// Update the variable probability
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				{
					// If the probability of the variable has not already been updated
					if(!cv$guard$metric_g) {
						// Set the guard so the update is only applied once.
						cv$guard$metric_g = true;
						
						// Update the variable probability
						state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
					}
				}
			}
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample33 using sampled
	// values.
	private final void logProbabilityValue$sample33() {
		// Determine if we need to calculate the values for sample task 33 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample33) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var32 = 0; var32 < state.noStates; var32 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double[] cv$sampleValue = state.m[var32];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.v, state.noStates));
								
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
			
			// Store the random variable instance probability
			state.logProbability$var33 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample33)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample33 = state.fixedFlag$sample33;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var32 = 0; var32 < state.noStates; var32 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var33;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample33)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample57 using sampled
	// values.
	private final void logProbabilityValue$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample57) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Copy of index so that its values can be safely substituted
				int index$sample$1 = sample$var45;
				{
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = state.st[sample$var45][0];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$sampleValue])) && (state.initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample57[((sample$var45 - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample57 = (state.fixedFlag$sample57 && state.fixedFlag$sample20);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample57[((sample$var45 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample76 using sampled
	// values.
	private final void logProbabilityValue$sample76() {
		// Determine if we need to calculate the values for sample task 76 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample76) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Copy of index so that its values can be safely substituted
					int index$timeStep$1 = timeStep$var66;
					
					// Copy of index so that its values can be safely substituted
					int index$sample$2 = sample$var45;
					{
						{
							// The sample value to calculate the probability of generating
							int cv$sampleValue = state.st[sample$var45][timeStep$var66];
							{
								{
									double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									
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
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					
					// Store the sample task probability
					state.logProbability$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample76)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample76 = ((state.fixedFlag$sample76 && state.fixedFlag$sample33) && state.fixedFlag$sample57);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample76)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
						double[] var33 = state.m[var32];
						if(!state.fixedFlag$sample33)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var33);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
						int[] var52 = state.st[sample$var45];
						if(!state.fixedFlag$sample57)
							var52[0] = DistributionSampling.sampleCategorical(RNG$1, state.initialStateDistribution, state.noStates);
						int[] var67 = state.st[sample$var45];
						for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(!state.fixedFlag$sample76)
								var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$var119, int forEnd$var119, int threadID$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var119 = forStart$var119; var119 < forEnd$var119; var119 += 1) {
						double[] var120 = state.current_metric_mean[var119];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1) {
										if(!state.fixedFlag$sample134)
											var120[var129] = (0.0 + (((double)state.max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$2)));
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$var146, int forEnd$var146, int threadID$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var146 = forStart$var146; var146 < forEnd$var146; var146 += 1) {
						double[] var147 = state.current_metric_var[var146];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1) {
										if(!state.fixedFlag$sample162)
											var147[var156] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$var173, int forEnd$var173, int threadID$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var173 = forStart$var173; var173 < forEnd$var173; var173 += 1) {
						double[] var174 = state.current_metric_valid_bias[var173];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1) {
										if(!state.fixedFlag$sample190)
											var174[var183] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$index$sample$var196, int forEnd$index$sample$var196, int threadID$index$sample$var196, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$sample$var196 = forStart$index$sample$var196; index$sample$var196 < forEnd$index$sample$var196; index$sample$var196 += 1) {
						int sample$var196 = index$sample$var196;
						int threadID$sample$var196 = threadID$index$sample$var196;
						boolean[][] var215 = state.metric_valid_g[sample$var196];
						double[][] var211 = state.metric_g[sample$var196];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noServers, 1,
							(int forStart$index$server, int forEnd$index$server, int threadID$index$server, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$server = forStart$index$server; index$server < forEnd$index$server; index$server += 1) {
										int server = index$server;
										int threadID$server = threadID$index$server;
										boolean[] metric_valid_inner = state.metric_valid_g[sample$var196][server];
										double[] metric_inner = var211[server];
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, state.length$metric[sample$var196][0], 1,
											(int forStart$timeStep$var226, int forEnd$timeStep$var226, int threadID$timeStep$var226, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int timeStep$var226 = forStart$timeStep$var226; timeStep$var226 < forEnd$timeStep$var226; timeStep$var226 += 1) {
														metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(RNG$3, state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]]);
														if(metric_valid_inner[timeStep$var226]) {
															state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = ((Math.sqrt(state.current_metric_var[server][state.st[sample$var196][timeStep$var226]]) * DistributionSampling.sampleGaussian(RNG$3)) + state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]]);
															metric_inner[timeStep$var226] = state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
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
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
						double[] var33 = state.m[var32];
						if(!state.fixedFlag$sample33)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var33);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
						// Create local copy of variable probabilities.
						double[] cv$distribution$sample57 = state.distribution$sample57[((sample$var45 - 0) / 1)];
						for(int index$var54 = 0; index$var54 < state.noStates; index$var54 += 1) {
							// Probability for this value
							double cv$value = ((((((0.0 <= index$var54) && (index$var54 < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[index$var54])) && (state.initialStateDistribution[index$var54] <= 1.0))?state.initialStateDistribution[index$var54]:0.0);
							if(!state.fixedFlag$sample57)
								// Save the probability of each value
								cv$distribution$sample57[index$var54] = cv$value;
						}
						for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
							// Create local copy of variable probabilities.
							double[] cv$distribution$sample76 = state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)];
							for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1) {
								if(!state.fixedFlag$sample76)
									// Zero the probability of each value
									cv$distribution$sample76[index$var73] = 0.0;
							}
							
							// Iterate through possible values for var73's arguments.
							// 
							// Enumerating the possible arguments for Categorical 73.
							if(state.fixedFlag$sample57) {
								{
									for(int index$sample$1_1 = 0; index$sample$1_1 < state.noSamples; index$sample$1_1 += 1) {
										if((index$sample$1_1 == sample$var45)) {
											if((0 == (timeStep$var66 - 1))) {
												{
													for(int var32 = 0; var32 < state.noStates; var32 += 1) {
														if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
															{
																double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
																for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1) {
																	if(!state.fixedFlag$sample76)
																		// Save the probability of each value
																		cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (1.0 * ((((((0.0 <= index$var73) && (index$var73 < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[index$var73])) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0)));
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
								for(int index$sample$2 = 0; index$sample$2 < state.noSamples; index$sample$2 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 54.
										for(int index$sample57$3 = 0; index$sample57$3 < state.noStates; index$sample57$3 += 1) {
											int distributionTempVariable$var55$5 = index$sample57$3;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample57Value4 = (1.0 * state.distribution$sample57[((index$sample$2 - 0) / 1)][index$sample57$3]);
											{
												int traceTempVariable$var71$6_1 = distributionTempVariable$var55$5;
												if((index$sample$2 == sample$var45)) {
													if((0 == (timeStep$var66 - 1))) {
														{
															for(int var32 = 0; var32 < state.noStates; var32 += 1) {
																if((var32 == traceTempVariable$var71$6_1)) {
																	{
																		double[] var72 = state.m[traceTempVariable$var71$6_1];
																		for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1) {
																			if(!state.fixedFlag$sample76)
																				// Save the probability of each value
																				cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample57Value4 * ((((((0.0 <= index$var73) && (index$var73 < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[index$var73])) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0)));
																		}
																	}
																}
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
							if(state.fixedFlag$sample76) {
								{
									for(int index$sample$9_1 = 0; index$sample$9_1 < state.noSamples; index$sample$9_1 += 1) {
										for(int index$timeStep$9_2 = 1; index$timeStep$9_2 < state.length$metric[index$sample$9_1][0]; index$timeStep$9_2 += 1) {
											if((index$sample$9_1 == sample$var45)) {
												if((index$timeStep$9_2 == (timeStep$var66 - 1))) {
													{
														for(int var32 = 0; var32 < state.noStates; var32 += 1) {
															if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
																{
																	double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
																	for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1) {
																		if(!state.fixedFlag$sample76)
																			// Save the probability of each value
																			cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (1.0 * ((((((0.0 <= index$var73) && (index$var73 < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[index$var73])) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0)));
																	}
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
								for(int index$sample$10 = 0; index$sample$10 < state.noSamples; index$sample$10 += 1) {
									for(int index$timeStep$11 = 1; index$timeStep$11 < state.length$metric[index$sample$10][0]; index$timeStep$11 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 73.
											for(int index$sample76$12 = 0; index$sample76$12 < state.noStates; index$sample76$12 += 1) {
												int distributionTempVariable$var74$14 = index$sample76$12;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample76Value13 = (1.0 * state.distribution$sample76[((index$sample$10 - 0) / 1)][((index$timeStep$11 - 1) / 1)][index$sample76$12]);
												{
													int traceTempVariable$var71$15_1 = distributionTempVariable$var74$14;
													if((index$sample$10 == sample$var45)) {
														if((index$timeStep$11 == (timeStep$var66 - 1))) {
															{
																for(int var32 = 0; var32 < state.noStates; var32 += 1) {
																	if((var32 == traceTempVariable$var71$15_1)) {
																		{
																			double[] var72 = state.m[traceTempVariable$var71$15_1];
																			for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1) {
																				if(!state.fixedFlag$sample76)
																					// Save the probability of each value
																					cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample76Value13 * ((((((0.0 <= index$var73) && (index$var73 < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[index$var73])) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0)));
																			}
																		}
																	}
																}
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
							for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1) {
								if(!state.fixedFlag$sample76)
									// sum the probability of each value
									cv$var73$sum = (cv$var73$sum + cv$distribution$sample76[index$var73]);
							}
							for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1) {
								if(!state.fixedFlag$sample76)
									// Normalise the probability of each value
									cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] / cv$var73$sum);
							}
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$var119, int forEnd$var119, int threadID$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var119 = forStart$var119; var119 < forEnd$var119; var119 += 1) {
						double[] var120 = state.current_metric_mean[var119];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1) {
										if(!state.fixedFlag$sample134)
											var120[var129] = (0.0 + (((double)state.max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$2)));
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$var146, int forEnd$var146, int threadID$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var146 = forStart$var146; var146 < forEnd$var146; var146 += 1) {
						double[] var147 = state.current_metric_var[var146];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1) {
										if(!state.fixedFlag$sample162)
											var147[var156] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$var173, int forEnd$var173, int threadID$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var173 = forStart$var173; var173 < forEnd$var173; var173 += 1) {
						double[] var174 = state.current_metric_valid_bias[var173];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1) {
										if(!state.fixedFlag$sample190)
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
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
						double[] var33 = state.m[var32];
						if(!state.fixedFlag$sample33)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var33);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
						int[] var52 = state.st[sample$var45];
						if(!state.fixedFlag$sample57)
							var52[0] = DistributionSampling.sampleCategorical(RNG$1, state.initialStateDistribution, state.noStates);
						int[] var67 = state.st[sample$var45];
						for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(!state.fixedFlag$sample76)
								var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$var119, int forEnd$var119, int threadID$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var119 = forStart$var119; var119 < forEnd$var119; var119 += 1) {
						double[] var120 = state.current_metric_mean[var119];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1) {
										if(!state.fixedFlag$sample134)
											var120[var129] = (0.0 + (((double)state.max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$2)));
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$var146, int forEnd$var146, int threadID$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var146 = forStart$var146; var146 < forEnd$var146; var146 += 1) {
						double[] var147 = state.current_metric_var[var146];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1) {
										if(!state.fixedFlag$sample162)
											var147[var156] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$var173, int forEnd$var173, int threadID$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var173 = forStart$var173; var173 < forEnd$var173; var173 += 1) {
						double[] var174 = state.current_metric_valid_bias[var173];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1) {
										if(!state.fixedFlag$sample190)
											var174[var183] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$index$sample$var196, int forEnd$index$sample$var196, int threadID$index$sample$var196, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$sample$var196 = forStart$index$sample$var196; index$sample$var196 < forEnd$index$sample$var196; index$sample$var196 += 1) {
						int sample$var196 = index$sample$var196;
						int threadID$sample$var196 = threadID$index$sample$var196;
						boolean[][] var215 = state.metric_valid_g[sample$var196];
						double[][] var211 = state.metric_g[sample$var196];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noServers, 1,
							(int forStart$index$server, int forEnd$index$server, int threadID$index$server, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$server = forStart$index$server; index$server < forEnd$index$server; index$server += 1) {
										int server = index$server;
										int threadID$server = threadID$index$server;
										boolean[] metric_valid_inner = state.metric_valid_g[sample$var196][server];
										double[] metric_inner = var211[server];
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, state.length$metric[sample$var196][0], 1,
											(int forStart$timeStep$var226, int forEnd$timeStep$var226, int threadID$timeStep$var226, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int timeStep$var226 = forStart$timeStep$var226; timeStep$var226 < forEnd$timeStep$var226; timeStep$var226 += 1) {
														metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(RNG$3, state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]]);
														if(metric_valid_inner[timeStep$var226]) {
															state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = ((Math.sqrt(state.current_metric_var[server][state.st[sample$var196][timeStep$var226]]) * DistributionSampling.sampleGaussian(RNG$3)) + state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]]);
															metric_inner[timeStep$var226] = state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
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
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
						double[] var33 = state.m[var32];
						if(!state.fixedFlag$sample33)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var33);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
						int[] var52 = state.st[sample$var45];
						if(!state.fixedFlag$sample57)
							var52[0] = DistributionSampling.sampleCategorical(RNG$1, state.initialStateDistribution, state.noStates);
						int[] var67 = state.st[sample$var45];
						for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(!state.fixedFlag$sample76)
								var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$var119, int forEnd$var119, int threadID$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var119 = forStart$var119; var119 < forEnd$var119; var119 += 1) {
						double[] var120 = state.current_metric_mean[var119];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1) {
										if(!state.fixedFlag$sample134)
											var120[var129] = (0.0 + (((double)state.max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$2)));
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$var146, int forEnd$var146, int threadID$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var146 = forStart$var146; var146 < forEnd$var146; var146 += 1) {
						double[] var147 = state.current_metric_var[var146];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1) {
										if(!state.fixedFlag$sample162)
											var147[var156] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$var173, int forEnd$var173, int threadID$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var173 = forStart$var173; var173 < forEnd$var173; var173 += 1) {
						double[] var174 = state.current_metric_valid_bias[var173];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1) {
										if(!state.fixedFlag$sample190)
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
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
						double[] var33 = state.m[var32];
						if(!state.fixedFlag$sample33)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var33);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
						int[] var52 = state.st[sample$var45];
						if(!state.fixedFlag$sample57)
							var52[0] = DistributionSampling.sampleCategorical(RNG$1, state.initialStateDistribution, state.noStates);
						int[] var67 = state.st[sample$var45];
						for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(!state.fixedFlag$sample76)
								var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$var119, int forEnd$var119, int threadID$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var119 = forStart$var119; var119 < forEnd$var119; var119 += 1) {
						double[] var120 = state.current_metric_mean[var119];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1) {
										if(!state.fixedFlag$sample134)
											var120[var129] = (0.0 + (((double)state.max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$2)));
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$var146, int forEnd$var146, int threadID$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var146 = forStart$var146; var146 < forEnd$var146; var146 += 1) {
						double[] var147 = state.current_metric_var[var146];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1) {
										if(!state.fixedFlag$sample162)
											var147[var156] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$var173, int forEnd$var173, int threadID$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var173 = forStart$var173; var173 < forEnd$var173; var173 += 1) {
						double[] var174 = state.current_metric_valid_bias[var173];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1) {
										if(!state.fixedFlag$sample190)
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
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample20)
				inferSample20();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
							if(!state.fixedFlag$sample33)
								inferSample33(var32, threadID$var32, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noSamples, 1,
				(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
							if(!state.fixedFlag$sample57)
								inferSample57(sample$var45, threadID$sample$var45, RNG$1);
							for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
								if(!state.fixedFlag$sample76)
									inferSample76(sample$var45, timeStep$var66, threadID$sample$var45, RNG$1);
							}
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noServers, 1,
				(int forStart$index$var119, int forEnd$index$var119, int threadID$index$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$var119 = forStart$index$var119; index$var119 < forEnd$index$var119; index$var119 += 1) {
							int var119 = index$var119;
							int threadID$var119 = threadID$index$var119;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.noStates, 1,
								(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1) {
											if(!state.fixedFlag$sample134)
												inferSample134(var119, var129, threadID$var129, RNG$2);
										}
								}
							);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noServers, 1,
				(int forStart$index$var146, int forEnd$index$var146, int threadID$index$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$var146 = forStart$index$var146; index$var146 < forEnd$index$var146; index$var146 += 1) {
							int var146 = index$var146;
							int threadID$var146 = threadID$index$var146;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.noStates, 1,
								(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1) {
											if(!state.fixedFlag$sample162)
												inferSample162(var146, var156, threadID$var156, RNG$2);
										}
								}
							);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noServers, 1,
				(int forStart$index$var173, int forEnd$index$var173, int threadID$index$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$var173 = forStart$index$var173; index$var173 < forEnd$index$var173; index$var173 += 1) {
							int var173 = index$var173;
							int threadID$var173 = threadID$index$var173;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.noStates, 1,
								(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1) {
											if(!state.fixedFlag$sample190)
												inferSample190(var173, var183, threadID$var183, RNG$2);
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
			parallelFor(state.RNG$, 0, state.noServers, 1,
				(int forStart$index$var173, int forEnd$index$var173, int threadID$index$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$var173 = forStart$index$var173; index$var173 < forEnd$index$var173; index$var173 += 1) {
							int var173 = index$var173;
							int threadID$var173 = threadID$index$var173;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.noStates, 1,
								(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1) {
											if(!state.fixedFlag$sample190)
												inferSample190(var173, var183, threadID$var183, RNG$2);
										}
								}
							);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noServers, 1,
				(int forStart$index$var146, int forEnd$index$var146, int threadID$index$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$var146 = forStart$index$var146; index$var146 < forEnd$index$var146; index$var146 += 1) {
							int var146 = index$var146;
							int threadID$var146 = threadID$index$var146;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.noStates, 1,
								(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1) {
											if(!state.fixedFlag$sample162)
												inferSample162(var146, var156, threadID$var156, RNG$2);
										}
								}
							);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noServers, 1,
				(int forStart$index$var119, int forEnd$index$var119, int threadID$index$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$var119 = forStart$index$var119; index$var119 < forEnd$index$var119; index$var119 += 1) {
							int var119 = index$var119;
							int threadID$var119 = threadID$index$var119;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.noStates, 1,
								(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1) {
											if(!state.fixedFlag$sample134)
												inferSample134(var119, var129, threadID$var129, RNG$2);
										}
								}
							);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noSamples, 1,
				(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
							for(int timeStep$var66 = (state.length$metric[sample$var45][0] - ((((state.length$metric[sample$var45][0] - 1) - 1) % 1) + 1)); timeStep$var66 >= ((1 - 1) + 1); timeStep$var66 -= 1) {
								if(!state.fixedFlag$sample76)
									inferSample76(sample$var45, timeStep$var66, threadID$sample$var45, RNG$1);
							}
							if(!state.fixedFlag$sample57)
								inferSample57(sample$var45, threadID$sample$var45, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
							if(!state.fixedFlag$sample33)
								inferSample33(var32, threadID$var32, RNG$1);
						}
				}
			);
			if(!state.fixedFlag$sample20)
				inferSample20();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample20)
			drawValueSample20();
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
						if(!state.constrainedFlag$sample33[((var32 - 0) / 1)])
							drawValueSample33(var32, threadID$var32, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
						if(!state.constrainedFlag$sample57[((sample$var45 - 0) / 1)])
							drawValueSample57(sample$var45, threadID$sample$var45, RNG$1);
						for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(!state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)])
								drawValueSample76(sample$var45, timeStep$var66, threadID$sample$var45, RNG$1);
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$index$var119, int forEnd$index$var119, int threadID$index$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$var119 = forStart$index$var119; index$var119 < forEnd$index$var119; index$var119 += 1) {
						int var119 = index$var119;
						int threadID$var119 = threadID$index$var119;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1) {
										if(!state.constrainedFlag$sample134[((var119 - 0) / 1)][((var129 - 0) / 1)])
											drawValueSample134(var119, var129, threadID$var129, RNG$2);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$index$var146, int forEnd$index$var146, int threadID$index$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$var146 = forStart$index$var146; index$var146 < forEnd$index$var146; index$var146 += 1) {
						int var146 = index$var146;
						int threadID$var146 = threadID$index$var146;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1) {
										if(!state.constrainedFlag$sample162[((var146 - 0) / 1)][((var156 - 0) / 1)])
											drawValueSample162(var146, var156, threadID$var156, RNG$2);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noServers, 1,
			(int forStart$index$var173, int forEnd$index$var173, int threadID$index$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$var173 = forStart$index$var173; index$var173 < forEnd$index$var173; index$var173 += 1) {
						int var173 = index$var173;
						int threadID$var173 = threadID$index$var173;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noStates, 1,
							(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1) {
										if(!state.constrainedFlag$sample190[((var173 - 0) / 1)][((var183 - 0) / 1)])
											drawValueSample190(var173, var183, threadID$var183, RNG$2);
									}
							}
						);
					}
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
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample20)
			state.logProbability$initialStateDistribution = Double.NaN;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample33)
			state.logProbability$var33 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample57) {
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1)
				state.logProbability$sample57[((sample$var45 - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample76) {
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					state.logProbability$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = Double.NaN;
			}
		}
		state.logProbability$current_metric_mean = 0.0;
		if(!state.fixedProbFlag$sample134)
			state.logProbability$var130 = Double.NaN;
		state.logProbability$current_metric_var = 0.0;
		if(!state.fixedProbFlag$sample162)
			state.logProbability$var157 = Double.NaN;
		state.logProbability$current_metric_valid_bias = 0.0;
		if(!state.fixedProbFlag$sample190)
			state.logProbability$var184 = Double.NaN;
		state.logProbability$metric_valid_inner = 0.0;
		state.logProbability$metric_valid_g = 0.0;
		if(!state.fixedProbFlag$sample241) {
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1)
						state.logProbability$sample241[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = Double.NaN;
				}
			}
		}
		state.logProbability$var245 = 0.0;
		state.logProbability$metric_g = 0.0;
		if(!state.fixedProbFlag$sample256) {
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1)
						state.logProbability$sample256[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = Double.NaN;
				}
			}
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.noSamples = state.length$metric.length;
		for(int var16 = 0; var16 < state.noStates; var16 += 1)
			state.v[var16] = 0.1;
		state.noServers = state.length$metric[0].length;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample190$1 = 0; index$constrainedFlag$sample190$1 < state.constrainedFlag$sample190.length; index$constrainedFlag$sample190$1 += 1) {
			boolean[] cv$constrainedFlag$sample190$1 = state.constrainedFlag$sample190[index$constrainedFlag$sample190$1];
			for(int index$constrainedFlag$sample190$2 = 0; index$constrainedFlag$sample190$2 < cv$constrainedFlag$sample190$1.length; index$constrainedFlag$sample190$2 += 1)
				cv$constrainedFlag$sample190$1[index$constrainedFlag$sample190$2] = true;
		}
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample76$1 = 0; index$constrainedFlag$sample76$1 < state.constrainedFlag$sample76.length; index$constrainedFlag$sample76$1 += 1) {
			boolean[] cv$constrainedFlag$sample76$1 = state.constrainedFlag$sample76[index$constrainedFlag$sample76$1];
			for(int index$constrainedFlag$sample76$2 = 0; index$constrainedFlag$sample76$2 < cv$constrainedFlag$sample76$1.length; index$constrainedFlag$sample76$2 += 1)
				cv$constrainedFlag$sample76$1[index$constrainedFlag$sample76$2] = true;
		}
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < state.constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
			state.constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample134$1 = 0; index$constrainedFlag$sample134$1 < state.constrainedFlag$sample134.length; index$constrainedFlag$sample134$1 += 1) {
			boolean[] cv$constrainedFlag$sample134$1 = state.constrainedFlag$sample134[index$constrainedFlag$sample134$1];
			for(int index$constrainedFlag$sample134$2 = 0; index$constrainedFlag$sample134$2 < cv$constrainedFlag$sample134$1.length; index$constrainedFlag$sample134$2 += 1)
				cv$constrainedFlag$sample134$1[index$constrainedFlag$sample134$2] = true;
		}
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample162$1 = 0; index$constrainedFlag$sample162$1 < state.constrainedFlag$sample162.length; index$constrainedFlag$sample162$1 += 1) {
			boolean[] cv$constrainedFlag$sample162$1 = state.constrainedFlag$sample162[index$constrainedFlag$sample162$1];
			for(int index$constrainedFlag$sample162$2 = 0; index$constrainedFlag$sample162$2 < cv$constrainedFlag$sample162$1.length; index$constrainedFlag$sample162$2 += 1)
				cv$constrainedFlag$sample162$1[index$constrainedFlag$sample162$2] = true;
		}
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample33$1 = 0; index$constrainedFlag$sample33$1 < state.constrainedFlag$sample33.length; index$constrainedFlag$sample33$1 += 1)
			state.constrainedFlag$sample33[index$constrainedFlag$sample33$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(state.fixedFlag$sample33)
			logProbabilityValue$sample33();
		if(state.fixedFlag$sample134)
			logProbabilityValue$sample134();
		if(state.fixedFlag$sample162)
			logProbabilityValue$sample162();
		if(state.fixedFlag$sample190)
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
			boolean[][][] cv$source1 = state.metric_valid;
			boolean[][][] cv$target1 = state.metric_valid_g;
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
			double[][][] cv$source1 = state.metric;
			double[][][] cv$target1 = state.metric_g;
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
		for(int sample$var196 = (state.noSamples - ((((state.noSamples - 1) - 0) % 1) + 1)); sample$var196 >= ((0 - 1) + 1); sample$var196 -= 1) {
			for(int server = (state.noServers - ((((state.noServers - 1) - 0) % 1) + 1)); server >= ((0 - 1) + 1); server -= 1) {
				for(int timeStep$var226 = (state.length$metric[sample$var196][0] - ((((state.length$metric[sample$var196][0] - 1) - 0) % 1) + 1)); timeStep$var226 >= ((0 - 1) + 1); timeStep$var226 -= 1) {
					if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
						{
							{
								{
									{
										double[][] var211;
										var211 = state.metric_g[sample$var196];
										double[] metric_inner;
										metric_inner = state.metric_g[sample$var196][server];
										state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = metric_inner[timeStep$var226];
									}
								}
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