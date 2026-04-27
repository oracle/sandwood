package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMMetrics2$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMMetrics2.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMMetrics2$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$distributionAccumulator$var120;
		double[] cv$var102$stateProbabilityGlobal;
		double[] cv$var121$stateProbabilityGlobal;
		double[] cv$var19$countGlobal;
		double[] cv$var32$countGlobal;
		boolean[][] guard$sample104gaussian156$global;
		boolean[][] guard$sample123gaussian156$global;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var19$countGlobal
			// 
			// Allocation of cv$var19$countGlobal for single threaded execution
			cv$var19$countGlobal = new double[state.noStates];
			
			// Constructor for cv$var32$countGlobal
			// 
			// Allocation of cv$var32$countGlobal for single threaded execution
			cv$var32$countGlobal = new double[state.noStates];
			
			// Constructor for cv$distributionAccumulator$var120
			// 
			// Allocation of cv$distributionAccumulator$var120 for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 121. Initially set to the value
			// of putTask 33.
			cv$distributionAccumulator$var120 = new double[state.noStates];
			
			// Constructor for cv$var102$stateProbabilityGlobal
			// 
			// Allocation of cv$var102$stateProbabilityGlobal for single threaded execution
			cv$var102$stateProbabilityGlobal = new double[state.noStates];
			
			// Constructor for guard$sample104gaussian156$global
			{
				// Calculate the largest index of timeStep that is possible and allocate an array
				// to hold the guard for each of these.
				int cv$max_timeStep$var136 = 0;
				for(int sample = 0; sample < state.length$metric.length; sample += 1)
					cv$max_timeStep$var136 = Math.max(cv$max_timeStep$var136, state.length$metric[sample]);
				
				// Allocation of guard$sample104gaussian156$global for single threaded execution
				guard$sample104gaussian156$global = new boolean[state.length$metric.length][cv$max_timeStep$var136];
			}
			
			// Allocation of cv$var121$stateProbabilityGlobal for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 121. Initially set to the value
			// of putTask 33.
			cv$var121$stateProbabilityGlobal = new double[state.noStates];
			
			// Constructor for guard$sample123gaussian156$global
			// 
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var136 = 0;
			for(int sample = 0; sample < state.length$metric.length; sample += 1)
				cv$max_timeStep$var136 = Math.max(cv$max_timeStep$var136, state.length$metric[sample]);
			
			// Allocation of guard$sample123gaussian156$global for single threaded execution
			guard$sample123gaussian156$global = new boolean[state.length$metric.length][cv$max_timeStep$var136];
		}
	}


	public HMMMetrics2$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample104
	private final void drawValueSample104(int sample) {
		state.st[sample][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
	}

	// Pick a value from the distribution for the unconditioned variable from sample123
	private final void drawValueSample123(int sample, int timeStep$var113) {
		state.st[sample][timeStep$var113] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample][(timeStep$var113 - 1)]], state.noStates);
	}

	// Pick a value from the distribution for the unconditioned variable from sample19
	private final void drawValueSample19() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
	}

	// Pick a value from the distribution for the unconditioned variable from sample32
	private final void drawValueSample32(int var31) {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var31]);
	}

	// Pick a value from the distribution for the unconditioned variable from sample52
	private final void drawValueSample52(int var50) {
		state.metric_mean[var50] = (DistributionSampling.sampleUniform(state.RNG$) * 100.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample68
	private final void drawValueSample68(int var66) {
		state.metric_var[var66] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample84
	private final void drawValueSample84(int var82) {
		state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 104 drawn from Categorical 101. Inference was performed using variable
	// marginalization.
	private final void inferSample104(int sample) {
		state.constrainedFlag$sample104[sample] = false;
		
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
			if((state.fixedFlag$sample123 && (1 < state.length$metric[sample]))) {
				// Looking for a path between Sample 104 and consumer Categorical 120.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample104[sample] = true;
				
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Categorical 120 which is consuming
				// the output of Sample task 104.
				// 
				// Value of the variable at this index
				if((cv$valuePos < state.noStates)) {
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					double[] var119 = state.m[cv$valuePos];
					
															// Substituted "index$sample$2_2" with its value "sample".
					cv$accumulatedConsumerProbabilities = (((((0.0 <= state.st[sample][1]) && (state.st[sample][1] < state.noStates)) && (0.0 <= var119[state.st[sample][1]])) && (var119[state.st[sample][1]] <= 1.0))?Math.log(var119[state.st[sample][1]]):Double.NEGATIVE_INFINITY);
					
					// Recorded the probability of reaching sample task 123 with the current configuration.
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
			if((0 < state.length$metric[sample])) {
				{
					// Looking for a path between Sample 104 and consumer Bernoulli 140.
					// Processing sample task 145 of consumer random variable null.
					// 
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample104[sample] = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 140 which is consuming
					// the output of Sample task 104.
					// 
					// Value of the variable at this index
					if((cv$valuePos < state.noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var139 = state.metric_valid_bias[cv$valuePos];
						
																		// Substituted "index$sample$8_2" with its value "sample".
						cv$accumulatedConsumerProbabilities = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[sample][0]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 145 with the current configuration.
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
				if(state.metric_valid_g[sample][0]) {
					// Looking for a path between Sample 104 and consumer Gaussian 150.
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample104gaussian156$global[sample][0] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample104gaussian156$global[sample][0]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample104gaussian156$global[sample][0] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample104[sample] = true;
						
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
							double var149 = state.metric_var[cv$valuePos];
							
																					// Substituted "index$sample$14_2" with its value "sample".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 157 with the current configuration.
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
					
					// Substituted "timeStep$var136" with its value "0".
					if(!scratch.guard$sample104gaussian156$global[sample][0]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample104gaussian156$global[sample][0] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample104[sample] = true;
						
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
							double var149 = state.metric_var[cv$valuePos];
							
																					// Substituted "index$sample$15_2" with its value "sample".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 157 with the current configuration.
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
			if((!state.fixedFlag$sample123 && (1 < state.length$metric[sample]))) {
				// Looking for a path between Sample 104 and consumer Categorical 120.
				// Processing sample task 123 of consumer random variable null.
				// 
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					scratch.cv$distributionAccumulator$var120[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Enumerating the possible arguments for the variable Categorical 120 which is consuming
				// the output of Sample task 104.
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
					DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var120, 1.0, state.m[cv$valuePos], state.noStates);
				}
				
				// A local copy of the samples' distribution.
				// 
												// Substituted "index$sample$66_2" with its value "sample".
				double[] cv$sampleDistribution = state.distribution$sample123[sample][0];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var120[cv$i] / cv$reachedDistributionProbability);
					
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
			scratch.cv$var102$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(state.constrainedFlag$sample104[sample]) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// Local copy of the probability array
			double[] cv$localProbability = state.distribution$sample104[sample];
			
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var102$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var102$stateProbabilityGlobal[cv$lseIndex];
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
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var102$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
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
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var102$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var102$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 123 drawn from Categorical 120. Inference was performed using variable
	// marginalization.
	private final void inferSample123(int sample, int timeStep$var113) {
		state.constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = false;
		
		// Calculate the number of states to evaluate.
		int cv$numStates = 0;
		
		// Exploring all the possible state counts for random variable 120.
		// 
		// Enumerating the possible arguments for Categorical 120.
		if((1 == timeStep$var113)) {
			// Exploring all the possible state counts for random variable 120.
			// 
			// Enumerating the possible arguments for Categorical 120.
			if(state.fixedFlag$sample104) {
				int var31 = state.st[sample][0];
				
												// Substituted "timeStep$var113" with its value "1".
				if(((0 <= var31) && (var31 < state.noStates)))
					// variable marginalization
					// 
										// cv$numStates's comment
					// Calculate the number of states to evaluate.
					cv$numStates = Math.max(0, state.noStates);
			} else {
				// Enumerating the possible outputs of Categorical 101.
				if((0 < state.noStates))
					// variable marginalization
					cv$numStates = state.noStates;
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < state.noStates)) {
			int index$timeStep$13 = (timeStep$var113 - 1);
			
												// index$sample$2's comment
			// Copy of index so that its values can be safely substituted
			// 
									// Substituted "index$sample$12" with its value "sample".
			// 
									// Substituted "index$timeStep$13" with its value "(timeStep$var113 - 1)".
			// 
									// Substituted "index$timeStep$13" with its value "(timeStep$var113 - 1)".
			// 
									// Substituted "index$timeStep$13" with its value "(timeStep$var113 - 1)".
			// 
									// Substituted "index$timeStep$13" with its value "(timeStep$var113 - 1)".
			// 
									// Substituted "index$timeStep$13" with its value "(timeStep$var113 - 1)".
			// 
									// Substituted "index$timeStep$13" with its value "(timeStep$var113 - 1)".
			if(((1 <= index$timeStep$13) && !(index$timeStep$13 == timeStep$var113)))
				// variable marginalization
				cv$numStates = state.noStates;
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 120 creating
			// sample task 123.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Enumerating the possible arguments for Categorical 120.
			if((1 == timeStep$var113)) {
				// Enumerating the possible arguments for Categorical 120.
				if(state.fixedFlag$sample104) {
					int var31 = state.st[sample][0];
					
															// Substituted "timeStep$var113" with its value "1".
					if(((0 <= var31) && (var31 < state.noStates))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "timeStep$var113" with its value "1".
						double[] var119 = state.m[state.st[sample][0]];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
																		// Value of the variable at this index
						double cv$accumulatedProbabilities = ((((cv$valuePos < state.noStates) && (0.0 <= var119[cv$valuePos])) && (var119[cv$valuePos] <= 1.0))?Math.log(var119[cv$valuePos]):Double.NEGATIVE_INFINITY);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < state.length$metric[sample])) {
							{
								// Looking for a path between Sample 123 and consumer Bernoulli 140.
								// Processing sample task 145 of consumer random variable null.
								// 
								// Mark that the sample has observed constrained data.
								// 
								// Substituted "timeStep$var113" with its value "1".
								state.constrainedFlag$sample123[sample][0] = true;
								
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Enumerating the possible arguments for the variable Bernoulli 140 which is consuming
								// the output of Sample task 123.
								// 
								// Value of the variable at this index
								if((cv$valuePos < state.noStates)) {
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var139 = state.metric_valid_bias[cv$valuePos];
									
									// Substituted "index$sample$41_2" with its value "sample".
									// 
									// Substituted "timeStep$var136" with its value "1".
									cv$accumulatedConsumerProbabilities = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[sample][1]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
									
									// Recorded the probability of reaching sample task 145 with the current configuration.
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
							if(state.metric_valid_g[sample][1]) {
								// Set the flags to false
								// 
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								// 
								// Substituted "timeStep$var136" with its value "1".
								scratch.guard$sample123gaussian156$global[sample][1] = false;
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if(!scratch.guard$sample123gaussian156$global[sample][1]) {
									// The body will execute, so should not be executed again
									// 
									// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var136" with its value "1".
									scratch.guard$sample123gaussian156$global[sample][1] = true;
									
									// Mark that the sample has observed constrained data.
									// 
									// Substituted "timeStep$var113" with its value "1".
									state.constrainedFlag$sample123[sample][0] = true;
									
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
									// the output of Sample task 123.
									// 
									// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
									// the output of Sample task 123.
									// 
									// Value of the variable at this index
									if((cv$valuePos < state.noStates)) {
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double var149 = state.metric_var[cv$valuePos];
										
										// Substituted "index$sample$65_2" with its value "sample".
										// 
										// Substituted "timeStep$var136" with its value "1".
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][1] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
										
										// Recorded the probability of reaching sample task 157 with the current configuration.
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
								
																								// Substituted "timeStep$var113" with its value "1".
								// 
																								// Substituted "timeStep$var136" with its value "1".
								if(!scratch.guard$sample123gaussian156$global[sample][1]) {
									// The body will execute, so should not be executed again
									// 
									// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var136" with its value "1".
									scratch.guard$sample123gaussian156$global[sample][1] = true;
									
									// Mark that the sample has observed constrained data.
									// 
									// Substituted "timeStep$var113" with its value "1".
									state.constrainedFlag$sample123[sample][0] = true;
									
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
										double var149 = state.metric_var[cv$valuePos];
										
										// Substituted "index$sample$69_2" with its value "sample".
										// 
										// Substituted "timeStep$var136" with its value "1".
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][1] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
										
										// Recorded the probability of reaching sample task 157 with the current configuration.
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
					// Enumerating the possible outputs of Categorical 101.
					for(int index$sample104$22 = 0; index$sample104$22 < state.noStates; index$sample104$22 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$21" with its value "sample".
						double cv$probabilitySample104Value23 = state.distribution$sample104[sample][index$sample104$22];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample104Value23);
						
						// Constructing a random variable input for use later.
						double[] var119 = state.m[index$sample104$22];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
																		// Value of the variable at this index
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample104Value23) + (((0.0 <= var119[cv$valuePos]) && (var119[cv$valuePos] <= 1.0))?Math.log(var119[cv$valuePos]):Double.NEGATIVE_INFINITY));
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < state.length$metric[sample])) {
							// Looking for a path between Sample 123 and consumer Bernoulli 140.
							// 
							// Processing sample task 145 of consumer random variable null.
							// 
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "timeStep$var113" with its value "1".
							state.constrainedFlag$sample123[sample][0] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var139 = state.metric_valid_bias[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 145 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "index$sample$42_2" with its value "sample".
							// 
							// Substituted "timeStep$var136" with its value "1".
							cv$accumulatedProbabilities = ((((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[sample][1]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(state.metric_valid_g[sample][1]) {
								// Set the flags to false
								// 
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								// 
								// Substituted "timeStep$var136" with its value "1".
								scratch.guard$sample123gaussian156$global[sample][1] = false;
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if(!scratch.guard$sample123gaussian156$global[sample][1]) {
									// The body will execute, so should not be executed again
									// 
									// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var136" with its value "1".
									scratch.guard$sample123gaussian156$global[sample][1] = true;
									
									// Mark that the sample has observed constrained data.
									// 
									// Substituted "timeStep$var113" with its value "1".
									state.constrainedFlag$sample123[sample][0] = true;
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var149 = state.metric_var[cv$valuePos];
									
									// A check to ensure rounding of floating point values can never result in a negative
									// value.
									// 
									// Recorded the probability of reaching sample task 157 with the current configuration.
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
									// Substituted "index$sample$66_2" with its value "sample".
									// 
									// Substituted "timeStep$var136" with its value "1".
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][1] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
								}
								
																								// Substituted "timeStep$var113" with its value "1".
								// 
																								// Substituted "timeStep$var136" with its value "1".
								if(!scratch.guard$sample123gaussian156$global[sample][1]) {
									// The body will execute, so should not be executed again
									// 
									// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									// 
									// Substituted "timeStep$var136" with its value "1".
									scratch.guard$sample123gaussian156$global[sample][1] = true;
									
									// Mark that the sample has observed constrained data.
									// 
									// Substituted "timeStep$var113" with its value "1".
									state.constrainedFlag$sample123[sample][0] = true;
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var149 = state.metric_var[cv$valuePos];
									
									// A check to ensure rounding of floating point values can never result in a negative
									// value.
									// 
									// Recorded the probability of reaching sample task 157 with the current configuration.
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
									// Substituted "index$sample$70_2" with its value "sample".
									// 
									// Substituted "timeStep$var136" with its value "1".
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][1] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$timeStep$30 = (timeStep$var113 - 1);
			
												// index$sample$2's comment
			// Copy of index so that its values can be safely substituted
			// 
									// Substituted "index$sample$29" with its value "sample".
			// 
									// Substituted "index$timeStep$30" with its value "(timeStep$var113 - 1)".
			// 
									// Substituted "index$timeStep$30" with its value "(timeStep$var113 - 1)".
			// 
									// Substituted "index$timeStep$30" with its value "(timeStep$var113 - 1)".
			// 
									// Substituted "index$timeStep$30" with its value "(timeStep$var113 - 1)".
			// 
									// Substituted "index$timeStep$30" with its value "(timeStep$var113 - 1)".
			// 
									// Substituted "index$timeStep$30" with its value "(timeStep$var113 - 1)".
			if(((1 <= index$timeStep$30) && !(index$timeStep$30 == timeStep$var113))) {
				// Enumerating the possible outputs of Categorical 120.
				for(int index$sample123$31 = 0; index$sample123$31 < state.noStates; index$sample123$31 += 1) {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$29" with its value "sample".
					double cv$probabilitySample123Value32 = state.distribution$sample123[sample][(index$timeStep$30 - 1)][index$sample123$31];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample123Value32);
					
					// Constructing a random variable input for use later.
					double[] var119 = state.m[index$sample123$31];
					
					// Looking for a path between Sample 123 and consumer Bernoulli 140.
					// 
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = true;
					
					// Constructing a random variable input for use later.
					double var139 = state.metric_valid_bias[index$sample123$31];
					
					// Variable declaration of cv$accumulatedProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
															// Value of the variable at this index
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 145 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
															// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
															// Value of the variable at this index
					double cv$accumulatedProbabilities = (((((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[sample][timeStep$var113]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY) + Math.log(cv$probabilitySample123Value32)) + (((0.0 <= var119[cv$valuePos]) && (var119[cv$valuePos] <= 1.0))?Math.log(var119[cv$valuePos]):Double.NEGATIVE_INFINITY));
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(state.metric_valid_g[sample][timeStep$var113]) {
						// Looking for a path between Sample 123 and consumer Gaussian 150.
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample123gaussian156$global[sample][timeStep$var113] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample123gaussian156$global[sample][timeStep$var113]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample123gaussian156$global[sample][timeStep$var113] = true;
							
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var149 = state.metric_var[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 157 with the current configuration.
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
																					// Substituted "index$sample$68_2" with its value "sample".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var113] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						if(!scratch.guard$sample123gaussian156$global[sample][timeStep$var113]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample123gaussian156$global[sample][timeStep$var113] = true;
							
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var149 = state.metric_var[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 157 with the current configuration.
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
																					// Substituted "index$sample$72_2" with its value "sample".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var113] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$timeStep$265_3 = (timeStep$var113 + 1);
			if((index$timeStep$265_3 < state.length$metric[sample])) {
				// Processing sample task 123 of consumer random variable null.
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					scratch.cv$distributionAccumulator$var120[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Enumerating the possible arguments for the variable Categorical 120 which is consuming
				// the output of Sample task 123.
				// 
				// Processing random variable 120.
				// 
				// Looking for a path between Sample 123 and consumer Categorical 120.
				// 
				// Value of the variable at this index
				if((cv$valuePos < state.noStates)) {
					// Declare and zero an accumulator for tracking the reached source probability space.
					double scopeVariable$reachedSourceProbability = 0.0;
					
					// Enumerating the possible arguments for Categorical 120.
					if((1 == timeStep$var113)) {
						// Enumerating the possible arguments for Categorical 120.
						if(state.fixedFlag$sample104) {
							int index$var31$276_1 = state.st[sample][0];
							
																					// Substituted "timeStep$var113" with its value "1".
							if(((0 <= index$var31$276_1) && (index$var31$276_1 < state.noStates)))
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 101.
							for(int index$sample104$272 = 0; index$sample104$272 < state.noStates; index$sample104$272 += 1)
								// Add the probability of this argument configuration.
								// 
																// cv$probabilitySample104Value273's comment
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$271" with its value "sample".
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample104[sample][index$sample104$272]);
						}
					}
					int index$timeStep$280 = (timeStep$var113 - 1);
					
																				// index$timeStep$267's comment
					// Copy of index so that its values can be safely substituted
					// 
																				// index$sample$268's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "index$sample$265_2" with its value "sample".
					// 
															// Substituted "index$timeStep$265_3" with its value "(timeStep$var113 + 1)".
					// 
															// Substituted "index$timeStep$265_3" with its value "(timeStep$var113 + 1)".
					// 
															// Substituted "index$timeStep$265_3" with its value "(timeStep$var113 + 1)".
					// 
															// Substituted "index$timeStep$265_3" with its value "(timeStep$var113 + 1)".
					// 
															// Substituted "index$timeStep$265_3" with its value "(timeStep$var113 + 1)".
					// 
															// Substituted "index$timeStep$265_3" with its value "(timeStep$var113 + 1)".
					if((((1 <= index$timeStep$280) && !(index$timeStep$280 == timeStep$var113)) && !(index$timeStep$280 == index$timeStep$265_3))) {
						// Enumerating the possible outputs of Categorical 120.
						for(int index$sample123$281 = 0; index$sample123$281 < state.noStates; index$sample123$281 += 1)
							// Add the probability of this argument configuration.
							// 
														// cv$probabilitySample123Value282's comment
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$279" with its value "sample".
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample123[sample][(index$timeStep$280 - 1)][index$sample123$281]);
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
					// Processing random variable 120.
					// 
					// Looking for a path between Sample 123 and consumer Categorical 120.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var120, scopeVariable$reachedSourceProbability, state.m[cv$valuePos], state.noStates);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$sample$265_2" with its value "sample".
				double[] cv$sampleDistribution = state.distribution$sample123[sample][(index$timeStep$265_3 - 1)];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var120[cv$i] / cv$reachedDistributionProbability);
					
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
			scratch.cv$var121$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(state.constrainedFlag$sample123[sample][(timeStep$var113 - 1)]) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// Local copy of the probability array
			double[] cv$localProbability = state.distribution$sample123[sample][(timeStep$var113 - 1)];
			
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var121$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var121$stateProbabilityGlobal[cv$lseIndex];
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
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var121$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
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
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var121$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var121$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 19 drawn from Dirichlet 18. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample19() {
		state.constrainedFlag$sample19 = false;
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			scratch.cv$var19$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample104) {
			// Processing random variable 101.
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				// Processing sample task 104 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample19 = true;
				
				// Increment the sample counter with the value sampled by sample task 104 of random
				// variable var101
				// 
												// A local reference to the scratch space.
				scratch.cv$var19$countGlobal[state.st[sample][0]] = (scratch.cv$var19$countGlobal[state.st[sample][0]] + 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				// Processing sample task 104 of consumer random variable null.
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
					scratch.cv$var19$countGlobal[cv$loopIndex] = (scratch.cv$var19$countGlobal[cv$loopIndex] + state.distribution$sample104[sample][cv$loopIndex]);
			}
		}
		if(state.constrainedFlag$sample19)
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
									// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var19$countGlobal, state.initialStateDistribution, state.noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 32 drawn from Dirichlet 20. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample32(int var31) {
		state.constrainedFlag$sample32[var31] = false;
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			scratch.cv$var32$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample123) {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < state.length$metric[sample])) {
					if(state.fixedFlag$sample104) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var31 == state.st[sample][0])) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample32[var31] = true;
							
							// Increment the sample counter with the value sampled by sample task 123 of random
							// variable var120
							// 
																					// A local reference to the scratch space.
							scratch.cv$var32$countGlobal[state.st[sample][1]] = (scratch.cv$var32$countGlobal[state.st[sample][1]] + 1.0);
						}
					} else {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample32[var31] = true;
						
						// Increment the sample counter with the value sampled by sample task 123 of random
						// variable var120
						// 
																		// A local reference to the scratch space.
						// 
						// Substituted "index$sample104$5" with its value "var31".
						scratch.cv$var32$countGlobal[state.st[sample][1]] = (scratch.cv$var32$countGlobal[state.st[sample][1]] + state.distribution$sample104[sample][var31]);
					}
				}
			}
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 2; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
					if((var31 == state.st[sample][(timeStep$var113 - 1)])) {
						// Processing sample task 123 of consumer random variable null.
						// 
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample32[var31] = true;
						
						// Increment the sample counter with the value sampled by sample task 123 of random
						// variable var120
						// 
																		// A local reference to the scratch space.
						scratch.cv$var32$countGlobal[state.st[sample][timeStep$var113]] = (scratch.cv$var32$countGlobal[state.st[sample][timeStep$var113]] + 1.0);
					}
				}
			}
		}
		
		// Processing random variable 120.
		// 
		// Looking for a path between Sample 32 and consumer Categorical 120.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < state.length$metric[sample])) {
					if(state.fixedFlag$sample104) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var31 == state.st[sample][0])) {
							// Merge the distribution probabilities into the count
							// 
							// Get the length of the array
							for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
																								// A local reference to the scratch space.
								// 
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								scratch.cv$var32$countGlobal[cv$loopIndex] = (scratch.cv$var32$countGlobal[cv$loopIndex] + state.distribution$sample123[sample][0][cv$loopIndex]);
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
						// Substituted "index$sample104$42" with its value "var31".
						double cv$distributionProbability = state.distribution$sample104[sample][var31];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
																					// A local reference to the scratch space.
							scratch.cv$var32$countGlobal[cv$loopIndex] = (scratch.cv$var32$countGlobal[cv$loopIndex] + (state.distribution$sample123[sample][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
					int index$timeStep$52 = (timeStep$var113 - 1);
					
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
						// Substituted "index$sample123$53" with its value "var31".
						double cv$distributionProbability = state.distribution$sample123[sample][(index$timeStep$52 - 1)][var31];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
																					// A local reference to the scratch space.
							scratch.cv$var32$countGlobal[cv$loopIndex] = (scratch.cv$var32$countGlobal[cv$loopIndex] + (state.distribution$sample123[sample][(timeStep$var113 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		if(state.constrainedFlag$sample32[var31])
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
									// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var32$countGlobal, state.m[var31], state.noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 52 drawn from Uniform 39. Inference was performed using Metropolis-Hastings.
	private final void inferSample52(int var50) {
		state.constrainedFlag$sample52[var50] = false;
		
		// The original value of the sample
		double cv$originalValue = state.metric_mean[var50];
		
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
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue < 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
			
			// Looking for a path between Sample 52 and consumer Gaussian 150.
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 < state.length$metric[sample]) && state.metric_valid_g[sample][0])) {
					if(state.fixedFlag$sample104) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var50 == state.st[sample][0])) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample52[var50] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var66 = state.st[sample][0];
							
																					// Substituted "timeStep$var136" with its value "0".
							if(((0 <= var66) && (var66 < state.noStates))) {
								// Constructing a random variable input for use later.
								// 
								// Substituted "timeStep$var136" with its value "0".
								double var149 = state.metric_var[state.st[sample][0]];
								
								// Substituted "timeStep$var136" with its value "0".
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 157 with the current configuration.
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
						// Substituted "index$sample$5" with its value "sample".
						// 
						// Substituted "index$sample104$6" with its value "var50".
						double cv$probabilitySample104Value7 = state.distribution$sample104[sample][var50];
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample52[var50] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample104$6" with its value "var50".
						double var149 = state.metric_var[var50];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "timeStep$var136" with its value "0".
						// 
						// Set the current value to the current state of the tree.
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 157 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 157 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
																					// Constructing a random variable input for use later.
							// 
							// Substituted "index$sample104$6" with its value "var50".
							// 
																					// Constructing a random variable input for use later.
							// 
							// Substituted "index$sample104$6" with its value "var50".
							// 
																					// Constructing a random variable input for use later.
							// 
							// Substituted "index$sample104$6" with its value "var50".
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 1; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						if(state.fixedFlag$sample123) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var50 == state.st[sample][timeStep$var136])) {
								// Mark that the sample has observed constrained data.
								state.constrainedFlag$sample52[var50] = true;
								
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var66 = state.st[sample][timeStep$var136];
								if(((0 <= var66) && (var66 < state.noStates))) {
									// Constructing a random variable input for use later.
									double var149 = state.metric_var[state.st[sample][timeStep$var136]];
									
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
									
									// Recorded the probability of reaching sample task 157 with the current configuration.
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
																					// Substituted "index$sample$15" with its value "sample".
							// 
							// Substituted "index$sample123$17" with its value "var50".
							double cv$probabilitySample123Value18 = state.distribution$sample123[sample][(timeStep$var136 - 1)][var50];
							
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample52[var50] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Substituted "index$sample123$17" with its value "var50".
							double var149 = state.metric_var[var50];
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Set the current value to the current state of the tree.
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 157 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 157 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								// 
																								// Constructing a random variable input for use later.
								// 
								// Substituted "index$sample123$17" with its value "var50".
								// 
																								// Constructing a random variable input for use later.
								// 
								// Substituted "index$sample123$17" with its value "var50".
								// 
																								// Constructing a random variable input for use later.
								// 
								// Substituted "index$sample123$17" with its value "var50".
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
		if(state.constrainedFlag$sample52[var50]) {
			// Guards to ensure that metric_mean is only updated when there is a valid path.
			state.metric_mean[var50] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
			
			// Looking for a path between Sample 52 and consumer Gaussian 150.
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 < state.length$metric[sample]) && state.metric_valid_g[sample][0])) {
					if(state.fixedFlag$sample104) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var50 == state.st[sample][0])) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample52[var50] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var66 = state.st[sample][0];
							
																					// Substituted "timeStep$var136" with its value "0".
							if(((0 <= var66) && (var66 < state.noStates))) {
								// Constructing a random variable input for use later.
								// 
								// Substituted "timeStep$var136" with its value "0".
								double var149 = state.metric_var[state.st[sample][0]];
								
								// Substituted "timeStep$var136" with its value "0".
								cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 157 with the current configuration.
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
						// Substituted "index$sample$5" with its value "sample".
						// 
						// Substituted "index$sample104$6" with its value "var50".
						double cv$probabilitySample104Value7 = state.distribution$sample104[sample][var50];
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample52[var50] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample104$6" with its value "var50".
						double var149 = state.metric_var[var50];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "timeStep$var136" with its value "0".
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 157 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 157 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
																					// Constructing a random variable input for use later.
							// 
							// Substituted "index$sample104$6" with its value "var50".
							// 
																					// Constructing a random variable input for use later.
							// 
							// Substituted "index$sample104$6" with its value "var50".
							// 
																					// Constructing a random variable input for use later.
							// 
							// Substituted "index$sample104$6" with its value "var50".
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 1; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						if(state.fixedFlag$sample123) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var50 == state.st[sample][timeStep$var136])) {
								// Mark that the sample has observed constrained data.
								state.constrainedFlag$sample52[var50] = true;
								
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var66 = state.st[sample][timeStep$var136];
								if(((0 <= var66) && (var66 < state.noStates))) {
									// Constructing a random variable input for use later.
									double var149 = state.metric_var[state.st[sample][timeStep$var136]];
									cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
									
									// Recorded the probability of reaching sample task 157 with the current configuration.
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
																					// Substituted "index$sample$15" with its value "sample".
							// 
							// Substituted "index$sample123$17" with its value "var50".
							double cv$probabilitySample123Value18 = state.distribution$sample123[sample][(timeStep$var136 - 1)][var50];
							
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample52[var50] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Substituted "index$sample123$17" with its value "var50".
							double var149 = state.metric_var[var50];
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 157 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 157 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								// 
																								// Constructing a random variable input for use later.
								// 
								// Substituted "index$sample123$17" with its value "var50".
								// 
																								// Constructing a random variable input for use later.
								// 
								// Substituted "index$sample123$17" with its value "var50".
								// 
																								// Constructing a random variable input for use later.
								// 
								// Substituted "index$sample123$17" with its value "var50".
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
				// Guards to ensure that metric_mean is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				state.metric_mean[var50] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 68 drawn from InverseGamma 55. Inference was performed using Metropolis-Hastings.
	private final void inferSample68(int var66) {
		state.constrainedFlag$sample68[var66] = false;
		
		// The original value of the sample
		double cv$originalValue = state.metric_var[var66];
		
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
			
			// Looking for a path between Sample 68 and consumer Gaussian 150.
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 < state.length$metric[sample]) && state.metric_valid_g[sample][0])) {
					if(state.fixedFlag$sample104) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var66 == state.st[sample][0])) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample68[var66] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var50 = state.st[sample][0];
							
																					// Substituted "timeStep$var136" with its value "0".
							if(((0 <= var50) && (var50 < state.noStates))) {
								// Substituted "timeStep$var136" with its value "0".
								// 
																								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - state.metric_mean[state.st[sample][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 157 with the current configuration.
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
						// Substituted "index$sample$5" with its value "sample".
						// 
						// Substituted "index$sample104$6" with its value "var66".
						double cv$probabilitySample104Value7 = state.distribution$sample104[sample][var66];
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample68[var66] = true;
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "timeStep$var136" with its value "0".
						// 
																		// Set the current value to the current state of the tree.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample104$6" with its value "var66".
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - state.metric_mean[var66]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 157 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 157 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
						
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
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 1; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						if(state.fixedFlag$sample123) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var66 == state.st[sample][timeStep$var136])) {
								// Mark that the sample has observed constrained data.
								state.constrainedFlag$sample68[var66] = true;
								
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var50 = state.st[sample][timeStep$var136];
								if(((0 <= var50) && (var50 < state.noStates))) {
																											// Set the current value to the current state of the tree.
									// 
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - state.metric_mean[state.st[sample][timeStep$var136]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
									
									// Recorded the probability of reaching sample task 157 with the current configuration.
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
																					// Substituted "index$sample$15" with its value "sample".
							// 
							// Substituted "index$sample123$17" with its value "var66".
							double cv$probabilitySample123Value18 = state.distribution$sample123[sample][(timeStep$var136 - 1)][var66];
							
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample68[var66] = true;
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
																					// Set the current value to the current state of the tree.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "index$sample123$17" with its value "var66".
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - state.metric_mean[var66]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 157 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 157 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
							
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
		if(state.constrainedFlag$sample68[var66]) {
			// Guards to ensure that metric_var is only updated when there is a valid path.
			state.metric_var[var66] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
			
			// Looking for a path between Sample 68 and consumer Gaussian 150.
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 < state.length$metric[sample]) && state.metric_valid_g[sample][0])) {
					if(state.fixedFlag$sample104) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var66 == state.st[sample][0])) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample68[var66] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var50 = state.st[sample][0];
							
																					// Substituted "timeStep$var136" with its value "0".
							if(((0 <= var50) && (var50 < state.noStates))) {
								// Substituted "timeStep$var136" with its value "0".
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "timeStep$var136" with its value "0".
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - state.metric_mean[state.st[sample][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 157 with the current configuration.
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
						// Substituted "index$sample$5" with its value "sample".
						// 
						// Substituted "index$sample104$6" with its value "var66".
						double cv$probabilitySample104Value7 = state.distribution$sample104[sample][var66];
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample68[var66] = true;
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "timeStep$var136" with its value "0".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample104$6" with its value "var66".
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - state.metric_mean[var66]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 157 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 157 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
						
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
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 1; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						if(state.fixedFlag$sample123) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var66 == state.st[sample][timeStep$var136])) {
								// Mark that the sample has observed constrained data.
								state.constrainedFlag$sample68[var66] = true;
								
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var50 = state.st[sample][timeStep$var136];
								if(((0 <= var50) && (var50 < state.noStates))) {
									// Constructing a random variable input for use later.
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - state.metric_mean[state.st[sample][timeStep$var136]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
									
									// Recorded the probability of reaching sample task 157 with the current configuration.
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
																					// Substituted "index$sample$15" with its value "sample".
							// 
							// Substituted "index$sample123$17" with its value "var66".
							double cv$probabilitySample123Value18 = state.distribution$sample123[sample][(timeStep$var136 - 1)][var66];
							
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample68[var66] = true;
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "index$sample123$17" with its value "var66".
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - state.metric_mean[var66]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 157 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 157 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
							
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
				// Guards to ensure that metric_var is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				state.metric_var[var66] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 84 drawn from Beta 71. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample84(int var82) {
		state.constrainedFlag$sample84[var82] = false;
		
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		
		// Processing random variable 140.
		// 
		// Looking for a path between Sample 84 and consumer Bernoulli 140.
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.length$metric[sample])) {
				if(state.fixedFlag$sample104) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var82 == state.st[sample][0])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample84[var82] = true;
						
						// Include the value sampled by task 145 from random variable var140.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						// 
						// Substituted "timeStep$var136" with its value "0".
						if(state.metric_valid_g[sample][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$4" with its value "sample".
					// 
					// Substituted "index$sample104$5" with its value "var82".
					double cv$probabilitySample104Value6 = state.distribution$sample104[sample][var82];
					
					// Processing sample task 145 of consumer random variable null.
					// 
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample84[var82] = true;
					
					// Include the value sampled by task 145 from random variable var140.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + cv$probabilitySample104Value6);
					
					// If the sample value was positive increase the count
					// 
					// Substituted "timeStep$var136" with its value "0".
					if(state.metric_valid_g[sample][0])
						cv$sum = (cv$sum + cv$probabilitySample104Value6);
				}
			}
		}
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			for(int timeStep$var136 = 1; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
				if(state.fixedFlag$sample123) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var82 == state.st[sample][timeStep$var136])) {
						// Processing sample task 145 of consumer random variable null.
						// 
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample84[var82] = true;
						
						// Include the value sampled by task 145 from random variable var140.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						if(state.metric_valid_g[sample][timeStep$var136])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
															// Substituted "index$sample$14" with its value "sample".
					// 
					// Substituted "index$sample123$16" with its value "var82".
					double cv$probabilitySample123Value17 = state.distribution$sample123[sample][(timeStep$var136 - 1)][var82];
					
					// Processing sample task 145 of consumer random variable null.
					// 
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample84[var82] = true;
					
					// Include the value sampled by task 145 from random variable var140.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + cv$probabilitySample123Value17);
					
					// If the sample value was positive increase the count
					if(state.metric_valid_g[sample][timeStep$var136])
						cv$sum = (cv$sum + cv$probabilitySample123Value17);
				}
			}
		}
		if(state.constrainedFlag$sample84[var82])
			// Guards to ensure that metric_valid_bias is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			state.metric_valid_bias[var82] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Calculate the probability of the samples represented by sample104 using probability
	// distributions.
	private final void logProbabilityDistribution$sample104() {
		// Determine if we need to calculate the values for sample task 104 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample104) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(state.fixedFlag$sample104) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int sample = 0; sample < state.noSamples; sample += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = state.st[sample][0];
					
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
					state.logProbability$sample104[sample] = cv$distributionAccumulator;
				}
				
				// Update the variable probability
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
				
				// Add probability to model
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample104" with its value "true".
				state.fixedProbFlag$sample104 = state.fixedFlag$sample19;
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample104[sample]);
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(state.fixedFlag$sample104)
				// Update the variable probability
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample104)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample123 using probability
	// distributions.
	private final void logProbabilityDistribution$sample123() {
		// Determine if we need to calculate the values for sample task 123 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample123) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(state.fixedFlag$sample123) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int sample = 0; sample < state.noSamples; sample += 1) {
					for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// The sample value to calculate the probability of generating
						int cv$sampleValue = state.st[sample][timeStep$var113];
						
						// Enumerating the possible arguments for Categorical 120.
						if((1 == timeStep$var113)) {
							// Enumerating the possible arguments for Categorical 120.
							if(state.fixedFlag$sample104) {
								int var31 = state.st[sample][0];
								
																								// Substituted "timeStep$var113" with its value "1".
								if(((0 <= var31) && (var31 < state.noStates))) {
									// Substituted "timeStep$var113" with its value "1".
									double[] var119 = state.m[state.st[sample][0]];
									
									// Store the value of the function call, so the function call is only made once.
									cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									
									// Add the probability of this distribution configuration to the accumulator.
									// 
									// An accumulator for the distributed probability space covered.
									cv$probabilityReached = 1.0;
								}
							} else {
								// Enumerating the possible outputs of Categorical 101.
								for(int index$sample104$6 = 0; index$sample104$6 < state.noStates; index$sample104$6 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$5" with its value "sample".
									double cv$probabilitySample104Value7 = state.distribution$sample104[sample][index$sample104$6];
									double[] var119 = state.m[index$sample104$6];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample104Value7) + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value7);
								}
							}
						}
						
						// Substituted "index$sample$13_1" with its value "sample".
						// 
						// Substituted "index$timeStep$13_2" with its value "(timeStep$var113 - 1)".
						if((2 <= timeStep$var113)) {
							int var31 = state.st[sample][(timeStep$var113 - 1)];
							if(((0 <= var31) && (var31 < state.noStates))) {
								double[] var119 = state.m[state.st[sample][(timeStep$var113 - 1)]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								
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
						state.logProbability$sample123[sample][(timeStep$var113 - 1)] = cv$distributionAccumulator;
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
				// Substituted "fixedFlag$sample123" with its value "true".
				state.fixedProbFlag$sample123 = (state.fixedFlag$sample32 && state.fixedFlag$sample104);
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample123[sample][(timeStep$var113 - 1)]);
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(state.fixedFlag$sample123)
				// Update the variable probability
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample123)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample145 using probability
	// distributions.
	private final void logProbabilityDistribution$sample145() {
		// Determine if we need to calculate the values for sample task 145 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample145) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Look for paths between the variable and the sample task 145 including any distribution
					// values.
					// 
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = state.metric_valid_g[sample][timeStep$var136];
					
					// Enumerating the possible arguments for Bernoulli 140.
					if((0 == timeStep$var136)) {
						// Enumerating the possible arguments for Bernoulli 140.
						if(state.fixedFlag$sample104) {
							int var82 = state.st[sample][0];
							
																					// Substituted "timeStep$var136" with its value "0".
							if(((0 <= var82) && (var82 < state.noStates))) {
								// Substituted "timeStep$var136" with its value "0".
								double var139 = state.metric_valid_bias[state.st[sample][0]];
								
								// Store the value of the function call, so the function call is only made once.
								cv$distributionAccumulator = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						} else {
							// Enumerating the possible outputs of Categorical 101.
							for(int index$sample104$4 = 0; index$sample104$4 < state.noStates; index$sample104$4 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$3" with its value "sample".
								double cv$probabilitySample104Value5 = state.distribution$sample104[sample][index$sample104$4];
								double var139 = state.metric_valid_bias[index$sample104$4];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample104Value5) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value5);
							}
						}
					}
					
					// Enumerating the possible arguments for Bernoulli 140.
					if((1 <= timeStep$var136)) {
						// Enumerating the possible arguments for Bernoulli 140.
						if(state.fixedFlag$sample123) {
							int var82 = state.st[sample][timeStep$var136];
							if(((0 <= var82) && (var82 < state.noStates))) {
								double var139 = state.metric_valid_bias[state.st[sample][timeStep$var136]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
								
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
							// Enumerating the possible outputs of Categorical 120.
							for(int index$sample123$13 = 0; index$sample123$13 < state.noStates; index$sample123$13 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
																								// Substituted "index$sample$11" with its value "sample".
								double cv$probabilitySample123Value14 = state.distribution$sample123[sample][(timeStep$var136 - 1)][index$sample123$13];
								double var139 = state.metric_valid_bias[index$sample123$13];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample123Value14) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value14);
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
					state.logProbability$sample145[sample][timeStep$var136] = cv$distributionAccumulator;
				}
			}
			
			// Update the variable probability
			state.logProbability$metric_valid_1d = (state.logProbability$metric_valid_1d + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample145 = ((state.fixedFlag$sample84 && state.fixedFlag$sample104) && state.fixedFlag$sample123);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample145[sample][timeStep$var136]);
			}
			
			// Update the variable probability
			state.logProbability$metric_valid_1d = (state.logProbability$metric_valid_1d + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample157 using probability
	// distributions.
	private final void logProbabilityDistribution$sample157() {
		// Determine if we need to calculate the values for sample task 157 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample157) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 157 including any distribution
						// values.
						// 
						// The sample value to calculate the probability of generating
						double cv$sampleValue = state.var151[sample][timeStep$var136];
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == timeStep$var136)) {
							// Enumerating the possible arguments for Gaussian 150.
							// 
							// Enumerating the possible arguments for Gaussian 150.
							if(state.fixedFlag$sample104) {
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if((0 <= state.st[sample][0])) {
									int var50 = state.st[sample][0];
									
																											// Substituted "timeStep$var136" with its value "0".
									if(((0 <= var50) && (var50 < state.noStates))) {
										// Substituted "timeStep$var136" with its value "0".
										double var149 = state.metric_var[state.st[sample][0]];
										
										// Store the value of the function call, so the function call is only made once.
										// 
										// Substituted "timeStep$var136" with its value "0".
										cv$distributionAccumulator = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.metric_mean[state.st[sample][0]]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
										
										// Add the probability of this distribution configuration to the accumulator.
										// 
										// An accumulator for the distributed probability space covered.
										cv$probabilityReached = 1.0;
									}
								}
							} else {
								// Enumerating the possible outputs of Categorical 101.
								for(int index$sample104$4 = 0; index$sample104$4 < state.noStates; index$sample104$4 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$3" with its value "sample".
									double cv$probabilitySample104Value5 = state.distribution$sample104[sample][index$sample104$4];
									double var149 = state.metric_var[index$sample104$4];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample104Value5) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.metric_mean[index$sample104$4]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value5);
								}
							}
						}
						
						// Enumerating the possible arguments for Gaussian 150.
						if((1 <= timeStep$var136)) {
							// Enumerating the possible arguments for Gaussian 150.
							if(state.fixedFlag$sample123) {
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if((0 <= state.st[sample][timeStep$var136])) {
									int var50 = state.st[sample][timeStep$var136];
									if(((0 <= var50) && (var50 < state.noStates))) {
										double var149 = state.metric_var[state.st[sample][timeStep$var136]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.metric_mean[state.st[sample][timeStep$var136]]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
										
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
								// Enumerating the possible outputs of Categorical 120.
								for(int index$sample123$49 = 0; index$sample123$49 < state.noStates; index$sample123$49 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
																											// Substituted "index$sample$47" with its value "sample".
									double cv$probabilitySample123Value50 = state.distribution$sample123[sample][(timeStep$var136 - 1)][index$sample123$49];
									double var149 = state.metric_var[index$sample123$49];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample123Value50) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.metric_mean[index$sample123$49]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value50);
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
						state.logProbability$sample157[sample][timeStep$var136] = cv$distributionAccumulator;
					}
				}
			}
			
			// Update the variable probability
			state.logProbability$var151 = (state.logProbability$var151 + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample157 = (((state.fixedFlag$sample52 && state.fixedFlag$sample68) && state.fixedFlag$sample104) && state.fixedFlag$sample123);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136])
						cv$accumulator = (cv$accumulator + state.logProbability$sample157[sample][timeStep$var136]);
				}
			}
			
			// Update the variable probability
			state.logProbability$var151 = (state.logProbability$var151 + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample104 using sampled
	// values.
	private final void logProbabilityValue$sample104() {
		// Determine if we need to calculate the values for sample task 104 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample104) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.st[sample][0];
				
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
				state.logProbability$sample104[sample] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample104)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample104 = (state.fixedFlag$sample104 && state.fixedFlag$sample19);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample104[sample]);
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample104)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample123 using sampled
	// values.
	private final void logProbabilityValue$sample123() {
		// Determine if we need to calculate the values for sample task 123 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample123) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = state.st[sample][timeStep$var113];
					double[] var119 = state.m[state.st[sample][(timeStep$var113 - 1)]];
					
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
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					state.logProbability$sample123[sample][(timeStep$var113 - 1)] = cv$distributionAccumulator;
				}
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample123)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample123 = ((state.fixedFlag$sample123 && state.fixedFlag$sample32) && state.fixedFlag$sample104);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample123[sample][(timeStep$var113 - 1)]);
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample123)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample145 using sampled
	// values.
	private final void logProbabilityValue$sample145() {
		// Determine if we need to calculate the values for sample task 145 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample145) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					double var139 = state.metric_valid_bias[state.st[sample][timeStep$var136]];
					
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
					double cv$distributionAccumulator = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[sample][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					state.logProbability$sample145[sample][timeStep$var136] = cv$distributionAccumulator;
				}
			}
			
			// Update the variable probability
			state.logProbability$metric_valid_1d = (state.logProbability$metric_valid_1d + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample145 = ((state.fixedFlag$sample84 && state.fixedFlag$sample104) && state.fixedFlag$sample123);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample145[sample][timeStep$var136]);
			}
			
			// Update the variable probability
			state.logProbability$metric_valid_1d = (state.logProbability$metric_valid_1d + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample157 using sampled
	// values.
	private final void logProbabilityValue$sample157() {
		// Determine if we need to calculate the values for sample task 157 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample157) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						double var149 = state.metric_var[state.st[sample][timeStep$var136]];
						
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
						double cv$distributionAccumulator = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - state.metric_mean[state.st[sample][timeStep$var136]]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
						
						// Add the probability of this instance of the random variable to the probability
						// of all instances of the random variable.
						// 
						// Add the probability of this sample task to the sample task accumulator.
						// 
						// Accumulator for sample probabilities for a specific instance of the random variable.
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						
						// Store the sample task probability
						state.logProbability$sample157[sample][timeStep$var136] = cv$distributionAccumulator;
					}
				}
			}
			
			// Update the variable probability
			state.logProbability$var151 = (state.logProbability$var151 + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample157 = (((state.fixedFlag$sample52 && state.fixedFlag$sample68) && state.fixedFlag$sample104) && state.fixedFlag$sample123);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136])
						cv$accumulator = (cv$accumulator + state.logProbability$sample157[sample][timeStep$var136]);
				}
			}
			
			// Update the variable probability
			state.logProbability$var151 = (state.logProbability$var151 + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample19 using sampled
	// values.
	private final void logProbabilityValue$sample19() {
		// Determine if we need to calculate the values for sample task 19 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample19) {
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
			if(state.fixedFlag$sample19)
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
			state.fixedProbFlag$sample19 = state.fixedFlag$sample19;
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
			if(state.fixedFlag$sample19)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$initialStateDistribution);
		}
	}

	// Calculate the probability of the samples represented by sample32 using sampled
	// values.
	private final void logProbabilityValue$sample32() {
		// Determine if we need to calculate the values for sample task 32 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample32) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var31 = 0; var31 < state.noStates; var31 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.m[var31], state.v, state.noStates));
			
			// Store the random variable instance probability
			state.logProbability$var32 = cv$sampleAccumulator;
			
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
			if(state.fixedFlag$sample32)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample32 = state.fixedFlag$sample32;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$m = (state.logProbability$m + state.logProbability$var32);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var32);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample32)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var32);
		}
	}

	// Calculate the probability of the samples represented by sample52 using sampled
	// values.
	private final void logProbabilityValue$sample52() {
		// Determine if we need to calculate the values for sample task 52 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample52) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var50 = 0; var50 < state.noStates; var50 += 1) {
				// The sample value to calculate the probability of generating
				double cv$sampleValue = state.metric_mean[var50];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY));
			}
			
			// Store the random variable instance probability
			state.logProbability$var51 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$metric_mean = (state.logProbability$metric_mean + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample52)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample52 = state.fixedFlag$sample52;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$metric_mean = (state.logProbability$metric_mean + state.logProbability$var51);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var51);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample52)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var51);
		}
	}

	// Calculate the probability of the samples represented by sample68 using sampled
	// values.
	private final void logProbabilityValue$sample68() {
		// Determine if we need to calculate the values for sample task 68 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample68) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var66 = 0; var66 < state.noStates; var66 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(state.metric_var[var66], 1.0, 1.0));
			
			// Store the random variable instance probability
			state.logProbability$var67 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$metric_var = (state.logProbability$metric_var + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample68)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample68 = state.fixedFlag$sample68;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$metric_var = (state.logProbability$metric_var + state.logProbability$var67);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var67);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample68)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var67);
		}
	}

	// Calculate the probability of the samples represented by sample84 using sampled
	// values.
	private final void logProbabilityValue$sample84() {
		// Determine if we need to calculate the values for sample task 84 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample84) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var82 = 0; var82 < state.noStates; var82 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(state.metric_valid_bias[var82], 1.0, 1.0));
			
			// Store the random variable instance probability
			state.logProbability$var83 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$metric_valid_bias = (state.logProbability$metric_valid_bias + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample84)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample84 = state.fixedFlag$sample84;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$metric_valid_bias = (state.logProbability$metric_valid_bias + state.logProbability$var83);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var83);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample84)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var83);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample32) {
			for(int var31 = 0; var31 < state.noStates; var31 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var31]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample52) {
			for(int var50 = 0; var50 < state.noStates; var50 += 1)
				state.metric_mean[var50] = (DistributionSampling.sampleUniform(state.RNG$) * 100.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample68) {
			for(int var66 = 0; var66 < state.noStates; var66 += 1)
				state.metric_var[var66] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample84) {
			for(int var82 = 0; var82 < state.noStates; var82 += 1)
				state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			if(!state.fixedFlag$sample104)
				state.st[sample][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample123) {
				int[] var114 = state.st[sample];
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
					var114[timeStep$var113] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample][(timeStep$var113 - 1)]], state.noStates);
			}
			boolean[] metric_valid_1d = state.metric_valid_g[sample];
			double[] metric_1d = state.metric_g[sample];
			for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
				metric_valid_1d[timeStep$var136] = DistributionSampling.sampleBernoulli(state.RNG$, state.metric_valid_bias[state.st[sample][timeStep$var136]]);
				if(metric_valid_1d[timeStep$var136]) {
					if(!state.fixedFlag$sample157)
						state.var151[sample][timeStep$var136] = ((Math.sqrt(state.metric_var[state.st[sample][timeStep$var136]]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.metric_mean[state.st[sample][timeStep$var136]]);
					metric_1d[timeStep$var136] = state.var151[sample][timeStep$var136];
				}
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample32) {
			for(int var31 = 0; var31 < state.noStates; var31 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var31]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample52) {
			for(int var50 = 0; var50 < state.noStates; var50 += 1)
				state.metric_mean[var50] = (DistributionSampling.sampleUniform(state.RNG$) * 100.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample68) {
			for(int var66 = 0; var66 < state.noStates; var66 += 1)
				state.metric_var[var66] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample84) {
			for(int var82 = 0; var82 < state.noStates; var82 += 1)
				state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample104) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample104 = state.distribution$sample104[sample];
				for(int index$var101 = 0; index$var101 < state.noStates; index$var101 += 1)
					// Save the probability of each value
					// 
					// Probability for this value
					cv$distribution$sample104[index$var101] = (((0.0 <= state.initialStateDistribution[index$var101]) && (state.initialStateDistribution[index$var101] <= 1.0))?state.initialStateDistribution[index$var101]:0.0);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample123) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
					// Create local copy of variable probabilities.
					double[] cv$distribution$sample123 = state.distribution$sample123[sample][(timeStep$var113 - 1)];
					for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1)
						// Zero the probability of each value
						cv$distribution$sample123[index$var120] = 0.0;
					
					// Iterate through possible values for var120's arguments.
					// 
					// Enumerating the possible arguments for Categorical 120.
					if((1 == timeStep$var113)) {
						// Iterate through possible values for var120's arguments.
						// 
						// Enumerating the possible arguments for Categorical 120.
						if(state.fixedFlag$sample104) {
							int var31 = state.st[sample][0];
							
																					// Substituted "timeStep$var113" with its value "1".
							if(((0 <= var31) && (var31 < state.noStates))) {
								// Substituted "timeStep$var113" with its value "1".
								double[] var119 = state.m[state.st[sample][0]];
								for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1)
									// Save the probability of each value
									cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (((0.0 <= var119[index$var120]) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0));
							}
						} else {
							// Enumerating the possible outputs of Categorical 101.
							for(int index$sample104$3 = 0; index$sample104$3 < state.noStates; index$sample104$3 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$2" with its value "sample".
								double cv$probabilitySample104Value4 = state.distribution$sample104[sample][index$sample104$3];
								double[] var119 = state.m[index$sample104$3];
								for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1)
									// Save the probability of each value
									cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (cv$probabilitySample104Value4 * (((0.0 <= var119[index$var120]) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0)));
							}
						}
					}
					int index$timeStep$11 = (timeStep$var113 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$sample$10" with its value "sample".
					if((1 <= index$timeStep$11)) {
						// Enumerating the possible outputs of Categorical 120.
						for(int index$sample123$12 = 0; index$sample123$12 < state.noStates; index$sample123$12 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$10" with its value "sample".
							double cv$probabilitySample123Value13 = state.distribution$sample123[sample][(index$timeStep$11 - 1)][index$sample123$12];
							double[] var119 = state.m[index$sample123$12];
							for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1)
								// Save the probability of each value
								cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (cv$probabilitySample123Value13 * (((0.0 <= var119[index$var120]) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0)));
						}
					}
					
					// Sum the values in the array
					double cv$var120$sum = 0.0;
					for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1)
						// sum the probability of each value
						cv$var120$sum = (cv$var120$sum + cv$distribution$sample123[index$var120]);
					for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1)
						// Normalise the probability of each value
						cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] / cv$var120$sum);
				}
			}
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample32) {
			for(int var31 = 0; var31 < state.noStates; var31 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var31]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample52) {
			for(int var50 = 0; var50 < state.noStates; var50 += 1)
				state.metric_mean[var50] = (DistributionSampling.sampleUniform(state.RNG$) * 100.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample68) {
			for(int var66 = 0; var66 < state.noStates; var66 += 1)
				state.metric_var[var66] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample84) {
			for(int var82 = 0; var82 < state.noStates; var82 += 1)
				state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			if(!state.fixedFlag$sample104)
				state.st[sample][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample123) {
				int[] var114 = state.st[sample];
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
					var114[timeStep$var113] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample][(timeStep$var113 - 1)]], state.noStates);
			}
			boolean[] metric_valid_1d = state.metric_valid_g[sample];
			double[] metric_1d = state.metric_g[sample];
			for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
				metric_valid_1d[timeStep$var136] = DistributionSampling.sampleBernoulli(state.RNG$, state.metric_valid_bias[state.st[sample][timeStep$var136]]);
				if(metric_valid_1d[timeStep$var136]) {
					if(!state.fixedFlag$sample157)
						state.var151[sample][timeStep$var136] = ((Math.sqrt(state.metric_var[state.st[sample][timeStep$var136]]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.metric_mean[state.st[sample][timeStep$var136]]);
					metric_1d[timeStep$var136] = state.var151[sample][timeStep$var136];
				}
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample32) {
			for(int var31 = 0; var31 < state.noStates; var31 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var31]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample52) {
			for(int var50 = 0; var50 < state.noStates; var50 += 1)
				state.metric_mean[var50] = (DistributionSampling.sampleUniform(state.RNG$) * 100.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample68) {
			for(int var66 = 0; var66 < state.noStates; var66 += 1)
				state.metric_var[var66] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample84) {
			for(int var82 = 0; var82 < state.noStates; var82 += 1)
				state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			if(!state.fixedFlag$sample104)
				state.st[sample][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample123) {
				int[] var114 = state.st[sample];
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
					var114[timeStep$var113] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample][(timeStep$var113 - 1)]], state.noStates);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample32) {
			for(int var31 = 0; var31 < state.noStates; var31 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var31]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample52) {
			for(int var50 = 0; var50 < state.noStates; var50 += 1)
				state.metric_mean[var50] = (DistributionSampling.sampleUniform(state.RNG$) * 100.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample68) {
			for(int var66 = 0; var66 < state.noStates; var66 += 1)
				state.metric_var[var66] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample84) {
			for(int var82 = 0; var82 < state.noStates; var82 += 1)
				state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			if(!state.fixedFlag$sample104)
				state.st[sample][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample123) {
				int[] var114 = state.st[sample];
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
					var114[timeStep$var113] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample][(timeStep$var113 - 1)]], state.noStates);
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample19)
				inferSample19();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample32) {
				for(int var31 = 0; var31 < state.noStates; var31 += 1)
					inferSample32(var31);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample52) {
				for(int var50 = 0; var50 < state.noStates; var50 += 1)
					inferSample52(var50);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample68) {
				for(int var66 = 0; var66 < state.noStates; var66 += 1)
					inferSample68(var66);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample84) {
				for(int var82 = 0; var82 < state.noStates; var82 += 1)
					inferSample84(var82);
			}
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				if(!state.fixedFlag$sample104)
					inferSample104(sample);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!state.fixedFlag$sample123) {
					for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
						inferSample123(sample, timeStep$var113);
				}
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int sample = (state.noSamples - 1); sample >= 0; sample -= 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!state.fixedFlag$sample123) {
					for(int timeStep$var113 = (state.length$metric[sample] - 1); timeStep$var113 >= 1; timeStep$var113 -= 1)
						inferSample123(sample, timeStep$var113);
				}
				if(!state.fixedFlag$sample104)
					inferSample104(sample);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample84) {
				for(int var82 = (state.noStates - 1); var82 >= 0; var82 -= 1)
					inferSample84(var82);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample68) {
				for(int var66 = (state.noStates - 1); var66 >= 0; var66 -= 1)
					inferSample68(var66);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample52) {
				for(int var50 = (state.noStates - 1); var50 >= 0; var50 -= 1)
					inferSample52(var50);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample32) {
				for(int var31 = (state.noStates - 1); var31 >= 0; var31 -= 1)
					inferSample32(var31);
			}
			if(!state.fixedFlag$sample19)
				inferSample19();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample19)
			drawValueSample19();
		for(int var31 = 0; var31 < state.noStates; var31 += 1) {
			if(!state.constrainedFlag$sample32[var31])
				drawValueSample32(var31);
		}
		for(int var50 = 0; var50 < state.noStates; var50 += 1) {
			if(!state.constrainedFlag$sample52[var50])
				drawValueSample52(var50);
		}
		for(int var66 = 0; var66 < state.noStates; var66 += 1) {
			if(!state.constrainedFlag$sample68[var66])
				drawValueSample68(var66);
		}
		for(int var82 = 0; var82 < state.noStates; var82 += 1) {
			if(!state.constrainedFlag$sample84[var82])
				drawValueSample84(var82);
		}
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			if(!state.constrainedFlag$sample104[sample])
				drawValueSample104(sample);
			for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
				if(!state.constrainedFlag$sample123[sample][(timeStep$var113 - 1)])
					drawValueSample123(sample, timeStep$var113);
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
		if(!state.fixedProbFlag$sample19)
			state.logProbability$initialStateDistribution = Double.NaN;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample32)
			state.logProbability$var32 = Double.NaN;
		state.logProbability$metric_mean = 0.0;
		if(!state.fixedProbFlag$sample52)
			state.logProbability$var51 = Double.NaN;
		state.logProbability$metric_var = 0.0;
		if(!state.fixedProbFlag$sample68)
			state.logProbability$var67 = Double.NaN;
		state.logProbability$metric_valid_bias = 0.0;
		if(!state.fixedProbFlag$sample84)
			state.logProbability$var83 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample104) {
			for(int sample = 0; sample < state.noSamples; sample += 1)
				state.logProbability$sample104[sample] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample123) {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
					state.logProbability$sample123[sample][(timeStep$var113 - 1)] = Double.NaN;
			}
		}
		state.logProbability$metric_valid_1d = 0.0;
		state.logProbability$metric_valid_g = 0.0;
		if(!state.fixedProbFlag$sample145) {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1)
					state.logProbability$sample145[sample][timeStep$var136] = Double.NaN;
			}
		}
		state.logProbability$var151 = 0.0;
		state.logProbability$metric_g = 0.0;
		if(!state.fixedProbFlag$sample157) {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1)
					state.logProbability$sample157[sample][timeStep$var136] = Double.NaN;
			}
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.noSamples = state.length$metric.length;
		for(int var15 = 0; var15 < state.noStates; var15 += 1)
			state.v[var15] = 0.1;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample32$1 = 0; index$constrainedFlag$sample32$1 < state.constrainedFlag$sample32.length; index$constrainedFlag$sample32$1 += 1)
			state.constrainedFlag$sample32[index$constrainedFlag$sample32$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample123$1 = 0; index$constrainedFlag$sample123$1 < state.constrainedFlag$sample123.length; index$constrainedFlag$sample123$1 += 1) {
			boolean[] cv$constrainedFlag$sample123$1 = state.constrainedFlag$sample123[index$constrainedFlag$sample123$1];
			for(int index$constrainedFlag$sample123$2 = 0; index$constrainedFlag$sample123$2 < cv$constrainedFlag$sample123$1.length; index$constrainedFlag$sample123$2 += 1)
				cv$constrainedFlag$sample123$1[index$constrainedFlag$sample123$2] = true;
		}
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample104$1 = 0; index$constrainedFlag$sample104$1 < state.constrainedFlag$sample104.length; index$constrainedFlag$sample104$1 += 1)
			state.constrainedFlag$sample104[index$constrainedFlag$sample104$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample84$1 = 0; index$constrainedFlag$sample84$1 < state.constrainedFlag$sample84.length; index$constrainedFlag$sample84$1 += 1)
			state.constrainedFlag$sample84[index$constrainedFlag$sample84$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample68$1 = 0; index$constrainedFlag$sample68$1 < state.constrainedFlag$sample68.length; index$constrainedFlag$sample68$1 += 1)
			state.constrainedFlag$sample68[index$constrainedFlag$sample68$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample52$1 = 0; index$constrainedFlag$sample52$1 < state.constrainedFlag$sample52.length; index$constrainedFlag$sample52$1 += 1)
			state.constrainedFlag$sample52[index$constrainedFlag$sample52$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample19)
			logProbabilityValue$sample19();
		if(state.fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(state.fixedFlag$sample52)
			logProbabilityValue$sample52();
		if(state.fixedFlag$sample68)
			logProbabilityValue$sample68();
		if(state.fixedFlag$sample84)
			logProbabilityValue$sample84();
		logProbabilityValue$sample145();
		logProbabilityValue$sample157();
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
		logProbabilityValue$sample19();
		logProbabilityValue$sample32();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample84();
		logProbabilityDistribution$sample104();
		logProbabilityDistribution$sample123();
		logProbabilityDistribution$sample145();
		logProbabilityDistribution$sample157();
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
		logProbabilityValue$sample19();
		logProbabilityValue$sample32();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample84();
		logProbabilityValue$sample104();
		logProbabilityValue$sample123();
		logProbabilityValue$sample145();
		logProbabilityValue$sample157();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Reset any fixed flags on observed values
		state.fixedFlag$sample157 = false;
		int cv$length1 = state.metric_valid_g.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = state.metric_valid[cv$index1];
			boolean[] cv$target2 = state.metric_valid_g[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
		for(int sample = (state.noSamples - 1); sample >= 0; sample -= 1) {
			for(int timeStep$var136 = (state.length$metric[sample] - 1); timeStep$var136 >= 0; timeStep$var136 -= 1) {
				state.metric_g[sample][timeStep$var136] = state.metric[sample][timeStep$var136];
				if(state.metric_valid_g[sample][timeStep$var136])
					// Looking for a path between Put 158 and consumer double 154.
					state.var151[sample][timeStep$var136] = state.metric_g[sample][timeStep$var136];
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
		     + "model HMMMetrics2(\n"
		     + "               double[][] metric,\n"
		     + "               boolean[][] metric_valid, \n"
		     + "               int noStates) {\n"
		     + "    \n"
		     + "    int noSamples = metric.length;\n"
		     + "\n"
		     + "    // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "    double[] v = new double[noStates] <~ 0.1;\n"
		     + "    double[] initialStateDistribution = dirichlet(v).sample();\n"
		     + "    double[][] m = dirichlet(v).sample(noStates);\n"
		     + "\n"
		     + "    //Allocate space for states\n"
		     + "    int[][] st = new int[noSamples][];\n"
		     + "\n"
		     + "    //Allocate space for generated metrics \n"
		     + "    double[][] metric_g = new double[noSamples][];\n"
		     + "    boolean[][] metric_valid_g = new boolean[noSamples][];\n"
		     + "    \n"
		     + "    //Calculate priors for the metric\n"
		     + "    double[] metric_mean = uniform(0.0, 100.0).sample(noStates);\n"
		     + "    double[] metric_var = inverseGamma(1.0, 1.0).sample(noStates);\n"
		     + "    double[] metric_valid_bias = beta(1.0, 1.0).sample(noStates);\n"
		     + "    \n"
		     + "    // Compute the values of each metric value\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        //Calculate all the state transitions\n"
		     + "        int streamLength = metric[sample].length;\n"
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
		     + "        \n"
		     + "        //Calculate metric values\n"
		     + "        double[] metric_1d = new double[streamLength];\n"
		     + "        metric_g[sample] = metric_1d;\n"
		     + "\n"
		     + "        boolean[] metric_valid_1d = new boolean[streamLength];\n"
		     + "        metric_valid_g[sample] = metric_valid_1d;\n"
		     + "\n"
		     + "        //Generate values.\n"
		     + "        for(int timeStep = 0; timeStep < streamLength; timeStep++){\n"
		     + "            int currentState = st[sample][timeStep];\n"
		     + "            \n"
		     + "            metric_valid_1d[timeStep] = bernoulli(metric_valid_bias[currentState]).sample();\n"
		     + "            if(metric_valid_1d[timeStep])\n"
		     + "                metric_1d[timeStep] = gaussian(metric_mean[currentState], metric_var[currentState]).sample();\n"
		     + "            // Observing here as a cast is required and doing it inside the for loops removes the need duplicate them later.\n"
		     + "            metric_1d[timeStep].observe(metric[sample][timeStep]);\n"
		     + "        }\n"
		     + "    }\n"
		     + "\n"
		     + "    //Tie the values to the measured values.\n"
		     + "    metric_valid_g.observe(metric_valid);\n"
		     + "}";
	}
}