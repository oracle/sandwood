package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class GaussianMixtureTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements GaussianMixtureTest$CoreInterface {
	
	// Declare the variables for the model.
	private double[] alpha;
	private double[] cv$var19$countGlobal;
	private double[][] cv$var70$stateProbabilityGlobal;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample37 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedFlag$sample73 = false;
	private boolean fixedFlag$sample77 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample37 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean fixedProbFlag$sample73 = false;
	private boolean fixedProbFlag$sample77 = false;
	private int k;
	private int length$xMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$mu;
	private double logProbability$phi;
	private double[] logProbability$sample73;
	private double[] logProbability$sample77;
	private double logProbability$sigma;
	private double logProbability$var18;
	private double logProbability$var24;
	private double logProbability$var36;
	private double logProbability$var42;
	private double logProbability$var54;
	private double[] logProbability$var69;
	private double[] logProbability$var73;
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
		
		// Should the probability of sample 73 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample73 = (fixedFlag$sample20 && fixedProbFlag$sample73);
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
		
		// Should the probability of sample 77 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample77 = (fixedFlag$sample37 && fixedProbFlag$sample77);
	}

	// Getter for fixedFlag$sample55.
	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	// Setter for fixedFlag$sample55.
	@Override
	public final void set$fixedFlag$sample55(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample55 including if probabilities
		// need to be updated.
		fixedFlag$sample55 = cv$value;
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedProbFlag$sample55);
		
		// Should the probability of sample 77 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample77 = (fixedFlag$sample55 && fixedProbFlag$sample77);
	}

	// Getter for fixedFlag$sample73.
	@Override
	public final boolean get$fixedFlag$sample73() {
		return fixedFlag$sample73;
	}

	// Setter for fixedFlag$sample73.
	@Override
	public final void set$fixedFlag$sample73(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample73 including if probabilities
		// need to be updated.
		fixedFlag$sample73 = cv$value;
		
		// Should the probability of sample 73 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample73 = (fixedFlag$sample73 && fixedProbFlag$sample73);
		
		// Should the probability of sample 77 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample77 = (fixedFlag$sample73 && fixedProbFlag$sample77);
	}

	// Getter for fixedFlag$sample77.
	@Override
	public final boolean get$fixedFlag$sample77() {
		return fixedFlag$sample77;
	}

	// Setter for fixedFlag$sample77.
	@Override
	public final void set$fixedFlag$sample77(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample77 including if probabilities
		// need to be updated.
		fixedFlag$sample77 = cv$value;
		
		// Should the probability of sample 77 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample77 = (fixedFlag$sample77 && fixedProbFlag$sample77);
	}

	// Getter for k.
	@Override
	public final int get$k() {
		return k;
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
		// Set flags for all the side effects of mu including if probabilities need to be
		// updated.
		// Set mu with flag to mark that it has been set so another array doesn't need to
		// be constructed
		mu = cv$value;
		setFlag$mu = true;
		
		// Unset the fixed probability flag for sample 37 as it depends on mu.
		fixedProbFlag$sample37 = false;
		
		// Unset the fixed probability flag for sample 77 as it depends on mu.
		fixedProbFlag$sample77 = false;
	}

	// Getter for phi.
	@Override
	public final double[] get$phi() {
		return phi;
	}

	// Setter for phi.
	@Override
	public final void set$phi(double[] cv$value) {
		// Set flags for all the side effects of phi including if probabilities need to be
		// updated.
		// Set phi with flag to mark that it has been set so another array doesn't need to
		// be constructed
		phi = cv$value;
		setFlag$phi = true;
		
		// Unset the fixed probability flag for sample 20 as it depends on phi.
		fixedProbFlag$sample20 = false;
		
		// Unset the fixed probability flag for sample 73 as it depends on phi.
		fixedProbFlag$sample73 = false;
	}

	// Getter for sigma.
	@Override
	public final double[] get$sigma() {
		return sigma;
	}

	// Setter for sigma.
	@Override
	public final void set$sigma(double[] cv$value) {
		// Set flags for all the side effects of sigma including if probabilities need to
		// be updated.
		// Set sigma with flag to mark that it has been set so another array doesn't need
		// to be constructed
		sigma = cv$value;
		setFlag$sigma = true;
		
		// Unset the fixed probability flag for sample 55 as it depends on sigma.
		fixedProbFlag$sample55 = false;
		
		// Unset the fixed probability flag for sample 77 as it depends on sigma.
		fixedProbFlag$sample77 = false;
	}

	// Getter for x.
	@Override
	public final double[] get$x() {
		return x;
	}

	// Setter for x.
	@Override
	public final void set$x(double[] cv$value) {
		// Set flags for all the side effects of x including if probabilities need to be updated.
		// Set x with flag to mark that it has been set so another array doesn't need to be
		// constructed
		x = cv$value;
		setFlag$x = true;
		
		// Unset the fixed probability flag for sample 77 as it depends on x.
		fixedProbFlag$sample77 = false;
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
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				// The sample value to calculate the probability of generating
				double[] cv$sampleValue = phi;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, alpha));
						
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
			logProbability$var18 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$phi = cv$sampleProbability;
			
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
			double cv$sampleValue = logProbability$phi;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var18 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample20)
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var35 = 0; var35 < k; var35 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = mu[var35];
					{
						{
							double var22 = 0.0;
							double var23 = 20.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var22) / Math.sqrt(var23))) - (0.5 * Math.log(var23))));
							
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
			logProbability$var24 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var36 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$mu = (logProbability$mu + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample37)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample37 = fixedFlag$sample37;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var36;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var24 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$mu = (logProbability$mu + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample37)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample55 using sampled
	// values.
	private final void logProbabilityValue$sample55() {
		// Determine if we need to calculate the values for sample task 55 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample55) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var53 = 0; var53 < k; var53 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = sigma[var53];
					{
						{
							double var40 = 1.0;
							double var41 = 1.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var40, var41));
							
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
			logProbability$var42 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var54 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$sigma = (logProbability$sigma + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample55 = fixedFlag$sample55;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var54;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var42 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$sigma = (logProbability$sigma + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample73 using sampled
	// values.
	private final void logProbabilityValue$sample73() {
		// Determine if we need to calculate the values for sample task 73 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample73) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = z[((i$var68 - 0) / 1)];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < phi.length))?Math.log(phi[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var69[((i$var68 - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample73[((i$var68 - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$z = (logProbability$z + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample73 = (fixedFlag$sample73 && fixedFlag$sample20);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample73[((i$var68 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[((i$var68 - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$z = (logProbability$z + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample73)
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
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = x[i$var68];
					{
						{
							double var71 = mu[z[((i$var68 - 0) / 1)]];
							double var72 = sigma[z[((i$var68 - 0) / 1)]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var71) / Math.sqrt(var72))) - (0.5 * Math.log(var72))));
							
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
				logProbability$var73[((i$var68 - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample77[((i$var68 - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$x = (logProbability$x + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample77 = (((fixedFlag$sample77 && fixedFlag$sample37) && fixedFlag$sample55) && fixedFlag$sample73);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample77[((i$var68 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var73[((i$var68 - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$x = (logProbability$x + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 20 drawn from Dirichlet 18. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample20() {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = phi;
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var19$countGlobal;
		
		// Get the length of the array
		int cv$arrayLength = k;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 69.
			{
				{
					for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
						// Increment the sample counter with the value sampled by sample task 73 of random
						// variable var69
						cv$countLocal[z[((i$var68 - 0) / 1)]] = (cv$countLocal[z[((i$var68 - 0) / 1)]] + 1.0);
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$countLocal, cv$targetLocal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 37 drawn from Gaussian 24. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample37(int var35, int threadID$cv$var35, Rng RNG$) {
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
			// Processing random variable 73.
			{
				// Looking for a path between Sample 37 and consumer Gaussian 73.
				{
					for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
						if((var35 == z[((i$var68 - 0) / 1)])) {
							// Processing sample task 77 of consumer random variable null.
							{
								{
									{
										{
											{
												// State for tracking the changes that happen to the sampled value between it being
												// consumed and it being produced.
												double cv$denominator = 1.0;
												double cv$numerator = 0.0;
												
												// Record the value of a sample generated by a consuming sample 77 of random variable
												// var73.
												// 
												// Add the denominator squared to the sample denominator
												cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
												
												// Add the weighting of the sample to the sum.
												cv$sum = (cv$sum + (cv$denominator * (x[i$var68] - cv$numerator)));
												
												// If we have not got the value of sigma yet record it and set a flag so it is not
												// recorded again.
												if(cv$sigmaNotFound) {
													cv$sigmaValue = sigma[z[((i$var68 - 0) / 1)]];
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
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		double var36 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 20.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		mu[var35] = var36;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 55 drawn from InverseGamma 42. Inference was performed using a Inverse
	// Gamma to Gaussian conjugate prior.
	private final void sample55(int var53, int threadID$cv$var53, Rng RNG$) {
		// Variable to track the sum of the difference between the samples and the random
		// variables mean squared.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		{
			// Processing random variable 73.
			{
				// Looking for a path between Sample 55 and consumer Gaussian 73.
				{
					for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
						if((var53 == z[((i$var68 - 0) / 1)])) {
							// Processing sample task 77 of consumer random variable null.
							{
								{
									{
										{
											{
												// The mean parameter for Gaussian var73.
												double cv$var73$mu = mu[z[((i$var68 - 0) / 1)]];
												
												// Consume sample task 77 from random variable var73.
												// 
												// The difference between the mean parameter and the value sampled from the Gaussian.
												double cv$var73$diff = (cv$var73$mu - x[i$var68]);
												
												// Include this sample by adding the square of the difference to the sum.
												cv$sum = (cv$sum + (cv$var73$diff * cv$var73$diff));
												
												// Increment the number of samples in the calculation.
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
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		double var54 = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 1.0, 1.0, cv$sum, cv$count);
		sigma[var53] = var54;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 73 drawn from Categorical 69. Inference was performed using variable
	// marginalization.
	private final void sample73(int i$var68, int threadID$cv$i$var68, Rng RNG$) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// variable marginalization
			cv$noStates = Math.max(cv$noStates, k);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var70$stateProbabilityGlobal[threadID$cv$i$var68];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// The value currently being tested
			int cv$currentValue;
			
			// Value of the variable at this index
			cv$currentValue = cv$valuePos;
			
			// Write out the new value of the sample.
			z[((i$var68 - 0) / 1)] = cv$currentValue;
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$phi;
				{
					cv$temp$0$phi = phi;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$phi.length))?Math.log(cv$temp$0$phi[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 73.
				{
					{
						// Guard to check that at most one copy of the code is executed for a given set of
						// loop iterations.
						boolean guard$sample73gaussian76 = false;
						if(!guard$sample73gaussian76) {
							// The body will execute, so should not be executed again
							guard$sample73gaussian76 = true;
							
							// Processing sample task 77 of consumer random variable null.
							{
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
												double cv$temp$1$var71;
												{
													// Constructing a random variable input for use later.
													double var71 = mu[cv$currentValue];
													cv$temp$1$var71 = var71;
												}
												double cv$temp$2$var72;
												{
													// Constructing a random variable input for use later.
													double var72 = sigma[cv$currentValue];
													cv$temp$2$var72 = var72;
												}
												
												// Record the probability of sample task 77 generating output with current configuration.
												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$1$var71) / Math.sqrt(cv$temp$2$var72))) - (0.5 * Math.log(cv$temp$2$var72)))) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$1$var71) / Math.sqrt(cv$temp$2$var72))) - (0.5 * Math.log(cv$temp$2$var72)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													// If the second value is -infinity.
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$1$var71) / Math.sqrt(cv$temp$2$var72))) - (0.5 * Math.log(cv$temp$2$var72))));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$1$var71) / Math.sqrt(cv$temp$2$var72))) - (0.5 * Math.log(cv$temp$2$var72)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$1$var71) / Math.sqrt(cv$temp$2$var72))) - (0.5 * Math.log(cv$temp$2$var72)))));
												}
												
												// Recorded the probability of reaching sample task 77 with the current configuration.
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
						if(!guard$sample73gaussian76) {
							// The body will execute, so should not be executed again
							guard$sample73gaussian76 = true;
							
							// Processing sample task 77 of consumer random variable null.
							{
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
												double cv$temp$3$var71;
												{
													// Constructing a random variable input for use later.
													double var71 = mu[cv$currentValue];
													cv$temp$3$var71 = var71;
												}
												double cv$temp$4$var72;
												{
													// Constructing a random variable input for use later.
													double var72 = sigma[cv$currentValue];
													cv$temp$4$var72 = var72;
												}
												
												// Record the probability of sample task 77 generating output with current configuration.
												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$3$var71) / Math.sqrt(cv$temp$4$var72))) - (0.5 * Math.log(cv$temp$4$var72)))) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$3$var71) / Math.sqrt(cv$temp$4$var72))) - (0.5 * Math.log(cv$temp$4$var72)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													// If the second value is -infinity.
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$3$var71) / Math.sqrt(cv$temp$4$var72))) - (0.5 * Math.log(cv$temp$4$var72))));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$3$var71) / Math.sqrt(cv$temp$4$var72))) - (0.5 * Math.log(cv$temp$4$var72)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$3$var71) / Math.sqrt(cv$temp$4$var72))) - (0.5 * Math.log(cv$temp$4$var72)))));
												}
												
												// Recorded the probability of reaching sample task 77 with the current configuration.
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
			
			// Save the calculated index value into the array of index value probabilities
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// The sum of all the probabilities in log space
		double cv$logSum = 0.0;
		
		// Sum all the values
		{
			// Initialise the max to the first element.
			double cv$lseMax = cv$stateProbabilityLocal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
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
				for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		z[((i$var68 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var19$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			cv$max = Math.max(cv$max, 5);
			
			// Allocation of cv$var19$countGlobal for single threaded execution
			cv$var19$countGlobal = new double[cv$max];
		}
		
		// Constructor for cv$var70$stateProbabilityGlobal
		{
			// Allocation of cv$var70$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var70$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var70$stateProbabilityGlobal[cv$index] = new double[5];
			}
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for alpha
		{
			alpha = new double[5];
		}
		
		// If phi has not been set already allocate space.
		if(!setFlag$phi) {
			// Constructor for phi
			{
				phi = new double[5];
			}
		}
		
		// If mu has not been set already allocate space.
		if(!setFlag$mu) {
			// Constructor for mu
			{
				mu = new double[5];
			}
		}
		
		// If sigma has not been set already allocate space.
		if(!setFlag$sigma) {
			// Constructor for sigma
			{
				sigma = new double[5];
			}
		}
		
		// If x has not been set already allocate space.
		if(!setFlag$x) {
			// Constructor for x
			{
				x = new double[length$xMeasured];
			}
		}
		
		// If z has not been set already allocate space.
		if(!setFlag$z) {
			// Constructor for z
			{
				z = new int[((((length$xMeasured - 1) - 0) / 1) + 1)];
			}
		}
		
		// Constructor for logProbability$var69
		{
			logProbability$var69 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample73
		{
			logProbability$sample73 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var73
		{
			logProbability$var73 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample77
		{
			logProbability$sample77 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
						if(!fixedFlag$sample37)
							mu[var35] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
						if(!fixedFlag$sample55)
							sigma[var53] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var68, int forEnd$i$var68, int threadID$i$var68, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var68 = forStart$i$var68; i$var68 < forEnd$i$var68; i$var68 += 1) {
						if(!fixedFlag$sample73)
							z[((i$var68 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi);
						if(!fixedFlag$sample77)
							x[i$var68] = ((Math.sqrt(sigma[z[((i$var68 - 0) / 1)]]) * DistributionSampling.sampleGaussian(RNG$1)) + mu[z[((i$var68 - 0) / 1)]]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
						if(!fixedFlag$sample37)
							mu[var35] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
						if(!fixedFlag$sample55)
							sigma[var53] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var68, int forEnd$i$var68, int threadID$i$var68, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var68 = forStart$i$var68; i$var68 < forEnd$i$var68; i$var68 += 1) {
						if(!fixedFlag$sample73)
							z[((i$var68 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
						if(!fixedFlag$sample37)
							mu[var35] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
						if(!fixedFlag$sample55)
							sigma[var53] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var68, int forEnd$i$var68, int threadID$i$var68, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var68 = forStart$i$var68; i$var68 < forEnd$i$var68; i$var68 += 1) {
						if(!fixedFlag$sample73)
							z[((i$var68 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi);
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample20)
				sample20();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
							if(!fixedFlag$sample37)
								sample37(var35, threadID$var35, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
							if(!fixedFlag$sample55)
								sample55(var53, threadID$var53, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$xMeasured, 1,
				(int forStart$i$var68, int forEnd$i$var68, int threadID$i$var68, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var68 = forStart$i$var68; i$var68 < forEnd$i$var68; i$var68 += 1) {
							if(!fixedFlag$sample73)
								sample73(i$var68, threadID$i$var68, RNG$1);
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$xMeasured, 1,
				(int forStart$i$var68, int forEnd$i$var68, int threadID$i$var68, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var68 = forStart$i$var68; i$var68 < forEnd$i$var68; i$var68 += 1) {
							if(!fixedFlag$sample73)
								sample73(i$var68, threadID$i$var68, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
							if(!fixedFlag$sample55)
								sample55(var53, threadID$var53, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
							if(!fixedFlag$sample37)
								sample37(var35, threadID$var35, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample20)
				sample20();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		k = 5;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var15, int forEnd$i$var15, int threadID$i$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var15 = forStart$i$var15; i$var15 < forEnd$i$var15; i$var15 += 1)
						alpha[i$var15] = 1.0;
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
		logProbability$var18 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$phi = 0.0;
		logProbability$var24 = 0.0;
		logProbability$mu = 0.0;
		if(!fixedProbFlag$sample37)
			logProbability$var36 = 0.0;
		logProbability$var42 = 0.0;
		logProbability$sigma = 0.0;
		if(!fixedProbFlag$sample55)
			logProbability$var54 = 0.0;
		for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
			logProbability$var69[((i$var68 - 0) / 1)] = 0.0;
		logProbability$z = 0.0;
		if(!fixedProbFlag$sample73) {
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
				logProbability$sample73[((i$var68 - 0) / 1)] = 0.0;
		}
		for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
			logProbability$var73[((i$var68 - 0) / 1)] = 0.0;
		logProbability$x = 0.0;
		if(!fixedProbFlag$sample77) {
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
				logProbability$sample77[((i$var68 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(fixedFlag$sample37)
			logProbabilityValue$sample37();
		if(fixedFlag$sample55)
			logProbabilityValue$sample55();
		if(fixedFlag$sample73)
			logProbabilityValue$sample73();
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
		logProbabilityValue$sample20();
		logProbabilityValue$sample37();
		logProbabilityValue$sample55();
		logProbabilityValue$sample73();
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
		logProbabilityValue$sample20();
		logProbabilityValue$sample37();
		logProbabilityValue$sample55();
		logProbabilityValue$sample73();
		logProbabilityValue$sample77();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
						if(!fixedFlag$sample37)
							mu[var35] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
						if(!fixedFlag$sample55)
							sigma[var53] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var68, int forEnd$i$var68, int threadID$i$var68, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var68 = forStart$i$var68; i$var68 < forEnd$i$var68; i$var68 += 1) {
						if(!fixedFlag$sample73)
							z[((i$var68 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi);
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
		// Deep copy between arrays
		double[] cv$source1 = xMeasured;
		double[] cv$target1 = x;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
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