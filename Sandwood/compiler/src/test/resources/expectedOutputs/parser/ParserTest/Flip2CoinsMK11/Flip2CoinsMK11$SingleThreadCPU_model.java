package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK11$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip2CoinsMK11$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample15 = false;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedProbFlag$sample15 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample52 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private int[] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli1;
	private double[] logProbability$bernoulli2;
	private double logProbability$beta;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample36;
	private double[] logProbability$sample52;
	private double logProbability$var13;
	private double logProbability$var18;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK11$SingleThreadCPU(ExecutionTarget target) {
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
		// Set bias with flag to mark that it has been set so another array doesn't need to
		// be constructed
		bias = cv$value;
		setFlag$bias = true;
	}

	// Getter for coins.
	@Override
	public final int get$coins() {
		return coins;
	}

	// Getter for fixedFlag$sample15.
	@Override
	public final boolean get$fixedFlag$sample15() {
		return fixedFlag$sample15;
	}

	// Setter for fixedFlag$sample15.
	@Override
	public final void set$fixedFlag$sample15(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample15 including if probabilities
		// need to be updated.
		fixedFlag$sample15 = cv$value;
		
		// Should the probability of sample 15 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample15 = (fixedFlag$sample15 && fixedProbFlag$sample15);
		
		// Should the probability of sample 36 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample36 = (fixedFlag$sample15 && fixedProbFlag$sample36);
		
		// Should the probability of sample 52 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample52 = (fixedFlag$sample15 && fixedProbFlag$sample52);
	}

	// Getter for fixedFlag$sample20.
	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	// Setter for fixedFlag$sample20.
	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample20 including if probabilities
		// need to be updated.
		fixedFlag$sample20 = cv$value;
		
		// Should the probability of sample 20 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedProbFlag$sample20);
		
		// Should the probability of sample 36 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample36 = (fixedFlag$sample20 && fixedProbFlag$sample36);
		
		// Should the probability of sample 52 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample52 = (fixedFlag$sample20 && fixedProbFlag$sample52);
	}

	// Getter for fixedFlag$sample36.
	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	// Setter for fixedFlag$sample36.
	@Override
	public final void set$fixedFlag$sample36(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample36 including if probabilities
		// need to be updated.
		fixedFlag$sample36 = cv$value;
		
		// Should the probability of sample 36 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedProbFlag$sample36);
	}

	// Getter for fixedFlag$sample52.
	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	// Setter for fixedFlag$sample52.
	@Override
	public final void set$fixedFlag$sample52(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample52 including if probabilities
		// need to be updated.
		fixedFlag$sample52 = cv$value;
		
		// Should the probability of sample 52 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample52 = (fixedFlag$sample52 && fixedProbFlag$sample52);
	}

	// Getter for flips.
	@Override
	public final boolean[][] get$flips() {
		return flips;
	}

	// Setter for flips.
	@Override
	public final void set$flips(boolean[][] cv$value) {
		// Set flips with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips = cv$value;
		setFlag$flips = true;
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

	// Calculate the probability of the samples represented by sample15 using sampled
	// values.
	private final void logProbabilityValue$sample15() {
		// Determine if we need to calculate the values for sample task 15 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample15) {
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
						double var9 = 1.0;
						double var10 = 1.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var9, var10));
						
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
			logProbability$var13 = cv$sampleProbability;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample15 = fixedFlag$sample15;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var13;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample20 using sampled
	// values.
	private final void logProbabilityValue$sample20() {
		// Determine if we need to calculate the values for sample task 20 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample20) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var17 = 1; i$var17 < coins; i$var17 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = bias[i$var17];
					{
						{
							double var9 = 1.0;
							double var10 = 1.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var9, var10));
							
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
			logProbability$var18 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample20 = fixedFlag$sample20;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var18;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample36 using sampled
	// values.
	private final void logProbabilityValue$sample36() {
		// Determine if we need to calculate the values for sample task 36 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample36) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < 1; j += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int var32 = 0; var32 < length$flipsMeasured[j]; var32 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = flips[j][var32];
						{
							{
								double var27 = bias[j];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var27));
								
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
				logProbability$sample36[((j - 0) / 1)] = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample36 = ((fixedFlag$sample36 && fixedFlag$sample15) && fixedFlag$sample20);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < 1; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample36[((j - 0) / 1)];
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

	// Calculate the probability of the samples represented by sample52 using sampled
	// values.
	private final void logProbabilityValue$sample52() {
		// Determine if we need to calculate the values for sample task 52 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample52) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int k = 1; k < coins; k += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int var47 = 0; var47 < length$flipsMeasured[k]; var47 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = flips[k][var47];
						{
							{
								double var42 = bias[k];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var42));
								
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
				logProbability$sample52[((k - 1) / 1)] = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample52 = ((fixedFlag$sample52 && fixedFlag$sample15) && fixedFlag$sample20);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int k = 1; k < coins; k += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample52[((k - 1) / 1)];
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
	// by sample task 15 drawn from beta. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample15() {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		{
			// Processing random variable 28.
			{
				// Looking for a path between Sample 15 and consumer Bernoulli 28.
				{
					for(int j = 0; j < 1; j += 1) {
						if((0 == j)) {
							// Processing sample task 36 of consumer random variable bernoulli1.
							{
								for(int var32 = 0; var32 < length$flipsMeasured[j]; var32 += 1) {
									// Include the value sampled by task 36 from random variable bernoulli1.
									// Increment the number of samples.
									cv$count = (cv$count + 1);
									
									// If the sample value was positive increase the count
									if(flips[j][var32])
										cv$sum = (cv$sum + 1);
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 43.
			{
				// Looking for a path between Sample 15 and consumer Bernoulli 43.
				{
					for(int k = 1; k < coins; k += 1) {
						if((0 == k)) {
							// Processing sample task 52 of consumer random variable bernoulli2.
							{
								for(int var47 = 0; var47 < length$flipsMeasured[k]; var47 += 1) {
									// Include the value sampled by task 52 from random variable bernoulli2.
									// Increment the number of samples.
									cv$count = (cv$count + 1);
									
									// If the sample value was positive increase the count
									if(flips[k][var47])
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
		double var13 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[0] = var13;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 20 drawn from beta. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample20(int i$var17) {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		{
			// Processing random variable 28.
			{
				// Looking for a path between Sample 20 and consumer Bernoulli 28.
				{
					for(int j = 0; j < 1; j += 1) {
						if((i$var17 == j)) {
							// Processing sample task 36 of consumer random variable bernoulli1.
							{
								for(int var32 = 0; var32 < length$flipsMeasured[j]; var32 += 1) {
									// Include the value sampled by task 36 from random variable bernoulli1.
									// Increment the number of samples.
									cv$count = (cv$count + 1);
									
									// If the sample value was positive increase the count
									if(flips[j][var32])
										cv$sum = (cv$sum + 1);
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 43.
			{
				// Looking for a path between Sample 20 and consumer Bernoulli 43.
				{
					for(int k = 1; k < coins; k += 1) {
						if((i$var17 == k)) {
							// Processing sample task 52 of consumer random variable bernoulli2.
							{
								for(int var47 = 0; var47 < length$flipsMeasured[k]; var47 += 1) {
									// Include the value sampled by task 52 from random variable bernoulli2.
									// Increment the number of samples.
									cv$count = (cv$count + 1);
									
									// If the sample value was positive increase the count
									if(flips[k][var47])
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
		double var18 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[i$var17] = var18;
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
		
		// Constructor for logProbability$sample36
		{
			logProbability$sample36 = new double[((((1 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$bernoulli2
		{
			logProbability$bernoulli2 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample52
		{
			logProbability$sample52 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)];
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample15)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var17 = 1; i$var17 < coins; i$var17 += 1) {
			if(!fixedFlag$sample20)
				bias[i$var17] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int j = 0; j < 1; j += 1) {
			boolean[] var29 = flips[j];
			for(int var32 = 0; var32 < length$flipsMeasured[j]; var32 += 1) {
				if(!fixedFlag$sample36)
					var29[var32] = DistributionSampling.sampleBernoulli(RNG$, bias[j]);
			}
		}
		for(int k = 1; k < coins; k += 1) {
			boolean[] var44 = flips[k];
			for(int var47 = 0; var47 < length$flipsMeasured[k]; var47 += 1) {
				if(!fixedFlag$sample52)
					var44[var47] = DistributionSampling.sampleBernoulli(RNG$, bias[k]);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample15)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var17 = 1; i$var17 < coins; i$var17 += 1) {
			if(!fixedFlag$sample20)
				bias[i$var17] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample15)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var17 = 1; i$var17 < coins; i$var17 += 1) {
			if(!fixedFlag$sample20)
				bias[i$var17] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample15)
				sample15();
			for(int i$var17 = 1; i$var17 < coins; i$var17 += 1) {
				if(!fixedFlag$sample20)
					sample20(i$var17);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var17 = (coins - ((((coins - 1) - 1) % 1) + 1)); i$var17 >= ((1 - 1) + 1); i$var17 -= 1) {
				if(!fixedFlag$sample20)
					sample20(i$var17);
			}
			if(!fixedFlag$sample15)
				sample15();
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
		if(!fixedProbFlag$sample15)
			logProbability$var13 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$var18 = 0.0;
		for(int j = 0; j < 1; j += 1)
			logProbability$bernoulli1[((j - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample36) {
			for(int j = 0; j < 1; j += 1)
				logProbability$sample36[((j - 0) / 1)] = 0.0;
		}
		for(int k = 1; k < coins; k += 1)
			logProbability$bernoulli2[((k - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample52) {
			for(int k = 1; k < coins; k += 1)
				logProbability$sample52[((k - 1) / 1)] = 0.0;
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
		if(fixedFlag$sample15)
			logProbabilityValue$sample15();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		logProbabilityValue$sample36();
		logProbabilityValue$sample52();
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
		logProbabilityValue$sample15();
		logProbabilityValue$sample20();
		logProbabilityValue$sample36();
		logProbabilityValue$sample52();
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
		logProbabilityValue$sample15();
		logProbabilityValue$sample20();
		logProbabilityValue$sample36();
		logProbabilityValue$sample52();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample15)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var17 = 1; i$var17 < coins; i$var17 += 1) {
			if(!fixedFlag$sample20)
				bias[i$var17] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		for(int i$var53 = (coins - ((((coins - 1) - 0) % 1) + 1)); i$var53 >= ((0 - 1) + 1); i$var53 -= 1) {
			// Deep copy between arrays
			boolean[] cv$source1 = flipsMeasured[(coins - (i$var53 + 1))];
			boolean[] cv$target1 = flips[i$var53];
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK11(boolean[][] flipsMeasured) {\n    int coins = flipsMeasured.length;\n         \n    boolean[][] flips = new boolean[coins][];\n    double [] bias = new double[coins];\n        \n    Beta beta = beta(1.0, 1.0);\n        \n    bias[0] = beta.sample();\n        \n    for(int i:[1..coins))\n        bias[i] = beta.sample();\n        \n    for(int j:[0..1)) {\n        int samples = flipsMeasured[j].length;\n        Bernoulli bernoulli1 = bernoulli(bias[j]);\n        flips[j] = bernoulli1.sample(samples);\n    }\n                \n    for(int k:[1..coins)) {\n        int samples = flipsMeasured[k].length;\n        Bernoulli bernoulli2 = bernoulli(bias[k]);\n        flips[k] = bernoulli2.sample(samples);\n    }\n        \n    for(int i:[0..coins)) {\n        boolean[] f = flips[i];\n        boolean[] m = flipsMeasured[coins - (i+1)];\n        f.observe(m);\n    }\n}";
	}
}