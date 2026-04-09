package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class GaussianMixtureTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements GaussianMixtureTest$CoreInterface {
	
	// Declare the variables for the model.
	private double[] alpha;
	private double[] cv$var17$countGlobal;
	private double[][] cv$var68$stateProbabilityGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedFlag$sample68 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample52 = false;
	private boolean fixedProbFlag$sample68 = false;
	private boolean fixedProbFlag$sample72 = false;
	private int k;
	private int length$xMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$mu;
	private double logProbability$phi;
	private double logProbability$sigma;
	private double logProbability$var16;
	private double logProbability$var22;
	private double logProbability$var34;
	private double logProbability$var40;
	private double logProbability$var52;
	private double logProbability$var67;
	private double logProbability$var71;
	private double logProbability$var72;
	private double logProbability$x;
	private double logProbability$z;
	private double[] mu;
	private double[] phi;
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

	// Getter for fixedFlag$sample17.
	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	// Setter for fixedFlag$sample17.
	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample17 including if probabilities
		// need to be updated.
		fixedFlag$sample17 = cv$value;
		
		// Should the probability of sample 17 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample17 = (fixedFlag$sample17 && fixedProbFlag$sample17);
		
		// Should the probability of sample 68 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample68 = (fixedFlag$sample17 && fixedProbFlag$sample68);
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
		fixedProbFlag$sample34 = (fixedFlag$sample34 && fixedProbFlag$sample34);
		
		// Should the probability of sample 72 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample72 = (fixedFlag$sample34 && fixedProbFlag$sample72);
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
		
		// Should the probability of sample 72 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample72 = (fixedFlag$sample52 && fixedProbFlag$sample72);
	}

	// Getter for fixedFlag$sample68.
	@Override
	public final boolean get$fixedFlag$sample68() {
		return fixedFlag$sample68;
	}

	// Setter for fixedFlag$sample68.
	@Override
	public final void set$fixedFlag$sample68(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample68 including if probabilities
		// need to be updated.
		fixedFlag$sample68 = cv$value;
		
		// Should the probability of sample 68 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample68 = (fixedFlag$sample68 && fixedProbFlag$sample68);
		
		// Should the probability of sample 72 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample72 = (fixedFlag$sample68 && fixedProbFlag$sample72);
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
		// Set mu
		mu = cv$value;
		
		// Unset the fixed probability flag for sample 34 as it depends on mu.
		fixedProbFlag$sample34 = false;
		
		// Unset the fixed probability flag for sample 72 as it depends on mu.
		fixedProbFlag$sample72 = false;
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
		// Set phi
		phi = cv$value;
		
		// Unset the fixed probability flag for sample 17 as it depends on phi.
		fixedProbFlag$sample17 = false;
		
		// Unset the fixed probability flag for sample 68 as it depends on phi.
		fixedProbFlag$sample68 = false;
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
		// Set sigma
		sigma = cv$value;
		
		// Unset the fixed probability flag for sample 52 as it depends on sigma.
		fixedProbFlag$sample52 = false;
		
		// Unset the fixed probability flag for sample 72 as it depends on sigma.
		fixedProbFlag$sample72 = false;
	}

	// Getter for x.
	@Override
	public final double[] get$x() {
		return x;
	}

	// Getter for xMeasured.
	@Override
	public final double[] get$xMeasured() {
		return xMeasured;
	}

	// Setter for xMeasured.
	@Override
	public final void set$xMeasured(double[] cv$value) {
		// Set xMeasured
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
		// Set z
		z = cv$value;
	}

	// Calculate the probability of the samples represented by sample17 using sampled
	// values.
	private final void logProbabilityValue$sample17() {
		// Determine if we need to calculate the values for sample task 17 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample17) {
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
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, alpha, k));
						
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
			logProbability$var16 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$phi = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample17 = fixedFlag$sample17;
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
			logProbability$var16 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample34 using sampled
	// values.
	private final void logProbabilityValue$sample34() {
		// Determine if we need to calculate the values for sample task 34 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample34) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var33 = 0; var33 < k; var33 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = mu[var33];
					{
						{
							double var20 = 0.0;
							double var21 = 20.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var20) / Math.sqrt(var21))) - (0.5 * Math.log(var21))));
							
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
			logProbability$var22 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var34 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$mu = (logProbability$mu + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample34 = fixedFlag$sample34;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var34;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var22 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$mu = (logProbability$mu + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample34)
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var51 = 0; var51 < k; var51 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = sigma[var51];
					{
						{
							double var38 = 1.0;
							double var39 = 1.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var38, var39));
							
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
			logProbability$var40 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var52 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$sigma = (logProbability$sigma + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample52 = fixedFlag$sample52;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var52;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var40 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$sigma = (logProbability$sigma + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample68 using sampled
	// values.
	private final void logProbabilityValue$sample68() {
		// Determine if we need to calculate the values for sample task 68 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample68) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = z[((i$var66 - 0) / 1)];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < k))?Math.log(phi[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			logProbability$var67 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$z = cv$accumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample68 = (fixedFlag$sample68 && fixedFlag$sample17);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$z;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var67 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = x[i$var66];
					{
						{
							double var69 = mu[z[((i$var66 - 0) / 1)]];
							double var70 = sigma[z[((i$var66 - 0) / 1)]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var69) / Math.sqrt(var70))) - (0.5 * Math.log(var70))));
							
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
			logProbability$var71 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var72 = cv$accumulator;
			
			// Update the variable probability
			logProbability$x = (logProbability$x + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample72 = ((fixedFlag$sample34 && fixedFlag$sample52) && fixedFlag$sample68);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var72;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var71 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$x = (logProbability$x + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 17 drawn from Dirichlet 16. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample17() {
		if(true) {
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = phi;
			
			// A local reference to the scratch space.
			double[] cv$countLocal = cv$var17$countGlobal;
			
			// Get the length of the array
			int cv$arrayLength = k;
			
			// Initialize the array values to 0.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				// Processing random variable 67.
				{
					{
						for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1)
							// Increment the sample counter with the value sampled by sample task 68 of random
							// variable var67
							cv$countLocal[z[((i$var66 - 0) / 1)]] = (cv$countLocal[z[((i$var66 - 0) / 1)]] + 1.0);
					}
				}
			}
			
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$countLocal, cv$targetLocal, k);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 34 drawn from Gaussian 22. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample34(int var33, int threadID$cv$var33, Rng RNG$) {
		if(true) {
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
				// Processing random variable 71.
				{
					// Looking for a path between Sample 34 and consumer Gaussian 71.
					{
						for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1) {
							if((var33 == z[((i$var66 - 0) / 1)])) {
								// Processing sample task 72 of consumer random variable null.
								{
									{
										{
											{
												{
													// State for tracking the changes that happen to the sampled value between it being
													// consumed and it being produced.
													double cv$denominator = 1.0;
													double cv$numerator = 0.0;
													
													// Record the value of a sample generated by a consuming sample 72 of random variable
													// var71.
													// 
													// Add the denominator squared to the sample denominator
													cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
													
													// Add the weighting of the sample to the sum.
													cv$sum = (cv$sum + (cv$denominator * (x[i$var66] - cv$numerator)));
													
													// If we have not got the value of sigma yet record it and set a flag so it is not
													// recorded again.
													if(cv$sigmaNotFound) {
														cv$sigmaValue = sigma[z[((i$var66 - 0) / 1)]];
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
			double var34 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 20.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
			
			// Guards to ensure that mu is only updated when there is a valid path.
			{
				{
					mu[var33] = var34;
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 52 drawn from InverseGamma 40. Inference was performed using a Inverse
	// Gamma to Gaussian conjugate prior.
	private final void sample52(int var51, int threadID$cv$var51, Rng RNG$) {
		if(true) {
			// Variable to track the sum of the difference between the samples and the random
			// variables mean squared.
			double cv$sum = 0.0;
			
			// Variable to record the number of samples from consuming random variables.
			int cv$count = 0;
			{
				// Processing random variable 71.
				{
					// Looking for a path between Sample 52 and consumer Gaussian 71.
					{
						for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1) {
							if((var51 == z[((i$var66 - 0) / 1)])) {
								// Processing sample task 72 of consumer random variable null.
								{
									{
										{
											{
												{
													// The mean parameter for Gaussian var71.
													double cv$var71$mu = mu[z[((i$var66 - 0) / 1)]];
													
													// Consume sample task 72 from random variable var71.
													// 
													// The difference between the mean parameter and the value sampled from the Gaussian.
													double cv$var71$diff = (cv$var71$mu - x[i$var66]);
													
													// Include this sample by adding the square of the difference to the sum.
													cv$sum = (cv$sum + (cv$var71$diff * cv$var71$diff));
													
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
			double var52 = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 1.0, 1.0, cv$sum, cv$count);
			
			// Guards to ensure that sigma is only updated when there is a valid path.
			{
				{
					sigma[var51] = var52;
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 68 drawn from Categorical 67. Inference was performed using variable
	// marginalization.
	private final void sample68(int i$var66, int threadID$cv$i$var66, Rng RNG$) {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			{
				// variable marginalization
				cv$numNumStates = Math.max(cv$numNumStates, k);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var68$stateProbabilityGlobal[threadID$cv$i$var66];
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
				
				// Value of the variable at this index
				cv$currentValue = cv$valuePos;
				
				// Write out the new value of the sample.
				z[((i$var66 - 0) / 1)] = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$phi;
					{
						cv$temp$0$phi = phi;
					}
					int cv$temp$1$$var319;
					{
						// Constructing a random variable input for use later.
						int $var319 = k;
						cv$temp$1$$var319 = $var319;
					}
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var319))?Math.log(cv$temp$0$phi[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 71.
					{
						{
							// Guard to check that at most one copy of the code is executed for a given set of
							// loop iterations.
							boolean guard$sample68gaussian71 = false;
							if(!guard$sample68gaussian71) {
								// The body will execute, so should not be executed again
								guard$sample68gaussian71 = true;
								
								// Processing sample task 72 of consumer random variable null.
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
													double cv$temp$2$var69;
													{
														// Constructing a random variable input for use later.
														double var69 = mu[cv$currentValue];
														cv$temp$2$var69 = var69;
													}
													double cv$temp$3$var70;
													{
														// Constructing a random variable input for use later.
														double var70 = sigma[cv$currentValue];
														cv$temp$3$var70 = var70;
													}
													
													// Record the probability of sample task 72 generating output with current configuration.
													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$2$var69) / Math.sqrt(cv$temp$3$var70))) - (0.5 * Math.log(cv$temp$3$var70)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$2$var69) / Math.sqrt(cv$temp$3$var70))) - (0.5 * Math.log(cv$temp$3$var70)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$2$var69) / Math.sqrt(cv$temp$3$var70))) - (0.5 * Math.log(cv$temp$3$var70))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$2$var69) / Math.sqrt(cv$temp$3$var70))) - (0.5 * Math.log(cv$temp$3$var70)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$2$var69) / Math.sqrt(cv$temp$3$var70))) - (0.5 * Math.log(cv$temp$3$var70)))));
													}
													
													// Recorded the probability of reaching sample task 72 with the current configuration.
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
							if(!guard$sample68gaussian71) {
								// The body will execute, so should not be executed again
								guard$sample68gaussian71 = true;
								
								// Processing sample task 72 of consumer random variable null.
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
													double cv$temp$4$var69;
													{
														// Constructing a random variable input for use later.
														double var69 = mu[cv$currentValue];
														cv$temp$4$var69 = var69;
													}
													double cv$temp$5$var70;
													{
														// Constructing a random variable input for use later.
														double var70 = sigma[cv$currentValue];
														cv$temp$5$var70 = var70;
													}
													
													// Record the probability of sample task 72 generating output with current configuration.
													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$4$var69) / Math.sqrt(cv$temp$5$var70))) - (0.5 * Math.log(cv$temp$5$var70)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$4$var69) / Math.sqrt(cv$temp$5$var70))) - (0.5 * Math.log(cv$temp$5$var70)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$4$var69) / Math.sqrt(cv$temp$5$var70))) - (0.5 * Math.log(cv$temp$5$var70))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$4$var69) / Math.sqrt(cv$temp$5$var70))) - (0.5 * Math.log(cv$temp$5$var70)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$4$var69) / Math.sqrt(cv$temp$5$var70))) - (0.5 * Math.log(cv$temp$5$var70)))));
													}
													
													// Recorded the probability of reaching sample task 72 with the current configuration.
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
				for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
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
					for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
						cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
					
					// Increment the value of the target, moving the value back into log space.
					cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
				}
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var17$countGlobal
		{
			// Allocation of cv$var17$countGlobal for single threaded execution
			cv$var17$countGlobal = new double[5];
		}
		
		// Constructor for cv$var68$stateProbabilityGlobal
		{
			// Allocation of cv$var68$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var68$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var68$stateProbabilityGlobal[cv$index] = new double[5];
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
		if(!fixedFlag$sample17) {
			// Constructor for phi
			{
				phi = new double[5];
			}
		}
		
		// If mu has not been set already allocate space.
		if(!fixedFlag$sample34) {
			// Constructor for mu
			{
				mu = new double[5];
			}
		}
		
		// If sigma has not been set already allocate space.
		if(!fixedFlag$sample52) {
			// Constructor for sigma
			{
				sigma = new double[5];
			}
		}
		
		// Constructor for x
		{
			x = new double[length$xMeasured];
		}
		
		// If z has not been set already allocate space.
		if(!fixedFlag$sample68) {
			// Constructor for z
			{
				z = new int[((((length$xMeasured - 1) - 0) / 1) + 1)];
			}
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, alpha, k, phi);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!fixedFlag$sample34)
							mu[var33] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!fixedFlag$sample52)
							sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1) {
						if(!fixedFlag$sample68)
							z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi, k);
						x[i$var66] = ((Math.sqrt(sigma[z[((i$var66 - 0) / 1)]]) * DistributionSampling.sampleGaussian(RNG$1)) + mu[z[((i$var66 - 0) / 1)]]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, alpha, k, phi);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!fixedFlag$sample34)
							mu[var33] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!fixedFlag$sample52)
							sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1) {
						if(!fixedFlag$sample68)
							z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi, k);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, alpha, k, phi);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!fixedFlag$sample34)
							mu[var33] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!fixedFlag$sample52)
							sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1) {
						if(!fixedFlag$sample68)
							z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi, k);
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample17)
				sample17();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
							if(!fixedFlag$sample34)
								sample34(var33, threadID$var33, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
							if(!fixedFlag$sample52)
								sample52(var51, threadID$var51, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$xMeasured, 1,
				(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1) {
							if(!fixedFlag$sample68)
								sample68(i$var66, threadID$i$var66, RNG$1);
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$xMeasured, 1,
				(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1) {
							if(!fixedFlag$sample68)
								sample68(i$var66, threadID$i$var66, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
							if(!fixedFlag$sample52)
								sample52(var51, threadID$var51, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
							if(!fixedFlag$sample34)
								sample34(var33, threadID$var33, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample17)
				sample17();
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
			(int forStart$i$var13, int forEnd$i$var13, int threadID$i$var13, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var13 = forStart$i$var13; i$var13 < forEnd$i$var13; i$var13 += 1)
						alpha[i$var13] = 1.0;
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
		logProbability$var16 = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$phi = 0.0;
		logProbability$var22 = 0.0;
		logProbability$mu = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var34 = 0.0;
		logProbability$var40 = 0.0;
		logProbability$sigma = 0.0;
		if(!fixedProbFlag$sample52)
			logProbability$var52 = 0.0;
		logProbability$var67 = 0.0;
		if(!fixedProbFlag$sample68)
			logProbability$z = 0.0;
		logProbability$var71 = 0.0;
		logProbability$x = 0.0;
		if(!fixedProbFlag$sample72)
			logProbability$var72 = 0.0;
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
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample52)
			logProbabilityValue$sample52();
		if(fixedFlag$sample68)
			logProbabilityValue$sample68();
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
		logProbabilityValue$sample17();
		logProbabilityValue$sample34();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
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
		logProbabilityValue$sample17();
		logProbabilityValue$sample34();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample72();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, alpha, k, phi);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!fixedFlag$sample34)
							mu[var33] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!fixedFlag$sample52)
							sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1) {
						if(!fixedFlag$sample68)
							z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi, k);
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
		double[] cv$source1 = xMeasured;
		double[] cv$target1 = x;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
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
		     + "model GaussianMixtureTest(double[] xMeasured) {\n"
		     + "\n"
		     + "        int k = 5;\n"
		     + "\n"
		     + "        double[] alpha = new double[k];\n"
		     + "        for(int i:[0..k)) \n"
		     + "            alpha[i] = 1.0;\n"
		     + "        \n"
		     + "        double[] phi = dirichlet(alpha).sample();\n"
		     + "        double[] mu = gaussian(0, 20).sample(k);\n"
		     + "        double[] sigma = inverseGamma(1, 1).sample(k);\n"
		     + "        \n"
		     + "        double[] x = new double[xMeasured.length];\n"
		     + "        for(int i:[0..xMeasured.length)) {\n"
		     + "            int z = categorical(phi).sample();\n"
		     + "            x[i] = gaussian(mu[z], sigma[z]).sample();\n"
		     + "        }\n"
		     + "        \n"
		     + "        x.observe(xMeasured);\n"
		     + "}\n"
		     + "";
	}
}