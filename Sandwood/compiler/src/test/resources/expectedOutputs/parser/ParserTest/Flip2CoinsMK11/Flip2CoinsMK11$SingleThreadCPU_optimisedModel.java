package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip2CoinsMK11$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip2CoinsMK11.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip2CoinsMK11$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {}
	}


	public Flip2CoinsMK11$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample22
	private final void drawValueSample22(int i$var21) {
		state.bias[i$var21] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample9
	private final void drawValueSample9() {
		state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 22 drawn from beta. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample22(int i$var21) {
		state.constrainedFlag$sample22[(i$var21 - 1)] = false;
		
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing sample task 77 of consumer random variable bernoulli2.
		// 
		// Substituted "k" with its value "i$var21".
		for(int var75 = 0; var75 < state.length$flipsMeasured[i$var21]; var75 += 1) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample22[(i$var21 - 1)] = true;
			
			// Include the value sampled by task 77 from random variable bernoulli2.
			// 
			// Increment the number of samples.
			cv$count = (cv$count + 1);
			
			// If the sample value was positive increase the count
			// 
			// Substituted "k" with its value "i$var21".
			if(state.flips[i$var21][var75])
				cv$sum = (cv$sum + 1);
		}
		if(state.constrainedFlag$sample22[(i$var21 - 1)])
			// Guards to ensure that bias is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			state.bias[i$var21] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 9 drawn from beta. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample9() {
		state.constrainedFlag$sample9 = false;
		
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing sample task 49 of consumer random variable bernoulli1.
		// 
		// Substituted "j" with its value "0".
		for(int var48 = 0; var48 < state.length$flipsMeasured[0]; var48 += 1) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample9 = true;
			
			// Include the value sampled by task 49 from random variable bernoulli1.
			// 
			// Increment the number of samples.
			cv$count = (cv$count + 1);
			
			// If the sample value was positive increase the count
			// 
			// Substituted "j" with its value "0".
			if(state.flips[0][var48])
				cv$sum = (cv$sum + 1);
		}
		if(state.constrainedFlag$sample9)
			// Guards to ensure that bias is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			state.bias[0] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Calculate the probability of the samples represented by sample22 using sampled
	// values.
	private final void logProbabilityValue$sample22() {
		// Determine if we need to calculate the values for sample task 22 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample22) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(state.bias[i$var21], 1.0, 1.0));
			state.logProbability$beta = (state.logProbability$beta + cv$sampleAccumulator);
			
			// Store the random variable instance probability
			state.logProbability$var22 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$bias = (state.logProbability$bias + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample22)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample22 = state.fixedFlag$sample22;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			state.logProbability$beta = (state.logProbability$beta + state.logProbability$var22);
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var22);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var22);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample22)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var22);
		}
	}

	// Calculate the probability of the samples represented by sample49 using sampled
	// values.
	private final void logProbabilityValue$sample49() {
		// Determine if we need to calculate the values for sample task 49 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample49) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// Substituted "j" with its value "0".
			for(int var48 = 0; var48 < state.length$flipsMeasured[0]; var48 += 1) {
				// Substituted "j" with its value "0".
				double var37 = state.bias[0];
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				// 
				// Substituted "j" with its value "0".
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var37) && (var37 <= 1.0))?Math.log((state.flips[0][var48]?var37:(1.0 - var37))):Double.NEGATIVE_INFINITY));
			}
			
			// Substituted "j" with its value "0".
			state.logProbability$bernoulli1[0] = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Substituted "j" with its value "0".
			state.logProbability$sample49[0] = cv$sampleAccumulator;
			
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
			state.fixedProbFlag$sample49 = (state.fixedFlag$sample9 && state.fixedFlag$sample22);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Variable declaration of cv$rvAccumulator moved.
			// 
			// Substituted "j" with its value "0".
			double cv$rvAccumulator = state.logProbability$sample49[0];
			
			// Substituted "j" with its value "0".
			state.logProbability$bernoulli1[0] = cv$rvAccumulator;
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample77 using sampled
	// values.
	private final void logProbabilityValue$sample77() {
		// Determine if we need to calculate the values for sample task 77 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample77) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int k = 1; k < state.coins; k += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int var75 = 0; var75 < state.length$flipsMeasured[k]; var75 += 1) {
					double var64 = state.bias[k];
					
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
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var64) && (var64 <= 1.0))?Math.log((state.flips[k][var75]?var64:(1.0 - var64))):Double.NEGATIVE_INFINITY));
				}
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				state.logProbability$bernoulli2[(k - 1)] = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				state.logProbability$sample77[(k - 1)] = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample77 = (state.fixedFlag$sample9 && state.fixedFlag$sample22);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int k = 1; k < state.coins; k += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = state.logProbability$sample77[(k - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$bernoulli2[(k - 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample9 using sampled values.
	private final void logProbabilityValue$sample9() {
		// Determine if we need to calculate the values for sample task 9 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample9) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.bias[0], 1.0, 1.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$beta = (state.logProbability$beta + cv$distributionAccumulator);
			
			// Store the sample task probability
			state.logProbability$var9 = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample9)
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
			state.fixedProbFlag$sample9 = state.fixedFlag$sample9;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			state.logProbability$beta = (state.logProbability$beta + state.logProbability$var9);
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var9);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var9);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample9)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var9);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample9)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample22) {
			for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1)
				state.bias[i$var21] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		
		// Substituted "j" with its value "0".
		boolean[] var39 = state.flips[0];
		
		// Substituted "j" with its value "0".
		for(int var48 = 0; var48 < state.length$flipsMeasured[0]; var48 += 1)
			// Substituted "j" with its value "0".
			var39[var48] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[0]);
		for(int k = 1; k < state.coins; k += 1) {
			boolean[] var66 = state.flips[k];
			for(int var75 = 0; var75 < state.length$flipsMeasured[k]; var75 += 1)
				var66[var75] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[k]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample22) {
			for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1)
				state.bias[i$var21] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample9)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample22) {
			for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1)
				state.bias[i$var21] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		
		// Substituted "j" with its value "0".
		boolean[] var39 = state.flips[0];
		
		// Substituted "j" with its value "0".
		for(int var48 = 0; var48 < state.length$flipsMeasured[0]; var48 += 1)
			// Substituted "j" with its value "0".
			var39[var48] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[0]);
		for(int k = 1; k < state.coins; k += 1) {
			boolean[] var66 = state.flips[k];
			for(int var75 = 0; var75 < state.length$flipsMeasured[k]; var75 += 1)
				var66[var75] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[k]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample9)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample22) {
			for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1)
				state.bias[i$var21] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample22) {
			for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1)
				state.bias[i$var21] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample9)
				inferSample9();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample22) {
				for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1)
					inferSample22(i$var21);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample22) {
				for(int i$var21 = (state.coins - 1); i$var21 >= 1; i$var21 -= 1)
					inferSample22(i$var21);
			}
			if(!state.fixedFlag$sample9)
				inferSample9();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample9)
			drawValueSample9();
		for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1) {
			if(!state.constrainedFlag$sample22[(i$var21 - 1)])
				drawValueSample22(i$var21);
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
		state.logProbability$beta = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample9)
			state.logProbability$var9 = Double.NaN;
		if(!state.fixedProbFlag$sample22)
			state.logProbability$var22 = Double.NaN;
		
		// Unrolled loop
		// 
		// Substituted "j" with its value "0".
		state.logProbability$bernoulli1[0] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample49)
			// Unrolled loop
			// 
			// Substituted "j" with its value "0".
			state.logProbability$sample49[0] = Double.NaN;
		for(int k = 1; k < state.coins; k += 1)
			state.logProbability$bernoulli2[(k - 1)] = Double.NaN;
		if(!state.fixedProbFlag$sample77) {
			for(int k = 1; k < state.coins; k += 1)
				state.logProbability$sample77[(k - 1)] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.coins = state.length$flipsMeasured.length;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample22$1 = 0; index$constrainedFlag$sample22$1 < state.constrainedFlag$sample22.length; index$constrainedFlag$sample22$1 += 1)
			state.constrainedFlag$sample22[index$constrainedFlag$sample22$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample9)
			logProbabilityValue$sample9();
		if(state.fixedFlag$sample22)
			logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
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
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
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
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		for(int i$var88 = (state.coins - 1); i$var88 >= 0; i$var88 -= 1) {
			// Deep copy between arrays
			boolean[] cv$source1 = state.flipsMeasured[(state.coins - (i$var88 + 1))];
			boolean[] cv$target1 = state.flips[i$var88];
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
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
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Flip2CoinsMK11(boolean[][] flipsMeasured) {\n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "         \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "    double [] bias = new double[coins];\n"
		     + "        \n"
		     + "    Beta beta = beta(1.0, 1.0);\n"
		     + "        \n"
		     + "    bias[0] = beta.sample();\n"
		     + "        \n"
		     + "    for(int i:[1..coins))\n"
		     + "        bias[i] = beta.sample();\n"
		     + "        \n"
		     + "    for(int j:[0..1)) {\n"
		     + "        int samples = flipsMeasured[j].length;\n"
		     + "        Bernoulli bernoulli1 = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli1.sample(samples);\n"
		     + "    }\n"
		     + "                \n"
		     + "    for(int k:[1..coins)) {\n"
		     + "        int samples = flipsMeasured[k].length;\n"
		     + "        Bernoulli bernoulli2 = bernoulli(bias[k]);\n"
		     + "        flips[k] = bernoulli2.sample(samples);\n"
		     + "    }\n"
		     + "        \n"
		     + "    for(int i:[0..coins)) {\n"
		     + "        boolean[] f = flips[i];\n"
		     + "        boolean[] m = flipsMeasured[coins - (i+1)];\n"
		     + "        f.observe(m);\n"
		     + "    }\n"
		     + "}";
	}
}