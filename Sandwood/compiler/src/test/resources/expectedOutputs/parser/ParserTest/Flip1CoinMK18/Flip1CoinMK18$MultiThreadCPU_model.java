package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK18$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK18$CoreInterface {
	
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
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;
	private double t;

	public Flip1CoinMK18$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample11 = (fixedFlag$sample11 && fixedProbFlag$sample11);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample103 = (fixedFlag$sample11 && fixedProbFlag$sample103);
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
		fixedProbFlag$sample17 = (fixedFlag$sample17 && fixedProbFlag$sample17);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample103 = (fixedFlag$sample17 && fixedProbFlag$sample103);
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
		// Set flipsMeasured with flag to mark that it has been set so another array doesn't
		// need to be constructed
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
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var96 = 0; var96 < samples; var96 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips[var96];
					{
						{
							double var84 = bias[a][b][c];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var84));
							
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
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$bernoulli = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var97 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample103 = (fixedFlag$sample11 && fixedFlag$sample17);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var97;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample11 using sampled
	// values.
	private final void logProbabilityValue$sample11() {
		// Determine if we need to calculate the values for sample task 11 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample11) {
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
				// The sample value to calculate the probability of generating
				double cv$sampleValue = q;
				{
					{
						double var8 = 1.0;
						double var9 = 1.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var8, var9));
						
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
			logProbability$var10 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$q = cv$sampleProbability;
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample11 = fixedFlag$sample11;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$q;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var10 = cv$rvAccumulator;
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample17 using sampled
	// values.
	private final void logProbabilityValue$sample17() {
		// Determine if we need to calculate the values for sample task 17 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample17) {
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
				// The sample value to calculate the probability of generating
				double cv$sampleValue = t;
				{
					{
						double var14 = 1.0;
						double var15 = 1.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var14, var15));
						
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
			logProbability$var16 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$t = cv$sampleProbability;
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample17 = fixedFlag$sample17;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$t;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var16 = cv$rvAccumulator;
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$bias) {
					// Set the guard so the update is only applied once.
					cv$guard$bias = true;
					
					// Update the variable probability
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 11 drawn from Beta 10. Inference was performed using Metropolis-Hastings.
	private final void sample11() {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		{
			// Metropolis-Hastings
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		
		// The original value of the sample
		double cv$originalValue = q;
		
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
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
				{
					// Write out the new value of the sample.
					q = cv$proposedValue;
					
					// Guards to ensure that bias is only updated when there is a valid path.
					{
						{
							double[][] var21 = bias[0];
							double[] var36 = var21[1];
							var36[0] = (1 - cv$currentValue);
						}
					}
					
					// Guards to ensure that bias is only updated when there is a valid path.
					{
						// Guard to check that at most one copy of the code is executed for a given set of
						// loop iterations.
						boolean guard$sample11put86 = false;
						if(!guard$sample11put86) {
							// The body will execute, so should not be executed again
							guard$sample11put86 = true;
							{
								double[][] var52 = bias[1];
								double[] var54 = var52[0];
								var54[1] = (1 - cv$currentValue);
								double[] var67 = var52[1];
								var67[0] = (1 - cv$currentValue);
								var67[1] = cv$currentValue;
							}
						}
						if(!guard$sample11put86) {
							// The body will execute, so should not be executed again
							guard$sample11put86 = true;
							{
								double[][] var52 = bias[1];
								double[] var54 = var52[0];
								var54[1] = (1 - cv$currentValue);
								double[] var67 = var52[1];
								var67[0] = (1 - cv$currentValue);
								var67[1] = cv$currentValue;
							}
						}
						if(!guard$sample11put86) {
							// The body will execute, so should not be executed again
							guard$sample11put86 = true;
							{
								double[][] var52 = bias[1];
								double[] var54 = var52[0];
								var54[1] = (1 - cv$currentValue);
								double[] var67 = var52[1];
								var67[0] = (1 - cv$currentValue);
								var67[1] = cv$currentValue;
							}
						}
					}
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var8;
				{
					cv$temp$0$var8 = 1.0;
				}
				double cv$temp$1$var9;
				{
					cv$temp$1$var9 = 1.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, cv$temp$0$var8, cv$temp$1$var9));
				
				// Processing random variable 85.
				{
					// Looking for a path between Sample 11 and consumer Bernoulli 85.
					{
						double traceTempVariable$q$5_1 = cv$currentValue;
						double traceTempVariable$var84$5_2 = (1 - traceTempVariable$q$5_1);
						if((0 == a)) {
							if((1 == b)) {
								if((0 == c)) {
									// Processing sample task 103 of consumer random variable bernoulli.
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
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
															double cv$temp$2$var84;
															{
																// Constructing a random variable input for use later.
																double var84 = traceTempVariable$var84$5_2;
																cv$temp$2$var84 = var84;
															}
															
															// Record the probability of sample task 103 generating output with current configuration.
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)));
															}
															
															// Recorded the probability of reaching sample task 103 with the current configuration.
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
						double traceTempVariable$q$6_1 = cv$currentValue;
						double traceTempVariable$var84$6_2 = (1 - traceTempVariable$q$6_1);
						if((1 == a)) {
							if((0 == b)) {
								if((1 == c)) {
									// Processing sample task 103 of consumer random variable bernoulli.
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
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
															double cv$temp$3$var84;
															{
																// Constructing a random variable input for use later.
																double var84 = traceTempVariable$var84$6_2;
																cv$temp$3$var84 = var84;
															}
															
															// Record the probability of sample task 103 generating output with current configuration.
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)));
															}
															
															// Recorded the probability of reaching sample task 103 with the current configuration.
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
						double traceTempVariable$q$7_1 = cv$currentValue;
						double traceTempVariable$var84$7_2 = (1 - traceTempVariable$q$7_1);
						if((1 == a)) {
							if((1 == b)) {
								if((0 == c)) {
									// Processing sample task 103 of consumer random variable bernoulli.
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
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
															double cv$temp$4$var84;
															{
																// Constructing a random variable input for use later.
																double var84 = traceTempVariable$var84$7_2;
																cv$temp$4$var84 = var84;
															}
															
															// Record the probability of sample task 103 generating output with current configuration.
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)));
															}
															
															// Recorded the probability of reaching sample task 103 with the current configuration.
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
						double traceTempVariable$var84$8_1 = cv$currentValue;
						if((1 == a)) {
							if((1 == b)) {
								if((1 == c)) {
									// Processing sample task 103 of consumer random variable bernoulli.
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
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
															double cv$temp$5$var84;
															{
																// Constructing a random variable input for use later.
																double var84 = traceTempVariable$var84$8_1;
																cv$temp$5$var84 = var84;
															}
															
															// Record the probability of sample task 103 generating output with current configuration.
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)));
															}
															
															// Recorded the probability of reaching sample task 103 with the current configuration.
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
		}
		
		// The probability ration for the proposed value and the current value.
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the new value of the sample.
			q = cv$originalValue;
			
			// Guards to ensure that bias is only updated when there is a valid path.
			{
				{
					double[][] var21 = bias[0];
					double[] var36 = var21[1];
					var36[0] = (1 - q);
				}
			}
			
			// Guards to ensure that bias is only updated when there is a valid path.
			{
				// Guard to check that at most one copy of the code is executed for a given set of
				// loop iterations.
				boolean guard$sample11put86 = false;
				if(!guard$sample11put86) {
					// The body will execute, so should not be executed again
					guard$sample11put86 = true;
					{
						double[][] var52 = bias[1];
						double[] var54 = var52[0];
						var54[1] = (1 - q);
						double[] var67 = var52[1];
						var67[0] = (1 - q);
						var67[1] = q;
					}
				}
				if(!guard$sample11put86) {
					// The body will execute, so should not be executed again
					guard$sample11put86 = true;
					{
						double[][] var52 = bias[1];
						double[] var54 = var52[0];
						var54[1] = (1 - q);
						double[] var67 = var52[1];
						var67[0] = (1 - q);
						var67[1] = q;
					}
				}
				if(!guard$sample11put86) {
					// The body will execute, so should not be executed again
					guard$sample11put86 = true;
					{
						double[][] var52 = bias[1];
						double[] var54 = var52[0];
						var54[1] = (1 - q);
						double[] var67 = var52[1];
						var67[0] = (1 - q);
						var67[1] = q;
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 17 drawn from Beta 16. Inference was performed using Metropolis-Hastings.
	private final void sample17() {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		{
			// Metropolis-Hastings
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		
		// The original value of the sample
		double cv$originalValue = t;
		
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
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
				{
					// Write out the new value of the sample.
					t = cv$proposedValue;
					
					// Guards to ensure that bias is only updated when there is a valid path.
					{
						// Guard to check that at most one copy of the code is executed for a given set of
						// loop iterations.
						boolean guard$sample17put52 = false;
						if(!guard$sample17put52) {
							// The body will execute, so should not be executed again
							guard$sample17put52 = true;
							{
								double[][] var21 = bias[0];
								double[] var23 = var21[0];
								var23[0] = cv$currentValue;
								var23[1] = (1 - cv$currentValue);
								double[] var36 = var21[1];
								var36[1] = cv$currentValue;
							}
						}
						if(!guard$sample17put52) {
							// The body will execute, so should not be executed again
							guard$sample17put52 = true;
							{
								double[][] var21 = bias[0];
								double[] var23 = var21[0];
								var23[0] = cv$currentValue;
								var23[1] = (1 - cv$currentValue);
								double[] var36 = var21[1];
								var36[1] = cv$currentValue;
							}
						}
						if(!guard$sample17put52) {
							// The body will execute, so should not be executed again
							guard$sample17put52 = true;
							{
								double[][] var21 = bias[0];
								double[] var23 = var21[0];
								var23[0] = cv$currentValue;
								var23[1] = (1 - cv$currentValue);
								double[] var36 = var21[1];
								var36[1] = cv$currentValue;
							}
						}
					}
					
					// Guards to ensure that bias is only updated when there is a valid path.
					{
						{
							double[][] var52 = bias[1];
							double[] var54 = var52[0];
							var54[0] = cv$currentValue;
						}
					}
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var14;
				{
					cv$temp$0$var14 = 1.0;
				}
				double cv$temp$1$var15;
				{
					cv$temp$1$var15 = 1.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, cv$temp$0$var14, cv$temp$1$var15));
				
				// Processing random variable 85.
				{
					// Looking for a path between Sample 17 and consumer Bernoulli 85.
					{
						double traceTempVariable$var84$5_1 = cv$currentValue;
						if((0 == a)) {
							if((0 == b)) {
								if((0 == c)) {
									// Processing sample task 103 of consumer random variable bernoulli.
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
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
															double cv$temp$2$var84;
															{
																// Constructing a random variable input for use later.
																double var84 = traceTempVariable$var84$5_1;
																cv$temp$2$var84 = var84;
															}
															
															// Record the probability of sample task 103 generating output with current configuration.
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)));
															}
															
															// Recorded the probability of reaching sample task 103 with the current configuration.
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
						double traceTempVariable$t$6_1 = cv$currentValue;
						double traceTempVariable$var84$6_2 = (1 - traceTempVariable$t$6_1);
						if((0 == a)) {
							if((0 == b)) {
								if((1 == c)) {
									// Processing sample task 103 of consumer random variable bernoulli.
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
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
															double cv$temp$3$var84;
															{
																// Constructing a random variable input for use later.
																double var84 = traceTempVariable$var84$6_2;
																cv$temp$3$var84 = var84;
															}
															
															// Record the probability of sample task 103 generating output with current configuration.
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)));
															}
															
															// Recorded the probability of reaching sample task 103 with the current configuration.
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
						double traceTempVariable$var84$7_1 = cv$currentValue;
						if((0 == a)) {
							if((1 == b)) {
								if((1 == c)) {
									// Processing sample task 103 of consumer random variable bernoulli.
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
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
															double cv$temp$4$var84;
															{
																// Constructing a random variable input for use later.
																double var84 = traceTempVariable$var84$7_1;
																cv$temp$4$var84 = var84;
															}
															
															// Record the probability of sample task 103 generating output with current configuration.
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)));
															}
															
															// Recorded the probability of reaching sample task 103 with the current configuration.
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
						double traceTempVariable$var84$8_1 = cv$currentValue;
						if((1 == a)) {
							if((0 == b)) {
								if((0 == c)) {
									// Processing sample task 103 of consumer random variable bernoulli.
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
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
															double cv$temp$5$var84;
															{
																// Constructing a random variable input for use later.
																double var84 = traceTempVariable$var84$8_1;
																cv$temp$5$var84 = var84;
															}
															
															// Record the probability of sample task 103 generating output with current configuration.
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)));
															}
															
															// Recorded the probability of reaching sample task 103 with the current configuration.
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
		}
		
		// The probability ration for the proposed value and the current value.
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the new value of the sample.
			t = cv$originalValue;
			
			// Guards to ensure that bias is only updated when there is a valid path.
			{
				// Guard to check that at most one copy of the code is executed for a given set of
				// loop iterations.
				boolean guard$sample17put52 = false;
				if(!guard$sample17put52) {
					// The body will execute, so should not be executed again
					guard$sample17put52 = true;
					{
						double[][] var21 = bias[0];
						double[] var23 = var21[0];
						var23[0] = t;
						var23[1] = (1 - t);
						double[] var36 = var21[1];
						var36[1] = t;
					}
				}
				if(!guard$sample17put52) {
					// The body will execute, so should not be executed again
					guard$sample17put52 = true;
					{
						double[][] var21 = bias[0];
						double[] var23 = var21[0];
						var23[0] = t;
						var23[1] = (1 - t);
						double[] var36 = var21[1];
						var36[1] = t;
					}
				}
				if(!guard$sample17put52) {
					// The body will execute, so should not be executed again
					guard$sample17put52 = true;
					{
						double[][] var21 = bias[0];
						double[] var23 = var21[0];
						var23[0] = t;
						var23[1] = (1 - t);
						double[] var36 = var21[1];
						var36[1] = t;
					}
				}
			}
			
			// Guards to ensure that bias is only updated when there is a valid path.
			{
				{
					double[][] var52 = bias[1];
					double[] var54 = var52[0];
					var54[0] = t;
				}
			}
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
		{
			bias = new double[2][][];
			double[][] subarray$0 = new double[2][];
			bias[0] = subarray$0;
			subarray$0[0] = new double[2];
			subarray$0[1] = new double[2];
			double[][] subarray$1 = new double[2][];
			bias[1] = subarray$1;
			subarray$1[0] = new double[2];
			subarray$1[1] = new double[2];
		}
		
		// Constructor for flips
		{
			flips = new boolean[samples];
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample11)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample17)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		double[][] var21 = bias[0];
		double[] var23 = var21[0];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var23[0] = t;
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var23[1] = (1 - t);
		double[] var36 = var21[1];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var36[0] = (1 - q);
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var36[1] = t;
		double[][] var52 = bias[1];
		double[] var54 = var52[0];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var54[0] = t;
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var54[1] = (1 - q);
		double[] var67 = var52[1];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var67[0] = (1 - q);
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var67[1] = q;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1)
						flips[var96] = DistributionSampling.sampleBernoulli(RNG$1, bias[a][b][c]);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample11)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample17)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		double[][] var21 = bias[0];
		double[] var23 = var21[0];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var23[0] = t;
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var23[1] = (1 - t);
		double[] var36 = var21[1];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var36[0] = (1 - q);
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var36[1] = t;
		double[][] var52 = bias[1];
		double[] var54 = var52[0];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var54[0] = t;
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var54[1] = (1 - q);
		double[] var67 = var52[1];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var67[0] = (1 - q);
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var67[1] = q;
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample11)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample17)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		double[][] var21 = bias[0];
		double[] var23 = var21[0];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var23[0] = t;
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var23[1] = (1 - t);
		double[] var36 = var21[1];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var36[0] = (1 - q);
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var36[1] = t;
		double[][] var52 = bias[1];
		double[] var54 = var52[0];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var54[0] = t;
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var54[1] = (1 - q);
		double[] var67 = var52[1];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var67[0] = (1 - q);
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var67[1] = q;
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
			logProbability$q = 0.0;
		logProbability$var16 = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$t = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample103)
			logProbability$var97 = 0.0;
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
		if(!fixedFlag$sample17)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		double[][] var21 = bias[0];
		double[] var23 = var21[0];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var23[0] = t;
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var23[1] = (1 - t);
		double[] var36 = var21[1];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var36[0] = (1 - q);
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var36[1] = t;
		double[][] var52 = bias[1];
		double[] var54 = var52[0];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var54[0] = t;
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var54[1] = (1 - q);
		double[] var67 = var52[1];
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var67[0] = (1 - q);
		if(!(fixedFlag$sample11 && fixedFlag$sample17))
			var67[1] = q;
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Deep copy between arrays
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		if(true) {
			double[][] var21 = bias[0];
			double[] var23 = var21[0];
			var23[0] = t;
			var23[1] = (1 - t);
			double[] var36 = var21[1];
			var36[0] = (1 - q);
			var36[1] = t;
		}
		if(true) {
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