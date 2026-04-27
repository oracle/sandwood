package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.RaggedArray6$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.RaggedArray6.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray6$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var45$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// 
			// Constructor for cv$var45$stateProbabilityGlobal
			// 
			// Allocation of cv$var45$stateProbabilityGlobal for single threaded execution
			cv$var45$stateProbabilityGlobal = new double[2];
		}
	}


	public RaggedArray6$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample47
	private final void drawValueSample47() {
		state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample50
	private final void drawValueSample50() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$48_4 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.y))
			lengthCV$a$48_4 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.y))
			lengthCV$a$48_4 = 3;
		DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_4, state.d);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 47 drawn from Categorical 44. Inference was performed using variable
	// marginalization.
	private final void inferSample47() {
		state.constrainedFlag$sample47 = false;
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			state.y = 0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= state.b[0]) && (state.b[0] <= 1.0))?Math.log(state.b[0]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.fixedFlag$sample50 || state.constrainedFlag$sample50)) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample47 = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 50 with the current configuration.
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
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityDirichlet(state.d, state.a[0], 2) + cv$accumulatedProbabilities);
			}
			
			// Processing sample task 65 of consumer random variable null.
			for(int var62 = 0; var62 < state.length$obs_measured; var62 += 1) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample47 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double var49 = state.d[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 65 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= var49) && (var49 <= 1.0))?Math.log((state.obs[var62]?var49:(1.0 - var49))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var45$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.y = 1;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
						// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= state.b[1]) && (state.b[1] <= 1.0))?Math.log(state.b[1]):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((state.fixedFlag$sample50 || state.constrainedFlag$sample50)) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample47 = true;
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 50 with the current configuration.
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
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityDirichlet(state.d, state.a[1], 3) + cv$accumulatedProbabilities);
		}
		
		// Processing sample task 65 of consumer random variable null.
		for(int var62 = 0; var62 < state.length$obs_measured; var62 += 1) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample47 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double var49 = state.d[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 65 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = ((((0.0 <= var49) && (var49 <= 1.0))?Math.log((state.obs[var62]?var49:(1.0 - var49))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var45$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample47) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var45$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var45$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				// 
				// Get a local reference to the scratch space.
				// 
				// Get a local reference to the scratch space.
				// 
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((scratch.cv$var45$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var45$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var45$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var45$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var45$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var45$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var45$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var45$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var45$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var45$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.y = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var45$stateProbabilityGlobal, 2);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 50 drawn from Dirichlet 47. Inference was performed using Metropolis-Hastings.
	private final void inferSample50() {
		state.constrainedFlag$sample50 = false;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the probability of the random variable generating the original sampled
		// value.
		double cv$originalProbability;
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$48_2 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.y))
			lengthCV$a$48_2 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.y))
			lengthCV$a$48_2 = 3;
		
		// Pick a value in the array to adjust.
		int cv$indexToChange = (int)((double)lengthCV$a$48_2 * DistributionSampling.sampleUniform(state.RNG$));
		
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
			cv$proposedDifference = state.d[cv$indexToChange];
		else {
			// Calculate the maximum magnitude of the proposed index change.
			// Initially set the maximum to the amount that the value we are changing could increase
			// without exceeding 1
			// 
			// A reference local to the function for the sample variable.
			cv$proposedDifference = (1.0 - state.d[cv$indexToChange]);
			
			// For the array values up to the index we are going to change calculate the maximum
			// move possible.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
				// Calculate the maximum change value that the value at array index cv$loopIndex could
				// support. Based on moving all other values by an equal amount.
				// 
				// A reference local to the function for the sample variable.
				double cv$temp = (state.d[cv$loopIndex] * (lengthCV$a$48_2 - 1));
				
				// If the maximum move is less than the proposed move update the move size.
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			
			// For the array values after the index we are going to change calculate the maximum
			// move possible.
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < lengthCV$a$48_2; cv$loopIndex += 1) {
				// Calculate the maximum change value that the value at array index cv$loopIndex could
				// support. Based on moving all other values by an equal amount.
				// 
				// A reference local to the function for the sample variable.
				double cv$temp = (state.d[cv$loopIndex] * (lengthCV$a$48_2 - 1));
				
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
		double cv$rebalanceValue = (cv$proposedDifference / (lengthCV$a$48_2 - 1));
		{
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$48_3 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$48_3 = 3;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(state.d, state.a[state.y], lengthCV$a$48_3);
			
			// Processing random variable 50.
			// 
			// Processing sample task 65 of consumer random variable null.
			for(int var62 = 0; var62 < state.length$obs_measured; var62 += 1) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample50 = true;
				
				// Constructing a random variable input for use later.
				double var49 = state.d[state.y];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 65 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= var49) && (var49 <= 1.0))?Math.log((state.obs[var62]?var49:(1.0 - var49))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
		if(state.constrainedFlag$sample50) {
			// Update Sample and intermediate values
			// 
			// Update the sample value
			// 
			// Update all the indexes up to the index selected.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
												// A reference local to the function for the sample variable.
				state.d[cv$loopIndex] = (state.d[cv$loopIndex] - cv$rebalanceValue);
			
			// Update the selected index.
			// 
									// A reference local to the function for the sample variable.
			state.d[cv$indexToChange] = (state.d[cv$indexToChange] + cv$proposedDifference);
			
			// Update all the indexes after the index we selected.
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < lengthCV$a$48_2; cv$loopIndex += 1)
												// A reference local to the function for the sample variable.
				state.d[cv$loopIndex] = (state.d[cv$loopIndex] - cv$rebalanceValue);
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$48_3 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$48_3 = 3;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(state.d, state.a[state.y], lengthCV$a$48_3);
			
			// Processing sample task 65 of consumer random variable null.
			for(int var62 = 0; var62 < state.length$obs_measured; var62 += 1) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample50 = true;
				
				// Constructing a random variable input for use later.
				double var49 = state.d[state.y];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 65 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= var49) && (var49 <= 1.0))?Math.log((state.obs[var62]?var49:(1.0 - var49))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
				// 
				// Calculate the new sample value
				// 
				// Update the sample value
				// Update all the indexes up to the index selected.
				for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
															// A reference local to the function for the sample variable.
					state.d[cv$loopIndex] = (state.d[cv$loopIndex] + cv$rebalanceValue);
				
				// Update the selected index.
				// 
												// A reference local to the function for the sample variable.
				state.d[cv$indexToChange] = (state.d[cv$indexToChange] - cv$proposedDifference);
				
				// Update all the indexes after the index we selected.
				for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < lengthCV$a$48_2; cv$loopIndex += 1)
															// A reference local to the function for the sample variable.
					state.d[cv$loopIndex] = (state.d[cv$loopIndex] + cv$rebalanceValue);
			}
		}
	}

	// Calculate the probability of the samples represented by sample47 using sampled
	// values.
	private final void logProbabilityValue$sample47() {
		// Determine if we need to calculate the values for sample task 47 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample47) {
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
			double cv$distributionAccumulator = (((((0.0 <= state.y) && (state.y < 2)) && (0.0 <= state.b[state.y])) && (state.b[state.y] <= 1.0))?Math.log(state.b[state.y]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$y = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample47)
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
			state.fixedProbFlag$sample47 = state.fixedFlag$sample47;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$y);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample47)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$y);
		}
	}

	// Calculate the probability of the samples represented by sample50 using sampled
	// values.
	private final void logProbabilityValue$sample50() {
		// Determine if we need to calculate the values for sample task 50 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample50) {
			// Generating probabilities for sample task
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$48_5 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$48_5 = 3;
			
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.d, state.a[state.y], lengthCV$a$48_5);
			
			// Store the sample task probability
			state.logProbability$d = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample50)
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
			state.fixedProbFlag$sample50 = (state.fixedFlag$sample50 && state.fixedFlag$sample47);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$d);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample50)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$d);
		}
	}

	// Calculate the probability of the samples represented by sample65 using sampled
	// values.
	private final void logProbabilityValue$sample65() {
		// Determine if we need to calculate the values for sample task 65 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample65) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var62 = 0; var62 < state.length$obs_measured; var62 += 1) {
				double var49 = state.d[state.y];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var49) && (var49 <= 1.0))?Math.log((state.obs[var62]?var49:(1.0 - var49))):Double.NEGATIVE_INFINITY));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				state.logProbability$var63 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$obs = (state.logProbability$obs + cv$sampleAccumulator);
			
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
			state.fixedProbFlag$sample65 = (state.fixedFlag$sample47 && state.fixedFlag$sample50);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$obs = (state.logProbability$obs + state.logProbability$var63);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var63);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var63);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample50) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$48_6 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$48_6 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_6, state.d);
		}
		for(int var62 = 0; var62 < state.length$obs_measured; var62 += 1)
			state.obs[var62] = DistributionSampling.sampleBernoulli(state.RNG$, state.d[state.y]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample50) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_10 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$48_10 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$48_10 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_10, state.d);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample50) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_7 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$48_7 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$48_7 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_7, state.d);
		}
		for(int var62 = 0; var62 < state.length$obs_measured; var62 += 1)
			state.obs[var62] = DistributionSampling.sampleBernoulli(state.RNG$, state.d[state.y]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample50) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_8 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$48_8 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$48_8 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_8, state.d);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample50) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_9 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$48_9 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$48_9 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_9, state.d);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample47)
				inferSample47();
			if(!state.fixedFlag$sample50)
				inferSample50();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!state.fixedFlag$sample50)
				inferSample50();
			if(!state.fixedFlag$sample47)
				inferSample47();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample47)
			drawValueSample47();
		if(!state.constrainedFlag$sample50)
			drawValueSample50();
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
		if(!state.fixedProbFlag$sample47)
			state.logProbability$y = Double.NaN;
		if(!state.fixedProbFlag$sample50)
			state.logProbability$d = Double.NaN;
		state.logProbability$obs = 0.0;
		if(!state.fixedProbFlag$sample65)
			state.logProbability$var63 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		double[] var5 = state.a[0];
		var5[0] = 0.4;
		var5[1] = 0.6;
		double[] var18 = state.a[1];
		var18[0] = 0.2;
		var18[1] = 0.3;
		var18[2] = 0.5;
		state.b[0] = 0.35;
		state.b[1] = 0.65;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(state.fixedFlag$sample50)
			logProbabilityValue$sample50();
		logProbabilityValue$sample65();
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
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample65();
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
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample65();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.obs.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.obs[cv$index1] = state.obs_measured[cv$index1];
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
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + " package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray6(boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[] b = { 0.35, 0.65 };\n"
		     + "    int y = categorical(b).sample();\n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    boolean[] obs = bernoulli(d[y]).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}