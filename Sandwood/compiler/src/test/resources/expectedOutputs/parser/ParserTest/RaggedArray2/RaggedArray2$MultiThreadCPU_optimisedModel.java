package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.RaggedArray2$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.RaggedArray2.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray2$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var77$stateProbabilityGlobal;
		double[] cv$var80$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var77$stateProbabilityGlobal
			// 
			// Allocation of cv$var77$stateProbabilityGlobal for single threaded execution
			cv$var77$stateProbabilityGlobal = new double[2];
			
			// Allocation of cv$var80$stateProbabilityGlobal for single threaded execution
			// 
			// Test if the input to putTask 34 is larger than the current values.
			cv$var80$stateProbabilityGlobal = new double[3];
		}
	}


	public RaggedArray2$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample81
	private final void drawValueSample81() {
		state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		
		// Guards to ensure that p is only updated when there is a valid path.
		// 
		// Write out the new sample value.
		state.p = state.b[state.y][state.i];
	}

	// Pick a value from the distribution for the unconditioned variable from sample84
	private final void drawValueSample84() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$82_13 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.y))
			lengthCV$a$82_13 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.y))
			lengthCV$a$82_13 = 3;
		state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_13);
		
		// Guards to ensure that p is only updated when there is a valid path.
		// 
		// Write out the new sample value.
		state.p = state.b[state.y][state.i];
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 81 drawn from Categorical 76. Inference was performed using variable
	// marginalization.
	private final void inferSample81() {
		state.constrainedFlag$sample81 = false;
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			state.y = 0;
			
			// Guards to ensure that p is only updated when there is a valid path.
			// 
			// Write out the new sample value.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			state.p = state.b[0][state.i];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= state.c[0]) && (state.c[0] <= 1.0))?Math.log(state.c[0]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.fixedFlag$sample84 || state.constrainedFlag$sample84)) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample81 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var78 = state.a[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 84 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((((0.0 <= state.i) && (state.i < 2)) && (0.0 <= var78[state.i])) && (var78[state.i] <= 1.0))?Math.log(var78[state.i]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Processing sample task 100 of consumer random variable null.
			for(int var95 = 0; var95 < state.length$obs_measured; var95 += 1) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample81 = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 100 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var77$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.y = 1;
		
		// Guards to ensure that p is only updated when there is a valid path.
		// 
		// Write out the new sample value.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.p = state.b[1][state.i];
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
						// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= state.c[1]) && (state.c[1] <= 1.0))?Math.log(state.c[1]):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((state.fixedFlag$sample84 || state.constrainedFlag$sample84)) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample81 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var78 = state.a[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 84 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = ((((((0.0 <= state.i) && (state.i < 3)) && (0.0 <= var78[state.i])) && (var78[state.i] <= 1.0))?Math.log(var78[state.i]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Processing sample task 100 of consumer random variable null.
		for(int var95 = 0; var95 < state.length$obs_measured; var95 += 1) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample81 = true;
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 100 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = ((((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var77$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample81) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var77$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var77$stateProbabilityGlobal[1];
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
				// Initialise the sum of the array elements
				cv$logSum = (Math.log((Math.exp((scratch.cv$var77$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var77$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var77$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var77$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var77$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var77$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var77$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var77$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var77$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var77$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.y = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var77$stateProbabilityGlobal, 2);
			
			// Guards to ensure that p is only updated when there is a valid path.
			// 
			// Write out the new sample value.
			state.p = state.b[state.y][state.i];
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 84 drawn from Categorical 79. Inference was performed using variable
	// marginalization.
	private final void inferSample84() {
		state.constrainedFlag$sample84 = false;
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$82_11 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.y))
			lengthCV$a$82_11 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.y))
			lengthCV$a$82_11 = 3;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
				// cv$numStates's comment
		// Calculate the number of states to evaluate.
		int cv$numStates = Math.max(0, lengthCV$a$82_11);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			state.i = cv$valuePos;
			
			// Guards to ensure that p is only updated when there is a valid path.
			// 
			// Write out the new sample value.
			// 
			// Value of the variable at this index
			state.p = state.b[state.y][cv$valuePos];
			
			// Constructing a random variable input for use later.
			double[] var78 = state.a[state.y];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$82_12 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$82_12 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$82_12 = 3;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < lengthCV$a$82_12) && (0 < lengthCV$a$82_12)) && (0.0 <= var78[cv$valuePos])) && (var78[cv$valuePos] <= 1.0))?Math.log(var78[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Processing sample task 100 of consumer random variable null.
			for(int var95 = 0; var95 < state.length$obs_measured; var95 += 1) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample84 = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 100 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var80$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample84) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var80$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var80$stateProbabilityGlobal[cv$lseIndex];
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
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var80$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Get a local reference to the scratch space.
					scratch.cv$var80$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
															// Get a local reference to the scratch space.
					scratch.cv$var80$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var80$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var80$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var80$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// Get a local reference to the scratch space.
			state.i = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var80$stateProbabilityGlobal, cv$numStates);
			
			// Guards to ensure that p is only updated when there is a valid path.
			// 
			// Write out the new sample value.
			state.p = state.b[state.y][state.i];
		}
	}

	// Calculate the probability of the samples represented by sample100 using sampled
	// values.
	private final void logProbabilityValue$sample100() {
		// Determine if we need to calculate the values for sample task 100 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample100) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var95 = 0; var95 < state.length$obs_measured; var95 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY));
			
			// Store the random variable instance probability
			state.logProbability$var96 = cv$sampleAccumulator;
			
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
			state.fixedProbFlag$sample100 = (state.fixedFlag$sample81 && state.fixedFlag$sample84);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$obs = (state.logProbability$obs + state.logProbability$var96);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var96);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var96);
		}
	}

	// Calculate the probability of the samples represented by sample81 using sampled
	// values.
	private final void logProbabilityValue$sample81() {
		// Determine if we need to calculate the values for sample task 81 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample81) {
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
			double cv$distributionAccumulator = (((((0.0 <= state.y) && (state.y < 2)) && (0.0 <= state.c[state.y])) && (state.c[state.y] <= 1.0))?Math.log(state.c[state.y]):Double.NEGATIVE_INFINITY);
			
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
			if(state.fixedFlag$sample81)
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
			state.fixedProbFlag$sample81 = state.fixedFlag$sample81;
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
			if(state.fixedFlag$sample81)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$y);
		}
	}

	// Calculate the probability of the samples represented by sample84 using sampled
	// values.
	private final void logProbabilityValue$sample84() {
		// Determine if we need to calculate the values for sample task 84 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample84) {
			// Generating probabilities for sample task
			double[] var78 = state.a[state.y];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$82_14 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$82_14 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$82_14 = 3;
			
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
			double cv$distributionAccumulator = ((((((0.0 <= state.i) && (state.i < lengthCV$a$82_14)) && (0 < lengthCV$a$82_14)) && (0.0 <= var78[state.i])) && (var78[state.i] <= 1.0))?Math.log(var78[state.i]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$i = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample84)
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
			state.fixedProbFlag$sample84 = (state.fixedFlag$sample84 && state.fixedFlag$sample81);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$i);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample84)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$i);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample81)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample84) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$82_15 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$82_15 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$82_15 = 3;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_15);
		}
		if((!state.fixedFlag$sample81 || !state.fixedFlag$sample84))
			state.p = state.b[state.y][state.i];
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
						state.obs[var95] = DistributionSampling.sampleBernoulli(RNG$1, state.p);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample81)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample84) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$82_19 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$82_19 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$82_19 = 3;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_19);
		}
		state.p = state.b[state.y][state.i];
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample81)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample84) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$82_16 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$82_16 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$82_16 = 3;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_16);
		}
		state.p = state.b[state.y][state.i];
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
						state.obs[var95] = DistributionSampling.sampleBernoulli(RNG$1, state.p);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample81)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample84) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$82_17 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$82_17 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$82_17 = 3;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_17);
		}
		if((!state.fixedFlag$sample81 || !state.fixedFlag$sample84))
			state.p = state.b[state.y][state.i];
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample81)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample84) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$82_18 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$82_18 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$82_18 = 3;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_18);
		}
		state.p = state.b[state.y][state.i];
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample81)
				inferSample81();
			if(!state.fixedFlag$sample84)
				inferSample84();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!state.fixedFlag$sample84)
				inferSample84();
			if(!state.fixedFlag$sample81)
				inferSample81();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample81)
			drawValueSample81();
		if(!state.constrainedFlag$sample84)
			drawValueSample84();
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
		if(!state.fixedProbFlag$sample81)
			state.logProbability$y = Double.NaN;
		if(!state.fixedProbFlag$sample84)
			state.logProbability$i = Double.NaN;
		state.logProbability$obs = 0.0;
		if(!state.fixedProbFlag$sample100)
			state.logProbability$var96 = Double.NaN;
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
		double[] var37 = state.b[0];
		var37[0] = 0.2;
		var37[1] = 0.8;
		double[] var50 = state.b[1];
		var50[0] = 0.4;
		var50[1] = 0.2;
		var50[2] = 0.6;
		state.c[0] = 0.35;
		state.c[1] = 0.65;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample81)
			logProbabilityValue$sample81();
		if(state.fixedFlag$sample84)
			logProbabilityValue$sample84();
		logProbabilityValue$sample100();
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
		logProbabilityValue$sample81();
		logProbabilityValue$sample84();
		logProbabilityValue$sample100();
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
		logProbabilityValue$sample81();
		logProbabilityValue$sample84();
		logProbabilityValue$sample100();
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
	public final void setIntermediates() {
		state.p = state.b[state.y][state.i];
	}

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
		     + "public model RaggedArray2(boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n"
		     + "    double[] c = { 0.35, 0.65 };\n"
		     + "    int y = categorical(c).sample();\n"
		     + "    int i = categorical(a[y]).sample();\n"
		     + "    double p = b[y][i];\n"
		     + "    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}