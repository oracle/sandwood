package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LinearRegressionTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements LinearRegressionTest$CoreInterface {
	
	// Declare the variables for the model.
	private double bias;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample42 = false;
	private boolean fixedFlag$sample46 = false;
	private boolean fixedFlag$sample85 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample42 = false;
	private boolean fixedProbFlag$sample46 = false;
	private boolean fixedProbFlag$sample85 = false;
	private int k;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$phi;
	private double[] logProbability$sample35;
	private double logProbability$tau;
	private double logProbability$var19;
	private double logProbability$var37;
	private double logProbability$var41;
	private double logProbability$var79;
	private double logProbability$var80;
	private double logProbability$weights;
	private double logProbability$y;
	private int n;
	private double[][] phi;
	private boolean setFlag$weights = false;
	private boolean setFlag$y = false;
	private boolean system$gibbsForward = true;
	private double tau;
	private double[] weights;
	private double[][] x;
	private double[] y;
	private double[] yMeasured;

	public LinearRegressionTest$SingleThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 42 as it depends on bias.
		fixedProbFlag$sample42 = false;
		
		// Unset the fixed probability flag for sample 85 as it depends on bias.
		fixedProbFlag$sample85 = false;
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
		
		// Should the probability of sample 85 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample85 = (fixedFlag$sample35 && fixedProbFlag$sample85);
	}

	// Getter for fixedFlag$sample42.
	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	// Setter for fixedFlag$sample42.
	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample42 including if probabilities
		// need to be updated.
		fixedFlag$sample42 = cv$value;
		
		// Should the probability of sample 42 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample42 = (fixedFlag$sample42 && fixedProbFlag$sample42);
		
		// Should the probability of sample 85 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample85 = (fixedFlag$sample42 && fixedProbFlag$sample85);
	}

	// Getter for fixedFlag$sample46.
	@Override
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	// Setter for fixedFlag$sample46.
	@Override
	public final void set$fixedFlag$sample46(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample46 including if probabilities
		// need to be updated.
		fixedFlag$sample46 = cv$value;
		
		// Should the probability of sample 46 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample46 = (fixedFlag$sample46 && fixedProbFlag$sample46);
		
		// Should the probability of sample 85 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample85 = (fixedFlag$sample46 && fixedProbFlag$sample85);
	}

	// Getter for fixedFlag$sample85.
	@Override
	public final boolean get$fixedFlag$sample85() {
		return fixedFlag$sample85;
	}

	// Setter for fixedFlag$sample85.
	@Override
	public final void set$fixedFlag$sample85(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample85 including if probabilities
		// need to be updated.
		fixedFlag$sample85 = cv$value;
		
		// Should the probability of sample 85 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample85 = (fixedFlag$sample85 && fixedProbFlag$sample85);
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
		
		// Unset the fixed probability flag for sample 46 as it depends on tau.
		fixedProbFlag$sample46 = false;
		
		// Unset the fixed probability flag for sample 85 as it depends on tau.
		fixedProbFlag$sample85 = false;
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
		// Set weights with flag to mark that it has been set so another array doesn't need
		// to be constructed
		weights = cv$value;
		setFlag$weights = true;
		
		// Unset the fixed probability flag for sample 35 as it depends on weights.
		fixedProbFlag$sample35 = false;
		
		// Unset the fixed probability flag for sample 85 as it depends on weights.
		fixedProbFlag$sample85 = false;
	}

	// Getter for x.
	@Override
	public final double[][] get$x() {
		return x;
	}

	// Setter for x.
	@Override
	public final void set$x(double[][] cv$value) {
		// Set x with flag to mark that it has been set so another array doesn't need to be
		// constructed
		x = cv$value;
	}

	// Getter for y.
	@Override
	public final double[] get$y() {
		return y;
	}

	// Setter for y.
	@Override
	public final void set$y(double[] cv$value) {
		// Set flags for all the side effects of y including if probabilities need to be updated.
		// Set y with flag to mark that it has been set so another array doesn't need to be
		// constructed
		y = cv$value;
		setFlag$y = true;
		
		// Unset the fixed probability flag for sample 85 as it depends on y.
		fixedProbFlag$sample85 = false;
	}

	// Getter for yMeasured.
	@Override
	public final double[] get$yMeasured() {
		return yMeasured;
	}

	// Setter for yMeasured.
	@Override
	public final void set$yMeasured(double[] cv$value) {
		// Set yMeasured with flag to mark that it has been set so another array doesn't need
		// to be constructed
		yMeasured = cv$value;
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
			for(int var30 = 0; var30 < k; var30 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = weights[var30];
					{
						{
							double var17 = 0.0;
							double var18 = 10.0;
							
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
				logProbability$sample35[((var30 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that phi is only updated once for this probability.
				boolean cv$guard$phi = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 35 and consumer double[] 67.
				{
					for(int j$var62 = 0; j$var62 < k; j$var62 += 1) {
						if((var30 == j$var62)) {
							for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
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
			logProbability$var19 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$weights = (logProbability$weights + cv$accumulator);
			
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
			for(int var30 = 0; var30 < k; var30 += 1) {
				double cv$sampleValue = logProbability$sample35[((var30 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that phi is only updated once for this probability.
				boolean cv$guard$phi = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 35 and consumer double[] 67.
				{
					for(int j$var62 = 0; j$var62 < k; j$var62 += 1) {
						if((var30 == j$var62)) {
							for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
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
			logProbability$var19 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$weights = (logProbability$weights + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample42 using sampled
	// values.
	private final void logProbabilityValue$sample42() {
		// Determine if we need to calculate the values for sample task 42 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample42) {
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
						double var35 = 0.0;
						double var36 = 10.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var35) / Math.sqrt(var36))) - (0.5 * Math.log(var36))));
						
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
			logProbability$var37 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$bias = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample42 = fixedFlag$sample42;
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
			logProbability$var37 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample46 using sampled
	// values.
	private final void logProbabilityValue$sample46() {
		// Determine if we need to calculate the values for sample task 46 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample46) {
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
						double var39 = 3.0;
						double var40 = 1.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var39, var40));
						
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
			logProbability$var41 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$tau = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample46 = fixedFlag$sample46;
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
			logProbability$var41 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample85 using sampled
	// values.
	private final void logProbabilityValue$sample85() {
		// Determine if we need to calculate the values for sample task 85 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample85) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = y[i$var52];
					{
						{
							// Reduction of array phi
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$var77$3 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction76Index = 0; cv$reduction76Index < k; cv$reduction76Index += 1) {
								// Set the left hand term of the reduction function to the return variable value.
								double i$var74 = reduceVar$var77$3;
								
								// Set the right hand term to a value from the array phi
								double j$var75 = phi[((i$var52 - 0) / 1)][cv$reduction76Index];
								
								// Execute the reduction function, saving the result into the return value.
								// 
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$var77$3 = (i$var74 + j$var75);
							}
							double var78 = (reduceVar$var77$3 + bias);
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var78) / Math.sqrt(tau))) - (0.5 * Math.log(tau))));
							
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
			logProbability$var79 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var80 = cv$accumulator;
			
			// Update the variable probability
			logProbability$y = (logProbability$y + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample85 = (((fixedFlag$sample85 && fixedFlag$sample35) && fixedFlag$sample42) && fixedFlag$sample46);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var80;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var79 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$y = (logProbability$y + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 35 drawn from Gaussian 19. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample35(int var30) {
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
			// Processing random variable 79.
			{
				// Looking for a path between Sample 35 and consumer Gaussian 79.
				{
					for(int j$var62 = 0; j$var62 < k; j$var62 += 1) {
						if((var30 == j$var62)) {
							for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
								if(((0 <= j$var62) && (j$var62 < k))) {
									{
										// Processing sample task 85 of consumer random variable null.
										{
											{
												{
													{
														{
															// State for tracking the changes that happen to the sampled value between it being
															// consumed and it being produced.
															double cv$denominator = 1.0;
															double cv$numerator = 0.0;
															cv$numerator = (cv$numerator * x[i$var52][j$var62]);
															cv$denominator = (cv$denominator * x[i$var52][j$var62]);
															if((0 < k)) {
																// Reduction of array phi
																// 
																// A generated name to prevent name collisions if the reduction is implemented more
																// than once in inference and probability code. Initialize the variable to the unit
																// value
																double reduceVar$var77$0 = 0.0;
																
																// Reduce for every value except a masked value which will be skipped.
																for(int cv$reduction162Index = 0; cv$reduction162Index < j$var62; cv$reduction162Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double i$var74 = reduceVar$var77$0;
																	
																	// Set the right hand term to a value from the array phi
																	double j$var75 = phi[((i$var52 - 0) / 1)][cv$reduction162Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$var77$0 = (i$var74 + j$var75);
																}
																for(int cv$reduction162Index = (j$var62 + 1); cv$reduction162Index < k; cv$reduction162Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double i$var74 = reduceVar$var77$0;
																	
																	// Set the right hand term to a value from the array phi
																	double j$var75 = phi[((i$var52 - 0) / 1)][cv$reduction162Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$var77$0 = (i$var74 + j$var75);
																}
																double cv$reduced76 = reduceVar$var77$0;
																cv$numerator = (cv$numerator + cv$reduced76);
															}
															cv$numerator = (cv$numerator + bias);
															
															// Record the value of a sample generated by a consuming sample 85 of random variable
															// var79.
															// 
															// Add the denominator squared to the sample denominator
															cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
															
															// Add the weighting of the sample to the sum.
															cv$sum = (cv$sum + (cv$denominator * (y[i$var52] - cv$numerator)));
															
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
		double var31 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		weights[var30] = var31;
		
		// Guards to ensure that phi is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 35 and consumer double[] 67.
		{
			for(int j$var62 = 0; j$var62 < k; j$var62 += 1) {
				if((var30 == j$var62)) {
					for(int i$var52 = 0; i$var52 < n; i$var52 += 1)
						phi[((i$var52 - 0) / 1)][j$var62] = (weights[j$var62] * x[i$var52][j$var62]);
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 42 drawn from Gaussian 37. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample42() {
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
			// Processing random variable 79.
			{
				{
					for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
						// State for tracking the changes that happen to the sampled value between it being
						// consumed and it being produced.
						double cv$denominator = 1.0;
						double cv$numerator = 0.0;
						
						// Reduction of array phi
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$var77$1 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction76Index = 0; cv$reduction76Index < k; cv$reduction76Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double i$var74 = reduceVar$var77$1;
							
							// Set the right hand term to a value from the array phi
							double j$var75 = phi[((i$var52 - 0) / 1)][cv$reduction76Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var77$1 = (i$var74 + j$var75);
						}
						cv$numerator = (reduceVar$var77$1 + cv$numerator);
						
						// Record the value of a sample generated by a consuming sample 85 of random variable
						// var79.
						// 
						// Add the denominator squared to the sample denominator
						cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
						
						// Add the weighting of the sample to the sum.
						cv$sum = (cv$sum + (cv$denominator * (y[i$var52] - cv$numerator)));
						
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
	// by sample task 46 drawn from InverseGamma 41. Inference was performed using a Inverse
	// Gamma to Gaussian conjugate prior.
	private final void sample46() {
		// Variable to track the sum of the difference between the samples and the random
		// variables mean squared.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		{
			// Processing random variable 79.
			{
				{
					for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
						// Reduction of array phi
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$var77$2 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction76Index = 0; cv$reduction76Index < k; cv$reduction76Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double i$var74 = reduceVar$var77$2;
							
							// Set the right hand term to a value from the array phi
							double j$var75 = phi[((i$var52 - 0) / 1)][cv$reduction76Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var77$2 = (i$var74 + j$var75);
						}
						
						// The mean parameter for Gaussian var79.
						double cv$var79$mu = (reduceVar$var77$2 + bias);
						
						// Consume sample task 85 from random variable var79.
						// 
						// The difference between the mean parameter and the value sampled from the Gaussian.
						double cv$var79$diff = (cv$var79$mu - y[i$var52]);
						
						// Include this sample by adding the square of the difference to the sum.
						cv$sum = (cv$sum + (cv$var79$diff * cv$var79$diff));
						
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
		// If y has not been set already allocate space.
		if(!setFlag$y) {
			// Constructor for y
			{
				y = new double[x.length];
			}
		}
		
		// If weights has not been set already allocate space.
		if(!setFlag$weights) {
			// Constructor for weights
			{
				weights = new double[x[0].length];
			}
		}
		
		// Constructor for phi
		{
			phi = new double[((((x.length - 1) - 0) / 1) + 1)][];
			for(int i$var52 = 0; i$var52 < x.length; i$var52 += 1)
				phi[((i$var52 - 0) / 1)] = new double[x[0].length];
		}
		
		// Constructor for logProbability$sample35
		{
			logProbability$sample35 = new double[((((x[0].length - 1) - 0) / 1) + 1)];
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		for(int var30 = 0; var30 < k; var30 += 1) {
			if(!fixedFlag$sample35)
				weights[var30] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		if(!fixedFlag$sample42)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample46)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
			for(int j$var62 = 0; j$var62 < k; j$var62 += 1) {
				if(!fixedFlag$sample35)
					phi[((i$var52 - 0) / 1)][j$var62] = (weights[j$var62] * x[i$var52][j$var62]);
			}
			
			// Reduction of array phi
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$var77$4 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction76Index = 0; cv$reduction76Index < k; cv$reduction76Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double i$var74 = reduceVar$var77$4;
				
				// Set the right hand term to a value from the array phi
				double j$var75 = phi[((i$var52 - 0) / 1)][cv$reduction76Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample85)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$var77$4 = (i$var74 + j$var75);
			}
			if(!fixedFlag$sample85)
				y[i$var52] = ((Math.sqrt(tau) * DistributionSampling.sampleGaussian(RNG$)) + (reduceVar$var77$4 + bias));
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var30 = 0; var30 < k; var30 += 1) {
			if(!fixedFlag$sample35)
				weights[var30] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		if(!fixedFlag$sample42)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample46)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
			for(int j$var62 = 0; j$var62 < k; j$var62 += 1) {
				if(!fixedFlag$sample35)
					phi[((i$var52 - 0) / 1)][j$var62] = (weights[j$var62] * x[i$var52][j$var62]);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var30 = 0; var30 < k; var30 += 1) {
			if(!fixedFlag$sample35)
				weights[var30] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		if(!fixedFlag$sample42)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample46)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
			for(int j$var62 = 0; j$var62 < k; j$var62 += 1) {
				if(!fixedFlag$sample35)
					phi[((i$var52 - 0) / 1)][j$var62] = (weights[j$var62] * x[i$var52][j$var62]);
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			for(int var30 = 0; var30 < k; var30 += 1) {
				if(!fixedFlag$sample35)
					sample35(var30);
			}
			if(!fixedFlag$sample42)
				sample42();
			if(!fixedFlag$sample46)
				sample46();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample46)
				sample46();
			if(!fixedFlag$sample42)
				sample42();
			for(int var30 = (k - ((((k - 1) - 0) % 1) + 1)); var30 >= ((0 - 1) + 1); var30 -= 1) {
				if(!fixedFlag$sample35)
					sample35(var30);
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
		logProbability$var19 = 0.0;
		logProbability$phi = 0.0;
		logProbability$weights = 0.0;
		if(!fixedProbFlag$sample35) {
			for(int var30 = 0; var30 < k; var30 += 1)
				logProbability$sample35[((var30 - 0) / 1)] = 0.0;
		}
		logProbability$var37 = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$bias = 0.0;
		logProbability$var41 = 0.0;
		if(!fixedProbFlag$sample46)
			logProbability$tau = 0.0;
		logProbability$var79 = 0.0;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample85)
			logProbability$var80 = 0.0;
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
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(fixedFlag$sample46)
			logProbabilityValue$sample46();
		logProbabilityValue$sample85();
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
		logProbabilityValue$sample35();
		logProbabilityValue$sample42();
		logProbabilityValue$sample46();
		logProbabilityValue$sample85();
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
		logProbabilityValue$sample35();
		logProbabilityValue$sample42();
		logProbabilityValue$sample46();
		logProbabilityValue$sample85();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		for(int var30 = 0; var30 < k; var30 += 1) {
			if(!fixedFlag$sample35)
				weights[var30] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		if(!fixedFlag$sample42)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample46)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
			for(int j$var62 = 0; j$var62 < k; j$var62 += 1) {
				if(!fixedFlag$sample35)
					phi[((i$var52 - 0) / 1)][j$var62] = (weights[j$var62] * x[i$var52][j$var62]);
			}
		}
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Deep copy between arrays
		double[] cv$source1 = yMeasured;
		double[] cv$target1 = y;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
			for(int j$var62 = 0; j$var62 < k; j$var62 += 1) {
				if(setFlag$weights)
					phi[((i$var52 - 0) / 1)][j$var62] = (weights[j$var62] * x[i$var52][j$var62]);
			}
		}
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