package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart6$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart6$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[] cv$var28$countGlobal;
	private double[] cv$var52$stateProbabilityGlobal;
	private double[] cv$var77$stateProbabilityGlobal;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample78 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample78 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample103;
	private double[] logProbability$sample78;
	private double logProbability$st;
	private double[] logProbability$var101;
	private double logProbability$var16;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var51;
	private double logProbability$var52;
	private double[] logProbability$var76;
	private double[][] m;
	private int samples;
	private int[] st;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart6$SingleThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 45 as it depends on bias.
		fixedProbFlag$sample45 = false;
		
		// Unset the fixed probability flag for sample 103 as it depends on bias.
		fixedProbFlag$sample103 = false;
	}

	// Getter for fixedFlag$sample28.
	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	// Setter for fixedFlag$sample28.
	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample28 including if probabilities
		// need to be updated.
		fixedFlag$sample28 = cv$value;
		
		// Should the probability of sample 28 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample53 = (fixedFlag$sample28 && fixedProbFlag$sample53);
		
		// Should the probability of sample 78 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample78 = (fixedFlag$sample28 && fixedProbFlag$sample78);
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
		fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedProbFlag$sample45);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample103 = (fixedFlag$sample45 && fixedProbFlag$sample103);
	}

	// Getter for fixedFlag$sample53.
	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	// Setter for fixedFlag$sample53.
	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample53 including if probabilities
		// need to be updated.
		fixedFlag$sample53 = cv$value;
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedProbFlag$sample53);
		
		// Should the probability of sample 78 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample78 = (fixedFlag$sample53 && fixedProbFlag$sample78);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample103 = (fixedFlag$sample53 && fixedProbFlag$sample103);
	}

	// Getter for fixedFlag$sample78.
	@Override
	public final boolean get$fixedFlag$sample78() {
		return fixedFlag$sample78;
	}

	// Setter for fixedFlag$sample78.
	@Override
	public final void set$fixedFlag$sample78(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample78 including if probabilities
		// need to be updated.
		fixedFlag$sample78 = cv$value;
		
		// Should the probability of sample 78 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample78 = (fixedFlag$sample78 && fixedProbFlag$sample78);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample103 = (fixedFlag$sample78 && fixedProbFlag$sample103);
	}

	// Getter for flips.
	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		// Set flipsMeasured
		flipsMeasured = cv$value;
	}

	// Getter for length$flipsMeasured.
	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	// Setter for length$flipsMeasured.
	@Override
	public final void set$length$flipsMeasured(int cv$value) {
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

	// Getter for logProbability$m.
	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	// Getter for logProbability$st.
	@Override
	public final double get$logProbability$st() {
		return logProbability$st;
	}

	// Getter for m.
	@Override
	public final double[][] get$m() {
		return m;
	}

	// Setter for m.
	@Override
	public final void set$m(double[][] cv$value) {
		// Set flags for all the side effects of m including if probabilities need to be updated.
		// Set m
		m = cv$value;
		
		// Unset the fixed probability flag for sample 28 as it depends on m.
		fixedProbFlag$sample28 = false;
		
		// Unset the fixed probability flag for sample 53 as it depends on m.
		fixedProbFlag$sample53 = false;
		
		// Unset the fixed probability flag for sample 78 as it depends on m.
		fixedProbFlag$sample78 = false;
	}

	// Getter for samples.
	@Override
	public final int get$samples() {
		return samples;
	}

	// Getter for st.
	@Override
	public final int[] get$st() {
		return st;
	}

	// Setter for st.
	@Override
	public final void set$st(int[] cv$value) {
		// Set flags for all the side effects of st including if probabilities need to be
		// updated.
		// Set st
		st = cv$value;
		
		// Unset the fixed probability flag for sample 53 as it depends on st.
		fixedProbFlag$sample53 = false;
		
		// Unset the fixed probability flag for sample 78 as it depends on st.
		fixedProbFlag$sample78 = false;
		
		// Unset the fixed probability flag for sample 103 as it depends on st.
		fixedProbFlag$sample103 = false;
	}

	// Getter for states.
	@Override
	public final int get$states() {
		return states;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Calculate the probability of the samples represented by sample103 using sampled
	// values.
	private final void logProbabilityValue$sample103() {
		// Determine if we need to calculate the values for sample task 103 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample103) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 5; j < (samples + 5); j += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips[(j - 5)];
					{
						{
							double var100 = bias[((states - 1) - st[(j - 5)])];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var100));
							
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
				logProbability$var101[((j - 5) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample103[((j - 5) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample103 = ((fixedFlag$sample45 && fixedFlag$sample53) && fixedFlag$sample78);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 5; j < (samples + 5); j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample103[((j - 5) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var101[((j - 5) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample28 using sampled
	// values.
	private final void logProbabilityValue$sample28() {
		// Determine if we need to calculate the values for sample task 28 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample28) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var27 = 0; var27 < states; var27 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = m[var27];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, states));
							
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
			logProbability$var16 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var28 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample28 = fixedFlag$sample28;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var28;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var16 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var43 = 0; var43 < states; var43 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = bias[var43];
					{
						{
							double var30 = 1.0;
							double var31 = 1.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var30, var31));
							
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
			logProbability$var32 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var44 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample45 = fixedFlag$sample45;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var44;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var32 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample53 using sampled
	// values.
	private final void logProbabilityValue$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample53) {
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
				int cv$sampleValue = st[0];
				{
					{
						double[] var50 = m[0];
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY));
						
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
			logProbability$var51 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$var52 = cv$sampleProbability;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample28);
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
			logProbability$var51 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample78 using sampled
	// values.
	private final void logProbabilityValue$sample78() {
		// Determine if we need to calculate the values for sample task 78 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample78) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[(i$var66 - 3)];
					{
						{
							double[] var75 = m[((states - 1) - st[(i$var66 - 4)])];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var75[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var76[((i$var66 - 4) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample78[((i$var66 - 4) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample78 = ((fixedFlag$sample78 && fixedFlag$sample28) && fixedFlag$sample53);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample78[((i$var66 - 4) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var76[((i$var66 - 4) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from Dirichlet 16. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample28(int var27) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = m[var27];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var28$countGlobal;
		
		// Get the length of the array
		int cv$arrayLength = states;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 51.
			{
				// Looking for a path between Sample 28 and consumer Categorical 51.
				{
					if((var27 == 0)) {
						// Processing sample task 53 of consumer random variable null.
						{
							{
								{
									{
										{
											// Increment the sample counter with the value sampled by sample task 53 of random
											// variable var51
											cv$countLocal[st[0]] = (cv$countLocal[st[0]] + 1.0);
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 76.
			{
				// Looking for a path between Sample 28 and consumer Categorical 76.
				{
					for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1) {
						if((var27 == ((states - 1) - st[(i$var66 - 4)]))) {
							// Processing sample task 78 of consumer random variable null.
							{
								{
									{
										{
											{
												// Increment the sample counter with the value sampled by sample task 78 of random
												// variable var76
												cv$countLocal[st[(i$var66 - 3)]] = (cv$countLocal[st[(i$var66 - 3)]] + 1.0);
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
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, states);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 45 drawn from Beta 32. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample45(int var43) {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		{
			// Processing random variable 101.
			{
				// Looking for a path between Sample 45 and consumer Bernoulli 101.
				{
					for(int j = 5; j < (samples + 5); j += 1) {
						if((var43 == ((states - 1) - st[(j - 5)]))) {
							// Processing sample task 103 of consumer random variable null.
							{
								{
									{
										{
											{
												// Include the value sampled by task 103 from random variable var101.
												// Increment the number of samples.
												cv$count = (cv$count + 1);
												
												// If the sample value was positive increase the count
												if(flips[(j - 5)])
													cv$sum = (cv$sum + 1);
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
		double var44 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		
		// Guards to ensure that bias is only updated when there is a valid path.
		{
			{
				bias[var43] = var44;
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 53 drawn from Categorical 51. Inference was performed using variable
	// marginalization.
	private final void sample53() {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		{
			// variable marginalization
			cv$numNumStates = Math.max(cv$numNumStates, states);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var52$stateProbabilityGlobal;
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
			
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			int var52 = cv$currentValue;
			
			// Guards to ensure that st is only updated when there is a valid path.
			{
				{
					st[0] = cv$currentValue;
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var50;
				{
					// Constructing a random variable input for use later.
					double[] var50 = m[0];
					cv$temp$0$var50 = var50;
				}
				int cv$temp$1$$var190;
				{
					// Constructing a random variable input for use later.
					int $var190 = states;
					cv$temp$1$$var190 = $var190;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var190))?Math.log(cv$temp$0$var50[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 76.
				{
					// Looking for a path between Sample 53 and consumer Categorical 76.
					{
						int traceTempVariable$var73$2_1 = cv$currentValue;
						for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1) {
							if((0 == (i$var66 - 4))) {
								// Processing sample task 78 of consumer random variable null.
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
													double[] cv$temp$2$var75;
													{
														// Constructing a random variable input for use later.
														double[] var75 = m[((states - 1) - traceTempVariable$var73$2_1)];
														cv$temp$2$var75 = var75;
													}
													int cv$temp$3$$var199;
													{
														// Constructing a random variable input for use later.
														int $var199 = states;
														cv$temp$3$$var199 = $var199;
													}
													
													// Record the probability of sample task 78 generating output with current configuration.
													if(((Math.log(1.0) + (((0.0 <= st[(i$var66 - 3)]) && (st[(i$var66 - 3)] < cv$temp$3$$var199))?Math.log(cv$temp$2$var75[st[(i$var66 - 3)]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[(i$var66 - 3)]) && (st[(i$var66 - 3)] < cv$temp$3$$var199))?Math.log(cv$temp$2$var75[st[(i$var66 - 3)]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[(i$var66 - 3)]) && (st[(i$var66 - 3)] < cv$temp$3$$var199))?Math.log(cv$temp$2$var75[st[(i$var66 - 3)]]):Double.NEGATIVE_INFINITY));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[(i$var66 - 3)]) && (st[(i$var66 - 3)] < cv$temp$3$$var199))?Math.log(cv$temp$2$var75[st[(i$var66 - 3)]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[(i$var66 - 3)]) && (st[(i$var66 - 3)] < cv$temp$3$$var199))?Math.log(cv$temp$2$var75[st[(i$var66 - 3)]]):Double.NEGATIVE_INFINITY)));
													}
													
													// Recorded the probability of reaching sample task 78 with the current configuration.
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
				}
				
				// Processing random variable 101.
				{
					// Looking for a path between Sample 53 and consumer Bernoulli 101.
					{
						int traceTempVariable$var98$5_1 = cv$currentValue;
						for(int j = 5; j < (samples + 5); j += 1) {
							if((0 == (j - 5))) {
								// Processing sample task 103 of consumer random variable null.
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
													double cv$temp$4$var100;
													{
														// Constructing a random variable input for use later.
														double var100 = bias[((states - 1) - traceTempVariable$var98$5_1)];
														cv$temp$4$var100 = var100;
													}
													
													// Record the probability of sample task 103 generating output with current configuration.
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[(j - 5)], cv$temp$4$var100)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[(j - 5)], cv$temp$4$var100)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[(j - 5)], cv$temp$4$var100));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[(j - 5)], cv$temp$4$var100)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[(j - 5)], cv$temp$4$var100)));
													}
													
													// Recorded the probability of reaching sample task 103 with the current configuration.
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
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		int var52 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
		
		// Guards to ensure that st is only updated when there is a valid path.
		{
			{
				st[0] = var52;
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 78 drawn from Categorical 76. Inference was performed using variable
	// marginalization.
	private final void sample78(int i$var66) {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		{
			// variable marginalization
			cv$numNumStates = Math.max(cv$numNumStates, states);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var77$stateProbabilityGlobal;
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
			
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			int var77 = cv$currentValue;
			
			// Guards to ensure that st is only updated when there is a valid path.
			{
				{
					st[(i$var66 - 3)] = cv$currentValue;
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var75;
				{
					// Constructing a random variable input for use later.
					double[] var75 = m[((states - 1) - st[(i$var66 - 4)])];
					cv$temp$0$var75 = var75;
				}
				int cv$temp$1$$var221;
				{
					// Constructing a random variable input for use later.
					int $var221 = states;
					cv$temp$1$$var221 = $var221;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var221))?Math.log(cv$temp$0$var75[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 76.
				{
					// Looking for a path between Sample 78 and consumer Categorical 76.
					{
						int traceTempVariable$var73$2_1 = cv$currentValue;
						for(int index$i$2_2 = 4; index$i$2_2 < (samples + 3); index$i$2_2 += 1) {
							if(((i$var66 - 3) == (index$i$2_2 - 4))) {
								// Processing sample task 78 of consumer random variable null.
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
													double[] cv$temp$2$var75;
													{
														// Constructing a random variable input for use later.
														double[] var75 = m[((states - 1) - traceTempVariable$var73$2_1)];
														cv$temp$2$var75 = var75;
													}
													int cv$temp$3$$var230;
													{
														// Constructing a random variable input for use later.
														int $var230 = states;
														cv$temp$3$$var230 = $var230;
													}
													
													// Record the probability of sample task 78 generating output with current configuration.
													if(((Math.log(1.0) + (((0.0 <= st[(index$i$2_2 - 3)]) && (st[(index$i$2_2 - 3)] < cv$temp$3$$var230))?Math.log(cv$temp$2$var75[st[(index$i$2_2 - 3)]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[(index$i$2_2 - 3)]) && (st[(index$i$2_2 - 3)] < cv$temp$3$$var230))?Math.log(cv$temp$2$var75[st[(index$i$2_2 - 3)]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[(index$i$2_2 - 3)]) && (st[(index$i$2_2 - 3)] < cv$temp$3$$var230))?Math.log(cv$temp$2$var75[st[(index$i$2_2 - 3)]]):Double.NEGATIVE_INFINITY));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[(index$i$2_2 - 3)]) && (st[(index$i$2_2 - 3)] < cv$temp$3$$var230))?Math.log(cv$temp$2$var75[st[(index$i$2_2 - 3)]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[(index$i$2_2 - 3)]) && (st[(index$i$2_2 - 3)] < cv$temp$3$$var230))?Math.log(cv$temp$2$var75[st[(index$i$2_2 - 3)]]):Double.NEGATIVE_INFINITY)));
													}
													
													// Recorded the probability of reaching sample task 78 with the current configuration.
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
				}
				
				// Processing random variable 101.
				{
					// Looking for a path between Sample 78 and consumer Bernoulli 101.
					{
						int traceTempVariable$var98$5_1 = cv$currentValue;
						for(int j = 5; j < (samples + 5); j += 1) {
							if(((i$var66 - 3) == (j - 5))) {
								// Processing sample task 103 of consumer random variable null.
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
													double cv$temp$4$var100;
													{
														// Constructing a random variable input for use later.
														double var100 = bias[((states - 1) - traceTempVariable$var98$5_1)];
														cv$temp$4$var100 = var100;
													}
													
													// Record the probability of sample task 103 generating output with current configuration.
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[(j - 5)], cv$temp$4$var100)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[(j - 5)], cv$temp$4$var100)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[(j - 5)], cv$temp$4$var100));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[(j - 5)], cv$temp$4$var100)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[(j - 5)], cv$temp$4$var100)));
													}
													
													// Recorded the probability of reaching sample task 103 with the current configuration.
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
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		int var77 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
		
		// Guards to ensure that st is only updated when there is a valid path.
		{
			{
				st[(i$var66 - 3)] = var77;
			}
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var28$countGlobal
		{
			// Allocation of cv$var28$countGlobal for single threaded execution
			cv$var28$countGlobal = new double[2];
		}
		
		// Constructor for cv$var52$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 51. Initially set to the value
			// of putTask 29.
			int cv$var29$max = 2;
			
			// Allocation of cv$var52$stateProbabilityGlobal for single threaded execution
			cv$var52$stateProbabilityGlobal = new double[cv$var29$max];
		}
		
		// Constructor for cv$var77$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 76. Initially set to the value
			// of putTask 29.
			int cv$var29$max = 2;
			
			// Allocation of cv$var77$stateProbabilityGlobal for single threaded execution
			cv$var77$stateProbabilityGlobal = new double[cv$var29$max];
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		{
			v = new double[2];
		}
		
		// If m has not been set already allocate space.
		if(!fixedFlag$sample28) {
			// Constructor for m
			{
				m = new double[2][];
				for(int var27 = 0; var27 < 2; var27 += 1)
					m[var27] = new double[2];
			}
		}
		
		// If bias has not been set already allocate space.
		if(!fixedFlag$sample45) {
			// Constructor for bias
			{
				bias = new double[2];
			}
		}
		
		// If st has not been set already allocate space.
		if((!fixedFlag$sample53 || !fixedFlag$sample78)) {
			// Constructor for st
			{
				st = new int[length$flipsMeasured];
			}
		}
		
		// Constructor for flips
		{
			flips = new boolean[length$flipsMeasured];
		}
		
		// Constructor for logProbability$var76
		{
			logProbability$var76 = new double[(((((length$flipsMeasured + 3) - 1) - 4) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample78
		{
			logProbability$sample78 = new double[(((((length$flipsMeasured + 3) - 1) - 4) / 1) + 1)];
		}
		
		// Constructor for logProbability$var101
		{
			logProbability$var101 = new double[(((((length$flipsMeasured + 5) - 1) - 5) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample103
		{
			logProbability$sample103 = new double[(((((length$flipsMeasured + 5) - 1) - 5) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1) {
			if(!fixedFlag$sample78)
				st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[((states - 1) - st[(i$var66 - 4)])], states);
		}
		for(int j = 5; j < (samples + 5); j += 1)
			flips[(j - 5)] = DistributionSampling.sampleBernoulli(RNG$, bias[((states - 1) - st[(j - 5)])]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1) {
			if(!fixedFlag$sample78)
				st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[((states - 1) - st[(i$var66 - 4)])], states);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1) {
			if(!fixedFlag$sample78)
				st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[((states - 1) - st[(i$var66 - 4)])], states);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			for(int var27 = 0; var27 < states; var27 += 1) {
				if(!fixedFlag$sample28)
					sample28(var27);
			}
			for(int var43 = 0; var43 < states; var43 += 1) {
				if(!fixedFlag$sample45)
					sample45(var43);
			}
			if(!fixedFlag$sample53)
				sample53();
			for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1) {
				if(!fixedFlag$sample78)
					sample78(i$var66);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var66 = ((samples + 3) - (((((samples + 3) - 1) - 4) % 1) + 1)); i$var66 >= ((4 - 1) + 1); i$var66 -= 1) {
				if(!fixedFlag$sample78)
					sample78(i$var66);
			}
			if(!fixedFlag$sample53)
				sample53();
			for(int var43 = (states - ((((states - 1) - 0) % 1) + 1)); var43 >= ((0 - 1) + 1); var43 -= 1) {
				if(!fixedFlag$sample45)
					sample45(var43);
			}
			for(int var27 = (states - ((((states - 1) - 0) % 1) + 1)); var27 >= ((0 - 1) + 1); var27 -= 1) {
				if(!fixedFlag$sample28)
					sample28(var27);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		states = 2;
		for(int i$var13 = 0; i$var13 < 2; i$var13 += 1)
			v[i$var13] = 0.1;
		samples = length$flipsMeasured;
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
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = 0.0;
		logProbability$var51 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var52 = 0.0;
		for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1)
			logProbability$var76[((i$var66 - 4) / 1)] = 0.0;
		if(!fixedProbFlag$sample78) {
			for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1)
				logProbability$sample78[((i$var66 - 4) / 1)] = 0.0;
		}
		for(int j = 5; j < (samples + 5); j += 1)
			logProbability$var101[((j - 5) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample103) {
			for(int j = 5; j < (samples + 5); j += 1)
				logProbability$sample103[((j - 5) / 1)] = 0.0;
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
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		if(fixedFlag$sample78)
			logProbabilityValue$sample78();
		logProbabilityValue$sample103();
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
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample78();
		logProbabilityValue$sample103();
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
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample78();
		logProbabilityValue$sample103();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1) {
			if(!fixedFlag$sample78)
				st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[((states - 1) - st[(i$var66 - 4)])], states);
		}
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
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
		     + "model HMMTestPart6(boolean[] flipsMeasured) {\n"
		     + "        int states = 2;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        int[] st = new int[samples];\n"
		     + "\n"
		     + "        st[0] = categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i:[4..samples + 3))\n"
		     + "            st[i-3] = categorical(m[(states - 1) - st[i-4]]).sample();\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[5..samples+5))\n"
		     + "            flips[j-5] = bernoulli(bias[(states - 1) - st[j-5]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}