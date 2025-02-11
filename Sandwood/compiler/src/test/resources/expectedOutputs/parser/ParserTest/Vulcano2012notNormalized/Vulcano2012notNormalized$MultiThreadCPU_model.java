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
	private boolean fixedFlag$sample98 = false;
	private boolean fixedProbFlag$sample25 = false;
	private boolean fixedProbFlag$sample43 = false;
	private boolean fixedProbFlag$sample51 = false;
	private boolean fixedProbFlag$sample98 = false;
	private boolean[] guard$sample25multinomial97$global;
	private boolean[][] guard$sample25put95$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$lambda;
	private double[] logProbability$sample25;
	private double[] logProbability$sample51;
	private double[] logProbability$sample98;
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
		
		// Unset the fixed probability flag for sample 51 as it depends on arrivals.
		fixedProbFlag$sample51 = false;
		
		// Unset the fixed probability flag for sample 98 as it depends on arrivals.
		fixedProbFlag$sample98 = false;
	}

	// Getter for exped.
	@Override
	public final double[] get$exped() {
		return exped;
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
		
		// Should the probability of sample 98 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample98 = (fixedFlag$sample25 && fixedProbFlag$sample98);
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
		fixedProbFlag$sample43 = (fixedFlag$sample43 && fixedProbFlag$sample43);
		
		// Should the probability of sample 51 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample51 = (fixedFlag$sample43 && fixedProbFlag$sample51);
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
		fixedProbFlag$sample51 = (fixedFlag$sample51 && fixedProbFlag$sample51);
		
		// Should the probability of sample 98 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample98 = (fixedFlag$sample51 && fixedProbFlag$sample98);
	}

	// Getter for fixedFlag$sample98.
	@Override
	public final boolean get$fixedFlag$sample98() {
		return fixedFlag$sample98;
	}

	// Setter for fixedFlag$sample98.
	@Override
	public final void set$fixedFlag$sample98(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample98 including if probabilities
		// need to be updated.
		fixedFlag$sample98 = cv$value;
		
		// Should the probability of sample 98 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample98 = (fixedFlag$sample98 && fixedProbFlag$sample98);
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
		
		// Unset the fixed probability flag for sample 43 as it depends on lambda.
		fixedProbFlag$sample43 = false;
		
		// Unset the fixed probability flag for sample 51 as it depends on lambda.
		fixedProbFlag$sample51 = false;
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
		
		// Unset the fixed probability flag for sample 25 as it depends on ut.
		fixedProbFlag$sample25 = false;
		
		// Unset the fixed probability flag for sample 98 as it depends on ut.
		fixedProbFlag$sample98 = false;
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
				
				// Guard to ensure that weekly_ut is only updated once for this probability.
				boolean cv$guard$weekly_ut = false;
				
				// Guard to ensure that weekly_rates is only updated once for this probability.
				boolean cv$guard$weekly_rates = false;
				
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
				
				// Looking for a path between Sample 25 and consumer double[] 73.
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var28 == j$var68)) {
									for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
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
				
				// Looking for a path between Sample 25 and consumer double[] 91.
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var28 == j$var68)) {
									for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
										if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
											{
												for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
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
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var28 == j$var68)) {
									for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
										if((j$var68 == j$var88)) {
											for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
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
				
				// Guard to ensure that weekly_ut is only updated once for this probability.
				boolean cv$guard$weekly_ut = false;
				
				// Guard to ensure that weekly_rates is only updated once for this probability.
				boolean cv$guard$weekly_rates = false;
				
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
				
				// Looking for a path between Sample 25 and consumer double[] 73.
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var28 == j$var68)) {
									for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
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
				
				// Looking for a path between Sample 25 and consumer double[] 91.
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var28 == j$var68)) {
									for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
										if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
											{
												for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
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
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var28 == j$var68)) {
									for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
										if((j$var68 == j$var88)) {
											for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
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

	// Calculate the probability of the samples represented by sample43 using sampled
	// values.
	private final void logProbabilityValue$sample43() {
		// Determine if we need to calculate the values for sample task 43 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample43) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var40 = 0; var40 < T; var40 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = lambda[var40];
					{
						{
							double var34 = 10.0;
							double var35 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var34, var35));
							
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
			logProbability$var36 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var41 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample43)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample43 = fixedFlag$sample43;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var41;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var36 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample43)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
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
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = arrivals[t$var46];
					{
						{
							double var47 = lambda[t$var46];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var47));
							
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
				logProbability$var48[((t$var46 - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample51[((t$var46 - 0) / 1)] = cv$sampleProbability;
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
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample51[((t$var46 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var48[((t$var46 - 0) / 1)] = cv$rvAccumulator;
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

	// Calculate the probability of the samples represented by sample98 using sampled
	// values.
	private final void logProbabilityValue$sample98() {
		// Determine if we need to calculate the values for sample task 98 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample98) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int[] cv$sampleValue = weekly_sales[((t$var59 - 0) / 1)];
					{
						{
							int var92 = arrivals[t$var59];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, weekly_rates[((t$var59 - 0) / 1)], var92));
							
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
				logProbability$var93[((t$var59 - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample98[((t$var59 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that Sales is only updated once for this probability.
				boolean cv$guard$Sales = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					for(int j$var98 = 0; j$var98 < noProducts; j$var98 += 1) {
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
			
			// Update the variable probability
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample98 = ((fixedFlag$sample98 && fixedFlag$sample25) && fixedFlag$sample51);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample98[((t$var59 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var93[((t$var59 - 0) / 1)] = cv$rvAccumulator;
				
				// Guard to ensure that Sales is only updated once for this probability.
				boolean cv$guard$Sales = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					for(int j$var98 = 0; j$var98 < noProducts; j$var98 += 1) {
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
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// Metropolis-Hastings
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
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
					
					// Guards to ensure that weekly_ut is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 25 and consumer double[] 73.
					{
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
											weekly_ut[((t$var59 - 0) / 1)][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
									}
								}
							}
						}
					}
					
					// Guards to ensure that weekly_rates is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 25 and consumer double[] 91.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample25put95 = guard$sample25put95$global;
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
											if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
												{
													for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1)
														// Set the flags to false
														guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
											if((j$var68 == j$var88)) {
												for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
													// Set the flags to false
													guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
											if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
												{
													for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
														if(!guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = true;
															{
																// Reduction of array weekly_ut
																// 
																// A generated name to prevent name collisions if the reduction is implemented more
																// than once in inference and probability code. Initialize the variable to the unit
																// value
																double reduceVar$denom$10 = 0.0;
																
																// For each index in the array to be reduced
																for(int cv$reduction907Index = 0; cv$reduction907Index < (noProducts + 1); cv$reduction907Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double k = reduceVar$denom$10;
																	
																	// Set the right hand term to a value from the array weekly_ut
																	double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction907Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$denom$10 = (k + l);
																}
																weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$10);
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
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
											if((j$var68 == j$var88)) {
												for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
													if(!guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = true;
														{
															// Reduction of array weekly_ut
															// 
															// A generated name to prevent name collisions if the reduction is implemented more
															// than once in inference and probability code. Initialize the variable to the unit
															// value
															double reduceVar$denom$11 = 0.0;
															
															// For each index in the array to be reduced
															for(int cv$reduction83Index = 0; cv$reduction83Index < (noProducts + 1); cv$reduction83Index += 1) {
																// Set the left hand term of the reduction function to the return variable value.
																double k = reduceVar$denom$11;
																
																// Set the right hand term to a value from the array weekly_ut
																double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction83Index];
																
																// Execute the reduction function, saving the result into the return value.
																// 
																// Copy the result of the reduction into the variable returned by the reduction.
																reduceVar$denom$11 = (k + l);
															}
															weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$11);
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
				
				// Processing random variable 93.
				{
					// Looking for a path between Sample 25 and consumer Multinomial 93.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[] guard$sample25multinomial97 = guard$sample25multinomial97$global;
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
											if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
												{
													// Set the flags to false
													guard$sample25multinomial97[((t$var59 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
											if((j$var68 == j$var88)) {
												for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
													// Set the flags to false
													guard$sample25multinomial97[((t$var59 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						double traceTempVariable$var29$9_1 = cv$currentValue;
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								double traceTempVariable$var69$9_3 = Math.exp(traceTempVariable$var29$9_1);
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
											double traceTempVariable$k$9_6 = (traceTempVariable$var69$9_3 * Avail[t$var59][j$var68]);
											if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
												{
													if(((0 < weekly_ut[((t$var59 - 0) / 1)].length) && (0 < (noProducts + 1)))) {
														// Reduction of array weekly_ut
														// 
														// A generated name to prevent name collisions if the reduction is implemented more
														// than once in inference and probability code. Initialize the variable to the unit
														// value
														double reduceVar$denom$12 = 0.0;
														
														// Reduce for every value except a masked value which will be skipped.
														for(int cv$reduction998Index = 0; cv$reduction998Index < j$var68; cv$reduction998Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$denom$12;
															
															// Set the right hand term to a value from the array weekly_ut
															double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction998Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$denom$12 = (k + l);
														}
														for(int cv$reduction998Index = (j$var68 + 1); cv$reduction998Index < (noProducts + 1); cv$reduction998Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$denom$12;
															
															// Set the right hand term to a value from the array weekly_ut
															double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction998Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$denom$12 = (k + l);
														}
														double cv$reduced83 = reduceVar$denom$12;
														
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$denom$12 = (traceTempVariable$k$9_6 + cv$reduced83);
														double traceTempVariable$denom$9_7 = reduceVar$denom$12;
														if(!guard$sample25multinomial97[((t$var59 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample25multinomial97[((t$var59 - 0) / 1)] = true;
															
															// Processing sample task 98 of consumer random variable null.
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
																					cv$temp$2$weekly_rates = weekly_rates[((t$var59 - 0) / 1)];
																				}
																				int cv$temp$3$var92;
																				{
																					// Constructing a random variable input for use later.
																					int var92 = arrivals[t$var59];
																					cv$temp$3$var92 = var92;
																				}
																				
																				// Record the probability of sample task 98 generating output with current configuration.
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var92)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var92)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var92));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var92)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var92)));
																				}
																				
																				// Recorded the probability of reaching sample task 98 with the current configuration.
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
						double traceTempVariable$var29$10_1 = cv$currentValue;
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								double traceTempVariable$var69$10_3 = Math.exp(traceTempVariable$var29$10_1);
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
											double traceTempVariable$var89$10_6 = (traceTempVariable$var69$10_3 * Avail[t$var59][j$var68]);
											for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
												if((j$var68 == j$var88)) {
													if(!guard$sample25multinomial97[((t$var59 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample25multinomial97[((t$var59 - 0) / 1)] = true;
														
														// Processing sample task 98 of consumer random variable null.
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
																			double[] cv$temp$4$weekly_rates;
																			{
																				cv$temp$4$weekly_rates = weekly_rates[((t$var59 - 0) / 1)];
																			}
																			int cv$temp$5$var92;
																			{
																				// Constructing a random variable input for use later.
																				int var92 = arrivals[t$var59];
																				cv$temp$5$var92 = var92;
																			}
																			
																			// Record the probability of sample task 98 generating output with current configuration.
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var92)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var92)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var92));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var92)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var92)));
																			}
																			
																			// Recorded the probability of reaching sample task 98 with the current configuration.
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
			
			// Guards to ensure that weekly_ut is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 25 and consumer double[] 73.
			{
				for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
					if((var22 == j$var28)) {
						for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
							if((j$var28 == j$var68)) {
								for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
									weekly_ut[((t$var59 - 0) / 1)][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
							}
						}
					}
				}
			}
			
			// Guards to ensure that weekly_rates is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 25 and consumer double[] 91.
			{
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[][] guard$sample25put95 = guard$sample25put95$global;
				for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
					if((var22 == j$var28)) {
						for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
							if((j$var28 == j$var68)) {
								for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
									if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
										{
											for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1)
												// Set the flags to false
												guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = false;
										}
									}
								}
							}
						}
					}
				}
				for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
					if((var22 == j$var28)) {
						for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
							if((j$var28 == j$var68)) {
								for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
									if((j$var68 == j$var88)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
											// Set the flags to false
											guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = false;
									}
								}
							}
						}
					}
				}
				for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
					if((var22 == j$var28)) {
						for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
							if((j$var28 == j$var68)) {
								for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
									if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
										{
											for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
												if(!guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = true;
													{
														// Reduction of array weekly_ut
														// 
														// A generated name to prevent name collisions if the reduction is implemented more
														// than once in inference and probability code. Initialize the variable to the unit
														// value
														double reduceVar$denom$13 = 0.0;
														
														// For each index in the array to be reduced
														for(int cv$reduction1112Index = 0; cv$reduction1112Index < (noProducts + 1); cv$reduction1112Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$denom$13;
															
															// Set the right hand term to a value from the array weekly_ut
															double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction1112Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$denom$13 = (k + l);
														}
														weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$13);
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
				for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
					if((var22 == j$var28)) {
						for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
							if((j$var28 == j$var68)) {
								for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
									if((j$var68 == j$var88)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
											if(!guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = true;
												{
													// Reduction of array weekly_ut
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													double reduceVar$denom$14 = 0.0;
													
													// For each index in the array to be reduced
													for(int cv$reduction83Index = 0; cv$reduction83Index < (noProducts + 1); cv$reduction83Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k = reduceVar$denom$14;
														
														// Set the right hand term to a value from the array weekly_ut
														double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction83Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$denom$14 = (k + l);
													}
													weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$14);
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
	// by sample task 43 drawn from Gamma 36. Inference was performed using a Gamma to
	// Poisson conjugate prior.
	private final void sample43(int var40, int threadID$cv$var40, Rng RNG$) {
		// Variable to store the sum of all the samples from consuming random variables.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		{
			// Processing random variable 48.
			{
				// Looking for a path between Sample 43 and consumer Poisson 48.
				{
					for(int t$var46 = 0; t$var46 < T; t$var46 += 1) {
						if((var40 == t$var46)) {
							// Processing sample task 51 of consumer random variable null.
							{
								{
									{
										{
											{
												// Add the value of a sample from consuming random variable var48 to the inference
												// state.
												cv$sum = (cv$sum + arrivals[t$var46]);
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
		double var41 = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
		lambda[var40] = var41;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 51 drawn from Poisson 48. Inference was performed using Metropolis-Hastings.
	private final void sample51(int t$var46, int threadID$cv$t$var46, Rng RNG$) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// Metropolis-Hastings
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
		// The original value of the sample
		int cv$originalValue = arrivals[t$var46];
		
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
					int var49 = cv$proposedValue;
					arrivals[t$var46] = cv$currentValue;
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var47;
				{
					// Constructing a random variable input for use later.
					double var47 = lambda[t$var46];
					cv$temp$0$var47 = var47;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$currentValue, cv$temp$0$var47));
				
				// Processing random variable 93.
				{
					// Looking for a path between Sample 51 and consumer Multinomial 93.
					{
						int traceTempVariable$var92$1_1 = cv$currentValue;
						for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
							if((t$var46 == t$var59)) {
								// Processing sample task 98 of consumer random variable null.
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
														cv$temp$1$weekly_rates = weekly_rates[((t$var59 - 0) / 1)];
													}
													int cv$temp$2$var92;
													{
														// Constructing a random variable input for use later.
														int var92 = traceTempVariable$var92$1_1;
														cv$temp$2$var92 = var92;
													}
													
													// Record the probability of sample task 98 generating output with current configuration.
													if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var92)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var92)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var92));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var92)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var92)));
													}
													
													// Recorded the probability of reaching sample task 98 with the current configuration.
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
			int var49 = cv$originalValue;
			arrivals[t$var46] = var49;
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for guard$sample25put95$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t$var59 = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var88 = 0;
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				cv$max_j$var88 = Math.max(cv$max_j$var88, (((noProducts + 1) - 0) / 1));
			cv$max_t$var59 = Math.max(cv$max_t$var59, ((T - 0) / 1));
			
			// Allocation of guard$sample25put95$global for single threaded execution
			guard$sample25put95$global = new boolean[cv$max_t$var59][cv$max_j$var88];
		}
		
		// Constructor for guard$sample25multinomial97$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t$var59 = 0;
			cv$max_t$var59 = Math.max(cv$max_t$var59, ((T - 0) / 1));
			
			// Allocation of guard$sample25multinomial97$global for single threaded execution
			guard$sample25multinomial97$global = new boolean[cv$max_t$var59];
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
			for(int var54 = 0; var54 < T; var54 += 1)
				Sales[var54] = new int[noProducts];
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				Sales[t$var59] = new int[noProducts];
		}
		
		// Constructor for weekly_rates
		{
			weekly_rates = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				weekly_rates[((t$var59 - 0) / 1)] = new double[(noProducts + 1)];
		}
		
		// Constructor for weekly_ut
		{
			weekly_ut = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				weekly_ut[((t$var59 - 0) / 1)] = new double[(noProducts + 1)];
		}
		
		// If weekly_sales has not been set already allocate space.
		if(!setFlag$weekly_sales) {
			// Constructor for weekly_sales
			{
				weekly_sales = new int[((((T - 1) - 0) / 1) + 1)][];
				for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
					weekly_sales[((t$var59 - 0) / 1)] = new int[(noProducts + 1)];
			}
		}
		
		// Constructor for logProbability$sample25
		{
			logProbability$sample25 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var48
		{
			logProbability$var48 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample51
		{
			logProbability$sample51 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var93
		{
			logProbability$var93 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample98
		{
			logProbability$sample98 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1) {
						if(!fixedFlag$sample25)
							ut[var22] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1) {
						if(!fixedFlag$sample25)
							exped[j$var28] = Math.exp(ut[j$var28]);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1) {
						if(!fixedFlag$sample43)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1) {
						if(!fixedFlag$sample51)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!fixedFlag$sample25)
											weekly_ut[((t$var59 - 0) / 1)][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
									}
							}
						);
						weekly_ut[((t$var59 - 0) / 1)][noProducts] = 1.0;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$15 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction83Index = 0; cv$reduction83Index < (noProducts + 1); cv$reduction83Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$denom$15;
							
							// Set the right hand term to a value from the array weekly_ut
							double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction83Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!fixedFlag$sample25)
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$denom$15 = (k + l);
						}
						
						// Alternative value for reduceVar$denom$15 to make it effectively final.
						double reduceVar$denom$15$1 = reduceVar$denom$15;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, (noProducts + 1), 1,
							(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1) {
										if(!fixedFlag$sample25)
											weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$15$1);
									}
							}
						);
						if(!fixedFlag$sample98)
							DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[((t$var59 - 0) / 1)], arrivals[t$var59], weekly_sales[((t$var59 - 0) / 1)]);
						int[] observed_weekly_sales = Sales[t$var59];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var98, int forEnd$j$var98, int threadID$j$var98, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var98 = forStart$j$var98; j$var98 < forEnd$j$var98; j$var98 += 1) {
										if(!fixedFlag$sample98)
											observed_weekly_sales[j$var98] = weekly_sales[((t$var59 - 0) / 1)][j$var98];
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
			(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1) {
						if(!fixedFlag$sample25)
							ut[var22] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1) {
						if(!fixedFlag$sample25)
							exped[j$var28] = Math.exp(ut[j$var28]);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1) {
						if(!fixedFlag$sample43)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1) {
						if(!fixedFlag$sample51)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!fixedFlag$sample25)
											weekly_ut[((t$var59 - 0) / 1)][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
									}
							}
						);
						weekly_ut[((t$var59 - 0) / 1)][noProducts] = 1.0;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$17 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction83Index = 0; cv$reduction83Index < (noProducts + 1); cv$reduction83Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$denom$17;
							
							// Set the right hand term to a value from the array weekly_ut
							double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction83Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!fixedFlag$sample25)
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$denom$17 = (k + l);
						}
						
						// Alternative value for reduceVar$denom$17 to make it effectively final.
						double reduceVar$denom$17$1 = reduceVar$denom$17;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, (noProducts + 1), 1,
							(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1) {
										if(!fixedFlag$sample25)
											weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$17$1);
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
			(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1) {
						if(!fixedFlag$sample25)
							ut[var22] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1) {
						if(!fixedFlag$sample25)
							exped[j$var28] = Math.exp(ut[j$var28]);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1) {
						if(!fixedFlag$sample43)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1) {
						if(!fixedFlag$sample51)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!fixedFlag$sample25)
											weekly_ut[((t$var59 - 0) / 1)][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
									}
							}
						);
						weekly_ut[((t$var59 - 0) / 1)][noProducts] = 1.0;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$16 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction83Index = 0; cv$reduction83Index < (noProducts + 1); cv$reduction83Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$denom$16;
							
							// Set the right hand term to a value from the array weekly_ut
							double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction83Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!fixedFlag$sample25)
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$denom$16 = (k + l);
						}
						
						// Alternative value for reduceVar$denom$16 to make it effectively final.
						double reduceVar$denom$16$1 = reduceVar$denom$16;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, (noProducts + 1), 1,
							(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1) {
										if(!fixedFlag$sample25)
											weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$16$1);
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
			for(int var22 = 0; var22 < noProducts; var22 += 1) {
				if(!fixedFlag$sample25)
					sample25(var22);
			}
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1) {
							if(!fixedFlag$sample43)
								sample43(var40, threadID$var40, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1) {
							if(!fixedFlag$sample51)
								sample51(t$var46, threadID$t$var46, RNG$1);
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1) {
							if(!fixedFlag$sample51)
								sample51(t$var46, threadID$t$var46, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1) {
							if(!fixedFlag$sample43)
								sample43(var40, threadID$var40, RNG$1);
						}
				}
			);
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
		logProbability$ut = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$weekly_rates = 0.0;
		logProbability$exped = 0.0;
		if(!fixedProbFlag$sample25) {
			for(int var22 = 0; var22 < noProducts; var22 += 1)
				logProbability$sample25[((var22 - 0) / 1)] = 0.0;
		}
		logProbability$var36 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample43)
			logProbability$var41 = 0.0;
		for(int t$var46 = 0; t$var46 < T; t$var46 += 1)
			logProbability$var48[((t$var46 - 0) / 1)] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample51) {
			for(int t$var46 = 0; t$var46 < T; t$var46 += 1)
				logProbability$sample51[((t$var46 - 0) / 1)] = 0.0;
		}
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
			logProbability$var93[((t$var59 - 0) / 1)] = 0.0;
		logProbability$Sales = 0.0;
		logProbability$weekly_sales = 0.0;
		if(!fixedProbFlag$sample98) {
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				logProbability$sample98[((t$var59 - 0) / 1)] = 0.0;
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
		logProbabilityValue$sample98();
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
		logProbabilityValue$sample98();
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
		logProbabilityValue$sample98();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1) {
						if(!fixedFlag$sample25)
							ut[var22] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1) {
						if(!fixedFlag$sample25)
							exped[j$var28] = Math.exp(ut[j$var28]);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1) {
						if(!fixedFlag$sample43)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1) {
						if(!fixedFlag$sample51)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!fixedFlag$sample25)
											weekly_ut[((t$var59 - 0) / 1)][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
									}
							}
						);
						weekly_ut[((t$var59 - 0) / 1)][noProducts] = 1.0;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$18 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction83Index = 0; cv$reduction83Index < (noProducts + 1); cv$reduction83Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$denom$18;
							
							// Set the right hand term to a value from the array weekly_ut
							double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction83Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!fixedFlag$sample25)
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$denom$18 = (k + l);
						}
						
						// Alternative value for reduceVar$denom$18 to make it effectively final.
						double reduceVar$denom$18$1 = reduceVar$denom$18;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, (noProducts + 1), 1,
							(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1) {
										if(!fixedFlag$sample25)
											weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$18$1);
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
		for(int t$var59 = (T - ((((T - 1) - 0) % 1) + 1)); t$var59 >= ((0 - 1) + 1); t$var59 -= 1) {
			int[] observed_weekly_sales;
			observed_weekly_sales = Sales[t$var59];
			for(int j$var98 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); j$var98 >= ((0 - 1) + 1); j$var98 -= 1)
				weekly_sales[((t$var59 - 0) / 1)][j$var98] = observed_weekly_sales[j$var98];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1) {
						if(setFlag$ut)
							exped[j$var28] = Math.exp(ut[j$var28]);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(setFlag$ut)
											weekly_ut[((t$var59 - 0) / 1)][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
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
						for(int cv$reduction83Index = 0; cv$reduction83Index < (noProducts + 1); cv$reduction83Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$denom$19;
							
							// Set the right hand term to a value from the array weekly_ut
							double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction83Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$denom$19 = (k + l);
						}
						
						// Alternative value for reduceVar$denom$19 to make it effectively final.
						double reduceVar$denom$19$1 = reduceVar$denom$19;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, (noProducts + 1), 1,
							(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1) {
										if(setFlag$ut)
											weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$19$1);
									}
							}
						);
						if(setFlag$weekly_sales) {
							int[] observed_weekly_sales = Sales[t$var59];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var98, int forEnd$j$var98, int threadID$j$var98, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var98 = forStart$j$var98; j$var98 < forEnd$j$var98; j$var98 += 1)
											observed_weekly_sales[j$var98] = weekly_sales[((t$var59 - 0) / 1)][j$var98];
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