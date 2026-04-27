package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Deterministic2$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Deterministic2.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Deterministic2$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$distributionAccumulator$var53;
		double[][] cv$var29$countGlobal;
		double[] cv$var54$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var29$countGlobal
			// 
			// Allocation of cv$var29$countGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var29$countGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var29$countGlobal[cv$index] = new double[5];
			
			// Constructor for cv$distributionAccumulator$var53
			// 
			// Allocation of cv$distributionAccumulator$var53 for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 53. Initially set to the value
			// of putTask 30.
			cv$distributionAccumulator$var53 = new double[5];
			
			// Allocation of cv$var54$stateProbabilityGlobal for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 53. Initially set to the value
			// of putTask 30.
			cv$var54$stateProbabilityGlobal = new double[5];
		}
	}


	public Deterministic2$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample29
	private final void drawValueSample29(int var28, int threadID$cv$var28, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, state.v, 5, state.m[var28]);
	}

	// Pick a value from the distribution for the unconditioned variable from sample55
	private final void drawValueSample55(int i$var46) {
		state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
		
		// Guards to ensure that b is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 55 and consumer int[] 50.
		int index$i$2_1 = (i$var46 + 1);
		if((index$i$2_1 < state.n))
			state.b[index$i$2_1] = state.a[(index$i$2_1 - 1)];
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 29 drawn from Dirichlet 17. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample29(int var28, int threadID$cv$var28, Rng RNG$) {
		state.constrainedFlag$sample29[var28] = false;
		
		// A local reference to the scratch space.
		double[] cv$countLocal = scratch.cv$var29$countGlobal[threadID$cv$var28];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample55) {
			// Processing random variable 53.
			// 
			// Looking for a path between Sample 29 and consumer Categorical 53.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((1 < state.n) && (var28 == 0))) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample29[0] = true;
				
				// Increment the sample counter with the value sampled by sample task 55 of random
				// variable var53
				// 
												// Substituted "i$var46" with its value "1".
				cv$countLocal[state.a[1]] = (cv$countLocal[state.a[1]] + 1.0);
			}
			for(int i$var46 = 2; i$var46 < state.n; i$var46 += 1) {
				if((var28 == state.a[(i$var46 - 1)])) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample29[var28] = true;
					
					// Increment the sample counter with the value sampled by sample task 55 of random
					// variable var53
					cv$countLocal[state.a[i$var46]] = (cv$countLocal[state.a[i$var46]] + 1.0);
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			// Processing random variable 53.
			// 
			// Looking for a path between Sample 29 and consumer Categorical 53.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((1 < state.n) && (var28 == 0))) {
				// Merge the distribution probabilities into the count
				// 
				// Get the length of the array
				for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
					// The probability of reaching the consumer with this set of consumer arguments
					// 
															// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + state.distribution$sample55[0][cv$loopIndex]);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var28 < 5)) {
				for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
					int index$i$27 = (i$var46 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 <= index$i$27)) {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
																								// cv$probabilitySample55Value29's comment
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample55$28" with its value "var28".
						double cv$distributionProbability = state.distribution$sample55[(index$i$27 - 1)][var28];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample55[(i$var46 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		if(state.constrainedFlag$sample29[var28])
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
			// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, state.m[var28], 5);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 55 drawn from Categorical 53. Inference was performed using variable
	// marginalization.
	private final void inferSample55(int i$var46) {
		state.constrainedFlag$sample55[(i$var46 - 1)] = false;
		
		// Calculate the number of states to evaluate.
		int cv$numStates = 0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == i$var46))
			// variable marginalization
			cv$numStates = 5;
		int index$i$5 = (i$var46 - 1);
		
								// index$i$1's comment
		// Copy of index so that its values can be safely substituted
		// 
						// Substituted "index$i$5" with its value "(i$var46 - 1)".
		// 
						// Substituted "index$i$5" with its value "(i$var46 - 1)".
		// 
						// Substituted "index$i$5" with its value "(i$var46 - 1)".
		// 
						// Substituted "index$i$5" with its value "(i$var46 - 1)".
		if(((1 <= index$i$5) && !(index$i$5 == i$var46)))
			// variable marginalization
			cv$numStates = 5;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 53 creating
			// sample task 55.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == i$var46)) {
				// Record the reached probability density.
				// 
				// Initialize a counter to track the reached distributions.
				cv$reachedDistributionSourceRV = 1.0;
				
				// Constructing a random variable input for use later.
				double[] var52 = state.m[0];
				
				// Mark that the sample has observed constrained data.
				// 
				// Substituted "i$var46" with its value "1".
				state.constrainedFlag$sample55[0] = true;
				
				// Constructing a random variable input for use later.
				// 
												// Looking for a path between Sample 55 and consumer Bernoulli 73.
				// 
				// Value of the variable at this index
				double var72 = (double)(1 / cv$valuePos);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 75 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
												// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				// 
												// Value of the variable at this index
				cv$stateProbabilityValue = ((((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[0]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY) + (((0.0 <= var52[cv$valuePos]) && (var52[cv$valuePos] <= 1.0))?Math.log(var52[cv$valuePos]):Double.NEGATIVE_INFINITY));
			}
			int index$i$15 = (i$var46 - 1);
			
												// index$i$1's comment
			// Copy of index so that its values can be safely substituted
			// 
									// Substituted "index$i$15" with its value "(i$var46 - 1)".
			// 
									// Substituted "index$i$15" with its value "(i$var46 - 1)".
			// 
									// Substituted "index$i$15" with its value "(i$var46 - 1)".
			// 
									// Substituted "index$i$15" with its value "(i$var46 - 1)".
			if(((1 <= index$i$15) && !(index$i$15 == i$var46))) {
				// Enumerating the possible outputs of Categorical 53.
				for(int index$sample55$16 = 0; index$sample55$16 < 5; index$sample55$16 += 1) {
					// Update the probability of sampling this value from the distribution value.
					double cv$probabilitySample55Value17 = state.distribution$sample55[(index$i$15 - 1)][index$sample55$16];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample55Value17);
					
					// Constructing a random variable input for use later.
					double[] var52 = state.m[index$sample55$16];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample55[(i$var46 - 1)] = true;
					
					// Constructing a random variable input for use later.
					// 
															// Looking for a path between Sample 55 and consumer Bernoulli 73.
					double var72 = (double)(1 / index$sample55$16);
					
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
					// Recorded the probability of reaching sample task 75 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
															// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
															// Value of the variable at this index
					double cv$accumulatedProbabilities = (((((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[(i$var46 - 1)]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY) + Math.log(cv$probabilitySample55Value17)) + (((0.0 <= var52[cv$valuePos]) && (var52[cv$valuePos] <= 1.0))?Math.log(var52[cv$valuePos]):Double.NEGATIVE_INFINITY));
					
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
			int index$i$34_2 = (i$var46 + 1);
			if((index$i$34_2 < state.n)) {
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					scratch.cv$distributionAccumulator$var53[cv$i] = 0.0;
				
				// Declare and zero an accumulator for tracking the reached source probability space.
				double scopeVariable$reachedSourceProbability = 0.0;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == i$var46))
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					scopeVariable$reachedSourceProbability = 1.0;
				int index$i$41 = (i$var46 - 1);
				
																// index$i$1's comment
				// Copy of index so that its values can be safely substituted
				// 
																// index$i$36's comment
				// Processing sample task 55 of consumer random variable null.
				// 
				// Copy of index so that its values can be safely substituted
				// 
				// Substituted "index$i$34_4" with its value "index$i$34_2".
				// 
												// Substituted "index$i$34_2" with its value "(i$var46 + 1)".
				// 
												// Substituted "index$i$34_2" with its value "(i$var46 + 1)".
				// 
												// Substituted "index$i$34_2" with its value "(i$var46 + 1)".
				// 
												// Substituted "index$i$34_2" with its value "(i$var46 + 1)".
				// 
												// Substituted "index$i$34_2" with its value "(i$var46 + 1)".
				if((((1 <= index$i$41) && !(index$i$41 == i$var46)) && !(index$i$41 == index$i$34_2))) {
					// Enumerating the possible outputs of Categorical 53.
					for(int index$sample55$42 = 0; index$sample55$42 < 5; index$sample55$42 += 1)
						// Add the probability of this argument configuration.
						// 
												// cv$probabilitySample55Value43's comment
						// Update the probability of sampling this value from the distribution value.
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample55[(index$i$41 - 1)][index$sample55$42]);
				}
				
				// Add the current distribution to the distribution accumulator.
				// 
												// The probability of reaching the consumer with this set of consumer arguments
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 53.
				// 
				// Looking for a path between Sample 55 and consumer Categorical 53.
				// 
				// Value of the variable at this index
				DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var53, scopeVariable$reachedSourceProbability, state.m[cv$valuePos], 5);
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$i$34_4" with its value "index$i$34_2".
				double[] cv$sampleDistribution = state.distribution$sample55[(index$i$34_2 - 1)];
				
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
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var53[cv$i] / scopeVariable$reachedSourceProbability);
					
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
			scratch.cv$var54$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(state.constrainedFlag$sample55[(i$var46 - 1)]) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// Local copy of the probability array
			double[] cv$localProbability = state.distribution$sample55[(i$var46 - 1)];
			
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var54$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var54$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else {
				// Initialize the sum of the array elements
				double cv$lseSum = 0.0;
				
				// Offset values, move to normal space, and sum.
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var54$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
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
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var54$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var54$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Calculate the probability of the samples represented by sample55 using probability
	// distributions.
	private final void logProbabilityDistribution$sample55() {
		// Determine if we need to calculate the values for sample task 55 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample55) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(state.fixedFlag$sample55) {
				// Generating probabilities for sample task
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// The sample value to calculate the probability of generating
					int cv$sampleValue = state.a[i$var46];
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == i$var46)) {
						double[] var52 = state.m[0];
						
						// Store the value of the function call, so the function call is only made once.
						cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var52[cv$sampleValue])) && (var52[cv$sampleValue] <= 1.0))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY);
						
						// Add the probability of this distribution configuration to the accumulator.
						// 
						// An accumulator for the distributed probability space covered.
						cv$probabilityReached = 1.0;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$i$6_1" with its value "(i$var46 - 1)".
					if((2 <= i$var46)) {
						// Substituted "index$i$6_2" with its value "i$var46".
						int traceTempVariable$var51$6_3 = state.a[(i$var46 - 1)];
						
																		// Substituted "index$i$6_2" with its value "i$var46".
						if(((0 <= traceTempVariable$var51$6_3) && (traceTempVariable$var51$6_3 < 5))) {
							double[] var52 = state.m[traceTempVariable$var51$6_3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var52[cv$sampleValue])) && (var52[cv$sampleValue] <= 1.0))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY);
							
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
					
					// Store the sample task probability
					state.logProbability$sample55[(i$var46 - 1)] = cv$distributionAccumulator;
					
															// Guard to ensure that b is only updated once for this probability.
					if((i$var46 < (state.n - 1)))
						// Update the variable probability
						state.logProbability$b = (state.logProbability$b + cv$distributionAccumulator);
				}
				
				// Update the variable probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$a = (state.logProbability$a + cv$sampleAccumulator);
				
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
				// Substituted "fixedFlag$sample55" with its value "true".
				state.fixedProbFlag$sample55 = state.fixedFlag$sample29;
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				double cv$sampleValue = state.logProbability$sample55[(i$var46 - 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((state.fixedFlag$sample55 && (i$var46 < (state.n - 1))))
					// Update the variable probability
					state.logProbability$b = (state.logProbability$b + cv$sampleValue);
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(state.fixedFlag$sample55)
				// Update the variable probability
				state.logProbability$a = (state.logProbability$a + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample75 using probability
	// distributions.
	private final void logProbabilityDistribution$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample75) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.n; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 75 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = state.flips[j];
				
				// Enumerating the possible arguments for Bernoulli 73.
				if(state.fixedFlag$sample55) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((j < (state.n - 1))) {
						double var72 = (double)(1 / state.a[(j + 1)]);
						
						// Store the value of the function call, so the function call is only made once.
						cv$distributionAccumulator = (((0.0 <= var72) && (var72 <= 1.0))?Math.log((cv$sampleValue?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY);
						
						// Add the probability of this distribution configuration to the accumulator.
						// 
						// An accumulator for the distributed probability space covered.
						cv$probabilityReached = 1.0;
					}
				} else {
					int i$var46 = (j + 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((i$var46 < state.n)) {
						// Enumerating the possible outputs of Categorical 53.
						for(int index$sample55$5 = 0; index$sample55$5 < 5; index$sample55$5 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample55Value6 = state.distribution$sample55[(i$var46 - 1)][index$sample55$5];
							double var72 = (double)(1 / index$sample55$5);
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample55Value6) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((cv$sampleValue?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample55Value6);
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
				state.logProbability$var74 = cv$sampleAccumulator;
			
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
			state.fixedProbFlag$sample75 = state.fixedFlag$sample55;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$flips = (state.logProbability$flips + state.logProbability$var74);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var74);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var74);
		}
	}

	// Calculate the probability of the samples represented by sample29 using sampled
	// values.
	private final void logProbabilityValue$sample29() {
		// Determine if we need to calculate the values for sample task 29 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample29) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var28 = 0; var28 < 5; var28 += 1) {
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.m[var28], state.v, 5));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				state.logProbability$var29 = cv$sampleAccumulator;
			
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
			if(state.fixedFlag$sample29)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample29 = state.fixedFlag$sample29;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$m = (state.logProbability$m + state.logProbability$var29);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var29);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample29)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var29);
		}
	}

	// Calculate the probability of the samples represented by sample55 using sampled
	// values.
	private final void logProbabilityValue$sample55() {
		// Determine if we need to calculate the values for sample task 55 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample55) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.a[i$var46];
				double[] var52 = state.m[state.b[i$var46]];
				
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
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var52[cv$sampleValue])) && (var52[cv$sampleValue] <= 1.0))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample55[(i$var46 - 1)] = cv$distributionAccumulator;
				
												// Guard to ensure that b is only updated once for this probability.
				if((i$var46 < (state.n - 1)))
					// Update the variable probability
					state.logProbability$b = (state.logProbability$b + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$a = (state.logProbability$a + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample55)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample55 = (state.fixedFlag$sample55 && state.fixedFlag$sample29);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				double cv$sampleValue = state.logProbability$sample55[(i$var46 - 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
												// Guard to ensure that b is only updated once for this probability.
				if((i$var46 < (state.n - 1)))
					// Update the variable probability
					state.logProbability$b = (state.logProbability$b + cv$sampleValue);
			}
			
			// Update the variable probability
			state.logProbability$a = (state.logProbability$a + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample75 using sampled
	// values.
	private final void logProbabilityValue$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample75) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.n; j += 1) {
				double var72 = (double)(1 / state.a[(j + 1)]);
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$var74 = cv$sampleAccumulator;
			
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
			state.fixedProbFlag$sample75 = state.fixedFlag$sample55;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$flips = (state.logProbability$flips + state.logProbability$var74);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var74);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var74);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				state.b[i$var46] = state.a[(i$var46 - 1)];
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
			}
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, (1 / state.a[(j + 1)]));
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample55 = state.distribution$sample55[(i$var46 - 1)];
				for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
					// Zero the probability of each value
					cv$distribution$sample55[index$var53] = 0.0;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == i$var46)) {
					double[] var52 = state.m[0];
					for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
						// Save the probability of each value
						cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] + (((0.0 <= var52[index$var53]) && (var52[index$var53] <= 1.0))?var52[index$var53]:0.0));
				}
				int index$i$4 = (i$var46 - 1);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 <= index$i$4)) {
					// Enumerating the possible outputs of Categorical 53.
					for(int index$sample55$5 = 0; index$sample55$5 < 5; index$sample55$5 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample55Value6 = state.distribution$sample55[(index$i$4 - 1)][index$sample55$5];
						double[] var52 = state.m[index$sample55$5];
						for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
							// Save the probability of each value
							cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] + (cv$probabilitySample55Value6 * (((0.0 <= var52[index$var53]) && (var52[index$var53] <= 1.0))?var52[index$var53]:0.0)));
					}
				}
				
				// Sum the values in the array
				double cv$var53$sum = 0.0;
				for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
					// sum the probability of each value
					cv$var53$sum = (cv$var53$sum + cv$distribution$sample55[index$var53]);
				for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
					// Normalise the probability of each value
					cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] / cv$var53$sum);
			}
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			state.b[i$var46] = state.a[(i$var46 - 1)];
			if(!state.fixedFlag$sample55)
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, (1 / state.a[(j + 1)]));
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				state.b[i$var46] = state.a[(i$var46 - 1)];
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			state.b[i$var46] = state.a[(i$var46 - 1)];
			if(!state.fixedFlag$sample55)
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample29)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
								inferSample29(var28, threadID$var28, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample55) {
				for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1)
					inferSample55(i$var46);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample55) {
				for(int i$var46 = (state.n - 1); i$var46 >= 1; i$var46 -= 1)
					inferSample55(i$var46);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample29)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
								inferSample29(var28, threadID$var28, RNG$1);
					}
				);

		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1) {
						if(!state.constrainedFlag$sample29[var28])
							drawValueSample29(var28, threadID$var28, RNG$1);
					}
			}
		);
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			if(!state.constrainedFlag$sample55[(i$var46 - 1)])
				drawValueSample55(i$var46);
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
		if(!state.fixedProbFlag$sample29)
			state.logProbability$var29 = Double.NaN;
		state.logProbability$a = 0.0;
		state.logProbability$b = 0.0;
		if(!state.fixedProbFlag$sample55) {
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1)
				state.logProbability$sample55[(i$var46 - 1)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample75)
			state.logProbability$var74 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		for(int i$var14 = 0; i$var14 < 5; i$var14 += 1)
			state.v[i$var14] = 0.1;
		state.a[0] = 0;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample29$1 = 0; index$constrainedFlag$sample29$1 < state.constrainedFlag$sample29.length; index$constrainedFlag$sample29$1 += 1)
			state.constrainedFlag$sample29[index$constrainedFlag$sample29$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample55$1 = 0; index$constrainedFlag$sample55$1 < state.constrainedFlag$sample55.length; index$constrainedFlag$sample55$1 += 1)
			state.constrainedFlag$sample55[index$constrainedFlag$sample55$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample29)
			logProbabilityValue$sample29();
		logProbabilityValue$sample75();
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
		logProbabilityValue$sample29();
		logProbabilityDistribution$sample55();
		logProbabilityDistribution$sample75();
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
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
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
	public final void setIntermediates() {
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1)
			state.b[i$var46] = state.a[(i$var46 - 1)];
	}

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
		     + "/**\n"
		     + " * A model for the fairness work.\n"
		     + " */\n"
		     + "public model Deterministic2(int n, boolean[] flipsMeasured) {\n"
		     + "    int states = 5;\n"
		     + "\n"
		     + "    double[] v = new double[states];\n"
		     + "    for(int i:[0..states))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(states);\n"
		     + "\n"
		     + "    int[] a = new int[n];\n"
		     + "    int[] b = new int[n];\n"
		     + "    a[0] = 0;\n"
		     + "    for(int i=1; i<n; i++) {\n"
		     + "        b[i] = a[i-1];\n"
		     + "        a[i] = categorical(m[b[i]]).sampleDistribution();\n"
		     + "    }\n"
		     + "    \n"
		     + "    boolean[] flips = new boolean[n];\n"
		     + "            \n"
		     + "    for(int j:[0..n))\n"
		     + "        flips[j] = bernoulli(1/a[j+1]).sample();\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}";
	}
}