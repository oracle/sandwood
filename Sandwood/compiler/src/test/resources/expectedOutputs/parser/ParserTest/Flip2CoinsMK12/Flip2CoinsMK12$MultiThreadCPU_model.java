package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK12$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip2CoinsMK12$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample37 = false;
	private boolean fixedFlag$sample54 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample37 = false;
	private boolean fixedProbFlag$sample54 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private boolean[][] intermediateFlips;
	private int[] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli1;
	private double[] logProbability$bernoulli2;
	private double logProbability$beta;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample37;
	private double[] logProbability$sample54;
	private double logProbability$var14;
	private double logProbability$var19;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK12$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for bias.
	@Override
	public final double[] get$bias() {
		return bias;
	}

	// Setter for bias.
	@Override
	public final void set$bias(double[] cv$value) {
		// Set flags for all the side effects of bias including if probabilities need to be
		// updated.
		// Set bias with flag to mark that it has been set so another array doesn't need to
		// be constructed
		bias = cv$value;
		setFlag$bias = true;
		
		// Unset the fixed probability flag for sample 16 as it depends on bias.
		fixedProbFlag$sample16 = false;
		
		// Unset the fixed probability flag for sample 21 as it depends on bias.
		fixedProbFlag$sample21 = false;
		
		// Unset the fixed probability flag for sample 37 as it depends on bias.
		fixedProbFlag$sample37 = false;
		
		// Unset the fixed probability flag for sample 54 as it depends on bias.
		fixedProbFlag$sample54 = false;
	}

	// Getter for coins.
	@Override
	public final int get$coins() {
		return coins;
	}

	// Getter for fixedFlag$sample16.
	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	// Setter for fixedFlag$sample16.
	@Override
	public final void set$fixedFlag$sample16(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample16 including if probabilities
		// need to be updated.
		fixedFlag$sample16 = cv$value;
		
		// Should the probability of sample 16 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample16 = (fixedFlag$sample16 && fixedProbFlag$sample16);
		
		// Should the probability of sample 37 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample37 = (fixedFlag$sample16 && fixedProbFlag$sample37);
		
		// Should the probability of sample 54 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample54 = (fixedFlag$sample16 && fixedProbFlag$sample54);
	}

	// Getter for fixedFlag$sample21.
	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	// Setter for fixedFlag$sample21.
	@Override
	public final void set$fixedFlag$sample21(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample21 including if probabilities
		// need to be updated.
		fixedFlag$sample21 = cv$value;
		
		// Should the probability of sample 21 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample21 = (fixedFlag$sample21 && fixedProbFlag$sample21);
		
		// Should the probability of sample 37 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample37 = (fixedFlag$sample21 && fixedProbFlag$sample37);
		
		// Should the probability of sample 54 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample54 = (fixedFlag$sample21 && fixedProbFlag$sample54);
	}

	// Getter for fixedFlag$sample37.
	@Override
	public final boolean get$fixedFlag$sample37() {
		return fixedFlag$sample37;
	}

	// Setter for fixedFlag$sample37.
	@Override
	public final void set$fixedFlag$sample37(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample37 including if probabilities
		// need to be updated.
		fixedFlag$sample37 = cv$value;
		
		// Should the probability of sample 37 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample37 = (fixedFlag$sample37 && fixedProbFlag$sample37);
	}

	// Getter for fixedFlag$sample54.
	@Override
	public final boolean get$fixedFlag$sample54() {
		return fixedFlag$sample54;
	}

	// Setter for fixedFlag$sample54.
	@Override
	public final void set$fixedFlag$sample54(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample54 including if probabilities
		// need to be updated.
		fixedFlag$sample54 = cv$value;
		
		// Should the probability of sample 54 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample54 = (fixedFlag$sample54 && fixedProbFlag$sample54);
	}

	// Getter for flips.
	@Override
	public final boolean[][] get$flips() {
		return flips;
	}

	// Setter for flips.
	@Override
	public final void set$flips(boolean[][] cv$value) {
		// Set flags for all the side effects of flips including if probabilities need to
		// be updated.
		// Set flips with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips = cv$value;
		setFlag$flips = true;
		
		// Unset the fixed probability flag for sample 37 as it depends on flips.
		fixedProbFlag$sample37 = false;
		
		// Unset the fixed probability flag for sample 54 as it depends on flips.
		fixedProbFlag$sample54 = false;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[][] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[][] cv$value) {
		// Set flipsMeasured with flag to mark that it has been set so another array doesn't
		// need to be constructed
		flipsMeasured = cv$value;
	}

	// Getter for intermediateFlips.
	@Override
	public final boolean[][] get$intermediateFlips() {
		return intermediateFlips;
	}

	// Getter for length$flipsMeasured.
	@Override
	public final int[] get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	// Setter for length$flipsMeasured.
	@Override
	public final void set$length$flipsMeasured(int[] cv$value) {
		// Set length$flipsMeasured with flag to mark that it has been set so another array
		// doesn't need to be constructed
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

	// Getter for logProbability$bernoulli1.
	@Override
	public final double[] get$logProbability$bernoulli1() {
		return logProbability$bernoulli1;
	}

	// Getter for logProbability$bernoulli2.
	@Override
	public final double[] get$logProbability$bernoulli2() {
		return logProbability$bernoulli2;
	}

	// Getter for logProbability$beta.
	@Override
	public final double get$logProbability$beta() {
		return logProbability$beta;
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

	// Calculate the probability of the samples represented by sample16 using sampled
	// values.
	private final void logProbabilityValue$sample16() {
		// Determine if we need to calculate the values for sample task 16 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample16) {
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
				double cv$sampleValue = bias[0];
				{
					{
						double var10 = 1.0;
						double var11 = 1.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var10, var11));
						
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
			logProbability$beta = (logProbability$beta + cv$sampleAccumulator);
			
			// Store the sample task probability
			logProbability$var14 = cv$sampleProbability;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample16 = fixedFlag$sample16;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var14;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample21 using sampled
	// values.
	private final void logProbabilityValue$sample21() {
		// Determine if we need to calculate the values for sample task 21 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample21) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var18 = 1; i$var18 < coins; i$var18 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = bias[i$var18];
					{
						{
							double var10 = 1.0;
							double var11 = 1.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var10, var11));
							
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
			logProbability$beta = (logProbability$beta + cv$sampleAccumulator);
			
			// Store the random variable instance probability
			logProbability$var19 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample21 = fixedFlag$sample21;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var19;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample37 using sampled
	// values.
	private final void logProbabilityValue$sample37() {
		// Determine if we need to calculate the values for sample task 37 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample37) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < 1; j += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int var33 = 0; var33 < length$flipsMeasured[j]; var33 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = flips[j][var33];
						{
							{
								double var28 = bias[j];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var28));
								
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
				logProbability$bernoulli1[((j - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				logProbability$sample37[((j - 0) / 1)] = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample37 = ((fixedFlag$sample37 && fixedFlag$sample16) && fixedFlag$sample21);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < 1; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample37[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli1[((j - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample54 using sampled
	// values.
	private final void logProbabilityValue$sample54() {
		// Determine if we need to calculate the values for sample task 54 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample54) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int k = 1; k < coins; k += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int var48 = 0; var48 < length$flipsMeasured[k]; var48 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = flips[k][var48];
						{
							{
								double var43 = bias[k];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var43));
								
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
				logProbability$bernoulli2[((k - 1) / 1)] = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				logProbability$sample54[((k - 1) / 1)] = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample54 = ((fixedFlag$sample54 && fixedFlag$sample16) && fixedFlag$sample21);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int k = 1; k < coins; k += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample54[((k - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli2[((k - 1) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 16 drawn from beta. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample16() {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		{
			// Processing random variable 29.
			{
				// Looking for a path between Sample 16 and consumer Bernoulli 29.
				{
					for(int j = 0; j < 1; j += 1) {
						if((0 == j)) {
							// Processing sample task 37 of consumer random variable bernoulli1.
							{
								for(int var33 = 0; var33 < length$flipsMeasured[j]; var33 += 1) {
									// Include the value sampled by task 37 from random variable bernoulli1.
									// Increment the number of samples.
									cv$count = (cv$count + 1);
									
									// If the sample value was positive increase the count
									if(flips[j][var33])
										cv$sum = (cv$sum + 1);
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 44.
			{
				// Looking for a path between Sample 16 and consumer Bernoulli 44.
				{
					for(int k = 1; k < coins; k += 1) {
						if((0 == k)) {
							// Processing sample task 54 of consumer random variable bernoulli2.
							{
								for(int var48 = 0; var48 < length$flipsMeasured[k]; var48 += 1) {
									// Include the value sampled by task 54 from random variable bernoulli2.
									// Increment the number of samples.
									cv$count = (cv$count + 1);
									
									// If the sample value was positive increase the count
									if(flips[k][var48])
										cv$sum = (cv$sum + 1);
								}
							}
						}
					}
				}
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		double var14 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[0] = var14;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 21 drawn from beta. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample21(int i$var18, int threadID$cv$i$var18, Rng RNG$) {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		{
			// Processing random variable 29.
			{
				// Looking for a path between Sample 21 and consumer Bernoulli 29.
				{
					for(int j = 0; j < 1; j += 1) {
						if((i$var18 == j)) {
							// Processing sample task 37 of consumer random variable bernoulli1.
							{
								for(int var33 = 0; var33 < length$flipsMeasured[j]; var33 += 1) {
									// Include the value sampled by task 37 from random variable bernoulli1.
									// Increment the number of samples.
									cv$count = (cv$count + 1);
									
									// If the sample value was positive increase the count
									if(flips[j][var33])
										cv$sum = (cv$sum + 1);
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 44.
			{
				// Looking for a path between Sample 21 and consumer Bernoulli 44.
				{
					for(int k = 1; k < coins; k += 1) {
						if((i$var18 == k)) {
							// Processing sample task 54 of consumer random variable bernoulli2.
							{
								for(int var48 = 0; var48 < length$flipsMeasured[k]; var48 += 1) {
									// Include the value sampled by task 54 from random variable bernoulli2.
									// Increment the number of samples.
									cv$count = (cv$count + 1);
									
									// If the sample value was positive increase the count
									if(flips[k][var48])
										cv$sum = (cv$sum + 1);
								}
							}
						}
					}
				}
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		double var19 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[i$var18] = var19;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If flips has not been set already allocate space.
		if(!setFlag$flips) {
			// Constructor for flips
			{
				flips = new boolean[length$flipsMeasured.length][];
				for(int j = 0; j < 1; j += 1)
					flips[j] = new boolean[length$flipsMeasured[j]];
				for(int k = 1; k < length$flipsMeasured.length; k += 1)
					flips[k] = new boolean[length$flipsMeasured[k]];
			}
		}
		
		// Constructor for intermediateFlips
		{
			intermediateFlips = new boolean[length$flipsMeasured.length][];
			for(int l = 0; l < length$flipsMeasured.length; l += 1)
				intermediateFlips[l] = new boolean[length$flipsMeasured[l]];
		}
		
		// If bias has not been set already allocate space.
		if(!setFlag$bias) {
			// Constructor for bias
			{
				bias = new double[length$flipsMeasured.length];
			}
		}
		
		// Constructor for logProbability$bernoulli1
		{
			logProbability$bernoulli1 = new double[((((1 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample37
		{
			logProbability$sample37 = new double[((((1 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$bernoulli2
		{
			logProbability$bernoulli2 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample54
		{
			logProbability$sample54 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)];
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample16)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, coins, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!fixedFlag$sample21)
							bias[i$var18] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 1, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						boolean[] var30 = flips[j];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, length$flipsMeasured[j], 1,
							(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
										if(!fixedFlag$sample37)
											var30[var33] = DistributionSampling.sampleBernoulli(RNG$2, bias[j]);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, coins, 1,
			(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
						int k = index$k;
						boolean[] var45 = flips[k];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, length$flipsMeasured[k], 1,
							(int forStart$var48, int forEnd$var48, int threadID$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var48 = forStart$var48; var48 < forEnd$var48; var48 += 1) {
										if(!fixedFlag$sample54)
											var45[var48] = DistributionSampling.sampleBernoulli(RNG$2, bias[k]);
									}
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample16)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, coins, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!fixedFlag$sample21)
							bias[i$var18] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample16)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, coins, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!fixedFlag$sample21)
							bias[i$var18] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample16)
				sample16();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
							if(!fixedFlag$sample21)
								sample21(i$var18, threadID$i$var18, RNG$1);
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
							if(!fixedFlag$sample21)
								sample21(i$var18, threadID$i$var18, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample16)
				sample16();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		coins = length$flipsMeasured.length;
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
		logProbability$beta = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$var14 = 0.0;
		if(!fixedProbFlag$sample21)
			logProbability$var19 = 0.0;
		for(int j = 0; j < 1; j += 1)
			logProbability$bernoulli1[((j - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample37) {
			for(int j = 0; j < 1; j += 1)
				logProbability$sample37[((j - 0) / 1)] = 0.0;
		}
		for(int k = 1; k < coins; k += 1)
			logProbability$bernoulli2[((k - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample54) {
			for(int k = 1; k < coins; k += 1)
				logProbability$sample54[((k - 1) / 1)] = 0.0;
		}
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
		if(fixedFlag$sample16)
			logProbabilityValue$sample16();
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample37();
		logProbabilityValue$sample54();
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
		logProbabilityValue$sample16();
		logProbabilityValue$sample21();
		logProbabilityValue$sample37();
		logProbabilityValue$sample54();
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
		logProbabilityValue$sample16();
		logProbabilityValue$sample21();
		logProbabilityValue$sample37();
		logProbabilityValue$sample54();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample16)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, coins, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!fixedFlag$sample21)
							bias[i$var18] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Setting intermediate variables that are used to constrain other variables.
		{
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$flipsMeasured.length, 1,
				(int forStart$index$l, int forEnd$index$l, int threadID$index$l, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$l = forStart$index$l; index$l < forEnd$index$l; index$l += 1) {
							int l = index$l;
							boolean[] target = intermediateFlips[l];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, length$flipsMeasured[l], 1,
								(int forStart$m$var62, int forEnd$m$var62, int threadID$m$var62, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int m$var62 = forStart$m$var62; m$var62 < forEnd$m$var62; m$var62 += 1)
											target[m$var62] = flipsMeasured[l][m$var62];
								}
							);
						}
				}
			);
		}
		
		// Propagating values back from observations into the models intermediate variables.
		{
			for(int i$var68 = (coins - ((((coins - 1) - 0) % 1) + 1)); i$var68 >= ((0 - 1) + 1); i$var68 -= 1) {
				// Deep copy between arrays
				boolean[] cv$source1 = intermediateFlips[(coins - (i$var68 + 1))];
				boolean[] cv$target1 = flips[i$var68];
				int cv$length1 = cv$target1.length;
				for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
					cv$target1[cv$index1] = cv$source1[cv$index1];
			}
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK12(boolean[][] flipsMeasured) {\n    int coins = flipsMeasured.length;\n         \n    boolean[][] flips = new boolean[coins][];\n    boolean[][] intermediateFlips = new boolean[coins][];\n    double [] bias = new double[coins];\n        \n    Beta beta = beta(1.0, 1.0);\n        \n    bias[0] = beta.sample();\n        \n    for(int i:[1..coins))\n        bias[i] = beta.sample();\n    \n    for(int j:[0..1)) {\n        int samples = flipsMeasured[j].length;\n        Bernoulli bernoulli1 = bernoulli(bias[j]);\n        flips[j] = bernoulli1.sample(samples);\n    }\n                \n    for(int k:[1..coins)) {\n        int samples = flipsMeasured[k].length;\n        Bernoulli bernoulli2 = bernoulli(bias[k]);\n        flips[k] = bernoulli2.sample(samples);\n    }\n        \n    for(int l:[0..coins)) {\n        boolean[] source = flipsMeasured[l];\n        int noFlips = source.length;\n        boolean[] target = new boolean[noFlips];\n        intermediateFlips[l] = target;\n        \n        for(int m:[0..noFlips))\n            target[m] = source[m];\n    }\n        \n    for(int i:[0..coins)) {\n        boolean[] f = flips[i];\n        boolean[] m = intermediateFlips[coins - (i+1)];\n        f.observe(m);\n    }\n}";
	}
}