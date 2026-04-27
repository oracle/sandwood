package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DiscreteChoiceRandCoeff$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DiscreteChoiceRandCoeff.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DiscreteChoiceRandCoeff$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		boolean[] guard$sample21categorical102$global;
		boolean[][] guard$sample21put101$global;
		boolean[][] guard$sample47categorical102$global;
		boolean[][][] guard$sample47put101$global;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for guard$sample21put101$global
			{
				// Calculate the largest index of i that is possible and allocate an array to hold
				// the guard for each of these.
				int cv$max_i = 0;
				
				// Calculate the largest index of j that is possible and allocate an array to hold
				// the guard for each of these.
				int cv$max_j$var97 = 0;
				for(int i = 0; i < state.noObs; i += 1)
					cv$max_j$var97 = Math.max(cv$max_j$var97, ((state.noProducts - 0) / 1));
				cv$max_i = Math.max(cv$max_i, ((state.noObs - 0) / 1));
				
				// Allocation of guard$sample21put101$global for single threaded execution
				guard$sample21put101$global = new boolean[cv$max_i][cv$max_j$var97];
			}
			
			// Constructor for guard$sample21categorical102$global
			{
				// Calculate the largest index of i that is possible and allocate an array to hold
				// the guard for each of these.
				int cv$max_i = 0;
				cv$max_i = Math.max(cv$max_i, ((state.noObs - 0) / 1));
				
				// Allocation of guard$sample21categorical102$global for single threaded execution
				guard$sample21categorical102$global = new boolean[cv$max_i];
			}
			
			// Constructor for guard$sample47put101$global
			{
				// Calculate the largest index of i that is possible and allocate an array to hold
				// the guard for each of these.
				int cv$max_i = 0;
				
				// Calculate the largest index of j that is possible and allocate an array to hold
				// the guard for each of these.
				int cv$max_j$var97 = 0;
				for(int i = 0; i < state.noObs; i += 1)
					cv$max_j$var97 = Math.max(cv$max_j$var97, ((state.noProducts - 0) / 1));
				cv$max_i = Math.max(cv$max_i, ((state.noObs - 0) / 1));
				
				// Allocation of guard$sample47put101$global for multithreaded execution
				{
					// Get the thread count.
					int cv$threadCount = threadCount();
					
					// Allocate an array to hold a copy per thread
					guard$sample47put101$global = new boolean[cv$threadCount][][];
					
					// Populate the array with a copy per thread
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						guard$sample47put101$global[cv$index] = new boolean[cv$max_i][cv$max_j$var97];
				}
			}
			
			// Constructor for guard$sample47categorical102$global
			{
				// Calculate the largest index of i that is possible and allocate an array to hold
				// the guard for each of these.
				int cv$max_i = 0;
				cv$max_i = Math.max(cv$max_i, ((state.noObs - 0) / 1));
				
				// Allocation of guard$sample47categorical102$global for multithreaded execution
				{
					// Get the thread count.
					int cv$threadCount = threadCount();
					
					// Allocate an array to hold a copy per thread
					guard$sample47categorical102$global = new boolean[cv$threadCount][];
					
					// Populate the array with a copy per thread
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						guard$sample47categorical102$global[cv$index] = new boolean[cv$max_i];
				}
			}
		}
	}


	public DiscreteChoiceRandCoeff$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample21
	private final void drawValueSample21(int var20) {
		state.ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 21 and consumer double[] 77.
		{
			{
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
					if((var20 == j$var69)) {
						for(int i = 0; i < state.noObs; i += 1)
							state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
					}
				}
			}
		}
		
		// Guards to ensure that prob is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 21 and consumer double[] 100.
		{
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			boolean[][] guard$sample21put101 = scratch.guard$sample21put101$global;
			{
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
					if((var20 == j$var69)) {
						for(int i = 0; i < state.noObs; i += 1) {
							if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
								for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
									// Set the flags to false
									guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
							}
						}
					}
				}
			}
			{
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
					if((var20 == j$var69)) {
						for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
							if((j$var69 == j$var97)) {
								for(int i = 0; i < state.noObs; i += 1)
									// Set the flags to false
									guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
							}
						}
					}
				}
			}
			{
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
					if((var20 == j$var69)) {
						for(int i = 0; i < state.noObs; i += 1) {
							if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
								for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
									if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
										// The body will execute, so should not be executed again
										guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
										{
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$30 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k = reduceVar$sum$30;
												
												// Set the right hand term to a value from the array exped
												double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$30 = (k + l);
											}
											state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$30);
										}
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
					if((var20 == j$var69)) {
						for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
							if((j$var69 == j$var97)) {
								for(int i = 0; i < state.noObs; i += 1) {
									if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
										// The body will execute, so should not be executed again
										guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
										{
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$31 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k = reduceVar$sum$31;
												
												// Set the right hand term to a value from the array exped
												double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$31 = (k + l);
											}
											state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$31);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// Pick a value from the distribution for the unconditioned variable from sample28
	private final void drawValueSample28() {
		state.b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample34
	private final void drawValueSample34() {
		state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample47
	private final void drawValueSample47(int var46, int threadID$cv$var46, Rng RNG$) {
		state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(RNG$)) + state.b);
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 47 and consumer double[] 77.
		{
			{
				for(int i = 0; i < state.noObs; i += 1) {
					if((var46 == i)) {
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
							state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
					}
				}
			}
		}
		
		// Guards to ensure that prob is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 47 and consumer double[] 100.
		{
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			boolean[][] guard$sample47put101 = scratch.guard$sample47put101$global[threadID$cv$var46];
			{
				for(int i = 0; i < state.noObs; i += 1) {
					if((var46 == i)) {
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
								for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
									// Set the flags to false
									guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
							}
						}
					}
				}
			}
			{
				for(int i = 0; i < state.noObs; i += 1) {
					if((var46 == i)) {
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
								if((j$var69 == j$var97))
									// Set the flags to false
									guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
							}
						}
					}
				}
			}
			{
				for(int i = 0; i < state.noObs; i += 1) {
					if((var46 == i)) {
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
								for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
									if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
										// The body will execute, so should not be executed again
										guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
										{
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$32 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k = reduceVar$sum$32;
												
												// Set the right hand term to a value from the array exped
												double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$32 = (k + l);
											}
											state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$32);
										}
									}
								}
							}
						}
					}
				}
			}
			{
				for(int i = 0; i < state.noObs; i += 1) {
					if((var46 == i)) {
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
								if((j$var69 == j$var97)) {
									if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
										// The body will execute, so should not be executed again
										guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
										{
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$33 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k = reduceVar$sum$33;
												
												// Set the right hand term to a value from the array exped
												double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$33 = (k + l);
											}
											state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$33);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 21 drawn from Gaussian 9. Inference was performed using Metropolis-Hastings.
	private final void inferSample21(int var20) {
		if(true) {
			state.constrainedFlag$sample21[((var20 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// Metropolis-Hastings
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = state.ut[var20];
			
			// The probability of the random variable generating the originally sampled value
			double cv$originalProbability = 0.0;
			
			// Calculate a proposed variance.
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			
			// Ensure the variance is at least 0.01
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			
			// The proposed new value for the sample
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			
			// The probability of the random variable generating the new sample value.
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample21[((var20 - 0) / 1)] || (cv$valuePos == 0))) {
					// Initialize the summed probabilities to 0.
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					
					// Initialize a counter to track the reached distributions.
					double cv$reachedDistributionSourceRV = 0.0;
					
					// Initialize a log space accumulator to take the product of all the distribution
					// probabilities.
					double cv$accumulatedDistributionProbabilities = 0.0;
					
					// The value currently being tested
					double cv$currentValue;
					if((cv$valuePos == 0))
						// Set the current value to the current state of the tree.
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						
						// Update Sample and intermediate values
						// 
						// Write out the value of the sample to a temporary variable prior to updating the
						// intermediate variables.
						double var21 = cv$proposedValue;
						
						// Guards to ensure that ut is only updated when there is a valid path.
						{
							{
								{
									state.ut[var20] = cv$currentValue;
								}
							}
						}
						
						// Guards to ensure that exped is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 21 and consumer double[] 77.
						{
							{
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									if((var20 == j$var69)) {
										for(int i = 0; i < state.noObs; i += 1)
											state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
									}
								}
							}
						}
						
						// Guards to ensure that prob is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 21 and consumer double[] 100.
						{
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[][] guard$sample21put101 = scratch.guard$sample21put101$global;
							{
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									if((var20 == j$var69)) {
										for(int i = 0; i < state.noObs; i += 1) {
											if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
													// Set the flags to false
													guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									if((var20 == j$var69)) {
										for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
											if((j$var69 == j$var97)) {
												for(int i = 0; i < state.noObs; i += 1)
													// Set the flags to false
													guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									if((var20 == j$var69)) {
										for(int i = 0; i < state.noObs; i += 1) {
											if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
													if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
														{
															// Reduction of array exped
															// 
															// A generated name to prevent name collisions if the reduction is implemented more
															// than once in inference and probability code. Initialize the variable to the unit
															// value
															double reduceVar$sum$20 = 0.0;
															
															// For each index in the array to be reduced
															for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																// Set the left hand term of the reduction function to the return variable value.
																double k = reduceVar$sum$20;
																
																// Set the right hand term to a value from the array exped
																double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																
																// Execute the reduction function, saving the result into the return value.
																// 
																// Copy the result of the reduction into the variable returned by the reduction.
																reduceVar$sum$20 = (k + l);
															}
															state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$20);
														}
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									if((var20 == j$var69)) {
										for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
											if((j$var69 == j$var97)) {
												for(int i = 0; i < state.noObs; i += 1) {
													if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
														{
															// Reduction of array exped
															// 
															// A generated name to prevent name collisions if the reduction is implemented more
															// than once in inference and probability code. Initialize the variable to the unit
															// value
															double reduceVar$sum$21 = 0.0;
															
															// For each index in the array to be reduced
															for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																// Set the left hand term of the reduction function to the return variable value.
																double k = reduceVar$sum$21;
																
																// Set the right hand term to a value from the array exped
																double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																
																// Execute the reduction function, saving the result into the return value.
																// 
																// Copy the result of the reduction into the variable returned by the reduction.
																reduceVar$sum$21 = (k + l);
															}
															state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$21);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					{
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < 10.0)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 0.0) / Math.sqrt(10.0))) - (0.5 * Math.log(10.0))):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 101.
						{
							// Looking for a path between Sample 21 and consumer Categorical 101.
							{
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								boolean[] guard$sample21categorical102 = scratch.guard$sample21categorical102$global;
								{
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int i = 0; i < state.noObs; i += 1) {
												if(((0 <= j$var69) && (j$var69 < state.noProducts)))
													// Set the flags to false
													guard$sample21categorical102[((i - 0) / 1)] = false;
											}
										}
									}
								}
								{
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
												if((j$var69 == j$var97)) {
													for(int i = 0; i < state.noObs; i += 1)
														// Set the flags to false
														guard$sample21categorical102[((i - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									double traceTempVariable$var70$9_1 = cv$currentValue;
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int i = 0; i < state.noObs; i += 1) {
												double traceTempVariable$k$9_4 = Math.exp((traceTempVariable$var70$9_1 - (state.beta[i] * state.Prices[i][j$var69])));
												if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
													if((0 < state.noProducts)) {
														// Reduction of array exped
														// 
														// A generated name to prevent name collisions if the reduction is implemented more
														// than once in inference and probability code. Initialize the variable to the unit
														// value
														double reduceVar$sum$22 = 0.0;
														
														// Reduce for every value except a masked value which will be skipped.
														for(int cv$reduction1610Index = 0; cv$reduction1610Index < j$var69; cv$reduction1610Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$sum$22;
															
															// Set the right hand term to a value from the array exped
															double l = state.exped[((i - 0) / 1)][cv$reduction1610Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$sum$22 = (k + l);
														}
														for(int cv$reduction1610Index = (j$var69 + 1); cv$reduction1610Index < state.noProducts; cv$reduction1610Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$sum$22;
															
															// Set the right hand term to a value from the array exped
															double l = state.exped[((i - 0) / 1)][cv$reduction1610Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$sum$22 = (k + l);
														}
														double cv$reduced82 = reduceVar$sum$22;
														
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$22 = (traceTempVariable$k$9_4 + cv$reduced82);
														double traceTempVariable$sum$9_5 = reduceVar$sum$22;
														if(!guard$sample21categorical102[((i - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample21categorical102[((i - 0) / 1)] = true;
															
															// Processing sample task 103 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		state.constrainedFlag$sample21[((var20 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							// Record the probability of sample task 103 generating output with current configuration.
																							if(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 103 with the current configuration.
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																			else
																				cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								{
									double traceTempVariable$var70$10_1 = cv$currentValue;
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int i = 0; i < state.noObs; i += 1) {
												double traceTempVariable$var98$10_4 = Math.exp((traceTempVariable$var70$10_1 - (state.beta[i] * state.Prices[i][j$var69])));
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
													if((j$var69 == j$var97)) {
														if(!guard$sample21categorical102[((i - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample21categorical102[((i - 0) / 1)] = true;
															
															// Processing sample task 103 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		state.constrainedFlag$sample21[((var20 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							// Record the probability of sample task 103 generating output with current configuration.
																							if(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 103 with the current configuration.
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																			else
																				cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						
						// Add the values for the source and any standard consumers for this configuration
						// of arguments to the source.
						if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
						else {
							// If the second value is -infinity.
							if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
								cv$stateProbabilityValue = cv$accumulatedProbabilities;
							else
								cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
						}
					}
					
					// Save the probability of the original value.
					if((cv$valuePos == 0))
						cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					
					// Save the probability of the proposed value.
					else
						cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					
					// The probability ration for the proposed value and the current value.
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					
					// Test if the probability of the sample is sufficient to keep the value. This needs
					// to be less than or equal as otherwise if the proposed value is not possible and
					// the random value is 0 an impossible value will be accepted.
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio))) {
							// If it is not revert the changes.
							// 
							// Set the sample value
							// Write out the value of the sample to a temporary variable prior to updating the
							// intermediate variables.
							double var21 = cv$originalValue;
							
							// Guards to ensure that ut is only updated when there is a valid path.
							{
								{
									{
										state.ut[var20] = var21;
									}
								}
							}
							
							// Guards to ensure that exped is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 21 and consumer double[] 77.
							{
								{
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int i = 0; i < state.noObs; i += 1)
												state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
										}
									}
								}
							}
							
							// Guards to ensure that prob is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 21 and consumer double[] 100.
							{
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								boolean[][] guard$sample21put101 = scratch.guard$sample21put101$global;
								{
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int i = 0; i < state.noObs; i += 1) {
												if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
													for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
														// Set the flags to false
														guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
												if((j$var69 == j$var97)) {
													for(int i = 0; i < state.noObs; i += 1)
														// Set the flags to false
														guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int i = 0; i < state.noObs; i += 1) {
												if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
													for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
														if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
															{
																// Reduction of array exped
																// 
																// A generated name to prevent name collisions if the reduction is implemented more
																// than once in inference and probability code. Initialize the variable to the unit
																// value
																double reduceVar$sum$23 = 0.0;
																
																// For each index in the array to be reduced
																for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double k = reduceVar$sum$23;
																	
																	// Set the right hand term to a value from the array exped
																	double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$sum$23 = (k + l);
																}
																state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$23);
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
												if((j$var69 == j$var97)) {
													for(int i = 0; i < state.noObs; i += 1) {
														if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
															{
																// Reduction of array exped
																// 
																// A generated name to prevent name collisions if the reduction is implemented more
																// than once in inference and probability code. Initialize the variable to the unit
																// value
																double reduceVar$sum$24 = 0.0;
																
																// For each index in the array to be reduced
																for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double k = reduceVar$sum$24;
																	
																	// Set the right hand term to a value from the array exped
																	double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$sum$24 = (k + l);
																}
																state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$24);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from Gaussian 27. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void inferSample28() {
		if(true) {
			state.constrainedFlag$sample28 = false;
			
			// State to record the weighting of each sample that is consumed. This is the:
			// sum of the sample denominator*(the sample value - the sample nominator).
			double cv$sum = 0.0;
			
			// State for storing the sum of the squares of the sample denominators.
			double cv$denominatorSquareSum = 0.0;
			
			// Flag to record if we have a value for Sigma.
			boolean cv$sigmaNotFound = true;
			
			// State for the value of sigma once we find it.
			double cv$sigmaValue = 1.0;
			{
				// Processing random variable 35.
				{
					{
						{
							// Processing sample task 47 of consumer random variable null.
							{
								{
									for(int var46 = 0; var46 < state.noObs; var46 += 1) {
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = (state.fixedFlag$sample47 || state.constrainedFlag$sample47[((var46 - 0) / 1)]);
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											state.constrainedFlag$sample28 = true;
											{
												{
													{
														{
															{
																// State for tracking the changes that happen to the sampled value between it being
																// consumed and it being produced.
																double cv$denominator = 1.0;
																double cv$numerator = 0.0;
																
																// Record the value of a sample generated by a consuming sample 47 of random variable
																// var35.
																// 
																// Add the denominator squared to the sample denominator
																cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
																
																// Add the weighting of the sample to the sum.
																cv$sum = (cv$sum + (cv$denominator * (state.beta[var46] - cv$numerator)));
																
																// If we have not got the value of sigma yet record it and set a flag so it is not
																// recorded again.
																if(cv$sigmaNotFound) {
																	cv$sigmaValue = state.sigma;
																	cv$sigmaNotFound = false;
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample28)
				// Write out the new value of the sample.
				state.b = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 34 drawn from InverseGamma 33. Inference was performed using a Inverse
	// Gamma to Gaussian conjugate prior.
	private final void inferSample34() {
		if(true) {
			state.constrainedFlag$sample34 = false;
			
			// Variable to track the sum of the difference between the samples and the random
			// variables mean squared.
			double cv$sum = 0.0;
			
			// Variable to record the number of samples from consuming random variables.
			int cv$count = 0;
			{
				// Processing random variable 35.
				{
					{
						{
							// Processing sample task 47 of consumer random variable null.
							{
								{
									for(int var46 = 0; var46 < state.noObs; var46 += 1) {
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = (state.fixedFlag$sample47 || state.constrainedFlag$sample47[((var46 - 0) / 1)]);
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											state.constrainedFlag$sample34 = true;
											{
												{
													{
														{
															{
																// The mean parameter for Gaussian var35.
																double cv$var35$mu = state.b;
																
																// Consume sample task 47 from random variable var35.
																// 
																// The difference between the mean parameter and the value sampled from the Gaussian.
																double cv$var35$diff = (cv$var35$mu - state.beta[var46]);
																
																// Include this sample by adding the square of the difference to the sum.
																cv$sum = (cv$sum + (cv$var35$diff * cv$var35$diff));
																
																// Increment the number of samples in the calculation.
																cv$count = (cv$count + 1);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample34)
				// Write out the new value of the sample.
				state.sigma = Conjugates.sampleConjugateInverseGammaGaussian(state.RNG$, 2.0, 2.0, cv$sum, cv$count);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 47 drawn from Gaussian 35. Inference was performed using Metropolis-Hastings.
	private final void inferSample47(int var46, int threadID$cv$var46, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample47[((var46 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// Metropolis-Hastings
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = state.beta[var46];
			
			// The probability of the random variable generating the originally sampled value
			double cv$originalProbability = 0.0;
			
			// Calculate a proposed variance.
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			
			// Ensure the variance is at least 0.01
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			
			// The proposed new value for the sample
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			
			// The probability of the random variable generating the new sample value.
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample47[((var46 - 0) / 1)] || (cv$valuePos == 0))) {
					// Initialize the summed probabilities to 0.
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					
					// Initialize a counter to track the reached distributions.
					double cv$reachedDistributionSourceRV = 0.0;
					
					// Initialize a log space accumulator to take the product of all the distribution
					// probabilities.
					double cv$accumulatedDistributionProbabilities = 0.0;
					
					// The value currently being tested
					double cv$currentValue;
					if((cv$valuePos == 0))
						// Set the current value to the current state of the tree.
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						
						// Update Sample and intermediate values
						// 
						// Write out the value of the sample to a temporary variable prior to updating the
						// intermediate variables.
						double var47 = cv$proposedValue;
						
						// Guards to ensure that beta is only updated when there is a valid path.
						{
							{
								{
									state.beta[var46] = cv$currentValue;
								}
							}
						}
						
						// Guards to ensure that exped is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 47 and consumer double[] 77.
						{
							{
								for(int i = 0; i < state.noObs; i += 1) {
									if((var46 == i)) {
										for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
											state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
									}
								}
							}
						}
						
						// Guards to ensure that prob is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 47 and consumer double[] 100.
						{
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[][] guard$sample47put101 = scratch.guard$sample47put101$global[threadID$cv$var46];
							{
								for(int i = 0; i < state.noObs; i += 1) {
									if((var46 == i)) {
										for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
											if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
													// Set the flags to false
													guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								for(int i = 0; i < state.noObs; i += 1) {
									if((var46 == i)) {
										for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
											for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
												if((j$var69 == j$var97))
													// Set the flags to false
													guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								for(int i = 0; i < state.noObs; i += 1) {
									if((var46 == i)) {
										for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
											if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
													if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
														{
															// Reduction of array exped
															// 
															// A generated name to prevent name collisions if the reduction is implemented more
															// than once in inference and probability code. Initialize the variable to the unit
															// value
															double reduceVar$sum$25 = 0.0;
															
															// For each index in the array to be reduced
															for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																// Set the left hand term of the reduction function to the return variable value.
																double k = reduceVar$sum$25;
																
																// Set the right hand term to a value from the array exped
																double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																
																// Execute the reduction function, saving the result into the return value.
																// 
																// Copy the result of the reduction into the variable returned by the reduction.
																reduceVar$sum$25 = (k + l);
															}
															state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$25);
														}
													}
												}
											}
										}
									}
								}
							}
							{
								for(int i = 0; i < state.noObs; i += 1) {
									if((var46 == i)) {
										for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
											for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
												if((j$var69 == j$var97)) {
													if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
														{
															// Reduction of array exped
															// 
															// A generated name to prevent name collisions if the reduction is implemented more
															// than once in inference and probability code. Initialize the variable to the unit
															// value
															double reduceVar$sum$26 = 0.0;
															
															// For each index in the array to be reduced
															for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																// Set the left hand term of the reduction function to the return variable value.
																double k = reduceVar$sum$26;
																
																// Set the right hand term to a value from the array exped
																double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																
																// Execute the reduction function, saving the result into the return value.
																// 
																// Copy the result of the reduction into the variable returned by the reduction.
																reduceVar$sum$26 = (k + l);
															}
															state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$26);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					{
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < state.sigma)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - state.b) / Math.sqrt(state.sigma))) - (0.5 * Math.log(state.sigma))):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 101.
						{
							// Looking for a path between Sample 47 and consumer Categorical 101.
							{
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								boolean[] guard$sample47categorical102 = scratch.guard$sample47categorical102$global[threadID$cv$var46];
								{
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												if(((0 <= j$var69) && (j$var69 < state.noProducts)))
													// Set the flags to false
													guard$sample47categorical102[((i - 0) / 1)] = false;
											}
										}
									}
								}
								{
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
													if((j$var69 == j$var97))
														// Set the flags to false
														guard$sample47categorical102[((i - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									double traceTempVariable$var71$9_1 = cv$currentValue;
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												double traceTempVariable$k$9_4 = Math.exp((state.ut[j$var69] - (traceTempVariable$var71$9_1 * state.Prices[i][j$var69])));
												if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
													if((0 < state.noProducts)) {
														// Reduction of array exped
														// 
														// A generated name to prevent name collisions if the reduction is implemented more
														// than once in inference and probability code. Initialize the variable to the unit
														// value
														double reduceVar$sum$27 = 0.0;
														
														// Reduce for every value except a masked value which will be skipped.
														for(int cv$reduction1963Index = 0; cv$reduction1963Index < j$var69; cv$reduction1963Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$sum$27;
															
															// Set the right hand term to a value from the array exped
															double l = state.exped[((i - 0) / 1)][cv$reduction1963Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$sum$27 = (k + l);
														}
														for(int cv$reduction1963Index = (j$var69 + 1); cv$reduction1963Index < state.noProducts; cv$reduction1963Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$sum$27;
															
															// Set the right hand term to a value from the array exped
															double l = state.exped[((i - 0) / 1)][cv$reduction1963Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$sum$27 = (k + l);
														}
														double cv$reduced82 = reduceVar$sum$27;
														
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$27 = (traceTempVariable$k$9_4 + cv$reduced82);
														double traceTempVariable$sum$9_5 = reduceVar$sum$27;
														if(!guard$sample47categorical102[((i - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample47categorical102[((i - 0) / 1)] = true;
															
															// Processing sample task 103 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		state.constrainedFlag$sample47[((var46 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							// Record the probability of sample task 103 generating output with current configuration.
																							if(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 103 with the current configuration.
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																			else
																				cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								{
									double traceTempVariable$var71$10_1 = cv$currentValue;
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												double traceTempVariable$var98$10_4 = Math.exp((state.ut[j$var69] - (traceTempVariable$var71$10_1 * state.Prices[i][j$var69])));
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
													if((j$var69 == j$var97)) {
														if(!guard$sample47categorical102[((i - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample47categorical102[((i - 0) / 1)] = true;
															
															// Processing sample task 103 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		state.constrainedFlag$sample47[((var46 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							// Record the probability of sample task 103 generating output with current configuration.
																							if(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 103 with the current configuration.
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																			else
																				cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						
						// Add the values for the source and any standard consumers for this configuration
						// of arguments to the source.
						if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
						else {
							// If the second value is -infinity.
							if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
								cv$stateProbabilityValue = cv$accumulatedProbabilities;
							else
								cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
						}
					}
					
					// Save the probability of the original value.
					if((cv$valuePos == 0))
						cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					
					// Save the probability of the proposed value.
					else
						cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					
					// The probability ration for the proposed value and the current value.
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					
					// Test if the probability of the sample is sufficient to keep the value. This needs
					// to be less than or equal as otherwise if the proposed value is not possible and
					// the random value is 0 an impossible value will be accepted.
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
							// If it is not revert the changes.
							// 
							// Set the sample value
							// Write out the value of the sample to a temporary variable prior to updating the
							// intermediate variables.
							double var47 = cv$originalValue;
							
							// Guards to ensure that beta is only updated when there is a valid path.
							{
								{
									{
										state.beta[var46] = var47;
									}
								}
							}
							
							// Guards to ensure that exped is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 47 and consumer double[] 77.
							{
								{
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
												state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
										}
									}
								}
							}
							
							// Guards to ensure that prob is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 47 and consumer double[] 100.
							{
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								boolean[][] guard$sample47put101 = scratch.guard$sample47put101$global[threadID$cv$var46];
								{
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
													for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
														// Set the flags to false
														guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
													if((j$var69 == j$var97))
														// Set the flags to false
														guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
													for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
														if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
															{
																// Reduction of array exped
																// 
																// A generated name to prevent name collisions if the reduction is implemented more
																// than once in inference and probability code. Initialize the variable to the unit
																// value
																double reduceVar$sum$28 = 0.0;
																
																// For each index in the array to be reduced
																for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double k = reduceVar$sum$28;
																	
																	// Set the right hand term to a value from the array exped
																	double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$sum$28 = (k + l);
																}
																state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$28);
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
													if((j$var69 == j$var97)) {
														if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
															{
																// Reduction of array exped
																// 
																// A generated name to prevent name collisions if the reduction is implemented more
																// than once in inference and probability code. Initialize the variable to the unit
																// value
																double reduceVar$sum$29 = 0.0;
																
																// For each index in the array to be reduced
																for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double k = reduceVar$sum$29;
																	
																	// Set the right hand term to a value from the array exped
																	double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$sum$29 = (k + l);
																}
																state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$29);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
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
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.noObs; i += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = state.choices[i];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][cv$sampleValue])) && (state.prob[((i - 0) / 1)][cv$sampleValue] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				state.logProbability$var102 = cv$accumulator;
			
			// Update the variable probability
			state.logProbability$choices = (state.logProbability$choices + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample103 = (state.fixedFlag$sample21 && state.fixedFlag$sample47);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.noObs; i += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var102;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$choices = (state.logProbability$choices + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample21 using sampled
	// values.
	private final void logProbabilityValue$sample21() {
		// Determine if we need to calculate the values for sample task 21 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample21) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = state.ut[var20];
						{
							{
								double var7 = 0.0;
								double var8 = 10.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var8)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var7) / Math.sqrt(var8))) - (0.5 * Math.log(var8))):Double.NEGATIVE_INFINITY));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				
				// Only update the sample if it was reached, otherwise the NaN will be
				// erroneously over written.
				if(cv$sampleReached)
					// Store the sample task probability
					state.logProbability$sample21[((var20 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 21 and consumer double[] 100.
				{
					{
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int i = 0; i < state.noObs; i += 1) {
									if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
										for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$prob) {
												// Set the guard so the update is only applied once.
												cv$guard$prob = true;
												
												// Update the variable probability
												state.logProbability$prob = (state.logProbability$prob + cv$sampleProbability);
											}
										}
									}
								}
							}
						}
					}
					{
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
									if((j$var69 == j$var97)) {
										for(int i = 0; i < state.noObs; i += 1) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$prob) {
												// Set the guard so the update is only applied once.
												cv$guard$prob = true;
												
												// Update the variable probability
												state.logProbability$prob = (state.logProbability$prob + cv$sampleProbability);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Update the variable probability
			state.logProbability$ut = (state.logProbability$ut + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample21 = state.fixedFlag$sample21;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
				double cv$sampleValue = state.logProbability$sample21[((var20 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 21 and consumer double[] 100.
				{
					{
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int i = 0; i < state.noObs; i += 1) {
									if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
										for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$prob) {
												// Set the guard so the update is only applied once.
												cv$guard$prob = true;
												
												// Update the variable probability
												state.logProbability$prob = (state.logProbability$prob + cv$sampleValue);
											}
										}
									}
								}
							}
						}
					}
					{
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
									if((j$var69 == j$var97)) {
										for(int i = 0; i < state.noObs; i += 1) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$prob) {
												// Set the guard so the update is only applied once.
												cv$guard$prob = true;
												
												// Update the variable probability
												state.logProbability$prob = (state.logProbability$prob + cv$sampleValue);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$ut = (state.logProbability$ut + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample28 using sampled
	// values.
	private final void logProbabilityValue$sample28() {
		// Determine if we need to calculate the values for sample task 28 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample28) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = state.b;
					{
						{
							double var25 = 0.0;
							double var26 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var26)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var25) / Math.sqrt(var26))) - (0.5 * Math.log(var26))):Double.NEGATIVE_INFINITY));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			
			// Add the probability of this sample task to the sample task accumulator.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Store the sample task probability
			state.logProbability$b = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample28 = state.fixedFlag$sample28;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$b;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample34 using sampled
	// values.
	private final void logProbabilityValue$sample34() {
		// Determine if we need to calculate the values for sample task 34 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample34) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = state.sigma;
					{
						{
							double var31 = 2.0;
							double var32 = 2.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var31, var32));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			
			// Add the probability of this sample task to the sample task accumulator.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Store the sample task probability
			state.logProbability$sigma = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample34)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample34 = state.fixedFlag$sample34;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$sigma;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample34)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample47 using sampled
	// values.
	private final void logProbabilityValue$sample47() {
		// Determine if we need to calculate the values for sample task 47 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample47) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var46 = 0; var46 < state.noObs; var46 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = state.beta[var46];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < state.sigma)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.b) / Math.sqrt(state.sigma))) - (0.5 * Math.log(state.sigma))):Double.NEGATIVE_INFINITY));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				
				// Only update the sample if it was reached, otherwise the NaN will be
				// erroneously over written.
				if(cv$sampleReached)
					// Store the sample task probability
					state.logProbability$sample47[((var46 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 47 and consumer double[] 100.
				{
					{
						for(int i = 0; i < state.noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
										for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$prob) {
												// Set the guard so the update is only applied once.
												cv$guard$prob = true;
												
												// Update the variable probability
												state.logProbability$prob = (state.logProbability$prob + cv$sampleProbability);
											}
										}
									}
								}
							}
						}
					}
					{
						for(int i = 0; i < state.noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
										if((j$var69 == j$var97)) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$prob) {
												// Set the guard so the update is only applied once.
												cv$guard$prob = true;
												
												// Update the variable probability
												state.logProbability$prob = (state.logProbability$prob + cv$sampleProbability);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Update the variable probability
			state.logProbability$beta = (state.logProbability$beta + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample47 = ((state.fixedFlag$sample47 && state.fixedFlag$sample28) && state.fixedFlag$sample34);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var46 = 0; var46 < state.noObs; var46 += 1) {
				double cv$sampleValue = state.logProbability$sample47[((var46 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 47 and consumer double[] 100.
				{
					{
						for(int i = 0; i < state.noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
										for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$prob) {
												// Set the guard so the update is only applied once.
												cv$guard$prob = true;
												
												// Update the variable probability
												state.logProbability$prob = (state.logProbability$prob + cv$sampleValue);
											}
										}
									}
								}
							}
						}
					}
					{
						for(int i = 0; i < state.noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
										if((j$var69 == j$var97)) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$prob) {
												// Set the guard so the update is only applied once.
												cv$guard$prob = true;
												
												// Update the variable probability
												state.logProbability$prob = (state.logProbability$prob + cv$sampleValue);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$beta = (state.logProbability$beta + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1) {
						if(!state.fixedFlag$sample21)
							state.ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!state.fixedFlag$sample28)
			state.b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!state.fixedFlag$sample47)
							state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(RNG$1)) + state.b);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1) {
										if(!(state.fixedFlag$sample21 && state.fixedFlag$sample47))
											state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
									}
							}
						);
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$34 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$34;
							
							// Set the right hand term to a value from the array exped
							double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!(state.fixedFlag$sample21 && state.fixedFlag$sample47))
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$sum$34 = (k + l);
						}
						
						// Alternative name for reduceVar$sum$34 to make it effectively final.
						double reduceVar$sum$34$1 = reduceVar$sum$34;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1) {
										if(!(state.fixedFlag$sample21 && state.fixedFlag$sample47))
											state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$34$1);
									}
							}
						);
						state.choices[i] = DistributionSampling.sampleCategorical(RNG$1, state.prob[((i - 0) / 1)], state.noProducts);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1) {
						if(!state.fixedFlag$sample21)
							state.ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!state.fixedFlag$sample28)
			state.b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!state.fixedFlag$sample47)
							state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(RNG$1)) + state.b);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
										state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
							}
						);
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$38 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$38;
							
							// Set the right hand term to a value from the array exped
							double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$sum$38 = (k + l);
						}
						
						// Alternative name for reduceVar$sum$38 to make it effectively final.
						double reduceVar$sum$38$1 = reduceVar$sum$38;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
										state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$38$1);
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
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1) {
						if(!state.fixedFlag$sample21)
							state.ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!state.fixedFlag$sample28)
			state.b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!state.fixedFlag$sample47)
							state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(RNG$1)) + state.b);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
										state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
							}
						);
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$35 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$35;
							
							// Set the right hand term to a value from the array exped
							double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$sum$35 = (k + l);
						}
						
						// Alternative name for reduceVar$sum$35 to make it effectively final.
						double reduceVar$sum$35$1 = reduceVar$sum$35;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
										state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$35$1);
							}
						);
						state.choices[i] = DistributionSampling.sampleCategorical(RNG$1, state.prob[((i - 0) / 1)], state.noProducts);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1) {
						if(!state.fixedFlag$sample21)
							state.ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!state.fixedFlag$sample28)
			state.b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!state.fixedFlag$sample47)
							state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(RNG$1)) + state.b);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1) {
										if(!(state.fixedFlag$sample21 && state.fixedFlag$sample47))
											state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
									}
							}
						);
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$36 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$36;
							
							// Set the right hand term to a value from the array exped
							double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!(state.fixedFlag$sample21 && state.fixedFlag$sample47))
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$sum$36 = (k + l);
						}
						
						// Alternative name for reduceVar$sum$36 to make it effectively final.
						double reduceVar$sum$36$1 = reduceVar$sum$36;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1) {
										if(!(state.fixedFlag$sample21 && state.fixedFlag$sample47))
											state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$36$1);
									}
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
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1) {
						if(!state.fixedFlag$sample21)
							state.ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!state.fixedFlag$sample28)
			state.b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!state.fixedFlag$sample47)
							state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(RNG$1)) + state.b);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
										state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
							}
						);
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$37 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$37;
							
							// Set the right hand term to a value from the array exped
							double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$sum$37 = (k + l);
						}
						
						// Alternative name for reduceVar$sum$37 to make it effectively final.
						double reduceVar$sum$37$1 = reduceVar$sum$37;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
										state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$37$1);
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
			for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
				if(!state.fixedFlag$sample21)
					inferSample21(var20);
			}
			if(!state.fixedFlag$sample28)
				inferSample28();
			if(!state.fixedFlag$sample34)
				inferSample34();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
							if(!state.fixedFlag$sample47)
								inferSample47(var46, threadID$var46, RNG$1);
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
							if(!state.fixedFlag$sample47)
								inferSample47(var46, threadID$var46, RNG$1);
						}
				}
			);
			if(!state.fixedFlag$sample34)
				inferSample34();
			if(!state.fixedFlag$sample28)
				inferSample28();
			for(int var20 = (state.noProducts - ((((state.noProducts - 1) - 0) % 1) + 1)); var20 >= ((0 - 1) + 1); var20 -= 1) {
				if(!state.fixedFlag$sample21)
					inferSample21(var20);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
			if(!state.constrainedFlag$sample21[((var20 - 0) / 1)])
				drawValueSample21(var20);
		}
		if(!state.constrainedFlag$sample28)
			drawValueSample28();
		if(!state.constrainedFlag$sample34)
			drawValueSample34();
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!state.constrainedFlag$sample47[((var46 - 0) / 1)])
							drawValueSample47(var46, threadID$var46, RNG$1);
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
		state.logProbability$ut = 0.0;
		state.logProbability$prob = 0.0;
		if(!state.fixedProbFlag$sample21) {
			for(int var20 = 0; var20 < state.noProducts; var20 += 1)
				state.logProbability$sample21[((var20 - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample28)
			state.logProbability$b = Double.NaN;
		if(!state.fixedProbFlag$sample34)
			state.logProbability$sigma = Double.NaN;
		state.logProbability$beta = 0.0;
		if(!state.fixedProbFlag$sample47) {
			for(int var46 = 0; var46 < state.noObs; var46 += 1)
				state.logProbability$sample47[((var46 - 0) / 1)] = Double.NaN;
		}
		state.logProbability$choices = 0.0;
		if(!state.fixedProbFlag$sample103)
			state.logProbability$var102 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		// Set all the values in the array
		for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < state.constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
			state.constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample21$1 = 0; index$constrainedFlag$sample21$1 < state.constrainedFlag$sample21.length; index$constrainedFlag$sample21$1 += 1)
			state.constrainedFlag$sample21[index$constrainedFlag$sample21$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample21)
			logProbabilityValue$sample21();
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(state.fixedFlag$sample47)
			logProbabilityValue$sample47();
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
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
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
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
		logProbabilityValue$sample103();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		int[] cv$source1 = state.ObsChoices;
		int[] cv$target1 = state.choices;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
										state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
							}
						);
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$39 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$39;
							
							// Set the right hand term to a value from the array exped
							double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$sum$39 = (k + l);
						}
						
						// Alternative name for reduceVar$sum$39 to make it effectively final.
						double reduceVar$sum$39$1 = reduceVar$sum$39;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
										state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$39$1);
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
		     + "\n"
		     + "model DiscreteChoiceRandCoeff(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = gaussian(0, 10).sample(noProducts);\n"
		     + "    //can we set the first element to 0? like ut[0] <~ 0\n"
		     + "\n"
		     + "    //priors for distribution of beta\n"
		     + "    double b = gaussian(0,10).sample();\n"
		     + "    double sigma =  inverseGamma(2,2).sample();\n"
		     + "\n"
		     + "    double[] beta = gaussian(b, sigma).sample(noObs);\n"
		     + "\n"
		     + "    int[] choices = new int[noObs];\n"
		     + "\n"
		     + "    for (int i:[0..noObs)){\n"
		     + "        // calculate choice probabilities for consumer i\n"
		     + "\n"
		     + "        double[] exped = new double[noProducts];\n"
		     + "        for(int j : [0..noProducts)) {\n"
		     + "            exped[j] = exp(ut[j]- beta[i]*Prices[i][j]);\n"
		     + "            }\n"
		     + "        double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "        public double[] prob = new double[noProducts];\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            prob[j] = exped[j] / sum;\n"
		     + "        }\n"
		     + "        // emit choices of consumer i\n"
		     + "        choices[i] = categorical(prob).sample();\n"
		     + "                                }\n"
		     + "\n"
		     + "    // assert that generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}