package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LDATest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LDATest$CoreInterface {
	
	// Declare the variables for the model.
	private double[] alpha;
	private double[] beta;
	private double[][] cv$var46$countGlobal;
	private double[][] cv$var61$countGlobal;
	private double[][] cv$var94$stateProbabilityGlobal;
	private int[][] documents;
	private boolean fixedFlag$sample102 = false;
	private boolean fixedFlag$sample105 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample64 = false;
	private boolean fixedProbFlag$sample102 = false;
	private boolean fixedProbFlag$sample105 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample64 = false;
	private int[] length$documents;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$phi;
	private double[][] logProbability$sample102;
	private double[][] logProbability$sample105;
	private double logProbability$theta;
	private double logProbability$var34;
	private double logProbability$var46;
	private double logProbability$var48;
	private double logProbability$var61;
	private double[][] logProbability$var93;
	private double[][] logProbability$var96;
	private double logProbability$w;
	private double logProbability$z;
	private int noTopics;
	private double[][] phi;
	private boolean setFlag$phi = false;
	private boolean setFlag$theta = false;
	private boolean setFlag$w = false;
	private boolean setFlag$z = false;
	private boolean system$gibbsForward = true;
	private double[][] theta;
	private int vocabSize;
	private int[][] w;
	private int[][] z;

	public LDATest$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for alpha.
	@Override
	public final double[] get$alpha() {
		return alpha;
	}

	// Getter for beta.
	@Override
	public final double[] get$beta() {
		return beta;
	}

	// Getter for documents.
	@Override
	public final int[][] get$documents() {
		return documents;
	}

	// Setter for documents.
	@Override
	public final void set$documents(int[][] cv$value) {
		// Set documents with flag to mark that it has been set so another array doesn't need
		// to be constructed
		documents = cv$value;
	}

	// Getter for fixedFlag$sample102.
	@Override
	public final boolean get$fixedFlag$sample102() {
		return fixedFlag$sample102;
	}

	// Setter for fixedFlag$sample102.
	@Override
	public final void set$fixedFlag$sample102(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample102 including if probabilities
		// need to be updated.
		fixedFlag$sample102 = cv$value;
		
		// Should the probability of sample 102 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample102 = (fixedFlag$sample102 && fixedProbFlag$sample102);
		
		// Should the probability of sample 105 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample105 = (fixedFlag$sample102 && fixedProbFlag$sample105);
	}

	// Getter for fixedFlag$sample105.
	@Override
	public final boolean get$fixedFlag$sample105() {
		return fixedFlag$sample105;
	}

	// Setter for fixedFlag$sample105.
	@Override
	public final void set$fixedFlag$sample105(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample105 including if probabilities
		// need to be updated.
		fixedFlag$sample105 = cv$value;
		
		// Should the probability of sample 105 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample105 = (fixedFlag$sample105 && fixedProbFlag$sample105);
	}

	// Getter for fixedFlag$sample47.
	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	// Setter for fixedFlag$sample47.
	@Override
	public final void set$fixedFlag$sample47(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample47 including if probabilities
		// need to be updated.
		fixedFlag$sample47 = cv$value;
		
		// Should the probability of sample 47 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample47 = (fixedFlag$sample47 && fixedProbFlag$sample47);
		
		// Should the probability of sample 105 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample105 = (fixedFlag$sample47 && fixedProbFlag$sample105);
	}

	// Getter for fixedFlag$sample64.
	@Override
	public final boolean get$fixedFlag$sample64() {
		return fixedFlag$sample64;
	}

	// Setter for fixedFlag$sample64.
	@Override
	public final void set$fixedFlag$sample64(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample64 including if probabilities
		// need to be updated.
		fixedFlag$sample64 = cv$value;
		
		// Should the probability of sample 64 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample64 = (fixedFlag$sample64 && fixedProbFlag$sample64);
		
		// Should the probability of sample 102 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample102 = (fixedFlag$sample64 && fixedProbFlag$sample102);
	}

	// Getter for length$documents.
	@Override
	public final int[] get$length$documents() {
		return length$documents;
	}

	// Setter for length$documents.
	@Override
	public final void set$length$documents(int[] cv$value) {
		// Set length$documents with flag to mark that it has been set so another array doesn't
		// need to be constructed
		length$documents = cv$value;
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

	// Getter for logProbability$phi.
	@Override
	public final double get$logProbability$phi() {
		return logProbability$phi;
	}

	// Getter for logProbability$theta.
	@Override
	public final double get$logProbability$theta() {
		return logProbability$theta;
	}

	// Getter for logProbability$w.
	@Override
	public final double get$logProbability$w() {
		return logProbability$w;
	}

	// Getter for logProbability$z.
	@Override
	public final double get$logProbability$z() {
		return logProbability$z;
	}

	// Getter for noTopics.
	@Override
	public final int get$noTopics() {
		return noTopics;
	}

	// Setter for noTopics.
	@Override
	public final void set$noTopics(int cv$value) {
		noTopics = cv$value;
	}

	// Getter for phi.
	@Override
	public final double[][] get$phi() {
		return phi;
	}

	// Setter for phi.
	@Override
	public final void set$phi(double[][] cv$value) {
		// Set flags for all the side effects of phi including if probabilities need to be
		// updated.
		// Set phi with flag to mark that it has been set so another array doesn't need to
		// be constructed
		phi = cv$value;
		setFlag$phi = true;
		
		// Unset the fixed probability flag for sample 47 as it depends on phi.
		fixedProbFlag$sample47 = false;
		
		// Unset the fixed probability flag for sample 105 as it depends on phi.
		fixedProbFlag$sample105 = false;
	}

	// Getter for theta.
	@Override
	public final double[][] get$theta() {
		return theta;
	}

	// Setter for theta.
	@Override
	public final void set$theta(double[][] cv$value) {
		// Set flags for all the side effects of theta including if probabilities need to
		// be updated.
		// Set theta with flag to mark that it has been set so another array doesn't need
		// to be constructed
		theta = cv$value;
		setFlag$theta = true;
		
		// Unset the fixed probability flag for sample 64 as it depends on theta.
		fixedProbFlag$sample64 = false;
		
		// Unset the fixed probability flag for sample 102 as it depends on theta.
		fixedProbFlag$sample102 = false;
	}

	// Getter for vocabSize.
	@Override
	public final int get$vocabSize() {
		return vocabSize;
	}

	// Setter for vocabSize.
	@Override
	public final void set$vocabSize(int cv$value) {
		vocabSize = cv$value;
	}

	// Getter for w.
	@Override
	public final int[][] get$w() {
		return w;
	}

	// Setter for w.
	@Override
	public final void set$w(int[][] cv$value) {
		// Set flags for all the side effects of w including if probabilities need to be updated.
		// Set w with flag to mark that it has been set so another array doesn't need to be
		// constructed
		w = cv$value;
		setFlag$w = true;
		
		// Unset the fixed probability flag for sample 105 as it depends on w.
		fixedProbFlag$sample105 = false;
	}

	// Getter for z.
	@Override
	public final int[][] get$z() {
		return z;
	}

	// Setter for z.
	@Override
	public final void set$z(int[][] cv$value) {
		// Set z with flag to mark that it has been set so another array doesn't need to be
		// constructed
		z = cv$value;
		setFlag$z = true;
	}

	// Calculate the probability of the samples represented by sample102 using sampled
	// values.
	private final void logProbabilityValue$sample102() {
		// Determine if we need to calculate the values for sample task 102 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample102) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = z[((i$var75 - 0) / 1)][((j - 0) / 1)];
						{
							{
								double[] var92 = theta[i$var75];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
					logProbability$var93[((i$var75 - 0) / 1)][((j - 0) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample102[((i$var75 - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			logProbability$z = (logProbability$z + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample102)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample102 = (fixedFlag$sample102 && fixedFlag$sample64);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample102[((i$var75 - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var93[((i$var75 - 0) / 1)][((j - 0) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$z = (logProbability$z + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample102)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample105 using sampled
	// values.
	private final void logProbabilityValue$sample105() {
		// Determine if we need to calculate the values for sample task 105 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample105) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = w[i$var75][j];
						{
							{
								double[] var95 = phi[z[((i$var75 - 0) / 1)][((j - 0) / 1)]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var95.length))?Math.log(var95[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
					logProbability$var96[((i$var75 - 0) / 1)][((j - 0) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample105[((i$var75 - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			logProbability$w = (logProbability$w + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample105 = ((fixedFlag$sample105 && fixedFlag$sample47) && fixedFlag$sample102);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample105[((i$var75 - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var96[((i$var75 - 0) / 1)][((j - 0) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$w = (logProbability$w + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample47 using sampled
	// values.
	private final void logProbabilityValue$sample47() {
		// Determine if we need to calculate the values for sample task 47 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample47) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < noTopics; var45 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = phi[var45];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, beta));
							
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
			logProbability$var46 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$phi = (logProbability$phi + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample47 = fixedFlag$sample47;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var46;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var34 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$phi = (logProbability$phi + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample64 using sampled
	// values.
	private final void logProbabilityValue$sample64() {
		// Determine if we need to calculate the values for sample task 64 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample64) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var60 = 0; var60 < length$documents.length; var60 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = theta[var60];
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
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var48 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var61 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$theta = (logProbability$theta + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample64)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample64 = fixedFlag$sample64;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var61;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var48 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$theta = (logProbability$theta + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample64)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 102 drawn from Categorical 93. Inference was performed using variable
	// marginalization.
	private final void sample102(int i$var75, int j, int threadID$cv$j, Rng RNG$) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// variable marginalization
			cv$noStates = Math.max(cv$noStates, noTopics);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var94$stateProbabilityGlobal[threadID$cv$j];
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
			z[((i$var75 - 0) / 1)][((j - 0) / 1)] = cv$currentValue;
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var92;
				{
					// Constructing a random variable input for use later.
					double[] var92 = theta[i$var75];
					cv$temp$0$var92 = var92;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var92.length))?Math.log(cv$temp$0$var92[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 96.
				{
					{
						// Processing sample task 105 of consumer random variable null.
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
											double[] cv$temp$1$var95;
											{
												// Constructing a random variable input for use later.
												double[] var95 = phi[cv$currentValue];
												cv$temp$1$var95 = var95;
											}
											
											// Record the probability of sample task 105 generating output with current configuration.
											if(((Math.log(1.0) + (((0.0 <= w[i$var75][j]) && (w[i$var75][j] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[w[i$var75][j]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= w[i$var75][j]) && (w[i$var75][j] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[w[i$var75][j]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
											else {
												// If the second value is -infinity.
												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= w[i$var75][j]) && (w[i$var75][j] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[w[i$var75][j]]):Double.NEGATIVE_INFINITY));
												else
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= w[i$var75][j]) && (w[i$var75][j] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[w[i$var75][j]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= w[i$var75][j]) && (w[i$var75][j] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[w[i$var75][j]]):Double.NEGATIVE_INFINITY)));
											}
											
											// Recorded the probability of reaching sample task 105 with the current configuration.
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
		z[((i$var75 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 47 drawn from Dirichlet 34. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample47(int var45, int threadID$cv$var45, Rng RNG$) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = phi[var45];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var46$countGlobal[threadID$cv$var45];
		
		// Get the length of the array
		int cv$arrayLength = vocabSize;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 96.
			{
				// Looking for a path between Sample 47 and consumer Categorical 96.
				{
					for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
						for(int j = 0; j < length$documents[i$var75]; j += 1) {
							if((var45 == z[((i$var75 - 0) / 1)][((j - 0) / 1)])) {
								// Processing sample task 105 of consumer random variable null.
								{
									{
										{
											{
												{
													// Increment the sample counter with the value sampled by sample task 105 of random
													// variable var96
													cv$countLocal[w[i$var75][j]] = (cv$countLocal[w[i$var75][j]] + 1.0);
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
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, beta, cv$countLocal, cv$targetLocal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 64 drawn from Dirichlet 48. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample64(int var60, int threadID$cv$var60, Rng RNG$) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = theta[var60];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var61$countGlobal[threadID$cv$var60];
		
		// Get the length of the array
		int cv$arrayLength = noTopics;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 93.
			{
				// Looking for a path between Sample 64 and consumer Categorical 93.
				{
					for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
						if((var60 == i$var75)) {
							for(int j = 0; j < length$documents[i$var75]; j += 1)
								// Increment the sample counter with the value sampled by sample task 102 of random
								// variable var93
								cv$countLocal[z[((i$var75 - 0) / 1)][((j - 0) / 1)]] = (cv$countLocal[z[((i$var75 - 0) / 1)][((j - 0) / 1)]] + 1.0);
						}
					}
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$countLocal, cv$targetLocal);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var46$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			for(int var45 = 0; var45 < noTopics; var45 += 1)
				cv$max = Math.max(cv$max, vocabSize);
			
			// Allocation of cv$var46$countGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var46$countGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var46$countGlobal[cv$index] = new double[cv$max];
			}
		}
		
		// Constructor for cv$var61$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			for(int var60 = 0; var60 < length$documents.length; var60 += 1)
				cv$max = Math.max(cv$max, noTopics);
			
			// Allocation of cv$var61$countGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var61$countGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var61$countGlobal[cv$index] = new double[cv$max];
			}
		}
		
		// Constructor for cv$var94$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 100. Initially set to the value
			// of putTask 65.
			int cv$var62$max = noTopics;
			
			// Allocation of cv$var94$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var94$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var94$stateProbabilityGlobal[cv$index] = new double[cv$var62$max];
			}
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for alpha
		{
			alpha = new double[noTopics];
		}
		
		// Constructor for beta
		{
			beta = new double[vocabSize];
		}
		
		// If phi has not been set already allocate space.
		if(!setFlag$phi) {
			// Constructor for phi
			{
				phi = new double[noTopics][];
				for(int var45 = 0; var45 < noTopics; var45 += 1)
					phi[var45] = new double[vocabSize];
			}
		}
		
		// If theta has not been set already allocate space.
		if(!setFlag$theta) {
			// Constructor for theta
			{
				theta = new double[length$documents.length][];
				for(int var60 = 0; var60 < length$documents.length; var60 += 1)
					theta[var60] = new double[noTopics];
			}
		}
		
		// If w has not been set already allocate space.
		if(!setFlag$w) {
			// Constructor for w
			{
				w = new int[length$documents.length][];
				for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
					w[i$var75] = new int[length$documents[i$var75]];
			}
		}
		
		// If z has not been set already allocate space.
		if(!setFlag$z) {
			// Constructor for z
			{
				z = new int[((((length$documents.length - 1) - 0) / 1) + 1)][];
				for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
					z[((i$var75 - 0) / 1)] = new int[((((length$documents[i$var75] - 1) - 0) / 1) + 1)];
			}
		}
		
		// Constructor for logProbability$var93
		{
			logProbability$var93 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
				logProbability$var93[((i$var75 - 0) / 1)] = new double[((((length$documents[i$var75] - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample102
		{
			logProbability$sample102 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
				logProbability$sample102[((i$var75 - 0) / 1)] = new double[((((length$documents[i$var75] - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var96
		{
			logProbability$var96 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
				logProbability$var96[((i$var75 - 0) / 1)] = new double[((((length$documents[i$var75] - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample105
		{
			logProbability$sample105 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
				logProbability$sample105[((i$var75 - 0) / 1)] = new double[((((length$documents[i$var75] - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noTopics, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						double[] var46 = phi[var45];
						if(!fixedFlag$sample47)
							DistributionSampling.sampleDirichlet(RNG$1, beta, var46);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$var60, int forEnd$var60, int threadID$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var60 = forStart$var60; var60 < forEnd$var60; var60 += 1) {
						double[] var61 = theta[var60];
						if(!fixedFlag$sample64)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, var61);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$index$i$var75, int forEnd$index$i$var75, int threadID$index$i$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var75 = forStart$index$i$var75; index$i$var75 < forEnd$index$i$var75; index$i$var75 += 1) {
						int i$var75 = index$i$var75;
						int threadID$i$var75 = threadID$index$i$var75;
						int[] t = w[i$var75];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, length$documents[i$var75], 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j = forStart$j; j < forEnd$j; j += 1) {
										if(!fixedFlag$sample102)
											z[((i$var75 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var75]);
										if(!fixedFlag$sample105)
											t[j] = DistributionSampling.sampleCategorical(RNG$2, phi[z[((i$var75 - 0) / 1)][((j - 0) / 1)]]);
									}
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noTopics, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						double[] var46 = phi[var45];
						if(!fixedFlag$sample47)
							DistributionSampling.sampleDirichlet(RNG$1, beta, var46);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$var60, int forEnd$var60, int threadID$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var60 = forStart$var60; var60 < forEnd$var60; var60 += 1) {
						double[] var61 = theta[var60];
						if(!fixedFlag$sample64)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, var61);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$index$i$var75, int forEnd$index$i$var75, int threadID$index$i$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var75 = forStart$index$i$var75; index$i$var75 < forEnd$index$i$var75; index$i$var75 += 1) {
						int i$var75 = index$i$var75;
						int threadID$i$var75 = threadID$index$i$var75;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, length$documents[i$var75], 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j = forStart$j; j < forEnd$j; j += 1) {
										if(!fixedFlag$sample102)
											z[((i$var75 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var75]);
									}
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noTopics, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						double[] var46 = phi[var45];
						if(!fixedFlag$sample47)
							DistributionSampling.sampleDirichlet(RNG$1, beta, var46);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$var60, int forEnd$var60, int threadID$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var60 = forStart$var60; var60 < forEnd$var60; var60 += 1) {
						double[] var61 = theta[var60];
						if(!fixedFlag$sample64)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, var61);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$index$i$var75, int forEnd$index$i$var75, int threadID$index$i$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var75 = forStart$index$i$var75; index$i$var75 < forEnd$index$i$var75; index$i$var75 += 1) {
						int i$var75 = index$i$var75;
						int threadID$i$var75 = threadID$index$i$var75;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, length$documents[i$var75], 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j = forStart$j; j < forEnd$j; j += 1) {
										if(!fixedFlag$sample102)
											z[((i$var75 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var75]);
									}
							}
						);
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
			parallelFor(RNG$, 0, noTopics, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!fixedFlag$sample47)
								sample47(var45, threadID$var45, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$var60, int forEnd$var60, int threadID$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var60 = forStart$var60; var60 < forEnd$var60; var60 += 1) {
							if(!fixedFlag$sample64)
								sample64(var60, threadID$var60, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$index$i$var75, int forEnd$index$i$var75, int threadID$index$i$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$i$var75 = forStart$index$i$var75; index$i$var75 < forEnd$index$i$var75; index$i$var75 += 1) {
							int i$var75 = index$i$var75;
							int threadID$i$var75 = threadID$index$i$var75;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, length$documents[i$var75], 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1) {
											if(!fixedFlag$sample102)
												sample102(i$var75, j, threadID$j, RNG$2);
										}
								}
							);
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$index$i$var75, int forEnd$index$i$var75, int threadID$index$i$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$i$var75 = forStart$index$i$var75; index$i$var75 < forEnd$index$i$var75; index$i$var75 += 1) {
							int i$var75 = index$i$var75;
							int threadID$i$var75 = threadID$index$i$var75;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, length$documents[i$var75], 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1) {
											if(!fixedFlag$sample102)
												sample102(i$var75, j, threadID$j, RNG$2);
										}
								}
							);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$var60, int forEnd$var60, int threadID$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var60 = forStart$var60; var60 < forEnd$var60; var60 += 1) {
							if(!fixedFlag$sample64)
								sample64(var60, threadID$var60, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noTopics, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!fixedFlag$sample47)
								sample47(var45, threadID$var45, RNG$1);
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
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noTopics, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1)
						alpha[i$var18] = 0.1;
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, vocabSize, 1,
			(int forStart$i$var31, int forEnd$i$var31, int threadID$i$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var31 = forStart$i$var31; i$var31 < forEnd$i$var31; i$var31 += 1)
						beta[i$var31] = 0.1;
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
		logProbability$var34 = 0.0;
		logProbability$phi = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$var46 = 0.0;
		logProbability$var48 = 0.0;
		logProbability$theta = 0.0;
		if(!fixedProbFlag$sample64)
			logProbability$var61 = 0.0;
		for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
			for(int j = 0; j < length$documents[i$var75]; j += 1)
				logProbability$var93[((i$var75 - 0) / 1)][((j - 0) / 1)] = 0.0;
		}
		logProbability$z = 0.0;
		if(!fixedProbFlag$sample102) {
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1)
					logProbability$sample102[((i$var75 - 0) / 1)][((j - 0) / 1)] = 0.0;
			}
		}
		for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
			for(int j = 0; j < length$documents[i$var75]; j += 1)
				logProbability$var96[((i$var75 - 0) / 1)][((j - 0) / 1)] = 0.0;
		}
		logProbability$w = 0.0;
		if(!fixedProbFlag$sample105) {
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1)
					logProbability$sample105[((i$var75 - 0) / 1)][((j - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(fixedFlag$sample64)
			logProbabilityValue$sample64();
		if(fixedFlag$sample102)
			logProbabilityValue$sample102();
		logProbabilityValue$sample105();
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
		logProbabilityValue$sample47();
		logProbabilityValue$sample64();
		logProbabilityValue$sample102();
		logProbabilityValue$sample105();
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
		logProbabilityValue$sample47();
		logProbabilityValue$sample64();
		logProbabilityValue$sample102();
		logProbabilityValue$sample105();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noTopics, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						double[] var46 = phi[var45];
						if(!fixedFlag$sample47)
							DistributionSampling.sampleDirichlet(RNG$1, beta, var46);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$var60, int forEnd$var60, int threadID$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var60 = forStart$var60; var60 < forEnd$var60; var60 += 1) {
						double[] var61 = theta[var60];
						if(!fixedFlag$sample64)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, var61);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$index$i$var75, int forEnd$index$i$var75, int threadID$index$i$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var75 = forStart$index$i$var75; index$i$var75 < forEnd$index$i$var75; index$i$var75 += 1) {
						int i$var75 = index$i$var75;
						int threadID$i$var75 = threadID$index$i$var75;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, length$documents[i$var75], 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j = forStart$j; j < forEnd$j; j += 1) {
										if(!fixedFlag$sample102)
											z[((i$var75 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var75]);
									}
							}
						);
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
		int[][] cv$source1 = documents;
		int[][] cv$target1 = w;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = cv$source1[cv$index1];
			int[] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel LDATest(int noTopics, int vocabSize, int[][] documents) {\n\n    double[] alpha = new double[noTopics];\n    for(int i:[0..noTopics))\n        alpha[i] = 0.1;\n\n    double[] beta = new double[vocabSize];\n    for(int i:[0..vocabSize))\n        beta[i] = 0.1;\n\n    double[][] phi = dirichlet(beta).sample(noTopics);\n    double[][] theta = dirichlet(alpha).sample(documents.length);\n    int[][] w = new int[documents.length][];\n\n    for(int i:[0..documents.length)) {\n        int[] t = new int[documents[i].length];\n        for(int j:[0..documents[i].length)) {\n            int z = categorical(theta[i]).sample();\n            t[j] = categorical(phi[z]).sample();\n        }\n        w[i] = t;\n    }\n\n    w.observe(documents);\n}\n";
	}
}