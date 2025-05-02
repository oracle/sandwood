package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basic$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Vulcano2012basic$CoreInterface {
	
	// Declare the variables for the model.
	private int[][] Avail;
	private int[][] ObsSales;
	private int[][] Sales;
	private int T;
	private double[] exped;
	private double[] expedNorm;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedProbFlag$sample157 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean[] guard$sample26multinomial156$global;
	private boolean[][] guard$sample26put131$global;
	private boolean[][] guard$sample26put154$global;
	private boolean[] guard$sample26put68$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$exped;
	private double logProbability$expedNorm;
	private double[] logProbability$sample26;
	private double logProbability$sum;
	private double logProbability$ut;
	private double logProbability$var152;
	private double logProbability$var25;
	private double logProbability$weekly_rates;
	private double logProbability$weekly_sales;
	private double logProbability$weekly_ut;
	private int noProducts;
	private double r;
	private int[] sales_sum;
	private double sum;
	private boolean system$gibbsForward = true;
	private double[] ut;
	private double[][] weekly_rates;
	private double[][] weekly_ut;

	public Vulcano2012basic$MultiThreadCPU(ExecutionTarget target) {
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
		// Set Avail
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
		// Set ObsSales
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

	// Getter for exped.
	@Override
	public final double[] get$exped() {
		return exped;
	}

	// Getter for expedNorm.
	@Override
	public final double[] get$expedNorm() {
		return expedNorm;
	}

	// Getter for fixedFlag$sample26.
	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	// Setter for fixedFlag$sample26.
	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample26 including if probabilities
		// need to be updated.
		fixedFlag$sample26 = cv$value;
		
		// Should the probability of sample 26 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample26" with its value "cv$value".
		fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
		
		// Should the probability of sample 157 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample26" with its value "cv$value".
		fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
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

	// Getter for logProbability$exped.
	@Override
	public final double get$logProbability$exped() {
		return logProbability$exped;
	}

	// Getter for logProbability$expedNorm.
	@Override
	public final double get$logProbability$expedNorm() {
		return logProbability$expedNorm;
	}

	// Getter for logProbability$sum.
	@Override
	public final double get$logProbability$sum() {
		return logProbability$sum;
	}

	// Getter for logProbability$ut.
	@Override
	public final double get$logProbability$ut() {
		return logProbability$ut;
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

	// Getter for r.
	@Override
	public final double get$r() {
		return r;
	}

	// Setter for r.
	@Override
	public final void set$r(double cv$value) {
		r = cv$value;
	}

	// Getter for sales_sum.
	@Override
	public final int[] get$sales_sum() {
		return sales_sum;
	}

	// Getter for sum.
	@Override
	public final double get$sum() {
		return sum;
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
		
		// Unset the fixed probability flag for sample 26 as it depends on ut.
		fixedProbFlag$sample26 = false;
		
		// Unset the fixed probability flag for sample 157 as it depends on ut.
		fixedProbFlag$sample157 = false;
	}

	// Calculate the probability of the samples represented by sample157 using sampled
	// values.
	private final void logProbabilityValue$sample157() {
		// Determine if we need to calculate the values for sample task 157 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample157) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				logProbability$var152 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$weekly_sales = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$Sales = (logProbability$Sales + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample157 = fixedFlag$sample26;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if((0 < T))
				// Record that the sample was reached.
				cv$sampleReached = true;
			if(cv$sampleReached)
				logProbability$var152 = logProbability$weekly_sales;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$Sales = (logProbability$Sales + logProbability$weekly_sales);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$weekly_sales);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$weekly_sales);
		}
	}

	// Calculate the probability of the samples represented by sample26 using sampled
	// values.
	private final void logProbabilityValue$sample26() {
		// Determine if we need to calculate the values for sample task 26 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample26) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
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
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[j$var20] / 1.4142135623730951)) - 0.34657359027997264);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample26[(j$var20 - 1)] = cv$distributionAccumulator;
				
				// Update the variable probability
				logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
				
				// Update the variable probability
				logProbability$sum = (logProbability$sum + cv$distributionAccumulator);
				
				// Update the variable probability
				logProbability$expedNorm = (logProbability$expedNorm + cv$distributionAccumulator);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < T)) {
					// Update the variable probability
					logProbability$weekly_ut = (logProbability$weekly_ut + cv$distributionAccumulator);
					
					// Update the variable probability
					logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
				}
			}
			if(cv$sampleReached)
				logProbability$var25 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample26)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample26 = fixedFlag$sample26;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
				double cv$sampleValue = logProbability$sample26[(j$var20 - 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Update the variable probability
				logProbability$exped = (logProbability$exped + cv$sampleValue);
				
				// Update the variable probability
				logProbability$sum = (logProbability$sum + cv$sampleValue);
				
				// Update the variable probability
				logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 < T)) {
					// Update the variable probability
					logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
					
					// Update the variable probability
					logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
				}
			}
			if(cv$sampleReached)
				logProbability$var25 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 26 drawn from Gaussian 25. Inference was performed using Metropolis-Hastings.
	private final void sample26(int j$var20) {
		// The original value of the sample
		double cv$originalValue = ut[j$var20];
		
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
			// Substituted "cv$temp$1$var24" with its value "2.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 1.4142135623730951)) - 0.34657359027997264);
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample26multinomial156$global[t$var112] = false;
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample26multinomial156$global[t$var112]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample26multinomial156$global[t$var112] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 157 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 157 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$4$var151" with its value "sales_sum[t$var112]".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				// Substituted "j$var123" with its value "j$var63".
				if(!guard$sample26multinomial156$global[t$var112]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample26multinomial156$global[t$var112] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 157 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 157 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$7$var151" with its value "sales_sum[t$var112]".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample26multinomial156$global[t$var112]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample26multinomial156$global[t$var112] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 157 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 157 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$10$var151" with its value "sales_sum[t$var112]".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample26multinomial156$global[t$var112])
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 157 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 157 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$13$var151" with its value "sales_sum[t$var112]".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
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
		ut[j$var20] = cv$proposedValue;
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 26 and consumer double[] 41.
		// 
		// Substituted "j$var38" with its value "j$var20".
		exped[j$var20] = Math.exp(ut[j$var20]);
		
		// Guards to ensure that sum is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 26 and consumer double 52.
		// 
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$9 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// l$var50's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction46Index]);
		
		// Write out the new sample value.
		sum = reduceVar$sum$9;
		
		// Guards to ensure that expedNorm is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 26 and consumer double[] 67.
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample26put68$global[j$var63] = false;
		
		// Set the flags to false
		// 
		// Guard to check that at most one copy of the code is executed for a given random
		// variable instance.
		// 
		// Substituted "j$var63" with its value "j$var20".
		guard$sample26put68$global[j$var20] = false;
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			if(!guard$sample26put68$global[j$var63]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample26put68$global[j$var63] = true;
				
				// sum's comment
				// Write out the new sample value.
				expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$9));
			}
		}
		
		// Substituted "j$var38" with its value "j$var20".
		// 
		// Substituted "j$var63" with its value "j$var20".
		if(!guard$sample26put68$global[j$var20]) {
			// The body will execute, so should not be executed again
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var63" with its value "j$var20".
			guard$sample26put68$global[j$var20] = true;
			
			// Substituted "j$var63" with its value "j$var20".
			// 
			// sum's comment
			// Write out the new sample value.
			expedNorm[j$var20] = (exped[j$var20] / (r * reduceVar$sum$9));
		}
		
		// Guards to ensure that weekly_ut is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 26 and consumer double[] 128.
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample26put131$global[t$var112][j$var63] = false;
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var63" with its value "j$var20".
			guard$sample26put131$global[t$var112][j$var20] = false;
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample26put131$global[t$var112][j$var63]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample26put131$global[t$var112][j$var63] = true;
					
					// Substituted "j$var123" with its value "j$var63".
					weekly_ut[t$var112][j$var63] = (expedNorm[j$var63] * Avail[t$var112][j$var63]);
				}
			}
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var63" with its value "j$var20".
			if(!guard$sample26put131$global[t$var112][j$var20]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var63" with its value "j$var20".
				guard$sample26put131$global[t$var112][j$var20] = true;
				
				// Substituted "j$var123" with its value "j$var63".
				// 
				// Substituted "j$var63" with its value "j$var20".
				weekly_ut[t$var112][j$var20] = (expedNorm[j$var20] * Avail[t$var112][j$var20]);
			}
		}
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var147" with its value "j$var63".
				guard$sample26put154$global[t$var112][j$var63] = false;
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample26put154$global[t$var112][j$var147] = false;
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// j$var147's comment
			// Substituted "j$var63" with its value "j$var20".
			guard$sample26put154$global[t$var112][j$var20] = false;
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample26put154$global[t$var112][j$var147]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample26put154$global[t$var112][j$var147] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$15 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var136's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$15 = (reduceVar$denom$15 + weekly_ut[t$var112][cv$reduction136Index]);
					weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$15);
				}
			}
		}
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var147" with its value "j$var63".
				if(!guard$sample26put154$global[t$var112][j$var63]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var147" with its value "j$var63".
					guard$sample26put154$global[t$var112][j$var63] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$16 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var136's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$16 = (reduceVar$denom$16 + weekly_ut[t$var112][cv$reduction136Index]);
					
					// Substituted "j$var147" with its value "j$var63".
					weekly_rates[t$var112][j$var63] = (weekly_ut[t$var112][j$var63] / reduceVar$denom$16);
				}
			}
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample26put154$global[t$var112][j$var147]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample26put154$global[t$var112][j$var147] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$17 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var136's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$17 = (reduceVar$denom$17 + weekly_ut[t$var112][cv$reduction136Index]);
					weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$17);
				}
			}
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// j$var147's comment
			// Substituted "j$var63" with its value "j$var20".
			if(!guard$sample26put154$global[t$var112][j$var20]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// j$var147's comment
				// Substituted "j$var63" with its value "j$var20".
				guard$sample26put154$global[t$var112][j$var20] = true;
				
				// Reduction of array weekly_ut
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$denom$18 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var136's comment
					// Set the right hand term to a value from the array weekly_ut
					reduceVar$denom$18 = (reduceVar$denom$18 + weekly_ut[t$var112][cv$reduction136Index]);
				
				// j$var147's comment
				// Substituted "j$var63" with its value "j$var20".
				weekly_rates[t$var112][j$var20] = (weekly_ut[t$var112][j$var20] / reduceVar$denom$18);
			}
		}
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var24" with its value "2.0".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 1.4142135623730951)) - 0.34657359027997264);
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample26multinomial156$global[t$var112] = false;
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard$sample26multinomial156$global[t$var112]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample26multinomial156$global[t$var112] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 157 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 157 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$4$var151" with its value "sales_sum[t$var112]".
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
			}
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			// Substituted "j$var123" with its value "j$var63".
			if(!guard$sample26multinomial156$global[t$var112]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample26multinomial156$global[t$var112] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 157 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 157 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$7$var151" with its value "sales_sum[t$var112]".
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
			}
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard$sample26multinomial156$global[t$var112]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample26multinomial156$global[t$var112] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 157 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 157 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$10$var151" with its value "sales_sum[t$var112]".
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
			}
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard$sample26multinomial156$global[t$var112]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample26multinomial156$global[t$var112] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 157 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 157 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$13$var151" with its value "sales_sum[t$var112]".
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
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
			ut[j$var20] = cv$originalValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 26 and consumer double[] 41.
			// 
			// Substituted "j$var38" with its value "j$var20".
			exped[j$var20] = Math.exp(ut[j$var20]);
			
			// Guards to ensure that sum is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 26 and consumer double 52.
			// 
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$12 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l$var50's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$12 = (reduceVar$sum$12 + exped[cv$reduction46Index]);
			
			// Write out the new sample value.
			sum = reduceVar$sum$12;
			
			// Guards to ensure that expedNorm is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 26 and consumer double[] 67.
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample26put68$global[j$var63] = false;
			
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var63" with its value "j$var20".
			guard$sample26put68$global[j$var20] = false;
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample26put68$global[j$var63]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample26put68$global[j$var63] = true;
					
					// sum's comment
					// Write out the new sample value.
					expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$12));
				}
			}
			
			// Substituted "j$var38" with its value "j$var20".
			// 
			// Substituted "j$var63" with its value "j$var20".
			if(!guard$sample26put68$global[j$var20]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var63" with its value "j$var20".
				guard$sample26put68$global[j$var20] = true;
				
				// Substituted "j$var63" with its value "j$var20".
				// 
				// sum's comment
				// Write out the new sample value.
				expedNorm[j$var20] = (exped[j$var20] / (r * reduceVar$sum$12));
			}
			
			// Guards to ensure that weekly_ut is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 26 and consumer double[] 128.
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
				for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample26put131$global[t$var112][j$var63] = false;
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var63" with its value "j$var20".
				guard$sample26put131$global[t$var112][j$var20] = false;
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
				for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample26put131$global[t$var112][j$var63]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample26put131$global[t$var112][j$var63] = true;
						
						// Substituted "j$var123" with its value "j$var63".
						weekly_ut[t$var112][j$var63] = (expedNorm[j$var63] * Avail[t$var112][j$var63]);
					}
				}
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var63" with its value "j$var20".
				if(!guard$sample26put131$global[t$var112][j$var20]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var63" with its value "j$var20".
					guard$sample26put131$global[t$var112][j$var20] = true;
					
					// Substituted "j$var123" with its value "j$var63".
					// 
					// Substituted "j$var63" with its value "j$var20".
					weekly_ut[t$var112][j$var20] = (expedNorm[j$var20] * Avail[t$var112][j$var20]);
				}
			}
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
				for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var147" with its value "j$var63".
					guard$sample26put154$global[t$var112][j$var63] = false;
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample26put154$global[t$var112][j$var147] = false;
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// j$var147's comment
				// Substituted "j$var63" with its value "j$var20".
				guard$sample26put154$global[t$var112][j$var20] = false;
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample26put154$global[t$var112][j$var147]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample26put154$global[t$var112][j$var147] = true;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$21 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l$var136's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$21 = (reduceVar$denom$21 + weekly_ut[t$var112][cv$reduction136Index]);
						weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$21);
					}
				}
			}
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
				for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var147" with its value "j$var63".
					if(!guard$sample26put154$global[t$var112][j$var63]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "j$var147" with its value "j$var63".
						guard$sample26put154$global[t$var112][j$var63] = true;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$22 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l$var136's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$22 = (reduceVar$denom$22 + weekly_ut[t$var112][cv$reduction136Index]);
						
						// Substituted "j$var147" with its value "j$var63".
						weekly_rates[t$var112][j$var63] = (weekly_ut[t$var112][j$var63] / reduceVar$denom$22);
					}
				}
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample26put154$global[t$var112][j$var147]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample26put154$global[t$var112][j$var147] = true;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$23 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l$var136's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$23 = (reduceVar$denom$23 + weekly_ut[t$var112][cv$reduction136Index]);
						weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$23);
					}
				}
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// j$var147's comment
				// Substituted "j$var63" with its value "j$var20".
				if(!guard$sample26put154$global[t$var112][j$var20]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// j$var147's comment
					// Substituted "j$var63" with its value "j$var20".
					guard$sample26put154$global[t$var112][j$var20] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$24 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var136's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$24 = (reduceVar$denom$24 + weekly_ut[t$var112][cv$reduction136Index]);
					
					// j$var147's comment
					// Substituted "j$var63" with its value "j$var20".
					weekly_rates[t$var112][j$var20] = (weekly_ut[t$var112][j$var20] / reduceVar$denom$24);
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
		// Allocation of guard$sample26put68$global for single threaded execution
		// 
		// Calculate the largest index of j that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample26put68$global = new boolean[Math.max(0, noProducts)];
		
		// Constructor for guard$sample26put131$global
		// 
		// Calculate the largest index of j that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_j$var123 = 0;
		if((0 < T))
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			cv$max_j$var123 = Math.max(0, noProducts);
		
		// Allocation of guard$sample26put131$global for single threaded execution
		// 
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample26put131$global = new boolean[Math.max(0, T)][cv$max_j$var123];
		
		// Constructor for guard$sample26put154$global
		// 
		// Calculate the largest index of j that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_j$var147 = 0;
		if((0 < T))
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			cv$max_j$var147 = Math.max(0, noProducts);
		
		// Allocation of guard$sample26put154$global for single threaded execution
		// 
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample26put154$global = new boolean[Math.max(0, T)][cv$max_j$var147];
		
		// Allocation of guard$sample26multinomial156$global for single threaded execution
		// 
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample26multinomial156$global = new boolean[Math.max(0, T)];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If ut has not been set already allocate space.
		if(!fixedFlag$sample26)
			// Constructor for ut
			ut = new double[noProducts];
		
		// Constructor for exped
		exped = new double[noProducts];
		
		// Constructor for expedNorm
		expedNorm = new double[noProducts];
		
		// Constructor for sales_sum
		sales_sum = new int[T];
		
		// Constructor for Sales
		Sales = new int[T][];
		for(int var100 = 0; var100 < T; var100 += 1)
			Sales[var100] = new int[noProducts];
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
			Sales[t$var112] = new int[noProducts];
		
		// Constructor for weekly_rates
		weekly_rates = new double[T][];
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
			weekly_rates[t$var112] = new double[noProducts];
		
		// Constructor for weekly_ut
		weekly_ut = new double[T][];
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
			weekly_ut[t$var112] = new double[noProducts];
		
		// Constructor for logProbability$sample26
		logProbability$sample26 = new double[(noProducts - 1)];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
							exped[j$var38] = Math.exp(ut[j$var38]);
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$13 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l$var50's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$13 = (reduceVar$sum$13 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$13;
			
			// Alternative name for reduceVar$sum$13 to make it effectively final.
			double reduceVar$sum$13$1 = reduceVar$sum$13;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
							// Substituted "sum" with its value "reduceVar$sum$13".
							expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$13$1));
				}
			);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
						int t$var112 = index$t$var112;
						int threadID$t$var112 = threadID$index$t$var112;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample26) {
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
											weekly_ut[t$var112][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
								}
							);
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$25 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l$var136's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$25 = (reduceVar$denom$25 + weekly_ut[t$var112][cv$reduction136Index]);
							
							// Alternative name for reduceVar$denom$25 to make it effectively final.
							double reduceVar$denom$25$2 = reduceVar$denom$25;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
											weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$25$2);
								}
							);
						}
						
						// Substituted "weekly_sales" with its value "Sales[t$var112]".
						DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[t$var112], noProducts, sales_sum[t$var112], Sales[t$var112]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
							exped[j$var38] = Math.exp(ut[j$var38]);
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$15 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l$var50's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$15 = (reduceVar$sum$15 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$15;
			
			// Alternative name for reduceVar$sum$15 to make it effectively final.
			double reduceVar$sum$15$1 = reduceVar$sum$15;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
							// Substituted "sum" with its value "reduceVar$sum$15".
							expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$15$1));
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
							int t$var112 = index$t$var112;
							int threadID$t$var112 = threadID$index$t$var112;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
											weekly_ut[t$var112][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
								}
							);
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$27 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l$var136's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$27 = (reduceVar$denom$27 + weekly_ut[t$var112][cv$reduction136Index]);
							
							// Alternative name for reduceVar$denom$27 to make it effectively final.
							double reduceVar$denom$27$2 = reduceVar$denom$27;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
											weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$27$2);
								}
							);
						}
				}
			);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
							exped[j$var38] = Math.exp(ut[j$var38]);
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$14 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l$var50's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$14 = (reduceVar$sum$14 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$14;
			
			// Alternative name for reduceVar$sum$14 to make it effectively final.
			double reduceVar$sum$14$1 = reduceVar$sum$14;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
							// Substituted "sum" with its value "reduceVar$sum$14".
							expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$14$1));
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
							int t$var112 = index$t$var112;
							int threadID$t$var112 = threadID$index$t$var112;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
											weekly_ut[t$var112][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
								}
							);
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$26 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l$var136's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$26 = (reduceVar$denom$26 + weekly_ut[t$var112][cv$reduction136Index]);
							
							// Alternative name for reduceVar$denom$26 to make it effectively final.
							double reduceVar$denom$26$2 = reduceVar$denom$26;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
											weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$26$2);
								}
							);
						}
				}
			);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(!fixedFlag$sample26) {
			// Infer the samples in chronological order.
			if(system$gibbsForward) {
				for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1)
					sample26(j$var20);
			}
			// Infer the samples in reverse chronological order.
			else {
				for(int j$var20 = (noProducts - 1); j$var20 >= 1; j$var20 -= 1)
					sample26(j$var20);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		ut[0] = 0.0;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1) {
						// Reduction of array week_sales
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						int reduceVar$var88$1 = 0;
						
						// For each index in the array to be reduced
						for(int cv$reduction84Index = 0; cv$reduction84Index < ObsSales[t$var78].length; cv$reduction84Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l$var86's comment
							// Set the right hand term to a value from the array week_sales
							reduceVar$var88$1 = (reduceVar$var88$1 + ObsSales[t$var78][cv$reduction84Index]);
						sales_sum[t$var78] = reduceVar$var88$1;
					}
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
		logProbability$var25 = Double.NaN;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$sum = 0.0;
		logProbability$expedNorm = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$weekly_rates = 0.0;
		if(!fixedProbFlag$sample26) {
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1)
				logProbability$sample26[(j$var20 - 1)] = Double.NaN;
		}
		logProbability$var152 = Double.NaN;
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample157)
			logProbability$weekly_sales = Double.NaN;
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
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		logProbabilityValue$sample157();
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
		logProbabilityValue$sample26();
		logProbabilityValue$sample157();
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
		logProbabilityValue$sample26();
		logProbabilityValue$sample157();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
							exped[j$var38] = Math.exp(ut[j$var38]);
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$16 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l$var50's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$16 = (reduceVar$sum$16 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$16;
			
			// Alternative name for reduceVar$sum$16 to make it effectively final.
			double reduceVar$sum$16$1 = reduceVar$sum$16;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
							// Substituted "sum" with its value "reduceVar$sum$16".
							expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$16$1));
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
							int t$var112 = index$t$var112;
							int threadID$t$var112 = threadID$index$t$var112;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
											weekly_ut[t$var112][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
								}
							);
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$28 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l$var136's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$28 = (reduceVar$denom$28 + weekly_ut[t$var112][cv$reduction136Index]);
							
							// Alternative name for reduceVar$denom$28 to make it effectively final.
							double reduceVar$denom$28$2 = reduceVar$denom$28;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
											weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$28$2);
								}
							);
						}
				}
			);
		}
		
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
		int cv$length1 = Sales.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = ObsSales[cv$index1];
			int[] cv$target2 = Sales[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample26) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
							exped[j$var38] = Math.exp(ut[j$var38]);
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$17 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l$var50's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$17 = (reduceVar$sum$17 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$17;
			
			// Alternative name for reduceVar$sum$17 to make it effectively final.
			double reduceVar$sum$17$1 = reduceVar$sum$17;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
							// Substituted "sum" with its value "reduceVar$sum$17".
							expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$17$1));
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
							int t$var112 = index$t$var112;
							int threadID$t$var112 = threadID$index$t$var112;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
											weekly_ut[t$var112][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
								}
							);
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$29 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l$var136's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$29 = (reduceVar$denom$29 + weekly_ut[t$var112][cv$reduction136Index]);
							
							// Alternative name for reduceVar$denom$29 to make it effectively final.
							double reduceVar$denom$29$2 = reduceVar$denom$29;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
											weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$29$2);
								}
							);
						}
				}
			);
		}
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "/*\n"
		     + " * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n"
		     + " * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n"
		     + " * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model Vulcano2012basic(int noProducts, int T, int[][] ObsSales, int[][] Avail, double r) {\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = new double[noProducts];\n"
		     + "    ut[0] = 0.0;  //the first one is fixed so that we can normalize the sum to be equal 1/r\n"
		     + "    for(int j : [1..noProducts)) {\n"
		     + "        ut[j] = gaussian(0, 2).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    //exponentiate right here (in the non-basic models move to the for loop)\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "        exped[j] = exp(ut[j]);\n"
		     + "    }\n"
		     + "\n"
		     + "    double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "    //now normalize\n"
		     + "    double[] expedNorm = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "        expedNorm[j] = exped[j]/(r*sum);\n"
		     + "    }\n"
		     + "\n"
		     + "    int[] sales_sum = new int[T];\n"
		     + "    for (int t : [0..T)){\n"
		     + "        int[] week_sales = ObsSales[t];\n"
		     + "        sales_sum[t] = reduce(week_sales, 0, (k, l) -> { return k + l; });\n"
		     + "    }\n"
		     + "\n"
		     + "    int[][] Sales = new int[T][noProducts];\n"
		     + "\n"
		     + "    for (int t:[0..T)){\n"
		     + "        // for each period t calculate choice probabilities and sales\n"
		     + "\n"
		     + "        double[] weekly_rates = new double[noProducts];\n"
		     + "        double[] weekly_ut = new double[noProducts];\n"
		     + "\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_ut[j] = expedNorm[j]*Avail[t][j] ;\n"
		     + "        }\n"
		     + "        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_rates[j] = weekly_ut[j]/denom ;\n"
		     + "        }\n"
		     + "\n"
		     + "        int[] weekly_sales = multinomial(weekly_rates, sales_sum[t]).sample();\n"
		     + "\n"
		     + "        // record sales for period t\n"
		     + "        Sales[t] = weekly_sales;\n"
		     + "\n"
		     + "                                }\n"
		     + "    // assert that generated sales match observed sales\n"
		     + "    Sales.observe(ObsSales);\n"
		     + "\n"
		     + "}";
	}
}