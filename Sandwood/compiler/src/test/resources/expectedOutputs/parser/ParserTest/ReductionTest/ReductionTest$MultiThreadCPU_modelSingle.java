package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ReductionTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements ReductionTest$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[][] cv$var18$countGlobal;
	private double[] cv$var35$stateProbabilityGlobal;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample37 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample37 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double logProbability$st;
	private double logProbability$var13;
	private double logProbability$var18;
	private double logProbability$var22;
	private double logProbability$var27;
	private double logProbability$var34;
	private double logProbability$var35;
	private double logProbability$var49;
	private double logProbability$var50;
	private double[][] m;
	private int noCats;
	private int noFlips;
	private int noStates;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public ReductionTest$MultiThreadCPU(ExecutionTarget target) {
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
		// Set bias with flag to mark that it has been set so another array doesn't need to
		// be constructed
		bias = cv$value;
		setFlag$bias = true;
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
		
		// Should the probability of sample 37 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample37 = (fixedFlag$sample20 && fixedProbFlag$sample37);
	}

	// Getter for fixedFlag$sample29.
	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	// Setter for fixedFlag$sample29.
	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample29 including if probabilities
		// need to be updated.
		fixedFlag$sample29 = cv$value;
		
		// Should the probability of sample 29 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedProbFlag$sample29);
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample53 = (fixedFlag$sample29 && fixedProbFlag$sample53);
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
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample53 = (fixedFlag$sample37 && fixedProbFlag$sample53);
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
	}

	// Getter for flips.
	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	// Setter for flips.
	@Override
	public final void set$flips(boolean[] cv$value) {
		// Set flips with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips = cv$value;
		setFlag$flips = true;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		// Set flipsMeasured with flag to mark that it has been set so another array doesn't
		// need to be constructed
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
		// Set m with flag to mark that it has been set so another array doesn't need to be
		// constructed
		m = cv$value;
		setFlag$m = true;
	}

	// Getter for noCats.
	@Override
	public final int get$noCats() {
		return noCats;
	}

	// Setter for noCats.
	@Override
	public final void set$noCats(int cv$value) {
		noCats = cv$value;
	}

	// Getter for noFlips.
	@Override
	public final int get$noFlips() {
		return noFlips;
	}

	// Getter for noStates.
	@Override
	public final int get$noStates() {
		return noStates;
	}

	// Getter for st.
	@Override
	public final int[] get$st() {
		return st;
	}

	// Setter for st.
	@Override
	public final void set$st(int[] cv$value) {
		// Set st with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st = cv$value;
		setFlag$st = true;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
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
			for(int var17 = 0; var17 < noCats; var17 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = m[var17];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v));
							
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
			logProbability$var13 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var18 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
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
			double cv$sampleValue = logProbability$var18;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var13 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample29 using sampled
	// values.
	private final void logProbabilityValue$sample29() {
		// Determine if we need to calculate the values for sample task 29 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample29) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var26 = 0; var26 < noFlips; var26 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = bias[var26];
					{
						{
							double var20 = 1.0;
							double var21 = 1.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var20, var21));
							
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
			logProbability$var27 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample29 = fixedFlag$sample29;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var27;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var22 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample29)
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
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var32];
					{
						{
							double[] var33 = m[i$var32];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, var33));
							
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
			logProbability$var34 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var35 = cv$accumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample37)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample37 = (fixedFlag$sample37 && fixedFlag$sample20);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var35;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var34 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample37)
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
			for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips[j$var40];
					{
						{
							// Reduction of array st
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							int reduceVar$var47$6 = 0;
							
							// For each index in the array to be reduced
							for(int cv$reduction46Index = 0; cv$reduction46Index < noCats; cv$reduction46Index += 1) {
								// Set the left hand term of the reduction function to the return variable value.
								int i$var44 = reduceVar$var47$6;
								
								// Set the right hand term to a value from the array st
								int j$var45 = st[cv$reduction46Index];
								
								// Execute the reduction function, saving the result into the return value.
								// 
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$var47$6 = (i$var44 + j$var45);
							}
							double var48 = bias[reduceVar$var47$6];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var48));
							
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
			logProbability$var49 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var50 = cv$accumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample53 = ((fixedFlag$sample53 && fixedFlag$sample29) && fixedFlag$sample37);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var50;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var49 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 20 drawn from Dirichlet 13. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample20(int var17, int threadID$cv$var17, Rng RNG$) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = m[var17];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var18$countGlobal[threadID$cv$var17];
		
		// Get the length of the array
		int cv$arrayLength = noStates;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 34.
			{
				// Looking for a path between Sample 20 and consumer Categorical 34.
				{
					for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1) {
						if((var17 == i$var32)) {
							// Processing sample task 37 of consumer random variable null.
							{
								{
									{
										{
											{
												// Increment the sample counter with the value sampled by sample task 37 of random
												// variable var34
												cv$countLocal[st[i$var32]] = (cv$countLocal[st[i$var32]] + 1.0);
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 29 drawn from Beta 22. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample29(int var26, int threadID$cv$var26, Rng RNG$) {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		{
			// Processing random variable 49.
			{
				// Looking for a path between Sample 29 and consumer Bernoulli 49.
				{
					// Reduction of array st
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					int reduceVar$var47$4 = 0;
					
					// For each index in the array to be reduced
					for(int cv$reduction46Index = 0; cv$reduction46Index < noCats; cv$reduction46Index += 1) {
						// Set the left hand term of the reduction function to the return variable value.
						int i$var44 = reduceVar$var47$4;
						
						// Set the right hand term to a value from the array st
						int j$var45 = st[cv$reduction46Index];
						
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						reduceVar$var47$4 = (i$var44 + j$var45);
					}
					if((var26 == reduceVar$var47$4)) {
						for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1) {
							// Include the value sampled by task 53 from random variable var49.
							// Increment the number of samples.
							cv$count = (cv$count + 1);
							
							// If the sample value was positive increase the count
							if(flips[j$var40])
								cv$sum = (cv$sum + 1);
						}
					}
				}
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		double var27 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[var26] = var27;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 37 drawn from Categorical 34. Inference was performed using variable
	// marginalization.
	private final void sample37(int i$var32) {
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var35$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
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
			int var35 = cv$currentValue;
			st[i$var32] = cv$currentValue;
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var33;
				{
					// Constructing a random variable input for use later.
					double[] var33 = m[i$var32];
					cv$temp$0$var33 = var33;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$currentValue, cv$temp$0$var33));
				
				// Processing random variable 49.
				{
					{
						int traceTempVariable$i$1_1 = cv$currentValue;
						if(((0 <= i$var32) && (i$var32 < noCats))) {
							{
								if((0 < noCats)) {
									// Reduction of array st
									// 
									// A generated name to prevent name collisions if the reduction is implemented more
									// than once in inference and probability code. Initialize the variable to the unit
									// value
									int reduceVar$var47$5 = 0;
									
									// Reduce for every value except a masked value which will be skipped.
									for(int cv$reduction218Index = 0; cv$reduction218Index < i$var32; cv$reduction218Index += 1) {
										// Set the left hand term of the reduction function to the return variable value.
										int i$var44 = reduceVar$var47$5;
										
										// Set the right hand term to a value from the array st
										int j$var45 = st[cv$reduction218Index];
										
										// Execute the reduction function, saving the result into the return value.
										// 
										// Copy the result of the reduction into the variable returned by the reduction.
										reduceVar$var47$5 = (i$var44 + j$var45);
									}
									for(int cv$reduction218Index = (i$var32 + 1); cv$reduction218Index < noCats; cv$reduction218Index += 1) {
										// Set the left hand term of the reduction function to the return variable value.
										int i$var44 = reduceVar$var47$5;
										
										// Set the right hand term to a value from the array st
										int j$var45 = st[cv$reduction218Index];
										
										// Execute the reduction function, saving the result into the return value.
										// 
										// Execute the reduction function, saving the result into the return value.
										// 
										// Copy the result of the reduction into the variable returned by the reduction.
										reduceVar$var47$5 = (i$var44 + j$var45);
									}
									int cv$reduced46 = reduceVar$var47$5;
									
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$var47$5 = (traceTempVariable$i$1_1 + cv$reduced46);
									int traceTempVariable$var47$1_2 = reduceVar$var47$5;
									for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1) {
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
														double cv$temp$1$var48;
														{
															// Constructing a random variable input for use later.
															double var48 = bias[traceTempVariable$var47$1_2];
															cv$temp$1$var48 = var48;
														}
														
														// Record the probability of sample task 53 generating output with current configuration.
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j$var40], cv$temp$1$var48)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j$var40], cv$temp$1$var48)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															// If the second value is -infinity.
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j$var40], cv$temp$1$var48));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j$var40], cv$temp$1$var48)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j$var40], cv$temp$1$var48)));
														}
														
														// Recorded the probability of reaching sample task 53 with the current configuration.
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
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
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
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		int var35 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		st[i$var32] = var35;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var18$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			for(int var17 = 0; var17 < noCats; var17 += 1)
				cv$max = Math.max(cv$max, (length$flipsMeasured / noCats));
			
			// Allocation of cv$var18$countGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var18$countGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var18$countGlobal[cv$index] = new double[cv$max];
			}
		}
		
		// Constructor for cv$var35$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 35. Initially set to the value
			// of putTask 21.
			int cv$var19$max = ((0.0 <= length$flipsMeasured)?((0.0 <= noCats)?(length$flipsMeasured / noCats):((noCats < 0.0)?(length$flipsMeasured / noCats):length$flipsMeasured)):((length$flipsMeasured < 0.0)?((0.0 <= noCats)?(length$flipsMeasured / noCats):((noCats < 0.0)?(length$flipsMeasured / noCats):(-length$flipsMeasured))):((0.0 <= noCats)?(length$flipsMeasured / noCats):((noCats < 0.0)?(length$flipsMeasured / noCats):Math.max(length$flipsMeasured, (-length$flipsMeasured))))));
			
			// Allocation of cv$var35$stateProbabilityGlobal for single threaded execution
			cv$var35$stateProbabilityGlobal = new double[cv$var19$max];
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		{
			v = new double[(length$flipsMeasured / noCats)];
		}
		
		// If m has not been set already allocate space.
		if(!setFlag$m) {
			// Constructor for m
			{
				m = new double[noCats][];
				for(int var17 = 0; var17 < noCats; var17 += 1)
					m[var17] = new double[(length$flipsMeasured / noCats)];
			}
		}
		
		// If bias has not been set already allocate space.
		if(!setFlag$bias) {
			// Constructor for bias
			{
				bias = new double[length$flipsMeasured];
			}
		}
		
		// If st has not been set already allocate space.
		if(!setFlag$st) {
			// Constructor for st
			{
				st = new int[noCats];
			}
		}
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips) {
			// Constructor for flips
			{
				flips = new boolean[length$flipsMeasured];
			}
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1) {
						double[] var18 = m[var17];
						if(!fixedFlag$sample20)
							DistributionSampling.sampleDirichlet(RNG$1, v, var18);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noFlips, 1,
			(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1) {
						if(!fixedFlag$sample29)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$i$var32, int forEnd$i$var32, int threadID$i$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var32 = forStart$i$var32; i$var32 < forEnd$i$var32; i$var32 += 1) {
						if(!fixedFlag$sample37)
							st[i$var32] = DistributionSampling.sampleCategorical(RNG$1, m[i$var32]);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noFlips, 1,
			(int forStart$j$var40, int forEnd$j$var40, int threadID$j$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var40 = forStart$j$var40; j$var40 < forEnd$j$var40; j$var40 += 1) {
						// Reduction of array st
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						int reduceVar$var47$7 = 0;
						
						// For each index in the array to be reduced
						for(int cv$reduction46Index = 0; cv$reduction46Index < noCats; cv$reduction46Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							int i$var44 = reduceVar$var47$7;
							
							// Set the right hand term to a value from the array st
							int j$var45 = st[cv$reduction46Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!fixedFlag$sample53)
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$var47$7 = (i$var44 + j$var45);
						}
						if(!fixedFlag$sample53)
							flips[j$var40] = DistributionSampling.sampleBernoulli(RNG$1, bias[reduceVar$var47$7]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1) {
						double[] var18 = m[var17];
						if(!fixedFlag$sample20)
							DistributionSampling.sampleDirichlet(RNG$1, v, var18);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noFlips, 1,
			(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1) {
						if(!fixedFlag$sample29)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$i$var32, int forEnd$i$var32, int threadID$i$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var32 = forStart$i$var32; i$var32 < forEnd$i$var32; i$var32 += 1) {
						if(!fixedFlag$sample37)
							st[i$var32] = DistributionSampling.sampleCategorical(RNG$1, m[i$var32]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1) {
						double[] var18 = m[var17];
						if(!fixedFlag$sample20)
							DistributionSampling.sampleDirichlet(RNG$1, v, var18);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noFlips, 1,
			(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1) {
						if(!fixedFlag$sample29)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$i$var32, int forEnd$i$var32, int threadID$i$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var32 = forStart$i$var32; i$var32 < forEnd$i$var32; i$var32 += 1) {
						if(!fixedFlag$sample37)
							st[i$var32] = DistributionSampling.sampleCategorical(RNG$1, m[i$var32]);
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1) {
							if(!fixedFlag$sample20)
								sample20(var17, threadID$var17, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noFlips, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1) {
							if(!fixedFlag$sample29)
								sample29(var26, threadID$var26, RNG$1);
						}
				}
			);
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1) {
				if(!fixedFlag$sample37)
					sample37(i$var32);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var32 = (noCats - ((((noCats - 1) - 0) % 1) + 1)); i$var32 >= ((0 - 1) + 1); i$var32 -= 1) {
				if(!fixedFlag$sample37)
					sample37(i$var32);
			}
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noFlips, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1) {
							if(!fixedFlag$sample29)
								sample29(var26, threadID$var26, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1) {
							if(!fixedFlag$sample20)
								sample20(var17, threadID$var17, RNG$1);
						}
				}
			);
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		noFlips = length$flipsMeasured;
		noStates = (length$flipsMeasured / noCats);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, (length$flipsMeasured / noCats), 1,
			(int forStart$i$var10, int forEnd$i$var10, int threadID$i$var10, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var10 = forStart$i$var10; i$var10 < forEnd$i$var10; i$var10 += 1)
						v[i$var10] = 0.1;
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
		logProbability$var13 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$var18 = 0.0;
		logProbability$var22 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var27 = 0.0;
		logProbability$var34 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample37)
			logProbability$var35 = 0.0;
		logProbability$var49 = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var50 = 0.0;
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
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(fixedFlag$sample37)
			logProbabilityValue$sample37();
		logProbabilityValue$sample53();
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
		logProbabilityValue$sample29();
		logProbabilityValue$sample37();
		logProbabilityValue$sample53();
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
		logProbabilityValue$sample29();
		logProbabilityValue$sample37();
		logProbabilityValue$sample53();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1) {
						double[] var18 = m[var17];
						if(!fixedFlag$sample20)
							DistributionSampling.sampleDirichlet(RNG$1, v, var18);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noFlips, 1,
			(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1) {
						if(!fixedFlag$sample29)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$i$var32, int forEnd$i$var32, int threadID$i$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var32 = forStart$i$var32; i$var32 < forEnd$i$var32; i$var32 += 1) {
						if(!fixedFlag$sample37)
							st[i$var32] = DistributionSampling.sampleCategorical(RNG$1, m[i$var32]);
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
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel ReductionTest(boolean[] flipsMeasured, int noCats) {\n    int noFlips = flipsMeasured.length;\n    int noStates = noFlips/noCats;\n    \n    double[] v = new double[noStates];\n    for(int i:[0..noStates))\n        v[i] = 0.1;\n    \n    double[][] m = dirichlet(v).sample(noCats);\n    \n    double[] bias = beta(1.0, 1.0).sample(noFlips);\n    \n    int[] st = new int[noCats];\n\n\n    for(int i:[0..noCats))\n        st[i] = categorical(m[i]).sample();\n            \n    boolean[] flips = new boolean[noFlips];\n            \n    for(int j:[0..noFlips))\n        flips[j] = bernoulli(bias[sum(st)]).sample();\n\n    flips.observe(flipsMeasured);\n    \n    private int sum(int[] a) {\n        return reduce(a, 0, (i,j) -> {\n            return i + j;\n        });\n    }\n}";
	}
}