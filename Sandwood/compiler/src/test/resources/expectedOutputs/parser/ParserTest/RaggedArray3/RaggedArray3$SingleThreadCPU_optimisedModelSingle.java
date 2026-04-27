package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.RaggedArray3$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.RaggedArray3.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray3$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var37$countGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// 
			// Constructor for cv$var37$countGlobal
			// 
			// Allocation of cv$var37$countGlobal for single threaded execution
			// 
			// Test if the input to putTask 35 is larger than the current values.
			cv$var37$countGlobal = new double[3];
		}
	}


	public RaggedArray3$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample39
	private final void drawValueSample39() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_3 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.y))
			lengthCV$a$37_3 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.y))
			lengthCV$a$37_3 = 3;
		DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_3, state.d);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 39 drawn from Dirichlet 36. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample39() {
		state.constrainedFlag$sample39 = false;
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_1 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.y))
			lengthCV$a$37_1 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.y))
			lengthCV$a$37_1 = 3;
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < lengthCV$a$37_1; cv$loopIndex += 1)
			// A local reference to the scratch space.
			scratch.cv$var37$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 38.
		// 
		// Processing sample task 53 of consumer random variable null.
		for(int var50 = 0; var50 < state.length$obs_measured; var50 += 1) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample39 = true;
			
			// Increment the sample counter with the value sampled by sample task 53 of random
			// variable var38
			// 
									// A local reference to the scratch space.
			scratch.cv$var37$countGlobal[state.obs[var50]] = (scratch.cv$var37$countGlobal[state.obs[var50]] + 1.0);
		}
		if(state.constrainedFlag$sample39) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_2 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$37_2 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$37_2 = 3;
			
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
									// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.a[state.y], scratch.cv$var37$countGlobal, state.d, lengthCV$a$37_2);
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
			int lengthCV$a$37_4 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$37_4 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$37_4 = 3;
			
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.d, state.a[state.y], lengthCV$a$37_4);
			
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

	// Calculate the probability of the samples represented by sample53 using sampled
	// values.
	private final void logProbabilityValue$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample53) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var50 = 0; var50 < state.length$obs_measured; var50 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.obs[var50];
				
				// Allocate a local variable to hold the length of the array.
				int lengthCV$a$37_5 = -1;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.y))
					lengthCV$a$37_5 = 2;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.y))
					lengthCV$a$37_5 = 3;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$a$37_5)) && (0 < lengthCV$a$37_5)) && (0.0 <= state.d[cv$sampleValue])) && (state.d[cv$sampleValue] <= 1.0))?Math.log(state.d[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				state.logProbability$var51 = cv$sampleAccumulator;
			
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
			state.fixedProbFlag$sample53 = state.fixedFlag$sample39;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$obs = (state.logProbability$obs + state.logProbability$var51);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var51);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var51);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample39) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$37_6 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$37_6 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_6, state.d);
		}
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_7 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.y))
			lengthCV$a$37_7 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.y))
			lengthCV$a$37_7 = 3;
		for(int var50 = 0; var50 < state.length$obs_measured; var50 += 1)
			state.obs[var50] = DistributionSampling.sampleCategorical(state.RNG$, state.d, lengthCV$a$37_7);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample39) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_12 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$37_12 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$37_12 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_12, state.d);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample39) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_8 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$37_8 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$37_8 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_8, state.d);
		}
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_9 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.y))
			lengthCV$a$37_9 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.y))
			lengthCV$a$37_9 = 3;
		for(int var50 = 0; var50 < state.length$obs_measured; var50 += 1)
			state.obs[var50] = DistributionSampling.sampleCategorical(state.RNG$, state.d, lengthCV$a$37_9);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample39) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_10 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$37_10 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$37_10 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_10, state.d);
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
			int lengthCV$a$37_11 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.y))
				lengthCV$a$37_11 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.y))
				lengthCV$a$37_11 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_11, state.d);
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
		if(!state.fixedProbFlag$sample53)
			state.logProbability$var51 = Double.NaN;
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
		logProbabilityValue$sample53();
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
		logProbabilityValue$sample53();
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
		logProbabilityValue$sample53();
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
		     + "public model RaggedArray3(int y, int[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    \n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    int[] obs = categorical(d).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}