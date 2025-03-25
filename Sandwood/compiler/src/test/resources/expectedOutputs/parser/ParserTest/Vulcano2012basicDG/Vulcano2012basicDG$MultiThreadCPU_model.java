package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basicDG$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Vulcano2012basicDG$CoreInterface {
	
	// Declare the variables for the model.
	private int[][] ObsSales;
	private int[] arrivals;
	private boolean[][] avail;
	private double[] exped;
	private double[] expedNorm;
	private boolean fixedFlag$sample112 = false;
	private boolean fixedFlag$sample114 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedProbFlag$sample112 = false;
	private boolean fixedProbFlag$sample114 = false;
	private boolean fixedProbFlag$sample166 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean[] guard$sample32multinomial165$global;
	private boolean[][] guard$sample32put135$global;
	private boolean[][] guard$sample32put164$global;
	private boolean[] guard$sample32put73$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$expedNorm;
	private double logProbability$lambda;
	private double logProbability$sales;
	private double[] logProbability$sample112;
	private double[] logProbability$sample114;
	private double[] logProbability$sample166;
	private double[] logProbability$sample32;
	private double logProbability$ut;
	private double[] logProbability$var106;
	private double[] logProbability$var108;
	private double[] logProbability$var157;
	private double logProbability$var19;
	private double logProbability$weekly_rates;
	private double logProbability$weekly_sales;
	private double logProbability$weekly_ut;
	private int numTimeSteps;
	private double r;
	private int[][] sales;
	private boolean system$gibbsForward = true;
	private double[] ut;
	private double[][] weekly_rates;
	private int[][] weekly_sales;
	private double[][] weekly_ut;

	public Vulcano2012basicDG$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for ObsSales.
	@Override
	public final int[][] get$ObsSales() {
		return ObsSales;
	}

	// Setter for ObsSales.
	@Override
	public final void set$ObsSales(int[][] cv$value) {
		// Set ObsSales
		ObsSales = cv$value;
	}

	// Getter for arrivals.
	@Override
	public final int[] get$arrivals() {
		return arrivals;
	}

	// Setter for arrivals.
	@Override
	public final void set$arrivals(int[] cv$value) {
		// Set arrivals
		arrivals = cv$value;
	}

	// Getter for avail.
	@Override
	public final boolean[][] get$avail() {
		return avail;
	}

	// Setter for avail.
	@Override
	public final void set$avail(boolean[][] cv$value) {
		// Set avail
		avail = cv$value;
	}

	// Getter for fixedFlag$sample112.
	@Override
	public final boolean get$fixedFlag$sample112() {
		return fixedFlag$sample112;
	}

	// Setter for fixedFlag$sample112.
	@Override
	public final void set$fixedFlag$sample112(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample112 including if probabilities
		// need to be updated.
		fixedFlag$sample112 = cv$value;
		
		// Should the probability of sample 112 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample112 = (fixedFlag$sample112 && fixedProbFlag$sample112);
		
		// Should the probability of sample 114 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample114 = (fixedFlag$sample112 && fixedProbFlag$sample114);
	}

	// Getter for fixedFlag$sample114.
	@Override
	public final boolean get$fixedFlag$sample114() {
		return fixedFlag$sample114;
	}

	// Setter for fixedFlag$sample114.
	@Override
	public final void set$fixedFlag$sample114(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample114 including if probabilities
		// need to be updated.
		fixedFlag$sample114 = cv$value;
		
		// Should the probability of sample 114 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample114 = (fixedFlag$sample114 && fixedProbFlag$sample114);
		
		// Should the probability of sample 166 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample166 = (fixedFlag$sample114 && fixedProbFlag$sample166);
	}

	// Getter for fixedFlag$sample32.
	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	// Setter for fixedFlag$sample32.
	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample32 including if probabilities
		// need to be updated.
		fixedFlag$sample32 = cv$value;
		
		// Should the probability of sample 32 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample32 = (fixedFlag$sample32 && fixedProbFlag$sample32);
		
		// Should the probability of sample 166 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample166 = (fixedFlag$sample32 && fixedProbFlag$sample166);
	}

	// Getter for lambda.
	@Override
	public final double[] get$lambda() {
		return lambda;
	}

	// Setter for lambda.
	@Override
	public final void set$lambda(double[] cv$value) {
		// Set lambda
		lambda = cv$value;
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

	// Getter for logProbability$arrivals.
	@Override
	public final double get$logProbability$arrivals() {
		return logProbability$arrivals;
	}

	// Getter for logProbability$lambda.
	@Override
	public final double get$logProbability$lambda() {
		return logProbability$lambda;
	}

	// Getter for logProbability$weekly_sales.
	@Override
	public final double get$logProbability$weekly_sales() {
		return logProbability$weekly_sales;
	}

	// Getter for numTimeSteps.
	@Override
	public final int get$numTimeSteps() {
		return numTimeSteps;
	}

	// Getter for r.
	@Override
	public final double get$r() {
		return r;
	}

	// Getter for ut.
	@Override
	public final double[] get$ut() {
		return ut;
	}

	// Setter for ut.
	@Override
	public final void set$ut(double[] cv$value) {
		// Set flags for all the side effects of ut including if probabilities need to be
		// updated.
		// Set ut
		ut = cv$value;
		
		// Unset the fixed probability flag for sample 32 as it depends on ut.
		fixedProbFlag$sample32 = false;
		
		// Unset the fixed probability flag for sample 166 as it depends on ut.
		fixedProbFlag$sample166 = false;
	}

	// Getter for weekly_sales.
	@Override
	public final int[][] get$weekly_sales() {
		return weekly_sales;
	}

	// Calculate the probability of the samples represented by sample112 using sampled
	// values.
	private final void logProbabilityValue$sample112() {
		// Determine if we need to calculate the values for sample task 112 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample112) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						if((0 < numTimeSteps)) {
							// The sample value to calculate the probability of generating
							double cv$sampleValue = lambda[((t - 0) / 1)];
							{
								{
									double var104 = 10.0;
									double var105 = 10.0;
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var104, var105));
									
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
					logProbability$var106[((t - 0) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample112[((t - 0) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample112)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample112 = fixedFlag$sample112;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample112[((t - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var106[((t - 0) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample112)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample114 using sampled
	// values.
	private final void logProbabilityValue$sample114() {
		// Determine if we need to calculate the values for sample task 114 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample114) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						if((0 < numTimeSteps)) {
							// Reduction of array null
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							int reduceVar$numSales$13 = 0;
							
							// For each index in the array to be reduced
							for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
								// Set the left hand term of the reduction function to the return variable value.
								int k$var98 = reduceVar$numSales$13;
								
								// Set the right hand term to a value from the array var92
								int l$var99 = ObsSales[t][cv$reduction100Index];
								
								// Execute the reduction function, saving the result into the return value.
								// 
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$numSales$13 = (k$var98 + l$var99);
							}
							
							// The sample value to calculate the probability of generating
							int cv$sampleValue = (arrivals[((t - 0) / 1)] - reduceVar$numSales$13);
							{
								{
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, lambda[((t - 0) / 1)]));
									
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
					logProbability$var108[((t - 0) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample114[((t - 0) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample114)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample114 = (fixedFlag$sample114 && fixedFlag$sample112);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample114[((t - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var108[((t - 0) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample114)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample166 using sampled
	// values.
	private final void logProbabilityValue$sample166() {
		// Determine if we need to calculate the values for sample task 166 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample166) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						if((0 < numTimeSteps)) {
							// The sample value to calculate the probability of generating
							int[] cv$sampleValue = weekly_sales[((t - 0) / 1)];
							{
								{
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, weekly_rates[((t - 0) / 1)], (avail[0].length + 1), arrivals[((t - 0) / 1)]));
									
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
					logProbability$var157[((t - 0) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample166[((t - 0) / 1)] = cv$sampleProbability;
					
					// Guard to ensure that sales is only updated once for this probability.
					boolean cv$guard$sales = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					{
						if((0 < numTimeSteps)) {
							for(int j$var168 = 0; j$var168 < avail[0].length; j$var168 += 1) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$sales) {
									// Set the guard so the update is only applied once.
									cv$guard$sales = true;
									
									// Update the variable probability
									logProbability$sales = (logProbability$sales + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample166 = (fixedFlag$sample32 && fixedFlag$sample114);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample166[((t - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var157[((t - 0) / 1)] = cv$rvAccumulator;
					
					// Guard to ensure that sales is only updated once for this probability.
					boolean cv$guard$sales = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					{
						if((0 < numTimeSteps)) {
							for(int j$var168 = 0; j$var168 < avail[0].length; j$var168 += 1) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$sales) {
									// Set the guard so the update is only applied once.
									cv$guard$sales = true;
									
									// Update the variable probability
									logProbability$sales = (logProbability$sales + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample32 using sampled
	// values.
	private final void logProbabilityValue$sample32() {
		// Determine if we need to calculate the values for sample task 32 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample32) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int var29 = 0; var29 < avail[0].length; var29 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						if((0 < numTimeSteps)) {
							// The sample value to calculate the probability of generating
							double cv$sampleValue = ut[var29];
							{
								{
									double var17 = 0.0;
									double var18 = 1.0;
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var17) / Math.sqrt(var18))) - (0.5 * Math.log(var18))));
									
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
					
					// Store the sample task probability
					logProbability$sample32[((var29 - 0) / 1)] = cv$sampleProbability;
					
					// Guard to ensure that exped is only updated once for this probability.
					boolean cv$guard$exped = false;
					
					// Guard to ensure that expedNorm is only updated once for this probability.
					boolean cv$guard$expedNorm = false;
					
					// Guard to ensure that weekly_ut is only updated once for this probability.
					boolean cv$guard$weekly_ut = false;
					
					// Guard to ensure that weekly_rates is only updated once for this probability.
					boolean cv$guard$weekly_rates = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Looking for a path between Sample 32 and consumer double[] 44.
					{
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$exped) {
										// Set the guard so the update is only applied once.
										cv$guard$exped = true;
										
										// Update the variable probability
										logProbability$exped = (logProbability$exped + cv$sampleProbability);
									}
								}
							}
						}
					}
					
					// Looking for a path between Sample 32 and consumer double[] 70.
					{
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												// If the probability of the variable has not already been updated
												if(!cv$guard$expedNorm) {
													// Set the guard so the update is only applied once.
													cv$guard$expedNorm = true;
													
													// Update the variable probability
													logProbability$expedNorm = (logProbability$expedNorm + cv$sampleProbability);
												}
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$expedNorm) {
												// Set the guard so the update is only applied once.
												cv$guard$expedNorm = true;
												
												// Update the variable probability
												logProbability$expedNorm = (logProbability$expedNorm + cv$sampleProbability);
											}
										}
									}
								}
							}
						}
					}
					
					// Looking for a path between Sample 32 and consumer double[] 128.
					{
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																// If the probability of the variable has not already been updated
																if(!cv$guard$weekly_ut) {
																	// Set the guard so the update is only applied once.
																	cv$guard$weekly_ut = true;
																	
																	// Update the variable probability
																	logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleProbability);
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															// If the probability of the variable has not already been updated
															if(!cv$guard$weekly_ut) {
																// Set the guard so the update is only applied once.
																cv$guard$weekly_ut = true;
																
																// Update the variable probability
																logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleProbability);
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
					
					// Looking for a path between Sample 32 and consumer double[] 156.
					{
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																	{
																		for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																			// If the probability of the variable has not already been updated
																			if(!cv$guard$weekly_rates) {
																				// Set the guard so the update is only applied once.
																				cv$guard$weekly_rates = true;
																				
																				// Update the variable probability
																				logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleProbability);
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																	if((j$var121 == j$var153)) {
																		// If the probability of the variable has not already been updated
																		if(!cv$guard$weekly_rates) {
																			// Set the guard so the update is only applied once.
																			cv$guard$weekly_rates = true;
																			
																			// Update the variable probability
																			logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleProbability);
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																{
																	for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																		// If the probability of the variable has not already been updated
																		if(!cv$guard$weekly_rates) {
																			// Set the guard so the update is only applied once.
																			cv$guard$weekly_rates = true;
																			
																			// Update the variable probability
																			logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleProbability);
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																if((j$var121 == j$var153)) {
																	// If the probability of the variable has not already been updated
																	if(!cv$guard$weekly_rates) {
																		// Set the guard so the update is only applied once.
																		cv$guard$weekly_rates = true;
																		
																		// Update the variable probability
																		logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleProbability);
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
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var19 = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample32 = fixedFlag$sample32;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				double cv$rvAccumulator = 0.0;
				for(int var29 = 0; var29 < avail[0].length; var29 += 1) {
					double cv$sampleValue = logProbability$sample32[((var29 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Guard to ensure that exped is only updated once for this probability.
					boolean cv$guard$exped = false;
					
					// Guard to ensure that expedNorm is only updated once for this probability.
					boolean cv$guard$expedNorm = false;
					
					// Guard to ensure that weekly_ut is only updated once for this probability.
					boolean cv$guard$weekly_ut = false;
					
					// Guard to ensure that weekly_rates is only updated once for this probability.
					boolean cv$guard$weekly_rates = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Looking for a path between Sample 32 and consumer double[] 44.
					{
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$exped) {
										// Set the guard so the update is only applied once.
										cv$guard$exped = true;
										
										// Update the variable probability
										logProbability$exped = (logProbability$exped + cv$sampleValue);
									}
								}
							}
						}
					}
					
					// Looking for a path between Sample 32 and consumer double[] 70.
					{
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												// If the probability of the variable has not already been updated
												if(!cv$guard$expedNorm) {
													// Set the guard so the update is only applied once.
													cv$guard$expedNorm = true;
													
													// Update the variable probability
													logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
												}
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$expedNorm) {
												// Set the guard so the update is only applied once.
												cv$guard$expedNorm = true;
												
												// Update the variable probability
												logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
											}
										}
									}
								}
							}
						}
					}
					
					// Looking for a path between Sample 32 and consumer double[] 128.
					{
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																// If the probability of the variable has not already been updated
																if(!cv$guard$weekly_ut) {
																	// Set the guard so the update is only applied once.
																	cv$guard$weekly_ut = true;
																	
																	// Update the variable probability
																	logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															// If the probability of the variable has not already been updated
															if(!cv$guard$weekly_ut) {
																// Set the guard so the update is only applied once.
																cv$guard$weekly_ut = true;
																
																// Update the variable probability
																logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
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
					
					// Looking for a path between Sample 32 and consumer double[] 156.
					{
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																	{
																		for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																			// If the probability of the variable has not already been updated
																			if(!cv$guard$weekly_rates) {
																				// Set the guard so the update is only applied once.
																				cv$guard$weekly_rates = true;
																				
																				// Update the variable probability
																				logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																	if((j$var121 == j$var153)) {
																		// If the probability of the variable has not already been updated
																		if(!cv$guard$weekly_rates) {
																			// Set the guard so the update is only applied once.
																			cv$guard$weekly_rates = true;
																			
																			// Update the variable probability
																			logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																{
																	for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																		// If the probability of the variable has not already been updated
																		if(!cv$guard$weekly_rates) {
																			// Set the guard so the update is only applied once.
																			cv$guard$weekly_rates = true;
																			
																			// Update the variable probability
																			logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																if((j$var121 == j$var153)) {
																	// If the probability of the variable has not already been updated
																	if(!cv$guard$weekly_rates) {
																		// Set the guard so the update is only applied once.
																		cv$guard$weekly_rates = true;
																		
																		// Update the variable probability
																		logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
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
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var19 = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 112 drawn from Gamma 106. Inference was performed using a Gamma
	// to Poisson conjugate prior.
	private final void sample112(int t, int threadID$cv$t, Rng RNG$) {
		// Variable to store the sum of all the samples from consuming random variables.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		{
			// Processing random variable 108.
			{
				{
					if((0 < numTimeSteps)) {
						// Processing sample task 114 of consumer random variable null.
						{
							if((0 < numTimeSteps)) {
								{
									{
										{
											{
												if((0 < numTimeSteps)) {
													// Reduction of array null
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													int reduceVar$numSales$9 = 0;
													
													// For each index in the array to be reduced
													for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														int k$var98 = reduceVar$numSales$9;
														
														// Set the right hand term to a value from the array var92
														int l$var99 = ObsSales[t][cv$reduction100Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$numSales$9 = (k$var98 + l$var99);
													}
													
													// Add the value of a sample from consuming random variable var108 to the inference
													// state.
													cv$sum = (cv$sum + (arrivals[((t - 0) / 1)] - reduceVar$numSales$9));
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
		
		// Write out the new value of the sample.
		lambda[((t - 0) / 1)] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 114 drawn from Poisson 108. Inference was performed using Metropolis-Hastings.
	private final void sample114(int t, int threadID$cv$t, Rng RNG$) {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		{
			// Metropolis-Hastings
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		
		// Reduction of array null
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		int reduceVar$numSales$10 = 0;
		
		// For each index in the array to be reduced
		for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			int k$var98 = reduceVar$numSales$10;
			
			// Set the right hand term to a value from the array var92
			int l$var99 = ObsSales[t][cv$reduction100Index];
			
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			reduceVar$numSales$10 = (k$var98 + l$var99);
		}
		
		// The original value of the sample
		int cv$originalValue = (arrivals[((t - 0) / 1)] - reduceVar$numSales$10);
		
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability = 0.0;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		
		// Ensure the variance is at least 1
		if((cv$var < 1.0))
			cv$var = 1.0;
		
		// An offset for the current value
		double cv$offset = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		
		// Make sure the offset is not 0
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		
		// The proposed new value for the sample
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		
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
			int cv$currentValue;
			if((cv$valuePos == 0))
				// Set the current value to the current state of the tree.
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				
				// Update Sample and intermediate values
				{
					// Write out the value of the sample to a temporary variable prior to updating the
					// intermediate variables.
					int var109 = cv$proposedValue;
					
					// Guards to ensure that arrivals is only updated when there is a valid path.
					{
						if((0 < numTimeSteps)) {
							{
								// Reduction of array null
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								int reduceVar$numSales$11 = 0;
								
								// For each index in the array to be reduced
								for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									int k$var98 = reduceVar$numSales$11;
									
									// Set the right hand term to a value from the array var92
									int l$var99 = ObsSales[t][cv$reduction100Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$numSales$11 = (k$var98 + l$var99);
								}
								
								// Write out the new sample value.
								arrivals[((t - 0) / 1)] = (reduceVar$numSales$11 + cv$currentValue);
							}
						}
					}
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$lambda;
				{
					cv$temp$0$lambda = lambda[((t - 0) / 1)];
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$currentValue, cv$temp$0$lambda));
				
				// Processing random variable 157.
				{
					{
						if((0 < numTimeSteps)) {
							int traceTempVariable$arrivals$2_1 = arrivals[((t - 0) / 1)];
							
							// Processing sample task 166 of consumer random variable null.
							{
								if((0 < numTimeSteps)) {
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
													if((0 < numTimeSteps)) {
														double[] cv$temp$1$weekly_rates;
														{
															cv$temp$1$weekly_rates = weekly_rates[((t - 0) / 1)];
														}
														int cv$temp$2$$var1684;
														{
															// Constructing a random variable input for use later.
															int $var1684 = (avail[0].length + 1);
															cv$temp$2$$var1684 = $var1684;
														}
														int cv$temp$3$arrivals;
														{
															cv$temp$3$arrivals = traceTempVariable$arrivals$2_1;
														}
														
														// Record the probability of sample task 166 generating output with current configuration.
														if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var1684, cv$temp$3$arrivals)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var1684, cv$temp$3$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															// If the second value is -infinity.
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var1684, cv$temp$3$arrivals));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var1684, cv$temp$3$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var1684, cv$temp$3$arrivals)));
														}
														
														// Recorded the probability of reaching sample task 166 with the current configuration.
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
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			int var109 = cv$originalValue;
			
			// Guards to ensure that arrivals is only updated when there is a valid path.
			{
				if((0 < numTimeSteps)) {
					{
						// Reduction of array null
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						int reduceVar$numSales$12 = 0;
						
						// For each index in the array to be reduced
						for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							int k$var98 = reduceVar$numSales$12;
							
							// Set the right hand term to a value from the array var92
							int l$var99 = ObsSales[t][cv$reduction100Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$numSales$12 = (k$var98 + l$var99);
						}
						
						// Write out the new sample value.
						arrivals[((t - 0) / 1)] = (reduceVar$numSales$12 + var109);
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 32 drawn from Gaussian 19. Inference was performed using Metropolis-Hastings.
	private final void sample32(int var29) {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		{
			// Metropolis-Hastings
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		
		// The original value of the sample
		double cv$originalValue = ut[var29];
		
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
					// Write out the value of the sample to a temporary variable prior to updating the
					// intermediate variables.
					double var30 = cv$proposedValue;
					
					// Guards to ensure that ut is only updated when there is a valid path.
					{
						if((0 < numTimeSteps)) {
							{
								ut[var29] = cv$currentValue;
							}
						}
					}
					
					// Guards to ensure that exped is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 32 and consumer double[] 44.
					{
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									{
										exped[j$var41] = Math.exp(ut[j$var41]);
									}
								}
							}
						}
					}
					
					// Guards to ensure that expedNorm is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 32 and consumer double[] 70.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[] guard$sample32put73 = guard$sample32put73$global;
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1)
												// Set the flags to false
												guard$sample32put73[((j$var66 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66))
											// Set the flags to false
											guard$sample32put73[((j$var66 - 0) / 1)] = false;
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												if(!guard$sample32put73[((j$var66 - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample32put73[((j$var66 - 0) / 1)] = true;
													{
														// Reduction of array exped
														// 
														// A generated name to prevent name collisions if the reduction is implemented more
														// than once in inference and probability code. Initialize the variable to the unit
														// value
														double reduceVar$sum$13 = 0.0;
														
														// For each index in the array to be reduced
														for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k$var54 = reduceVar$sum$13;
															
															// Set the right hand term to a value from the array exped
															double l$var55 = exped[cv$reduction54Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$sum$13 = (k$var54 + l$var55);
														}
														expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$13));
													}
												}
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											if(!guard$sample32put73[((j$var66 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample32put73[((j$var66 - 0) / 1)] = true;
												{
													// Reduction of array exped
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													double reduceVar$sum$14 = 0.0;
													
													// For each index in the array to be reduced
													for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k$var54 = reduceVar$sum$14;
														
														// Set the right hand term to a value from the array exped
														double l$var55 = exped[cv$reduction54Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$14 = (k$var54 + l$var55);
													}
													expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$14));
												}
											}
										}
									}
								}
							}
						}
					}
					
					// Guards to ensure that weekly_ut is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 32 and consumer double[] 128.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample32put135 = guard$sample32put135$global;
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121))
																// Set the flags to false
																guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = false;
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121))
															// Set the flags to false
															guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = false;
													}
												}
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																if(!guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)]) {
																	// The body will execute, so should not be executed again
																	guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = true;
																	{
																		weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															if(!guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)]) {
																// The body will execute, so should not be executed again
																guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = true;
																{
																	weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
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
					
					// Guards to ensure that weekly_rates is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 32 and consumer double[] 156.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample32put164 = guard$sample32put164$global;
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																	{
																		for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1)
																			// Set the flags to false
																			guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																	if((j$var121 == j$var153))
																		// Set the flags to false
																		guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																{
																	for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1)
																		// Set the flags to false
																		guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																if((j$var121 == j$var153))
																	// Set the flags to false
																	guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																	{
																		for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																			if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																				// The body will execute, so should not be executed again
																				guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																				{
																					// Reduction of array weekly_ut
																					// 
																					// A generated name to prevent name collisions if the reduction is implemented more
																					// than once in inference and probability code. Initialize the variable to the unit
																					// value
																					double reduceVar$denom$15 = 0.0;
																					
																					// For each index in the array to be reduced
																					for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																						// Set the left hand term of the reduction function to the return variable value.
																						double k$var139 = reduceVar$denom$15;
																						
																						// Set the right hand term to a value from the array weekly_ut
																						double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																						
																						// Execute the reduction function, saving the result into the return value.
																						// 
																						// Copy the result of the reduction into the variable returned by the reduction.
																						reduceVar$denom$15 = (k$var139 + l$var140);
																					}
																					weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$15);
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																	if((j$var121 == j$var153)) {
																		if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																			{
																				// Reduction of array weekly_ut
																				// 
																				// A generated name to prevent name collisions if the reduction is implemented more
																				// than once in inference and probability code. Initialize the variable to the unit
																				// value
																				double reduceVar$denom$16 = 0.0;
																				
																				// For each index in the array to be reduced
																				for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																					// Set the left hand term of the reduction function to the return variable value.
																					double k$var139 = reduceVar$denom$16;
																					
																					// Set the right hand term to a value from the array weekly_ut
																					double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																					
																					// Execute the reduction function, saving the result into the return value.
																					// 
																					// Copy the result of the reduction into the variable returned by the reduction.
																					reduceVar$denom$16 = (k$var139 + l$var140);
																				}
																				weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$16);
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																{
																	for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																		if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																			{
																				// Reduction of array weekly_ut
																				// 
																				// A generated name to prevent name collisions if the reduction is implemented more
																				// than once in inference and probability code. Initialize the variable to the unit
																				// value
																				double reduceVar$denom$17 = 0.0;
																				
																				// For each index in the array to be reduced
																				for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																					// Set the left hand term of the reduction function to the return variable value.
																					double k$var139 = reduceVar$denom$17;
																					
																					// Set the right hand term to a value from the array weekly_ut
																					double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																					
																					// Execute the reduction function, saving the result into the return value.
																					// 
																					// Copy the result of the reduction into the variable returned by the reduction.
																					reduceVar$denom$17 = (k$var139 + l$var140);
																				}
																				weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$17);
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																if((j$var121 == j$var153)) {
																	if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																		// The body will execute, so should not be executed again
																		guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																		{
																			// Reduction of array weekly_ut
																			// 
																			// A generated name to prevent name collisions if the reduction is implemented more
																			// than once in inference and probability code. Initialize the variable to the unit
																			// value
																			double reduceVar$denom$18 = 0.0;
																			
																			// For each index in the array to be reduced
																			for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																				// Set the left hand term of the reduction function to the return variable value.
																				double k$var139 = reduceVar$denom$18;
																				
																				// Set the right hand term to a value from the array weekly_ut
																				double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																				
																				// Execute the reduction function, saving the result into the return value.
																				// 
																				// Copy the result of the reduction into the variable returned by the reduction.
																				reduceVar$denom$18 = (k$var139 + l$var140);
																			}
																			weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$18);
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
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var17;
				{
					cv$temp$0$var17 = 0.0;
				}
				double cv$temp$1$var18;
				{
					cv$temp$1$var18 = 1.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var17) / Math.sqrt(cv$temp$1$var18))) - (0.5 * Math.log(cv$temp$1$var18))));
				
				// Processing random variable 157.
				{
					// Looking for a path between Sample 32 and consumer Multinomial 157.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[] guard$sample32multinomial165 = guard$sample32multinomial165$global;
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																	{
																		// Set the flags to false
																		guard$sample32multinomial165[((t - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																	if((j$var121 == j$var153))
																		// Set the flags to false
																		guard$sample32multinomial165[((t - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																{
																	// Set the flags to false
																	guard$sample32multinomial165[((t - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																if((j$var121 == j$var153))
																	// Set the flags to false
																	guard$sample32multinomial165[((t - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							double traceTempVariable$var42$23_1 = cv$currentValue;
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									double traceTempVariable$k$23_3 = Math.exp(traceTempVariable$var42$23_1);
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											if((0 < avail[0].length)) {
												// Reduction of array exped
												// 
												// A generated name to prevent name collisions if the reduction is implemented more
												// than once in inference and probability code. Initialize the variable to the unit
												// value
												double reduceVar$sum$15 = 0.0;
												
												// Reduce for every value except a masked value which will be skipped.
												for(int cv$reduction2553Index = 0; cv$reduction2553Index < j$var41; cv$reduction2553Index += 1) {
													// Set the left hand term of the reduction function to the return variable value.
													double k$var54 = reduceVar$sum$15;
													
													// Set the right hand term to a value from the array exped
													double l$var55 = exped[cv$reduction2553Index];
													
													// Execute the reduction function, saving the result into the return value.
													// 
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$15 = (k$var54 + l$var55);
												}
												for(int cv$reduction2553Index = (j$var41 + 1); cv$reduction2553Index < avail[0].length; cv$reduction2553Index += 1) {
													// Set the left hand term of the reduction function to the return variable value.
													double k$var54 = reduceVar$sum$15;
													
													// Set the right hand term to a value from the array exped
													double l$var55 = exped[cv$reduction2553Index];
													
													// Execute the reduction function, saving the result into the return value.
													// 
													// Execute the reduction function, saving the result into the return value.
													// 
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$15 = (k$var54 + l$var55);
												}
												double cv$reduced54 = reduceVar$sum$15;
												
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$15 = (traceTempVariable$k$23_3 + cv$reduced54);
												double traceTempVariable$sum$23_4 = reduceVar$sum$15;
												for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
													double traceTempVariable$var127$23_6 = (exped[j$var66] / (r * traceTempVariable$sum$23_4));
													for(int t = 0; t < numTimeSteps; t += 1) {
														for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
															if(avail[t][j$var121]) {
																if((j$var66 == j$var121)) {
																	double traceTempVariable$k$23_9 = traceTempVariable$var127$23_6;
																	if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																		{
																			if((0 < (avail[0].length + 1))) {
																				// Reduction of array weekly_ut
																				// 
																				// A generated name to prevent name collisions if the reduction is implemented more
																				// than once in inference and probability code. Initialize the variable to the unit
																				// value
																				double reduceVar$denom$19 = 0.0;
																				
																				// Reduce for every value except a masked value which will be skipped.
																				for(int cv$reduction2574Index = 0; cv$reduction2574Index < j$var121; cv$reduction2574Index += 1) {
																					// Set the left hand term of the reduction function to the return variable value.
																					double k$var139 = reduceVar$denom$19;
																					
																					// Set the right hand term to a value from the array weekly_ut
																					double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction2574Index];
																					
																					// Execute the reduction function, saving the result into the return value.
																					// 
																					// Copy the result of the reduction into the variable returned by the reduction.
																					reduceVar$denom$19 = (k$var139 + l$var140);
																				}
																				for(int cv$reduction2574Index = (j$var121 + 1); cv$reduction2574Index < (avail[0].length + 1); cv$reduction2574Index += 1) {
																					// Set the left hand term of the reduction function to the return variable value.
																					double k$var139 = reduceVar$denom$19;
																					
																					// Set the right hand term to a value from the array weekly_ut
																					double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction2574Index];
																					
																					// Execute the reduction function, saving the result into the return value.
																					// 
																					// Execute the reduction function, saving the result into the return value.
																					// 
																					// Copy the result of the reduction into the variable returned by the reduction.
																					reduceVar$denom$19 = (k$var139 + l$var140);
																				}
																				double cv$reduced144 = reduceVar$denom$19;
																				
																				// Copy the result of the reduction into the variable returned by the reduction.
																				reduceVar$denom$19 = (traceTempVariable$k$23_9 + cv$reduced144);
																				double traceTempVariable$denom$23_10 = reduceVar$denom$19;
																				if(!guard$sample32multinomial165[((t - 0) / 1)]) {
																					// The body will execute, so should not be executed again
																					guard$sample32multinomial165[((t - 0) / 1)] = true;
																					
																					// Processing sample task 166 of consumer random variable null.
																					{
																						if((0 < numTimeSteps)) {
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
																											if((0 < numTimeSteps)) {
																												double[] cv$temp$2$weekly_rates;
																												{
																													cv$temp$2$weekly_rates = weekly_rates[((t - 0) / 1)];
																												}
																												int cv$temp$3$$var1457;
																												{
																													// Constructing a random variable input for use later.
																													int $var1457 = (avail[0].length + 1);
																													cv$temp$3$$var1457 = $var1457;
																												}
																												int cv$temp$4$arrivals;
																												{
																													cv$temp$4$arrivals = arrivals[((t - 0) / 1)];
																												}
																												
																												// Record the probability of sample task 166 generating output with current configuration.
																												if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var1457, cv$temp$4$arrivals)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var1457, cv$temp$4$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var1457, cv$temp$4$arrivals));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var1457, cv$temp$4$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var1457, cv$temp$4$arrivals)));
																												}
																												
																												// Recorded the probability of reaching sample task 166 with the current configuration.
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
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							double traceTempVariable$var42$24_1 = cv$currentValue;
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									double traceTempVariable$k$24_3 = Math.exp(traceTempVariable$var42$24_1);
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											if((0 < avail[0].length)) {
												// Reduction of array exped
												// 
												// A generated name to prevent name collisions if the reduction is implemented more
												// than once in inference and probability code. Initialize the variable to the unit
												// value
												double reduceVar$sum$16 = 0.0;
												
												// Reduce for every value except a masked value which will be skipped.
												for(int cv$reduction2599Index = 0; cv$reduction2599Index < j$var41; cv$reduction2599Index += 1) {
													// Set the left hand term of the reduction function to the return variable value.
													double k$var54 = reduceVar$sum$16;
													
													// Set the right hand term to a value from the array exped
													double l$var55 = exped[cv$reduction2599Index];
													
													// Execute the reduction function, saving the result into the return value.
													// 
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$16 = (k$var54 + l$var55);
												}
												for(int cv$reduction2599Index = (j$var41 + 1); cv$reduction2599Index < avail[0].length; cv$reduction2599Index += 1) {
													// Set the left hand term of the reduction function to the return variable value.
													double k$var54 = reduceVar$sum$16;
													
													// Set the right hand term to a value from the array exped
													double l$var55 = exped[cv$reduction2599Index];
													
													// Execute the reduction function, saving the result into the return value.
													// 
													// Execute the reduction function, saving the result into the return value.
													// 
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$16 = (k$var54 + l$var55);
												}
												double cv$reduced54 = reduceVar$sum$16;
												
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$16 = (traceTempVariable$k$24_3 + cv$reduced54);
												double traceTempVariable$sum$24_4 = reduceVar$sum$16;
												for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
													double traceTempVariable$var127$24_6 = (exped[j$var66] / (r * traceTempVariable$sum$24_4));
													for(int t = 0; t < numTimeSteps; t += 1) {
														for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
															if(avail[t][j$var121]) {
																if((j$var66 == j$var121)) {
																	double traceTempVariable$var154$24_9 = traceTempVariable$var127$24_6;
																	for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																		if((j$var121 == j$var153)) {
																			if(!guard$sample32multinomial165[((t - 0) / 1)]) {
																				// The body will execute, so should not be executed again
																				guard$sample32multinomial165[((t - 0) / 1)] = true;
																				
																				// Processing sample task 166 of consumer random variable null.
																				{
																					if((0 < numTimeSteps)) {
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
																										if((0 < numTimeSteps)) {
																											double[] cv$temp$5$weekly_rates;
																											{
																												cv$temp$5$weekly_rates = weekly_rates[((t - 0) / 1)];
																											}
																											int cv$temp$6$$var1464;
																											{
																												// Constructing a random variable input for use later.
																												int $var1464 = (avail[0].length + 1);
																												cv$temp$6$$var1464 = $var1464;
																											}
																											int cv$temp$7$arrivals;
																											{
																												cv$temp$7$arrivals = arrivals[((t - 0) / 1)];
																											}
																											
																											// Record the probability of sample task 166 generating output with current configuration.
																											if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var1464, cv$temp$7$arrivals)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var1464, cv$temp$7$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var1464, cv$temp$7$arrivals));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var1464, cv$temp$7$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var1464, cv$temp$7$arrivals)));
																											}
																											
																											// Recorded the probability of reaching sample task 166 with the current configuration.
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
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							double traceTempVariable$var42$25_1 = cv$currentValue;
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									double traceTempVariable$var67$25_3 = Math.exp(traceTempVariable$var42$25_1);
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$17 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k$var54 = reduceVar$sum$17;
												
												// Set the right hand term to a value from the array exped
												double l$var55 = exped[cv$reduction54Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$17 = (k$var54 + l$var55);
											}
											double traceTempVariable$var127$25_5 = (traceTempVariable$var67$25_3 / (r * reduceVar$sum$17));
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															double traceTempVariable$k$25_8 = traceTempVariable$var127$25_5;
															if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																{
																	if((0 < (avail[0].length + 1))) {
																		// Reduction of array weekly_ut
																		// 
																		// A generated name to prevent name collisions if the reduction is implemented more
																		// than once in inference and probability code. Initialize the variable to the unit
																		// value
																		double reduceVar$denom$20 = 0.0;
																		
																		// Reduce for every value except a masked value which will be skipped.
																		for(int cv$reduction2645Index = 0; cv$reduction2645Index < j$var121; cv$reduction2645Index += 1) {
																			// Set the left hand term of the reduction function to the return variable value.
																			double k$var139 = reduceVar$denom$20;
																			
																			// Set the right hand term to a value from the array weekly_ut
																			double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction2645Index];
																			
																			// Execute the reduction function, saving the result into the return value.
																			// 
																			// Copy the result of the reduction into the variable returned by the reduction.
																			reduceVar$denom$20 = (k$var139 + l$var140);
																		}
																		for(int cv$reduction2645Index = (j$var121 + 1); cv$reduction2645Index < (avail[0].length + 1); cv$reduction2645Index += 1) {
																			// Set the left hand term of the reduction function to the return variable value.
																			double k$var139 = reduceVar$denom$20;
																			
																			// Set the right hand term to a value from the array weekly_ut
																			double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction2645Index];
																			
																			// Execute the reduction function, saving the result into the return value.
																			// 
																			// Execute the reduction function, saving the result into the return value.
																			// 
																			// Copy the result of the reduction into the variable returned by the reduction.
																			reduceVar$denom$20 = (k$var139 + l$var140);
																		}
																		double cv$reduced144 = reduceVar$denom$20;
																		
																		// Copy the result of the reduction into the variable returned by the reduction.
																		reduceVar$denom$20 = (traceTempVariable$k$25_8 + cv$reduced144);
																		double traceTempVariable$denom$25_9 = reduceVar$denom$20;
																		if(!guard$sample32multinomial165[((t - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample32multinomial165[((t - 0) / 1)] = true;
																			
																			// Processing sample task 166 of consumer random variable null.
																			{
																				if((0 < numTimeSteps)) {
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
																									if((0 < numTimeSteps)) {
																										double[] cv$temp$8$weekly_rates;
																										{
																											cv$temp$8$weekly_rates = weekly_rates[((t - 0) / 1)];
																										}
																										int cv$temp$9$$var1471;
																										{
																											// Constructing a random variable input for use later.
																											int $var1471 = (avail[0].length + 1);
																											cv$temp$9$$var1471 = $var1471;
																										}
																										int cv$temp$10$arrivals;
																										{
																											cv$temp$10$arrivals = arrivals[((t - 0) / 1)];
																										}
																										
																										// Record the probability of sample task 166 generating output with current configuration.
																										if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$$var1471, cv$temp$10$arrivals)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$$var1471, cv$temp$10$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											// If the second value is -infinity.
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$$var1471, cv$temp$10$arrivals));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$$var1471, cv$temp$10$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$$var1471, cv$temp$10$arrivals)));
																										}
																										
																										// Recorded the probability of reaching sample task 166 with the current configuration.
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
								}
							}
						}
						if((0 < numTimeSteps)) {
							double traceTempVariable$var42$26_1 = cv$currentValue;
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									double traceTempVariable$var67$26_3 = Math.exp(traceTempVariable$var42$26_1);
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$18 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k$var54 = reduceVar$sum$18;
												
												// Set the right hand term to a value from the array exped
												double l$var55 = exped[cv$reduction54Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$18 = (k$var54 + l$var55);
											}
											double traceTempVariable$var127$26_5 = (traceTempVariable$var67$26_3 / (r * reduceVar$sum$18));
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															double traceTempVariable$var154$26_8 = traceTempVariable$var127$26_5;
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																if((j$var121 == j$var153)) {
																	if(!guard$sample32multinomial165[((t - 0) / 1)]) {
																		// The body will execute, so should not be executed again
																		guard$sample32multinomial165[((t - 0) / 1)] = true;
																		
																		// Processing sample task 166 of consumer random variable null.
																		{
																			if((0 < numTimeSteps)) {
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
																								if((0 < numTimeSteps)) {
																									double[] cv$temp$11$weekly_rates;
																									{
																										cv$temp$11$weekly_rates = weekly_rates[((t - 0) / 1)];
																									}
																									int cv$temp$12$$var1478;
																									{
																										// Constructing a random variable input for use later.
																										int $var1478 = (avail[0].length + 1);
																										cv$temp$12$$var1478 = $var1478;
																									}
																									int cv$temp$13$arrivals;
																									{
																										cv$temp$13$arrivals = arrivals[((t - 0) / 1)];
																									}
																									
																									// Record the probability of sample task 166 generating output with current configuration.
																									if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$11$weekly_rates, cv$temp$12$$var1478, cv$temp$13$arrivals)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$11$weekly_rates, cv$temp$12$$var1478, cv$temp$13$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$11$weekly_rates, cv$temp$12$$var1478, cv$temp$13$arrivals));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$11$weekly_rates, cv$temp$12$$var1478, cv$temp$13$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$11$weekly_rates, cv$temp$12$$var1478, cv$temp$13$arrivals)));
																									}
																									
																									// Recorded the probability of reaching sample task 166 with the current configuration.
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
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			double var30 = cv$originalValue;
			
			// Guards to ensure that ut is only updated when there is a valid path.
			{
				if((0 < numTimeSteps)) {
					{
						ut[var29] = var30;
					}
				}
			}
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 32 and consumer double[] 44.
			{
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							{
								exped[j$var41] = Math.exp(ut[j$var41]);
							}
						}
					}
				}
			}
			
			// Guards to ensure that expedNorm is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 32 and consumer double[] 70.
			{
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[] guard$sample32put73 = guard$sample32put73$global;
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1)
										// Set the flags to false
										guard$sample32put73[((j$var66 - 0) / 1)] = false;
								}
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66))
									// Set the flags to false
									guard$sample32put73[((j$var66 - 0) / 1)] = false;
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if(!guard$sample32put73[((j$var66 - 0) / 1)]) {
											// The body will execute, so should not be executed again
											guard$sample32put73[((j$var66 - 0) / 1)] = true;
											{
												// Reduction of array exped
												// 
												// A generated name to prevent name collisions if the reduction is implemented more
												// than once in inference and probability code. Initialize the variable to the unit
												// value
												double reduceVar$sum$19 = 0.0;
												
												// For each index in the array to be reduced
												for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
													// Set the left hand term of the reduction function to the return variable value.
													double k$var54 = reduceVar$sum$19;
													
													// Set the right hand term to a value from the array exped
													double l$var55 = exped[cv$reduction54Index];
													
													// Execute the reduction function, saving the result into the return value.
													// 
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$19 = (k$var54 + l$var55);
												}
												expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$19));
											}
										}
									}
								}
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66)) {
									if(!guard$sample32put73[((j$var66 - 0) / 1)]) {
										// The body will execute, so should not be executed again
										guard$sample32put73[((j$var66 - 0) / 1)] = true;
										{
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$20 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k$var54 = reduceVar$sum$20;
												
												// Set the right hand term to a value from the array exped
												double l$var55 = exped[cv$reduction54Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$20 = (k$var54 + l$var55);
											}
											expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$20));
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Guards to ensure that weekly_ut is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 32 and consumer double[] 128.
			{
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[][] guard$sample32put135 = guard$sample32put135$global;
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
												if(avail[t][j$var121]) {
													if((j$var66 == j$var121))
														// Set the flags to false
														guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if((j$var66 == j$var121))
													// Set the flags to false
													guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
												if(avail[t][j$var121]) {
													if((j$var66 == j$var121)) {
														if(!guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = true;
															{
																weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
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
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if((j$var66 == j$var121)) {
													if(!guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = true;
														{
															weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
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
			
			// Guards to ensure that weekly_rates is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 32 and consumer double[] 156.
			{
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[][] guard$sample32put164 = guard$sample32put164$global;
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
												if(avail[t][j$var121]) {
													if((j$var66 == j$var121)) {
														if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
															{
																for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1)
																	// Set the flags to false
																	guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
												if(avail[t][j$var121]) {
													if((j$var66 == j$var121)) {
														for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
															if((j$var121 == j$var153))
																// Set the flags to false
																guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if((j$var66 == j$var121)) {
													if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
														{
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1)
																// Set the flags to false
																guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if((j$var66 == j$var121)) {
													for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
														if((j$var121 == j$var153))
															// Set the flags to false
															guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
												if(avail[t][j$var121]) {
													if((j$var66 == j$var121)) {
														if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
															{
																for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																	if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																		// The body will execute, so should not be executed again
																		guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																		{
																			// Reduction of array weekly_ut
																			// 
																			// A generated name to prevent name collisions if the reduction is implemented more
																			// than once in inference and probability code. Initialize the variable to the unit
																			// value
																			double reduceVar$denom$21 = 0.0;
																			
																			// For each index in the array to be reduced
																			for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																				// Set the left hand term of the reduction function to the return variable value.
																				double k$var139 = reduceVar$denom$21;
																				
																				// Set the right hand term to a value from the array weekly_ut
																				double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																				
																				// Execute the reduction function, saving the result into the return value.
																				// 
																				// Copy the result of the reduction into the variable returned by the reduction.
																				reduceVar$denom$21 = (k$var139 + l$var140);
																			}
																			weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$21);
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
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
												if(avail[t][j$var121]) {
													if((j$var66 == j$var121)) {
														for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
															if((j$var121 == j$var153)) {
																if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																	// The body will execute, so should not be executed again
																	guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																	{
																		// Reduction of array weekly_ut
																		// 
																		// A generated name to prevent name collisions if the reduction is implemented more
																		// than once in inference and probability code. Initialize the variable to the unit
																		// value
																		double reduceVar$denom$22 = 0.0;
																		
																		// For each index in the array to be reduced
																		for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																			// Set the left hand term of the reduction function to the return variable value.
																			double k$var139 = reduceVar$denom$22;
																			
																			// Set the right hand term to a value from the array weekly_ut
																			double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																			
																			// Execute the reduction function, saving the result into the return value.
																			// 
																			// Copy the result of the reduction into the variable returned by the reduction.
																			reduceVar$denom$22 = (k$var139 + l$var140);
																		}
																		weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$22);
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
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if((j$var66 == j$var121)) {
													if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
														{
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																	// The body will execute, so should not be executed again
																	guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																	{
																		// Reduction of array weekly_ut
																		// 
																		// A generated name to prevent name collisions if the reduction is implemented more
																		// than once in inference and probability code. Initialize the variable to the unit
																		// value
																		double reduceVar$denom$23 = 0.0;
																		
																		// For each index in the array to be reduced
																		for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																			// Set the left hand term of the reduction function to the return variable value.
																			double k$var139 = reduceVar$denom$23;
																			
																			// Set the right hand term to a value from the array weekly_ut
																			double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																			
																			// Execute the reduction function, saving the result into the return value.
																			// 
																			// Copy the result of the reduction into the variable returned by the reduction.
																			reduceVar$denom$23 = (k$var139 + l$var140);
																		}
																		weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$23);
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
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if((j$var66 == j$var121)) {
													for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
														if((j$var121 == j$var153)) {
															if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																// The body will execute, so should not be executed again
																guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																{
																	// Reduction of array weekly_ut
																	// 
																	// A generated name to prevent name collisions if the reduction is implemented more
																	// than once in inference and probability code. Initialize the variable to the unit
																	// value
																	double reduceVar$denom$24 = 0.0;
																	
																	// For each index in the array to be reduced
																	for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																		// Set the left hand term of the reduction function to the return variable value.
																		double k$var139 = reduceVar$denom$24;
																		
																		// Set the right hand term to a value from the array weekly_ut
																		double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																		
																		// Execute the reduction function, saving the result into the return value.
																		// 
																		// Copy the result of the reduction into the variable returned by the reduction.
																		reduceVar$denom$24 = (k$var139 + l$var140);
																	}
																	weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$24);
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
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for guard$sample32put73$global
		{
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var66 = 0;
			if((0 < avail.length))
				cv$max_j$var66 = Math.max(cv$max_j$var66, ((avail[0].length - 0) / 1));
			
			// Allocation of guard$sample32put73$global for single threaded execution
			guard$sample32put73$global = new boolean[cv$max_j$var66];
		}
		
		// Constructor for guard$sample32put135$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var121 = 0;
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					cv$max_j$var121 = Math.max(cv$max_j$var121, ((avail[0].length - 0) / 1));
				cv$max_t = Math.max(cv$max_t, ((avail.length - 0) / 1));
			}
			
			// Allocation of guard$sample32put135$global for single threaded execution
			guard$sample32put135$global = new boolean[cv$max_t][cv$max_j$var121];
		}
		
		// Constructor for guard$sample32put164$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var153 = 0;
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					cv$max_j$var153 = Math.max(cv$max_j$var153, (((avail[0].length + 1) - 0) / 1));
				cv$max_t = Math.max(cv$max_t, ((avail.length - 0) / 1));
			}
			
			// Allocation of guard$sample32put164$global for single threaded execution
			guard$sample32put164$global = new boolean[cv$max_t][cv$max_j$var153];
		}
		
		// Constructor for guard$sample32multinomial165$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t = 0;
			if((0 < avail.length))
				cv$max_t = Math.max(cv$max_t, ((avail.length - 0) / 1));
			
			// Allocation of guard$sample32multinomial165$global for single threaded execution
			guard$sample32multinomial165$global = new boolean[cv$max_t];
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If ut has not been set already allocate space.
		if(!fixedFlag$sample32) {
			// Constructor for ut
			{
				if((0 < numTimeSteps))
					ut = new double[avail[0].length];
			}
		}
		
		// Constructor for exped
		{
			if((0 < numTimeSteps))
				exped = new double[avail[0].length];
		}
		
		// Constructor for expedNorm
		{
			if((0 < numTimeSteps))
				expedNorm = new double[avail[0].length];
		}
		
		// Constructor for sales
		{
			if((0 < numTimeSteps)) {
				sales = new int[avail.length][];
				for(int var80 = 0; var80 < avail.length; var80 += 1)
					sales[var80] = new int[avail[0].length];
			}
		}
		
		// If lambda has not been set already allocate space.
		if(!fixedFlag$sample112) {
			// Constructor for lambda
			{
				lambda = new double[((((avail.length - 1) - 0) / 1) + 1)];
			}
		}
		
		// If arrivals has not been set already allocate space.
		if(!fixedFlag$sample114) {
			// Constructor for arrivals
			{
				arrivals = new int[((((avail.length - 1) - 0) / 1) + 1)];
			}
		}
		
		// Constructor for weekly_rates
		{
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					weekly_rates[((t - 0) / 1)] = new double[(avail[0].length + 1)];
			}
			weekly_rates = new double[((((avail.length - 1) - 0) / 1) + 1)][];
		}
		
		// Constructor for weekly_ut
		{
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					weekly_ut[((t - 0) / 1)] = new double[(avail[0].length + 1)];
			}
			weekly_ut = new double[((((avail.length - 1) - 0) / 1) + 1)][];
		}
		
		// Constructor for weekly_sales
		{
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					weekly_sales[((t - 0) / 1)] = new int[(avail[0].length + 1)];
			}
			weekly_sales = new int[((((avail.length - 1) - 0) / 1) + 1)][];
		}
		
		// Constructor for logProbability$sample32
		{
			logProbability$sample32 = new double[((((avail[0].length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var106
		{
			logProbability$var106 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample112
		{
			logProbability$sample112 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var108
		{
			logProbability$var108 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample114
		{
			logProbability$sample114 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var157
		{
			logProbability$var157 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample166
		{
			logProbability$sample166 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if((0 < numTimeSteps)) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!fixedFlag$sample32)
								ut[var29] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var41, int forEnd$j$var41, int threadID$j$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var41 = forStart$j$var41; j$var41 < forEnd$j$var41; j$var41 += 1) {
							if(!fixedFlag$sample32)
								exped[j$var41] = Math.exp(ut[j$var41]);
						}
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$21 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var54 = reduceVar$sum$21;
				
				// Set the right hand term to a value from the array exped
				double l$var55 = exped[cv$reduction54Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample32)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$sum$21 = (k$var54 + l$var55);
			}
			
			// Alternative name for reduceVar$sum$21 to make it effectively final.
			double reduceVar$sum$21$1 = reduceVar$sum$21;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var66, int forEnd$j$var66, int threadID$j$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var66 = forStart$j$var66; j$var66 < forEnd$j$var66; j$var66 += 1) {
							if(!fixedFlag$sample32)
								expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$21$1));
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample112)
								lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							
							// Reduction of array null
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							int reduceVar$numSales$14 = 0;
							
							// For each index in the array to be reduced
							for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
								// Set the left hand term of the reduction function to the return variable value.
								int k$var98 = reduceVar$numSales$14;
								
								// Set the right hand term to a value from the array var92
								int l$var99 = ObsSales[t][cv$reduction100Index];
								
								// Execute the reduction function, saving the result into the return value.
								if(!fixedFlag$sample114)
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$numSales$14 = (k$var98 + l$var99);
							}
							if(!fixedFlag$sample114)
								arrivals[((t - 0) / 1)] = (reduceVar$numSales$14 + DistributionSampling.samplePoisson(RNG$1, lambda[((t - 0) / 1)]));
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var121, int forEnd$j$var121, int threadID$j$var121, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var121 = forStart$j$var121; j$var121 < forEnd$j$var121; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if(!fixedFlag$sample32)
													weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
											} else
												weekly_ut[((t - 0) / 1)][j$var121] = 0.0;
										}
								}
							);
							weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$25 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
								// Set the left hand term of the reduction function to the return variable value.
								double k$var139 = reduceVar$denom$25;
								
								// Set the right hand term to a value from the array weekly_ut
								double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
								
								// Execute the reduction function, saving the result into the return value.
								if(!fixedFlag$sample32)
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$denom$25 = (k$var139 + l$var140);
							}
							
							// Alternative name for reduceVar$denom$25 to make it effectively final.
							double reduceVar$denom$25$2 = reduceVar$denom$25;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
								(int forStart$j$var153, int forEnd$j$var153, int threadID$j$var153, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var153 = forStart$j$var153; j$var153 < forEnd$j$var153; j$var153 += 1) {
											if(!fixedFlag$sample32)
												weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$25$2);
										}
								}
							);
							DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[((t - 0) / 1)], (avail[0].length + 1), arrivals[((t - 0) / 1)], weekly_sales[((t - 0) / 1)]);
							int[] observed_weekly_sales = sales[t];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var168, int forEnd$j$var168, int threadID$j$var168, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var168 = forStart$j$var168; j$var168 < forEnd$j$var168; j$var168 += 1)
											observed_weekly_sales[j$var168] = weekly_sales[((t - 0) / 1)][j$var168];
								}
							);
						}
				}
			);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if((0 < numTimeSteps)) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!fixedFlag$sample32)
								ut[var29] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var41, int forEnd$j$var41, int threadID$j$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var41 = forStart$j$var41; j$var41 < forEnd$j$var41; j$var41 += 1) {
							if(!fixedFlag$sample32)
								exped[j$var41] = Math.exp(ut[j$var41]);
						}
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$23 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var54 = reduceVar$sum$23;
				
				// Set the right hand term to a value from the array exped
				double l$var55 = exped[cv$reduction54Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample32)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$sum$23 = (k$var54 + l$var55);
			}
			
			// Alternative name for reduceVar$sum$23 to make it effectively final.
			double reduceVar$sum$23$1 = reduceVar$sum$23;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var66, int forEnd$j$var66, int threadID$j$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var66 = forStart$j$var66; j$var66 < forEnd$j$var66; j$var66 += 1) {
							if(!fixedFlag$sample32)
								expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$23$1));
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample112)
								lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							
							// Reduction of array null
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							int reduceVar$numSales$16 = 0;
							
							// For each index in the array to be reduced
							for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
								// Set the left hand term of the reduction function to the return variable value.
								int k$var98 = reduceVar$numSales$16;
								
								// Set the right hand term to a value from the array var92
								int l$var99 = ObsSales[t][cv$reduction100Index];
								
								// Execute the reduction function, saving the result into the return value.
								if(!fixedFlag$sample114)
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$numSales$16 = (k$var98 + l$var99);
							}
							if(!fixedFlag$sample114)
								arrivals[((t - 0) / 1)] = (reduceVar$numSales$16 + DistributionSampling.samplePoisson(RNG$1, lambda[((t - 0) / 1)]));
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var121, int forEnd$j$var121, int threadID$j$var121, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var121 = forStart$j$var121; j$var121 < forEnd$j$var121; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if(!fixedFlag$sample32)
													weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
											} else
												weekly_ut[((t - 0) / 1)][j$var121] = 0.0;
										}
								}
							);
							weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$27 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
								// Set the left hand term of the reduction function to the return variable value.
								double k$var139 = reduceVar$denom$27;
								
								// Set the right hand term to a value from the array weekly_ut
								double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
								
								// Execute the reduction function, saving the result into the return value.
								if(!fixedFlag$sample32)
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$denom$27 = (k$var139 + l$var140);
							}
							
							// Alternative name for reduceVar$denom$27 to make it effectively final.
							double reduceVar$denom$27$2 = reduceVar$denom$27;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
								(int forStart$j$var153, int forEnd$j$var153, int threadID$j$var153, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var153 = forStart$j$var153; j$var153 < forEnd$j$var153; j$var153 += 1) {
											if(!fixedFlag$sample32)
												weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$27$2);
										}
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
		if((0 < numTimeSteps)) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!fixedFlag$sample32)
								ut[var29] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var41, int forEnd$j$var41, int threadID$j$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var41 = forStart$j$var41; j$var41 < forEnd$j$var41; j$var41 += 1) {
							if(!fixedFlag$sample32)
								exped[j$var41] = Math.exp(ut[j$var41]);
						}
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$22 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var54 = reduceVar$sum$22;
				
				// Set the right hand term to a value from the array exped
				double l$var55 = exped[cv$reduction54Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample32)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$sum$22 = (k$var54 + l$var55);
			}
			
			// Alternative name for reduceVar$sum$22 to make it effectively final.
			double reduceVar$sum$22$1 = reduceVar$sum$22;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var66, int forEnd$j$var66, int threadID$j$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var66 = forStart$j$var66; j$var66 < forEnd$j$var66; j$var66 += 1) {
							if(!fixedFlag$sample32)
								expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$22$1));
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample112)
								lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							
							// Reduction of array null
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							int reduceVar$numSales$15 = 0;
							
							// For each index in the array to be reduced
							for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
								// Set the left hand term of the reduction function to the return variable value.
								int k$var98 = reduceVar$numSales$15;
								
								// Set the right hand term to a value from the array var92
								int l$var99 = ObsSales[t][cv$reduction100Index];
								
								// Execute the reduction function, saving the result into the return value.
								if(!fixedFlag$sample114)
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$numSales$15 = (k$var98 + l$var99);
							}
							if(!fixedFlag$sample114)
								arrivals[((t - 0) / 1)] = (reduceVar$numSales$15 + DistributionSampling.samplePoisson(RNG$1, lambda[((t - 0) / 1)]));
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var121, int forEnd$j$var121, int threadID$j$var121, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var121 = forStart$j$var121; j$var121 < forEnd$j$var121; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if(!fixedFlag$sample32)
													weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
											} else
												weekly_ut[((t - 0) / 1)][j$var121] = 0.0;
										}
								}
							);
							weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$26 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
								// Set the left hand term of the reduction function to the return variable value.
								double k$var139 = reduceVar$denom$26;
								
								// Set the right hand term to a value from the array weekly_ut
								double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
								
								// Execute the reduction function, saving the result into the return value.
								if(!fixedFlag$sample32)
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$denom$26 = (k$var139 + l$var140);
							}
							
							// Alternative name for reduceVar$denom$26 to make it effectively final.
							double reduceVar$denom$26$2 = reduceVar$denom$26;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
								(int forStart$j$var153, int forEnd$j$var153, int threadID$j$var153, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var153 = forStart$j$var153; j$var153 < forEnd$j$var153; j$var153 += 1) {
											if(!fixedFlag$sample32)
												weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$26$2);
										}
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
		if(system$gibbsForward) {
			if((0 < numTimeSteps)) {
				for(int var29 = 0; var29 < avail[0].length; var29 += 1) {
					if(!fixedFlag$sample32)
						sample32(var29);
				}
				
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, numTimeSteps, 1,
					(int forStart$t, int forEnd$t, int threadID$t, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int t = forStart$t; t < forEnd$t; t += 1) {
								if(!fixedFlag$sample112)
									sample112(t, threadID$t, RNG$1);
								if(!fixedFlag$sample114)
									sample114(t, threadID$t, RNG$1);
							}
					}
				);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			if((0 < numTimeSteps)) {
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, numTimeSteps, 1,
					(int forStart$t, int forEnd$t, int threadID$t, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int t = forStart$t; t < forEnd$t; t += 1) {
								if(!fixedFlag$sample114)
									sample114(t, threadID$t, RNG$1);
								if(!fixedFlag$sample112)
									sample112(t, threadID$t, RNG$1);
							}
					}
				);
				for(int var29 = (avail[0].length - ((((avail[0].length - 1) - 0) % 1) + 1)); var29 >= ((0 - 1) + 1); var29 -= 1) {
					if(!fixedFlag$sample32)
						sample32(var29);
				}
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		r = 0.3;
		numTimeSteps = avail.length;
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
		logProbability$var19 = 0.0;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$expedNorm = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$weekly_rates = 0.0;
		if(!fixedProbFlag$sample32) {
			if((0 < numTimeSteps)) {
				for(int var29 = 0; var29 < avail[0].length; var29 += 1)
					logProbability$sample32[((var29 - 0) / 1)] = 0.0;
			}
		}
		if((0 < numTimeSteps)) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var106[((t - 0) / 1)] = 0.0;
		}
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample112) {
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample112[((t - 0) / 1)] = 0.0;
			}
		}
		if((0 < numTimeSteps)) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var108[((t - 0) / 1)] = 0.0;
		}
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample114) {
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample114[((t - 0) / 1)] = 0.0;
			}
		}
		if((0 < numTimeSteps)) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var157[((t - 0) / 1)] = 0.0;
		}
		logProbability$weekly_sales = 0.0;
		logProbability$sales = 0.0;
		if(!fixedProbFlag$sample166) {
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample166[((t - 0) / 1)] = 0.0;
			}
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
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample112)
			logProbabilityValue$sample112();
		if(fixedFlag$sample114)
			logProbabilityValue$sample114();
		logProbabilityValue$sample166();
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
		logProbabilityValue$sample32();
		logProbabilityValue$sample112();
		logProbabilityValue$sample114();
		logProbabilityValue$sample166();
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
		logProbabilityValue$sample32();
		logProbabilityValue$sample112();
		logProbabilityValue$sample114();
		logProbabilityValue$sample166();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if((0 < numTimeSteps)) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!fixedFlag$sample32)
								ut[var29] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var41, int forEnd$j$var41, int threadID$j$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var41 = forStart$j$var41; j$var41 < forEnd$j$var41; j$var41 += 1) {
							if(!fixedFlag$sample32)
								exped[j$var41] = Math.exp(ut[j$var41]);
						}
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$24 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var54 = reduceVar$sum$24;
				
				// Set the right hand term to a value from the array exped
				double l$var55 = exped[cv$reduction54Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample32)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$sum$24 = (k$var54 + l$var55);
			}
			
			// Alternative name for reduceVar$sum$24 to make it effectively final.
			double reduceVar$sum$24$1 = reduceVar$sum$24;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var66, int forEnd$j$var66, int threadID$j$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var66 = forStart$j$var66; j$var66 < forEnd$j$var66; j$var66 += 1) {
							if(!fixedFlag$sample32)
								expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$24$1));
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample112)
								lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							
							// Reduction of array null
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							int reduceVar$numSales$17 = 0;
							
							// For each index in the array to be reduced
							for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
								// Set the left hand term of the reduction function to the return variable value.
								int k$var98 = reduceVar$numSales$17;
								
								// Set the right hand term to a value from the array var92
								int l$var99 = ObsSales[t][cv$reduction100Index];
								
								// Execute the reduction function, saving the result into the return value.
								if(!fixedFlag$sample114)
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$numSales$17 = (k$var98 + l$var99);
							}
							if(!fixedFlag$sample114)
								arrivals[((t - 0) / 1)] = (reduceVar$numSales$17 + DistributionSampling.samplePoisson(RNG$1, lambda[((t - 0) / 1)]));
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var121, int forEnd$j$var121, int threadID$j$var121, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var121 = forStart$j$var121; j$var121 < forEnd$j$var121; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if(!fixedFlag$sample32)
													weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
											} else
												weekly_ut[((t - 0) / 1)][j$var121] = 0.0;
										}
								}
							);
							weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$28 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
								// Set the left hand term of the reduction function to the return variable value.
								double k$var139 = reduceVar$denom$28;
								
								// Set the right hand term to a value from the array weekly_ut
								double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
								
								// Execute the reduction function, saving the result into the return value.
								if(!fixedFlag$sample32)
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$denom$28 = (k$var139 + l$var140);
							}
							
							// Alternative name for reduceVar$denom$28 to make it effectively final.
							double reduceVar$denom$28$2 = reduceVar$denom$28;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
								(int forStart$j$var153, int forEnd$j$var153, int threadID$j$var153, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var153 = forStart$j$var153; j$var153 < forEnd$j$var153; j$var153 += 1) {
											if(!fixedFlag$sample32)
												weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$28$2);
										}
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
	public final void propagateObservedValues() {
		if((0 < numTimeSteps)) {
			{
				// Deep copy between arrays
				int[][] cv$source1 = ObsSales;
				int[][] cv$target1 = sales;
				int cv$length1 = cv$target1.length;
				for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
					int[] cv$source2 = cv$source1[cv$index1];
					int[] cv$target2 = cv$target1[cv$index1];
					int cv$length2 = cv$target2.length;
					for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
						cv$target2[cv$index2] = cv$source2[cv$index2];
				}
			}
			for(int t = (numTimeSteps - ((((numTimeSteps - 1) - 0) % 1) + 1)); t >= ((0 - 1) + 1); t -= 1) {
				if((0 < numTimeSteps)) {
					for(int j$var168 = (avail[0].length - ((((avail[0].length - 1) - 0) % 1) + 1)); j$var168 >= ((0 - 1) + 1); j$var168 -= 1) {
						if((0 < numTimeSteps)) {
							int[] observed_weekly_sales;
							observed_weekly_sales = sales[t];
							weekly_sales[((t - 0) / 1)][j$var168] = observed_weekly_sales[j$var168];
						}
					}
				}
			}
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		if((0 < numTimeSteps)) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var41, int forEnd$j$var41, int threadID$j$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var41 = forStart$j$var41; j$var41 < forEnd$j$var41; j$var41 += 1) {
							if(fixedFlag$sample32)
								exped[j$var41] = Math.exp(ut[j$var41]);
						}
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$25 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var54 = reduceVar$sum$25;
				
				// Set the right hand term to a value from the array exped
				double l$var55 = exped[cv$reduction54Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(fixedFlag$sample32)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$sum$25 = (k$var54 + l$var55);
			}
			
			// Alternative name for reduceVar$sum$25 to make it effectively final.
			double reduceVar$sum$25$1 = reduceVar$sum$25;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var66, int forEnd$j$var66, int threadID$j$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var66 = forStart$j$var66; j$var66 < forEnd$j$var66; j$var66 += 1) {
							if(fixedFlag$sample32)
								expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$25$1));
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var121, int forEnd$j$var121, int threadID$j$var121, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var121 = forStart$j$var121; j$var121 < forEnd$j$var121; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if(fixedFlag$sample32)
													weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
											} else
												weekly_ut[((t - 0) / 1)][j$var121] = 0.0;
										}
								}
							);
							weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$29 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
								// Set the left hand term of the reduction function to the return variable value.
								double k$var139 = reduceVar$denom$29;
								
								// Set the right hand term to a value from the array weekly_ut
								double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
								
								// Execute the reduction function, saving the result into the return value.
								if(fixedFlag$sample32)
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$denom$29 = (k$var139 + l$var140);
							}
							
							// Alternative name for reduceVar$denom$29 to make it effectively final.
							double reduceVar$denom$29$2 = reduceVar$denom$29;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
								(int forStart$j$var153, int forEnd$j$var153, int threadID$j$var153, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var153 = forStart$j$var153; j$var153 < forEnd$j$var153; j$var153 += 1) {
											if(fixedFlag$sample32)
												weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$29$2);
										}
								}
							);
						}
				}
			);
		}
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
		     + "/*\n"
		     + " * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n"
		     + " * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n"
		     + " * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n"
		     + " */\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model Vulcano2012basicDG(int[][] ObsSales, boolean[][] avail) {\n"
		     + "   // avail is the availability matrix, numTimeSteps-by-numProducts\n"
		     + "   // r is the normalization constant here, number between 0 and 1. \"Relative appeal of the outside option\"\n"
		     + "   double r = 0.3;\n"
		     + "    \n"
		     + "   int numTimeSteps = avail.length;\n"
		     + "   if(numTimeSteps > 0) {\n"
		     + "      int numProducts = avail[0].length;\n"
		     + "\n"
		     + "      // draw utilities\n"
		     + "      double[] ut = gaussian(0, 1).sample(numProducts);\n"
		     + "\n"
		     + "      //exponentiate right here (in the non-basic models move to the for loop)\n"
		     + "      double[] exped = new double[numProducts];\n"
		     + "      for(int j : [0..numProducts))\n"
		     + "         exped[j] = exp(ut[j]);\n"
		     + "\n"
		     + "      //Choices includes the choice to not buy anything.\n"
		     + "      int numChoices = numProducts + 1;\n"
		     + "\n"
		     + "      //now normalize\n"
		     + "      double[] expedNorm = new double[numProducts];\n"
		     + "      double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "      for(int j : [0..numProducts))\n"
		     + "         expedNorm[j] = exped[j]/(r*sum);\n"
		     + "\n"
		     + "      int[][] sales = new int[numTimeSteps][numProducts];\n"
		     + "\n"
		     + "      for (int t:[0..numTimeSteps)){\n"
		     + "         // Calculate the number of purchases made.\n"
		     + "         int numSales = reduce(ObsSales[t], 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "         // prior for the distribution of lambda for arrivals. These can be \n"
		     + "         // supplied as a vector if RGBU has some estimates, or just use some priors.\n"
		     + "         public double lambda = gamma(10,10).sample();\n"
		     + "         public int arrivals = numSales + poisson(lambda).sample();\n"
		     + "\n"
		     + "         // for each period t calculate choice probabilities and sales\n"
		     + "         double[] weekly_rates = new double[numChoices];\n"
		     + "         double[] weekly_ut = new double[numChoices];\n"
		     + "\n"
		     + "         for(int j : [0..numProducts)) {\n"
		     + "            if(avail[t][j])\n"
		     + "               weekly_ut[j] = expedNorm[j];\n"
		     + "            else\n"
		     + "               weekly_ut[j] = 0.0;\n"
		     + "         }\n"
		     + "         // Moved v_0 to the end of the array to keep indexing consistent everywhere else in \n"
		     + "         // the model and delayed the assignment of the value 1 to here. None of this is a \n"
		     + "         // sandwood requirement, I just thought it made the model eaiser to follow.\n"
		     + "         weekly_ut[numProducts] = 1.0;\n"
		     + "\n"
		     + "         double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "         for(int j : [0..numProducts]) \n"
		     + "            weekly_rates[j] = weekly_ut[j]/denom ;\n"
		     + "\n"
		     + "         int[] weekly_sales = multinomial(weekly_rates, arrivals).sample();\n"
		     + "\n"
		     + "         //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n"
		     + "         int[] observed_weekly_sales = sales[t];\n"
		     + "         for (int j : [0..numProducts))\n"
		     + "            observed_weekly_sales[j] = weekly_sales[j] ;\n"
		     + "      }\n"
		     + "      // assert that generated sales match observed sales\n"
		     + "      sales.observe(ObsSales);\n"
		     + "   }\n"
		     + "}";
	}
}