package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Vulcano2012basic$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Vulcano2012basic.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Vulcano2012basic$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		boolean[] guard$sample26multinomial156$global;
		boolean[][] guard$sample26put131$global;
		boolean[][] guard$sample26put154$global;
		boolean[] guard$sample26put68$global;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Allocation of guard$sample26put68$global for single threaded execution
			// 
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			guard$sample26put68$global = new boolean[Math.max(0, state.noProducts)];
			
			// Constructor for guard$sample26put131$global
			// 
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var123 = 0;
			if((0 < state.T))
				// Calculate the largest index of j that is possible and allocate an array to hold
				// the guard for each of these.
				cv$max_j$var123 = Math.max(0, state.noProducts);
			
			// Allocation of guard$sample26put131$global for single threaded execution
			// 
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			guard$sample26put131$global = new boolean[Math.max(0, state.T)][cv$max_j$var123];
			
			// Constructor for guard$sample26put154$global
			// 
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var147 = 0;
			if((0 < state.T))
				// Calculate the largest index of j that is possible and allocate an array to hold
				// the guard for each of these.
				cv$max_j$var147 = Math.max(0, state.noProducts);
			
			// Allocation of guard$sample26put154$global for single threaded execution
			// 
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			guard$sample26put154$global = new boolean[Math.max(0, state.T)][cv$max_j$var147];
			
			// Allocation of guard$sample26multinomial156$global for single threaded execution
			// 
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			guard$sample26multinomial156$global = new boolean[Math.max(0, state.T)];
		}
	}


	public Vulcano2012basic$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample26
	private final void drawValueSample26(int j$var20) {
		state.ut[j$var20] = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 26 and consumer double[] 41.
		// 
						// Substituted "j$var38" with its value "j$var20".
		state.exped[j$var20] = Math.exp(state.ut[j$var20]);
		
		// Guards to ensure that sum is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 26 and consumer double 52.
		// 
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$15 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// l$var50's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$15 = (reduceVar$sum$15 + state.exped[cv$reduction46Index]);
		
		// Write out the new sample value.
		state.sum = reduceVar$sum$15;
		
		// Guards to ensure that expedNorm is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 26 and consumer double[] 67.
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			scratch.guard$sample26put68$global[j$var63] = false;
		
		// Set the flags to false
		// 
		// Guard to check that at most one copy of the code is executed for a given random
		// variable instance.
		// 
		// Substituted "j$var63" with its value "j$var20".
		scratch.guard$sample26put68$global[j$var20] = false;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			if(!scratch.guard$sample26put68$global[j$var63]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample26put68$global[j$var63] = true;
				
								// sum's comment
				// Write out the new sample value.
				state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$15));
			}
		}
		
						// Substituted "j$var38" with its value "j$var20".
		// 
		// Substituted "j$var63" with its value "j$var20".
		if(!scratch.guard$sample26put68$global[j$var20]) {
			// The body will execute, so should not be executed again
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var63" with its value "j$var20".
			scratch.guard$sample26put68$global[j$var20] = true;
			
									// Substituted "j$var63" with its value "j$var20".
			// 
						// sum's comment
			// Write out the new sample value.
			state.expedNorm[j$var20] = (state.exped[j$var20] / (state.r * reduceVar$sum$15));
		}
		
		// Guards to ensure that weekly_ut is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 26 and consumer double[] 128.
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1)
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample26put131$global[t$var112][j$var63] = false;
		}
		for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1)
			// Set the flags to false
			// 
									// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var63" with its value "j$var20".
			scratch.guard$sample26put131$global[t$var112][j$var20] = false;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!scratch.guard$sample26put131$global[t$var112][j$var63]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample26put131$global[t$var112][j$var63] = true;
					
															// Substituted "j$var123" with its value "j$var63".
					state.weekly_ut[t$var112][j$var63] = (state.expedNorm[j$var63] * state.Avail[t$var112][j$var63]);
				}
			}
		}
		for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
									// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var63" with its value "j$var20".
			if(!scratch.guard$sample26put131$global[t$var112][j$var20]) {
				// The body will execute, so should not be executed again
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var63" with its value "j$var20".
				scratch.guard$sample26put131$global[t$var112][j$var20] = true;
				
												// Substituted "j$var123" with its value "j$var63".
				// 
												// Substituted "j$var63" with its value "j$var20".
				state.weekly_ut[t$var112][j$var20] = (state.expedNorm[j$var20] * state.Avail[t$var112][j$var20]);
			}
		}
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var147" with its value "j$var63".
				scratch.guard$sample26put154$global[t$var112][j$var63] = false;
		}
		for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
			for(int j$var147 = 0; j$var147 < state.noProducts; j$var147 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample26put154$global[t$var112][j$var147] = false;
		}
		for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
						// j$var147's comment
			// Substituted "j$var63" with its value "j$var20".
			scratch.guard$sample26put154$global[t$var112][j$var20] = false;
		for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
			for(int j$var147 = 0; j$var147 < state.noProducts; j$var147 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!scratch.guard$sample26put154$global[t$var112][j$var147]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample26put154$global[t$var112][j$var147] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$30 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
																								// l$var136's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$30 = (reduceVar$denom$30 + state.weekly_ut[t$var112][cv$reduction136Index]);
					state.weekly_rates[t$var112][j$var147] = (state.weekly_ut[t$var112][j$var147] / reduceVar$denom$30);
				}
			}
		}
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var147" with its value "j$var63".
				if(!scratch.guard$sample26put154$global[t$var112][j$var63]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var147" with its value "j$var63".
					scratch.guard$sample26put154$global[t$var112][j$var63] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$31 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
																								// l$var136's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$31 = (reduceVar$denom$31 + state.weekly_ut[t$var112][cv$reduction136Index]);
					
															// Substituted "j$var147" with its value "j$var63".
					state.weekly_rates[t$var112][j$var63] = (state.weekly_ut[t$var112][j$var63] / reduceVar$denom$31);
				}
			}
		}
		for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
			for(int j$var147 = 0; j$var147 < state.noProducts; j$var147 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!scratch.guard$sample26put154$global[t$var112][j$var147]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample26put154$global[t$var112][j$var147] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$32 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
																								// l$var136's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$32 = (reduceVar$denom$32 + state.weekly_ut[t$var112][cv$reduction136Index]);
					state.weekly_rates[t$var112][j$var147] = (state.weekly_ut[t$var112][j$var147] / reduceVar$denom$32);
				}
			}
		}
		for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
						// j$var147's comment
			// Substituted "j$var63" with its value "j$var20".
			if(!scratch.guard$sample26put154$global[t$var112][j$var20]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
								// j$var147's comment
				// Substituted "j$var63" with its value "j$var20".
				scratch.guard$sample26put154$global[t$var112][j$var20] = true;
				
				// Reduction of array weekly_ut
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$denom$33 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
																				// l$var136's comment
					// Set the right hand term to a value from the array weekly_ut
					reduceVar$denom$33 = (reduceVar$denom$33 + state.weekly_ut[t$var112][cv$reduction136Index]);
				
																// j$var147's comment
				// Substituted "j$var63" with its value "j$var20".
				state.weekly_rates[t$var112][j$var20] = (state.weekly_ut[t$var112][j$var20] / reduceVar$denom$33);
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 26 drawn from Gaussian 25. Inference was performed using Metropolis-Hastings.
	private final void inferSample26(int j$var20) {
		state.constrainedFlag$sample26[(j$var20 - 1)] = false;
		
		// The original value of the sample
		double cv$originalValue = state.ut[j$var20];
		
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
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 1.4142135623730951)) - 0.34657359027997264);
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample26multinomial156$global[t$var112] = false;
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample26multinomial156$global[t$var112]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample26multinomial156$global[t$var112] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					
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
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var112], state.weekly_rates[t$var112], state.noProducts, state.sales_sum[t$var112]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
												// Substituted "j$var123" with its value "j$var63".
				if(!scratch.guard$sample26multinomial156$global[t$var112]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample26multinomial156$global[t$var112] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					
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
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var112], state.weekly_rates[t$var112], state.noProducts, state.sales_sum[t$var112]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample26multinomial156$global[t$var112]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample26multinomial156$global[t$var112] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					
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
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var112], state.weekly_rates[t$var112], state.noProducts, state.sales_sum[t$var112]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample26multinomial156$global[t$var112]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample26multinomial156$global[t$var112] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					
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
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var112], state.weekly_rates[t$var112], state.noProducts, state.sales_sum[t$var112]) + cv$accumulatedProbabilities);
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
		if(state.constrainedFlag$sample26[(j$var20 - 1)]) {
			// Guards to ensure that ut is only updated when there is a valid path.
			state.ut[j$var20] = cv$proposedValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 26 and consumer double[] 41.
			// 
									// Substituted "j$var38" with its value "j$var20".
			state.exped[j$var20] = Math.exp(state.ut[j$var20]);
			
			// Guards to ensure that sum is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 26 and consumer double 52.
			// 
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$11 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// l$var50's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$11 = (reduceVar$sum$11 + state.exped[cv$reduction46Index]);
			
			// Write out the new sample value.
			state.sum = reduceVar$sum$11;
			
			// Guards to ensure that expedNorm is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 26 and consumer double[] 67.
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample26put68$global[j$var63] = false;
			
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var63" with its value "j$var20".
			scratch.guard$sample26put68$global[j$var20] = false;
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!scratch.guard$sample26put68$global[j$var63]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample26put68$global[j$var63] = true;
					
										// sum's comment
					// Write out the new sample value.
					state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$11));
				}
			}
			
									// Substituted "j$var38" with its value "j$var20".
			// 
			// Substituted "j$var63" with its value "j$var20".
			if(!scratch.guard$sample26put68$global[j$var20]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var63" with its value "j$var20".
				scratch.guard$sample26put68$global[j$var20] = true;
				
												// Substituted "j$var63" with its value "j$var20".
				// 
								// sum's comment
				// Write out the new sample value.
				state.expedNorm[j$var20] = (state.exped[j$var20] / (state.r * reduceVar$sum$11));
			}
			
			// Guards to ensure that weekly_ut is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 26 and consumer double[] 128.
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
				for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample26put131$global[t$var112][j$var63] = false;
			}
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1)
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var63" with its value "j$var20".
				scratch.guard$sample26put131$global[t$var112][j$var20] = false;
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
				for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!scratch.guard$sample26put131$global[t$var112][j$var63]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample26put131$global[t$var112][j$var63] = true;
						
																		// Substituted "j$var123" with its value "j$var63".
						state.weekly_ut[t$var112][j$var63] = (state.expedNorm[j$var63] * state.Avail[t$var112][j$var63]);
					}
				}
			}
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var63" with its value "j$var20".
				if(!scratch.guard$sample26put131$global[t$var112][j$var20]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var63" with its value "j$var20".
					scratch.guard$sample26put131$global[t$var112][j$var20] = true;
					
															// Substituted "j$var123" with its value "j$var63".
					// 
															// Substituted "j$var63" with its value "j$var20".
					state.weekly_ut[t$var112][j$var20] = (state.expedNorm[j$var20] * state.Avail[t$var112][j$var20]);
				}
			}
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
				for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var147" with its value "j$var63".
					scratch.guard$sample26put154$global[t$var112][j$var63] = false;
			}
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
				for(int j$var147 = 0; j$var147 < state.noProducts; j$var147 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample26put154$global[t$var112][j$var147] = false;
			}
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
								// j$var147's comment
				// Substituted "j$var63" with its value "j$var20".
				scratch.guard$sample26put154$global[t$var112][j$var20] = false;
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
				for(int j$var147 = 0; j$var147 < state.noProducts; j$var147 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!scratch.guard$sample26put154$global[t$var112][j$var147]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample26put154$global[t$var112][j$var147] = true;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$20 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// l$var136's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$20 = (reduceVar$denom$20 + state.weekly_ut[t$var112][cv$reduction136Index]);
						state.weekly_rates[t$var112][j$var147] = (state.weekly_ut[t$var112][j$var147] / reduceVar$denom$20);
					}
				}
			}
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
				for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var147" with its value "j$var63".
					if(!scratch.guard$sample26put154$global[t$var112][j$var63]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "j$var147" with its value "j$var63".
						scratch.guard$sample26put154$global[t$var112][j$var63] = true;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$21 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// l$var136's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$21 = (reduceVar$denom$21 + state.weekly_ut[t$var112][cv$reduction136Index]);
						
																		// Substituted "j$var147" with its value "j$var63".
						state.weekly_rates[t$var112][j$var63] = (state.weekly_ut[t$var112][j$var63] / reduceVar$denom$21);
					}
				}
			}
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
				for(int j$var147 = 0; j$var147 < state.noProducts; j$var147 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!scratch.guard$sample26put154$global[t$var112][j$var147]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample26put154$global[t$var112][j$var147] = true;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$22 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// l$var136's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$22 = (reduceVar$denom$22 + state.weekly_ut[t$var112][cv$reduction136Index]);
						state.weekly_rates[t$var112][j$var147] = (state.weekly_ut[t$var112][j$var147] / reduceVar$denom$22);
					}
				}
			}
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
								// j$var147's comment
				// Substituted "j$var63" with its value "j$var20".
				if(!scratch.guard$sample26put154$global[t$var112][j$var20]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
										// j$var147's comment
					// Substituted "j$var63" with its value "j$var20".
					scratch.guard$sample26put154$global[t$var112][j$var20] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$23 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
																								// l$var136's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$23 = (reduceVar$denom$23 + state.weekly_ut[t$var112][cv$reduction136Index]);
					
																				// j$var147's comment
					// Substituted "j$var63" with its value "j$var20".
					state.weekly_rates[t$var112][j$var20] = (state.weekly_ut[t$var112][j$var20] / reduceVar$denom$23);
				}
			}
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 1.4142135623730951)) - 0.34657359027997264);
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample26multinomial156$global[t$var112] = false;
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample26multinomial156$global[t$var112]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample26multinomial156$global[t$var112] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					
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
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var112], state.weekly_rates[t$var112], state.noProducts, state.sales_sum[t$var112]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
												// Substituted "j$var123" with its value "j$var63".
				if(!scratch.guard$sample26multinomial156$global[t$var112]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample26multinomial156$global[t$var112] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					
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
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var112], state.weekly_rates[t$var112], state.noProducts, state.sales_sum[t$var112]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample26multinomial156$global[t$var112]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample26multinomial156$global[t$var112] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					
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
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var112], state.weekly_rates[t$var112], state.noProducts, state.sales_sum[t$var112]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample26multinomial156$global[t$var112]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample26multinomial156$global[t$var112] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					
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
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var112], state.weekly_rates[t$var112], state.noProducts, state.sales_sum[t$var112]) + cv$accumulatedProbabilities);
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
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				// If it is not revert the changes.
				// 
				// Set the sample value
				// Guards to ensure that ut is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				state.ut[j$var20] = cv$originalValue;
				
				// Guards to ensure that exped is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 26 and consumer double[] 41.
				// 
												// Substituted "j$var38" with its value "j$var20".
				state.exped[j$var20] = Math.exp(state.ut[j$var20]);
				
				// Guards to ensure that sum is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 26 and consumer double 52.
				// 
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$14 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
																				// l$var50's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$14 = (reduceVar$sum$14 + state.exped[cv$reduction46Index]);
				
				// Write out the new sample value.
				state.sum = reduceVar$sum$14;
				
				// Guards to ensure that expedNorm is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 26 and consumer double[] 67.
				for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample26put68$global[j$var63] = false;
				
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var63" with its value "j$var20".
				scratch.guard$sample26put68$global[j$var20] = false;
				for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!scratch.guard$sample26put68$global[j$var63]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample26put68$global[j$var63] = true;
						
												// sum's comment
						// Write out the new sample value.
						state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$14));
					}
				}
				
												// Substituted "j$var38" with its value "j$var20".
				// 
				// Substituted "j$var63" with its value "j$var20".
				if(!scratch.guard$sample26put68$global[j$var20]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var63" with its value "j$var20".
					scratch.guard$sample26put68$global[j$var20] = true;
					
															// Substituted "j$var63" with its value "j$var20".
					// 
										// sum's comment
					// Write out the new sample value.
					state.expedNorm[j$var20] = (state.exped[j$var20] / (state.r * reduceVar$sum$14));
				}
				
				// Guards to ensure that weekly_ut is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 26 and consumer double[] 128.
				for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
					for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1)
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample26put131$global[t$var112][j$var63] = false;
				}
				for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var63" with its value "j$var20".
					scratch.guard$sample26put131$global[t$var112][j$var20] = false;
				for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
					for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						if(!scratch.guard$sample26put131$global[t$var112][j$var63]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample26put131$global[t$var112][j$var63] = true;
							
																					// Substituted "j$var123" with its value "j$var63".
							state.weekly_ut[t$var112][j$var63] = (state.expedNorm[j$var63] * state.Avail[t$var112][j$var63]);
						}
					}
				}
				for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var63" with its value "j$var20".
					if(!scratch.guard$sample26put131$global[t$var112][j$var20]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "j$var63" with its value "j$var20".
						scratch.guard$sample26put131$global[t$var112][j$var20] = true;
						
																		// Substituted "j$var123" with its value "j$var63".
						// 
																		// Substituted "j$var63" with its value "j$var20".
						state.weekly_ut[t$var112][j$var20] = (state.expedNorm[j$var20] * state.Avail[t$var112][j$var20]);
					}
				}
				for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
					for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1)
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "j$var147" with its value "j$var63".
						scratch.guard$sample26put154$global[t$var112][j$var63] = false;
				}
				for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
					for(int j$var147 = 0; j$var147 < state.noProducts; j$var147 += 1)
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample26put154$global[t$var112][j$var147] = false;
				}
				for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
										// j$var147's comment
					// Substituted "j$var63" with its value "j$var20".
					scratch.guard$sample26put154$global[t$var112][j$var20] = false;
				for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
					for(int j$var147 = 0; j$var147 < state.noProducts; j$var147 += 1) {
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						if(!scratch.guard$sample26put154$global[t$var112][j$var147]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample26put154$global[t$var112][j$var147] = true;
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$26 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
								// Execute the reduction function, saving the result into the return value.
								// 
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
																																// l$var136's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$26 = (reduceVar$denom$26 + state.weekly_ut[t$var112][cv$reduction136Index]);
							state.weekly_rates[t$var112][j$var147] = (state.weekly_ut[t$var112][j$var147] / reduceVar$denom$26);
						}
					}
				}
				for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
					for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "j$var147" with its value "j$var63".
						if(!scratch.guard$sample26put154$global[t$var112][j$var63]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "j$var147" with its value "j$var63".
							scratch.guard$sample26put154$global[t$var112][j$var63] = true;
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$27 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
								// Execute the reduction function, saving the result into the return value.
								// 
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
																																// l$var136's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$27 = (reduceVar$denom$27 + state.weekly_ut[t$var112][cv$reduction136Index]);
							
																					// Substituted "j$var147" with its value "j$var63".
							state.weekly_rates[t$var112][j$var63] = (state.weekly_ut[t$var112][j$var63] / reduceVar$denom$27);
						}
					}
				}
				for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
					for(int j$var147 = 0; j$var147 < state.noProducts; j$var147 += 1) {
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						if(!scratch.guard$sample26put154$global[t$var112][j$var147]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample26put154$global[t$var112][j$var147] = true;
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$28 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
								// Execute the reduction function, saving the result into the return value.
								// 
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
																																// l$var136's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$28 = (reduceVar$denom$28 + state.weekly_ut[t$var112][cv$reduction136Index]);
							state.weekly_rates[t$var112][j$var147] = (state.weekly_ut[t$var112][j$var147] / reduceVar$denom$28);
						}
					}
				}
				for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
										// j$var147's comment
					// Substituted "j$var63" with its value "j$var20".
					if(!scratch.guard$sample26put154$global[t$var112][j$var20]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
												// j$var147's comment
						// Substituted "j$var63" with its value "j$var20".
						scratch.guard$sample26put154$global[t$var112][j$var20] = true;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$29 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// l$var136's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$29 = (reduceVar$denom$29 + state.weekly_ut[t$var112][cv$reduction136Index]);
						
																								// j$var147's comment
						// Substituted "j$var63" with its value "j$var20".
						state.weekly_rates[t$var112][j$var20] = (state.weekly_ut[t$var112][j$var20] / reduceVar$denom$29);
					}
				}
			}
		}
	}

	// Calculate the probability of the samples represented by sample157 using sampled
	// values.
	private final void logProbabilityValue$sample157() {
		// Determine if we need to calculate the values for sample task 157 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample157) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int t$var112 = 0; t$var112 < state.T; t$var112 += 1) {
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var112], state.weekly_rates[t$var112], state.noProducts, state.sales_sum[t$var112]));
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
				state.logProbability$weekly_sales = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$Sales = (state.logProbability$Sales + cv$sampleAccumulator);
			
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
			state.fixedProbFlag$sample157 = state.fixedFlag$sample26;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$Sales = (state.logProbability$Sales + state.logProbability$weekly_sales);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$weekly_sales);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$weekly_sales);
		}
	}

	// Calculate the probability of the samples represented by sample26 using sampled
	// values.
	private final void logProbabilityValue$sample26() {
		// Determine if we need to calculate the values for sample task 26 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample26) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1) {
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((state.ut[j$var20] / 1.4142135623730951)) - 0.34657359027997264);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample26[(j$var20 - 1)] = cv$distributionAccumulator;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 26 and consumer double[] 41.
				// 
				// Update the variable probability
				state.logProbability$exped = (state.logProbability$exped + cv$distributionAccumulator);
				
				// Looking for a path between Sample 26 and consumer double 52.
				// 
				// Update the variable probability
				state.logProbability$sum = (state.logProbability$sum + cv$distributionAccumulator);
				
				// Update the variable probability
				state.logProbability$expedNorm = (state.logProbability$expedNorm + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$ut = (state.logProbability$ut + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample26)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample26 = state.fixedFlag$sample26;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1) {
				double cv$sampleValue = state.logProbability$sample26[(j$var20 - 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 26 and consumer double[] 41.
				// 
				// Update the variable probability
				state.logProbability$exped = (state.logProbability$exped + cv$sampleValue);
				
				// Looking for a path between Sample 26 and consumer double 52.
				// 
				// Update the variable probability
				state.logProbability$sum = (state.logProbability$sum + cv$sampleValue);
				
				// Update the variable probability
				state.logProbability$expedNorm = (state.logProbability$expedNorm + cv$sampleValue);
			}
			
			// Update the variable probability
			state.logProbability$ut = (state.logProbability$ut + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample26)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample26) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 1, state.noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							state.ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
							state.exped[j$var38] = Math.exp(state.ut[j$var38]);
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$16 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// l$var50's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$16 = (reduceVar$sum$16 + state.exped[cv$reduction46Index]);
			state.sum = reduceVar$sum$16;
			
			// Alternative name for reduceVar$sum$16 to make it effectively final.
			double reduceVar$sum$16$1 = reduceVar$sum$16;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
							// Substituted "sum" with its value "reduceVar$sum$16".
							state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$16$1));
				}
			);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.T, 1,
			(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
						int t$var112 = index$t$var112;
						int threadID$t$var112 = threadID$index$t$var112;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample26) {
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.noProducts, 1,
								(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
											state.weekly_ut[t$var112][j$var123] = (state.expedNorm[j$var123] * state.Avail[t$var112][j$var123]);
								}
							);
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$34 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
																																// l$var136's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$34 = (reduceVar$denom$34 + state.weekly_ut[t$var112][cv$reduction136Index]);
							
							// Alternative name for reduceVar$denom$34 to make it effectively final.
							double reduceVar$denom$34$2 = reduceVar$denom$34;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.noProducts, 1,
								(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
											state.weekly_rates[t$var112][j$var147] = (state.weekly_ut[t$var112][j$var147] / reduceVar$denom$34$2);
								}
							);
						}
						
						// Substituted "weekly_sales" with its value "Sales[t$var112]".
						DistributionSampling.sampleMultinomial(RNG$1, state.weekly_rates[t$var112], state.noProducts, state.sales_sum[t$var112], state.Sales[t$var112]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample26)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 1, state.noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							state.ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
						state.exped[j$var38] = Math.exp(state.ut[j$var38]);
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$20 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// l$var50's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$20 = (reduceVar$sum$20 + state.exped[cv$reduction46Index]);
		state.sum = reduceVar$sum$20;
		
		// Alternative name for reduceVar$sum$20 to make it effectively final.
		double reduceVar$sum$20$1 = reduceVar$sum$20;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
						// Substituted "sum" with its value "reduceVar$sum$20".
						state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$20$1));
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.T, 1,
			(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
						int t$var112 = index$t$var112;
						int threadID$t$var112 = threadID$index$t$var112;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
										state.weekly_ut[t$var112][j$var123] = (state.expedNorm[j$var123] * state.Avail[t$var112][j$var123]);
							}
						);
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$38 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// l$var136's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$38 = (reduceVar$denom$38 + state.weekly_ut[t$var112][cv$reduction136Index]);
						
						// Alternative name for reduceVar$denom$38 to make it effectively final.
						double reduceVar$denom$38$2 = reduceVar$denom$38;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
										state.weekly_rates[t$var112][j$var147] = (state.weekly_ut[t$var112][j$var147] / reduceVar$denom$38$2);
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
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample26)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 1, state.noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							state.ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
						state.exped[j$var38] = Math.exp(state.ut[j$var38]);
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$17 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// l$var50's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$17 = (reduceVar$sum$17 + state.exped[cv$reduction46Index]);
		state.sum = reduceVar$sum$17;
		
		// Alternative name for reduceVar$sum$17 to make it effectively final.
		double reduceVar$sum$17$1 = reduceVar$sum$17;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
						// Substituted "sum" with its value "reduceVar$sum$17".
						state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$17$1));
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.T, 1,
			(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
						int t$var112 = index$t$var112;
						int threadID$t$var112 = threadID$index$t$var112;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
										state.weekly_ut[t$var112][j$var123] = (state.expedNorm[j$var123] * state.Avail[t$var112][j$var123]);
							}
						);
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$35 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// l$var136's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$35 = (reduceVar$denom$35 + state.weekly_ut[t$var112][cv$reduction136Index]);
						
						// Alternative name for reduceVar$denom$35 to make it effectively final.
						double reduceVar$denom$35$2 = reduceVar$denom$35;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
										state.weekly_rates[t$var112][j$var147] = (state.weekly_ut[t$var112][j$var147] / reduceVar$denom$35$2);
							}
						);
						
						// Substituted "weekly_sales" with its value "Sales[t$var112]".
						DistributionSampling.sampleMultinomial(RNG$1, state.weekly_rates[t$var112], state.noProducts, state.sales_sum[t$var112], state.Sales[t$var112]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample26) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 1, state.noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							state.ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
							state.exped[j$var38] = Math.exp(state.ut[j$var38]);
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$18 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// l$var50's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$18 = (reduceVar$sum$18 + state.exped[cv$reduction46Index]);
			state.sum = reduceVar$sum$18;
			
			// Alternative name for reduceVar$sum$18 to make it effectively final.
			double reduceVar$sum$18$1 = reduceVar$sum$18;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
							// Substituted "sum" with its value "reduceVar$sum$18".
							state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$18$1));
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.T, 1,
				(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
							int t$var112 = index$t$var112;
							int threadID$t$var112 = threadID$index$t$var112;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.noProducts, 1,
								(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
											state.weekly_ut[t$var112][j$var123] = (state.expedNorm[j$var123] * state.Avail[t$var112][j$var123]);
								}
							);
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$36 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
																																// l$var136's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$36 = (reduceVar$denom$36 + state.weekly_ut[t$var112][cv$reduction136Index]);
							
							// Alternative name for reduceVar$denom$36 to make it effectively final.
							double reduceVar$denom$36$2 = reduceVar$denom$36;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.noProducts, 1,
								(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
											state.weekly_rates[t$var112][j$var147] = (state.weekly_ut[t$var112][j$var147] / reduceVar$denom$36$2);
								}
							);
						}
				}
			);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample26)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 1, state.noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							state.ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
						state.exped[j$var38] = Math.exp(state.ut[j$var38]);
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$19 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// l$var50's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$19 = (reduceVar$sum$19 + state.exped[cv$reduction46Index]);
		state.sum = reduceVar$sum$19;
		
		// Alternative name for reduceVar$sum$19 to make it effectively final.
		double reduceVar$sum$19$1 = reduceVar$sum$19;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
						// Substituted "sum" with its value "reduceVar$sum$19".
						state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$19$1));
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.T, 1,
			(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
						int t$var112 = index$t$var112;
						int threadID$t$var112 = threadID$index$t$var112;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
										state.weekly_ut[t$var112][j$var123] = (state.expedNorm[j$var123] * state.Avail[t$var112][j$var123]);
							}
						);
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$37 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// l$var136's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$37 = (reduceVar$denom$37 + state.weekly_ut[t$var112][cv$reduction136Index]);
						
						// Alternative name for reduceVar$denom$37 to make it effectively final.
						double reduceVar$denom$37$2 = reduceVar$denom$37;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
										state.weekly_rates[t$var112][j$var147] = (state.weekly_ut[t$var112][j$var147] / reduceVar$denom$37$2);
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
		if(!state.fixedFlag$sample26) {
			// Infer the samples in chronological order.
			if(state.system$gibbsForward) {
				for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1)
					inferSample26(j$var20);
			}
			// Infer the samples in reverse chronological order.
			else {
				for(int j$var20 = (state.noProducts - 1); j$var20 >= 1; j$var20 -= 1)
					inferSample26(j$var20);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1) {
			if(!state.constrainedFlag$sample26[(j$var20 - 1)])
				drawValueSample26(j$var20);
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
		state.logProbability$ut = 0.0;
		state.logProbability$exped = 0.0;
		state.logProbability$sum = 0.0;
		state.logProbability$expedNorm = 0.0;
		if(!state.fixedProbFlag$sample26) {
			for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1)
				state.logProbability$sample26[(j$var20 - 1)] = Double.NaN;
		}
		state.logProbability$Sales = 0.0;
		if(!state.fixedProbFlag$sample157)
			state.logProbability$weekly_sales = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.ut[0] = 0.0;
		for(int t$var78 = 0; t$var78 < state.T; t$var78 += 1) {
			// Reduction of array week_sales
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			int reduceVar$var88$1 = 0;
			
			// For each index in the array to be reduced
			for(int cv$reduction84Index = 0; cv$reduction84Index < state.ObsSales[t$var78].length; cv$reduction84Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// l$var86's comment
				// Set the right hand term to a value from the array week_sales
				reduceVar$var88$1 = (reduceVar$var88$1 + state.ObsSales[t$var78][cv$reduction84Index]);
			state.sales_sum[t$var78] = reduceVar$var88$1;
		}
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample26$1 = 0; index$constrainedFlag$sample26$1 < state.constrainedFlag$sample26.length; index$constrainedFlag$sample26$1 += 1)
			state.constrainedFlag$sample26[index$constrainedFlag$sample26$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample26)
			logProbabilityValue$sample26();
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
		logProbabilityValue$sample26();
		logProbabilityValue$sample157();
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
		logProbabilityValue$sample26();
		logProbabilityValue$sample157();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.Sales.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = state.ObsSales[cv$index1];
			int[] cv$target2 = state.Sales[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
						state.exped[j$var38] = Math.exp(state.ut[j$var38]);
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$21 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// l$var50's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$21 = (reduceVar$sum$21 + state.exped[cv$reduction46Index]);
		state.sum = reduceVar$sum$21;
		
		// Alternative name for reduceVar$sum$21 to make it effectively final.
		double reduceVar$sum$21$1 = reduceVar$sum$21;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
						// Substituted "sum" with its value "reduceVar$sum$21".
						state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$21$1));
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.T, 1,
			(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
						int t$var112 = index$t$var112;
						int threadID$t$var112 = threadID$index$t$var112;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
										state.weekly_ut[t$var112][j$var123] = (state.expedNorm[j$var123] * state.Avail[t$var112][j$var123]);
							}
						);
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$39 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction136Index = 0; cv$reduction136Index < state.noProducts; cv$reduction136Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// l$var136's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$39 = (reduceVar$denom$39 + state.weekly_ut[t$var112][cv$reduction136Index]);
						
						// Alternative name for reduceVar$denom$39 to make it effectively final.
						double reduceVar$denom$39$2 = reduceVar$denom$39;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
										state.weekly_rates[t$var112][j$var147] = (state.weekly_ut[t$var112][j$var147] / reduceVar$denom$39$2);
							}
						);
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "/*\n"
		     + " * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n"
		     + " * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n"
		     + " * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model Vulcano2012basic(int noProducts, int T, int[][] ObsSales, int[][] Avail, double r) {\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = new double[noProducts];\n"
		     + "    ut[0] = 0.0;  //the first one is fixed so that we can normalize the sum to be equal 1/r\n"
		     + "    for(int j : [1..noProducts)) {\n"
		     + "        ut[j] = gaussian(0, 2).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    //exponentiate right here (in the non-basic models move to the for loop)\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "        exped[j] = exp(ut[j]);\n"
		     + "    }\n"
		     + "\n"
		     + "    double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "    //now normalize\n"
		     + "    double[] expedNorm = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "        expedNorm[j] = exped[j]/(r*sum);\n"
		     + "    }\n"
		     + "\n"
		     + "    int[] sales_sum = new int[T];\n"
		     + "    for (int t : [0..T)){\n"
		     + "        int[] week_sales = ObsSales[t];\n"
		     + "        sales_sum[t] = reduce(week_sales, 0, (k, l) -> { return k + l; });\n"
		     + "    }\n"
		     + "\n"
		     + "    int[][] Sales = new int[T][noProducts];\n"
		     + "\n"
		     + "    for (int t:[0..T)){\n"
		     + "        // for each period t calculate choice probabilities and sales\n"
		     + "\n"
		     + "        double[] weekly_rates = new double[noProducts];\n"
		     + "        double[] weekly_ut = new double[noProducts];\n"
		     + "\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_ut[j] = expedNorm[j]*Avail[t][j] ;\n"
		     + "        }\n"
		     + "        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_rates[j] = weekly_ut[j]/denom ;\n"
		     + "        }\n"
		     + "\n"
		     + "        int[] weekly_sales = multinomial(weekly_rates, sales_sum[t]).sample();\n"
		     + "\n"
		     + "        // record sales for period t\n"
		     + "        Sales[t] = weekly_sales;\n"
		     + "\n"
		     + "                                }\n"
		     + "    // assert that generated sales match observed sales\n"
		     + "    Sales.observe(ObsSales);\n"
		     + "\n"
		     + "}";
	}
}