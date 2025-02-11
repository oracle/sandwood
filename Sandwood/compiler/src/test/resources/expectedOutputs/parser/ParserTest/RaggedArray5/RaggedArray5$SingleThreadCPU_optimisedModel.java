package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray5$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements RaggedArray5$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] a;
	private double[] d;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample44 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample44 = false;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$d;
	private double logProbability$obs;
	private double logProbability$var31;
	private double logProbability$var34;
	private double logProbability$var40;
	private boolean[] obs;
	private boolean[] obs_measured;
	private boolean setFlag$d = false;
	private boolean setFlag$obs = false;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray5$SingleThreadCPU(ExecutionTarget target) {
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
		// Set d with flag to mark that it has been set so another array doesn't need to be
		// constructed
		d = cv$value;
		setFlag$d = true;
		
		// Unset the fixed probability flag for sample 35 as it depends on d.
		fixedProbFlag$sample35 = false;
		
		// Unset the fixed probability flag for sample 44 as it depends on d.
		fixedProbFlag$sample44 = false;
	}

	// Getter for fixedFlag$sample35.
	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	// Setter for fixedFlag$sample35.
	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample35 including if probabilities
		// need to be updated.
		fixedFlag$sample35 = cv$value;
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample35" with its value "cv$value".
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		
		// Should the probability of sample 44 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample35" with its value "cv$value".
		fixedProbFlag$sample44 = (cv$value && fixedProbFlag$sample44);
	}

	// Getter for fixedFlag$sample44.
	@Override
	public final boolean get$fixedFlag$sample44() {
		return fixedFlag$sample44;
	}

	// Setter for fixedFlag$sample44.
	@Override
	public final void set$fixedFlag$sample44(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample44 including if probabilities
		// need to be updated.
		fixedFlag$sample44 = cv$value;
		
		// Should the probability of sample 44 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample44" with its value "cv$value".
		fixedProbFlag$sample44 = (cv$value && fixedProbFlag$sample44);
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
	public final boolean[] get$obs() {
		return obs;
	}

	// Setter for obs.
	@Override
	public final void set$obs(boolean[] cv$value) {
		// Set flags for all the side effects of obs including if probabilities need to be
		// updated.
		// Set obs with flag to mark that it has been set so another array doesn't need to
		// be constructed
		obs = cv$value;
		setFlag$obs = true;
		
		// Unset the fixed probability flag for sample 44 as it depends on obs.
		fixedProbFlag$sample44 = false;
	}

	// Getter for obs_measured.
	@Override
	public final boolean[] get$obs_measured() {
		return obs_measured;
	}

	// Setter for obs_measured.
	@Override
	public final void set$obs_measured(boolean[] cv$value) {
		// Set obs_measured with flag to mark that it has been set so another array doesn't
		// need to be constructed
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

	// Calculate the probability of the samples represented by sample35 using sampled
	// values.
	private final void logProbabilityValue$sample35() {
		// Determine if we need to calculate the values for sample task 35 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample35) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(d, a[y]);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var31 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample35)
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
			fixedProbFlag$sample35 = fixedFlag$sample35;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var31 = logProbability$d;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$d);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$d);
		}
	}

	// Calculate the probability of the samples represented by sample44 using sampled
	// values.
	private final void logProbabilityValue$sample44() {
		// Determine if we need to calculate the values for sample task 44 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample44) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var39 = 0; var39 < length$obs_measured; var39 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(obs[var39], d[y]));
			logProbability$var34 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var40 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample44 = (fixedFlag$sample44 && fixedFlag$sample35);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var34 = logProbability$var40;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$obs = (logProbability$obs + logProbability$var40);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var40);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var40);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 35 drawn from Dirichlet 31. Inference was performed using Metropolis-Hastings.
	private final void sample35() {
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the probability of the random variable generating the original sampled
		// value.
		double cv$originalProbability;
		
		// A reference local to the function for the sample variable.
		int cv$arrayLength = d.length;
		
		// Pick a value in the array to adjust.
		int cv$indexToChange = (int)((double)cv$arrayLength * DistributionSampling.sampleUniform(RNG$));
		
		// Pick how much the value should be moved by. Initially this value is proposed as
		// a ratio of the current magnitude of the value, we will check to make sure the adjustment
		// will not make this value too large or other values too small and adjust if required
		// before it is applied.
		double cv$movementRatio = ((DistributionSampling.sampleBeta(RNG$, 5, 5) * 1.9999) - 1);
		
		// Calculate how much we are going to move the array index cv$indexToChange the by.
		// 
		// Allocate space for the proposed change to be stored as an absolute value
		double cv$proposedDifference;
		
		// Test if we are increasing or decreasing the value at the index. For each case calculate
		// the maximum valid adjustment.
		if((cv$movementRatio < 0))
			// The maximum reduction of the array at the index without going below 0 is the value
			// of the array at that index.
			// 
			// A reference local to the function for the sample variable.
			cv$proposedDifference = d[cv$indexToChange];
		else {
			// Calculate the maximum magnitude of the proposed index change.
			// Initially set the maximum to the amount that the value we are changing could increase
			// without exceeding 1
			// 
			// A reference local to the function for the sample variable.
			cv$proposedDifference = (1.0 - d[cv$indexToChange]);
			
			// For the array values up to the index we are going to change calculate the maximum
			// move possible.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
				// Calculate the maximum change value that the value at array index cv$loopIndex could
				// support. Based on moving all other values by an equal amount.
				// 
				// A reference local to the function for the sample variable.
				double cv$temp = (d[cv$loopIndex] * (cv$arrayLength - 1));
				
				// If the maximum move is less than the proposed move update the move size.
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			
			// For the array values after the index we are going to change calculate the maximum
			// move possible.
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1) {
				// Calculate the maximum change value that the value at array index cv$loopIndex could
				// support. Based on moving all other values by an equal amount.
				// 
				// A reference local to the function for the sample variable.
				double cv$temp = (d[cv$loopIndex] * (cv$arrayLength - 1));
				
				// If this is less than the proposed increase, change the proposed increase to this
				// value.
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
		}
		
		// Multiply the maximum adjustment by the adjustment ratio to get the actual adjustment
		// we are going to make.
		cv$proposedDifference = (cv$movementRatio * cv$proposedDifference);
		
		// Calculate how much each of the other indexes needs to be adjusted by in order to
		// maintain that the sum of the indexes is 1.
		double cv$rebalanceValue = (cv$proposedDifference / (cv$arrayLength - 1));
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$var30" with its value "a[y]".
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(d, a[y]);
			
			// Processing random variable 34.
			// 
			// Processing sample task 44 of consumer random variable null.
			for(int var39 = 0; var39 < length$obs_measured; var39 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 44 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$1$var33's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var39], d[y]) + cv$accumulatedProbabilities);
			
			// Initialize an accumulator to take the product of all the distribution probabilities
			// in log space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		// 
		// Update the sample value
		// 
		// Update all the indexes up to the index selected.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
			// A reference local to the function for the sample variable.
			d[cv$loopIndex] = (d[cv$loopIndex] - cv$rebalanceValue);
		
		// Update the selected index.
		// 
		// A reference local to the function for the sample variable.
		d[cv$indexToChange] = (d[cv$indexToChange] + cv$proposedDifference);
		
		// Update all the indexes after the index we selected.
		for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			// A reference local to the function for the sample variable.
			d[cv$loopIndex] = (d[cv$loopIndex] - cv$rebalanceValue);
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$var30" with its value "a[y]".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(d, a[y]);
		
		// Processing random variable 34.
		// 
		// Processing sample task 44 of consumer random variable null.
		for(int var39 = 0; var39 < length$obs_measured; var39 += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 44 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// cv$temp$1$var33's comment
			// Constructing a random variable input for use later.
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var39], d[y]) + cv$accumulatedProbabilities);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// Variable declaration of cv$proposedProbability moved.
		// Declaration comment was:
		// The probability of the random variable generating the new sample value.
		// 
		// Initialize an accumulator to take the product of all the distribution probabilities
		// in log space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if(((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$)))) {
			// If it is not revert the sample value and intermediates to their original values.
			// 
			// Set the sample value
			// 
			// Calculate the new sample value
			// 
			// Update the sample value
			// Update all the indexes up to the index selected.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
				// A reference local to the function for the sample variable.
				d[cv$loopIndex] = (d[cv$loopIndex] + cv$rebalanceValue);
			
			// Update the selected index.
			// 
			// A reference local to the function for the sample variable.
			d[cv$indexToChange] = (d[cv$indexToChange] - cv$proposedDifference);
			
			// Update all the indexes after the index we selected.
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				// A reference local to the function for the sample variable.
				d[cv$loopIndex] = (d[cv$loopIndex] + cv$rebalanceValue);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for a
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		
		// If d has not been set already allocate space.
		if(!setFlag$d)
			// Constructor for d
			d = new double[d.length];
		
		// If obs has not been set already allocate space.
		if(!setFlag$obs)
			// Constructor for obs
			obs = new boolean[length$obs_measured];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample35)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample44) {
			for(int var39 = 0; var39 < length$obs_measured; var39 += 1)
				obs[var39] = DistributionSampling.sampleBernoulli(RNG$, d[y]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample35)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample35)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample35)
			sample35();
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		double[] var8 = a[0];
		var8[0] = 0.4;
		var8[1] = 0.6;
		double[] var18 = a[1];
		var18[0] = 0.2;
		var18[1] = 0.3;
		var18[2] = 0.5;
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
		logProbability$var31 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$d = 0.0;
		logProbability$var34 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample44)
			logProbability$var40 = 0.0;
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
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample44();
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
		logProbabilityValue$sample35();
		logProbabilityValue$sample44();
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
		logProbabilityValue$sample35();
		logProbabilityValue$sample44();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample35)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = obs.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			obs[cv$index1] = obs_measured[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model RaggedArray5(int y, boolean[] obs_measured) {\n    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n    \n    double[] d = dirichlet(a[y]).sample();\n    boolean[] obs = bernoulli(d[y]).sample(obs_measured.length);\n    obs.observe(obs_measured);\n}";
	}
}