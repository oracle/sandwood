package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip2CoinsMK10$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip2CoinsMK10.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip2CoinsMK10$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {}
	}


	public Flip2CoinsMK10$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample10
	private final void drawValueSample10() {
		state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample23
	private final void drawValueSample23(int i) {
		state.bias[i] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 10 drawn from beta. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample10() {
		state.constrainedFlag$sample10 = false;
		
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
				// j's comment
		// Processing random variable 37.
		// 
		// Looking for a path between Sample 10 and consumer Bernoulli 37.
		if((0 < state.coins)) {
			// Processing sample task 48 of consumer random variable bernoulli.
			// 
			// Substituted "j" with its value "0".
			for(int var47 = 0; var47 < state.shape[0]; var47 += 1) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample10 = true;
				
				// Include the value sampled by task 48 from random variable bernoulli.
				// 
				// Increment the number of samples.
				cv$count = (cv$count + 1);
				
				// If the sample value was positive increase the count
				// 
				// Substituted "j" with its value "0".
				if(state.flips[0][var47])
					cv$sum = (cv$sum + 1);
			}
		}
		if(state.constrainedFlag$sample10)
			// Guards to ensure that bias is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			state.bias[0] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 23 drawn from beta. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample23(int i) {
		state.constrainedFlag$sample23[(i - 1)] = false;
		
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing sample task 48 of consumer random variable bernoulli.
		// 
		// Substituted "j" with its value "i".
		for(int var47 = 0; var47 < state.shape[i]; var47 += 1) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample23[(i - 1)] = true;
			
			// Include the value sampled by task 48 from random variable bernoulli.
			// 
			// Increment the number of samples.
			cv$count = (cv$count + 1);
			
			// If the sample value was positive increase the count
			// 
			// Substituted "j" with its value "i".
			if(state.flips[i][var47])
				cv$sum = (cv$sum + 1);
		}
		if(state.constrainedFlag$sample23[(i - 1)])
			// Guards to ensure that bias is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			state.bias[i] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Calculate the probability of the samples represented by sample10 using sampled
	// values.
	private final void logProbabilityValue$sample10() {
		// Determine if we need to calculate the values for sample task 10 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample10) {
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
			state.logProbability$var10 = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample10)
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
			state.fixedProbFlag$sample10 = state.fixedFlag$sample10;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			state.logProbability$beta = (state.logProbability$beta + state.logProbability$var10);
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var10);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var10);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample10)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var10);
		}
	}

	// Calculate the probability of the samples represented by sample23 using sampled
	// values.
	private final void logProbabilityValue$sample23() {
		// Determine if we need to calculate the values for sample task 23 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample23) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i = 1; i < state.coins; i += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(state.bias[i], 1.0, 1.0));
			state.logProbability$beta = (state.logProbability$beta + cv$sampleAccumulator);
			
			// Store the random variable instance probability
			state.logProbability$var23 = cv$sampleAccumulator;
			
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
			if(state.fixedFlag$sample23)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample23 = state.fixedFlag$sample23;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			state.logProbability$beta = (state.logProbability$beta + state.logProbability$var23);
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var23);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var23);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample23)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var23);
		}
	}

	// Calculate the probability of the samples represented by sample48 using sampled
	// values.
	private final void logProbabilityValue$sample48() {
		// Determine if we need to calculate the values for sample task 48 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample48) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.coins; j += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int var47 = 0; var47 < state.shape[j]; var47 += 1) {
					double var36 = state.bias[j];
					
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
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var36) && (var36 <= 1.0))?Math.log((state.flips[j][var47]?var36:(1.0 - var36))):Double.NEGATIVE_INFINITY));
				}
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				state.logProbability$bernoulli[j] = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				state.logProbability$sample48[j] = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample48 = (state.fixedFlag$sample10 && state.fixedFlag$sample23);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.coins; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = state.logProbability$sample48[j];
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
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample23) {
			for(int i = 1; i < state.coins; i += 1)
				state.bias[i] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int j = 0; j < state.coins; j += 1) {
			boolean[] var38 = state.flips[j];
			for(int var47 = 0; var47 < state.shape[j]; var47 += 1)
				var38[var47] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample23) {
			for(int i = 1; i < state.coins; i += 1)
				state.bias[i] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample23) {
			for(int i = 1; i < state.coins; i += 1)
				state.bias[i] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int j = 0; j < state.coins; j += 1) {
			boolean[] var38 = state.flips[j];
			for(int var47 = 0; var47 < state.shape[j]; var47 += 1)
				var38[var47] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample23) {
			for(int i = 1; i < state.coins; i += 1)
				state.bias[i] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample23) {
			for(int i = 1; i < state.coins; i += 1)
				state.bias[i] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample10)
				inferSample10();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample23) {
				for(int i = 1; i < state.coins; i += 1)
					inferSample23(i);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample23) {
				for(int i = (state.coins - 1); i >= 1; i -= 1)
					inferSample23(i);
			}
			if(!state.fixedFlag$sample10)
				inferSample10();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample10)
			drawValueSample10();
		for(int i = 1; i < state.coins; i += 1) {
			if(!state.constrainedFlag$sample23[(i - 1)])
				drawValueSample23(i);
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
		if(!state.fixedProbFlag$sample10)
			state.logProbability$var10 = Double.NaN;
		if(!state.fixedProbFlag$sample23)
			state.logProbability$var23 = Double.NaN;
		for(int j = 0; j < state.coins; j += 1)
			state.logProbability$bernoulli[j] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample48) {
			for(int j = 0; j < state.coins; j += 1)
				state.logProbability$sample48[j] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.coins = state.shape.length;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample23$1 = 0; index$constrainedFlag$sample23$1 < state.constrainedFlag$sample23.length; index$constrainedFlag$sample23$1 += 1)
			state.constrainedFlag$sample23[index$constrainedFlag$sample23$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample10)
			logProbabilityValue$sample10();
		if(state.fixedFlag$sample23)
			logProbabilityValue$sample23();
		logProbabilityValue$sample48();
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
		logProbabilityValue$sample10();
		logProbabilityValue$sample23();
		logProbabilityValue$sample48();
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
		logProbabilityValue$sample10();
		logProbabilityValue$sample23();
		logProbabilityValue$sample48();
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
		     + "public model Flip2CoinsMK10(boolean[][] flipsMeasured, int[] shape) {\n"
		     + "    int coins = shape.length;\n"
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
		     + "    for(int j:[0..coins)) {\n"
		     + "        int samples = shape[j];\n"
		     + "        Bernoulli bernoulli = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli.sample(samples);\n"
		     + "    }\n"
		     + "        \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}";
	}
}