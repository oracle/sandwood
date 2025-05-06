package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Conditional1b$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Conditional1b$CoreInterface {
	
	// Declare the variables for the model.
	private double[] cv$var4$stateProbabilityGlobal;
	private boolean fixedFlag$sample4 = false;
	private boolean fixedFlag$sample8 = false;
	private boolean fixedProbFlag$sample4 = false;
	private boolean fixedProbFlag$sample8 = false;
	private boolean guard;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$guard;
	private double logProbability$sample8;
	private double logProbability$u;
	private double logProbability$value;
	private double logProbability$var7;
	private double observedValue;
	private boolean system$gibbsForward = true;
	private double u;
	private double value;

	public Conditional1b$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for fixedFlag$sample4.
	@Override
	public final boolean get$fixedFlag$sample4() {
		return fixedFlag$sample4;
	}

	// Setter for fixedFlag$sample4.
	@Override
	public final void set$fixedFlag$sample4(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample4 including if probabilities
		// need to be updated.
		fixedFlag$sample4 = cv$value;
		
		// Should the probability of sample 4 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample4" with its value "cv$value".
		fixedProbFlag$sample4 = (cv$value && fixedProbFlag$sample4);
	}

	// Getter for fixedFlag$sample8.
	@Override
	public final boolean get$fixedFlag$sample8() {
		return fixedFlag$sample8;
	}

	// Setter for fixedFlag$sample8.
	@Override
	public final void set$fixedFlag$sample8(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample8 including if probabilities
		// need to be updated.
		fixedFlag$sample8 = cv$value;
		
		// Should the probability of sample 8 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample8" with its value "cv$value".
		fixedProbFlag$sample8 = (cv$value && fixedProbFlag$sample8);
	}

	// Getter for guard.
	@Override
	public final boolean get$guard() {
		return guard;
	}

	// Setter for guard.
	@Override
	public final void set$guard(boolean cv$value) {
		// Set flags for all the side effects of guard including if probabilities need to
		// be updated.
		guard = cv$value;
		
		// Unset the fixed probability flag for sample 4 as it depends on guard.
		fixedProbFlag$sample4 = false;
	}

	// Getter for logProbability$$evidence.
	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	// Getter for the probability of logProbability$$model.
	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	// Getter for logProbability$bernoulli.
	@Override
	public final double get$logProbability$bernoulli() {
		return logProbability$bernoulli;
	}

	// Getter for logProbability$guard.
	@Override
	public final double get$logProbability$guard() {
		return logProbability$guard;
	}

	// Getter for logProbability$u.
	@Override
	public final double get$logProbability$u() {
		return logProbability$u;
	}

	// Getter for logProbability$value.
	@Override
	public final double get$logProbability$value() {
		return logProbability$value;
	}

	// Getter for observedValue.
	@Override
	public final double get$observedValue() {
		return observedValue;
	}

	// Setter for observedValue.
	@Override
	public final void set$observedValue(double cv$value) {
		observedValue = cv$value;
	}

	// Getter for u.
	@Override
	public final double get$u() {
		return u;
	}

	// Setter for u.
	@Override
	public final void set$u(double cv$value) {
		// Set flags for all the side effects of u including if probabilities need to be updated.
		u = cv$value;
		
		// Unset the fixed probability flag for sample 8 as it depends on u.
		fixedProbFlag$sample8 = false;
	}

	// Getter for value.
	@Override
	public final double get$value() {
		return value;
	}

	// Calculate the probability of the samples represented by sample4 using sampled values.
	private final void logProbabilityValue$sample4() {
		// Determine if we need to calculate the values for sample task 4 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample4) {
			// Generating probabilities for sample task
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$weightedProbability = -0.6931471805599453;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((guard && !(value == 1.0)))
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
			logProbability$bernoulli = cv$weightedProbability;
			
			// Store the sample task probability
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			logProbability$guard = cv$weightedProbability;
			
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
			logProbability$$model = (logProbability$$model + cv$weightedProbability);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample4)
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
				logProbability$$evidence = (logProbability$$evidence + cv$weightedProbability);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample4 = fixedFlag$sample4;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$bernoulli = logProbability$guard;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$guard);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample4)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$guard);
		}
	}

	// Calculate the probability of the samples represented by sample8 using sampled values.
	private final void logProbabilityValue$sample8() {
		// Determine if we need to calculate the values for sample task 8 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample8) {
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
			double cv$distributionAccumulator = (((0.0 <= u) && (u < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var7 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$sample8 = cv$distributionAccumulator;
			
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
			logProbability$u = (logProbability$u + cv$distributionAccumulator);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard)
				// Update the variable probability
				logProbability$value = (logProbability$value + cv$distributionAccumulator);
			
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
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
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
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample8 = fixedFlag$sample8;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var7 = logProbability$sample8;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$u = (logProbability$u + logProbability$sample8);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard)
				// Update the variable probability
				logProbability$value = (logProbability$value + logProbability$sample8);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$sample8);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$sample8);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 4 drawn from bernoulli. Inference was performed using variable marginalization.
	private final void sample4() {
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "0".
		guard = false;
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$var2" with its value "0.5".
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$var2" with its value "0.5".
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		cv$var4$stateProbabilityGlobal[0] = -0.6931471805599453;
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		guard = true;
		
		// Processing conditional point17.
		// 
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
		
		// Check observed variable is possible
		if((value == 1.0))
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
		// Substituted "cv$temp$0$var2" with its value "0.5".
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching branch with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		cv$var4$stateProbabilityGlobal[1] = (cv$accumulatedConsumerProbabilities - 0.6931471805599453);
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var4$stateProbabilityGlobal[0];
		
		// Unrolled loop
		// 
		// Get a local reference to the scratch space.
		double cv$lseElementValue = cv$var4$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		
		// If the maximum value is -infinity return -infinity.
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		
		// Sum the values in the array.
		else
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			// 
			// Get a local reference to the scratch space.
			// 
			// Get a local reference to the scratch space.
			// 
			// Initialise the sum of the array elements
			cv$logSum = (Math.log((Math.exp((cv$var4$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var4$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var4$stateProbabilityGlobal[0] = 0.5;
			
			// Get a local reference to the scratch space.
			cv$var4$stateProbabilityGlobal[1] = 0.5;
		} else {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var4$stateProbabilityGlobal[0] = Math.exp((cv$var4$stateProbabilityGlobal[0] - cv$logSum));
			
			// Get a local reference to the scratch space.
			cv$var4$stateProbabilityGlobal[1] = Math.exp((cv$var4$stateProbabilityGlobal[1] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = 2; cv$indexName < cv$var4$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var4$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		// 
		// cv$numNumStates's comment
		// variable marginalization
		guard = (DistributionSampling.sampleCategorical(RNG$, cv$var4$stateProbabilityGlobal, 2) == 1);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Constructor for cv$var4$stateProbabilityGlobal
		// 
		// Allocate scratch space.
		// 
		// Allocation of cv$var4$stateProbabilityGlobal for single threaded execution
		cv$var4$stateProbabilityGlobal = new double[2];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(!fixedFlag$sample8)
			u = DistributionSampling.sampleUniform(RNG$);
		if(guard) {
			if(!fixedFlag$sample4)
				value = 1.0;
		} else {
			if((!fixedFlag$sample4 || !fixedFlag$sample8))
				value = u;
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(!fixedFlag$sample8)
			u = DistributionSampling.sampleUniform(RNG$);
		if(guard)
			value = 1.0;
		else {
			if((!fixedFlag$sample4 || !fixedFlag$sample8))
				value = u;
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample4)
			sample4();
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {}

	// A method to initialize all the probabilities in the model to 0/Log(1) ready for
	// the current probabilities to be calculated by calculating the probability of each
	// sample task, and its effect on the rest of the model.
	private final void initializeLogProbabilityFields() {
		// Set the probabilities of the random variable, and the model as a whole to ready
		// them to be reconstructed by the probability calls for each sample. Sample probabilities
		// are only reset for samples that are not fixed at a value that has already been
		// calculated.
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$bernoulli = 0.0;
		if(!fixedProbFlag$sample4)
			logProbability$guard = Double.NaN;
		logProbability$var7 = 0.0;
		logProbability$u = 0.0;
		logProbability$value = 0.0;
		if(!fixedProbFlag$sample8)
			logProbability$sample8 = Double.NaN;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample4)
			logProbabilityValue$sample4();
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
		logProbabilityValue$sample4();
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
		logProbabilityValue$sample4();
		logProbabilityValue$sample8();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		value = observedValue;
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
		     + "public model Conditional1b(double observedValue)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    \n"
		     + "    double u = uniform(0.0, 1.0).sample();    \n"
		     + "    double value = guard?1.0:u;\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value.observe(observedValue);\n"
		     + "}";
	}
}