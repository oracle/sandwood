package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LinearRegressionBasic2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LinearRegressionBasic2$CoreInterface {
	
	// Declare the variables for the model.
	private double b0;
	private double b1;
	private boolean fixedFlag$sample14 = false;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample33 = false;
	private boolean fixedProbFlag$sample14 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample33 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b0;
	private double logProbability$b1;
	private double[] logProbability$sample33;
	private double logProbability$var10;
	private double logProbability$var14;
	private double logProbability$var19;
	private double logProbability$var20;
	private double[] logProbability$var29;
	private double logProbability$variance;
	private double logProbability$y;
	private int noSamples;
	private boolean setFlag$y = false;
	private boolean system$gibbsForward = true;
	private double variance;
	private double[] x;
	private double[] y;
	private double[] yMeasured;

	public LinearRegressionBasic2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for b0.
	@Override
	public final double get$b0() {
		return b0;
	}

	// Setter for b0.
	@Override
	public final void set$b0(double cv$value) {
		b0 = cv$value;
	}

	// Getter for b1.
	@Override
	public final double get$b1() {
		return b1;
	}

	// Setter for b1.
	@Override
	public final void set$b1(double cv$value) {
		b1 = cv$value;
	}

	// Getter for fixedFlag$sample14.
	@Override
	public final boolean get$fixedFlag$sample14() {
		return fixedFlag$sample14;
	}

	// Setter for fixedFlag$sample14.
	@Override
	public final void set$fixedFlag$sample14(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample14 including if probabilities
		// need to be updated.
		fixedFlag$sample14 = cv$value;
		
		// Should the probability of sample 14 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample14" with its value "cv$value".
		fixedProbFlag$sample14 = (cv$value && fixedProbFlag$sample14);
		
		// Should the probability of sample 33 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample14" with its value "cv$value".
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
	}

	// Getter for fixedFlag$sample18.
	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	// Setter for fixedFlag$sample18.
	@Override
	public final void set$fixedFlag$sample18(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample18 including if probabilities
		// need to be updated.
		fixedFlag$sample18 = cv$value;
		
		// Should the probability of sample 18 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample18" with its value "cv$value".
		fixedProbFlag$sample18 = (cv$value && fixedProbFlag$sample18);
		
		// Should the probability of sample 33 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample18" with its value "cv$value".
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
	}

	// Getter for fixedFlag$sample23.
	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	// Setter for fixedFlag$sample23.
	@Override
	public final void set$fixedFlag$sample23(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample23 including if probabilities
		// need to be updated.
		fixedFlag$sample23 = cv$value;
		
		// Should the probability of sample 23 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample23" with its value "cv$value".
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
		
		// Should the probability of sample 33 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample23" with its value "cv$value".
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
	}

	// Getter for fixedFlag$sample33.
	@Override
	public final boolean get$fixedFlag$sample33() {
		return fixedFlag$sample33;
	}

	// Setter for fixedFlag$sample33.
	@Override
	public final void set$fixedFlag$sample33(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample33 including if probabilities
		// need to be updated.
		fixedFlag$sample33 = cv$value;
		
		// Should the probability of sample 33 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample33" with its value "cv$value".
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
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

	// Getter for logProbability$b0.
	@Override
	public final double get$logProbability$b0() {
		return logProbability$b0;
	}

	// Getter for logProbability$b1.
	@Override
	public final double get$logProbability$b1() {
		return logProbability$b1;
	}

	// Getter for logProbability$variance.
	@Override
	public final double get$logProbability$variance() {
		return logProbability$variance;
	}

	// Getter for logProbability$y.
	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	// Getter for noSamples.
	@Override
	public final int get$noSamples() {
		return noSamples;
	}

	// Getter for variance.
	@Override
	public final double get$variance() {
		return variance;
	}

	// Setter for variance.
	@Override
	public final void set$variance(double cv$value) {
		variance = cv$value;
	}

	// Getter for x.
	@Override
	public final double[] get$x() {
		return x;
	}

	// Setter for x.
	@Override
	public final void set$x(double[] cv$value) {
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
		// Set y with flag to mark that it has been set so another array doesn't need to be
		// constructed
		y = cv$value;
		setFlag$y = true;
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

	// Calculate the probability of the samples represented by sample14 using sampled
	// values.
	private final void logProbabilityValue$sample14() {
		// Determine if we need to calculate the values for sample task 14 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample14) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(b0, 0.0, 2.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var10 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$b0 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample14)
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
			fixedProbFlag$sample14 = fixedFlag$sample14;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var10 = logProbability$b0;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$b0);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample14)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$b0);
		}
	}

	// Calculate the probability of the samples represented by sample18 using sampled
	// values.
	private final void logProbabilityValue$sample18() {
		// Determine if we need to calculate the values for sample task 18 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample18) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(b1, 1.0, 5.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var14 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$b1 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample18)
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
			fixedProbFlag$sample18 = fixedFlag$sample18;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var14 = logProbability$b1;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$b1);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample18)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$b1);
		}
	}

	// Calculate the probability of the samples represented by sample23 using sampled
	// values.
	private final void logProbabilityValue$sample23() {
		// Determine if we need to calculate the values for sample task 23 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample23) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityGamma((1 / variance), 1.0, 1.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var19 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var20 = cv$distributionAccumulator;
			
			// Update the variable probability
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
			logProbability$variance = (logProbability$variance + cv$distributionAccumulator);
			
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
			if(fixedFlag$sample23)
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
			fixedProbFlag$sample23 = fixedFlag$sample23;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var19 = logProbability$var20;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$variance = (logProbability$variance + logProbability$var20);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var20);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample23)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var20);
		}
	}

	// Calculate the probability of the samples represented by sample33 using sampled
	// values.
	private final void logProbabilityValue$sample33() {
		// Determine if we need to calculate the values for sample task 33 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample33) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < noSamples; i += 1) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(y[i], (b0 + (b1 * x[i])), variance);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var29[i] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample33[i] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$y = (logProbability$y + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample33 = (((fixedFlag$sample33 && fixedFlag$sample14) && fixedFlag$sample18) && fixedFlag$sample23);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < noSamples; i += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample33[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var29[i] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$y = (logProbability$y + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 14 drawn from Gaussian 10. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample14() {
		// State to record the weighting of each sample that is consumed. This is the:
		// sum of the sample denominator*(the sample value - the sample nominator).
		double cv$sum = 0.0;
		
		// State for storing the sum of the squares of the sample denominators.
		double cv$denominatorSquareSum = 0.0;
		
		// Flag to record if we have a value for Sigma.
		boolean cv$sigmaNotFound = true;
		
		// State for the value of sigma once we find it.
		double cv$sigmaValue = 1.0;
		
		// Processing random variable 29.
		for(int i = 0; i < noSamples; i += 1) {
			// Processing sample task 33 of consumer random variable null.
			// Record the value of a sample generated by a consuming sample 33 of random variable
			// var29.
			// 
			// Add the denominator squared to the sample denominator
			// 
			// cv$denominator's comment
			// State for tracking the changes that happen to the sampled value between it being
			// consumed and it being produced.
			cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
			
			// Add the weighting of the sample to the sum.
			// 
			// cv$denominator's comment
			// State for tracking the changes that happen to the sampled value between it being
			// consumed and it being produced.
			// 
			// cv$numerator's comment
			// Substituted "cv$numerator" with its value "0.0".
			cv$sum = ((cv$sum + y[i]) - (b1 * x[i]));
			
			// If we have not got the value of sigma yet record it and set a flag so it is not
			// recorded again.
			if(cv$sigmaNotFound) {
				cv$sigmaValue = variance;
				cv$sigmaNotFound = false;
			}
		}
		
		// Write out the new value of the sample.
		b0 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 2.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 18 drawn from Gaussian 14. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample18() {
		// State to record the weighting of each sample that is consumed. This is the:
		// sum of the sample denominator*(the sample value - the sample nominator).
		double cv$sum = 0.0;
		
		// State for storing the sum of the squares of the sample denominators.
		double cv$denominatorSquareSum = 0.0;
		
		// Flag to record if we have a value for Sigma.
		boolean cv$sigmaNotFound = true;
		
		// State for the value of sigma once we find it.
		double cv$sigmaValue = 1.0;
		
		// Processing random variable 29.
		for(int i = 0; i < noSamples; i += 1) {
			// Processing sample task 33 of consumer random variable null.
			// Variable declaration of cv$denominator moved.
			// Declaration comment was:
			// State for tracking the changes that happen to the sampled value between it being
			// consumed and it being produced.
			// 
			// cv$denominator's comment
			// State for tracking the changes that happen to the sampled value between it being
			// consumed and it being produced.
			double cv$denominator = x[i];
			
			// Record the value of a sample generated by a consuming sample 33 of random variable
			// var29.
			// 
			// Add the denominator squared to the sample denominator
			cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
			
			// Add the weighting of the sample to the sum.
			// 
			// cv$numerator's comment
			// 
			// cv$numerator's comment
			// Substituted "cv$numerator" with its value "0.0".
			cv$sum = (cv$sum + (cv$denominator * (y[i] - b0)));
			
			// If we have not got the value of sigma yet record it and set a flag so it is not
			// recorded again.
			if(cv$sigmaNotFound) {
				cv$sigmaValue = variance;
				cv$sigmaNotFound = false;
			}
		}
		
		// Write out the new value of the sample.
		b1 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 1.0, 5.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 23 drawn from Gamma 19. Inference was performed using a Gamma to
	// Gaussian conjugate prior.
	private final void sample23() {
		// Variable to track the sum of the difference between the samples and the random
		// variables mean squared.
		double cv$sum = 0.0;
		
		// Variable to record how many samples have been included in this calculation.
		int cv$count = 0;
		
		// Processing random variable 29.
		for(int i = 0; i < noSamples; i += 1) {
			// Processing sample task 33 of consumer random variable null.
			// Consume sample task 33 from random variable var29.
			// 
			// The difference between the mean parameter and the value sampled from the Gaussian.
			// 
			// The mean parameter for Gaussian var29.
			double cv$var29$diff = ((b0 + (b1 * x[i])) - y[i]);
			
			// Include this sample by adding the square of the difference to the sum.
			cv$sum = (cv$sum + (cv$var29$diff * cv$var29$diff));
			
			// Increment the number of samples in the calculation.
			cv$count = (cv$count + 1);
		}
		
		// Write out the new sample value.
		// 
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		variance = (1 / Conjugates.sampleConjugateGammaGaussian(RNG$, 1.0, 1.0, cv$sum, cv$count));
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
		if(!setFlag$y)
			// Constructor for y
			y = new double[x.length];
		
		// Constructor for logProbability$var29
		logProbability$var29 = new double[x.length];
		
		// Constructor for logProbability$sample33
		logProbability$sample33 = new double[x.length];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample14)
			b0 = DistributionSampling.sampleGaussian(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample18)
			b1 = DistributionSampling.sampleGaussian(RNG$, 1.0, 5.0);
		if(!fixedFlag$sample23)
			variance = (1 / DistributionSampling.sampleGamma(RNG$, 1.0, 1.0));
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample33)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							y[i] = DistributionSampling.sampleGaussian(RNG$1, (b0 + (b1 * x[i])), variance);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample14)
			b0 = DistributionSampling.sampleGaussian(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample18)
			b1 = DistributionSampling.sampleGaussian(RNG$, 1.0, 5.0);
		if(!fixedFlag$sample23)
			variance = (1 / DistributionSampling.sampleGamma(RNG$, 1.0, 1.0));
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample14)
			b0 = DistributionSampling.sampleGaussian(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample18)
			b1 = DistributionSampling.sampleGaussian(RNG$, 1.0, 5.0);
		if(!fixedFlag$sample23)
			variance = (1 / DistributionSampling.sampleGamma(RNG$, 1.0, 1.0));
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample14)
				sample14();
			if(!fixedFlag$sample18)
				sample18();
			if(!fixedFlag$sample23)
				sample23();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample23)
				sample23();
			if(!fixedFlag$sample18)
				sample18();
			if(!fixedFlag$sample14)
				sample14();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		noSamples = x.length;
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
		logProbability$var10 = 0.0;
		if(!fixedProbFlag$sample14)
			logProbability$b0 = 0.0;
		logProbability$var14 = 0.0;
		if(!fixedProbFlag$sample18)
			logProbability$b1 = 0.0;
		logProbability$var19 = 0.0;
		logProbability$variance = 0.0;
		if(!fixedProbFlag$sample23)
			logProbability$var20 = 0.0;
		for(int i = 0; i < noSamples; i += 1)
			logProbability$var29[i] = 0.0;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample33) {
			for(int i = 0; i < noSamples; i += 1)
				logProbability$sample33[i] = 0.0;
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
		if(fixedFlag$sample14)
			logProbabilityValue$sample14();
		if(fixedFlag$sample18)
			logProbabilityValue$sample18();
		if(fixedFlag$sample23)
			logProbabilityValue$sample23();
		logProbabilityValue$sample33();
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
		logProbabilityValue$sample14();
		logProbabilityValue$sample18();
		logProbabilityValue$sample23();
		logProbabilityValue$sample33();
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
		logProbabilityValue$sample14();
		logProbabilityValue$sample18();
		logProbabilityValue$sample23();
		logProbabilityValue$sample33();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample14)
			b0 = DistributionSampling.sampleGaussian(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample18)
			b1 = DistributionSampling.sampleGaussian(RNG$, 1.0, 5.0);
		if(!fixedFlag$sample23)
			variance = (1 / DistributionSampling.sampleGamma(RNG$, 1.0, 1.0));
		
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
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			y[cv$index1] = yMeasured[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model LinearRegressionBasic2(double[] x, double[] yMeasured) {\n    \n        int noSamples = x.length;\n        double b0 = gaussian(0.0, 2.0).sample();\n        double b1 = gaussian(1.0, 5.0).sample();\n        double variance = 1/gamma(1.0, 1.0).sample();\n        double[] y = new double[noSamples];\n        for(int i:[0..noSamples)) {\n           y[i] = gaussian(b0 + b1 * x[i], variance).sample();\n        }\n        y.observe(yMeasured);\n}\n";
	}
}