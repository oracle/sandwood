package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK13$SingleThreadCPU extends CoreModelSingleThreadCPU implements Flip1CoinMK13$CoreInterface {

	// Declare the variables for the model.
<<<<<<< Upstream, based on POW
	private double b;
	private double bias;
	private boolean constrainedFlag$sample9 = true;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample40 = false;
	private boolean fixedProbFlag$sample9 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private boolean guard1;
	private boolean guard2;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$var36;
	private int samples;
	private boolean system$gibbsForward = true;
=======
	double b;
	double bias;
	boolean constrainedFlag$sample9 = true;
	boolean fixedFlag$sample9 = false;
	boolean fixedProbFlag$sample40 = false;
	boolean fixedProbFlag$sample9 = false;
	boolean[] flips;
	boolean[] flipsMeasured;
	boolean guard1;
	boolean guard2;
	int length$flipsMeasured;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$b;
	double logProbability$bernoulli;
	double logProbability$bias;
	double logProbability$flips;
	double logProbability$sample9;
	double logProbability$var36;
	int samples;
	boolean system$gibbsForward = true;
>>>>>>> daee89e Adding in a class to hold just the state. This will be worked on further as the code generation progresses. Commit before adding inner classes to the outer classes. Updating output class structure checkpoint Checkpoint in the restructuring of the output classes to increase the shared code. Finished restructuring the classes, time to start using inner classes. Updates to tree structure Changing the structure of get field so that it can be used to get other types of field, read for getting data out of the scratch and model data classes. Removing unused imports Adding nodes to allow fields in an object ot be set. Moving rng package so that we can add other internal only variable types. Updates to the handling of transformations. Moving from sets to lists of generics Updating the structure of inner class. Changing the passing of fields to sub classes. Updating class structure

	public Flip1CoinMK13$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for b.
	@Override
	public final double get$b() {
		return b;
	}

	// Setter for b.
	@Override
	public final void set$b(double cv$value, boolean allocated$) {
		// Set flags for all the side effects of b including if probabilities need to be updated.
		b = cv$value;
		
		// Unset the fixed probability flag for sample 9 as it depends on b.
		fixedProbFlag$sample9 = false;
		
		// Unset the fixed probability flag for sample 40 as it depends on b.
		fixedProbFlag$sample40 = false;
	}

	// Getter for bias.
	@Override
	public final double get$bias() {
		return bias;
	}

	// Getter for fixedFlag$sample9.
	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	// Setter for fixedFlag$sample9.
	@Override
	public final void set$fixedFlag$sample9(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample9 including if probabilities
		// need to be updated.
		fixedFlag$sample9 = cv$value;
		
		// Substituted "fixedFlag$sample9" with its value "cv$value".
		constrainedFlag$sample9 = (cv$value || constrainedFlag$sample9);
		
		// Should the probability of sample 9 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample9" with its value "cv$value".
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
		
		// Should the probability of sample 40 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample9" with its value "cv$value".
		fixedProbFlag$sample40 = (cv$value && fixedProbFlag$sample40);
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
	public final void set$flipsMeasured(boolean[] cv$value, boolean allocated$) {
		flipsMeasured = cv$value;
	}

	// Getter for guard1.
	@Override
	public final boolean get$guard1() {
		return guard1;
	}

	// Setter for guard1.
	@Override
	public final void set$guard1(boolean cv$value, boolean allocated$) {
		guard1 = cv$value;
	}

	// Getter for guard2.
	@Override
	public final boolean get$guard2() {
		return guard2;
	}

	// Setter for guard2.
	@Override
	public final void set$guard2(boolean cv$value, boolean allocated$) {
		guard2 = cv$value;
	}

	// Getter for length$flipsMeasured.
	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	// Setter for length$flipsMeasured.
	@Override
	public final void set$length$flipsMeasured(int cv$value, boolean allocated$) {
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

	// Getter for logProbability$b.
	@Override
	public final double get$logProbability$b() {
		return logProbability$b;
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

	// Pick a value from the distribution for the unconditioned variable from sample9
	private final void drawValueSample9() {
		b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		// Guards to ensure that bias is only updated when there is a valid path.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(guard1)
			bias = b;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(guard2)
				bias = (b / 2);
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else
				bias = (b / 3);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 9 drawn from Beta 8. Inference was performed using Metropolis-Hastings.
	private final void inferSample9() {
		constrainedFlag$sample9 = false;
		
		// The original value of the sample
		double cv$originalValue = b;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		// 
						// The original value of the sample
		double cv$var = (((b < 0)?(-b):b) * 40.0);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.01))
			cv$var = 0.01;
		
		// The proposed new value for the sample
		// 
		// The original value of the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + b);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			// 
			// The original value of the sample
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(b, 1.0, 1.0);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(guard1) {
				// Processing sample task 40 of consumer random variable bernoulli.
				for(int var35 = 0; var35 < samples; var35 += 1) {
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample9 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 40 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Set the current value to the current state of the tree.
					// 
					// The original value of the sample
					cv$accumulatedProbabilities = ((((0.0 <= b) && (b <= 1.0))?Math.log((flips[var35]?b:(1.0 - b))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(guard2) {
					// Set the current value to the current state of the tree.
					// 
					// The original value of the sample
					double traceTempVariable$bias$5_2 = (b / 2);
					
					// Processing sample task 40 of consumer random variable bernoulli.
					for(int var35 = 0; var35 < samples; var35 += 1) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample9 = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 40 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$bias$5_2) && (traceTempVariable$bias$5_2 <= 1.0))?Math.log((flips[var35]?traceTempVariable$bias$5_2:(1.0 - traceTempVariable$bias$5_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					// Set the current value to the current state of the tree.
					// 
					// The original value of the sample
					double traceTempVariable$bias$6_2 = (b / 3);
					
					// Processing sample task 40 of consumer random variable bernoulli.
					for(int var35 = 0; var35 < samples; var35 += 1) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample9 = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 40 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$bias$6_2) && (traceTempVariable$bias$6_2 <= 1.0))?Math.log((flips[var35]?traceTempVariable$bias$6_2:(1.0 - traceTempVariable$bias$6_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(constrainedFlag$sample9) {
			// Update Sample and intermediate values
			// 
			// Write out the new value of the sample.
			b = cv$proposedValue;
			
			// Guards to ensure that bias is only updated when there is a valid path.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(guard1)
				bias = cv$proposedValue;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(guard2)
					bias = (cv$proposedValue / 2);
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else
					bias = (cv$proposedValue / 3);
			}
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(guard1) {
				// Processing sample task 40 of consumer random variable bernoulli.
				for(int var35 = 0; var35 < samples; var35 += 1) {
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample9 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 40 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((flips[var35]?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(guard2) {
					double traceTempVariable$bias$5_2 = (cv$proposedValue / 2);
					
					// Processing sample task 40 of consumer random variable bernoulli.
					for(int var35 = 0; var35 < samples; var35 += 1) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample9 = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 40 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$bias$5_2) && (traceTempVariable$bias$5_2 <= 1.0))?Math.log((flips[var35]?traceTempVariable$bias$5_2:(1.0 - traceTempVariable$bias$5_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					double traceTempVariable$bias$6_2 = (cv$proposedValue / 3);
					
					// Processing sample task 40 of consumer random variable bernoulli.
					for(int var35 = 0; var35 < samples; var35 += 1) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample9 = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 40 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$bias$6_2) && (traceTempVariable$bias$6_2 <= 1.0))?Math.log((flips[var35]?traceTempVariable$bias$6_2:(1.0 - traceTempVariable$bias$6_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
				// If it is not revert the changes.
				// 
				// Set the sample value
				// Write out the new value of the sample.
				b = cv$originalValue;
				
				// Guards to ensure that bias is only updated when there is a valid path.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(guard1)
										// b's comment
					// Write out the new value of the sample.
					bias = cv$originalValue;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(guard2)
												// b's comment
						// Write out the new value of the sample.
						bias = (cv$originalValue / 2);
					// Constraints moved from conditionals in inner loops/scopes/etc.
					else
												// b's comment
						// Write out the new value of the sample.
						bias = (cv$originalValue / 3);
				}
			}
		}
	}

	// Calculate the probability of the samples represented by sample40 using sampled
	// values.
	private final void logProbabilityValue$sample40() {
		// Determine if we need to calculate the values for sample task 40 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample40) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var35 = 0; var35 < samples; var35 += 1) {
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
				// 
				// The sample value to calculate the probability of generating
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= bias) && (bias <= 1.0))?Math.log((flips[var35]?bias:(1.0 - bias))):Double.NEGATIVE_INFINITY));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				logProbability$bernoulli = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				logProbability$var36 = cv$sampleAccumulator;
			}
			
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
			fixedProbFlag$sample40 = fixedFlag$sample9;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if((0 < samples))
				// Record that the sample was reached.
				cv$sampleReached = true;
			if(cv$sampleReached)
				logProbability$bernoulli = logProbability$var36;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$flips = (logProbability$flips + logProbability$var36);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var36);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var36);
		}
	}

	// Calculate the probability of the samples represented by sample9 using sampled values.
	private final void logProbabilityValue$sample9() {
		// Determine if we need to calculate the values for sample task 9 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample9) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(b, 1.0, 1.0);
			
			// Store the sample task probability
			logProbability$b = cv$distributionAccumulator;
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Add probability to constructed variables from the combined probability
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(guard1)
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
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Guard to ensure that bias is only updated once for this probability.
				if(guard2) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
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
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((!guard2 && !cv$guard$bias))
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
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
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
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Add probability to constructed variables from the combined probability
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(guard1)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$bias = (logProbability$bias + logProbability$b);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Guard to ensure that bias is only updated once for this probability.
				if(guard2) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					// 
					// Variable declaration of cv$accumulator moved.
					logProbability$bias = (logProbability$bias + logProbability$b);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((!guard2 && !cv$guard$bias))
					// Update the variable probability
					// 
					// Variable declaration of cv$accumulator moved.
					logProbability$bias = (logProbability$bias + logProbability$b);
			}
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$b);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$b);
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocate() {
		// Constructor for flips
		flips = new boolean[length$flipsMeasured];
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample9) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(guard1)
				bias = b;
			else {
				if(guard2)
					bias = (b / 2);
				else
					bias = (b / 3);
			}
		}
		for(int var35 = 0; var35 < samples; var35 += 1)
			flips[var35] = DistributionSampling.sampleBernoulli(RNG$, bias);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample9)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample9)
				bias = b;
		} else {
			if(guard2)
				bias = (b / 2);
			else
				bias = (b / 3);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample9)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample9)
				bias = b;
		} else {
			if(guard2)
				bias = (b / 2);
			else
				bias = (b / 3);
		}
		for(int var35 = 0; var35 < samples; var35 += 1)
			flips[var35] = DistributionSampling.sampleBernoulli(RNG$, bias);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample9) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(guard1)
				bias = b;
			else {
				if(guard2)
					bias = (b / 2);
				else
					bias = (b / 3);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample9)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample9)
				bias = b;
		} else {
			if(guard2)
				bias = (b / 2);
			else
				bias = (b / 3);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample9)
			inferSample9();
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample9)
			drawValueSample9();
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
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$b = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample40)
			logProbability$var36 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		samples = length$flipsMeasured;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample9)
			logProbabilityValue$sample9();
		logProbabilityValue$sample40();
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
		logProbabilityValue$sample9();
		logProbabilityValue$sample40();
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
		logProbabilityValue$sample9();
		logProbabilityValue$sample40();
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
			if(fixedFlag$sample9)
				bias = b;
		} else {
			if(guard2)
				bias = (b / 2);
			else
				bias = (b / 3);
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
		     + "public model Flip1CoinMK13(boolean[] flipsMeasured, boolean guard1, boolean guard2) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double b = beta(1.0, 1).sample();\n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = b;\n"
		     + "    else { \n"
		     + "        if(guard2) {\n"
		     + "            bias = b/2;\n"
		     + "        } else\n"
		     + "            bias = b/3;\n"
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