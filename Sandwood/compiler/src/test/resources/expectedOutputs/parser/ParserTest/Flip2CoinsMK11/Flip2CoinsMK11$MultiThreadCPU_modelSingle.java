package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK11$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip2CoinsMK11$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample22 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean fixedProbFlag$sample77 = false;
	private boolean fixedProbFlag$sample9 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private int[] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli1;
	private double logProbability$bernoulli2;
	private double logProbability$beta;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$var22;
	private double logProbability$var49;
	private double logProbability$var76;
	private double logProbability$var9;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK11$MultiThreadCPU(ExecutionTarget target) {
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
		// Set bias
		bias = cv$value;
		
		// Unset the fixed probability flag for sample 9 as it depends on bias.
		fixedProbFlag$sample9 = false;
		
		// Unset the fixed probability flag for sample 22 as it depends on bias.
		fixedProbFlag$sample22 = false;
		
		// Unset the fixed probability flag for sample 49 as it depends on bias.
		fixedProbFlag$sample49 = false;
		
		// Unset the fixed probability flag for sample 77 as it depends on bias.
		fixedProbFlag$sample77 = false;
	}

	// Getter for coins.
	@Override
	public final int get$coins() {
		return coins;
	}

	// Getter for fixedFlag$sample22.
	@Override
	public final boolean get$fixedFlag$sample22() {
		return fixedFlag$sample22;
	}

	// Setter for fixedFlag$sample22.
	@Override
	public final void set$fixedFlag$sample22(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample22 including if probabilities
		// need to be updated.
		fixedFlag$sample22 = cv$value;
		
		// Should the probability of sample 22 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample22 = (fixedFlag$sample22 && fixedProbFlag$sample22);
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample49 = (fixedFlag$sample22 && fixedProbFlag$sample49);
		
		// Should the probability of sample 77 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample77 = (fixedFlag$sample22 && fixedProbFlag$sample77);
	}

	// Getter for fixedFlag$sample9.
	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	// Setter for fixedFlag$sample9.
	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample9 including if probabilities
		// need to be updated.
		fixedFlag$sample9 = cv$value;
		
		// Should the probability of sample 9 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample9 = (fixedFlag$sample9 && fixedProbFlag$sample9);
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample49 = (fixedFlag$sample9 && fixedProbFlag$sample49);
		
		// Should the probability of sample 77 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample77 = (fixedFlag$sample9 && fixedProbFlag$sample77);
	}

	// Getter for flips.
	@Override
	public final boolean[][] get$flips() {
		return flips;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[][] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[][] cv$value) {
		// Set flipsMeasured
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
		// Set length$flipsMeasured
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
	public final double get$logProbability$bernoulli1() {
		return logProbability$bernoulli1;
	}

	// Getter for logProbability$bernoulli2.
	@Override
	public final double get$logProbability$bernoulli2() {
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

	// Calculate the probability of the samples represented by sample22 using sampled
	// values.
	private final void logProbabilityValue$sample22() {
		// Determine if we need to calculate the values for sample task 22 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample22) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var21 = 1; i$var21 < coins; i$var21 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = bias[i$var21];
					{
						{
							double var5 = 1.0;
							double var6 = 1.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var5, var6));
							
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
			logProbability$var22 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample22 = fixedFlag$sample22;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var22;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample49 using sampled
	// values.
	private final void logProbabilityValue$sample49() {
		// Determine if we need to calculate the values for sample task 49 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample49) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j = 0; j < 1; j += 1) {
				for(int var48 = 0; var48 < length$flipsMeasured[j]; var48 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = flips[j][var48];
						{
							{
								double var37 = bias[j];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var37));
								
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
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$bernoulli1 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var49 = cv$accumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample49 = (fixedFlag$sample9 && fixedFlag$sample22);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var49;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli1 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample77 using sampled
	// values.
	private final void logProbabilityValue$sample77() {
		// Determine if we need to calculate the values for sample task 77 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample77) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int k = 1; k < coins; k += 1) {
				for(int var75 = 0; var75 < length$flipsMeasured[k]; var75 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = flips[k][var75];
						{
							{
								double var64 = bias[k];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var64));
								
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
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$bernoulli2 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var76 = cv$accumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample77 = (fixedFlag$sample9 && fixedFlag$sample22);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var76;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli2 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample9 using sampled values.
	private final void logProbabilityValue$sample9() {
		// Determine if we need to calculate the values for sample task 9 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample9) {
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
						double var5 = 1.0;
						double var6 = 1.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var5, var6));
						
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
			logProbability$var9 = cv$sampleProbability;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample9 = fixedFlag$sample9;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var9;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 22 drawn from beta. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample22(int i$var21, int threadID$cv$i$var21, Rng RNG$) {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		{
			// Processing random variable 38.
			{
				// Looking for a path between Sample 22 and consumer Bernoulli 38.
				{
					for(int j = 0; j < 1; j += 1) {
						if((i$var21 == j)) {
							// Processing sample task 49 of consumer random variable bernoulli1.
							{
								for(int var48 = 0; var48 < length$flipsMeasured[j]; var48 += 1) {
									// Include the value sampled by task 49 from random variable bernoulli1.
									// Increment the number of samples.
									cv$count = (cv$count + 1);
									
									// If the sample value was positive increase the count
									if(flips[j][var48])
										cv$sum = (cv$sum + 1);
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 65.
			{
				// Looking for a path between Sample 22 and consumer Bernoulli 65.
				{
					for(int k = 1; k < coins; k += 1) {
						if((i$var21 == k)) {
							// Processing sample task 77 of consumer random variable bernoulli2.
							{
								for(int var75 = 0; var75 < length$flipsMeasured[k]; var75 += 1) {
									// Include the value sampled by task 77 from random variable bernoulli2.
									// Increment the number of samples.
									cv$count = (cv$count + 1);
									
									// If the sample value was positive increase the count
									if(flips[k][var75])
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
		double var22 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		
		// Guards to ensure that bias is only updated when there is a valid path.
		{
			{
				bias[i$var21] = var22;
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 9 drawn from beta. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample9() {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		{
			// Processing random variable 38.
			{
				// Looking for a path between Sample 9 and consumer Bernoulli 38.
				{
					for(int j = 0; j < 1; j += 1) {
						if((0 == j)) {
							// Processing sample task 49 of consumer random variable bernoulli1.
							{
								for(int var48 = 0; var48 < length$flipsMeasured[j]; var48 += 1) {
									// Include the value sampled by task 49 from random variable bernoulli1.
									// Increment the number of samples.
									cv$count = (cv$count + 1);
									
									// If the sample value was positive increase the count
									if(flips[j][var48])
										cv$sum = (cv$sum + 1);
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 65.
			{
				// Looking for a path between Sample 9 and consumer Bernoulli 65.
				{
					for(int k = 1; k < coins; k += 1) {
						if((0 == k)) {
							// Processing sample task 77 of consumer random variable bernoulli2.
							{
								for(int var75 = 0; var75 < length$flipsMeasured[k]; var75 += 1) {
									// Include the value sampled by task 77 from random variable bernoulli2.
									// Increment the number of samples.
									cv$count = (cv$count + 1);
									
									// If the sample value was positive increase the count
									if(flips[k][var75])
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
		double var9 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		
		// Guards to ensure that bias is only updated when there is a valid path.
		{
			{
				bias[0] = var9;
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
		// Constructor for flips
		{
			flips = new boolean[length$flipsMeasured.length][];
			for(int j = 0; j < 1; j += 1)
				flips[j] = new boolean[length$flipsMeasured[j]];
			for(int k = 1; k < length$flipsMeasured.length; k += 1)
				flips[k] = new boolean[length$flipsMeasured[k]];
		}
		
		// If bias has not been set already allocate space.
		if((!fixedFlag$sample9 || !fixedFlag$sample22)) {
			// Constructor for bias
			{
				bias = new double[length$flipsMeasured.length];
			}
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample9)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, coins, 1,
			(int forStart$i$var21, int forEnd$i$var21, int threadID$i$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var21 = forStart$i$var21; i$var21 < forEnd$i$var21; i$var21 += 1) {
						if(!fixedFlag$sample22)
							bias[i$var21] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
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
						int threadID$j = threadID$index$j;
						boolean[] var39 = flips[j];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, length$flipsMeasured[j], 1,
							(int forStart$var48, int forEnd$var48, int threadID$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var48 = forStart$var48; var48 < forEnd$var48; var48 += 1)
										var39[var48] = DistributionSampling.sampleBernoulli(RNG$2, bias[j]);
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
						int threadID$k = threadID$index$k;
						boolean[] var66 = flips[k];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, length$flipsMeasured[k], 1,
							(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
										var66[var75] = DistributionSampling.sampleBernoulli(RNG$2, bias[k]);
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
		if(!fixedFlag$sample9)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, coins, 1,
			(int forStart$i$var21, int forEnd$i$var21, int threadID$i$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var21 = forStart$i$var21; i$var21 < forEnd$i$var21; i$var21 += 1) {
						if(!fixedFlag$sample22)
							bias[i$var21] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample9)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, coins, 1,
			(int forStart$i$var21, int forEnd$i$var21, int threadID$i$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var21 = forStart$i$var21; i$var21 < forEnd$i$var21; i$var21 += 1) {
						if(!fixedFlag$sample22)
							bias[i$var21] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample9)
				sample9();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i$var21, int forEnd$i$var21, int threadID$i$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var21 = forStart$i$var21; i$var21 < forEnd$i$var21; i$var21 += 1) {
							if(!fixedFlag$sample22)
								sample22(i$var21, threadID$i$var21, RNG$1);
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i$var21, int forEnd$i$var21, int threadID$i$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var21 = forStart$i$var21; i$var21 < forEnd$i$var21; i$var21 += 1) {
							if(!fixedFlag$sample22)
								sample22(i$var21, threadID$i$var21, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample9)
				sample9();
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
		if(!fixedProbFlag$sample9)
			logProbability$var9 = 0.0;
		if(!fixedProbFlag$sample22)
			logProbability$var22 = 0.0;
		logProbability$bernoulli1 = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample49)
			logProbability$var49 = 0.0;
		logProbability$bernoulli2 = 0.0;
		if(!fixedProbFlag$sample77)
			logProbability$var76 = 0.0;
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
		if(fixedFlag$sample9)
			logProbabilityValue$sample9();
		if(fixedFlag$sample22)
			logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
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
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
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
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample9)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, coins, 1,
			(int forStart$i$var21, int forEnd$i$var21, int threadID$i$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var21 = forStart$i$var21; i$var21 < forEnd$i$var21; i$var21 += 1) {
						if(!fixedFlag$sample22)
							bias[i$var21] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
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
	public final void propagateObservedValues() {
		for(int i$var88 = (coins - ((((coins - 1) - 0) % 1) + 1)); i$var88 >= ((0 - 1) + 1); i$var88 -= 1) {
			// Deep copy between arrays
			boolean[] cv$source1 = flipsMeasured[(coins - (i$var88 + 1))];
			boolean[] cv$target1 = flips[i$var88];
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {}

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
		     + "public model Flip2CoinsMK11(boolean[][] flipsMeasured) {\n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "         \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "    double [] bias = new double[coins];\n"
		     + "        \n"
		     + "    Beta beta = beta(1.0, 1.0);\n"
		     + "        \n"
		     + "    bias[0] = beta.sample();\n"
		     + "        \n"
		     + "    for(int i:[1..coins))\n"
		     + "        bias[i] = beta.sample();\n"
		     + "        \n"
		     + "    for(int j:[0..1)) {\n"
		     + "        int samples = flipsMeasured[j].length;\n"
		     + "        Bernoulli bernoulli1 = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli1.sample(samples);\n"
		     + "    }\n"
		     + "                \n"
		     + "    for(int k:[1..coins)) {\n"
		     + "        int samples = flipsMeasured[k].length;\n"
		     + "        Bernoulli bernoulli2 = bernoulli(bias[k]);\n"
		     + "        flips[k] = bernoulli2.sample(samples);\n"
		     + "    }\n"
		     + "        \n"
		     + "    for(int i:[0..coins)) {\n"
		     + "        boolean[] f = flips[i];\n"
		     + "        boolean[] m = flipsMeasured[coins - (i+1)];\n"
		     + "        f.observe(m);\n"
		     + "    }\n"
		     + "}";
	}
}