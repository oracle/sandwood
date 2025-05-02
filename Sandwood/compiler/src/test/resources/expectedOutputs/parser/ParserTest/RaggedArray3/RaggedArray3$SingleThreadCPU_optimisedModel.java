package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray3$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements RaggedArray3$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] a;
	private double[] cv$var37$countGlobal;
	private double[] d;
	private boolean fixedFlag$sample39 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample53 = false;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$d;
	private double logProbability$obs;
	private double logProbability$var36;
	private double logProbability$var38;
	private double logProbability$var51;
	private int[] obs;
	private int[] obs_measured;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray3$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for a.
	@Override
	public final double[][] get$a() {
		return a;
	}

	// Getter for d.
	@Override
	public final double[] get$d() {
		return d;
	}

	// Setter for d.
	@Override
	public final void set$d(double[] cv$value) {
		// Set flags for all the side effects of d including if probabilities need to be updated.
		// Set d
		d = cv$value;
		
		// Unset the fixed probability flag for sample 39 as it depends on d.
		fixedProbFlag$sample39 = false;
		
		// Unset the fixed probability flag for sample 53 as it depends on d.
		fixedProbFlag$sample53 = false;
	}

	// Getter for fixedFlag$sample39.
	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	// Setter for fixedFlag$sample39.
	@Override
	public final void set$fixedFlag$sample39(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample39 including if probabilities
		// need to be updated.
		fixedFlag$sample39 = cv$value;
		
		// Should the probability of sample 39 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample39" with its value "cv$value".
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample39" with its value "cv$value".
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	// Getter for length$obs_measured.
	@Override
	public final int get$length$obs_measured() {
		return length$obs_measured;
	}

	// Setter for length$obs_measured.
	@Override
	public final void set$length$obs_measured(int cv$value) {
		length$obs_measured = cv$value;
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

	// Getter for logProbability$d.
	@Override
	public final double get$logProbability$d() {
		return logProbability$d;
	}

	// Getter for logProbability$obs.
	@Override
	public final double get$logProbability$obs() {
		return logProbability$obs;
	}

	// Getter for obs.
	@Override
	public final int[] get$obs() {
		return obs;
	}

	// Getter for obs_measured.
	@Override
	public final int[] get$obs_measured() {
		return obs_measured;
	}

	// Setter for obs_measured.
	@Override
	public final void set$obs_measured(int[] cv$value) {
		// Set obs_measured
		obs_measured = cv$value;
	}

	// Getter for y.
	@Override
	public final int get$y() {
		return y;
	}

	// Setter for y.
	@Override
	public final void set$y(int cv$value) {
		y = cv$value;
	}

	// Calculate the probability of the samples represented by sample39 using sampled
	// values.
	private final void logProbabilityValue$sample39() {
		// Determine if we need to calculate the values for sample task 39 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample39) {
			// Generating probabilities for sample task
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$37_3 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$37_3 = 3;
			
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(d, a[y], lengthCV$a$37_3);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var36 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$d = cv$distributionAccumulator;
			
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
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample39)
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
			fixedProbFlag$sample39 = fixedFlag$sample39;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var36 = logProbability$d;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$d);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample39)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$d);
		}
	}

	// Calculate the probability of the samples represented by sample53 using sampled
	// values.
	private final void logProbabilityValue$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample53) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var50 = 0; var50 < length$obs_measured; var50 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = obs[var50];
				
				// Allocate a local variable to hold the length of the array.
				int lengthCV$a$37_4 = -1;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == y))
					lengthCV$a$37_4 = 2;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == y))
					lengthCV$a$37_4 = 3;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$a$37_4))?Math.log(d[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var38 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var51 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample53 = fixedFlag$sample39;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var38 = logProbability$var51;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$obs = (logProbability$obs + logProbability$var51);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var51);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var51);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 39 drawn from Dirichlet 36. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample39() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_1 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == y))
			lengthCV$a$37_1 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == y))
			lengthCV$a$37_1 = 3;
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < lengthCV$a$37_1; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var37$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 38.
		// 
		// Processing sample task 53 of consumer random variable null.
		for(int var50 = 0; var50 < length$obs_measured; var50 += 1)
			// Increment the sample counter with the value sampled by sample task 53 of random
			// variable var38
			// 
			// A local reference to the scratch space.
			cv$var37$countGlobal[obs[var50]] = (cv$var37$countGlobal[obs[var50]] + 1.0);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_2 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == y))
			lengthCV$a$37_2 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == y))
			lengthCV$a$37_2 = 3;
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, a[y], cv$var37$countGlobal, d, lengthCV$a$37_2);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Constructor for cv$var37$countGlobal
		// 
		// Allocate scratch space.
		// 
		// Allocation of cv$var37$countGlobal for single threaded execution
		// 
		// Test if the input to putTask 35 is larger than the current values.
		cv$var37$countGlobal = new double[3];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for a
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		
		// If d has not been set already allocate space.
		if(!fixedFlag$sample39) {
			// Constructor for d
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_0 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$37_0 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$37_0 = 3;
			d = new double[lengthCV$a$37_0];
		}
		
		// Constructor for obs
		obs = new int[length$obs_measured];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample39) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$37_5 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$37_5 = 3;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_5, d);
		}
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_6 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == y))
			lengthCV$a$37_6 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == y))
			lengthCV$a$37_6 = 3;
		for(int var50 = 0; var50 < length$obs_measured; var50 += 1)
			obs[var50] = DistributionSampling.sampleCategorical(RNG$, d, lengthCV$a$37_6);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample39) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_8 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$37_8 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$37_8 = 3;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_8, d);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample39) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_7 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$37_7 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$37_7 = 3;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_7, d);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample39)
			sample39();
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		double[] var6 = a[0];
		var6[0] = 0.4;
		var6[1] = 0.6;
		double[] var19 = a[1];
		var19[0] = 0.2;
		var19[1] = 0.3;
		var19[2] = 0.5;
	}

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
		logProbability$var36 = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$d = Double.NaN;
		logProbability$var38 = Double.NaN;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var51 = Double.NaN;
	}

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	@Override
	public final void logEvidenceGeneration() {
		// Generate values for all the samples in the model that were not fixed or observed.
		forwardGenerationValuesNoOutputs();
		
		// Calculate the probability for the resulting model.
		logEvidenceProbabilities();
	}

	// Construct the evidence probabilities.
	private final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample39)
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

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample39) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_9 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$37_9 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$37_9 = 3;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_9, d);
		}
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = obs.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			obs[cv$index1] = obs_measured[cv$index1];
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