package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.InjectionAttackTest$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.InjectionAttackTest.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class InjectionAttackTest$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {}
	}


	public InjectionAttackTest$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample6
	private final void drawValueSample6() {
		state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 6 drawn from Beta 5. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample6() {
		state.constrainedFlag$sample6 = false;
		
		// Mark that the sample has observed constrained data.
		state.constrainedFlag$sample6 = true;
		
		// Write out the new value of the sample.
		// 
						// Include the value sampled by task 8 from random variable binomial.
		// 
		// Increment the number of booleans sampled.
		// 
		// Local variable to record the number of samples.
		state.bias = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, state.positiveCount, state.observedSampleCount);
	}

	// Calculate the probability of the samples represented by sample6 using sampled values.
	private final void logProbabilityValue$sample6() {
		// Determine if we need to calculate the values for sample task 6 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample6) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.bias, 1.0, 1.0);
			
			// Store the sample task probability
			state.logProbability$bias = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample6)
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
			state.fixedProbFlag$sample6 = state.fixedFlag$sample6;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$bias);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample6)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$bias);
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBinomial(state.positiveCount, state.bias, state.observedSampleCount);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$binomial = cv$distributionAccumulator;
			
			// Store the sample task probability
			state.logProbability$positiveCount = cv$distributionAccumulator;
			
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
			state.fixedProbFlag$sample8 = state.fixedFlag$sample6;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			state.logProbability$binomial = state.logProbability$positiveCount;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$positiveCount);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$positiveCount);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample6)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		state.positiveCount = DistributionSampling.sampleBinomial(state.RNG$, state.bias, state.observedSampleCount);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample6)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample6)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		state.positiveCount = DistributionSampling.sampleBinomial(state.RNG$, state.bias, state.observedSampleCount);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample6)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample6)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample6)
			inferSample6();
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample6)
			drawValueSample6();
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
		if(!state.fixedProbFlag$sample6)
			state.logProbability$bias = Double.NaN;
		state.logProbability$binomial = 0.0;
		if(!state.fixedProbFlag$sample8)
			state.logProbability$positiveCount = Double.NaN;
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
		if(state.fixedFlag$sample6)
			logProbabilityValue$sample6();
		logProbabilityValue$sample8();
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
		logProbabilityValue$sample6();
		logProbabilityValue$sample8();
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
		logProbabilityValue$sample6();
		logProbabilityValue$sample8();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		state.positiveCount = state.observedPositiveCount;
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
		     + " \n"
		     + " /** \" + new java.util.Random().nextDouble() + \" */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "/**\n"
		     + " * A model for the fairness work. \" + new java.util.Random().nextDouble() + \"\n"
		     + " */\n"
		     + "public model InjectionAttackTest(/** The number of observed samples \" + new java.util.Random().nextDouble() + \"*/int observedSampleCount, int observedPositiveCount)  {\n"
		     + "    // \" + new java.util.Random().nextDouble() + \"\n"
		     + "    /** a bias to see how like values are to be collected. \" + new java.util.Random().nextDouble() + \"*/    \n"
		     + "    double bias = beta(1.0, 1.0).sample();\n"
		     + "        \n"
		     + "    //Construct a binomial\n"
		     + "    /** A binomial distribution for the tests. \" + new java.util.Random().nextDouble() + \" */\n"
		     + "    Binomial binomial = binomial(bias, observedSampleCount);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    int positiveCount = binomial.sample();\n"
		     + "        \n"
		     + "    //Link the sampled values to the observed values\n"
		     + "    positiveCount.observe(observedPositiveCount);\n"
		     + "}";
	}
}