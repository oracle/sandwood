package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoiceRandCoeff$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DiscreteChoiceRandCoeff$CoreInterface {
	
	// Declare the variables for the model.
	private int[] ObsChoices;
	private int[][] Prices;
	private double b;
	private double[] beta;
	private int[] choices;
	private double[][] exped;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean[] guard$sample21categorical102$global;
	private boolean[][] guard$sample21put101$global;
	private boolean[][] guard$sample47categorical102$global;
	private boolean[][][] guard$sample47put101$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$beta;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample103;
	private double[] logProbability$sample21;
	private double[] logProbability$sample47;
	private double logProbability$sigma;
	private double logProbability$ut;
	private double[] logProbability$var101;
	private double logProbability$var27;
	private double logProbability$var33;
	private double logProbability$var35;
	private double logProbability$var9;
	private int noObs;
	private int noProducts;
	private double[][] prob;
	private double sigma;
	private boolean system$gibbsForward = true;
	private double[] ut;

	public DiscreteChoiceRandCoeff$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for ObsChoices.
	@Override
	public final int[] get$ObsChoices() {
		return ObsChoices;
	}

	// Setter for ObsChoices.
	@Override
	public final void set$ObsChoices(int[] cv$value) {
		// Set ObsChoices
		ObsChoices = cv$value;
	}

	// Getter for Prices.
	@Override
	public final int[][] get$Prices() {
		return Prices;
	}

	// Setter for Prices.
	@Override
	public final void set$Prices(int[][] cv$value) {
		// Set Prices
		Prices = cv$value;
	}

	// Getter for b.
	@Override
	public final double get$b() {
		return b;
	}

	// Setter for b.
	@Override
	public final void set$b(double cv$value) {
		// Set flags for all the side effects of b including if probabilities need to be updated.
		b = cv$value;
		
		// Unset the fixed probability flag for sample 28 as it depends on b.
		fixedProbFlag$sample28 = false;
		
		// Unset the fixed probability flag for sample 47 as it depends on b.
		fixedProbFlag$sample47 = false;
	}

	// Getter for beta.
	@Override
	public final double[] get$beta() {
		return beta;
	}

	// Setter for beta.
	@Override
	public final void set$beta(double[] cv$value) {
		// Set flags for all the side effects of beta including if probabilities need to be
		// updated.
		// Set beta
		beta = cv$value;
		
		// Unset the fixed probability flag for sample 47 as it depends on beta.
		fixedProbFlag$sample47 = false;
		
		// Unset the fixed probability flag for sample 103 as it depends on beta.
		fixedProbFlag$sample103 = false;
	}

	// Getter for choices.
	@Override
	public final int[] get$choices() {
		return choices;
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
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample21" with its value "cv$value".
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
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
		// 
		// Substituted "fixedFlag$sample28" with its value "cv$value".
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		
		// Should the probability of sample 47 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample28" with its value "cv$value".
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
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
		
		// Should the probability of sample 47 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
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
		// 
		// Substituted "fixedFlag$sample47" with its value "cv$value".
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample47" with its value "cv$value".
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
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

	// Getter for logProbability$b.
	@Override
	public final double get$logProbability$b() {
		return logProbability$b;
	}

	// Getter for logProbability$beta.
	@Override
	public final double get$logProbability$beta() {
		return logProbability$beta;
	}

	// Getter for logProbability$choices.
	@Override
	public final double get$logProbability$choices() {
		return logProbability$choices;
	}

	// Getter for logProbability$prob.
	@Override
	public final double get$logProbability$prob() {
		return logProbability$prob;
	}

	// Getter for logProbability$sigma.
	@Override
	public final double get$logProbability$sigma() {
		return logProbability$sigma;
	}

	// Getter for logProbability$ut.
	@Override
	public final double get$logProbability$ut() {
		return logProbability$ut;
	}

	// Getter for noObs.
	@Override
	public final int get$noObs() {
		return noObs;
	}

	// Setter for noObs.
	@Override
	public final void set$noObs(int cv$value) {
		noObs = cv$value;
	}

	// Getter for noProducts.
	@Override
	public final int get$noProducts() {
		return noProducts;
	}

	// Setter for noProducts.
	@Override
	public final void set$noProducts(int cv$value) {
		noProducts = cv$value;
	}

	// Getter for prob.
	@Override
	public final double[][] get$prob() {
		return prob;
	}

	// Getter for sigma.
	@Override
	public final double get$sigma() {
		return sigma;
	}

	// Setter for sigma.
	@Override
	public final void set$sigma(double cv$value) {
		// Set flags for all the side effects of sigma including if probabilities need to
		// be updated.
		sigma = cv$value;
		
		// Unset the fixed probability flag for sample 34 as it depends on sigma.
		fixedProbFlag$sample34 = false;
		
		// Unset the fixed probability flag for sample 47 as it depends on sigma.
		fixedProbFlag$sample47 = false;
	}

	// Getter for ut.
	@Override
	public final double[] get$ut() {
		return ut;
	}

	// Setter for ut.
	@Override
	public final void set$ut(double[] cv$value) {
		// Set flags for all the side effects of ut including if probabilities need to be
		// updated.
		// Set ut
		ut = cv$value;
		
		// Unset the fixed probability flag for sample 21 as it depends on ut.
		fixedProbFlag$sample21 = false;
		
		// Unset the fixed probability flag for sample 103 as it depends on ut.
		fixedProbFlag$sample103 = false;
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
			for(int i = 0; i < noObs; i += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = choices[i];
				
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
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noProducts))?Math.log(prob[i][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
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
				logProbability$var101[i] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample103[i] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$choices = (logProbability$choices + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample103 = (fixedFlag$sample21 && fixedFlag$sample47);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample103[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var101[i] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$choices = (logProbability$choices + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
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
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var20 = 0; var20 < noProducts; var20 += 1) {
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[var20] / 3.1622776601683795)) - 1.151292546497023);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample21[var20] = cv$distributionAccumulator;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < noObs)) {
					// Update the variable probability
					logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
					
					// Update the variable probability
					logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
				}
			}
			logProbability$var9 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$ut = (logProbability$ut + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample21)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample21 = fixedFlag$sample21;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int var20 = 0; var20 < noProducts; var20 += 1) {
				double cv$sampleValue = logProbability$sample21[var20];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < noObs)) {
					// Update the variable probability
					logProbability$exped = (logProbability$exped + cv$sampleValue);
					
					// Update the variable probability
					logProbability$prob = (logProbability$prob + cv$sampleValue);
				}
			}
			logProbability$var9 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample28 using sampled
	// values.
	private final void logProbabilityValue$sample28() {
		// Determine if we need to calculate the values for sample task 28 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample28) {
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
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((b / 3.1622776601683795)) - 1.151292546497023);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var27 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$b = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample28)
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
			fixedProbFlag$sample28 = fixedFlag$sample28;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var27 = logProbability$b;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$b);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample28)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$b);
		}
	}

	// Calculate the probability of the samples represented by sample34 using sampled
	// values.
	private final void logProbabilityValue$sample34() {
		// Determine if we need to calculate the values for sample task 34 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample34) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityInverseGamma(sigma, 2.0, 2.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var33 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$sigma = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample34)
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
			fixedProbFlag$sample34 = fixedFlag$sample34;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var33 = logProbability$sigma;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$sigma);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample34)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$sigma);
		}
	}

	// Calculate the probability of the samples represented by sample47 using sampled
	// values.
	private final void logProbabilityValue$sample47() {
		// Determine if we need to calculate the values for sample task 47 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample47) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < noObs; var46 += 1) {
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((beta[var46] - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5));
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample47[var46] = cv$distributionAccumulator;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < noProducts)) {
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Looking for a path between Sample 47 and consumer double[] 77.
					// 
					// Update the variable probability
					logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
					
					// Update the variable probability
					logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
				}
			}
			logProbability$var35 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$beta = (logProbability$beta + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample47)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample47 = ((fixedFlag$sample47 && fixedFlag$sample28) && fixedFlag$sample34);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int var46 = 0; var46 < noObs; var46 += 1) {
				double cv$sampleValue = logProbability$sample47[var46];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < noProducts)) {
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Looking for a path between Sample 47 and consumer double[] 77.
					// 
					// Update the variable probability
					logProbability$exped = (logProbability$exped + cv$sampleValue);
					
					// Update the variable probability
					logProbability$prob = (logProbability$prob + cv$sampleValue);
				}
			}
			logProbability$var35 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 21 drawn from Gaussian 9. Inference was performed using Metropolis-Hastings.
	private final void sample21(int var20) {
		// The original value of the sample
		double cv$originalValue = ut[var20];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		
		// Unrolled loop
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var8" with its value "10.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int i = 0; i < noObs; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample21categorical102$global[i] = false;
			for(int i = 0; i < noObs; i += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample21categorical102$global[i]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample21categorical102$global[i] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 103 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 103 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$2$prob" with its value "prob[i]".
					cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < noProducts))?Math.log(prob[i][choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int i = 0; i < noObs; i += 1) {
				// Substituted "j$var69" with its value "var20".
				if(!guard$sample21categorical102$global[i])
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 103 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 103 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$4$prob" with its value "prob[i]".
					cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < noProducts))?Math.log(prob[i][choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
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
		// Guards to ensure that ut is only updated when there is a valid path.
		ut[var20] = cv$proposedValue;
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 21 and consumer double[] 77.
		for(int i = 0; i < noObs; i += 1)
			// Substituted "j$var69" with its value "var20".
			exped[i][var20] = Math.exp((ut[var20] - (beta[i] * Prices[i][var20])));
		for(int i = 0; i < noObs; i += 1) {
			for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample21put101$global[i][j$var97] = false;
		}
		for(int i = 0; i < noObs; i += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var97" with its value "var20".
			guard$sample21put101$global[i][var20] = false;
		for(int i = 0; i < noObs; i += 1) {
			for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample21put101$global[i][j$var97]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample21put101$global[i][j$var97] = true;
					
					// Reduction of array exped
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$sum$15 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l's comment
						// Set the right hand term to a value from the array exped
						reduceVar$sum$15 = (reduceVar$sum$15 + exped[i][cv$reduction82Index]);
					prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$15);
				}
			}
		}
		for(int i = 0; i < noObs; i += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var97" with its value "var20".
			if(!guard$sample21put101$global[i][var20]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var97" with its value "var20".
				guard$sample21put101$global[i][var20] = true;
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$16 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$16 = (reduceVar$sum$16 + exped[i][cv$reduction82Index]);
				
				// Substituted "j$var97" with its value "var20".
				prob[i][var20] = (exped[i][var20] / reduceVar$sum$16);
			}
		}
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var8" with its value "10.0".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int i = 0; i < noObs; i += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample21categorical102$global[i] = false;
		for(int i = 0; i < noObs; i += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard$sample21categorical102$global[i]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample21categorical102$global[i] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 103 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 103 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$2$prob" with its value "prob[i]".
				cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < noProducts))?Math.log(prob[i][choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		for(int i = 0; i < noObs; i += 1) {
			// Substituted "j$var69" with its value "var20".
			if(!guard$sample21categorical102$global[i]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample21categorical102$global[i] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 103 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 103 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$4$prob" with its value "prob[i]".
				cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < noProducts))?Math.log(prob[i][choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Guards to ensure that ut is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			ut[var20] = cv$originalValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 21 and consumer double[] 77.
			for(int i = 0; i < noObs; i += 1)
				// Substituted "j$var69" with its value "var20".
				exped[i][var20] = Math.exp((ut[var20] - (beta[i] * Prices[i][var20])));
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample21put101$global[i][j$var97] = false;
			}
			for(int i = 0; i < noObs; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var97" with its value "var20".
				guard$sample21put101$global[i][var20] = false;
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample21put101$global[i][j$var97]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample21put101$global[i][j$var97] = true;
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$18 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l's comment
							// Set the right hand term to a value from the array exped
							reduceVar$sum$18 = (reduceVar$sum$18 + exped[i][cv$reduction82Index]);
						prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$18);
					}
				}
			}
			for(int i = 0; i < noObs; i += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var97" with its value "var20".
				if(!guard$sample21put101$global[i][var20]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var97" with its value "var20".
					guard$sample21put101$global[i][var20] = true;
					
					// Reduction of array exped
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$sum$19 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l's comment
						// Set the right hand term to a value from the array exped
						reduceVar$sum$19 = (reduceVar$sum$19 + exped[i][cv$reduction82Index]);
					
					// Substituted "j$var97" with its value "var20".
					prob[i][var20] = (exped[i][var20] / reduceVar$sum$19);
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from Gaussian 27. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample28() {
		// State to record the weighting of each sample that is consumed. This is the:
		// sum of the sample denominator*(the sample value - the sample nominator).
		double cv$sum = 0.0;
		
		// State for storing the sum of the squares of the sample denominators.
		double cv$denominatorSquareSum = 0.0;
		
		// Flag to record if we have a value for Sigma.
		boolean cv$sigmaNotFound = true;
		
		// State for the value of sigma once we find it.
		double cv$sigmaValue = 1.0;
		
		// Processing random variable 35.
		// 
		// Processing sample task 47 of consumer random variable null.
		for(int var46 = 0; var46 < noObs; var46 += 1) {
			// Record the value of a sample generated by a consuming sample 47 of random variable
			// var35.
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
			cv$sum = (cv$sum + beta[var46]);
			
			// If we have not got the value of sigma yet record it and set a flag so it is not
			// recorded again.
			if(cv$sigmaNotFound) {
				cv$sigmaValue = sigma;
				cv$sigmaNotFound = false;
			}
		}
		
		// Write out the new value of the sample.
		b = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 34 drawn from InverseGamma 33. Inference was performed using a Inverse
	// Gamma to Gaussian conjugate prior.
	private final void sample34() {
		// Variable to track the sum of the difference between the samples and the random
		// variables mean squared.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		
		// Processing random variable 35.
		// 
		// Processing sample task 47 of consumer random variable null.
		for(int var46 = 0; var46 < noObs; var46 += 1) {
			// Consume sample task 47 from random variable var35.
			// 
			// The difference between the mean parameter and the value sampled from the Gaussian.
			// 
			// The mean parameter for Gaussian var35.
			double cv$var35$diff = (b - beta[var46]);
			
			// Include this sample by adding the square of the difference to the sum.
			cv$sum = (cv$sum + (cv$var35$diff * cv$var35$diff));
			
			// Increment the number of samples in the calculation.
			cv$count = (cv$count + 1);
		}
		
		// Write out the new value of the sample.
		sigma = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 2.0, 2.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 47 drawn from Gaussian 35. Inference was performed using Metropolis-Hastings.
	private final void sample47(int var46, int threadID$cv$var46, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = beta[var46];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the new sample value.
		double cv$proposedProbability;
		{
			// Unrolled loop
			{
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				// 
				// Substituted "cv$temp$1$sigma" with its value "sigma".
				// 
				// Set the current value to the current state of the tree.
				double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5));
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < noProducts)) {
					// Processing random variable 101.
					// 
					// Looking for a path between Sample 47 and consumer Categorical 101.
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					boolean[] guard$sample47categorical102 = guard$sample47categorical102$global[threadID$cv$var46];
					
					// Set the flags to false
					// 
					// Substituted "i" with its value "var46".
					guard$sample47categorical102[var46] = false;
					if(!guard$sample47categorical102[var46]) {
						// The body will execute, so should not be executed again
						// 
						// Substituted "i" with its value "var46".
						guard$sample47categorical102[var46] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 103 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 103 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i" with its value "var46".
						// 
						// cv$temp$2$prob's comment
						// Substituted "i" with its value "var46".
						cv$accumulatedProbabilities = ((((0.0 <= choices[var46]) && (choices[var46] < noProducts))?Math.log(prob[var46][choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample47categorical102[var46]) {
						// The body will execute, so should not be executed again
						// 
						// Substituted "i" with its value "var46".
						guard$sample47categorical102[var46] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 103 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 103 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i" with its value "var46".
						// 
						// cv$temp$4$prob's comment
						// Substituted "i" with its value "var46".
						cv$accumulatedProbabilities = ((((0.0 <= choices[var46]) && (choices[var46] < noProducts))?Math.log(prob[var46][choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
				
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
			// Guards to ensure that beta is only updated when there is a valid path.
			beta[var46] = cv$proposedValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 47 and consumer double[] 77.
			for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
				// Substituted "i" with its value "var46".
				exped[var46][j$var69] = Math.exp((ut[j$var69] - (beta[var46] * Prices[var46][j$var69])));
			
			// Guards to ensure that prob is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 47 and consumer double[] 100.
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			boolean[][] guard$sample47put101 = guard$sample47put101$global[threadID$cv$var46];
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < noProducts)) {
				for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
					// Set the flags to false
					// 
					// Substituted "i" with its value "var46".
					guard$sample47put101[var46][j$var97] = false;
			}
			for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
				// Set the flags to false
				// 
				// Substituted "i" with its value "var46".
				guard$sample47put101[var46][j$var69] = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < noProducts)) {
				for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
					// Substituted "i" with its value "var46".
					if(!guard$sample47put101[var46][j$var97]) {
						// The body will execute, so should not be executed again
						// 
						// Substituted "i" with its value "var46".
						guard$sample47put101[var46][j$var97] = true;
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$20 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// k's comment
							// Set the left hand term of the reduction function to the return variable value.
							// 
							// l's comment
							// Set the right hand term to a value from the array exped
							// 
							// Substituted "i" with its value "var46".
							reduceVar$sum$20 = (reduceVar$sum$20 + exped[var46][cv$reduction82Index]);
						
						// Substituted "i" with its value "var46".
						prob[var46][j$var97] = (exped[var46][j$var97] / reduceVar$sum$20);
					}
				}
			}
			for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
				if(!guard$sample47put101[var46][j$var69]) {
					// The body will execute, so should not be executed again
					// 
					// Substituted "i" with its value "var46".
					guard$sample47put101[var46][j$var69] = true;
					
					// Reduction of array exped
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$sum$21 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// k's comment
						// Set the left hand term of the reduction function to the return variable value.
						// 
						// l's comment
						// Set the right hand term to a value from the array exped
						// 
						// Substituted "i" with its value "var46".
						reduceVar$sum$21 = (reduceVar$sum$21 + exped[var46][cv$reduction82Index]);
					
					// Substituted "i" with its value "var46".
					prob[var46][j$var69] = (exped[var46][j$var69] / reduceVar$sum$21);
				}
			}
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$sigma" with its value "sigma".
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5));
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < noProducts)) {
				// Processing random variable 101.
				// 
				// Looking for a path between Sample 47 and consumer Categorical 101.
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[] guard$sample47categorical102 = guard$sample47categorical102$global[threadID$cv$var46];
				
				// Set the flags to false
				// 
				// Substituted "i" with its value "var46".
				guard$sample47categorical102[var46] = false;
				if(!guard$sample47categorical102[var46]) {
					// The body will execute, so should not be executed again
					// 
					// Substituted "i" with its value "var46".
					guard$sample47categorical102[var46] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 103 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 103 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i" with its value "var46".
					// 
					// cv$temp$2$prob's comment
					// Substituted "i" with its value "var46".
					cv$accumulatedProbabilities = ((((0.0 <= choices[var46]) && (choices[var46] < noProducts))?Math.log(prob[var46][choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				if(!guard$sample47categorical102[var46]) {
					// The body will execute, so should not be executed again
					// 
					// Substituted "i" with its value "var46".
					guard$sample47categorical102[var46] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 103 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 103 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i" with its value "var46".
					// 
					// cv$temp$4$prob's comment
					// Substituted "i" with its value "var46".
					cv$accumulatedProbabilities = ((((0.0 <= choices[var46]) && (choices[var46] < noProducts))?Math.log(prob[var46][choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$proposedProbability = cv$accumulatedProbabilities;
		}
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$proposedProbability - cv$originalProbability)))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Guards to ensure that beta is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			beta[var46] = cv$originalValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 47 and consumer double[] 77.
			for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
				// Substituted "i" with its value "var46".
				exped[var46][j$var69] = Math.exp((ut[j$var69] - (beta[var46] * Prices[var46][j$var69])));
			
			// Guards to ensure that prob is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 47 and consumer double[] 100.
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			boolean[][] guard$sample47put101 = guard$sample47put101$global[threadID$cv$var46];
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < noProducts)) {
				for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
					// Set the flags to false
					// 
					// Substituted "i" with its value "var46".
					guard$sample47put101[var46][j$var97] = false;
			}
			for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
				// Set the flags to false
				// 
				// Substituted "i" with its value "var46".
				guard$sample47put101[var46][j$var69] = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < noProducts)) {
				for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
					// Substituted "i" with its value "var46".
					if(!guard$sample47put101[var46][j$var97]) {
						// The body will execute, so should not be executed again
						// 
						// Substituted "i" with its value "var46".
						guard$sample47put101[var46][j$var97] = true;
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$23 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// k's comment
							// Set the left hand term of the reduction function to the return variable value.
							// 
							// l's comment
							// Set the right hand term to a value from the array exped
							// 
							// Substituted "i" with its value "var46".
							reduceVar$sum$23 = (reduceVar$sum$23 + exped[var46][cv$reduction82Index]);
						
						// Substituted "i" with its value "var46".
						prob[var46][j$var97] = (exped[var46][j$var97] / reduceVar$sum$23);
					}
				}
			}
			for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
				if(!guard$sample47put101[var46][j$var69]) {
					// The body will execute, so should not be executed again
					// 
					// Substituted "i" with its value "var46".
					guard$sample47put101[var46][j$var69] = true;
					
					// Reduction of array exped
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$sum$24 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// k's comment
						// Set the left hand term of the reduction function to the return variable value.
						// 
						// l's comment
						// Set the right hand term to a value from the array exped
						// 
						// Substituted "i" with its value "var46".
						reduceVar$sum$24 = (reduceVar$sum$24 + exped[var46][cv$reduction82Index]);
					
					// Substituted "i" with its value "var46".
					prob[var46][j$var69] = (exped[var46][j$var69] / reduceVar$sum$24);
				}
			}
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for guard$sample21put101$global
		{
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var97 = 0;
			if((0 < noObs))
				// Calculate the largest index of j that is possible and allocate an array to hold
				// the guard for each of these.
				cv$max_j$var97 = Math.max(0, noProducts);
			
			// Allocation of guard$sample21put101$global for single threaded execution
			// 
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			guard$sample21put101$global = new boolean[Math.max(0, noObs)][cv$max_j$var97];
		}
		
		// Constructor for guard$sample21categorical102$global
		// 
		// Allocation of guard$sample21categorical102$global for single threaded execution
		// 
		// Calculate the largest index of i that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample21categorical102$global = new boolean[Math.max(0, noObs)];
		
		// Constructor for guard$sample47put101$global
		{
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var97 = 0;
			if((0 < noObs))
				// Calculate the largest index of j that is possible and allocate an array to hold
				// the guard for each of these.
				cv$max_j$var97 = Math.max(0, noProducts);
			
			// Variable declaration of cv$max_i moved.
			// Declaration comment was:
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			// 
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_i = Math.max(0, noObs);
			
			// Allocation of guard$sample47put101$global for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			guard$sample47put101$global = new boolean[cv$threadCount][][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample47put101$global[cv$index] = new boolean[cv$max_i][cv$max_j$var97];
		}
		
		// Variable declaration of cv$max_i moved.
		// Declaration comment was:
		// Constructor for guard$sample47categorical102$global
		// 
		// Calculate the largest index of i that is possible and allocate an array to hold
		// the guard for each of these.
		// 
		// Calculate the largest index of i that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_i = Math.max(0, noObs);
		
		// Allocation of guard$sample47categorical102$global for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		guard$sample47categorical102$global = new boolean[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			guard$sample47categorical102$global[cv$index] = new boolean[cv$max_i];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If ut has not been set already allocate space.
		if(!fixedFlag$sample21)
			// Constructor for ut
			ut = new double[noProducts];
		
		// If beta has not been set already allocate space.
		if(!fixedFlag$sample47)
			// Constructor for beta
			beta = new double[noObs];
		
		// Constructor for choices
		choices = new int[noObs];
		
		// Constructor for exped
		exped = new double[noObs][];
		for(int i = 0; i < noObs; i += 1)
			exped[i] = new double[noProducts];
		
		// Constructor for prob
		prob = new double[noObs][];
		for(int i = 0; i < noObs; i += 1)
			prob[i] = new double[noProducts];
		
		// Constructor for logProbability$sample21
		logProbability$sample21 = new double[noProducts];
		
		// Constructor for logProbability$sample47
		logProbability$sample47 = new double[noObs];
		
		// Constructor for logProbability$var101
		logProbability$var101 = new double[noObs];
		
		// Constructor for logProbability$sample103
		logProbability$sample103 = new double[noObs];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample21)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1)
							ut[var20] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample28)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample47)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((!fixedFlag$sample21 || !fixedFlag$sample47)) {
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
											exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
								}
							);
							
							// Reduction of array exped
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$sum$25 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array exped
								reduceVar$sum$25 = (reduceVar$sum$25 + exped[i][cv$reduction82Index]);
							
							// Alternative name for reduceVar$sum$25 to make it effectively final.
							double reduceVar$sum$25$1 = reduceVar$sum$25;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
											prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$25$1);
								}
							);
						}
						choices[i] = DistributionSampling.sampleCategorical(RNG$1, prob[i], noProducts);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample21)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1)
							ut[var20] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample28)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample47)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((!fixedFlag$sample21 || !fixedFlag$sample47))
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
							int i = index$i;
							int threadID$i = threadID$index$i;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
											exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
								}
							);
							
							// Reduction of array exped
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$sum$27 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array exped
								reduceVar$sum$27 = (reduceVar$sum$27 + exped[i][cv$reduction82Index]);
							
							// Alternative name for reduceVar$sum$27 to make it effectively final.
							double reduceVar$sum$27$1 = reduceVar$sum$27;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
											prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$27$1);
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
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample21)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1)
							ut[var20] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample28)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample47)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((!fixedFlag$sample21 || !fixedFlag$sample47))
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
							int i = index$i;
							int threadID$i = threadID$index$i;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
											exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
								}
							);
							
							// Reduction of array exped
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$sum$26 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array exped
								reduceVar$sum$26 = (reduceVar$sum$26 + exped[i][cv$reduction82Index]);
							
							// Alternative name for reduceVar$sum$26 to make it effectively final.
							double reduceVar$sum$26$1 = reduceVar$sum$26;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
											prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$26$1);
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
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample21) {
				for(int var20 = 0; var20 < noProducts; var20 += 1)
					sample21(var20);
			}
			if(!fixedFlag$sample28)
				sample28();
			if(!fixedFlag$sample34)
				sample34();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample47)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noObs, 1,
					(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
								sample47(var46, threadID$var46, RNG$1);
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample47)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noObs, 1,
					(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
								sample47(var46, threadID$var46, RNG$1);
					}
				);

			if(!fixedFlag$sample34)
				sample34();
			if(!fixedFlag$sample28)
				sample28();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample21) {
				for(int var20 = (noProducts - 1); var20 >= 0; var20 -= 1)
					sample21(var20);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {}

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
		logProbability$var9 = 0.0;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$prob = 0.0;
		if(!fixedProbFlag$sample21) {
			for(int var20 = 0; var20 < noProducts; var20 += 1)
				logProbability$sample21[var20] = 0.0;
		}
		logProbability$var27 = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$b = 0.0;
		logProbability$var33 = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$sigma = 0.0;
		logProbability$var35 = 0.0;
		logProbability$beta = 0.0;
		if(!fixedProbFlag$sample47) {
			for(int var46 = 0; var46 < noObs; var46 += 1)
				logProbability$sample47[var46] = 0.0;
		}
		for(int i = 0; i < noObs; i += 1)
			logProbability$var101[i] = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample103) {
			for(int i = 0; i < noObs; i += 1)
				logProbability$sample103[i] = 0.0;
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
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
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
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
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
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
		logProbabilityValue$sample103();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample21)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1)
							ut[var20] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample28)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample47)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((!fixedFlag$sample21 || !fixedFlag$sample47))
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
							int i = index$i;
							int threadID$i = threadID$index$i;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
											exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
								}
							);
							
							// Reduction of array exped
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$sum$28 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array exped
								reduceVar$sum$28 = (reduceVar$sum$28 + exped[i][cv$reduction82Index]);
							
							// Alternative name for reduceVar$sum$28 to make it effectively final.
							double reduceVar$sum$28$1 = reduceVar$sum$28;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
											prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$28$1);
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
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = choices.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			choices[cv$index1] = ObsChoices[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((fixedFlag$sample21 && fixedFlag$sample47))
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
							int i = index$i;
							int threadID$i = threadID$index$i;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
											exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
								}
							);
							
							// Reduction of array exped
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$sum$29 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array exped
								reduceVar$sum$29 = (reduceVar$sum$29 + exped[i][cv$reduction82Index]);
							
							// Alternative name for reduceVar$sum$29 to make it effectively final.
							double reduceVar$sum$29$1 = reduceVar$sum$29;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
											prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$29$1);
								}
							);
						}
				}
			);

	}

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
		     + "\n"
		     + "model DiscreteChoiceRandCoeff(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = gaussian(0, 10).sample(noProducts);\n"
		     + "    //can we set the first element to 0? like ut[0] <~ 0\n"
		     + "\n"
		     + "    //priors for distribution of beta\n"
		     + "    double b = gaussian(0,10).sample();\n"
		     + "    double sigma =  inverseGamma(2,2).sample();\n"
		     + "\n"
		     + "    double[] beta = gaussian(b, sigma).sample(noObs);\n"
		     + "\n"
		     + "    int[] choices = new int[noObs];\n"
		     + "\n"
		     + "    for (int i:[0..noObs)){\n"
		     + "        // calculate choice probabilities for consumer i\n"
		     + "\n"
		     + "        double[] exped = new double[noProducts];\n"
		     + "        for(int j : [0..noProducts)) {\n"
		     + "            exped[j] = exp(ut[j]- beta[i]*Prices[i][j]);\n"
		     + "            }\n"
		     + "        double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "        public double[] prob = new double[noProducts];\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            prob[j] = exped[j] / sum;\n"
		     + "        }\n"
		     + "        // emit choices of consumer i\n"
		     + "        choices[i] = categorical(prob).sample();\n"
		     + "                                }\n"
		     + "\n"
		     + "    // assert that generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}