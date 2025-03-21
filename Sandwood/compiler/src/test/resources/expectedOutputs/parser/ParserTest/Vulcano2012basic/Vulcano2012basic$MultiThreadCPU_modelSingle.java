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
		fixedProbFlag$sample22 = (fixedFlag$sample22 && fixedProbFlag$sample22);
		
		// Should the probability of sample 127 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample127 = (fixedFlag$sample22 && fixedProbFlag$sample127);
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
		fixedProbFlag$sample67 = (fixedFlag$sample67 && fixedProbFlag$sample67);
		
		// Should the probability of sample 82 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample82 = (fixedFlag$sample67 && fixedProbFlag$sample82);
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
		fixedProbFlag$sample82 = (fixedFlag$sample82 && fixedProbFlag$sample82);
		
		// Should the probability of sample 127 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample127 = (fixedFlag$sample82 && fixedProbFlag$sample127);
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
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = Sales[t$var105][j$var115];
						{
							{
								double var122 = (((exped[j$var115] * Avail[t$var105][j$var115]) / denom) * arrivals[t$var105]);
								double var123 = 0.2;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var122) / Math.sqrt(var123))) - (0.5 * Math.log(var123))));
								
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
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var124 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var125 = cv$accumulator;
			
			// Update the variable probability
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample127 = (fixedFlag$sample22 && fixedFlag$sample82);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var125;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var124 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample22 using sampled
	// values.
	private final void logProbabilityValue$sample22() {
		// Determine if we need to calculate the values for sample task 22 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample22) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var21 = 0; var21 < noProducts; var21 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = ut[var21];
					{
						{
							double var8 = 0.0;
							double var9 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var8) / Math.sqrt(var9))) - (0.5 * Math.log(var9))));
							
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
				logProbability$sample22[((var21 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that sum is only updated once for this probability.
				boolean cv$guard$sum = false;
				
				// Guard to ensure that denom is only updated once for this probability.
				boolean cv$guard$denom = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 22 and consumer double[] 37.
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
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
				
				// Looking for a path between Sample 22 and consumer double 48.
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							if(((0 <= j$var34) && (j$var34 < noProducts))) {
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
				
				// Looking for a path between Sample 22 and consumer double 49.
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							if(((0 <= j$var34) && (j$var34 < noProducts))) {
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
			logProbability$var10 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample22 = fixedFlag$sample22;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var21 = 0; var21 < noProducts; var21 += 1) {
				double cv$sampleValue = logProbability$sample22[((var21 - 0) / 1)];
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
				// Looking for a path between Sample 22 and consumer double[] 37.
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
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
				
				// Looking for a path between Sample 22 and consumer double 48.
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							if(((0 <= j$var34) && (j$var34 < noProducts))) {
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
				
				// Looking for a path between Sample 22 and consumer double 49.
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							if(((0 <= j$var34) && (j$var34 < noProducts))) {
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
			logProbability$var10 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample67 using sampled
	// values.
	private final void logProbabilityValue$sample67() {
		// Determine if we need to calculate the values for sample task 67 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample67) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var65 = 0; var65 < T; var65 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = lambda[var65];
					{
						{
							double var52 = 10.0;
							double var53 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var52, var53));
							
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
			logProbability$var54 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var66 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample67)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample67 = fixedFlag$sample67;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var66;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var54 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample67)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample82 using sampled
	// values.
	private final void logProbabilityValue$sample82() {
		// Determine if we need to calculate the values for sample task 82 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample82) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = arrivals[t$var78];
					{
						{
							double var79 = lambda[t$var78];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var79));
							
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
			logProbability$var80 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var81 = cv$accumulator;
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample82)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample82 = (fixedFlag$sample82 && fixedFlag$sample67);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var81;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var80 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample82)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 22 drawn from Gaussian 10. Inference was performed using Metropolis-Hastings.
	private final void sample22(int var21) {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		{
			// Metropolis-Hastings
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		
		// The original value of the sample
		double cv$originalValue = ut[var21];
		
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
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
					double var22 = cv$proposedValue;
					ut[var21] = cv$currentValue;
					
					// Guards to ensure that exped is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 22 and consumer double[] 37.
					{
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								{
									exped[j$var34] = Math.exp(ut[j$var34]);
								}
							}
						}
					}
					
					// Guards to ensure that sum is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 22 and consumer double 48.
					{
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								if(((0 <= j$var34) && (j$var34 < noProducts))) {
									{
										{
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$8 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k = reduceVar$sum$8;
												
												// Set the right hand term to a value from the array exped
												double l = exped[cv$reduction42Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$8 = (k + l);
											}
											
											// Write out the new sample value.
											sum = reduceVar$sum$8;
										}
									}
								}
							}
						}
					}
					
					// Guards to ensure that denom is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 22 and consumer double 49.
					{
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								if(((0 <= j$var34) && (j$var34 < noProducts))) {
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
				double cv$temp$0$var8;
				{
					cv$temp$0$var8 = 0.0;
				}
				double cv$temp$1$var9;
				{
					cv$temp$1$var9 = 10.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var8) / Math.sqrt(cv$temp$1$var9))) - (0.5 * Math.log(cv$temp$1$var9))));
				
				// Processing random variable 124.
				{
					// Looking for a path between Sample 22 and consumer Gaussian 124.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample22gaussian126 = guard$sample22gaussian126$global;
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								if(((0 <= j$var34) && (j$var34 < noProducts))) {
									{
										for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
											for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
												// Set the flags to false
												guard$sample22gaussian126[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
									if((j$var34 == j$var115)) {
										for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
											// Set the flags to false
											guard$sample22gaussian126[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)] = false;
									}
								}
							}
						}
						double traceTempVariable$var35$6_1 = cv$currentValue;
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								double traceTempVariable$k$6_3 = Math.exp(traceTempVariable$var35$6_1);
								if(((0 <= j$var34) && (j$var34 < noProducts))) {
									{
										if((0 < noProducts)) {
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$9 = 0.0;
											
											// Reduce for every value except a masked value which will be skipped.
											for(int cv$reduction779Index = 0; cv$reduction779Index < j$var34; cv$reduction779Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k = reduceVar$sum$9;
												
												// Set the right hand term to a value from the array exped
												double l = exped[cv$reduction779Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$9 = (k + l);
											}
											for(int cv$reduction779Index = (j$var34 + 1); cv$reduction779Index < noProducts; cv$reduction779Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k = reduceVar$sum$9;
												
												// Set the right hand term to a value from the array exped
												double l = exped[cv$reduction779Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$9 = (k + l);
											}
											double cv$reduced42 = reduceVar$sum$9;
											
											// Copy the result of the reduction into the variable returned by the reduction.
											reduceVar$sum$9 = (traceTempVariable$k$6_3 + cv$reduced42);
											double traceTempVariable$sum$6_4 = reduceVar$sum$9;
											for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
												for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
													double traceTempVariable$denom$6_7 = (traceTempVariable$sum$6_4 / s);
													if(!guard$sample22gaussian126[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample22gaussian126[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)] = true;
														
														// Processing sample task 127 of consumer random variable null.
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
																			double cv$temp$2$var122;
																			{
																				// Constructing a random variable input for use later.
																				double var122 = (((exped[j$var115] * Avail[t$var105][j$var115]) / traceTempVariable$denom$6_7) * arrivals[t$var105]);
																				cv$temp$2$var122 = var122;
																			}
																			double cv$temp$3$var123;
																			{
																				cv$temp$3$var123 = 0.2;
																			}
																			
																			// Record the probability of sample task 127 generating output with current configuration.
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$2$var122) / Math.sqrt(cv$temp$3$var123))) - (0.5 * Math.log(cv$temp$3$var123)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$2$var122) / Math.sqrt(cv$temp$3$var123))) - (0.5 * Math.log(cv$temp$3$var123)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$2$var122) / Math.sqrt(cv$temp$3$var123))) - (0.5 * Math.log(cv$temp$3$var123))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$2$var122) / Math.sqrt(cv$temp$3$var123))) - (0.5 * Math.log(cv$temp$3$var123)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$2$var122) / Math.sqrt(cv$temp$3$var123))) - (0.5 * Math.log(cv$temp$3$var123)))));
																			}
																			
																			// Recorded the probability of reaching sample task 127 with the current configuration.
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
						double traceTempVariable$var35$7_1 = cv$currentValue;
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								double traceTempVariable$var116$7_3 = Math.exp(traceTempVariable$var35$7_1);
								for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
									if((j$var34 == j$var115)) {
										for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
											if(!guard$sample22gaussian126[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample22gaussian126[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)] = true;
												
												// Processing sample task 127 of consumer random variable null.
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
																	double cv$temp$4$var122;
																	{
																		// Constructing a random variable input for use later.
																		double var122 = (((traceTempVariable$var116$7_3 * Avail[t$var105][j$var115]) / denom) * arrivals[t$var105]);
																		cv$temp$4$var122 = var122;
																	}
																	double cv$temp$5$var123;
																	{
																		cv$temp$5$var123 = 0.2;
																	}
																	
																	// Record the probability of sample task 127 generating output with current configuration.
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$4$var122) / Math.sqrt(cv$temp$5$var123))) - (0.5 * Math.log(cv$temp$5$var123)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$4$var122) / Math.sqrt(cv$temp$5$var123))) - (0.5 * Math.log(cv$temp$5$var123)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$4$var122) / Math.sqrt(cv$temp$5$var123))) - (0.5 * Math.log(cv$temp$5$var123))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$4$var122) / Math.sqrt(cv$temp$5$var123))) - (0.5 * Math.log(cv$temp$5$var123)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$4$var122) / Math.sqrt(cv$temp$5$var123))) - (0.5 * Math.log(cv$temp$5$var123)))));
																	}
																	
																	// Recorded the probability of reaching sample task 127 with the current configuration.
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
			double var22 = cv$originalValue;
			ut[var21] = var22;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 22 and consumer double[] 37.
			{
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						{
							exped[j$var34] = Math.exp(ut[j$var34]);
						}
					}
				}
			}
			
			// Guards to ensure that sum is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 22 and consumer double 48.
			{
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						if(((0 <= j$var34) && (j$var34 < noProducts))) {
							{
								{
									// Reduction of array exped
									// 
									// A generated name to prevent name collisions if the reduction is implemented more
									// than once in inference and probability code. Initialize the variable to the unit
									// value
									double reduceVar$sum$10 = 0.0;
									
									// For each index in the array to be reduced
									for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1) {
										// Set the left hand term of the reduction function to the return variable value.
										double k = reduceVar$sum$10;
										
										// Set the right hand term to a value from the array exped
										double l = exped[cv$reduction42Index];
										
										// Execute the reduction function, saving the result into the return value.
										// 
										// Copy the result of the reduction into the variable returned by the reduction.
										reduceVar$sum$10 = (k + l);
									}
									
									// Write out the new sample value.
									sum = reduceVar$sum$10;
								}
							}
						}
					}
				}
			}
			
			// Guards to ensure that denom is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 22 and consumer double 49.
			{
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						if(((0 <= j$var34) && (j$var34 < noProducts))) {
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
	// by sample task 67 drawn from Gamma 54. Inference was performed using a Gamma to
	// Poisson conjugate prior.
	private final void sample67(int var65, int threadID$cv$var65, Rng RNG$) {
		// Variable to store the sum of all the samples from consuming random variables.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		{
			// Processing random variable 80.
			{
				// Looking for a path between Sample 67 and consumer Poisson 80.
				{
					for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
						if((var65 == t$var78)) {
							// Processing sample task 82 of consumer random variable null.
							{
								{
									{
										{
											{
												// Add the value of a sample from consuming random variable var80 to the inference
												// state.
												cv$sum = (cv$sum + arrivals[t$var78]);
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
		double var66 = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
		lambda[var65] = var66;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 82 drawn from Poisson 80. Inference was performed using Metropolis-Hastings.
	private final void sample82(int t$var78, int threadID$cv$t$var78, Rng RNG$) {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		{
			// Metropolis-Hastings
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		
		// The original value of the sample
		int cv$originalValue = arrivals[t$var78];
		
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
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
					int var81 = cv$proposedValue;
					arrivals[t$var78] = cv$currentValue;
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var79;
				{
					// Constructing a random variable input for use later.
					double var79 = lambda[t$var78];
					cv$temp$0$var79 = var79;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$currentValue, cv$temp$0$var79));
				
				// Processing random variable 124.
				{
					// Looking for a path between Sample 82 and consumer Gaussian 124.
					{
						int traceTempVariable$var121$1_1 = cv$currentValue;
						for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
							if((t$var78 == t$var105)) {
								for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
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
													double cv$temp$1$var122;
													{
														// Constructing a random variable input for use later.
														double var122 = (((exped[j$var115] * Avail[t$var105][j$var115]) / denom) * traceTempVariable$var121$1_1);
														cv$temp$1$var122 = var122;
													}
													double cv$temp$2$var123;
													{
														cv$temp$2$var123 = 0.2;
													}
													
													// Record the probability of sample task 127 generating output with current configuration.
													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$1$var122) / Math.sqrt(cv$temp$2$var123))) - (0.5 * Math.log(cv$temp$2$var123)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$1$var122) / Math.sqrt(cv$temp$2$var123))) - (0.5 * Math.log(cv$temp$2$var123)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$1$var122) / Math.sqrt(cv$temp$2$var123))) - (0.5 * Math.log(cv$temp$2$var123))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$1$var122) / Math.sqrt(cv$temp$2$var123))) - (0.5 * Math.log(cv$temp$2$var123)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$1$var122) / Math.sqrt(cv$temp$2$var123))) - (0.5 * Math.log(cv$temp$2$var123)))));
													}
													
													// Recorded the probability of reaching sample task 127 with the current configuration.
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
			int var81 = cv$originalValue;
			arrivals[t$var78] = var81;
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_t$var105 = 0;
		
		// Calculate the largest index of j that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_j$var115 = 0;
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
			cv$max_j$var115 = Math.max(cv$max_j$var115, ((noProducts - 0) / 1));
		cv$max_t$var105 = Math.max(cv$max_t$var105, ((T - 0) / 1));
		
		// Allocation of guard$sample22gaussian126$global for single threaded execution
		guard$sample22gaussian126$global = new boolean[cv$max_t$var105][cv$max_j$var115];
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
		
		// Constructor for Sales
		{
			Sales = new double[T][];
			for(int var93 = 0; var93 < T; var93 += 1)
				Sales[var93] = new double[noProducts];
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				Sales[t$var105] = new double[noProducts];
		}
		
		// Constructor for logProbability$sample22
		{
			logProbability$sample22 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1) {
						if(!fixedFlag$sample22)
							ut[var21] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var34, int forEnd$j$var34, int threadID$j$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var34 = forStart$j$var34; j$var34 < forEnd$j$var34; j$var34 += 1) {
						if(!fixedFlag$sample22)
							exped[j$var34] = Math.exp(ut[j$var34]);
					}
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$11 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k = reduceVar$sum$11;
			
			// Set the right hand term to a value from the array exped
			double l = exped[cv$reduction42Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample22)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$11 = (k + l);
		}
		if(!fixedFlag$sample22)
			sum = reduceVar$sum$11;
		if(!fixedFlag$sample22)
			denom = (sum / s);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var65, int forEnd$var65, int threadID$var65, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var65 = forStart$var65; var65 < forEnd$var65; var65 += 1) {
						if(!fixedFlag$sample67)
							lambda[var65] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1) {
						if(!fixedFlag$sample82)
							arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var78]);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var105, int forEnd$index$t$var105, int threadID$index$t$var105, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var105 = forStart$index$t$var105; index$t$var105 < forEnd$index$t$var105; index$t$var105 += 1) {
						int t$var105 = index$t$var105;
						int threadID$t$var105 = threadID$index$t$var105;
						double[] weekly_sales = Sales[t$var105];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var115, int forEnd$j$var115, int threadID$j$var115, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var115 = forStart$j$var115; j$var115 < forEnd$j$var115; j$var115 += 1)
										weekly_sales[j$var115] = ((Math.sqrt(0.2) * DistributionSampling.sampleGaussian(RNG$2)) + (((exped[j$var115] * Avail[t$var105][j$var115]) / denom) * arrivals[t$var105]));
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
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1) {
						if(!fixedFlag$sample22)
							ut[var21] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var34, int forEnd$j$var34, int threadID$j$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var34 = forStart$j$var34; j$var34 < forEnd$j$var34; j$var34 += 1) {
						if(!fixedFlag$sample22)
							exped[j$var34] = Math.exp(ut[j$var34]);
					}
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$13 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k = reduceVar$sum$13;
			
			// Set the right hand term to a value from the array exped
			double l = exped[cv$reduction42Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample22)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$13 = (k + l);
		}
		if(!fixedFlag$sample22)
			sum = reduceVar$sum$13;
		if(!fixedFlag$sample22)
			denom = (sum / s);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var65, int forEnd$var65, int threadID$var65, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var65 = forStart$var65; var65 < forEnd$var65; var65 += 1) {
						if(!fixedFlag$sample67)
							lambda[var65] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1) {
						if(!fixedFlag$sample82)
							arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var78]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1) {
						if(!fixedFlag$sample22)
							ut[var21] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var34, int forEnd$j$var34, int threadID$j$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var34 = forStart$j$var34; j$var34 < forEnd$j$var34; j$var34 += 1) {
						if(!fixedFlag$sample22)
							exped[j$var34] = Math.exp(ut[j$var34]);
					}
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$12 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k = reduceVar$sum$12;
			
			// Set the right hand term to a value from the array exped
			double l = exped[cv$reduction42Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample22)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$12 = (k + l);
		}
		if(!fixedFlag$sample22)
			sum = reduceVar$sum$12;
		if(!fixedFlag$sample22)
			denom = (sum / s);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var65, int forEnd$var65, int threadID$var65, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var65 = forStart$var65; var65 < forEnd$var65; var65 += 1) {
						if(!fixedFlag$sample67)
							lambda[var65] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1) {
						if(!fixedFlag$sample82)
							arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var78]);
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			for(int var21 = 0; var21 < noProducts; var21 += 1) {
				if(!fixedFlag$sample22)
					sample22(var21);
			}
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var65, int forEnd$var65, int threadID$var65, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var65 = forStart$var65; var65 < forEnd$var65; var65 += 1) {
							if(!fixedFlag$sample67)
								sample67(var65, threadID$var65, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1) {
							if(!fixedFlag$sample82)
								sample82(t$var78, threadID$t$var78, RNG$1);
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1) {
							if(!fixedFlag$sample82)
								sample82(t$var78, threadID$t$var78, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var65, int forEnd$var65, int threadID$var65, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var65 = forStart$var65; var65 < forEnd$var65; var65 += 1) {
							if(!fixedFlag$sample67)
								sample67(var65, threadID$var65, RNG$1);
						}
				}
			);
			for(int var21 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); var21 >= ((0 - 1) + 1); var21 -= 1) {
				if(!fixedFlag$sample22)
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
		logProbability$sum = 0.0;
		logProbability$denom = 0.0;
		if(!fixedProbFlag$sample22) {
			for(int var21 = 0; var21 < noProducts; var21 += 1)
				logProbability$sample22[((var21 - 0) / 1)] = 0.0;
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
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1) {
						if(!fixedFlag$sample22)
							ut[var21] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var34, int forEnd$j$var34, int threadID$j$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var34 = forStart$j$var34; j$var34 < forEnd$j$var34; j$var34 += 1) {
						if(!fixedFlag$sample22)
							exped[j$var34] = Math.exp(ut[j$var34]);
					}
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$14 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k = reduceVar$sum$14;
			
			// Set the right hand term to a value from the array exped
			double l = exped[cv$reduction42Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample22)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$14 = (k + l);
		}
		if(!fixedFlag$sample22)
			sum = reduceVar$sum$14;
		if(!fixedFlag$sample22)
			denom = (sum / s);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var65, int forEnd$var65, int threadID$var65, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var65 = forStart$var65; var65 < forEnd$var65; var65 += 1) {
						if(!fixedFlag$sample67)
							lambda[var65] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1) {
						if(!fixedFlag$sample82)
							arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var78]);
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
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var34, int forEnd$j$var34, int threadID$j$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var34 = forStart$j$var34; j$var34 < forEnd$j$var34; j$var34 += 1) {
						if(setFlag$ut)
							exped[j$var34] = Math.exp(ut[j$var34]);
					}
			}
		);
		if(setFlag$ut) {
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$15 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k = reduceVar$sum$15;
				
				// Set the right hand term to a value from the array exped
				double l = exped[cv$reduction42Index];
				
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$15 = (k + l);
			}
			sum = reduceVar$sum$15;
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