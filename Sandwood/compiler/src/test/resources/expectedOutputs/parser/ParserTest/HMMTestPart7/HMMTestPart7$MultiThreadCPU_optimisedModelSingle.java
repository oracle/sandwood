package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMTestPart7$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMTestPart7.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart7$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$distributionAccumulator$var69;
		double[][] cv$var28$countGlobal;
		double[] cv$var52$stateProbabilityGlobal;
		double[] cv$var70$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var28$countGlobal
			// 
			// Allocation of cv$var28$countGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var28$countGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var28$countGlobal[cv$index] = new double[5];
			
			// Constructor for cv$distributionAccumulator$var69
			// 
			// Allocation of cv$distributionAccumulator$var69 for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 69. Initially set to the value
			// of putTask 29.
			cv$distributionAccumulator$var69 = new double[5];
			
			// Constructor for cv$var52$stateProbabilityGlobal
			// 
			// Allocation of cv$var52$stateProbabilityGlobal for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 51. Initially set to the value
			// of putTask 29.
			cv$var52$stateProbabilityGlobal = new double[5];
			
			// Allocation of cv$var70$stateProbabilityGlobal for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 69. Initially set to the value
			// of putTask 29.
			cv$var70$stateProbabilityGlobal = new double[5];
		}
	}


	public HMMTestPart7$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample28
	private final void drawValueSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, state.v, 5, state.m[var27]);
	}

	// Pick a value from the distribution for the unconditioned variable from sample45
	private final void drawValueSample45(int var43, int threadID$cv$var43, Rng RNG$) {
		state.bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample53
	private final void drawValueSample53() {
		state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 5);
	}

	// Pick a value from the distribution for the unconditioned variable from sample71
	private final void drawValueSample71(int i$var64) {
		state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], 5);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from Dirichlet 16. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		state.constrainedFlag$sample28[var27] = false;
		
		// A local reference to the scratch space.
		double[] cv$countLocal = scratch.cv$var28$countGlobal[threadID$cv$var27];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(((var27 == 0) && state.fixedFlag$sample53)) {
			// Looking for a path between Sample 28 and consumer Categorical 51.
			// 
			// Processing sample task 53 of consumer random variable null.
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample28[0] = true;
			
			// Increment the sample counter with the value sampled by sample task 53 of random
			// variable var51
			cv$countLocal[state.st[0]] = (cv$countLocal[state.st[0]] + 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample71) {
			// Processing random variable 69.
			// 
			// Looking for a path between Sample 28 and consumer Categorical 69.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < state.samples)) {
				if(state.fixedFlag$sample53) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var27 == state.st[0])) {
						// Processing sample task 71 of consumer random variable null.
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample28[var27] = true;
						
						// Increment the sample counter with the value sampled by sample task 71 of random
						// variable var69
						// 
																		// Substituted "i$var64" with its value "1".
						cv$countLocal[state.st[1]] = (cv$countLocal[state.st[1]] + 1.0);
					}
				} else {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var27 < 5)) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample28[var27] = true;
						
						// Increment the sample counter with the value sampled by sample task 71 of random
						// variable var69
						// 
																								// cv$probabilitySample53Value7's comment
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample53$6" with its value "var27".
						cv$countLocal[state.st[1]] = (cv$countLocal[state.st[1]] + state.distribution$sample53[var27]);
					}
				}
			}
			for(int i$var64 = 2; i$var64 < state.samples; i$var64 += 1) {
				if((var27 == state.st[(i$var64 - 1)])) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample28[var27] = true;
					
					// Increment the sample counter with the value sampled by sample task 71 of random
					// variable var69
					cv$countLocal[state.st[i$var64]] = (cv$countLocal[state.st[i$var64]] + 1.0);
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(((var27 == 0) && !state.fixedFlag$sample53)) {
			// Looking for a path between Sample 28 and consumer Categorical 51.
			// 
			// Processing sample task 53 of consumer random variable null.
			// 
			// Merge the distribution probabilities into the count
			// 
			// Get the length of the array
			for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// Add the probability of this argument configuration.
				// 
				// Declare and zero an accumulator for tracking the reached source probability space.
				cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + state.distribution$sample53[cv$loopIndex]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample71) {
			// Processing random variable 69.
			// 
			// Looking for a path between Sample 28 and consumer Categorical 69.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < state.samples)) {
				if(state.fixedFlag$sample53) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var27 == state.st[0])) {
						// Processing sample task 71 of consumer random variable null.
						// 
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
																					// The probability of reaching the consumer with this set of consumer arguments
							// 
							// Add the probability of this argument configuration.
							// 
							// Declare and zero an accumulator for tracking the reached source probability space.
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + state.distribution$sample71[0][cv$loopIndex]);
					}
				} else {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var27 < 5)) {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
												// cv$probabilitySample53Value38's comment
						// Update the probability of sampling this value from the distribution value.
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample53$37" with its value "var27".
						double cv$distributionProbability = state.distribution$sample53[var27];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							// Substituted "i$var64" with its value "1".
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample71[0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 < 5)) {
				for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
					int index$i$45 = (i$var64 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 <= index$i$45)) {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
												// cv$probabilitySample71Value47's comment
						// Update the probability of sampling this value from the distribution value.
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample71$46" with its value "var27".
						double cv$distributionProbability = state.distribution$sample71[(index$i$45 - 1)][var27];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample71[(i$var64 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		if(state.constrainedFlag$sample28[var27])
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
			// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, state.m[var27], 5);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 45 drawn from Beta 32. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample45(int var43, int threadID$cv$var43, Rng RNG$) {
		state.constrainedFlag$sample45[var43] = false;
		
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < state.samples)) {
			if(state.fixedFlag$sample53) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var43 == state.st[0])) {
					// Processing sample task 87 of consumer random variable null.
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample45[var43] = true;
					
					// Include the value sampled by task 87 from random variable var85.
					// 
					// Increment the number of samples.
					// 
					// Local variable to record the number of samples.
					cv$count = 1.0;
					
					// If the sample value was positive increase the count
					// 
					// Substituted "j" with its value "0".
					if(state.flips[0])
						// Local variable to record the number of true samples.
						cv$sum = 1.0;
				}
			} else {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var43 < 5)) {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample53$3" with its value "var43".
					double cv$probabilitySample53Value4 = state.distribution$sample53[var43];
					
					// Processing sample task 87 of consumer random variable null.
					// 
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample45[var43] = true;
					
					// Include the value sampled by task 87 from random variable var85.
					// 
					// Increment the number of samples.
					// 
					// Local variable to record the number of samples.
					cv$count = cv$probabilitySample53Value4;
					
					// If the sample value was positive increase the count
					// 
					// Substituted "j" with its value "0".
					if(state.flips[0])
						// Local variable to record the number of true samples.
						cv$sum = cv$probabilitySample53Value4;
				}
			}
		}
		for(int j = 1; j < state.samples; j += 1) {
			if(state.fixedFlag$sample71) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var43 == state.st[j])) {
					// Processing sample task 87 of consumer random variable null.
					// 
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample45[var43] = true;
					
					// Include the value sampled by task 87 from random variable var85.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + 1.0);
					
					// If the sample value was positive increase the count
					if(state.flips[j])
						cv$sum = (cv$sum + 1.0);
				}
			} else {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var43 < 5)) {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var64" with its value "j".
					// 
					// Substituted "index$sample71$12" with its value "var43".
					double cv$probabilitySample71Value13 = state.distribution$sample71[(j - 1)][var43];
					
					// Processing sample task 87 of consumer random variable null.
					// 
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample45[var43] = true;
					
					// Include the value sampled by task 87 from random variable var85.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + cv$probabilitySample71Value13);
					
					// If the sample value was positive increase the count
					if(state.flips[j])
						cv$sum = (cv$sum + cv$probabilitySample71Value13);
				}
			}
		}
		if(state.constrainedFlag$sample45[var43])
			// Guards to ensure that bias is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			state.bias[var43] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 53 drawn from Categorical 51. Inference was performed using variable
	// marginalization.
	private final void inferSample53() {
		state.constrainedFlag$sample53 = false;
		
				// cv$numStates's comment
		// variable marginalization
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Constructing a random variable input for use later.
			double[] var50 = state.m[0];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			double cv$accumulatedProbabilities = (((0.0 <= var50[cv$valuePos]) && (var50[cv$valuePos] <= 1.0))?Math.log(var50[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.fixedFlag$sample71 && (1 < state.samples))) {
				// Looking for a path between Sample 53 and consumer Categorical 69.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample53 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				double[] var68 = state.m[cv$valuePos];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 71 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var64" with its value "1".
				cv$accumulatedProbabilities = ((((((0.0 <= state.st[1]) && (state.st[1] < 5)) && (0.0 <= var68[state.st[1]])) && (var68[state.st[1]] <= 1.0))?Math.log(var68[state.st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Substituted "j" with its value "0".
			if((0 < state.samples)) {
				// Processing sample task 87 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample53 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Looking for a path between Sample 53 and consumer Bernoulli 85.
				// 
				// Value of the variable at this index
				double var84 = state.bias[cv$valuePos];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 87 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "j" with its value "0".
				cv$accumulatedProbabilities = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[0]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!state.fixedFlag$sample71 && (1 < state.samples))) {
				// Looking for a path between Sample 53 and consumer Categorical 69.
				// Processing sample task 71 of consumer random variable null.
				// 
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					scratch.cv$distributionAccumulator$var69[cv$i] = 0.0;
				
				// Add the current distribution to the distribution accumulator.
				// 
												// Constructing a random variable input for use later.
				// 
												// Value of the variable at this index
				DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var69, 1.0, state.m[cv$valuePos], 5);
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "i$var64" with its value "1".
				double[] cv$sampleDistribution = state.distribution$sample71[0];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < 5; cv$i += 1) {
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
					double cv$normalisedDistValue = scratch.cv$distributionAccumulator$var69[cv$i];
					
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
			scratch.cv$var52$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(state.constrainedFlag$sample53) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var52$stateProbabilityGlobal[0];
			
			// Unrolled loop
			{
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var52$stateProbabilityGlobal[1];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			{
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var52$stateProbabilityGlobal[2];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			{
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var52$stateProbabilityGlobal[3];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var52$stateProbabilityGlobal[4];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else {
				// Initialise the sum of the array elements
				double cv$lseSum = 0.0;
				
				// Offset values, move to normal space, and sum.
				// 
								// cv$numStates's comment
				// variable marginalization
				for(int cv$lseIndex = 0; cv$lseIndex < 5; cv$lseIndex += 1)
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var52$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				// 
								// cv$numStates's comment
				// variable marginalization
				for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
															// Local copy of the probability array
					state.distribution$sample53[cv$indexName] = 0.2;
			} else {
				// Normalize log space values and move to normal space
				// 
								// cv$numStates's comment
				// variable marginalization
				for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
															// Local copy of the probability array
					state.distribution$sample53[cv$indexName] = Math.exp((scratch.cv$var52$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 5; cv$indexName < scratch.cv$var52$stateProbabilityGlobal.length; cv$indexName += 1)
				// Local copy of the probability array
				state.distribution$sample53[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 71 drawn from Categorical 69. Inference was performed using variable
	// marginalization.
	private final void inferSample71(int i$var64) {
		state.constrainedFlag$sample71[(i$var64 - 1)] = false;
		
		// Calculate the number of states to evaluate.
		int cv$numStates = 0;
		
		// Exploring all the possible state counts for random variable 69.
		// 
		// Enumerating the possible arguments for Categorical 69.
		if((1 == i$var64)) {
			// Exploring all the possible state counts for random variable 69.
			// 
			// Enumerating the possible arguments for Categorical 69.
			if(state.fixedFlag$sample53) {
				int var27 = state.st[0];
				
												// Substituted "i$var64" with its value "1".
				if(((0 <= var27) && (var27 < 5)))
					// variable marginalization
					cv$numStates = 5;
			} else
				// variable marginalization
				cv$numStates = 5;
		}
		int index$i$10 = (i$var64 - 1);
		
								// index$i$1's comment
		// Copy of index so that its values can be safely substituted
		// 
						// Substituted "index$i$10" with its value "(i$var64 - 1)".
		// 
						// Substituted "index$i$10" with its value "(i$var64 - 1)".
		// 
						// Substituted "index$i$10" with its value "(i$var64 - 1)".
		// 
						// Substituted "index$i$10" with its value "(i$var64 - 1)".
		// 
						// Substituted "index$i$10" with its value "(i$var64 - 1)".
		// 
						// Substituted "index$i$10" with its value "(i$var64 - 1)".
		// 
						// Substituted "index$i$10" with its value "(i$var64 - 1)".
		if(((1 <= index$i$10) && !(index$i$10 == i$var64)))
			// variable marginalization
			cv$numStates = 5;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 69 creating
			// sample task 71.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Enumerating the possible arguments for Categorical 69.
			if((1 == i$var64)) {
				// Enumerating the possible arguments for Categorical 69.
				if(state.fixedFlag$sample53) {
					int var27 = state.st[0];
					
															// Substituted "i$var64" with its value "1".
					if(((0 <= var27) && (var27 < 5))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var64" with its value "1".
						double[] var68 = state.m[state.st[0]];
						
						// Mark that the sample has observed constrained data.
						// 
						// Substituted "i$var64" with its value "1".
						state.constrainedFlag$sample71[0] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Looking for a path between Sample 71 and consumer Bernoulli 85.
						// 
						// Value of the variable at this index
						double var84 = state.bias[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 87 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
																		// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
																		// Value of the variable at this index
						cv$stateProbabilityValue = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[1]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + (((0.0 <= var68[cv$valuePos]) && (var68[cv$valuePos] <= 1.0))?Math.log(var68[cv$valuePos]):Double.NEGATIVE_INFINITY));
					}
				} else {
					// Enumerating the possible outputs of Categorical 51.
					for(int index$sample53$18 = 0; index$sample53$18 < 5; index$sample53$18 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample53Value19 = state.distribution$sample53[index$sample53$18];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample53Value19);
						
						// Constructing a random variable input for use later.
						double[] var68 = state.m[index$sample53$18];
						
						// Mark that the sample has observed constrained data.
						// 
						// Substituted "i$var64" with its value "1".
						state.constrainedFlag$sample71[0] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Looking for a path between Sample 71 and consumer Bernoulli 85.
						// 
						// Value of the variable at this index
						double var84 = state.bias[cv$valuePos];
						
						// Variable declaration of cv$accumulatedProbabilities moved.
						// Declaration comment was:
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
																		// Value of the variable at this index
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 87 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
																		// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
																		// Value of the variable at this index
						double cv$accumulatedProbabilities = (((((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[1]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + Math.log(cv$probabilitySample53Value19)) + (((0.0 <= var68[cv$valuePos]) && (var68[cv$valuePos] <= 1.0))?Math.log(var68[cv$valuePos]):Double.NEGATIVE_INFINITY));
						
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
			int index$i$25 = (i$var64 - 1);
			
												// index$i$1's comment
			// Copy of index so that its values can be safely substituted
			// 
									// Substituted "index$i$25" with its value "(i$var64 - 1)".
			// 
									// Substituted "index$i$25" with its value "(i$var64 - 1)".
			// 
									// Substituted "index$i$25" with its value "(i$var64 - 1)".
			// 
									// Substituted "index$i$25" with its value "(i$var64 - 1)".
			// 
									// Substituted "index$i$25" with its value "(i$var64 - 1)".
			// 
									// Substituted "index$i$25" with its value "(i$var64 - 1)".
			// 
									// Substituted "index$i$25" with its value "(i$var64 - 1)".
			if(((1 <= index$i$25) && !(index$i$25 == i$var64))) {
				// Enumerating the possible outputs of Categorical 69.
				for(int index$sample71$26 = 0; index$sample71$26 < 5; index$sample71$26 += 1) {
					// Update the probability of sampling this value from the distribution value.
					double cv$probabilitySample71Value27 = state.distribution$sample71[(index$i$25 - 1)][index$sample71$26];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample71Value27);
					
					// Constructing a random variable input for use later.
					double[] var68 = state.m[index$sample71$26];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample71[(i$var64 - 1)] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Looking for a path between Sample 71 and consumer Bernoulli 85.
					double var84 = state.bias[index$sample71$26];
					
					// Variable declaration of cv$accumulatedProbabilities moved.
					// Declaration comment was:
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
															// Value of the variable at this index
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 87 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
															// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
															// Value of the variable at this index
					double cv$accumulatedProbabilities = (((((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[i$var64]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + Math.log(cv$probabilitySample71Value27)) + (((0.0 <= var68[cv$valuePos]) && (var68[cv$valuePos] <= 1.0))?Math.log(var68[cv$valuePos]):Double.NEGATIVE_INFINITY));
					
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
			int index$i$52_2 = (i$var64 + 1);
			if((index$i$52_2 < state.samples)) {
				// Processing sample task 71 of consumer random variable null.
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					scratch.cv$distributionAccumulator$var69[cv$i] = 0.0;
				
				// Declare and zero an accumulator for tracking the reached source probability space.
				double scopeVariable$reachedSourceProbability = 0.0;
				
				// Enumerating the possible arguments for Categorical 69.
				if((1 == i$var64)) {
					// Enumerating the possible arguments for Categorical 69.
					if(state.fixedFlag$sample53) {
						int index$var27$61_1 = state.st[0];
						
																		// Substituted "i$var64" with its value "1".
						if(((0 <= index$var27$61_1) && (index$var27$61_1 < 5)))
							// Add the probability of this argument configuration.
							// 
							// Declare and zero an accumulator for tracking the reached source probability space.
							scopeVariable$reachedSourceProbability = 1.0;
					} else {
						// Enumerating the possible outputs of Categorical 51.
						for(int index$sample53$57 = 0; index$sample53$57 < 5; index$sample53$57 += 1)
							// Add the probability of this argument configuration.
							// 
														// cv$probabilitySample53Value58's comment
							// Update the probability of sampling this value from the distribution value.
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample53[index$sample53$57]);
					}
				}
				int index$i$64 = (i$var64 - 1);
				
																// index$i$54's comment
				// Copy of index so that its values can be safely substituted
				// 
												// Substituted "index$i$52_2" with its value "(i$var64 + 1)".
				// 
												// Substituted "index$i$52_2" with its value "(i$var64 + 1)".
				// 
												// Substituted "index$i$52_2" with its value "(i$var64 + 1)".
				// 
												// Substituted "index$i$52_2" with its value "(i$var64 + 1)".
				// 
												// Substituted "index$i$52_2" with its value "(i$var64 + 1)".
				// 
												// Substituted "index$i$52_2" with its value "(i$var64 + 1)".
				// 
												// Substituted "index$i$52_2" with its value "(i$var64 + 1)".
				if((((1 <= index$i$64) && !(index$i$64 == i$var64)) && !(index$i$64 == index$i$52_2))) {
					// Enumerating the possible outputs of Categorical 69.
					for(int index$sample71$65 = 0; index$sample71$65 < 5; index$sample71$65 += 1)
						// Add the probability of this argument configuration.
						// 
												// cv$probabilitySample71Value66's comment
						// Update the probability of sampling this value from the distribution value.
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample71[(index$i$64 - 1)][index$sample71$65]);
				}
				
				// Add the current distribution to the distribution accumulator.
				// 
												// Constructing a random variable input for use later.
				// 
				// Processing random variable 69.
				// 
				// Looking for a path between Sample 71 and consumer Categorical 69.
				// 
				// Value of the variable at this index
				DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var69, scopeVariable$reachedSourceProbability, state.m[cv$valuePos], 5);
				
				// A local copy of the samples' distribution.
				double[] cv$sampleDistribution = state.distribution$sample71[(index$i$52_2 - 1)];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < 5; cv$i += 1) {
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
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var69[cv$i] / scopeVariable$reachedSourceProbability);
					
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
			scratch.cv$var70$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(state.constrainedFlag$sample71[(i$var64 - 1)]) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// Local copy of the probability array
			double[] cv$localProbability = state.distribution$sample71[(i$var64 - 1)];
			
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var70$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var70$stateProbabilityGlobal[cv$lseIndex];
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
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var70$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
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
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var70$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var70$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Calculate the probability of the samples represented by sample53 using probability
	// distributions.
	private final void logProbabilityDistribution$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample53) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(state.fixedFlag$sample53) {
				// Generating probabilities for sample task
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.st[0];
				double[] var50 = state.m[0];
				
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
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var50[cv$sampleValue])) && (var50[cv$sampleValue] <= 1.0))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Store the sample task probability
				state.logProbability$var52 = cv$distributionAccumulator;
				
				// Update the variable probability
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
				state.logProbability$st = (state.logProbability$st + cv$distributionAccumulator);
				
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
				// 
				// Substituted "fixedFlag$sample53" with its value "true".
				state.fixedProbFlag$sample53 = state.fixedFlag$sample28;
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(state.fixedFlag$sample53)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				state.logProbability$st = (state.logProbability$st + state.logProbability$var52);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var52);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample53)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var52);
		}
	}

	// Calculate the probability of the samples represented by sample71 using probability
	// distributions.
	private final void logProbabilityDistribution$sample71() {
		// Determine if we need to calculate the values for sample task 71 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample71) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(state.fixedFlag$sample71) {
				// Generating probabilities for sample task
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// A guard to check if the sample value is ever reached.
				boolean cv$sampleReached = false;
				for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// The sample value to calculate the probability of generating
					int cv$sampleValue = state.st[i$var64];
					
					// Enumerating the possible arguments for Categorical 69.
					if((1 == i$var64)) {
						// Enumerating the possible arguments for Categorical 69.
						if(state.fixedFlag$sample53) {
							int var27 = state.st[0];
							
																					// Substituted "i$var64" with its value "1".
							if(((0 <= var27) && (var27 < 5))) {
								// Substituted "i$var64" with its value "1".
								double[] var68 = state.m[state.st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						} else {
							// Enumerating the possible outputs of Categorical 51.
							for(int index$sample53$4 = 0; index$sample53$4 < 5; index$sample53$4 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample53Value5 = state.distribution$sample53[index$sample53$4];
								double[] var68 = state.m[index$sample53$4];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample53Value5) + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample53Value5);
							}
						}
					}
					
					// Substituted "index$i$11_1" with its value "(i$var64 - 1)".
					if((2 <= i$var64)) {
						int var27 = state.st[(i$var64 - 1)];
						if(((0 <= var27) && (var27 < 5))) {
							double[] var68 = state.m[state.st[(i$var64 - 1)]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY);
							
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
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				}
				
				// Only update the sample if it was reached, otherwise the NaN will be
				// erroneously over written.
				if(cv$sampleReached)
					// Store the random variable instance probability
					// 
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Accumulator for probabilities of instances of the random variable
					state.logProbability$var70 = cv$sampleAccumulator;
				
				// Update the variable probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$st = (state.logProbability$st + cv$sampleAccumulator);
				
				// Add probability to model
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample71" with its value "true".
				state.fixedProbFlag$sample71 = (state.fixedFlag$sample28 && state.fixedFlag$sample53);
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(state.fixedFlag$sample71)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				state.logProbability$st = (state.logProbability$st + state.logProbability$var70);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var70);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample71)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var70);
		}
	}

	// Calculate the probability of the samples represented by sample87 using probability
	// distributions.
	private final void logProbabilityDistribution$sample87() {
		// Determine if we need to calculate the values for sample task 87 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample87) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.samples; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 87 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = state.flips[j];
				
				// Enumerating the possible arguments for Bernoulli 85.
				if((0 == j)) {
					// Enumerating the possible arguments for Bernoulli 85.
					if(state.fixedFlag$sample53) {
						int var43 = state.st[0];
						
																		// Substituted "j" with its value "0".
						if(((0 <= var43) && (var43 < 5))) {
							// Substituted "j" with its value "0".
							double var84 = state.bias[state.st[0]];
							
							// Store the value of the function call, so the function call is only made once.
							cv$distributionAccumulator = (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY);
							
							// Add the probability of this distribution configuration to the accumulator.
							// 
							// An accumulator for the distributed probability space covered.
							cv$probabilityReached = 1.0;
						}
					} else {
						// Enumerating the possible outputs of Categorical 51.
						for(int index$sample53$3 = 0; index$sample53$3 < 5; index$sample53$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample53Value4 = state.distribution$sample53[index$sample53$3];
							double var84 = state.bias[index$sample53$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample53Value4) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample53Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Bernoulli 85.
				if((1 <= j)) {
					// Enumerating the possible arguments for Bernoulli 85.
					if(state.fixedFlag$sample71) {
						int var43 = state.st[j];
						if(((0 <= var43) && (var43 < 5))) {
							double var84 = state.bias[state.st[j]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY);
							
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
						// Enumerating the possible outputs of Categorical 69.
						for(int index$sample71$11 = 0; index$sample71$11 < 5; index$sample71$11 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var64" with its value "j".
							double cv$probabilitySample71Value12 = state.distribution$sample71[(j - 1)][index$sample71$11];
							double var84 = state.bias[index$sample71$11];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample71Value12) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value12);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$var86 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$flips = (state.logProbability$flips + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample87 = ((state.fixedFlag$sample45 && state.fixedFlag$sample53) && state.fixedFlag$sample71);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$flips = (state.logProbability$flips + state.logProbability$var86);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var86);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var86);
		}
	}

	// Calculate the probability of the samples represented by sample28 using sampled
	// values.
	private final void logProbabilityValue$sample28() {
		// Determine if we need to calculate the values for sample task 28 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample28) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var27 = 0; var27 < 5; var27 += 1) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.m[var27], state.v, 5));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				state.logProbability$var28 = cv$sampleAccumulator;
			
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
			if(state.fixedFlag$sample28)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample28 = state.fixedFlag$sample28;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$m = (state.logProbability$m + state.logProbability$var28);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var28);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample28)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var28);
		}
	}

	// Calculate the probability of the samples represented by sample45 using sampled
	// values.
	private final void logProbabilityValue$sample45() {
		// Determine if we need to calculate the values for sample task 45 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample45) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var43 = 0; var43 < 5; var43 += 1) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(state.bias[var43], 1.0, 1.0));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				state.logProbability$var44 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$bias = (state.logProbability$bias + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample45)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample45 = state.fixedFlag$sample45;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var44);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var44);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample45)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var44);
		}
	}

	// Calculate the probability of the samples represented by sample53 using sampled
	// values.
	private final void logProbabilityValue$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample53) {
			// Generating probabilities for sample task
			// The sample value to calculate the probability of generating
			int cv$sampleValue = state.st[0];
			double[] var50 = state.m[0];
			
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
			double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var50[cv$sampleValue])) && (var50[cv$sampleValue] <= 1.0))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$var52 = cv$distributionAccumulator;
			
			// Update the variable probability
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
			state.logProbability$st = (state.logProbability$st + cv$distributionAccumulator);
			
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
			if(state.fixedFlag$sample53)
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
			state.fixedProbFlag$sample53 = (state.fixedFlag$sample53 && state.fixedFlag$sample28);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$st = (state.logProbability$st + state.logProbability$var52);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var52);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample53)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var52);
		}
	}

	// Calculate the probability of the samples represented by sample71 using sampled
	// values.
	private final void logProbabilityValue$sample71() {
		// Determine if we need to calculate the values for sample task 71 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample71) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.st[i$var64];
				double[] var68 = state.m[state.st[(i$var64 - 1)]];
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$var70 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$st = (state.logProbability$st + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample71)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample71 = ((state.fixedFlag$sample71 && state.fixedFlag$sample28) && state.fixedFlag$sample53);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$st = (state.logProbability$st + state.logProbability$var70);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var70);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample71)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var70);
		}
	}

	// Calculate the probability of the samples represented by sample87 using sampled
	// values.
	private final void logProbabilityValue$sample87() {
		// Determine if we need to calculate the values for sample task 87 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample87) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.samples; j += 1) {
				double var84 = state.bias[state.st[j]];
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$var86 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$flips = (state.logProbability$flips + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample87 = ((state.fixedFlag$sample45 && state.fixedFlag$sample53) && state.fixedFlag$sample71);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$flips = (state.logProbability$flips + state.logProbability$var86);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var86);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var86);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var27]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample45)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 5);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], 5);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, state.bias[state.st[j]]);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var27]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample45)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample53) {
			double[] var50 = state.m[0];
			for(int index$var51 = 0; index$var51 < 5; index$var51 += 1)
				// Save the probability of each value
				// 
																// cv$distribution$sample53's comment
				// Create local copy of variable probabilities.
				state.distribution$sample53[index$var51] = (((0.0 <= var50[index$var51]) && (var50[index$var51] <= 1.0))?var50[index$var51]:0.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample71 = state.distribution$sample71[(i$var64 - 1)];
				for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
					// Zero the probability of each value
					cv$distribution$sample71[index$var69] = 0.0;
				
				// Iterate through possible values for var69's arguments.
				// 
				// Enumerating the possible arguments for Categorical 69.
				if((1 == i$var64)) {
					// Iterate through possible values for var69's arguments.
					// 
					// Enumerating the possible arguments for Categorical 69.
					if(state.fixedFlag$sample53) {
						int var27 = state.st[0];
						
																		// Substituted "i$var64" with its value "1".
						if(((0 <= var27) && (var27 < 5))) {
							// Substituted "i$var64" with its value "1".
							double[] var68 = state.m[state.st[0]];
							for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
								// Save the probability of each value
								cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (((0.0 <= var68[index$var69]) && (var68[index$var69] <= 1.0))?var68[index$var69]:0.0));
						}
					} else {
						// Enumerating the possible outputs of Categorical 51.
						for(int index$sample53$2 = 0; index$sample53$2 < 5; index$sample53$2 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample53Value3 = state.distribution$sample53[index$sample53$2];
							double[] var68 = state.m[index$sample53$2];
							for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
								// Save the probability of each value
								cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (cv$probabilitySample53Value3 * (((0.0 <= var68[index$var69]) && (var68[index$var69] <= 1.0))?var68[index$var69]:0.0)));
						}
					}
				}
				int index$i$9 = (i$var64 - 1);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 <= index$i$9)) {
					// Enumerating the possible outputs of Categorical 69.
					for(int index$sample71$10 = 0; index$sample71$10 < 5; index$sample71$10 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample71Value11 = state.distribution$sample71[(index$i$9 - 1)][index$sample71$10];
						double[] var68 = state.m[index$sample71$10];
						for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
							// Save the probability of each value
							cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (cv$probabilitySample71Value11 * (((0.0 <= var68[index$var69]) && (var68[index$var69] <= 1.0))?var68[index$var69]:0.0)));
					}
				}
				
				// Sum the values in the array
				double cv$var69$sum = 0.0;
				for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
					// sum the probability of each value
					cv$var69$sum = (cv$var69$sum + cv$distribution$sample71[index$var69]);
				for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
					// Normalise the probability of each value
					cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] / cv$var69$sum);
			}
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var27]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample45)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 5);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], 5);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, state.bias[state.st[j]]);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var27]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample45)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 5);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], 5);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var27]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample45)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 5);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], 5);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample28)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								inferSample28(var27, threadID$var27, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample45)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								inferSample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!state.fixedFlag$sample53)
				inferSample53();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample71) {
				for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
					inferSample71(i$var64);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample71) {
				for(int i$var64 = (state.samples - 1); i$var64 >= 1; i$var64 -= 1)
					inferSample71(i$var64);
			}
			if(!state.fixedFlag$sample53)
				inferSample53();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample45)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								inferSample45(var43, threadID$var43, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample28)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								inferSample28(var27, threadID$var27, RNG$1);
					}
				);

		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!state.constrainedFlag$sample28[var27])
							drawValueSample28(var27, threadID$var27, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!state.constrainedFlag$sample45[var43])
							drawValueSample45(var43, threadID$var43, RNG$1);
					}
			}
		);
		if(!state.constrainedFlag$sample53)
			drawValueSample53();
		for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
			if(!state.constrainedFlag$sample71[(i$var64 - 1)])
				drawValueSample71(i$var64);
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
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample28)
			state.logProbability$var28 = Double.NaN;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample45)
			state.logProbability$var44 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample53)
			state.logProbability$var52 = Double.NaN;
		if(!state.fixedProbFlag$sample71)
			state.logProbability$var70 = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample87)
			state.logProbability$var86 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		for(int i$var13 = 0; i$var13 < 5; i$var13 += 1)
			state.v[i$var13] = 0.1;
		state.samples = state.length$flipsMeasured;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < state.constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			state.constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < state.constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			state.constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample71$1 = 0; index$constrainedFlag$sample71$1 < state.constrainedFlag$sample71.length; index$constrainedFlag$sample71$1 += 1)
			state.constrainedFlag$sample71[index$constrainedFlag$sample71$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample45)
			logProbabilityValue$sample45();
		logProbabilityValue$sample87();
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
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityDistribution$sample53();
		logProbabilityDistribution$sample71();
		logProbabilityDistribution$sample87();
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
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.flips[cv$index1] = state.flipsMeasured[cv$index1];
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
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model HMMTestPart7(boolean[] flipsMeasured) {\n"
		     + "        int states = 5;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        int[] st = new int[samples];\n"
		     + "\n"
		     + "        st[0] = categorical(m[0]).sampleDistribution();\n"
		     + "\n"
		     + "        for(int i:[1..samples))\n"
		     + "            st[i] = categorical(m[st[i-1]]).sampleDistribution();\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[0..samples))\n"
		     + "            flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}