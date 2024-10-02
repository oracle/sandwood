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
	private boolean fixedFlag$sample137 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample77 = false;
	private boolean fixedFlag$sample92 = false;
	private boolean fixedProbFlag$sample137 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample77 = false;
	private boolean fixedProbFlag$sample92 = false;
	private boolean[][] guard$sample32gaussian136$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$arrivals;
	private double logProbability$denom;
	private double logProbability$exped;
	private double logProbability$lambda;
	private double[][] logProbability$sample137;
	private double[] logProbability$sample32;
	private double[] logProbability$sample92;
	private double logProbability$sum;
	private double logProbability$ut;
	private double[][] logProbability$var132;
	private double logProbability$var18;
	private double logProbability$var62;
	private double logProbability$var74;
	private double[] logProbability$var88;
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
		
		// Unset the fixed probability flag for sample 137 as it depends on Sales.
		fixedProbFlag$sample137 = false;
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
		
		// Unset the fixed probability flag for sample 92 as it depends on arrivals.
		fixedProbFlag$sample92 = false;
		
		// Unset the fixed probability flag for sample 137 as it depends on arrivals.
		fixedProbFlag$sample137 = false;
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

	// Getter for fixedFlag$sample137.
	@Override
	public final boolean get$fixedFlag$sample137() {
		return fixedFlag$sample137;
	}

	// Setter for fixedFlag$sample137.
	@Override
	public final void set$fixedFlag$sample137(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample137 including if probabilities
		// need to be updated.
		fixedFlag$sample137 = cv$value;
		
		// Should the probability of sample 137 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample137 = (fixedFlag$sample137 && fixedProbFlag$sample137);
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
		fixedProbFlag$sample32 = (fixedFlag$sample32 && fixedProbFlag$sample32);
		
		// Should the probability of sample 137 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample137 = (fixedFlag$sample32 && fixedProbFlag$sample137);
	}

	// Getter for fixedFlag$sample77.
	@Override
	public final boolean get$fixedFlag$sample77() {
		return fixedFlag$sample77;
	}

	// Setter for fixedFlag$sample77.
	@Override
	public final void set$fixedFlag$sample77(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample77 including if probabilities
		// need to be updated.
		fixedFlag$sample77 = cv$value;
		
		// Should the probability of sample 77 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample77 = (fixedFlag$sample77 && fixedProbFlag$sample77);
		
		// Should the probability of sample 92 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample92 = (fixedFlag$sample77 && fixedProbFlag$sample92);
	}

	// Getter for fixedFlag$sample92.
	@Override
	public final boolean get$fixedFlag$sample92() {
		return fixedFlag$sample92;
	}

	// Setter for fixedFlag$sample92.
	@Override
	public final void set$fixedFlag$sample92(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample92 including if probabilities
		// need to be updated.
		fixedFlag$sample92 = cv$value;
		
		// Should the probability of sample 92 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample92 = (fixedFlag$sample92 && fixedProbFlag$sample92);
		
		// Should the probability of sample 137 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample137 = (fixedFlag$sample92 && fixedProbFlag$sample137);
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
		
		// Unset the fixed probability flag for sample 77 as it depends on lambda.
		fixedProbFlag$sample77 = false;
		
		// Unset the fixed probability flag for sample 92 as it depends on lambda.
		fixedProbFlag$sample92 = false;
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
		
		// Unset the fixed probability flag for sample 32 as it depends on ut.
		fixedProbFlag$sample32 = false;
		
		// Unset the fixed probability flag for sample 137 as it depends on ut.
		fixedProbFlag$sample137 = false;
	}

	// Calculate the probability of the samples represented by sample137 using sampled
	// values.
	private final void logProbabilityValue$sample137() {
		// Determine if we need to calculate the values for sample task 137 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample137) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
				for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = Sales[t$var113][j$var123];
						{
							{
								double var130 = (((exped[j$var123] * Avail[t$var113][j$var123]) / denom) * arrivals[t$var113]);
								double var131 = 0.2;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var130) / Math.sqrt(var131))) - (0.5 * Math.log(var131))));
								
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
					logProbability$var132[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample137[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample137 = ((fixedFlag$sample137 && fixedFlag$sample32) && fixedFlag$sample92);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
				for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample137[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var132[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			
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
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var29 = 0; var29 < noProducts; var29 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = ut[var29];
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
				logProbability$sample32[((var29 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that sum is only updated once for this probability.
				boolean cv$guard$sum = false;
				
				// Guard to ensure that denom is only updated once for this probability.
				boolean cv$guard$denom = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 32 and consumer double[] 45.
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
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
				
				// Looking for a path between Sample 32 and consumer double 56.
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							if(((0 <= j$var42) && (j$var42 < noProducts))) {
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
				
				// Looking for a path between Sample 32 and consumer double 57.
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							if(((0 <= j$var42) && (j$var42 < noProducts))) {
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
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample32 = fixedFlag$sample32;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var29 = 0; var29 < noProducts; var29 += 1) {
				double cv$sampleValue = logProbability$sample32[((var29 - 0) / 1)];
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
				// Looking for a path between Sample 32 and consumer double[] 45.
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
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
				
				// Looking for a path between Sample 32 and consumer double 56.
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							if(((0 <= j$var42) && (j$var42 < noProducts))) {
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
				
				// Looking for a path between Sample 32 and consumer double 57.
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							if(((0 <= j$var42) && (j$var42 < noProducts))) {
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
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample77 using sampled
	// values.
	private final void logProbabilityValue$sample77() {
		// Determine if we need to calculate the values for sample task 77 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample77) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var73 = 0; var73 < T; var73 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = lambda[var73];
					{
						{
							double var60 = 10.0;
							double var61 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var60, var61));
							
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
			logProbability$var62 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var74 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample77)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample77 = fixedFlag$sample77;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var74;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var62 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample77)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample92 using sampled
	// values.
	private final void logProbabilityValue$sample92() {
		// Determine if we need to calculate the values for sample task 92 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample92) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int t$var86 = 0; t$var86 < T; t$var86 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = arrivals[t$var86];
					{
						{
							double var87 = lambda[t$var86];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var87));
							
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
				logProbability$var88[((t$var86 - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample92[((t$var86 - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample92)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample92 = (fixedFlag$sample92 && fixedFlag$sample77);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int t$var86 = 0; t$var86 < T; t$var86 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample92[((t$var86 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var88[((t$var86 - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample92)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 32 drawn from Gaussian 18. Inference was performed using Metropolis-Hastings.
	private final void sample32(int var29) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// Metropolis-Hastings
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
		// The original value of the sample
		double cv$originalValue = ut[var29];
		
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
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
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
					double var30 = cv$proposedValue;
					ut[var29] = cv$currentValue;
					
					// Guards to ensure that exped is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 32 and consumer double[] 45.
					{
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								{
									exped[j$var42] = Math.exp(ut[j$var42]);
								}
							}
						}
					}
					
					// Guards to ensure that sum is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 32 and consumer double 56.
					{
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								if(((0 <= j$var42) && (j$var42 < noProducts))) {
									{
										{
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$0 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction52Index = 0; cv$reduction52Index < noProducts; cv$reduction52Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k = reduceVar$sum$0;
												
												// Set the right hand term to a value from the array exped
												double l = exped[cv$reduction52Index];
												
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
					// Looking for a path between Sample 32 and consumer double 57.
					{
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								if(((0 <= j$var42) && (j$var42 < noProducts))) {
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
				
				// Processing random variable 132.
				{
					// Looking for a path between Sample 32 and consumer Gaussian 132.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample32gaussian136 = guard$sample32gaussian136$global;
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								if(((0 <= j$var42) && (j$var42 < noProducts))) {
									{
										for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
											for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1)
												// Set the flags to false
												guard$sample32gaussian136[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var42 == j$var123)) {
										for(int t$var113 = 0; t$var113 < T; t$var113 += 1)
											// Set the flags to false
											guard$sample32gaussian136[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = false;
									}
								}
							}
						}
						double traceTempVariable$var43$6_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								double traceTempVariable$k$6_3 = Math.exp(traceTempVariable$var43$6_1);
								if(((0 <= j$var42) && (j$var42 < noProducts))) {
									{
										if((0 < noProducts)) {
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$1 = 0.0;
											
											// Reduce for every value except a masked value which will be skipped.
											for(int cv$reduction330Index = 0; cv$reduction330Index < j$var42; cv$reduction330Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k = reduceVar$sum$1;
												
												// Set the right hand term to a value from the array exped
												double l = exped[cv$reduction330Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$1 = (k + l);
											}
											for(int cv$reduction330Index = (j$var42 + 1); cv$reduction330Index < noProducts; cv$reduction330Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k = reduceVar$sum$1;
												
												// Set the right hand term to a value from the array exped
												double l = exped[cv$reduction330Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$1 = (k + l);
											}
											double cv$reduced52 = reduceVar$sum$1;
											
											// Copy the result of the reduction into the variable returned by the reduction.
											reduceVar$sum$1 = (traceTempVariable$k$6_3 + cv$reduced52);
											double traceTempVariable$sum$6_4 = reduceVar$sum$1;
											for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
												for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
													double traceTempVariable$denom$6_7 = (traceTempVariable$sum$6_4 / s);
													if(!guard$sample32gaussian136[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample32gaussian136[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = true;
														
														// Processing sample task 137 of consumer random variable null.
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
																			double cv$temp$2$var130;
																			{
																				// Constructing a random variable input for use later.
																				double var130 = (((exped[j$var123] * Avail[t$var113][j$var123]) / traceTempVariable$denom$6_7) * arrivals[t$var113]);
																				cv$temp$2$var130 = var130;
																			}
																			double cv$temp$3$var131;
																			{
																				cv$temp$3$var131 = 0.2;
																			}
																			
																			// Record the probability of sample task 137 generating output with current configuration.
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$2$var130) / Math.sqrt(cv$temp$3$var131))) - (0.5 * Math.log(cv$temp$3$var131)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$2$var130) / Math.sqrt(cv$temp$3$var131))) - (0.5 * Math.log(cv$temp$3$var131)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$2$var130) / Math.sqrt(cv$temp$3$var131))) - (0.5 * Math.log(cv$temp$3$var131))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$2$var130) / Math.sqrt(cv$temp$3$var131))) - (0.5 * Math.log(cv$temp$3$var131)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$2$var130) / Math.sqrt(cv$temp$3$var131))) - (0.5 * Math.log(cv$temp$3$var131)))));
																			}
																			
																			// Recorded the probability of reaching sample task 137 with the current configuration.
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
						double traceTempVariable$var43$7_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								double traceTempVariable$var124$7_3 = Math.exp(traceTempVariable$var43$7_1);
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var42 == j$var123)) {
										for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
											if(!guard$sample32gaussian136[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample32gaussian136[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = true;
												
												// Processing sample task 137 of consumer random variable null.
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
																	double cv$temp$4$var130;
																	{
																		// Constructing a random variable input for use later.
																		double var130 = (((traceTempVariable$var124$7_3 * Avail[t$var113][j$var123]) / denom) * arrivals[t$var113]);
																		cv$temp$4$var130 = var130;
																	}
																	double cv$temp$5$var131;
																	{
																		cv$temp$5$var131 = 0.2;
																	}
																	
																	// Record the probability of sample task 137 generating output with current configuration.
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$4$var130) / Math.sqrt(cv$temp$5$var131))) - (0.5 * Math.log(cv$temp$5$var131)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$4$var130) / Math.sqrt(cv$temp$5$var131))) - (0.5 * Math.log(cv$temp$5$var131)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$4$var130) / Math.sqrt(cv$temp$5$var131))) - (0.5 * Math.log(cv$temp$5$var131))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$4$var130) / Math.sqrt(cv$temp$5$var131))) - (0.5 * Math.log(cv$temp$5$var131)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$4$var130) / Math.sqrt(cv$temp$5$var131))) - (0.5 * Math.log(cv$temp$5$var131)))));
																	}
																	
																	// Recorded the probability of reaching sample task 137 with the current configuration.
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
			double var30 = cv$originalValue;
			ut[var29] = var30;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 32 and consumer double[] 45.
			{
				for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
					if((var29 == j$var42)) {
						{
							exped[j$var42] = Math.exp(ut[j$var42]);
						}
					}
				}
			}
			
			// Guards to ensure that sum is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 32 and consumer double 56.
			{
				for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
					if((var29 == j$var42)) {
						if(((0 <= j$var42) && (j$var42 < noProducts))) {
							{
								{
									// Reduction of array exped
									// 
									// A generated name to prevent name collisions if the reduction is implemented more
									// than once in inference and probability code. Initialize the variable to the unit
									// value
									double reduceVar$sum$2 = 0.0;
									
									// For each index in the array to be reduced
									for(int cv$reduction52Index = 0; cv$reduction52Index < noProducts; cv$reduction52Index += 1) {
										// Set the left hand term of the reduction function to the return variable value.
										double k = reduceVar$sum$2;
										
										// Set the right hand term to a value from the array exped
										double l = exped[cv$reduction52Index];
										
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
			// Looking for a path between Sample 32 and consumer double 57.
			{
				for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
					if((var29 == j$var42)) {
						if(((0 <= j$var42) && (j$var42 < noProducts))) {
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
	// by sample task 77 drawn from Gamma 62. Inference was performed using a Gamma to
	// Poisson conjugate prior.
	private final void sample77(int var73) {
		// Variable to store the sum of all the samples from consuming random variables.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		{
			// Processing random variable 88.
			{
				// Looking for a path between Sample 77 and consumer Poisson 88.
				{
					for(int t$var86 = 0; t$var86 < T; t$var86 += 1) {
						if((var73 == t$var86)) {
							// Processing sample task 92 of consumer random variable null.
							{
								{
									{
										{
											{
												// Add the value of a sample from consuming random variable var88 to the inference
												// state.
												cv$sum = (cv$sum + arrivals[t$var86]);
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
		double var74 = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
		lambda[var73] = var74;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 92 drawn from Poisson 88. Inference was performed using Metropolis-Hastings.
	private final void sample92(int t$var86) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// Metropolis-Hastings
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
		// The original value of the sample
		int cv$originalValue = arrivals[t$var86];
		
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
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
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
					int var89 = cv$proposedValue;
					arrivals[t$var86] = cv$currentValue;
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var87;
				{
					// Constructing a random variable input for use later.
					double var87 = lambda[t$var86];
					cv$temp$0$var87 = var87;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$currentValue, cv$temp$0$var87));
				
				// Processing random variable 132.
				{
					// Looking for a path between Sample 92 and consumer Gaussian 132.
					{
						int traceTempVariable$var129$1_1 = cv$currentValue;
						for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
							if((t$var86 == t$var113)) {
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
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
													double cv$temp$1$var130;
													{
														// Constructing a random variable input for use later.
														double var130 = (((exped[j$var123] * Avail[t$var113][j$var123]) / denom) * traceTempVariable$var129$1_1);
														cv$temp$1$var130 = var130;
													}
													double cv$temp$2$var131;
													{
														cv$temp$2$var131 = 0.2;
													}
													
													// Record the probability of sample task 137 generating output with current configuration.
													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$1$var130) / Math.sqrt(cv$temp$2$var131))) - (0.5 * Math.log(cv$temp$2$var131)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$1$var130) / Math.sqrt(cv$temp$2$var131))) - (0.5 * Math.log(cv$temp$2$var131)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$1$var130) / Math.sqrt(cv$temp$2$var131))) - (0.5 * Math.log(cv$temp$2$var131))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$1$var130) / Math.sqrt(cv$temp$2$var131))) - (0.5 * Math.log(cv$temp$2$var131)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$1$var130) / Math.sqrt(cv$temp$2$var131))) - (0.5 * Math.log(cv$temp$2$var131)))));
													}
													
													// Recorded the probability of reaching sample task 137 with the current configuration.
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
			int var89 = cv$originalValue;
			arrivals[t$var86] = var89;
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_t$var113 = 0;
		
		// Calculate the largest index of j that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_j$var123 = 0;
		for(int t$var113 = 0; t$var113 < T; t$var113 += 1)
			cv$max_j$var123 = Math.max(cv$max_j$var123, ((noProducts - 0) / 1));
		cv$max_t$var113 = Math.max(cv$max_t$var113, ((T - 0) / 1));
		
		// Allocation of guard$sample32gaussian136$global for single threaded execution
		guard$sample32gaussian136$global = new boolean[cv$max_t$var113][cv$max_j$var123];
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
		
		// Constructor for exped
		{
			exped = new double[noProducts];
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
				for(int var101 = 0; var101 < T; var101 += 1)
					Sales[var101] = new double[noProducts];
				for(int t$var113 = 0; t$var113 < T; t$var113 += 1)
					Sales[t$var113] = new double[noProducts];
			}
		}
		
		// Constructor for logProbability$sample32
		{
			logProbability$sample32 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var88
		{
			logProbability$var88 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample92
		{
			logProbability$sample92 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var132
		{
			logProbability$var132 = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var113 = 0; t$var113 < T; t$var113 += 1)
				logProbability$var132[((t$var113 - 0) / 1)] = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample137
		{
			logProbability$sample137 = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var113 = 0; t$var113 < T; t$var113 += 1)
				logProbability$sample137[((t$var113 - 0) / 1)] = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		for(int var29 = 0; var29 < noProducts; var29 += 1) {
			if(!fixedFlag$sample32)
				ut[var29] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
			if(!fixedFlag$sample32)
				exped[j$var42] = Math.exp(ut[j$var42]);
		}
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$3 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction52Index = 0; cv$reduction52Index < noProducts; cv$reduction52Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k = reduceVar$sum$3;
			
			// Set the right hand term to a value from the array exped
			double l = exped[cv$reduction52Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample32)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$3 = (k + l);
		}
		if(!fixedFlag$sample32)
			sum = reduceVar$sum$3;
		if(!fixedFlag$sample32)
			denom = (sum / s);
		for(int var73 = 0; var73 < T; var73 += 1) {
			if(!fixedFlag$sample77)
				lambda[var73] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var86 = 0; t$var86 < T; t$var86 += 1) {
			if(!fixedFlag$sample92)
				arrivals[t$var86] = DistributionSampling.samplePoisson(RNG$, lambda[t$var86]);
		}
		for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
			double[] weekly_sales = Sales[t$var113];
			for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
				if(!fixedFlag$sample137)
					weekly_sales[j$var123] = ((Math.sqrt(0.2) * DistributionSampling.sampleGaussian(RNG$)) + (((exped[j$var123] * Avail[t$var113][j$var123]) / denom) * arrivals[t$var113]));
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var29 = 0; var29 < noProducts; var29 += 1) {
			if(!fixedFlag$sample32)
				ut[var29] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
			if(!fixedFlag$sample32)
				exped[j$var42] = Math.exp(ut[j$var42]);
		}
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$5 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction52Index = 0; cv$reduction52Index < noProducts; cv$reduction52Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k = reduceVar$sum$5;
			
			// Set the right hand term to a value from the array exped
			double l = exped[cv$reduction52Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample32)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$5 = (k + l);
		}
		if(!fixedFlag$sample32)
			sum = reduceVar$sum$5;
		if(!fixedFlag$sample32)
			denom = (sum / s);
		for(int var73 = 0; var73 < T; var73 += 1) {
			if(!fixedFlag$sample77)
				lambda[var73] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var86 = 0; t$var86 < T; t$var86 += 1) {
			if(!fixedFlag$sample92)
				arrivals[t$var86] = DistributionSampling.samplePoisson(RNG$, lambda[t$var86]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var29 = 0; var29 < noProducts; var29 += 1) {
			if(!fixedFlag$sample32)
				ut[var29] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
			if(!fixedFlag$sample32)
				exped[j$var42] = Math.exp(ut[j$var42]);
		}
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$4 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction52Index = 0; cv$reduction52Index < noProducts; cv$reduction52Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k = reduceVar$sum$4;
			
			// Set the right hand term to a value from the array exped
			double l = exped[cv$reduction52Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample32)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$4 = (k + l);
		}
		if(!fixedFlag$sample32)
			sum = reduceVar$sum$4;
		if(!fixedFlag$sample32)
			denom = (sum / s);
		for(int var73 = 0; var73 < T; var73 += 1) {
			if(!fixedFlag$sample77)
				lambda[var73] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var86 = 0; t$var86 < T; t$var86 += 1) {
			if(!fixedFlag$sample92)
				arrivals[t$var86] = DistributionSampling.samplePoisson(RNG$, lambda[t$var86]);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			for(int var29 = 0; var29 < noProducts; var29 += 1) {
				if(!fixedFlag$sample32)
					sample32(var29);
			}
			for(int var73 = 0; var73 < T; var73 += 1) {
				if(!fixedFlag$sample77)
					sample77(var73);
			}
			for(int t$var86 = 0; t$var86 < T; t$var86 += 1) {
				if(!fixedFlag$sample92)
					sample92(t$var86);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int t$var86 = (T - ((((T - 1) - 0) % 1) + 1)); t$var86 >= ((0 - 1) + 1); t$var86 -= 1) {
				if(!fixedFlag$sample92)
					sample92(t$var86);
			}
			for(int var73 = (T - ((((T - 1) - 0) % 1) + 1)); var73 >= ((0 - 1) + 1); var73 -= 1) {
				if(!fixedFlag$sample77)
					sample77(var73);
			}
			for(int var29 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); var29 >= ((0 - 1) + 1); var29 -= 1) {
				if(!fixedFlag$sample32)
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
		logProbability$sum = 0.0;
		logProbability$denom = 0.0;
		logProbability$exped = 0.0;
		logProbability$ut = 0.0;
		if(!fixedProbFlag$sample32) {
			for(int var29 = 0; var29 < noProducts; var29 += 1)
				logProbability$sample32[((var29 - 0) / 1)] = 0.0;
		}
		logProbability$var62 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample77)
			logProbability$var74 = 0.0;
		for(int t$var86 = 0; t$var86 < T; t$var86 += 1)
			logProbability$var88[((t$var86 - 0) / 1)] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample92) {
			for(int t$var86 = 0; t$var86 < T; t$var86 += 1)
				logProbability$sample92[((t$var86 - 0) / 1)] = 0.0;
		}
		for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
			for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1)
				logProbability$var132[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = 0.0;
		}
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample137) {
			for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
				for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1)
					logProbability$sample137[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample77)
			logProbabilityValue$sample77();
		if(fixedFlag$sample92)
			logProbabilityValue$sample92();
		logProbabilityValue$sample137();
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
		logProbabilityValue$sample77();
		logProbabilityValue$sample92();
		logProbabilityValue$sample137();
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
		logProbabilityValue$sample77();
		logProbabilityValue$sample92();
		logProbabilityValue$sample137();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		for(int var29 = 0; var29 < noProducts; var29 += 1) {
			if(!fixedFlag$sample32)
				ut[var29] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
			if(!fixedFlag$sample32)
				exped[j$var42] = Math.exp(ut[j$var42]);
		}
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$6 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction52Index = 0; cv$reduction52Index < noProducts; cv$reduction52Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k = reduceVar$sum$6;
			
			// Set the right hand term to a value from the array exped
			double l = exped[cv$reduction52Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample32)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$6 = (k + l);
		}
		if(!fixedFlag$sample32)
			sum = reduceVar$sum$6;
		if(!fixedFlag$sample32)
			denom = (sum / s);
		for(int var73 = 0; var73 < T; var73 += 1) {
			if(!fixedFlag$sample77)
				lambda[var73] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var86 = 0; t$var86 < T; t$var86 += 1) {
			if(!fixedFlag$sample92)
				arrivals[t$var86] = DistributionSampling.samplePoisson(RNG$, lambda[t$var86]);
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
		for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
			if(setFlag$ut)
				exped[j$var42] = Math.exp(ut[j$var42]);
		}
		if(setFlag$ut) {
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$7 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction52Index = 0; cv$reduction52Index < noProducts; cv$reduction52Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k = reduceVar$sum$7;
				
				// Set the right hand term to a value from the array exped
				double l = exped[cv$reduction52Index];
				
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