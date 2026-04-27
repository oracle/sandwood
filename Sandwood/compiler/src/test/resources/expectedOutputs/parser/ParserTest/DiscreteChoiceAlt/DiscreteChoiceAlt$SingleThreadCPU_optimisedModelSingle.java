package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DiscreteChoiceAlt$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DiscreteChoiceAlt.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DiscreteChoiceAlt$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		boolean[] guard$sample24put65$global;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// 
			// Constructor for guard$sample24put65$global
			// 
			// Allocation of guard$sample24put65$global for single threaded execution
			// 
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			guard$sample24put65$global = new boolean[Math.max(0, state.noProducts)];
		}
	}


	public DiscreteChoiceAlt$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample24
	private final void drawValueSample24(int i$var18) {
		state.ut[i$var18] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 24 and consumer double[] 39.
		// 
						// Substituted "i$var36" with its value "i$var18".
		state.exped[i$var18] = Math.exp(state.ut[i$var18]);
		
		// Guards to ensure that sum is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 24 and consumer double 50.
		// 
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$3 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// j's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$3 = (reduceVar$sum$3 + state.exped[cv$reduction44Index]);
		
		// Write out the new sample value.
		state.sum = reduceVar$sum$3;
		for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			scratch.guard$sample24put65$global[i$var61] = false;
		
		// Set the flags to false
		// 
		// Guard to check that at most one copy of the code is executed for a given random
		// variable instance.
		// 
		// Substituted "i$var61" with its value "i$var18".
		scratch.guard$sample24put65$global[i$var18] = false;
		for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			if(!scratch.guard$sample24put65$global[i$var61]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample24put65$global[i$var61] = true;
				
								// sum's comment
				// Write out the new sample value.
				state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$3);
			}
		}
		
						// Substituted "i$var36" with its value "i$var18".
		// 
		// Substituted "i$var61" with its value "i$var18".
		if(!scratch.guard$sample24put65$global[i$var18]) {
			// The body will execute, so should not be executed again
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "i$var61" with its value "i$var18".
			scratch.guard$sample24put65$global[i$var18] = true;
			
									// Substituted "i$var61" with its value "i$var18".
			// 
						// sum's comment
			// Write out the new sample value.
			state.prob[i$var18] = (state.exped[i$var18] / reduceVar$sum$3);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 24 drawn from Gaussian 23. Inference was performed using Metropolis-Hastings.
	private final void inferSample24(int i$var18) {
		state.constrainedFlag$sample24[(i$var18 - 1)] = false;
		
		// The original value of the sample
		double cv$originalValue = state.ut[i$var18];
		
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
			for(int var76 = 0; var76 < state.noObs; var76 += 1) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample24[(i$var18 - 1)] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 78 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
		if(state.constrainedFlag$sample24[(i$var18 - 1)]) {
			// Guards to ensure that ut is only updated when there is a valid path.
			state.ut[i$var18] = cv$proposedValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 24 and consumer double[] 39.
			// 
									// Substituted "i$var36" with its value "i$var18".
			state.exped[i$var18] = Math.exp(state.ut[i$var18]);
			
			// Guards to ensure that sum is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 24 and consumer double 50.
			// 
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$0 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// j's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$0 = (reduceVar$sum$0 + state.exped[cv$reduction44Index]);
			
			// Write out the new sample value.
			state.sum = reduceVar$sum$0;
			for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample24put65$global[i$var61] = false;
			
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "i$var61" with its value "i$var18".
			scratch.guard$sample24put65$global[i$var18] = false;
			for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!scratch.guard$sample24put65$global[i$var61]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample24put65$global[i$var61] = true;
					
										// sum's comment
					// Write out the new sample value.
					state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$0);
				}
			}
			
									// Substituted "i$var36" with its value "i$var18".
			// 
			// Substituted "i$var61" with its value "i$var18".
			if(!scratch.guard$sample24put65$global[i$var18]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "i$var61" with its value "i$var18".
				scratch.guard$sample24put65$global[i$var18] = true;
				
												// Substituted "i$var61" with its value "i$var18".
				// 
								// sum's comment
				// Write out the new sample value.
				state.prob[i$var18] = (state.exped[i$var18] / reduceVar$sum$0);
			}
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
			for(int var76 = 0; var76 < state.noObs; var76 += 1) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample24[(i$var18 - 1)] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 78 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
				state.ut[i$var18] = cv$originalValue;
				
				// Guards to ensure that exped is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 24 and consumer double[] 39.
				// 
												// Substituted "i$var36" with its value "i$var18".
				state.exped[i$var18] = Math.exp(state.ut[i$var18]);
				
				// Guards to ensure that sum is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 24 and consumer double 50.
				// 
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$2 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
																				// j's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$2 = (reduceVar$sum$2 + state.exped[cv$reduction44Index]);
				
				// Write out the new sample value.
				state.sum = reduceVar$sum$2;
				for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample24put65$global[i$var61] = false;
				
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "i$var61" with its value "i$var18".
				scratch.guard$sample24put65$global[i$var18] = false;
				for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!scratch.guard$sample24put65$global[i$var61]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample24put65$global[i$var61] = true;
						
												// sum's comment
						// Write out the new sample value.
						state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$2);
					}
				}
				
												// Substituted "i$var36" with its value "i$var18".
				// 
				// Substituted "i$var61" with its value "i$var18".
				if(!scratch.guard$sample24put65$global[i$var18]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "i$var61" with its value "i$var18".
					scratch.guard$sample24put65$global[i$var18] = true;
					
															// Substituted "i$var61" with its value "i$var18".
					// 
										// sum's comment
					// Write out the new sample value.
					state.prob[i$var18] = (state.exped[i$var18] / reduceVar$sum$2);
				}
			}
		}
	}

	// Calculate the probability of the samples represented by sample24 using sampled
	// values.
	private final void logProbabilityValue$sample24() {
		// Determine if we need to calculate the values for sample task 24 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample24) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1) {
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((state.ut[i$var18] / 3.1622776601683795)) - 1.151292546497023);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample24[(i$var18 - 1)] = cv$distributionAccumulator;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 24 and consumer double[] 39.
				// 
				// Update the variable probability
				state.logProbability$exped = (state.logProbability$exped + cv$distributionAccumulator);
				
				// Looking for a path between Sample 24 and consumer double 50.
				// 
				// Update the variable probability
				state.logProbability$sum = (state.logProbability$sum + cv$distributionAccumulator);
				
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
			if(state.fixedFlag$sample24)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample24 = state.fixedFlag$sample24;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1) {
				double cv$sampleValue = state.logProbability$sample24[(i$var18 - 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 24 and consumer double[] 39.
				// 
				// Update the variable probability
				state.logProbability$exped = (state.logProbability$exped + cv$sampleValue);
				
				// Looking for a path between Sample 24 and consumer double 50.
				// 
				// Update the variable probability
				state.logProbability$sum = (state.logProbability$sum + cv$sampleValue);
				
				// Update the variable probability
				state.logProbability$prob = (state.logProbability$prob + cv$sampleValue);
			}
			
			// Update the variable probability
			state.logProbability$ut = (state.logProbability$ut + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample24)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample78 using sampled
	// values.
	private final void logProbabilityValue$sample78() {
		// Determine if we need to calculate the values for sample task 78 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample78) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var76 = 0; var76 < state.noObs; var76 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.choices[var76];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[cv$sampleValue])) && (state.prob[cv$sampleValue] <= 1.0))?Math.log(state.prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				state.logProbability$var77 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$choices = (state.logProbability$choices + cv$sampleAccumulator);
			
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
			state.fixedProbFlag$sample78 = state.fixedFlag$sample24;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$choices = (state.logProbability$choices + state.logProbability$var77);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var77);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var77);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample24) {
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
				state.ut[i$var18] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
			for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1)
				state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$4 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// j's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$4 = (reduceVar$sum$4 + state.exped[cv$reduction44Index]);
			state.sum = reduceVar$sum$4;
			for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
				// Substituted "sum" with its value "reduceVar$sum$4".
				state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$4);
		}
		for(int var76 = 0; var76 < state.noObs; var76 += 1)
			state.choices[var76] = DistributionSampling.sampleCategorical(state.RNG$, state.prob, state.noProducts);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample24) {
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
				state.ut[i$var18] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1)
			state.exped[i$var36] = Math.exp(state.ut[i$var36]);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$8 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// j's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$8 = (reduceVar$sum$8 + state.exped[cv$reduction44Index]);
		state.sum = reduceVar$sum$8;
		for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
			// Substituted "sum" with its value "reduceVar$sum$8".
			state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$8);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample24) {
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
				state.ut[i$var18] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1)
			state.exped[i$var36] = Math.exp(state.ut[i$var36]);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$5 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// j's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$5 = (reduceVar$sum$5 + state.exped[cv$reduction44Index]);
		state.sum = reduceVar$sum$5;
		for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
			// Substituted "sum" with its value "reduceVar$sum$5".
			state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$5);
		for(int var76 = 0; var76 < state.noObs; var76 += 1)
			state.choices[var76] = DistributionSampling.sampleCategorical(state.RNG$, state.prob, state.noProducts);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample24) {
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
				state.ut[i$var18] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
			for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1)
				state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$6 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// j's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$6 = (reduceVar$sum$6 + state.exped[cv$reduction44Index]);
			state.sum = reduceVar$sum$6;
			for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
				// Substituted "sum" with its value "reduceVar$sum$6".
				state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$6);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample24) {
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
				state.ut[i$var18] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1)
			state.exped[i$var36] = Math.exp(state.ut[i$var36]);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$7 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// j's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$7 = (reduceVar$sum$7 + state.exped[cv$reduction44Index]);
		state.sum = reduceVar$sum$7;
		for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
			// Substituted "sum" with its value "reduceVar$sum$7".
			state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$7);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(!state.fixedFlag$sample24) {
			// Infer the samples in chronological order.
			if(state.system$gibbsForward) {
				for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
					inferSample24(i$var18);
			}
			// Infer the samples in reverse chronological order.
			else {
				for(int i$var18 = (state.noProducts - 1); i$var18 >= 1; i$var18 -= 1)
					inferSample24(i$var18);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1) {
			if(!state.constrainedFlag$sample24[(i$var18 - 1)])
				drawValueSample24(i$var18);
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
		state.logProbability$prob = 0.0;
		if(!state.fixedProbFlag$sample24) {
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
				state.logProbability$sample24[(i$var18 - 1)] = Double.NaN;
		}
		state.logProbability$choices = 0.0;
		if(!state.fixedProbFlag$sample78)
			state.logProbability$var77 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.ut[0] = 0.0;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample24$1 = 0; index$constrainedFlag$sample24$1 < state.constrainedFlag$sample24.length; index$constrainedFlag$sample24$1 += 1)
			state.constrainedFlag$sample24[index$constrainedFlag$sample24$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample24)
			logProbabilityValue$sample24();
		logProbabilityValue$sample78();
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
		logProbabilityValue$sample78();
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
		logProbabilityValue$sample78();
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
		for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1)
			state.exped[i$var36] = Math.exp(state.ut[i$var36]);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$9 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// j's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$9 = (reduceVar$sum$9 + state.exped[cv$reduction44Index]);
		state.sum = reduceVar$sum$9;
		for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
			// Substituted "sum" with its value "reduceVar$sum$9".
			state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$9);
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model DiscreteChoiceAlt(int noProducts, int noObs, int[] ObsChoices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = new double[noProducts];\n"
		     + "    ut[0] = 0.0;\n"
		     + "    for(int i=1; i<noProducts; i++) \n"
		     + "        ut[i]= gaussian(0, 10).sample();\n"
		     + "\n"
		     + "    // calculate choice probabilities\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int i : [0..noProducts)) {\n"
		     + "        exped[i] = exp(ut[i]);\n"
		     + "    }\n"
		     + "    double sum = reduce(exped, 0, (i, j) -> { return i + j; });\n"
		     + "    double[] prob = new double[noProducts];\n"
		     + "    for (int i : [0..noProducts)) {\n"
		     + "        prob[i] = exped[i] / sum;\n"
		     + "    }\n"
		     + "    // draw consumer choices according to the calculated probabilities\n"
		     + "    int[] choices = categorical(prob).sample(noObs);\n"
		     + "\n"
		     + "    // assert generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}