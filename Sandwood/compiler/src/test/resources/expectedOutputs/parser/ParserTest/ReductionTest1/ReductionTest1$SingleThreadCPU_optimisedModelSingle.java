package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ReductionTest1$SingleThreadCPU extends CoreModelSingleThreadCPU implements ReductionTest1$CoreInterface {

	// Declare the variables for the model.
	int[][] ObsArr;
	int T;
	double[][] TimeFeat;
	int[][] arr;
	boolean[][] constrainedFlag$sample101;
	boolean fixedFlag$sample101 = false;
	boolean fixedProbFlag$sample101 = false;
	boolean fixedProbFlag$sample165 = false;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$arr;
	double[][] logProbability$sample101;
	double logProbability$sum_t;
	double logProbability$time_coeff;
	double logProbability$time_impact;
	double logProbability$var158;
	int n_ac;
	double[][] sum_t;
	boolean system$gibbsForward = true;
	double[][] time_coeff;
	int time_dim;
	double[][][] time_impact;

	public ReductionTest1$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for ObsArr.
	@Override
	public final int[][] get$ObsArr() {
		return ObsArr;
	}

	// Setter for ObsArr.
	@Override
	public final void set$ObsArr(int[][] cv$value, boolean allocated$) {
		ObsArr = cv$value;
	}

	// Getter for T.
	@Override
	public final int get$T() {
		return T;
	}

	// Setter for T.
	@Override
	public final void set$T(int cv$value, boolean allocated$) {
		T = cv$value;
	}

	// Getter for TimeFeat.
	@Override
	public final double[][] get$TimeFeat() {
		return TimeFeat;
	}

	// Setter for TimeFeat.
	@Override
	public final void set$TimeFeat(double[][] cv$value, boolean allocated$) {
		TimeFeat = cv$value;
	}

	// Getter for arr.
	@Override
	public final int[][] get$arr() {
		return arr;
	}

	// Getter for fixedFlag$sample101.
	@Override
	public final boolean get$fixedFlag$sample101() {
		return fixedFlag$sample101;
	}

	// Setter for fixedFlag$sample101.
	@Override
	public final void set$fixedFlag$sample101(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample101 including if probabilities
		// need to be updated.
		fixedFlag$sample101 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1) {
				boolean[] cv$constrainedFlag$sample101$1 = constrainedFlag$sample101[index$constrainedFlag$sample101$1];
				for(int index$constrainedFlag$sample101$2 = 0; index$constrainedFlag$sample101$2 < cv$constrainedFlag$sample101$1.length; index$constrainedFlag$sample101$2 += 1)
					cv$constrainedFlag$sample101$1[index$constrainedFlag$sample101$2] = true;
			}
		}
		
		// Should the probability of sample 101 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample101" with its value "cv$value".
		fixedProbFlag$sample101 = (cv$value && fixedProbFlag$sample101);
		
		// Should the probability of sample 165 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample101" with its value "cv$value".
		fixedProbFlag$sample165 = (cv$value && fixedProbFlag$sample165);
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

	// Getter for logProbability$arr.
	@Override
	public final double get$logProbability$arr() {
		return logProbability$arr;
	}

	// Getter for logProbability$sum_t.
	@Override
	public final double get$logProbability$sum_t() {
		return logProbability$sum_t;
	}

	// Getter for logProbability$time_coeff.
	@Override
	public final double get$logProbability$time_coeff() {
		return logProbability$time_coeff;
	}

	// Getter for logProbability$time_impact.
	@Override
	public final double get$logProbability$time_impact() {
		return logProbability$time_impact;
	}

	// Getter for n_ac.
	@Override
	public final int get$n_ac() {
		return n_ac;
	}

	// Setter for n_ac.
	@Override
	public final void set$n_ac(int cv$value, boolean allocated$) {
		n_ac = cv$value;
	}

	// Getter for sum_t.
	@Override
	public final double[][] get$sum_t() {
		return sum_t;
	}

	// Getter for time_coeff.
	@Override
	public final double[][] get$time_coeff() {
		return time_coeff;
	}

	// Setter for time_coeff.
	@Override
	public final void set$time_coeff(double[][] cv$value, boolean allocated$) {
		// Set flags for all the side effects of time_coeff including if probabilities need
		// to be updated.
		time_coeff = cv$value;
		
		// Unset the fixed probability flag for sample 101 as it depends on time_coeff.
		fixedProbFlag$sample101 = false;
		
		// Unset the fixed probability flag for sample 165 as it depends on time_coeff.
		fixedProbFlag$sample165 = false;
	}

	// Getter for time_dim.
	@Override
	public final int get$time_dim() {
		return time_dim;
	}

	// Getter for time_impact.
	@Override
	public final double[][][] get$time_impact() {
		return time_impact;
	}

	// Pick a value from the distribution for the unconditioned variable from sample101
	private final void drawValueSample101(int i$var80, int var95) {
		time_coeff[i$var80][var95] = DistributionSampling.sampleGaussian(RNG$);
		
		// Guards to ensure that time_impact is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 101 and consumer double[][][] 138.
		for(int t = 1; t < T; t += 1)
									// Substituted "i$var119" with its value "i$var80".
			// 
			// Substituted "i$var119" with its value "i$var80".
			time_impact[t][i$var80][var95] = (TimeFeat[t][var95] * time_coeff[i$var80][var95]);
		for(int t = 1; t < T; t += 1) {
			// Reduction of array null
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$var151$3 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
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
				reduceVar$var151$3 = (reduceVar$var151$3 + time_impact[t][i$var80][cv$reduction152Index]);
			
									// Substituted "index$t$2_4" with its value "t".
			sum_t[t][i$var80] = reduceVar$var151$3;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 101 drawn from Gaussian 85. Inference was performed using Metropolis-Hastings.
	private final void inferSample101(int i$var80, int var95) {
		constrainedFlag$sample101[i$var80][var95] = false;
		
		// The original value of the sample
		double cv$originalValue = time_coeff[i$var80][var95];
		
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
			for(int t = 1; t < T; t += 1) {
				// Reduction of array null
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$var151$1 = 0.0;
				
				// Reduce for every value except a masked value which will be skipped.
				// 
				// Substituted "j" with its value "var95".
				for(int cv$reduction332Index = 0; cv$reduction332Index < var95; cv$reduction332Index += 1)
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
					reduceVar$var151$1 = (reduceVar$var151$1 + time_impact[t][i$var80][cv$reduction332Index]);
				
				// Substituted "j" with its value "var95".
				for(int cv$reduction332Index = (var95 + 1); cv$reduction332Index < time_dim; cv$reduction332Index += 1)
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
					reduceVar$var151$1 = (reduceVar$var151$1 + time_impact[t][i$var80][cv$reduction332Index]);
				
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// Substituted "j" with its value "var95".
				// 
				// Set the current value to the current state of the tree.
				reduceVar$var151$1 = ((TimeFeat[t][var95] * cv$originalValue) + reduceVar$var151$1);
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample101[i$var80][var95] = true;
				
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
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityPoisson(arr[t][i$var80], reduceVar$var151$1) + cv$accumulatedProbabilities);
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
		if(constrainedFlag$sample101[i$var80][var95]) {
			time_coeff[i$var80][var95] = cv$proposedValue;
			
			// Guards to ensure that time_impact is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 101 and consumer double[][][] 138.
			for(int t = 1; t < T; t += 1)
												// Substituted "i$var119" with its value "i$var80".
				// 
				// Substituted "i$var119" with its value "i$var80".
				time_impact[t][i$var80][var95] = (TimeFeat[t][var95] * time_coeff[i$var80][var95]);
			for(int t = 1; t < T; t += 1) {
				// Reduction of array null
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$var151$0 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
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
					reduceVar$var151$0 = (reduceVar$var151$0 + time_impact[t][i$var80][cv$reduction152Index]);
				
												// Substituted "index$t$3_4" with its value "t".
				sum_t[t][i$var80] = reduceVar$var151$0;
			}
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$proposedValue);
			for(int t = 1; t < T; t += 1) {
				// Reduction of array null
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$var151$1 = 0.0;
				
				// Reduce for every value except a masked value which will be skipped.
				// 
				// Substituted "j" with its value "var95".
				for(int cv$reduction332Index = 0; cv$reduction332Index < var95; cv$reduction332Index += 1)
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
					reduceVar$var151$1 = (reduceVar$var151$1 + time_impact[t][i$var80][cv$reduction332Index]);
				
				// Substituted "j" with its value "var95".
				for(int cv$reduction332Index = (var95 + 1); cv$reduction332Index < time_dim; cv$reduction332Index += 1)
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
					reduceVar$var151$1 = (reduceVar$var151$1 + time_impact[t][i$var80][cv$reduction332Index]);
				
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// Substituted "j" with its value "var95".
				reduceVar$var151$1 = ((TimeFeat[t][var95] * cv$proposedValue) + reduceVar$var151$1);
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample101[i$var80][var95] = true;
				
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
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityPoisson(arr[t][i$var80], reduceVar$var151$1) + cv$accumulatedProbabilities);
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
				time_coeff[i$var80][var95] = cv$originalValue;
				
				// Guards to ensure that time_impact is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 101 and consumer double[][][] 138.
				for(int t = 1; t < T; t += 1)
															// Substituted "i$var119" with its value "i$var80".
					// 
					// Substituted "i$var119" with its value "i$var80".
					time_impact[t][i$var80][var95] = (TimeFeat[t][var95] * time_coeff[i$var80][var95]);
				for(int t = 1; t < T; t += 1) {
					// Reduction of array null
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$var151$2 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
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
						reduceVar$var151$2 = (reduceVar$var151$2 + time_impact[t][i$var80][cv$reduction152Index]);
					
															// Substituted "index$t$9_4" with its value "t".
					sum_t[t][i$var80] = reduceVar$var151$2;
				}
			}
		}
	}

	// Calculate the probability of the samples represented by sample101 using sampled
	// values.
	private final void logProbabilityValue$sample101() {
		// Determine if we need to calculate the values for sample task 101 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample101) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
				for(int var95 = 0; var95 < time_dim; var95 += 1) {
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
					double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(time_coeff[i$var80][var95]);
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					logProbability$sample101[i$var80][var95] = cv$distributionAccumulator;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 < T)) {
						// Update the variable probability
						logProbability$time_impact = (logProbability$time_impact + cv$distributionAccumulator);
						
						// Update the variable probability
						logProbability$sum_t = (logProbability$sum_t + cv$distributionAccumulator);
					}
				}
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$time_coeff = (logProbability$time_coeff + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample101)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample101 = fixedFlag$sample101;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
				for(int var95 = 0; var95 < time_dim; var95 += 1) {
					double cv$sampleValue = logProbability$sample101[i$var80][var95];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 < T)) {
						// Update the variable probability
						logProbability$time_impact = (logProbability$time_impact + cv$sampleValue);
						
						// Update the variable probability
						logProbability$sum_t = (logProbability$sum_t + cv$sampleValue);
					}
				}
			}
			
			// Update the variable probability
			logProbability$time_coeff = (logProbability$time_coeff + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample101)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample165 using sampled
	// values.
	private final void logProbabilityValue$sample165() {
		// Determine if we need to calculate the values for sample task 165 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample165) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int t = 1; t < T; t += 1) {
				for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
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
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityPoisson(arr[t][i$var119], sum_t[t][i$var119]));
				}
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
				logProbability$var158 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$arr = (logProbability$arr + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample165 = fixedFlag$sample101;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$arr = (logProbability$arr + logProbability$var158);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var158);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var158);
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocate() {
		// If time_coeff has not been set already allocate space.
		if(!fixedFlag$sample101) {
			// Constructor for time_coeff
			time_coeff = new double[n_ac][];
			for(int var18 = 0; var18 < n_ac; var18 += 1)
				time_coeff[var18] = new double[TimeFeat[0].length];
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1)
				time_coeff[i$var80] = new double[TimeFeat[0].length];
		}
		
		// Constructor for sum_t
		sum_t = new double[T][];
		for(int var31 = 0; var31 < T; var31 += 1)
			sum_t[var31] = new double[n_ac];
		
		// Constructor for time_impact
		time_impact = new double[T][][];
		for(int var44 = 0; var44 < T; var44 += 1) {
			double[][] subarray$0 = new double[n_ac][];
			time_impact[var44] = subarray$0;
			for(int var54 = 0; var54 < n_ac; var54 += 1)
				subarray$0[var54] = new double[TimeFeat[0].length];
		}
		
		// Constructor for arr
		arr = new int[T][];
		for(int var68 = 0; var68 < T; var68 += 1)
			arr[var68] = new int[n_ac];
		
		// Constructor for constrainedFlag$sample101
		constrainedFlag$sample101 = new boolean[n_ac][];
		for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1)
			constrainedFlag$sample101[i$var80] = new boolean[TimeFeat[0].length];
		
		// Constructor for logProbability$sample101
		logProbability$sample101 = new double[n_ac][];
		for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1)
			logProbability$sample101[i$var80] = new double[TimeFeat[0].length];
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
		if(!fixedFlag$sample101) {
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
				double[] var86 = time_coeff[i$var80];
				for(int var95 = 0; var95 < time_dim; var95 += 1)
					var86[var95] = DistributionSampling.sampleGaussian(RNG$);
			}
		}
		for(int t = 1; t < T; t += 1) {
			double[][] var129 = time_impact[t];
			double[] var139 = sum_t[t];
			int[] var154 = arr[t];
			for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample101) {
					for(int j = 0; j < time_dim; j += 1)
						var129[i$var119][j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
					
					// Reduction of array null
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$var151$4 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
																								// y's comment
						// Set the right hand term to a value from the array var141
						reduceVar$var151$4 = (reduceVar$var151$4 + time_impact[t][i$var119][cv$reduction152Index]);
					var139[i$var119] = reduceVar$var151$4;
				}
				var154[i$var119] = DistributionSampling.samplePoisson(RNG$, sum_t[t][i$var119]);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample101) {
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
				double[] var86 = time_coeff[i$var80];
				for(int var95 = 0; var95 < time_dim; var95 += 1)
					var86[var95] = DistributionSampling.sampleGaussian(RNG$);
			}
		}
		for(int t = 1; t < T; t += 1) {
			double[][] var129 = time_impact[t];
			double[] var139 = sum_t[t];
			for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
				for(int j = 0; j < time_dim; j += 1)
					var129[i$var119][j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
				
				// Reduction of array null
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$var151$8 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
																				// y's comment
					// Set the right hand term to a value from the array var141
					reduceVar$var151$8 = (reduceVar$var151$8 + time_impact[t][i$var119][cv$reduction152Index]);
				var139[i$var119] = reduceVar$var151$8;
			}
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample101) {
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
				double[] var86 = time_coeff[i$var80];
				for(int var95 = 0; var95 < time_dim; var95 += 1)
					var86[var95] = DistributionSampling.sampleGaussian(RNG$);
			}
		}
		for(int t = 1; t < T; t += 1) {
			double[][] var129 = time_impact[t];
			double[] var139 = sum_t[t];
			int[] var154 = arr[t];
			for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
				for(int j = 0; j < time_dim; j += 1)
					var129[i$var119][j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
				
				// Reduction of array null
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$var151$5 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
																				// y's comment
					// Set the right hand term to a value from the array var141
					reduceVar$var151$5 = (reduceVar$var151$5 + time_impact[t][i$var119][cv$reduction152Index]);
				var139[i$var119] = reduceVar$var151$5;
				var154[i$var119] = DistributionSampling.samplePoisson(RNG$, sum_t[t][i$var119]);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample101) {
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
				double[] var86 = time_coeff[i$var80];
				for(int var95 = 0; var95 < time_dim; var95 += 1)
					var86[var95] = DistributionSampling.sampleGaussian(RNG$);
			}
			for(int t = 1; t < T; t += 1) {
				double[][] var129 = time_impact[t];
				double[] var139 = sum_t[t];
				for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
					for(int j = 0; j < time_dim; j += 1)
						var129[i$var119][j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
					
					// Reduction of array null
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$var151$6 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
																								// y's comment
						// Set the right hand term to a value from the array var141
						reduceVar$var151$6 = (reduceVar$var151$6 + time_impact[t][i$var119][cv$reduction152Index]);
					var139[i$var119] = reduceVar$var151$6;
				}
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample101) {
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
				double[] var86 = time_coeff[i$var80];
				for(int var95 = 0; var95 < time_dim; var95 += 1)
					var86[var95] = DistributionSampling.sampleGaussian(RNG$);
			}
		}
		for(int t = 1; t < T; t += 1) {
			double[][] var129 = time_impact[t];
			double[] var139 = sum_t[t];
			for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
				for(int j = 0; j < time_dim; j += 1)
					var129[i$var119][j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
				
				// Reduction of array null
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$var151$7 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
																				// y's comment
					// Set the right hand term to a value from the array var141
					reduceVar$var151$7 = (reduceVar$var151$7 + time_impact[t][i$var119][cv$reduction152Index]);
				var139[i$var119] = reduceVar$var151$7;
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(!fixedFlag$sample101) {
			// Infer the samples in chronological order.
			if(system$gibbsForward) {
				for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
					for(int var95 = 0; var95 < time_dim; var95 += 1)
						inferSample101(i$var80, var95);
				}
			}
			// Infer the samples in reverse chronological order.
			else {
				for(int i$var80 = (n_ac - 1); i$var80 >= 0; i$var80 -= 1) {
					for(int var95 = (time_dim - 1); var95 >= 0; var95 -= 1)
						inferSample101(i$var80, var95);
				}
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
		for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
			for(int var95 = 0; var95 < time_dim; var95 += 1) {
				if(!constrainedFlag$sample101[i$var80][var95])
					drawValueSample101(i$var80, var95);
			}
		}
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
		logProbability$time_coeff = 0.0;
		logProbability$time_impact = 0.0;
		logProbability$sum_t = 0.0;
		if(!fixedProbFlag$sample101) {
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
				for(int var95 = 0; var95 < time_dim; var95 += 1)
					logProbability$sample101[i$var80][var95] = Double.NaN;
			}
		}
		logProbability$arr = 0.0;
		if(!fixedProbFlag$sample165)
			logProbability$var158 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		time_dim = TimeFeat[0].length;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1) {
			boolean[] cv$constrainedFlag$sample101$1 = constrainedFlag$sample101[index$constrainedFlag$sample101$1];
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
		if(fixedFlag$sample101)
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
		int cv$length1 = arr.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = ObsArr[cv$index1];
			int[] cv$target2 = arr[cv$index1];
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
		for(int t = 1; t < T; t += 1) {
			double[][] var129 = time_impact[t];
			double[] var139 = sum_t[t];
			for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
				for(int j = 0; j < time_dim; j += 1)
					var129[i$var119][j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
				
				// Reduction of array null
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$var151$9 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
																				// y's comment
					// Set the right hand term to a value from the array var141
					reduceVar$var151$9 = (reduceVar$var151$9 + time_impact[t][i$var119][cv$reduction152Index]);
				var139[i$var119] = reduceVar$var151$9;
			}
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