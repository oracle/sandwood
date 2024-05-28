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
	private boolean fixedFlag$sample141 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample64 = false;
	private boolean fixedFlag$sample79 = false;
	private boolean fixedProbFlag$sample141 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample64 = false;
	private boolean fixedProbFlag$sample79 = false;
	private boolean[] guard$sample32multinomial140$global;
	private boolean[][] guard$sample32put138$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$lambda;
	private double[] logProbability$sample141;
	private double[] logProbability$sample32;
	private double[] logProbability$sample79;
	private double logProbability$ut;
	private double[] logProbability$var137;
	private double logProbability$var18;
	private double logProbability$var50;
	private double logProbability$var62;
	private double[] logProbability$var76;
	private double logProbability$weekly_rates;
	private double logProbability$weekly_sales;
	private double logProbability$weekly_ut;
	private int noProducts;
	private int s;
	private boolean setFlag$arrivals = false;
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
		// Set flags for all the side effects of arrivals including if probabilities need
		// to be updated.
		// Set arrivals with flag to mark that it has been set so another array doesn't need
		// to be constructed
		arrivals = cv$value;
		setFlag$arrivals = true;
		
		// Unset the fixed probability flag for sample 79 as it depends on arrivals.
		fixedProbFlag$sample79 = false;
		
		// Unset the fixed probability flag for sample 141 as it depends on arrivals.
		fixedProbFlag$sample141 = false;
	}

	// Getter for exped.
	@Override
	public final double[] get$exped() {
		return exped;
	}

	// Getter for fixedFlag$sample141.
	@Override
	public final boolean get$fixedFlag$sample141() {
		return fixedFlag$sample141;
	}

	// Setter for fixedFlag$sample141.
	@Override
	public final void set$fixedFlag$sample141(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample141 including if probabilities
		// need to be updated.
		fixedFlag$sample141 = cv$value;
		
		// Should the probability of sample 141 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample141" with its value "cv$value".
		fixedProbFlag$sample141 = (cv$value && fixedProbFlag$sample141);
	}

	// Getter for fixedFlag$sample32.
	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	// Setter for fixedFlag$sample32.
	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample32 including if probabilities
		// need to be updated.
		fixedFlag$sample32 = cv$value;
		
		// Should the probability of sample 32 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample32" with its value "cv$value".
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
		
		// Should the probability of sample 141 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample32" with its value "cv$value".
		fixedProbFlag$sample141 = (cv$value && fixedProbFlag$sample141);
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
		// 
		// Substituted "fixedFlag$sample64" with its value "cv$value".
		fixedProbFlag$sample64 = (cv$value && fixedProbFlag$sample64);
		
		// Should the probability of sample 79 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample64" with its value "cv$value".
		fixedProbFlag$sample79 = (cv$value && fixedProbFlag$sample79);
	}

	// Getter for fixedFlag$sample79.
	@Override
	public final boolean get$fixedFlag$sample79() {
		return fixedFlag$sample79;
	}

	// Setter for fixedFlag$sample79.
	@Override
	public final void set$fixedFlag$sample79(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample79 including if probabilities
		// need to be updated.
		fixedFlag$sample79 = cv$value;
		
		// Should the probability of sample 79 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample79" with its value "cv$value".
		fixedProbFlag$sample79 = (cv$value && fixedProbFlag$sample79);
		
		// Should the probability of sample 141 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample79" with its value "cv$value".
		fixedProbFlag$sample141 = (cv$value && fixedProbFlag$sample141);
	}

	// Getter for lambda.
	@Override
	public final double[] get$lambda() {
		return lambda;
	}

	// Setter for lambda.
	@Override
	public final void set$lambda(double[] cv$value) {
		// Set flags for all the side effects of lambda including if probabilities need to
		// be updated.
		// Set lambda with flag to mark that it has been set so another array doesn't need
		// to be constructed
		lambda = cv$value;
		setFlag$lambda = true;
		
		// Unset the fixed probability flag for sample 64 as it depends on lambda.
		fixedProbFlag$sample64 = false;
		
		// Unset the fixed probability flag for sample 79 as it depends on lambda.
		fixedProbFlag$sample79 = false;
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
		// Set flags for all the side effects of ut including if probabilities need to be
		// updated.
		// Set ut with flag to mark that it has been set so another array doesn't need to
		// be constructed
		ut = cv$value;
		setFlag$ut = true;
		
		// Unset the fixed probability flag for sample 32 as it depends on ut.
		fixedProbFlag$sample32 = false;
		
		// Unset the fixed probability flag for sample 141 as it depends on ut.
		fixedProbFlag$sample141 = false;
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

	// Calculate the probability of the samples represented by sample141 using sampled
	// values.
	private final void logProbabilityValue$sample141() {
		// Determine if we need to calculate the values for sample task 141 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample141) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var89], weekly_rates[t$var89], arrivals[t$var89]);
				
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
				logProbability$var137[t$var89] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample141[t$var89] = cv$distributionAccumulator;
				
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
			fixedProbFlag$sample141 = ((fixedFlag$sample141 && fixedFlag$sample32) && fixedFlag$sample79);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				double cv$sampleValue = logProbability$sample141[t$var89];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var137[t$var89] = cv$sampleValue;
				
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

	// Calculate the probability of the samples represented by sample32 using sampled
	// values.
	private final void logProbabilityValue$sample32() {
		// Determine if we need to calculate the values for sample task 32 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample32) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var29 = 0; var29 < noProducts; var29 += 1) {
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[var29] / 3.1622776601683795)) - 1.151292546497023);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample32[var29] = cv$distributionAccumulator;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 32 and consumer double[] 45.
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
			if(fixedFlag$sample32)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample32 = fixedFlag$sample32;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int var29 = 0; var29 < noProducts; var29 += 1) {
				double cv$sampleValue = logProbability$sample32[var29];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 32 and consumer double[] 45.
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
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample64 using sampled
	// values.
	private final void logProbabilityValue$sample64() {
		// Determine if we need to calculate the values for sample task 64 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample64) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var61 = 0; var61 < T; var61 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGamma(lambda[var61], 10.0, 10.0));
			logProbability$var50 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var62 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample64)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample64 = fixedFlag$sample64;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var50 = logProbability$var62;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$lambda = (logProbability$lambda + logProbability$var62);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var62);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample64)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var62);
		}
	}

	// Calculate the probability of the samples represented by sample79 using sampled
	// values.
	private final void logProbabilityValue$sample79() {
		// Determine if we need to calculate the values for sample task 79 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample79) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson(arrivals[t$var74], lambda[t$var74]);
				
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
				logProbability$var76[t$var74] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample79[t$var74] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample79 = (fixedFlag$sample79 && fixedFlag$sample64);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample79[t$var74];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var76[t$var74] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 32 drawn from Gaussian 18. Inference was performed using Metropolis-Hastings.
	private final void sample32(int var29) {
		// The original value of the sample
		double cv$originalValue = ut[var29];
		
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
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32multinomial140$global[t$var89] = false;
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((0 < weekly_ut[t$var89].length) && !guard$sample32multinomial140$global[t$var89])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32multinomial140$global[t$var89] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 141 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 141 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$3$var136's comment
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var89], weekly_rates[t$var89], arrivals[t$var89]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample32multinomial140$global[t$var89])
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 141 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 141 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$5$var136's comment
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var89], weekly_rates[t$var89], arrivals[t$var89]) + cv$accumulatedProbabilities);
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
		ut[var29] = cv$proposedValue;
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 32 and consumer double[] 45.
		// 
		// Substituted "j$var42" with its value "var29".
		exped[var29] = Math.exp(ut[var29]);
		
		// Guards to ensure that weekly_ut is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 32 and consumer double[] 109.
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
			// Substituted "j$var104" with its value "var29".
			weekly_ut[t$var89][var29] = (exped[var29] * Avail[t$var89][var29]);
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			for(int j$var132 = 0; j$var132 <= noProducts; j$var132 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32put138$global[t$var89][j$var132] = false;
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var104" with its value "var29".
			guard$sample32put138$global[t$var89][var29] = false;
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			for(int j$var132 = 0; j$var132 <= noProducts; j$var132 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample32put138$global[t$var89][j$var132]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put138$global[t$var89][j$var132] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$10 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$10 = (reduceVar$denom$10 + weekly_ut[t$var89][cv$reduction118Index]);
					weekly_rates[t$var89][j$var132] = (weekly_ut[t$var89][j$var132] / reduceVar$denom$10);
				}
			}
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var104" with its value "var29".
			if(!guard$sample32put138$global[t$var89][var29]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var104" with its value "var29".
				guard$sample32put138$global[t$var89][var29] = true;
				
				// Reduction of array weekly_ut
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$denom$11 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l's comment
					// Set the right hand term to a value from the array weekly_ut
					reduceVar$denom$11 = (reduceVar$denom$11 + weekly_ut[t$var89][cv$reduction118Index]);
				
				// Substituted "j$var132" with its value "j$var104".
				// 
				// Substituted "j$var104" with its value "var29".
				weekly_rates[t$var89][var29] = (weekly_ut[t$var89][var29] / reduceVar$denom$11);
			}
		}
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var17" with its value "10.0".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample32multinomial140$global[t$var89] = false;
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((0 < weekly_ut[t$var89].length) && !guard$sample32multinomial140$global[t$var89])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32multinomial140$global[t$var89] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 141 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 141 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$3$var136's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var89], weekly_rates[t$var89], arrivals[t$var89]) + cv$accumulatedProbabilities);
			}
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard$sample32multinomial140$global[t$var89]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample32multinomial140$global[t$var89] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 141 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 141 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$5$var136's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var89], weekly_rates[t$var89], arrivals[t$var89]) + cv$accumulatedProbabilities);
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
			ut[var29] = cv$originalValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 32 and consumer double[] 45.
			// 
			// Substituted "j$var42" with its value "var29".
			exped[var29] = Math.exp(ut[var29]);
			
			// Guards to ensure that weekly_ut is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 32 and consumer double[] 109.
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				// Substituted "j$var104" with its value "var29".
				weekly_ut[t$var89][var29] = (exped[var29] * Avail[t$var89][var29]);
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				for(int j$var132 = 0; j$var132 <= noProducts; j$var132 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample32put138$global[t$var89][j$var132] = false;
			}
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var104" with its value "var29".
				guard$sample32put138$global[t$var89][var29] = false;
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				for(int j$var132 = 0; j$var132 <= noProducts; j$var132 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample32put138$global[t$var89][j$var132]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample32put138$global[t$var89][j$var132] = true;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$13 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$13 = (reduceVar$denom$13 + weekly_ut[t$var89][cv$reduction118Index]);
						weekly_rates[t$var89][j$var132] = (weekly_ut[t$var89][j$var132] / reduceVar$denom$13);
					}
				}
			}
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var104" with its value "var29".
				if(!guard$sample32put138$global[t$var89][var29]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var104" with its value "var29".
					guard$sample32put138$global[t$var89][var29] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$14 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$14 = (reduceVar$denom$14 + weekly_ut[t$var89][cv$reduction118Index]);
					
					// Substituted "j$var132" with its value "j$var104".
					// 
					// Substituted "j$var104" with its value "var29".
					weekly_rates[t$var89][var29] = (weekly_ut[t$var89][var29] / reduceVar$denom$14);
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 64 drawn from Gamma 50. Inference was performed using a Gamma to
	// Poisson conjugate prior.
	private final void sample64(int var61, int threadID$cv$var61, Rng RNG$) {
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Variable to record the number of samples from consuming random variables.
		lambda[var61] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, arrivals[var61], 1);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 79 drawn from Poisson 76. Inference was performed using Metropolis-Hastings.
	private final void sample79(int t$var74, int threadID$cv$t$var74, Rng RNG$) {
		// The original value of the sample
		int cv$originalValue = arrivals[t$var74];
		
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
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// cv$temp$0$var75's comment
		// Constructing a random variable input for use later.
		// 
		// Set the current value to the current state of the tree.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 141 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// cv$temp$0$var75's comment
		// Constructing a random variable input for use later.
		// 
		// Set the current value to the current state of the tree.
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Processing sample task 141 of consumer random variable null.
		// 
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// Substituted "t$var89" with its value "t$var74".
		// 
		// cv$temp$1$weekly_rates's comment
		// Substituted "t$var89" with its value "t$var74".
		// 
		// cv$temp$2$var136's comment
		// Variable declaration of cv$temp$2$var136 moved.
		// 
		// Constructing a random variable input for use later.
		// 
		// Set the current value to the current state of the tree.
		double cv$originalProbability = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var74], weekly_rates[t$var74], cv$originalValue) + DistributionSampling.logProbabilityPoisson(cv$originalValue, lambda[t$var74]));
		
		// Update Sample and intermediate values
		arrivals[t$var74] = cv$proposedValue;
		
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// cv$temp$0$var75's comment
		// Constructing a random variable input for use later.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 141 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// cv$temp$0$var75's comment
		// Constructing a random variable input for use later.
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Processing sample task 141 of consumer random variable null.
		// 
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// Substituted "t$var89" with its value "t$var74".
		// 
		// cv$temp$1$weekly_rates's comment
		// Substituted "t$var89" with its value "t$var74".
		// 
		// cv$temp$2$var136's comment
		// Variable declaration of cv$temp$2$var136 moved.
		// 
		// Constructing a random variable input for use later.
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var74], weekly_rates[t$var74], cv$proposedValue) + DistributionSampling.logProbabilityPoisson(cv$proposedValue, lambda[t$var74]));
		
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
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			arrivals[t$var74] = cv$originalValue;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for guard$sample32put138$global
		// 
		// Calculate the largest index of j that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_j$var132 = 0;
		if((0 < T))
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			cv$max_j$var132 = Math.max(0, (noProducts + 1));
		
		// Allocation of guard$sample32put138$global for single threaded execution
		// 
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample32put138$global = new boolean[Math.max(0, T)][cv$max_j$var132];
		
		// Allocation of guard$sample32multinomial140$global for single threaded execution
		// 
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample32multinomial140$global = new boolean[Math.max(0, T)];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If ut has not been set already allocate space.
		if(!setFlag$ut)
			// Constructor for ut
			ut = new double[noProducts];
		
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
		
		// Constructor for Sales
		Sales = new int[T][];
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
			Sales[t$var89] = new int[noProducts];
		
		// Constructor for weekly_rates
		weekly_rates = new double[T][];
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
			weekly_rates[t$var89] = new double[(noProducts + 1)];
		
		// Constructor for weekly_ut
		weekly_ut = new double[T][];
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
			weekly_ut[t$var89] = new double[(noProducts + 1)];
		
		// If weekly_sales has not been set already allocate space.
		if(!setFlag$weekly_sales) {
			// Constructor for weekly_sales
			weekly_sales = new int[T][];
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				weekly_sales[t$var89] = new int[(noProducts + 1)];
		}
		
		// Constructor for logProbability$sample32
		logProbability$sample32 = new double[noProducts];
		
		// Constructor for logProbability$var76
		logProbability$var76 = new double[T];
		
		// Constructor for logProbability$sample79
		logProbability$sample79 = new double[T];
		
		// Constructor for logProbability$var137
		logProbability$var137 = new double[T];
		
		// Constructor for logProbability$sample141
		logProbability$sample141 = new double[T];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							ut[var29] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1)
							exped[j$var42] = Math.exp(ut[j$var42]);
				}
			);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample64)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
							lambda[var61] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample79)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var74, int forEnd$t$var74, int threadID$t$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var74 = forStart$t$var74; t$var74 < forEnd$t$var74; t$var74 += 1)
							arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var74]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var89, int forEnd$index$t$var89, int threadID$index$t$var89, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var89 = forStart$index$t$var89; index$t$var89 < forEnd$index$t$var89; index$t$var89 += 1) {
						int t$var89 = index$t$var89;
						int threadID$t$var89 = threadID$index$t$var89;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample32)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var104, int forEnd$j$var104, int threadID$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var104 = forStart$j$var104; j$var104 < forEnd$j$var104; j$var104 += 1)
											weekly_ut[t$var89][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
								}
							);

						weekly_ut[t$var89][noProducts] = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample32) {
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$15 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$15 = (reduceVar$denom$15 + weekly_ut[t$var89][cv$reduction118Index]);
							
							// Alternative name for reduceVar$denom$15 to make it effectively final.
							double reduceVar$denom$15$1 = reduceVar$denom$15;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var132, int forEnd$j$var132, int threadID$j$var132, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var132 = forStart$j$var132; j$var132 < forEnd$j$var132; j$var132 += 1)
											weekly_rates[t$var89][j$var132] = (weekly_ut[t$var89][j$var132] / reduceVar$denom$15$1);
								}
							);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample141) {
							DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[t$var89], arrivals[t$var89], weekly_sales[t$var89]);
							int[] observed_weekly_sales = Sales[t$var89];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var148, int forEnd$j$var148, int threadID$j$var148, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var148 = forStart$j$var148; j$var148 < forEnd$j$var148; j$var148 += 1)
											observed_weekly_sales[j$var148] = weekly_sales[t$var89][j$var148];
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
		if(!fixedFlag$sample32) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							ut[var29] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1)
							exped[j$var42] = Math.exp(ut[j$var42]);
				}
			);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample64)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
							lambda[var61] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample79)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var74, int forEnd$t$var74, int threadID$t$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var74 = forStart$t$var74; t$var74 < forEnd$t$var74; t$var74 += 1)
							arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var74]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var89, int forEnd$index$t$var89, int threadID$index$t$var89, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var89 = forStart$index$t$var89; index$t$var89 < forEnd$index$t$var89; index$t$var89 += 1) {
						int t$var89 = index$t$var89;
						int threadID$t$var89 = threadID$index$t$var89;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample32)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var104, int forEnd$j$var104, int threadID$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var104 = forStart$j$var104; j$var104 < forEnd$j$var104; j$var104 += 1)
											weekly_ut[t$var89][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
								}
							);

						weekly_ut[t$var89][noProducts] = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample32) {
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$17 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$17 = (reduceVar$denom$17 + weekly_ut[t$var89][cv$reduction118Index]);
							
							// Alternative name for reduceVar$denom$17 to make it effectively final.
							double reduceVar$denom$17$1 = reduceVar$denom$17;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var132, int forEnd$j$var132, int threadID$j$var132, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var132 = forStart$j$var132; j$var132 < forEnd$j$var132; j$var132 += 1)
											weekly_rates[t$var89][j$var132] = (weekly_ut[t$var89][j$var132] / reduceVar$denom$17$1);
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
		if(!fixedFlag$sample32) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							ut[var29] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1)
							exped[j$var42] = Math.exp(ut[j$var42]);
				}
			);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample64)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
							lambda[var61] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample79)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var74, int forEnd$t$var74, int threadID$t$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var74 = forStart$t$var74; t$var74 < forEnd$t$var74; t$var74 += 1)
							arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var74]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var89, int forEnd$index$t$var89, int threadID$index$t$var89, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var89 = forStart$index$t$var89; index$t$var89 < forEnd$index$t$var89; index$t$var89 += 1) {
						int t$var89 = index$t$var89;
						int threadID$t$var89 = threadID$index$t$var89;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample32)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var104, int forEnd$j$var104, int threadID$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var104 = forStart$j$var104; j$var104 < forEnd$j$var104; j$var104 += 1)
											weekly_ut[t$var89][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
								}
							);

						weekly_ut[t$var89][noProducts] = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample32) {
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$16 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$16 = (reduceVar$denom$16 + weekly_ut[t$var89][cv$reduction118Index]);
							
							// Alternative name for reduceVar$denom$16 to make it effectively final.
							double reduceVar$denom$16$1 = reduceVar$denom$16;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var132, int forEnd$j$var132, int threadID$j$var132, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var132 = forStart$j$var132; j$var132 < forEnd$j$var132; j$var132 += 1)
											weekly_rates[t$var89][j$var132] = (weekly_ut[t$var89][j$var132] / reduceVar$denom$16$1);
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
			if(!fixedFlag$sample32) {
				for(int var29 = 0; var29 < noProducts; var29 += 1)
					sample32(var29);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample64)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
								sample64(var61, threadID$var61, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample79)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var74, int forEnd$t$var74, int threadID$t$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int t$var74 = forStart$t$var74; t$var74 < forEnd$t$var74; t$var74 += 1)
								sample79(t$var74, threadID$t$var74, RNG$1);
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample79)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var74, int forEnd$t$var74, int threadID$t$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int t$var74 = forStart$t$var74; t$var74 < forEnd$t$var74; t$var74 += 1)
								sample79(t$var74, threadID$t$var74, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample64)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
								sample64(var61, threadID$var61, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample32) {
				for(int var29 = (noProducts - 1); var29 >= 0; var29 -= 1)
					sample32(var29);
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
		logProbability$weekly_rates = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$ut = 0.0;
		if(!fixedProbFlag$sample32) {
			for(int var29 = 0; var29 < noProducts; var29 += 1)
				logProbability$sample32[var29] = 0.0;
		}
		logProbability$var50 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample64)
			logProbability$var62 = 0.0;
		for(int t$var74 = 0; t$var74 < T; t$var74 += 1)
			logProbability$var76[t$var74] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample79) {
			for(int t$var74 = 0; t$var74 < T; t$var74 += 1)
				logProbability$sample79[t$var74] = 0.0;
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
			logProbability$var137[t$var89] = 0.0;
		logProbability$Sales = 0.0;
		logProbability$weekly_sales = 0.0;
		if(!fixedProbFlag$sample141) {
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				logProbability$sample141[t$var89] = 0.0;
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
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample64)
			logProbabilityValue$sample64();
		if(fixedFlag$sample79)
			logProbabilityValue$sample79();
		logProbabilityValue$sample141();
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
		logProbabilityValue$sample32();
		logProbabilityValue$sample64();
		logProbabilityValue$sample79();
		logProbabilityValue$sample141();
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
		logProbabilityValue$sample32();
		logProbabilityValue$sample64();
		logProbabilityValue$sample79();
		logProbabilityValue$sample141();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							ut[var29] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1)
							exped[j$var42] = Math.exp(ut[j$var42]);
				}
			);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample64)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
							lambda[var61] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample79)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var74, int forEnd$t$var74, int threadID$t$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var74 = forStart$t$var74; t$var74 < forEnd$t$var74; t$var74 += 1)
							arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var74]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var89, int forEnd$index$t$var89, int threadID$index$t$var89, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var89 = forStart$index$t$var89; index$t$var89 < forEnd$index$t$var89; index$t$var89 += 1) {
						int t$var89 = index$t$var89;
						int threadID$t$var89 = threadID$index$t$var89;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample32)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var104, int forEnd$j$var104, int threadID$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var104 = forStart$j$var104; j$var104 < forEnd$j$var104; j$var104 += 1)
											weekly_ut[t$var89][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
								}
							);

						weekly_ut[t$var89][noProducts] = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample32) {
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$18 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$18 = (reduceVar$denom$18 + weekly_ut[t$var89][cv$reduction118Index]);
							
							// Alternative name for reduceVar$denom$18 to make it effectively final.
							double reduceVar$denom$18$1 = reduceVar$denom$18;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var132, int forEnd$j$var132, int threadID$j$var132, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var132 = forStart$j$var132; j$var132 < forEnd$j$var132; j$var132 += 1)
											weekly_rates[t$var89][j$var132] = (weekly_ut[t$var89][j$var132] / reduceVar$denom$18$1);
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
		for(int t$var89 = (T - 1); t$var89 >= 0; t$var89 -= 1) {
			for(int j$var148 = (noProducts - 1); j$var148 >= 0; j$var148 -= 1)
				// Substituted "observed_weekly_sales" with its value "Sales[t$var89]".
				weekly_sales[t$var89][j$var148] = Sales[t$var89][j$var148];
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
				(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1)
							exped[j$var42] = Math.exp(ut[j$var42]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var89, int forEnd$index$t$var89, int threadID$index$t$var89, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var89 = forStart$index$t$var89; index$t$var89 < forEnd$index$t$var89; index$t$var89 += 1) {
						int t$var89 = index$t$var89;
						int threadID$t$var89 = threadID$index$t$var89;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(setFlag$ut)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var104, int forEnd$j$var104, int threadID$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var104 = forStart$j$var104; j$var104 < forEnd$j$var104; j$var104 += 1)
											weekly_ut[t$var89][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
								}
							);

						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$19 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$19 = (reduceVar$denom$19 + weekly_ut[t$var89][cv$reduction118Index]);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(setFlag$ut) {
							// Alternative name for reduceVar$denom$19 to make it effectively final.
							double reduceVar$denom$19$1 = reduceVar$denom$19;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var132, int forEnd$j$var132, int threadID$j$var132, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var132 = forStart$j$var132; j$var132 < forEnd$j$var132; j$var132 += 1)
											weekly_rates[t$var89][j$var132] = (weekly_ut[t$var89][j$var132] / reduceVar$denom$19$1);
								}
							);
						}
						if(setFlag$weekly_sales) {
							int[] observed_weekly_sales = Sales[t$var89];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var148, int forEnd$j$var148, int threadID$j$var148, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var148 = forStart$j$var148; j$var148 < forEnd$j$var148; j$var148 += 1)
											observed_weekly_sales[j$var148] = weekly_sales[t$var89][j$var148];
								}
							);
						}
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\nmodel Vulcano2012notNormalized(int noProducts, int T, int s, int[][] ObsSales, int[][] Avail) {\n    // Avail is the availability matrix, T-by-noProducts\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n\n    //exponentiate right here (in the non-basic models move to the for loop)\n    double[] exped = new double[noProducts];\n    for(int j : [0..noProducts)) {\n    exped[j] = exp(ut[j]);\n    }\n\n    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector if RGBU has some estimates, or just use some ad hoc priors\n    double[ ] lambda = gamma(10,10).sample(T);\n\n    // draw arrivals\n    int[] arrivals = new int[T];\n    for (int t : [0..T)){\n    arrivals[t]= poisson(lambda[t]).sample();\n    }\n\n    int[][] Sales = new int[T][];\n\n    for (int t:[0..T)){\n        // for each period t calculate choice probabilities and sales\n\n        double[] weekly_rates = new double[noProducts+1];\n        double[] weekly_ut = new double[noProducts+1];\n\n        for (int j : [0..noProducts)) {\n            weekly_ut[j] = exped[j]*Avail[t][j] ;\n        }\n        // add outside option value (which is always available)\n        weekly_ut[noProducts] = 1.0;\n        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n\n        for (int j : [0..noProducts]) {\n            weekly_rates[j] = weekly_ut[j]/denom ;\n        }\n\n        int[] weekly_sales = multinomial(weekly_rates, arrivals[t]).sample();\n\n        //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n        int[] observed_weekly_sales = new int[noProducts];\n        for (int j : [0..noProducts)) {\n            observed_weekly_sales[j] = weekly_sales[j] ;\n        }\n\n        // record sales for period t\n        Sales[t] = observed_weekly_sales;\n\n    }\n    // assert that generated sales match observed sales\n    Sales.observe(ObsSales);\n}";
	}
}