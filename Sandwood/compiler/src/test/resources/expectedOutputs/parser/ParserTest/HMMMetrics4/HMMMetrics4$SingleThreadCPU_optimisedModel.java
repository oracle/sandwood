package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMMetrics4$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMMetrics4.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMMetrics4$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$distributionAccumulator$var73;
		double[] cv$var20$countGlobal;
		double[] cv$var33$countGlobal;
		double[] cv$var55$stateProbabilityGlobal;
		double[] cv$var74$stateProbabilityGlobal;
		boolean[][][] guard$sample57gaussian255$global;
		boolean[][][] guard$sample76gaussian255$global;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var20$countGlobal
			// 
			// Allocation of cv$var20$countGlobal for single threaded execution
			cv$var20$countGlobal = new double[state.noStates];
			
			// Constructor for cv$var33$countGlobal
			// 
			// Allocation of cv$var33$countGlobal for single threaded execution
			cv$var33$countGlobal = new double[state.noStates];
			
			// Constructor for cv$distributionAccumulator$var73
			// 
			// Allocation of cv$distributionAccumulator$var73 for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 74. Initially set to the value
			// of putTask 34.
			cv$distributionAccumulator$var73 = new double[state.noStates];
			
			// Constructor for cv$var55$stateProbabilityGlobal
			// 
			// Allocation of cv$var55$stateProbabilityGlobal for single threaded execution
			cv$var55$stateProbabilityGlobal = new double[state.noStates];
			
			// Constructor for guard$sample57gaussian255$global
			{
				// Calculate the largest index of server that is possible and allocate an array to
				// hold the guard for each of these.
				int cv$max_server = 0;
				
				// Calculate the largest index of timeStep that is possible and allocate an array
				// to hold the guard for each of these.
				int cv$max_timeStep$var226 = 0;
				for(int sample$var196 = 0; sample$var196 < state.length$metric.length; sample$var196 += 1) {
					if((0 < state.length$metric[0].length))
						cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, state.length$metric[sample$var196][0]);
					cv$max_server = Math.max(cv$max_server, state.length$metric[0].length);
				}
				
				// Allocation of guard$sample57gaussian255$global for single threaded execution
				guard$sample57gaussian255$global = new boolean[state.length$metric.length][cv$max_server][cv$max_timeStep$var226];
			}
			
			// Allocation of cv$var74$stateProbabilityGlobal for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 74. Initially set to the value
			// of putTask 34.
			cv$var74$stateProbabilityGlobal = new double[state.noStates];
			
			// Constructor for guard$sample76gaussian255$global
			// 
			// Calculate the largest index of server that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_server = 0;
			
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var226 = 0;
			for(int sample$var196 = 0; sample$var196 < state.length$metric.length; sample$var196 += 1) {
				if((0 < state.length$metric[0].length))
					cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, state.length$metric[sample$var196][0]);
				cv$max_server = Math.max(cv$max_server, state.length$metric[0].length);
			}
			
			// Allocation of guard$sample76gaussian255$global for single threaded execution
			guard$sample76gaussian255$global = new boolean[state.length$metric.length][cv$max_server][cv$max_timeStep$var226];
		}
	}


	public HMMMetrics4$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample134
	private final void drawValueSample134(int var119, int var129) {
		state.current_metric_mean[var119][var129] = ((double)state.max_metric * DistributionSampling.sampleUniform(state.RNG$));
	}

	// Pick a value from the distribution for the unconditioned variable from sample162
	private final void drawValueSample162(int var146, int var156) {
		state.current_metric_var[var146][var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample190
	private final void drawValueSample190(int var173, int var183) {
		state.current_metric_valid_bias[var173][var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample20
	private final void drawValueSample20() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
	}

	// Pick a value from the distribution for the unconditioned variable from sample33
	private final void drawValueSample33(int var32) {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var32]);
	}

	// Pick a value from the distribution for the unconditioned variable from sample57
	private final void drawValueSample57(int sample$var45) {
		state.st[sample$var45][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
	}

	// Pick a value from the distribution for the unconditioned variable from sample76
	private final void drawValueSample76(int sample$var45, int timeStep$var66) {
		state.st[sample$var45][timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 134 drawn from Uniform 108. Inference was performed using Metropolis-Hastings.
	private final void inferSample134(int var119, int var129) {
		state.constrainedFlag$sample134[var119][var129] = false;
		
		// The original value of the sample
		double cv$originalValue = state.current_metric_mean[var119][var129];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.01))
			cv$var = 0.01;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Constructing a random variable input for use later.
			// 
									// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue < (double)state.max_metric))?(-Math.log(state.max_metric)):Double.NEGATIVE_INFINITY);
			
			// Looking for a path between Sample 134 and consumer Gaussian 244.
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 < state.length$metric[sample$var196][0]) && state.metric_valid_g[sample$var196][var119][0])) {
					if(state.fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var129 == state.st[sample$var196][0])) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample134[var119][var129] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var156 = state.st[sample$var196][0];
							
																					// Substituted "server" with its value "var119".
							if(((0 <= var156) && (var156 < state.noStates))) {
								// Constructing a random variable input for use later.
								// 
																								// Substituted "server" with its value "var119".
								double var243 = state.current_metric_var[var119][state.st[sample$var196][0]];
								
																								// Substituted "server" with its value "var119".
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][0] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
								
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
						}
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "sample$var45" with its value "sample$var196".
						// 
						// Substituted "index$sample57$7" with its value "var129".
						double cv$probabilitySample57Value8 = state.distribution$sample57[sample$var196][var129];
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample134[var119][var129] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var119".
						// 
						// Substituted "index$sample57$7" with its value "var129".
						double var243 = state.current_metric_var[var119][var129];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
																		// Substituted "server" with its value "var119".
						// 
						// Set the current value to the current state of the tree.
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][0] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
																					// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample57$7" with its value "var129".
							// 
																					// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample57$7" with its value "var129".
							// 
																					// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample57$7" with its value "var129".
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
					// Substituted "server" with its value "var119".
					if(state.metric_valid_g[sample$var196][var119][timeStep$var226]) {
						if(state.fixedFlag$sample76) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var129 == state.st[sample$var196][timeStep$var226])) {
								// Mark that the sample has observed constrained data.
								state.constrainedFlag$sample134[var119][var129] = true;
								
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var156 = state.st[sample$var196][timeStep$var226];
								
																								// Substituted "server" with its value "var119".
								if(((0 <= var156) && (var156 < state.noStates))) {
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var119".
									double var243 = state.current_metric_var[var119][state.st[sample$var196][timeStep$var226]];
									
									// Substituted "server" with its value "var119".
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][timeStep$var226] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
									
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
							}
						} else {
							// Update the probability of sampling this value from the distribution value.
							// 
																					// Substituted "sample$var45" with its value "sample$var196".
							// 
							// Substituted "index$sample76$19" with its value "var129".
							double cv$probabilitySample76Value20 = state.distribution$sample76[sample$var196][(timeStep$var226 - 1)][var129];
							
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample134[var119][var129] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample76$19" with its value "var129".
							double var243 = state.current_metric_var[var119][var129];
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Set the current value to the current state of the tree.
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][timeStep$var226] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								// 
																								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								// 
								// Substituted "index$sample76$19" with its value "var129".
								// 
																								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								// 
								// Substituted "index$sample76$19" with its value "var129".
								// 
																								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								// 
								// Substituted "index$sample76$19" with its value "var129".
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
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.constrainedFlag$sample134[var119][var129]) {
			state.current_metric_mean[var119][var129] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < (double)state.max_metric))?(-Math.log(state.max_metric)):Double.NEGATIVE_INFINITY);
			
			// Looking for a path between Sample 134 and consumer Gaussian 244.
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 < state.length$metric[sample$var196][0]) && state.metric_valid_g[sample$var196][var119][0])) {
					if(state.fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var129 == state.st[sample$var196][0])) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample134[var119][var129] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var156 = state.st[sample$var196][0];
							
																					// Substituted "server" with its value "var119".
							if(((0 <= var156) && (var156 < state.noStates))) {
								// Constructing a random variable input for use later.
								// 
																								// Substituted "server" with its value "var119".
								double var243 = state.current_metric_var[var119][state.st[sample$var196][0]];
								
																								// Substituted "server" with its value "var119".
								cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][0] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
								
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
						}
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "sample$var45" with its value "sample$var196".
						// 
						// Substituted "index$sample57$7" with its value "var129".
						double cv$probabilitySample57Value8 = state.distribution$sample57[sample$var196][var129];
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample134[var119][var129] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var119".
						// 
						// Substituted "index$sample57$7" with its value "var129".
						double var243 = state.current_metric_var[var119][var129];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
																		// Substituted "server" with its value "var119".
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][0] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
																					// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample57$7" with its value "var129".
							// 
																					// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample57$7" with its value "var129".
							// 
																					// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample57$7" with its value "var129".
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
					// Substituted "server" with its value "var119".
					if(state.metric_valid_g[sample$var196][var119][timeStep$var226]) {
						if(state.fixedFlag$sample76) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var129 == state.st[sample$var196][timeStep$var226])) {
								// Mark that the sample has observed constrained data.
								state.constrainedFlag$sample134[var119][var129] = true;
								
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var156 = state.st[sample$var196][timeStep$var226];
								
																								// Substituted "server" with its value "var119".
								if(((0 <= var156) && (var156 < state.noStates))) {
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var119".
									double var243 = state.current_metric_var[var119][state.st[sample$var196][timeStep$var226]];
									
									// Substituted "server" with its value "var119".
									cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][timeStep$var226] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
									
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
							}
						} else {
							// Update the probability of sampling this value from the distribution value.
							// 
																					// Substituted "sample$var45" with its value "sample$var196".
							// 
							// Substituted "index$sample76$19" with its value "var129".
							double cv$probabilitySample76Value20 = state.distribution$sample76[sample$var196][(timeStep$var226 - 1)][var129];
							
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample134[var119][var129] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample76$19" with its value "var129".
							double var243 = state.current_metric_var[var119][var129];
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "server" with its value "var119".
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][timeStep$var226] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								// 
																								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								// 
								// Substituted "index$sample76$19" with its value "var129".
								// 
																								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								// 
								// Substituted "index$sample76$19" with its value "var129".
								// 
																								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								// 
								// Substituted "index$sample76$19" with its value "var129".
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
					}
				}
			}
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				state.current_metric_mean[var119][var129] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 162 drawn from InverseGamma 135. Inference was performed using Metropolis-Hastings.
	private final void inferSample162(int var146, int var156) {
		state.constrainedFlag$sample162[var146][var156] = false;
		
		// The original value of the sample
		double cv$originalValue = state.current_metric_var[var146][var156];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.01))
			cv$var = 0.01;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			
			// Looking for a path between Sample 162 and consumer Gaussian 244.
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 < state.length$metric[sample$var196][0]) && state.metric_valid_g[sample$var196][var146][0])) {
					if(state.fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var156 == state.st[sample$var196][0])) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample162[var146][var156] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var129 = state.st[sample$var196][0];
							
																					// Substituted "server" with its value "var146".
							if(((0 <= var129) && (var129 < state.noStates))) {
																								// Substituted "server" with its value "var146".
								// 
																								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][0] - state.current_metric_mean[var146][state.st[sample$var196][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
								
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
						}
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "sample$var45" with its value "sample$var196".
						// 
						// Substituted "index$sample57$7" with its value "var156".
						double cv$probabilitySample57Value8 = state.distribution$sample57[sample$var196][var156];
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample162[var146][var156] = true;
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
																		// Substituted "server" with its value "var146".
						// 
																		// Set the current value to the current state of the tree.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var146".
						// 
						// Substituted "index$sample57$7" with its value "var156".
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][0] - state.current_metric_mean[var146][var156]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
						
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
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
					// Substituted "server" with its value "var146".
					if(state.metric_valid_g[sample$var196][var146][timeStep$var226]) {
						if(state.fixedFlag$sample76) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var156 == state.st[sample$var196][timeStep$var226])) {
								// Mark that the sample has observed constrained data.
								state.constrainedFlag$sample162[var146][var156] = true;
								
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var129 = state.st[sample$var196][timeStep$var226];
								
																								// Substituted "server" with its value "var146".
								if(((0 <= var129) && (var129 < state.noStates))) {
									// Substituted "server" with its value "var146".
									// 
																											// Set the current value to the current state of the tree.
									// 
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var146".
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][timeStep$var226] - state.current_metric_mean[var146][state.st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
									
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
							}
						} else {
							// Update the probability of sampling this value from the distribution value.
							// 
																					// Substituted "sample$var45" with its value "sample$var196".
							// 
							// Substituted "index$sample76$19" with its value "var156".
							double cv$probabilitySample76Value20 = state.distribution$sample76[sample$var196][(timeStep$var226 - 1)][var156];
							
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample162[var146][var156] = true;
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "server" with its value "var146".
							// 
																					// Set the current value to the current state of the tree.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var146".
							// 
							// Substituted "index$sample76$19" with its value "var156".
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][timeStep$var226] - state.current_metric_mean[var146][var156]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
							
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
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.constrainedFlag$sample162[var146][var156]) {
			state.current_metric_var[var146][var156] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
			
			// Looking for a path between Sample 162 and consumer Gaussian 244.
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 < state.length$metric[sample$var196][0]) && state.metric_valid_g[sample$var196][var146][0])) {
					if(state.fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var156 == state.st[sample$var196][0])) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample162[var146][var156] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var129 = state.st[sample$var196][0];
							
																					// Substituted "server" with its value "var146".
							if(((0 <= var129) && (var129 < state.noStates))) {
																								// Substituted "server" with its value "var146".
								// 
								// Constructing a random variable input for use later.
								// 
																								// Substituted "server" with its value "var146".
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][0] - state.current_metric_mean[var146][state.st[sample$var196][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
								
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
						}
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "sample$var45" with its value "sample$var196".
						// 
						// Substituted "index$sample57$7" with its value "var156".
						double cv$probabilitySample57Value8 = state.distribution$sample57[sample$var196][var156];
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample162[var146][var156] = true;
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
																		// Substituted "server" with its value "var146".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var146".
						// 
						// Substituted "index$sample57$7" with its value "var156".
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][0] - state.current_metric_mean[var146][var156]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
						
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
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
					// Substituted "server" with its value "var146".
					if(state.metric_valid_g[sample$var196][var146][timeStep$var226]) {
						if(state.fixedFlag$sample76) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var156 == state.st[sample$var196][timeStep$var226])) {
								// Mark that the sample has observed constrained data.
								state.constrainedFlag$sample162[var146][var156] = true;
								
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var129 = state.st[sample$var196][timeStep$var226];
								
																								// Substituted "server" with its value "var146".
								if(((0 <= var129) && (var129 < state.noStates))) {
									// Substituted "server" with its value "var146".
									// 
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var146".
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][timeStep$var226] - state.current_metric_mean[var146][state.st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
									
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
							}
						} else {
							// Update the probability of sampling this value from the distribution value.
							// 
																					// Substituted "sample$var45" with its value "sample$var196".
							// 
							// Substituted "index$sample76$19" with its value "var156".
							double cv$probabilitySample76Value20 = state.distribution$sample76[sample$var196][(timeStep$var226 - 1)][var156];
							
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample162[var146][var156] = true;
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "server" with its value "var146".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var146".
							// 
							// Substituted "index$sample76$19" with its value "var156".
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][timeStep$var226] - state.current_metric_mean[var146][var156]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
							
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
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				state.current_metric_var[var146][var156] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 190 drawn from Beta 162. Inference was performed using a Beta to
	// Bernoulli/Binomial conjugate prior.
	private final void inferSample190(int var173, int var183) {
		state.constrainedFlag$sample190[var173][var183] = false;
		
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		
		// Processing random variable 231.
		// 
		// Looking for a path between Sample 190 and consumer Bernoulli 231.
		for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.length$metric[sample$var196][0])) {
				if(state.fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var183 == state.st[sample$var196][0])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample190[var173][var183] = true;
						
						// Include the value sampled by task 241 from random variable var231.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						// 
																		// Substituted "server" with its value "var173".
						if(state.metric_valid_g[sample$var196][var173][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "sample$var45" with its value "sample$var196".
					// 
					// Substituted "index$sample57$6" with its value "var183".
					double cv$probabilitySample57Value7 = state.distribution$sample57[sample$var196][var183];
					
					// Processing sample task 241 of consumer random variable null.
					// 
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample190[var173][var183] = true;
					
					// Include the value sampled by task 241 from random variable var231.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + cv$probabilitySample57Value7);
					
					// If the sample value was positive increase the count
					// 
															// Substituted "server" with its value "var173".
					if(state.metric_valid_g[sample$var196][var173][0])
						cv$sum = (cv$sum + cv$probabilitySample57Value7);
				}
			}
		}
		for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
			for(int timeStep$var226 = 1; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
				if(state.fixedFlag$sample76) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var183 == state.st[sample$var196][timeStep$var226])) {
						// Processing sample task 241 of consumer random variable null.
						// 
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample190[var173][var183] = true;
						
						// Include the value sampled by task 241 from random variable var231.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						// 
						// Substituted "server" with its value "var173".
						if(state.metric_valid_g[sample$var196][var173][timeStep$var226])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
															// Substituted "sample$var45" with its value "sample$var196".
					// 
					// Substituted "index$sample76$18" with its value "var183".
					double cv$probabilitySample76Value19 = state.distribution$sample76[sample$var196][(timeStep$var226 - 1)][var183];
					
					// Processing sample task 241 of consumer random variable null.
					// 
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample190[var173][var183] = true;
					
					// Include the value sampled by task 241 from random variable var231.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + cv$probabilitySample76Value19);
					
					// If the sample value was positive increase the count
					// 
					// Substituted "server" with its value "var173".
					if(state.metric_valid_g[sample$var196][var173][timeStep$var226])
						cv$sum = (cv$sum + cv$probabilitySample76Value19);
				}
			}
		}
		if(state.constrainedFlag$sample190[var173][var183])
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			state.current_metric_valid_bias[var173][var183] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 20 drawn from Dirichlet 19. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample20() {
		state.constrainedFlag$sample20 = false;
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			scratch.cv$var20$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample57) {
			// Processing random variable 54.
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				// Processing sample task 57 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample20 = true;
				
				// Increment the sample counter with the value sampled by sample task 57 of random
				// variable var54
				// 
												// A local reference to the scratch space.
				scratch.cv$var20$countGlobal[state.st[sample$var45][0]] = (scratch.cv$var20$countGlobal[state.st[sample$var45][0]] + 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				// Processing sample task 57 of consumer random variable null.
				// 
				// Merge the distribution probabilities into the count
				// 
				// Get the length of the array
				for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
															// A local reference to the scratch space.
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					scratch.cv$var20$countGlobal[cv$loopIndex] = (scratch.cv$var20$countGlobal[cv$loopIndex] + state.distribution$sample57[sample$var45][cv$loopIndex]);
			}
		}
		if(state.constrainedFlag$sample20)
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
									// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var20$countGlobal, state.initialStateDistribution, state.noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 33 drawn from Dirichlet 21. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample33(int var32) {
		state.constrainedFlag$sample33[var32] = false;
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			scratch.cv$var33$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample76) {
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < state.length$metric[sample$var45][0])) {
					if(state.fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var32 == state.st[sample$var45][0])) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample33[var32] = true;
							
							// Increment the sample counter with the value sampled by sample task 76 of random
							// variable var73
							// 
																					// A local reference to the scratch space.
							scratch.cv$var33$countGlobal[state.st[sample$var45][1]] = (scratch.cv$var33$countGlobal[state.st[sample$var45][1]] + 1.0);
						}
					} else {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample33[var32] = true;
						
						// Increment the sample counter with the value sampled by sample task 76 of random
						// variable var73
						// 
																		// A local reference to the scratch space.
						// 
						// Substituted "index$sample57$5" with its value "var32".
						scratch.cv$var33$countGlobal[state.st[sample$var45][1]] = (scratch.cv$var33$countGlobal[state.st[sample$var45][1]] + state.distribution$sample57[sample$var45][var32]);
					}
				}
			}
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 2; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
					if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
						// Processing sample task 76 of consumer random variable null.
						// 
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample33[var32] = true;
						
						// Increment the sample counter with the value sampled by sample task 76 of random
						// variable var73
						// 
																		// A local reference to the scratch space.
						scratch.cv$var33$countGlobal[state.st[sample$var45][timeStep$var66]] = (scratch.cv$var33$countGlobal[state.st[sample$var45][timeStep$var66]] + 1.0);
					}
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
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < state.length$metric[sample$var45][0])) {
					if(state.fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var32 == state.st[sample$var45][0])) {
							// Merge the distribution probabilities into the count
							// 
							// Get the length of the array
							for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
																								// A local reference to the scratch space.
								// 
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								scratch.cv$var33$countGlobal[cv$loopIndex] = (scratch.cv$var33$countGlobal[cv$loopIndex] + state.distribution$sample76[sample$var45][0][cv$loopIndex]);
						}
					} else {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
																		// Substituted "index$sample$41" with its value "sample$var45".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample57$42" with its value "var32".
						double cv$distributionProbability = state.distribution$sample57[sample$var45][var32];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
																					// A local reference to the scratch space.
							scratch.cv$var33$countGlobal[cv$loopIndex] = (scratch.cv$var33$countGlobal[cv$loopIndex] + (state.distribution$sample76[sample$var45][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
					int index$timeStep$52 = (timeStep$var66 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$sample$51" with its value "sample$var45".
					if((1 <= index$timeStep$52)) {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
																		// Substituted "index$sample$51" with its value "sample$var45".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample76$53" with its value "var32".
						double cv$distributionProbability = state.distribution$sample76[sample$var45][(index$timeStep$52 - 1)][var32];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
																					// A local reference to the scratch space.
							scratch.cv$var33$countGlobal[cv$loopIndex] = (scratch.cv$var33$countGlobal[cv$loopIndex] + (state.distribution$sample76[sample$var45][(timeStep$var66 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		if(state.constrainedFlag$sample33[var32])
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
									// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var33$countGlobal, state.m[var32], state.noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 57 drawn from Categorical 54. Inference was performed using variable
	// marginalization.
	private final void inferSample57(int sample$var45) {
		state.constrainedFlag$sample57[sample$var45] = false;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
				// cv$numStates's comment
		// Calculate the number of states to evaluate.
		int cv$numStates = Math.max(0, state.noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < state.noStates) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$valuePos])) && (state.initialStateDistribution[cv$valuePos] <= 1.0))?Math.log(state.initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.fixedFlag$sample76 && (1 < state.length$metric[sample$var45][0]))) {
				// Looking for a path between Sample 57 and consumer Categorical 73.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample57[sample$var45] = true;
				
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Categorical 73 which is consuming
				// the output of Sample task 57.
				// 
				// Value of the variable at this index
				if((cv$valuePos < state.noStates)) {
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					double[] var72 = state.m[cv$valuePos];
					
															// Substituted "index$sample$2_2" with its value "sample$var45".
					cv$accumulatedConsumerProbabilities = (((((0.0 <= state.st[sample$var45][1]) && (state.st[sample$var45][1] < state.noStates)) && (0.0 <= var72[state.st[sample$var45][1]])) && (var72[state.st[sample$var45][1]] <= 1.0))?Math.log(var72[state.st[sample$var45][1]]):Double.NEGATIVE_INFINITY);
					
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
			if((0 < state.length$metric[sample$var45][0])) {
				// Looking for a path between Sample 57 and consumer Bernoulli 231.
				for(int server = 0; server < state.noServers; server += 1) {
					// Processing sample task 241 of consumer random variable null.
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample57[sample$var45] = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Value of the variable at this index
					if((cv$valuePos < state.noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var230 = state.current_metric_valid_bias[server][cv$valuePos];
						
																		// Substituted "sample$var196" with its value "sample$var45".
						cv$accumulatedConsumerProbabilities = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var45][server][0]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
						
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
				for(int server = 0; server < state.noServers; server += 1) {
															// Substituted "sample$var196" with its value "sample$var45".
					if(state.metric_valid_g[sample$var45][server][0])
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample57gaussian255$global[sample$var45][server][0] = false;
				}
				for(int server = 0; server < state.noServers; server += 1) {
															// Substituted "sample$var196" with its value "sample$var45".
					if(state.metric_valid_g[sample$var45][server][0])
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample57gaussian255$global[sample$var45][server][0] = false;
				}
				for(int server = 0; server < state.noServers; server += 1) {
															// Substituted "sample$var196" with its value "sample$var45".
					if((state.metric_valid_g[sample$var45][server][0] && !scratch.guard$sample57gaussian255$global[sample$var45][server][0])) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample57gaussian255$global[sample$var45][server][0] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample57[sample$var45] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((cv$valuePos < state.noStates)) {
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var243 = state.current_metric_var[server][cv$valuePos];
							
																					// Substituted "sample$var196" with its value "sample$var45".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][0] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
							
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
					}
				}
				for(int server = 0; server < state.noServers; server += 1) {
															// Substituted "sample$var196" with its value "sample$var45".
					if((state.metric_valid_g[sample$var45][server][0] && !scratch.guard$sample57gaussian255$global[sample$var45][server][0])) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample57gaussian255$global[sample$var45][server][0] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample57[sample$var45] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Value of the variable at this index
						if((cv$valuePos < state.noStates)) {
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var243 = state.current_metric_var[server][cv$valuePos];
							
																					// Substituted "sample$var196" with its value "sample$var45".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][0] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
							
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
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!state.fixedFlag$sample76 && (1 < state.length$metric[sample$var45][0]))) {
				// Looking for a path between Sample 57 and consumer Categorical 73.
				// Processing sample task 76 of consumer random variable null.
				// 
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					scratch.cv$distributionAccumulator$var73[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Enumerating the possible arguments for the variable Categorical 73 which is consuming
				// the output of Sample task 57.
				// 
				// Value of the variable at this index
				if((cv$valuePos < state.noStates)) {
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
															// Constructing a random variable input for use later.
					// 
															// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var73, 1.0, state.m[cv$valuePos], state.noStates);
				}
				
				// A local copy of the samples' distribution.
				// 
												// Substituted "index$sample$66_2" with its value "sample$var45".
				double[] cv$sampleDistribution = state.distribution$sample76[sample$var45][0];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var73[cv$i] / cv$reachedDistributionProbability);
					
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
			scratch.cv$var55$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(state.constrainedFlag$sample57[sample$var45]) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// Local copy of the probability array
			double[] cv$localProbability = state.distribution$sample57[sample$var45];
			
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var55$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var55$stateProbabilityGlobal[cv$lseIndex];
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
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var55$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Get a local reference to the scratch space.
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var55$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var55$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 76 drawn from Categorical 73. Inference was performed using variable
	// marginalization.
	private final void inferSample76(int sample$var45, int timeStep$var66) {
		state.constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = false;
		
		// Calculate the number of states to evaluate.
		int cv$numStates = 0;
		
		// Exploring all the possible state counts for random variable 73.
		// 
		// Enumerating the possible arguments for Categorical 73.
		if((1 == timeStep$var66)) {
			// Exploring all the possible state counts for random variable 73.
			// 
			// Enumerating the possible arguments for Categorical 73.
			if(state.fixedFlag$sample57) {
				int var32 = state.st[sample$var45][0];
				
												// Substituted "timeStep$var66" with its value "1".
				if(((0 <= var32) && (var32 < state.noStates)))
					// variable marginalization
					// 
										// cv$numStates's comment
					// Calculate the number of states to evaluate.
					cv$numStates = Math.max(0, state.noStates);
			} else {
				// Enumerating the possible outputs of Categorical 54.
				if((0 < state.noStates))
					// variable marginalization
					cv$numStates = state.noStates;
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < state.noStates)) {
			int index$timeStep$13 = (timeStep$var66 - 1);
			
												// index$sample$2's comment
			// Copy of index so that its values can be safely substituted
			// 
									// Substituted "index$sample$12" with its value "sample$var45".
			// 
									// Substituted "index$timeStep$13" with its value "(timeStep$var66 - 1)".
			// 
									// Substituted "index$timeStep$13" with its value "(timeStep$var66 - 1)".
			// 
									// Substituted "index$timeStep$13" with its value "(timeStep$var66 - 1)".
			// 
									// Substituted "index$timeStep$13" with its value "(timeStep$var66 - 1)".
			// 
									// Substituted "index$timeStep$13" with its value "(timeStep$var66 - 1)".
			// 
									// Substituted "index$timeStep$13" with its value "(timeStep$var66 - 1)".
			if(((1 <= index$timeStep$13) && !(index$timeStep$13 == timeStep$var66)))
				// variable marginalization
				cv$numStates = state.noStates;
		}
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
			
			// Enumerating the possible arguments for Categorical 73.
			if((1 == timeStep$var66)) {
				// Enumerating the possible arguments for Categorical 73.
				if(state.fixedFlag$sample57) {
					int var32 = state.st[sample$var45][0];
					
															// Substituted "timeStep$var66" with its value "1".
					if(((0 <= var32) && (var32 < state.noStates))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "timeStep$var66" with its value "1".
						double[] var72 = state.m[state.st[sample$var45][0]];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
																		// Value of the variable at this index
						double cv$accumulatedProbabilities = ((((cv$valuePos < state.noStates) && (0.0 <= var72[cv$valuePos])) && (var72[cv$valuePos] <= 1.0))?Math.log(var72[cv$valuePos]):Double.NEGATIVE_INFINITY);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < state.length$metric[sample$var45][0])) {
							// Looking for a path between Sample 76 and consumer Bernoulli 231.
							for(int server = 0; server < state.noServers; server += 1) {
								// Processing sample task 241 of consumer random variable null.
								// Mark that the sample has observed constrained data.
								// 
								// Substituted "timeStep$var66" with its value "1".
								state.constrainedFlag$sample76[sample$var45][0] = true;
								
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Value of the variable at this index
								if((cv$valuePos < state.noStates)) {
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var230 = state.current_metric_valid_bias[server][cv$valuePos];
									
									// Substituted "sample$var196" with its value "sample$var45".
									// 
									// Substituted "timeStep$var226" with its value "1".
									cv$accumulatedConsumerProbabilities = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var45][server][1]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
									
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
							for(int server = 0; server < state.noServers; server += 1) {
								// Substituted "sample$var196" with its value "sample$var45".
								// 
								// Substituted "timeStep$var226" with its value "1".
								if(state.metric_valid_g[sample$var45][server][1])
									// Set the flags to false
									// 
																											// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var226" with its value "1".
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < state.noServers; server += 1) {
								// Substituted "sample$var196" with its value "sample$var45".
								// 
								// Substituted "timeStep$var226" with its value "1".
								if(state.metric_valid_g[sample$var45][server][1])
									// Set the flags to false
									// 
																											// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var226" with its value "1".
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < state.noServers; server += 1) {
								// Substituted "sample$var196" with its value "sample$var45".
								// 
																								// Substituted "timeStep$var226" with its value "1".
								if((state.metric_valid_g[sample$var45][server][1] && !scratch.guard$sample76gaussian255$global[sample$var45][server][1])) {
									// The body will execute, so should not be executed again
									// 
																											// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var226" with its value "1".
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = true;
									
									// Mark that the sample has observed constrained data.
									// 
									// Substituted "timeStep$var66" with its value "1".
									state.constrainedFlag$sample76[sample$var45][0] = true;
									
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Value of the variable at this index
									if((cv$valuePos < state.noStates)) {
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double var243 = state.current_metric_var[server][cv$valuePos];
										
										// Substituted "sample$var196" with its value "sample$var45".
										// 
										// Substituted "timeStep$var226" with its value "1".
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][1] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
										
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
								}
							}
							for(int server = 0; server < state.noServers; server += 1) {
								// Substituted "sample$var196" with its value "sample$var45".
								// 
																								// Substituted "timeStep$var226" with its value "1".
								if((state.metric_valid_g[sample$var45][server][1] && !scratch.guard$sample76gaussian255$global[sample$var45][server][1])) {
									// The body will execute, so should not be executed again
									// 
																											// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var226" with its value "1".
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = true;
									
									// Mark that the sample has observed constrained data.
									// 
									// Substituted "timeStep$var66" with its value "1".
									state.constrainedFlag$sample76[sample$var45][0] = true;
									
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((cv$valuePos < state.noStates)) {
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double var243 = state.current_metric_var[server][cv$valuePos];
										
										// Substituted "sample$var196" with its value "sample$var45".
										// 
										// Substituted "timeStep$var226" with its value "1".
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][1] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
										
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
								}
							}
						}
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					// Enumerating the possible outputs of Categorical 54.
					for(int index$sample57$22 = 0; index$sample57$22 < state.noStates; index$sample57$22 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$21" with its value "sample$var45".
						double cv$probabilitySample57Value23 = state.distribution$sample57[sample$var45][index$sample57$22];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value23);
						
						// Constructing a random variable input for use later.
						double[] var72 = state.m[index$sample57$22];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
																		// Value of the variable at this index
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value23) + (((0.0 <= var72[cv$valuePos]) && (var72[cv$valuePos] <= 1.0))?Math.log(var72[cv$valuePos]):Double.NEGATIVE_INFINITY));
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < state.length$metric[sample$var45][0])) {
							// Looking for a path between Sample 76 and consumer Bernoulli 231.
							for(int server = 0; server < state.noServers; server += 1) {
								// Processing sample task 241 of consumer random variable null.
								// Mark that the sample has observed constrained data.
								// 
								// Substituted "timeStep$var66" with its value "1".
								state.constrainedFlag$sample76[sample$var45][0] = true;
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var230 = state.current_metric_valid_bias[server][cv$valuePos];
								
								// A check to ensure rounding of floating point values can never result in a negative
								// value.
								// 
								// Recorded the probability of reaching sample task 241 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								// 
								// Variable declaration of cv$accumulatedConsumerProbabilities moved.
								// Declaration comment was:
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								// 
								// Substituted "sample$var196" with its value "sample$var45".
								// 
								// Substituted "timeStep$var226" with its value "1".
								cv$accumulatedProbabilities = ((((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var45][server][1]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
							}
							for(int server = 0; server < state.noServers; server += 1) {
								// Substituted "sample$var196" with its value "sample$var45".
								// 
								// Substituted "timeStep$var226" with its value "1".
								if(state.metric_valid_g[sample$var45][server][1])
									// Set the flags to false
									// 
																											// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var226" with its value "1".
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < state.noServers; server += 1) {
								// Substituted "sample$var196" with its value "sample$var45".
								// 
								// Substituted "timeStep$var226" with its value "1".
								if(state.metric_valid_g[sample$var45][server][1])
									// Set the flags to false
									// 
																											// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var226" with its value "1".
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < state.noServers; server += 1) {
								// Substituted "sample$var196" with its value "sample$var45".
								// 
																								// Substituted "timeStep$var226" with its value "1".
								if((state.metric_valid_g[sample$var45][server][1] && !scratch.guard$sample76gaussian255$global[sample$var45][server][1])) {
									// The body will execute, so should not be executed again
									// 
																											// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var226" with its value "1".
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = true;
									
									// Mark that the sample has observed constrained data.
									// 
									// Substituted "timeStep$var66" with its value "1".
									state.constrainedFlag$sample76[sample$var45][0] = true;
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var243 = state.current_metric_var[server][cv$valuePos];
									
									// A check to ensure rounding of floating point values can never result in a negative
									// value.
									// 
									// Recorded the probability of reaching sample task 256 with the current configuration.
									// 
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									// 
									// Variable declaration of cv$accumulatedConsumerProbabilities moved.
									// Declaration comment was:
									// This value is not used before it is set again, so removing the value declaration.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									// 
									// Substituted "sample$var196" with its value "sample$var45".
									// 
									// Substituted "timeStep$var226" with its value "1".
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][1] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
								}
							}
							for(int server = 0; server < state.noServers; server += 1) {
								// Substituted "sample$var196" with its value "sample$var45".
								// 
																								// Substituted "timeStep$var226" with its value "1".
								if((state.metric_valid_g[sample$var45][server][1] && !scratch.guard$sample76gaussian255$global[sample$var45][server][1])) {
									// The body will execute, so should not be executed again
									// 
																											// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var226" with its value "1".
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = true;
									
									// Mark that the sample has observed constrained data.
									// 
									// Substituted "timeStep$var66" with its value "1".
									state.constrainedFlag$sample76[sample$var45][0] = true;
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var243 = state.current_metric_var[server][cv$valuePos];
									
									// A check to ensure rounding of floating point values can never result in a negative
									// value.
									// 
									// Recorded the probability of reaching sample task 256 with the current configuration.
									// 
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									// 
									// Variable declaration of cv$accumulatedConsumerProbabilities moved.
									// Declaration comment was:
									// This value is not used before it is set again, so removing the value declaration.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									// 
									// Substituted "sample$var196" with its value "sample$var45".
									// 
									// Substituted "timeStep$var226" with its value "1".
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][1] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$timeStep$30 = (timeStep$var66 - 1);
			
												// index$sample$2's comment
			// Copy of index so that its values can be safely substituted
			// 
									// Substituted "index$sample$29" with its value "sample$var45".
			// 
									// Substituted "index$timeStep$30" with its value "(timeStep$var66 - 1)".
			// 
									// Substituted "index$timeStep$30" with its value "(timeStep$var66 - 1)".
			// 
									// Substituted "index$timeStep$30" with its value "(timeStep$var66 - 1)".
			// 
									// Substituted "index$timeStep$30" with its value "(timeStep$var66 - 1)".
			// 
									// Substituted "index$timeStep$30" with its value "(timeStep$var66 - 1)".
			// 
									// Substituted "index$timeStep$30" with its value "(timeStep$var66 - 1)".
			if(((1 <= index$timeStep$30) && !(index$timeStep$30 == timeStep$var66))) {
				// Enumerating the possible outputs of Categorical 73.
				for(int index$sample76$31 = 0; index$sample76$31 < state.noStates; index$sample76$31 += 1) {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$29" with its value "sample$var45".
					double cv$probabilitySample76Value32 = state.distribution$sample76[sample$var45][(index$timeStep$30 - 1)][index$sample76$31];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample76Value32);
					
					// Constructing a random variable input for use later.
					double[] var72 = state.m[index$sample76$31];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
															// Value of the variable at this index
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample76Value32) + (((0.0 <= var72[cv$valuePos]) && (var72[cv$valuePos] <= 1.0))?Math.log(var72[cv$valuePos]):Double.NEGATIVE_INFINITY));
					for(int server = 0; server < state.noServers; server += 1) {
						// Processing sample task 241 of consumer random variable null.
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = true;
						
						// Constructing a random variable input for use later.
						double var230 = state.current_metric_valid_bias[server][index$sample76$31];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 241 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
																		// Substituted "sample$var196" with its value "sample$var45".
						cv$accumulatedProbabilities = ((((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var45][server][timeStep$var66]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					for(int server = 0; server < state.noServers; server += 1) {
																		// Substituted "sample$var196" with its value "sample$var45".
						if(state.metric_valid_g[sample$var45][server][timeStep$var66])
							// Set the flags to false
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = false;
					}
					for(int server = 0; server < state.noServers; server += 1) {
																		// Substituted "sample$var196" with its value "sample$var45".
						if(state.metric_valid_g[sample$var45][server][timeStep$var66])
							// Set the flags to false
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = false;
					}
					for(int server = 0; server < state.noServers; server += 1) {
																		// Substituted "sample$var196" with its value "sample$var45".
						if((state.metric_valid_g[sample$var45][server][timeStep$var66] && !scratch.guard$sample76gaussian255$global[sample$var45][server][timeStep$var66])) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = true;
							
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var243 = state.current_metric_var[server][cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
																					// Substituted "sample$var196" with its value "sample$var45".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][timeStep$var66] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					for(int server = 0; server < state.noServers; server += 1) {
																		// Substituted "sample$var196" with its value "sample$var45".
						if((state.metric_valid_g[sample$var45][server][timeStep$var66] && !scratch.guard$sample76gaussian255$global[sample$var45][server][timeStep$var66])) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = true;
							
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var243 = state.current_metric_var[server][cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
																					// Substituted "sample$var196" with its value "sample$var45".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][timeStep$var66] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$timeStep$265_3 = (timeStep$var66 + 1);
			if((index$timeStep$265_3 < state.length$metric[sample$var45][0])) {
				// Processing sample task 76 of consumer random variable null.
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					scratch.cv$distributionAccumulator$var73[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Enumerating the possible arguments for the variable Categorical 73 which is consuming
				// the output of Sample task 76.
				// 
				// Processing random variable 73.
				// 
				// Looking for a path between Sample 76 and consumer Categorical 73.
				// 
				// Value of the variable at this index
				if((cv$valuePos < state.noStates)) {
					// Declare and zero an accumulator for tracking the reached source probability space.
					double scopeVariable$reachedSourceProbability = 0.0;
					
					// Enumerating the possible arguments for Categorical 73.
					if((1 == timeStep$var66)) {
						// Enumerating the possible arguments for Categorical 73.
						if(state.fixedFlag$sample57) {
							int index$var32$276_1 = state.st[sample$var45][0];
							
																					// Substituted "timeStep$var66" with its value "1".
							if(((0 <= index$var32$276_1) && (index$var32$276_1 < state.noStates)))
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 54.
							for(int index$sample57$272 = 0; index$sample57$272 < state.noStates; index$sample57$272 += 1)
								// Add the probability of this argument configuration.
								// 
																// cv$probabilitySample57Value273's comment
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$271" with its value "sample$var45".
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample57[sample$var45][index$sample57$272]);
						}
					}
					int index$timeStep$280 = (timeStep$var66 - 1);
					
																				// index$timeStep$267's comment
					// Copy of index so that its values can be safely substituted
					// 
																				// index$sample$268's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "index$sample$265_2" with its value "sample$var45".
					// 
															// Substituted "index$timeStep$265_3" with its value "(timeStep$var66 + 1)".
					// 
															// Substituted "index$timeStep$265_3" with its value "(timeStep$var66 + 1)".
					// 
															// Substituted "index$timeStep$265_3" with its value "(timeStep$var66 + 1)".
					// 
															// Substituted "index$timeStep$265_3" with its value "(timeStep$var66 + 1)".
					// 
															// Substituted "index$timeStep$265_3" with its value "(timeStep$var66 + 1)".
					// 
															// Substituted "index$timeStep$265_3" with its value "(timeStep$var66 + 1)".
					if((((1 <= index$timeStep$280) && !(index$timeStep$280 == timeStep$var66)) && !(index$timeStep$280 == index$timeStep$265_3))) {
						// Enumerating the possible outputs of Categorical 73.
						for(int index$sample76$281 = 0; index$sample76$281 < state.noStates; index$sample76$281 += 1)
							// Add the probability of this argument configuration.
							// 
														// cv$probabilitySample76Value282's comment
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$279" with its value "sample$var45".
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample76[sample$var45][(index$timeStep$280 - 1)][index$sample76$281]);
					}
					
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Zero an accumulator to track the probabilities reached.
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					
					// Add the current distribution to the distribution accumulator.
					// 
															// Constructing a random variable input for use later.
					// 
					// Processing random variable 73.
					// 
					// Looking for a path between Sample 76 and consumer Categorical 73.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var73, scopeVariable$reachedSourceProbability, state.m[cv$valuePos], state.noStates);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$sample$265_2" with its value "sample$var45".
				double[] cv$sampleDistribution = state.distribution$sample76[sample$var45][(index$timeStep$265_3 - 1)];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var73[cv$i] / cv$reachedDistributionProbability);
					
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
			scratch.cv$var74$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(state.constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)]) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// Local copy of the probability array
			double[] cv$localProbability = state.distribution$sample76[sample$var45][(timeStep$var66 - 1)];
			
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var74$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var74$stateProbabilityGlobal[cv$lseIndex];
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
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var74$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Get a local reference to the scratch space.
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var74$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var74$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
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
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 241 including any distribution
						// values.
						// 
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = state.metric_valid_g[sample$var196][server][timeStep$var226];
						
						// Enumerating the possible arguments for Bernoulli 231.
						if((0 == timeStep$var226)) {
							// Enumerating the possible arguments for Bernoulli 231.
							if(state.fixedFlag$sample57) {
								int var183 = state.st[sample$var196][0];
								if(((0 <= var183) && (var183 < state.noStates))) {
									// Substituted "timeStep$var226" with its value "0".
									double var230 = state.current_metric_valid_bias[server][state.st[sample$var196][0]];
									
									// Store the value of the function call, so the function call is only made once.
									cv$distributionAccumulator = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
									
									// Add the probability of this distribution configuration to the accumulator.
									// 
									// An accumulator for the distributed probability space covered.
									cv$probabilityReached = 1.0;
								}
							} else {
								// Enumerating the possible outputs of Categorical 54.
								for(int index$sample57$4 = 0; index$sample57$4 < state.noStates; index$sample57$4 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "sample$var45" with its value "sample$var196".
									double cv$probabilitySample57Value5 = state.distribution$sample57[sample$var196][index$sample57$4];
									double var230 = state.current_metric_valid_bias[server][index$sample57$4];
									
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
						
						// Enumerating the possible arguments for Bernoulli 231.
						if((1 <= timeStep$var226)) {
							// Enumerating the possible arguments for Bernoulli 231.
							if(state.fixedFlag$sample76) {
								int var183 = state.st[sample$var196][timeStep$var226];
								if(((0 <= var183) && (var183 < state.noStates))) {
									double var230 = state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
									
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
								for(int index$sample76$13 = 0; index$sample76$13 < state.noStates; index$sample76$13 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
																											// Substituted "sample$var45" with its value "sample$var196".
									double cv$probabilitySample76Value14 = state.distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$13];
									double var230 = state.current_metric_valid_bias[server][index$sample76$13];
									
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
						
						// Store the sample task probability
						state.logProbability$sample241[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
					}
				}
			}
			
			// Update the variable probability
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			
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
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1)
						cv$accumulator = (cv$accumulator + state.logProbability$sample241[sample$var196][server][timeStep$var226]);
				}
			}
			
			// Update the variable probability
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			
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
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
							// An accumulator for log probabilities.
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							
							// An accumulator for the distributed probability space covered.
							double cv$probabilityReached = 0.0;
							
							// Look for paths between the variable and the sample task 256 including any distribution
							// values.
							// 
							// The sample value to calculate the probability of generating
							double cv$sampleValue = state.var245[sample$var196][server][timeStep$var226];
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((0 == timeStep$var226)) {
								// Enumerating the possible arguments for Gaussian 244.
								// 
								// Enumerating the possible arguments for Gaussian 244.
								if(state.fixedFlag$sample57) {
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((0 <= state.st[sample$var196][0])) {
										int var129 = state.st[sample$var196][0];
										if(((0 <= var129) && (var129 < state.noStates))) {
											// Substituted "timeStep$var226" with its value "0".
											double var243 = state.current_metric_var[server][state.st[sample$var196][0]];
											
											// Store the value of the function call, so the function call is only made once.
											// 
											// Substituted "timeStep$var226" with its value "0".
											cv$distributionAccumulator = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.current_metric_mean[server][state.st[sample$var196][0]]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
											
											// Add the probability of this distribution configuration to the accumulator.
											// 
											// An accumulator for the distributed probability space covered.
											cv$probabilityReached = 1.0;
										}
									}
								} else {
									// Enumerating the possible outputs of Categorical 54.
									for(int index$sample57$4 = 0; index$sample57$4 < state.noStates; index$sample57$4 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "sample$var45" with its value "sample$var196".
										double cv$probabilitySample57Value5 = state.distribution$sample57[sample$var196][index$sample57$4];
										double var243 = state.current_metric_var[server][index$sample57$4];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.current_metric_mean[server][index$sample57$4]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
										
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
							
							// Enumerating the possible arguments for Gaussian 244.
							if((1 <= timeStep$var226)) {
								// Enumerating the possible arguments for Gaussian 244.
								if(state.fixedFlag$sample76) {
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((0 <= state.st[sample$var196][timeStep$var226])) {
										int var129 = state.st[sample$var196][timeStep$var226];
										if(((0 <= var129) && (var129 < state.noStates))) {
											double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
											
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
									// Enumerating the possible outputs of Categorical 73.
									for(int index$sample76$49 = 0; index$sample76$49 < state.noStates; index$sample76$49 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
																														// Substituted "sample$var45" with its value "sample$var196".
										double cv$probabilitySample76Value50 = state.distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$49];
										double var243 = state.current_metric_var[server][index$sample76$49];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(cv$probabilitySample76Value50) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.current_metric_mean[server][index$sample76$49]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
										
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
							
							// Store the sample task probability
							state.logProbability$sample256[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
						}
					}
				}
			}
			
			// Update the variable probability
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			
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
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226])
							cv$accumulator = (cv$accumulator + state.logProbability$sample256[sample$var196][server][timeStep$var226]);
					}
				}
			}
			
			// Update the variable probability
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			
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
				for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = state.st[sample$var45][0];
					
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
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$sampleValue])) && (state.initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					state.logProbability$sample57[sample$var45] = cv$distributionAccumulator;
				}
				
				// Update the variable probability
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
				
				// Add probability to model
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample57" with its value "true".
				state.fixedProbFlag$sample57 = state.fixedFlag$sample20;
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample57[sample$var45]);
			
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
				for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
					for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// The sample value to calculate the probability of generating
						int cv$sampleValue = state.st[sample$var45][timeStep$var66];
						
						// Enumerating the possible arguments for Categorical 73.
						if((1 == timeStep$var66)) {
							// Enumerating the possible arguments for Categorical 73.
							if(state.fixedFlag$sample57) {
								int var32 = state.st[sample$var45][0];
								
																								// Substituted "timeStep$var66" with its value "1".
								if(((0 <= var32) && (var32 < state.noStates))) {
									// Substituted "timeStep$var66" with its value "1".
									double[] var72 = state.m[state.st[sample$var45][0]];
									
									// Store the value of the function call, so the function call is only made once.
									cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									
									// Add the probability of this distribution configuration to the accumulator.
									// 
									// An accumulator for the distributed probability space covered.
									cv$probabilityReached = 1.0;
								}
							} else {
								// Enumerating the possible outputs of Categorical 54.
								for(int index$sample57$6 = 0; index$sample57$6 < state.noStates; index$sample57$6 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$5" with its value "sample$var45".
									double cv$probabilitySample57Value7 = state.distribution$sample57[sample$var45][index$sample57$6];
									double[] var72 = state.m[index$sample57$6];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample57Value7) + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									
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
						
						// Substituted "index$sample$13_1" with its value "sample$var45".
						// 
						// Substituted "index$timeStep$13_2" with its value "(timeStep$var66 - 1)".
						if((2 <= timeStep$var66)) {
							int var32 = state.st[sample$var45][(timeStep$var66 - 1)];
							if(((0 <= var32) && (var32 < state.noStates))) {
								double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								
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
						
						// Store the sample task probability
						state.logProbability$sample76[sample$var45][(timeStep$var66 - 1)] = cv$distributionAccumulator;
					}
				}
				
				// Update the variable probability
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
				
				// Add probability to model
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample76" with its value "true".
				state.fixedProbFlag$sample76 = (state.fixedFlag$sample33 && state.fixedFlag$sample57);
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample76[sample$var45][(timeStep$var66 - 1)]);
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
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				for(int var129 = 0; var129 < state.noStates; var129 += 1) {
					// The sample value to calculate the probability of generating
					double cv$sampleValue = state.current_metric_mean[var119][var129];
					
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
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < (double)state.max_metric))?(-Math.log(state.max_metric)):Double.NEGATIVE_INFINITY));
				}
			}
			
			// Store the random variable instance probability
			state.logProbability$var130 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$current_metric_mean = (state.logProbability$current_metric_mean + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample134)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample134 = state.fixedFlag$sample134;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$current_metric_mean = (state.logProbability$current_metric_mean + state.logProbability$var130);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var130);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample134)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var130);
		}
	}

	// Calculate the probability of the samples represented by sample162 using sampled
	// values.
	private final void logProbabilityValue$sample162() {
		// Determine if we need to calculate the values for sample task 162 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample162) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				for(int var156 = 0; var156 < state.noStates; var156 += 1)
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
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(state.current_metric_var[var146][var156], 1.0, 1.0));
			}
			
			// Store the random variable instance probability
			state.logProbability$var157 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$current_metric_var = (state.logProbability$current_metric_var + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample162)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample162 = state.fixedFlag$sample162;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$current_metric_var = (state.logProbability$current_metric_var + state.logProbability$var157);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var157);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample162)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var157);
		}
	}

	// Calculate the probability of the samples represented by sample190 using sampled
	// values.
	private final void logProbabilityValue$sample190() {
		// Determine if we need to calculate the values for sample task 190 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample190) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				for(int var183 = 0; var183 < state.noStates; var183 += 1)
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
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(state.current_metric_valid_bias[var173][var183], 1.0, 1.0));
			}
			
			// Store the random variable instance probability
			state.logProbability$var184 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$current_metric_valid_bias = (state.logProbability$current_metric_valid_bias + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample190)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample190 = state.fixedFlag$sample190;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$current_metric_valid_bias = (state.logProbability$current_metric_valid_bias + state.logProbability$var184);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var184);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample190)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var184);
		}
	}

	// Calculate the probability of the samples represented by sample20 using sampled
	// values.
	private final void logProbabilityValue$sample20() {
		// Determine if we need to calculate the values for sample task 20 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample20) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.initialStateDistribution, state.v, state.noStates);
			
			// Store the sample task probability
			state.logProbability$initialStateDistribution = cv$distributionAccumulator;
			
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
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample20)
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
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample20 = state.fixedFlag$sample20;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$initialStateDistribution);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample20)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$initialStateDistribution);
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
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double var230 = state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]];
						
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
						double cv$distributionAccumulator = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
						
						// Add the probability of this instance of the random variable to the probability
						// of all instances of the random variable.
						// 
						// Add the probability of this sample task to the sample task accumulator.
						// 
						// Accumulator for sample probabilities for a specific instance of the random variable.
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						
						// Store the sample task probability
						state.logProbability$sample241[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
					}
				}
			}
			
			// Update the variable probability
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			
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
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1)
						cv$accumulator = (cv$accumulator + state.logProbability$sample241[sample$var196][server][timeStep$var226]);
				}
			}
			
			// Update the variable probability
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			
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
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
							double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
							
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
							double cv$distributionAccumulator = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][server][timeStep$var226] - state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Add the probability of this instance of the random variable to the probability
							// of all instances of the random variable.
							// 
							// Add the probability of this sample task to the sample task accumulator.
							// 
							// Accumulator for sample probabilities for a specific instance of the random variable.
							cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
							
							// Store the sample task probability
							state.logProbability$sample256[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
						}
					}
				}
			}
			
			// Update the variable probability
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			
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
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226])
							cv$accumulator = (cv$accumulator + state.logProbability$sample256[sample$var196][server][timeStep$var226]);
					}
				}
			}
			
			// Update the variable probability
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			
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
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var32 = 0; var32 < state.noStates; var32 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.m[var32], state.v, state.noStates));
			
			// Store the random variable instance probability
			state.logProbability$var33 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$m = (state.logProbability$m + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample33)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample33 = state.fixedFlag$sample33;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$m = (state.logProbability$m + state.logProbability$var33);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var33);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample33)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var33);
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
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.st[sample$var45][0];
				
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
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$sampleValue])) && (state.initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample57[sample$var45] = cv$distributionAccumulator;
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
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample57[sample$var45]);
			
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
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = state.st[sample$var45][timeStep$var66];
					double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
					
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
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					state.logProbability$sample76[sample$var45][(timeStep$var66 - 1)] = cv$distributionAccumulator;
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
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample76[sample$var45][(timeStep$var66 - 1)]);
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

<<<<<<< Upstream, based on origin/Adding_types_to_variables_descriptions_so_that_global_local_and_scratch_accesses_can_be_separated
<<<<<<< Upstream, based on origin/Adding_types_to_variables_descriptions_so_that_global_local_and_scratch_accesses_can_be_separated
<<<<<<< Renaming_functions
	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 134 drawn from Uniform 108. Inference was performed using Metropolis-Hastings.
	private final void sample134(int var119, int var129) {
		constrainedFlag$sample134[var119][var129] = false;
		
		// The original value of the sample
		double cv$originalValue = current_metric_mean[var119][var129];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.01))
			cv$var = 0.01;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Constructing a random variable input for use later.
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue < (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
			
			// Looking for a path between Sample 134 and consumer Gaussian 244.
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 < length$metric[sample$var196][0]) && metric_valid_g[sample$var196][var119][0])) {
					if(fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var129 == st[sample$var196][0])) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample134[var119][var129] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var156 = st[sample$var196][0];
							
							// Substituted "server" with its value "var119".
							if(((0 <= var156) && (var156 < noStates))) {
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								double var243 = current_metric_var[var119][st[sample$var196][0]];
								
								// Substituted "server" with its value "var119".
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
								
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
						}
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "sample$var45" with its value "sample$var196".
						// 
						// Substituted "index$sample57$7" with its value "var129".
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][var129];
						
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample134[var119][var129] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var119".
						// 
						// Substituted "index$sample57$7" with its value "var129".
						double var243 = current_metric_var[var119][var129];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "server" with its value "var119".
						// 
						// Set the current value to the current state of the tree.
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample57$7" with its value "var129".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample57$7" with its value "var129".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample57$7" with its value "var129".
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					// Substituted "server" with its value "var119".
					if(metric_valid_g[sample$var196][var119][timeStep$var226]) {
						if(fixedFlag$sample76) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var129 == st[sample$var196][timeStep$var226])) {
								// Mark that the sample has observed constrained data.
								constrainedFlag$sample134[var119][var129] = true;
								
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var156 = st[sample$var196][timeStep$var226];
								
								// Substituted "server" with its value "var119".
								if(((0 <= var156) && (var156 < noStates))) {
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var119".
									double var243 = current_metric_var[var119][st[sample$var196][timeStep$var226]];
									
									// Substituted "server" with its value "var119".
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
									
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
							}
						} else {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "sample$var45" with its value "sample$var196".
							// 
							// Substituted "index$sample76$19" with its value "var129".
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][var129];
							
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample134[var119][var129] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample76$19" with its value "var129".
							double var243 = current_metric_var[var119][var129];
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Set the current value to the current state of the tree.
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								// 
								// Substituted "index$sample76$19" with its value "var129".
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								// 
								// Substituted "index$sample76$19" with its value "var129".
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								// 
								// Substituted "index$sample76$19" with its value "var129".
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
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(constrainedFlag$sample134[var119][var129]) {
			current_metric_mean[var119][var129] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
			
			// Looking for a path between Sample 134 and consumer Gaussian 244.
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 < length$metric[sample$var196][0]) && metric_valid_g[sample$var196][var119][0])) {
					if(fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var129 == st[sample$var196][0])) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample134[var119][var129] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var156 = st[sample$var196][0];
							
							// Substituted "server" with its value "var119".
							if(((0 <= var156) && (var156 < noStates))) {
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								double var243 = current_metric_var[var119][st[sample$var196][0]];
								
								// Substituted "server" with its value "var119".
								cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
								
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
						}
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "sample$var45" with its value "sample$var196".
						// 
						// Substituted "index$sample57$7" with its value "var129".
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][var129];
						
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample134[var119][var129] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var119".
						// 
						// Substituted "index$sample57$7" with its value "var129".
						double var243 = current_metric_var[var119][var129];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "server" with its value "var119".
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample57$7" with its value "var129".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample57$7" with its value "var129".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample57$7" with its value "var129".
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					// Substituted "server" with its value "var119".
					if(metric_valid_g[sample$var196][var119][timeStep$var226]) {
						if(fixedFlag$sample76) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var129 == st[sample$var196][timeStep$var226])) {
								// Mark that the sample has observed constrained data.
								constrainedFlag$sample134[var119][var129] = true;
								
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var156 = st[sample$var196][timeStep$var226];
								
								// Substituted "server" with its value "var119".
								if(((0 <= var156) && (var156 < noStates))) {
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var119".
									double var243 = current_metric_var[var119][st[sample$var196][timeStep$var226]];
									
									// Substituted "server" with its value "var119".
									cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
									
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
							}
						} else {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "sample$var45" with its value "sample$var196".
							// 
							// Substituted "index$sample76$19" with its value "var129".
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][var129];
							
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample134[var119][var129] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var119".
							// 
							// Substituted "index$sample76$19" with its value "var129".
							double var243 = current_metric_var[var119][var129];
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "server" with its value "var119".
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								// 
								// Substituted "index$sample76$19" with its value "var129".
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								// 
								// Substituted "index$sample76$19" with its value "var129".
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var119".
								// 
								// Substituted "index$sample76$19" with its value "var129".
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
					}
				}
			}
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				current_metric_mean[var119][var129] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 162 drawn from InverseGamma 135. Inference was performed using Metropolis-Hastings.
	private final void sample162(int var146, int var156) {
		constrainedFlag$sample162[var146][var156] = false;
		
		// The original value of the sample
		double cv$originalValue = current_metric_var[var146][var156];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.01))
			cv$var = 0.01;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			
			// Looking for a path between Sample 162 and consumer Gaussian 244.
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 < length$metric[sample$var196][0]) && metric_valid_g[sample$var196][var146][0])) {
					if(fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var156 == st[sample$var196][0])) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample162[var146][var156] = true;
							
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
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][st[sample$var196][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
								
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
						}
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "sample$var45" with its value "sample$var196".
						// 
						// Substituted "index$sample57$7" with its value "var156".
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][var156];
						
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample162[var146][var156] = true;
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "server" with its value "var146".
						// 
						// Set the current value to the current state of the tree.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var146".
						// 
						// Substituted "index$sample57$7" with its value "var156".
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][var156]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
						
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
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					// Substituted "server" with its value "var146".
					if(metric_valid_g[sample$var196][var146][timeStep$var226]) {
						if(fixedFlag$sample76) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var156 == st[sample$var196][timeStep$var226])) {
								// Mark that the sample has observed constrained data.
								constrainedFlag$sample162[var146][var156] = true;
								
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
									// Set the current value to the current state of the tree.
									// 
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var146".
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
									
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
							}
						} else {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "sample$var45" with its value "sample$var196".
							// 
							// Substituted "index$sample76$19" with its value "var156".
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][var156];
							
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample162[var146][var156] = true;
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "server" with its value "var146".
							// 
							// Set the current value to the current state of the tree.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var146".
							// 
							// Substituted "index$sample76$19" with its value "var156".
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][var156]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
							
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
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(constrainedFlag$sample162[var146][var156]) {
			current_metric_var[var146][var156] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
			
			// Looking for a path between Sample 162 and consumer Gaussian 244.
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 < length$metric[sample$var196][0]) && metric_valid_g[sample$var196][var146][0])) {
					if(fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var156 == st[sample$var196][0])) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample162[var146][var156] = true;
							
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
								// Constructing a random variable input for use later.
								// 
								// Substituted "server" with its value "var146".
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][st[sample$var196][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
								
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
						}
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "sample$var45" with its value "sample$var196".
						// 
						// Substituted "index$sample57$7" with its value "var156".
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][var156];
						
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample162[var146][var156] = true;
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "server" with its value "var146".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "server" with its value "var146".
						// 
						// Substituted "index$sample57$7" with its value "var156".
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][var156]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 256 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
						
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
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					// Substituted "server" with its value "var146".
					if(metric_valid_g[sample$var196][var146][timeStep$var226]) {
						if(fixedFlag$sample76) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var156 == st[sample$var196][timeStep$var226])) {
								// Mark that the sample has observed constrained data.
								constrainedFlag$sample162[var146][var156] = true;
								
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
									// Constructing a random variable input for use later.
									// 
									// Substituted "server" with its value "var146".
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
									
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
							}
						} else {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "sample$var45" with its value "sample$var196".
							// 
							// Substituted "index$sample76$19" with its value "var156".
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][var156];
							
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample162[var146][var156] = true;
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "server" with its value "var146".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "server" with its value "var146".
							// 
							// Substituted "index$sample76$19" with its value "var156".
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][var156]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
							
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
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				current_metric_var[var146][var156] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 190 drawn from Beta 162. Inference was performed using a Beta to
	// Bernoulli/Binomial conjugate prior.
	private final void sample190(int var173, int var183) {
		constrainedFlag$sample190[var173][var183] = false;
		
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		
		// Processing random variable 231.
		// 
		// Looking for a path between Sample 190 and consumer Bernoulli 231.
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < length$metric[sample$var196][0])) {
				if(fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var183 == st[sample$var196][0])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample190[var173][var183] = true;
						
						// Include the value sampled by task 241 from random variable var231.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						// 
						// Substituted "server" with its value "var173".
						if(metric_valid_g[sample$var196][var173][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "sample$var45" with its value "sample$var196".
					// 
					// Substituted "index$sample57$6" with its value "var183".
					double cv$probabilitySample57Value7 = distribution$sample57[sample$var196][var183];
					
					// Processing sample task 241 of consumer random variable null.
					// 
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample190[var173][var183] = true;
					
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
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
				if(fixedFlag$sample76) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var183 == st[sample$var196][timeStep$var226])) {
						// Processing sample task 241 of consumer random variable null.
						// 
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample190[var173][var183] = true;
						
						// Include the value sampled by task 241 from random variable var231.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						// 
						// Substituted "server" with its value "var173".
						if(metric_valid_g[sample$var196][var173][timeStep$var226])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "sample$var45" with its value "sample$var196".
					// 
					// Substituted "index$sample76$18" with its value "var183".
					double cv$probabilitySample76Value19 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][var183];
					
					// Processing sample task 241 of consumer random variable null.
					// 
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample190[var173][var183] = true;
					
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
		if(constrainedFlag$sample190[var173][var183])
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			current_metric_valid_bias[var173][var183] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 20 drawn from Dirichlet 19. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample20() {
		constrainedFlag$sample20 = false;
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var20$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample57) {
			// Processing random variable 54.
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				// Processing sample task 57 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample20 = true;
				
				// Increment the sample counter with the value sampled by sample task 57 of random
				// variable var54
				// 
				// A local reference to the scratch space.
				cv$var20$countGlobal[st[sample$var45][0]] = (cv$var20$countGlobal[st[sample$var45][0]] + 1.0);
			}
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
		if(constrainedFlag$sample20)
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
		constrainedFlag$sample33[var32] = false;
		
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
				if((1 < length$metric[sample$var45][0])) {
					if(fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var32 == st[sample$var45][0])) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample33[var32] = true;
							
							// Increment the sample counter with the value sampled by sample task 76 of random
							// variable var73
							// 
							// A local reference to the scratch space.
							cv$var33$countGlobal[st[sample$var45][1]] = (cv$var33$countGlobal[st[sample$var45][1]] + 1.0);
						}
					} else {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample33[var32] = true;
						
						// Increment the sample counter with the value sampled by sample task 76 of random
						// variable var73
						// 
						// A local reference to the scratch space.
						// 
						// Substituted "index$sample57$5" with its value "var32".
						cv$var33$countGlobal[st[sample$var45][1]] = (cv$var33$countGlobal[st[sample$var45][1]] + distribution$sample57[sample$var45][var32]);
					}
				}
			}
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 2; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
						// Processing sample task 76 of consumer random variable null.
						// 
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample33[var32] = true;
						
						// Increment the sample counter with the value sampled by sample task 76 of random
						// variable var73
						// 
						// A local reference to the scratch space.
						cv$var33$countGlobal[st[sample$var45][timeStep$var66]] = (cv$var33$countGlobal[st[sample$var45][timeStep$var66]] + 1.0);
					}
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
				if((1 < length$metric[sample$var45][0])) {
					if(fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var32 == st[sample$var45][0])) {
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
						}
					} else {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Substituted "index$sample$41" with its value "sample$var45".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample57$42" with its value "var32".
						double cv$distributionProbability = distribution$sample57[sample$var45][var32];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							// A local reference to the scratch space.
							cv$var33$countGlobal[cv$loopIndex] = (cv$var33$countGlobal[cv$loopIndex] + (distribution$sample76[sample$var45][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					int index$timeStep$52 = (timeStep$var66 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$sample$51" with its value "sample$var45".
					if((1 <= index$timeStep$52)) {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Substituted "index$sample$51" with its value "sample$var45".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample76$53" with its value "var32".
						double cv$distributionProbability = distribution$sample76[sample$var45][(index$timeStep$52 - 1)][var32];
						
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
		if(constrainedFlag$sample33[var32])
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
		constrainedFlag$sample57[sample$var45] = false;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$numStates's comment
		// Calculate the number of states to evaluate.
		int cv$numStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < noStates) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$valuePos])) && (initialStateDistribution[cv$valuePos] <= 1.0))?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample76 && (1 < length$metric[sample$var45][0]))) {
				// Looking for a path between Sample 57 and consumer Categorical 73.
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample57[sample$var45] = true;
				
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Categorical 73 which is consuming
				// the output of Sample task 57.
				// 
				// Value of the variable at this index
				if((cv$valuePos < noStates)) {
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					double[] var72 = m[cv$valuePos];
					
					// Substituted "index$sample$2_2" with its value "sample$var45".
					cv$accumulatedConsumerProbabilities = (((((0.0 <= st[sample$var45][1]) && (st[sample$var45][1] < noStates)) && (0.0 <= var72[st[sample$var45][1]])) && (var72[st[sample$var45][1]] <= 1.0))?Math.log(var72[st[sample$var45][1]]):Double.NEGATIVE_INFINITY);
					
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
				// Looking for a path between Sample 57 and consumer Bernoulli 231.
				for(int server = 0; server < noServers; server += 1) {
					// Processing sample task 241 of consumer random variable null.
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample57[sample$var45] = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Value of the variable at this index
					if((cv$valuePos < noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var230 = current_metric_valid_bias[server][cv$valuePos];
						
						// Substituted "sample$var196" with its value "sample$var45".
						cv$accumulatedConsumerProbabilities = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((metric_valid_g[sample$var45][server][0]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
						
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
						
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample57[sample$var45] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((cv$valuePos < noStates)) {
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var243 = current_metric_var[server][cv$valuePos];
							
							// Substituted "sample$var196" with its value "sample$var45".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
							
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
						
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample57[sample$var45] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Value of the variable at this index
						if((cv$valuePos < noStates)) {
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var243 = current_metric_var[server][cv$valuePos];
							
							// Substituted "sample$var196" with its value "sample$var45".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
							
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
				
				// Enumerating the possible arguments for the variable Categorical 73 which is consuming
				// the output of Sample task 57.
				// 
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
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var73, 1.0, m[cv$valuePos], noStates);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$sample$66_2" with its value "sample$var45".
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
		if(constrainedFlag$sample57[sample$var45]) {
			// Set the calculated probabilities to be the distribution values, and normalize
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
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
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
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
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
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Get a local reference to the scratch space.
					cv$localProbability[cv$indexName] = Math.exp((cv$var55$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var55$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 76 drawn from Categorical 73. Inference was performed using variable
	// marginalization.
	private final void sample76(int sample$var45, int timeStep$var66) {
		constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = false;
		
		// Calculate the number of states to evaluate.
		int cv$numStates = 0;
		
		// Exploring all the possible state counts for random variable 73.
		// 
		// Enumerating the possible arguments for Categorical 73.
		if((1 == timeStep$var66)) {
			// Exploring all the possible state counts for random variable 73.
			// 
			// Enumerating the possible arguments for Categorical 73.
			if(fixedFlag$sample57) {
				int var32 = st[sample$var45][0];
				
				// Substituted "timeStep$var66" with its value "1".
				if(((0 <= var32) && (var32 < noStates)))
					// variable marginalization
					// 
					// cv$numStates's comment
					// Calculate the number of states to evaluate.
					cv$numStates = Math.max(0, noStates);
			} else {
				// Enumerating the possible outputs of Categorical 54.
				if((0 < noStates))
					// variable marginalization
					cv$numStates = noStates;
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < noStates)) {
			int index$timeStep$13 = (timeStep$var66 - 1);
			
			// index$sample$2's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$sample$12" with its value "sample$var45".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var66 - 1)".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var66 - 1)".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var66 - 1)".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var66 - 1)".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var66 - 1)".
			// 
			// Substituted "index$timeStep$13" with its value "(timeStep$var66 - 1)".
			if(((1 <= index$timeStep$13) && !(index$timeStep$13 == timeStep$var66)))
				// variable marginalization
				cv$numStates = noStates;
		}
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
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "timeStep$var66" with its value "1".
						double[] var72 = m[st[sample$var45][0]];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = ((((cv$valuePos < noStates) && (0.0 <= var72[cv$valuePos])) && (var72[cv$valuePos] <= 1.0))?Math.log(var72[cv$valuePos]):Double.NEGATIVE_INFINITY);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < length$metric[sample$var45][0])) {
							// Looking for a path between Sample 76 and consumer Bernoulli 231.
							for(int server = 0; server < noServers; server += 1) {
								// Processing sample task 241 of consumer random variable null.
								// Mark that the sample has observed constrained data.
								// 
								// Substituted "timeStep$var66" with its value "1".
								constrainedFlag$sample76[sample$var45][0] = true;
								
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Value of the variable at this index
								if((cv$valuePos < noStates)) {
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var230 = current_metric_valid_bias[server][cv$valuePos];
									
									// Substituted "sample$var196" with its value "sample$var45".
									// 
									// Substituted "timeStep$var226" with its value "1".
									cv$accumulatedConsumerProbabilities = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((metric_valid_g[sample$var45][server][1]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
									
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
									
									// Mark that the sample has observed constrained data.
									// 
									// Substituted "timeStep$var66" with its value "1".
									constrainedFlag$sample76[sample$var45][0] = true;
									
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Value of the variable at this index
									if((cv$valuePos < noStates)) {
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double var243 = current_metric_var[server][cv$valuePos];
										
										// Substituted "sample$var196" with its value "sample$var45".
										// 
										// Substituted "timeStep$var226" with its value "1".
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
										
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
									
									// Mark that the sample has observed constrained data.
									// 
									// Substituted "timeStep$var66" with its value "1".
									constrainedFlag$sample76[sample$var45][0] = true;
									
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((cv$valuePos < noStates)) {
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double var243 = current_metric_var[server][cv$valuePos];
										
										// Substituted "sample$var196" with its value "sample$var45".
										// 
										// Substituted "timeStep$var226" with its value "1".
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
										
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
								}
							}
						}
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					// Enumerating the possible outputs of Categorical 54.
					for(int index$sample57$22 = 0; index$sample57$22 < noStates; index$sample57$22 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$21" with its value "sample$var45".
						double cv$probabilitySample57Value23 = distribution$sample57[sample$var45][index$sample57$22];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value23);
						
						// Constructing a random variable input for use later.
						double[] var72 = m[index$sample57$22];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value23) + (((0.0 <= var72[cv$valuePos]) && (var72[cv$valuePos] <= 1.0))?Math.log(var72[cv$valuePos]):Double.NEGATIVE_INFINITY));
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < length$metric[sample$var45][0])) {
							// Looking for a path between Sample 76 and consumer Bernoulli 231.
							for(int server = 0; server < noServers; server += 1) {
								// Processing sample task 241 of consumer random variable null.
								// Mark that the sample has observed constrained data.
								// 
								// Substituted "timeStep$var66" with its value "1".
								constrainedFlag$sample76[sample$var45][0] = true;
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var230 = current_metric_valid_bias[server][cv$valuePos];
								
								// A check to ensure rounding of floating point values can never result in a negative
								// value.
								// 
								// Recorded the probability of reaching sample task 241 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								// 
								// Variable declaration of cv$accumulatedConsumerProbabilities moved.
								// Declaration comment was:
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								// 
								// Substituted "sample$var196" with its value "sample$var45".
								// 
								// Substituted "timeStep$var226" with its value "1".
								cv$accumulatedProbabilities = ((((0.0 <= var230) && (var230 <= 1.0))?Math.log((metric_valid_g[sample$var45][server][1]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
									
									// Mark that the sample has observed constrained data.
									// 
									// Substituted "timeStep$var66" with its value "1".
									constrainedFlag$sample76[sample$var45][0] = true;
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var243 = current_metric_var[server][cv$valuePos];
									
									// A check to ensure rounding of floating point values can never result in a negative
									// value.
									// 
									// Recorded the probability of reaching sample task 256 with the current configuration.
									// 
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									// 
									// Variable declaration of cv$accumulatedConsumerProbabilities moved.
									// Declaration comment was:
									// This value is not used before it is set again, so removing the value declaration.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									// 
									// Substituted "sample$var196" with its value "sample$var45".
									// 
									// Substituted "timeStep$var226" with its value "1".
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
									
									// Mark that the sample has observed constrained data.
									// 
									// Substituted "timeStep$var66" with its value "1".
									constrainedFlag$sample76[sample$var45][0] = true;
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var243 = current_metric_var[server][cv$valuePos];
									
									// A check to ensure rounding of floating point values can never result in a negative
									// value.
									// 
									// Recorded the probability of reaching sample task 256 with the current configuration.
									// 
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									// 
									// Variable declaration of cv$accumulatedConsumerProbabilities moved.
									// Declaration comment was:
									// This value is not used before it is set again, so removing the value declaration.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									// 
									// Substituted "sample$var196" with its value "sample$var45".
									// 
									// Substituted "timeStep$var226" with its value "1".
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$timeStep$30 = (timeStep$var66 - 1);
			
			// index$sample$2's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$sample$29" with its value "sample$var45".
			// 
			// Substituted "index$timeStep$30" with its value "(timeStep$var66 - 1)".
			// 
			// Substituted "index$timeStep$30" with its value "(timeStep$var66 - 1)".
			// 
			// Substituted "index$timeStep$30" with its value "(timeStep$var66 - 1)".
			// 
			// Substituted "index$timeStep$30" with its value "(timeStep$var66 - 1)".
			// 
			// Substituted "index$timeStep$30" with its value "(timeStep$var66 - 1)".
			// 
			// Substituted "index$timeStep$30" with its value "(timeStep$var66 - 1)".
			if(((1 <= index$timeStep$30) && !(index$timeStep$30 == timeStep$var66))) {
				// Enumerating the possible outputs of Categorical 73.
				for(int index$sample76$31 = 0; index$sample76$31 < noStates; index$sample76$31 += 1) {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$29" with its value "sample$var45".
					double cv$probabilitySample76Value32 = distribution$sample76[sample$var45][(index$timeStep$30 - 1)][index$sample76$31];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample76Value32);
					
					// Constructing a random variable input for use later.
					double[] var72 = m[index$sample76$31];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample76Value32) + (((0.0 <= var72[cv$valuePos]) && (var72[cv$valuePos] <= 1.0))?Math.log(var72[cv$valuePos]):Double.NEGATIVE_INFINITY));
					for(int server = 0; server < noServers; server += 1) {
						// Processing sample task 241 of consumer random variable null.
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = true;
						
						// Constructing a random variable input for use later.
						double var230 = current_metric_valid_bias[server][index$sample76$31];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 241 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "sample$var196" with its value "sample$var45".
						cv$accumulatedProbabilities = ((((0.0 <= var230) && (var230 <= 1.0))?Math.log((metric_valid_g[sample$var45][server][timeStep$var66]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
							
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var243 = current_metric_var[server][cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "sample$var196" with its value "sample$var45".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][timeStep$var66] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
							
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var243 = current_metric_var[server][cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 256 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "sample$var196" with its value "sample$var45".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][timeStep$var66] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$timeStep$265_3 = (timeStep$var66 + 1);
			if((index$timeStep$265_3 < length$metric[sample$var45][0])) {
				// Processing sample task 76 of consumer random variable null.
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var73[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Enumerating the possible arguments for the variable Categorical 73 which is consuming
				// the output of Sample task 76.
				// 
				// Processing random variable 73.
				// 
				// Looking for a path between Sample 76 and consumer Categorical 73.
				// 
				// Value of the variable at this index
				if((cv$valuePos < noStates)) {
					// Declare and zero an accumulator for tracking the reached source probability space.
					double scopeVariable$reachedSourceProbability = 0.0;
					
					// Enumerating the possible arguments for Categorical 73.
					if((1 == timeStep$var66)) {
						// Enumerating the possible arguments for Categorical 73.
						if(fixedFlag$sample57) {
							int index$var32$276_1 = st[sample$var45][0];
							
							// Substituted "timeStep$var66" with its value "1".
							if(((0 <= index$var32$276_1) && (index$var32$276_1 < noStates)))
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 54.
							for(int index$sample57$272 = 0; index$sample57$272 < noStates; index$sample57$272 += 1)
								// Add the probability of this argument configuration.
								// 
								// cv$probabilitySample57Value273's comment
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$271" with its value "sample$var45".
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample57[sample$var45][index$sample57$272]);
						}
					}
					int index$timeStep$280 = (timeStep$var66 - 1);
					
					// index$timeStep$267's comment
					// Copy of index so that its values can be safely substituted
					// 
					// index$sample$268's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "index$sample$265_2" with its value "sample$var45".
					// 
					// Substituted "index$timeStep$265_3" with its value "(timeStep$var66 + 1)".
					// 
					// Substituted "index$timeStep$265_3" with its value "(timeStep$var66 + 1)".
					// 
					// Substituted "index$timeStep$265_3" with its value "(timeStep$var66 + 1)".
					// 
					// Substituted "index$timeStep$265_3" with its value "(timeStep$var66 + 1)".
					// 
					// Substituted "index$timeStep$265_3" with its value "(timeStep$var66 + 1)".
					// 
					// Substituted "index$timeStep$265_3" with its value "(timeStep$var66 + 1)".
					if((((1 <= index$timeStep$280) && !(index$timeStep$280 == timeStep$var66)) && !(index$timeStep$280 == index$timeStep$265_3))) {
						// Enumerating the possible outputs of Categorical 73.
						for(int index$sample76$281 = 0; index$sample76$281 < noStates; index$sample76$281 += 1)
							// Add the probability of this argument configuration.
							// 
							// cv$probabilitySample76Value282's comment
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$279" with its value "sample$var45".
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample76[sample$var45][(index$timeStep$280 - 1)][index$sample76$281]);
					}
					
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Zero an accumulator to track the probabilities reached.
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					
					// Add the current distribution to the distribution accumulator.
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
				// Substituted "index$sample$265_2" with its value "sample$var45".
				double[] cv$sampleDistribution = distribution$sample76[sample$var45][(index$timeStep$265_3 - 1)];
				
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
		if(constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)]) {
			// Set the calculated probabilities to be the distribution values, and normalize
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
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
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
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
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
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Get a local reference to the scratch space.
					cv$localProbability[cv$indexName] = Math.exp((cv$var74$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var74$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

=======
>>>>>>> 36e6d3b Restructuring naming and renaming functions, this does result in them changing location in the generated source. This is done to allow the addition of unconstrained variables to be completed by adding in the methods to sample the unconstrained values.
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

=======
>>>>>>> 0cb92c4 Adding in a class to hold just the state. This will be worked on further as the code generation progresses. Commit before adding inner classes to the outer classes. Updating output class structure checkpoint Checkpoint in the restructuring of the output classes to increase the shared code. Finished restructuring the classes, time to start using inner classes. Updates to tree structure Changing the structure of get field so that it can be used to get other types of field, read for getting data out of the scratch and model data classes. Removing unused imports Adding nodes to allow fields in an object ot be set. Moving rng package so that we can add other internal only variable types. Updates to the handling of transformations. Moving from sets to lists of generics Updating the structure of inner class. Changing the passing of fields to sub classes. Updating class structure
	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocate() {
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
		
		// Constructor for distribution$sample76
		distribution$sample76 = new double[length$metric.length][][];
		for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1) {
			double[][] subarray$0 = new double[(length$metric[sample$var45][0] - 1)][];
			distribution$sample76[sample$var45] = subarray$0;
			for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
				subarray$0[(timeStep$var66 - 1)] = new double[noStates];
		}
		
		// Constructor for distribution$sample57
		distribution$sample57 = new double[length$metric.length][];
		for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
			distribution$sample57[sample$var45] = new double[noStates];
		
		// Constructor for constrainedFlag$sample190
		constrainedFlag$sample190 = new boolean[length$metric[0].length][];
		for(int var173 = 0; var173 < length$metric[0].length; var173 += 1)
			constrainedFlag$sample190[var173] = new boolean[noStates];
		
		// Constructor for constrainedFlag$sample76
		constrainedFlag$sample76 = new boolean[length$metric.length][];
		for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
			constrainedFlag$sample76[sample$var45] = new boolean[(length$metric[sample$var45][0] - 1)];
		
		// Constructor for constrainedFlag$sample57
		constrainedFlag$sample57 = new boolean[length$metric.length];
		
		// Constructor for constrainedFlag$sample134
		constrainedFlag$sample134 = new boolean[length$metric[0].length][];
		for(int var119 = 0; var119 < length$metric[0].length; var119 += 1)
			constrainedFlag$sample134[var119] = new boolean[noStates];
		
		// Constructor for constrainedFlag$sample162
		constrainedFlag$sample162 = new boolean[length$metric[0].length][];
		for(int var146 = 0; var146 < length$metric[0].length; var146 += 1)
			constrainedFlag$sample162[var146] = new boolean[noStates];
		
		// Constructor for constrainedFlag$sample33
		constrainedFlag$sample33 = new boolean[noStates];
		
		// Constructor for logProbability$sample57
		logProbability$sample57 = new double[length$metric.length];
		
		// Constructor for logProbability$sample76
		logProbability$sample76 = new double[length$metric.length][];
		for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
			logProbability$sample76[sample$var45] = new double[(length$metric[sample$var45][0] - 1)];
		
		// Constructor for logProbability$sample241
		logProbability$sample241 = new double[length$metric.length][][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample241[sample$var196] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var196][0]];
		}
		
		// Constructor for logProbability$sample256
		logProbability$sample256 = new double[length$metric.length][][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample256[sample$var196] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var196][0]];
		}
		
		// Allocate scratch space
		allocateScratch();
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

=======
>>>>>>> 599badf Starting to add scratch the correct way. Adding a transformation to rewrite trees with accesses to scratch space. This will want changing so that we pass in a transformer rather than a series of flags at the end. Adding scratch state to the model. Changes that are only related to the addition of inner classes to hold the state. More adding state Updates to state location Adding state and scratch classes
	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample33) {
			for(int var32 = 0; var32 < state.noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			if(!state.fixedFlag$sample57)
				state.st[sample$var45][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample76) {
				int[] var67 = state.st[sample$var45];
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample134) {
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				double[] var120 = state.current_metric_mean[var119];
				for(int var129 = 0; var129 < state.noStates; var129 += 1)
					var120[var129] = ((double)state.max_metric * DistributionSampling.sampleUniform(state.RNG$));
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample162) {
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				double[] var147 = state.current_metric_var[var146];
				for(int var156 = 0; var156 < state.noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample190) {
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				double[] var174 = state.current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < state.noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
			double[][] var211 = state.metric_g[sample$var196];
			for(int server = 0; server < state.noServers; server += 1) {
				boolean[] metric_valid_inner = state.metric_valid_g[sample$var196][server];
				double[] metric_inner = var211[server];
				for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
					metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(state.RNG$, state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]]);
					if(metric_valid_inner[timeStep$var226]) {
						state.var245[sample$var196][server][timeStep$var226] = ((Math.sqrt(state.current_metric_var[server][state.st[sample$var196][timeStep$var226]]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]]);
						metric_inner[timeStep$var226] = state.var245[sample$var196][server][timeStep$var226];
					}
				}
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample33) {
			for(int var32 = 0; var32 < state.noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample57) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample57 = state.distribution$sample57[sample$var45];
				for(int index$var54 = 0; index$var54 < state.noStates; index$var54 += 1)
					// Save the probability of each value
					// 
					// Probability for this value
					cv$distribution$sample57[index$var54] = (((0.0 <= state.initialStateDistribution[index$var54]) && (state.initialStateDistribution[index$var54] <= 1.0))?state.initialStateDistribution[index$var54]:0.0);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample76) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
					// Create local copy of variable probabilities.
					double[] cv$distribution$sample76 = state.distribution$sample76[sample$var45][(timeStep$var66 - 1)];
					for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1)
						// Zero the probability of each value
						cv$distribution$sample76[index$var73] = 0.0;
					
					// Iterate through possible values for var73's arguments.
					// 
					// Enumerating the possible arguments for Categorical 73.
					if((1 == timeStep$var66)) {
						// Iterate through possible values for var73's arguments.
						// 
						// Enumerating the possible arguments for Categorical 73.
						if(state.fixedFlag$sample57) {
							int var32 = state.st[sample$var45][0];
							
																					// Substituted "timeStep$var66" with its value "1".
							if(((0 <= var32) && (var32 < state.noStates))) {
								// Substituted "timeStep$var66" with its value "1".
								double[] var72 = state.m[state.st[sample$var45][0]];
								for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1)
									// Save the probability of each value
									cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (((0.0 <= var72[index$var73]) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0));
							}
						} else {
							// Enumerating the possible outputs of Categorical 54.
							for(int index$sample57$3 = 0; index$sample57$3 < state.noStates; index$sample57$3 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$2" with its value "sample$var45".
								double cv$probabilitySample57Value4 = state.distribution$sample57[sample$var45][index$sample57$3];
								double[] var72 = state.m[index$sample57$3];
								for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1)
									// Save the probability of each value
									cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample57Value4 * (((0.0 <= var72[index$var73]) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0)));
							}
						}
					}
					int index$timeStep$11 = (timeStep$var66 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$sample$10" with its value "sample$var45".
					if((1 <= index$timeStep$11)) {
						// Enumerating the possible outputs of Categorical 73.
						for(int index$sample76$12 = 0; index$sample76$12 < state.noStates; index$sample76$12 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$10" with its value "sample$var45".
							double cv$probabilitySample76Value13 = state.distribution$sample76[sample$var45][(index$timeStep$11 - 1)][index$sample76$12];
							double[] var72 = state.m[index$sample76$12];
							for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1)
								// Save the probability of each value
								cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample76Value13 * (((0.0 <= var72[index$var73]) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0)));
						}
					}
					
					// Sum the values in the array
					double cv$var73$sum = 0.0;
					for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1)
						// sum the probability of each value
						cv$var73$sum = (cv$var73$sum + cv$distribution$sample76[index$var73]);
					for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1)
						// Normalise the probability of each value
						cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] / cv$var73$sum);
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample134) {
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				double[] var120 = state.current_metric_mean[var119];
				for(int var129 = 0; var129 < state.noStates; var129 += 1)
					var120[var129] = ((double)state.max_metric * DistributionSampling.sampleUniform(state.RNG$));
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample162) {
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				double[] var147 = state.current_metric_var[var146];
				for(int var156 = 0; var156 < state.noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample190) {
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				double[] var174 = state.current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < state.noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			}
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample33) {
			for(int var32 = 0; var32 < state.noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			if(!state.fixedFlag$sample57)
				state.st[sample$var45][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample76) {
				int[] var67 = state.st[sample$var45];
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample134) {
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				double[] var120 = state.current_metric_mean[var119];
				for(int var129 = 0; var129 < state.noStates; var129 += 1)
					var120[var129] = ((double)state.max_metric * DistributionSampling.sampleUniform(state.RNG$));
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample162) {
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				double[] var147 = state.current_metric_var[var146];
				for(int var156 = 0; var156 < state.noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample190) {
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				double[] var174 = state.current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < state.noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
			double[][] var211 = state.metric_g[sample$var196];
			for(int server = 0; server < state.noServers; server += 1) {
				boolean[] metric_valid_inner = state.metric_valid_g[sample$var196][server];
				double[] metric_inner = var211[server];
				for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
					metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(state.RNG$, state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]]);
					if(metric_valid_inner[timeStep$var226]) {
						state.var245[sample$var196][server][timeStep$var226] = ((Math.sqrt(state.current_metric_var[server][state.st[sample$var196][timeStep$var226]]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]]);
						metric_inner[timeStep$var226] = state.var245[sample$var196][server][timeStep$var226];
					}
				}
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample33) {
			for(int var32 = 0; var32 < state.noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			if(!state.fixedFlag$sample57)
				state.st[sample$var45][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample76) {
				int[] var67 = state.st[sample$var45];
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample134) {
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				double[] var120 = state.current_metric_mean[var119];
				for(int var129 = 0; var129 < state.noStates; var129 += 1)
					var120[var129] = ((double)state.max_metric * DistributionSampling.sampleUniform(state.RNG$));
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample162) {
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				double[] var147 = state.current_metric_var[var146];
				for(int var156 = 0; var156 < state.noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample190) {
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				double[] var174 = state.current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < state.noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample33) {
			for(int var32 = 0; var32 < state.noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			if(!state.fixedFlag$sample57)
				state.st[sample$var45][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample76) {
				int[] var67 = state.st[sample$var45];
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample134) {
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				double[] var120 = state.current_metric_mean[var119];
				for(int var129 = 0; var129 < state.noStates; var129 += 1)
					var120[var129] = ((double)state.max_metric * DistributionSampling.sampleUniform(state.RNG$));
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample162) {
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				double[] var147 = state.current_metric_var[var146];
				for(int var156 = 0; var156 < state.noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample190) {
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				double[] var174 = state.current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < state.noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample20)
				inferSample20();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample33) {
				for(int var32 = 0; var32 < state.noStates; var32 += 1)
					inferSample33(var32);
			}
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				if(!state.fixedFlag$sample57)
					inferSample57(sample$var45);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!state.fixedFlag$sample76) {
					for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
						inferSample76(sample$var45, timeStep$var66);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample134) {
				for(int var119 = 0; var119 < state.noServers; var119 += 1) {
					for(int var129 = 0; var129 < state.noStates; var129 += 1)
						inferSample134(var119, var129);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample162) {
				for(int var146 = 0; var146 < state.noServers; var146 += 1) {
					for(int var156 = 0; var156 < state.noStates; var156 += 1)
						inferSample162(var146, var156);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample190) {
				for(int var173 = 0; var173 < state.noServers; var173 += 1) {
					for(int var183 = 0; var183 < state.noStates; var183 += 1)
						inferSample190(var173, var183);
				}
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample190) {
				for(int var173 = (state.noServers - 1); var173 >= 0; var173 -= 1) {
					for(int var183 = (state.noStates - 1); var183 >= 0; var183 -= 1)
						inferSample190(var173, var183);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample162) {
				for(int var146 = (state.noServers - 1); var146 >= 0; var146 -= 1) {
					for(int var156 = (state.noStates - 1); var156 >= 0; var156 -= 1)
						inferSample162(var146, var156);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample134) {
				for(int var119 = (state.noServers - 1); var119 >= 0; var119 -= 1) {
					for(int var129 = (state.noStates - 1); var129 >= 0; var129 -= 1)
						inferSample134(var119, var129);
				}
			}
			for(int sample$var45 = (state.noSamples - 1); sample$var45 >= 0; sample$var45 -= 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!state.fixedFlag$sample76) {
					for(int timeStep$var66 = (state.length$metric[sample$var45][0] - 1); timeStep$var66 >= 1; timeStep$var66 -= 1)
						inferSample76(sample$var45, timeStep$var66);
				}
				if(!state.fixedFlag$sample57)
					inferSample57(sample$var45);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample33) {
				for(int var32 = (state.noStates - 1); var32 >= 0; var32 -= 1)
					inferSample33(var32);
			}
			if(!state.fixedFlag$sample20)
				inferSample20();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample20)
			drawValueSample20();
		for(int var32 = 0; var32 < state.noStates; var32 += 1) {
			if(!state.constrainedFlag$sample33[var32])
				drawValueSample33(var32);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			if(!state.constrainedFlag$sample57[sample$var45])
				drawValueSample57(sample$var45);
			for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
				if(!state.constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)])
					drawValueSample76(sample$var45, timeStep$var66);
			}
		}
		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
				if(!state.constrainedFlag$sample134[var119][var129])
					drawValueSample134(var119, var129);
			}
		}
		for(int var146 = 0; var146 < state.noServers; var146 += 1) {
			for(int var156 = 0; var156 < state.noStates; var156 += 1) {
				if(!state.constrainedFlag$sample162[var146][var156])
					drawValueSample162(var146, var156);
			}
		}
		for(int var173 = 0; var173 < state.noServers; var173 += 1) {
			for(int var183 = 0; var183 < state.noStates; var183 += 1) {
				if(!state.constrainedFlag$sample190[var173][var183])
					drawValueSample190(var173, var183);
			}
		}
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
				state.logProbability$sample57[sample$var45] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample76) {
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					state.logProbability$sample76[sample$var45][(timeStep$var66 - 1)] = Double.NaN;
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
						state.logProbability$sample241[sample$var196][server][timeStep$var226] = Double.NaN;
				}
			}
		}
		state.logProbability$var245 = 0.0;
		state.logProbability$metric_g = 0.0;
		if(!state.fixedProbFlag$sample256) {
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1)
						state.logProbability$sample256[sample$var196][server][timeStep$var226] = Double.NaN;
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
		// Propagating values back from observations into the models intermediate variables.
		{
			// Deep copy between arrays
			int cv$length1 = state.metric_valid_g.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				boolean[][] cv$source2 = state.metric_valid[cv$index1];
				boolean[][] cv$target2 = state.metric_valid_g[cv$index1];
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
		int cv$length1 = state.metric_g.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			double[][] cv$source2 = state.metric[cv$index1];
			double[][] cv$target2 = state.metric_g[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
				double[] cv$source3 = cv$source2[cv$index2];
				double[] cv$target3 = cv$target2[cv$index2];
				int cv$length3 = cv$target3.length;
				for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
					cv$target3[cv$index3] = cv$source3[cv$index3];
			}
		}
		for(int sample$var196 = (state.noSamples - 1); sample$var196 >= 0; sample$var196 -= 1) {
			for(int server = (state.noServers - 1); server >= 0; server -= 1) {
				for(int timeStep$var226 = (state.length$metric[sample$var196][0] - 1); timeStep$var226 >= 0; timeStep$var226 -= 1) {
					if(state.metric_valid_g[sample$var196][server][timeStep$var226])
						// Substituted "metric_inner" with its value "metric_g[sample$var196][server]".
						state.var245[sample$var196][server][timeStep$var226] = state.metric_g[sample$var196][server][timeStep$var226];
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