package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip2CoinsMK7$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip2CoinsMK7.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip2CoinsMK7$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {}
	}


	public Flip2CoinsMK7$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample18
	private final void drawValueSample18(int i) {
		state.bias[i] = (1 - DistributionSampling.sampleBeta(state.RNG$, state.a, state.b));
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 18 drawn from Beta 17. Inference was performed using Metropolis-Hastings.
	private final void inferSample18(int i) {
		state.constrainedFlag$sample18[i] = false;
		
		// The original value of the sample
		double cv$originalValue = (1 - state.bias[i]);
		
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
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$originalValue, state.a, state.b);
			
			// Looking for a path between Sample 18 and consumer Bernoulli 35.
			// 
			// Set the current value to the current state of the tree.
			double traceTempVariable$var34$2_1 = (1 - cv$originalValue);
			
			// Processing sample task 46 of consumer random variable bernoulli.
			// 
			// Substituted "j" with its value "i".
			for(int var45 = 0; var45 < state.length$flipsMeasured[i]; var45 += 1) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample18[i] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 46 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "j" with its value "i".
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var34$2_1) && (traceTempVariable$var34$2_1 <= 1.0))?Math.log((state.flips[i][var45]?traceTempVariable$var34$2_1:(1.0 - traceTempVariable$var34$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
		if(state.constrainedFlag$sample18[i]) {
			// Guards to ensure that bias is only updated when there is a valid path.
			state.bias[i] = (1 - cv$proposedValue);
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, state.a, state.b);
			
			// Looking for a path between Sample 18 and consumer Bernoulli 35.
			double traceTempVariable$var34$2_1 = (1 - cv$proposedValue);
			
			// Processing sample task 46 of consumer random variable bernoulli.
			// 
			// Substituted "j" with its value "i".
			for(int var45 = 0; var45 < state.length$flipsMeasured[i]; var45 += 1) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample18[i] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 46 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "j" with its value "i".
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var34$2_1) && (traceTempVariable$var34$2_1 <= 1.0))?Math.log((state.flips[i][var45]?traceTempVariable$var34$2_1:(1.0 - traceTempVariable$var34$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Guards to ensure that bias is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				state.bias[i] = (1 - cv$originalValue);
		}
	}

	// Calculate the probability of the samples represented by sample18 using sampled
	// values.
	private final void logProbabilityValue$sample18() {
		// Determine if we need to calculate the values for sample task 18 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample18) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.coins; i += 1) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta((1 - state.bias[i]), state.a, state.b);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample18[i] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample18)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample18 = state.fixedFlag$sample18;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.coins; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample18[i]);
			
			// Update the variable probability
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample18)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample46 using sampled
	// values.
	private final void logProbabilityValue$sample46() {
		// Determine if we need to calculate the values for sample task 46 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample46) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.coins; j += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int var45 = 0; var45 < state.length$flipsMeasured[j]; var45 += 1) {
					double var34 = state.bias[j];
					
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
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.flips[j][var45]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY));
				}
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				state.logProbability$bernoulli[j] = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				state.logProbability$sample46[j] = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample46 = state.fixedFlag$sample18;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.coins; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = state.logProbability$sample46[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$bernoulli[j] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample18) {
			for(int i = 0; i < state.coins; i += 1)
				state.bias[i] = (1 - DistributionSampling.sampleBeta(state.RNG$, state.a, state.b));
		}
		for(int j = 0; j < state.coins; j += 1) {
			boolean[] var36 = state.flips[j];
			for(int var45 = 0; var45 < state.length$flipsMeasured[j]; var45 += 1)
				var36[var45] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample18) {
			for(int i = 0; i < state.coins; i += 1)
				state.bias[i] = (1 - DistributionSampling.sampleBeta(state.RNG$, state.a, state.b));
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample18) {
			for(int i = 0; i < state.coins; i += 1)
				state.bias[i] = (1 - DistributionSampling.sampleBeta(state.RNG$, state.a, state.b));
		}
		for(int j = 0; j < state.coins; j += 1) {
			boolean[] var36 = state.flips[j];
			for(int var45 = 0; var45 < state.length$flipsMeasured[j]; var45 += 1)
				var36[var45] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample18) {
			for(int i = 0; i < state.coins; i += 1)
				state.bias[i] = (1 - DistributionSampling.sampleBeta(state.RNG$, state.a, state.b));
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample18) {
			for(int i = 0; i < state.coins; i += 1)
				state.bias[i] = (1 - DistributionSampling.sampleBeta(state.RNG$, state.a, state.b));
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(!state.fixedFlag$sample18) {
			// Infer the samples in chronological order.
			if(state.system$gibbsForward) {
				for(int i = 0; i < state.coins; i += 1)
					inferSample18(i);
			}
			// Infer the samples in reverse chronological order.
			else {
				for(int i = (state.coins - 1); i >= 0; i -= 1)
					inferSample18(i);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int i = 0; i < state.coins; i += 1) {
			if(!state.constrainedFlag$sample18[i])
				drawValueSample18(i);
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
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample18) {
			for(int i = 0; i < state.coins; i += 1)
				state.logProbability$sample18[i] = Double.NaN;
		}
		for(int j = 0; j < state.coins; j += 1)
			state.logProbability$bernoulli[j] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample46) {
			for(int j = 0; j < state.coins; j += 1)
				state.logProbability$sample46[j] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.coins = state.length$flipsMeasured.length;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample18$1 = 0; index$constrainedFlag$sample18$1 < state.constrainedFlag$sample18.length; index$constrainedFlag$sample18$1 += 1)
			state.constrainedFlag$sample18[index$constrainedFlag$sample18$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample18)
			logProbabilityValue$sample18();
		logProbabilityValue$sample46();
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
		logProbabilityValue$sample18();
		logProbabilityValue$sample46();
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
		logProbabilityValue$sample18();
		logProbabilityValue$sample46();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = state.flipsMeasured[cv$index1];
			boolean[] cv$target2 = state.flips[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
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
		     + "public model Flip2CoinsMK7(double a, double b, boolean[][] flipsMeasured) {\n"
		     + "     \n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "    double[] bias = new double[coins];\n"
		     + "    for(int i:[0..coins)) \n"
		     + "        bias[i] = 1 - beta(a, b).sample();\n"
		     + "                \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "        \n"
		     + "    for(int j:[0..coins)) {\n"
		     + "        int samples = flipsMeasured[j].length;\n"
		     + "        Bernoulli bernoulli = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli.sample(samples);\n"
		     + "    }\n"
		     + "\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}