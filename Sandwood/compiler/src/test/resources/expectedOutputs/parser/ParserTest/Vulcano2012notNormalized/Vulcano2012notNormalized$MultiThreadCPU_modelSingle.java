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
		fixedProbFlag$sample131 = (fixedFlag$sample131 && fixedProbFlag$sample131);
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
		
		// Should the probability of sample 131 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample131 = (fixedFlag$sample22 && fixedProbFlag$sample131);
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
		fixedProbFlag$sample54 = (fixedFlag$sample54 && fixedProbFlag$sample54);
		
		// Should the probability of sample 69 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample69 = (fixedFlag$sample54 && fixedProbFlag$sample69);
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
		fixedProbFlag$sample69 = (fixedFlag$sample69 && fixedProbFlag$sample69);
		
		// Should the probability of sample 131 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample131 = (fixedFlag$sample69 && fixedProbFlag$sample131);
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
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int[] cv$sampleValue = weekly_sales[((t$var81 - 0) / 1)];
					{
						{
							int var128 = arrivals[t$var81];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, weekly_rates[((t$var81 - 0) / 1)], (noProducts + 1), var128));
							
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
				logProbability$sample131[((t$var81 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that Sales is only updated once for this probability.
				boolean cv$guard$Sales = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
						// If the probability of the variable has not already been updated
						if(!cv$guard$Sales) {
							// Set the guard so the update is only applied once.
							cv$guard$Sales = true;
							
							// Update the variable probability
							logProbability$Sales = (logProbability$Sales + cv$sampleProbability);
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var129 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample131 = ((fixedFlag$sample131 && fixedFlag$sample22) && fixedFlag$sample69);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				double cv$sampleValue = logProbability$sample131[((t$var81 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that Sales is only updated once for this probability.
				boolean cv$guard$Sales = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
						// If the probability of the variable has not already been updated
						if(!cv$guard$Sales) {
							// Set the guard so the update is only applied once.
							cv$guard$Sales = true;
							
							// Update the variable probability
							logProbability$Sales = (logProbability$Sales + cv$sampleValue);
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var129 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			
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
				
				// Guard to ensure that weekly_ut is only updated once for this probability.
				boolean cv$guard$weekly_ut = false;
				
				// Guard to ensure that weekly_rates is only updated once for this probability.
				boolean cv$guard$weekly_rates = false;
				
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
				
				// Looking for a path between Sample 22 and consumer double[] 101.
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
								if((j$var34 == j$var96)) {
									for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$weekly_ut) {
											// Set the guard so the update is only applied once.
											cv$guard$weekly_ut = true;
											
											// Update the variable probability
											logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 22 and consumer double[] 127.
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
								if((j$var34 == j$var96)) {
									for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
										if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
											{
												for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
													// If the probability of the variable has not already been updated
													if(!cv$guard$weekly_rates) {
														// Set the guard so the update is only applied once.
														cv$guard$weekly_rates = true;
														
														// Update the variable probability
														logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleProbability);
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
								if((j$var34 == j$var96)) {
									for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
										if((j$var96 == j$var124)) {
											for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
												// If the probability of the variable has not already been updated
												if(!cv$guard$weekly_rates) {
													// Set the guard so the update is only applied once.
													cv$guard$weekly_rates = true;
													
													// Update the variable probability
													logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleProbability);
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
				
				// Guard to ensure that weekly_ut is only updated once for this probability.
				boolean cv$guard$weekly_ut = false;
				
				// Guard to ensure that weekly_rates is only updated once for this probability.
				boolean cv$guard$weekly_rates = false;
				
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
				
				// Looking for a path between Sample 22 and consumer double[] 101.
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
								if((j$var34 == j$var96)) {
									for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$weekly_ut) {
											// Set the guard so the update is only applied once.
											cv$guard$weekly_ut = true;
											
											// Update the variable probability
											logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
										}
									}
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 22 and consumer double[] 127.
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
								if((j$var34 == j$var96)) {
									for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
										if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
											{
												for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
													// If the probability of the variable has not already been updated
													if(!cv$guard$weekly_rates) {
														// Set the guard so the update is only applied once.
														cv$guard$weekly_rates = true;
														
														// Update the variable probability
														logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
								if((j$var34 == j$var96)) {
									for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
										if((j$var96 == j$var124)) {
											for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
												// If the probability of the variable has not already been updated
												if(!cv$guard$weekly_rates) {
													// Set the guard so the update is only applied once.
													cv$guard$weekly_rates = true;
													
													// Update the variable probability
													logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
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

	// Calculate the probability of the samples represented by sample54 using sampled
	// values.
	private final void logProbabilityValue$sample54() {
		// Determine if we need to calculate the values for sample task 54 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample54) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var53 = 0; var53 < T; var53 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = lambda[var53];
					{
						{
							double var40 = 10.0;
							double var41 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var40, var41));
							
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
			logProbability$var42 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var54 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample54)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample54 = fixedFlag$sample54;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var54;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var42 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample54)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample69 using sampled
	// values.
	private final void logProbabilityValue$sample69() {
		// Determine if we need to calculate the values for sample task 69 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample69) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int t$var66 = 0; t$var66 < T; t$var66 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = arrivals[t$var66];
					{
						{
							double var67 = lambda[t$var66];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var67));
							
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
			logProbability$var68 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var69 = cv$accumulator;
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample69)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample69 = (fixedFlag$sample69 && fixedFlag$sample54);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var69;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var68 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample69)
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
					
					// Guards to ensure that weekly_ut is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 22 and consumer double[] 101.
					{
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
											weekly_ut[((t$var81 - 0) / 1)][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
									}
								}
							}
						}
					}
					
					// Guards to ensure that weekly_rates is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 22 and consumer double[] 127.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample22put128 = guard$sample22put128$global;
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
											if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
												{
													for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1)
														// Set the flags to false
														guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
											if((j$var96 == j$var124)) {
												for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
													// Set the flags to false
													guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
											if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
												{
													for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
														if(!guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = true;
															{
																// Reduction of array weekly_ut
																// 
																// A generated name to prevent name collisions if the reduction is implemented more
																// than once in inference and probability code. Initialize the variable to the unit
																// value
																double reduceVar$denom$10 = 0.0;
																
																// For each index in the array to be reduced
																for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double k = reduceVar$denom$10;
																	
																	// Set the right hand term to a value from the array weekly_ut
																	double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$denom$10 = (k + l);
																}
																weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$10);
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
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
											if((j$var96 == j$var124)) {
												for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
													if(!guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = true;
														{
															// Reduction of array weekly_ut
															// 
															// A generated name to prevent name collisions if the reduction is implemented more
															// than once in inference and probability code. Initialize the variable to the unit
															// value
															double reduceVar$denom$11 = 0.0;
															
															// For each index in the array to be reduced
															for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
																// Set the left hand term of the reduction function to the return variable value.
																double k = reduceVar$denom$11;
																
																// Set the right hand term to a value from the array weekly_ut
																double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
																
																// Execute the reduction function, saving the result into the return value.
																// 
																// Copy the result of the reduction into the variable returned by the reduction.
																reduceVar$denom$11 = (k + l);
															}
															weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$11);
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
				
				// Processing random variable 129.
				{
					// Looking for a path between Sample 22 and consumer Multinomial 129.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[] guard$sample22multinomial130 = guard$sample22multinomial130$global;
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
											if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
												{
													// Set the flags to false
													guard$sample22multinomial130[((t$var81 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
											if((j$var96 == j$var124)) {
												for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
													// Set the flags to false
													guard$sample22multinomial130[((t$var81 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						double traceTempVariable$var35$9_1 = cv$currentValue;
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								double traceTempVariable$var97$9_3 = Math.exp(traceTempVariable$var35$9_1);
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
											double traceTempVariable$k$9_6 = (traceTempVariable$var97$9_3 * Avail[t$var81][j$var96]);
											if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
												{
													if((0 < (noProducts + 1))) {
														// Reduction of array weekly_ut
														// 
														// A generated name to prevent name collisions if the reduction is implemented more
														// than once in inference and probability code. Initialize the variable to the unit
														// value
														double reduceVar$denom$12 = 0.0;
														
														// Reduce for every value except a masked value which will be skipped.
														for(int cv$reduction1389Index = 0; cv$reduction1389Index < j$var96; cv$reduction1389Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$denom$12;
															
															// Set the right hand term to a value from the array weekly_ut
															double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction1389Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$denom$12 = (k + l);
														}
														for(int cv$reduction1389Index = (j$var96 + 1); cv$reduction1389Index < (noProducts + 1); cv$reduction1389Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$denom$12;
															
															// Set the right hand term to a value from the array weekly_ut
															double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction1389Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$denom$12 = (k + l);
														}
														double cv$reduced108 = reduceVar$denom$12;
														
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$denom$12 = (traceTempVariable$k$9_6 + cv$reduced108);
														double traceTempVariable$denom$9_7 = reduceVar$denom$12;
														if(!guard$sample22multinomial130[((t$var81 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample22multinomial130[((t$var81 - 0) / 1)] = true;
															
															// Processing sample task 131 of consumer random variable null.
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
																				double[] cv$temp$2$weekly_rates;
																				{
																					cv$temp$2$weekly_rates = weekly_rates[((t$var81 - 0) / 1)];
																				}
																				int cv$temp$3$$var1089;
																				{
																					// Constructing a random variable input for use later.
																					int $var1089 = (noProducts + 1);
																					cv$temp$3$$var1089 = $var1089;
																				}
																				int cv$temp$4$var128;
																				{
																					// Constructing a random variable input for use later.
																					int var128 = arrivals[t$var81];
																					cv$temp$4$var128 = var128;
																				}
																				
																				// Record the probability of sample task 131 generating output with current configuration.
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var1089, cv$temp$4$var128)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var1089, cv$temp$4$var128)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var1089, cv$temp$4$var128));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var1089, cv$temp$4$var128)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var1089, cv$temp$4$var128)));
																				}
																				
																				// Recorded the probability of reaching sample task 131 with the current configuration.
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
						}
						double traceTempVariable$var35$10_1 = cv$currentValue;
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								double traceTempVariable$var97$10_3 = Math.exp(traceTempVariable$var35$10_1);
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
											double traceTempVariable$var125$10_6 = (traceTempVariable$var97$10_3 * Avail[t$var81][j$var96]);
											for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
												if((j$var96 == j$var124)) {
													if(!guard$sample22multinomial130[((t$var81 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample22multinomial130[((t$var81 - 0) / 1)] = true;
														
														// Processing sample task 131 of consumer random variable null.
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
																			double[] cv$temp$5$weekly_rates;
																			{
																				cv$temp$5$weekly_rates = weekly_rates[((t$var81 - 0) / 1)];
																			}
																			int cv$temp$6$$var1094;
																			{
																				// Constructing a random variable input for use later.
																				int $var1094 = (noProducts + 1);
																				cv$temp$6$$var1094 = $var1094;
																			}
																			int cv$temp$7$var128;
																			{
																				// Constructing a random variable input for use later.
																				int var128 = arrivals[t$var81];
																				cv$temp$7$var128 = var128;
																			}
																			
																			// Record the probability of sample task 131 generating output with current configuration.
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var1094, cv$temp$7$var128)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var1094, cv$temp$7$var128)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var1094, cv$temp$7$var128));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var1094, cv$temp$7$var128)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var1094, cv$temp$7$var128)));
																			}
																			
																			// Recorded the probability of reaching sample task 131 with the current configuration.
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
			
			// Guards to ensure that weekly_ut is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 22 and consumer double[] 101.
			{
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
							if((j$var34 == j$var96)) {
								for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
									weekly_ut[((t$var81 - 0) / 1)][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
							}
						}
					}
				}
			}
			
			// Guards to ensure that weekly_rates is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 22 and consumer double[] 127.
			{
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[][] guard$sample22put128 = guard$sample22put128$global;
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
							if((j$var34 == j$var96)) {
								for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
									if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
										{
											for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1)
												// Set the flags to false
												guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = false;
										}
									}
								}
							}
						}
					}
				}
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
							if((j$var34 == j$var96)) {
								for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
									if((j$var96 == j$var124)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
											// Set the flags to false
											guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = false;
									}
								}
							}
						}
					}
				}
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
							if((j$var34 == j$var96)) {
								for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
									if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
										{
											for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
												if(!guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = true;
													{
														// Reduction of array weekly_ut
														// 
														// A generated name to prevent name collisions if the reduction is implemented more
														// than once in inference and probability code. Initialize the variable to the unit
														// value
														double reduceVar$denom$13 = 0.0;
														
														// For each index in the array to be reduced
														for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$denom$13;
															
															// Set the right hand term to a value from the array weekly_ut
															double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$denom$13 = (k + l);
														}
														weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$13);
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
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
							if((j$var34 == j$var96)) {
								for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
									if((j$var96 == j$var124)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
											if(!guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = true;
												{
													// Reduction of array weekly_ut
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													double reduceVar$denom$14 = 0.0;
													
													// For each index in the array to be reduced
													for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k = reduceVar$denom$14;
														
														// Set the right hand term to a value from the array weekly_ut
														double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$denom$14 = (k + l);
													}
													weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$14);
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
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 54 drawn from Gamma 42. Inference was performed using a Gamma to
	// Poisson conjugate prior.
	private final void sample54(int var53, int threadID$cv$var53, Rng RNG$) {
		// Variable to store the sum of all the samples from consuming random variables.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		{
			// Processing random variable 68.
			{
				// Looking for a path between Sample 54 and consumer Poisson 68.
				{
					for(int t$var66 = 0; t$var66 < T; t$var66 += 1) {
						if((var53 == t$var66)) {
							// Processing sample task 69 of consumer random variable null.
							{
								{
									{
										{
											{
												// Add the value of a sample from consuming random variable var68 to the inference
												// state.
												cv$sum = (cv$sum + arrivals[t$var66]);
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
		double var54 = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
		lambda[var53] = var54;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 69 drawn from Poisson 68. Inference was performed using Metropolis-Hastings.
	private final void sample69(int t$var66, int threadID$cv$t$var66, Rng RNG$) {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		{
			// Metropolis-Hastings
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		
		// The original value of the sample
		int cv$originalValue = arrivals[t$var66];
		
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
					int var69 = cv$proposedValue;
					arrivals[t$var66] = cv$currentValue;
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var67;
				{
					// Constructing a random variable input for use later.
					double var67 = lambda[t$var66];
					cv$temp$0$var67 = var67;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$currentValue, cv$temp$0$var67));
				
				// Processing random variable 129.
				{
					// Looking for a path between Sample 69 and consumer Multinomial 129.
					{
						int traceTempVariable$var128$1_1 = cv$currentValue;
						for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
							if((t$var66 == t$var81)) {
								// Processing sample task 131 of consumer random variable null.
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
													double[] cv$temp$1$weekly_rates;
													{
														cv$temp$1$weekly_rates = weekly_rates[((t$var81 - 0) / 1)];
													}
													int cv$temp$2$$var1230;
													{
														// Constructing a random variable input for use later.
														int $var1230 = (noProducts + 1);
														cv$temp$2$$var1230 = $var1230;
													}
													int cv$temp$3$var128;
													{
														// Constructing a random variable input for use later.
														int var128 = traceTempVariable$var128$1_1;
														cv$temp$3$var128 = var128;
													}
													
													// Record the probability of sample task 131 generating output with current configuration.
													if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var1230, cv$temp$3$var128)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var1230, cv$temp$3$var128)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var1230, cv$temp$3$var128));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var1230, cv$temp$3$var128)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var1230, cv$temp$3$var128)));
													}
													
													// Recorded the probability of reaching sample task 131 with the current configuration.
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
			int var69 = cv$originalValue;
			arrivals[t$var66] = var69;
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for guard$sample22put128$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t$var81 = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var124 = 0;
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				cv$max_j$var124 = Math.max(cv$max_j$var124, (((noProducts + 1) - 0) / 1));
			cv$max_t$var81 = Math.max(cv$max_t$var81, ((T - 0) / 1));
			
			// Allocation of guard$sample22put128$global for single threaded execution
			guard$sample22put128$global = new boolean[cv$max_t$var81][cv$max_j$var124];
		}
		
		// Constructor for guard$sample22multinomial130$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t$var81 = 0;
			cv$max_t$var81 = Math.max(cv$max_t$var81, ((T - 0) / 1));
			
			// Allocation of guard$sample22multinomial130$global for single threaded execution
			guard$sample22multinomial130$global = new boolean[cv$max_t$var81];
		}
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
			Sales = new int[T][];
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				Sales[t$var81] = new int[noProducts];
		}
		
		// Constructor for weekly_rates
		{
			weekly_rates = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				weekly_rates[((t$var81 - 0) / 1)] = new double[(noProducts + 1)];
		}
		
		// Constructor for weekly_ut
		{
			weekly_ut = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				weekly_ut[((t$var81 - 0) / 1)] = new double[(noProducts + 1)];
		}
		
		// If weekly_sales has not been set already allocate space.
		if(!setFlag$weekly_sales) {
			// Constructor for weekly_sales
			{
				weekly_sales = new int[((((T - 1) - 0) / 1) + 1)][];
				for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
					weekly_sales[((t$var81 - 0) / 1)] = new int[(noProducts + 1)];
			}
		}
		
		// Constructor for logProbability$sample22
		{
			logProbability$sample22 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample131
		{
			logProbability$sample131 = new double[((((T - 1) - 0) / 1) + 1)];
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
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
						if(!fixedFlag$sample54)
							lambda[var53] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var66, int forEnd$t$var66, int threadID$t$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int t$var66 = forStart$t$var66; t$var66 < forEnd$t$var66; t$var66 += 1) {
						if(!fixedFlag$sample69)
							arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var66]);
					}
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
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var96, int forEnd$j$var96, int threadID$j$var96, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var96 = forStart$j$var96; j$var96 < forEnd$j$var96; j$var96 += 1) {
										if(!fixedFlag$sample22)
											weekly_ut[((t$var81 - 0) / 1)][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
									}
							}
						);
						weekly_ut[((t$var81 - 0) / 1)][noProducts] = 1.0;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$15 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$denom$15;
							
							// Set the right hand term to a value from the array weekly_ut
							double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!fixedFlag$sample22)
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$denom$15 = (k + l);
						}
						
						// Alternative name for reduceVar$denom$15 to make it effectively final.
						double reduceVar$denom$15$1 = reduceVar$denom$15;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, (noProducts + 1), 1,
							(int forStart$j$var124, int forEnd$j$var124, int threadID$j$var124, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var124 = forStart$j$var124; j$var124 < forEnd$j$var124; j$var124 += 1) {
										if(!fixedFlag$sample22)
											weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$15$1);
									}
							}
						);
						if(!fixedFlag$sample131)
							DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[((t$var81 - 0) / 1)], (noProducts + 1), arrivals[t$var81], weekly_sales[((t$var81 - 0) / 1)]);
						int[] observed_weekly_sales = Sales[t$var81];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var140, int forEnd$j$var140, int threadID$j$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var140 = forStart$j$var140; j$var140 < forEnd$j$var140; j$var140 += 1) {
										if(!fixedFlag$sample131)
											observed_weekly_sales[j$var140] = weekly_sales[((t$var81 - 0) / 1)][j$var140];
									}
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
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
						if(!fixedFlag$sample54)
							lambda[var53] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var66, int forEnd$t$var66, int threadID$t$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int t$var66 = forStart$t$var66; t$var66 < forEnd$t$var66; t$var66 += 1) {
						if(!fixedFlag$sample69)
							arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var66]);
					}
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
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var96, int forEnd$j$var96, int threadID$j$var96, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var96 = forStart$j$var96; j$var96 < forEnd$j$var96; j$var96 += 1) {
										if(!fixedFlag$sample22)
											weekly_ut[((t$var81 - 0) / 1)][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
									}
							}
						);
						weekly_ut[((t$var81 - 0) / 1)][noProducts] = 1.0;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$17 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$denom$17;
							
							// Set the right hand term to a value from the array weekly_ut
							double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!fixedFlag$sample22)
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$denom$17 = (k + l);
						}
						
						// Alternative name for reduceVar$denom$17 to make it effectively final.
						double reduceVar$denom$17$1 = reduceVar$denom$17;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, (noProducts + 1), 1,
							(int forStart$j$var124, int forEnd$j$var124, int threadID$j$var124, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var124 = forStart$j$var124; j$var124 < forEnd$j$var124; j$var124 += 1) {
										if(!fixedFlag$sample22)
											weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$17$1);
									}
							}
						);
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
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
						if(!fixedFlag$sample54)
							lambda[var53] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var66, int forEnd$t$var66, int threadID$t$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int t$var66 = forStart$t$var66; t$var66 < forEnd$t$var66; t$var66 += 1) {
						if(!fixedFlag$sample69)
							arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var66]);
					}
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
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var96, int forEnd$j$var96, int threadID$j$var96, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var96 = forStart$j$var96; j$var96 < forEnd$j$var96; j$var96 += 1) {
										if(!fixedFlag$sample22)
											weekly_ut[((t$var81 - 0) / 1)][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
									}
							}
						);
						weekly_ut[((t$var81 - 0) / 1)][noProducts] = 1.0;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$16 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$denom$16;
							
							// Set the right hand term to a value from the array weekly_ut
							double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!fixedFlag$sample22)
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$denom$16 = (k + l);
						}
						
						// Alternative name for reduceVar$denom$16 to make it effectively final.
						double reduceVar$denom$16$1 = reduceVar$denom$16;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, (noProducts + 1), 1,
							(int forStart$j$var124, int forEnd$j$var124, int threadID$j$var124, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var124 = forStart$j$var124; j$var124 < forEnd$j$var124; j$var124 += 1) {
										if(!fixedFlag$sample22)
											weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$16$1);
									}
							}
						);
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
				(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
							if(!fixedFlag$sample54)
								sample54(var53, threadID$var53, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var66, int forEnd$t$var66, int threadID$t$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var66 = forStart$t$var66; t$var66 < forEnd$t$var66; t$var66 += 1) {
							if(!fixedFlag$sample69)
								sample69(t$var66, threadID$t$var66, RNG$1);
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var66, int forEnd$t$var66, int threadID$t$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var66 = forStart$t$var66; t$var66 < forEnd$t$var66; t$var66 += 1) {
							if(!fixedFlag$sample69)
								sample69(t$var66, threadID$t$var66, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
							if(!fixedFlag$sample54)
								sample54(var53, threadID$var53, RNG$1);
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
		logProbability$weekly_ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$ut = 0.0;
		logProbability$weekly_rates = 0.0;
		if(!fixedProbFlag$sample22) {
			for(int var21 = 0; var21 < noProducts; var21 += 1)
				logProbability$sample22[((var21 - 0) / 1)] = 0.0;
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
				logProbability$sample131[((t$var81 - 0) / 1)] = 0.0;
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
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
						if(!fixedFlag$sample54)
							lambda[var53] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var66, int forEnd$t$var66, int threadID$t$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int t$var66 = forStart$t$var66; t$var66 < forEnd$t$var66; t$var66 += 1) {
						if(!fixedFlag$sample69)
							arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var66]);
					}
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
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var96, int forEnd$j$var96, int threadID$j$var96, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var96 = forStart$j$var96; j$var96 < forEnd$j$var96; j$var96 += 1) {
										if(!fixedFlag$sample22)
											weekly_ut[((t$var81 - 0) / 1)][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
									}
							}
						);
						weekly_ut[((t$var81 - 0) / 1)][noProducts] = 1.0;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$18 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$denom$18;
							
							// Set the right hand term to a value from the array weekly_ut
							double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!fixedFlag$sample22)
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$denom$18 = (k + l);
						}
						
						// Alternative name for reduceVar$denom$18 to make it effectively final.
						double reduceVar$denom$18$1 = reduceVar$denom$18;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, (noProducts + 1), 1,
							(int forStart$j$var124, int forEnd$j$var124, int threadID$j$var124, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var124 = forStart$j$var124; j$var124 < forEnd$j$var124; j$var124 += 1) {
										if(!fixedFlag$sample22)
											weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$18$1);
									}
							}
						);
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
		{
			// Deep copy between arrays
			int[][] cv$source1 = ObsSales;
			int[][] cv$target1 = Sales;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				int[] cv$source2 = cv$source1[cv$index1];
				int[] cv$target2 = cv$target1[cv$index1];
				int cv$length2 = cv$target2.length;
				for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
					cv$target2[cv$index2] = cv$source2[cv$index2];
			}
		}
		for(int t$var81 = (T - ((((T - 1) - 0) % 1) + 1)); t$var81 >= ((0 - 1) + 1); t$var81 -= 1) {
			for(int j$var140 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); j$var140 >= ((0 - 1) + 1); j$var140 -= 1) {
				int[] observed_weekly_sales;
				observed_weekly_sales = Sales[t$var81];
				weekly_sales[((t$var81 - 0) / 1)][j$var140] = observed_weekly_sales[j$var140];
			}
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
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var81, int forEnd$index$t$var81, int threadID$index$t$var81, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var81 = forStart$index$t$var81; index$t$var81 < forEnd$index$t$var81; index$t$var81 += 1) {
						int t$var81 = index$t$var81;
						int threadID$t$var81 = threadID$index$t$var81;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var96, int forEnd$j$var96, int threadID$j$var96, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var96 = forStart$j$var96; j$var96 < forEnd$j$var96; j$var96 += 1) {
										if(setFlag$ut)
											weekly_ut[((t$var81 - 0) / 1)][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
									}
							}
						);
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$19 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$denom$19;
							
							// Set the right hand term to a value from the array weekly_ut
							double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$denom$19 = (k + l);
						}
						
						// Alternative name for reduceVar$denom$19 to make it effectively final.
						double reduceVar$denom$19$1 = reduceVar$denom$19;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, (noProducts + 1), 1,
							(int forStart$j$var124, int forEnd$j$var124, int threadID$j$var124, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var124 = forStart$j$var124; j$var124 < forEnd$j$var124; j$var124 += 1) {
										if(setFlag$ut)
											weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$19$1);
									}
							}
						);
						if(setFlag$weekly_sales) {
							int[] observed_weekly_sales = Sales[t$var81];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var140, int forEnd$j$var140, int threadID$j$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var140 = forStart$j$var140; j$var140 < forEnd$j$var140; j$var140 += 1)
											observed_weekly_sales[j$var140] = weekly_sales[((t$var81 - 0) / 1)][j$var140];
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