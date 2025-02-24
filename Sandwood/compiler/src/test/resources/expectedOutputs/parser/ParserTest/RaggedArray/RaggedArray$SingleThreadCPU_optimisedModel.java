package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements RaggedArray$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] a;
	private double[][] b;
	private double[] cv$var69$stateProbabilityGlobal;
	private boolean fixedFlag$sample73 = false;
	private boolean fixedFlag$sample89 = false;
	private boolean fixedProbFlag$sample73 = false;
	private boolean fixedProbFlag$sample89 = false;
	private int i;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$i;
	private double logProbability$obs;
	private double logProbability$p;
	private double logProbability$var68;
	private double logProbability$var72;
	private double logProbability$var85;
	private boolean[] obs;
	private boolean[] obs_measured;
	private double p;
	private boolean setFlag$obs = false;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for a.
	@Override
	public final double[][] get$a() {
		return a;
	}

	// Getter for b.
	@Override
	public final double[][] get$b() {
		return b;
	}

	// Getter for fixedFlag$sample73.
	@Override
	public final boolean get$fixedFlag$sample73() {
		return fixedFlag$sample73;
	}

	// Setter for fixedFlag$sample73.
	@Override
	public final void set$fixedFlag$sample73(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample73 including if probabilities
		// need to be updated.
		fixedFlag$sample73 = cv$value;
		
		// Should the probability of sample 73 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample73" with its value "cv$value".
		fixedProbFlag$sample73 = (cv$value && fixedProbFlag$sample73);
		
		// Should the probability of sample 89 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample73" with its value "cv$value".
		fixedProbFlag$sample89 = (cv$value && fixedProbFlag$sample89);
	}

	// Getter for fixedFlag$sample89.
	@Override
	public final boolean get$fixedFlag$sample89() {
		return fixedFlag$sample89;
	}

	// Setter for fixedFlag$sample89.
	@Override
	public final void set$fixedFlag$sample89(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample89 including if probabilities
		// need to be updated.
		fixedFlag$sample89 = cv$value;
		
		// Should the probability of sample 89 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample89" with its value "cv$value".
		fixedProbFlag$sample89 = (cv$value && fixedProbFlag$sample89);
	}

	// Getter for i.
	@Override
	public final int get$i() {
		return i;
	}

	// Setter for i.
	@Override
	public final void set$i(int cv$value) {
		// Set flags for all the side effects of i including if probabilities need to be updated.
		i = cv$value;
		
		// Unset the fixed probability flag for sample 73 as it depends on i.
		fixedProbFlag$sample73 = false;
		
		// Unset the fixed probability flag for sample 89 as it depends on i.
		fixedProbFlag$sample89 = false;
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

	// Getter for logProbability$i.
	@Override
	public final double get$logProbability$i() {
		return logProbability$i;
	}

	// Getter for logProbability$obs.
	@Override
	public final double get$logProbability$obs() {
		return logProbability$obs;
	}

	// Getter for logProbability$p.
	@Override
	public final double get$logProbability$p() {
		return logProbability$p;
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
		
		// Unset the fixed probability flag for sample 89 as it depends on obs.
		fixedProbFlag$sample89 = false;
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

	// Getter for p.
	@Override
	public final double get$p() {
		return p;
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

	// Calculate the probability of the samples represented by sample73 using sampled
	// values.
	private final void logProbabilityValue$sample73() {
		// Determine if we need to calculate the values for sample task 73 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample73) {
			// Generating probabilities for sample task
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$71_2 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$71_2 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$71_2 = 3;
			
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
			double cv$distributionAccumulator = (((0.0 <= i) && (i < lengthCV$a$71_2))?Math.log(a[y][i]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var68 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$i = cv$distributionAccumulator;
			
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
			logProbability$p = (logProbability$p + cv$distributionAccumulator);
			
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
			if(fixedFlag$sample73)
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
			fixedProbFlag$sample73 = fixedFlag$sample73;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var68 = logProbability$i;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$p = (logProbability$p + logProbability$i);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$i);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample73)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$i);
		}
	}

	// Calculate the probability of the samples represented by sample89 using sampled
	// values.
	private final void logProbabilityValue$sample89() {
		// Determine if we need to calculate the values for sample task 89 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample89) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var84 = 0; var84 < length$obs_measured; var84 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(obs[var84], p));
			logProbability$var72 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var85 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample89 = (fixedFlag$sample89 && fixedFlag$sample73);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var72 = logProbability$var85;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$obs = (logProbability$obs + logProbability$var85);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var85);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var85);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 73 drawn from Categorical 68. Inference was performed using variable
	// marginalization.
	private final void sample73() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$71_0 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == y))
			lengthCV$a$71_0 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == y))
			lengthCV$a$71_0 = 3;
		
		// Variable declaration of cv$numNumStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$numNumStates's comment
		// Calculate the number of states to evaluate.
		int cv$numNumStates = Math.max(0, lengthCV$a$71_0);
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			i = cv$valuePos;
			
			// Guards to ensure that p is only updated when there is a valid path.
			// 
			// Write out the new sample value.
			// 
			// Value of the variable at this index
			p = b[y][cv$valuePos];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$71_1 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$71_1 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$71_1 = 3;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$var67" with its value "a[y]".
			// 
			// Substituted "cv$temp$1$$var158" with its value "lengthCV$a$71_1".
			double cv$accumulatedProbabilities = ((cv$valuePos < lengthCV$a$71_1)?Math.log(a[y][cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Processing sample task 89 of consumer random variable null.
			for(int var84 = 0; var84 < length$obs_measured; var84 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 89 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$2$p" with its value "p".
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var84], p) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var69$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var69$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var69$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		
		// If the maximum value is -infinity return -infinity.
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		
		// Sum the values in the array.
		else {
			// Initialise the sum of the array elements
			double cv$lseSum = 0.0;
			
			// Offset values, move to normal space, and sum.
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var69$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var69$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var69$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var69$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var69$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var69$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		// 
		// Get a local reference to the scratch space.
		i = DistributionSampling.sampleCategorical(RNG$, cv$var69$stateProbabilityGlobal, cv$numNumStates);
		
		// Guards to ensure that p is only updated when there is a valid path.
		// 
		// Write out the new sample value.
		p = b[y][i];
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Constructor for cv$var69$stateProbabilityGlobal
		// 
		// Allocate scratch space.
		// 
		// Allocation of cv$var69$stateProbabilityGlobal for single threaded execution
		// 
		// Test if the input to putTask 35 is larger than the current values.
		cv$var69$stateProbabilityGlobal = new double[3];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for a
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		
		// Constructor for b
		b = new double[2][];
		b[0] = new double[2];
		b[1] = new double[3];
		
		// If obs has not been set already allocate space.
		if(!setFlag$obs)
			// Constructor for obs
			obs = new boolean[length$obs_measured];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample73) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$71_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$71_3 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$71_3 = 3;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$71_3);
			p = b[y][i];
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample89) {
			for(int var84 = 0; var84 < length$obs_measured; var84 += 1)
				obs[var84] = DistributionSampling.sampleBernoulli(RNG$, p);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample73) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$71_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$71_5 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$71_5 = 3;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$71_5);
			p = b[y][i];
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample73) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$71_4 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$71_4 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$71_4 = 3;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$71_4);
			p = b[y][i];
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample73)
			sample73();
		
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
		double[] var38 = b[0];
		var38[0] = 0.2;
		var38[1] = 0.8;
		double[] var51 = b[1];
		var51[0] = 0.4;
		var51[1] = 0.2;
		var51[2] = 0.6;
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
		logProbability$var68 = 0.0;
		logProbability$p = 0.0;
		if(!fixedProbFlag$sample73)
			logProbability$i = 0.0;
		logProbability$var72 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample89)
			logProbability$var85 = 0.0;
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
		if(fixedFlag$sample73)
			logProbabilityValue$sample73();
		logProbabilityValue$sample89();
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
		logProbabilityValue$sample73();
		logProbabilityValue$sample89();
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
		logProbabilityValue$sample73();
		logProbabilityValue$sample89();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample73) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$71_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$71_6 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$71_6 = 3;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$71_6);
			p = b[y][i];
		}
		
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
	public final void setIntermediates() {
		p = b[y][i];
	}

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
		     + "public model RaggedArray(int y, boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n"
		     + "    \n"
		     + "    int i = categorical(a[y]).sample();\n"
		     + "    double p = b[y][i];\n"
		     + "    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}