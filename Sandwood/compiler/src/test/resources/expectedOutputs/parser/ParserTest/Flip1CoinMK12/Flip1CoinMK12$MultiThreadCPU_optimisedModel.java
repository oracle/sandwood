package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK12$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK12$CoreInterface {
	
	// Declare the variables for the model.
	private double bias;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample52 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private boolean guard1;
	private int guard2;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$sample16;
	private double logProbability$sample28;
	private double logProbability$sample35;
	private double logProbability$var13;
	private double logProbability$var14;
	private double logProbability$var23;
	private double logProbability$var26;
	private double logProbability$var30;
	private double logProbability$var33;
	private double logProbability$var48;
	private int samples;
	private boolean system$gibbsForward = true;
	private double var14;
	private double var26;
	private double var33;

	public Flip1CoinMK12$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for bias.
	@Override
	public final double get$bias() {
		return bias;
	}

	// Getter for fixedFlag$sample16.
	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	// Setter for fixedFlag$sample16.
	@Override
	public final void set$fixedFlag$sample16(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample16 including if probabilities
		// need to be updated.
		fixedFlag$sample16 = cv$value;
		
		// Should the probability of sample 16 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample16" with its value "cv$value".
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
		
		// Should the probability of sample 52 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample16" with its value "cv$value".
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
	}

	// Getter for fixedFlag$sample28.
	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	// Setter for fixedFlag$sample28.
	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample28 including if probabilities
		// need to be updated.
		fixedFlag$sample28 = cv$value;
		
		// Should the probability of sample 28 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample28" with its value "cv$value".
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		
		// Should the probability of sample 52 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample28" with its value "cv$value".
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
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
		
		// Should the probability of sample 52 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample35" with its value "cv$value".
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
	}

	// Getter for flips.
	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		// Set flipsMeasured
		flipsMeasured = cv$value;
	}

	// Getter for guard1.
	@Override
	public final boolean get$guard1() {
		return guard1;
	}

	// Setter for guard1.
	@Override
	public final void set$guard1(boolean cv$value) {
		guard1 = cv$value;
	}

	// Getter for guard2.
	@Override
	public final int get$guard2() {
		return guard2;
	}

	// Setter for guard2.
	@Override
	public final void set$guard2(int cv$value) {
		guard2 = cv$value;
	}

	// Getter for length$flipsMeasured.
	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	// Setter for length$flipsMeasured.
	@Override
	public final void set$length$flipsMeasured(int cv$value) {
		length$flipsMeasured = cv$value;
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

	// Getter for logProbability$bias.
	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	// Getter for logProbability$flips.
	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	// Getter for samples.
	@Override
	public final int get$samples() {
		return samples;
	}

	// Getter for var14.
	@Override
	public final double get$var14() {
		return var14;
	}

	// Setter for var14.
	@Override
	public final void set$var14(double cv$value) {
		// Set flags for all the side effects of var14 including if probabilities need to
		// be updated.
		var14 = cv$value;
		
		// Unset the fixed probability flag for sample 16 as it depends on var14.
		fixedProbFlag$sample16 = false;
		
		// Unset the fixed probability flag for sample 52 as it depends on var14.
		fixedProbFlag$sample52 = false;
	}

	// Getter for var26.
	@Override
	public final double get$var26() {
		return var26;
	}

	// Setter for var26.
	@Override
	public final void set$var26(double cv$value) {
		// Set flags for all the side effects of var26 including if probabilities need to
		// be updated.
		var26 = cv$value;
		
		// Unset the fixed probability flag for sample 28 as it depends on var26.
		fixedProbFlag$sample28 = false;
		
		// Unset the fixed probability flag for sample 52 as it depends on var26.
		fixedProbFlag$sample52 = false;
	}

	// Getter for var33.
	@Override
	public final double get$var33() {
		return var33;
	}

	// Setter for var33.
	@Override
	public final void set$var33(double cv$value) {
		// Set flags for all the side effects of var33 including if probabilities need to
		// be updated.
		var33 = cv$value;
		
		// Unset the fixed probability flag for sample 35 as it depends on var33.
		fixedProbFlag$sample35 = false;
		
		// Unset the fixed probability flag for sample 52 as it depends on var33.
		fixedProbFlag$sample52 = false;
	}

	// Calculate the probability of the samples represented by sample16 using sampled
	// values.
	private final void logProbabilityValue$sample16() {
		// Determine if we need to calculate the values for sample task 16 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample16) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			if(guard1) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(var14, 1.0, 1.0);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = cv$distributionAccumulator;
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var13 = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample16 = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$var14 = (logProbability$var14 + cv$accumulator);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(guard1)
				// Update the variable probability
				logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample16 = fixedFlag$sample16;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if(guard1) {
				cv$accumulator = logProbability$sample16;
				logProbability$var13 = logProbability$sample16;
			}
			
			// Update the variable probability
			logProbability$var14 = (logProbability$var14 + cv$accumulator);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(guard1)
				// Update the variable probability
				logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample28 using sampled
	// values.
	private final void logProbabilityValue$sample28() {
		// Determine if we need to calculate the values for sample task 28 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample28) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			if((!guard1 && (guard2 <= 2))) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta((var26 * 2), 1.0, 1.0);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = cv$distributionAccumulator;
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var23 = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample28 = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$var26 = (logProbability$var26 + cv$accumulator);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!guard1 && (guard2 <= 2)))
				// Update the variable probability
				logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample28 = fixedFlag$sample28;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if((!guard1 && (guard2 <= 2))) {
				cv$accumulator = logProbability$sample28;
				logProbability$var23 = logProbability$sample28;
			}
			
			// Update the variable probability
			logProbability$var26 = (logProbability$var26 + cv$accumulator);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!guard1 && (guard2 <= 2)))
				// Update the variable probability
				logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample35 using sampled
	// values.
	private final void logProbabilityValue$sample35() {
		// Determine if we need to calculate the values for sample task 35 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample35) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			if((!guard1 && !(guard2 <= 2))) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta((var33 * 3), 1.0, 1.0);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = cv$distributionAccumulator;
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var30 = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample35 = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$var33 = (logProbability$var33 + cv$accumulator);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!guard1 && !(guard2 <= 2)))
				// Update the variable probability
				logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample35 = fixedFlag$sample35;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if((!guard1 && !(guard2 <= 2))) {
				cv$accumulator = logProbability$sample35;
				logProbability$var30 = logProbability$sample35;
			}
			
			// Update the variable probability
			logProbability$var33 = (logProbability$var33 + cv$accumulator);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!guard1 && !(guard2 <= 2)))
				// Update the variable probability
				logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample52 using sampled
	// values.
	private final void logProbabilityValue$sample52() {
		// Determine if we need to calculate the values for sample task 52 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample52) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var47 = 0; var47 < samples; var47 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((flips[var47]?bias:(1.0 - bias))));
			logProbability$bernoulli = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var48 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample52 = ((fixedFlag$sample16 && fixedFlag$sample28) && fixedFlag$sample35);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$bernoulli = logProbability$var48;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$flips = (logProbability$flips + logProbability$var48);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var48);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var48);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 16 drawn from Beta 13. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample16() {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing random variable 36.
		// 
		// Processing sample task 52 of consumer random variable bernoulli.
		for(int var47 = 0; var47 < samples; var47 += 1) {
			// Include the value sampled by task 52 from random variable bernoulli.
			// Increment the number of samples.
			cv$count = (cv$count + 1);
			
			// If the sample value was positive increase the count
			if(flips[var47])
				cv$sum = (cv$sum + 1);
		}
		
		// Write out the new value of the sample.
		var14 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias = var14;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from Beta 23. Inference was performed using Metropolis-Hastings.
	private final void sample28() {
		// The original value of the sample
		double cv$originalValue = (var26 * 2);
		
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
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var22" with its value "1.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$originalValue, 1.0, 1.0);
			
			// Processing sample task 52 of consumer random variable bernoulli.
			for(int var47 = 0; var47 < samples; var47 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 52 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$2$bias" with its value "traceTempVariable$bias$2_2".
				cv$accumulatedProbabilities = (Math.log((flips[var47]?var26:(1.0 - var26))) + cv$accumulatedProbabilities);
			
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
		// Write out the new sample value.
		var26 = (cv$proposedValue / 2);
		bias = var26;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var22" with its value "1.0".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
		
		// Processing sample task 52 of consumer random variable bernoulli.
		for(int var47 = 0; var47 < samples; var47 += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 52 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "cv$temp$2$bias" with its value "traceTempVariable$bias$2_2".
			cv$accumulatedProbabilities = (Math.log((flips[var47]?var26:(1.0 - var26))) + cv$accumulatedProbabilities);
		
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
			// Write out the new sample value.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			var26 = (cv$originalValue / 2);
			bias = var26;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 35 drawn from Beta 30. Inference was performed using Metropolis-Hastings.
	private final void sample35() {
		// The original value of the sample
		double cv$originalValue = (var33 * 3);
		
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
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var29" with its value "1.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$originalValue, 1.0, 1.0);
			
			// Processing sample task 52 of consumer random variable bernoulli.
			for(int var47 = 0; var47 < samples; var47 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 52 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$2$bias" with its value "traceTempVariable$bias$2_2".
				cv$accumulatedProbabilities = (Math.log((flips[var47]?var33:(1.0 - var33))) + cv$accumulatedProbabilities);
			
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
		// Write out the new sample value.
		var33 = (cv$proposedValue / 3);
		bias = var33;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var29" with its value "1.0".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
		
		// Processing sample task 52 of consumer random variable bernoulli.
		for(int var47 = 0; var47 < samples; var47 += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 52 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "cv$temp$2$bias" with its value "traceTempVariable$bias$2_2".
			cv$accumulatedProbabilities = (Math.log((flips[var47]?var33:(1.0 - var33))) + cv$accumulatedProbabilities);
		
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
			// Write out the new sample value.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			var33 = (cv$originalValue / 3);
			bias = var33;
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
		// Constructor for flips
		flips = new boolean[length$flipsMeasured];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(guard1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample16) {
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var14;
			}
		} else {
			if((guard2 <= 2)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample28) {
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var26;
				}
			} else {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample35) {
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var33;
				}
			}
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
						flips[var47] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(guard1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample16) {
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var14;
			}
		} else {
			if((guard2 <= 2)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample28) {
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var26;
				}
			} else {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample35) {
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var33;
				}
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(guard1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample16) {
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var14;
			}
		} else {
			if((guard2 <= 2)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample28) {
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var26;
				}
			} else {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample35) {
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var33;
				}
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		if(guard1) {
			if(!fixedFlag$sample16)
				sample16();
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28)
					sample28();
			} else {
				if(!fixedFlag$sample35)
					sample35();
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		samples = length$flipsMeasured;
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
		logProbability$var13 = 0.0;
		logProbability$var14 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$sample16 = 0.0;
		logProbability$var23 = 0.0;
		logProbability$var26 = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$sample28 = 0.0;
		logProbability$var30 = 0.0;
		logProbability$var33 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$sample35 = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample52)
			logProbability$var48 = 0.0;
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
		if(fixedFlag$sample16)
			logProbabilityValue$sample16();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample52();
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
		logProbabilityValue$sample16();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
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
		logProbabilityValue$sample16();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(guard1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample16) {
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var14;
			}
		} else {
			if((guard2 <= 2)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample28) {
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var26;
				}
			} else {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample35) {
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var33;
				}
			}
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
		for(int i = (samples - 1); i >= 0; i -= 1)
			flips[i] = flipsMeasured[i];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		if(guard1) {
			if(fixedFlag$sample16)
				bias = var14;
		} else {
			if((guard2 <= 2)) {
				if(fixedFlag$sample28)
					bias = var26;
			} else {
				if(fixedFlag$sample35)
					bias = var33;
			}
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
		     + "public model Flip1CoinMK12(boolean[] flipsMeasured, boolean guard1, int guard2) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = beta(1.0, 1).sample();\n"
		     + "    else { \n"
		     + "        if(guard2 <= 2) {\n"
		     + "            bias = beta(1.0, 1).sample()/2;\n"
		     + "        } else\n"
		     + "            bias = beta(1.0, 1).sample()/3;\n"
		     + "    }\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "\n"
		     + "    for(int i:[0..samples))\n"
		     + "        flips[i].observe(flipsMeasured[i]);\n"
		     + "}\n"
		     + "";
	}
}