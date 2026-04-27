package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.RaggedArray5$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.RaggedArray5.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray5$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {}
	}


	public RaggedArray5$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample39
	private final void drawValueSample39() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_13 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.y))
			lengthCV$a$37_13 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.y))
			lengthCV$a$37_13 = 3;
		DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_13, state.d);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 39 drawn from Dirichlet 36. Inference was performed using Metropolis-Hastings.
	private final void inferSample39() {
		state.constrainedFlag$sample39 = false;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the probability of the random variable generating the original sampled
		// value.
		double cv$originalProbability;
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_11 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.y))
			lengthCV$a$37_11 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.y))
			lengthCV$a$37_11 = 3;
		
		// Pick a value in the array to adjust.
		int cv$indexToChange = (int)((double)lengthCV$a$37_11 * DistributionSampling.sampleUniform(state.RNG$));
		
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
				double cv$temp = (state.d[cv$loopIndex] * (lengthCV$a$37_11 - 1));
				
				// If the maximum move is less than the proposed move update the move size.
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			
			// For the array values after the index we are going to change calculate the maximum
			// move possible.
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < lengthCV$a$37_11; cv$loopIndex += 1) {
				// Calculate the maximum change value that the value at array index cv$loopIndex could
				// support. Based on moving all other values by an equal amount.
				// 
				// A reference local to the function for the sample variable.
				double cv$temp = (state.d[cv$loopIndex] * (lengthCV$a$37_11 - 1));
				
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
		double cv$rebalanceValue = (cv$proposedDifference / (lengthCV$a$37_11 - 1));
		{
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_12 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$37_12 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$37_12 = 3;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(state.d, state.a[state.y], lengthCV$a$37_12);
			
			// Processing random variable 39.
			// 
			// Processing sample task 54 of consumer random variable null.
			for(int var51 = 0; var51 < state.length$obs_measured; var51 += 1) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample39 = true;
				
				// Constructing a random variable input for use later.
				double var38 = state.d[state.y];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 54 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= var38) && (var38 <= 1.0))?Math.log((state.obs[var51]?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
		if(state.constrainedFlag$sample39) {
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
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < lengthCV$a$37_11; cv$loopIndex += 1)
												// A reference local to the function for the sample variable.
				state.d[cv$loopIndex] = (state.d[cv$loopIndex] - cv$rebalanceValue);
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_12 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$37_12 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$37_12 = 3;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(state.d, state.a[state.y], lengthCV$a$37_12);
			
			// Processing sample task 54 of consumer random variable null.
			for(int var51 = 0; var51 < state.length$obs_measured; var51 += 1) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample39 = true;
				
				// Constructing a random variable input for use later.
				double var38 = state.d[state.y];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 54 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= var38) && (var38 <= 1.0))?Math.log((state.obs[var51]?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
				for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < lengthCV$a$37_11; cv$loopIndex += 1)
															// A reference local to the function for the sample variable.
					state.d[cv$loopIndex] = (state.d[cv$loopIndex] + cv$rebalanceValue);
			}
		}
	}

	// Calculate the probability of the samples represented by sample39 using sampled
	// values.
	private final void logProbabilityValue$sample39() {
		// Determine if we need to calculate the values for sample task 39 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample39) {
			// Generating probabilities for sample task
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_14 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$37_14 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$37_14 = 3;
			
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.d, state.a[state.y], lengthCV$a$37_14);
			
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
			if(state.fixedFlag$sample39)
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
			state.fixedProbFlag$sample39 = state.fixedFlag$sample39;
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
			if(state.fixedFlag$sample39)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$d);
		}
	}

	// Calculate the probability of the samples represented by sample54 using sampled
	// values.
	private final void logProbabilityValue$sample54() {
		// Determine if we need to calculate the values for sample task 54 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample54) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var51 = 0; var51 < state.length$obs_measured; var51 += 1) {
				double var38 = state.d[state.y];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var38) && (var38 <= 1.0))?Math.log((state.obs[var51]?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				state.logProbability$var52 = cv$sampleAccumulator;
			
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
			state.fixedProbFlag$sample54 = state.fixedFlag$sample39;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$obs = (state.logProbability$obs + state.logProbability$var52);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var52);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var52);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample39) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_15 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$37_15 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$37_15 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_15, state.d);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
						state.obs[var51] = DistributionSampling.sampleBernoulli(RNG$1, state.d[state.y]);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample39) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_19 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$37_19 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$37_19 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_19, state.d);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample39) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_16 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$37_16 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$37_16 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_16, state.d);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
						state.obs[var51] = DistributionSampling.sampleBernoulli(RNG$1, state.d[state.y]);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample39) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_17 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$37_17 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$37_17 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_17, state.d);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample39) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_18 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$37_18 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$37_18 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_18, state.d);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample39)
			inferSample39();
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample39)
			drawValueSample39();
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
		if(!state.fixedProbFlag$sample39)
			state.logProbability$d = Double.NaN;
		state.logProbability$obs = 0.0;
		if(!state.fixedProbFlag$sample54)
			state.logProbability$var52 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		double[] var6 = state.a[0];
		var6[0] = 0.4;
		var6[1] = 0.6;
		double[] var19 = state.a[1];
		var19[0] = 0.2;
		var19[1] = 0.3;
		var19[2] = 0.5;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample39)
			logProbabilityValue$sample39();
		logProbabilityValue$sample54();
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
		logProbabilityValue$sample39();
		logProbabilityValue$sample54();
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
		logProbabilityValue$sample39();
		logProbabilityValue$sample54();
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
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray5(int y, boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    \n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    boolean[] obs = bernoulli(d[y]).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}