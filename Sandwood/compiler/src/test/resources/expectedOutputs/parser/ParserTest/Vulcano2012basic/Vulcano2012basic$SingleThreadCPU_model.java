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
	private double[] logProbability$sample61;
	private double[][] logProbability$sample85;
	private double logProbability$sum;
	private double logProbability$ut;
	private double logProbability$var18;
	private double logProbability$var45;
	private double logProbability$var50;
	private double[] logProbability$var57;
	private double[][] logProbability$var81;
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
		fixedProbFlag$sample25 = (fixedFlag$sample25 && fixedProbFlag$sample25);
		
		// Should the probability of sample 85 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample85 = (fixedFlag$sample25 && fixedProbFlag$sample85);
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
		fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedProbFlag$sample53);
		
		// Should the probability of sample 61 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample61 = (fixedFlag$sample53 && fixedProbFlag$sample61);
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
		fixedProbFlag$sample61 = (fixedFlag$sample61 && fixedProbFlag$sample61);
		
		// Should the probability of sample 85 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample85 = (fixedFlag$sample61 && fixedProbFlag$sample85);
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
		fixedProbFlag$sample85 = (fixedFlag$sample85 && fixedProbFlag$sample85);
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
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var22 = 0; var22 < noProducts; var22 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = ut[var22];
					{
						{
							double var16 = 0.0;
							double var17 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var16) / Math.sqrt(var17))) - (0.5 * Math.log(var17))));
							
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
				
				// Store the sample task probability
				logProbability$sample25[((var22 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that sum is only updated once for this probability.
				boolean cv$guard$sum = false;
				
				// Guard to ensure that denom is only updated once for this probability.
				boolean cv$guard$denom = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 25 and consumer double[] 31.
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$exped) {
								// Set the guard so the update is only applied once.
								cv$guard$exped = true;
								
								// Update the variable probability
								logProbability$exped = (logProbability$exped + cv$sampleProbability);
							}
						}
					}
				}
				
				// Looking for a path between Sample 25 and consumer double 39.
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							if(((0 <= j$var28) && (j$var28 < noProducts))) {
								{
									// If the probability of the variable has not already been updated
									if(!cv$guard$sum) {
										// Set the guard so the update is only applied once.
										cv$guard$sum = true;
										
										// Update the variable probability
										logProbability$sum = (logProbability$sum + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 25 and consumer double 40.
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							if(((0 <= j$var28) && (j$var28 < noProducts))) {
								{
									// If the probability of the variable has not already been updated
									if(!cv$guard$denom) {
										// Set the guard so the update is only applied once.
										cv$guard$denom = true;
										
										// Update the variable probability
										logProbability$denom = (logProbability$denom + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var18 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample25)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample25 = fixedFlag$sample25;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var22 = 0; var22 < noProducts; var22 += 1) {
				double cv$sampleValue = logProbability$sample25[((var22 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that sum is only updated once for this probability.
				boolean cv$guard$sum = false;
				
				// Guard to ensure that denom is only updated once for this probability.
				boolean cv$guard$denom = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 25 and consumer double[] 31.
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$exped) {
								// Set the guard so the update is only applied once.
								cv$guard$exped = true;
								
								// Update the variable probability
								logProbability$exped = (logProbability$exped + cv$sampleValue);
							}
						}
					}
				}
				
				// Looking for a path between Sample 25 and consumer double 39.
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							if(((0 <= j$var28) && (j$var28 < noProducts))) {
								{
									// If the probability of the variable has not already been updated
									if(!cv$guard$sum) {
										// Set the guard so the update is only applied once.
										cv$guard$sum = true;
										
										// Update the variable probability
										logProbability$sum = (logProbability$sum + cv$sampleValue);
									}
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 25 and consumer double 40.
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							if(((0 <= j$var28) && (j$var28 < noProducts))) {
								{
									// If the probability of the variable has not already been updated
									if(!cv$guard$denom) {
										// Set the guard so the update is only applied once.
										cv$guard$denom = true;
										
										// Update the variable probability
										logProbability$denom = (logProbability$denom + cv$sampleValue);
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var18 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample25)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample53 using sampled
	// values.
	private final void logProbabilityValue$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample53) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var49 = 0; var49 < T; var49 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = lambda[var49];
					{
						{
							double var43 = 10.0;
							double var44 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var43, var44));
							
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
			logProbability$var45 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var50 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample53 = fixedFlag$sample53;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var50;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var45 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample61 using sampled
	// values.
	private final void logProbabilityValue$sample61() {
		// Determine if we need to calculate the values for sample task 61 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample61) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int t$var55 = 0; t$var55 < T; t$var55 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = arrivals[t$var55];
					{
						{
							double var56 = lambda[t$var55];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var56));
							
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
				logProbability$var57[((t$var55 - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample61[((t$var55 - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample61 = (fixedFlag$sample61 && fixedFlag$sample53);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int t$var55 = 0; t$var55 < T; t$var55 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample61[((t$var55 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var57[((t$var55 - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample85 using sampled
	// values.
	private final void logProbabilityValue$sample85() {
		// Determine if we need to calculate the values for sample task 85 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample85) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
				for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = Sales[t$var68][j$var72];
						{
							{
								double var79 = (((exped[j$var72] * Avail[t$var68][j$var72]) / denom) * arrivals[t$var68]);
								double var80 = 0.2;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var79) / Math.sqrt(var80))) - (0.5 * Math.log(var80))));
								
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
					logProbability$var81[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample85[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample85 = ((fixedFlag$sample85 && fixedFlag$sample25) && fixedFlag$sample61);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
				for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample85[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var81[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			
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
		
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability = 0.0;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		
		// Ensure the variance is at least 0.01
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		
		// The probability of the random variable generating the new sample value.
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// The value currently being tested
			double cv$currentValue;
			if((cv$valuePos == 0))
				// Set the current value to the current state of the tree.
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				
				// Update Sample and intermediate values
				{
					// Write out the value of the sample to a temporary variable prior to updating the
					// intermediate variables.
					double var23 = cv$proposedValue;
					ut[var22] = cv$currentValue;
					
					// Guards to ensure that exped is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 25 and consumer double[] 31.
					{
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								{
									exped[j$var28] = Math.exp(ut[j$var28]);
								}
							}
						}
					}
					
					// Guards to ensure that sum is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 25 and consumer double 39.
					{
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								if(((0 <= j$var28) && (j$var28 < noProducts))) {
									{
										{
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$0 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction134Index = 0; cv$reduction134Index < noProducts; cv$reduction134Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k = reduceVar$sum$0;
												
												// Set the right hand term to a value from the array exped
												double l = exped[cv$reduction134Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$0 = (k + l);
											}
											
											// Write out the new sample value.
											sum = reduceVar$sum$0;
										}
									}
								}
							}
						}
					}
					
					// Guards to ensure that denom is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 25 and consumer double 40.
					{
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								if(((0 <= j$var28) && (j$var28 < noProducts))) {
									{
										{
											// Write out the new sample value.
											denom = (sum / s);
										}
									}
								}
							}
						}
					}
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var16;
				{
					cv$temp$0$var16 = 0.0;
				}
				double cv$temp$1$var17;
				{
					cv$temp$1$var17 = 10.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var16) / Math.sqrt(cv$temp$1$var17))) - (0.5 * Math.log(cv$temp$1$var17))));
				
				// Processing random variable 81.
				{
					// Looking for a path between Sample 25 and consumer Gaussian 81.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample25gaussian84 = guard$sample25gaussian84$global;
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								if(((0 <= j$var28) && (j$var28 < noProducts))) {
									{
										for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
											for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
												// Set the flags to false
												guard$sample25gaussian84[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
									if((j$var28 == j$var72)) {
										for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
											// Set the flags to false
											guard$sample25gaussian84[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = false;
									}
								}
							}
						}
						double traceTempVariable$var29$6_1 = cv$currentValue;
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								double traceTempVariable$k$6_3 = Math.exp(traceTempVariable$var29$6_1);
								if(((0 <= j$var28) && (j$var28 < noProducts))) {
									{
										if((0 < noProducts)) {
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$1 = 0.0;
											
											// Reduce for every value except a masked value which will be skipped.
											for(int cv$reduction191Index = 0; cv$reduction191Index < j$var28; cv$reduction191Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k = reduceVar$sum$1;
												
												// Set the right hand term to a value from the array exped
												double l = exped[cv$reduction191Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$1 = (k + l);
											}
											for(int cv$reduction191Index = (j$var28 + 1); cv$reduction191Index < noProducts; cv$reduction191Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k = reduceVar$sum$1;
												
												// Set the right hand term to a value from the array exped
												double l = exped[cv$reduction191Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$1 = (k + l);
											}
											double cv$reduced38 = reduceVar$sum$1;
											
											// Copy the result of the reduction into the variable returned by the reduction.
											reduceVar$sum$1 = (traceTempVariable$k$6_3 + cv$reduced38);
											double traceTempVariable$sum$6_4 = reduceVar$sum$1;
											for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
												for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
													double traceTempVariable$denom$6_7 = (traceTempVariable$sum$6_4 / s);
													if(!guard$sample25gaussian84[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample25gaussian84[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = true;
														
														// Processing sample task 85 of consumer random variable null.
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
																			double cv$temp$2$var79;
																			{
																				// Constructing a random variable input for use later.
																				double var79 = (((exped[j$var72] * Avail[t$var68][j$var72]) / traceTempVariable$denom$6_7) * arrivals[t$var68]);
																				cv$temp$2$var79 = var79;
																			}
																			double cv$temp$3$var80;
																			{
																				cv$temp$3$var80 = 0.2;
																			}
																			
																			// Record the probability of sample task 85 generating output with current configuration.
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$2$var79) / Math.sqrt(cv$temp$3$var80))) - (0.5 * Math.log(cv$temp$3$var80)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$2$var79) / Math.sqrt(cv$temp$3$var80))) - (0.5 * Math.log(cv$temp$3$var80)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$2$var79) / Math.sqrt(cv$temp$3$var80))) - (0.5 * Math.log(cv$temp$3$var80))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$2$var79) / Math.sqrt(cv$temp$3$var80))) - (0.5 * Math.log(cv$temp$3$var80)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$2$var79) / Math.sqrt(cv$temp$3$var80))) - (0.5 * Math.log(cv$temp$3$var80)))));
																			}
																			
																			// Recorded the probability of reaching sample task 85 with the current configuration.
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
											}
										}
									}
								}
							}
						}
						double traceTempVariable$var29$7_1 = cv$currentValue;
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								double traceTempVariable$var73$7_3 = Math.exp(traceTempVariable$var29$7_1);
								for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
									if((j$var28 == j$var72)) {
										for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
											if(!guard$sample25gaussian84[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample25gaussian84[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = true;
												
												// Processing sample task 85 of consumer random variable null.
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
																	double cv$temp$4$var79;
																	{
																		// Constructing a random variable input for use later.
																		double var79 = (((traceTempVariable$var73$7_3 * Avail[t$var68][j$var72]) / denom) * arrivals[t$var68]);
																		cv$temp$4$var79 = var79;
																	}
																	double cv$temp$5$var80;
																	{
																		cv$temp$5$var80 = 0.2;
																	}
																	
																	// Record the probability of sample task 85 generating output with current configuration.
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$4$var79) / Math.sqrt(cv$temp$5$var80))) - (0.5 * Math.log(cv$temp$5$var80)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$4$var79) / Math.sqrt(cv$temp$5$var80))) - (0.5 * Math.log(cv$temp$5$var80)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$4$var79) / Math.sqrt(cv$temp$5$var80))) - (0.5 * Math.log(cv$temp$5$var80))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$4$var79) / Math.sqrt(cv$temp$5$var80))) - (0.5 * Math.log(cv$temp$5$var80)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$4$var79) / Math.sqrt(cv$temp$5$var80))) - (0.5 * Math.log(cv$temp$5$var80)))));
																	}
																	
																	// Recorded the probability of reaching sample task 85 with the current configuration.
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
									}
								}
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
			
			// Save the probability of the original value.
			if((cv$valuePos == 0))
				cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			
			// Save the probability of the proposed value.
			else
				cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// The probability ration for the proposed value and the current value.
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			double var23 = cv$originalValue;
			ut[var22] = var23;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 25 and consumer double[] 31.
			{
				for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
					if((var22 == j$var28)) {
						{
							exped[j$var28] = Math.exp(ut[j$var28]);
						}
					}
				}
			}
			
			// Guards to ensure that sum is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 25 and consumer double 39.
			{
				for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
					if((var22 == j$var28)) {
						if(((0 <= j$var28) && (j$var28 < noProducts))) {
							{
								{
									// Reduction of array exped
									// 
									// A generated name to prevent name collisions if the reduction is implemented more
									// than once in inference and probability code. Initialize the variable to the unit
									// value
									double reduceVar$sum$2 = 0.0;
									
									// For each index in the array to be reduced
									for(int cv$reduction244Index = 0; cv$reduction244Index < noProducts; cv$reduction244Index += 1) {
										// Set the left hand term of the reduction function to the return variable value.
										double k = reduceVar$sum$2;
										
										// Set the right hand term to a value from the array exped
										double l = exped[cv$reduction244Index];
										
										// Execute the reduction function, saving the result into the return value.
										// 
										// Copy the result of the reduction into the variable returned by the reduction.
										reduceVar$sum$2 = (k + l);
									}
									
									// Write out the new sample value.
									sum = reduceVar$sum$2;
								}
							}
						}
					}
				}
			}
			
			// Guards to ensure that denom is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 25 and consumer double 40.
			{
				for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
					if((var22 == j$var28)) {
						if(((0 <= j$var28) && (j$var28 < noProducts))) {
							{
								{
									// Write out the new sample value.
									denom = (sum / s);
								}
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 53 drawn from Gamma 45. Inference was performed using a Gamma to
	// Poisson conjugate prior.
	private final void sample53(int var49) {
		// Variable to store the sum of all the samples from consuming random variables.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		{
			// Processing random variable 57.
			{
				// Looking for a path between Sample 53 and consumer Poisson 57.
				{
					for(int t$var55 = 0; t$var55 < T; t$var55 += 1) {
						if((var49 == t$var55)) {
							// Processing sample task 61 of consumer random variable null.
							{
								{
									{
										{
											{
												// Add the value of a sample from consuming random variable var57 to the inference
												// state.
												cv$sum = (cv$sum + arrivals[t$var55]);
												cv$count = (cv$count + 1);
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
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		double var50 = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
		lambda[var49] = var50;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 61 drawn from Poisson 57. Inference was performed using Metropolis-Hastings.
	private final void sample61(int t$var55) {
		// The original value of the sample
		int cv$originalValue = arrivals[t$var55];
		
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability = 0.0;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		
		// Ensure the variance is at least 1
		if((cv$var < 1.0))
			cv$var = 1.0;
		
		// An offset for the current value
		double cv$offset = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		
		// Make sure the offset is not 0
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		
		// The proposed new value for the sample
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		
		// The probability of the random variable generating the new sample value.
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// The value currently being tested
			int cv$currentValue;
			if((cv$valuePos == 0))
				// Set the current value to the current state of the tree.
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				
				// Update Sample and intermediate values
				{
					// Write out the value of the sample to a temporary variable prior to updating the
					// intermediate variables.
					int var58 = cv$proposedValue;
					arrivals[t$var55] = cv$currentValue;
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var56;
				{
					// Constructing a random variable input for use later.
					double var56 = lambda[t$var55];
					cv$temp$0$var56 = var56;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$currentValue, cv$temp$0$var56));
				
				// Processing random variable 81.
				{
					// Looking for a path between Sample 61 and consumer Gaussian 81.
					{
						int traceTempVariable$var78$1_1 = cv$currentValue;
						for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
							if((t$var55 == t$var68)) {
								for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
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
													double cv$temp$1$var79;
													{
														// Constructing a random variable input for use later.
														double var79 = (((exped[j$var72] * Avail[t$var68][j$var72]) / denom) * traceTempVariable$var78$1_1);
														cv$temp$1$var79 = var79;
													}
													double cv$temp$2$var80;
													{
														cv$temp$2$var80 = 0.2;
													}
													
													// Record the probability of sample task 85 generating output with current configuration.
													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$1$var79) / Math.sqrt(cv$temp$2$var80))) - (0.5 * Math.log(cv$temp$2$var80)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$1$var79) / Math.sqrt(cv$temp$2$var80))) - (0.5 * Math.log(cv$temp$2$var80)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$1$var79) / Math.sqrt(cv$temp$2$var80))) - (0.5 * Math.log(cv$temp$2$var80))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$1$var79) / Math.sqrt(cv$temp$2$var80))) - (0.5 * Math.log(cv$temp$2$var80)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$1$var79) / Math.sqrt(cv$temp$2$var80))) - (0.5 * Math.log(cv$temp$2$var80)))));
													}
													
													// Recorded the probability of reaching sample task 85 with the current configuration.
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
			
			// Save the probability of the original value.
			if((cv$valuePos == 0))
				cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			
			// Save the probability of the proposed value.
			else
				cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// The probability ration for the proposed value and the current value.
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			int var58 = cv$originalValue;
			arrivals[t$var55] = var58;
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_t$var68 = 0;
		
		// Calculate the largest index of j that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_j$var72 = 0;
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
			cv$max_j$var72 = Math.max(cv$max_j$var72, ((noProducts - 0) / 1));
		cv$max_t$var68 = Math.max(cv$max_t$var68, ((T - 0) / 1));
		
		// Allocation of guard$sample25gaussian84$global for single threaded execution
		guard$sample25gaussian84$global = new boolean[cv$max_t$var68][cv$max_j$var72];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If ut has not been set already allocate space.
		if(!setFlag$ut) {
			// Constructor for ut
			{
				ut = new double[noProducts];
			}
		}
		
		// If exped has not been set already allocate space.
		if(!setFlag$exped) {
			// Constructor for exped
			{
				exped = new double[noProducts];
			}
		}
		
		// If lambda has not been set already allocate space.
		if(!setFlag$lambda) {
			// Constructor for lambda
			{
				lambda = new double[T];
			}
		}
		
		// If arrivals has not been set already allocate space.
		if(!setFlag$arrivals) {
			// Constructor for arrivals
			{
				arrivals = new int[T];
			}
		}
		
		// If Sales has not been set already allocate space.
		if(!setFlag$Sales) {
			// Constructor for Sales
			{
				Sales = new double[T][];
				for(int var63 = 0; var63 < T; var63 += 1)
					Sales[var63] = new double[noProducts];
				for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
					Sales[t$var68] = new double[noProducts];
			}
		}
		
		// Constructor for logProbability$sample25
		{
			logProbability$sample25 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var57
		{
			logProbability$var57 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample61
		{
			logProbability$sample61 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var81
		{
			logProbability$var81 = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
				logProbability$var81[((t$var68 - 0) / 1)] = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample85
		{
			logProbability$sample85 = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
				logProbability$sample85[((t$var68 - 0) / 1)] = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		for(int var22 = 0; var22 < noProducts; var22 += 1) {
			if(!fixedFlag$sample25)
				ut[var22] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
			if(!fixedFlag$sample25)
				exped[j$var28] = Math.exp(ut[j$var28]);
		}
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$3 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k = reduceVar$sum$3;
			
			// Set the right hand term to a value from the array exped
			double l = exped[cv$reduction38Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample25)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$3 = (k + l);
		}
		if(!fixedFlag$sample25)
			sum = reduceVar$sum$3;
		if(!fixedFlag$sample25)
			denom = (sum / s);
		for(int var49 = 0; var49 < T; var49 += 1) {
			if(!fixedFlag$sample53)
				lambda[var49] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var55 = 0; t$var55 < T; t$var55 += 1) {
			if(!fixedFlag$sample61)
				arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$, lambda[t$var55]);
		}
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
			double[] weekly_sales = Sales[t$var68];
			for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
				if(!fixedFlag$sample85)
					weekly_sales[j$var72] = ((Math.sqrt(0.2) * DistributionSampling.sampleGaussian(RNG$)) + (((exped[j$var72] * Avail[t$var68][j$var72]) / denom) * arrivals[t$var68]));
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var22 = 0; var22 < noProducts; var22 += 1) {
			if(!fixedFlag$sample25)
				ut[var22] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
			if(!fixedFlag$sample25)
				exped[j$var28] = Math.exp(ut[j$var28]);
		}
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$5 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k = reduceVar$sum$5;
			
			// Set the right hand term to a value from the array exped
			double l = exped[cv$reduction38Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample25)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$5 = (k + l);
		}
		if(!fixedFlag$sample25)
			sum = reduceVar$sum$5;
		if(!fixedFlag$sample25)
			denom = (sum / s);
		for(int var49 = 0; var49 < T; var49 += 1) {
			if(!fixedFlag$sample53)
				lambda[var49] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var55 = 0; t$var55 < T; t$var55 += 1) {
			if(!fixedFlag$sample61)
				arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$, lambda[t$var55]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var22 = 0; var22 < noProducts; var22 += 1) {
			if(!fixedFlag$sample25)
				ut[var22] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
			if(!fixedFlag$sample25)
				exped[j$var28] = Math.exp(ut[j$var28]);
		}
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$4 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k = reduceVar$sum$4;
			
			// Set the right hand term to a value from the array exped
			double l = exped[cv$reduction38Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample25)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$4 = (k + l);
		}
		if(!fixedFlag$sample25)
			sum = reduceVar$sum$4;
		if(!fixedFlag$sample25)
			denom = (sum / s);
		for(int var49 = 0; var49 < T; var49 += 1) {
			if(!fixedFlag$sample53)
				lambda[var49] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var55 = 0; t$var55 < T; t$var55 += 1) {
			if(!fixedFlag$sample61)
				arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$, lambda[t$var55]);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			for(int var22 = 0; var22 < noProducts; var22 += 1) {
				if(!fixedFlag$sample25)
					sample25(var22);
			}
			for(int var49 = 0; var49 < T; var49 += 1) {
				if(!fixedFlag$sample53)
					sample53(var49);
			}
			for(int t$var55 = 0; t$var55 < T; t$var55 += 1) {
				if(!fixedFlag$sample61)
					sample61(t$var55);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int t$var55 = (T - ((((T - 1) - 0) % 1) + 1)); t$var55 >= ((0 - 1) + 1); t$var55 -= 1) {
				if(!fixedFlag$sample61)
					sample61(t$var55);
			}
			for(int var49 = (T - ((((T - 1) - 0) % 1) + 1)); var49 >= ((0 - 1) + 1); var49 -= 1) {
				if(!fixedFlag$sample53)
					sample53(var49);
			}
			for(int var22 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); var22 >= ((0 - 1) + 1); var22 -= 1) {
				if(!fixedFlag$sample25)
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
				logProbability$sample25[((var22 - 0) / 1)] = 0.0;
		}
		logProbability$var45 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var50 = 0.0;
		for(int t$var55 = 0; t$var55 < T; t$var55 += 1)
			logProbability$var57[((t$var55 - 0) / 1)] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample61) {
			for(int t$var55 = 0; t$var55 < T; t$var55 += 1)
				logProbability$sample61[((t$var55 - 0) / 1)] = 0.0;
		}
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
			for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
				logProbability$var81[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = 0.0;
		}
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample85) {
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
				for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
					logProbability$sample85[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = 0.0;
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
		for(int var22 = 0; var22 < noProducts; var22 += 1) {
			if(!fixedFlag$sample25)
				ut[var22] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
			if(!fixedFlag$sample25)
				exped[j$var28] = Math.exp(ut[j$var28]);
		}
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$6 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k = reduceVar$sum$6;
			
			// Set the right hand term to a value from the array exped
			double l = exped[cv$reduction38Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample25)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$6 = (k + l);
		}
		if(!fixedFlag$sample25)
			sum = reduceVar$sum$6;
		if(!fixedFlag$sample25)
			denom = (sum / s);
		for(int var49 = 0; var49 < T; var49 += 1) {
			if(!fixedFlag$sample53)
				lambda[var49] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var55 = 0; t$var55 < T; t$var55 += 1) {
			if(!fixedFlag$sample61)
				arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$, lambda[t$var55]);
		}
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Deep copy between arrays
		double[][] cv$source1 = ObsSales;
		double[][] cv$target1 = Sales;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			double[] cv$source2 = cv$source1[cv$index1];
			double[] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
			if(setFlag$ut)
				exped[j$var28] = Math.exp(ut[j$var28]);
		}
		if(setFlag$ut) {
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$7 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k = reduceVar$sum$7;
				
				// Set the right hand term to a value from the array exped
				double l = exped[cv$reduction38Index];
				
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$7 = (k + l);
			}
			sum = reduceVar$sum$7;
		}
		if(setFlag$ut)
			denom = (sum / s);
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\n\nmodel Vulcano2012basic(int noProducts, int T, int s, double[][] ObsSales, int[][] Avail) {\n    // Avail is the availability matrix, T-by-noProducts\n    // s is the normalization constant (market share), number between 0 and 1\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n\n    //exponentiate right here (in the non-basic models move to the for loop)\n    double[] exped = new double[noProducts];\n    for(int j : [0..noProducts)) {\n    exped[j] = exp(ut[j]);\n    }\n    double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n    double denom = sum/s;   // this is the sum of utilities plus normalized by s outside options\n\n    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector, or just use some priors\n    double[ ] lambda = gamma(10,10).sample(T);\n\n    // draw arrivals\n    int[] arrivals = new int[T];\n    for (int t : [0..T)){\n    arrivals[t]= poisson(lambda[t]).sample();\n    }\n\n    double[][] Sales = new double[T][noProducts];\n\n    for (int t:[0..T)){\n        // for each period t calculate choice probabilities\n        // (does it matter if choice probabilities or individual choices?)\n        // let's try aggregate first\n\n        double[] weekly_sales = new double[noProducts];\n        for (int j : [0..noProducts)) {\n            weekly_sales[j] = gaussian(exped[j]*Avail[t][j] /denom *arrivals[t], 0.2).sample();\n        }\n        // record sales for period t\n        Sales[t] = weekly_sales;\n                                }\n    // assert that generated sales match observed sales\n    Sales.observe(ObsSales);\n}";
	}
}