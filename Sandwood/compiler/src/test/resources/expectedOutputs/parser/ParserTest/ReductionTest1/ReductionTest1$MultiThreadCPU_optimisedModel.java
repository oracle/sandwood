package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.ReductionTest1$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.ReductionTest1.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ReductionTest1$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {}
	}


	public ReductionTest1$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample101
	private final void drawValueSample101(int i$var80, int var95, int threadID$cv$i$var80, Rng RNG$) {
		state.time_coeff[i$var80][var95] = DistributionSampling.sampleGaussian(RNG$);
		
		// Guards to ensure that time_impact is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 101 and consumer double[][][] 138.
		for(int t = 1; t < state.T; t += 1)
									// Substituted "i$var119" with its value "i$var80".
			// 
			// Substituted "i$var119" with its value "i$var80".
			state.time_impact[t][i$var80][var95] = (state.TimeFeat[t][var95] * state.time_coeff[i$var80][var95]);
		for(int t = 1; t < state.T; t += 1) {
			// Reduction of array null
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$var151$13 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
								// x's comment
				// Set the left hand term of the reduction function to the return variable value.
				// 
								// y's comment
				// Set the right hand term to a value from the array var141
				// 
				// Substituted "index$t$2_4" with its value "t".
				// 
				// Substituted "index$i$2_5" with its value "i$var80".
				reduceVar$var151$13 = (reduceVar$var151$13 + state.time_impact[t][i$var80][cv$reduction152Index]);
			
									// Substituted "index$t$2_4" with its value "t".
			state.sum_t[t][i$var80] = reduceVar$var151$13;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 101 drawn from Gaussian 85. Inference was performed using Metropolis-Hastings.
	private final void inferSample101(int i$var80, int var95, int threadID$cv$i$var80, Rng RNG$) {
		state.constrainedFlag$sample101[i$var80][var95] = false;
		
		// The original value of the sample
		double cv$originalValue = state.time_coeff[i$var80][var95];
		
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
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$originalValue);
			for(int t = 1; t < state.T; t += 1) {
				// Reduction of array null
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$var151$11 = 0.0;
				
				// Reduce for every value except a masked value which will be skipped.
				// 
				// Substituted "j" with its value "var95".
				for(int cv$reduction744Index = 0; cv$reduction744Index < var95; cv$reduction744Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
										// x's comment
					// Set the left hand term of the reduction function to the return variable value.
					// 
										// y's comment
					// Set the right hand term to a value from the array var141
					// 
					// Substituted "index$t$4_6" with its value "t".
					// 
					// Substituted "index$i$4_7" with its value "i$var80".
					reduceVar$var151$11 = (reduceVar$var151$11 + state.time_impact[t][i$var80][cv$reduction744Index]);
				
				// Substituted "j" with its value "var95".
				for(int cv$reduction744Index = (var95 + 1); cv$reduction744Index < state.time_dim; cv$reduction744Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
										// x's comment
					// Set the left hand term of the reduction function to the return variable value.
					// 
										// y's comment
					// Set the right hand term to a value from the array var141
					// 
					// Substituted "index$t$4_6" with its value "t".
					// 
					// Substituted "index$i$4_7" with its value "i$var80".
					reduceVar$var151$11 = (reduceVar$var151$11 + state.time_impact[t][i$var80][cv$reduction744Index]);
				
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// Substituted "j" with its value "var95".
				// 
				// Set the current value to the current state of the tree.
				reduceVar$var151$11 = ((state.TimeFeat[t][var95] * cv$originalValue) + reduceVar$var151$11);
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample101[i$var80][var95] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 165 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "index$i$4_11" with its value "index$i$4_7".
				// 
												// Substituted "index$t$4_10" with its value "t".
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityPoisson(state.arr[t][i$var80], reduceVar$var151$11) + cv$accumulatedProbabilities);
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
		if(state.constrainedFlag$sample101[i$var80][var95]) {
			state.time_coeff[i$var80][var95] = cv$proposedValue;
			
			// Guards to ensure that time_impact is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 101 and consumer double[][][] 138.
			for(int t = 1; t < state.T; t += 1)
												// Substituted "i$var119" with its value "i$var80".
				// 
				// Substituted "i$var119" with its value "i$var80".
				state.time_impact[t][i$var80][var95] = (state.TimeFeat[t][var95] * state.time_coeff[i$var80][var95]);
			for(int t = 1; t < state.T; t += 1) {
				// Reduction of array null
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$var151$10 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
										// x's comment
					// Set the left hand term of the reduction function to the return variable value.
					// 
										// y's comment
					// Set the right hand term to a value from the array var141
					// 
					// Substituted "index$t$3_4" with its value "t".
					// 
					// Substituted "index$i$3_5" with its value "i$var80".
					reduceVar$var151$10 = (reduceVar$var151$10 + state.time_impact[t][i$var80][cv$reduction152Index]);
				
												// Substituted "index$t$3_4" with its value "t".
				state.sum_t[t][i$var80] = reduceVar$var151$10;
			}
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$proposedValue);
			for(int t = 1; t < state.T; t += 1) {
				// Reduction of array null
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$var151$11 = 0.0;
				
				// Reduce for every value except a masked value which will be skipped.
				// 
				// Substituted "j" with its value "var95".
				for(int cv$reduction744Index = 0; cv$reduction744Index < var95; cv$reduction744Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
										// x's comment
					// Set the left hand term of the reduction function to the return variable value.
					// 
										// y's comment
					// Set the right hand term to a value from the array var141
					// 
					// Substituted "index$t$4_6" with its value "t".
					// 
					// Substituted "index$i$4_7" with its value "i$var80".
					reduceVar$var151$11 = (reduceVar$var151$11 + state.time_impact[t][i$var80][cv$reduction744Index]);
				
				// Substituted "j" with its value "var95".
				for(int cv$reduction744Index = (var95 + 1); cv$reduction744Index < state.time_dim; cv$reduction744Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
										// x's comment
					// Set the left hand term of the reduction function to the return variable value.
					// 
										// y's comment
					// Set the right hand term to a value from the array var141
					// 
					// Substituted "index$t$4_6" with its value "t".
					// 
					// Substituted "index$i$4_7" with its value "i$var80".
					reduceVar$var151$11 = (reduceVar$var151$11 + state.time_impact[t][i$var80][cv$reduction744Index]);
				
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// Substituted "j" with its value "var95".
				reduceVar$var151$11 = ((state.TimeFeat[t][var95] * cv$proposedValue) + reduceVar$var151$11);
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample101[i$var80][var95] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 165 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "index$i$4_11" with its value "index$i$4_7".
				// 
												// Substituted "index$t$4_10" with its value "t".
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityPoisson(state.arr[t][i$var80], reduceVar$var151$11) + cv$accumulatedProbabilities);
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
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				state.time_coeff[i$var80][var95] = cv$originalValue;
				
				// Guards to ensure that time_impact is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 101 and consumer double[][][] 138.
				for(int t = 1; t < state.T; t += 1)
															// Substituted "i$var119" with its value "i$var80".
					// 
					// Substituted "i$var119" with its value "i$var80".
					state.time_impact[t][i$var80][var95] = (state.TimeFeat[t][var95] * state.time_coeff[i$var80][var95]);
				for(int t = 1; t < state.T; t += 1) {
					// Reduction of array null
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$var151$12 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
												// x's comment
						// Set the left hand term of the reduction function to the return variable value.
						// 
												// y's comment
						// Set the right hand term to a value from the array var141
						// 
						// Substituted "index$t$9_4" with its value "t".
						// 
						// Substituted "index$i$9_5" with its value "i$var80".
						reduceVar$var151$12 = (reduceVar$var151$12 + state.time_impact[t][i$var80][cv$reduction152Index]);
					
															// Substituted "index$t$9_4" with its value "t".
					state.sum_t[t][i$var80] = reduceVar$var151$12;
				}
			}
		}
	}

	// Calculate the probability of the samples represented by sample101 using sampled
	// values.
	private final void logProbabilityValue$sample101() {
		// Determine if we need to calculate the values for sample task 101 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample101) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
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
					double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(state.time_coeff[i$var80][var95]);
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					state.logProbability$sample101[i$var80][var95] = cv$distributionAccumulator;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 < state.T)) {
						// Update the variable probability
						state.logProbability$time_impact = (state.logProbability$time_impact + cv$distributionAccumulator);
						
						// Update the variable probability
						state.logProbability$sum_t = (state.logProbability$sum_t + cv$distributionAccumulator);
					}
				}
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			}
			
			// Update the variable probability
			state.logProbability$time_coeff = (state.logProbability$time_coeff + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample101)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample101 = state.fixedFlag$sample101;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
					double cv$sampleValue = state.logProbability$sample101[i$var80][var95];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 < state.T)) {
						// Update the variable probability
						state.logProbability$time_impact = (state.logProbability$time_impact + cv$sampleValue);
						
						// Update the variable probability
						state.logProbability$sum_t = (state.logProbability$sum_t + cv$sampleValue);
					}
				}
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			
			// Update the variable probability
			state.logProbability$time_coeff = (state.logProbability$time_coeff + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample101)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample165 using sampled
	// values.
	private final void logProbabilityValue$sample165() {
		// Determine if we need to calculate the values for sample task 165 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample165) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int t = 1; t < state.T; t += 1) {
				for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
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
					double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson(state.arr[t][i$var119], state.sum_t[t][i$var119]);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					state.logProbability$sample165[(t - 1)][i$var119] = cv$distributionAccumulator;
				}
			}
			
			// Update the variable probability
			state.logProbability$arr = (state.logProbability$arr + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample165 = state.fixedFlag$sample101;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int t = 1; t < state.T; t += 1) {
				for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample165[(t - 1)][i$var119]);
			}
			
			// Update the variable probability
			state.logProbability$arr = (state.logProbability$arr + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample101)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							double[] var86 = state.time_coeff[i$var80];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.time_dim, 1,
								(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
											var86[var95] = DistributionSampling.sampleGaussian(RNG$2);
								}
							);
						}
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = state.time_impact[t];
						double[] var139 = state.sum_t[t];
						int[] var154 = state.arr[t];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										
										// Constraints moved from conditionals in inner loops/scopes/etc.
										if(!state.fixedFlag$sample101) {
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, state.time_dim, 1,
												(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int j = forStart$j; j < forEnd$j; j += 1)
															var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
												}
											);
											
											// Reduction of array null
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$var151$14 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
												// Copy the result of the reduction into the variable returned by the reduction.
												// 
																																																// y's comment
												// Set the right hand term to a value from the array var141
												reduceVar$var151$14 = (reduceVar$var151$14 + state.time_impact[t][i$var119][cv$reduction152Index]);
											var139[i$var119] = reduceVar$var151$14;
										}
										var154[i$var119] = DistributionSampling.samplePoisson(RNG$2, state.sum_t[t][i$var119]);
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
		if(!state.fixedFlag$sample101)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							double[] var86 = state.time_coeff[i$var80];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.time_dim, 1,
								(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
											var86[var95] = DistributionSampling.sampleGaussian(RNG$2);
								}
							);
						}
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = state.time_impact[t];
						double[] var139 = state.sum_t[t];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, state.time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int j = forStart$j; j < forEnd$j; j += 1)
														var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
											}
										);
										
										// Reduction of array null
										// 
										// A generated name to prevent name collisions if the reduction is implemented more
										// than once in inference and probability code. Initialize the variable to the unit
										// value
										double reduceVar$var151$18 = 0.0;
										
										// For each index in the array to be reduced
										for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
											// Execute the reduction function, saving the result into the return value.
											// 
											// Copy the result of the reduction into the variable returned by the reduction.
											// 
																																												// y's comment
											// Set the right hand term to a value from the array var141
											reduceVar$var151$18 = (reduceVar$var151$18 + state.time_impact[t][i$var119][cv$reduction152Index]);
										var139[i$var119] = reduceVar$var151$18;
									}
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
		if(!state.fixedFlag$sample101)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							double[] var86 = state.time_coeff[i$var80];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.time_dim, 1,
								(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
											var86[var95] = DistributionSampling.sampleGaussian(RNG$2);
								}
							);
						}
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = state.time_impact[t];
						double[] var139 = state.sum_t[t];
						int[] var154 = state.arr[t];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, state.time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int j = forStart$j; j < forEnd$j; j += 1)
														var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
											}
										);
										
										// Reduction of array null
										// 
										// A generated name to prevent name collisions if the reduction is implemented more
										// than once in inference and probability code. Initialize the variable to the unit
										// value
										double reduceVar$var151$15 = 0.0;
										
										// For each index in the array to be reduced
										for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
											// Execute the reduction function, saving the result into the return value.
											// 
											// Copy the result of the reduction into the variable returned by the reduction.
											// 
																																												// y's comment
											// Set the right hand term to a value from the array var141
											reduceVar$var151$15 = (reduceVar$var151$15 + state.time_impact[t][i$var119][cv$reduction152Index]);
										var139[i$var119] = reduceVar$var151$15;
										var154[i$var119] = DistributionSampling.samplePoisson(RNG$2, state.sum_t[t][i$var119]);
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
		if(!state.fixedFlag$sample101) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							double[] var86 = state.time_coeff[i$var80];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.time_dim, 1,
								(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
											var86[var95] = DistributionSampling.sampleGaussian(RNG$2);
								}
							);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 1, state.T, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							double[][] var129 = state.time_impact[t];
							double[] var139 = state.sum_t[t];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.n_ac, 1,
								(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
											int i$var119 = index$i$var119;
											int threadID$i$var119 = threadID$index$i$var119;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, state.time_dim, 1,
												(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int j = forStart$j; j < forEnd$j; j += 1)
															var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
												}
											);
											
											// Reduction of array null
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$var151$16 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
												// Copy the result of the reduction into the variable returned by the reduction.
												// 
																																																// y's comment
												// Set the right hand term to a value from the array var141
												reduceVar$var151$16 = (reduceVar$var151$16 + state.time_impact[t][i$var119][cv$reduction152Index]);
											var139[i$var119] = reduceVar$var151$16;
										}
								}
							);
						}
				}
			);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample101)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							double[] var86 = state.time_coeff[i$var80];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.time_dim, 1,
								(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
											var86[var95] = DistributionSampling.sampleGaussian(RNG$2);
								}
							);
						}
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = state.time_impact[t];
						double[] var139 = state.sum_t[t];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, state.time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int j = forStart$j; j < forEnd$j; j += 1)
														var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
											}
										);
										
										// Reduction of array null
										// 
										// A generated name to prevent name collisions if the reduction is implemented more
										// than once in inference and probability code. Initialize the variable to the unit
										// value
										double reduceVar$var151$17 = 0.0;
										
										// For each index in the array to be reduced
										for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
											// Execute the reduction function, saving the result into the return value.
											// 
											// Copy the result of the reduction into the variable returned by the reduction.
											// 
																																												// y's comment
											// Set the right hand term to a value from the array var141
											reduceVar$var151$17 = (reduceVar$var151$17 + state.time_impact[t][i$var119][cv$reduction152Index]);
										var139[i$var119] = reduceVar$var151$17;
									}
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
		if(!state.fixedFlag$sample101) {
			// Infer the samples in chronological order.
			if(state.system$gibbsForward)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.n_ac, 1,
					(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
								for(int var95 = 0; var95 < state.time_dim; var95 += 1)
									inferSample101(i$var80, var95, threadID$i$var80, RNG$1);
							}
					}
				);
			// Infer the samples in reverse chronological order.
			else
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.n_ac, 1,
					(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
								for(int var95 = (state.time_dim - 1); var95 >= 0; var95 -= 1)
									inferSample101(i$var80, var95, threadID$i$var80, RNG$1);
							}
					}
				);
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.n_ac, 1,
			(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
						for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
							if(!state.constrainedFlag$sample101[i$var80][var95])
								drawValueSample101(i$var80, var95, threadID$i$var80, RNG$1);
						}
					}
			}
		);
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
		state.logProbability$time_coeff = 0.0;
		state.logProbability$time_impact = 0.0;
		state.logProbability$sum_t = 0.0;
		if(!state.fixedProbFlag$sample101) {
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				for(int var95 = 0; var95 < state.time_dim; var95 += 1)
					state.logProbability$sample101[i$var80][var95] = Double.NaN;
			}
		}
		state.logProbability$arr = 0.0;
		if(!state.fixedProbFlag$sample165) {
			for(int t = 1; t < state.T; t += 1) {
				for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1)
					state.logProbability$sample165[(t - 1)][i$var119] = Double.NaN;
			}
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.time_dim = state.TimeFeat[0].length;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < state.constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1) {
			boolean[] cv$constrainedFlag$sample101$1 = state.constrainedFlag$sample101[index$constrainedFlag$sample101$1];
			for(int index$constrainedFlag$sample101$2 = 0; index$constrainedFlag$sample101$2 < cv$constrainedFlag$sample101$1.length; index$constrainedFlag$sample101$2 += 1)
				cv$constrainedFlag$sample101$1[index$constrainedFlag$sample101$2] = true;
		}
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample101)
			logProbabilityValue$sample101();
		logProbabilityValue$sample165();
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
		logProbabilityValue$sample101();
		logProbabilityValue$sample165();
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
		logProbabilityValue$sample101();
		logProbabilityValue$sample165();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.arr.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = state.ObsArr[cv$index1];
			int[] cv$target2 = state.arr[cv$index1];
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
		parallelFor(state.RNG$, 1, state.T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = state.time_impact[t];
						double[] var139 = state.sum_t[t];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, state.time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int j = forStart$j; j < forEnd$j; j += 1)
														var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
											}
										);
										
										// Reduction of array null
										// 
										// A generated name to prevent name collisions if the reduction is implemented more
										// than once in inference and probability code. Initialize the variable to the unit
										// value
										double reduceVar$var151$19 = 0.0;
										
										// For each index in the array to be reduced
										for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
											// Execute the reduction function, saving the result into the return value.
											// 
											// Copy the result of the reduction into the variable returned by the reduction.
											// 
																																												// y's comment
											// Set the right hand term to a value from the array var141
											reduceVar$var151$19 = (reduceVar$var151$19 + state.time_impact[t][i$var119][cv$reduction152Index]);
										var139[i$var119] = reduceVar$var151$19;
									}
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
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model ReductionTest1(int T, int n_ac, int[][] ObsArr, double[][] TimeFeat) {\n"
		     + "    int time_dim = TimeFeat[0].length; //length of the feature vector\n"
		     + "\n"
		     + "\n"
		     + "    double[][] time_coeff = new double[n_ac][time_dim];\n"
		     + "    double[][] sum_t = new double[T][n_ac];\n"
		     + "    double[][][] time_impact = new double[T][n_ac][time_dim];\n"
		     + "    int[][] arr = new int[T][n_ac];\n"
		     + "    \n"
		     + "    for (int i : [0..n_ac))\n"
		     + "        time_coeff[i] = gaussian(0,1).sample(time_dim);\n"
		     + "\n"
		     + "    for (int t : (0..T)) {\n"
		     + "        for (int i : [0..n_ac)){\n"
		     + "            for (int j : [0..time_dim))\n"
		     + "                time_impact[t][i][j] = TimeFeat[t][j]*time_coeff[i][j];\n"
		     + "            //calculate sum\n"
		     + "            sum_t[t][i] = reduce(time_impact[t][i], 0, (x, y) -> { return x + y; });\n"
		     + "            arr[t][i] = poisson(sum_t[t][i]).sample();\n"
		     + "        }\n"
		     + "    }\n"
		     + "    arr.observe(ObsArr);\n"
		     + "}";
	}
}