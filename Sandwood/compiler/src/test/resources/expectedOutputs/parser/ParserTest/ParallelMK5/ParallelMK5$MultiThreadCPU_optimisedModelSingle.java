package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ParallelMK5$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements ParallelMK5$CoreInterface {
	
	// Declare the variables for the model.
	private boolean fixedFlag$sample39 = false;
	private boolean fixedFlag$sample63 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample63 = false;
	private int[] generated;
	private double[][] indirection1;
	private double[][] indirection2;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$generated;
	private double logProbability$indirection1;
	private double logProbability$indirection2;
	private double[][] logProbability$sample39;
	private double logProbability$var33;
	private double logProbability$var55;
	private double logProbability$var56;
	private int[] observed;
	private boolean setFlag$generated = false;
	private boolean setFlag$indirection1 = false;
	private boolean setFlag$indirection2 = false;
	private boolean system$gibbsForward = true;

	public ParallelMK5$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for fixedFlag$sample39.
	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	// Setter for fixedFlag$sample39.
	@Override
	public final void set$fixedFlag$sample39(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample39 including if probabilities
		// need to be updated.
		fixedFlag$sample39 = cv$value;
		
		// Should the probability of sample 39 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample39" with its value "cv$value".
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
		
		// Should the probability of sample 63 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample39" with its value "cv$value".
		fixedProbFlag$sample63 = (cv$value && fixedProbFlag$sample63);
	}

	// Getter for fixedFlag$sample63.
	@Override
	public final boolean get$fixedFlag$sample63() {
		return fixedFlag$sample63;
	}

	// Setter for fixedFlag$sample63.
	@Override
	public final void set$fixedFlag$sample63(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample63 including if probabilities
		// need to be updated.
		fixedFlag$sample63 = cv$value;
		
		// Should the probability of sample 63 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample63" with its value "cv$value".
		fixedProbFlag$sample63 = (cv$value && fixedProbFlag$sample63);
	}

	// Getter for generated.
	@Override
	public final int[] get$generated() {
		return generated;
	}

	// Setter for generated.
	@Override
	public final void set$generated(int[] cv$value) {
		// Set generated with flag to mark that it has been set so another array doesn't need
		// to be constructed
		generated = cv$value;
		setFlag$generated = true;
	}

	// Getter for indirection1.
	@Override
	public final double[][] get$indirection1() {
		return indirection1;
	}

	// Setter for indirection1.
	@Override
	public final void set$indirection1(double[][] cv$value) {
		// Set indirection1 with flag to mark that it has been set so another array doesn't
		// need to be constructed
		indirection1 = cv$value;
		setFlag$indirection1 = true;
	}

	// Getter for indirection2.
	@Override
	public final double[][] get$indirection2() {
		return indirection2;
	}

	// Setter for indirection2.
	@Override
	public final void set$indirection2(double[][] cv$value) {
		// Set indirection2 with flag to mark that it has been set so another array doesn't
		// need to be constructed
		indirection2 = cv$value;
		setFlag$indirection2 = true;
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
		// Set observed with flag to mark that it has been set so another array doesn't need
		// to be constructed
		observed = cv$value;
	}

	// Calculate the probability of the samples represented by sample39 using sampled
	// values.
	private final void logProbabilityValue$sample39() {
		// Determine if we need to calculate the values for sample task 39 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample39) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < length$observed; j += 1) {
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
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY);
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					logProbability$sample39[i][j] = cv$distributionAccumulator;
					
					// Update the variable probability
					logProbability$indirection2 = (logProbability$indirection2 + cv$distributionAccumulator);
				}
			}
			logProbability$var33 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$indirection1 = (logProbability$indirection1 + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample39)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample39 = fixedFlag$sample39;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < length$observed; j += 1) {
					double cv$sampleValue = logProbability$sample39[i][j];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Update the variable probability
					logProbability$indirection2 = (logProbability$indirection2 + cv$sampleValue);
				}
			}
			logProbability$var33 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$indirection1 = (logProbability$indirection1 + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample63 using sampled
	// values.
	private final void logProbabilityValue$sample63() {
		// Determine if we need to calculate the values for sample task 63 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample63) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int m = 0; m < length$observed; m += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = generated[m];
				double[] var54 = indirection2[m];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var54.length))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var55 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var56 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$generated = (logProbability$generated + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample63 = (fixedFlag$sample63 && fixedFlag$sample39);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var55 = logProbability$var56;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$generated = (logProbability$generated + logProbability$var56);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var56);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var56);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 39 drawn from Uniform 33. Inference was performed using Metropolis-Hastings.
	private final void sample39(int i, int j, int threadID$cv$j, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = indirection1[i][j];
		
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
			// Variable declaration of cv$temp$2$var54 moved.
			// 
			// Constructing a random variable input for use later.
			// 
			// Substituted "m" with its value "j".
			double[] cv$temp$2$var54 = indirection2[j];
			
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
			// Recorded the probability of reaching sample task 63 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$var31" with its value "0.0".
			// 
			// Set the current value to the current state of the tree.
			cv$originalProbability = ((((0.0 <= generated[j]) && (generated[j] < cv$temp$2$var54.length))?Math.log(cv$temp$2$var54[generated[j]]):Double.NEGATIVE_INFINITY) + (((0.0 <= cv$originalValue) && (cv$originalValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY));
		}
		indirection1[i][j] = cv$proposedValue;
		
		// Substituted "l" with its value "i".
		// 
		// Substituted "k" with its value "j".
		indirection2[j][i] = indirection1[i][j];
		
		// Variable declaration of cv$temp$2$var54 moved.
		// 
		// Constructing a random variable input for use later.
		// 
		// Substituted "m" with its value "j".
		double[] cv$temp$2$var54 = indirection2[j];
		
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$var31" with its value "0.0".
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 63 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$var31" with its value "0.0".
		double cv$accumulatedProbabilities = ((((0.0 <= generated[j]) && (generated[j] < cv$temp$2$var54.length))?Math.log(cv$temp$2$var54[generated[j]]):Double.NEGATIVE_INFINITY) + (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY));
		
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
			
			// Substituted "l" with its value "i".
			// 
			// Substituted "k" with its value "j".
			indirection2[j][i] = indirection1[i][j];
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
		// If generated has not been set already allocate space.
		if(!setFlag$generated)
			// Constructor for generated
			generated = new int[length$observed];
		
		// If indirection1 has not been set already allocate space.
		if(!setFlag$indirection1) {
			// Constructor for indirection1
			indirection1 = new double[10][];
			for(int var11 = 0; var11 < 10; var11 += 1)
				indirection1[var11] = new double[length$observed];
		}
		
		// If indirection2 has not been set already allocate space.
		if(!setFlag$indirection2) {
			// Constructor for indirection2
			indirection2 = new double[length$observed][];
			for(int var19 = 0; var19 < length$observed; var19 += 1)
				indirection2[var19] = new double[10];
		}
		
		// Constructor for logProbability$sample39
		logProbability$sample39 = new double[10][];
		for(int i = 0; i < 10; i += 1)
			logProbability$sample39[i] = new double[length$observed];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample39) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 10, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var30 = indirection1[i];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, length$observed, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1)
											var30[j] = DistributionSampling.sampleUniform(RNG$2);
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
							double[] var45 = indirection2[k];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int l = forStart$l; l < forEnd$l; l += 1)
											var45[l] = indirection1[l][k];
								}
							);
						}
				}
			);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample63)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$m, int forEnd$m, int threadID$m, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int m = forStart$m; m < forEnd$m; m += 1)
							generated[m] = DistributionSampling.sampleCategorical(RNG$1, indirection2[m]);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample39) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 10, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var30 = indirection1[i];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, length$observed, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1)
											var30[j] = DistributionSampling.sampleUniform(RNG$2);
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
							double[] var45 = indirection2[k];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int l = forStart$l; l < forEnd$l; l += 1)
											var45[l] = indirection1[l][k];
								}
							);
						}
				}
			);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample39) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 10, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var30 = indirection1[i];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, length$observed, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1)
											var30[j] = DistributionSampling.sampleUniform(RNG$2);
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
							double[] var45 = indirection2[k];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int l = forStart$l; l < forEnd$l; l += 1)
											var45[l] = indirection1[l][k];
								}
							);
						}
				}
			);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(!fixedFlag$sample39) {
			// Infer the samples in chronological order.
			if(system$gibbsForward) {
				for(int index$i = 0; index$i < 10; index$i += 1) {
					int i = index$i;
					
					//  Outer loop for dispatching multiple batches of iterations to execute in parallel
					parallelFor(RNG$, 0, length$observed, 1,
						(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
							
								// Inner loop for running batches of iterations, each batch has its own random number
								// generator.
								for(int j = forStart$j; j < forEnd$j; j += 1)
									sample39(i, j, threadID$j, RNG$1);
						}
					);
				}
			}
			// Infer the samples in reverse chronological order.
			else {
				for(int index$i = 9; index$i >= 0; index$i -= 1) {
					int i = index$i;
					
					//  Outer loop for dispatching multiple batches of iterations to execute in parallel
					parallelFor(RNG$, 0, length$observed, 1,
						(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
							
								// Inner loop for running batches of iterations, each batch has its own random number
								// generator.
								for(int j = forStart$j; j < forEnd$j; j += 1)
									sample39(i, j, threadID$j, RNG$1);
						}
					);
				}
			}
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
		logProbability$var33 = 0.0;
		logProbability$indirection2 = 0.0;
		logProbability$indirection1 = 0.0;
		if(!fixedProbFlag$sample39) {
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < length$observed; j += 1)
					logProbability$sample39[i][j] = 0.0;
			}
		}
		logProbability$var55 = 0.0;
		logProbability$generated = 0.0;
		if(!fixedProbFlag$sample63)
			logProbability$var56 = 0.0;
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
		if(fixedFlag$sample39)
			logProbabilityValue$sample39();
		logProbabilityValue$sample63();
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
		logProbabilityValue$sample39();
		logProbabilityValue$sample63();
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
		logProbabilityValue$sample39();
		logProbabilityValue$sample63();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample39) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 10, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var30 = indirection1[i];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, length$observed, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1)
											var30[j] = DistributionSampling.sampleUniform(RNG$2);
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
							double[] var45 = indirection2[k];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int l = forStart$l; l < forEnd$l; l += 1)
											var45[l] = indirection1[l][k];
								}
							);
						}
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
	public final void propogateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = generated.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			generated[cv$index1] = observed[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(setFlag$indirection1)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
							int k = index$k;
							double[] var45 = indirection2[k];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int l = forStart$l; l < forEnd$l; l += 1)
											var45[l] = indirection1[l][k];
								}
							);
						}
				}
			);

	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model ParallelMK5(int[] observed) {\n    int[] generated = new int[observed.length];\n    double[][] indirection1 = new double[10][observed.length];\n    double[][] indirection2 = new double[observed.length][10];\n\n    for(int i=0; i<10; i++) {\n        for(int j=0; j<observed.length; j++) {\n            indirection1[i][j] = uniform(0.0, 1.0).sample();\n        }\n    }\n    \n    for(int k=0; k<observed.length; k++) {\n        for(int l=0; l<10; l++) {\n            indirection2[k][l] = indirection1[l][k];\n        }\n    }\n    \n    for(int m=0; m<observed.length; m++) {\n        generated[m] = categorical(indirection2[m]).sample();\n    }\n\n    generated.observe(observed);\n}";
	}
}