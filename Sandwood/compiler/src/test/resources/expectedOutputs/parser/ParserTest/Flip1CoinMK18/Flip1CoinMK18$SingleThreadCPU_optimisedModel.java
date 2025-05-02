package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK18$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK18$CoreInterface {
	
	// Declare the variables for the model.
	private int a;
	private int b;
	private double[][][] bias;
	private int c;
	private boolean fixedFlag$sample11 = false;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample11 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$q;
	private double logProbability$t;
	private double logProbability$var10;
	private double logProbability$var16;
	private double logProbability$var97;
	private double q;
	private int samples;
	private boolean system$gibbsForward = true;
	private double t;

	public Flip1CoinMK18$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for a.
	@Override
	public final int get$a() {
		return a;
	}

	// Setter for a.
	@Override
	public final void set$a(int cv$value) {
		a = cv$value;
	}

	// Getter for b.
	@Override
	public final int get$b() {
		return b;
	}

	// Setter for b.
	@Override
	public final void set$b(int cv$value) {
		b = cv$value;
	}

	// Getter for bias.
	@Override
	public final double[][][] get$bias() {
		return bias;
	}

	// Getter for c.
	@Override
	public final int get$c() {
		return c;
	}

	// Setter for c.
	@Override
	public final void set$c(int cv$value) {
		c = cv$value;
	}

	// Getter for fixedFlag$sample11.
	@Override
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	// Setter for fixedFlag$sample11.
	@Override
	public final void set$fixedFlag$sample11(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample11 including if probabilities
		// need to be updated.
		fixedFlag$sample11 = cv$value;
		
		// Should the probability of sample 11 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample11" with its value "cv$value".
		fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample11" with its value "cv$value".
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
	}

	// Getter for fixedFlag$sample17.
	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	// Setter for fixedFlag$sample17.
	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample17 including if probabilities
		// need to be updated.
		fixedFlag$sample17 = cv$value;
		
		// Should the probability of sample 17 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample17" with its value "cv$value".
		fixedProbFlag$sample17 = (cv$value && fixedProbFlag$sample17);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample17" with its value "cv$value".
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
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

	// Getter for logProbability$q.
	@Override
	public final double get$logProbability$q() {
		return logProbability$q;
	}

	// Getter for logProbability$t.
	@Override
	public final double get$logProbability$t() {
		return logProbability$t;
	}

	// Getter for q.
	@Override
	public final double get$q() {
		return q;
	}

	// Setter for q.
	@Override
	public final void set$q(double cv$value) {
		// Set flags for all the side effects of q including if probabilities need to be updated.
		q = cv$value;
		
		// Unset the fixed probability flag for sample 11 as it depends on q.
		fixedProbFlag$sample11 = false;
		
		// Unset the fixed probability flag for sample 103 as it depends on q.
		fixedProbFlag$sample103 = false;
	}

	// Getter for samples.
	@Override
	public final int get$samples() {
		return samples;
	}

	// Setter for samples.
	@Override
	public final void set$samples(int cv$value) {
		samples = cv$value;
	}

	// Getter for t.
	@Override
	public final double get$t() {
		return t;
	}

	// Setter for t.
	@Override
	public final void set$t(double cv$value) {
		// Set flags for all the side effects of t including if probabilities need to be updated.
		t = cv$value;
		
		// Unset the fixed probability flag for sample 17 as it depends on t.
		fixedProbFlag$sample17 = false;
		
		// Unset the fixed probability flag for sample 103 as it depends on t.
		fixedProbFlag$sample103 = false;
	}

	// Calculate the probability of the samples represented by sample103 using sampled
	// values.
	private final void logProbabilityValue$sample103() {
		// Determine if we need to calculate the values for sample task 103 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample103) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var96 = 0; var96 < samples; var96 += 1) {
				double var84 = bias[a][b][c];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((flips[var96]?var84:(1.0 - var84))));
			}
			logProbability$bernoulli = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var97 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample103 = (fixedFlag$sample11 && fixedFlag$sample17);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$bernoulli = logProbability$var97;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$flips = (logProbability$flips + logProbability$var97);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var97);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var97);
		}
	}

	// Calculate the probability of the samples represented by sample11 using sampled
	// values.
	private final void logProbabilityValue$sample11() {
		// Determine if we need to calculate the values for sample task 11 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample11) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(q, 1.0, 1.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var10 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$q = cv$distributionAccumulator;
			
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
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			
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
			if(fixedFlag$sample11)
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
			fixedProbFlag$sample11 = fixedFlag$sample11;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var10 = logProbability$q;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$q);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$q);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample11)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$q);
		}
	}

	// Calculate the probability of the samples represented by sample17 using sampled
	// values.
	private final void logProbabilityValue$sample17() {
		// Determine if we need to calculate the values for sample task 17 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample17) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(t, 1.0, 1.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var16 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$t = cv$distributionAccumulator;
			
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
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			
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
			if(fixedFlag$sample17)
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
			fixedProbFlag$sample17 = fixedFlag$sample17;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var16 = logProbability$t;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$t);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$t);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$t);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 11 drawn from Beta 10. Inference was performed using Metropolis-Hastings.
	private final void sample11() {
		// The original value of the sample
		double cv$originalValue = q;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		// 
		// The original value of the sample
		double cv$var = ((q * q) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		// 
		// The original value of the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + q);
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the new sample value.
		double cv$proposedProbability;
		{
			// Unrolled loop
			{
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				// 
				// Substituted "cv$temp$1$var9" with its value "1.0".
				// 
				// Set the current value to the current state of the tree.
				// 
				// The original value of the sample
				double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(q, 1.0, 1.0);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((((0 == a) && (1 == b)) && (0 == c))) {
					// Set the current value to the current state of the tree.
					// 
					// The original value of the sample
					double traceTempVariable$var84$5_2 = (1 - q);
					
					// Processing sample task 103 of consumer random variable bernoulli.
					for(int var96 = 0; var96 < samples; var96 += 1)
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 103 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// cv$temp$2$var84's comment
						// Constructing a random variable input for use later.
						cv$accumulatedProbabilities = (Math.log((flips[var96]?traceTempVariable$var84$5_2:(1.0 - traceTempVariable$var84$5_2))) + cv$accumulatedProbabilities);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == a)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(((0 == b) && (1 == c))) {
						// Set the current value to the current state of the tree.
						// 
						// The original value of the sample
						double traceTempVariable$var84$6_2 = (1 - q);
						
						// Processing sample task 103 of consumer random variable bernoulli.
						for(int var96 = 0; var96 < samples; var96 += 1)
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 103 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// cv$temp$3$var84's comment
							// Constructing a random variable input for use later.
							cv$accumulatedProbabilities = (Math.log((flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == b)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c)) {
							// Set the current value to the current state of the tree.
							// 
							// The original value of the sample
							double traceTempVariable$var84$7_2 = (1 - q);
							
							// Processing sample task 103 of consumer random variable bernoulli.
							for(int var96 = 0; var96 < samples; var96 += 1)
								// A check to ensure rounding of floating point values can never result in a negative
								// value.
								// 
								// Recorded the probability of reaching sample task 103 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								// 
								// Variable declaration of cv$accumulatedConsumerProbabilities moved.
								// Declaration comment was:
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								// 
								// cv$temp$4$var84's comment
								// Constructing a random variable input for use later.
								cv$accumulatedProbabilities = (Math.log((flips[var96]?traceTempVariable$var84$7_2:(1.0 - traceTempVariable$var84$7_2))) + cv$accumulatedProbabilities);
						}
						if((1 == c)) {
							// Processing sample task 103 of consumer random variable bernoulli.
							for(int var96 = 0; var96 < samples; var96 += 1)
								// A check to ensure rounding of floating point values can never result in a negative
								// value.
								// 
								// Recorded the probability of reaching sample task 103 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								// 
								// Variable declaration of cv$accumulatedConsumerProbabilities moved.
								// Declaration comment was:
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								// 
								// cv$temp$5$var84's comment
								// Variable declaration of cv$temp$5$var84 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								// 
								// The original value of the sample
								cv$accumulatedProbabilities = (Math.log((flips[var96]?q:(1.0 - q))) + cv$accumulatedProbabilities);
						}
					}
				}
				
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
			// Write out the new value of the sample.
			q = cv$proposedValue;
			bias[0][1][0] = (1 - cv$proposedValue);
			double[][] var52 = bias[1];
			var52[0][1] = (1 - cv$proposedValue);
			double[] var67 = var52[1];
			var67[0] = (1 - cv$proposedValue);
			var67[1] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var9" with its value "1.0".
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((((0 == a) && (1 == b)) && (0 == c))) {
				double traceTempVariable$var84$5_2 = (1 - cv$proposedValue);
				
				// Processing sample task 103 of consumer random variable bernoulli.
				for(int var96 = 0; var96 < samples; var96 += 1)
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 103 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$2$var84's comment
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (Math.log((flips[var96]?traceTempVariable$var84$5_2:(1.0 - traceTempVariable$var84$5_2))) + cv$accumulatedProbabilities);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == a)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 == b) && (1 == c))) {
					double traceTempVariable$var84$6_2 = (1 - cv$proposedValue);
					
					// Processing sample task 103 of consumer random variable bernoulli.
					for(int var96 = 0; var96 < samples; var96 += 1)
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 103 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// cv$temp$3$var84's comment
						// Constructing a random variable input for use later.
						cv$accumulatedProbabilities = (Math.log((flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))) + cv$accumulatedProbabilities);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == b)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c)) {
						double traceTempVariable$var84$7_2 = (1 - cv$proposedValue);
						
						// Processing sample task 103 of consumer random variable bernoulli.
						for(int var96 = 0; var96 < samples; var96 += 1)
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 103 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// cv$temp$4$var84's comment
							// Constructing a random variable input for use later.
							cv$accumulatedProbabilities = (Math.log((flips[var96]?traceTempVariable$var84$7_2:(1.0 - traceTempVariable$var84$7_2))) + cv$accumulatedProbabilities);
					}
					if((1 == c)) {
						// Processing sample task 103 of consumer random variable bernoulli.
						for(int var96 = 0; var96 < samples; var96 += 1)
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 103 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// cv$temp$5$var84's comment
							// Variable declaration of cv$temp$5$var84 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedProbabilities = (Math.log((flips[var96]?cv$proposedValue:(1.0 - cv$proposedValue))) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$proposedProbability = cv$accumulatedProbabilities;
		}
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$proposedProbability - cv$originalProbability)))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the new value of the sample.
			q = cv$originalValue;
			
			// q's comment
			// Write out the new value of the sample.
			bias[0][1][0] = (1 - cv$originalValue);
			double[][] var52 = bias[1];
			
			// q's comment
			// Write out the new value of the sample.
			var52[0][1] = (1 - cv$originalValue);
			double[] var67 = var52[1];
			
			// q's comment
			// Write out the new value of the sample.
			var67[0] = (1 - cv$originalValue);
			
			// q's comment
			// Write out the new value of the sample.
			var67[1] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 17 drawn from Beta 16. Inference was performed using Metropolis-Hastings.
	private final void sample17() {
		// The original value of the sample
		double cv$originalValue = t;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		// 
		// The original value of the sample
		double cv$var = ((t * t) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		// 
		// The original value of the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + t);
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the new sample value.
		double cv$proposedProbability;
		{
			// Unrolled loop
			{
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				// 
				// Substituted "cv$temp$1$var15" with its value "1.0".
				// 
				// Set the current value to the current state of the tree.
				// 
				// The original value of the sample
				double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(t, 1.0, 1.0);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == a)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == b)) {
						if((0 == c)) {
							// Processing sample task 103 of consumer random variable bernoulli.
							for(int var96 = 0; var96 < samples; var96 += 1)
								// A check to ensure rounding of floating point values can never result in a negative
								// value.
								// 
								// Recorded the probability of reaching sample task 103 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								// 
								// Variable declaration of cv$accumulatedConsumerProbabilities moved.
								// Declaration comment was:
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								// 
								// cv$temp$2$var84's comment
								// Variable declaration of cv$temp$2$var84 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								// 
								// The original value of the sample
								cv$accumulatedProbabilities = (Math.log((flips[var96]?t:(1.0 - t))) + cv$accumulatedProbabilities);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c)) {
							// Set the current value to the current state of the tree.
							// 
							// The original value of the sample
							double traceTempVariable$var84$6_2 = (1 - t);
							
							// Processing sample task 103 of consumer random variable bernoulli.
							for(int var96 = 0; var96 < samples; var96 += 1)
								// A check to ensure rounding of floating point values can never result in a negative
								// value.
								// 
								// Recorded the probability of reaching sample task 103 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								// 
								// Variable declaration of cv$accumulatedConsumerProbabilities moved.
								// Declaration comment was:
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								// 
								// cv$temp$3$var84's comment
								// Constructing a random variable input for use later.
								cv$accumulatedProbabilities = (Math.log((flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))) + cv$accumulatedProbabilities);
						}
					}
					if(((1 == b) && (1 == c))) {
						// Processing sample task 103 of consumer random variable bernoulli.
						for(int var96 = 0; var96 < samples; var96 += 1)
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 103 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// cv$temp$4$var84's comment
							// Variable declaration of cv$temp$4$var84 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							// 
							// The original value of the sample
							cv$accumulatedProbabilities = (Math.log((flips[var96]?t:(1.0 - t))) + cv$accumulatedProbabilities);
					}
				}
				if((((1 == a) && (0 == b)) && (0 == c))) {
					// Processing sample task 103 of consumer random variable bernoulli.
					for(int var96 = 0; var96 < samples; var96 += 1)
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 103 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// cv$temp$5$var84's comment
						// Variable declaration of cv$temp$5$var84 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Set the current value to the current state of the tree.
						// 
						// The original value of the sample
						cv$accumulatedProbabilities = (Math.log((flips[var96]?t:(1.0 - t))) + cv$accumulatedProbabilities);
				}
				
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
			// Write out the new value of the sample.
			t = cv$proposedValue;
			double[][] var21 = bias[0];
			double[] var23 = var21[0];
			var23[0] = cv$proposedValue;
			var23[1] = (1 - cv$proposedValue);
			var21[1][1] = cv$proposedValue;
			bias[1][0][0] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var15" with its value "1.0".
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == a)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == b)) {
					if((0 == c)) {
						// Processing sample task 103 of consumer random variable bernoulli.
						for(int var96 = 0; var96 < samples; var96 += 1)
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 103 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// cv$temp$2$var84's comment
							// Variable declaration of cv$temp$2$var84 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedProbabilities = (Math.log((flips[var96]?cv$proposedValue:(1.0 - cv$proposedValue))) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c)) {
						double traceTempVariable$var84$6_2 = (1 - cv$proposedValue);
						
						// Processing sample task 103 of consumer random variable bernoulli.
						for(int var96 = 0; var96 < samples; var96 += 1)
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 103 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// cv$temp$3$var84's comment
							// Constructing a random variable input for use later.
							cv$accumulatedProbabilities = (Math.log((flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))) + cv$accumulatedProbabilities);
					}
				}
				if(((1 == b) && (1 == c))) {
					// Processing sample task 103 of consumer random variable bernoulli.
					for(int var96 = 0; var96 < samples; var96 += 1)
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 103 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// cv$temp$4$var84's comment
						// Variable declaration of cv$temp$4$var84 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedProbabilities = (Math.log((flips[var96]?cv$proposedValue:(1.0 - cv$proposedValue))) + cv$accumulatedProbabilities);
				}
			}
			if((((1 == a) && (0 == b)) && (0 == c))) {
				// Processing sample task 103 of consumer random variable bernoulli.
				for(int var96 = 0; var96 < samples; var96 += 1)
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 103 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$5$var84's comment
					// Variable declaration of cv$temp$5$var84 moved.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (Math.log((flips[var96]?cv$proposedValue:(1.0 - cv$proposedValue))) + cv$accumulatedProbabilities);
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$proposedProbability = cv$accumulatedProbabilities;
		}
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$proposedProbability - cv$originalProbability)))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the new value of the sample.
			t = cv$originalValue;
			double[][] var21 = bias[0];
			double[] var23 = var21[0];
			
			// t's comment
			// Write out the new value of the sample.
			var23[0] = cv$originalValue;
			
			// t's comment
			// Write out the new value of the sample.
			var23[1] = (1 - cv$originalValue);
			
			// t's comment
			// Write out the new value of the sample.
			var21[1][1] = cv$originalValue;
			
			// t's comment
			// Write out the new value of the sample.
			bias[1][0][0] = cv$originalValue;
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
		// Constructor for bias
		bias = new double[2][][];
		double[][] subarray$0 = new double[2][];
		bias[0] = subarray$0;
		subarray$0[0] = new double[2];
		subarray$0[1] = new double[2];
		double[][] subarray$1 = new double[2][];
		bias[1] = subarray$1;
		subarray$1[0] = new double[2];
		subarray$1[1] = new double[2];
		
		// Constructor for flips
		flips = new boolean[samples];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample11)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample17) {
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			double[][] var21 = bias[0];
			double[] var23 = var21[0];
			var23[0] = t;
			var23[1] = (1 - t);
			double[] var36 = var21[1];
			var36[0] = (1 - q);
			var36[1] = t;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample11) {
			double[][] var52 = bias[1];
			double[] var54 = var52[0];
			var54[0] = t;
			var54[1] = (1 - q);
			double[] var67 = var52[1];
			var67[0] = (1 - q);
			var67[1] = q;
		}
		for(int var96 = 0; var96 < samples; var96 += 1)
			flips[var96] = DistributionSampling.sampleBernoulli(RNG$, bias[a][b][c]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample11)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample17) {
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			double[][] var21 = bias[0];
			double[] var23 = var21[0];
			var23[0] = t;
			var23[1] = (1 - t);
			double[] var36 = var21[1];
			var36[0] = (1 - q);
			var36[1] = t;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample11) {
			double[][] var52 = bias[1];
			double[] var54 = var52[0];
			var54[0] = t;
			var54[1] = (1 - q);
			double[] var67 = var52[1];
			var67[0] = (1 - q);
			var67[1] = q;
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample11)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample17) {
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			double[][] var21 = bias[0];
			double[] var23 = var21[0];
			var23[0] = t;
			var23[1] = (1 - t);
			double[] var36 = var21[1];
			var36[0] = (1 - q);
			var36[1] = t;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample11) {
			double[][] var52 = bias[1];
			double[] var54 = var52[0];
			var54[0] = t;
			var54[1] = (1 - q);
			double[] var67 = var52[1];
			var67[0] = (1 - q);
			var67[1] = q;
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample11)
				sample11();
			if(!fixedFlag$sample17)
				sample17();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample17)
				sample17();
			if(!fixedFlag$sample11)
				sample11();
		}
		
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
		logProbability$var10 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$q = Double.NaN;
		logProbability$var16 = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$t = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample103)
			logProbability$var97 = Double.NaN;
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
		if(fixedFlag$sample11)
			logProbabilityValue$sample11();
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		logProbabilityValue$sample103();
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
		logProbabilityValue$sample11();
		logProbabilityValue$sample17();
		logProbabilityValue$sample103();
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
		logProbabilityValue$sample11();
		logProbabilityValue$sample17();
		logProbabilityValue$sample103();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample11)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample17) {
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			double[][] var21 = bias[0];
			double[] var23 = var21[0];
			var23[0] = t;
			var23[1] = (1 - t);
			double[] var36 = var21[1];
			var36[0] = (1 - q);
			var36[1] = t;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample11) {
			double[][] var52 = bias[1];
			double[] var54 = var52[0];
			var54[0] = t;
			var54[1] = (1 - q);
			double[] var67 = var52[1];
			var67[0] = (1 - q);
			var67[1] = q;
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
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample17) {
			double[][] var21 = bias[0];
			double[] var23 = var21[0];
			var23[0] = t;
			var23[1] = (1 - t);
			double[] var36 = var21[1];
			var36[0] = (1 - q);
			var36[1] = t;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample11) {
			double[][] var52 = bias[1];
			double[] var54 = var52[0];
			var54[0] = t;
			var54[1] = (1 - q);
			double[] var67 = var52[1];
			var67[0] = (1 - q);
			var67[1] = q;
		}
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Flip1CoinMK18(int samples, int a, int b, int c, boolean[] flipsMeasured) {\n"
		     + "    \n"
		     + "    double q = beta(1,1).sample();\n"
		     + "    double t = beta(1,1).sample();\n"
		     + "    double[][][] bias = {{{t, 1-t},{1-q, t}},{{t, 1-q},{1-q, q}}};\n"
		     + "    \n"
		     + "    Bernoulli bernoulli = bernoulli(bias[a][b][c]);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "    \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}