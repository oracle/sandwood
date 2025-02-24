package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basic$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Vulcano2012basic$CoreInterface {
	
	// Declare the variables for the model.
	private int[][] Avail;
	private double[][] ObsSales;
	private double[][] Sales;
	private int T;
	private int[] arrivals;
	private double denom;
	private double[] exped;
	private boolean fixedFlag$sample127 = false;
	private boolean fixedFlag$sample22 = false;
	private boolean fixedFlag$sample67 = false;
	private boolean fixedFlag$sample82 = false;
	private boolean fixedProbFlag$sample127 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample67 = false;
	private boolean fixedProbFlag$sample82 = false;
	private boolean[][] guard$sample22gaussian126$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$arrivals;
	private double logProbability$denom;
	private double logProbability$exped;
	private double logProbability$lambda;
	private double[] logProbability$sample22;
	private double logProbability$sum;
	private double logProbability$ut;
	private double logProbability$var10;
	private double logProbability$var124;
	private double logProbability$var125;
	private double logProbability$var54;
	private double logProbability$var66;
	private double logProbability$var80;
	private double logProbability$var81;
	private int noProducts;
	private int s;
	private boolean setFlag$Sales = false;
	private boolean setFlag$arrivals = false;
	private boolean setFlag$lambda = false;
	private boolean setFlag$ut = false;
	private double sum;
	private boolean system$gibbsForward = true;
	private double[] ut;

	public Vulcano2012basic$SingleThreadCPU(ExecutionTarget target) {
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
		// Set flags for all the side effects of Sales including if probabilities need to
		// be updated.
		// Set Sales with flag to mark that it has been set so another array doesn't need
		// to be constructed
		Sales = cv$value;
		setFlag$Sales = true;
		
		// Unset the fixed probability flag for sample 127 as it depends on Sales.
		fixedProbFlag$sample127 = false;
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
		
		// Unset the fixed probability flag for sample 82 as it depends on arrivals.
		fixedProbFlag$sample82 = false;
		
		// Unset the fixed probability flag for sample 127 as it depends on arrivals.
		fixedProbFlag$sample127 = false;
	}

	// Getter for denom.
	@Override
	public final double get$denom() {
		return denom;
	}

	// Getter for exped.
	@Override
	public final double[] get$exped() {
		return exped;
	}

	// Getter for fixedFlag$sample127.
	@Override
	public final boolean get$fixedFlag$sample127() {
		return fixedFlag$sample127;
	}

	// Setter for fixedFlag$sample127.
	@Override
	public final void set$fixedFlag$sample127(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample127 including if probabilities
		// need to be updated.
		fixedFlag$sample127 = cv$value;
		
		// Should the probability of sample 127 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample127" with its value "cv$value".
		fixedProbFlag$sample127 = (cv$value && fixedProbFlag$sample127);
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
		
		// Should the probability of sample 127 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample22" with its value "cv$value".
		fixedProbFlag$sample127 = (cv$value && fixedProbFlag$sample127);
	}

	// Getter for fixedFlag$sample67.
	@Override
	public final boolean get$fixedFlag$sample67() {
		return fixedFlag$sample67;
	}

	// Setter for fixedFlag$sample67.
	@Override
	public final void set$fixedFlag$sample67(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample67 including if probabilities
		// need to be updated.
		fixedFlag$sample67 = cv$value;
		
		// Should the probability of sample 67 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample67" with its value "cv$value".
		fixedProbFlag$sample67 = (cv$value && fixedProbFlag$sample67);
		
		// Should the probability of sample 82 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample67" with its value "cv$value".
		fixedProbFlag$sample82 = (cv$value && fixedProbFlag$sample82);
	}

	// Getter for fixedFlag$sample82.
	@Override
	public final boolean get$fixedFlag$sample82() {
		return fixedFlag$sample82;
	}

	// Setter for fixedFlag$sample82.
	@Override
	public final void set$fixedFlag$sample82(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample82 including if probabilities
		// need to be updated.
		fixedFlag$sample82 = cv$value;
		
		// Should the probability of sample 82 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample82" with its value "cv$value".
		fixedProbFlag$sample82 = (cv$value && fixedProbFlag$sample82);
		
		// Should the probability of sample 127 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample82" with its value "cv$value".
		fixedProbFlag$sample127 = (cv$value && fixedProbFlag$sample127);
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
		
		// Unset the fixed probability flag for sample 67 as it depends on lambda.
		fixedProbFlag$sample67 = false;
		
		// Unset the fixed probability flag for sample 82 as it depends on lambda.
		fixedProbFlag$sample82 = false;
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
		
		// Unset the fixed probability flag for sample 127 as it depends on ut.
		fixedProbFlag$sample127 = false;
	}

	// Calculate the probability of the samples represented by sample127 using sampled
	// values.
	private final void logProbabilityValue$sample127() {
		// Determine if we need to calculate the values for sample task 127 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample127) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
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
					cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - (((exped[j$var115] * Avail[t$var105][j$var115]) / denom) * arrivals[t$var105])) / 0.4472135954999579))) + 0.8047189562170501);
			}
			logProbability$var124 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var125 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample127 = ((fixedFlag$sample127 && fixedFlag$sample22) && fixedFlag$sample82);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var124 = logProbability$var125;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$Sales = (logProbability$Sales + logProbability$var125);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var125);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var125);
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
				
				// Looking for a path between Sample 22 and consumer double 48.
				// 
				// Update the variable probability
				logProbability$sum = (logProbability$sum + cv$distributionAccumulator);
				
				// Update the variable probability
				logProbability$denom = (logProbability$denom + cv$distributionAccumulator);
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
				
				// Looking for a path between Sample 22 and consumer double 48.
				// 
				// Update the variable probability
				logProbability$sum = (logProbability$sum + cv$sampleValue);
				
				// Update the variable probability
				logProbability$denom = (logProbability$denom + cv$sampleValue);
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

	// Calculate the probability of the samples represented by sample67 using sampled
	// values.
	private final void logProbabilityValue$sample67() {
		// Determine if we need to calculate the values for sample task 67 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample67) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var65 = 0; var65 < T; var65 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGamma(lambda[var65], 10.0, 10.0));
			logProbability$var54 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var66 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample67)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample67 = fixedFlag$sample67;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var54 = logProbability$var66;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$lambda = (logProbability$lambda + logProbability$var66);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var66);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample67)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var66);
		}
	}

	// Calculate the probability of the samples represented by sample82 using sampled
	// values.
	private final void logProbabilityValue$sample82() {
		// Determine if we need to calculate the values for sample task 82 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample82) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityPoisson(arrivals[t$var78], lambda[t$var78]));
			logProbability$var80 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var81 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample82)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample82 = (fixedFlag$sample82 && fixedFlag$sample67);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var80 = logProbability$var81;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$arrivals = (logProbability$arrivals + logProbability$var81);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var81);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample82)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var81);
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
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var9" with its value "10.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample22gaussian126$global[t$var105][j$var115] = false;
			}
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var115" with its value "var21".
				guard$sample22gaussian126$global[t$var105][var21] = false;
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$1 = 0.0;
			
			// Reduce for every value except a masked value which will be skipped.
			// 
			// Substituted "j$var34" with its value "var21".
			for(int cv$reduction320Index = 0; cv$reduction320Index < var21; cv$reduction320Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$1 = (reduceVar$sum$1 + exped[cv$reduction320Index]);
			
			// Substituted "j$var34" with its value "var21".
			for(int cv$reduction320Index = (var21 + 1); cv$reduction320Index < noProducts; cv$reduction320Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$1 = (reduceVar$sum$1 + exped[cv$reduction320Index]);
			
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// Set the current value to the current state of the tree.
			reduceVar$sum$1 = (Math.exp(cv$originalValue) + reduceVar$sum$1);
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample22gaussian126$global[t$var105][j$var115]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample22gaussian126$global[t$var105][j$var115] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 127 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// This value is not used before it is set again, so removing the value declaration.
						// 
						// Processing sample task 127 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "cv$temp$3$var123" with its value "0.2".
						// 
						// cv$temp$2$var122's comment
						// Variable declaration of cv$temp$2$var122 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - (((exped[j$var115] * Avail[t$var105][j$var115]) / (reduceVar$sum$1 / s)) * arrivals[t$var105])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
					}
				}
			}
			
			// Set the current value to the current state of the tree.
			double traceTempVariable$var116$7_3 = Math.exp(cv$originalValue);
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var115" with its value "var21".
				if(!guard$sample22gaussian126$global[t$var105][var21])
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 127 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Processing sample task 127 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$5$var123" with its value "0.2".
					// 
					// Substituted "j$var115" with its value "var21".
					// 
					// cv$temp$4$var122's comment
					// Variable declaration of cv$temp$4$var122 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "j$var115" with its value "var21".
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var105][var21] - (((traceTempVariable$var116$7_3 * Avail[t$var105][var21]) / denom) * arrivals[t$var105])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
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
		
		// Guards to ensure that sum is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 22 and consumer double 48.
		// 
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$0 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// l's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$0 = (reduceVar$sum$0 + exped[cv$reduction42Index]);
		
		// Write out the new sample value.
		sum = reduceVar$sum$0;
		
		// Write out the new sample value.
		// 
		// sum's comment
		// Write out the new sample value.
		denom = (reduceVar$sum$0 / s);
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var9" with its value "10.0".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample22gaussian126$global[t$var105][j$var115] = false;
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var115" with its value "var21".
			guard$sample22gaussian126$global[t$var105][var21] = false;
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$1 = 0.0;
		
		// Reduce for every value except a masked value which will be skipped.
		// 
		// Substituted "j$var34" with its value "var21".
		for(int cv$reduction320Index = 0; cv$reduction320Index < var21; cv$reduction320Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// l's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$1 = (reduceVar$sum$1 + exped[cv$reduction320Index]);
		
		// Substituted "j$var34" with its value "var21".
		for(int cv$reduction320Index = (var21 + 1); cv$reduction320Index < noProducts; cv$reduction320Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// l's comment
			// Set the right hand term to a value from the array exped
			reduceVar$sum$1 = (reduceVar$sum$1 + exped[cv$reduction320Index]);
		
		// Copy the result of the reduction into the variable returned by the reduction.
		reduceVar$sum$1 = (Math.exp(cv$proposedValue) + reduceVar$sum$1);
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample22gaussian126$global[t$var105][j$var115]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample22gaussian126$global[t$var105][j$var115] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 127 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Processing sample task 127 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$3$var123" with its value "0.2".
					// 
					// cv$temp$2$var122's comment
					// Variable declaration of cv$temp$2$var122 moved.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - (((exped[j$var115] * Avail[t$var105][j$var115]) / (reduceVar$sum$1 / s)) * arrivals[t$var105])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
				}
			}
		}
		double traceTempVariable$var116$7_3 = Math.exp(cv$proposedValue);
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var115" with its value "var21".
			if(!guard$sample22gaussian126$global[t$var105][var21]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var115" with its value "var21".
				guard$sample22gaussian126$global[t$var105][var21] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 127 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// This value is not used before it is set again, so removing the value declaration.
				// 
				// Processing sample task 127 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$5$var123" with its value "0.2".
				// 
				// Substituted "j$var115" with its value "var21".
				// 
				// cv$temp$4$var122's comment
				// Variable declaration of cv$temp$4$var122 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Substituted "j$var115" with its value "var21".
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var105][var21] - (((traceTempVariable$var116$7_3 * Avail[t$var105][var21]) / denom) * arrivals[t$var105])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
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
			
			// Guards to ensure that sum is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 22 and consumer double 48.
			// 
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$2 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$2 = (reduceVar$sum$2 + exped[cv$reduction42Index]);
			
			// Write out the new sample value.
			sum = reduceVar$sum$2;
			
			// Write out the new sample value.
			// 
			// sum's comment
			// Write out the new sample value.
			denom = (reduceVar$sum$2 / s);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 67 drawn from Gamma 54. Inference was performed using a Gamma to
	// Poisson conjugate prior.
	private final void sample67(int var65) {
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Variable to record the number of samples from consuming random variables.
		lambda[var65] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, arrivals[var65], 1);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 82 drawn from Poisson 80. Inference was performed using Metropolis-Hastings.
	private final void sample82(int t$var78) {
		// The original value of the sample
		int cv$originalValue = arrivals[t$var78];
		
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
			// cv$temp$0$var79's comment
			// Constructing a random variable input for use later.
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityPoisson(cv$originalValue, lambda[t$var78]);
			for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
				// Processing sample task 127 of consumer random variable null.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 127 with the current configuration.
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
				// Substituted "cv$temp$2$var123" with its value "0.2".
				// 
				// cv$temp$1$var122's comment
				// Variable declaration of cv$temp$1$var122 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Substituted "t$var105" with its value "t$var78".
				// 
				// Set the current value to the current state of the tree.
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var78][j$var115] - (((exped[j$var115] * Avail[t$var78][j$var115]) / denom) * cv$originalValue)) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		arrivals[t$var78] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// cv$temp$0$var79's comment
		// Constructing a random variable input for use later.
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityPoisson(cv$proposedValue, lambda[t$var78]);
		for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
			// Processing sample task 127 of consumer random variable null.
			// 
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 127 with the current configuration.
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
			// Substituted "cv$temp$2$var123" with its value "0.2".
			// 
			// cv$temp$1$var122's comment
			// Variable declaration of cv$temp$1$var122 moved.
			// 
			// Constructing a random variable input for use later.
			// 
			// Substituted "t$var105" with its value "t$var78".
			cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var78][j$var115] - (((exped[j$var115] * Avail[t$var78][j$var115]) / denom) * cv$proposedValue)) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
		
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
			arrivals[t$var78] = cv$originalValue;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Constructor for guard$sample22gaussian126$global
		// 
		// Allocate scratch space.
		// Calculate the largest index of j that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_j$var115 = 0;
		if((0 < T))
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			cv$max_j$var115 = Math.max(0, noProducts);
		
		// Allocation of guard$sample22gaussian126$global for single threaded execution
		// 
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		guard$sample22gaussian126$global = new boolean[Math.max(0, T)][cv$max_j$var115];
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
		
		// If Sales has not been set already allocate space.
		if(!setFlag$Sales) {
			// Constructor for Sales
			Sales = new double[T][];
			for(int var93 = 0; var93 < T; var93 += 1)
				Sales[var93] = new double[noProducts];
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				Sales[t$var105] = new double[noProducts];
		}
		
		// Constructor for logProbability$sample22
		logProbability$sample22 = new double[noProducts];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample22) {
			for(int var21 = 0; var21 < noProducts; var21 += 1)
				ut[var21] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1)
				exped[j$var34] = Math.exp(ut[j$var34]);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$3 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$3 = (reduceVar$sum$3 + exped[cv$reduction42Index]);
			sum = reduceVar$sum$3;
			
			// Substituted "sum" with its value "reduceVar$sum$3".
			denom = (reduceVar$sum$3 / s);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample67) {
			for(int var65 = 0; var65 < T; var65 += 1)
				lambda[var65] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample82) {
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
				arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$, lambda[t$var78]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample127) {
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				double[] weekly_sales = Sales[t$var105];
				for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
					weekly_sales[j$var115] = ((DistributionSampling.sampleGaussian(RNG$) * 0.4472135954999579) + (((exped[j$var115] * Avail[t$var105][j$var115]) / denom) * arrivals[t$var105]));
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample22) {
			for(int var21 = 0; var21 < noProducts; var21 += 1)
				ut[var21] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1)
				exped[j$var34] = Math.exp(ut[j$var34]);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$5 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$5 = (reduceVar$sum$5 + exped[cv$reduction42Index]);
			sum = reduceVar$sum$5;
			
			// Substituted "sum" with its value "reduceVar$sum$5".
			denom = (reduceVar$sum$5 / s);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample67) {
			for(int var65 = 0; var65 < T; var65 += 1)
				lambda[var65] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample82) {
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
				arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$, lambda[t$var78]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample22) {
			for(int var21 = 0; var21 < noProducts; var21 += 1)
				ut[var21] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1)
				exped[j$var34] = Math.exp(ut[j$var34]);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$4 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$4 = (reduceVar$sum$4 + exped[cv$reduction42Index]);
			sum = reduceVar$sum$4;
			
			// Substituted "sum" with its value "reduceVar$sum$4".
			denom = (reduceVar$sum$4 / s);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample67) {
			for(int var65 = 0; var65 < T; var65 += 1)
				lambda[var65] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample82) {
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
				arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$, lambda[t$var78]);
		}
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
			if(!fixedFlag$sample67) {
				for(int var65 = 0; var65 < T; var65 += 1)
					sample67(var65);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample82) {
				for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
					sample82(t$var78);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample82) {
				for(int t$var78 = (T - 1); t$var78 >= 0; t$var78 -= 1)
					sample82(t$var78);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample67) {
				for(int var65 = (T - 1); var65 >= 0; var65 -= 1)
					sample67(var65);
			}
			
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
		logProbability$denom = 0.0;
		logProbability$exped = 0.0;
		logProbability$ut = 0.0;
		logProbability$sum = 0.0;
		if(!fixedProbFlag$sample22) {
			for(int var21 = 0; var21 < noProducts; var21 += 1)
				logProbability$sample22[var21] = 0.0;
		}
		logProbability$var54 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample67)
			logProbability$var66 = 0.0;
		logProbability$var80 = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample82)
			logProbability$var81 = 0.0;
		logProbability$var124 = 0.0;
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample127)
			logProbability$var125 = 0.0;
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
		if(fixedFlag$sample67)
			logProbabilityValue$sample67();
		if(fixedFlag$sample82)
			logProbabilityValue$sample82();
		logProbabilityValue$sample127();
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
		logProbabilityValue$sample67();
		logProbabilityValue$sample82();
		logProbabilityValue$sample127();
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
		logProbabilityValue$sample67();
		logProbabilityValue$sample82();
		logProbabilityValue$sample127();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample22) {
			for(int var21 = 0; var21 < noProducts; var21 += 1)
				ut[var21] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1)
				exped[j$var34] = Math.exp(ut[j$var34]);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$6 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$6 = (reduceVar$sum$6 + exped[cv$reduction42Index]);
			sum = reduceVar$sum$6;
			
			// Substituted "sum" with its value "reduceVar$sum$6".
			denom = (reduceVar$sum$6 / s);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample67) {
			for(int var65 = 0; var65 < T; var65 += 1)
				lambda[var65] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample82) {
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
				arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$, lambda[t$var78]);
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
			for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1)
				exped[j$var34] = Math.exp(ut[j$var34]);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$7 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$7 = (reduceVar$sum$7 + exped[cv$reduction42Index]);
			sum = reduceVar$sum$7;
			
			// Substituted "sum" with its value "reduceVar$sum$7".
			denom = (reduceVar$sum$7 / s);
		}
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
		     + "\n"
		     + "model Vulcano2012basic(int noProducts, int T, int s, double[][] ObsSales, int[][] Avail) {\n"
		     + "    // Avail is the availability matrix, T-by-noProducts\n"
		     + "    // s is the normalization constant (market share), number between 0 and 1\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = gaussian(0, 10).sample(noProducts);\n"
		     + "\n"
		     + "    //exponentiate right here (in the non-basic models move to the for loop)\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "    exped[j] = exp(ut[j]);\n"
		     + "    }\n"
		     + "    double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "    double denom = sum/s;   // this is the sum of utilities plus normalized by s outside options\n"
		     + "\n"
		     + "    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector, or just use some priors\n"
		     + "    double[ ] lambda = gamma(10,10).sample(T);\n"
		     + "\n"
		     + "    // draw arrivals\n"
		     + "    int[] arrivals = new int[T];\n"
		     + "    for (int t : [0..T)){\n"
		     + "    arrivals[t]= poisson(lambda[t]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    double[][] Sales = new double[T][noProducts];\n"
		     + "\n"
		     + "    for (int t:[0..T)){\n"
		     + "        // for each period t calculate choice probabilities\n"
		     + "        // (does it matter if choice probabilities or individual choices?)\n"
		     + "        // let's try aggregate first\n"
		     + "\n"
		     + "        double[] weekly_sales = new double[noProducts];\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_sales[j] = gaussian(exped[j]*Avail[t][j] /denom *arrivals[t], 0.2).sample();\n"
		     + "        }\n"
		     + "        // record sales for period t\n"
		     + "        Sales[t] = weekly_sales;\n"
		     + "                                }\n"
		     + "    // assert that generated sales match observed sales\n"
		     + "    Sales.observe(ObsSales);\n"
		     + "}";
	}
}