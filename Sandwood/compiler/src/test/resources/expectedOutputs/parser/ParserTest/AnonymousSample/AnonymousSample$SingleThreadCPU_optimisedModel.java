package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class AnonymousSample$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements AnonymousSample$CoreInterface {
	
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
	private double[] logProbability$sample35;
	private double[] logProbability$sample39;
	private double logProbability$var14;
	private double logProbability$var20;
	private double[] logProbability$var34;
	private double[] logProbability$var38;
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

	public AnonymousSample$SingleThreadCPU(ExecutionTarget target) {
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
		// 
		// Substituted "fixedFlag$sample15" with its value "cv$value".
		fixedProbFlag$sample15 = (cv$value && fixedProbFlag$sample15);
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample15" with its value "cv$value".
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
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
		// 
		// Substituted "fixedFlag$sample21" with its value "cv$value".
		fixedProbFlag$sample21 = (cv$value && fixedProbFlag$sample21);
		
		// Should the probability of sample 39 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample21" with its value "cv$value".
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
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
		// 
		// Substituted "fixedFlag$sample9" with its value "cv$value".
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample9" with its value "cv$value".
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		
		// Should the probability of sample 39 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample9" with its value "cv$value".
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
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
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((mean1 - 2000.0) / 100.0)) - 4.605170185988092);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var14 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$mean1 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample15)
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
			fixedProbFlag$sample15 = fixedFlag$sample15;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var14 = logProbability$mean1;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$mean1);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample15)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$mean1);
		}
	}

	// Calculate the probability of the samples represented by sample21 using sampled
	// values.
	private final void logProbabilityValue$sample21() {
		// Determine if we need to calculate the values for sample task 21 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample21) {
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
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((mean2 - 2000.0) / 100.0)) - 4.605170185988092);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var20 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$mean2 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample21)
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
			fixedProbFlag$sample21 = fixedFlag$sample21;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var20 = logProbability$mean2;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$mean2);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample21)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$mean2);
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
			for(int i = 0; i < n; i += 1) {
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((amounts1[i] - mean1) / Math.sqrt(priorSigma2))) - (Math.log(priorSigma2) * 0.5));
				
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
				logProbability$var34[i] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample35[i] = cv$distributionAccumulator;
			}
			
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
			for(int i = 0; i < n; i += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample35[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var34[i] = cv$rvAccumulator;
			}
			
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
			for(int i = 0; i < n; i += 1) {
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((var39[i] - mean2) / Math.sqrt(priorSigma2))) - (Math.log(priorSigma2) * 0.5));
				
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
				logProbability$var38[i] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample39[i] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$var39 = (logProbability$var39 + cv$accumulator);
			
			// Update the variable probability
			logProbability$amounts2 = (logProbability$amounts2 + cv$accumulator);
			
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
			for(int i = 0; i < n; i += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample39[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var38[i] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$var39 = (logProbability$var39 + cv$accumulator);
			
			// Update the variable probability
			logProbability$amounts2 = (logProbability$amounts2 + cv$accumulator);
			
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
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((priorSigma2 - 10000.0) / 30.0)) - 3.4011973816621555);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var8 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$priorSigma2 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample9)
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
			fixedProbFlag$sample9 = fixedFlag$sample9;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var8 = logProbability$priorSigma2;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$priorSigma2);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$priorSigma2);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 15 drawn from Gaussian 14. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample15() {
		// State to record the weighting of each sample that is consumed. This is the:
		// sum of the sample denominator*(the sample value - the sample nominator).
		double cv$sum = 0.0;
		
		// State for storing the sum of the squares of the sample denominators.
		double cv$denominatorSquareSum = 0.0;
		
		// Flag to record if we have a value for Sigma.
		boolean cv$sigmaNotFound = true;
		
		// State for the value of sigma once we find it.
		double cv$sigmaValue = 1.0;
		
		// Processing random variable 34.
		for(int i = 0; i < n; i += 1) {
			// Processing sample task 35 of consumer random variable null.
			// Record the value of a sample generated by a consuming sample 35 of random variable
			// var34.
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
			cv$sum = (cv$sum + amounts1[i]);
			
			// If we have not got the value of sigma yet record it and set a flag so it is not
			// recorded again.
			if(cv$sigmaNotFound) {
				cv$sigmaValue = priorSigma2;
				cv$sigmaNotFound = false;
			}
		}
		
		// Write out the new value of the sample.
		mean1 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 2000.0, 10000.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 21 drawn from Gaussian 20. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample21() {
		// State to record the weighting of each sample that is consumed. This is the:
		// sum of the sample denominator*(the sample value - the sample nominator).
		double cv$sum = 0.0;
		
		// State for storing the sum of the squares of the sample denominators.
		double cv$denominatorSquareSum = 0.0;
		
		// Flag to record if we have a value for Sigma.
		boolean cv$sigmaNotFound = true;
		
		// State for the value of sigma once we find it.
		double cv$sigmaValue = 1.0;
		
		// Processing random variable 38.
		for(int i = 0; i < n; i += 1) {
			// Processing sample task 39 of consumer random variable null.
			// Record the value of a sample generated by a consuming sample 39 of random variable
			// var38.
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
			cv$sum = (cv$sum + var39[i]);
			
			// If we have not got the value of sigma yet record it and set a flag so it is not
			// recorded again.
			if(cv$sigmaNotFound) {
				cv$sigmaValue = priorSigma2;
				cv$sigmaNotFound = false;
			}
		}
		
		// Write out the new value of the sample.
		mean2 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 2000.0, 10000.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 9 drawn from Gaussian 8. Inference was performed using Metropolis-Hastings.
	private final void sample9() {
		// The original value of the sample
		double cv$originalValue = priorSigma2;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		// 
		// The original value of the sample
		double cv$var = ((priorSigma2 * priorSigma2) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		// 
		// The original value of the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + priorSigma2);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var7" with its value "900.0".
			// 
			// Set the current value to the current state of the tree.
			// 
			// The original value of the sample
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((priorSigma2 - 10000.0) / 30.0)) - 3.4011973816621555);
			
			// Processing random variable 34.
			for(int i = 0; i < n; i += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 35 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 35 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$2$mean1" with its value "mean1".
				// 
				// Substituted "cv$temp$3$priorSigma2" with its value "cv$currentValue".
				// 
				// Set the current value to the current state of the tree.
				// 
				// The original value of the sample
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((amounts1[i] - mean1) / Math.sqrt(priorSigma2))) + cv$accumulatedProbabilities) - (Math.log(priorSigma2) * 0.5));
			
			// Processing random variable 38.
			for(int i = 0; i < n; i += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 39 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 39 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$4$mean2" with its value "mean2".
				// 
				// Substituted "cv$temp$5$priorSigma2" with its value "cv$currentValue".
				// 
				// Set the current value to the current state of the tree.
				// 
				// The original value of the sample
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((var39[i] - mean2) / Math.sqrt(priorSigma2))) + cv$accumulatedProbabilities) - (Math.log(priorSigma2) * 0.5));
			
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
		priorSigma2 = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var7" with its value "900.0".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 10000.0) / 30.0)) - 3.4011973816621555);
		
		// Processing random variable 34.
		for(int i = 0; i < n; i += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 35 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 35 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "cv$temp$2$mean1" with its value "mean1".
			// 
			// Substituted "cv$temp$3$priorSigma2" with its value "cv$currentValue".
			cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((amounts1[i] - mean1) / Math.sqrt(cv$proposedValue))) + cv$accumulatedProbabilities) - (Math.log(cv$proposedValue) * 0.5));
		
		// Processing random variable 38.
		for(int i = 0; i < n; i += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 39 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 39 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "cv$temp$4$mean2" with its value "mean2".
			// 
			// Substituted "cv$temp$5$priorSigma2" with its value "cv$currentValue".
			cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((var39[i] - mean2) / Math.sqrt(cv$proposedValue))) + cv$accumulatedProbabilities) - (Math.log(cv$proposedValue) * 0.5));
		
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the new value of the sample.
			priorSigma2 = cv$originalValue;
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
		amounts1 = new double[length$obsAmounts1];
		
		// Constructor for amounts2
		amounts2 = new double[length$obsAmounts1];
		
		// Constructor for var39
		var39 = new double[length$obsAmounts1];
		
		// Constructor for logProbability$var34
		logProbability$var34 = new double[length$obsAmounts1];
		
		// Constructor for logProbability$sample35
		logProbability$sample35 = new double[length$obsAmounts1];
		
		// Constructor for logProbability$var38
		logProbability$var38 = new double[length$obsAmounts1];
		
		// Constructor for logProbability$sample39
		logProbability$sample39 = new double[length$obsAmounts1];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((DistributionSampling.sampleGaussian(RNG$) * 30.0) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
		for(int i = 0; i < n; i += 1) {
			amounts1[i] = ((Math.sqrt(priorSigma2) * DistributionSampling.sampleGaussian(RNG$)) + mean1);
			var39[i] = ((Math.sqrt(priorSigma2) * DistributionSampling.sampleGaussian(RNG$)) + mean2);
			amounts2[i] = (amounts1[i] + var39[i]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((DistributionSampling.sampleGaussian(RNG$) * 30.0) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((DistributionSampling.sampleGaussian(RNG$) * 30.0) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
		for(int i = 0; i < n; i += 1) {
			amounts1[i] = ((Math.sqrt(priorSigma2) * DistributionSampling.sampleGaussian(RNG$)) + mean1);
			var39[i] = ((Math.sqrt(priorSigma2) * DistributionSampling.sampleGaussian(RNG$)) + mean2);
			amounts2[i] = (amounts1[i] + var39[i]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((DistributionSampling.sampleGaussian(RNG$) * 30.0) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((DistributionSampling.sampleGaussian(RNG$) * 30.0) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
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
		for(int i = 0; i < n; i += 1)
			logProbability$var34[i] = Double.NaN;
		logProbability$amounts1 = 0.0;
		if(!fixedProbFlag$sample35) {
			for(int i = 0; i < n; i += 1)
				logProbability$sample35[i] = Double.NaN;
		}
		for(int i = 0; i < n; i += 1)
			logProbability$var38[i] = Double.NaN;
		logProbability$var39 = 0.0;
		logProbability$amounts2 = 0.0;
		if(!fixedProbFlag$sample39) {
			for(int i = 0; i < n; i += 1)
				logProbability$sample39[i] = Double.NaN;
		}
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
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

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		{
			// Deep copy between arrays
			int cv$length1 = amounts1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				amounts1[cv$index1] = obsAmounts1[cv$index1];
		}
		int cv$length1 = amounts2.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			amounts2[cv$index1] = obsAmounts2[cv$index1];
		for(int i = (n - 1); i >= 0; i -= 1)
			var39[i] = (amounts2[i] - amounts1[i]);
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