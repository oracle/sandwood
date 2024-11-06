package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012notNormalized$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Vulcano2012notNormalized$CoreInterface {
	
	// Declare the variables for the model.
	private int[][] Avail;
	private int[][] ObsSales;
	private int[][] Sales;
	private int T;
	private int[] arrivals;
	private double[] exped;
	private boolean fixedFlag$sample25 = false;
	private boolean fixedFlag$sample43 = false;
	private boolean fixedFlag$sample51 = false;
	private boolean fixedFlag$sample97 = false;
	private boolean fixedProbFlag$sample25 = false;
	private boolean fixedProbFlag$sample43 = false;
	private boolean fixedProbFlag$sample51 = false;
	private boolean fixedProbFlag$sample97 = false;
	private boolean[] guard$sample25multinomial96$global;
	private boolean[][] guard$sample25put94$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$lambda;
	private double[] logProbability$sample25;
	private double[] logProbability$sample51;
	private double[] logProbability$sample97;
	private double logProbability$ut;
	private double logProbability$var18;
	private double logProbability$var36;
	private double logProbability$var41;
	private double[] logProbability$var48;
	private double[] logProbability$var93;
	private double logProbability$weekly_rates;
	private double logProbability$weekly_sales;
	private double logProbability$weekly_ut;
	private int noProducts;
	private int s;
	private boolean setFlag$Sales = false;
	private boolean setFlag$arrivals = false;
	private boolean setFlag$exped = false;
	private boolean setFlag$lambda = false;
	private boolean setFlag$ut = false;
	private boolean setFlag$weekly_sales = false;
	private boolean system$gibbsForward = true;
	private double[] ut;
	private double[][] weekly_rates;
	private int[][] weekly_sales;
	private double[][] weekly_ut;

	public Vulcano2012notNormalized$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for Avail.
	@Override
	public final int[][] get$Avail() {
		return Avail;
	}

	// Setter for Avail.
	@Override
	public final void set$Avail(int[][] cv$value) {
		// Set Avail with flag to mark that it has been set so another array doesn't need
		// to be constructed
		Avail = cv$value;
	}

	// Getter for ObsSales.
	@Override
	public final int[][] get$ObsSales() {
		return ObsSales;
	}

	// Setter for ObsSales.
	@Override
	public final void set$ObsSales(int[][] cv$value) {
		// Set ObsSales with flag to mark that it has been set so another array doesn't need
		// to be constructed
		ObsSales = cv$value;
	}

	// Getter for Sales.
	@Override
	public final int[][] get$Sales() {
		return Sales;
	}

	// Setter for Sales.
	@Override
	public final void set$Sales(int[][] cv$value) {
		// Set Sales with flag to mark that it has been set so another array doesn't need
		// to be constructed
		Sales = cv$value;
		setFlag$Sales = true;
	}

	// Getter for T.
	@Override
	public final int get$T() {
		return T;
	}

	// Setter for T.
	@Override
	public final void set$T(int cv$value) {
		T = cv$value;
	}

	// Getter for arrivals.
	@Override
	public final int[] get$arrivals() {
		return arrivals;
	}

	// Setter for arrivals.
	@Override
	public final void set$arrivals(int[] cv$value) {
		// Set arrivals with flag to mark that it has been set so another array doesn't need
		// to be constructed
		arrivals = cv$value;
		setFlag$arrivals = true;
	}

	// Getter for exped.
	@Override
	public final double[] get$exped() {
		return exped;
	}

	// Setter for exped.
	@Override
	public final void set$exped(double[] cv$value) {
		// Set exped with flag to mark that it has been set so another array doesn't need
		// to be constructed
		exped = cv$value;
		setFlag$exped = true;
	}

	// Getter for fixedFlag$sample25.
	@Override
	public final boolean get$fixedFlag$sample25() {
		return fixedFlag$sample25;
	}

	// Setter for fixedFlag$sample25.
	@Override
	public final void set$fixedFlag$sample25(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample25 including if probabilities
		// need to be updated.
		fixedFlag$sample25 = cv$value;
		
		// Should the probability of sample 25 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample25" with its value "cv$value".
		fixedProbFlag$sample25 = (cv$value && fixedProbFlag$sample25);
		
		// Should the probability of sample 97 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample25" with its value "cv$value".
		fixedProbFlag$sample97 = (cv$value && fixedProbFlag$sample97);
	}

	// Getter for fixedFlag$sample43.
	@Override
	public final boolean get$fixedFlag$sample43() {
		return fixedFlag$sample43;
	}

	// Setter for fixedFlag$sample43.
	@Override
	public final void set$fixedFlag$sample43(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample43 including if probabilities
		// need to be updated.
		fixedFlag$sample43 = cv$value;
		
		// Should the probability of sample 43 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample43" with its value "cv$value".
		fixedProbFlag$sample43 = (cv$value && fixedProbFlag$sample43);
		
		// Should the probability of sample 51 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample43" with its value "cv$value".
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
	}

	// Getter for fixedFlag$sample51.
	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	// Setter for fixedFlag$sample51.
	@Override
	public final void set$fixedFlag$sample51(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample51 including if probabilities
		// need to be updated.
		fixedFlag$sample51 = cv$value;
		
		// Should the probability of sample 51 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample51" with its value "cv$value".
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
		
		// Should the probability of sample 97 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample51" with its value "cv$value".
		fixedProbFlag$sample97 = (cv$value && fixedProbFlag$sample97);
	}

	// Getter for fixedFlag$sample97.
	@Override
	public final boolean get$fixedFlag$sample97() {
		return fixedFlag$sample97;
	}

	// Setter for fixedFlag$sample97.
	@Override
	public final void set$fixedFlag$sample97(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample97 including if probabilities
		// need to be updated.
		fixedFlag$sample97 = cv$value;
		
		// Should the probability of sample 97 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample97" with its value "cv$value".
		fixedProbFlag$sample97 = (cv$value && fixedProbFlag$sample97);
	}

	// Getter for lambda.
	@Override
	public final double[] get$lambda() {
		return lambda;
	}

	// Setter for lambda.
	@Override
	public final void set$lambda(double[] cv$value) {
		// Set lambda with flag to mark that it has been set so another array doesn't need
		// to be constructed
		lambda = cv$value;
		setFlag$lambda = true;
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

	// Getter for logProbability$Sales.
	@Override
	public final double get$logProbability$Sales() {
		return logProbability$Sales;
	}

	// Getter for logProbability$arrivals.
	@Override
	public final double get$logProbability$arrivals() {
		return logProbability$arrivals;
	}

	// Getter for logProbability$exped.
	@Override
	public final double get$logProbability$exped() {
		return logProbability$exped;
	}

	// Getter for logProbability$lambda.
	@Override
	public final double get$logProbability$lambda() {
		return logProbability$lambda;
	}

	// Getter for logProbability$ut.
	@Override
	public final double get$logProbability$ut() {
		return logProbability$ut;
	}

	// Getter for logProbability$weekly_sales.
	@Override
	public final double get$logProbability$weekly_sales() {
		return logProbability$weekly_sales;
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

	// Getter for s.
	@Override
	public final int get$s() {
		return s;
	}

	// Setter for s.
	@Override
	public final void set$s(int cv$value) {
		s = cv$value;
	}

	// Getter for ut.
	@Override
	public final double[] get$ut() {
		return ut;
	}

	// Setter for ut.
	@Override
	public final void set$ut(double[] cv$value) {
		// Set ut with flag to mark that it has been set so another array doesn't need to
		// be constructed
		ut = cv$value;
		setFlag$ut = true;
	}

	// Getter for weekly_sales.
	@Override
	public final int[][] get$weekly_sales() {
		return weekly_sales;
	}

	// Setter for weekly_sales.
	@Override
	public final void set$weekly_sales(int[][] cv$value) {
		// Set weekly_sales with flag to mark that it has been set so another array doesn't
		// need to be constructed
		weekly_sales = cv$value;
		setFlag$weekly_sales = true;
	}

	// Calculate the probability of the samples represented by sample25 using sampled
	// values.
	private final void logProbabilityValue$sample25() {
		// Determine if we need to calculate the values for sample task 25 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample25) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var22 = 0; var22 < noProducts; var22 += 1) {
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[var22] / 3.1622776601683795)) - 1.151292546497023);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample25[var22] = cv$distributionAccumulator;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 25 and consumer double[] 31.
				// 
				// Update the variable probability
				logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < T)) {
					// Update the variable probability
					logProbability$weekly_ut = (logProbability$weekly_ut + cv$distributionAccumulator);
					
					// Update the variable probability
					logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
				}
			}
			logProbability$var18 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample25)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample25 = fixedFlag$sample25;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int var22 = 0; var22 < noProducts; var22 += 1) {
				double cv$sampleValue = logProbability$sample25[var22];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 25 and consumer double[] 31.
				// 
				// Update the variable probability
				logProbability$exped = (logProbability$exped + cv$sampleValue);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < T)) {
					// Update the variable probability
					logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
					
					// Update the variable probability
					logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
				}
			}
			logProbability$var18 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample25)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample43 using sampled
	// values.
	private final void logProbabilityValue$sample43() {
		// Determine if we need to calculate the values for sample task 43 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample43) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var40 = 0; var40 < T; var40 += 1)
				// Add the probability of this sample task to the sample task accumulator.
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGamma(lambda[var40], 10.0, 10.0));
			logProbability$var36 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var41 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$lambda = (logProbability$lambda + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample43)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample43 = fixedFlag$sample43;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var36 = logProbability$var41;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$lambda = (logProbability$lambda + logProbability$var41);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var41);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample43)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var41);
		}
	}

	// Calculate the probability of the samples represented by sample51 using sampled
	// values.
	private final void logProbabilityValue$sample51() {
		// Determine if we need to calculate the values for sample task 51 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample51) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int t$var46 = 0; t$var46 < T; t$var46 += 1) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson(arrivals[t$var46], lambda[t$var46]);
				
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
				logProbability$var48[t$var46] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample51[t$var46] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample51 = (fixedFlag$sample51 && fixedFlag$sample43);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int t$var46 = 0; t$var46 < T; t$var46 += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample51[t$var46];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var48[t$var46] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample97 using sampled
	// values.
	private final void logProbabilityValue$sample97() {
		// Determine if we need to calculate the values for sample task 97 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample97) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var59], weekly_rates[t$var59], arrivals[t$var59]);
				
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
				logProbability$var93[t$var59] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample97[t$var59] = cv$distributionAccumulator;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				if((0 < noProducts))
					// Update the variable probability
					logProbability$Sales = (logProbability$Sales + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample97 = ((fixedFlag$sample97 && fixedFlag$sample25) && fixedFlag$sample51);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				double cv$sampleValue = logProbability$sample97[t$var59];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var93[t$var59] = cv$sampleValue;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				if((0 < noProducts))
					// Update the variable probability
					logProbability$Sales = (logProbability$Sales + cv$sampleValue);
			}
			
			// Update the variable probability
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 25 drawn from Gaussian 18. Inference was performed using Metropolis-Hastings.
	private final void sample25(int var22) {
		// The original value of the sample
		double cv$originalValue = ut[var22];
		
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
			// Substituted "cv$temp$1$var17" with its value "10.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample25multinomial96$global[t$var59] = false;
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 < weekly_ut[t$var59].length) && !guard$sample25multinomial96$global[t$var59])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample25multinomial96$global[t$var59] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 97 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 97 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$3$var92's comment
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var59], weekly_rates[t$var59], arrivals[t$var59]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample25multinomial96$global[t$var59])
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 97 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 97 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$5$var92's comment
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var59], weekly_rates[t$var59], arrivals[t$var59]) + cv$accumulatedProbabilities);
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
		ut[var22] = cv$proposedValue;
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 25 and consumer double[] 31.
		// 
		// Substituted "j$var28" with its value "var22".
		exped[var22] = Math.exp(ut[var22]);
		
		// Guards to ensure that weekly_ut is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 25 and consumer double[] 73.
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
			// Substituted "j$var68" with its value "var22".
			weekly_ut[t$var59][var22] = (exped[var22] * Avail[t$var59][var22]);
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
			for(int j$var88 = 0; j$var88 <= noProducts; j$var88 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample25put94$global[t$var59][j$var88] = false;
		}
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var68" with its value "var22".
			guard$sample25put94$global[t$var59][var22] = false;
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
			for(int j$var88 = 0; j$var88 <= noProducts; j$var88 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample25put94$global[t$var59][j$var88]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample25put94$global[t$var59][j$var88] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$10 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction848Index = 0; cv$reduction848Index <= noProducts; cv$reduction848Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$10 = (reduceVar$denom$10 + weekly_ut[t$var59][cv$reduction848Index]);
					weekly_rates[t$var59][j$var88] = (weekly_ut[t$var59][j$var88] / reduceVar$denom$10);
				}
			}
		}
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var68" with its value "var22".
			if(!guard$sample25put94$global[t$var59][var22]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var68" with its value "var22".
				guard$sample25put94$global[t$var59][var22] = true;
				
				// Reduction of array weekly_ut
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$denom$11 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction82Index = 0; cv$reduction82Index <= noProducts; cv$reduction82Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l's comment
					// Set the right hand term to a value from the array weekly_ut
					reduceVar$denom$11 = (reduceVar$denom$11 + weekly_ut[t$var59][cv$reduction82Index]);
				
				// Substituted "j$var88" with its value "j$var68".
				// 
				// Substituted "j$var68" with its value "var22".
				weekly_rates[t$var59][var22] = (weekly_ut[t$var59][var22] / reduceVar$denom$11);
			}
		}
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var17" with its value "10.0".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample25multinomial96$global[t$var59] = false;
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((0 < weekly_ut[t$var59].length) && !guard$sample25multinomial96$global[t$var59])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample25multinomial96$global[t$var59] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 97 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 97 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$3$var92's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var59], weekly_rates[t$var59], arrivals[t$var59]) + cv$accumulatedProbabilities);
			}
		}
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard$sample25multinomial96$global[t$var59]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample25multinomial96$global[t$var59] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 97 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 97 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$5$var92's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var59], weekly_rates[t$var59], arrivals[t$var59]) + cv$accumulatedProbabilities);
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
			ut[var22] = cv$originalValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 25 and consumer double[] 31.
			// 
			// Substituted "j$var28" with its value "var22".
			exped[var22] = Math.exp(ut[var22]);
			
			// Guards to ensure that weekly_ut is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 25 and consumer double[] 73.
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				// Substituted "j$var68" with its value "var22".
				weekly_ut[t$var59][var22] = (exped[var22] * Avail[t$var59][var22]);
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				for(int j$var88 = 0; j$var88 <= noProducts; j$var88 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample25put94$global[t$var59][j$var88] = false;
			}
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var68" with its value "var22".
				guard$sample25put94$global[t$var59][var22] = false;
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				for(int j$var88 = 0; j$var88 <= noProducts; j$var88 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample25put94$global[t$var59][j$var88]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample25put94$global[t$var59][j$var88] = true;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$13 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction1047Index = 0; cv$reduction1047Index <= noProducts; cv$reduction1047Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$13 = (reduceVar$denom$13 + weekly_ut[t$var59][cv$reduction1047Index]);
						weekly_rates[t$var59][j$var88] = (weekly_ut[t$var59][j$var88] / reduceVar$denom$13);
					}
				}
			}
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var68" with its value "var22".
				if(!guard$sample25put94$global[t$var59][var22]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var68" with its value "var22".
					guard$sample25put94$global[t$var59][var22] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$14 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction82Index = 0; cv$reduction82Index <= noProducts; cv$reduction82Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$14 = (reduceVar$denom$14 + weekly_ut[t$var59][cv$reduction82Index]);
					
					// Substituted "j$var88" with its value "j$var68".
					// 
					// Substituted "j$var68" with its value "var22".
					weekly_rates[t$var59][var22] = (weekly_ut[t$var59][var22] / reduceVar$denom$14);
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 43 drawn from Gamma 36. Inference was performed using a Gamma to
	// Poisson conjugate prior.
	private final void sample43(int var40, int threadID$cv$var40, Rng RNG$) {
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Variable to record the number of samples from consuming random variables.
		lambda[var40] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, arrivals[var40], 1);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 51 drawn from Poisson 48. Inference was performed using Metropolis-Hastings.
	private final void sample51(int t$var46, int threadID$cv$t$var46, Rng RNG$) {
		// The original value of the sample
		int cv$originalValue = arrivals[t$var46];
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 1
		if((cv$var < 1.0))
			cv$var = 1.0;
		
		// An offset for the current value
		double cv$offset = (Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$));
		
		// Make sure the offset is not 0
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		
		// The proposed new value for the sample
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		
		// Variable declaration of cv$originalProbability moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 97 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// cv$temp$0$var47's comment
		// Constructing a random variable input for use later.
		double cv$originalProbability = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var46], weekly_rates[t$var46], cv$originalValue) + DistributionSampling.logProbabilityPoisson(cv$originalValue, lambda[t$var46]));
		
		// Update Sample and intermediate values
		arrivals[t$var46] = cv$proposedValue;
		
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// cv$temp$0$var47's comment
		// Constructing a random variable input for use later.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 97 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// cv$temp$0$var47's comment
		// Constructing a random variable input for use later.
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var46], weekly_rates[t$var46], cv$proposedValue) + DistributionSampling.logProbabilityPoisson(cv$proposedValue, lambda[t$var46]));
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Variable declaration of cv$proposedProbability moved.
		// Declaration comment was:
		// The probability of the random variable generating the new sample value.
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
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			arrivals[t$var46] = cv$originalValue;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for guard$sample25put94$global
		// 
		// Calculate the largest index of j that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_j$var88 = 0;
		if((0 < T))
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			cv$max_j$var88 = Math.max(0, (noProducts + 1));
		
		// Allocation of guard$sample25put94$global for single threaded execution
		// 
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample25put94$global = new boolean[Math.max(0, T)][cv$max_j$var88];
		
		// Allocation of guard$sample25multinomial96$global for single threaded execution
		// 
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample25multinomial96$global = new boolean[Math.max(0, T)];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If ut has not been set already allocate space.
		if(!setFlag$ut)
			// Constructor for ut
			ut = new double[noProducts];
		
		// If exped has not been set already allocate space.
		if(!setFlag$exped)
			// Constructor for exped
			exped = new double[noProducts];
		
		// If lambda has not been set already allocate space.
		if(!setFlag$lambda)
			// Constructor for lambda
			lambda = new double[T];
		
		// If arrivals has not been set already allocate space.
		if(!setFlag$arrivals)
			// Constructor for arrivals
			arrivals = new int[T];
		
		// If Sales has not been set already allocate space.
		if(!setFlag$Sales) {
			// Constructor for Sales
			Sales = new int[T][];
			for(int var54 = 0; var54 < T; var54 += 1)
				Sales[var54] = new int[noProducts];
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				Sales[t$var59] = new int[noProducts];
		}
		
		// Constructor for weekly_rates
		weekly_rates = new double[T][];
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
			weekly_rates[t$var59] = new double[(noProducts + 1)];
		
		// Constructor for weekly_ut
		weekly_ut = new double[T][];
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
			weekly_ut[t$var59] = new double[(noProducts + 1)];
		
		// If weekly_sales has not been set already allocate space.
		if(!setFlag$weekly_sales) {
			// Constructor for weekly_sales
			weekly_sales = new int[T][];
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				weekly_sales[t$var59] = new int[(noProducts + 1)];
		}
		
		// Constructor for logProbability$sample25
		logProbability$sample25 = new double[noProducts];
		
		// Constructor for logProbability$var48
		logProbability$var48 = new double[T];
		
		// Constructor for logProbability$sample51
		logProbability$sample51 = new double[T];
		
		// Constructor for logProbability$var93
		logProbability$var93 = new double[T];
		
		// Constructor for logProbability$sample97
		logProbability$sample97 = new double[T];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample25) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1)
							ut[var22] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1)
							exped[j$var28] = Math.exp(ut[j$var28]);
				}
			);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample43)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample51)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample25)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1)
											weekly_ut[t$var59][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
								}
							);

						weekly_ut[t$var59][noProducts] = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample25) {
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$15 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction82Index = 0; cv$reduction82Index <= noProducts; cv$reduction82Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$15 = (reduceVar$denom$15 + weekly_ut[t$var59][cv$reduction82Index]);
							
							// Alternative value for reduceVar$denom$15 to make it effectively final.
							double reduceVar$denom$15$1 = reduceVar$denom$15;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1)
											weekly_rates[t$var59][j$var88] = (weekly_ut[t$var59][j$var88] / reduceVar$denom$15$1);
								}
							);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample97) {
							DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[t$var59], arrivals[t$var59], weekly_sales[t$var59]);
							int[] observed_weekly_sales = Sales[t$var59];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var98, int forEnd$j$var98, int threadID$j$var98, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var98 = forStart$j$var98; j$var98 < forEnd$j$var98; j$var98 += 1)
											observed_weekly_sales[j$var98] = weekly_sales[t$var59][j$var98];
								}
							);
						}
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample25) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1)
							ut[var22] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1)
							exped[j$var28] = Math.exp(ut[j$var28]);
				}
			);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample43)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample51)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample25)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1)
											weekly_ut[t$var59][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
								}
							);

						weekly_ut[t$var59][noProducts] = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample25) {
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$17 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction82Index = 0; cv$reduction82Index <= noProducts; cv$reduction82Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$17 = (reduceVar$denom$17 + weekly_ut[t$var59][cv$reduction82Index]);
							
							// Alternative value for reduceVar$denom$17 to make it effectively final.
							double reduceVar$denom$17$1 = reduceVar$denom$17;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1)
											weekly_rates[t$var59][j$var88] = (weekly_ut[t$var59][j$var88] / reduceVar$denom$17$1);
								}
							);
						}
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample25) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1)
							ut[var22] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1)
							exped[j$var28] = Math.exp(ut[j$var28]);
				}
			);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample43)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample51)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample25)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1)
											weekly_ut[t$var59][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
								}
							);

						weekly_ut[t$var59][noProducts] = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample25) {
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$16 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction82Index = 0; cv$reduction82Index <= noProducts; cv$reduction82Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$16 = (reduceVar$denom$16 + weekly_ut[t$var59][cv$reduction82Index]);
							
							// Alternative value for reduceVar$denom$16 to make it effectively final.
							double reduceVar$denom$16$1 = reduceVar$denom$16;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1)
											weekly_rates[t$var59][j$var88] = (weekly_ut[t$var59][j$var88] / reduceVar$denom$16$1);
								}
							);
						}
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
			if(!fixedFlag$sample25) {
				for(int var22 = 0; var22 < noProducts; var22 += 1)
					sample25(var22);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample43)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
								sample43(var40, threadID$var40, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample51)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1)
								sample51(t$var46, threadID$t$var46, RNG$1);
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample51)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1)
								sample51(t$var46, threadID$t$var46, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample43)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
								sample43(var40, threadID$var40, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample25) {
				for(int var22 = (noProducts - 1); var22 >= 0; var22 -= 1)
					sample25(var22);
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
		logProbability$var18 = 0.0;
		logProbability$ut = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$weekly_rates = 0.0;
		logProbability$exped = 0.0;
		if(!fixedProbFlag$sample25) {
			for(int var22 = 0; var22 < noProducts; var22 += 1)
				logProbability$sample25[var22] = 0.0;
		}
		logProbability$var36 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample43)
			logProbability$var41 = 0.0;
		for(int t$var46 = 0; t$var46 < T; t$var46 += 1)
			logProbability$var48[t$var46] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample51) {
			for(int t$var46 = 0; t$var46 < T; t$var46 += 1)
				logProbability$sample51[t$var46] = 0.0;
		}
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
			logProbability$var93[t$var59] = 0.0;
		logProbability$Sales = 0.0;
		logProbability$weekly_sales = 0.0;
		if(!fixedProbFlag$sample97) {
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				logProbability$sample97[t$var59] = 0.0;
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
		if(fixedFlag$sample25)
			logProbabilityValue$sample25();
		if(fixedFlag$sample43)
			logProbabilityValue$sample43();
		if(fixedFlag$sample51)
			logProbabilityValue$sample51();
		logProbabilityValue$sample97();
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
		logProbabilityValue$sample25();
		logProbabilityValue$sample43();
		logProbabilityValue$sample51();
		logProbabilityValue$sample97();
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
		logProbabilityValue$sample25();
		logProbabilityValue$sample43();
		logProbabilityValue$sample51();
		logProbabilityValue$sample97();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample25) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1)
							ut[var22] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1)
							exped[j$var28] = Math.exp(ut[j$var28]);
				}
			);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample43)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample51)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample25)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1)
											weekly_ut[t$var59][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
								}
							);

						weekly_ut[t$var59][noProducts] = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample25) {
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$18 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction82Index = 0; cv$reduction82Index <= noProducts; cv$reduction82Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$18 = (reduceVar$denom$18 + weekly_ut[t$var59][cv$reduction82Index]);
							
							// Alternative value for reduceVar$denom$18 to make it effectively final.
							double reduceVar$denom$18$1 = reduceVar$denom$18;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1)
											weekly_rates[t$var59][j$var88] = (weekly_ut[t$var59][j$var88] / reduceVar$denom$18$1);
								}
							);
						}
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
		// Propagating values back from observations into the models intermediate variables.
		int cv$length1 = Sales.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = ObsSales[cv$index1];
			int[] cv$target2 = Sales[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
		for(int t$var59 = (T - 1); t$var59 >= 0; t$var59 -= 1) {
			// Variable declaration of observed_weekly_sales moved.
			int[] observed_weekly_sales = Sales[t$var59];
			for(int j$var98 = (noProducts - 1); j$var98 >= 0; j$var98 -= 1)
				weekly_sales[t$var59][j$var98] = observed_weekly_sales[j$var98];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(setFlag$ut)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1)
							exped[j$var28] = Math.exp(ut[j$var28]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(setFlag$ut)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1)
											weekly_ut[t$var59][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
								}
							);

						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$19 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction82Index = 0; cv$reduction82Index <= noProducts; cv$reduction82Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$19 = (reduceVar$denom$19 + weekly_ut[t$var59][cv$reduction82Index]);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(setFlag$ut) {
							// Alternative value for reduceVar$denom$19 to make it effectively final.
							double reduceVar$denom$19$1 = reduceVar$denom$19;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1)
											weekly_rates[t$var59][j$var88] = (weekly_ut[t$var59][j$var88] / reduceVar$denom$19$1);
								}
							);
						}
						if(setFlag$weekly_sales) {
							int[] observed_weekly_sales = Sales[t$var59];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var98, int forEnd$j$var98, int threadID$j$var98, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var98 = forStart$j$var98; j$var98 < forEnd$j$var98; j$var98 += 1)
											observed_weekly_sales[j$var98] = weekly_sales[t$var59][j$var98];
								}
							);
						}
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\nmodel Vulcano2012notNormalized(int noProducts, int T, int s, int[][] ObsSales, int[][] Avail) {\n    // Avail is the availability matrix, T-by-noProducts\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n\n    //exponentiate right here (in the non-basic models move to the for loop)\n    double[] exped = new double[noProducts];\n    for(int j : [0..noProducts)) {\n    exped[j] = exp(ut[j]);\n    }\n\n    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector if RGBU has some estimates, or just use some ad hoc priors\n    double[ ] lambda = gamma(10,10).sample(T);\n\n    // draw arrivals\n    int[] arrivals = new int[T];\n    for (int t : [0..T)){\n    arrivals[t]= poisson(lambda[t]).sample();\n    }\n\n    int[][] Sales = new int[T][noProducts];\n\n    for (int t:[0..T)){\n        // for each period t calculate choice probabilities and sales\n\n        double[] weekly_rates = new double[noProducts+1];\n        double[] weekly_ut = new double[noProducts+1];\n\n        for (int j : [0..noProducts)) {\n            weekly_ut[j] = exped[j]*Avail[t][j] ;\n        }\n        // add outside option value (which is always available)\n        weekly_ut[noProducts] = 1.0;\n        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n\n        for (int j : [0..noProducts]) {\n            weekly_rates[j] = weekly_ut[j]/denom ;\n        }\n\n        int[] weekly_sales = multinomial(weekly_rates, arrivals[t]).sample();\n\n        //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n        int[] observed_weekly_sales = new int[noProducts];\n        for (int j : [0..noProducts)) {\n            observed_weekly_sales[j] = weekly_sales[j] ;\n        }\n\n        // record sales for period t\n        Sales[t] = observed_weekly_sales;\n\n    }\n    // assert that generated sales match observed sales\n    Sales.observe(ObsSales);\n}";
	}
}