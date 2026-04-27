package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK14$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK14.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK14$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK14$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample8
	private final void drawValueSample8() {
		state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		// Guards to ensure that bias is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 8 and consumer double 22.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.guard1)
			state.bias = state.b;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			state.c[0] = (state.b / 2);
			state.bias = state.c[0];
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 8 drawn from Beta 7. Inference was performed using Metropolis-Hastings.
	private final void inferSample8() {
		state.constrainedFlag$sample8 = false;
		
		// The original value of the sample
		double cv$originalValue = state.b;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		// 
						// The original value of the sample
		double cv$var = ((state.b * state.b) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		// 
		// The original value of the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			// 
			// The original value of the sample
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(state.b, 1.0, 1.0);
			
			// Looking for a path between Sample 8 and consumer Bernoulli 23.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.guard1) {
				// Processing sample task 37 of consumer random variable bernoulli.
				for(int var34 = 0; var34 < state.samples; var34 += 1) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample8 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 37 with the current configuration.
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
					// 
					// The original value of the sample
					cv$accumulatedProbabilities = ((((0.0 <= state.b) && (state.b <= 1.0))?Math.log((state.flips[var34]?state.b:(1.0 - state.b))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// Set the current value to the current state of the tree.
				// 
				// The original value of the sample
				double traceTempVariable$var21$5_2 = (state.b / 2);
				
				// Processing sample task 37 of consumer random variable bernoulli.
				for(int var34 = 0; var34 < state.samples; var34 += 1) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample8 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 37 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var21$5_2) && (traceTempVariable$var21$5_2 <= 1.0))?Math.log((state.flips[var34]?traceTempVariable$var21$5_2:(1.0 - traceTempVariable$var21$5_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
		if(state.constrainedFlag$sample8) {
			// Update Sample and intermediate values
			// 
			// Write out the new value of the sample.
			state.b = cv$proposedValue;
			
			// Guards to ensure that bias is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 8 and consumer double 22.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.guard1)
				state.bias = cv$proposedValue;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				state.c[0] = (cv$proposedValue / 2);
				state.bias = state.c[0];
			}
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			
			// Looking for a path between Sample 8 and consumer Bernoulli 23.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.guard1) {
				// Processing sample task 37 of consumer random variable bernoulli.
				for(int var34 = 0; var34 < state.samples; var34 += 1) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample8 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 37 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((state.flips[var34]?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				double traceTempVariable$var21$5_2 = (cv$proposedValue / 2);
				
				// Processing sample task 37 of consumer random variable bernoulli.
				for(int var34 = 0; var34 < state.samples; var34 += 1) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample8 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 37 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var21$5_2) && (traceTempVariable$var21$5_2 <= 1.0))?Math.log((state.flips[var34]?traceTempVariable$var21$5_2:(1.0 - traceTempVariable$var21$5_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
				// Write out the new value of the sample.
				state.b = cv$originalValue;
				
				// Guards to ensure that bias is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 8 and consumer double 22.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(state.guard1)
										// b's comment
					// Write out the new value of the sample.
					state.bias = cv$originalValue;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
										// b's comment
					// Write out the new value of the sample.
					state.c[0] = (cv$originalValue / 2);
					state.bias = state.c[0];
				}
			}
		}
	}

	// Calculate the probability of the samples represented by sample37 using sampled
	// values.
	private final void logProbabilityValue$sample37() {
		// Determine if we need to calculate the values for sample task 37 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample37) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var34 = 0; var34 < state.samples; var34 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((state.flips[var34]?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY));
			state.logProbability$bernoulli = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			state.logProbability$var35 = cv$sampleAccumulator;
			
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
			state.fixedProbFlag$sample37 = state.fixedFlag$sample8;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			state.logProbability$bernoulli = state.logProbability$var35;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$flips = (state.logProbability$flips + state.logProbability$var35);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var35);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var35);
		}
	}

	// Calculate the probability of the samples represented by sample8 using sampled values.
	private final void logProbabilityValue$sample8() {
		// Determine if we need to calculate the values for sample task 8 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample8) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.b, 1.0, 1.0);
			
			// Store the sample task probability
			state.logProbability$b = cv$distributionAccumulator;
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Add probability to constructed variables from the combined probability
			// 
			// Looking for a path between Sample 8 and consumer double 22.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.guard1) {
				// Set the guard so the update is only applied once.
				cv$guard$bias = true;
				
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
				state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!state.guard1 && !cv$guard$bias))
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
				state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			
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
			if(state.fixedFlag$sample8)
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
			state.fixedProbFlag$sample8 = state.fixedFlag$sample8;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Add probability to constructed variables from the combined probability
			// 
			// Looking for a path between Sample 8 and consumer double 22.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.guard1) {
				// Set the guard so the update is only applied once.
				cv$guard$bias = true;
				
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				state.logProbability$bias = (state.logProbability$bias + state.logProbability$b);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!state.guard1 && !cv$guard$bias))
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				state.logProbability$bias = (state.logProbability$bias + state.logProbability$b);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$b);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample8)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$b);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample8) {
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(state.guard1)
				state.bias = state.b;
			else {
				state.c[0] = (state.b / 2);
				state.bias = state.c[0];
			}
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var34, int forEnd$var34, int threadID$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var34 = forStart$var34; var34 < forEnd$var34; var34 += 1)
						state.flips[var34] = DistributionSampling.sampleBernoulli(RNG$1, state.bias);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample8)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample8)
				state.bias = state.b;
		} else {
			state.c[0] = (state.b / 2);
			state.bias = state.c[0];
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample8)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample8)
				state.bias = state.b;
		} else {
			state.c[0] = (state.b / 2);
			state.bias = state.c[0];
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var34, int forEnd$var34, int threadID$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var34 = forStart$var34; var34 < forEnd$var34; var34 += 1)
						state.flips[var34] = DistributionSampling.sampleBernoulli(RNG$1, state.bias);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample8) {
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(state.guard1)
				state.bias = state.b;
			else {
				state.c[0] = (state.b / 2);
				state.bias = state.c[0];
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample8)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample8)
				state.bias = state.b;
		} else {
			state.c[0] = (state.b / 2);
			state.bias = state.c[0];
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample8)
			inferSample8();
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample8)
			drawValueSample8();
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
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample8)
			state.logProbability$b = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample37)
			state.logProbability$var35 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.samples = state.length$flipsMeasured;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample8)
			logProbabilityValue$sample8();
		logProbabilityValue$sample37();
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
		logProbabilityValue$sample8();
		logProbabilityValue$sample37();
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
		logProbabilityValue$sample8();
		logProbabilityValue$sample37();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		for(int i = (state.samples - 1); i >= 0; i -= 1)
			state.flips[i] = state.flipsMeasured[i];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		if(state.guard1) {
			if(state.fixedFlag$sample8)
				state.bias = state.b;
		} else {
			state.c[0] = (state.b / 2);
			state.bias = state.c[0];
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
		     + "public model Flip1CoinMK14(boolean[] flipsMeasured, boolean guard1) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double b = beta(1.0, 1).sample();\n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = b;\n"
		     + "    else {\n"
		     + "      double[] c = new double[1];\n"
		     + "      c[0] = b/2;\n"
		     + "      bias = c[0];\n"
		     + "    }\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "\n"
		     + "    for(int i:[0..samples))\n"
		     + "        flips[i].observe(flipsMeasured[i]);\n"
		     + "}\n"
		     + "";
	}
}