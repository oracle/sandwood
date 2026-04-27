package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK16$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK16.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK16$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK16$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample14
	private final void drawValueSample14() {
		state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 14 drawn from Beta 11. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample14() {
		state.constrainedFlag$sample14 = false;
		
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Mark that the sample has observed constrained data.
		state.constrainedFlag$sample14 = true;
		
		// If the sample value was positive increase the count
		if(state.flip)
			// Local variable to record the number of true samples.
			cv$sum = 1;
		
		// Write out the new value of the sample.
		// 
		// Include the value sampled by task 16 from random variable bernoulli.
		// 
		// Increment the number of samples.
		// 
		// Local variable to record the number of samples.
		state.bias = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, 1);
	}

	// Calculate the probability of the samples represented by sample14 using sampled
	// values.
	private final void logProbabilityValue$sample14() {
		// Generating probabilities for sample task
		// Accumulator for sample probabilities for a specific instance of the random variable.
		double cv$sampleAccumulator = 0.0;
		
		// A guard to check if the sample value is ever reached.
		boolean cv$sampleReached = false;
		if(Double.isNaN(state.guard)) {
			// Record that the sample was reached.
			cv$sampleReached = true;
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
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
			cv$sampleAccumulator = DistributionSampling.logProbabilityBeta(state.bias, 1.0, 1.0);
		}
		
		// Only update the sample if it was reached, otherwise the NaN will be
		// erroneously over written.
		if(cv$sampleReached)
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$bias = cv$sampleAccumulator;
		
		// Add probability to model
		// 
		// Add the probability of this instance of the random variable to the probability
		// of all instances of the random variable.
		// 
		// Accumulator for probabilities of instances of the random variable
		state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
	}

	// Calculate the probability of the samples represented by sample16 using sampled
	// values.
	private final void logProbabilityValue$sample16() {
		// Generating probabilities for sample task
		// Accumulator for sample probabilities for a specific instance of the random variable.
		double cv$sampleAccumulator = 0.0;
		
		// A guard to check if the sample value is ever reached.
		boolean cv$sampleReached = false;
		if(Double.isNaN(state.guard)) {
			// Record that the sample was reached.
			cv$sampleReached = true;
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
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
			cv$sampleAccumulator = (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((state.flip?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(cv$sampleReached) {
			state.logProbability$bernoulli = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$flip = cv$sampleAccumulator;
		}
		
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
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(Double.isNaN(state.guard)) {
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.flip = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(Double.isNaN(state.guard))
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(Double.isNaN(state.guard)) {
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.flip = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(Double.isNaN(state.guard))
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(Double.isNaN(state.guard))
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(Double.isNaN(state.guard))
			inferSample14();
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if((Double.isNaN(state.guard) && !state.constrainedFlag$sample14))
			drawValueSample14();
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
		state.logProbability$bias = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flip = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		logProbabilityValue$sample16();
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
		logProbabilityValue$sample14();
		logProbabilityValue$sample16();
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
		logProbabilityValue$sample14();
		logProbabilityValue$sample16();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(Double.isNaN(state.guard))
			state.flip = state.flipMeasured;
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
		     + "public model Flip1CoinMK16(boolean flipMeasured, double guard) {\n"
		     + "    if(isNaN(guard)) {\n"
		     + "        double bias = beta(1.0, 1.0).sample();\n"
		     + "        Bernoulli bernoulli = bernoulli(bias);\n"
		     + "        boolean flip = bernoulli.sample();\n"
		     + "        flip.observe(flipMeasured);\n"
		     + "    }\n"
		     + "}\n"
		     + "";
	}
}