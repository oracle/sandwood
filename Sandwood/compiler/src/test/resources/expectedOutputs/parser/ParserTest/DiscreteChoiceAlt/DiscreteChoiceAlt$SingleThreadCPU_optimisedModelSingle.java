package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoiceAlt$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DiscreteChoiceAlt$CoreInterface {
	
	// Declare the variables for the model.
	private int[] ObsChoices;
	private int[] choices;
	private double[] exped;
	private boolean fixedFlag$sample19 = false;
	private boolean fixedFlag$sample49 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean[] guard$sample19put43$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample19;
	private double logProbability$sum;
	private double logProbability$ut;
	private double logProbability$var17;
	private double logProbability$var42;
	private double logProbability$var47;
	private int noObs;
	private int noProducts;
	private double[] prob;
	private boolean setFlag$choices = false;
	private boolean setFlag$exped = false;
	private boolean setFlag$prob = false;
	private boolean setFlag$ut = false;
	private double sum;
	private boolean system$gibbsForward = true;
	private double[] ut;

	public DiscreteChoiceAlt$SingleThreadCPU(ExecutionTarget target) {
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
		// Set ObsChoices with flag to mark that it has been set so another array doesn't
		// need to be constructed
		ObsChoices = cv$value;
	}

	// Getter for choices.
	@Override
	public final int[] get$choices() {
		return choices;
	}

	// Setter for choices.
	@Override
	public final void set$choices(int[] cv$value) {
		// Set choices with flag to mark that it has been set so another array doesn't need
		// to be constructed
		choices = cv$value;
		setFlag$choices = true;
	}

	// Getter for exped.
	@Override
	public final double[] get$exped() {
		return exped;
	}

	// Setter for exped.
	@Override
	public final void set$exped(double[] cv$value) {
		// Set exped with flag to mark that it has been set so another array doesn't need
		// to be constructed
		exped = cv$value;
		setFlag$exped = true;
	}

	// Getter for fixedFlag$sample19.
	@Override
	public final boolean get$fixedFlag$sample19() {
		return fixedFlag$sample19;
	}

	// Setter for fixedFlag$sample19.
	@Override
	public final void set$fixedFlag$sample19(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample19 including if probabilities
		// need to be updated.
		fixedFlag$sample19 = cv$value;
		
		// Should the probability of sample 19 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample19" with its value "cv$value".
		fixedProbFlag$sample19 = (cv$value && fixedProbFlag$sample19);
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample19" with its value "cv$value".
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	// Getter for fixedFlag$sample49.
	@Override
	public final boolean get$fixedFlag$sample49() {
		return fixedFlag$sample49;
	}

	// Setter for fixedFlag$sample49.
	@Override
	public final void set$fixedFlag$sample49(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample49 including if probabilities
		// need to be updated.
		fixedFlag$sample49 = cv$value;
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample49" with its value "cv$value".
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
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

	// Setter for prob.
	@Override
	public final void set$prob(double[] cv$value) {
		// Set prob with flag to mark that it has been set so another array doesn't need to
		// be constructed
		prob = cv$value;
		setFlag$prob = true;
	}

	// Getter for sum.
	@Override
	public final double get$sum() {
		return sum;
	}

	// Setter for sum.
	@Override
	public final void set$sum(double cv$value) {
		sum = cv$value;
	}

	// Getter for ut.
	@Override
	public final double[] get$ut() {
		return ut;
	}

	// Setter for ut.
	@Override
	public final void set$ut(double[] cv$value) {
		// Set ut with flag to mark that it has been set so another array doesn't need to
		// be constructed
		ut = cv$value;
		setFlag$ut = true;
	}

	// Calculate the probability of the samples represented by sample19 using sampled
	// values.
	private final void logProbabilityValue$sample19() {
		// Determine if we need to calculate the values for sample task 19 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample19) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1) {
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[i$var12] / 3.1622776601683795)) - 1.151292546497023);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample19[(i$var12 - 1)] = cv$distributionAccumulator;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 19 and consumer double[] 26.
				// 
				// Update the variable probability
				logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
				
				// Looking for a path between Sample 19 and consumer double 34.
				// 
				// Update the variable probability
				logProbability$sum = (logProbability$sum + cv$distributionAccumulator);
				
				// Update the variable probability
				logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
			}
			logProbability$var17 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample19)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample19 = fixedFlag$sample19;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1) {
				double cv$sampleValue = logProbability$sample19[(i$var12 - 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 19 and consumer double[] 26.
				// 
				// Update the variable probability
				logProbability$exped = (logProbability$exped + cv$sampleValue);
				
				// Looking for a path between Sample 19 and consumer double 34.
				// 
				// Update the variable probability
				logProbability$sum = (logProbability$sum + cv$sampleValue);
				
				// Update the variable probability
				logProbability$prob = (logProbability$prob + cv$sampleValue);
			}
			logProbability$var17 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample49 using sampled
	// values.
	private final void logProbabilityValue$sample49() {
		// Determine if we need to calculate the values for sample task 49 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample49) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < noObs; var46 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = choices[var46];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < prob.length))?Math.log(prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var42 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var47 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedFlag$sample19);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var42 = logProbability$var47;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$choices = (logProbability$choices + logProbability$var47);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var47);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var47);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 19 drawn from Gaussian 17. Inference was performed using Metropolis-Hastings.
	private final void sample19(int i$var12) {
		// The original value of the sample
		double cv$originalValue = ut[i$var12];
		
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
			// Substituted "cv$temp$1$var16" with its value "10.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			
			// Processing sample task 49 of consumer random variable null.
			for(int var46 = 0; var46 < noObs; var46 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 49 with the current configuration.
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
				cv$accumulatedProbabilities = ((((0.0 <= choices[var46]) && (choices[var46] < prob.length))?Math.log(prob[choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		ut[i$var12] = cv$proposedValue;
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 19 and consumer double[] 26.
		// 
		// Substituted "i$var23" with its value "i$var12".
		exped[i$var12] = Math.exp(ut[i$var12]);
		
		// Guards to ensure that sum is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 19 and consumer double 34.
		// 
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$0 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction88Index = 0; cv$reduction88Index < noProducts; cv$reduction88Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// j's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$0 = (reduceVar$sum$0 + exped[cv$reduction88Index]);
		
		// Write out the new sample value.
		sum = reduceVar$sum$0;
		for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample19put43$global[i$var38] = false;
		
		// Set the flags to false
		// 
		// Guard to check that at most one copy of the code is executed for a given random
		// variable instance.
		// 
		// Substituted "i$var38" with its value "i$var12".
		guard$sample19put43$global[i$var12] = false;
		for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			if(!guard$sample19put43$global[i$var38]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample19put43$global[i$var38] = true;
				
				// sum's comment
				// Write out the new sample value.
				prob[i$var38] = (exped[i$var38] / reduceVar$sum$0);
			}
		}
		
		// Substituted "i$var23" with its value "i$var12".
		// 
		// Substituted "i$var38" with its value "i$var12".
		if(!guard$sample19put43$global[i$var12]) {
			// The body will execute, so should not be executed again
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "i$var38" with its value "i$var12".
			guard$sample19put43$global[i$var12] = true;
			
			// Substituted "i$var38" with its value "i$var12".
			// 
			// sum's comment
			// Write out the new sample value.
			prob[i$var12] = (exped[i$var12] / reduceVar$sum$0);
		}
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var16" with its value "10.0".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		
		// Processing sample task 49 of consumer random variable null.
		for(int var46 = 0; var46 < noObs; var46 += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 49 with the current configuration.
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
			cv$accumulatedProbabilities = ((((0.0 <= choices[var46]) && (choices[var46] < prob.length))?Math.log(prob[choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		
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
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			ut[i$var12] = cv$originalValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 19 and consumer double[] 26.
			// 
			// Substituted "i$var23" with its value "i$var12".
			exped[i$var12] = Math.exp(ut[i$var12]);
			
			// Guards to ensure that sum is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 19 and consumer double 34.
			// 
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$2 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction196Index = 0; cv$reduction196Index < noProducts; cv$reduction196Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// j's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$2 = (reduceVar$sum$2 + exped[cv$reduction196Index]);
			
			// Write out the new sample value.
			sum = reduceVar$sum$2;
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample19put43$global[i$var38] = false;
			
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "i$var38" with its value "i$var12".
			guard$sample19put43$global[i$var12] = false;
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample19put43$global[i$var38]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample19put43$global[i$var38] = true;
					
					// sum's comment
					// Write out the new sample value.
					prob[i$var38] = (exped[i$var38] / reduceVar$sum$2);
				}
			}
			
			// Substituted "i$var23" with its value "i$var12".
			// 
			// Substituted "i$var38" with its value "i$var12".
			if(!guard$sample19put43$global[i$var12]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "i$var38" with its value "i$var12".
				guard$sample19put43$global[i$var12] = true;
				
				// Substituted "i$var38" with its value "i$var12".
				// 
				// sum's comment
				// Write out the new sample value.
				prob[i$var12] = (exped[i$var12] / reduceVar$sum$2);
			}
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Constructor for guard$sample19put43$global
		// 
		// Allocate scratch space.
		// 
		// Allocation of guard$sample19put43$global for single threaded execution
		// 
		// Calculate the largest index of i that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample19put43$global = new boolean[Math.max(0, noProducts)];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If ut has not been set already allocate space.
		if(!setFlag$ut)
			// Constructor for ut
			ut = new double[noProducts];
		
		// If exped has not been set already allocate space.
		if(!setFlag$exped)
			// Constructor for exped
			exped = new double[noProducts];
		
		// If prob has not been set already allocate space.
		if(!setFlag$prob)
			// Constructor for prob
			prob = new double[noProducts];
		
		// If choices has not been set already allocate space.
		if(!setFlag$choices)
			// Constructor for choices
			choices = new int[noObs];
		
		// Constructor for logProbability$sample19
		logProbability$sample19 = new double[(noProducts - 1)];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample19) {
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
				ut[i$var12] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1)
				exped[i$var23] = Math.exp(ut[i$var23]);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$3 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// j's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$3 = (reduceVar$sum$3 + exped[cv$reduction32Index]);
			sum = reduceVar$sum$3;
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				// Substituted "sum" with its value "reduceVar$sum$3".
				prob[i$var38] = (exped[i$var38] / reduceVar$sum$3);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample49) {
			for(int var46 = 0; var46 < noObs; var46 += 1)
				choices[var46] = DistributionSampling.sampleCategorical(RNG$, prob);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample19) {
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
				ut[i$var12] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1)
				exped[i$var23] = Math.exp(ut[i$var23]);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$5 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// j's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$5 = (reduceVar$sum$5 + exped[cv$reduction32Index]);
			sum = reduceVar$sum$5;
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				// Substituted "sum" with its value "reduceVar$sum$5".
				prob[i$var38] = (exped[i$var38] / reduceVar$sum$5);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample19) {
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
				ut[i$var12] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1)
				exped[i$var23] = Math.exp(ut[i$var23]);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$4 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// j's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$4 = (reduceVar$sum$4 + exped[cv$reduction32Index]);
			sum = reduceVar$sum$4;
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				// Substituted "sum" with its value "reduceVar$sum$4".
				prob[i$var38] = (exped[i$var38] / reduceVar$sum$4);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(!fixedFlag$sample19) {
			// Infer the samples in chronological order.
			if(system$gibbsForward) {
				for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
					sample19(i$var12);
			}
			// Infer the samples in reverse chronological order.
			else {
				for(int i$var12 = (noProducts - 1); i$var12 >= 1; i$var12 -= 1)
					sample19(i$var12);
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
		logProbability$var17 = 0.0;
		logProbability$sum = 0.0;
		logProbability$ut = 0.0;
		logProbability$prob = 0.0;
		logProbability$exped = 0.0;
		if(!fixedProbFlag$sample19) {
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
				logProbability$sample19[(i$var12 - 1)] = 0.0;
		}
		logProbability$var42 = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample49)
			logProbability$var47 = 0.0;
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
		if(fixedFlag$sample19)
			logProbabilityValue$sample19();
		logProbabilityValue$sample49();
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
		logProbabilityValue$sample19();
		logProbabilityValue$sample49();
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
		logProbabilityValue$sample19();
		logProbabilityValue$sample49();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample19) {
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
				ut[i$var12] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1)
				exped[i$var23] = Math.exp(ut[i$var23]);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$6 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// j's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$6 = (reduceVar$sum$6 + exped[cv$reduction32Index]);
			sum = reduceVar$sum$6;
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				// Substituted "sum" with its value "reduceVar$sum$6".
				prob[i$var38] = (exped[i$var38] / reduceVar$sum$6);
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
		int cv$length1 = choices.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			choices[cv$index1] = ObsChoices[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(setFlag$ut) {
			for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1)
				exped[i$var23] = Math.exp(ut[i$var23]);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$7 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// j's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$7 = (reduceVar$sum$7 + exped[cv$reduction32Index]);
			sum = reduceVar$sum$7;
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				// Substituted "sum" with its value "reduceVar$sum$7".
				prob[i$var38] = (exped[i$var38] / reduceVar$sum$7);
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model DiscreteChoiceAlt(int noProducts, int noObs, int[] ObsChoices) {\n    // we just need an uninformative prior for utility intercepts\n\n    // draw utilities\n    double[] ut = new double[noProducts];\n    ut[0] = 0.0;\n    for(int i=1; i<noProducts; i++) \n        ut[i]= gaussian(0, 10).sample();\n\n    // calculate choice probabilities\n    double[] exped = new double[noProducts];\n    for(int i : [0..noProducts)) {\n        exped[i] = exp(ut[i]);\n    }\n    double sum = reduce(exped, 0, (i, j) -> { return i + j; });\n    double[] prob = new double[noProducts];\n    for (int i : [0..noProducts)) {\n        prob[i] = exped[i] / sum;\n    }\n    // draw consumer choices according to the calculated probabilities\n    int[] choices = categorical(prob).sample(noObs);\n\n    // assert generated choices match observed choices\n    choices.observe(ObsChoices);\n}";
	}
}