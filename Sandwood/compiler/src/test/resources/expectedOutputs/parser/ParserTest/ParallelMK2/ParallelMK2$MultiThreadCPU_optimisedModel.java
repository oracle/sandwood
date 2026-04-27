package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.ParallelMK2$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.ParallelMK2.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ParallelMK2$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		boolean[] guard$sample26gaussian31$global;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// 
			// Constructor for guard$sample26gaussian31$global
			// 
			// Allocation of guard$sample26gaussian31$global for single threaded execution
			guard$sample26gaussian31$global = new boolean[state.length$observed];
		}
	}


	public ParallelMK2$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample26
	private final void drawValueSample26(int i) {
		state.sample[i] = DistributionSampling.sampleUniform(state.RNG$);
		
		// Guards to ensure that indirection is only updated when there is a valid path.
		state.indirection[(i + 1)] = state.sample[i];
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 26 drawn from Uniform 25. Inference was performed using Metropolis-Hastings.
	private final void inferSample26(int i) {
		state.constrainedFlag$sample26[i] = false;
		
		// The original value of the sample
		double cv$originalValue = state.sample[i];
		
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
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			scratch.guard$sample26gaussian31$global[i] = false;
			
			// Substituted "index$i$3_1" with its value "(i + 1)".
			if((i < (state.length$observed - 1)))
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample26gaussian31$global[i] = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!scratch.guard$sample26gaussian31$global[i]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample26gaussian31$global[i] = true;
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample26[i] = true;
				
				// Constructing a random variable input for use later.
				double var30 = state.indirection[i];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 32 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Set the current value to the current state of the tree.
				cv$accumulatedProbabilities = (((0.0 < var30)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - cv$originalValue) / Math.sqrt(var30))) - (Math.log(var30) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			int index$i$5_2 = (i + 1);
			if(((index$i$5_2 < state.length$observed) && !scratch.guard$sample26gaussian31$global[i])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample26gaussian31$global[i] = true;
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample26[i] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 32 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Set the current value to the current state of the tree.
				cv$accumulatedProbabilities = (((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.generated[index$i$5_2] - cv$originalValue) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
		if(state.constrainedFlag$sample26[i]) {
			// Update Sample and intermediate values
			// 
			// Write out the new value of the sample.
			state.sample[i] = cv$proposedValue;
			
			// Guards to ensure that indirection is only updated when there is a valid path.
			state.indirection[(i + 1)] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			scratch.guard$sample26gaussian31$global[i] = false;
			
			// Substituted "index$i$3_1" with its value "(i + 1)".
			if((i < (state.length$observed - 1)))
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample26gaussian31$global[i] = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!scratch.guard$sample26gaussian31$global[i]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample26gaussian31$global[i] = true;
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample26[i] = true;
				
				// Constructing a random variable input for use later.
				double var30 = state.indirection[i];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 32 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = (((0.0 < var30)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - cv$proposedValue) / Math.sqrt(var30))) - (Math.log(var30) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			int index$i$5_2 = (i + 1);
			if(((index$i$5_2 < state.length$observed) && !scratch.guard$sample26gaussian31$global[i])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample26gaussian31$global[i] = true;
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample26[i] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 32 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = (((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.generated[index$i$5_2] - cv$proposedValue) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
				// Write out the new value of the sample.
				state.sample[i] = cv$originalValue;
				
				// Guards to ensure that indirection is only updated when there is a valid path.
				state.indirection[(i + 1)] = state.sample[i];
			}
		}
	}

	// Calculate the probability of the samples represented by sample26 using sampled
	// values.
	private final void logProbabilityValue$sample26() {
		// Determine if we need to calculate the values for sample task 26 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample26) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.length$observed; i += 1) {
				// The sample value to calculate the probability of generating
				double cv$sampleValue = state.sample[i];
				
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
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 1.0))?0.0:Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample26[i] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$sample = (state.logProbability$sample + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$indirection = (state.logProbability$indirection + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample26)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample26 = state.fixedFlag$sample26;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.length$observed; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample26[i]);
			
			// Update the variable probability
			state.logProbability$sample = (state.logProbability$sample + cv$accumulator);
			
			// Update the variable probability
			state.logProbability$indirection = (state.logProbability$indirection + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample26)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample32 using sampled
	// values.
	private final void logProbabilityValue$sample32() {
		// Determine if we need to calculate the values for sample task 32 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample32) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.length$observed; i += 1) {
				double var30 = state.indirection[i];
				
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
				double cv$distributionAccumulator = ((0.0 < var30)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - state.sample[i]) / Math.sqrt(var30))) - (Math.log(var30) * 0.5)):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample32[i] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$generated = (state.logProbability$generated + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample32 = state.fixedFlag$sample26;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.length$observed; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample32[i]);
			
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
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i = forStart$i; i < forEnd$i; i += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample26) {
							state.sample[i] = DistributionSampling.sampleUniform(RNG$1);
							state.indirection[(i + 1)] = state.sample[i];
						}
						state.generated[i] = ((Math.sqrt(state.indirection[i]) * DistributionSampling.sampleGaussian(RNG$1)) + state.sample[i]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.fixedFlag$sample26)
							state.sample[i] = DistributionSampling.sampleUniform(RNG$1);
						state.indirection[(i + 1)] = state.sample[i];
					}
			}
		);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.fixedFlag$sample26)
							state.sample[i] = DistributionSampling.sampleUniform(RNG$1);
						state.indirection[(i + 1)] = state.sample[i];
						state.generated[i] = ((Math.sqrt(state.indirection[i]) * DistributionSampling.sampleGaussian(RNG$1)) + state.sample[i]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample26)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							state.sample[i] = DistributionSampling.sampleUniform(RNG$1);
							state.indirection[(i + 1)] = state.sample[i];
						}
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.fixedFlag$sample26)
							state.sample[i] = DistributionSampling.sampleUniform(RNG$1);
						state.indirection[(i + 1)] = state.sample[i];
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
				for(int i = 0; i < state.length$observed; i += 1)
					inferSample26(i);
			}
			// Infer the samples in reverse chronological order.
			else {
				for(int i = (state.length$observed - 1); i >= 0; i -= 1)
					inferSample26(i);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int i = 0; i < state.length$observed; i += 1) {
			if(!state.constrainedFlag$sample26[i])
				drawValueSample26(i);
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
		state.logProbability$sample = 0.0;
		state.logProbability$indirection = 0.0;
		if(!state.fixedProbFlag$sample26) {
			for(int i = 0; i < state.length$observed; i += 1)
				state.logProbability$sample26[i] = Double.NaN;
		}
		state.logProbability$generated = 0.0;
		if(!state.fixedProbFlag$sample32) {
			for(int i = 0; i < state.length$observed; i += 1)
				state.logProbability$sample32[i] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.indirection[0] = 1.0;
		
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
		logProbabilityValue$sample32();
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
		logProbabilityValue$sample32();
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
		logProbabilityValue$sample32();
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
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i = forStart$i; i < forEnd$i; i += 1)
						state.indirection[(i + 1)] = state.sample[i];
			}
		);
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
		     + "public model ParallelMK2(double[] observed) {\n"
		     + "    double[] generated = new double[observed.length];\n"
		     + "    double[] indirection = new double[observed.length + 1];\n"
		     + "    indirection[0] = 1.0;\n"
		     + "\n"
		     + "    for(int i=0; i<observed.length; i++) {\n"
		     + "        double sample = uniform(0.0, 1.0).sample();\n"
		     + "        indirection[i + 1] = sample;\n"
		     + "        generated[i] = gaussian(sample, indirection[i]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    generated.observe(observed);\n"
		     + "}";
	}
}