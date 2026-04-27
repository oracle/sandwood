package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK18$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK18.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK18$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK18$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample11
	private final void drawValueSample11() {
		state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		state.bias[0][1][0] = (1 - state.q);
		double[][] var52 = state.bias[1];
		var52[0][1] = (1 - state.q);
		double[] var67 = var52[1];
		var67[0] = (1 - state.q);
		var67[1] = state.q;
	}

	// Pick a value from the distribution for the unconditioned variable from sample17
	private final void drawValueSample17() {
		state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		var23[0] = state.t;
		var23[1] = (1 - state.t);
		var21[1][1] = state.t;
		state.bias[1][0][0] = state.t;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 11 drawn from Beta 10. Inference was performed using Metropolis-Hastings.
	private final void inferSample11() {
		state.constrainedFlag$sample11 = false;
		
		// The original value of the sample
		double cv$originalValue = state.q;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		// 
						// The original value of the sample
		double cv$var = ((state.q * state.q) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		// 
		// The original value of the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.q);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			// 
			// The original value of the sample
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(state.q, 1.0, 1.0);
			
			// Looking for a path between Sample 11 and consumer Bernoulli 85.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((((0 == state.a) && (1 == state.b)) && (0 == state.c))) {
				// Set the current value to the current state of the tree.
				// 
				// The original value of the sample
				double traceTempVariable$var84$5_2 = (1 - state.q);
				
				// Processing sample task 103 of consumer random variable bernoulli.
				for(int var96 = 0; var96 < state.samples; var96 += 1) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample11 = true;
					
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
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$5_2) && (traceTempVariable$var84$5_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$5_2:(1.0 - traceTempVariable$var84$5_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.a)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 == state.b) && (1 == state.c))) {
					// Set the current value to the current state of the tree.
					// 
					// The original value of the sample
					double traceTempVariable$var84$6_2 = (1 - state.q);
					
					// Processing sample task 103 of consumer random variable bernoulli.
					for(int var96 = 0; var96 < state.samples; var96 += 1) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample11 = true;
						
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
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.b)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c)) {
						// Set the current value to the current state of the tree.
						// 
						// The original value of the sample
						double traceTempVariable$var84$7_2 = (1 - state.q);
						
						// Processing sample task 103 of consumer random variable bernoulli.
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample11 = true;
							
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
							cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$7_2) && (traceTempVariable$var84$7_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$7_2:(1.0 - traceTempVariable$var84$7_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c)) {
						// Processing sample task 103 of consumer random variable bernoulli.
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample11 = true;
							
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
																					// Set the current value to the current state of the tree.
							// 
							// The original value of the sample
							cv$accumulatedProbabilities = ((((0.0 <= state.q) && (state.q <= 1.0))?Math.log((state.flips[var96]?state.q:(1.0 - state.q))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
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
		if(state.constrainedFlag$sample11) {
			{
				// Update Sample and intermediate values
				// 
				// Write out the new value of the sample.
				state.q = cv$proposedValue;
				state.bias[0][1][0] = (1 - cv$proposedValue);
				double[][] var52 = state.bias[1];
				var52[0][1] = (1 - cv$proposedValue);
				double[] var67 = var52[1];
				var67[0] = (1 - cv$proposedValue);
				var67[1] = cv$proposedValue;
			}
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			
			// Looking for a path between Sample 11 and consumer Bernoulli 85.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((((0 == state.a) && (1 == state.b)) && (0 == state.c))) {
				double traceTempVariable$var84$5_2 = (1 - cv$proposedValue);
				
				// Processing sample task 103 of consumer random variable bernoulli.
				for(int var96 = 0; var96 < state.samples; var96 += 1) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample11 = true;
					
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
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$5_2) && (traceTempVariable$var84$5_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$5_2:(1.0 - traceTempVariable$var84$5_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.a)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 == state.b) && (1 == state.c))) {
					double traceTempVariable$var84$6_2 = (1 - cv$proposedValue);
					
					// Processing sample task 103 of consumer random variable bernoulli.
					for(int var96 = 0; var96 < state.samples; var96 += 1) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample11 = true;
						
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
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.b)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c)) {
						double traceTempVariable$var84$7_2 = (1 - cv$proposedValue);
						
						// Processing sample task 103 of consumer random variable bernoulli.
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample11 = true;
							
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
							cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$7_2) && (traceTempVariable$var84$7_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$7_2:(1.0 - traceTempVariable$var84$7_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c)) {
						// Processing sample task 103 of consumer random variable bernoulli.
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample11 = true;
							
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
							cv$accumulatedProbabilities = ((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((state.flips[var96]?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
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
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				// If it is not revert the changes.
				// 
				// Set the sample value
				// Write out the new value of the sample.
				state.q = cv$originalValue;
				
								// q's comment
				// Write out the new value of the sample.
				state.bias[0][1][0] = (1 - cv$originalValue);
				double[][] var52 = state.bias[1];
				
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
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 17 drawn from Beta 16. Inference was performed using Metropolis-Hastings.
	private final void inferSample17() {
		state.constrainedFlag$sample17 = false;
		
		// The original value of the sample
		double cv$originalValue = state.t;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		// 
						// The original value of the sample
		double cv$var = ((state.t * state.t) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		// 
		// The original value of the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.t);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			// 
			// The original value of the sample
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(state.t, 1.0, 1.0);
			
			// Looking for a path between Sample 17 and consumer Bernoulli 85.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.a)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.b)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c)) {
						// Processing sample task 103 of consumer random variable bernoulli.
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample17 = true;
							
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
																					// Set the current value to the current state of the tree.
							// 
							// The original value of the sample
							cv$accumulatedProbabilities = ((((0.0 <= state.t) && (state.t <= 1.0))?Math.log((state.flips[var96]?state.t:(1.0 - state.t))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c)) {
						// Set the current value to the current state of the tree.
						// 
						// The original value of the sample
						double traceTempVariable$var84$6_2 = (1 - state.t);
						
						// Processing sample task 103 of consumer random variable bernoulli.
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample17 = true;
							
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
							cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((1 == state.b) && (1 == state.c))) {
					// Processing sample task 103 of consumer random variable bernoulli.
					for(int var96 = 0; var96 < state.samples; var96 += 1) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample17 = true;
						
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
																		// Set the current value to the current state of the tree.
						// 
						// The original value of the sample
						cv$accumulatedProbabilities = ((((0.0 <= state.t) && (state.t <= 1.0))?Math.log((state.flips[var96]?state.t:(1.0 - state.t))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((((1 == state.a) && (0 == state.b)) && (0 == state.c))) {
				// Processing sample task 103 of consumer random variable bernoulli.
				for(int var96 = 0; var96 < state.samples; var96 += 1) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample17 = true;
					
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
															// Set the current value to the current state of the tree.
					// 
					// The original value of the sample
					cv$accumulatedProbabilities = ((((0.0 <= state.t) && (state.t <= 1.0))?Math.log((state.flips[var96]?state.t:(1.0 - state.t))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
		if(state.constrainedFlag$sample17) {
			{
				// Update Sample and intermediate values
				// 
				// Write out the new value of the sample.
				state.t = cv$proposedValue;
				double[][] var21 = state.bias[0];
				double[] var23 = var21[0];
				var23[0] = cv$proposedValue;
				var23[1] = (1 - cv$proposedValue);
				var21[1][1] = cv$proposedValue;
				state.bias[1][0][0] = cv$proposedValue;
			}
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			
			// Looking for a path between Sample 17 and consumer Bernoulli 85.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.a)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.b)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c)) {
						// Processing sample task 103 of consumer random variable bernoulli.
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample17 = true;
							
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
							cv$accumulatedProbabilities = ((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((state.flips[var96]?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c)) {
						double traceTempVariable$var84$6_2 = (1 - cv$proposedValue);
						
						// Processing sample task 103 of consumer random variable bernoulli.
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample17 = true;
							
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
							cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((1 == state.b) && (1 == state.c))) {
					// Processing sample task 103 of consumer random variable bernoulli.
					for(int var96 = 0; var96 < state.samples; var96 += 1) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample17 = true;
						
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
						cv$accumulatedProbabilities = ((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((state.flips[var96]?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((((1 == state.a) && (0 == state.b)) && (0 == state.c))) {
				// Processing sample task 103 of consumer random variable bernoulli.
				for(int var96 = 0; var96 < state.samples; var96 += 1) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample17 = true;
					
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
					cv$accumulatedProbabilities = ((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((state.flips[var96]?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				// If it is not revert the changes.
				// 
				// Set the sample value
				// Write out the new value of the sample.
				state.t = cv$originalValue;
				double[][] var21 = state.bias[0];
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
				state.bias[1][0][0] = cv$originalValue;
			}
		}
	}

	// Calculate the probability of the samples represented by sample103 using sampled
	// values.
	private final void logProbabilityValue$sample103() {
		// Determine if we need to calculate the values for sample task 103 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample103) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var96 = 0; var96 < state.samples; var96 += 1) {
				double var84 = state.bias[state.a][state.b][state.c];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[var96]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
			}
			state.logProbability$bernoulli = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			state.logProbability$var97 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$flips = (state.logProbability$flips + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample103 = (state.fixedFlag$sample11 && state.fixedFlag$sample17);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			state.logProbability$bernoulli = state.logProbability$var97;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$flips = (state.logProbability$flips + state.logProbability$var97);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var97);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var97);
		}
	}

	// Calculate the probability of the samples represented by sample11 using sampled
	// values.
	private final void logProbabilityValue$sample11() {
		// Determine if we need to calculate the values for sample task 11 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample11) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.q, 1.0, 1.0);
			
			// Store the sample task probability
			state.logProbability$q = cv$distributionAccumulator;
			
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
			state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			
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
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample11)
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
			state.fixedProbFlag$sample11 = state.fixedFlag$sample11;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$q);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$q);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample11)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$q);
		}
	}

	// Calculate the probability of the samples represented by sample17 using sampled
	// values.
	private final void logProbabilityValue$sample17() {
		// Determine if we need to calculate the values for sample task 17 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample17) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.t, 1.0, 1.0);
			
			// Store the sample task probability
			state.logProbability$t = cv$distributionAccumulator;
			
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
			state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			
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
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample17)
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
			state.fixedProbFlag$sample17 = state.fixedFlag$sample17;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$t);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$t);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample17)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$t);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample11)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample17) {
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			double[][] var21 = state.bias[0];
			double[] var23 = var21[0];
			var23[0] = state.t;
			var23[1] = (1 - state.t);
			double[] var36 = var21[1];
			var36[0] = (1 - state.q);
			var36[1] = state.t;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample11) {
			double[][] var52 = state.bias[1];
			double[] var54 = var52[0];
			var54[0] = state.t;
			var54[1] = (1 - state.q);
			double[] var67 = var52[1];
			var67[0] = (1 - state.q);
			var67[1] = state.q;
		}
		for(int var96 = 0; var96 < state.samples; var96 += 1)
			state.flips[var96] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.a][state.b][state.c]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample11)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample17)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		var23[0] = state.t;
		var23[1] = (1 - state.t);
		double[] var36 = var21[1];
		var36[0] = (1 - state.q);
		var36[1] = state.t;
		double[][] var52 = state.bias[1];
		double[] var54 = var52[0];
		var54[0] = state.t;
		var54[1] = (1 - state.q);
		double[] var67 = var52[1];
		var67[0] = (1 - state.q);
		var67[1] = state.q;
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample11)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample17)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		var23[0] = state.t;
		var23[1] = (1 - state.t);
		double[] var36 = var21[1];
		var36[0] = (1 - state.q);
		var36[1] = state.t;
		double[][] var52 = state.bias[1];
		double[] var54 = var52[0];
		var54[0] = state.t;
		var54[1] = (1 - state.q);
		double[] var67 = var52[1];
		var67[0] = (1 - state.q);
		var67[1] = state.q;
		for(int var96 = 0; var96 < state.samples; var96 += 1)
			state.flips[var96] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.a][state.b][state.c]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample11)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample17) {
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			double[][] var21 = state.bias[0];
			double[] var23 = var21[0];
			var23[0] = state.t;
			var23[1] = (1 - state.t);
			double[] var36 = var21[1];
			var36[0] = (1 - state.q);
			var36[1] = state.t;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample11) {
			double[][] var52 = state.bias[1];
			double[] var54 = var52[0];
			var54[0] = state.t;
			var54[1] = (1 - state.q);
			double[] var67 = var52[1];
			var67[0] = (1 - state.q);
			var67[1] = state.q;
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample11)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample17)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		var23[0] = state.t;
		var23[1] = (1 - state.t);
		double[] var36 = var21[1];
		var36[0] = (1 - state.q);
		var36[1] = state.t;
		double[][] var52 = state.bias[1];
		double[] var54 = var52[0];
		var54[0] = state.t;
		var54[1] = (1 - state.q);
		double[] var67 = var52[1];
		var67[0] = (1 - state.q);
		var67[1] = state.q;
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample11)
				inferSample11();
			if(!state.fixedFlag$sample17)
				inferSample17();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!state.fixedFlag$sample17)
				inferSample17();
			if(!state.fixedFlag$sample11)
				inferSample11();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample11)
			drawValueSample11();
		if(!state.constrainedFlag$sample17)
			drawValueSample17();
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
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample11)
			state.logProbability$q = Double.NaN;
		if(!state.fixedProbFlag$sample17)
			state.logProbability$t = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample103)
			state.logProbability$var97 = Double.NaN;
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
		if(state.fixedFlag$sample11)
			logProbabilityValue$sample11();
		if(state.fixedFlag$sample17)
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

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.flips[cv$index1] = state.flipsMeasured[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		var23[0] = state.t;
		var23[1] = (1 - state.t);
		double[] var36 = var21[1];
		var36[0] = (1 - state.q);
		var36[1] = state.t;
		double[][] var52 = state.bias[1];
		double[] var54 = var52[0];
		var54[0] = state.t;
		var54[1] = (1 - state.q);
		double[] var67 = var52[1];
		var67[0] = (1 - state.q);
		var67[1] = state.q;
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