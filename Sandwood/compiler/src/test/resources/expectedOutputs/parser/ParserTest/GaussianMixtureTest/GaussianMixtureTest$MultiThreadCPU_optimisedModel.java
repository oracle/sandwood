package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class GaussianMixtureTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements GaussianMixtureTest$CoreInterface {
	
	// Declare the variables for the model.
	private double[] alpha;
	private double[] cv$var12$countGlobal;
	private double[][] cv$var42$stateProbabilityGlobal;
	private boolean fixedFlag$sample13 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample49 = false;
	private boolean fixedProbFlag$sample13 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample49 = false;
	private int length$xMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$mu;
	private double logProbability$phi;
	private double[] logProbability$sample45;
	private double[] logProbability$sample49;
	private double logProbability$sigma;
	private double logProbability$var11;
	private double logProbability$var17;
	private double logProbability$var22;
	private double logProbability$var28;
	private double logProbability$var33;
	private double[] logProbability$var41;
	private double[] logProbability$var45;
	private double logProbability$x;
	private double logProbability$z;
	private double[] mu;
	private double[] phi;
	private boolean setFlag$mu = false;
	private boolean setFlag$phi = false;
	private boolean setFlag$sigma = false;
	private boolean setFlag$x = false;
	private boolean setFlag$z = false;
	private double[] sigma;
	private boolean system$gibbsForward = true;
	private double[] x;
	private double[] xMeasured;
	private int[] z;

	public GaussianMixtureTest$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for alpha.
	@Override
	public final double[] get$alpha() {
		return alpha;
	}

	// Getter for fixedFlag$sample13.
	@Override
	public final boolean get$fixedFlag$sample13() {
		return fixedFlag$sample13;
	}

	// Setter for fixedFlag$sample13.
	@Override
	public final void set$fixedFlag$sample13(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample13 including if probabilities
		// need to be updated.
		fixedFlag$sample13 = cv$value;
		
		// Should the probability of sample 13 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample13" with its value "cv$value".
		fixedProbFlag$sample13 = (cv$value && fixedProbFlag$sample13);
		
		// Should the probability of sample 45 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample13" with its value "cv$value".
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
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
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample23" with its value "cv$value".
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	// Getter for fixedFlag$sample34.
	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	// Setter for fixedFlag$sample34.
	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample34 including if probabilities
		// need to be updated.
		fixedFlag$sample34 = cv$value;
		
		// Should the probability of sample 34 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	// Getter for fixedFlag$sample45.
	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	// Setter for fixedFlag$sample45.
	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample45 including if probabilities
		// need to be updated.
		fixedFlag$sample45 = cv$value;
		
		// Should the probability of sample 45 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample45" with its value "cv$value".
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample45" with its value "cv$value".
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	// Getter for fixedFlag$sample49.
	@Override
	public final boolean get$fixedFlag$sample49() {
		return fixedFlag$sample49;
	}

	// Setter for fixedFlag$sample49.
	@Override
	public final void set$fixedFlag$sample49(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample49 including if probabilities
		// need to be updated.
		fixedFlag$sample49 = cv$value;
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample49" with its value "cv$value".
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	// Getter for k.
	@Override
	public final int get$k() {
		return 5;
	}

	// Getter for length$xMeasured.
	@Override
	public final int get$length$xMeasured() {
		return length$xMeasured;
	}

	// Setter for length$xMeasured.
	@Override
	public final void set$length$xMeasured(int cv$value) {
		length$xMeasured = cv$value;
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

	// Getter for logProbability$mu.
	@Override
	public final double get$logProbability$mu() {
		return logProbability$mu;
	}

	// Getter for logProbability$phi.
	@Override
	public final double get$logProbability$phi() {
		return logProbability$phi;
	}

	// Getter for logProbability$sigma.
	@Override
	public final double get$logProbability$sigma() {
		return logProbability$sigma;
	}

	// Getter for logProbability$x.
	@Override
	public final double get$logProbability$x() {
		return logProbability$x;
	}

	// Getter for logProbability$z.
	@Override
	public final double get$logProbability$z() {
		return logProbability$z;
	}

	// Getter for mu.
	@Override
	public final double[] get$mu() {
		return mu;
	}

	// Setter for mu.
	@Override
	public final void set$mu(double[] cv$value) {
		// Set mu with flag to mark that it has been set so another array doesn't need to
		// be constructed
		mu = cv$value;
		setFlag$mu = true;
	}

	// Getter for phi.
	@Override
	public final double[] get$phi() {
		return phi;
	}

	// Setter for phi.
	@Override
	public final void set$phi(double[] cv$value) {
		// Set phi with flag to mark that it has been set so another array doesn't need to
		// be constructed
		phi = cv$value;
		setFlag$phi = true;
	}

	// Getter for sigma.
	@Override
	public final double[] get$sigma() {
		return sigma;
	}

	// Setter for sigma.
	@Override
	public final void set$sigma(double[] cv$value) {
		// Set sigma with flag to mark that it has been set so another array doesn't need
		// to be constructed
		sigma = cv$value;
		setFlag$sigma = true;
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
		setFlag$x = true;
	}

	// Getter for xMeasured.
	@Override
	public final double[] get$xMeasured() {
		return xMeasured;
	}

	// Setter for xMeasured.
	@Override
	public final void set$xMeasured(double[] cv$value) {
		// Set xMeasured with flag to mark that it has been set so another array doesn't need
		// to be constructed
		xMeasured = cv$value;
	}

	// Getter for z.
	@Override
	public final int[] get$z() {
		return z;
	}

	// Setter for z.
	@Override
	public final void set$z(int[] cv$value) {
		// Set z with flag to mark that it has been set so another array doesn't need to be
		// constructed
		z = cv$value;
		setFlag$z = true;
	}

	// Calculate the probability of the samples represented by sample13 using sampled
	// values.
	private final void logProbabilityValue$sample13() {
		// Determine if we need to calculate the values for sample task 13 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample13) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(phi, alpha);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var11 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$phi = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample13)
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
			fixedProbFlag$sample13 = fixedFlag$sample13;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var11 = logProbability$phi;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$phi);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample13)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$phi);
		}
	}

	// Calculate the probability of the samples represented by sample23 using sampled
	// values.
	private final void logProbabilityValue$sample23() {
		// Determine if we need to calculate the values for sample task 23 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample23) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var21 = 0; var21 < 5; var21 += 1)
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
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian((mu[var21] / 4.47213595499958))) - 1.4978661367769954);
			logProbability$var17 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var22 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$mu = (logProbability$mu + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample23)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample23 = fixedFlag$sample23;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var17 = logProbability$var22;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$mu = (logProbability$mu + logProbability$var22);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var22);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample23)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var22);
		}
	}

	// Calculate the probability of the samples represented by sample34 using sampled
	// values.
	private final void logProbabilityValue$sample34() {
		// Determine if we need to calculate the values for sample task 34 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample34) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var32 = 0; var32 < 5; var32 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(sigma[var32], 1.0, 1.0));
			logProbability$var28 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var33 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$sigma = (logProbability$sigma + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample34)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample34 = fixedFlag$sample34;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var28 = logProbability$var33;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$sigma = (logProbability$sigma + logProbability$var33);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var33);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample34)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var33);
		}
	}

	// Calculate the probability of the samples represented by sample45 using sampled
	// values.
	private final void logProbabilityValue$sample45() {
		// Determine if we need to calculate the values for sample task 45 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample45) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = z[i$var40];
				
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
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
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < phi.length))?Math.log(phi[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
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
				logProbability$var41[i$var40] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample45[i$var40] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$z = (logProbability$z + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedFlag$sample13);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample45[i$var40];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var41[i$var40] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$z = (logProbability$z + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
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
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
				double var44 = sigma[z[i$var40]];
				
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((x[i$var40] - mu[z[i$var40]]) / Math.sqrt(var44))) - (Math.log(var44) * 0.5));
				
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
				logProbability$var45[i$var40] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample49[i$var40] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$x = (logProbability$x + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample49 = (((fixedFlag$sample49 && fixedFlag$sample23) && fixedFlag$sample34) && fixedFlag$sample45);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample49[i$var40];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var45[i$var40] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$x = (logProbability$x + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 13 drawn from Dirichlet 11. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample13() {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var12$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 41.
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
			// Processing sample task 45 of consumer random variable null.
			// 
			// Increment the sample counter with the value sampled by sample task 45 of random
			// variable var41
			// 
			// A local reference to the scratch space.
			cv$var12$countGlobal[z[i$var40]] = (cv$var12$countGlobal[z[i$var40]] + 1.0);
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$var12$countGlobal, phi);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 23 drawn from Gaussian 17. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample23(int var21, int threadID$cv$var21, Rng RNG$) {
		// State to record the weighting of each sample that is consumed. This is the:
		// sum of the sample denominator*(the sample value - the sample nominator).
		double cv$sum = 0.0;
		
		// State for storing the sum of the squares of the sample denominators.
		double cv$denominatorSquareSum = 0.0;
		
		// Flag to record if we have a value for Sigma.
		boolean cv$sigmaNotFound = true;
		
		// State for the value of sigma once we find it.
		double cv$sigmaValue = 1.0;
		
		// Processing random variable 45.
		// 
		// Looking for a path between Sample 23 and consumer Gaussian 45.
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
			if((var21 == z[i$var40])) {
				// Processing sample task 49 of consumer random variable null.
				// Record the value of a sample generated by a consuming sample 49 of random variable
				// var45.
				// 
				// Add the denominator squared to the sample denominator
				// 
				// cv$denominator's comment
				// State for tracking the changes that happen to the sampled value between it being
				// consumed and it being produced.
				cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
				
				// Add the weighting of the sample to the sum.
				// 
				// Substituted "cv$numerator" with its value "0.0".
				cv$sum = (cv$sum + x[i$var40]);
				
				// If we have not got the value of sigma yet record it and set a flag so it is not
				// recorded again.
				if(cv$sigmaNotFound) {
					cv$sigmaValue = sigma[z[i$var40]];
					cv$sigmaNotFound = false;
				}
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		mu[var21] = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 20.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 34 drawn from InverseGamma 28. Inference was performed using a Inverse
	// Gamma to Gaussian conjugate prior.
	private final void sample34(int var32, int threadID$cv$var32, Rng RNG$) {
		// Variable to track the sum of the difference between the samples and the random
		// variables mean squared.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		
		// Processing random variable 45.
		// 
		// Looking for a path between Sample 34 and consumer Gaussian 45.
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
			if((var32 == z[i$var40])) {
				// Processing sample task 49 of consumer random variable null.
				// Consume sample task 49 from random variable var45.
				// 
				// The difference between the mean parameter and the value sampled from the Gaussian.
				// 
				// The mean parameter for Gaussian var45.
				double cv$var45$diff = (mu[z[i$var40]] - x[i$var40]);
				
				// Include this sample by adding the square of the difference to the sum.
				cv$sum = (cv$sum + (cv$var45$diff * cv$var45$diff));
				
				// Increment the number of samples in the calculation.
				cv$count = (cv$count + 1);
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		sigma[var32] = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 45 drawn from Categorical 41. Inference was performed using variable
	// marginalization.
	private final void sample45(int i$var40, int threadID$cv$i$var40, Rng RNG$) {
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var42$stateProbabilityGlobal[threadID$cv$i$var40];
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			z[i$var40] = cv$valuePos;
			
			// Variable declaration of cv$temp$2$var44 moved.
			// 
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			double cv$temp$2$var44 = sigma[cv$valuePos];
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			// 
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 49 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$phi" with its value "phi".
			cv$stateProbabilityLocal[cv$valuePos] = ((DistributionSampling.logProbabilityGaussian(((x[i$var40] - mu[cv$valuePos]) / Math.sqrt(cv$temp$2$var44))) + ((cv$valuePos < phi.length)?Math.log(phi[cv$valuePos]):Double.NEGATIVE_INFINITY)) - (Math.log(cv$temp$2$var44) * 0.5));
		}
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		double cv$lseMax = cv$stateProbabilityLocal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		
		// If the maximum value is -infinity return -infinity.
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		
		// Sum the values in the array.
		else {
			// Initialise the sum of the array elements
			double cv$lseSum = 0.0;
			
			// Offset values, move to normal space, and sum.
			for(int cv$lseIndex = 0; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		
		// Write out the new value of the sample.
		z[i$var40] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Allocation of cv$var12$countGlobal for single threaded execution
		cv$var12$countGlobal = new double[5];
		
		// Constructor for cv$var42$stateProbabilityGlobal
		// 
		// Allocation of cv$var42$stateProbabilityGlobal for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		cv$var42$stateProbabilityGlobal = new double[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var42$stateProbabilityGlobal[cv$index] = new double[5];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for alpha
		alpha = new double[5];
		
		// If phi has not been set already allocate space.
		if(!setFlag$phi)
			// Constructor for phi
			phi = new double[5];
		
		// If mu has not been set already allocate space.
		if(!setFlag$mu)
			// Constructor for mu
			mu = new double[5];
		
		// If sigma has not been set already allocate space.
		if(!setFlag$sigma)
			// Constructor for sigma
			sigma = new double[5];
		
		// If x has not been set already allocate space.
		if(!setFlag$x)
			// Constructor for x
			x = new double[length$xMeasured];
		
		// If z has not been set already allocate space.
		if(!setFlag$z)
			// Constructor for z
			z = new int[length$xMeasured];
		
		// Constructor for logProbability$var41
		logProbability$var41 = new double[length$xMeasured];
		
		// Constructor for logProbability$sample45
		logProbability$sample45 = new double[length$xMeasured];
		
		// Constructor for logProbability$var45
		logProbability$var45 = new double[length$xMeasured];
		
		// Constructor for logProbability$sample49
		logProbability$sample49 = new double[length$xMeasured];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							mu[var21] = (DistributionSampling.sampleGaussian(RNG$1) * 4.47213595499958);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample34)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
							sigma[var32] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var40, int forEnd$i$var40, int threadID$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var40 = forStart$i$var40; i$var40 < forEnd$i$var40; i$var40 += 1) {
						if(!fixedFlag$sample45)
							z[i$var40] = DistributionSampling.sampleCategorical(RNG$1, phi);
						if(!fixedFlag$sample49)
							x[i$var40] = ((Math.sqrt(sigma[z[i$var40]]) * DistributionSampling.sampleGaussian(RNG$1)) + mu[z[i$var40]]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							mu[var21] = (DistributionSampling.sampleGaussian(RNG$1) * 4.47213595499958);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample34)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
							sigma[var32] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample45)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$xMeasured, 1,
				(int forStart$i$var40, int forEnd$i$var40, int threadID$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var40 = forStart$i$var40; i$var40 < forEnd$i$var40; i$var40 += 1)
							z[i$var40] = DistributionSampling.sampleCategorical(RNG$1, phi);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							mu[var21] = (DistributionSampling.sampleGaussian(RNG$1) * 4.47213595499958);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample34)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
							sigma[var32] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample45)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$xMeasured, 1,
				(int forStart$i$var40, int forEnd$i$var40, int threadID$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var40 = forStart$i$var40; i$var40 < forEnd$i$var40; i$var40 += 1)
							z[i$var40] = DistributionSampling.sampleCategorical(RNG$1, phi);
				}
			);

	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample13)
				sample13();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample23)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
								sample23(var21, threadID$var21, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample34)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
								sample34(var32, threadID$var32, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample45)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, length$xMeasured, 1,
					(int forStart$i$var40, int forEnd$i$var40, int threadID$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i$var40 = forStart$i$var40; i$var40 < forEnd$i$var40; i$var40 += 1)
								sample45(i$var40, threadID$i$var40, RNG$1);
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample45)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, length$xMeasured, 1,
					(int forStart$i$var40, int forEnd$i$var40, int threadID$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i$var40 = forStart$i$var40; i$var40 < forEnd$i$var40; i$var40 += 1)
								sample45(i$var40, threadID$i$var40, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample34)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
								sample34(var32, threadID$var32, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample23)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
								sample23(var21, threadID$var21, RNG$1);
					}
				);

			if(!fixedFlag$sample13)
				sample13();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var8, int forEnd$i$var8, int threadID$i$var8, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var8 = forStart$i$var8; i$var8 < forEnd$i$var8; i$var8 += 1)
						alpha[i$var8] = 1.0;
			}
		);
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
		logProbability$var11 = 0.0;
		if(!fixedProbFlag$sample13)
			logProbability$phi = 0.0;
		logProbability$var17 = 0.0;
		logProbability$mu = 0.0;
		if(!fixedProbFlag$sample23)
			logProbability$var22 = 0.0;
		logProbability$var28 = 0.0;
		logProbability$sigma = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var33 = 0.0;
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
			logProbability$var41[i$var40] = 0.0;
		logProbability$z = 0.0;
		if(!fixedProbFlag$sample45) {
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
				logProbability$sample45[i$var40] = 0.0;
		}
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
			logProbability$var45[i$var40] = 0.0;
		logProbability$x = 0.0;
		if(!fixedProbFlag$sample49) {
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
				logProbability$sample49[i$var40] = 0.0;
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
		if(fixedFlag$sample13)
			logProbabilityValue$sample13();
		if(fixedFlag$sample23)
			logProbabilityValue$sample23();
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		logProbabilityValue$sample49();
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
		logProbabilityValue$sample13();
		logProbabilityValue$sample23();
		logProbabilityValue$sample34();
		logProbabilityValue$sample45();
		logProbabilityValue$sample49();
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
		logProbabilityValue$sample13();
		logProbabilityValue$sample23();
		logProbabilityValue$sample34();
		logProbabilityValue$sample45();
		logProbabilityValue$sample49();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							mu[var21] = (DistributionSampling.sampleGaussian(RNG$1) * 4.47213595499958);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample34)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
							sigma[var32] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample45)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$xMeasured, 1,
				(int forStart$i$var40, int forEnd$i$var40, int threadID$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var40 = forStart$i$var40; i$var40 < forEnd$i$var40; i$var40 += 1)
							z[i$var40] = DistributionSampling.sampleCategorical(RNG$1, phi);
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
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = x.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			x[cv$index1] = xMeasured[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel GaussianMixtureTest(double[] xMeasured) {\n\n        int k = 5;\n\n        double[] alpha = new double[k];\n        for(int i:[0..k)) \n            alpha[i] = 1.0;\n        \n        double[] phi = dirichlet(alpha).sample();\n        double[] mu = gaussian(0, 20).sample(k);\n        double[] sigma = inverseGamma(1, 1).sample(k);\n        \n        double[] x = new double[xMeasured.length];\n        for(int i:[0..xMeasured.length)) {\n            int z = categorical(phi).sample();\n            x[i] = gaussian(mu[z], sigma[z]).sample();\n        }\n        \n        x.observe(xMeasured);\n}\n";
	}
}