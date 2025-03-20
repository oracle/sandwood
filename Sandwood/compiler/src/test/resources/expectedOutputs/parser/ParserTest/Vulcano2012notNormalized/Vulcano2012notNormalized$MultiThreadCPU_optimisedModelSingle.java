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
	private boolean fixedFlag$sample131 = false;
	private boolean fixedFlag$sample22 = false;
	private boolean fixedFlag$sample54 = false;
	private boolean fixedFlag$sample69 = false;
	private boolean fixedProbFlag$sample131 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample54 = false;
	private boolean fixedProbFlag$sample69 = false;
	private boolean[] guard$sample22multinomial130$global;
	private boolean[][] guard$sample22put128$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$lambda;
	private double[] logProbability$sample131;
	private double[] logProbability$sample22;
	private double logProbability$ut;
	private double logProbability$var10;
	private double logProbability$var129;
	private double logProbability$var42;
	private double logProbability$var54;
	private double logProbability$var68;
	private double logProbability$var69;
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
		
		// Unset the fixed probability flag for sample 69 as it depends on arrivals.
		fixedProbFlag$sample69 = false;
		
		// Unset the fixed probability flag for sample 131 as it depends on arrivals.
		fixedProbFlag$sample131 = false;
	}

	// Getter for exped.
	@Override
	public final double[] get$exped() {
		return exped;
	}

	// Getter for fixedFlag$sample131.
	@Override
	public final boolean get$fixedFlag$sample131() {
		return fixedFlag$sample131;
	}

	// Setter for fixedFlag$sample131.
	@Override
	public final void set$fixedFlag$sample131(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample131 including if probabilities
		// need to be updated.
		fixedFlag$sample131 = cv$value;
		
		// Should the probability of sample 131 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample131" with its value "cv$value".
		fixedProbFlag$sample131 = (cv$value && fixedProbFlag$sample131);
	}

	// Getter for fixedFlag$sample22.
	@Override
	public final boolean get$fixedFlag$sample22() {
		return fixedFlag$sample22;
	}

	// Setter for fixedFlag$sample22.
	@Override
	public final void set$fixedFlag$sample22(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample22 including if probabilities
		// need to be updated.
		fixedFlag$sample22 = cv$value;
		
		// Should the probability of sample 22 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample22" with its value "cv$value".
		fixedProbFlag$sample22 = (cv$value && fixedProbFlag$sample22);
		
		// Should the probability of sample 131 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample22" with its value "cv$value".
		fixedProbFlag$sample131 = (cv$value && fixedProbFlag$sample131);
	}

	// Getter for fixedFlag$sample54.
	@Override
	public final boolean get$fixedFlag$sample54() {
		return fixedFlag$sample54;
	}

	// Setter for fixedFlag$sample54.
	@Override
	public final void set$fixedFlag$sample54(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample54 including if probabilities
		// need to be updated.
		fixedFlag$sample54 = cv$value;
		
		// Should the probability of sample 54 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample54" with its value "cv$value".
		fixedProbFlag$sample54 = (cv$value && fixedProbFlag$sample54);
		
		// Should the probability of sample 69 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample54" with its value "cv$value".
		fixedProbFlag$sample69 = (cv$value && fixedProbFlag$sample69);
	}

	// Getter for fixedFlag$sample69.
	@Override
	public final boolean get$fixedFlag$sample69() {
		return fixedFlag$sample69;
	}

	// Setter for fixedFlag$sample69.
	@Override
	public final void set$fixedFlag$sample69(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample69 including if probabilities
		// need to be updated.
		fixedFlag$sample69 = cv$value;
		
		// Should the probability of sample 69 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample69" with its value "cv$value".
		fixedProbFlag$sample69 = (cv$value && fixedProbFlag$sample69);
		
		// Should the probability of sample 131 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample69" with its value "cv$value".
		fixedProbFlag$sample131 = (cv$value && fixedProbFlag$sample131);
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
		
		// Unset the fixed probability flag for sample 54 as it depends on lambda.
		fixedProbFlag$sample54 = false;
		
		// Unset the fixed probability flag for sample 69 as it depends on lambda.
		fixedProbFlag$sample69 = false;
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
		
		// Unset the fixed probability flag for sample 22 as it depends on ut.
		fixedProbFlag$sample22 = false;
		
		// Unset the fixed probability flag for sample 131 as it depends on ut.
		fixedProbFlag$sample131 = false;
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

	// Calculate the probability of the samples represented by sample131 using sampled
	// values.
	private final void logProbabilityValue$sample131() {
		// Determine if we need to calculate the values for sample task 131 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample131) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var81], weekly_rates[t$var81], (noProducts + 1), arrivals[t$var81]);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample131[t$var81] = cv$distributionAccumulator;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				if((0 < noProducts))
					// Update the variable probability
					logProbability$Sales = (logProbability$Sales + cv$distributionAccumulator);
			}
			logProbability$var129 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample131 = ((fixedFlag$sample131 && fixedFlag$sample22) && fixedFlag$sample69);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				double cv$sampleValue = logProbability$sample131[t$var81];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				if((0 < noProducts))
					// Update the variable probability
					logProbability$Sales = (logProbability$Sales + cv$sampleValue);
			}
			logProbability$var129 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample22 using sampled
	// values.
	private final void logProbabilityValue$sample22() {
		// Determine if we need to calculate the values for sample task 22 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample22) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var21 = 0; var21 < noProducts; var21 += 1) {
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[var21] / 3.1622776601683795)) - 1.151292546497023);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample22[var21] = cv$distributionAccumulator;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 22 and consumer double[] 37.
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
			logProbability$var10 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample22)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample22 = fixedFlag$sample22;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int var21 = 0; var21 < noProducts; var21 += 1) {
				double cv$sampleValue = logProbability$sample22[var21];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 22 and consumer double[] 37.
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
			logProbability$var10 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample54 using sampled
	// values.
	private final void logProbabilityValue$sample54() {
		// Determine if we need to calculate the values for sample task 54 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample54) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var53 = 0; var53 < T; var53 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGamma(lambda[var53], 10.0, 10.0));
			logProbability$var42 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var54 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample54)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample54 = fixedFlag$sample54;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var42 = logProbability$var54;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$lambda = (logProbability$lambda + logProbability$var54);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var54);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample54)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var54);
		}
	}

	// Calculate the probability of the samples represented by sample69 using sampled
	// values.
	private final void logProbabilityValue$sample69() {
		// Determine if we need to calculate the values for sample task 69 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample69) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int t$var66 = 0; t$var66 < T; t$var66 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityPoisson(arrivals[t$var66], lambda[t$var66]));
			logProbability$var68 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var69 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$arrivals = (logProbability$arrivals + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample69)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample69 = (fixedFlag$sample69 && fixedFlag$sample54);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var68 = logProbability$var69;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$arrivals = (logProbability$arrivals + logProbability$var69);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var69);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample69)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var69);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 22 drawn from Gaussian 10. Inference was performed using Metropolis-Hastings.
	private final void sample22(int var21) {
		// The original value of the sample
		double cv$originalValue = ut[var21];
		
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
			// Substituted "cv$temp$1$var9" with its value "10.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample22multinomial130$global[t$var81] = false;
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample22multinomial130$global[t$var81]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample22multinomial130$global[t$var81] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 131 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 131 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$4$var128's comment
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var81], weekly_rates[t$var81], (noProducts + 1), arrivals[t$var81]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample22multinomial130$global[t$var81])
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 131 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 131 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$7$var128's comment
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var81], weekly_rates[t$var81], (noProducts + 1), arrivals[t$var81]) + cv$accumulatedProbabilities);
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
		ut[var21] = cv$proposedValue;
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 22 and consumer double[] 37.
		// 
		// Substituted "j$var34" with its value "var21".
		exped[var21] = Math.exp(ut[var21]);
		
		// Guards to ensure that weekly_ut is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 22 and consumer double[] 101.
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
			// Substituted "j$var96" with its value "var21".
			weekly_ut[t$var81][var21] = (exped[var21] * Avail[t$var81][var21]);
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			for(int j$var124 = 0; j$var124 <= noProducts; j$var124 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample22put128$global[t$var81][j$var124] = false;
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var96" with its value "var21".
			guard$sample22put128$global[t$var81][var21] = false;
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			for(int j$var124 = 0; j$var124 <= noProducts; j$var124 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample22put128$global[t$var81][j$var124]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample22put128$global[t$var81][j$var124] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$10 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$10 = (reduceVar$denom$10 + weekly_ut[t$var81][cv$reduction108Index]);
					weekly_rates[t$var81][j$var124] = (weekly_ut[t$var81][j$var124] / reduceVar$denom$10);
				}
			}
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var96" with its value "var21".
			if(!guard$sample22put128$global[t$var81][var21]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var96" with its value "var21".
				guard$sample22put128$global[t$var81][var21] = true;
				
				// Reduction of array weekly_ut
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$denom$11 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l's comment
					// Set the right hand term to a value from the array weekly_ut
					reduceVar$denom$11 = (reduceVar$denom$11 + weekly_ut[t$var81][cv$reduction108Index]);
				
				// Substituted "j$var124" with its value "j$var96".
				// 
				// Substituted "j$var96" with its value "var21".
				weekly_rates[t$var81][var21] = (weekly_ut[t$var81][var21] / reduceVar$denom$11);
			}
		}
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var9" with its value "10.0".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample22multinomial130$global[t$var81] = false;
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard$sample22multinomial130$global[t$var81]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample22multinomial130$global[t$var81] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 131 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 131 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$4$var128's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var81], weekly_rates[t$var81], (noProducts + 1), arrivals[t$var81]) + cv$accumulatedProbabilities);
			}
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard$sample22multinomial130$global[t$var81]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample22multinomial130$global[t$var81] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 131 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 131 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$7$var128's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var81], weekly_rates[t$var81], (noProducts + 1), arrivals[t$var81]) + cv$accumulatedProbabilities);
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
			ut[var21] = cv$originalValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 22 and consumer double[] 37.
			// 
			// Substituted "j$var34" with its value "var21".
			exped[var21] = Math.exp(ut[var21]);
			
			// Guards to ensure that weekly_ut is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 22 and consumer double[] 101.
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				// Substituted "j$var96" with its value "var21".
				weekly_ut[t$var81][var21] = (exped[var21] * Avail[t$var81][var21]);
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				for(int j$var124 = 0; j$var124 <= noProducts; j$var124 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample22put128$global[t$var81][j$var124] = false;
			}
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var96" with its value "var21".
				guard$sample22put128$global[t$var81][var21] = false;
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				for(int j$var124 = 0; j$var124 <= noProducts; j$var124 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample22put128$global[t$var81][j$var124]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample22put128$global[t$var81][j$var124] = true;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$13 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$13 = (reduceVar$denom$13 + weekly_ut[t$var81][cv$reduction108Index]);
						weekly_rates[t$var81][j$var124] = (weekly_ut[t$var81][j$var124] / reduceVar$denom$13);
					}
				}
			}
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var96" with its value "var21".
				if(!guard$sample22put128$global[t$var81][var21]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var96" with its value "var21".
					guard$sample22put128$global[t$var81][var21] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$14 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$14 = (reduceVar$denom$14 + weekly_ut[t$var81][cv$reduction108Index]);
					
					// Substituted "j$var124" with its value "j$var96".
					// 
					// Substituted "j$var96" with its value "var21".
					weekly_rates[t$var81][var21] = (weekly_ut[t$var81][var21] / reduceVar$denom$14);
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 54 drawn from Gamma 42. Inference was performed using a Gamma to
	// Poisson conjugate prior.
	private final void sample54(int var53, int threadID$cv$var53, Rng RNG$) {
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Variable to record the number of samples from consuming random variables.
		lambda[var53] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, arrivals[var53], 1);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 69 drawn from Poisson 68. Inference was performed using Metropolis-Hastings.
	private final void sample69(int t$var66, int threadID$cv$t$var66, Rng RNG$) {
		// The original value of the sample
		int cv$originalValue = arrivals[t$var66];
		
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
		// cv$temp$0$var67's comment
		// Constructing a random variable input for use later.
		// 
		// Set the current value to the current state of the tree.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 131 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// cv$temp$0$var67's comment
		// Constructing a random variable input for use later.
		// 
		// Set the current value to the current state of the tree.
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Processing sample task 131 of consumer random variable null.
		// 
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// Substituted "t$var81" with its value "t$var66".
		// 
		// cv$temp$2$$var1222's comment
		// 
		// $var1222's comment
		// Constructing a random variable input for use later.
		// 
		// cv$temp$3$var128's comment
		// Variable declaration of cv$temp$3$var128 moved.
		// 
		// Constructing a random variable input for use later.
		// 
		// Set the current value to the current state of the tree.
		double cv$originalProbability = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var66], weekly_rates[t$var66], (noProducts + 1), cv$originalValue) + DistributionSampling.logProbabilityPoisson(cv$originalValue, lambda[t$var66]));
		
		// Update Sample and intermediate values
		arrivals[t$var66] = cv$proposedValue;
		
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// cv$temp$0$var67's comment
		// Constructing a random variable input for use later.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 131 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// cv$temp$0$var67's comment
		// Constructing a random variable input for use later.
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Processing sample task 131 of consumer random variable null.
		// 
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// Substituted "t$var81" with its value "t$var66".
		// 
		// cv$temp$2$$var1222's comment
		// 
		// $var1222's comment
		// Constructing a random variable input for use later.
		// 
		// cv$temp$3$var128's comment
		// Variable declaration of cv$temp$3$var128 moved.
		// 
		// Constructing a random variable input for use later.
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var66], weekly_rates[t$var66], (noProducts + 1), cv$proposedValue) + DistributionSampling.logProbabilityPoisson(cv$proposedValue, lambda[t$var66]));
		
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
			arrivals[t$var66] = cv$originalValue;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for guard$sample22put128$global
		// 
		// Calculate the largest index of j that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_j$var124 = 0;
		if((0 < T))
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			cv$max_j$var124 = Math.max(0, (noProducts + 1));
		
		// Allocation of guard$sample22put128$global for single threaded execution
		// 
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample22put128$global = new boolean[Math.max(0, T)][cv$max_j$var124];
		
		// Allocation of guard$sample22multinomial130$global for single threaded execution
		// 
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample22multinomial130$global = new boolean[Math.max(0, T)];
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
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
			Sales[t$var81] = new int[noProducts];
		
		// Constructor for weekly_rates
		weekly_rates = new double[T][];
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
			weekly_rates[t$var81] = new double[(noProducts + 1)];
		
		// Constructor for weekly_ut
		weekly_ut = new double[T][];
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
			weekly_ut[t$var81] = new double[(noProducts + 1)];
		
		// If weekly_sales has not been set already allocate space.
		if(!setFlag$weekly_sales) {
			// Constructor for weekly_sales
			weekly_sales = new int[T][];
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				weekly_sales[t$var81] = new int[(noProducts + 1)];
		}
		
		// Constructor for logProbability$sample22
		logProbability$sample22 = new double[noProducts];
		
		// Constructor for logProbability$sample131
		logProbability$sample131 = new double[T];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample22) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							ut[var21] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var34, int forEnd$j$var34, int threadID$j$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var34 = forStart$j$var34; j$var34 < forEnd$j$var34; j$var34 += 1)
							exped[j$var34] = Math.exp(ut[j$var34]);
				}
			);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample54)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1)
							lambda[var53] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample69)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var66, int forEnd$t$var66, int threadID$t$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var66 = forStart$t$var66; t$var66 < forEnd$t$var66; t$var66 += 1)
							arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var66]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var81, int forEnd$index$t$var81, int threadID$index$t$var81, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var81 = forStart$index$t$var81; index$t$var81 < forEnd$index$t$var81; index$t$var81 += 1) {
						int t$var81 = index$t$var81;
						int threadID$t$var81 = threadID$index$t$var81;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample22)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var96, int forEnd$j$var96, int threadID$j$var96, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var96 = forStart$j$var96; j$var96 < forEnd$j$var96; j$var96 += 1)
											weekly_ut[t$var81][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
								}
							);

						weekly_ut[t$var81][noProducts] = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample22) {
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$15 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$15 = (reduceVar$denom$15 + weekly_ut[t$var81][cv$reduction108Index]);
							
							// Alternative name for reduceVar$denom$15 to make it effectively final.
							double reduceVar$denom$15$1 = reduceVar$denom$15;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var124, int forEnd$j$var124, int threadID$j$var124, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var124 = forStart$j$var124; j$var124 < forEnd$j$var124; j$var124 += 1)
											weekly_rates[t$var81][j$var124] = (weekly_ut[t$var81][j$var124] / reduceVar$denom$15$1);
								}
							);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample131) {
							DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[t$var81], (noProducts + 1), arrivals[t$var81], weekly_sales[t$var81]);
							int[] observed_weekly_sales = Sales[t$var81];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var140, int forEnd$j$var140, int threadID$j$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var140 = forStart$j$var140; j$var140 < forEnd$j$var140; j$var140 += 1)
											observed_weekly_sales[j$var140] = weekly_sales[t$var81][j$var140];
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
		if(!fixedFlag$sample22) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							ut[var21] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var34, int forEnd$j$var34, int threadID$j$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var34 = forStart$j$var34; j$var34 < forEnd$j$var34; j$var34 += 1)
							exped[j$var34] = Math.exp(ut[j$var34]);
				}
			);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample54)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1)
							lambda[var53] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample69)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var66, int forEnd$t$var66, int threadID$t$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var66 = forStart$t$var66; t$var66 < forEnd$t$var66; t$var66 += 1)
							arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var66]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var81, int forEnd$index$t$var81, int threadID$index$t$var81, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var81 = forStart$index$t$var81; index$t$var81 < forEnd$index$t$var81; index$t$var81 += 1) {
						int t$var81 = index$t$var81;
						int threadID$t$var81 = threadID$index$t$var81;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample22)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var96, int forEnd$j$var96, int threadID$j$var96, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var96 = forStart$j$var96; j$var96 < forEnd$j$var96; j$var96 += 1)
											weekly_ut[t$var81][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
								}
							);

						weekly_ut[t$var81][noProducts] = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample22) {
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$17 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$17 = (reduceVar$denom$17 + weekly_ut[t$var81][cv$reduction108Index]);
							
							// Alternative name for reduceVar$denom$17 to make it effectively final.
							double reduceVar$denom$17$1 = reduceVar$denom$17;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var124, int forEnd$j$var124, int threadID$j$var124, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var124 = forStart$j$var124; j$var124 < forEnd$j$var124; j$var124 += 1)
											weekly_rates[t$var81][j$var124] = (weekly_ut[t$var81][j$var124] / reduceVar$denom$17$1);
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
		if(!fixedFlag$sample22) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							ut[var21] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var34, int forEnd$j$var34, int threadID$j$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var34 = forStart$j$var34; j$var34 < forEnd$j$var34; j$var34 += 1)
							exped[j$var34] = Math.exp(ut[j$var34]);
				}
			);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample54)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1)
							lambda[var53] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample69)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var66, int forEnd$t$var66, int threadID$t$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var66 = forStart$t$var66; t$var66 < forEnd$t$var66; t$var66 += 1)
							arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var66]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var81, int forEnd$index$t$var81, int threadID$index$t$var81, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var81 = forStart$index$t$var81; index$t$var81 < forEnd$index$t$var81; index$t$var81 += 1) {
						int t$var81 = index$t$var81;
						int threadID$t$var81 = threadID$index$t$var81;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample22)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var96, int forEnd$j$var96, int threadID$j$var96, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var96 = forStart$j$var96; j$var96 < forEnd$j$var96; j$var96 += 1)
											weekly_ut[t$var81][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
								}
							);

						weekly_ut[t$var81][noProducts] = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample22) {
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$16 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$16 = (reduceVar$denom$16 + weekly_ut[t$var81][cv$reduction108Index]);
							
							// Alternative name for reduceVar$denom$16 to make it effectively final.
							double reduceVar$denom$16$1 = reduceVar$denom$16;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var124, int forEnd$j$var124, int threadID$j$var124, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var124 = forStart$j$var124; j$var124 < forEnd$j$var124; j$var124 += 1)
											weekly_rates[t$var81][j$var124] = (weekly_ut[t$var81][j$var124] / reduceVar$denom$16$1);
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
			if(!fixedFlag$sample22) {
				for(int var21 = 0; var21 < noProducts; var21 += 1)
					sample22(var21);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample54)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1)
								sample54(var53, threadID$var53, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample69)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var66, int forEnd$t$var66, int threadID$t$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int t$var66 = forStart$t$var66; t$var66 < forEnd$t$var66; t$var66 += 1)
								sample69(t$var66, threadID$t$var66, RNG$1);
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample69)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var66, int forEnd$t$var66, int threadID$t$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int t$var66 = forStart$t$var66; t$var66 < forEnd$t$var66; t$var66 += 1)
								sample69(t$var66, threadID$t$var66, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample54)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1)
								sample54(var53, threadID$var53, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample22) {
				for(int var21 = (noProducts - 1); var21 >= 0; var21 -= 1)
					sample22(var21);
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
		logProbability$var10 = 0.0;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$weekly_rates = 0.0;
		if(!fixedProbFlag$sample22) {
			for(int var21 = 0; var21 < noProducts; var21 += 1)
				logProbability$sample22[var21] = 0.0;
		}
		logProbability$var42 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample54)
			logProbability$var54 = 0.0;
		logProbability$var68 = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample69)
			logProbability$var69 = 0.0;
		logProbability$var129 = 0.0;
		logProbability$weekly_sales = 0.0;
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample131) {
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				logProbability$sample131[t$var81] = 0.0;
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
		if(fixedFlag$sample22)
			logProbabilityValue$sample22();
		if(fixedFlag$sample54)
			logProbabilityValue$sample54();
		if(fixedFlag$sample69)
			logProbabilityValue$sample69();
		logProbabilityValue$sample131();
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
		logProbabilityValue$sample22();
		logProbabilityValue$sample54();
		logProbabilityValue$sample69();
		logProbabilityValue$sample131();
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
		logProbabilityValue$sample22();
		logProbabilityValue$sample54();
		logProbabilityValue$sample69();
		logProbabilityValue$sample131();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample22) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							ut[var21] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var34, int forEnd$j$var34, int threadID$j$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var34 = forStart$j$var34; j$var34 < forEnd$j$var34; j$var34 += 1)
							exped[j$var34] = Math.exp(ut[j$var34]);
				}
			);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample54)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1)
							lambda[var53] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample69)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var66, int forEnd$t$var66, int threadID$t$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var66 = forStart$t$var66; t$var66 < forEnd$t$var66; t$var66 += 1)
							arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var66]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var81, int forEnd$index$t$var81, int threadID$index$t$var81, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var81 = forStart$index$t$var81; index$t$var81 < forEnd$index$t$var81; index$t$var81 += 1) {
						int t$var81 = index$t$var81;
						int threadID$t$var81 = threadID$index$t$var81;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample22)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var96, int forEnd$j$var96, int threadID$j$var96, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var96 = forStart$j$var96; j$var96 < forEnd$j$var96; j$var96 += 1)
											weekly_ut[t$var81][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
								}
							);

						weekly_ut[t$var81][noProducts] = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample22) {
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$18 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$18 = (reduceVar$denom$18 + weekly_ut[t$var81][cv$reduction108Index]);
							
							// Alternative name for reduceVar$denom$18 to make it effectively final.
							double reduceVar$denom$18$1 = reduceVar$denom$18;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var124, int forEnd$j$var124, int threadID$j$var124, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var124 = forStart$j$var124; j$var124 < forEnd$j$var124; j$var124 += 1)
											weekly_rates[t$var81][j$var124] = (weekly_ut[t$var81][j$var124] / reduceVar$denom$18$1);
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
		for(int t$var81 = (T - 1); t$var81 >= 0; t$var81 -= 1) {
			for(int j$var140 = (noProducts - 1); j$var140 >= 0; j$var140 -= 1)
				// Substituted "observed_weekly_sales" with its value "Sales[t$var81]".
				weekly_sales[t$var81][j$var140] = Sales[t$var81][j$var140];
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
				(int forStart$j$var34, int forEnd$j$var34, int threadID$j$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var34 = forStart$j$var34; j$var34 < forEnd$j$var34; j$var34 += 1)
							exped[j$var34] = Math.exp(ut[j$var34]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var81, int forEnd$index$t$var81, int threadID$index$t$var81, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var81 = forStart$index$t$var81; index$t$var81 < forEnd$index$t$var81; index$t$var81 += 1) {
						int t$var81 = index$t$var81;
						int threadID$t$var81 = threadID$index$t$var81;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(setFlag$ut)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var96, int forEnd$j$var96, int threadID$j$var96, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var96 = forStart$j$var96; j$var96 < forEnd$j$var96; j$var96 += 1)
											weekly_ut[t$var81][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
								}
							);

						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$19 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$19 = (reduceVar$denom$19 + weekly_ut[t$var81][cv$reduction108Index]);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(setFlag$ut) {
							// Alternative name for reduceVar$denom$19 to make it effectively final.
							double reduceVar$denom$19$1 = reduceVar$denom$19;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var124, int forEnd$j$var124, int threadID$j$var124, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var124 = forStart$j$var124; j$var124 < forEnd$j$var124; j$var124 += 1)
											weekly_rates[t$var81][j$var124] = (weekly_ut[t$var81][j$var124] / reduceVar$denom$19$1);
								}
							);
						}
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "/*\n"
		     + " * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n"
		     + " * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n"
		     + " * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n"
		     + " */\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model Vulcano2012notNormalized(int noProducts, int T, int s, int[][] ObsSales, int[][] Avail) {\n"
		     + "    // Avail is the availability matrix, T-by-noProducts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = gaussian(0, 10).sample(noProducts);\n"
		     + "\n"
		     + "    //exponentiate right here (in the non-basic models move to the for loop)\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "    exped[j] = exp(ut[j]);\n"
		     + "    }\n"
		     + "\n"
		     + "    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector if RGBU has some estimates, or just use some ad hoc priors\n"
		     + "    double[ ] lambda = gamma(10,10).sample(T);\n"
		     + "\n"
		     + "    // draw arrivals\n"
		     + "    int[] arrivals = new int[T];\n"
		     + "    for (int t : [0..T)){\n"
		     + "    arrivals[t]= poisson(lambda[t]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    int[][] Sales = new int[T][];\n"
		     + "\n"
		     + "    for (int t:[0..T)){\n"
		     + "        // for each period t calculate choice probabilities and sales\n"
		     + "\n"
		     + "        double[] weekly_rates = new double[noProducts+1];\n"
		     + "        double[] weekly_ut = new double[noProducts+1];\n"
		     + "\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_ut[j] = exped[j]*Avail[t][j] ;\n"
		     + "        }\n"
		     + "        // add outside option value (which is always available)\n"
		     + "        weekly_ut[noProducts] = 1.0;\n"
		     + "        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "        for (int j : [0..noProducts]) {\n"
		     + "            weekly_rates[j] = weekly_ut[j]/denom ;\n"
		     + "        }\n"
		     + "\n"
		     + "        int[] weekly_sales = multinomial(weekly_rates, arrivals[t]).sample();\n"
		     + "\n"
		     + "        //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n"
		     + "        int[] observed_weekly_sales = new int[noProducts];\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            observed_weekly_sales[j] = weekly_sales[j] ;\n"
		     + "        }\n"
		     + "\n"
		     + "        // record sales for period t\n"
		     + "        Sales[t] = observed_weekly_sales;\n"
		     + "\n"
		     + "    }\n"
		     + "    // assert that generated sales match observed sales\n"
		     + "    Sales.observe(ObsSales);\n"
		     + "}";
	}
}