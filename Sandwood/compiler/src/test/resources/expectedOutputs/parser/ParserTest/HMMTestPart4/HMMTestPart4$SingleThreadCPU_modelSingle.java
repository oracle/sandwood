package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart4$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart4$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[] cv$var121$stateProbabilityGlobal;
	private double[] cv$var28$countGlobal;
	private double[] cv$var81$stateProbabilityGlobal;
	private boolean fixedFlag$sample124 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample84 = false;
	private boolean fixedProbFlag$sample124 = false;
	private boolean fixedProbFlag$sample191 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample84 = false;
	private boolean[][][] flips;
	private boolean[][][] flipsMeasured;
	private int[][] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double logProbability$st;
	private double logProbability$var120;
	private double logProbability$var121;
	private double logProbability$var16;
	private double logProbability$var185;
	private double logProbability$var186;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var80;
	private double logProbability$var81;
	private double[][] m;
	private int samples;
	private int[][][] st;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart4$SingleThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 191 as it depends on bias.
		fixedProbFlag$sample191 = false;
	}

	// Getter for fixedFlag$sample124.
	@Override
	public final boolean get$fixedFlag$sample124() {
		return fixedFlag$sample124;
	}

	// Setter for fixedFlag$sample124.
	@Override
	public final void set$fixedFlag$sample124(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample124 including if probabilities
		// need to be updated.
		fixedFlag$sample124 = cv$value;
		
		// Should the probability of sample 124 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample124 = (fixedFlag$sample124 && fixedProbFlag$sample124);
		
		// Should the probability of sample 191 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample191 = (fixedFlag$sample124 && fixedProbFlag$sample191);
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
		
		// Should the probability of sample 84 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample84 = (fixedFlag$sample28 && fixedProbFlag$sample84);
		
		// Should the probability of sample 124 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample124 = (fixedFlag$sample28 && fixedProbFlag$sample124);
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
		
		// Should the probability of sample 191 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample191 = (fixedFlag$sample45 && fixedProbFlag$sample191);
	}

	// Getter for fixedFlag$sample84.
	@Override
	public final boolean get$fixedFlag$sample84() {
		return fixedFlag$sample84;
	}

	// Setter for fixedFlag$sample84.
	@Override
	public final void set$fixedFlag$sample84(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample84 including if probabilities
		// need to be updated.
		fixedFlag$sample84 = cv$value;
		
		// Should the probability of sample 84 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample84 = (fixedFlag$sample84 && fixedProbFlag$sample84);
		
		// Should the probability of sample 191 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample191 = (fixedFlag$sample84 && fixedProbFlag$sample191);
	}

	// Getter for flips.
	@Override
	public final boolean[][][] get$flips() {
		return flips;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[][][] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[][][] cv$value) {
		// Set flipsMeasured
		flipsMeasured = cv$value;
	}

	// Getter for length$flipsMeasured.
	@Override
	public final int[][] get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	// Setter for length$flipsMeasured.
	@Override
	public final void set$length$flipsMeasured(int[][] cv$value) {
		// Set length$flipsMeasured
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
		
		// Unset the fixed probability flag for sample 84 as it depends on m.
		fixedProbFlag$sample84 = false;
		
		// Unset the fixed probability flag for sample 124 as it depends on m.
		fixedProbFlag$sample124 = false;
	}

	// Getter for samples.
	@Override
	public final int get$samples() {
		return samples;
	}

	// Getter for st.
	@Override
	public final int[][][] get$st() {
		return st;
	}

	// Setter for st.
	@Override
	public final void set$st(int[][][] cv$value) {
		// Set flags for all the side effects of st including if probabilities need to be
		// updated.
		// Set st
		st = cv$value;
		
		// Unset the fixed probability flag for sample 84 as it depends on st.
		fixedProbFlag$sample84 = false;
		
		// Unset the fixed probability flag for sample 124 as it depends on st.
		fixedProbFlag$sample124 = false;
		
		// Unset the fixed probability flag for sample 191 as it depends on st.
		fixedProbFlag$sample191 = false;
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

	// Calculate the probability of the samples represented by sample124 using sampled
	// values.
	private final void logProbabilityValue$sample124() {
		// Determine if we need to calculate the values for sample task 124 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample124) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i1 = 1; i1 < samples; i1 += 1) {
				for(int j1 = 0; j1 < samples; j1 += 1) {
					for(int k1 = 0; k1 < samples; k1 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						{
							// The sample value to calculate the probability of generating
							int cv$sampleValue = st[i1][j1][k1];
							{
								{
									double[] var119 = m[0];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									
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
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var120 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var121 = cv$accumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample124)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample124 = (fixedFlag$sample124 && fixedFlag$sample28);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var121;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var120 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample124)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample191 using sampled
	// values.
	private final void logProbabilityValue$sample191() {
		// Determine if we need to calculate the values for sample task 191 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample191) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						{
							// The sample value to calculate the probability of generating
							boolean cv$sampleValue = flips[l][n][p];
							{
								{
									double var184 = bias[st[p][l][n]];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var184));
									
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
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var185 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var186 = cv$accumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample191 = ((fixedFlag$sample45 && fixedFlag$sample84) && fixedFlag$sample124);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var186;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var185 = cv$rvAccumulator;
			
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

	// Calculate the probability of the samples represented by sample84 using sampled
	// values.
	private final void logProbabilityValue$sample84() {
		// Determine if we need to calculate the values for sample task 84 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample84) {
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
				int cv$sampleValue = st[0][0][0];
				{
					{
						double[] var79 = m[0];
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var79[cv$sampleValue]):Double.NEGATIVE_INFINITY));
						
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
			logProbability$var80 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$var81 = cv$sampleProbability;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample84 = (fixedFlag$sample84 && fixedFlag$sample28);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var81;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var80 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 124 drawn from Categorical 120. Inference was performed using variable
	// marginalization.
	private final void sample124(int i1, int j1, int k1) {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		{
			// variable marginalization
			cv$numNumStates = Math.max(cv$numNumStates, states);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var121$stateProbabilityGlobal;
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
			int var121 = cv$currentValue;
			
			// Guards to ensure that st is only updated when there is a valid path.
			{
				{
					int[][] var116 = st[i1];
					int[] var117 = var116[j1];
					var117[k1] = cv$currentValue;
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var119;
				{
					// Constructing a random variable input for use later.
					double[] var119 = m[0];
					cv$temp$0$var119 = var119;
				}
				int cv$temp$1$$var320;
				{
					// Constructing a random variable input for use later.
					int $var320 = states;
					cv$temp$1$$var320 = $var320;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var320))?Math.log(cv$temp$0$var119[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 185.
				{
					// Looking for a path between Sample 124 and consumer Bernoulli 185.
					{
						int traceTempVariable$var183$2_1 = cv$currentValue;
						for(int p = 0; p < samples; p += 1) {
							if((i1 == p)) {
								for(int l = 0; l < samples; l += 1) {
									if((j1 == l)) {
										for(int n = 0; n < samples; n += 1) {
											if((k1 == n)) {
												// Processing sample task 191 of consumer random variable null.
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
																	double cv$temp$2$var184;
																	{
																		// Constructing a random variable input for use later.
																		double var184 = bias[traceTempVariable$var183$2_1];
																		cv$temp$2$var184 = var184;
																	}
																	
																	// Record the probability of sample task 191 generating output with current configuration.
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var184)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var184)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var184));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var184)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var184)));
																	}
																	
																	// Recorded the probability of reaching sample task 191 with the current configuration.
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
		int var121 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
		
		// Guards to ensure that st is only updated when there is a valid path.
		{
			{
				int[][] var116 = st[i1];
				int[] var117 = var116[j1];
				var117[k1] = var121;
			}
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
			// Processing random variable 80.
			{
				// Looking for a path between Sample 28 and consumer Categorical 80.
				{
					if((var27 == 0)) {
						// Processing sample task 84 of consumer random variable null.
						{
							{
								{
									{
										{
											// Increment the sample counter with the value sampled by sample task 84 of random
											// variable var80
											cv$countLocal[st[0][0][0]] = (cv$countLocal[st[0][0][0]] + 1.0);
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 120.
			{
				// Looking for a path between Sample 28 and consumer Categorical 120.
				{
					if((var27 == 0)) {
						for(int i1 = 1; i1 < samples; i1 += 1) {
							for(int j1 = 0; j1 < samples; j1 += 1) {
								for(int k1 = 0; k1 < samples; k1 += 1)
									// Increment the sample counter with the value sampled by sample task 124 of random
									// variable var120
									cv$countLocal[st[i1][j1][k1]] = (cv$countLocal[st[i1][j1][k1]] + 1.0);
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
			// Processing random variable 185.
			{
				// Looking for a path between Sample 45 and consumer Bernoulli 185.
				{
					for(int l = 0; l < samples; l += 1) {
						for(int p = 0; p < samples; p += 1) {
							for(int n = 0; n < samples; n += 1) {
								if((var43 == st[p][l][n])) {
									// Processing sample task 191 of consumer random variable null.
									{
										{
											{
												{
													{
														// Include the value sampled by task 191 from random variable var185.
														// Increment the number of samples.
														cv$count = (cv$count + 1);
														
														// If the sample value was positive increase the count
														if(flips[l][n][p])
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
	// by sample task 84 drawn from Categorical 80. Inference was performed using variable
	// marginalization.
	private final void sample84() {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		{
			// variable marginalization
			cv$numNumStates = Math.max(cv$numNumStates, states);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var81$stateProbabilityGlobal;
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
			int var81 = cv$currentValue;
			
			// Guards to ensure that st is only updated when there is a valid path.
			{
				{
					int[][] var74 = st[0];
					int[] var76 = var74[0];
					var76[0] = cv$currentValue;
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var79;
				{
					// Constructing a random variable input for use later.
					double[] var79 = m[0];
					cv$temp$0$var79 = var79;
				}
				int cv$temp$1$$var296;
				{
					// Constructing a random variable input for use later.
					int $var296 = states;
					cv$temp$1$$var296 = $var296;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var296))?Math.log(cv$temp$0$var79[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 185.
				{
					// Looking for a path between Sample 84 and consumer Bernoulli 185.
					{
						int traceTempVariable$var183$2_1 = cv$currentValue;
						for(int p = 0; p < samples; p += 1) {
							if((0 == p)) {
								for(int l = 0; l < samples; l += 1) {
									if((0 == l)) {
										for(int n = 0; n < samples; n += 1) {
											if((0 == n)) {
												// Processing sample task 191 of consumer random variable null.
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
																	double cv$temp$2$var184;
																	{
																		// Constructing a random variable input for use later.
																		double var184 = bias[traceTempVariable$var183$2_1];
																		cv$temp$2$var184 = var184;
																	}
																	
																	// Record the probability of sample task 191 generating output with current configuration.
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var184)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var184)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var184));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var184)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var184)));
																	}
																	
																	// Recorded the probability of reaching sample task 191 with the current configuration.
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
		int var81 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
		
		// Guards to ensure that st is only updated when there is a valid path.
		{
			{
				int[][] var74 = st[0];
				int[] var76 = var74[0];
				var76[0] = var81;
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
		
		// Constructor for cv$var81$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 82. Initially set to the value
			// of putTask 29.
			int cv$var29$max = 2;
			
			// Allocation of cv$var81$stateProbabilityGlobal for single threaded execution
			cv$var81$stateProbabilityGlobal = new double[cv$var29$max];
		}
		
		// Constructor for cv$var121$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 122. Initially set to the value
			// of putTask 29.
			int cv$var29$max = 2;
			
			// Allocation of cv$var121$stateProbabilityGlobal for single threaded execution
			cv$var121$stateProbabilityGlobal = new double[cv$var29$max];
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
		if((!fixedFlag$sample84 || !fixedFlag$sample124)) {
			// Constructor for st
			{
				st = new int[length$flipsMeasured.length][][];
				for(int i$var57 = 0; i$var57 < length$flipsMeasured.length; i$var57 += 1) {
					int[][] subarray$0 = new int[length$flipsMeasured.length][];
					st[i$var57] = subarray$0;
					for(int j = 0; j < length$flipsMeasured.length; j += 1)
						subarray$0[j] = new int[length$flipsMeasured.length];
				}
			}
		}
		
		// Constructor for flips
		{
			flips = new boolean[length$flipsMeasured.length][][];
			for(int i2 = 0; i2 < length$flipsMeasured.length; i2 += 1) {
				boolean[][] subarray$0 = new boolean[length$flipsMeasured.length][];
				flips[i2] = subarray$0;
				for(int j2 = 0; j2 < length$flipsMeasured.length; j2 += 1)
					subarray$0[j2] = new boolean[length$flipsMeasured.length];
			}
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
		int[][] var74 = st[0];
		int[] var76 = var74[0];
		if(!fixedFlag$sample84)
			var76[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i1 = 1; i1 < samples; i1 += 1) {
			int[][] var116 = st[i1];
			for(int j1 = 0; j1 < samples; j1 += 1) {
				for(int k1 = 0; k1 < samples; k1 += 1) {
					int[] var117 = var116[j1];
					if(!fixedFlag$sample124)
						var117[k1] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
				}
			}
		}
		for(int l = 0; l < samples; l += 1) {
			boolean[][] var179 = flips[l];
			for(int p = 0; p < samples; p += 1) {
				for(int n = 0; n < samples; n += 1) {
					boolean[] var180 = var179[n];
					var180[p] = DistributionSampling.sampleBernoulli(RNG$, bias[st[p][l][n]]);
				}
			}
		}
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
		int[][] var74 = st[0];
		int[] var76 = var74[0];
		if(!fixedFlag$sample84)
			var76[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i1 = 1; i1 < samples; i1 += 1) {
			int[][] var116 = st[i1];
			for(int j1 = 0; j1 < samples; j1 += 1) {
				for(int k1 = 0; k1 < samples; k1 += 1) {
					int[] var117 = var116[j1];
					if(!fixedFlag$sample124)
						var117[k1] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
				}
			}
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
		int[][] var74 = st[0];
		int[] var76 = var74[0];
		if(!fixedFlag$sample84)
			var76[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i1 = 1; i1 < samples; i1 += 1) {
			int[][] var116 = st[i1];
			for(int j1 = 0; j1 < samples; j1 += 1) {
				for(int k1 = 0; k1 < samples; k1 += 1) {
					int[] var117 = var116[j1];
					if(!fixedFlag$sample124)
						var117[k1] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
				}
			}
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
			if(!fixedFlag$sample84)
				sample84();
			for(int i1 = 1; i1 < samples; i1 += 1) {
				for(int j1 = 0; j1 < samples; j1 += 1) {
					for(int k1 = 0; k1 < samples; k1 += 1) {
						if(!fixedFlag$sample124)
							sample124(i1, j1, k1);
					}
				}
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i1 = (samples - ((((samples - 1) - 1) % 1) + 1)); i1 >= ((1 - 1) + 1); i1 -= 1) {
				for(int j1 = (samples - ((((samples - 1) - 0) % 1) + 1)); j1 >= ((0 - 1) + 1); j1 -= 1) {
					for(int k1 = (samples - ((((samples - 1) - 0) % 1) + 1)); k1 >= ((0 - 1) + 1); k1 -= 1) {
						if(!fixedFlag$sample124)
							sample124(i1, j1, k1);
					}
				}
			}
			if(!fixedFlag$sample84)
				sample84();
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
		samples = length$flipsMeasured.length;
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
		logProbability$var80 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample84)
			logProbability$var81 = 0.0;
		logProbability$var120 = 0.0;
		if(!fixedProbFlag$sample124)
			logProbability$var121 = 0.0;
		logProbability$var185 = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample191)
			logProbability$var186 = 0.0;
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
		if(fixedFlag$sample84)
			logProbabilityValue$sample84();
		if(fixedFlag$sample124)
			logProbabilityValue$sample124();
		logProbabilityValue$sample191();
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
		logProbabilityValue$sample84();
		logProbabilityValue$sample124();
		logProbabilityValue$sample191();
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
		logProbabilityValue$sample84();
		logProbabilityValue$sample124();
		logProbabilityValue$sample191();
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
		int[][] var74 = st[0];
		int[] var76 = var74[0];
		if(!fixedFlag$sample84)
			var76[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i1 = 1; i1 < samples; i1 += 1) {
			int[][] var116 = st[i1];
			for(int j1 = 0; j1 < samples; j1 += 1) {
				for(int k1 = 0; k1 < samples; k1 += 1) {
					int[] var117 = var116[j1];
					if(!fixedFlag$sample124)
						var117[k1] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
				}
			}
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
		boolean[][][] cv$source1 = flipsMeasured;
		boolean[][][] cv$target1 = flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[][] cv$source2 = cv$source1[cv$index1];
			boolean[][] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
				boolean[] cv$source3 = cv$source2[cv$index2];
				boolean[] cv$target3 = cv$target2[cv$index2];
				int cv$length3 = cv$target3.length;
				for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
					cv$target3[cv$index3] = cv$source3[cv$index3];
			}
		}
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
		     + "model HMMTestPart4(boolean[][][] flipsMeasured) {\n"
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
		     + "        \n"
		     + "        \n"
		     + "        int[][][] st = new int[samples][][];\n"
		     + "        for(int i:[0..samples)) {\n"
		     + "            st[i] = new int[samples][];\n"
		     + "            for(int j:[0..samples))\n"
		     + "                st[i][j] = new int[samples];\n"
		     + "        }\n"
		     + "\n"
		     + "        st[0][0][0] = categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i1:[1..samples))\n"
		     + "            for(int j1:[0..samples))\n"
		     + "                for(int k1:[0..samples))\n"
		     + "                    st[i1][j1][k1] = categorical(m[0]).sample();\n"
		     + "            \n"
		     + "        boolean[][][] flips = new boolean[samples][][];\n"
		     + "        for(int i2:[0..samples)) {\n"
		     + "            flips[i2] = new boolean[samples][];\n"
		     + "            for(int j2:[0..samples))\n"
		     + "                flips[i2][j2] = new boolean[samples];\n"
		     + "        }\n"
		     + "            \n"
		     + "        for(int l:[0..samples))\n"
		     + "            for(int p:[0..samples))\n"
		     + "                for(int n:[0..samples))\n"
		     + "                    flips[l][n][p] = bernoulli(bias[st[p][l][n]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}