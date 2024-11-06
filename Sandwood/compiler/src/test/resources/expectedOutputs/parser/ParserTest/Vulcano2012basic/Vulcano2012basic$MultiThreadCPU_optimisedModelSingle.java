package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basic$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Vulcano2012basic$CoreInterface {
	
	// Declare the variables for the model.
	private int[][] Avail;
	private double[][] ObsSales;
	private double[][] Sales;
	private int T;
	private int[] arrivals;
	private double denom;
	private double[] exped;
	private boolean fixedFlag$sample25 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample61 = false;
	private boolean fixedFlag$sample85 = false;
	private boolean fixedProbFlag$sample25 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample61 = false;
	private boolean fixedProbFlag$sample85 = false;
	private boolean[][] guard$sample25gaussian84$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$arrivals;
	private double logProbability$denom;
	private double logProbability$exped;
	private double logProbability$lambda;
	private double[] logProbability$sample25;
	private double logProbability$sum;
	private double logProbability$ut;
	private double logProbability$var18;
	private double logProbability$var45;
	private double logProbability$var50;
	private double logProbability$var57;
	private double logProbability$var58;
	private double logProbability$var81;
	private double logProbability$var82;
	private int noProducts;
	private int s;
	private boolean setFlag$Sales = false;
	private boolean setFlag$arrivals = false;
	private boolean setFlag$exped = false;
	private boolean setFlag$lambda = false;
	private boolean setFlag$ut = false;
	private double sum;
	private boolean system$gibbsForward = true;
	private double[] ut;

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
		// Set Avail with flag to mark that it has been set so another array doesn't need
		// to be constructed
		Avail = cv$value;
	}

	// Getter for ObsSales.
	@Override
	public final double[][] get$ObsSales() {
		return ObsSales;
	}

	// Setter for ObsSales.
	@Override
	public final void set$ObsSales(double[][] cv$value) {
		// Set ObsSales with flag to mark that it has been set so another array doesn't need
		// to be constructed
		ObsSales = cv$value;
	}

	// Getter for Sales.
	@Override
	public final double[][] get$Sales() {
		return Sales;
	}

	// Setter for Sales.
	@Override
	public final void set$Sales(double[][] cv$value) {
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

	// Getter for denom.
	@Override
	public final double get$denom() {
		return denom;
	}

	// Setter for denom.
	@Override
	public final void set$denom(double cv$value) {
		denom = cv$value;
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
		
		// Should the probability of sample 85 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample25" with its value "cv$value".
		fixedProbFlag$sample85 = (cv$value && fixedProbFlag$sample85);
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
		// 
		// Substituted "fixedFlag$sample53" with its value "cv$value".
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		
		// Should the probability of sample 61 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample53" with its value "cv$value".
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
	}

	// Getter for fixedFlag$sample61.
	@Override
	public final boolean get$fixedFlag$sample61() {
		return fixedFlag$sample61;
	}

	// Setter for fixedFlag$sample61.
	@Override
	public final void set$fixedFlag$sample61(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample61 including if probabilities
		// need to be updated.
		fixedFlag$sample61 = cv$value;
		
		// Should the probability of sample 61 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample61" with its value "cv$value".
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
		
		// Should the probability of sample 85 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample61" with its value "cv$value".
		fixedProbFlag$sample85 = (cv$value && fixedProbFlag$sample85);
	}

	// Getter for fixedFlag$sample85.
	@Override
	public final boolean get$fixedFlag$sample85() {
		return fixedFlag$sample85;
	}

	// Setter for fixedFlag$sample85.
	@Override
	public final void set$fixedFlag$sample85(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample85 including if probabilities
		// need to be updated.
		fixedFlag$sample85 = cv$value;
		
		// Should the probability of sample 85 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample85" with its value "cv$value".
		fixedProbFlag$sample85 = (cv$value && fixedProbFlag$sample85);
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

	// Getter for logProbability$denom.
	@Override
	public final double get$logProbability$denom() {
		return logProbability$denom;
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

	// Getter for sum.
	@Override
	public final double get$sum() {
		return sum;
	}

	// Setter for sum.
	@Override
	public final void set$sum(double cv$value) {
		sum = cv$value;
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
				
				// Looking for a path between Sample 25 and consumer double 39.
				// 
				// Update the variable probability
				logProbability$sum = (logProbability$sum + cv$distributionAccumulator);
				
				// Update the variable probability
				logProbability$denom = (logProbability$denom + cv$distributionAccumulator);
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
				
				// Looking for a path between Sample 25 and consumer double 39.
				// 
				// Update the variable probability
				logProbability$sum = (logProbability$sum + cv$sampleValue);
				
				// Update the variable probability
				logProbability$denom = (logProbability$denom + cv$sampleValue);
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

	// Calculate the probability of the samples represented by sample53 using sampled
	// values.
	private final void logProbabilityValue$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample53) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var49 = 0; var49 < T; var49 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGamma(lambda[var49], 10.0, 10.0));
			logProbability$var45 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var50 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample53)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample53 = fixedFlag$sample53;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var45 = logProbability$var50;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$lambda = (logProbability$lambda + logProbability$var50);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var50);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample53)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var50);
		}
	}

	// Calculate the probability of the samples represented by sample61 using sampled
	// values.
	private final void logProbabilityValue$sample61() {
		// Determine if we need to calculate the values for sample task 61 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample61) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int t$var55 = 0; t$var55 < T; t$var55 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityPoisson(arrivals[t$var55], lambda[t$var55]));
			logProbability$var57 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var58 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample61)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample61 = (fixedFlag$sample61 && fixedFlag$sample53);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var57 = logProbability$var58;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$arrivals = (logProbability$arrivals + logProbability$var58);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var58);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample61)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var58);
		}
	}

	// Calculate the probability of the samples represented by sample85 using sampled
	// values.
	private final void logProbabilityValue$sample85() {
		// Determine if we need to calculate the values for sample task 85 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample85) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
				for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
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
					cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - (((exped[j$var72] * Avail[t$var68][j$var72]) / denom) * arrivals[t$var68])) / 0.4472135954999579))) + 0.8047189562170501);
			}
			logProbability$var81 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var82 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample85 = ((fixedFlag$sample85 && fixedFlag$sample25) && fixedFlag$sample61);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var81 = logProbability$var82;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$Sales = (logProbability$Sales + logProbability$var82);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var82);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var82);
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
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
				for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample25gaussian84$global[t$var68][j$var72] = false;
			}
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var72" with its value "var22".
				guard$sample25gaussian84$global[t$var68][var22] = false;
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$9 = 0.0;
			
			// Reduce for every value except a masked value which will be skipped.
			// 
			// Substituted "j$var28" with its value "var22".
			for(int cv$reduction471Index = 0; cv$reduction471Index < var22; cv$reduction471Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction471Index]);
			
			// Substituted "j$var28" with its value "var22".
			for(int cv$reduction471Index = (var22 + 1); cv$reduction471Index < noProducts; cv$reduction471Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction471Index]);
			
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// Set the current value to the current state of the tree.
			reduceVar$sum$9 = (Math.exp(cv$originalValue) + reduceVar$sum$9);
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
				for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample25gaussian84$global[t$var68][j$var72]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample25gaussian84$global[t$var68][j$var72] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 85 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// This value is not used before it is set again, so removing the value declaration.
						// 
						// Processing sample task 85 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "cv$temp$3$var80" with its value "0.2".
						// 
						// cv$temp$2$var79's comment
						// Variable declaration of cv$temp$2$var79 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - (((exped[j$var72] * Avail[t$var68][j$var72]) / (reduceVar$sum$9 / s)) * arrivals[t$var68])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
					}
				}
			}
			
			// Set the current value to the current state of the tree.
			double traceTempVariable$var73$7_3 = Math.exp(cv$originalValue);
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var72" with its value "var22".
				if(!guard$sample25gaussian84$global[t$var68][var22])
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 85 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Processing sample task 85 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$5$var80" with its value "0.2".
					// 
					// Substituted "j$var72" with its value "var22".
					// 
					// cv$temp$4$var79's comment
					// Variable declaration of cv$temp$4$var79 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var72" with its value "var22".
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var68][var22] - (((traceTempVariable$var73$7_3 * Avail[t$var68][var22]) / denom) * arrivals[t$var68])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
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
		
		// Guards to ensure that sum is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 25 and consumer double 39.
		// 
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$8 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction414Index = 0; cv$reduction414Index < noProducts; cv$reduction414Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// l's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$8 = (reduceVar$sum$8 + exped[cv$reduction414Index]);
		
		// Write out the new sample value.
		sum = reduceVar$sum$8;
		
		// Write out the new sample value.
		// 
		// sum's comment
		// Write out the new sample value.
		denom = (reduceVar$sum$8 / s);
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var17" with its value "10.0".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
			for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample25gaussian84$global[t$var68][j$var72] = false;
		}
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var72" with its value "var22".
			guard$sample25gaussian84$global[t$var68][var22] = false;
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$9 = 0.0;
		
		// Reduce for every value except a masked value which will be skipped.
		// 
		// Substituted "j$var28" with its value "var22".
		for(int cv$reduction471Index = 0; cv$reduction471Index < var22; cv$reduction471Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// l's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction471Index]);
		
		// Substituted "j$var28" with its value "var22".
		for(int cv$reduction471Index = (var22 + 1); cv$reduction471Index < noProducts; cv$reduction471Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// l's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction471Index]);
		
		// Copy the result of the reduction into the variable returned by the reduction.
		reduceVar$sum$9 = (Math.exp(cv$proposedValue) + reduceVar$sum$9);
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
			for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample25gaussian84$global[t$var68][j$var72]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample25gaussian84$global[t$var68][j$var72] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 85 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Processing sample task 85 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$3$var80" with its value "0.2".
					// 
					// cv$temp$2$var79's comment
					// Variable declaration of cv$temp$2$var79 moved.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - (((exped[j$var72] * Avail[t$var68][j$var72]) / (reduceVar$sum$9 / s)) * arrivals[t$var68])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
				}
			}
		}
		double traceTempVariable$var73$7_3 = Math.exp(cv$proposedValue);
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var72" with its value "var22".
			if(!guard$sample25gaussian84$global[t$var68][var22]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var72" with its value "var22".
				guard$sample25gaussian84$global[t$var68][var22] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 85 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// This value is not used before it is set again, so removing the value declaration.
				// 
				// Processing sample task 85 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$5$var80" with its value "0.2".
				// 
				// Substituted "j$var72" with its value "var22".
				// 
				// cv$temp$4$var79's comment
				// Variable declaration of cv$temp$4$var79 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Substituted "j$var72" with its value "var22".
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var68][var22] - (((traceTempVariable$var73$7_3 * Avail[t$var68][var22]) / denom) * arrivals[t$var68])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
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
			
			// Guards to ensure that sum is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 25 and consumer double 39.
			// 
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$10 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction524Index = 0; cv$reduction524Index < noProducts; cv$reduction524Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$10 = (reduceVar$sum$10 + exped[cv$reduction524Index]);
			
			// Write out the new sample value.
			sum = reduceVar$sum$10;
			
			// Write out the new sample value.
			// 
			// sum's comment
			// Write out the new sample value.
			denom = (reduceVar$sum$10 / s);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 53 drawn from Gamma 45. Inference was performed using a Gamma to
	// Poisson conjugate prior.
	private final void sample53(int var49, int threadID$cv$var49, Rng RNG$) {
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Variable to record the number of samples from consuming random variables.
		lambda[var49] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, arrivals[var49], 1);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 61 drawn from Poisson 57. Inference was performed using Metropolis-Hastings.
	private final void sample61(int t$var55, int threadID$cv$t$var55, Rng RNG$) {
		// The original value of the sample
		int cv$originalValue = arrivals[t$var55];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
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
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// cv$temp$0$var56's comment
			// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityPoisson(cv$originalValue, lambda[t$var55]);
			for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
				// Processing sample task 85 of consumer random variable null.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 85 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// This value is not used before it is set again, so removing the value declaration.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$2$var80" with its value "0.2".
				// 
				// Substituted "cv$temp$1$var79" with its value "var79".
				// 
				// Constructing a random variable input for use later.
				// 
				// Substituted "t$var68" with its value "t$var55".
				// 
				// Set the current value to the current state of the tree.
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var55][j$var72] - (((exped[j$var72] * Avail[t$var55][j$var72]) / denom) * cv$originalValue)) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		arrivals[t$var55] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// cv$temp$0$var56's comment
		// Constructing a random variable input for use later.
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityPoisson(cv$proposedValue, lambda[t$var55]);
		for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
			// Processing sample task 85 of consumer random variable null.
			// 
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 85 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "cv$temp$2$var80" with its value "0.2".
			// 
			// Substituted "cv$temp$1$var79" with its value "var79".
			// 
			// Constructing a random variable input for use later.
			// 
			// Substituted "t$var68" with its value "t$var55".
			cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var55][j$var72] - (((exped[j$var72] * Avail[t$var55][j$var72]) / denom) * cv$proposedValue)) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
		
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
			arrivals[t$var55] = cv$originalValue;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Constructor for guard$sample25gaussian84$global
		// 
		// Allocate scratch space.
		// Calculate the largest index of j that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_j$var72 = 0;
		if((0 < T))
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			cv$max_j$var72 = Math.max(0, noProducts);
		
		// Allocation of guard$sample25gaussian84$global for single threaded execution
		// 
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample25gaussian84$global = new boolean[Math.max(0, T)][cv$max_j$var72];
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
			Sales = new double[T][];
			for(int var63 = 0; var63 < T; var63 += 1)
				Sales[var63] = new double[noProducts];
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
				Sales[t$var68] = new double[noProducts];
		}
		
		// Constructor for logProbability$sample25
		logProbability$sample25 = new double[noProducts];
		
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
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$11 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$11 = (reduceVar$sum$11 + exped[cv$reduction38Index]);
			sum = reduceVar$sum$11;
			
			// Substituted "sum" with its value "reduceVar$sum$11".
			denom = (reduceVar$sum$11 / s);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample53)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
							lambda[var49] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample61)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1)
							arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var55]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample85)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$index$t$var68, int forEnd$index$t$var68, int threadID$index$t$var68, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t$var68 = forStart$index$t$var68; index$t$var68 < forEnd$index$t$var68; index$t$var68 += 1) {
							int t$var68 = index$t$var68;
							double[] weekly_sales = Sales[t$var68];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var72, int forEnd$j$var72, int threadID$j$var72, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var72 = forStart$j$var72; j$var72 < forEnd$j$var72; j$var72 += 1)
											weekly_sales[j$var72] = ((DistributionSampling.sampleGaussian(RNG$2) * 0.4472135954999579) + (((exped[j$var72] * Avail[t$var68][j$var72]) / denom) * arrivals[t$var68]));
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
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$13 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$13 = (reduceVar$sum$13 + exped[cv$reduction38Index]);
			sum = reduceVar$sum$13;
			
			// Substituted "sum" with its value "reduceVar$sum$13".
			denom = (reduceVar$sum$13 / s);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample53)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
							lambda[var49] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample61)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1)
							arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var55]);
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
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$12 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$12 = (reduceVar$sum$12 + exped[cv$reduction38Index]);
			sum = reduceVar$sum$12;
			
			// Substituted "sum" with its value "reduceVar$sum$12".
			denom = (reduceVar$sum$12 / s);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample53)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
							lambda[var49] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample61)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1)
							arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var55]);
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
			if(!fixedFlag$sample53)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
								sample53(var49, threadID$var49, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample61)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1)
								sample61(t$var55, threadID$t$var55, RNG$1);
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample61)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1)
								sample61(t$var55, threadID$t$var55, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample53)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
								sample53(var49, threadID$var49, RNG$1);
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
		logProbability$sum = 0.0;
		logProbability$ut = 0.0;
		logProbability$denom = 0.0;
		logProbability$exped = 0.0;
		if(!fixedProbFlag$sample25) {
			for(int var22 = 0; var22 < noProducts; var22 += 1)
				logProbability$sample25[var22] = 0.0;
		}
		logProbability$var45 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var50 = 0.0;
		logProbability$var57 = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample61)
			logProbability$var58 = 0.0;
		logProbability$var81 = 0.0;
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample85)
			logProbability$var82 = 0.0;
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
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		if(fixedFlag$sample61)
			logProbabilityValue$sample61();
		logProbabilityValue$sample85();
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
		logProbabilityValue$sample53();
		logProbabilityValue$sample61();
		logProbabilityValue$sample85();
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
		logProbabilityValue$sample53();
		logProbabilityValue$sample61();
		logProbabilityValue$sample85();
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
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$14 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$14 = (reduceVar$sum$14 + exped[cv$reduction38Index]);
			sum = reduceVar$sum$14;
			
			// Substituted "sum" with its value "reduceVar$sum$14".
			denom = (reduceVar$sum$14 / s);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample53)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
							lambda[var49] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample61)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1)
							arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var55]);
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
		// 
		// Deep copy between arrays
		int cv$length1 = Sales.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			double[] cv$source2 = ObsSales[cv$index1];
			double[] cv$target2 = Sales[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(setFlag$ut) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1)
							exped[j$var28] = Math.exp(ut[j$var28]);
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$15 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$15 = (reduceVar$sum$15 + exped[cv$reduction38Index]);
			sum = reduceVar$sum$15;
			
			// Substituted "sum" with its value "reduceVar$sum$15".
			denom = (reduceVar$sum$15 / s);
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\n\nmodel Vulcano2012basic(int noProducts, int T, int s, double[][] ObsSales, int[][] Avail) {\n    // Avail is the availability matrix, T-by-noProducts\n    // s is the normalization constant (market share), number between 0 and 1\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n\n    //exponentiate right here (in the non-basic models move to the for loop)\n    double[] exped = new double[noProducts];\n    for(int j : [0..noProducts)) {\n    exped[j] = exp(ut[j]);\n    }\n    double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n    double denom = sum/s;   // this is the sum of utilities plus normalized by s outside options\n\n    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector, or just use some priors\n    double[ ] lambda = gamma(10,10).sample(T);\n\n    // draw arrivals\n    int[] arrivals = new int[T];\n    for (int t : [0..T)){\n    arrivals[t]= poisson(lambda[t]).sample();\n    }\n\n    double[][] Sales = new double[T][noProducts];\n\n    for (int t:[0..T)){\n        // for each period t calculate choice probabilities\n        // (does it matter if choice probabilities or individual choices?)\n        // let's try aggregate first\n\n        double[] weekly_sales = new double[noProducts];\n        for (int j : [0..noProducts)) {\n            weekly_sales[j] = gaussian(exped[j]*Avail[t][j] /denom *arrivals[t], 0.2).sample();\n        }\n        // record sales for period t\n        Sales[t] = weekly_sales;\n                                }\n    // assert that generated sales match observed sales\n    Sales.observe(ObsSales);\n}";
	}
}