package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Conditional2b$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Conditional2b.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Conditional2b$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var4$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// 
			// Constructor for cv$var4$stateProbabilityGlobal
			// 
			// Allocation of cv$var4$stateProbabilityGlobal for single threaded execution
			cv$var4$stateProbabilityGlobal = new double[2];
		}
	}


	public Conditional2b$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample4
	private final void drawValueSample4() {
		state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 4 drawn from bernoulli. Inference was performed using variable marginalization.
	private final void inferSample4() {
		state.constrainedFlag$sample4 = false;
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "0".
		state.guard = false;
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
						// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		scratch.cv$var4$stateProbabilityGlobal[0] = -0.6931471805599453;
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.guard = true;
		
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
		
		// Processing observed variable value2
		// 
		// Looking for a path between Put 20 and consumer double[] 27.
		// 
		// Check observed variable is possible
		if((state.value2[0] == 1.0))
			cv$accumulatedConsumerProbabilities = 0.0;
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching branch with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		scratch.cv$var4$stateProbabilityGlobal[1] = (cv$accumulatedConsumerProbabilities - 0.6931471805599453);
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
			double cv$distributionAccumulator = (((0.0 <= state.u) && (state.u < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$u = cv$distributionAccumulator;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.guard) {
				// Add probability to constructed variables from the combined probability
				// 
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
				state.logProbability$value = (state.logProbability$value + cv$distributionAccumulator);
				
				// Looking for a path between Sample 10 and consumer double[] 27.
				// 
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
				state.logProbability$value2 = (state.logProbability$value2 + cv$distributionAccumulator);
			}
			
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
			state.fixedProbFlag$sample10 = state.fixedFlag$sample10;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.guard) {
				// Add probability to constructed variables from the combined probability
				// 
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				state.logProbability$value = (state.logProbability$value + state.logProbability$u);
				
				// Looking for a path between Sample 10 and consumer double[] 27.
				// 
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				state.logProbability$value2 = (state.logProbability$value2 + state.logProbability$u);
			}
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$u);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$u);
		}
	}

	// Calculate the probability of the samples represented by sample4 using sampled values.
	private final void logProbabilityValue$sample4() {
		// Determine if we need to calculate the values for sample task 4 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample4) {
			// Generating probabilities for sample task
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$weightedProbability = -0.6931471805599453;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.guard && !(state.value2[0] == 1.0)))
				// Looking for a path between Put 20 and consumer double[] 27.
				cv$weightedProbability = Double.NEGATIVE_INFINITY;
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			state.logProbability$bernoulli = cv$weightedProbability;
			
			// Store the sample task probability
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			state.logProbability$guard = cv$weightedProbability;
			
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
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			state.logProbability$$model = (state.logProbability$$model + cv$weightedProbability);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample4)
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
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$weightedProbability);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample4 = state.fixedFlag$sample4;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			state.logProbability$bernoulli = state.logProbability$guard;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$guard);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample4)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$guard);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(!state.fixedFlag$sample10)
			state.u = DistributionSampling.sampleUniform(state.RNG$);
		if(state.guard)
			state.value[0] = 1.0;
		else {
			if(!state.fixedFlag$sample10)
				state.value[0] = state.u;
		}
		if((!state.fixedFlag$sample4 || !state.fixedFlag$sample10))
			state.value2[0] = state.value[0];
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(!state.fixedFlag$sample10)
			state.u = DistributionSampling.sampleUniform(state.RNG$);
		if(state.guard)
			state.value[0] = 1.0;
		else
			state.value[0] = state.u;
		state.value2[0] = state.value[0];
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample4)
			inferSample4();
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample4)
			drawValueSample4();
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
		state.logProbability$bernoulli = 0.0;
		if(!state.fixedProbFlag$sample4)
			state.logProbability$guard = Double.NaN;
		state.logProbability$value = 0.0;
		state.logProbability$value2 = 0.0;
		if(!state.fixedProbFlag$sample10)
			state.logProbability$u = Double.NaN;
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
		if(state.fixedFlag$sample4)
			logProbabilityValue$sample4();
		logProbabilityValue$sample10();
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
		logProbabilityValue$sample4();
		logProbabilityValue$sample10();
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
		logProbabilityValue$sample4();
		logProbabilityValue$sample10();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		int cv$length1 = state.value2.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.value2[cv$index1] = state.observedValue[cv$index1];
		state.value[0] = state.value2[0];
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
		     + "public model Conditional2b(double[] observedValue)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    \n"
		     + "    double[] value = new double[1];\n"
		     + "    \n"
		     + "    double u = uniform(0.0, 1.0).sample();\n"
		     + "\n"
		     + "    if(guard)\n"
		     + "        value[0] = 1.0;\n"
		     + "    else {\n"
		     + "        value[0] = u;\n"
		     + "    }\n"
		     + "    \n"
		     + "    double [] value2 = new double[1];\n"
		     + "    \n"
		     + "    value2[0] = value[0];\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value2.observe(observedValue);\n"
		     + "}";
	}
}