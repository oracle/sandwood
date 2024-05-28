package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoiceRandCoeff$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DiscreteChoiceRandCoeff$CoreInterface {
	
	// Declare the variables for the model.
	private int[] ObsChoices;
	private int[][] Prices;
	private double b;
	private double[] beta;
	private int[] choices;
	private double[][] exped;
	private boolean fixedFlag$sample111 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample42 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedProbFlag$sample111 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample42 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean[] guard$sample29categorical110$global;
	private boolean[][] guard$sample29put109$global;
	private boolean[] guard$sample55categorical110$global;
	private boolean[][] guard$sample55put109$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$beta;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample111;
	private double[] logProbability$sample29;
	private double[] logProbability$sample55;
	private double logProbability$sigma;
	private double logProbability$ut;
	private double[] logProbability$var107;
	private double logProbability$var15;
	private double logProbability$var33;
	private double logProbability$var39;
	private double logProbability$var41;
	private int noObs;
	private int noProducts;
	private double[][] prob;
	private boolean setFlag$beta = false;
	private boolean setFlag$choices = false;
	private boolean setFlag$ut = false;
	private double sigma;
	private boolean system$gibbsForward = true;
	private double[] ut;

	public DiscreteChoiceRandCoeff$SingleThreadCPU(ExecutionTarget target) {
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
		// Set ObsChoices with flag to mark that it has been set so another array doesn't
		// need to be constructed
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
		// Set Prices with flag to mark that it has been set so another array doesn't need
		// to be constructed
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
		
		// Unset the fixed probability flag for sample 36 as it depends on b.
		fixedProbFlag$sample36 = false;
		
		// Unset the fixed probability flag for sample 55 as it depends on b.
		fixedProbFlag$sample55 = false;
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
		// Set beta with flag to mark that it has been set so another array doesn't need to
		// be constructed
		beta = cv$value;
		setFlag$beta = true;
		
		// Unset the fixed probability flag for sample 55 as it depends on beta.
		fixedProbFlag$sample55 = false;
		
		// Unset the fixed probability flag for sample 111 as it depends on beta.
		fixedProbFlag$sample111 = false;
	}

	// Getter for choices.
	@Override
	public final int[] get$choices() {
		return choices;
	}

	// Setter for choices.
	@Override
	public final void set$choices(int[] cv$value) {
		// Set flags for all the side effects of choices including if probabilities need to
		// be updated.
		// Set choices with flag to mark that it has been set so another array doesn't need
		// to be constructed
		choices = cv$value;
		setFlag$choices = true;
		
		// Unset the fixed probability flag for sample 111 as it depends on choices.
		fixedProbFlag$sample111 = false;
	}

	// Getter for fixedFlag$sample111.
	@Override
	public final boolean get$fixedFlag$sample111() {
		return fixedFlag$sample111;
	}

	// Setter for fixedFlag$sample111.
	@Override
	public final void set$fixedFlag$sample111(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample111 including if probabilities
		// need to be updated.
		fixedFlag$sample111 = cv$value;
		
		// Should the probability of sample 111 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample111" with its value "cv$value".
		fixedProbFlag$sample111 = (cv$value && fixedProbFlag$sample111);
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
		// 
		// Substituted "fixedFlag$sample29" with its value "cv$value".
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
		
		// Should the probability of sample 111 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample29" with its value "cv$value".
		fixedProbFlag$sample111 = (cv$value && fixedProbFlag$sample111);
	}

	// Getter for fixedFlag$sample36.
	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	// Setter for fixedFlag$sample36.
	@Override
	public final void set$fixedFlag$sample36(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample36 including if probabilities
		// need to be updated.
		fixedFlag$sample36 = cv$value;
		
		// Should the probability of sample 36 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample36" with its value "cv$value".
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample36" with its value "cv$value".
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	// Getter for fixedFlag$sample42.
	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	// Setter for fixedFlag$sample42.
	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample42 including if probabilities
		// need to be updated.
		fixedFlag$sample42 = cv$value;
		
		// Should the probability of sample 42 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample42" with its value "cv$value".
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample42" with its value "cv$value".
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
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
		// 
		// Substituted "fixedFlag$sample55" with its value "cv$value".
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
		
		// Should the probability of sample 111 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample55" with its value "cv$value".
		fixedProbFlag$sample111 = (cv$value && fixedProbFlag$sample111);
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
		
		// Unset the fixed probability flag for sample 42 as it depends on sigma.
		fixedProbFlag$sample42 = false;
		
		// Unset the fixed probability flag for sample 55 as it depends on sigma.
		fixedProbFlag$sample55 = false;
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
		// Set ut with flag to mark that it has been set so another array doesn't need to
		// be constructed
		ut = cv$value;
		setFlag$ut = true;
		
		// Unset the fixed probability flag for sample 29 as it depends on ut.
		fixedProbFlag$sample29 = false;
		
		// Unset the fixed probability flag for sample 111 as it depends on ut.
		fixedProbFlag$sample111 = false;
	}

	// Calculate the probability of the samples represented by sample111 using sampled
	// values.
	private final void logProbabilityValue$sample111() {
		// Determine if we need to calculate the values for sample task 111 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample111) {
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
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < prob[i].length))?Math.log(prob[i][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
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
				logProbability$var107[i] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample111[i] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$choices = (logProbability$choices + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample111 = ((fixedFlag$sample111 && fixedFlag$sample29) && fixedFlag$sample55);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample111[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var107[i] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$choices = (logProbability$choices + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
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
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var26 = 0; var26 < noProducts; var26 += 1) {
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[var26] / 3.1622776601683795)) - 1.151292546497023);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample29[var26] = cv$distributionAccumulator;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < noObs)) {
					// Update the variable probability
					logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
					
					// Update the variable probability
					logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
				}
			}
			logProbability$var15 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample29)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample29 = fixedFlag$sample29;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int var26 = 0; var26 < noProducts; var26 += 1) {
				double cv$sampleValue = logProbability$sample29[var26];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < noObs)) {
					// Update the variable probability
					logProbability$exped = (logProbability$exped + cv$sampleValue);
					
					// Update the variable probability
					logProbability$prob = (logProbability$prob + cv$sampleValue);
				}
			}
			logProbability$var15 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample36 using sampled
	// values.
	private final void logProbabilityValue$sample36() {
		// Determine if we need to calculate the values for sample task 36 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample36) {
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
			logProbability$var33 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample36)
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
			fixedProbFlag$sample36 = fixedFlag$sample36;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var33 = logProbability$b;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$b);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample36)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$b);
		}
	}

	// Calculate the probability of the samples represented by sample42 using sampled
	// values.
	private final void logProbabilityValue$sample42() {
		// Determine if we need to calculate the values for sample task 42 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample42) {
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
			logProbability$var39 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample42)
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
			fixedProbFlag$sample42 = fixedFlag$sample42;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var39 = logProbability$sigma;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$sigma);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample42)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$sigma);
		}
	}

	// Calculate the probability of the samples represented by sample55 using sampled
	// values.
	private final void logProbabilityValue$sample55() {
		// Determine if we need to calculate the values for sample task 55 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample55) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var52 = 0; var52 < noObs; var52 += 1) {
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((beta[var52] - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5));
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample55[var52] = cv$distributionAccumulator;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < noProducts)) {
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Looking for a path between Sample 55 and consumer double[] 83.
					// 
					// Update the variable probability
					logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
					
					// Update the variable probability
					logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
				}
			}
			logProbability$var41 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample55)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample55 = ((fixedFlag$sample55 && fixedFlag$sample36) && fixedFlag$sample42);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int var52 = 0; var52 < noObs; var52 += 1) {
				double cv$sampleValue = logProbability$sample55[var52];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < noProducts)) {
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Looking for a path between Sample 55 and consumer double[] 83.
					// 
					// Update the variable probability
					logProbability$exped = (logProbability$exped + cv$sampleValue);
					
					// Update the variable probability
					logProbability$prob = (logProbability$prob + cv$sampleValue);
				}
			}
			logProbability$var41 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 29 drawn from Gaussian 15. Inference was performed using Metropolis-Hastings.
	private final void sample29(int var26) {
		// The original value of the sample
		double cv$originalValue = ut[var26];
		
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
			// Substituted "cv$temp$1$var14" with its value "10.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int i = 0; i < noObs; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample29categorical110$global[i] = false;
			for(int i = 0; i < noObs; i += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample29categorical110$global[i]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample29categorical110$global[i] = true;
					
					// Variable declaration of cv$temp$2$prob moved.
					double[] cv$temp$2$prob = prob[i];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 111 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 111 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int i = 0; i < noObs; i += 1) {
				// Substituted "j$var75" with its value "var26".
				if(!guard$sample29categorical110$global[i]) {
					// Variable declaration of cv$temp$3$prob moved.
					double[] cv$temp$3$prob = prob[i];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 111 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 111 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
		ut[var26] = cv$proposedValue;
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 29 and consumer double[] 83.
		for(int i = 0; i < noObs; i += 1)
			// Substituted "j$var75" with its value "var26".
			exped[i][var26] = Math.exp((ut[var26] - (beta[i] * Prices[i][var26])));
		for(int i = 0; i < noObs; i += 1) {
			for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample29put109$global[i][j$var103] = false;
		}
		for(int i = 0; i < noObs; i += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var103" with its value "var26".
			guard$sample29put109$global[i][var26] = false;
		for(int i = 0; i < noObs; i += 1) {
			for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample29put109$global[i][j$var103]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample29put109$global[i][j$var103] = true;
					
					// Reduction of array exped
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$sum$0 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l's comment
						// Set the right hand term to a value from the array exped
						reduceVar$sum$0 = (reduceVar$sum$0 + exped[i][cv$reduction90Index]);
					prob[i][j$var103] = (exped[i][j$var103] / reduceVar$sum$0);
				}
			}
		}
		for(int i = 0; i < noObs; i += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var103" with its value "var26".
			if(!guard$sample29put109$global[i][var26]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var103" with its value "var26".
				guard$sample29put109$global[i][var26] = true;
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$1 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$1 = (reduceVar$sum$1 + exped[i][cv$reduction90Index]);
				
				// Substituted "j$var103" with its value "var26".
				prob[i][var26] = (exped[i][var26] / reduceVar$sum$1);
			}
		}
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var14" with its value "10.0".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int i = 0; i < noObs; i += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample29categorical110$global[i] = false;
		for(int i = 0; i < noObs; i += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard$sample29categorical110$global[i]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample29categorical110$global[i] = true;
				
				// Variable declaration of cv$temp$2$prob moved.
				double[] cv$temp$2$prob = prob[i];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 111 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 111 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		for(int i = 0; i < noObs; i += 1) {
			// Substituted "j$var75" with its value "var26".
			if(!guard$sample29categorical110$global[i]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample29categorical110$global[i] = true;
				
				// Variable declaration of cv$temp$3$prob moved.
				double[] cv$temp$3$prob = prob[i];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 111 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 111 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			ut[var26] = cv$originalValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 29 and consumer double[] 83.
			for(int i = 0; i < noObs; i += 1)
				// Substituted "j$var75" with its value "var26".
				exped[i][var26] = Math.exp((ut[var26] - (beta[i] * Prices[i][var26])));
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample29put109$global[i][j$var103] = false;
			}
			for(int i = 0; i < noObs; i += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var103" with its value "var26".
				guard$sample29put109$global[i][var26] = false;
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample29put109$global[i][j$var103]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample29put109$global[i][j$var103] = true;
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$3 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l's comment
							// Set the right hand term to a value from the array exped
							reduceVar$sum$3 = (reduceVar$sum$3 + exped[i][cv$reduction90Index]);
						prob[i][j$var103] = (exped[i][j$var103] / reduceVar$sum$3);
					}
				}
			}
			for(int i = 0; i < noObs; i += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var103" with its value "var26".
				if(!guard$sample29put109$global[i][var26]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var103" with its value "var26".
					guard$sample29put109$global[i][var26] = true;
					
					// Reduction of array exped
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$sum$4 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l's comment
						// Set the right hand term to a value from the array exped
						reduceVar$sum$4 = (reduceVar$sum$4 + exped[i][cv$reduction90Index]);
					
					// Substituted "j$var103" with its value "var26".
					prob[i][var26] = (exped[i][var26] / reduceVar$sum$4);
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 36 drawn from Gaussian 33. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample36() {
		// State to record the weighting of each sample that is consumed. This is the:
		// sum of the sample denominator*(the sample value - the sample nominator).
		double cv$sum = 0.0;
		
		// State for storing the sum of the squares of the sample denominators.
		double cv$denominatorSquareSum = 0.0;
		
		// Flag to record if we have a value for Sigma.
		boolean cv$sigmaNotFound = true;
		
		// State for the value of sigma once we find it.
		double cv$sigmaValue = 1.0;
		
		// Processing random variable 41.
		// 
		// Processing sample task 55 of consumer random variable null.
		for(int var52 = 0; var52 < noObs; var52 += 1) {
			// Record the value of a sample generated by a consuming sample 55 of random variable
			// var41.
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
			cv$sum = (cv$sum + beta[var52]);
			
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
	// by sample task 42 drawn from InverseGamma 39. Inference was performed using a Inverse
	// Gamma to Gaussian conjugate prior.
	private final void sample42() {
		// Variable to track the sum of the difference between the samples and the random
		// variables mean squared.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		
		// Processing random variable 41.
		// 
		// Processing sample task 55 of consumer random variable null.
		for(int var52 = 0; var52 < noObs; var52 += 1) {
			// Consume sample task 55 from random variable var41.
			// 
			// The difference between the mean parameter and the value sampled from the Gaussian.
			// 
			// The mean parameter for Gaussian var41.
			double cv$var41$diff = (b - beta[var52]);
			
			// Include this sample by adding the square of the difference to the sum.
			cv$sum = (cv$sum + (cv$var41$diff * cv$var41$diff));
			
			// Increment the number of samples in the calculation.
			cv$count = (cv$count + 1);
		}
		
		// Write out the new value of the sample.
		sigma = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 2.0, 2.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 55 drawn from Gaussian 41. Inference was performed using Metropolis-Hastings.
	private final void sample55(int var52) {
		// The original value of the sample
		double cv$originalValue = beta[var52];
		
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
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample55categorical110$global[var52] = false;
				if(!guard$sample55categorical110$global[var52]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample55categorical110$global[var52] = true;
					
					// Variable declaration of cv$temp$2$prob moved.
					// 
					// Substituted "i" with its value "var52".
					double[] cv$temp$2$prob = prob[var52];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 111 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 111 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i" with its value "var52".
					cv$accumulatedProbabilities = ((((0.0 <= choices[var52]) && (choices[var52] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var52]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				if(!guard$sample55categorical110$global[var52]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample55categorical110$global[var52] = true;
					
					// Variable declaration of cv$temp$3$prob moved.
					// 
					// Substituted "i" with its value "var52".
					double[] cv$temp$3$prob = prob[var52];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 111 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 111 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i" with its value "var52".
					cv$accumulatedProbabilities = ((((0.0 <= choices[var52]) && (choices[var52] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var52]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
		beta[var52] = cv$proposedValue;
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 55 and consumer double[] 83.
		for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1)
			// Substituted "i" with its value "var52".
			exped[var52][j$var75] = Math.exp((ut[j$var75] - (beta[var52] * Prices[var52][j$var75])));
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < noProducts)) {
			for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample55put109$global[var52][j$var103] = false;
		}
		for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample55put109$global[var52][j$var75] = false;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < noProducts)) {
			for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample55put109$global[var52][j$var103]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample55put109$global[var52][j$var103] = true;
					
					// Reduction of array exped
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$sum$5 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
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
						// Substituted "i" with its value "var52".
						reduceVar$sum$5 = (reduceVar$sum$5 + exped[var52][cv$reduction90Index]);
					
					// Substituted "i" with its value "var52".
					prob[var52][j$var103] = (exped[var52][j$var103] / reduceVar$sum$5);
				}
			}
		}
		for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1) {
			if(!guard$sample55put109$global[var52][j$var75]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample55put109$global[var52][j$var75] = true;
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$6 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
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
					// Substituted "i" with its value "var52".
					reduceVar$sum$6 = (reduceVar$sum$6 + exped[var52][cv$reduction90Index]);
				
				// Substituted "i" with its value "var52".
				prob[var52][j$var75] = (exped[var52][j$var75] / reduceVar$sum$6);
			}
		}
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$sigma" with its value "sigma".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5));
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < noProducts)) {
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample55categorical110$global[var52] = false;
			if(!guard$sample55categorical110$global[var52]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample55categorical110$global[var52] = true;
				
				// Variable declaration of cv$temp$2$prob moved.
				// 
				// Substituted "i" with its value "var52".
				double[] cv$temp$2$prob = prob[var52];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 111 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 111 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i" with its value "var52".
				cv$accumulatedProbabilities = ((((0.0 <= choices[var52]) && (choices[var52] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var52]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if(!guard$sample55categorical110$global[var52]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample55categorical110$global[var52] = true;
				
				// Variable declaration of cv$temp$3$prob moved.
				// 
				// Substituted "i" with its value "var52".
				double[] cv$temp$3$prob = prob[var52];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 111 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 111 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i" with its value "var52".
				cv$accumulatedProbabilities = ((((0.0 <= choices[var52]) && (choices[var52] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var52]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			beta[var52] = cv$originalValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 55 and consumer double[] 83.
			for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1)
				// Substituted "i" with its value "var52".
				exped[var52][j$var75] = Math.exp((ut[j$var75] - (beta[var52] * Prices[var52][j$var75])));
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < noProducts)) {
				for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample55put109$global[var52][j$var103] = false;
			}
			for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample55put109$global[var52][j$var75] = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < noProducts)) {
				for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample55put109$global[var52][j$var103]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample55put109$global[var52][j$var103] = true;
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$8 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
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
							// Substituted "i" with its value "var52".
							reduceVar$sum$8 = (reduceVar$sum$8 + exped[var52][cv$reduction90Index]);
						
						// Substituted "i" with its value "var52".
						prob[var52][j$var103] = (exped[var52][j$var103] / reduceVar$sum$8);
					}
				}
			}
			for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1) {
				if(!guard$sample55put109$global[var52][j$var75]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample55put109$global[var52][j$var75] = true;
					
					// Reduction of array exped
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$sum$9 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
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
						// Substituted "i" with its value "var52".
						reduceVar$sum$9 = (reduceVar$sum$9 + exped[var52][cv$reduction90Index]);
					
					// Substituted "i" with its value "var52".
					prob[var52][j$var75] = (exped[var52][j$var75] / reduceVar$sum$9);
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
		// Constructor for guard$sample29put109$global
		{
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var103 = 0;
			if((0 < noObs))
				// Calculate the largest index of j that is possible and allocate an array to hold
				// the guard for each of these.
				cv$max_j$var103 = Math.max(0, noProducts);
			
			// Allocation of guard$sample29put109$global for single threaded execution
			// 
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			guard$sample29put109$global = new boolean[Math.max(0, noObs)][cv$max_j$var103];
		}
		
		// Constructor for guard$sample29categorical110$global
		// 
		// Allocation of guard$sample29categorical110$global for single threaded execution
		// 
		// Calculate the largest index of i that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample29categorical110$global = new boolean[Math.max(0, noObs)];
		
		// Constructor for guard$sample55put109$global
		// 
		// Calculate the largest index of j that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_j$var103 = 0;
		if((0 < noObs))
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			cv$max_j$var103 = Math.max(0, noProducts);
		
		// Allocation of guard$sample55put109$global for single threaded execution
		// 
		// Calculate the largest index of i that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample55put109$global = new boolean[Math.max(0, noObs)][cv$max_j$var103];
		
		// Allocation of guard$sample55categorical110$global for single threaded execution
		// 
		// Calculate the largest index of i that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample55categorical110$global = new boolean[Math.max(0, noObs)];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If ut has not been set already allocate space.
		if(!setFlag$ut)
			// Constructor for ut
			ut = new double[noProducts];
		
		// If beta has not been set already allocate space.
		if(!setFlag$beta)
			// Constructor for beta
			beta = new double[noObs];
		
		// If choices has not been set already allocate space.
		if(!setFlag$choices)
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
		
		// Constructor for logProbability$sample29
		logProbability$sample29 = new double[noProducts];
		
		// Constructor for logProbability$sample55
		logProbability$sample55 = new double[noObs];
		
		// Constructor for logProbability$var107
		logProbability$var107 = new double[noObs];
		
		// Constructor for logProbability$sample111
		logProbability$sample111 = new double[noObs];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29) {
			for(int var26 = 0; var26 < noProducts; var26 += 1)
				ut[var26] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample36)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample42)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample55) {
			for(int var52 = 0; var52 < noObs; var52 += 1)
				beta[var52] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$)) + b);
		}
		for(int i = 0; i < noObs; i += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!fixedFlag$sample29 || !fixedFlag$sample55)) {
				for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1)
					exped[i][j$var75] = Math.exp((ut[j$var75] - (beta[i] * Prices[i][j$var75])));
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$10 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$10 = (reduceVar$sum$10 + exped[i][cv$reduction90Index]);
				for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1)
					prob[i][j$var103] = (exped[i][j$var103] / reduceVar$sum$10);
			}
			if(!fixedFlag$sample111)
				choices[i] = DistributionSampling.sampleCategorical(RNG$, prob[i]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29) {
			for(int var26 = 0; var26 < noProducts; var26 += 1)
				ut[var26] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample36)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample42)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample55) {
			for(int var52 = 0; var52 < noObs; var52 += 1)
				beta[var52] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$)) + b);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((!fixedFlag$sample29 || !fixedFlag$sample55)) {
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1)
					exped[i][j$var75] = Math.exp((ut[j$var75] - (beta[i] * Prices[i][j$var75])));
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$12 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$12 = (reduceVar$sum$12 + exped[i][cv$reduction90Index]);
				for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1)
					prob[i][j$var103] = (exped[i][j$var103] / reduceVar$sum$12);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29) {
			for(int var26 = 0; var26 < noProducts; var26 += 1)
				ut[var26] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample36)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample42)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample55) {
			for(int var52 = 0; var52 < noObs; var52 += 1)
				beta[var52] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$)) + b);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((!fixedFlag$sample29 || !fixedFlag$sample55)) {
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1)
					exped[i][j$var75] = Math.exp((ut[j$var75] - (beta[i] * Prices[i][j$var75])));
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$11 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$11 = (reduceVar$sum$11 + exped[i][cv$reduction90Index]);
				for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1)
					prob[i][j$var103] = (exped[i][j$var103] / reduceVar$sum$11);
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample29) {
				for(int var26 = 0; var26 < noProducts; var26 += 1)
					sample29(var26);
			}
			if(!fixedFlag$sample36)
				sample36();
			if(!fixedFlag$sample42)
				sample42();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample55) {
				for(int var52 = 0; var52 < noObs; var52 += 1)
					sample55(var52);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample55) {
				for(int var52 = (noObs - 1); var52 >= 0; var52 -= 1)
					sample55(var52);
			}
			if(!fixedFlag$sample42)
				sample42();
			if(!fixedFlag$sample36)
				sample36();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample29) {
				for(int var26 = (noProducts - 1); var26 >= 0; var26 -= 1)
					sample29(var26);
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
		logProbability$var15 = 0.0;
		logProbability$exped = 0.0;
		logProbability$prob = 0.0;
		logProbability$ut = 0.0;
		if(!fixedProbFlag$sample29) {
			for(int var26 = 0; var26 < noProducts; var26 += 1)
				logProbability$sample29[var26] = 0.0;
		}
		logProbability$var33 = 0.0;
		if(!fixedProbFlag$sample36)
			logProbability$b = 0.0;
		logProbability$var39 = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$sigma = 0.0;
		logProbability$var41 = 0.0;
		logProbability$beta = 0.0;
		if(!fixedProbFlag$sample55) {
			for(int var52 = 0; var52 < noObs; var52 += 1)
				logProbability$sample55[var52] = 0.0;
		}
		for(int i = 0; i < noObs; i += 1)
			logProbability$var107[i] = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample111) {
			for(int i = 0; i < noObs; i += 1)
				logProbability$sample111[i] = 0.0;
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
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(fixedFlag$sample36)
			logProbabilityValue$sample36();
		if(fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(fixedFlag$sample55)
			logProbabilityValue$sample55();
		logProbabilityValue$sample111();
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
		logProbabilityValue$sample29();
		logProbabilityValue$sample36();
		logProbabilityValue$sample42();
		logProbabilityValue$sample55();
		logProbabilityValue$sample111();
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
		logProbabilityValue$sample29();
		logProbabilityValue$sample36();
		logProbabilityValue$sample42();
		logProbabilityValue$sample55();
		logProbabilityValue$sample111();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29) {
			for(int var26 = 0; var26 < noProducts; var26 += 1)
				ut[var26] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample36)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample42)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample55) {
			for(int var52 = 0; var52 < noObs; var52 += 1)
				beta[var52] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$)) + b);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((!fixedFlag$sample29 || !fixedFlag$sample55)) {
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1)
					exped[i][j$var75] = Math.exp((ut[j$var75] - (beta[i] * Prices[i][j$var75])));
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$13 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$13 = (reduceVar$sum$13 + exped[i][cv$reduction90Index]);
				for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1)
					prob[i][j$var103] = (exped[i][j$var103] / reduceVar$sum$13);
			}
		}
		
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
		int cv$length1 = choices.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			choices[cv$index1] = ObsChoices[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		for(int i = 0; i < noObs; i += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((setFlag$ut && setFlag$beta)) {
				for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1)
					exped[i][j$var75] = Math.exp((ut[j$var75] - (beta[i] * Prices[i][j$var75])));
			}
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$14 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$14 = (reduceVar$sum$14 + exped[i][cv$reduction90Index]);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((setFlag$ut && setFlag$beta)) {
				for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1)
					prob[i][j$var103] = (exped[i][j$var103] / reduceVar$sum$14);
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n\nmodel DiscreteChoiceRandCoeff(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {\n    // we just need an uninformative prior for utility intercepts\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n    //can we set the first element to 0? like ut[0] <~ 0\n\n    //priors for distribution of beta\n    double b = gaussian(0,10).sample();\n    double sigma =  inverseGamma(2,2).sample();\n\n    double[] beta = gaussian(b, sigma).sample(noObs);\n\n    int[] choices = new int[noObs];\n\n    for (int i:[0..noObs)){\n        // calculate choice probabilities for consumer i\n\n        double[] exped = new double[noProducts];\n        for(int j : [0..noProducts)) {\n            exped[j] = exp(ut[j]- beta[i]*Prices[i][j]);\n            }\n        double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n        public double[] prob = new double[noProducts];\n        for (int j : [0..noProducts)) {\n            prob[j] = exped[j] / sum;\n        }\n        // emit choices of consumer i\n        choices[i] = categorical(prob).sample();\n                                }\n\n    // assert that generated choices match observed choices\n    choices.observe(ObsChoices);\n}";
	}
}