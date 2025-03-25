package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LinearRegressionTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LinearRegressionTest$CoreInterface {
	
	// Declare the variables for the model.
	private double bias;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample74 = false;
	private int k;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$phi;
	private double[] logProbability$sample24;
	private double[] logProbability$sample74;
	private double logProbability$tau;
	private double logProbability$var12;
	private double logProbability$var30;
	private double logProbability$var34;
	private double[] logProbability$var72;
	private double logProbability$weights;
	private double logProbability$y;
	private int n;
	private double[][] phi;
	private boolean system$gibbsForward = true;
	private double tau;
	private double[] weights;
	private double[][] x;
	private double[] y;
	private double[] yMeasured;

	public LinearRegressionTest$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for bias.
	@Override
	public final double get$bias() {
		return bias;
	}

	// Setter for bias.
	@Override
	public final void set$bias(double cv$value) {
		// Set flags for all the side effects of bias including if probabilities need to be
		// updated.
		bias = cv$value;
		
		// Unset the fixed probability flag for sample 31 as it depends on bias.
		fixedProbFlag$sample31 = false;
		
		// Unset the fixed probability flag for sample 74 as it depends on bias.
		fixedProbFlag$sample74 = false;
	}

	// Getter for fixedFlag$sample24.
	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	// Setter for fixedFlag$sample24.
	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample24 including if probabilities
		// need to be updated.
		fixedFlag$sample24 = cv$value;
		
		// Should the probability of sample 24 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample24 = (fixedFlag$sample24 && fixedProbFlag$sample24);
		
		// Should the probability of sample 74 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample74 = (fixedFlag$sample24 && fixedProbFlag$sample74);
	}

	// Getter for fixedFlag$sample31.
	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	// Setter for fixedFlag$sample31.
	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample31 including if probabilities
		// need to be updated.
		fixedFlag$sample31 = cv$value;
		
		// Should the probability of sample 31 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample31 = (fixedFlag$sample31 && fixedProbFlag$sample31);
		
		// Should the probability of sample 74 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample74 = (fixedFlag$sample31 && fixedProbFlag$sample74);
	}

	// Getter for fixedFlag$sample35.
	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	// Setter for fixedFlag$sample35.
	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample35 including if probabilities
		// need to be updated.
		fixedFlag$sample35 = cv$value;
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
		
		// Should the probability of sample 74 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample74 = (fixedFlag$sample35 && fixedProbFlag$sample74);
	}

	// Getter for k.
	@Override
	public final int get$k() {
		return k;
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

	// Getter for logProbability$bias.
	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	// Getter for logProbability$tau.
	@Override
	public final double get$logProbability$tau() {
		return logProbability$tau;
	}

	// Getter for logProbability$weights.
	@Override
	public final double get$logProbability$weights() {
		return logProbability$weights;
	}

	// Getter for logProbability$y.
	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	// Getter for n.
	@Override
	public final int get$n() {
		return n;
	}

	// Getter for tau.
	@Override
	public final double get$tau() {
		return tau;
	}

	// Setter for tau.
	@Override
	public final void set$tau(double cv$value) {
		// Set flags for all the side effects of tau including if probabilities need to be
		// updated.
		tau = cv$value;
		
		// Unset the fixed probability flag for sample 35 as it depends on tau.
		fixedProbFlag$sample35 = false;
		
		// Unset the fixed probability flag for sample 74 as it depends on tau.
		fixedProbFlag$sample74 = false;
	}

	// Getter for weights.
	@Override
	public final double[] get$weights() {
		return weights;
	}

	// Setter for weights.
	@Override
	public final void set$weights(double[] cv$value) {
		// Set flags for all the side effects of weights including if probabilities need to
		// be updated.
		// Set weights
		weights = cv$value;
		
		// Unset the fixed probability flag for sample 24 as it depends on weights.
		fixedProbFlag$sample24 = false;
		
		// Unset the fixed probability flag for sample 74 as it depends on weights.
		fixedProbFlag$sample74 = false;
	}

	// Getter for x.
	@Override
	public final double[][] get$x() {
		return x;
	}

	// Setter for x.
	@Override
	public final void set$x(double[][] cv$value) {
		// Set x
		x = cv$value;
	}

	// Getter for y.
	@Override
	public final double[] get$y() {
		return y;
	}

	// Getter for yMeasured.
	@Override
	public final double[] get$yMeasured() {
		return yMeasured;
	}

	// Setter for yMeasured.
	@Override
	public final void set$yMeasured(double[] cv$value) {
		// Set yMeasured
		yMeasured = cv$value;
	}

	// Calculate the probability of the samples represented by sample24 using sampled
	// values.
	private final void logProbabilityValue$sample24() {
		// Determine if we need to calculate the values for sample task 24 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample24) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var23 = 0; var23 < k; var23 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = weights[var23];
					{
						{
							double var10 = 0.0;
							double var11 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var10) / Math.sqrt(var11))) - (0.5 * Math.log(var11))));
							
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
				
				// Store the sample task probability
				logProbability$sample24[((var23 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that phi is only updated once for this probability.
				boolean cv$guard$phi = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 24 and consumer double[] 60.
				{
					for(int j$var55 = 0; j$var55 < k; j$var55 += 1) {
						if((var23 == j$var55)) {
							for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$phi) {
									// Set the guard so the update is only applied once.
									cv$guard$phi = true;
									
									// Update the variable probability
									logProbability$phi = (logProbability$phi + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var12 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$weights = (logProbability$weights + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample24 = fixedFlag$sample24;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var23 = 0; var23 < k; var23 += 1) {
				double cv$sampleValue = logProbability$sample24[((var23 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that phi is only updated once for this probability.
				boolean cv$guard$phi = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 24 and consumer double[] 60.
				{
					for(int j$var55 = 0; j$var55 < k; j$var55 += 1) {
						if((var23 == j$var55)) {
							for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$phi) {
									// Set the guard so the update is only applied once.
									cv$guard$phi = true;
									
									// Update the variable probability
									logProbability$phi = (logProbability$phi + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var12 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$weights = (logProbability$weights + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample31 using sampled
	// values.
	private final void logProbabilityValue$sample31() {
		// Determine if we need to calculate the values for sample task 31 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample31) {
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
				double cv$sampleValue = bias;
				{
					{
						double var28 = 0.0;
						double var29 = 10.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var28) / Math.sqrt(var29))) - (0.5 * Math.log(var29))));
						
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
			logProbability$var30 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$bias = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample31 = fixedFlag$sample31;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$bias;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var30 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample35 using sampled
	// values.
	private final void logProbabilityValue$sample35() {
		// Determine if we need to calculate the values for sample task 35 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample35) {
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
				double cv$sampleValue = tau;
				{
					{
						double var32 = 3.0;
						double var33 = 1.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var32, var33));
						
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
			logProbability$var34 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$tau = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample35 = fixedFlag$sample35;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$tau;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var34 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample74 using sampled
	// values.
	private final void logProbabilityValue$sample74() {
		// Determine if we need to calculate the values for sample task 74 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample74) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = y[i$var45];
					{
						{
							// Reduction of array phi
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$var70$8 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction65Index = 0; cv$reduction65Index < k; cv$reduction65Index += 1) {
								// Set the left hand term of the reduction function to the return variable value.
								double i$var67 = reduceVar$var70$8;
								
								// Set the right hand term to a value from the array phi
								double j$var68 = phi[((i$var45 - 0) / 1)][cv$reduction65Index];
								
								// Execute the reduction function, saving the result into the return value.
								// 
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$var70$8 = (i$var67 + j$var68);
							}
							double var71 = (reduceVar$var70$8 + bias);
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var71) / Math.sqrt(tau))) - (0.5 * Math.log(tau))));
							
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
				logProbability$var72[((i$var45 - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample74[((i$var45 - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$y = (logProbability$y + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample74 = ((fixedFlag$sample24 && fixedFlag$sample31) && fixedFlag$sample35);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample74[((i$var45 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var72[((i$var45 - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$y = (logProbability$y + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 24 drawn from Gaussian 12. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample24(int var23) {
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
			// Processing random variable 72.
			{
				// Looking for a path between Sample 24 and consumer Gaussian 72.
				{
					for(int j$var55 = 0; j$var55 < k; j$var55 += 1) {
						if((var23 == j$var55)) {
							for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
								if(((0 <= j$var55) && (j$var55 < k))) {
									{
										// Processing sample task 74 of consumer random variable null.
										{
											{
												{
													{
														{
															// State for tracking the changes that happen to the sampled value between it being
															// consumed and it being produced.
															double cv$denominator = 1.0;
															double cv$numerator = 0.0;
															cv$numerator = (cv$numerator * x[i$var45][j$var55]);
															cv$denominator = (cv$denominator * x[i$var45][j$var55]);
															if((0 < k)) {
																// Reduction of array phi
																// 
																// A generated name to prevent name collisions if the reduction is implemented more
																// than once in inference and probability code. Initialize the variable to the unit
																// value
																double reduceVar$var70$5 = 0.0;
																
																// Reduce for every value except a masked value which will be skipped.
																for(int cv$reduction378Index = 0; cv$reduction378Index < j$var55; cv$reduction378Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double i$var67 = reduceVar$var70$5;
																	
																	// Set the right hand term to a value from the array phi
																	double j$var68 = phi[((i$var45 - 0) / 1)][cv$reduction378Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$var70$5 = (i$var67 + j$var68);
																}
																for(int cv$reduction378Index = (j$var55 + 1); cv$reduction378Index < k; cv$reduction378Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double i$var67 = reduceVar$var70$5;
																	
																	// Set the right hand term to a value from the array phi
																	double j$var68 = phi[((i$var45 - 0) / 1)][cv$reduction378Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$var70$5 = (i$var67 + j$var68);
																}
																double cv$reduced65 = reduceVar$var70$5;
																cv$numerator = (cv$numerator + cv$reduced65);
															}
															cv$numerator = (cv$numerator + bias);
															
															// Record the value of a sample generated by a consuming sample 74 of random variable
															// var72.
															// 
															// Add the denominator squared to the sample denominator
															cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
															
															// Add the weighting of the sample to the sum.
															cv$sum = (cv$sum + (cv$denominator * (y[i$var45] - cv$numerator)));
															
															// If we have not got the value of sigma yet record it and set a flag so it is not
															// recorded again.
															if(cv$sigmaNotFound) {
																cv$sigmaValue = tau;
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
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		double var24 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		
		// Guards to ensure that weights is only updated when there is a valid path.
		{
			{
				weights[var23] = var24;
			}
		}
		
		// Guards to ensure that phi is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 24 and consumer double[] 60.
		{
			for(int j$var55 = 0; j$var55 < k; j$var55 += 1) {
				if((var23 == j$var55)) {
					for(int i$var45 = 0; i$var45 < n; i$var45 += 1)
						phi[((i$var45 - 0) / 1)][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 31 drawn from Gaussian 30. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample31() {
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
			// Processing random variable 72.
			{
				{
					for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
						// State for tracking the changes that happen to the sampled value between it being
						// consumed and it being produced.
						double cv$denominator = 1.0;
						double cv$numerator = 0.0;
						
						// Reduction of array phi
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$var70$6 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction65Index = 0; cv$reduction65Index < k; cv$reduction65Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double i$var67 = reduceVar$var70$6;
							
							// Set the right hand term to a value from the array phi
							double j$var68 = phi[((i$var45 - 0) / 1)][cv$reduction65Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var70$6 = (i$var67 + j$var68);
						}
						cv$numerator = (reduceVar$var70$6 + cv$numerator);
						
						// Record the value of a sample generated by a consuming sample 74 of random variable
						// var72.
						// 
						// Add the denominator squared to the sample denominator
						cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
						
						// Add the weighting of the sample to the sum.
						cv$sum = (cv$sum + (cv$denominator * (y[i$var45] - cv$numerator)));
						
						// If we have not got the value of sigma yet record it and set a flag so it is not
						// recorded again.
						if(cv$sigmaNotFound) {
							cv$sigmaValue = tau;
							cv$sigmaNotFound = false;
						}
					}
				}
			}
		}
		
		// Write out the new value of the sample.
		bias = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 35 drawn from InverseGamma 34. Inference was performed using a Inverse
	// Gamma to Gaussian conjugate prior.
	private final void sample35() {
		// Variable to track the sum of the difference between the samples and the random
		// variables mean squared.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		{
			// Processing random variable 72.
			{
				{
					for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
						// Reduction of array phi
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$var70$7 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction65Index = 0; cv$reduction65Index < k; cv$reduction65Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double i$var67 = reduceVar$var70$7;
							
							// Set the right hand term to a value from the array phi
							double j$var68 = phi[((i$var45 - 0) / 1)][cv$reduction65Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var70$7 = (i$var67 + j$var68);
						}
						
						// The mean parameter for Gaussian var72.
						double cv$var72$mu = (reduceVar$var70$7 + bias);
						
						// Consume sample task 74 from random variable var72.
						// 
						// The difference between the mean parameter and the value sampled from the Gaussian.
						double cv$var72$diff = (cv$var72$mu - y[i$var45]);
						
						// Include this sample by adding the square of the difference to the sum.
						cv$sum = (cv$sum + (cv$var72$diff * cv$var72$diff));
						
						// Increment the number of samples in the calculation.
						cv$count = (cv$count + 1);
					}
				}
			}
		}
		
		// Write out the new value of the sample.
		tau = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 3.0, 1.0, cv$sum, cv$count);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for y
		{
			y = new double[x.length];
		}
		
		// If weights has not been set already allocate space.
		if(!fixedFlag$sample24) {
			// Constructor for weights
			{
				weights = new double[x[0].length];
			}
		}
		
		// Constructor for phi
		{
			phi = new double[((((x.length - 1) - 0) / 1) + 1)][];
			for(int i$var45 = 0; i$var45 < x.length; i$var45 += 1)
				phi[((i$var45 - 0) / 1)] = new double[x[0].length];
		}
		
		// Constructor for logProbability$sample24
		{
			logProbability$sample24 = new double[((((x[0].length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var72
		{
			logProbability$var72 = new double[((((x.length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample74
		{
			logProbability$sample74 = new double[((((x.length - 1) - 0) / 1) + 1)];
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1) {
						if(!fixedFlag$sample24)
							weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample31)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample35)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
						int i$var45 = index$i$var45;
						int threadID$i$var45 = threadID$index$i$var45;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1) {
										if(!fixedFlag$sample24)
											phi[((i$var45 - 0) / 1)][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
									}
							}
						);
						
						// Reduction of array phi
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$var70$9 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction65Index = 0; cv$reduction65Index < k; cv$reduction65Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double i$var67 = reduceVar$var70$9;
							
							// Set the right hand term to a value from the array phi
							double j$var68 = phi[((i$var45 - 0) / 1)][cv$reduction65Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var70$9 = (i$var67 + j$var68);
						}
						y[i$var45] = ((Math.sqrt(tau) * DistributionSampling.sampleGaussian(RNG$1)) + (reduceVar$var70$9 + bias));
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1) {
						if(!fixedFlag$sample24)
							weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample31)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample35)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
						int i$var45 = index$i$var45;
						int threadID$i$var45 = threadID$index$i$var45;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1) {
										if(!fixedFlag$sample24)
											phi[((i$var45 - 0) / 1)][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
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
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1) {
						if(!fixedFlag$sample24)
							weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample31)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample35)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
						int i$var45 = index$i$var45;
						int threadID$i$var45 = threadID$index$i$var45;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1) {
										if(!fixedFlag$sample24)
											phi[((i$var45 - 0) / 1)][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
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
		if(system$gibbsForward) {
			for(int var23 = 0; var23 < k; var23 += 1) {
				if(!fixedFlag$sample24)
					sample24(var23);
			}
			if(!fixedFlag$sample31)
				sample31();
			if(!fixedFlag$sample35)
				sample35();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample31)
				sample31();
			for(int var23 = (k - ((((k - 1) - 0) % 1) + 1)); var23 >= ((0 - 1) + 1); var23 -= 1) {
				if(!fixedFlag$sample24)
					sample24(var23);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		n = x.length;
		k = x[0].length;
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
		logProbability$var12 = 0.0;
		logProbability$weights = 0.0;
		logProbability$phi = 0.0;
		if(!fixedProbFlag$sample24) {
			for(int var23 = 0; var23 < k; var23 += 1)
				logProbability$sample24[((var23 - 0) / 1)] = 0.0;
		}
		logProbability$var30 = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$bias = 0.0;
		logProbability$var34 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$tau = 0.0;
		for(int i$var45 = 0; i$var45 < n; i$var45 += 1)
			logProbability$var72[((i$var45 - 0) / 1)] = 0.0;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample74) {
			for(int i$var45 = 0; i$var45 < n; i$var45 += 1)
				logProbability$sample74[((i$var45 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample24)
			logProbabilityValue$sample24();
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample74();
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
		logProbabilityValue$sample24();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample74();
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
		logProbabilityValue$sample24();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample74();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1) {
						if(!fixedFlag$sample24)
							weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample31)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample35)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
						int i$var45 = index$i$var45;
						int threadID$i$var45 = threadID$index$i$var45;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1) {
										if(!fixedFlag$sample24)
											phi[((i$var45 - 0) / 1)][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
									}
							}
						);
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
		// Deep copy between arrays
		double[] cv$source1 = yMeasured;
		double[] cv$target1 = y;
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
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
						int i$var45 = index$i$var45;
						int threadID$i$var45 = threadID$index$i$var45;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1) {
										if(fixedFlag$sample24)
											phi[((i$var45 - 0) / 1)][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
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
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model LinearRegressionTest(double[][] x, double[] yMeasured) {\n"
		     + "\n"
		     + "        int n = x.length;\n"
		     + "        int k = x[0].length;\n"
		     + "\n"
		     + "        double[] y = new double[n];\n"
		     + "\n"
		     + "        double[] weights = gaussian(0,10).sample(k);\n"
		     + "        double bias = gaussian(0,10).sample();\n"
		     + "        double tau = inverseGamma(3.0,1.0).sample();\n"
		     + "\n"
		     + "        for(int i:[0..n)) {\n"
		     + "            double[] phi = new double[k];\n"
		     + "            for(int j:[0..k,1))\n"
		     + "                phi[j] = weights[j] * x[i][j];\n"
		     + "            \n"
		     + "            y[i] = gaussian(sum(phi) + bias, tau).sample();\n"
		     + "        }\n"
		     + "        \n"
		     + "        y.observe(yMeasured);\n"
		     + "\n"
		     + "    private double sum(double[] a) {\n"
		     + "        return reduce(a, 0, (i,j) -> {\n"
		     + "            return i + j;\n"
		     + "        });\n"
		     + "    }\n"
		     + "}\n"
		     + "";
	}
}