package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LogitRegressionTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements LogitRegressionTest$CoreInterface {
	
	// Declare the variables for the model.
	private double bias;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample39 = false;
	private boolean fixedFlag$sample72 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample72 = false;
	private boolean[][] guard$sample32bernoulli71$global;
	private boolean[][] guard$sample32put67$global;
	private double[][] indicator;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$indicator;
	private double logProbability$p;
	private double[] logProbability$sample32;
	private double[][] logProbability$sample72;
	private double logProbability$var23;
	private double logProbability$var34;
	private double[][] logProbability$var67;
	private double logProbability$weights;
	private double logProbability$y;
	private int n;
	private double[][] p;
	private boolean setFlag$weights = false;
	private boolean setFlag$y = false;
	private boolean system$gibbsForward = true;
	private double[] weights;
	private double[][] x;
	private boolean[][] y;
	private boolean[][] yMeasured;

	public LogitRegressionTest$SingleThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 39 as it depends on bias.
		fixedProbFlag$sample39 = false;
		
		// Unset the fixed probability flag for sample 72 as it depends on bias.
		fixedProbFlag$sample72 = false;
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
		// 
		// Substituted "fixedFlag$sample32" with its value "cv$value".
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
		
		// Should the probability of sample 72 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample32" with its value "cv$value".
		fixedProbFlag$sample72 = (cv$value && fixedProbFlag$sample72);
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
		
		// Should the probability of sample 72 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample39" with its value "cv$value".
		fixedProbFlag$sample72 = (cv$value && fixedProbFlag$sample72);
	}

	// Getter for fixedFlag$sample72.
	@Override
	public final boolean get$fixedFlag$sample72() {
		return fixedFlag$sample72;
	}

	// Setter for fixedFlag$sample72.
	@Override
	public final void set$fixedFlag$sample72(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample72 including if probabilities
		// need to be updated.
		fixedFlag$sample72 = cv$value;
		
		// Should the probability of sample 72 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample72" with its value "cv$value".
		fixedProbFlag$sample72 = (cv$value && fixedProbFlag$sample72);
	}

	// Getter for k.
	@Override
	public final int get$k() {
		return 3;
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
		
		// Unset the fixed probability flag for sample 32 as it depends on weights.
		fixedProbFlag$sample32 = false;
		
		// Unset the fixed probability flag for sample 72 as it depends on weights.
		fixedProbFlag$sample72 = false;
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
	public final boolean[][] get$y() {
		return y;
	}

	// Setter for y.
	@Override
	public final void set$y(boolean[][] cv$value) {
		// Set flags for all the side effects of y including if probabilities need to be updated.
		// Set y with flag to mark that it has been set so another array doesn't need to be
		// constructed
		y = cv$value;
		setFlag$y = true;
		
		// Unset the fixed probability flag for sample 72 as it depends on y.
		fixedProbFlag$sample72 = false;
	}

	// Getter for yMeasured.
	@Override
	public final boolean[][] get$yMeasured() {
		return yMeasured;
	}

	// Setter for yMeasured.
	@Override
	public final void set$yMeasured(boolean[][] cv$value) {
		// Set yMeasured with flag to mark that it has been set so another array doesn't need
		// to be constructed
		yMeasured = cv$value;
	}

	// Calculate the probability of the samples represented by sample32 using sampled
	// values.
	private final void logProbabilityValue$sample32() {
		// Determine if we need to calculate the values for sample task 32 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample32) {
			// Generating probabilities for sample task
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator;
			{
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((weights[0] / 3.1622776601683795)) - 1.151292546497023);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				cv$sampleAccumulator = cv$weightedProbability;
				
				// Store the sample task probability
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				logProbability$sample32[0] = cv$weightedProbability;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < n)) {
					// Unrolled loop
					// 
					// Update the variable probability
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					logProbability$indicator = (logProbability$indicator + cv$weightedProbability);
					
					// Update the variable probability
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					logProbability$p = (logProbability$p + cv$weightedProbability);
				}
			}
			{
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((weights[1] / 3.1622776601683795)) - 1.151292546497023);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$weightedProbability);
				
				// Store the sample task probability
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				logProbability$sample32[1] = cv$weightedProbability;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < n)) {
					// Unrolled loop
					// 
					// Update the variable probability
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					logProbability$indicator = (logProbability$indicator + cv$weightedProbability);
					
					// Update the variable probability
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					logProbability$p = (logProbability$p + cv$weightedProbability);
				}
			}
			
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((weights[2] / 3.1622776601683795)) - 1.151292546497023);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$weightedProbability);
			
			// Store the sample task probability
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			logProbability$sample32[2] = cv$weightedProbability;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < n)) {
				// Unrolled loop
				// 
				// Update the variable probability
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				logProbability$indicator = (logProbability$indicator + cv$weightedProbability);
				
				// Update the variable probability
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				logProbability$p = (logProbability$p + cv$weightedProbability);
			}
			logProbability$var23 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$weights = (logProbability$weights + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample32)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample32 = fixedFlag$sample32;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// This value is not used before it is set again, so removing the value declaration.
			double cv$rvAccumulator;
			{
				double cv$sampleValue = logProbability$sample32[0];
				cv$rvAccumulator = cv$sampleValue;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < n)) {
					// Unrolled loop
					// 
					// Update the variable probability
					logProbability$indicator = (logProbability$indicator + cv$sampleValue);
					
					// Update the variable probability
					logProbability$p = (logProbability$p + cv$sampleValue);
				}
			}
			{
				double cv$sampleValue = logProbability$sample32[1];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < n)) {
					// Unrolled loop
					// 
					// Update the variable probability
					logProbability$indicator = (logProbability$indicator + cv$sampleValue);
					
					// Update the variable probability
					logProbability$p = (logProbability$p + cv$sampleValue);
				}
			}
			double cv$sampleValue = logProbability$sample32[2];
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < n)) {
				// Unrolled loop
				// 
				// Update the variable probability
				logProbability$indicator = (logProbability$indicator + cv$sampleValue);
				
				// Update the variable probability
				logProbability$p = (logProbability$p + cv$sampleValue);
			}
			logProbability$var23 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$weights = (logProbability$weights + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample39 using sampled
	// values.
	private final void logProbabilityValue$sample39() {
		// Determine if we need to calculate the values for sample task 39 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample39) {
			// Generating probabilities for sample task
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
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((bias / 3.1622776601683795)) - 1.151292546497023);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var34 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$bias = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample39)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample39 = fixedFlag$sample39;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var34 = logProbability$bias;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$bias);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample39)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	// Calculate the probability of the samples represented by sample72 using sampled
	// values.
	private final void logProbabilityValue$sample72() {
		// Determine if we need to calculate the values for sample task 72 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample72) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				// Unrolled loop
				{
					// Store the value of the function call, so the function call is only made once.
					// 
					// Substituted "j$var60" with its value "0".
					double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(y[i][0], (p[i][0] + bias));
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					cv$accumulator = (cv$accumulator + cv$weightedProbability);
					
					// Substituted "j$var60" with its value "0".
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					logProbability$var67[i][0] = cv$weightedProbability;
					
					// Store the sample task probability
					// 
					// Substituted "j$var60" with its value "0".
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					logProbability$sample72[i][0] = cv$weightedProbability;
				}
				{
					// Store the value of the function call, so the function call is only made once.
					// 
					// Substituted "j$var60" with its value "1".
					double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(y[i][1], (p[i][1] + bias));
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					cv$accumulator = (cv$accumulator + cv$weightedProbability);
					
					// Substituted "j$var60" with its value "1".
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					logProbability$var67[i][1] = cv$weightedProbability;
					
					// Store the sample task probability
					// 
					// Substituted "j$var60" with its value "1".
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					logProbability$sample72[i][1] = cv$weightedProbability;
				}
				
				// Store the value of the function call, so the function call is only made once.
				// 
				// Substituted "j$var60" with its value "2".
				double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(y[i][2], (p[i][2] + bias));
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				cv$accumulator = (cv$accumulator + cv$weightedProbability);
				
				// Substituted "j$var60" with its value "2".
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				logProbability$var67[i][2] = cv$weightedProbability;
				
				// Store the sample task probability
				// 
				// Substituted "j$var60" with its value "2".
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				logProbability$sample72[i][2] = cv$weightedProbability;
			}
			
			// Update the variable probability
			logProbability$y = (logProbability$y + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample72 = ((fixedFlag$sample72 && fixedFlag$sample32) && fixedFlag$sample39);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				// Unrolled loop
				{
					// Variable declaration of cv$rvAccumulator moved.
					// 
					// Substituted "j$var60" with its value "0".
					double cv$rvAccumulator = logProbability$sample72[i][0];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					
					// Substituted "j$var60" with its value "0".
					logProbability$var67[i][0] = cv$rvAccumulator;
				}
				{
					// Variable declaration of cv$rvAccumulator moved.
					// 
					// Substituted "j$var60" with its value "1".
					double cv$rvAccumulator = logProbability$sample72[i][1];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					
					// Substituted "j$var60" with its value "1".
					logProbability$var67[i][1] = cv$rvAccumulator;
				}
				
				// Variable declaration of cv$rvAccumulator moved.
				// 
				// Substituted "j$var60" with its value "2".
				double cv$rvAccumulator = logProbability$sample72[i][2];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				
				// Substituted "j$var60" with its value "2".
				logProbability$var67[i][2] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$y = (logProbability$y + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 32 drawn from Gaussian 23. Inference was performed using Metropolis-Hastings.
	private final void sample32(int var27) {
		// The original value of the sample
		double cv$originalValue = weights[var27];
		
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
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var22" with its value "10.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 0)) {
				// Unrolled loop
				// Unrolled loop
				for(int i = 0; i < n; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][0] = false;
				for(int i = 0; i < n; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][1] = false;
				for(int i = 0; i < n; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][2] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 1)) {
				// Unrolled loop
				// Unrolled loop
				for(int i = 0; i < n; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][0] = false;
				for(int i = 0; i < n; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][1] = false;
				for(int i = 0; i < n; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][2] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 2)) {
				// Unrolled loop
				// Unrolled loop
				for(int i = 0; i < n; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][0] = false;
				for(int i = 0; i < n; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][1] = false;
				for(int i = 0; i < n; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][2] = false;
			}
			
			// Unrolled loop
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 0)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][0] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 1)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][1] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 2)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][2] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 0)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1) {
					// Substituted "j$var42" with its value "0".
					// 
					// Set the current value to the current state of the tree.
					double traceTempVariable$var50$14_4 = Math.exp((cv$originalValue * x[i][0]));
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32bernoulli71$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32bernoulli71$global[i][0] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 72 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 72 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$14_7" with its value "0".
						// 
						// cv$temp$2$var66's comment
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var60" with its value "0".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((indicator[i][0] / ((traceTempVariable$var50$14_4 + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32bernoulli71$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32bernoulli71$global[i][1] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 72 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 72 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$14_7" with its value "1".
						// 
						// cv$temp$2$var66's comment
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var60" with its value "1".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((indicator[i][1] / ((traceTempVariable$var50$14_4 + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32bernoulli71$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32bernoulli71$global[i][2] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 72 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 72 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$14_7" with its value "2".
						// 
						// cv$temp$2$var66's comment
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var60" with its value "2".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((indicator[i][2] / ((traceTempVariable$var50$14_4 + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 1)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1) {
					// Substituted "j$var42" with its value "1".
					// 
					// Set the current value to the current state of the tree.
					double traceTempVariable$var52$15_4 = Math.exp((cv$originalValue * x[i][1]));
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32bernoulli71$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32bernoulli71$global[i][0] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 72 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 72 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$15_7" with its value "0".
						// 
						// cv$temp$3$var66's comment
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var60" with its value "0".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((indicator[i][0] / ((indicator[i][0] + traceTempVariable$var52$15_4) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32bernoulli71$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32bernoulli71$global[i][1] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 72 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 72 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$15_7" with its value "1".
						// 
						// cv$temp$3$var66's comment
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var60" with its value "1".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((indicator[i][1] / ((indicator[i][0] + traceTempVariable$var52$15_4) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32bernoulli71$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32bernoulli71$global[i][2] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 72 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 72 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$15_7" with its value "2".
						// 
						// cv$temp$3$var66's comment
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var60" with its value "2".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((indicator[i][2] / ((indicator[i][0] + traceTempVariable$var52$15_4) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 2)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1) {
					// Substituted "j$var42" with its value "2".
					// 
					// Set the current value to the current state of the tree.
					double traceTempVariable$var55$16_4 = Math.exp((cv$originalValue * x[i][2]));
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32bernoulli71$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32bernoulli71$global[i][0] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 72 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 72 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$16_7" with its value "0".
						// 
						// cv$temp$4$var66's comment
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var60" with its value "0".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var55$16_4)) + bias)) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32bernoulli71$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32bernoulli71$global[i][1] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 72 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 72 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$16_7" with its value "1".
						// 
						// cv$temp$4$var66's comment
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var60" with its value "1".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var55$16_4)) + bias)) + cv$accumulatedProbabilities);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32bernoulli71$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32bernoulli71$global[i][2] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 72 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 72 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$16_7" with its value "2".
						// 
						// cv$temp$4$var66's comment
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var60" with its value "2".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var55$16_4)) + bias)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 0)) {
				for(int i = 0; i < n; i += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32bernoulli71$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32bernoulli71$global[i][0] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 72 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 72 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$17_7" with its value "0".
						// 
						// cv$temp$5$var66's comment
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var42" with its value "0".
						// 
						// Set the current value to the current state of the tree.
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((Math.exp((cv$originalValue * x[i][0])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 1)) {
				for(int i = 0; i < n; i += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32bernoulli71$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32bernoulli71$global[i][1] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 72 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 72 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$17_7" with its value "1".
						// 
						// cv$temp$5$var66's comment
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var42" with its value "1".
						// 
						// Set the current value to the current state of the tree.
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((Math.exp((cv$originalValue * x[i][1])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 2)) {
				for(int i = 0; i < n; i += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32bernoulli71$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32bernoulli71$global[i][2] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 72 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 72 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "index$j$17_7" with its value "2".
						// 
						// cv$temp$5$var66's comment
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var42" with its value "2".
						// 
						// Set the current value to the current state of the tree.
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((Math.exp((cv$originalValue * x[i][2])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		weights[var27] = cv$proposedValue;
		
		// Guards to ensure that indicator is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 32 and consumer double[] 48.
		// 
		// Unrolled loop
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 0)) {
			for(int i = 0; i < n; i += 1)
				// Substituted "j$var42" with its value "0".
				indicator[i][0] = Math.exp((weights[0] * x[i][0]));
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 1)) {
			for(int i = 0; i < n; i += 1)
				// Substituted "j$var42" with its value "1".
				indicator[i][1] = Math.exp((weights[1] * x[i][1]));
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 2)) {
			for(int i = 0; i < n; i += 1)
				// Substituted "j$var42" with its value "2".
				indicator[i][2] = Math.exp((weights[2] * x[i][2]));
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 0)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1) {
				// Unrolled loop
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32put67$global[i][0] = false;
				
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32put67$global[i][1] = false;
				
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32put67$global[i][2] = false;
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 1)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1) {
				// Unrolled loop
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32put67$global[i][0] = false;
				
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32put67$global[i][1] = false;
				
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32put67$global[i][2] = false;
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 2)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1) {
				// Unrolled loop
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32put67$global[i][0] = false;
				
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32put67$global[i][1] = false;
				
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32put67$global[i][2] = false;
			}
		}
		
		// Unrolled loop
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 0)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32put67$global[i][0] = false;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 1)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32put67$global[i][1] = false;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 2)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32put67$global[i][2] = false;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 0)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1) {
				// Unrolled loop
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32put67$global[i][0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][0] = true;
					
					// Substituted "j$var60" with its value "0".
					p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32put67$global[i][1]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][1] = true;
					
					// Substituted "j$var60" with its value "1".
					p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32put67$global[i][2]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][2] = true;
					
					// Substituted "j$var60" with its value "2".
					p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 1)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1) {
				// Unrolled loop
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32put67$global[i][0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][0] = true;
					
					// Substituted "j$var60" with its value "0".
					p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32put67$global[i][1]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][1] = true;
					
					// Substituted "j$var60" with its value "1".
					p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32put67$global[i][2]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][2] = true;
					
					// Substituted "j$var60" with its value "2".
					p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 2)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1) {
				// Unrolled loop
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32put67$global[i][0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][0] = true;
					
					// Substituted "j$var60" with its value "0".
					p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32put67$global[i][1]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][1] = true;
					
					// Substituted "j$var60" with its value "1".
					p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32put67$global[i][2]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][2] = true;
					
					// Substituted "j$var60" with its value "2".
					p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 0)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample32put67$global[i][0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][0] = true;
					
					// Substituted "j$var60" with its value "0".
					p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 1)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample32put67$global[i][1]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][1] = true;
					
					// Substituted "j$var60" with its value "1".
					p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 2)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample32put67$global[i][2]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][2] = true;
					
					// Substituted "j$var60" with its value "2".
					p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var22" with its value "10.0".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 0)) {
			// Unrolled loop
			// Unrolled loop
			for(int i = 0; i < n; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32bernoulli71$global[i][0] = false;
			for(int i = 0; i < n; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32bernoulli71$global[i][1] = false;
			for(int i = 0; i < n; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32bernoulli71$global[i][2] = false;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 1)) {
			// Unrolled loop
			// Unrolled loop
			for(int i = 0; i < n; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32bernoulli71$global[i][0] = false;
			for(int i = 0; i < n; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32bernoulli71$global[i][1] = false;
			for(int i = 0; i < n; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32bernoulli71$global[i][2] = false;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 2)) {
			// Unrolled loop
			// Unrolled loop
			for(int i = 0; i < n; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32bernoulli71$global[i][0] = false;
			for(int i = 0; i < n; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32bernoulli71$global[i][1] = false;
			for(int i = 0; i < n; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32bernoulli71$global[i][2] = false;
		}
		
		// Unrolled loop
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 0)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32bernoulli71$global[i][0] = false;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 1)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32bernoulli71$global[i][1] = false;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 2)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32bernoulli71$global[i][2] = false;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 0)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1) {
				// Substituted "j$var42" with its value "0".
				double traceTempVariable$var50$14_4 = Math.exp((cv$proposedValue * x[i][0]));
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32bernoulli71$global[i][0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][0] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 72 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 72 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "index$j$14_7" with its value "0".
					// 
					// cv$temp$2$var66's comment
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var60" with its value "0".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((indicator[i][0] / ((traceTempVariable$var50$14_4 + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32bernoulli71$global[i][1]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][1] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 72 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 72 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "index$j$14_7" with its value "1".
					// 
					// cv$temp$2$var66's comment
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var60" with its value "1".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((indicator[i][1] / ((traceTempVariable$var50$14_4 + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32bernoulli71$global[i][2]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][2] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 72 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 72 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "index$j$14_7" with its value "2".
					// 
					// cv$temp$2$var66's comment
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var60" with its value "2".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((indicator[i][2] / ((traceTempVariable$var50$14_4 + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 1)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1) {
				// Substituted "j$var42" with its value "1".
				double traceTempVariable$var52$15_4 = Math.exp((cv$proposedValue * x[i][1]));
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32bernoulli71$global[i][0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][0] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 72 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 72 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "index$j$15_7" with its value "0".
					// 
					// cv$temp$3$var66's comment
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var60" with its value "0".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((indicator[i][0] / ((indicator[i][0] + traceTempVariable$var52$15_4) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32bernoulli71$global[i][1]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][1] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 72 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 72 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "index$j$15_7" with its value "1".
					// 
					// cv$temp$3$var66's comment
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var60" with its value "1".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((indicator[i][1] / ((indicator[i][0] + traceTempVariable$var52$15_4) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32bernoulli71$global[i][2]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][2] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 72 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 72 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "index$j$15_7" with its value "2".
					// 
					// cv$temp$3$var66's comment
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var60" with its value "2".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((indicator[i][2] / ((indicator[i][0] + traceTempVariable$var52$15_4) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 2)) {
			// Unrolled loop
			for(int i = 0; i < n; i += 1) {
				// Substituted "j$var42" with its value "2".
				double traceTempVariable$var55$16_4 = Math.exp((cv$proposedValue * x[i][2]));
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32bernoulli71$global[i][0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][0] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 72 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 72 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "index$j$16_7" with its value "0".
					// 
					// cv$temp$4$var66's comment
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var60" with its value "0".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var55$16_4)) + bias)) + cv$accumulatedProbabilities);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32bernoulli71$global[i][1]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][1] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 72 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 72 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "index$j$16_7" with its value "1".
					// 
					// cv$temp$4$var66's comment
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var60" with its value "1".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var55$16_4)) + bias)) + cv$accumulatedProbabilities);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32bernoulli71$global[i][2]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][2] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 72 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 72 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "index$j$16_7" with its value "2".
					// 
					// cv$temp$4$var66's comment
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var60" with its value "2".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var55$16_4)) + bias)) + cv$accumulatedProbabilities);
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 0)) {
			for(int i = 0; i < n; i += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32bernoulli71$global[i][0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][0] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 72 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 72 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "index$j$17_7" with its value "0".
					// 
					// cv$temp$5$var66's comment
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var42" with its value "0".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((Math.exp((cv$proposedValue * x[i][0])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 1)) {
			for(int i = 0; i < n; i += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32bernoulli71$global[i][1]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][1] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 72 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 72 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "index$j$17_7" with its value "1".
					// 
					// cv$temp$5$var66's comment
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var42" with its value "1".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((Math.exp((cv$proposedValue * x[i][1])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 2)) {
			for(int i = 0; i < n; i += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32bernoulli71$global[i][2]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32bernoulli71$global[i][2] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 72 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 72 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "index$j$17_7" with its value "2".
					// 
					// cv$temp$5$var66's comment
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var42" with its value "2".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((Math.exp((cv$proposedValue * x[i][2])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
			}
		}
		
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
			weights[var27] = cv$originalValue;
			
			// Guards to ensure that indicator is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 32 and consumer double[] 48.
			// 
			// Unrolled loop
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 0)) {
				for(int i = 0; i < n; i += 1)
					// Substituted "j$var42" with its value "0".
					indicator[i][0] = Math.exp((weights[0] * x[i][0]));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 1)) {
				for(int i = 0; i < n; i += 1)
					// Substituted "j$var42" with its value "1".
					indicator[i][1] = Math.exp((weights[1] * x[i][1]));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 2)) {
				for(int i = 0; i < n; i += 1)
					// Substituted "j$var42" with its value "2".
					indicator[i][2] = Math.exp((weights[2] * x[i][2]));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 0)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1) {
					// Unrolled loop
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][0] = false;
					
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][1] = false;
					
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][2] = false;
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 1)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1) {
					// Unrolled loop
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][0] = false;
					
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][1] = false;
					
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][2] = false;
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 2)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1) {
					// Unrolled loop
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][0] = false;
					
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][1] = false;
					
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][2] = false;
				}
			}
			
			// Unrolled loop
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 0)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][0] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 1)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][1] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 2)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put67$global[i][2] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 0)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1) {
					// Unrolled loop
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32put67$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32put67$global[i][0] = true;
						
						// Substituted "j$var60" with its value "0".
						p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32put67$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32put67$global[i][1] = true;
						
						// Substituted "j$var60" with its value "1".
						p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32put67$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32put67$global[i][2] = true;
						
						// Substituted "j$var60" with its value "2".
						p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 1)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1) {
					// Unrolled loop
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32put67$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32put67$global[i][0] = true;
						
						// Substituted "j$var60" with its value "0".
						p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32put67$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32put67$global[i][1] = true;
						
						// Substituted "j$var60" with its value "1".
						p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32put67$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32put67$global[i][2] = true;
						
						// Substituted "j$var60" with its value "2".
						p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 2)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1) {
					// Unrolled loop
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32put67$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32put67$global[i][0] = true;
						
						// Substituted "j$var60" with its value "0".
						p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32put67$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32put67$global[i][1] = true;
						
						// Substituted "j$var60" with its value "1".
						p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample32put67$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32put67$global[i][2] = true;
						
						// Substituted "j$var60" with its value "2".
						p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 0)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample32put67$global[i][0]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32put67$global[i][0] = true;
						
						// Substituted "j$var60" with its value "0".
						p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 1)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample32put67$global[i][1]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32put67$global[i][1] = true;
						
						// Substituted "j$var60" with its value "1".
						p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var27 == 2)) {
				// Unrolled loop
				for(int i = 0; i < n; i += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample32put67$global[i][2]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32put67$global[i][2] = true;
						
						// Substituted "j$var60" with its value "2".
						p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 39 drawn from Gaussian 34. Inference was performed using Metropolis-Hastings.
	private final void sample39() {
		// The original value of the sample
		double cv$originalValue = bias;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		// 
		// The original value of the sample
		double cv$var = ((bias * bias) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		// 
		// The original value of the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + bias);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var33" with its value "10.0".
			// 
			// Set the current value to the current state of the tree.
			// 
			// The original value of the sample
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((bias / 3.1622776601683795)) - 1.151292546497023);
			
			// Processing random variable 67.
			for(int i = 0; i < n; i += 1) {
				// Unrolled loop
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 72 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 72 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "j$var60" with its value "0".
				// 
				// Substituted "cv$temp$2$var66" with its value "var66".
				// 
				// Constructing a random variable input for use later.
				// 
				// Substituted "j$var60" with its value "0".
				// 
				// Set the current value to the current state of the tree.
				// 
				// The original value of the sample
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], (p[i][0] + bias)) + cv$accumulatedProbabilities);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 72 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 72 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "j$var60" with its value "1".
				// 
				// Substituted "cv$temp$2$var66" with its value "var66".
				// 
				// Constructing a random variable input for use later.
				// 
				// Substituted "j$var60" with its value "1".
				// 
				// Set the current value to the current state of the tree.
				// 
				// The original value of the sample
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], (p[i][1] + bias)) + cv$accumulatedProbabilities);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 72 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 72 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "j$var60" with its value "2".
				// 
				// Substituted "cv$temp$2$var66" with its value "var66".
				// 
				// Constructing a random variable input for use later.
				// 
				// Substituted "j$var60" with its value "2".
				// 
				// Set the current value to the current state of the tree.
				// 
				// The original value of the sample
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], (p[i][2] + bias)) + cv$accumulatedProbabilities);
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		// 
		// Write out the new value of the sample.
		bias = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var33" with its value "10.0".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		
		// Processing random variable 67.
		for(int i = 0; i < n; i += 1) {
			// Unrolled loop
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 72 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 72 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "j$var60" with its value "0".
			// 
			// Substituted "cv$temp$2$var66" with its value "var66".
			// 
			// Constructing a random variable input for use later.
			// 
			// Substituted "j$var60" with its value "0".
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], (p[i][0] + cv$proposedValue)) + cv$accumulatedProbabilities);
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 72 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 72 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "j$var60" with its value "1".
			// 
			// Substituted "cv$temp$2$var66" with its value "var66".
			// 
			// Constructing a random variable input for use later.
			// 
			// Substituted "j$var60" with its value "1".
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], (p[i][1] + cv$proposedValue)) + cv$accumulatedProbabilities);
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 72 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 72 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "j$var60" with its value "2".
			// 
			// Substituted "cv$temp$2$var66" with its value "var66".
			// 
			// Constructing a random variable input for use later.
			// 
			// Substituted "j$var60" with its value "2".
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], (p[i][2] + cv$proposedValue)) + cv$accumulatedProbabilities);
		}
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Variable declaration of cv$proposedProbability moved.
		// Declaration comment was:
		// The probability of the random variable generating the new sample value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the new value of the sample.
			bias = cv$originalValue;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for guard$sample32put67$global
		{
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var60 = 0;
			if((0 < x.length))
				cv$max_j$var60 = 3;
			
			// Allocation of guard$sample32put67$global for single threaded execution
			guard$sample32put67$global = new boolean[x.length][cv$max_j$var60];
		}
		
		// Constructor for guard$sample32bernoulli71$global
		// 
		// Calculate the largest index of j that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_j$var60 = 0;
		if((0 < x.length))
			cv$max_j$var60 = 3;
		
		// Allocation of guard$sample32bernoulli71$global for single threaded execution
		guard$sample32bernoulli71$global = new boolean[x.length][cv$max_j$var60];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If y has not been set already allocate space.
		if(!setFlag$y) {
			// Constructor for y
			y = new boolean[x.length][];
			for(int var16 = 0; var16 < x.length; var16 += 1)
				y[var16] = new boolean[3];
		}
		
		// If weights has not been set already allocate space.
		if(!setFlag$weights)
			// Constructor for weights
			weights = new double[3];
		
		// Constructor for indicator
		indicator = new double[x.length][];
		for(int i = 0; i < x.length; i += 1)
			indicator[i] = new double[3];
		
		// Constructor for p
		p = new double[x.length][];
		for(int i = 0; i < x.length; i += 1)
			p[i] = new double[3];
		
		// Constructor for logProbability$sample32
		logProbability$sample32 = new double[3];
		
		// Constructor for logProbability$var67
		logProbability$var67 = new double[x.length][];
		for(int i = 0; i < x.length; i += 1)
			logProbability$var67[i] = new double[3];
		
		// Constructor for logProbability$sample72
		logProbability$sample72 = new double[x.length][];
		for(int i = 0; i < x.length; i += 1)
			logProbability$sample72[i] = new double[3];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32) {
			weights[0] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[1] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[2] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample39)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		for(int i = 0; i < n; i += 1) {
			boolean[] var64 = y[i];
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample32) {
				// Substituted "j$var42" with its value "0".
				indicator[i][0] = Math.exp((weights[0] * x[i][0]));
				
				// Substituted "j$var42" with its value "1".
				indicator[i][1] = Math.exp((weights[1] * x[i][1]));
				
				// Substituted "j$var42" with its value "2".
				indicator[i][2] = Math.exp((weights[2] * x[i][2]));
				
				// Substituted "j$var60" with its value "0".
				p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			}
			if(!fixedFlag$sample72)
				// Substituted "j$var60" with its value "0".
				var64[0] = DistributionSampling.sampleBernoulli(RNG$, (p[i][0] + bias));
			if(!fixedFlag$sample32)
				// Substituted "j$var60" with its value "1".
				p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			if(!fixedFlag$sample72)
				// Substituted "j$var60" with its value "1".
				var64[1] = DistributionSampling.sampleBernoulli(RNG$, (p[i][1] + bias));
			if(!fixedFlag$sample32)
				// Substituted "j$var60" with its value "2".
				p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			if(!fixedFlag$sample72)
				// Substituted "j$var60" with its value "2".
				var64[2] = DistributionSampling.sampleBernoulli(RNG$, (p[i][2] + bias));
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32) {
			weights[0] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[1] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[2] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample39)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32) {
			for(int i = 0; i < n; i += 1) {
				// Substituted "j$var42" with its value "0".
				indicator[i][0] = Math.exp((weights[0] * x[i][0]));
				
				// Substituted "j$var42" with its value "1".
				indicator[i][1] = Math.exp((weights[1] * x[i][1]));
				
				// Substituted "j$var42" with its value "2".
				indicator[i][2] = Math.exp((weights[2] * x[i][2]));
				
				// Substituted "j$var60" with its value "0".
				p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				
				// Substituted "j$var60" with its value "1".
				p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				
				// Substituted "j$var60" with its value "2".
				p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32) {
			weights[0] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[1] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[2] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample39)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32) {
			for(int i = 0; i < n; i += 1) {
				// Substituted "j$var42" with its value "0".
				indicator[i][0] = Math.exp((weights[0] * x[i][0]));
				
				// Substituted "j$var42" with its value "1".
				indicator[i][1] = Math.exp((weights[1] * x[i][1]));
				
				// Substituted "j$var42" with its value "2".
				indicator[i][2] = Math.exp((weights[2] * x[i][2]));
				
				// Substituted "j$var60" with its value "0".
				p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				
				// Substituted "j$var60" with its value "1".
				p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				
				// Substituted "j$var60" with its value "2".
				p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample32) {
				sample32(0);
				sample32(1);
				sample32(2);
			}
			if(!fixedFlag$sample39)
				sample39();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample39)
				sample39();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample32) {
				sample32(2);
				sample32(1);
				sample32(0);
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
		logProbability$var23 = 0.0;
		logProbability$weights = 0.0;
		logProbability$p = 0.0;
		logProbability$indicator = 0.0;
		if(!fixedProbFlag$sample32) {
			// Unrolled loop
			logProbability$sample32[0] = 0.0;
			logProbability$sample32[1] = 0.0;
			logProbability$sample32[2] = 0.0;
		}
		logProbability$var34 = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$bias = 0.0;
		for(int i = 0; i < n; i += 1) {
			// Unrolled loop
			// Substituted "j$var60" with its value "0".
			logProbability$var67[i][0] = 0.0;
			
			// Substituted "j$var60" with its value "1".
			logProbability$var67[i][1] = 0.0;
			
			// Substituted "j$var60" with its value "2".
			logProbability$var67[i][2] = 0.0;
		}
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample72) {
			for(int i = 0; i < n; i += 1) {
				// Unrolled loop
				// Substituted "j$var60" with its value "0".
				logProbability$sample72[i][0] = 0.0;
				
				// Substituted "j$var60" with its value "1".
				logProbability$sample72[i][1] = 0.0;
				
				// Substituted "j$var60" with its value "2".
				logProbability$sample72[i][2] = 0.0;
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
		if(fixedFlag$sample39)
			logProbabilityValue$sample39();
		logProbabilityValue$sample72();
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
		logProbabilityValue$sample39();
		logProbabilityValue$sample72();
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
		logProbabilityValue$sample39();
		logProbabilityValue$sample72();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32) {
			weights[0] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[1] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[2] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample39)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32) {
			for(int i = 0; i < n; i += 1) {
				// Substituted "j$var42" with its value "0".
				indicator[i][0] = Math.exp((weights[0] * x[i][0]));
				
				// Substituted "j$var42" with its value "1".
				indicator[i][1] = Math.exp((weights[1] * x[i][1]));
				
				// Substituted "j$var42" with its value "2".
				indicator[i][2] = Math.exp((weights[2] * x[i][2]));
				
				// Substituted "j$var60" with its value "0".
				p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				
				// Substituted "j$var60" with its value "1".
				p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				
				// Substituted "j$var60" with its value "2".
				p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
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
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = y.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = yMeasured[cv$index1];
			boolean[] cv$target2 = y[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(setFlag$weights) {
			for(int i = 0; i < n; i += 1) {
				// Substituted "j$var42" with its value "0".
				indicator[i][0] = Math.exp((weights[0] * x[i][0]));
				
				// Substituted "j$var42" with its value "1".
				indicator[i][1] = Math.exp((weights[1] * x[i][1]));
				
				// Substituted "j$var42" with its value "2".
				indicator[i][2] = Math.exp((weights[2] * x[i][2]));
				
				// Substituted "j$var60" with its value "0".
				p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				
				// Substituted "j$var60" with its value "1".
				p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				
				// Substituted "j$var60" with its value "2".
				p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel LogitRegressionTest(double[][] x, boolean[][] yMeasured) {\n    int k = 3;\n\n    int n = x.length;\n    boolean[][] y = new boolean[n][k];\n\n    double[] weights = gaussian(0,10).sample(k);\n    //TODO, change this to a beta distribution.\n    double bias = gaussian(0,10).sample();\n\n    for(int i:[0 .. n)) {\n        double[] indicator = new double[k];\n        for(int j:[0 .. k)) {\n            indicator[j] = exp(weights[j] * x[i][j]);\n        }\n        \n        //Single assignment semantics means a for loop cannot be used here.\n        double sum = indicator[0] + indicator[1] + indicator[2];\n        double[] p = new double[k];\n\n        for(int j:[0 .. k)) {\n            p[j] = indicator[j]/sum;\n            //This really wants to be a Categorical, but for now y will have\n            //to be arrays with just a single value set.\n            y[i][j] = bernoulli(p[j] + bias).sample();\n        }    \n    }\n\n    y.observe(yMeasured);\n}\n";
	}
}