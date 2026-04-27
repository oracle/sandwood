package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.MultinomialBernoulli$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.MultinomialBernoulli.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class MultinomialBernoulli$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var17$countGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// 
			// Constructor for cv$var17$countGlobal
			// 
			// Allocation of cv$var17$countGlobal for single threaded execution
			cv$var17$countGlobal = new double[3];
		}
	}


	public MultinomialBernoulli$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample17
	private final void drawValueSample17() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
	}

	// Pick a value from the distribution for the unconditioned variable from sample20
	private final void drawValueSample20() {
		DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, 10, state.prior);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 17 drawn from Dirichlet 16. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample17() {
		state.constrainedFlag$sample17 = false;
		
		// Unrolled loop
		// 
		// A local reference to the scratch space.
		scratch.cv$var17$countGlobal[0] = 0.0;
		
		// A local reference to the scratch space.
		scratch.cv$var17$countGlobal[1] = 0.0;
		
		// A local reference to the scratch space.
		scratch.cv$var17$countGlobal[2] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((state.fixedFlag$sample20 || state.constrainedFlag$sample20)) {
			// Processing random variable 19.
			// 
			// Processing sample task 20 of consumer random variable null.
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample17 = true;
			
									// A local reference to the scratch space.
			scratch.cv$var17$countGlobal[0] = (scratch.cv$var17$countGlobal[0] + state.prior[0]);
			
									// A local reference to the scratch space.
			scratch.cv$var17$countGlobal[1] = (scratch.cv$var17$countGlobal[1] + state.prior[1]);
			
									// A local reference to the scratch space.
			scratch.cv$var17$countGlobal[2] = (scratch.cv$var17$countGlobal[2] + state.prior[2]);
		}
		if(state.constrainedFlag$sample17)
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
									// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.beta, scratch.cv$var17$countGlobal, state.p, 3);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 20 drawn from Multinomial 19. Inference was performed using Metropolis-Hastings.
	private final void inferSample20() {
		state.constrainedFlag$sample20 = false;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the probability of the random variable generating the original sampled
		// value.
		double cv$originalProbability;
		
		// Count how many non zero entries there are.
		int cv$nonZeroCount = 0;
		
		// Unrolled loop
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!(state.prior[0] == 0))
			// Count how many non zero entries there are.
			cv$nonZeroCount = 1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!(state.prior[1] == 0))
			cv$nonZeroCount = (cv$nonZeroCount + 1);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!(state.prior[2] == 0))
			cv$nonZeroCount = (cv$nonZeroCount + 1);
		
		// Pick a value in the array to adjust.
		int cv$sourceIndex = (int)((double)cv$nonZeroCount * DistributionSampling.sampleUniform(state.RNG$));
		for(int cv$loopIndex = 0; cv$loopIndex <= cv$sourceIndex; cv$loopIndex += 1) {
									// A reference local to the function for the sample variable.
			if((state.prior[cv$loopIndex] == 0))
				cv$sourceIndex = (cv$sourceIndex + 1);
		}
		
		// Select the number of trials to remove from the selected category.
		// 
						// A reference local to the function for the sample variable.
		int cv$changeValue = (int)(((double)state.prior[cv$sourceIndex] * DistributionSampling.sampleUniform(state.RNG$)) + 1.0);
		
		// Select the destination of the moved trials.
		int cv$destinationIndex = (int)(DistributionSampling.sampleUniform(state.RNG$) * 2.0);
		
		// Ensure the source and target are not equal
		if((cv$sourceIndex <= cv$destinationIndex))
			cv$destinationIndex = (cv$destinationIndex + 1);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// A reference local to the function for the sample variable.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityMultinomial(state.prior, state.p, 3, 10);
			
			// Processing random variable 25.
			// 
			// Processing sample task 48 of consumer random variable b1.
			for(int i$var47 = 0; i$var47 < state.length; i$var47 += 3) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample20 = true;
				
				// Constructing a random variable input for use later.
				double var24 = (double)(state.prior[0] / 10);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 48 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= var24) && (var24 <= 1.0))?Math.log((state.output[i$var47]?var24:(1.0 - var24))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Processing random variable 30.
			// 
			// Processing sample task 60 of consumer random variable b2.
			for(int i$var59 = 1; i$var59 < state.length; i$var59 += 3) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample20 = true;
				
				// Constructing a random variable input for use later.
				double var29 = (double)(state.prior[1] / 10);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 60 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= var29) && (var29 <= 1.0))?Math.log((state.output[i$var59]?var29:(1.0 - var29))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Processing random variable 35.
			// 
			// Processing sample task 72 of consumer random variable b3.
			for(int i$var71 = 2; i$var71 < state.length; i$var71 += 3) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample20 = true;
				
				// Constructing a random variable input for use later.
				double var34 = (double)(state.prior[2] / 10);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 72 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.output[i$var71]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
		if(state.constrainedFlag$sample20) {
			// Update Sample and intermediate values
			// 
			// Update the sample values
			// 
									// A reference local to the function for the sample variable.
			state.prior[cv$sourceIndex] = (state.prior[cv$sourceIndex] - cv$changeValue);
			
									// A reference local to the function for the sample variable.
			state.prior[cv$destinationIndex] = (state.prior[cv$destinationIndex] + cv$changeValue);
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// A reference local to the function for the sample variable.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityMultinomial(state.prior, state.p, 3, 10);
			
			// Processing random variable 25.
			// 
			// Processing sample task 48 of consumer random variable b1.
			for(int i$var47 = 0; i$var47 < state.length; i$var47 += 3) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample20 = true;
				
				// Constructing a random variable input for use later.
				double var24 = (double)(state.prior[0] / 10);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 48 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= var24) && (var24 <= 1.0))?Math.log((state.output[i$var47]?var24:(1.0 - var24))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Processing random variable 30.
			// 
			// Processing sample task 60 of consumer random variable b2.
			for(int i$var59 = 1; i$var59 < state.length; i$var59 += 3) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample20 = true;
				
				// Constructing a random variable input for use later.
				double var29 = (double)(state.prior[1] / 10);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 60 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= var29) && (var29 <= 1.0))?Math.log((state.output[i$var59]?var29:(1.0 - var29))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Processing random variable 35.
			// 
			// Processing sample task 72 of consumer random variable b3.
			for(int i$var71 = 2; i$var71 < state.length; i$var71 += 3) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample20 = true;
				
				// Constructing a random variable input for use later.
				double var34 = (double)(state.prior[2] / 10);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 72 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.output[i$var71]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
				// Update the sample values
												// A reference local to the function for the sample variable.
				state.prior[cv$sourceIndex] = (state.prior[cv$sourceIndex] + cv$changeValue);
				
												// A reference local to the function for the sample variable.
				state.prior[cv$destinationIndex] = (state.prior[cv$destinationIndex] - cv$changeValue);
			}
		}
	}

	// Calculate the probability of the samples represented by sample17 using sampled
	// values.
	private final void logProbabilityValue$sample17() {
		// Determine if we need to calculate the values for sample task 17 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample17) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.p, state.beta, 3);
			
			// Store the sample task probability
			state.logProbability$p = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample17)
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
			state.fixedProbFlag$sample17 = state.fixedFlag$sample17;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$p);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample17)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$p);
		}
	}

	// Calculate the probability of the samples represented by sample20 using sampled
	// values.
	private final void logProbabilityValue$sample20() {
		// Determine if we need to calculate the values for sample task 20 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample20) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(state.prior, state.p, 3, 10);
			
			// Store the sample task probability
			state.logProbability$prior = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample20)
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
			state.fixedProbFlag$sample20 = (state.fixedFlag$sample20 && state.fixedFlag$sample17);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$prior);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample20)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$prior);
		}
	}

	// Calculate the probability of the samples represented by sample48 using sampled
	// values.
	private final void logProbabilityValue$sample48() {
		// Determine if we need to calculate the values for sample task 48 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample48) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var47 = 0; i$var47 < state.length; i$var47 += 3) {
				double var24 = (double)(state.prior[0] / 10);
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var24) && (var24 <= 1.0))?Math.log((state.output[i$var47]?var24:(1.0 - var24))):Double.NEGATIVE_INFINITY));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				state.logProbability$b1 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				state.logProbability$var48 = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$output = (state.logProbability$output + cv$sampleAccumulator);
			
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
			state.fixedProbFlag$sample48 = state.fixedFlag$sample20;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if((0 < state.length))
				// Record that the sample was reached.
				cv$sampleReached = true;
			if(cv$sampleReached)
				state.logProbability$b1 = state.logProbability$var48;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$output = (state.logProbability$output + state.logProbability$var48);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var48);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var48);
		}
	}

	// Calculate the probability of the samples represented by sample60 using sampled
	// values.
	private final void logProbabilityValue$sample60() {
		// Determine if we need to calculate the values for sample task 60 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample60) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var59 = 1; i$var59 < state.length; i$var59 += 3) {
				double var29 = (double)(state.prior[1] / 10);
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var29) && (var29 <= 1.0))?Math.log((state.output[i$var59]?var29:(1.0 - var29))):Double.NEGATIVE_INFINITY));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				state.logProbability$b2 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				state.logProbability$var60 = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$output = (state.logProbability$output + cv$sampleAccumulator);
			
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
			state.fixedProbFlag$sample60 = state.fixedFlag$sample20;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if((1 < state.length))
				// Record that the sample was reached.
				cv$sampleReached = true;
			if(cv$sampleReached)
				state.logProbability$b2 = state.logProbability$var60;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$output = (state.logProbability$output + state.logProbability$var60);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var60);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var60);
		}
	}

	// Calculate the probability of the samples represented by sample72 using sampled
	// values.
	private final void logProbabilityValue$sample72() {
		// Determine if we need to calculate the values for sample task 72 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample72) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var71 = 2; i$var71 < state.length; i$var71 += 3) {
				double var34 = (double)(state.prior[2] / 10);
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.output[i$var71]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				state.logProbability$b3 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				state.logProbability$var72 = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$output = (state.logProbability$output + cv$sampleAccumulator);
			
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
			state.fixedProbFlag$sample72 = state.fixedFlag$sample20;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if((2 < state.length))
				// Record that the sample was reached.
				cv$sampleReached = true;
			if(cv$sampleReached)
				state.logProbability$b3 = state.logProbability$var72;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$output = (state.logProbability$output + state.logProbability$var72);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var72);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var72);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, 10, state.prior);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length, 3,
			(int forStart$i$var47, int forEnd$i$var47, int threadID$i$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var47 = forStart$i$var47; i$var47 < forEnd$i$var47; i$var47 += 3)
						state.output[i$var47] = DistributionSampling.sampleBernoulli(RNG$1, (state.prior[0] / 10));
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.length, 3,
			(int forStart$i$var59, int forEnd$i$var59, int threadID$i$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var59 = forStart$i$var59; i$var59 < forEnd$i$var59; i$var59 += 3)
						state.output[i$var59] = DistributionSampling.sampleBernoulli(RNG$1, (state.prior[1] / 10));
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 2, state.length, 3,
			(int forStart$i$var71, int forEnd$i$var71, int threadID$i$var71, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var71 = forStart$i$var71; i$var71 < forEnd$i$var71; i$var71 += 3)
						state.output[i$var71] = DistributionSampling.sampleBernoulli(RNG$1, (state.prior[2] / 10));
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, 10, state.prior);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, 10, state.prior);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length, 3,
			(int forStart$i$var47, int forEnd$i$var47, int threadID$i$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var47 = forStart$i$var47; i$var47 < forEnd$i$var47; i$var47 += 3)
						state.output[i$var47] = DistributionSampling.sampleBernoulli(RNG$1, (state.prior[0] / 10));
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.length, 3,
			(int forStart$i$var59, int forEnd$i$var59, int threadID$i$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var59 = forStart$i$var59; i$var59 < forEnd$i$var59; i$var59 += 3)
						state.output[i$var59] = DistributionSampling.sampleBernoulli(RNG$1, (state.prior[1] / 10));
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 2, state.length, 3,
			(int forStart$i$var71, int forEnd$i$var71, int threadID$i$var71, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var71 = forStart$i$var71; i$var71 < forEnd$i$var71; i$var71 += 3)
						state.output[i$var71] = DistributionSampling.sampleBernoulli(RNG$1, (state.prior[2] / 10));
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, 10, state.prior);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, 10, state.prior);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample17)
				inferSample17();
			if(!state.fixedFlag$sample20)
				inferSample20();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!state.fixedFlag$sample20)
				inferSample20();
			if(!state.fixedFlag$sample17)
				inferSample17();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample17)
			drawValueSample17();
		if(!state.constrainedFlag$sample20)
			drawValueSample20();
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
		if(!state.fixedProbFlag$sample17)
			state.logProbability$p = Double.NaN;
		if(!state.fixedProbFlag$sample20)
			state.logProbability$prior = Double.NaN;
		state.logProbability$b1 = Double.NaN;
		state.logProbability$output = 0.0;
		if(!state.fixedProbFlag$sample48)
			state.logProbability$var48 = Double.NaN;
		state.logProbability$b2 = Double.NaN;
		if(!state.fixedProbFlag$sample60)
			state.logProbability$var60 = Double.NaN;
		state.logProbability$b3 = Double.NaN;
		if(!state.fixedProbFlag$sample72)
			state.logProbability$var72 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.beta[0] = 0.1;
		state.beta[1] = 0.1;
		state.beta[2] = 0.1;
		state.length = state.length$observed;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample17)
			logProbabilityValue$sample17();
		if(state.fixedFlag$sample20)
			logProbabilityValue$sample20();
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
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
		logProbabilityValue$sample17();
		logProbabilityValue$sample20();
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
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
		logProbabilityValue$sample17();
		logProbabilityValue$sample20();
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.output.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.output[cv$index1] = state.observed[cv$index1];
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
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model MultinomialBernoulli(boolean[] observed) {\n"
		     + "    double[] beta = {0.1, 0.1, 0.1};\n"
		     + "    double[] p = dirichlet(beta).sample();\n"
		     + "    int n = 10;\n"
		     + "    int[] prior = multinomial(p, n).sample();\n"
		     + "    Bernoulli b1 = new Bernoulli(prior[0]/n);\n"
		     + "    Bernoulli b2 = new Bernoulli(prior[1]/n);\n"
		     + "    Bernoulli b3 = new Bernoulli(prior[2]/n);\n"
		     + "    int length = observed.length;\n"
		     + "    boolean[] output = new boolean[length];\n"
		     + "    for(int i=0; i<length; i+=3)\n"
		     + "        output[i] = b1.sample();\n"
		     + "    for(int i=1; i<length; i+=3)\n"
		     + "        output[i] = b2.sample();\n"
		     + "    for(int i=2; i<length; i+=3)\n"
		     + "        output[i] = b3.sample();\n"
		     + "    output.observe(observed);\n"
		     + "}\n"
		     + "";
	}
}