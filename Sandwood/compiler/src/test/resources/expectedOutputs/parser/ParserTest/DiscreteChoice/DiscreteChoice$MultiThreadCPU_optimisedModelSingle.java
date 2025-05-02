package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoice$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DiscreteChoice$CoreInterface {
	
	// Declare the variables for the model.
	private int[] ObsChoices;
	private int[] choices;
	private double[] exped;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample78 = false;
	private boolean[] guard$sample24put65$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample24;
	private double logProbability$sum;
	private double logProbability$ut;
	private double logProbability$var23;
	private double logProbability$var65;
	private double logProbability$var77;
	private int noObs;
	private int noProducts;
	private double[] prob;
	private double sum;
	private boolean system$gibbsForward = true;
	private double[] ut;

	public DiscreteChoice$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for ObsChoices.
	@Override
	public final int[] get$ObsChoices() {
		return ObsChoices;
	}

	// Setter for ObsChoices.
	@Override
	public final void set$ObsChoices(int[] cv$value) {
		// Set ObsChoices
		ObsChoices = cv$value;
	}

	// Getter for choices.
	@Override
	public final int[] get$choices() {
		return choices;
	}

	// Getter for exped.
	@Override
	public final double[] get$exped() {
		return exped;
	}

	// Getter for fixedFlag$sample24.
	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	// Setter for fixedFlag$sample24.
	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample24 including if probabilities
		// need to be updated.
		fixedFlag$sample24 = cv$value;
		
		// Should the probability of sample 24 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample24" with its value "cv$value".
		fixedProbFlag$sample24 = (cv$value && fixedProbFlag$sample24);
		
		// Should the probability of sample 78 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample24" with its value "cv$value".
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
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

	// Getter for logProbability$choices.
	@Override
	public final double get$logProbability$choices() {
		return logProbability$choices;
	}

	// Getter for logProbability$exped.
	@Override
	public final double get$logProbability$exped() {
		return logProbability$exped;
	}

	// Getter for logProbability$prob.
	@Override
	public final double get$logProbability$prob() {
		return logProbability$prob;
	}

	// Getter for logProbability$sum.
	@Override
	public final double get$logProbability$sum() {
		return logProbability$sum;
	}

	// Getter for logProbability$ut.
	@Override
	public final double get$logProbability$ut() {
		return logProbability$ut;
	}

	// Getter for noObs.
	@Override
	public final int get$noObs() {
		return noObs;
	}

	// Setter for noObs.
	@Override
	public final void set$noObs(int cv$value) {
		noObs = cv$value;
	}

	// Getter for noProducts.
	@Override
	public final int get$noProducts() {
		return noProducts;
	}

	// Setter for noProducts.
	@Override
	public final void set$noProducts(int cv$value) {
		noProducts = cv$value;
	}

	// Getter for prob.
	@Override
	public final double[] get$prob() {
		return prob;
	}

	// Getter for sum.
	@Override
	public final double get$sum() {
		return sum;
	}

	// Getter for ut.
	@Override
	public final double[] get$ut() {
		return ut;
	}

	// Setter for ut.
	@Override
	public final void set$ut(double[] cv$value) {
		// Set flags for all the side effects of ut including if probabilities need to be
		// updated.
		// Set ut
		ut = cv$value;
		
		// Unset the fixed probability flag for sample 24 as it depends on ut.
		fixedProbFlag$sample24 = false;
		
		// Unset the fixed probability flag for sample 78 as it depends on ut.
		fixedProbFlag$sample78 = false;
	}

	// Calculate the probability of the samples represented by sample24 using sampled
	// values.
	private final void logProbabilityValue$sample24() {
		// Determine if we need to calculate the values for sample task 24 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample24) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1) {
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[i$var18] / 3.1622776601683795)) - 1.151292546497023);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample24[(i$var18 - 1)] = cv$distributionAccumulator;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 24 and consumer double[] 39.
				// 
				// Update the variable probability
				logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
				
				// Looking for a path between Sample 24 and consumer double 50.
				// 
				// Update the variable probability
				logProbability$sum = (logProbability$sum + cv$distributionAccumulator);
				
				// Update the variable probability
				logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
			}
			if(cv$sampleReached)
				logProbability$var23 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$ut = (logProbability$ut + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample24)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample24 = fixedFlag$sample24;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1) {
				double cv$sampleValue = logProbability$sample24[(i$var18 - 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 24 and consumer double[] 39.
				// 
				// Update the variable probability
				logProbability$exped = (logProbability$exped + cv$sampleValue);
				
				// Looking for a path between Sample 24 and consumer double 50.
				// 
				// Update the variable probability
				logProbability$sum = (logProbability$sum + cv$sampleValue);
				
				// Update the variable probability
				logProbability$prob = (logProbability$prob + cv$sampleValue);
			}
			if(cv$sampleReached)
				logProbability$var23 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample78 using sampled
	// values.
	private final void logProbabilityValue$sample78() {
		// Determine if we need to calculate the values for sample task 78 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample78) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var76 = 0; var76 < noObs; var76 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = choices[var76];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noProducts))?Math.log(prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				logProbability$var65 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				logProbability$var77 = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$choices = (logProbability$choices + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample78 = fixedFlag$sample24;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if((0 < noObs))
				// Record that the sample was reached.
				cv$sampleReached = true;
			if(cv$sampleReached)
				logProbability$var65 = logProbability$var77;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$choices = (logProbability$choices + logProbability$var77);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var77);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var77);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 24 drawn from Gaussian 23. Inference was performed using Metropolis-Hastings.
	private final void sample24(int i$var18) {
		// The original value of the sample
		double cv$originalValue = ut[i$var18];
		
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
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		
		// Unrolled loop
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var22" with its value "10.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			
			// Processing sample task 78 of consumer random variable null.
			for(int var76 = 0; var76 < noObs; var76 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 78 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$2$prob" with its value "prob".
				// 
				// cv$temp$3$$var503's comment
				// 
				// $var503's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = ((((0.0 <= choices[var76]) && (choices[var76] < noProducts))?Math.log(prob[choices[var76]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		// 
		// Guards to ensure that ut is only updated when there is a valid path.
		ut[i$var18] = cv$proposedValue;
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 24 and consumer double[] 39.
		// 
		// Substituted "i$var36" with its value "i$var18".
		exped[i$var18] = Math.exp(ut[i$var18]);
		
		// Guards to ensure that sum is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 24 and consumer double 50.
		// 
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$8 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// j's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$8 = (reduceVar$sum$8 + exped[cv$reduction44Index]);
		
		// Write out the new sample value.
		sum = reduceVar$sum$8;
		for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample24put65$global[i$var61] = false;
		
		// Set the flags to false
		// 
		// Guard to check that at most one copy of the code is executed for a given random
		// variable instance.
		// 
		// Substituted "i$var61" with its value "i$var18".
		guard$sample24put65$global[i$var18] = false;
		for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			if(!guard$sample24put65$global[i$var61]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample24put65$global[i$var61] = true;
				
				// sum's comment
				// Write out the new sample value.
				prob[i$var61] = (exped[i$var61] / reduceVar$sum$8);
			}
		}
		
		// Substituted "i$var36" with its value "i$var18".
		// 
		// Substituted "i$var61" with its value "i$var18".
		if(!guard$sample24put65$global[i$var18]) {
			// The body will execute, so should not be executed again
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "i$var61" with its value "i$var18".
			guard$sample24put65$global[i$var18] = true;
			
			// Substituted "i$var61" with its value "i$var18".
			// 
			// sum's comment
			// Write out the new sample value.
			prob[i$var18] = (exped[i$var18] / reduceVar$sum$8);
		}
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var22" with its value "10.0".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		
		// Processing sample task 78 of consumer random variable null.
		for(int var76 = 0; var76 < noObs; var76 += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 78 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "cv$temp$2$prob" with its value "prob".
			// 
			// cv$temp$3$$var503's comment
			// 
			// $var503's comment
			// Constructing a random variable input for use later.
			cv$accumulatedProbabilities = ((((0.0 <= choices[var76]) && (choices[var76] < noProducts))?Math.log(prob[choices[var76]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Guards to ensure that ut is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			ut[i$var18] = cv$originalValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 24 and consumer double[] 39.
			// 
			// Substituted "i$var36" with its value "i$var18".
			exped[i$var18] = Math.exp(ut[i$var18]);
			
			// Guards to ensure that sum is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 24 and consumer double 50.
			// 
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$10 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// j's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$10 = (reduceVar$sum$10 + exped[cv$reduction44Index]);
			
			// Write out the new sample value.
			sum = reduceVar$sum$10;
			for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample24put65$global[i$var61] = false;
			
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "i$var61" with its value "i$var18".
			guard$sample24put65$global[i$var18] = false;
			for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample24put65$global[i$var61]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample24put65$global[i$var61] = true;
					
					// sum's comment
					// Write out the new sample value.
					prob[i$var61] = (exped[i$var61] / reduceVar$sum$10);
				}
			}
			
			// Substituted "i$var36" with its value "i$var18".
			// 
			// Substituted "i$var61" with its value "i$var18".
			if(!guard$sample24put65$global[i$var18]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "i$var61" with its value "i$var18".
				guard$sample24put65$global[i$var18] = true;
				
				// Substituted "i$var61" with its value "i$var18".
				// 
				// sum's comment
				// Write out the new sample value.
				prob[i$var18] = (exped[i$var18] / reduceVar$sum$10);
			}
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Constructor for guard$sample24put65$global
		// 
		// Allocate scratch space.
		// 
		// Allocation of guard$sample24put65$global for single threaded execution
		// 
		// Calculate the largest index of i that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample24put65$global = new boolean[Math.max(0, noProducts)];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If ut has not been set already allocate space.
		if(!fixedFlag$sample24)
			// Constructor for ut
			ut = new double[noProducts];
		
		// Constructor for exped
		exped = new double[noProducts];
		
		// Constructor for prob
		prob = new double[noProducts];
		
		// Constructor for choices
		choices = new int[noObs];
		
		// Constructor for logProbability$sample24
		logProbability$sample24 = new double[(noProducts - 1)];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample24) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, noProducts, 1,
				(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1)
							ut[i$var18] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
							exped[i$var36] = Math.exp(ut[i$var36]);
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$11 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// j's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$11 = (reduceVar$sum$11 + exped[cv$reduction44Index]);
			sum = reduceVar$sum$11;
			
			// Alternative name for reduceVar$sum$11 to make it effectively final.
			double reduceVar$sum$11$1 = reduceVar$sum$11;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
							// Substituted "sum" with its value "reduceVar$sum$11".
							prob[i$var61] = (exped[i$var61] / reduceVar$sum$11$1);
				}
			);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var76, int forEnd$var76, int threadID$var76, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var76 = forStart$var76; var76 < forEnd$var76; var76 += 1)
						choices[var76] = DistributionSampling.sampleCategorical(RNG$1, prob, noProducts);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample24) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, noProducts, 1,
				(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1)
							ut[i$var18] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
							exped[i$var36] = Math.exp(ut[i$var36]);
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$13 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// j's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$13 = (reduceVar$sum$13 + exped[cv$reduction44Index]);
			sum = reduceVar$sum$13;
			
			// Alternative name for reduceVar$sum$13 to make it effectively final.
			double reduceVar$sum$13$1 = reduceVar$sum$13;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
							// Substituted "sum" with its value "reduceVar$sum$13".
							prob[i$var61] = (exped[i$var61] / reduceVar$sum$13$1);
				}
			);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample24) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, noProducts, 1,
				(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1)
							ut[i$var18] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
							exped[i$var36] = Math.exp(ut[i$var36]);
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$12 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// j's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$12 = (reduceVar$sum$12 + exped[cv$reduction44Index]);
			sum = reduceVar$sum$12;
			
			// Alternative name for reduceVar$sum$12 to make it effectively final.
			double reduceVar$sum$12$1 = reduceVar$sum$12;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
							// Substituted "sum" with its value "reduceVar$sum$12".
							prob[i$var61] = (exped[i$var61] / reduceVar$sum$12$1);
				}
			);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(!fixedFlag$sample24) {
			// Infer the samples in chronological order.
			if(system$gibbsForward) {
				for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1)
					sample24(i$var18);
			}
			// Infer the samples in reverse chronological order.
			else {
				for(int i$var18 = (noProducts - 1); i$var18 >= 1; i$var18 -= 1)
					sample24(i$var18);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		ut[0] = 0.0;
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
		logProbability$var23 = Double.NaN;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$sum = 0.0;
		logProbability$prob = 0.0;
		if(!fixedProbFlag$sample24) {
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1)
				logProbability$sample24[(i$var18 - 1)] = Double.NaN;
		}
		logProbability$var65 = Double.NaN;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample78)
			logProbability$var77 = Double.NaN;
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
		if(fixedFlag$sample24)
			logProbabilityValue$sample24();
		logProbabilityValue$sample78();
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
		logProbabilityValue$sample24();
		logProbabilityValue$sample78();
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
		logProbabilityValue$sample24();
		logProbabilityValue$sample78();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample24) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, noProducts, 1,
				(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1)
							ut[i$var18] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
							exped[i$var36] = Math.exp(ut[i$var36]);
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$14 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// j's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$14 = (reduceVar$sum$14 + exped[cv$reduction44Index]);
			sum = reduceVar$sum$14;
			
			// Alternative name for reduceVar$sum$14 to make it effectively final.
			double reduceVar$sum$14$1 = reduceVar$sum$14;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
							// Substituted "sum" with its value "reduceVar$sum$14".
							prob[i$var61] = (exped[i$var61] / reduceVar$sum$14$1);
				}
			);
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
		int cv$length1 = choices.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			choices[cv$index1] = ObsChoices[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample24) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
							exped[i$var36] = Math.exp(ut[i$var36]);
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$15 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// j's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$15 = (reduceVar$sum$15 + exped[cv$reduction44Index]);
			sum = reduceVar$sum$15;
			
			// Alternative name for reduceVar$sum$15 to make it effectively final.
			double reduceVar$sum$15$1 = reduceVar$sum$15;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
							// Substituted "sum" with its value "reduceVar$sum$15".
							prob[i$var61] = (exped[i$var61] / reduceVar$sum$15$1);
				}
			);
		}
	}

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
		     + "public model DiscreteChoice(int noProducts, int noObs, int[] ObsChoices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = new double[noProducts];\n"
		     + "    ut[0] = 0.0;\n"
		     + "    for(int i=1; i<noProducts; i++) {\n"
		     + "        ut[i]= gaussian(0, 10).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    // calculate choice probabilities\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int i : [0..noProducts)) {\n"
		     + "        exped[i] = exp(ut[i]);\n"
		     + "    }\n"
		     + "    double sum = reduce(exped, 0, (i, j) -> { return i + j; });\n"
		     + "    double[] prob = new double[noProducts];\n"
		     + "    for (int i : [0..noProducts)) {\n"
		     + "        prob[i] = exped[i] / sum;\n"
		     + "    }\n"
		     + "    // draw consumer choices according to the calculated probabilities\n"
		     + "    int[] choices = categorical(prob).sample(noObs);\n"
		     + "\n"
		     + "    // assert generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}