package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.LogitRegressionTest$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.LogitRegressionTest.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LogitRegressionTest$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		boolean[][] guard$sample35bernoulli93$global;
		boolean[][] guard$sample35put89$global;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for guard$sample35put89$global
			{
				// Calculate the largest index of j that is possible and allocate an array to hold
				// the guard for each of these.
				int cv$max_j$var85 = 0;
				if((0 < state.x.length))
					cv$max_j$var85 = 3;
				
				// Allocation of guard$sample35put89$global for single threaded execution
				guard$sample35put89$global = new boolean[state.x.length][cv$max_j$var85];
			}
			
			// Constructor for guard$sample35bernoulli93$global
			// 
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var85 = 0;
			if((0 < state.x.length))
				cv$max_j$var85 = 3;
			
			// Allocation of guard$sample35bernoulli93$global for single threaded execution
			guard$sample35bernoulli93$global = new boolean[state.x.length][cv$max_j$var85];
		}
	}


	public LogitRegressionTest$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample35
	private final void drawValueSample35(int var33) {
		state.weights[var33] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		
		// Guards to ensure that indicator is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 35 and consumer double[] 67.
		// 
		// Unrolled loop
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var33 == 0)) {
			for(int i = 0; i < state.n; i += 1)
												// Substituted "j$var61" with its value "0".
				state.indicator[i][0] = Math.exp((state.weights[0] * state.x[i][0]));
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var33 == 1)) {
			for(int i = 0; i < state.n; i += 1)
												// Substituted "j$var61" with its value "1".
				state.indicator[i][1] = Math.exp((state.weights[1] * state.x[i][1]));
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var33 == 2)) {
			for(int i = 0; i < state.n; i += 1)
												// Substituted "j$var61" with its value "2".
				state.indicator[i][2] = Math.exp((state.weights[2] * state.x[i][2]));
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var33 == 0)) {
			// Unrolled loop
			for(int i = 0; i < state.n; i += 1) {
				// Unrolled loop
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample35put89$global[i][0] = false;
				
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample35put89$global[i][1] = false;
				
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample35put89$global[i][2] = false;
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var33 == 1)) {
			// Unrolled loop
			for(int i = 0; i < state.n; i += 1) {
				// Unrolled loop
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample35put89$global[i][0] = false;
				
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample35put89$global[i][1] = false;
				
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample35put89$global[i][2] = false;
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var33 == 2)) {
			// Unrolled loop
			for(int i = 0; i < state.n; i += 1) {
				// Unrolled loop
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample35put89$global[i][0] = false;
				
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample35put89$global[i][1] = false;
				
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample35put89$global[i][2] = false;
			}
		}
		
		// Unrolled loop
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var33 == 0)) {
			// Unrolled loop
			for(int i = 0; i < state.n; i += 1)
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample35put89$global[i][0] = false;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var33 == 1)) {
			// Unrolled loop
			for(int i = 0; i < state.n; i += 1)
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample35put89$global[i][1] = false;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var33 == 2)) {
			// Unrolled loop
			for(int i = 0; i < state.n; i += 1)
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample35put89$global[i][2] = false;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var33 == 0)) {
			// Unrolled loop
			for(int i = 0; i < state.n; i += 1) {
				// Unrolled loop
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample35put89$global[i][0]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][0] = true;
					
															// Substituted "j$var85" with its value "0".
					state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample35put89$global[i][1]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][1] = true;
					
															// Substituted "j$var85" with its value "1".
					state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample35put89$global[i][2]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][2] = true;
					
															// Substituted "j$var85" with its value "2".
					state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var33 == 1)) {
			// Unrolled loop
			for(int i = 0; i < state.n; i += 1) {
				// Unrolled loop
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample35put89$global[i][0]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][0] = true;
					
															// Substituted "j$var85" with its value "0".
					state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample35put89$global[i][1]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][1] = true;
					
															// Substituted "j$var85" with its value "1".
					state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample35put89$global[i][2]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][2] = true;
					
															// Substituted "j$var85" with its value "2".
					state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var33 == 2)) {
			// Unrolled loop
			for(int i = 0; i < state.n; i += 1) {
				// Unrolled loop
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample35put89$global[i][0]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][0] = true;
					
															// Substituted "j$var85" with its value "0".
					state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample35put89$global[i][1]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][1] = true;
					
															// Substituted "j$var85" with its value "1".
					state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample35put89$global[i][2]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][2] = true;
					
															// Substituted "j$var85" with its value "2".
					state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var33 == 0)) {
			// Unrolled loop
			for(int i = 0; i < state.n; i += 1) {
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!scratch.guard$sample35put89$global[i][0]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][0] = true;
					
															// Substituted "j$var85" with its value "0".
					state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var33 == 1)) {
			// Unrolled loop
			for(int i = 0; i < state.n; i += 1) {
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!scratch.guard$sample35put89$global[i][1]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][1] = true;
					
															// Substituted "j$var85" with its value "1".
					state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var33 == 2)) {
			// Unrolled loop
			for(int i = 0; i < state.n; i += 1) {
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!scratch.guard$sample35put89$global[i][2]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][2] = true;
					
															// Substituted "j$var85" with its value "2".
					state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
			}
		}
	}

	// Pick a value from the distribution for the unconditioned variable from sample42
	private final void drawValueSample42() {
		state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 35 drawn from Gaussian 22. Inference was performed using Metropolis-Hastings.
	private final void inferSample35(int var33) {
		state.constrainedFlag$sample35[var33] = false;
		
		// The original value of the sample
		double cv$originalValue = state.weights[var33];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.01))
			cv$var = 0.01;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 0)) {
				// Unrolled loop
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 1)) {
				// Unrolled loop
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 2)) {
				// Unrolled loop
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			
			// Unrolled loop
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 0)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][0] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 1)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][1] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 2)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1) {
					// Substituted "j$var61" with its value "0".
					// 
					// Set the current value to the current state of the tree.
					double traceTempVariable$var69$15_4 = Math.exp((cv$originalValue * state.x[i][0]));
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[0] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "0".
						double var91 = ((state.indicator[i][0] / ((traceTempVariable$var69$15_4 + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$15_7" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[0] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "1".
						double var91 = ((state.indicator[i][1] / ((traceTempVariable$var69$15_4 + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$15_7" with its value "1".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[0] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "2".
						double var91 = ((state.indicator[i][2] / ((traceTempVariable$var69$15_4 + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$15_7" with its value "2".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1) {
					// Substituted "j$var61" with its value "1".
					// 
					// Set the current value to the current state of the tree.
					double traceTempVariable$var71$16_4 = Math.exp((cv$originalValue * state.x[i][1]));
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[1] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "0".
						double var91 = ((state.indicator[i][0] / ((state.indicator[i][0] + traceTempVariable$var71$16_4) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$16_7" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[1] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "1".
						double var91 = ((state.indicator[i][1] / ((state.indicator[i][0] + traceTempVariable$var71$16_4) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$16_7" with its value "1".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[1] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "2".
						double var91 = ((state.indicator[i][2] / ((state.indicator[i][0] + traceTempVariable$var71$16_4) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$16_7" with its value "2".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1) {
					// Substituted "j$var61" with its value "2".
					// 
					// Set the current value to the current state of the tree.
					double traceTempVariable$var74$17_4 = Math.exp((cv$originalValue * state.x[i][2]));
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[2] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "0".
						double var91 = ((state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + traceTempVariable$var74$17_4)) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$17_7" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[2] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "1".
						double var91 = ((state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + traceTempVariable$var74$17_4)) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$17_7" with its value "1".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[2] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "2".
						double var91 = ((state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + traceTempVariable$var74$17_4)) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$17_7" with its value "2".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[0] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var61" with its value "0".
						// 
						// Set the current value to the current state of the tree.
						double var91 = ((Math.exp((cv$originalValue * state.x[i][0])) / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$18_7" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[1] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var61" with its value "1".
						// 
						// Set the current value to the current state of the tree.
						double var91 = ((Math.exp((cv$originalValue * state.x[i][1])) / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$18_7" with its value "1".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[2] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var61" with its value "2".
						// 
						// Set the current value to the current state of the tree.
						double var91 = ((Math.exp((cv$originalValue * state.x[i][2])) / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$18_7" with its value "2".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
		if(state.constrainedFlag$sample35[var33]) {
			// Guards to ensure that weights is only updated when there is a valid path.
			state.weights[var33] = cv$proposedValue;
			
			// Guards to ensure that indicator is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 35 and consumer double[] 67.
			// 
			// Unrolled loop
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1)
															// Substituted "j$var61" with its value "0".
					state.indicator[i][0] = Math.exp((state.weights[0] * state.x[i][0]));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1)
															// Substituted "j$var61" with its value "1".
					state.indicator[i][1] = Math.exp((state.weights[1] * state.x[i][1]));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1)
															// Substituted "j$var61" with its value "2".
					state.indicator[i][2] = Math.exp((state.weights[2] * state.x[i][2]));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 0)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1) {
					// Unrolled loop
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][0] = false;
					
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][1] = false;
					
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][2] = false;
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 1)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1) {
					// Unrolled loop
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][0] = false;
					
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][1] = false;
					
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][2] = false;
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 2)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1) {
					// Unrolled loop
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][0] = false;
					
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][1] = false;
					
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][2] = false;
				}
			}
			
			// Unrolled loop
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 0)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][0] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 1)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][1] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 2)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35put89$global[i][2] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 0)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1) {
					// Unrolled loop
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35put89$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][0] = true;
						
																		// Substituted "j$var85" with its value "0".
						state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35put89$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][1] = true;
						
																		// Substituted "j$var85" with its value "1".
						state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35put89$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][2] = true;
						
																		// Substituted "j$var85" with its value "2".
						state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 1)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1) {
					// Unrolled loop
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35put89$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][0] = true;
						
																		// Substituted "j$var85" with its value "0".
						state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35put89$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][1] = true;
						
																		// Substituted "j$var85" with its value "1".
						state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35put89$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][2] = true;
						
																		// Substituted "j$var85" with its value "2".
						state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 2)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1) {
					// Unrolled loop
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35put89$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][0] = true;
						
																		// Substituted "j$var85" with its value "0".
						state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35put89$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][1] = true;
						
																		// Substituted "j$var85" with its value "1".
						state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35put89$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][2] = true;
						
																		// Substituted "j$var85" with its value "2".
						state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 0)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1) {
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!scratch.guard$sample35put89$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][0] = true;
						
																		// Substituted "j$var85" with its value "0".
						state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 1)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1) {
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!scratch.guard$sample35put89$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][1] = true;
						
																		// Substituted "j$var85" with its value "1".
						state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 2)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1) {
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!scratch.guard$sample35put89$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][2] = true;
						
																		// Substituted "j$var85" with its value "2".
						state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
				}
			}
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 0)) {
				// Unrolled loop
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 1)) {
				// Unrolled loop
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 2)) {
				// Unrolled loop
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			
			// Unrolled loop
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 0)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][0] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 1)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][1] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 2)) {
				// Unrolled loop
				for(int i = 0; i < state.n; i += 1)
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1) {
					// Substituted "j$var61" with its value "0".
					double traceTempVariable$var69$15_4 = Math.exp((cv$proposedValue * state.x[i][0]));
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[0] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "0".
						double var91 = ((state.indicator[i][0] / ((traceTempVariable$var69$15_4 + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$15_7" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[0] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "1".
						double var91 = ((state.indicator[i][1] / ((traceTempVariable$var69$15_4 + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$15_7" with its value "1".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[0] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "2".
						double var91 = ((state.indicator[i][2] / ((traceTempVariable$var69$15_4 + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$15_7" with its value "2".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1) {
					// Substituted "j$var61" with its value "1".
					double traceTempVariable$var71$16_4 = Math.exp((cv$proposedValue * state.x[i][1]));
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[1] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "0".
						double var91 = ((state.indicator[i][0] / ((state.indicator[i][0] + traceTempVariable$var71$16_4) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$16_7" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[1] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "1".
						double var91 = ((state.indicator[i][1] / ((state.indicator[i][0] + traceTempVariable$var71$16_4) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$16_7" with its value "1".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[1] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "2".
						double var91 = ((state.indicator[i][2] / ((state.indicator[i][0] + traceTempVariable$var71$16_4) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$16_7" with its value "2".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1) {
					// Substituted "j$var61" with its value "2".
					double traceTempVariable$var74$17_4 = Math.exp((cv$proposedValue * state.x[i][2]));
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[2] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "0".
						double var91 = ((state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + traceTempVariable$var74$17_4)) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$17_7" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[2] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "1".
						double var91 = ((state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + traceTempVariable$var74$17_4)) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$17_7" with its value "1".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[2] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var85" with its value "2".
						double var91 = ((state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + traceTempVariable$var74$17_4)) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$17_7" with its value "2".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[0] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var61" with its value "0".
						double var91 = ((Math.exp((cv$proposedValue * state.x[i][0])) / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$18_7" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[1] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var61" with its value "1".
						double var91 = ((Math.exp((cv$proposedValue * state.x[i][1])) / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$18_7" with its value "1".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample35[2] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var61" with its value "2".
						double var91 = ((Math.exp((cv$proposedValue * state.x[i][2])) / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 94 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$18_7" with its value "2".
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
				// Guards to ensure that weights is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				state.weights[var33] = cv$originalValue;
				
				// Guards to ensure that indicator is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 35 and consumer double[] 67.
				// 
				// Unrolled loop
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var33 == 0)) {
					for(int i = 0; i < state.n; i += 1)
																		// Substituted "j$var61" with its value "0".
						state.indicator[i][0] = Math.exp((state.weights[0] * state.x[i][0]));
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var33 == 1)) {
					for(int i = 0; i < state.n; i += 1)
																		// Substituted "j$var61" with its value "1".
						state.indicator[i][1] = Math.exp((state.weights[1] * state.x[i][1]));
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var33 == 2)) {
					for(int i = 0; i < state.n; i += 1)
																		// Substituted "j$var61" with its value "2".
						state.indicator[i][2] = Math.exp((state.weights[2] * state.x[i][2]));
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var33 == 0)) {
					// Unrolled loop
					for(int i = 0; i < state.n; i += 1) {
						// Unrolled loop
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][0] = false;
						
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][1] = false;
						
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][2] = false;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var33 == 1)) {
					// Unrolled loop
					for(int i = 0; i < state.n; i += 1) {
						// Unrolled loop
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][0] = false;
						
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][1] = false;
						
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][2] = false;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var33 == 2)) {
					// Unrolled loop
					for(int i = 0; i < state.n; i += 1) {
						// Unrolled loop
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][0] = false;
						
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][1] = false;
						
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][2] = false;
					}
				}
				
				// Unrolled loop
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var33 == 0)) {
					// Unrolled loop
					for(int i = 0; i < state.n; i += 1)
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][0] = false;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var33 == 1)) {
					// Unrolled loop
					for(int i = 0; i < state.n; i += 1)
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][1] = false;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var33 == 2)) {
					// Unrolled loop
					for(int i = 0; i < state.n; i += 1)
						// Set the flags to false
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample35put89$global[i][2] = false;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var33 == 0)) {
					// Unrolled loop
					for(int i = 0; i < state.n; i += 1) {
						// Unrolled loop
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample35put89$global[i][0]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample35put89$global[i][0] = true;
							
																					// Substituted "j$var85" with its value "0".
							state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample35put89$global[i][1]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample35put89$global[i][1] = true;
							
																					// Substituted "j$var85" with its value "1".
							state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample35put89$global[i][2]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample35put89$global[i][2] = true;
							
																					// Substituted "j$var85" with its value "2".
							state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var33 == 1)) {
					// Unrolled loop
					for(int i = 0; i < state.n; i += 1) {
						// Unrolled loop
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample35put89$global[i][0]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample35put89$global[i][0] = true;
							
																					// Substituted "j$var85" with its value "0".
							state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample35put89$global[i][1]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample35put89$global[i][1] = true;
							
																					// Substituted "j$var85" with its value "1".
							state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample35put89$global[i][2]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample35put89$global[i][2] = true;
							
																					// Substituted "j$var85" with its value "2".
							state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var33 == 2)) {
					// Unrolled loop
					for(int i = 0; i < state.n; i += 1) {
						// Unrolled loop
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample35put89$global[i][0]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample35put89$global[i][0] = true;
							
																					// Substituted "j$var85" with its value "0".
							state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample35put89$global[i][1]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample35put89$global[i][1] = true;
							
																					// Substituted "j$var85" with its value "1".
							state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample35put89$global[i][2]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample35put89$global[i][2] = true;
							
																					// Substituted "j$var85" with its value "2".
							state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var33 == 0)) {
					// Unrolled loop
					for(int i = 0; i < state.n; i += 1) {
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						if(!scratch.guard$sample35put89$global[i][0]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample35put89$global[i][0] = true;
							
																					// Substituted "j$var85" with its value "0".
							state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var33 == 1)) {
					// Unrolled loop
					for(int i = 0; i < state.n; i += 1) {
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						if(!scratch.guard$sample35put89$global[i][1]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample35put89$global[i][1] = true;
							
																					// Substituted "j$var85" with its value "1".
							state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var33 == 2)) {
					// Unrolled loop
					for(int i = 0; i < state.n; i += 1) {
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						if(!scratch.guard$sample35put89$global[i][2]) {
							// The body will execute, so should not be executed again
							// 
																					// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							scratch.guard$sample35put89$global[i][2] = true;
							
																					// Substituted "j$var85" with its value "2".
							state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 42 drawn from Gaussian 40. Inference was performed using Metropolis-Hastings.
	private final void inferSample42() {
		state.constrainedFlag$sample42 = false;
		
		// The original value of the sample
		double cv$originalValue = state.bias;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		// 
						// The original value of the sample
		double cv$var = (((state.bias < 0)?(-state.bias):state.bias) * 40.0);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.01))
			cv$var = 0.01;
		
		// The proposed new value for the sample
		// 
		// The original value of the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.bias);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			// 
			// The original value of the sample
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((state.bias / 3.1622776601683795)) - 1.151292546497023);
			
			// Processing random variable 92.
			for(int i = 0; i < state.n; i += 1) {
				// Unrolled loop
				{
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample42 = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var85" with its value "0".
					// 
					// Set the current value to the current state of the tree.
					// 
					// The original value of the sample
					double var91 = (state.p[i][0] + state.bias);
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 94 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "j$var85" with its value "0".
					cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				{
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample42 = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var85" with its value "1".
					// 
					// Set the current value to the current state of the tree.
					// 
					// The original value of the sample
					double var91 = (state.p[i][1] + state.bias);
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 94 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "j$var85" with its value "1".
					cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample42 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Substituted "j$var85" with its value "2".
				// 
				// Set the current value to the current state of the tree.
				// 
				// The original value of the sample
				double var91 = (state.p[i][2] + state.bias);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 94 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "j$var85" with its value "2".
				cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
		if(state.constrainedFlag$sample42) {
			// Update Sample and intermediate values
			// 
			// Write out the new value of the sample.
			state.bias = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
			
			// Processing random variable 92.
			for(int i = 0; i < state.n; i += 1) {
				// Unrolled loop
				{
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample42 = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var85" with its value "0".
					double var91 = (state.p[i][0] + cv$proposedValue);
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 94 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "j$var85" with its value "0".
					cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				{
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample42 = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var85" with its value "1".
					double var91 = (state.p[i][1] + cv$proposedValue);
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 94 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "j$var85" with its value "1".
					cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample42 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Substituted "j$var85" with its value "2".
				double var91 = (state.p[i][2] + cv$proposedValue);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 94 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "j$var85" with its value "2".
				cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Write out the new value of the sample.
				state.bias = cv$originalValue;
		}
	}

	// Calculate the probability of the samples represented by sample35 using sampled
	// values.
	private final void logProbabilityValue$sample35() {
		// Determine if we need to calculate the values for sample task 35 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample35) {
			// Generating probabilities for sample task
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator;
			{
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((state.weights[0] / 3.1622776601683795)) - 1.151292546497023);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				cv$sampleAccumulator = cv$weightedProbability;
				
				// Store the sample task probability
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				state.logProbability$sample35[0] = cv$weightedProbability;
			}
			{
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((state.weights[1] / 3.1622776601683795)) - 1.151292546497023);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$weightedProbability);
				
				// Store the sample task probability
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				state.logProbability$sample35[1] = cv$weightedProbability;
			}
			
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((state.weights[2] / 3.1622776601683795)) - 1.151292546497023);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$weightedProbability);
			
			// Store the sample task probability
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			state.logProbability$sample35[2] = cv$weightedProbability;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$weights = (state.logProbability$weights + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample35)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample35 = state.fixedFlag$sample35;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Variable declaration of cv$rvAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$rvAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$rvAccumulator moved.
			// Declaration comment was:
			// This value is not used before it is set again, so removing the value declaration.
			double cv$rvAccumulator = ((state.logProbability$sample35[0] + state.logProbability$sample35[1]) + state.logProbability$sample35[2]);
			
			// Update the variable probability
			state.logProbability$weights = (state.logProbability$weights + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample42 using sampled
	// values.
	private final void logProbabilityValue$sample42() {
		// Determine if we need to calculate the values for sample task 42 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample42) {
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
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((state.bias / 3.1622776601683795)) - 1.151292546497023);
			
			// Store the sample task probability
			state.logProbability$bias = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample42)
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
			state.fixedProbFlag$sample42 = state.fixedFlag$sample42;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$bias);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample42)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$bias);
		}
	}

	// Calculate the probability of the samples represented by sample94 using sampled
	// values.
	private final void logProbabilityValue$sample94() {
		// Determine if we need to calculate the values for sample task 94 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample94) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.n; i += 1) {
				// Unrolled loop
				{
					// Substituted "j$var85" with its value "0".
					double var91 = (state.p[i][0] + state.bias);
					
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					// 
					// Store the value of the function call, so the function call is only made once.
					// 
					// The sample value to calculate the probability of generating
					// 
					// Substituted "j$var85" with its value "0".
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY));
				}
				{
					// Substituted "j$var85" with its value "1".
					double var91 = (state.p[i][1] + state.bias);
					
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					// 
					// Store the value of the function call, so the function call is only made once.
					// 
					// The sample value to calculate the probability of generating
					// 
					// Substituted "j$var85" with its value "1".
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY));
				}
				
				// Substituted "j$var85" with its value "2".
				double var91 = (state.p[i][2] + state.bias);
				
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
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				// 
				// Substituted "j$var85" with its value "2".
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$var93 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$y = (state.logProbability$y + cv$sampleAccumulator);
			
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
			state.fixedProbFlag$sample94 = (state.fixedFlag$sample35 && state.fixedFlag$sample42);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$y = (state.logProbability$y + state.logProbability$var93);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var93);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var93);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample35)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 3, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1)
							state.weights[var33] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!state.fixedFlag$sample42)
			state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample35)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 3, 1,
								(int forStart$j$var61, int forEnd$j$var61, int threadID$j$var61, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var61 = forStart$j$var61; j$var61 < forEnd$j$var61; j$var61 += 1)
											state.indicator[i][j$var61] = Math.exp((state.weights[j$var61] * state.x[i][j$var61]));
								}
							);

						boolean[] var89 = state.y[i];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 3, 1,
							(int forStart$j$var85, int forEnd$j$var85, int threadID$j$var85, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var85 = forStart$j$var85; j$var85 < forEnd$j$var85; j$var85 += 1) {
										if(!state.fixedFlag$sample35)
											state.p[i][j$var85] = (state.indicator[i][j$var85] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
										var89[j$var85] = DistributionSampling.sampleBernoulli(RNG$2, (state.p[i][j$var85] + state.bias));
									}
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample35)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 3, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1)
							state.weights[var33] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!state.fixedFlag$sample42)
			state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 3, 1,
							(int forStart$j$var61, int forEnd$j$var61, int threadID$j$var61, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var61 = forStart$j$var61; j$var61 < forEnd$j$var61; j$var61 += 1)
										state.indicator[i][j$var61] = Math.exp((state.weights[j$var61] * state.x[i][j$var61]));
							}
						);
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 3, 1,
							(int forStart$j$var85, int forEnd$j$var85, int threadID$j$var85, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var85 = forStart$j$var85; j$var85 < forEnd$j$var85; j$var85 += 1)
										state.p[i][j$var85] = (state.indicator[i][j$var85] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample35)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 3, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1)
							state.weights[var33] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!state.fixedFlag$sample42)
			state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 3, 1,
							(int forStart$j$var61, int forEnd$j$var61, int threadID$j$var61, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var61 = forStart$j$var61; j$var61 < forEnd$j$var61; j$var61 += 1)
										state.indicator[i][j$var61] = Math.exp((state.weights[j$var61] * state.x[i][j$var61]));
							}
						);
						boolean[] var89 = state.y[i];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 3, 1,
							(int forStart$j$var85, int forEnd$j$var85, int threadID$j$var85, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var85 = forStart$j$var85; j$var85 < forEnd$j$var85; j$var85 += 1) {
										state.p[i][j$var85] = (state.indicator[i][j$var85] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
										var89[j$var85] = DistributionSampling.sampleBernoulli(RNG$2, (state.p[i][j$var85] + state.bias));
									}
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample35)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 3, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1)
							state.weights[var33] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!state.fixedFlag$sample42)
			state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample35)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.n, 1,
				(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
							int i = index$i;
							int threadID$i = threadID$index$i;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 3, 1,
								(int forStart$j$var61, int forEnd$j$var61, int threadID$j$var61, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var61 = forStart$j$var61; j$var61 < forEnd$j$var61; j$var61 += 1)
											state.indicator[i][j$var61] = Math.exp((state.weights[j$var61] * state.x[i][j$var61]));
								}
							);
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 3, 1,
								(int forStart$j$var85, int forEnd$j$var85, int threadID$j$var85, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var85 = forStart$j$var85; j$var85 < forEnd$j$var85; j$var85 += 1)
											state.p[i][j$var85] = (state.indicator[i][j$var85] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
								}
							);
						}
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample35)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 3, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1)
							state.weights[var33] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!state.fixedFlag$sample42)
			state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 3, 1,
							(int forStart$j$var61, int forEnd$j$var61, int threadID$j$var61, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var61 = forStart$j$var61; j$var61 < forEnd$j$var61; j$var61 += 1)
										state.indicator[i][j$var61] = Math.exp((state.weights[j$var61] * state.x[i][j$var61]));
							}
						);
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 3, 1,
							(int forStart$j$var85, int forEnd$j$var85, int threadID$j$var85, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var85 = forStart$j$var85; j$var85 < forEnd$j$var85; j$var85 += 1)
										state.p[i][j$var85] = (state.indicator[i][j$var85] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
							}
						);
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample35) {
				inferSample35(0);
				inferSample35(1);
				inferSample35(2);
			}
			if(!state.fixedFlag$sample42)
				inferSample42();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!state.fixedFlag$sample42)
				inferSample42();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample35) {
				inferSample35(2);
				inferSample35(1);
				inferSample35(0);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.constrainedFlag$sample35[0])
			drawValueSample35(0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.constrainedFlag$sample35[1])
			drawValueSample35(1);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.constrainedFlag$sample35[2])
			drawValueSample35(2);
		if(!state.constrainedFlag$sample42)
			drawValueSample42();
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
		state.logProbability$weights = 0.0;
		if(!state.fixedProbFlag$sample35) {
			// Unrolled loop
			state.logProbability$sample35[0] = Double.NaN;
			state.logProbability$sample35[1] = Double.NaN;
			state.logProbability$sample35[2] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample42)
			state.logProbability$bias = Double.NaN;
		state.logProbability$y = 0.0;
		if(!state.fixedProbFlag$sample94)
			state.logProbability$var93 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.n = state.x.length;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample35$1 = 0; index$constrainedFlag$sample35$1 < state.constrainedFlag$sample35.length; index$constrainedFlag$sample35$1 += 1)
			state.constrainedFlag$sample35[index$constrainedFlag$sample35$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(state.fixedFlag$sample42)
			logProbabilityValue$sample42();
		logProbabilityValue$sample94();
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
		logProbabilityValue$sample42();
		logProbabilityValue$sample94();
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
		logProbabilityValue$sample42();
		logProbabilityValue$sample94();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.y.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = state.yMeasured[cv$index1];
			boolean[] cv$target2 = state.y[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 3, 1,
							(int forStart$j$var61, int forEnd$j$var61, int threadID$j$var61, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var61 = forStart$j$var61; j$var61 < forEnd$j$var61; j$var61 += 1)
										state.indicator[i][j$var61] = Math.exp((state.weights[j$var61] * state.x[i][j$var61]));
							}
						);
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 3, 1,
							(int forStart$j$var85, int forEnd$j$var85, int threadID$j$var85, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var85 = forStart$j$var85; j$var85 < forEnd$j$var85; j$var85 += 1)
										state.p[i][j$var85] = (state.indicator[i][j$var85] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
							}
						);
					}
			}
		);
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
		     + "model LogitRegressionTest(double[][] x, boolean[][] yMeasured) {\n"
		     + "    int k = 3;\n"
		     + "\n"
		     + "    int n = x.length;\n"
		     + "    boolean[][] y = new boolean[n][k];\n"
		     + "\n"
		     + "    double[] weights = gaussian(0,10).sample(k);\n"
		     + "    //TODO, change this to a beta distribution.\n"
		     + "    double bias = gaussian(0,10).sample();\n"
		     + "\n"
		     + "    for(int i:[0 .. n)) {\n"
		     + "        double[] indicator = new double[k];\n"
		     + "        for(int j:[0 .. k)) {\n"
		     + "            indicator[j] = exp(weights[j] * x[i][j]);\n"
		     + "        }\n"
		     + "        \n"
		     + "        //Single assignment semantics means a for loop cannot be used here.\n"
		     + "        double sum = indicator[0] + indicator[1] + indicator[2];\n"
		     + "        double[] p = new double[k];\n"
		     + "\n"
		     + "        for(int j:[0 .. k)) {\n"
		     + "            p[j] = indicator[j]/sum;\n"
		     + "            //This really wants to be a Categorical, but for now y will have\n"
		     + "            //to be arrays with just a single value set.\n"
		     + "            y[i][j] = bernoulli(p[j] + bias).sample();\n"
		     + "        }    \n"
		     + "    }\n"
		     + "\n"
		     + "    y.observe(yMeasured);\n"
		     + "}\n"
		     + "";
	}
}