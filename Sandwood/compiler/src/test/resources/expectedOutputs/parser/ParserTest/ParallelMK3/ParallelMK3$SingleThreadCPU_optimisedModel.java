package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.ParallelMK3$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.ParallelMK3.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ParallelMK3$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		boolean[] guard$sample21gaussian37$global;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// 
			// Constructor for guard$sample21gaussian37$global
			// 
			// Allocation of guard$sample21gaussian37$global for single threaded execution
			guard$sample21gaussian37$global = new boolean[state.length$observed];
		}
	}


	public ParallelMK3$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample21
	private final void drawValueSample21() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		
		// Guards to ensure that indirection is only updated when there is a valid path.
		for(int i = 0; i < state.length$observed; i += 1)
			state.indirection[i] = state.sample[i];
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 21 drawn from Dirichlet 20. Inference was performed using Metropolis-Hastings.
	private final void inferSample21() {
		state.constrainedFlag$sample21 = false;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the probability of the random variable generating the original sampled
		// value.
		double cv$originalProbability;
		
		// Pick a value in the array to adjust.
		int cv$indexToChange = (int)(DistributionSampling.sampleUniform(state.RNG$) * 10.0);
		
		// Pick how much the value should be moved by. Initially this value is proposed as
		// a ratio of the current magnitude of the value, we will check to make sure the adjustment
		// will not make this value too large or other values too small and adjust if required
		// before it is applied.
		double cv$movementRatio = ((DistributionSampling.sampleBeta(state.RNG$, 5, 5) * 1.9999) - 1);
		
		// Calculate how much we are going to move the array index cv$indexToChange the by.
		// 
		// Allocate space for the proposed change to be stored as an absolute value
		double cv$proposedDifference;
		
		// Test if we are increasing or decreasing the value at the index. For each case calculate
		// the maximum valid adjustment.
		if((cv$movementRatio < 0))
			// The maximum reduction of the array at the index without going below 0 is the value
			// of the array at that index.
			// 
			// A reference local to the function for the sample variable.
			cv$proposedDifference = state.sample[cv$indexToChange];
		else {
			// Calculate the maximum magnitude of the proposed index change.
			// Initially set the maximum to the amount that the value we are changing could increase
			// without exceeding 1
			// 
			// A reference local to the function for the sample variable.
			cv$proposedDifference = (1.0 - state.sample[cv$indexToChange]);
			
			// For the array values up to the index we are going to change calculate the maximum
			// move possible.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
				// Calculate the maximum change value that the value at array index cv$loopIndex could
				// support. Based on moving all other values by an equal amount.
				// 
				// A reference local to the function for the sample variable.
				double cv$temp = (state.sample[cv$loopIndex] * 9);
				
				// If the maximum move is less than the proposed move update the move size.
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			
			// For the array values after the index we are going to change calculate the maximum
			// move possible.
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < 10; cv$loopIndex += 1) {
				// Calculate the maximum change value that the value at array index cv$loopIndex could
				// support. Based on moving all other values by an equal amount.
				// 
				// A reference local to the function for the sample variable.
				double cv$temp = (state.sample[cv$loopIndex] * 9);
				
				// If this is less than the proposed increase, change the proposed increase to this
				// value.
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
		}
		
		// Multiply the maximum adjustment by the adjustment ratio to get the actual adjustment
		// we are going to make.
		cv$proposedDifference = (cv$movementRatio * cv$proposedDifference);
		
		// Calculate how much each of the other indexes needs to be adjusted by in order to
		// maintain that the sum of the indexes is 1.
		double cv$rebalanceValue = (cv$proposedDifference / 9);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// A reference local to the function for the sample variable.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(state.sample, state.v, 10);
			for(int i = 0; i < state.length$observed; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample21gaussian37$global[i] = false;
			for(int i = 0; i < state.length$observed; i += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!scratch.guard$sample21gaussian37$global[i]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample21gaussian37$global[i] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample21 = true;
					
					// Constructing a random variable input for use later.
					double var36 = state.indirection[i];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 38 with the current configuration.
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
					cv$accumulatedProbabilities = (((0.0 < var36)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - state.sample[i]) / Math.sqrt(var36))) - (Math.log(var36) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int i = 0; i < state.length$observed; i += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample21gaussian37$global[i]) {
					double traceTempVariable$var36$5_2 = state.sample[i];
					
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample21gaussian37$global[i] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample21 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 38 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "index$i$5_3" with its value "i".
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$i$5_3" with its value "i".
					cv$accumulatedProbabilities = (((0.0 < traceTempVariable$var36$5_2)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - state.sample[i]) / Math.sqrt(traceTempVariable$var36$5_2))) - (Math.log(traceTempVariable$var36$5_2) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			
			// Initialize an accumulator to take the product of all the distribution probabilities
			// in log space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.constrainedFlag$sample21) {
			// Update Sample and intermediate values
			// 
			// Update the sample value
			// 
			// Update all the indexes up to the index selected.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
												// A reference local to the function for the sample variable.
				state.sample[cv$loopIndex] = (state.sample[cv$loopIndex] - cv$rebalanceValue);
			
			// Update the selected index.
			// 
									// A reference local to the function for the sample variable.
			state.sample[cv$indexToChange] = (state.sample[cv$indexToChange] + cv$proposedDifference);
			
			// Update all the indexes after the index we selected.
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < 10; cv$loopIndex += 1)
												// A reference local to the function for the sample variable.
				state.sample[cv$loopIndex] = (state.sample[cv$loopIndex] - cv$rebalanceValue);
			
			// Guards to ensure that indirection is only updated when there is a valid path.
			for(int i = 0; i < state.length$observed; i += 1)
				state.indirection[i] = state.sample[i];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// A reference local to the function for the sample variable.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(state.sample, state.v, 10);
			for(int i = 0; i < state.length$observed; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample21gaussian37$global[i] = false;
			for(int i = 0; i < state.length$observed; i += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!scratch.guard$sample21gaussian37$global[i]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample21gaussian37$global[i] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample21 = true;
					
					// Constructing a random variable input for use later.
					double var36 = state.indirection[i];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 38 with the current configuration.
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
					cv$accumulatedProbabilities = (((0.0 < var36)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - state.sample[i]) / Math.sqrt(var36))) - (Math.log(var36) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int i = 0; i < state.length$observed; i += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample21gaussian37$global[i]) {
					double traceTempVariable$var36$5_2 = state.sample[i];
					
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample21gaussian37$global[i] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample21 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 38 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "index$i$5_3" with its value "i".
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$i$5_3" with its value "i".
					cv$accumulatedProbabilities = (((0.0 < traceTempVariable$var36$5_2)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - state.sample[i]) / Math.sqrt(traceTempVariable$var36$5_2))) - (Math.log(traceTempVariable$var36$5_2) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			
			// Ratio of the probability of proposed and original sample values
			// 
			// Initialize an accumulator to take the product of all the distribution probabilities
			// in log space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			// 
			// Substituted "cv$valuePos" with its value "1".
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				// If it is not revert the sample value and intermediates to their original values.
				// 
				// Set the sample value
				// Calculate the new sample value
				// 
				// Update the sample value
				// 
				// Update all the indexes up to the index selected.
				for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
															// A reference local to the function for the sample variable.
					state.sample[cv$loopIndex] = (state.sample[cv$loopIndex] + cv$rebalanceValue);
				
				// Update the selected index.
				// 
												// A reference local to the function for the sample variable.
				state.sample[cv$indexToChange] = (state.sample[cv$indexToChange] - cv$proposedDifference);
				
				// Update all the indexes after the index we selected.
				for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < 10; cv$loopIndex += 1)
															// A reference local to the function for the sample variable.
					state.sample[cv$loopIndex] = (state.sample[cv$loopIndex] + cv$rebalanceValue);
				
				// Guards to ensure that indirection is only updated when there is a valid path.
				for(int i = 0; i < state.length$observed; i += 1)
					state.indirection[i] = state.sample[i];
			}
		}
	}

	// Calculate the probability of the samples represented by sample21 using sampled
	// values.
	private final void logProbabilityValue$sample21() {
		// Determine if we need to calculate the values for sample task 21 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample21) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.sample, state.v, 10);
			
			// Store the sample task probability
			state.logProbability$sample = cv$distributionAccumulator;
			
			// Add probability to constructed variables from the combined probability
			if((0 < state.length$observed))
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
				state.logProbability$indirection = (state.logProbability$indirection + cv$distributionAccumulator);
			
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
			if(state.fixedFlag$sample21)
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
			state.fixedProbFlag$sample21 = state.fixedFlag$sample21;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to constructed variables from the combined probability
			if((0 < state.length$observed))
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				state.logProbability$indirection = (state.logProbability$indirection + state.logProbability$sample);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$sample);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample21)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$sample);
		}
	}

	// Calculate the probability of the samples represented by sample38 using sampled
	// values.
	private final void logProbabilityValue$sample38() {
		// Determine if we need to calculate the values for sample task 38 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample38) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.length$observed; i += 1) {
				double var36 = state.indirection[i];
				
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
				double cv$distributionAccumulator = ((0.0 < var36)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - state.sample[i]) / Math.sqrt(var36))) - (Math.log(var36) * 0.5)):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample38[i] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$generated = (state.logProbability$generated + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample38 = state.fixedFlag$sample21;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.length$observed; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample38[i]);
			
			// Update the variable probability
			state.logProbability$generated = (state.logProbability$generated + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		for(int i = 0; i < state.length$observed; i += 1) {
			if(!state.fixedFlag$sample21)
				state.indirection[i] = state.sample[i];
			state.generated[i] = ((Math.sqrt(state.indirection[i]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.sample[i]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		for(int i = 0; i < state.length$observed; i += 1)
			state.indirection[i] = state.sample[i];
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		for(int i = 0; i < state.length$observed; i += 1) {
			state.indirection[i] = state.sample[i];
			state.generated[i] = ((Math.sqrt(state.indirection[i]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.sample[i]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample21) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
			for(int i = 0; i < state.length$observed; i += 1)
				state.indirection[i] = state.sample[i];
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		for(int i = 0; i < state.length$observed; i += 1)
			state.indirection[i] = state.sample[i];
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample21)
			inferSample21();
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample21)
			drawValueSample21();
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
		state.logProbability$indirection = 0.0;
		if(!state.fixedProbFlag$sample21)
			state.logProbability$sample = Double.NaN;
		state.logProbability$generated = 0.0;
		if(!state.fixedProbFlag$sample38) {
			for(int i = 0; i < state.length$observed; i += 1)
				state.logProbability$sample38[i] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		for(int var17 = 0; var17 < 10; var17 += 1)
			state.v[var17] = 0.1;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample38();
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
		logProbabilityValue$sample38();
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
		logProbabilityValue$sample38();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.generated.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.generated[cv$index1] = state.observed[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		for(int i = 0; i < state.length$observed; i += 1)
			state.indirection[i] = state.sample[i];
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
		     + "public model ParallelMK3(double[] observed) {\n"
		     + "    double[] generated = new double[observed.length];\n"
		     + "    double[] indirection = new double[observed.length];\n"
		     + "    double[] v = new double[10] <~ 0.1;\n"
		     + "\n"
		     + "\n"
		     + "    double[] sample = dirichlet(v).sample();\n"
		     + "    for(int i=0; i<observed.length; i++) {\n"
		     + "        indirection[i] = sample[i];\n"
		     + "        generated[i] = gaussian(sample[i], indirection[i]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    generated.observe(observed);\n"
		     + "}";
	}
}