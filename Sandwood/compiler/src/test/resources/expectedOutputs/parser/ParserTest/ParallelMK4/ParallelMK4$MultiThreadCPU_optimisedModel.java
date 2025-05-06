package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ParallelMK4$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements ParallelMK4$CoreInterface {
	
	// Declare the variables for the model.
	private boolean fixedFlag$sample61 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample61 = false;
	private int[] generated;
	private double[][] indirection1;
	private double[][] indirection2;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$generated;
	private double logProbability$indirection1;
	private double logProbability$indirection2;
	private double[] logProbability$sample103;
	private double[][] logProbability$sample61;
	private double[] logProbability$var100;
	private double[][] logProbability$var58;
	private int[] observed;
	private boolean system$gibbsForward = true;

	public ParallelMK4$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for fixedFlag$sample61.
	@Override
	public final boolean get$fixedFlag$sample61() {
		return fixedFlag$sample61;
	}

	// Setter for fixedFlag$sample61.
	@Override
	public final void set$fixedFlag$sample61(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample61 including if probabilities
		// need to be updated.
		fixedFlag$sample61 = cv$value;
		
		// Should the probability of sample 61 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample61" with its value "cv$value".
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample61" with its value "cv$value".
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
	}

	// Getter for generated.
	@Override
	public final int[] get$generated() {
		return generated;
	}

	// Getter for indirection1.
	@Override
	public final double[][] get$indirection1() {
		return indirection1;
	}

	// Setter for indirection1.
	@Override
	public final void set$indirection1(double[][] cv$value) {
		// Set flags for all the side effects of indirection1 including if probabilities need
		// to be updated.
		// Set indirection1
		indirection1 = cv$value;
		
		// Unset the fixed probability flag for sample 61 as it depends on indirection1.
		fixedProbFlag$sample61 = false;
		
		// Unset the fixed probability flag for sample 103 as it depends on indirection1.
		fixedProbFlag$sample103 = false;
	}

	// Getter for indirection2.
	@Override
	public final double[][] get$indirection2() {
		return indirection2;
	}

	// Getter for length$observed.
	@Override
	public final int get$length$observed() {
		return length$observed;
	}

	// Setter for length$observed.
	@Override
	public final void set$length$observed(int cv$value) {
		length$observed = cv$value;
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

	// Getter for logProbability$generated.
	@Override
	public final double get$logProbability$generated() {
		return logProbability$generated;
	}

	// Getter for logProbability$indirection1.
	@Override
	public final double get$logProbability$indirection1() {
		return logProbability$indirection1;
	}

	// Getter for logProbability$indirection2.
	@Override
	public final double get$logProbability$indirection2() {
		return logProbability$indirection2;
	}

	// Getter for observed.
	@Override
	public final int[] get$observed() {
		return observed;
	}

	// Setter for observed.
	@Override
	public final void set$observed(int[] cv$value) {
		// Set observed
		observed = cv$value;
	}

	// Calculate the probability of the samples represented by sample103 using sampled
	// values.
	private final void logProbabilityValue$sample103() {
		// Determine if we need to calculate the values for sample task 103 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample103) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int m = 0; m < length$observed; m += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = generated[m];
				
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
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
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 10))?Math.log(indirection2[m][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var100[m] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample103[m] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$generated = (logProbability$generated + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample103 = fixedFlag$sample61;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int m = 0; m < length$observed; m += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample103[m];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var100[m] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$generated = (logProbability$generated + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample61 using sampled
	// values.
	private final void logProbabilityValue$sample61() {
		// Determine if we need to calculate the values for sample task 61 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample61) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				for(int j = 0; j < 10; j += 1) {
					// The sample value to calculate the probability of generating
					double cv$sampleValue = indirection1[i][j];
					
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// An accumulator for log probabilities.
					// 
					// Store the value of the function call, so the function call is only made once.
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
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 1.0))?0.0:Double.NEGATIVE_INFINITY);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					logProbability$var58[i][j] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample61[i][j] = cv$distributionAccumulator;
					
					// Update the variable probability
					logProbability$indirection2 = (logProbability$indirection2 + cv$distributionAccumulator);
				}
			}
			
			// Update the variable probability
			logProbability$indirection1 = (logProbability$indirection1 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample61 = fixedFlag$sample61;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				for(int j = 0; j < 10; j += 1) {
					double cv$sampleValue = logProbability$sample61[i][j];
					cv$accumulator = (cv$accumulator + cv$sampleValue);
					logProbability$var58[i][j] = cv$sampleValue;
					
					// Update the variable probability
					logProbability$indirection2 = (logProbability$indirection2 + cv$sampleValue);
				}
			}
			
			// Update the variable probability
			logProbability$indirection1 = (logProbability$indirection1 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 61 drawn from Uniform 58. Inference was performed using Metropolis-Hastings.
	private final void sample61(int i, int j, int threadID$cv$i, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = indirection1[i][j];
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		
		// Variable declaration of cv$originalProbability moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		// 
		// Unrolled loop
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 103 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$var56" with its value "0.0".
		// 
		// Set the current value to the current state of the tree.
		double cv$originalProbability = ((((0.0 <= generated[i]) && (generated[i] < 10))?Math.log(indirection2[i][generated[i]]):Double.NEGATIVE_INFINITY) + (((0.0 <= cv$originalValue) && (cv$originalValue < 1.0))?0.0:Double.NEGATIVE_INFINITY));
		indirection1[i][j] = cv$proposedValue;
		
		// Substituted "k" with its value "i".
		// 
		// Substituted "k" with its value "i".
		indirection2[i][j] = indirection1[i][j];
		
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$var56" with its value "0.0".
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 103 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$var56" with its value "0.0".
		double cv$accumulatedProbabilities = ((((0.0 <= generated[i]) && (generated[i] < 10))?Math.log(indirection2[i][generated[i]]):Double.NEGATIVE_INFINITY) + (((0.0 <= cv$proposedValue) && (cv$proposedValue < 1.0))?0.0:Double.NEGATIVE_INFINITY));
		
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
			indirection1[i][j] = cv$originalValue;
			
			// Substituted "k" with its value "i".
			// 
			// Substituted "k" with its value "i".
			indirection2[i][j] = indirection1[i][j];
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
		// Constructor for generated
		generated = new int[length$observed];
		
		// If indirection1 has not been set already allocate space.
		if(!fixedFlag$sample61) {
			// Constructor for indirection1
			indirection1 = new double[length$observed][];
			for(int var16 = 0; var16 < length$observed; var16 += 1)
				indirection1[var16] = new double[10];
		}
		
		// Constructor for indirection2
		indirection2 = new double[length$observed][];
		for(int var31 = 0; var31 < length$observed; var31 += 1)
			indirection2[var31] = new double[10];
		
		// Constructor for logProbability$var58
		logProbability$var58 = new double[length$observed][];
		for(int i = 0; i < length$observed; i += 1)
			logProbability$var58[i] = new double[10];
		
		// Constructor for logProbability$sample61
		logProbability$sample61 = new double[length$observed][];
		for(int i = 0; i < length$observed; i += 1)
			logProbability$sample61[i] = new double[10];
		
		// Constructor for logProbability$var100
		logProbability$var100 = new double[length$observed];
		
		// Constructor for logProbability$sample103
		logProbability$sample103 = new double[length$observed];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample61) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var55 = indirection1[i];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1)
											var55[j] = DistributionSampling.sampleUniform(RNG$2);
								}
							);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
							int k = index$k;
							int threadID$k = threadID$index$k;
							double[] var83 = indirection2[k];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int l = forStart$l; l < forEnd$l; l += 1)
											var83[l] = indirection1[k][l];
								}
							);
						}
				}
			);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$m, int forEnd$m, int threadID$m, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int m = forStart$m; m < forEnd$m; m += 1)
						generated[m] = DistributionSampling.sampleCategorical(RNG$1, indirection2[m], 10);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample61)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var55 = indirection1[i];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1)
											var55[j] = DistributionSampling.sampleUniform(RNG$2);
								}
							);
						}
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
						int k = index$k;
						int threadID$k = threadID$index$k;
						double[] var83 = indirection2[k];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 10, 1,
							(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int l = forStart$l; l < forEnd$l; l += 1)
										var83[l] = indirection1[k][l];
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
		if(!fixedFlag$sample61)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var55 = indirection1[i];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1)
											var55[j] = DistributionSampling.sampleUniform(RNG$2);
								}
							);
						}
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
						int k = index$k;
						int threadID$k = threadID$index$k;
						double[] var83 = indirection2[k];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 10, 1,
							(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int l = forStart$l; l < forEnd$l; l += 1)
										var83[l] = indirection1[k][l];
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$m, int forEnd$m, int threadID$m, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int m = forStart$m; m < forEnd$m; m += 1)
						generated[m] = DistributionSampling.sampleCategorical(RNG$1, indirection2[m], 10);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample61) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var55 = indirection1[i];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1)
											var55[j] = DistributionSampling.sampleUniform(RNG$2);
								}
							);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
							int k = index$k;
							int threadID$k = threadID$index$k;
							double[] var83 = indirection2[k];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int l = forStart$l; l < forEnd$l; l += 1)
											var83[l] = indirection1[k][l];
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
		if(!fixedFlag$sample61)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var55 = indirection1[i];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1)
											var55[j] = DistributionSampling.sampleUniform(RNG$2);
								}
							);
						}
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
						int k = index$k;
						int threadID$k = threadID$index$k;
						double[] var83 = indirection2[k];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 10, 1,
							(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int l = forStart$l; l < forEnd$l; l += 1)
										var83[l] = indirection1[k][l];
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
		if(!fixedFlag$sample61) {
			// Infer the samples in chronological order.
			if(system$gibbsForward)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, length$observed, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i = forStart$i; i < forEnd$i; i += 1) {
								for(int j = 0; j < 10; j += 1)
									sample61(i, j, threadID$i, RNG$1);
							}
					}
				);
			// Infer the samples in reverse chronological order.
			else
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, length$observed, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i = forStart$i; i < forEnd$i; i += 1) {
								for(int j = 9; j >= 0; j -= 1)
									sample61(i, j, threadID$i, RNG$1);
							}
					}
				);
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
		for(int i = 0; i < length$observed; i += 1) {
			for(int j = 0; j < 10; j += 1)
				logProbability$var58[i][j] = Double.NaN;
		}
		logProbability$indirection1 = 0.0;
		logProbability$indirection2 = 0.0;
		if(!fixedProbFlag$sample61) {
			for(int i = 0; i < length$observed; i += 1) {
				for(int j = 0; j < 10; j += 1)
					logProbability$sample61[i][j] = Double.NaN;
			}
		}
		for(int m = 0; m < length$observed; m += 1)
			logProbability$var100[m] = Double.NaN;
		logProbability$generated = 0.0;
		if(!fixedProbFlag$sample103) {
			for(int m = 0; m < length$observed; m += 1)
				logProbability$sample103[m] = Double.NaN;
		}
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample61)
			logProbabilityValue$sample61();
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
		logProbabilityValue$sample61();
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
		logProbabilityValue$sample61();
		logProbabilityValue$sample103();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = generated.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			generated[cv$index1] = observed[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
						int k = index$k;
						int threadID$k = threadID$index$k;
						double[] var83 = indirection2[k];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 10, 1,
							(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int l = forStart$l; l < forEnd$l; l += 1)
										var83[l] = indirection1[k][l];
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
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model ParallelMK4(int[] observed) {\n"
		     + "    int[] generated = new int[observed.length];\n"
		     + "    double[][] indirection1 = new double[observed.length][10];\n"
		     + "    double[][] indirection2 = new double[observed.length][10];\n"
		     + "\n"
		     + "    for(int i=0; i<observed.length; i++) {\n"
		     + "        for(int j=0; j<10; j++) {\n"
		     + "            indirection1[i][j] = uniform(0.0, 1.0).sample();\n"
		     + "        }\n"
		     + "    }\n"
		     + "    \n"
		     + "    for(int k=0; k<observed.length; k++) {\n"
		     + "        for(int l=0; l<10; l++) {\n"
		     + "            indirection2[k][l] = indirection1[k][l];\n"
		     + "        }\n"
		     + "    }\n"
		     + "    \n"
		     + "    for(int m=0; m<observed.length; m++) {\n"
		     + "        generated[m] = categorical(indirection2[m]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    generated.observe(observed);\n"
		     + "}";
	}
}