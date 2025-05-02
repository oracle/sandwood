package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class AnonymousSample$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements AnonymousSample$CoreInterface {
	
	// Declare the variables for the model.
	private double[] amounts1;
	private double[] amounts2;
	private boolean fixedFlag$sample15 = false;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample15 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample9 = false;
	private int length$obsAmounts1;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$amounts1;
	private double logProbability$amounts2;
	private double logProbability$mean1;
	private double logProbability$mean2;
	private double logProbability$priorSigma2;
	private double logProbability$var14;
	private double logProbability$var20;
	private double logProbability$var34;
	private double logProbability$var35;
	private double logProbability$var38;
	private double logProbability$var39;
	private double logProbability$var8;
	private double mean1;
	private double mean2;
	private int n;
	private double[] obsAmounts1;
	private double[] obsAmounts2;
	private double priorSigma2;
	private boolean system$gibbsForward = true;
	private double[] var39;

	public AnonymousSample$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for amounts1.
	@Override
	public final double[] get$amounts1() {
		return amounts1;
	}

	// Getter for amounts2.
	@Override
	public final double[] get$amounts2() {
		return amounts2;
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
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample35 = (fixedFlag$sample15 && fixedProbFlag$sample35);
	}

	// Getter for fixedFlag$sample21.
	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	// Setter for fixedFlag$sample21.
	@Override
	public final void set$fixedFlag$sample21(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample21 including if probabilities
		// need to be updated.
		fixedFlag$sample21 = cv$value;
		
		// Should the probability of sample 21 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample21 = (fixedFlag$sample21 && fixedProbFlag$sample21);
		
		// Should the probability of sample 39 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample39 = (fixedFlag$sample21 && fixedProbFlag$sample39);
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
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample35 = (fixedFlag$sample9 && fixedProbFlag$sample35);
		
		// Should the probability of sample 39 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample39 = (fixedFlag$sample9 && fixedProbFlag$sample39);
	}

	// Getter for length$obsAmounts1.
	@Override
	public final int get$length$obsAmounts1() {
		return length$obsAmounts1;
	}

	// Setter for length$obsAmounts1.
	@Override
	public final void set$length$obsAmounts1(int cv$value) {
		length$obsAmounts1 = cv$value;
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

	// Getter for logProbability$amounts1.
	@Override
	public final double get$logProbability$amounts1() {
		return logProbability$amounts1;
	}

	// Getter for logProbability$amounts2.
	@Override
	public final double get$logProbability$amounts2() {
		return logProbability$amounts2;
	}

	// Getter for logProbability$mean1.
	@Override
	public final double get$logProbability$mean1() {
		return logProbability$mean1;
	}

	// Getter for logProbability$mean2.
	@Override
	public final double get$logProbability$mean2() {
		return logProbability$mean2;
	}

	// Getter for logProbability$priorSigma2.
	@Override
	public final double get$logProbability$priorSigma2() {
		return logProbability$priorSigma2;
	}

	// Getter for mean1.
	@Override
	public final double get$mean1() {
		return mean1;
	}

	// Setter for mean1.
	@Override
	public final void set$mean1(double cv$value) {
		// Set flags for all the side effects of mean1 including if probabilities need to
		// be updated.
		mean1 = cv$value;
		
		// Unset the fixed probability flag for sample 15 as it depends on mean1.
		fixedProbFlag$sample15 = false;
		
		// Unset the fixed probability flag for sample 35 as it depends on mean1.
		fixedProbFlag$sample35 = false;
	}

	// Getter for mean2.
	@Override
	public final double get$mean2() {
		return mean2;
	}

	// Setter for mean2.
	@Override
	public final void set$mean2(double cv$value) {
		// Set flags for all the side effects of mean2 including if probabilities need to
		// be updated.
		mean2 = cv$value;
		
		// Unset the fixed probability flag for sample 21 as it depends on mean2.
		fixedProbFlag$sample21 = false;
		
		// Unset the fixed probability flag for sample 39 as it depends on mean2.
		fixedProbFlag$sample39 = false;
	}

	// Getter for n.
	@Override
	public final int get$n() {
		return n;
	}

	// Getter for obsAmounts1.
	@Override
	public final double[] get$obsAmounts1() {
		return obsAmounts1;
	}

	// Setter for obsAmounts1.
	@Override
	public final void set$obsAmounts1(double[] cv$value) {
		// Set obsAmounts1
		obsAmounts1 = cv$value;
	}

	// Getter for obsAmounts2.
	@Override
	public final double[] get$obsAmounts2() {
		return obsAmounts2;
	}

	// Setter for obsAmounts2.
	@Override
	public final void set$obsAmounts2(double[] cv$value) {
		// Set obsAmounts2
		obsAmounts2 = cv$value;
	}

	// Getter for priorSigma2.
	@Override
	public final double get$priorSigma2() {
		return priorSigma2;
	}

	// Setter for priorSigma2.
	@Override
	public final void set$priorSigma2(double cv$value) {
		// Set flags for all the side effects of priorSigma2 including if probabilities need
		// to be updated.
		priorSigma2 = cv$value;
		
		// Unset the fixed probability flag for sample 9 as it depends on priorSigma2.
		fixedProbFlag$sample9 = false;
		
		// Unset the fixed probability flag for sample 35 as it depends on priorSigma2.
		fixedProbFlag$sample35 = false;
		
		// Unset the fixed probability flag for sample 39 as it depends on priorSigma2.
		fixedProbFlag$sample39 = false;
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
				double cv$sampleValue = mean1;
				{
					{
						double var12 = 2000.0;
						double var13 = 10000.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var12) / Math.sqrt(var13))) - (0.5 * Math.log(var13))));
						
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
			logProbability$var14 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$mean1 = cv$sampleProbability;
			
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
			double cv$sampleValue = logProbability$mean1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var14 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample21 using sampled
	// values.
	private final void logProbabilityValue$sample21() {
		// Determine if we need to calculate the values for sample task 21 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample21) {
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
				double cv$sampleValue = mean2;
				{
					{
						double var18 = 2000.0;
						double var19 = 10000.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var18) / Math.sqrt(var19))) - (0.5 * Math.log(var19))));
						
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
			logProbability$var20 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$mean2 = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample21 = fixedFlag$sample21;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$mean2;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var20 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample21)
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i = 0; i < n; i += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = amounts1[i];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - mean1) / Math.sqrt(priorSigma2))) - (0.5 * Math.log(priorSigma2))));
							
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
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			if(cv$sampleReached)
				logProbability$var34 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var35 = cv$accumulator;
			
			// Update the variable probability
			logProbability$amounts1 = (logProbability$amounts1 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample35 = (fixedFlag$sample9 && fixedFlag$sample15);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i = 0; i < n; i += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var35;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var34 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$amounts1 = (logProbability$amounts1 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample39 using sampled
	// values.
	private final void logProbabilityValue$sample39() {
		// Determine if we need to calculate the values for sample task 39 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample39) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i = 0; i < n; i += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = var39[((i - 0) / 1)];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - mean2) / Math.sqrt(priorSigma2))) - (0.5 * Math.log(priorSigma2))));
							
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
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			if(cv$sampleReached)
				logProbability$var38 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var39 = cv$accumulator;
			
			// Guard to ensure that amounts2 is only updated once for this probability.
			boolean cv$guard$amounts2 = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$amounts2) {
					// Set the guard so the update is only applied once.
					cv$guard$amounts2 = true;
					
					// Update the variable probability
					logProbability$amounts2 = (logProbability$amounts2 + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample39 = (fixedFlag$sample9 && fixedFlag$sample21);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i = 0; i < n; i += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var39;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var38 = cv$rvAccumulator;
			
			// Guard to ensure that amounts2 is only updated once for this probability.
			boolean cv$guard$amounts2 = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$amounts2) {
					// Set the guard so the update is only applied once.
					cv$guard$amounts2 = true;
					
					// Update the variable probability
					logProbability$amounts2 = (logProbability$amounts2 + cv$accumulator);
				}
			}
			
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
				double cv$sampleValue = priorSigma2;
				{
					{
						double var6 = 10000.0;
						double var7 = 900.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var6) / Math.sqrt(var7))) - (0.5 * Math.log(var7))));
						
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
			logProbability$var8 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$priorSigma2 = cv$sampleProbability;
			
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
			double cv$sampleValue = logProbability$priorSigma2;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var8 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 15 drawn from Gaussian 14. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample15() {
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
				// Processing random variable 34.
				{
					{
						for(int i = 0; i < n; i += 1) {
							// State for tracking the changes that happen to the sampled value between it being
							// consumed and it being produced.
							double cv$denominator = 1.0;
							double cv$numerator = 0.0;
							
							// Record the value of a sample generated by a consuming sample 35 of random variable
							// var34.
							// 
							// Add the denominator squared to the sample denominator
							cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
							
							// Add the weighting of the sample to the sum.
							cv$sum = (cv$sum + (cv$denominator * (amounts1[i] - cv$numerator)));
							
							// If we have not got the value of sigma yet record it and set a flag so it is not
							// recorded again.
							if(cv$sigmaNotFound) {
								cv$sigmaValue = priorSigma2;
								cv$sigmaNotFound = false;
							}
						}
					}
				}
			}
			
			// Write out the new value of the sample.
			mean1 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 2000.0, 10000.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 21 drawn from Gaussian 20. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample21() {
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
				// Processing random variable 38.
				{
					{
						for(int i = 0; i < n; i += 1) {
							// State for tracking the changes that happen to the sampled value between it being
							// consumed and it being produced.
							double cv$denominator = 1.0;
							double cv$numerator = 0.0;
							
							// Record the value of a sample generated by a consuming sample 39 of random variable
							// var38.
							// 
							// Add the denominator squared to the sample denominator
							cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
							
							// Add the weighting of the sample to the sum.
							cv$sum = (cv$sum + (cv$denominator * (var39[((i - 0) / 1)] - cv$numerator)));
							
							// If we have not got the value of sigma yet record it and set a flag so it is not
							// recorded again.
							if(cv$sigmaNotFound) {
								cv$sigmaValue = priorSigma2;
								cv$sigmaNotFound = false;
							}
						}
					}
				}
			}
			
			// Write out the new value of the sample.
			mean2 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 2000.0, 10000.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 9 drawn from Gaussian 8. Inference was performed using Metropolis-Hastings.
	private final void sample9() {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			{
				// Metropolis-Hastings
				cv$numNumStates = Math.max(cv$numNumStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = priorSigma2;
			
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
						// Write out the new value of the sample.
						priorSigma2 = cv$proposedValue;
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$temp$0$var6;
					{
						cv$temp$0$var6 = 10000.0;
					}
					double cv$temp$1$var7;
					{
						cv$temp$1$var7 = 900.0;
					}
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var6) / Math.sqrt(cv$temp$1$var7))) - (0.5 * Math.log(cv$temp$1$var7))));
					
					// Processing random variable 34.
					{
						{
							for(int i = 0; i < n; i += 1) {
								double traceTempVariable$priorSigma2$1_2 = cv$currentValue;
								
								// Processing sample task 35 of consumer random variable null.
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
													double cv$temp$2$mean1;
													{
														cv$temp$2$mean1 = mean1;
													}
													double cv$temp$3$priorSigma2;
													{
														cv$temp$3$priorSigma2 = traceTempVariable$priorSigma2$1_2;
													}
													
													// Record the probability of sample task 35 generating output with current configuration.
													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((amounts1[i] - cv$temp$2$mean1) / Math.sqrt(cv$temp$3$priorSigma2))) - (0.5 * Math.log(cv$temp$3$priorSigma2)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((amounts1[i] - cv$temp$2$mean1) / Math.sqrt(cv$temp$3$priorSigma2))) - (0.5 * Math.log(cv$temp$3$priorSigma2)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((amounts1[i] - cv$temp$2$mean1) / Math.sqrt(cv$temp$3$priorSigma2))) - (0.5 * Math.log(cv$temp$3$priorSigma2))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((amounts1[i] - cv$temp$2$mean1) / Math.sqrt(cv$temp$3$priorSigma2))) - (0.5 * Math.log(cv$temp$3$priorSigma2)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((amounts1[i] - cv$temp$2$mean1) / Math.sqrt(cv$temp$3$priorSigma2))) - (0.5 * Math.log(cv$temp$3$priorSigma2)))));
													}
													
													// Recorded the probability of reaching sample task 35 with the current configuration.
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
					
					// Processing random variable 38.
					{
						{
							for(int i = 0; i < n; i += 1) {
								double traceTempVariable$priorSigma2$4_2 = cv$currentValue;
								
								// Processing sample task 39 of consumer random variable null.
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
													double cv$temp$4$mean2;
													{
														cv$temp$4$mean2 = mean2;
													}
													double cv$temp$5$priorSigma2;
													{
														cv$temp$5$priorSigma2 = traceTempVariable$priorSigma2$4_2;
													}
													
													// Record the probability of sample task 39 generating output with current configuration.
													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var39[((i - 0) / 1)] - cv$temp$4$mean2) / Math.sqrt(cv$temp$5$priorSigma2))) - (0.5 * Math.log(cv$temp$5$priorSigma2)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var39[((i - 0) / 1)] - cv$temp$4$mean2) / Math.sqrt(cv$temp$5$priorSigma2))) - (0.5 * Math.log(cv$temp$5$priorSigma2)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var39[((i - 0) / 1)] - cv$temp$4$mean2) / Math.sqrt(cv$temp$5$priorSigma2))) - (0.5 * Math.log(cv$temp$5$priorSigma2))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var39[((i - 0) / 1)] - cv$temp$4$mean2) / Math.sqrt(cv$temp$5$priorSigma2))) - (0.5 * Math.log(cv$temp$5$priorSigma2)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var39[((i - 0) / 1)] - cv$temp$4$mean2) / Math.sqrt(cv$temp$5$priorSigma2))) - (0.5 * Math.log(cv$temp$5$priorSigma2)))));
													}
													
													// Recorded the probability of reaching sample task 39 with the current configuration.
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
			if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Write out the new value of the sample.
				priorSigma2 = cv$originalValue;
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
		// Constructor for amounts1
		{
			amounts1 = new double[length$obsAmounts1];
		}
		
		// Constructor for amounts2
		{
			amounts2 = new double[length$obsAmounts1];
		}
		
		// Constructor for var39
		{
			var39 = new double[((((length$obsAmounts1 - 1) - 0) / 1) + 1)];
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((Math.sqrt(900.0) * DistributionSampling.sampleGaussian(RNG$)) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, n, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i = forStart$i; i < forEnd$i; i += 1) {
						amounts1[i] = ((Math.sqrt(priorSigma2) * DistributionSampling.sampleGaussian(RNG$1)) + mean1);
						var39[((i - 0) / 1)] = ((Math.sqrt(priorSigma2) * DistributionSampling.sampleGaussian(RNG$1)) + mean2);
						amounts2[i] = (amounts1[i] + var39[((i - 0) / 1)]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((Math.sqrt(900.0) * DistributionSampling.sampleGaussian(RNG$)) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((Math.sqrt(900.0) * DistributionSampling.sampleGaussian(RNG$)) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample9)
				sample9();
			if(!fixedFlag$sample15)
				sample15();
			if(!fixedFlag$sample21)
				sample21();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample21)
				sample21();
			if(!fixedFlag$sample15)
				sample15();
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
		n = length$obsAmounts1;
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
		logProbability$var8 = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$priorSigma2 = Double.NaN;
		logProbability$var14 = 0.0;
		if(!fixedProbFlag$sample15)
			logProbability$mean1 = Double.NaN;
		logProbability$var20 = 0.0;
		if(!fixedProbFlag$sample21)
			logProbability$mean2 = Double.NaN;
		logProbability$var34 = Double.NaN;
		logProbability$amounts1 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$var35 = Double.NaN;
		logProbability$var38 = Double.NaN;
		logProbability$amounts2 = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$var39 = Double.NaN;
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
		if(fixedFlag$sample15)
			logProbabilityValue$sample15();
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
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
		logProbabilityValue$sample15();
		logProbabilityValue$sample21();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
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
		logProbabilityValue$sample15();
		logProbabilityValue$sample21();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample9)
			priorSigma2 = ((Math.sqrt(900.0) * DistributionSampling.sampleGaussian(RNG$)) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		{
			// Deep copy between arrays
			double[] cv$source1 = obsAmounts1;
			double[] cv$target1 = amounts1;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
		{
			// Deep copy between arrays
			double[] cv$source1 = obsAmounts2;
			double[] cv$target1 = amounts2;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
		for(int i = (n - ((((n - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1)
			var39[((i - 0) / 1)] = (amounts2[i] - amounts1[i]);
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
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model AnonymousSample(double[] obsAmounts1, double[] obsAmounts2) {\n"
		     + "    int n = obsAmounts1.length;\n"
		     + "\n"
		     + "    double priorSigma2 = gaussian(10000, 900).sample();   // can always use inverseGamma(1.5, 100)\n"
		     + "\n"
		     + "    double mean1 = gaussian(2000, 10000).sample();\n"
		     + "    double mean2 = gaussian(2000, 10000).sample();\n"
		     + "\n"
		     + "\n"
		     + "    double[] amounts1 = new double[n];\n"
		     + "    double[] amounts2 = new double[n];\n"
		     + "    for(int i : [0..n)) {\n"
		     + "        amounts1[i] = gaussian(mean1, priorSigma2).sample();\n"
		     + "        amounts2[i] = amounts1[i] + gaussian(mean2, priorSigma2).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    amounts1.observe(obsAmounts1);\n"
		     + "    amounts2.observe(obsAmounts2);\n"
		     + "}";
	}
}