package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DiscreteChoiceRandCoeff$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DiscreteChoiceRandCoeff.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DiscreteChoiceRandCoeff$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		boolean[] guard$sample21categorical102$global;
		boolean[][] guard$sample21put101$global;
		boolean[] guard$sample47categorical102$global;
		boolean[][] guard$sample47put101$global;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for guard$sample21put101$global
			{
				// Calculate the largest index of j that is possible and allocate an array to hold
				// the guard for each of these.
				int cv$max_j$var97 = 0;
				if((0 < state.noObs))
					// Calculate the largest index of j that is possible and allocate an array to hold
					// the guard for each of these.
					cv$max_j$var97 = Math.max(0, state.noProducts);
				
				// Allocation of guard$sample21put101$global for single threaded execution
				// 
				// Calculate the largest index of i that is possible and allocate an array to hold
				// the guard for each of these.
				guard$sample21put101$global = new boolean[Math.max(0, state.noObs)][cv$max_j$var97];
			}
			
			// Constructor for guard$sample21categorical102$global
			// 
			// Allocation of guard$sample21categorical102$global for single threaded execution
			// 
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			guard$sample21categorical102$global = new boolean[Math.max(0, state.noObs)];
			
			// Constructor for guard$sample47put101$global
			// 
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var97 = 0;
			if((0 < state.noObs))
				// Calculate the largest index of j that is possible and allocate an array to hold
				// the guard for each of these.
				cv$max_j$var97 = Math.max(0, state.noProducts);
			
			// Allocation of guard$sample47put101$global for single threaded execution
			// 
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			guard$sample47put101$global = new boolean[Math.max(0, state.noObs)][cv$max_j$var97];
			
			// Allocation of guard$sample47categorical102$global for single threaded execution
			// 
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			guard$sample47categorical102$global = new boolean[Math.max(0, state.noObs)];
		}
	}


	public DiscreteChoiceRandCoeff$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample21
	private final void drawValueSample21(int var20) {
		state.ut[var20] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 21 and consumer double[] 77.
		for(int i = 0; i < state.noObs; i += 1)
									// Substituted "j$var69" with its value "var20".
			state.exped[i][var20] = Math.exp((state.ut[var20] - (state.beta[i] * state.Prices[i][var20])));
		for(int i = 0; i < state.noObs; i += 1) {
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample21put101$global[i][j$var97] = false;
		}
		for(int i = 0; i < state.noObs; i += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var97" with its value "var20".
			scratch.guard$sample21put101$global[i][var20] = false;
		for(int i = 0; i < state.noObs; i += 1) {
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!scratch.guard$sample21put101$global[i][j$var97]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample21put101$global[i][j$var97] = true;
					
					// Reduction of array exped
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$sum$10 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
																								// l's comment
						// Set the right hand term to a value from the array exped
						reduceVar$sum$10 = (reduceVar$sum$10 + state.exped[i][cv$reduction82Index]);
					state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$10);
				}
			}
		}
		for(int i = 0; i < state.noObs; i += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var97" with its value "var20".
			if(!scratch.guard$sample21put101$global[i][var20]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var97" with its value "var20".
				scratch.guard$sample21put101$global[i][var20] = true;
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$11 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
																				// l's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$11 = (reduceVar$sum$11 + state.exped[i][cv$reduction82Index]);
				
												// Substituted "j$var97" with its value "var20".
				state.prob[i][var20] = (state.exped[i][var20] / reduceVar$sum$11);
			}
		}
	}

	// Pick a value from the distribution for the unconditioned variable from sample28
	private final void drawValueSample28() {
		state.b = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
	}

	// Pick a value from the distribution for the unconditioned variable from sample34
	private final void drawValueSample34() {
		state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample47
	private final void drawValueSample47(int var46) {
		state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 47 and consumer double[] 77.
		for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
									// Substituted "i" with its value "var46".
			state.exped[var46][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[var46] * state.Prices[var46][j$var69])));
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < state.noProducts)) {
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample47put101$global[var46][j$var97] = false;
		}
		for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
			// Set the flags to false
			// 
									// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			scratch.guard$sample47put101$global[var46][j$var69] = false;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < state.noProducts)) {
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!scratch.guard$sample47put101$global[var46][j$var97]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample47put101$global[var46][j$var97] = true;
					
					// Reduction of array exped
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$sum$12 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
												// k's comment
						// Set the left hand term of the reduction function to the return variable value.
						// 
												// l's comment
						// Set the right hand term to a value from the array exped
						// 
						// Substituted "i" with its value "var46".
						reduceVar$sum$12 = (reduceVar$sum$12 + state.exped[var46][cv$reduction82Index]);
					
															// Substituted "i" with its value "var46".
					state.prob[var46][j$var97] = (state.exped[var46][j$var97] / reduceVar$sum$12);
				}
			}
		}
		for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
			if(!scratch.guard$sample47put101$global[var46][j$var69]) {
				// The body will execute, so should not be executed again
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample47put101$global[var46][j$var69] = true;
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$13 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
										// k's comment
					// Set the left hand term of the reduction function to the return variable value.
					// 
										// l's comment
					// Set the right hand term to a value from the array exped
					// 
					// Substituted "i" with its value "var46".
					reduceVar$sum$13 = (reduceVar$sum$13 + state.exped[var46][cv$reduction82Index]);
				
												// Substituted "i" with its value "var46".
				state.prob[var46][j$var69] = (state.exped[var46][j$var69] / reduceVar$sum$13);
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 21 drawn from Gaussian 9. Inference was performed using Metropolis-Hastings.
	private final void inferSample21(int var20) {
		state.constrainedFlag$sample21[var20] = false;
		
		// The original value of the sample
		double cv$originalValue = state.ut[var20];
		
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
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int i = 0; i < state.noObs; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample21categorical102$global[i] = false;
			for(int i = 0; i < state.noObs; i += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample21categorical102$global[i]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample21categorical102$global[i] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample21[var20] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 103 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0.0 <= state.prob[i][state.choices[i]])) && (state.prob[i][state.choices[i]] <= 1.0))?Math.log(state.prob[i][state.choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int i = 0; i < state.noObs; i += 1) {
												// Substituted "j$var69" with its value "var20".
				if(!scratch.guard$sample21categorical102$global[i]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample21categorical102$global[i] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample21[var20] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 103 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0.0 <= state.prob[i][state.choices[i]])) && (state.prob[i][state.choices[i]] <= 1.0))?Math.log(state.prob[i][state.choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
		if(state.constrainedFlag$sample21[var20]) {
			// Guards to ensure that ut is only updated when there is a valid path.
			state.ut[var20] = cv$proposedValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 21 and consumer double[] 77.
			for(int i = 0; i < state.noObs; i += 1)
												// Substituted "j$var69" with its value "var20".
				state.exped[i][var20] = Math.exp((state.ut[var20] - (state.beta[i] * state.Prices[i][var20])));
			for(int i = 0; i < state.noObs; i += 1) {
				for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample21put101$global[i][j$var97] = false;
			}
			for(int i = 0; i < state.noObs; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var97" with its value "var20".
				scratch.guard$sample21put101$global[i][var20] = false;
			for(int i = 0; i < state.noObs; i += 1) {
				for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!scratch.guard$sample21put101$global[i][j$var97]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample21put101$global[i][j$var97] = true;
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$0 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// l's comment
							// Set the right hand term to a value from the array exped
							reduceVar$sum$0 = (reduceVar$sum$0 + state.exped[i][cv$reduction82Index]);
						state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$0);
					}
				}
			}
			for(int i = 0; i < state.noObs; i += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var97" with its value "var20".
				if(!scratch.guard$sample21put101$global[i][var20]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var97" with its value "var20".
					scratch.guard$sample21put101$global[i][var20] = true;
					
					// Reduction of array exped
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$sum$1 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
																								// l's comment
						// Set the right hand term to a value from the array exped
						reduceVar$sum$1 = (reduceVar$sum$1 + state.exped[i][cv$reduction82Index]);
					
															// Substituted "j$var97" with its value "var20".
					state.prob[i][var20] = (state.exped[i][var20] / reduceVar$sum$1);
				}
			}
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
			for(int i = 0; i < state.noObs; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample21categorical102$global[i] = false;
			for(int i = 0; i < state.noObs; i += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample21categorical102$global[i]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample21categorical102$global[i] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample21[var20] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 103 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0.0 <= state.prob[i][state.choices[i]])) && (state.prob[i][state.choices[i]] <= 1.0))?Math.log(state.prob[i][state.choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int i = 0; i < state.noObs; i += 1) {
												// Substituted "j$var69" with its value "var20".
				if(!scratch.guard$sample21categorical102$global[i]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample21categorical102$global[i] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample21[var20] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 103 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0.0 <= state.prob[i][state.choices[i]])) && (state.prob[i][state.choices[i]] <= 1.0))?Math.log(state.prob[i][state.choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
				state.ut[var20] = cv$originalValue;
				
				// Guards to ensure that exped is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 21 and consumer double[] 77.
				for(int i = 0; i < state.noObs; i += 1)
															// Substituted "j$var69" with its value "var20".
					state.exped[i][var20] = Math.exp((state.ut[var20] - (state.beta[i] * state.Prices[i][var20])));
				for(int i = 0; i < state.noObs; i += 1) {
					for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample21put101$global[i][j$var97] = false;
				}
				for(int i = 0; i < state.noObs; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var97" with its value "var20".
					scratch.guard$sample21put101$global[i][var20] = false;
				for(int i = 0; i < state.noObs; i += 1) {
					for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						if(!scratch.guard$sample21put101$global[i][j$var97]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample21put101$global[i][j$var97] = true;
							
							// Reduction of array exped
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$sum$3 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
								// Execute the reduction function, saving the result into the return value.
								// 
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
																																// l's comment
								// Set the right hand term to a value from the array exped
								reduceVar$sum$3 = (reduceVar$sum$3 + state.exped[i][cv$reduction82Index]);
							state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$3);
						}
					}
				}
				for(int i = 0; i < state.noObs; i += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var97" with its value "var20".
					if(!scratch.guard$sample21put101$global[i][var20]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "j$var97" with its value "var20".
						scratch.guard$sample21put101$global[i][var20] = true;
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$4 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// l's comment
							// Set the right hand term to a value from the array exped
							reduceVar$sum$4 = (reduceVar$sum$4 + state.exped[i][cv$reduction82Index]);
						
																		// Substituted "j$var97" with its value "var20".
						state.prob[i][var20] = (state.exped[i][var20] / reduceVar$sum$4);
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from Gaussian 27. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void inferSample28() {
		state.constrainedFlag$sample28 = false;
		
		// State to record the weighting of each sample that is consumed. This is the:
		// sum of the sample denominator*(the sample value - the sample nominator).
		double cv$sum = 0.0;
		
		// State for storing the sum of the squares of the sample denominators.
		double cv$denominatorSquareSum = 0.0;
		
		// Flag to record if we have a value for Sigma.
		boolean cv$sigmaNotFound = true;
		
		// State for the value of sigma once we find it.
		double cv$sigmaValue = 1.0;
		
		// Processing random variable 35.
		// 
		// Processing sample task 47 of consumer random variable null.
		for(int var46 = 0; var46 < state.noObs; var46 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.fixedFlag$sample47 || state.constrainedFlag$sample47[var46])) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample28 = true;
				
				// Record the value of a sample generated by a consuming sample 47 of random variable
				// var35.
				// 
				// Add the denominator squared to the sample denominator
				// 
																// cv$denominator's comment
				// State for tracking the changes that happen to the sampled value between it being
				// consumed and it being produced.
				cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
				
				// Add the weighting of the sample to the sum.
				// 
												// Substituted "cv$numerator" with its value "0.0".
				cv$sum = (cv$sum + state.beta[var46]);
				
				// If we have not got the value of sigma yet record it and set a flag so it is not
				// recorded again.
				if(cv$sigmaNotFound) {
					cv$sigmaValue = state.sigma;
					cv$sigmaNotFound = false;
				}
			}
		}
		if(state.constrainedFlag$sample28)
			// Write out the new value of the sample.
			state.b = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 34 drawn from InverseGamma 33. Inference was performed using a Inverse
	// Gamma to Gaussian conjugate prior.
	private final void inferSample34() {
		state.constrainedFlag$sample34 = false;
		
		// Variable to track the sum of the difference between the samples and the random
		// variables mean squared.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		
		// Processing random variable 35.
		// 
		// Processing sample task 47 of consumer random variable null.
		for(int var46 = 0; var46 < state.noObs; var46 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.fixedFlag$sample47 || state.constrainedFlag$sample47[var46])) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample34 = true;
				
				// Consume sample task 47 from random variable var35.
				// 
				// The difference between the mean parameter and the value sampled from the Gaussian.
				// 
				// The mean parameter for Gaussian var35.
				double cv$var35$diff = (state.b - state.beta[var46]);
				
				// Include this sample by adding the square of the difference to the sum.
				cv$sum = (cv$sum + (cv$var35$diff * cv$var35$diff));
				
				// Increment the number of samples in the calculation.
				cv$count = (cv$count + 1);
			}
		}
		if(state.constrainedFlag$sample34)
			// Write out the new value of the sample.
			state.sigma = Conjugates.sampleConjugateInverseGammaGaussian(state.RNG$, 2.0, 2.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 47 drawn from Gaussian 35. Inference was performed using Metropolis-Hastings.
	private final void inferSample47(int var46) {
		state.constrainedFlag$sample47[var46] = false;
		
		// The original value of the sample
		double cv$originalValue = state.beta[var46];
		
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
			double cv$accumulatedProbabilities = ((0.0 < state.sigma)?(DistributionSampling.logProbabilityGaussian(((cv$originalValue - state.b) / Math.sqrt(state.sigma))) - (Math.log(state.sigma) * 0.5)):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.noProducts)) {
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample47categorical102$global[var46] = false;
				if(!scratch.guard$sample47categorical102$global[var46]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample47categorical102$global[var46] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample47[var46] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 103 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i" with its value "var46".
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[var46]) && (state.choices[var46] < state.noProducts)) && (0.0 <= state.prob[var46][state.choices[var46]])) && (state.prob[var46][state.choices[var46]] <= 1.0))?Math.log(state.prob[var46][state.choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				if(!scratch.guard$sample47categorical102$global[var46]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample47categorical102$global[var46] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample47[var46] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 103 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i" with its value "var46".
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[var46]) && (state.choices[var46] < state.noProducts)) && (0.0 <= state.prob[var46][state.choices[var46]])) && (state.prob[var46][state.choices[var46]] <= 1.0))?Math.log(state.prob[var46][state.choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
		if(state.constrainedFlag$sample47[var46]) {
			// Guards to ensure that beta is only updated when there is a valid path.
			state.beta[var46] = cv$proposedValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 47 and consumer double[] 77.
			for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
												// Substituted "i" with its value "var46".
				state.exped[var46][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[var46] * state.Prices[var46][j$var69])));
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.noProducts)) {
				for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample47put101$global[var46][j$var97] = false;
			}
			for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample47put101$global[var46][j$var69] = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.noProducts)) {
				for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!scratch.guard$sample47put101$global[var46][j$var97]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample47put101$global[var46][j$var97] = true;
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$5 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
														// k's comment
							// Set the left hand term of the reduction function to the return variable value.
							// 
														// l's comment
							// Set the right hand term to a value from the array exped
							// 
							// Substituted "i" with its value "var46".
							reduceVar$sum$5 = (reduceVar$sum$5 + state.exped[var46][cv$reduction82Index]);
						
																		// Substituted "i" with its value "var46".
						state.prob[var46][j$var97] = (state.exped[var46][j$var97] / reduceVar$sum$5);
					}
				}
			}
			for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
				if(!scratch.guard$sample47put101$global[var46][j$var69]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample47put101$global[var46][j$var69] = true;
					
					// Reduction of array exped
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$sum$6 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
												// k's comment
						// Set the left hand term of the reduction function to the return variable value.
						// 
												// l's comment
						// Set the right hand term to a value from the array exped
						// 
						// Substituted "i" with its value "var46".
						reduceVar$sum$6 = (reduceVar$sum$6 + state.exped[var46][cv$reduction82Index]);
					
															// Substituted "i" with its value "var46".
					state.prob[var46][j$var69] = (state.exped[var46][j$var69] / reduceVar$sum$6);
				}
			}
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = ((0.0 < state.sigma)?(DistributionSampling.logProbabilityGaussian(((cv$proposedValue - state.b) / Math.sqrt(state.sigma))) - (Math.log(state.sigma) * 0.5)):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.noProducts)) {
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample47categorical102$global[var46] = false;
				if(!scratch.guard$sample47categorical102$global[var46]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample47categorical102$global[var46] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample47[var46] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 103 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i" with its value "var46".
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[var46]) && (state.choices[var46] < state.noProducts)) && (0.0 <= state.prob[var46][state.choices[var46]])) && (state.prob[var46][state.choices[var46]] <= 1.0))?Math.log(state.prob[var46][state.choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				if(!scratch.guard$sample47categorical102$global[var46]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample47categorical102$global[var46] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample47[var46] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 103 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i" with its value "var46".
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[var46]) && (state.choices[var46] < state.noProducts)) && (0.0 <= state.prob[var46][state.choices[var46]])) && (state.prob[var46][state.choices[var46]] <= 1.0))?Math.log(state.prob[var46][state.choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
				// Guards to ensure that beta is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				state.beta[var46] = cv$originalValue;
				
				// Guards to ensure that exped is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 47 and consumer double[] 77.
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
															// Substituted "i" with its value "var46".
					state.exped[var46][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[var46] * state.Prices[var46][j$var69])));
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < state.noProducts)) {
					for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample47put101$global[var46][j$var97] = false;
				}
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample47put101$global[var46][j$var69] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < state.noProducts)) {
					for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						if(!scratch.guard$sample47put101$global[var46][j$var97]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample47put101$global[var46][j$var97] = true;
							
							// Reduction of array exped
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$sum$8 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
								// Execute the reduction function, saving the result into the return value.
								// 
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
																// k's comment
								// Set the left hand term of the reduction function to the return variable value.
								// 
																// l's comment
								// Set the right hand term to a value from the array exped
								// 
								// Substituted "i" with its value "var46".
								reduceVar$sum$8 = (reduceVar$sum$8 + state.exped[var46][cv$reduction82Index]);
							
																					// Substituted "i" with its value "var46".
							state.prob[var46][j$var97] = (state.exped[var46][j$var97] / reduceVar$sum$8);
						}
					}
				}
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
					if(!scratch.guard$sample47put101$global[var46][j$var69]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample47put101$global[var46][j$var69] = true;
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$9 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
														// k's comment
							// Set the left hand term of the reduction function to the return variable value.
							// 
														// l's comment
							// Set the right hand term to a value from the array exped
							// 
							// Substituted "i" with its value "var46".
							reduceVar$sum$9 = (reduceVar$sum$9 + state.exped[var46][cv$reduction82Index]);
						
																		// Substituted "i" with its value "var46".
						state.prob[var46][j$var69] = (state.exped[var46][j$var69] / reduceVar$sum$9);
					}
				}
			}
		}
	}

	// Calculate the probability of the samples represented by sample103 using sampled
	// values.
	private final void logProbabilityValue$sample103() {
		// Determine if we need to calculate the values for sample task 103 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample103) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.noObs; i += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.choices[i];
				
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
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[i][cv$sampleValue])) && (state.prob[i][cv$sampleValue] <= 1.0))?Math.log(state.prob[i][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample103[i] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$choices = (state.logProbability$choices + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample103 = (state.fixedFlag$sample21 && state.fixedFlag$sample47);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.noObs; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample103[i]);
			
			// Update the variable probability
			state.logProbability$choices = (state.logProbability$choices + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample21 using sampled
	// values.
	private final void logProbabilityValue$sample21() {
		// Determine if we need to calculate the values for sample task 21 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample21) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((state.ut[var20] / 3.1622776601683795)) - 1.151292546497023);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample21[var20] = cv$distributionAccumulator;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < state.noObs))
					// Update the variable probability
					state.logProbability$prob = (state.logProbability$prob + cv$distributionAccumulator);
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
			if(state.fixedFlag$sample21)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample21 = state.fixedFlag$sample21;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
				double cv$sampleValue = state.logProbability$sample21[var20];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < state.noObs))
					// Update the variable probability
					state.logProbability$prob = (state.logProbability$prob + cv$sampleValue);
			}
			
			// Update the variable probability
			state.logProbability$ut = (state.logProbability$ut + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample28 using sampled
	// values.
	private final void logProbabilityValue$sample28() {
		// Determine if we need to calculate the values for sample task 28 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample28) {
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
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((state.b / 3.1622776601683795)) - 1.151292546497023);
			
			// Store the sample task probability
			state.logProbability$b = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample28)
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
			state.fixedProbFlag$sample28 = state.fixedFlag$sample28;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$b);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample28)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$b);
		}
	}

	// Calculate the probability of the samples represented by sample34 using sampled
	// values.
	private final void logProbabilityValue$sample34() {
		// Determine if we need to calculate the values for sample task 34 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample34) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityInverseGamma(state.sigma, 2.0, 2.0);
			
			// Store the sample task probability
			state.logProbability$sigma = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample34)
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
			state.fixedProbFlag$sample34 = state.fixedFlag$sample34;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$sigma);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample34)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$sigma);
		}
	}

	// Calculate the probability of the samples represented by sample47 using sampled
	// values.
	private final void logProbabilityValue$sample47() {
		// Determine if we need to calculate the values for sample task 47 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample47) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < state.noObs; var46 += 1) {
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
				double cv$distributionAccumulator = ((0.0 < state.sigma)?(DistributionSampling.logProbabilityGaussian(((state.beta[var46] - state.b) / Math.sqrt(state.sigma))) - (Math.log(state.sigma) * 0.5)):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample47[var46] = cv$distributionAccumulator;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < state.noProducts))
					// Update the variable probability
					state.logProbability$prob = (state.logProbability$prob + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$beta = (state.logProbability$beta + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample47)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample47 = ((state.fixedFlag$sample47 && state.fixedFlag$sample28) && state.fixedFlag$sample34);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int var46 = 0; var46 < state.noObs; var46 += 1) {
				double cv$sampleValue = state.logProbability$sample47[var46];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < state.noProducts))
					// Update the variable probability
					state.logProbability$prob = (state.logProbability$prob + cv$sampleValue);
			}
			
			// Update the variable probability
			state.logProbability$beta = (state.logProbability$beta + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample21) {
			for(int var20 = 0; var20 < state.noProducts; var20 += 1)
				state.ut[var20] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		if(!state.fixedFlag$sample28)
			state.b = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample47) {
			for(int var46 = 0; var46 < state.noObs; var46 += 1)
				state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		}
		for(int i = 0; i < state.noObs; i += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!state.fixedFlag$sample21 || !state.fixedFlag$sample47)) {
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
					state.exped[i][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$14 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
																				// l's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$14 = (reduceVar$sum$14 + state.exped[i][cv$reduction82Index]);
				for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
					state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$14);
			}
			state.choices[i] = DistributionSampling.sampleCategorical(state.RNG$, state.prob[i], state.noProducts);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample21) {
			for(int var20 = 0; var20 < state.noProducts; var20 += 1)
				state.ut[var20] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		if(!state.fixedFlag$sample28)
			state.b = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample47) {
			for(int var46 = 0; var46 < state.noObs; var46 += 1)
				state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		}
		for(int i = 0; i < state.noObs; i += 1) {
			for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
				state.exped[i][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$18 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$18 = (reduceVar$sum$18 + state.exped[i][cv$reduction82Index]);
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
				state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$18);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample21) {
			for(int var20 = 0; var20 < state.noProducts; var20 += 1)
				state.ut[var20] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		if(!state.fixedFlag$sample28)
			state.b = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample47) {
			for(int var46 = 0; var46 < state.noObs; var46 += 1)
				state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		}
		for(int i = 0; i < state.noObs; i += 1) {
			for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
				state.exped[i][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$15 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$15 = (reduceVar$sum$15 + state.exped[i][cv$reduction82Index]);
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
				state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$15);
			state.choices[i] = DistributionSampling.sampleCategorical(state.RNG$, state.prob[i], state.noProducts);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample21) {
			for(int var20 = 0; var20 < state.noProducts; var20 += 1)
				state.ut[var20] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		if(!state.fixedFlag$sample28)
			state.b = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample47) {
			for(int var46 = 0; var46 < state.noObs; var46 += 1)
				state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((!state.fixedFlag$sample21 || !state.fixedFlag$sample47)) {
			for(int i = 0; i < state.noObs; i += 1) {
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
					state.exped[i][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$16 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
																				// l's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$16 = (reduceVar$sum$16 + state.exped[i][cv$reduction82Index]);
				for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
					state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$16);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample21) {
			for(int var20 = 0; var20 < state.noProducts; var20 += 1)
				state.ut[var20] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		if(!state.fixedFlag$sample28)
			state.b = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample47) {
			for(int var46 = 0; var46 < state.noObs; var46 += 1)
				state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		}
		for(int i = 0; i < state.noObs; i += 1) {
			for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
				state.exped[i][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$17 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$17 = (reduceVar$sum$17 + state.exped[i][cv$reduction82Index]);
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
				state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$17);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample21) {
				for(int var20 = 0; var20 < state.noProducts; var20 += 1)
					inferSample21(var20);
			}
			if(!state.fixedFlag$sample28)
				inferSample28();
			if(!state.fixedFlag$sample34)
				inferSample34();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample47) {
				for(int var46 = 0; var46 < state.noObs; var46 += 1)
					inferSample47(var46);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample47) {
				for(int var46 = (state.noObs - 1); var46 >= 0; var46 -= 1)
					inferSample47(var46);
			}
			if(!state.fixedFlag$sample34)
				inferSample34();
			if(!state.fixedFlag$sample28)
				inferSample28();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample21) {
				for(int var20 = (state.noProducts - 1); var20 >= 0; var20 -= 1)
					inferSample21(var20);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
			if(!state.constrainedFlag$sample21[var20])
				drawValueSample21(var20);
		}
		if(!state.constrainedFlag$sample28)
			drawValueSample28();
		if(!state.constrainedFlag$sample34)
			drawValueSample34();
		for(int var46 = 0; var46 < state.noObs; var46 += 1) {
			if(!state.constrainedFlag$sample47[var46])
				drawValueSample47(var46);
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
		state.logProbability$prob = 0.0;
		if(!state.fixedProbFlag$sample21) {
			for(int var20 = 0; var20 < state.noProducts; var20 += 1)
				state.logProbability$sample21[var20] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample28)
			state.logProbability$b = Double.NaN;
		if(!state.fixedProbFlag$sample34)
			state.logProbability$sigma = Double.NaN;
		state.logProbability$beta = 0.0;
		if(!state.fixedProbFlag$sample47) {
			for(int var46 = 0; var46 < state.noObs; var46 += 1)
				state.logProbability$sample47[var46] = Double.NaN;
		}
		state.logProbability$choices = 0.0;
		if(!state.fixedProbFlag$sample103) {
			for(int i = 0; i < state.noObs; i += 1)
				state.logProbability$sample103[i] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		// Set all the values in the array
		for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < state.constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
			state.constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample21$1 = 0; index$constrainedFlag$sample21$1 < state.constrainedFlag$sample21.length; index$constrainedFlag$sample21$1 += 1)
			state.constrainedFlag$sample21[index$constrainedFlag$sample21$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample21)
			logProbabilityValue$sample21();
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(state.fixedFlag$sample47)
			logProbabilityValue$sample47();
		logProbabilityValue$sample103();
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
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
		logProbabilityValue$sample103();
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
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
		logProbabilityValue$sample103();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.choices.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.choices[cv$index1] = state.ObsChoices[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		for(int i = 0; i < state.noObs; i += 1) {
			for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
				state.exped[i][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$19 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$19 = (reduceVar$sum$19 + state.exped[i][cv$reduction82Index]);
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
				state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$19);
		}
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
		     + "\n"
		     + "model DiscreteChoiceRandCoeff(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = gaussian(0, 10).sample(noProducts);\n"
		     + "    //can we set the first element to 0? like ut[0] <~ 0\n"
		     + "\n"
		     + "    //priors for distribution of beta\n"
		     + "    double b = gaussian(0,10).sample();\n"
		     + "    double sigma =  inverseGamma(2,2).sample();\n"
		     + "\n"
		     + "    double[] beta = gaussian(b, sigma).sample(noObs);\n"
		     + "\n"
		     + "    int[] choices = new int[noObs];\n"
		     + "\n"
		     + "    for (int i:[0..noObs)){\n"
		     + "        // calculate choice probabilities for consumer i\n"
		     + "\n"
		     + "        double[] exped = new double[noProducts];\n"
		     + "        for(int j : [0..noProducts)) {\n"
		     + "            exped[j] = exp(ut[j]- beta[i]*Prices[i][j]);\n"
		     + "            }\n"
		     + "        double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "        public double[] prob = new double[noProducts];\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            prob[j] = exped[j] / sum;\n"
		     + "        }\n"
		     + "        // emit choices of consumer i\n"
		     + "        choices[i] = categorical(prob).sample();\n"
		     + "                                }\n"
		     + "\n"
		     + "    // assert that generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}